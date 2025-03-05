insert
	into
	OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDPAROR',
'ocdparor.pleaseselecttermtype',
'Please select term type',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null);
------------------------------------------------------------------
update
	SYSTEM_LABELS
set
	modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,MSG_VALUE = 'Elite 5.0.0.3.7'
where
	MODULE_NAME = 'LOGIN'
	and MSG_KEY in ('home.title.header', 'login.header');    