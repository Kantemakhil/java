OFFENDERS_BOOK_VINE_INTF_TRG_OFFENDER_BOOKINGS_GET{
SELECT OFFENDER_BOOK_ID,BOOKING_BEGIN_DATE,BOOKING_END_DATE,BOOKING_NO,OFFENDER_ID,AGY_LOC_ID,LIVING_UNIT_ID,DISCLOSURE_FLAG,IN_OUT_STATUS,ACTIVE_FLAG,BOOKING_STATUS,YOUTH_ADULT_CODE,FINGER_PRINTED_STAFF_ID,SEARCH_STAFF_ID,PHOTO_TAKING_STAFF_ID,ASSIGNED_STAFF_ID,CREATE_AGY_LOC_ID,BOOKING_TYPE,BOOKING_CREATED_DATE,ROOT_OFFENDER_ID,AGENCY_IML_ID,SERVICE_FEE_FLAG,EARNED_CREDIT_LEVEL,EKSTRAND_CREDIT_LEVEL,INTAKE_AGY_LOC_ID,ACTIVITY_DATE,INTAKE_CASELOAD_ID,INTAKE_USER_ID,CASE_OFFICER_ID,CASE_DATE,CASE_TIME,COMMUNITY_ACTIVE_FLAG,CREATE_INTAKE_AGY_LOC_ID,COMM_STAFF_ID,COMM_STATUS,COMMUNITY_AGY_LOC_ID,NO_COMM_AGY_LOC_ID,COMM_STAFF_ROLE,AGY_LOC_ID_LIST,STATUS_REASON,TOTAL_UNEXCUSED_ABSENCES,REQUEST_NAME,RECORD_USER_ID,INTAKE_AGY_LOC_ASSIGN_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG FROM offender_bookings WHERE OFFENDER_BOOK_ID = :offenderBookId
}
OFFENDERS_BOOK_VINE_INTF_TRG_CUR_OFF{
SELECT OFFENDER_ID_DISPLAY, FIRST_NAME, MIDDLE_NAME, LAST_NAME, BIRTH_DATE, RACE_CODE, SEX_CODE, SUFFIX FROM OFFENDERS  WHERE OFFENDER_ID = :offenderId
}
OFFENDERS_BOOK_VINE_INTF_TRG_OR_AUDIT_INSERT{
insert into OR_AUDIT(action_type, agy_loc_id, offender_id_display, booking_no, booking_begin_date, first_name, middle_name, last_name, birth_date, race_code, sex_code, release_date, rel_reason_code, offender_id, modified_date, offender_book_id, create_datetime, modify_datetime, create_user_id) values ('A', :agyLocId, :offenderIdDisplay, :bookingNo, :bookingBeginDate, :firstName, :middleName, :lastName, :birthDate, :raceCode, :sexCode, null, null, :offenderId, :modifiedDate, :offenderBookId, current_timestamp, NULL, :createUserId)
}
OFFENDERS_BOOK_VINE_INTF_TRG_OR_AUDIT_UPDATE{
update or_audit set action_type = 'U', agy_loc_id = :agyLocId, offender_id_display = :offenderIdDisplay, booking_no = :bookingNo, booking_begin_date = :bookingBeginDate, first_name = :firstName, middle_name = :middleName, last_name = :lastName, birth_date = :birthDate, race_code = :raceCode, sex_code = :sexCode, release_date = :releaseDate, rel_reason_code = :relReasonCode, modified_date = :modifiedDate, modify_datetime = current_timestamp, modify_user_id =:modifyUserId where offender_id = :offenderId and booking_no = :bookingNo and action_type in ('A', 'U')
}
OFFENDERS_BOOK_VINE_INTF_TRG_OR_AUDIT_DELETE{
insert into OR_AUDIT(action_type, agy_loc_id, offender_id_display, booking_no, booking_begin_date, first_name, middle_name, last_name, birth_date, race_code, sex_code, release_date, rel_reason_code, offender_id, modified_date, offender_book_id, create_datetime, create_user_id, modify_datetime) values ('D', :agyLocId, :offenderIdDisplay, :bookingNo, :bookingBeginDate, :firstName, :middleName, :lastName, :birthDate, :raceCode, :sexCode, null, null, :offenderId, :modifiedDate, :offenderBookId, current_timestamp, :createUserId, NULL) 
}
OFFENDERS_BOOK_VINE_INTF_TRG_AN_AUDIT_UPDATE{
update an_audit set action_type = 'U', agy_loc_id = :agyLocId, offender_id_display = :offenderIdDisplay, booking_no = :bookingNo, first_name = :firstName, middle_name = :middleName, suffix = :suffix, last_name = :lastName, birth_date = :birthDate, modified_date = :modifiedDate, modify_datetime =current_timestamp, modify_user_id =:modifyUserId where offender_id = :offenderId and booking_no = :bookingNo and action_type in ('A', 'U')
}
