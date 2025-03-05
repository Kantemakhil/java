insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSCEXM','oidscexm.returnDate','Return Date','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSCEXM','oidscexm.returnTime','Return Time','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMIEPLV','oimieplv.onlyonedefaultiepshouldbechecked','Only one default Intake IEP should be selected','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDADMIS','oidadmis.iepconfigurationismissed','Intake completed but IEP configuration is missed','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'CALSCH','calsch.pleaseselecttype','Schedule Type must be entered','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
------------------------------------------------------------------


update
	SYSTEM_LABELS
set
	modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,MSG_VALUE = 'Elite 5.0.0.3.6_HF1'
where
	MODULE_NAME = 'LOGIN'
	and MSG_KEY in ('home.title.header', 'login.header');  