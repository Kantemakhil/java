INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDPSREP', 'ocdpsrep.defencesolicitor', 'Defence Solicitor', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDPSREP', 'ocdpsrep.reportauthor', 'Assigned Officer', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDPSREP', 'ocdpsrep.reportrequesthistory', 'Report Request History', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDPSREP', 'ocdpsrep.requestingofficer', 'Requesting Officer', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDPSREP', 'ocdpsrep.statusdate', 'Status Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
 
INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDPSREP', 'ocdpsrep.teamresponsible', 'Team Responsible', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
 
--
UPDATE SYSTEM_LABELS SET MSG_VALUE = 'Elite 5.0.0.0.11_HF' WHERE MODULE_NAME = 'LOGIN' AND MSG_KEY IN ('home.title.header', 'login.header');
