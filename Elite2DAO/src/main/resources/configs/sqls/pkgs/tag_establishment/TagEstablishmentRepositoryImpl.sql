
SELECT_GET_HOUSING_LABELS{
SELECT housing_lev_1_code, housing_lev_2_code, housing_lev_3_code, housing_lev_4_code FROM agency_locations WHERE agy_loc_id =:p_agy_loc_id
}

GET_AGY_LOC_DESC{
SELECT DESCRIPTION FROM AGENCY_LOCATIONS WHERE AGY_LOC_ID = :P_AGY_LOC_ID
}

GET_COUNT_VS_AGYCUR{
SELECT COUNT (*) FROM AGENCY_LOCATIONS ALAL WHERE ALAL.AGENCY_LOCATION_TYPE = 'INST' AND ALAL.AGY_LOC_ID IN ( SELECT CA.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CA WHERE CA.CASELOAD_ID = :P_GLOBAL_CASELOAD_ID) AND ALAL.AGY_LOC_ID NOT IN ('TRN', 'OUT') AND ALAL.ACTIVE_FLAG = 'Y' 

}      
 
DEFAULT_AGENCY_VS_AGYLOCCUR{
 SELECT ALAL.AGY_LOC_ID, ALAL.DESCRIPTION FROM AGENCY_LOCATIONS ALAL WHERE ALAL.AGENCY_LOCATION_TYPE = 'INST' AND ALAL.AGY_LOC_ID IN ( SELECT CA.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CA WHERE CA.CASELOAD_ID = :P_GLOBAL_CASELOAD_ID) AND ALAL.AGY_LOC_ID NOT IN ('TRN', 'OUT') 
}            
 
GET_ACTIVE_AGY_LOC_DESC{
SELECT DESCRIPTION FROM AGENCY_LOCATIONS WHERE AGY_LOC_ID = :P_AGY_LOC_ID AND ACTIVE_FLAG = 'Y' AND DEACTIVATION_DATE IS NULL
}
LOCK_RECORD_C{
select 'X' from agency_internal_locations where internal_location_id in (with recursive cte as ( select internal_location_id, parent_internal_location_id from agency_internal_locations where internal_location_id = :p_internal_location_id union all select ail.internal_location_id, ail.parent_internal_location_id from agency_internal_locations ail join cte c on (c.parent_internal_location_id = ail.internal_location_id)) select internal_location_id from cte) for UPDATE
}
UPDATE_AGENCY_INTERNAL_LOCATIONS{
update AGENCY_INTERNAL_LOCATIONS al set no_of_occupant = no_of_occupant + coalesce(:p_no_adjustments, 0), modify_user_id =:modifyUserId, modify_datetime =current_timestamp where ( no_of_occupant + coalesce (:p_no_adjustments, 0) ) >= 0 and internal_location_id in ( with recursive cte as ( select internal_location_id, parent_internal_location_id from agency_internal_locations where internal_location_id = :p_internal_location_id union all select ail.internal_location_id, ail.parent_internal_location_id from agency_internal_locations ail join cte c on (c.parent_internal_location_id = ail.internal_location_id)) select internal_location_id from cte c where c.internal_location_id = al.internal_location_id) 
}
