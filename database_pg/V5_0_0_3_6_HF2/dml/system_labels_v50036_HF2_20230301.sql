update SYSTEM_LABELS set modify_datetime = current_timestamp ,modify_user_id = 'OMS_OWNER' ,MSG_VALUE = 'Save' where MODULE_NAME = 'OCDLEGLS' and MSG_KEY = 'ocdlegls.override';
update SYSTEM_LABELS set modify_datetime = current_timestamp ,modify_user_id = 'OMS_OWNER' ,MSG_VALUE = 'You do not have the privileges required to override key dates' where MODULE_NAME = 'OCDLEGLS' and MSG_KEY = 'ocdlegls.userdonthaveaccesstooverride';
update SYSTEM_LABELS set modify_datetime = current_timestamp ,modify_user_id = 'OMS_OWNER' ,MSG_VALUE = 'Add' where MODULE_NAME = 'OCUOVKEY' and MSG_KEY = 'ocuovkey.save';

update SYSTEM_LABELS set modify_datetime = current_timestamp ,modify_user_id = 'OMS_OWNER' ,MSG_VALUE = 'Verify' where MODULE_NAME = 'OCDLEGLS' and MSG_KEY = 'ocdlegls.verifybtn';
update SYSTEM_LABELS set modify_datetime = current_timestamp ,modify_user_id = 'OMS_OWNER' ,MSG_VALUE = 'You do not have the privileges required to verify key dates' where MODULE_NAME = 'OCDLEGLS' and MSG_KEY = 'ocdlegls.userdonthaveaccesstoverify';

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCUOVKEY','ocuovkey.datetype','Date Type','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL); 
  
 insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCUOVKEY','ocuovkey.originaldate','Original Date','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL); 
  
 insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCUOVKEY','ocuovkey.override','Override','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);

------------------------------------------------------------------


update
	SYSTEM_LABELS
set
	modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,MSG_VALUE = 'Elite 5.0.0.3.6_HF2'
where
	MODULE_NAME = 'LOGIN'
	and MSG_KEY in ('home.title.header', 'login.header');    