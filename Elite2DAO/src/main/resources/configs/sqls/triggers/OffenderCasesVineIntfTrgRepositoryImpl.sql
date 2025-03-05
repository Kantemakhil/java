OFFENDER_CASES_VINE_INTF_TRG_CUR_ACT_BOOK{
SELECT OFFENDER_ID, AGY_LOC_ID, BOOKING_NO FROM offender_bookings WHERE offender_book_id = :pOffBookId AND active_flag = 'Y'
}
OFFENDER_CASES_VINE_INTF_TRG_CUR_OFF{
SELECT OFFENDER_ID_DISPLAY, FIRST_NAME, LAST_NAME FROM OFFENDERS WHERE offender_id = :pOffId
}
OFFENDER_CASES_VINE_INTF_TRG_OFFENDER_CASES_OLD{
SELECT CASE_ID,OFFENDER_BOOK_ID,CASE_INFO_NUMBER,CASE_TYPE,CASE_STATUS,COMBINED_CASE_ID,MODIFY_DATETIME,MODIFY_USER_ID,BEGIN_DATE,AGY_LOC_ID,CREATE_USER_ID,CREATE_DATETIME,CASE_INFO_PREFIX,VICTIM_LIAISON_UNIT,STATUS_UPDATE_REASON,STATUS_UPDATE_COMMENT,STATUS_UPDATE_DATE,STATUS_UPDATE_STAFF_ID,LIDS_CASE_NUMBER,CASE_SEQ,SEAL_FLAG FROM OFFENDER_CASES WHERE CASE_ID=:caseId
}
OFFENDER_CASES_VINE_INTF_TRG_INSERTING{
INSERT INTO ca_audit(action_type, agy_loc_id, offender_id_display,booking_no, case_info_number,first_name,last_name, case_status,case_id, modified_date,offender_book_id,create_datetime,modify_datetime,create_user_id)VALUES(:actionType,:agyLocId,:offenderIdDisplay,:bookingNo,:caseInfoNumber,:firstName,:lastName,:caseStatus,:caseId,modifiedDate,:offenderBookId,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,:createUserId)
}
OFFENDER_CASES_VINE_INTF_TRG_UPDATING{
UPDATE ca_audit SET action_type = 'U', agy_loc_id = :agyLocId,offender_id_display = :offenderIdDisplay,booking_no =:bookingNo,case_info_number = :caseInfoNumber, first_name = :firstName,last_name = :lastName, case_status = :caseStatus,modified_date = :modifiedDate,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId WHERE case_id = :caseId AND action_type IN ('A','U')
}
OFFENDER_CASES_VINE_INTF_TRG_DELETING{
INSERT INTO ca_audit (action_type,agy_loc_id,offender_id_display, booking_no,case_info_number, first_name, last_name, case_status,case_id,modified_date, offender_book_id)VALUES(:actionType, :agyLocId,:offenderIdDisplay, :bookingNo,:caseInfoNumber,:firstName,:lastName,:caseStatus,:caseId,CURRENT_DATE,:offenderBookId)
}
