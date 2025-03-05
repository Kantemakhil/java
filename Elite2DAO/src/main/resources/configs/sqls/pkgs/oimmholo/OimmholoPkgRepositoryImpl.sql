GET_HOUS_LVL_CODE_CUR{
  SELECT case :P_LEVEL
                        when 1 then 
                       housing_lev_1_code
                        when 2 then 
                       housing_lev_2_code
                        when 3 then 
                       housing_lev_3_code
                        when 4 then 
                       housing_lev_4_code end
           FROM agency_locations
          WHERE agy_loc_id = :P_AGY_LOC_ID
}
GET_PARENT_DESC_CUR{
  SELECT DESCRIPTION || '-' || :P_LIVING_UNIT_CODE
           FROM LIVING_UNITS
          WHERE LIVING_UNIT_ID = :P_PARENT_LU_ID
}

GET_ALL_LEVELS_CUR{
WITH RECURSIVE CTE_NAME AS( SELECT 1 AS LEVEL, PARENT_LIVING_UNIT_ID, LIVING_UNIT_ID, ACTIVE_FLAG FROM LIVING_UNITS WHERE LIVING_UNIT_ID =:P_LIVING_UNIT_ID UNION ALL SELECT (CTE_NAME.LEVEL + 1), LU.PARENT_LIVING_UNIT_ID, LU.LIVING_UNIT_ID, LU.ACTIVE_FLAG FROM LIVING_UNITS LU JOIN CTE_NAME ON CTE_NAME.PARENT_LIVING_UNIT_ID = LU.LIVING_UNIT_ID) SELECT LEVEL, LIVING_UNIT_ID FROM CTE_NAME WHERE LIVING_UNIT_ID <>:P_LIVING_UNIT_ID AND ACTIVE_FLAG = 'Y' ORDER BY LEVEL, PARENT_LIVING_UNIT_ID, LIVING_UNIT_ID
}

GET_CHILD_TOTALS{
SELECT COALESCE (SUM(COALESCE(CAPACITY, 0)), 0) CAPACITY, COALESCE(SUM(COALESCE(CNA_NO, 0)), 0) CNA_NO,
COALESCE (SUM(COALESCE(OPERATION_CAPACITY, 0)), 0) OPERATION_CAPACITY FROM LIVING_UNITS 
WHERE PARENT_LIVING_UNIT_ID = :P_PARENT_LU_ID AND ACTIVE_FLAG = 'Y'
}

GET_LIV_UNIT_UPD_CUR{
SELECT LIVING_UNIT_ID FROM LIVING_UNITS WHERE LIVING_UNIT_ID = :P_LIV_UNIT_ID
}

UPDATE_LIVING_UNITS{
UPDATE LIVING_UNITS SET CAPACITY = :LV_CAPACITY, CNA_NO = :LV_CNA,OPERATION_CAPACITY =:operationCapacity, modify_user_id =:modifyUserId,modify_datetime = current_timestamp
WHERE LIVING_UNIT_ID = (SELECT LIVING_UNIT_ID FROM LIVING_UNITS WHERE LIVING_UNIT_ID = :P_LIV_UNIT_ID )
}


GET_ALL_ATTRIBUTES_CUR { 
SELECT int_loc_profile_type, int_loc_profile_code FROM living_unit_profiles WHERE living_unit_id = :p_parent_lu_id
}

AGY_INT_LOC_PROFILES{
insert into agy_int_loc_profiles (internal_location_id , int_loc_profile_type , int_loc_profile_code, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:p_lu_id , each_attrib.int_loc_profile_type , each_attrib.int_loc_profile_code, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP) 
}
            
LIVING_UNITS_UPDATE{     
update
living_units
set
active_flag = :p_active_flag ,
deactivate_date = date_trunc('day', CURRENT_TIMESTAMP) ,
deactivate_reason_code = :p_reason_code,
modify_user_id =:modifyUserId,
modify_datetime = current_timestamp
where
living_unit_id in (with recursive cte as (
select
living_unit_id
from
living_units
where
living_unit_id = :p_parent_lu_id
union all
select
lu.living_unit_id
from
living_units lu
join cte c on
(c.living_unit_id = :parent_living_unit_id) )
select
*
from
cte
where
living_unit_id <> :p_parent_lu_id

)
and active_flag = 'Y'
}
   
 LIVING_UNITS_UPDATE_ELSE{
 update
living_units
set
active_flag = :p_active_flag ,
deactivate_date = null ,
deactivate_reason_code = null,
modify_user_id =:modifyUserId,
modify_datetime = current_timestamp
where
living_unit_id in (with recursive cte as (
select
living_unit_id
from
living_units
where
living_unit_id = :p_parent_lu_id
union all
select
lu.living_unit_id
from
living_units lu
join cte c on
(c.living_unit_id = :parent_living_unit_id) )
select
*
from
cte
where
living_unit_id <> :p_parent_lu_id )
and active_flag <> :p_active_flag
and deactivate_reason_code = :p_reason_code
 }
