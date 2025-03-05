\o 'agy_int_locs_hous_locs_incorrect_description_records.csv'

WITH agy_locs_calculated_descripion AS (select * 
           from (WITH RECURSIVE cte_calculated_top_down AS ( 
                       SELECT internal_location_id, parent_internal_location_id, agy_loc_id, internal_location_code, internal_location_code 
                          , description, 1 AS depth,  agy_loc_id || '-' || internal_location_code AS calculated_description
                         FROM agency_internal_locations 
                        WHERE parent_internal_location_id IS NULL 
                      UNION  ALL 
                       SELECT agy_locs.internal_location_id, agy_locs.parent_internal_location_id, agy_locs.agy_loc_id, agy_locs.internal_location_code, agy_locs.internal_location_code 
                          , agy_locs.description, depth + 1  , calculated_description || '-' || agy_locs.internal_location_code 
                         FROM agency_internal_locations agy_locs 
                        INNER JOIN cte_calculated_top_down l ON l.internal_location_id = agy_locs.parent_internal_location_id 
                ) SELECT * 
                   FROM cte_calculated_top_down ) AS calculated_description)  
 SELECT * 
   FROM agency_internal_locations 
  WHERE description <> (SELECT calculated_description FROM  agy_locs_calculated_descripion WHERE agy_locs_calculated_descripion.internal_location_id = agency_internal_locations.internal_location_id);
  
\o
	