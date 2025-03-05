UPDATE_V_OFFENDER_ALL_SCHEDULES_2 {
UPDATE V_OFFENDER_ALL_SCHEDULES_2 SET EVENT_STATUS = 'DEL' WHERE OFFENDER_BOOK_ID = :offenderBookId AND EVENT_TYPE = :eventType AND REFERENCE_ID = :referenceId
}

ACTIVE_SENTA_CUR{
 SELECT sentence_seq FROM offender_sentences WHERE offender_book_id =:p_offender_book_id AND sentence_status = 'A' AND sentence_level = 'IND'
}
INSERT_OFFENDER_CASE_NOTE_SENTE{
insert into offender_case_note_sents (case_note_id, offender_book_id, sentence_seq , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:NcasenoteId, :offenderBookId, :sentenceSeq , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}

INSERT_OFFENNDER_IND_SCH{
insert into offender_ind_sch_sents (event_id, offender_book_id, sentence_seq , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:schId, :offenderBookId, :sentenceSeq , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}

UPDATE_SCHEDULE_UPDATING_PHYSICAL_RECORDS{
UPDATE v_offender_all_schedules_2 SET event_date = :P_EVENT_DATE, start_time = :P_START_TIME, end_time = :P_END_TIME, to_agy_loc_id = :P_AGY_LOC_ID, in_charge_staff_id = :P_IN_CHARGE_STAFF_ID, comment_text = :P_COMMENT_TEXT, event_outcome = :P_EVENT_OUTCOME, unexcused_absence_flag = :P_FLAG, event_status = :P_EVENT_STATUS, SMS_SCHEDULE_HOURS_BEFORE =:smsScheduleHoursBefore, EMAIL_SCHEDULE_HOURS_BEFORE =:emailScheduleHoursBefore, EMAIL_FLAG =:emailFlag, SMS_FLAG =:smsFlag WHERE event_id = :P_EVENT_ID
}  
 
UPDATE_SCHEDULE_UPDATING_VIRTUAL_RECORDS{
 UPDATE v_offender_all_schedules_2 SET event_date = :P_EVENT_DATE, start_time = :P_START_TIME, end_time = :P_END_TIME, to_agy_loc_id = :P_AGY_LOC_ID, in_charge_staff_id = :P_IN_CHARGE_STAFF_ID, comment_text = :P_COMMENT_TEXT, event_outcome = :P_EVENT_OUTCOME, unexcused_absence_flag = :P_FLAG, event_status = :P_EVENT_STATUS, SMS_SCHEDULE_HOURS_BEFORE =:smsScheduleHoursBefore, EMAIL_SCHEDULE_HOURS_BEFORE =:emailScheduleHoursBefore, EMAIL_FLAG =:emailFlag, SMS_FLAG =:smsFlag WHERE offender_book_id = :P_OFF_BOOK_ID AND event_type = :P_EVENT_TYPE AND reference_id = :P_REF_ID  
}
 
 DEL_NOTE_SCH_ASSOCIATION{
  DELETE FROM OFFENDER_IND_SCH_SENTS  WHERE EVENT_ID = :P_SCH_ID AND OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID
 }  
 
 GET_OUTCOME_FLAG_CUR {
	 SELECT coalesce (update_on_contact_log,'N')
            FROM event_measure_outcomes
           WHERE event_measure_id IN (
                    SELECT event_measure_id
                      FROM event_measures
                     WHERE event_type = :p_event_type
                       AND event_sub_type = :p_event_sub_type
                       AND active_flag = 'Y')
                AND active_flag = 'Y'
                AND outcome_code = :p_event_outcome
 }

GET_MODULE_NAME{
SELECT MODULE_NAME FROM WORKS WS WHERE WS.WORKFLOW_TYPE = 'CNOTE' AND WS.WORK_TYPE = :P_CONTACT_TYPE AND WS.WORK_SUB_TYPE = :P_CONTACT_SUB_TYPE
}          