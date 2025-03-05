INSERT INTO SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDPROGR','ocdprogr.orderdescription','Order Description','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDPROGR','ocdprogr.lineno','Line No','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDPROGR','ocdprogr.commencedate','Commence Date','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'),'OCMSUWPJ','ocmsuwpj.providertype','Provider Type','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'),'OCMSUWPJ','ocmsuwpj.provider','Provider','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'),'OCMSUWPJ','ocmsuwpj.providertypemandatory','Provider Type must be entered	','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'),'OCMSUWPJ','ocmsuwpj.providermandatory','Provider must be entered','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


insert into SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 values( NEXTVAL('LABLE_ID_SEQUENCE') ,'OUMCPASS','oumcpass.passwordsarenotthesameretypepassword','Password did not match, Please Re-enter Password','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 values( NEXTVAL('LABLE_ID_SEQUENCE') , 'COMMON', 'common.passwordchangedsuccessfully', 'Password Changed Successfully', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


insert into SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 values( NEXTVAL('LABLE_ID_SEQUENCE') , 'OUMCPASS', 'oumcpass.pleaseentervalidpassword', 'Please Enter Valid Password', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 values(NEXTVAL('LABLE_ID_SEQUENCE'), 'OIDSTABS', 'oidstabs.doyouwanttocontinue', 'Do you want to continue with appointment?', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 values(NEXTVAL('LABLE_ID_SEQUENCE'), 'OIDSTABS', 'oidstabs.nonassociationconflictmsg', 'The selected offender has a non-association with below offender(s),who has been scheduled to be released for temporary absence or return from temporary absence on the same date.','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 values( NEXTVAL('LABLE_ID_SEQUENCE') , 'OIDCRTEV', 'oidcrtev.cctvORonline', 'The selected offender has a non-association with below offender(s) who has Online/Video Link(CC TV) Court event scheduled on the same date and same location', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
 
--
UPDATE SYSTEM_LABELS SET MSG_VALUE = 'Elite 5.0.0.0.11' WHERE MODULE_NAME = 'LOGIN' AND MSG_KEY IN ('home.title.header', 'login.header'); 