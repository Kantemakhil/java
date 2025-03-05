INSERT INTO oms_owner.system_labels (label_id,module_name,msg_key,msg_value,msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES (NEXTVAL('lable_id_sequence'),'OCDCLOGS','ocdclogs.emailFlag','Email Reminder','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels (label_id,module_name,msg_key,msg_value,msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES (NEXTVAL('lable_id_sequence'),'OCDCLOGS','ocdclogs.smsFlag','SMS Reminder','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels (label_id,module_name,msg_key,msg_value,msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES (NEXTVAL('lable_id_sequence'),'OCDCLOGS','ocdclogs.emailScheduleHrsBefore','Email Reminder Timing (Hours)','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels (label_id,module_name,msg_key,msg_value,msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES (NEXTVAL('lable_id_sequence'),'OCDCLOGS','ocdclogs.smsScheduleHrsBefore','SMS Reminder Timing (Hours)','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

