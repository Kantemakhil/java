LIVING_UNIT_PROFILES_TID_LIVING_UNIT_PROFILES{
SELECT LIVING_UNIT_ID, PROFILE_ID, AGY_LOC_ID, DESCRIPTION, INT_LOC_PROFILE_TYPE, INT_LOC_PROFILE_CODE FROM LIVING_UNIT_PROFILES lp WHERE LIVING_UNIT_ID = :livingUnitId
}
LIVING_UNIT_PROFILES_TID_AGY_INT_LOC_PROFILES{
INSERT INTO AGY_INT_LOC_PROFILES(INTERNAL_LOCATION_ID,INT_LOC_PROFILE_TYPE,INT_LOC_PROFILE_CODE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME)VALUES(:livingUnitId,:intLocProfileType,:intLocProfileCode,:createDatetime,:createUserId,:modifyDatetime)
}
LIVING_UNIT_PROFILES_TID_DELETE{
DELETE FROM AGY_INT_LOC_PROFILES WHERE  INTERNAL_LOCATION_ID = :livingUnitId AND    INT_LOC_PROFILE_TYPE = :intLocProfileType AND    INT_LOC_PROFILE_CODE = :intLocProfileCode
}