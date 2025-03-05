EVENT_MEASURE_OUTCOMES_TJN_INSERT{
insert into EVENT_MEASURE_OUTCOMES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, EVENT_MEASURE_ID, OUTCOME_CODE, SET_COUNTER_FLAG , PROMPT_USER_FLAG, LIST_SEQ, ACTIVE_FLAG , UPDATE_ALLOWED_FLAG, EXPIRY_DATE, CREATE_DATE , CREATE_USER_ID, UPDATE_ON_CONTACT_LOG , SEAL_FLAG ,CREATE_DATETIME)
values ('INS', :createUserId, SYSDATE(), null, null, 0, :eventMeasureId, :outcomeCode, :setCounterFlag , :promptUserFlag, :listSeq, :activeFlag , :updateAllowedFlag, :expiryDate, :createDate , :createUserId, :updateOnContactLog , :sealFlag ,current_timestamp)
}

EVENT_MEASURE_OUTCOMES_TJN_UPDATE{
insert into EVENT_MEASURE_OUTCOMES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, EVENT_MEASURE_ID, OUTCOME_CODE , SET_COUNTER_FLAG, PROMPT_USER_FLAG, LIST_SEQ , ACTIVE_FLAG, UPDATE_ALLOWED_FLAG, EXPIRY_DATE , CREATE_DATE, CREATE_USER_ID, UPDATE_ON_CONTACT_LOG, SEAL_FLAG ) 
values ('UPD', :createUserId, SYSDATE(), null, null, 0, :eventMeasureId, :outcomeCode, :setCounterFlag , :promptUserFlag, :listSeq, :activeFlag , :updateAllowedFlag, :expiryDate, :createDate , :createUserId, :updateOnContactLog , :sealFlag )
}

EVENT_MEASURE_OUTCOMES_TJN_DELETE{
insert into EVENT_MEASURE_OUTCOMES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, EVENT_MEASURE_ID, OUTCOME_CODE , SET_COUNTER_FLAG, PROMPT_USER_FLAG, LIST_SEQ , ACTIVE_FLAG, UPDATE_ALLOWED_FLAG, EXPIRY_DATE , CREATE_DATE, CREATE_USER_ID, UPDATE_ON_CONTACT_LOG, SEAL_FLAG ) 
values ('DEL', :createUserId, current_timestamp, null, null, 0, :eventMeasureId, :outcomeCode, :setCounterFlag , :promptUserFlag, :listSeq, :activeFlag , :updateAllowedFlag, :expiryDate, current_timestamp , :createUserId, :updateOnContactLog , :sealFlag )
}