INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('LABLE_ID_SEQUENCE'),'OCDUPROJ','ocduproj.debitislargefornegativebal','Debit is too large and would cause a negative balance for total credited hours.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('LABLE_ID_SEQUENCE'),'OCMSUWPJ','ocmsuwpj.childrecordfound','Child record exist','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--
UPDATE SYSTEM_LABELS SET MSG_VALUE = 'Elite 5.0.0.0.11_HF' WHERE MODULE_NAME = 'LOGIN' AND MSG_KEY IN ('home.title.header', 'login.header');