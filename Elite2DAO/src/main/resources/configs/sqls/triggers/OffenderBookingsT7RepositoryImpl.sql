OFFENDER_BOOKINGS_T7_OFFENDER_BOOKINGS{
SELECT OFFENDER_BOOK_ID,BOOKING_BEGIN_DATE,BOOKING_END_DATE,BOOKING_NO,OFFENDER_ID,AGY_LOC_ID,LIVING_UNIT_ID,DISCLOSURE_FLAG,IN_OUT_STATUS,ACTIVE_FLAG,BOOKING_STATUS,YOUTH_ADULT_CODE,FINGER_PRINTED_STAFF_ID,SEARCH_STAFF_ID,PHOTO_TAKING_STAFF_ID,ASSIGNED_STAFF_ID,CREATE_AGY_LOC_ID,BOOKING_TYPE,BOOKING_CREATED_DATE,ROOT_OFFENDER_ID,AGENCY_IML_ID,SERVICE_FEE_FLAG,EARNED_CREDIT_LEVEL,EKSTRAND_CREDIT_LEVEL,INTAKE_AGY_LOC_ID,ACTIVITY_DATE,INTAKE_CASELOAD_ID,INTAKE_USER_ID,CASE_OFFICER_ID,CASE_DATE,CASE_TIME,COMMUNITY_ACTIVE_FLAG,CREATE_INTAKE_AGY_LOC_ID,COMM_STAFF_ID,COMM_STATUS,COMMUNITY_AGY_LOC_ID,NO_COMM_AGY_LOC_ID,COMM_STAFF_ROLE,AGY_LOC_ID_LIST,STATUS_REASON,TOTAL_UNEXCUSED_ABSENCES,REQUEST_NAME,RECORD_USER_ID,INTAKE_AGY_LOC_ASSIGN_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID=:offenderBookId
}
OFFENDER_BOOKINGS_T7_GET_AGY_LOC_TEAM_FUNS_CUR{
SELECT FUNCTION_TYPE, TEAM_ID, OVERWRITTEN_FLAG, EFFECTIVE_DATE FROM AGY_LOC_TEAM_FUNCTIONS WHERE AGY_LOC_ID = :pAgyLocId AND ACTIVE_FLAG = 'Y'
}
OFFENDER_BOOKINGS_T7_IS_OFF_TEAM_ASSIGN_EXISTS{
SELECT TEAM_ID FROM OFFENDER_TEAM_ASSIGNMENTS WHERE OFFENDER_BOOK_ID = :pOffBookId AND FUNCTION_TYPE = :pFunctionType
}
OFFENDER_BOOKINGS_T7_INS_OFFENDER_TEAM_ASSIGNMENTS{
insert into OFFENDER_TEAM_ASSIGNMENTS(OFFENDER_BOOK_ID, FUNCTION_TYPE, TEAM_ID, ASSIGNMENT_DATE, CREATE_DATETIME, MODIFY_DATETIME, CREATE_USER_ID) values (:offenderBookId, :functionType, :teamId, :assignmentDate, :createDatetime, NULL, :createUserId) 
}
OFFENDER_BOOKINGS_T7_OFFENDER_TEAM_ASSIGNMENTS_DELETE{
DELETE FROM offender_team_assignments WHERE offender_book_id = :offenderBookId AND function_type = item.function_type
}