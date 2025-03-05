GETTING_CASELOAD_AGENCY_LOCATIONS_DATA{
SELECT CAL.AGY_LOC_ID
             FROM caseload_agency_locations CAL
             WHERE CAL.CASELOAD_ID = :CASELOAD_ID
}

GETTING_CUR_CHK_ROLE_EXISTS_COUNT{
SELECT count(*) 
      FROM STAFF_LOCATION_ROLES SLR
      WHERE SLR.CAL_AGY_LOC_ID = :p_agy_loc_id
      AND SLR.SAC_STAFF_ID = :staff_id
      AND SLR.TO_DATE IS NULL
}
  UPDATE_STAFF_LOCATION_ROLES{
  
     UPDATE STAFF_LOCATION_ROLES
         SET TO_DATE = current_timestamp,
          modify_user_id =:modifyUserId, 
          modify_datetime = current_timestamp
         WHERE sac_staff_id = :staff_id
         AND CAL_AGY_LOC_ID = :AGY_LOC_ID
  }