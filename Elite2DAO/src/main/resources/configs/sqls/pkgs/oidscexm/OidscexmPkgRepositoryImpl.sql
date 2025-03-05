INSERT_OFFENDER_EXT_MOV{
insert into offender_external_movements (offender_book_id, movement_seq, movement_date, movement_time, from_agy_loc_id, to_address_id, from_city, from_address_id, to_agy_loc_id, to_city, movement_type, movement_reason_code, direction_code, escort_code, active_flag, comment_text, event_id, parent_event_id , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:offenderBookId, :movementSeq, CURRENT_TIMESTAMP, :eventTime, :fromAgyLocId, :toAddressId, :fromCity, :fromAddressId, :toAgyLocId, :toCity, :movementType, :movementReasonCode, :directionCode, :escortCode, 'Y', :commentText, :eventId, :parentEventId , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}

SELECT_SEQ{
SELECT coalesce (MAX (movement_seq), 0) + 1 FROM offender_external_movements WHERE offender_book_id =:p_book_id
}

SELECET_EVENT_ID{
SELECT event_id FROM offender_external_movements WHERE offender_book_id =:p_book_id AND movement_seq IN ( SELECT MAX (movement_seq) FROM offender_external_movements WHERE offender_book_id =:p_book_id AND direction_code = 'OUT' AND movement_type IN ('TAP', 'CRT'))
}

SELECT_NEW_EVENT_ID{
SELECT nextval('event_id') FROM dual
}

GET_CLOSE_CONTACT_FLAG{
SELECT close_contact_flag FROM movement_reasons WHERE movement_reason_code = 'OJ'
}

GET_COMM_ACTIVE_FLAG{
SELECT coalesce (community_active_flag, 'N') FROM offender_bookings WHERE offender_book_id = :p_off_bk_id
}

UPDATE_OFFENDER_BOOKINGS{
update offender_bookings set active_flag = 'N', in_out_status = :LV_IN_OUT_STATUS, agy_loc_id = :LV_AGY_LOC_ID, living_unit_id = null, booking_status = 'C', booking_end_date = CURRENT_TIMESTAMP, agency_iml_id = null, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id = :p_off_bk_id
}

UPDATE_OFFENDER_BOOKINGS_ONE{
update offender_bookings set active_flag = 'N', in_out_status = :LV_IN_OUT_STATUS, agy_loc_id = :LV_AGY_LOC_ID, living_unit_id = null, booking_status = 'O', booking_end_date = null, agency_iml_id = null, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id = :p_off_bk_id
}

UPDATE_OFFENDER_BOOKINGS_TWO{
update offender_bookings set active_flag = 'N', in_out_status = :LV_IN_OUT_STATUS, agy_loc_id = :LV_AGY_LOC_ID, living_unit_id = null, agency_iml_id = null, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id = :p_off_bk_id
}

UPDATE_V_OFFENDER{
UPDATE v_offender_all_schedules_2 SET return_time = :RETURN_TIME WHERE event_id = :P_EVENT_ID
}

INSERT_COURT_EVENTS_NEW{
insert into court_events ( offender_book_id, event_id, event_date, start_time, agy_loc_id, court_event_type, direction_code, create_datetime, event_status, parent_event_id , CREATE_USER_ID, MODIFY_DATETIME) values (:p_book_id, nextval('event_id'), :lv_return_date, :start_time, :p_to_agy_loc_id, :p_movement_reason_code, 'IN', CURRENT_TIMESTAMP, :p_ta_status, :p_event_id, :createUserId, CURRENT_TIMESTAMP)
}


INSERT_OFFENDER_IND_SCHEDULES{
insert into offender_ind_schedules ( offender_book_id, event_id, event_date, start_time, to_agy_loc_id, event_type, event_sub_type, direction_code, escort_code, create_datetime, event_status, event_class, parent_event_id , CREATE_USER_ID, MODIFY_DATETIME) values ( :P_BOOK_ID, nextval('event_id'), :LV_RETURN_DATE, :START_TIME, :P_TO_AGY_LOC_ID, :P_MOVEMENT_TYPE, :P_MOVEMENT_REASON_CODE, 'IN', :P_ESCORT_CODE, CURRENT_TIMESTAMP, :P_TA_STATUS, 'EXT_MOV', :P_EVENT_ID, :createUserId, CURRENT_TIMESTAMP)
}

UPDATE_CRS_EVENT_STATUS{
 update court_events set event_status = 'COMP', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where event_id =:EVENT_ID
}

UPDATE_OFFENDEREXTERNAL_MOVEMENTS{
 update offender_external_movements set active_flag = 'N', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id =:P_BOOK_ID
  }
  
UPDATE_V_OFFNDER_ALL_SCH{
UPDATE v_offender_all_schedules_2 SET event_status = 'COMP' WHERE event_id =:event_id	
}


UPDATE_OFFENDER_IN_OUT_STATUS{
update offender_bookings set in_out_status = :LV_IN_OUT, agency_iml_id = null, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id = :P_BOOK_ID 
}      
GET_OLD_REC_OF_V_OFFENDER_ALL_SCHEDULES_2{
SELECT EVENT_ID, OFFENDER_BOOK_ID, AGY_LOC_ID, EVENT_DATE, START_TIME, END_TIME, EVENT_CLASS, EVENT_TYPE , EVENT_SUB_TYPE , EVENT_STATUS , EVENT_OUTCOME, CONFIRM_FLAG , OUTCOME_REASON_CODE, COMMENT_TEXT, REFERENCE_ID, APPLICATION_DATE , APPLICATION_TIME , RETURN_DATE, RETURN_TIME, TO_AGY_LOC_ID , ESCORT_CODE, DIRECTION_CODE , SCHEDULE_MOVEMENT_TIME, TO_INTERNAL_LOCATION_ID, FROM_CITY_CODE, TO_CITY_CODE, CREDITED_HOURS, PIECE_WORK, ENGAGEMENT_CODE, UNDERSTANDING_CODE, DETAILS , UNPAID_WORK_BEHAVIOUR , UNPAID_WORK_ACTION , SICK_NOTE_RECEIVED_DATE, SICK_NOTE_EXPIRY_DATE, UNEXCUSED_ABSENCE_FLAG, IN_TIME, OUT_TIME, TRANSPORT_CODE, PERFORMANCE_CODE, AGREED_TRAVEL_HOUR, CHECK_BOX_1 , CHECK_BOX_2, HIDDEN_COMMENT_TEXT , IN_CHARGE_STAFF_ID , OFF_PRGREF_ID , CONTACT_PERSON_NAME, TO_ADDRESS_OWNER_CLASS, TO_ADDRESS_ID , TO_CORPORATE_ID , UNPAID_WORK_SUPERVISOR , TA_ID, RECORD_SOURCE, CHECK_SUM , PROV_STATE_CODE, SCHEDULED_TRIP_ID FROM V_OFFENDER_ALL_SCHEDULES_2 WHERE EVENT_ID = :EVENT_ID
}

GET_OLD_REC_OFF_BOOKING {
select * from offender_bookings where offender_book_id = :offender_book_id 
}

GET_OLD_REC_OFFENDER_EXTERNAL_MOVEMENTS{
SELECT * FROM OFFENDER_EXTERNAL_MOVEMENTS WHERE OFFENDER_BOOK_ID =:OFFENDER_BOOK_ID and MOVEMENT_SEQ = :MOVEMENT_SEQ
}

