DO $housing_occupancy_reset$
DECLARE 
        cur_living_unit_count CURSOR 
        FOR 
        SELECT living_unit_id, COUNT(living_unit_id) total
        FROM oms_owner.offender_bookings
        WHERE living_unit_id IS NOT NULL
		AND agy_loc_id <> 'TRN'
        GROUP BY living_unit_id;

-- Note: reserve_bed_locations is not included in the occupancy count.


     --the application allows maximum 4 levels; leafs can be configured at any level;  

        --the cursor selects the parents of all leafs regardless of the leaf level 
        cur_parents_of_leafs CURSOR 
        FOR 
        SELECT DISTINCT agl_3.parent_internal_location_id AS int_loc_id
        FROM oms_owner.agency_internal_locations agl_3
        WHERE agl_3.parent_internal_location_id IS NOT NULL
        AND NOT EXISTS ( SELECT 1
                           FROM oms_owner.agency_internal_locations agl_99
                          WHERE agl_99.parent_internal_location_id = agl_3.internal_location_id);

        --parents of records selected in cur_parents_of_leafs
        cur_level_2 CURSOR 
        FOR
        SELECT DISTINCT agl_2.parent_internal_location_id AS int_loc_id
        FROM oms_owner.agency_internal_locations agl_2
        WHERE agl_2.parent_internal_location_id IS NOT NULL
         AND agl_2.internal_location_id IN ( SELECT DISTINCT agl_3.parent_internal_location_id
                                               FROM oms_owner.agency_internal_locations agl_3
                                              WHERE agl_3.parent_internal_location_id IS NOT NULL
                                                AND NOT EXISTS ( SELECT 1
                                                                   FROM oms_owner.agency_internal_locations agl_99
                                                                  WHERE agl_99.parent_internal_location_id = agl_3.internal_location_id
                                                               )
                                           );

        --parents of records selected in cur_level_2  
        cur_level_1 CURSOR 
        FOR 
        SELECT DISTINCT agl_1.parent_internal_location_id AS int_loc_id
        FROM oms_owner.agency_internal_locations agl_1
        WHERE agl_1.parent_internal_location_id IS NOT NULL
          AND agl_1.internal_location_id IN (SELECT DISTINCT agl_2.parent_internal_location_id
                                               FROM oms_owner.agency_internal_locations agl_2
                                              WHERE agl_2.parent_internal_location_id IS NOT NULL
                                                AND agl_2.internal_location_id IN (SELECT DISTINCT agl_3.parent_internal_location_id
                                                                                     FROM oms_owner.agency_internal_locations agl_3
                                                                                    WHERE agl_3.parent_internal_location_id IS NOT NULL
                                                                                      AND NOT EXISTS (SELECT 1
                                                                                                        FROM oms_owner.agency_internal_locations agl_99
                                                                                                       WHERE agl_99.parent_internal_location_id = agl_3.internal_location_id
                                                                                                     )
                                                                                  )
                                            );
                                            

	     rec RECORD;
             each_living_unit RECORD;

BEGIN 

     --initially reset no_of_occupant to 0 for all internal location records

        UPDATE oms_owner.agency_internal_locations 
        SET no_of_occupant = 0, modify_datetime = current_timestamp, modify_user_id = 'OMS_OWNER';

     --calculate beds counts (these records will all be lowest level of housing as only the lowest level housing locations are offered as inmate living unit in the application)

        FOR each_living_unit IN cur_living_unit_count LOOP
            UPDATE oms_owner.agency_internal_locations 
               SET no_of_occupant = coalesce(each_living_unit.total, 0), modify_datetime = current_timestamp, modify_user_id = 'OMS_OWNER'
             WHERE internal_location_id = each_living_unit.living_unit_id;

        END LOOP;


     --calculate no_of_occupant for all parents of the bed counts (lowest level housing locations) calculated above;    
     --then we calculate the next level up based on the immediate children nodes only thus making sure we don't include all the sublevels multiple times;

        FOR rec IN cur_parents_of_leafs LOOP 
            --immediate children only
            UPDATE oms_owner.agency_internal_locations 
            SET no_of_occupant = ( SELECT coalesce(SUM(coalesce(agl_93.no_of_occupant, 0)), 0)  
                                           FROM oms_owner.agency_internal_locations agl_93
                                          WHERE agl_93.parent_internal_location_id = rec.int_loc_id
                                        )
										, modify_datetime = current_timestamp, modify_user_id = 'OMS_OWNER'
            WHERE internal_location_id = rec.int_loc_id;

        END LOOP;

        FOR rec IN cur_level_2 LOOP 
            --immediate children only
            UPDATE oms_owner.agency_internal_locations 
            SET no_of_occupant = (   SELECT coalesce(SUM(coalesce(agl_92.no_of_occupant, 0)), 0)  
                                             FROM oms_owner.agency_internal_locations agl_92
                                            WHERE agl_92.parent_internal_location_id = rec.int_loc_id
                                       )
									   , modify_datetime = current_timestamp, modify_user_id = 'OMS_OWNER'
            WHERE internal_location_id = rec.int_loc_id;

        END LOOP;

        FOR rec IN cur_level_1 LOOP 
            --immediate children only
            UPDATE oms_owner.agency_internal_locations 
            SET no_of_occupant = ( SELECT coalesce(SUM(coalesce(agl_91.no_of_occupant, 0)), 0)  
                                           FROM oms_owner.agency_internal_locations agl_91
                                          WHERE agl_91.parent_internal_location_id = rec.int_loc_id
                                       )
									   , modify_datetime = current_timestamp, modify_user_id = 'OMS_OWNER'
            WHERE internal_location_id = rec.int_loc_id;

        END LOOP;
END $housing_occupancy_reset$;
