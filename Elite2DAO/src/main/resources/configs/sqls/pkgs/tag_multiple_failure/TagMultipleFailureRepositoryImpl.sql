CHECK_UA_EVENT_OUTCOME_OLD{
SELECT COUNT(*) FROM EVENT_MEASURES event_measure, EVENT_MEASURE_OUTCOMES event_out WHERE event_measure.event_type = :eventType AND event_measure.event_sub_type = :eventSubType AND event_out.outcome_code = :eventOutcome AND event_out.set_counter_flag = 'Y' AND event_out.event_measure_id = event_measure.event_measure_id
}

CHECK_UA_EVENT_OUTCOME_NEW{
SELECT COUNT(*) FROM EVENT_MEASURES event_measure, EVENT_MEASURE_OUTCOMES event_out WHERE event_measure.event_type = :eventType AND event_measure.event_sub_type = :eventSubType AND event_out.outcome_code = :eventOutcome AND event_out.set_counter_flag = 'Y' AND event_out.event_measure_id = event_measure.event_measure_id
}


OCDCLOGS_OSUA_CUR_SELECT_OPERATION{
 SELECT offender_book_id, sentence_seq, counted_flag FROM OFFENDER_SENTENCE_UA_EVENTS WHERE event_id = :eventId --FOR UPDATE OF sentence_seq WAIT 1
}

OCDCLOGS_OSUA_REC_DELETE_OPERATION{
DELETE FROM OFFENDER_SENTENCE_UA_EVENTS WHERE OFFENDER_BOOK_ID = :offenderBookId AND SENTENCE_SEQ = :sentenceSeq AND EVENT_ID =:eventId
}


OCDCLOGS_MF_ADJUST_SELECT_OPERATION{
 SELECT * FROM GT_MULTIPLE_FAILURE_ADJ WHERE offender_book_id = :offenderBookId AND sentence_seq = :sentenceSeq
}


OCDCLOGS_MFD_ADJUST_UPDATE{
UPDATE GT_MULTIPLE_FAILURE_ADJ SET adjustment = adjustment + :adjustment WHERE offender_book_id = :offenderBookId AND sentence_seq = :sentenceSeq
}
OCDCLOGS_MFD_ADJUST_INSERT{
INSERT INTO GT_MULTIPLE_FAILURE_ADJ( offender_book_id, sentence_seq, adjustment) VALUES ( :offenderBookId, :sentenceSeq, :adjustment)
}


OCDCLOGS_OSECUR_SELECT_OPERATION
{
SELECT offender_book_id, sentence_seq, event_date FROM v_offender_sentence_events v_off_sent WHERE v_off_sent.event_id = :eventId
}
OCDCLOGS_COUNT_SENTENCE_UA_SELECT_OPERATION{
 SELECT COUNT (*) FROM OFFENDER_SENTENCE_UA_EVENTS WHERE offender_book_id = :offenderBookId AND sentence_seq = :sentenceSeq AND event_date = :eventDate AND event_id != :eventId
}
OCDCLOGS_UA_EVENT_EXISTS{
 SELECT COUNT(*) FROM OFFENDER_SENTENCE_UA_EVENTS WHERE offender_book_id = :offenderBookId AND sentence_seq = :sentenceSeq AND event_id = :eventId
}
OCDCLOGS_UA_EVENT_EXISTS_DEL_OPERATION{
DELETE FROM OFFENDER_SENTENCE_UA_EVENTS WHERE offender_book_id = :offenderBookId AND sentence_seq = :sentenceSeq AND event_id = :eventId
}

OCDCLOGS_CREATE_UAS_INSERT{
-- INSERT INTO OFFENDER_SENTENCE_UA_EVENTS (offender_book_id, sentence_seq, event_id, counted_flag, event_date ) VALUES (:offenderBookId, :sentenceSeq, :eventId, :countedFlag, :eventDate )
insert into OFFENDER_SENTENCE_UA_EVENTS (offender_book_id, sentence_seq, event_id, counted_flag, event_date , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:offenderBookId, :sentenceSeq, :eventId, :countedFlag, :eventDate , :nbtCreateUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
}

CHECK_UA_EVENT_OUTCOME_ONE{
 select count(*) from EVENT_MEASURES event_measure, EVENT_MEASURE_OUTCOMES event_out where event_measure.event_type = :p_event_type and event_measure.event_sub_type = :p_event_sub_type and event_out.outcome_code = :p_event_outcome and event_out.set_counter_flag = 'Y' and event_out.event_measure_id = event_measure.event_measure_id
}