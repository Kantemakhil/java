OFF_COURT_EVENT_VINE_INTF_TRG_GET_OLD_OBJECT{
SELECT EVENT_ID,CASE_ID,OFFENDER_BOOK_ID,EVENT_DATE,START_TIME,END_TIME,COURT_EVENT_TYPE,JUDGE_NAME,EVENT_STATUS,PARENT_EVENT_ID,AGY_LOC_ID,
OUTCOME_REASON_CODE,COMMENT_TEXT,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,EVENT_OUTCOME,NEXT_EVENT_REQUEST_FLAG,ORDER_REQUESTED_FLAG,RESULT_CODE,
NEXT_EVENT_DATE,NEXT_EVENT_START_TIME,OUTCOME_DATE,OFFENDER_PROCEEDING_ID,DIRECTION_CODE,HOLD_FLAG,SEAL_FLAG,SCHEDULED_TRIP_ID FROM court_events WHERE EVENT_ID = :eventId
}
OFF_COURT_EVENT_VINE_INTF_TRG_CUR_ACT_BOOK{
SELECT OFFENDER_ID, AGY_LOC_ID, BOOKING_NO FROM offender_bookings WHERE offender_book_id = :offBookId AND active_flag = 'Y'
}
OFF_COURT_EVENT_VINE_INTF_TRG_CUR_OFF{
SELECT OFFENDER_ID_DISPLAY, FIRST_NAME, LAST_NAME FROM OFFENDERS WHERE offender_id = :offId
}
OFF_COURT_EVENT_VINE_INTF_TRG_CUR_OFF_CASE{
 SELECT case_info_number FROM offender_cases WHERE CASE_ID = :caseId
}
OFF_COURT_EVENT_VINE_INTF_TRG_INSERTING{
insert into ce_audit (action_type, agy_loc_id, offender_id_display, booking_no, case_info_number, first_name, last_name, event_date, event_time, movement_type, movement_reason_code, sch_agy_loc_id, event_id, event_status, case_id, modified_date, offender_book_id, create_datetime, create_user_id) values(:actionType, :agyLocId, :offenderIdDisplay, :bookingNo, :caseInfoNumber, :firstName, :lastName, :eventDate, :eventTime, :movementType, :movementReasonCode, :schAgyLocId, :eventId, :eventStatus, :caseId, :modifiedDate, :offenderBookId, current_timestamp, :createUserId) 
}
OFF_COURT_EVENT_VINE_INTF_TRG_UPDADING{
update ce_audit set action_type = 'U', agy_loc_id = :agyLocId, offender_id_display = :offenderIdDisplay, booking_no =:bookingNo, case_info_number = :caseInfoNumber, first_name = :firstName, last_name = :lastName, event_date = :eventDate, event_time = :eventTime, movement_type = 'CRT', movement_reason_code = :movementReasonCode, sch_agy_loc_id = :schAgyLocId, event_status = :eventStatus, modified_date = :modifiedDate, modify_user_id =:modifyUserId, modify_datetime = current_timestamp where event_id = :eventId and action_type in ('A', 'U')
}
OFF_COURT_EVENT_VINE_INTF_TRG_DELETING{
insert into ce_audit (action_type, agy_loc_id, offender_id_display, booking_no, case_info_number, first_name, last_name, event_date, event_time, movement_type, movement_reason_code, sch_agy_loc_id, event_id, event_status, case_id, modified_date, offender_book_id, create_datetime, create_user_id) values(:actionType, :agyLocId, :offenderIdDisplay, :bookingNo, :caseInfoNumber, :firstName, :lastName, :eventDate, :startTime, :movementType, :movementReasonCode, :schAgyLocId, :eventId, :eventStatus, :caseId, :modifiedDate, :offenderBookId, current_timestamp, :createUserId) 
}