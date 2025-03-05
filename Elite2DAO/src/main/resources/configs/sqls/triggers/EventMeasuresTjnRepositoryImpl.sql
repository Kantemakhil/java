EVENT_MEASURES_TJN_INSERT{
insert into EVENT_MEASURES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, EVENT_MEASURE_ID, EVENT_TYPE, EVENT_SUB_TYPE , MEASURES_STANDARD_FLAG, LIST_SEQ, ACTIVE_FLAG , UPDATE_ALLOWED_FLAG, EXPIRY_DATE, CREATE_DATE , CREATE_USER_ID, MODIFY_USER_ID, SEAL_FLAG ) 
values ('INS', :createUserId, SYSDATE(), null, null, 0, :eventMeasureId, :eventType, :eventSubType , :measuresStandardFlag, :listSeq, :activeFlag , :updateAllowedFlag, :expiryDate, :createDate , :createUserId, :modifyUserId, :sealFlag )
}

EVENT_MEASURES_TJN_UPDATE{
insert into EVENT_MEASURES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, EVENT_MEASURE_ID, EVENT_TYPE, EVENT_SUB_TYPE , MEASURES_STANDARD_FLAG, LIST_SEQ, ACTIVE_FLAG , UPDATE_ALLOWED_FLAG, EXPIRY_DATE, CREATE_DATE , CREATE_USER_ID, MODIFY_USER_ID, SEAL_FLAG ) 
values ('UPD', :createUserId, SYSDATE(), null, null, 0, :eventMeasureId, :eventType, :eventSubType , :measuresStandardFlag, :listSeq, :activeFlag , :updateAllowedFlag, :expiryDate, :createDate , :createUserId, :modifyUserId, :sealFlag)
}

EVENT_MEASURES_TJN_DELETE{
insert into EVENT_MEASURES_JN (JN_OPERATION, JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, JN_SESSION, EVENT_MEASURE_ID, EVENT_TYPE, EVENT_SUB_TYPE , MEASURES_STANDARD_FLAG, LIST_SEQ, ACTIVE_FLAG , UPDATE_ALLOWED_FLAG, EXPIRY_DATE, CREATE_DATE , CREATE_USER_ID, MODIFY_USER_ID, SEAL_FLAG )
values ('DEL', :createUserId, SYSDATE(), null, null, 0, :eventMeasureId, :eventType, :eventSubType , :measuresStandardFlag, :listSeq, :activeFlag , :updateAllowedFlag, :expiryDate, :createDate , :createUserId, :modifyUserId, :sealFlag)
}