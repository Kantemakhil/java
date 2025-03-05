OCUMULTI_OFFBLOCK_FIND_V_OFFENDER_ALL_SCHEDULES {
 select * from v_offender_all_schedules where event_id in (SELECT DISTINCT v_off_sent.event_id FROM v_offender_sentence_events v_off_sent, offender_sentences off_sent, event_measures event_measure, event_measure_outcomes event_out WHERE v_off_sent.offender_book_id =:offBookId AND event_date = :eventDate AND event_id !=:eventId AND off_sent.offender_book_id = v_off_sent.offender_book_id AND event_measure.event_type = v_off_sent.event_type AND event_measure.event_sub_type = v_off_sent.event_sub_type AND v_off_sent.event_outcome = event_out.outcome_code AND off_sent.sentence_status = 'A' AND event_out.set_counter_flag = 'Y' AND event_out.event_measure_id = event_measure.event_measure_id)
}
OCUMULTI_OFFBLOCK_INSERT_V_OFFENDER_ALL_SCHEDULES {
	INSERT INTO V_OFFENDER_ALL_SCHEDULES() VALUES(:)
}