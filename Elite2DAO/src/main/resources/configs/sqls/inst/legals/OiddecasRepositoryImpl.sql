DELETE_COURT_CASE {
delete from offender_cases where offender_book_id=:offenderBookId and case_id=:caseId
}

DELETE_COURT_EVENT {
delete from court_events where offender_book_id=:offenderBookId and case_id=:caseId and event_id=:eventId
}

IS_LINKED_CASE {
select tag_delete_legals.is_case_linked(:offenderChargeId) from dual
}

IS_SENTENCE_EXIST {
select tag_delete_legals.is_sent_offence_attached
                                                (:offenderBookId,
                                                 :offenderChargeId
                                                ) from dual
}

IS_LINKED_CASE_QUERY {
SELECT COUNT(*)
FROM link_case_txns
WHERE offender_charge_id = :OFFENDERCHARGEID
}

IS_SENTENCE_EXIST_QUERY {
SELECT 'X'
FROM offender_sentence_charges
WHERE offender_charge_id = :OFFENDERCHARGEID
}

DELETE_COURT_EVENT_CHARGES {
DELETE from court_event_charges where offender_charge_id=:offenderChargeId
}
DELETE_OFFENDER_OFFENSES {
delete from offender_charges where offender_book_id=19644 and offender_charge_id=:offenderChargeId
}

DELETE_SENTENCES {
DELETE FROM OFFENDER_SENTENCES WHERE OFFENDER_BOOK_ID=:offenderBookId AND SENTENCE_SEQ=:sentenceSeq
}

IS_CONSEC_SENTENCE_EXIST {
SELECT COUNT (sentence_seq) FROM offender_sentences
START WITH offender_book_id = :offenderBookId
AND sentence_seq = :sentenceSeq
CONNECT BY consec_to_sentence_seq = PRIOR sentence_seq
AND offender_book_id = :offenderBookId
AND sentence_status = 'A'
}