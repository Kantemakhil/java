
-- records of location LRP-LRP (should be 0 before)
SELECT count(*)
  FROM agency_internal_locations
 WHERE agy_loc_id = 'LRP'
   AND internal_location_code = 'LRP'
   AND parent_internal_location_id IS NULL;   


-- number of housing records on LRP at each hierarchy level
    with recursive location_query as
    (select internal_location_id, parent_internal_location_id, capacity, operation_capacity, agy_loc_id, 1 as level,
	 unit_type, no_of_occupant
     from oms_owner.agency_internal_locations
     where parent_internal_location_id IS NULL
    UNION ALL
     select 
     ail.internal_location_id, ail.parent_internal_location_id, ail.capacity, ail.operation_capacity, ail.agy_loc_id, c.level+1,
	 ail.unit_type, ail.no_of_occupant
     from oms_owner.agency_internal_locations ail, location_query c
     where c.internal_location_id = ail.parent_internal_location_id
    )
    select count(*), level  
	from location_query
    where agy_loc_id = 'LRP'
	and unit_type IS NOT NULL
	group by level;

-- current definition of housing levels on agency_locations for LRP
SELECT housing_lev_1_code, housing_lev_2_code, housing_lev_3_code, housing_lev_4_code
  FROM agency_locations
 WHERE agy_loc_id = 'LRP';

-- anonymous procedure to perform the updates.
-- this procedure validates that LRP-LRP does not exist. If the record exists, the update aborts, and the data is unchanged.
DO $$
DECLARE 
  lv_parent_int_loc_id numeric;

  get_housing_cur CURSOR(cp_agy_loc_id varchar) FOR
    with recursive location_query as
    (select internal_location_id, parent_internal_location_id, internal_location_code, description, agy_loc_id, 1 as level,
     concat(agy_loc_id, '-', internal_location_code)::varchar as newloc, unit_type
     from oms_owner.agency_internal_locations
     where parent_internal_location_id IS NULL
    UNION ALL
     select 
     ail.internal_location_id, ail.parent_internal_location_id, ail.internal_location_code, ail.description, ail.agy_loc_id, c.level+1,
     CONCAT( c.newloc, '-', ail.internal_location_code) as newloc, ail.unit_type
     from oms_owner.agency_internal_locations ail, location_query c
     where c.internal_location_id = ail.parent_internal_location_id
    )
    select * from location_query
    where agy_loc_id = cp_agy_loc_id
	and unit_type IS NOT NULL;

  get_housing_capacity_cur CURSOR(cp_agy_loc_id varchar, cp_level numeric) FOR
    with recursive location_query as
    (select internal_location_id, parent_internal_location_id, capacity, operation_capacity, agy_loc_id, 1 as level,
	 unit_type, no_of_occupant
     from oms_owner.agency_internal_locations
     where parent_internal_location_id IS NULL
    UNION ALL
     select 
     ail.internal_location_id, ail.parent_internal_location_id, ail.capacity, ail.operation_capacity, ail.agy_loc_id, c.level+1,
	 ail.unit_type, ail.no_of_occupant
     from oms_owner.agency_internal_locations ail, location_query c
     where c.internal_location_id = ail.parent_internal_location_id
    )
    select sum(COALESCE(capacity,0)) level_capacity, sum(coalesce(operation_capacity,0)) as level_operation_capacity, sum(coalesce(no_of_occupant, 0)) as level_no_of_occupant 
	from location_query
    where agy_loc_id = cp_agy_loc_id
    and level = cp_level
	and unit_type IS NOT NULL
	group by level;


  housing_rec RECORD;
  capacity_rec RECORD;

BEGIN

  BEGIN
    -- ensure the LRP internal location does not exist yet. If it does, stop--the script has already been applied, or the location already exists for another reason.
    SELECT internal_location_id 
      INTO STRICT lv_parent_int_loc_id
      FROM oms_owner.agency_internal_locations
     WHERE agy_loc_id = 'LRP'
       AND internal_location_code = 'LRP'
       AND parent_internal_location_id IS NULL;

    -- The query should fail. If not, raise an error.
    RAISE '%', 'The LRP location already exists.';

  EXCEPTION
    WHEN NO_DATA_FOUND THEN 
	  raise info 'Location does not exist. Proceeding with update.';
  END;
   -- update the agency location level codes fir the three levels to be AREA, CELL and BED.

    UPDATE oms_owner.agency_locations 
       SET housing_lev_1_code = 'AREA',
           housing_lev_2_code = 'CELL',
	       housing_lev_3_code = 'BED',
	       housing_lev_4_code = NULL
     WHERE agy_loc_id = 'LRP';

     -- get next sequence for populating new parent internal housing location.
      SELECT NEXTVAL('internal_location_id')
	    INTO lv_parent_int_loc_id
		FROM dual;

     -- add new housing location at root (level 1).
    insert into oms_owner.agency_internal_locations (internal_location_id, internal_location_code, agy_loc_id, internal_location_type,
	    								   description, capacity, create_user_id, parent_internal_location_id, active_flag,
									       list_seq, create_datetime, cna_no, certified_flag, unit_type, operation_capacity, 
									       no_of_occupant, tracking_flag)
    values (lv_parent_int_loc_id, 'LRP', 'LRP', 'AREA', 'LRP-LRP', 0, UPPER(USER), NULL, 'Y',
	   	    1, NOW(), 0, 'N', 'ACCOM', 0, 0, 'N' );

    -- set all other housing locations at level 1 (no parent) to have the new location as parent.
    update oms_owner.agency_internal_locations
	   SET parent_internal_location_id = lv_parent_int_loc_id
	 WHERE agy_loc_id = 'LRP'
	   AND parent_internal_location_id IS NULL
	   AND internal_location_code != 'LRP'
	   AND unit_type IS NOT NULL;
   

   -- There should be only one record. Applies capacity to new parent location based on immediate children.
   FOR capacity_rec IN  get_housing_capacity_cur('LRP', 2) LOOP
      UPDATE oms_owner.agency_internal_locations
   	     SET capacity = capacity_rec.level_capacity,
	         operation_capacity = capacity_rec.level_operation_capacity,
		     no_of_occupant = capacity_rec.level_no_of_occupant
       WHERE internal_location_id = lv_parent_int_loc_id;

   END LOOP;


     -- update the description for all child records to the new parent. Included the new parent location for completeness.
        FOR housing_rec IN get_housing_cur( 'LRP') LOOP 

            UPDATE oms_owner.agency_internal_locations 
			SET description = housing_rec.newloc,
			    internal_location_type = ( CASE housing_rec.level 
				                              when 1 THEN 'AREA'
											  when 2 then 'CELL'
											  when 3 then 'BED'
                                              when 4 then null
											  end )
            WHERE internal_location_id = housing_rec.internal_location_id;

        END LOOP;

  commit;
  
END;
$$;

 -- number of housing records on LRP at each hierarchy level after update. The counts at level 1 and 2 before should match the counts at level 2 and 3. The new record
 -- should be the only record at level 1 housing.
    with recursive location_query as
    (select internal_location_id, parent_internal_location_id, capacity, operation_capacity, agy_loc_id, 1 as level,
	 unit_type, no_of_occupant
     from oms_owner.agency_internal_locations
     where parent_internal_location_id IS NULL
    UNION ALL
     select 
     ail.internal_location_id, ail.parent_internal_location_id, ail.capacity, ail.operation_capacity, ail.agy_loc_id, c.level+1,
	 ail.unit_type, ail.no_of_occupant
     from oms_owner.agency_internal_locations ail, location_query c
     where c.internal_location_id = ail.parent_internal_location_id
    )
    select count(*), level  
	from location_query
    where agy_loc_id = 'LRP'
	and unit_type IS NOT NULL
	group by level;

-- updated definition of housing levels on agency_locations for LRP
SELECT housing_lev_1_code, housing_lev_2_code, housing_lev_3_code, housing_lev_4_code
  FROM agency_locations
 WHERE agy_loc_id = 'LRP';
