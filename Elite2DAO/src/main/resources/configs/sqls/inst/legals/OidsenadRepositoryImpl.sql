SENTENCE_ADJUSTMENT_GRID_DATA{
select offender_book_id,case_info_number,offence_code,sentence_seq,
		sentence_calc_type,sentence_status 
		from V_OFFENDER_SENTENCE_CHARGES 
		where offender_book_id= :offenderBookId
		order by SENTENCE_STATUS, SENTENCE_SEQ 
}
DEBIT_TYPE_LOV{
SELECT description, sentence_adjust_code 
FROM sentence_adjustments 
WHERE usage_code = 'SENT'
ORDER BY 1
}
DEBIT_CREDIT_GRID_DATA{
 select OFF_SENT_ADJ.OFFENDER_SENTENCE_ADJUST_ID,OFF_SENT_ADJ.OFFENDER_BOOK_ID,
        OFF_SENT_ADJ.SENTENCE_ADJUST_CODE,OFF_SENT_ADJ.ADJUST_FROM_DATE,OFF_SENT_ADJ.ADJUST_TO_DATE,OFF_SENT_ADJ.ADJUST_DAYS,
        OFF_SENT_ADJ.COMMENT_TEXT ,OFF_SENT_ADJ.ADJUST_DATE,OFF_SENT.OFFENCE_DATE
        from OFFENDER_SENTENCE_ADJUSTS OFF_SENT_ADJ,V_OFFENDER_SENTENCE_CHARGES OFF_SENT
        where OFF_SENT_ADJ.OFFENDER_KEY_DATE_ADJUST_ID IS NULL 
        AND OFF_SENT_ADJ.SENTENCE_SEQ =:sentence_seq 
        AND OFF_SENT_ADJ.OFFENDER_BOOK_ID=:offender_book_id AND OFF_SENT.OFFENDER_BOOK_ID = OFF_SENT_ADJ.OFFENDER_BOOK_ID 
        AND OFF_SENT.SENTENCE_SEQ = OFF_SENT_ADJ.SENTENCE_SEQ 
        order by OFF_SENT_ADJ.ADJUST_FROM_DATE DESC
}
SENT_ADJ_PREINSERT_ID{
 SELECT offender_sentence_adjust_id.nextval FROM DUAL

}
INSERT_DEBIT_CREDIT_RECORD{
INSERT INTO OFFENDER_SENTENCE_ADJUSTS
(OFFENDER_SENTENCE_ADJUST_ID,SENTENCE_ADJUST_CODE,ADJUST_DATE,ADJUST_DAYS,OFFENDER_BOOK_ID,SENTENCE_SEQ,
CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,ADJUST_FROM_DATE,ADJUST_TO_DATE,COMMENT_TEXT)
VALUES
(:offenderSentenceAdjustId,:sentenceAdjustCode,:postedDate,:days,:offenderBookId,
:sentenceSeq,:createDateTime,:createUserId,:modifyDateTime,:modifyUserId,:fromDate,:toDate,:comment)
}

UPDATE_DEBIT_CREDIT_RECORD
{
UPDATE OFFENDER_SENTENCE_ADJUSTS SET 
SENTENCE_ADJUST_CODE=:sentenceAdjustCode,
ADJUST_DATE=:postedDate,
ADJUST_DAYS=:days,
ADJUST_FROM_DATE=:fromDate,
ADJUST_TO_DATE=:toDate,
COMMENT_TEXT=:comment
WHERE OFFENDER_SENTENCE_ADJUST_ID=:offenderSentenceAdjustId 
AND SENTENCE_SEQ=:sentenceSeq
}

DELETE_DEBIT_CREDIT_RECORD
{
DELETE FROM OFFENDER_SENTENCE_ADJUSTS WHERE 
OFFENDER_SENTENCE_ADJUST_ID=:offenderSentenceAdjustId
}



SENTENCE_ADJUSTMENT_GRID_DATA{
select offender_book_id,case_info_number,offence_code,sentence_seq,
		sentence_calc_type,sentence_status 
		from V_OFFENDER_SENTENCE_CHARGES 
		where offender_book_id= :offenderBookId
		order by SENTENCE_STATUS, SENTENCE_SEQ 
}
DEBIT_TYPE_LOV{
SELECT description, sentence_adjust_code 
FROM sentence_adjustments 
WHERE usage_code = 'SENT'
ORDER BY 1
}
DEBIT_CREDIT_GRID_DATA{
 select OFF_SENT_ADJ.OFFENDER_SENTENCE_ADJUST_ID,OFF_SENT_ADJ.OFFENDER_BOOK_ID,
        OFF_SENT_ADJ.SENTENCE_ADJUST_CODE,OFF_SENT_ADJ.ADJUST_FROM_DATE,OFF_SENT_ADJ.ADJUST_TO_DATE,OFF_SENT_ADJ.ADJUST_DAYS,
        OFF_SENT_ADJ.COMMENT_TEXT ,OFF_SENT_ADJ.ADJUST_DATE
        from OFFENDER_SENTENCE_ADJUSTS OFF_SENT_ADJ,V_OFFENDER_SENTENCE_CHARGES OFF_SENT
        where OFF_SENT_ADJ.OFFENDER_KEY_DATE_ADJUST_ID IS NULL 
        AND OFF_SENT_ADJ.SENTENCE_SEQ =:sentence_seq 
        AND OFF_SENT_ADJ.OFFENDER_BOOK_ID=:offender_book_id AND OFF_SENT.OFFENDER_BOOK_ID = OFF_SENT_ADJ.OFFENDER_BOOK_ID 
        AND OFF_SENT.SENTENCE_SEQ = OFF_SENT_ADJ.SENTENCE_SEQ 
        order by OFF_SENT_ADJ.ADJUST_FROM_DATE DESC
}

ADJUST_TYPE_LOV {
SELECT description, sentence_adjust_code
FROM sentence_adjustments
WHERE usage_code = 'BKG'
ORDER BY sentence_adjust_code, description
}

ADJUST_GRID_DATA {
 SELECT OFFENDER_KEY_DATE_ADJUST_ID,
 OFFENDER_BOOK_ID,
 SENTENCE_ADJUST_CODE,
 ADJUST_FROM_DATE,
 ADJUST_TO_DATE,
 ADJUST_DAYS,
 ADJUST_STATUS,
 COMMENT_TEXT,
 ADJUST_DATE,
 CREATE_DATETIME,
 CREATE_USER_ID,
 MODIFY_DATETIME,
 MODIFY_USER_ID,
 SEAL_FLAG
 FROM OFFENDER_KEY_DATE_ADJUSTS WHERE OFFENDER_BOOK_ID =:offenderBookId 
}

GET_NEXT_ADJUST_ID {
  SELECT offender_key_date_adjust_id.nextval FROM DUAL
}

INSERT_ADJUST_GRID_RECORD {

INSERT INTO OFFENDER_KEY_DATE_ADJUSTS
(
 OFFENDER_KEY_DATE_ADJUST_ID,
 OFFENDER_BOOK_ID,
 SENTENCE_ADJUST_CODE,
 ADJUST_FROM_DATE,
 ADJUST_TO_DATE,
 ADJUST_DAYS,
 ADJUST_STATUS,
 COMMENT_TEXT,
 ADJUST_DATE,
 CREATE_DATETIME,
 CREATE_USER_ID,
 MODIFY_DATETIME,
 MODIFY_USER_ID,
 SEAL_FLAG
 )
VALUES
(
:keyDatesAdjustId,
:offenderBookId,
:sentenceAdjustCode,
:adjustFromDate,
:adjustToDate,
:adjustDays,
:adjustStatus,
:commentText,
:adjustDate,
:createDateTime,
:createUserId,
:modifyDateTime,
:modifyUserId,
:sealFlag)
}

UPDATE__ADJUST_GRID_RECORD {
	UPDATE OFFENDER_KEY_DATE_ADJUSTS SET
	 OFFENDER_KEY_DATE_ADJUST_ID =:keyDatesAdjustId,
	 OFFENDER_BOOK_ID =:offenderBookId,
	 SENTENCE_ADJUST_CODE =:sentenceAdjustCode,
	 ADJUST_FROM_DATE =:adjustFromDate,
	 ADJUST_TO_DATE =:adjustToDate,
	 ADJUST_DAYS =:adjustDays,
	 ADJUST_STATUS =:adjustStatus,
	 COMMENT_TEXT =:commentText,
	 ADJUST_DATE =:adjustDate,
	 CREATE_DATETIME =:createDateTime,
	 CREATE_USER_ID =:createUserId,
	 MODIFY_DATETIME =:modifyDateTime,
	 MODIFY_USER_ID =:modifyUserId,
	 SEAL_FLAG =:sealFlag
	 WHERE OFFENDER_BOOK_ID =:offenderBookId AND OFFENDER_KEY_DATE_ADJUST_ID =:keyDatesAdjustId
}

DELETE_ADJUST_GRID_RECORD {
	DELETE FROM OFFENDER_KEY_DATE_ADJUSTS
	WHERE OFFENDER_BOOK_ID =:offenderBookId AND OFFENDER_KEY_DATE_ADJUST_ID =:keyDatesAdjustId
}

