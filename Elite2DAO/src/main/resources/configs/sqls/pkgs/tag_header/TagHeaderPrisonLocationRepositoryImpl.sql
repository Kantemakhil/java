TAG_HEADER_GET_DESC_CUR{
	SELECT INITCAP (DESCRIPTION)
	FROM AGENCY_LOCATIONS
	WHERE AGY_LOC_ID = :agyLocId
}
TAG_HEADER_CHECK_MULTI_LOCS_CUR{
	SELECT COUNT(0)
	FROM OFFENDER_BOOKING_AGY_LOCS OBA
	WHERE OBA.REMOVED_DATE IS NULL 
	AND OBA.OFFENDER_BOOK_ID = :offenderBookId
}
TAG_HEADER_COMM_AGY_CUR{
	SELECT CP.AGY_LOC_ID
	FROM CASE_PLANS CP
   	WHERE CP.CASE_PLAN_STATUS = 'ACTIVE'
	AND CP.OFFENDER_BOOK_ID = :offenderBookId
}
TAG_HEADER_GET_COMM_STAFF_CUR{
SELECT    INITCAP (SM.LAST_NAME) || ', ' || INITCAP (SM.FIRST_NAME)
FROM STAFF_MEMBERS SM, CASE_PLANS CP
WHERE CP.OFFENDER_BOOK_ID = :offenderBookId
AND CP.CASE_PLAN_STATUS = 'ACTIVE'
AND CP.END_DATE IS NULL
AND SM.STAFF_ID = CP.SAC_STAFF_ID;
}
TAG_HEADER_GET_INST_STAFF_CUR{
	SELECT INITCAP (SM.LAST_NAME) || ', ' || INITCAP (SM.FIRST_NAME)
	FROM STAFF_MEMBERS SM, CASE_PLANS CP
	WHERE CP.OFFENDER_BOOK_ID = :offenderBookId
	AND CP.CASE_PLAN_STATUS = 'ACTIVE'
	AND CP.END_DATE IS NULL
	AND SM.STAFF_ID = CP.INST_SAC_STAFF_ID;
}
TAG_HEADER_TAG_INT_LOC_INT_LOC_PATH{
	SELECT INTERNAL_LOCATION_CODE, PARENT_INTERNAL_LOCATION_ID
	FROM AGENCY_INTERNAL_LOCATIONS
	WHERE INTERNAL_LOCATION_ID = :internalLocationId
}
