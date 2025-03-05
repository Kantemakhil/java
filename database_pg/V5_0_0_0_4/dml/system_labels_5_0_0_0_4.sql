insert into SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values( NEXTVAL('LABLE_ID_SEQUENCE') , 'OCUUCOND', 'ocuucondidlg.legalTextofCondition', 'Legal Text of Condition', 'LABEL', CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP, NULL, NULL);
	
insert into SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values( NEXTVAL('LABLE_ID_SEQUENCE') ,'OCUACOND', 'ocuacond.pleaseSelectLength', 'Please enter Length', 'LABEL', CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP, NULL, NULL);

insert into SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values( NEXTVAL('LABLE_ID_SEQUENCE') , 'OCUACOND', 'ocuacond.pleaseSelectUnit', 'Please enter Unit', 'LABEL', CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP, NULL, NULL);

UPDATE system_labels set MSG_VALUE = 'Calculation Reason' where MSG_KEY='ocucalcr.screenname';


