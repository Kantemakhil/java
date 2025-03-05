UPDATE_SENTENCE_DATA {
 UPDATE OFFENDER_SENTENCES SET
 	   SENTENCE_STATUS=:status,
       STATUS_UPDATE_REASON=:statusUpdateReason,
       STATUS_UPDATE_STAFF_ID=:statusUpdateStaffId,
       STATUS_UPDATE_DATE=:statusUpdateDate
 WHERE CASE_ID=:caseId and OFFENDER_BOOK_ID=:offenderBookId AND SENTENCE_SEQ=:line
 
 }
 
 GET_CONDITION_DATA{
	SELECT COMM_CONDITION_TYPE,
	COMM_CONDITION_CODE,
    CONDITION_STATUS,
    START_DATE,
    EXPIRY_DATE,
    LENGTH,
    OFFENDER_BOOK_ID, 
    LENGTH_UNIT, 
    STATUS_UPDATE_DATE,
    STATUS_UPDATE_REASON,
    STATUS_UPDATE_STAFF_ID,
    OFFENDER_SENT_CONDITION_ID
    FROM offender_sent_conditions WHERE sentence_seq =:sentence_seq AND comm_condition_type =:comm_condition_type AND offender_book_id =:offender_book_id
 }
 
 UPDATE_CONDITION{
 	 UPDATE OFFENDER_SENT_CONDITIONS SET
 	   CONDITION_STATUS=:conditionStatus,
       STATUS_UPDATE_REASON=:statusUpdateReason,
       STATUS_UPDATE_STAFF_ID=:statusUpdateStaffId,
       STATUS_UPDATE_DATE=:statusUpdateDate
       WHERE OFFENDER_BOOK_ID=:offenderBookId AND OFFENDER_SENT_CONDITION_ID=:offenderSentConditionId
 
 }
 
GET_CONDITION_TYPE_LOV{
  SELECT comm_condition_code code, description
  FROM community_conditions
 WHERE (active_flag = 'Y')
  ORDER BY list_seq,description, code
} 

CONDITION_STATUS {
SELECT   description nbt_case_status, 
         code case_status	 
    FROM reference_codes 
   WHERE domain = 'ACTIVE_TYPE'
     AND (active_flag = 'Y')      
ORDER BY description, list_seq
}

STATUS_UPDATE_REASON_LOV{
	 SELECT  
	        s.description description,
	        l.update_reason_code code
	FROM 
		sentence_update_reasons s,
		legal_update_reasons l
	WHERE
		 s.sentence_calc_type=:SENTENCE_CALC_TYPE
	AND s.update_reason_code = l.update_reason_code
	AND     l.reason_category NOT IN ('CASE_UNMERGE', 'CASE_MERGE')   
	AND     s.active_flag = 'Y' 
	AND     l.active_flag = 'Y'
	ORDER BY 
	        l.list_seq,
	        s.description    
}

UPDATE_CASES {
UPDATE OFFENDER_CASES
SET
    STATUS_UPDATE_REASON  =:statusUpdateReason, 
    CASE_STATUS =:caseStatus,   
    STATUS_UPDATE_COMMENT =:statusUpdateComment, 
    STATUS_UPDATE_DATE =:status_update_date,
    STATUS_UPDATE_STAFF_ID =:status_update_staff_id
 WHERE CASE_SEQ =:case_Seq 
}