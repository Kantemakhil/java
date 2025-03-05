GET_COURSE_ACTIVITIES_DATA{
SELECT provider_party_class, provider_party_id, provider_party_code, program_id FROM course_activities ca WHERE ca.crs_acty_id = :p_crs_acty_id
}

GET_REQUIREMENT{
SELECT r.description FROM program_services p, reference_codes r WHERE p.program_id = :l_program_id AND p.program_category = r.code AND r.domain = 'PS_CATEGORY'
}

GET_LOCATION{
SELECT a.description FROM agency_locations a WHERE a.agy_loc_id = :l_provider_party_code
}

GET_LOCATION_FRM_TEAMS{
SELECT t.description FROM teams t WHERE t.team_id = :l_provider_party_id
}

GET_LOCATION_FRM_CORPORATES{
SELECT c.corporate_name FROM corporates c WHERE c.corporate_id = :l_provider_party_id
}
GET_COURTEVENT_SENTENCES{
SELECT ces.result_code chargeStatus, r.description, r.disposition_code FROM court_event_sentences ces, offence_result_codes r WHERE ces.offender_book_id =:p_offender_book_id AND ces.event_id =:p_event_id AND ces.sentence_seq =:p_sentence_seq AND ces.result_code = r.result_code
}

DELETE_FROM_COURT_EVENT_SENTENCES{
DELETE FROM COURT_EVENT_SENTENCES WHERE EVENT_ID = :P_EVENT_ID AND OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND SENTENCE_SEQ = :P_SENTENCE_SEQ
}

UPDATE_COURT_EVENT_SENTENCES{
--UPDATE COURT_EVENT_SENTENCES SET RESULT_CODE = :P_RESULT_CODE WHERE EVENT_ID = :P_EVENT_ID AND OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND SENTENCE_SEQ = :P_SENTENCE_SEQ
update COURT_EVENT_SENTENCES set RESULT_CODE = :P_RESULT_CODE, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where EVENT_ID = :P_EVENT_ID and OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID and SENTENCE_SEQ = :P_SENTENCE_SEQ
}

INSERT_INTO_COURT_EVENT_SENTENCES{
--INSERT INTO COURT_EVENT_SENTENCES ( RESULT_CODE, EVENT_ID, OFFENDER_BOOK_ID, SENTENCE_SEQ ) VALUES ( :P_RESULT_CODE, :P_EVENT_ID, :P_OFFENDER_BOOK_ID, :P_SENTENCE_SEQ )
insert into COURT_EVENT_SENTENCES ( RESULT_CODE, EVENT_ID, OFFENDER_BOOK_ID, SENTENCE_SEQ , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME) values ( :P_RESULT_CODE, :P_EVENT_ID, :P_OFFENDER_BOOK_ID, :P_SENTENCE_SEQ , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}
