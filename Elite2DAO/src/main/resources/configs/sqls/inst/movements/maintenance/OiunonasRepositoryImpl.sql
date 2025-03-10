
OIUNONAS_FIND_RGNONASSOTYPE {
 	SELECT   CODE ,          DESCRIPTION     FROM REFERENCE_CODES    WHERE DOMAIN = 'NON_ASSO_TYP'      AND (   (ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL )           OR '' = 'ENTER-QUERY'           ) ORDER BY LIST_SEQ , DESCRIPTION
}

OIUNONAS_INTLOCPROF_FIND_AGY_INT_LOC_PROFILES {
SELECT
internal_location_id,
int_loc_profile_type,
int_loc_profile_code,
create_datetime,
create_user_id,
modify_datetime,
modify_user_id,
seal_flag,
row_id
FROM
agy_int_loc_profiles
WHERE
internal_location_id = :internalLocationId
AND int_loc_profile_type = 'NON_ASSO_TYP'
}
OIUNONAS_INTLOCPROF_INSERT_AGY_INT_LOC_PROFILES {
	INSERT INTO AGY_INT_LOC_PROFILES(INTERNAL_LOCATION_ID, INT_LOC_PROFILE_TYPE, INT_LOC_PROFILE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG) 
    VALUES(:internalLocationId , :intLocProfileType, :intLocProfileCode, current_timestamp, :createUserId, null,:sealFlag)
}

OIUNONAS_INTLOCPROF_UPDATE_AGY_INT_LOC_PROFILES {
	UPDATE AGY_INT_LOC_PROFILES 
    SET  INT_LOC_PROFILE_CODE=:intLocProfileCode, 
     MODIFY_DATETIME=current_timestamp, MODIFY_USER_ID=:modifyUserId, SEAL_FLAG=:sealFlag
    WHERE INTERNAL_LOCATION_ID=:internalLocationId and INT_LOC_PROFILE_TYPE=:intLocProfileType and ROW_ID =:rowId
}
OIUNONAS_INTLOCPROF_DELETE_AGY_INT_LOC_PROFILES { 
	DELETE FROM AGY_INT_LOC_PROFILES WHERE INTERNAL_LOCATION_ID=:internalLocationId and INT_LOC_PROFILE_TYPE=:intLocProfileType and INT_LOC_PROFILE_CODE=:intLocProfileCode
}
OIUNONAS_PRE_INSERT {
SELECT COUNT(*) FROM AGY_INT_LOC_PROFILES WHERE INTERNAL_LOCATION_ID IN (:INTERNAL_LOCATION_ID) AND INT_LOC_PROFILE_TYPE='NON_ASSO_TYP'
AND INT_LOC_PROFILE_CODE IN (:INT_LOC_PROFILE_CODE)
}
