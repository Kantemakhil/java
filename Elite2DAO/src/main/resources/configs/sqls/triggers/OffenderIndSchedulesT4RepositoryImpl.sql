OFFENDER_IND_SCHEDULES_T4_OFFENDER_IND_SCHEDULES_GET{
SELECT EVENT_ID,OFFENDER_BOOK_ID,EVENT_DATE,START_TIME,END_TIME,EVENT_CLASS,EVENT_TYPE,EVENT_SUB_TYPE,EVENT_STATUS,COMMENT_TEXT,HIDDEN_COMMENT_TEXT,APPLICATION_DATE,PARENT_EVENT_ID,AGY_LOC_ID,TO_AGY_LOC_ID,TO_INTERNAL_LOCATION_ID,FROM_CITY,TO_CITY,CRS_SCH_ID,ORDER_ID,SENTENCE_SEQ,OUTCOME_REASON_CODE,JUDGE_NAME,CHECK_BOX_1,CHECK_BOX_2,IN_CHARGE_STAFF_ID,CREDITED_HOURS,REPORT_IN_DATE,PIECE_WORK,ENGAGEMENT_CODE,UNDERSTANDING_CODE,DETAILS,CREDITED_WORK_HOUR,AGREED_TRAVEL_HOUR,UNPAID_WORK_SUPERVISOR,UNPAID_WORK_BEHAVIOUR,UNPAID_WORK_ACTION,SICK_NOTE_RECEIVED_DATE,SICK_NOTE_EXPIRY_DATE,COURT_EVENT_RESULT,UNEXCUSED_ABSENCE_FLAG,CREATE_USER_ID,MODIFY_USER_ID,CREATE_DATETIME,MODIFY_DATETIME,ESCORT_CODE,CONFIRM_FLAG,DIRECTION_CODE,TO_CITY_CODE,FROM_CITY_CODE,OFF_PRGREF_ID,IN_TIME,OUT_TIME,PERFORMANCE_CODE,TEMP_ABS_SCH_ID,REFERENCE_ID,TRANSPORT_CODE,APPLICATION_TIME,CONTACT_PERSON_NAME,TO_ADDRESS_OWNER_CLASS,TO_ADDRESS_ID,RETURN_DATE,RETURN_TIME,TO_CORPORATE_ID,TA_ID,EVENT_OUTCOME,OFFENDER_PRG_OBLIGATION_ID,PROV_STATE_CODE,SEAL_FLAG,SCHEDULED_TRIP_ID FROM OFFENDER_IND_SCHEDULES WHERE EVENT_ID = :eventId
}
OFFENDER_IND_SCHEDULES_T4_GET_CASENOTE_DETAILS_C{
SELECT ocn.offender_book_id, ocn.event_id, ocn.contact_date,ocn.contact_time, ocn.note_source_code, ocn.case_note_type,ocn.case_note_sub_type, ocn.case_note_text, ocn.date_creation,ocn.time_creation, ocn.staff_id, ocn.check_box1, ocn.check_box2,ocn.check_box3, ocn.check_box4, ocn.check_box5 FROM offender_case_notes ocn WHERE event_id = :eventId AND case_note_id = (SELECT MAX (case_note_id) FROM offender_case_notes ocn WHERE event_id = :eventId)
}
OFFENDER_IND_SCHEDULES_T4_OFFENDER_CASE_NOTES{
INSERT INTO offender_case_notes(offender_book_id, event_id, case_note_id, contact_date, contact_time, note_source_code, case_note_type, case_note_sub_type, case_note_text, date_creation, time_creation, staff_id, check_box1, check_box2, check_box3, check_box4, check_box5,create_user_id, create_datetime)
VALUES (:offenderBookId, :eventId, :caseNoteId, :contactDate, :contactTime, :noteSourceCode, :caseNoteType, :caseNoteSubType, :caseNoteText, :dateCreation, :timeCreation, :staffId, :checkBox1, :checkBox2, :checkBox3, :checkBox4, :checkBox5, :createUserId, current_timestamp)
}
OFFENDER_IND_SCHEDULES_T4_CASE_NOTE_ID_NEXTVAL{
select NEXTVAL('case_note_id')
}