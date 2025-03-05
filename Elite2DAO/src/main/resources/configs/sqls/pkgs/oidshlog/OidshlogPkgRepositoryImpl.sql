
SELECT_CLOB_QUERY{
      SELECT 'CLOB_IS_NULL' agyActivityCode
        FROM agency_shift_logs  
       WHERE agency_shift_logs.shift_log_seq =:SHIFT_LOG_SEQ 
         AND agency_shift_logs.observation_text IS NULL
 }
 
 UPDATE_AGENCY_SHIFT_LOGS{
  update agency_shift_logs set agency_shift_logs.observation_text = empty_clob(), MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where agency_shift_logs.shift_log_seq =:SHIFT_LOG_SEQ     
  }
  
SELECT_AGENCY_SHIFTLOGS{
	SELECT agency_shift_logs.observation_text agyActivityCode from agency_shift_logs 
	WHERE agency_shift_logs.shift_log_seq =:SHIFT_LOG_SEQ
}

GET_OBSERVATION_TEXT{
 SELECT AGENCY_SHIFT_LOGS.OBSERVATION_TEXT FROM AGENCY_SHIFT_LOGS WHERE AGENCY_SHIFT_LOGS.SHIFT_LOG_SEQ = :P_SHIFT_LOG_SEQ 
 }      