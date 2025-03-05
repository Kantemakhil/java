INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
   VALUES( NEXTVAL('lable_id_sequence') , 'OUMPERSO','oumperso.mailid','Mail Id', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
 
UPDATE SYSTEM_LABELS SET MSG_VALUE = 'Elite 5.0.0.0.6 HF_1' WHERE MODULE_NAME = 'LOGIN' AND MSG_KEY IN ('home.title.header', 'login.header'); 
