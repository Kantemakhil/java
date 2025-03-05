PRINS_QM_PROCESSES_CUR{
 SELECT PROCESS_ID FROM QM_PROCESSES WHERE NAME = :name
}
PRINS_QM_PROCESSES{
INSERT INTO QM_PROCESSES (PROCESS_ID  ,NAME ,DESCRIPTION ,EXECUTION_TYPE  ,LOCATION_TYPE  ,ACTIVE_FLAG,EXPIRY_DATE ,EVENT_TYPE,create_datetime,modify_datetime,create_user_id )VALUES (:processId,:name  ,:description ,'PARALELL','SINGLE' ,:activeFlag  ,:expiryDate,:eventType,:createDatetime,:modifyDatetime,:createUserId )
}
PRINS_QM_COMPOSITIONS{
INSERT INTO QM_COMPOSITIONS (COMPOSITION_ID  ,MANAGING_AGY_LOC_ID    ,EVENT_TYPE_AGY_LOC_ID     ,PROCESS_ID ,EVENT_TYPE,ACTIVE_FLAG ,EXPIRY_DATE,create_datetime,modify_datetime,create_user_id ) VALUES (:compositionId  ,:managingAgyLocId ,:eventTypeAgyLocId ,:processId ,:eventType ,:activeFlag ,:expiryDate,:createDatetime,:modifyDatetime,:createUserId )
}
PRINS_QM_OBJECTS{
 INSERT INTO QM_OBJECTS (OBJECT_ID ,NAME  ,DESCRIPTION  ,PROCESS_ID ,QM_DATA_TYPE_DOMAIN ,QM_DATA_TYPE_CODE ,PURPOSE,create_datetime,modify_datetime,create_user_id ) VALUES ( OBJECT_ID_SEQ.NEXTVAL,'OFFENDER_BOOK_ID','OFFENDER BOOK ID',:processId,'QM_DATA_TYPE','NUMBER','EVENT',:createDatetime,:modifyDatetime,:createUserId )
}
PRUPD_QM_COMPOSITIONS{
UPDATE QM_COMPOSITIONS SET ACTIVE_FLAG     =:activeFlag ,EXPIRY_DATE     = :expiryDate,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId WHERE COMPOSITION_ID  = :compositionId AND PROCESS_ID      = :processId
}