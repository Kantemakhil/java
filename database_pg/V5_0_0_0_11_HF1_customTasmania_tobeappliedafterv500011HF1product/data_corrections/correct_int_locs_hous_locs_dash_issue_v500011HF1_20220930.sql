-----------------------------------------------------------------------------------------------------------------------------------
--verifications before corrections
--codes that contain dash
select count(*) 
from agency_internal_locations 
where POSITION('-' IN internal_location_code) <> 0;

--codes that contain leading or trailing spaces (no corrections will be made)
select count(*) 
from agency_internal_locations 
where TRIM(internal_location_code) <> internal_location_code;

--records that have the description different than what the calculated value should be, regardless of presence of dash
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
 SELECT count(*)  
   FROM agency_internal_locations 
  WHERE description <> (SELECT calculated_description FROM  agy_locs_calculated_descripion WHERE agy_locs_calculated_descripion.internal_location_id = agency_internal_locations.internal_location_id);
    

-------------------------------------------------- corrections ---------------------------------------------------------------------
--correct location code, description and user description 
--
--location code is being corrected by replacing dash with underscore
--if location code contains leading or trailing spaces, they are NOT removed
--description is being corrected by recreating the description for records where description does not match the calculated description 
--the user description is corrected only for records that have dashes in the user description and only for records where description does not match the calculated description; in this case the user description is being updated with the corrected description  

update agency_internal_locations 
   set modify_datetime = current_timestamp 
      ,modify_user_id = user 
      ,internal_location_code = REPLACE(internal_location_code, '-', '_') 
where POSITION('-' IN internal_location_code) <> 0; 


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
 UPDATE agency_internal_locations 
    SET modify_datetime = current_timestamp 
      ,modify_user_id = user 
      ,description = (SELECT calculated_description FROM  agy_locs_calculated_descripion WHERE agy_locs_calculated_descripion.internal_location_id = agency_internal_locations.internal_location_id) 
      ,user_desc = CASE WHEN POSITION('-' IN user_desc) <> 0 THEN (SELECT calculated_description FROM  agy_locs_calculated_descripion WHERE agy_locs_calculated_descripion.internal_location_id = agency_internal_locations.internal_location_id)  
                   ELSE user_desc END    
   WHERE description <> (SELECT calculated_description FROM  agy_locs_calculated_descripion WHERE agy_locs_calculated_descripion.internal_location_id = agency_internal_locations.internal_location_id);       



-----------------------------------------------------------------------------------------------------------------------------------
--verifications after corrections
--codes that contain dash
select count(*) 
from agency_internal_locations 
where POSITION('-' IN internal_location_code) <> 0;

--codes that contain leading or trailing spaces
select count(*) 
from agency_internal_locations 
where TRIM(internal_location_code) <> internal_location_code;

--records that have the description different than what the calculated value should be, regardless of presence of dash
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
 SELECT count(*)  
   FROM agency_internal_locations 
  WHERE description <> (SELECT calculated_description FROM  agy_locs_calculated_descripion WHERE agy_locs_calculated_descripion.internal_location_id = agency_internal_locations.internal_location_id);
  
    

  
               



