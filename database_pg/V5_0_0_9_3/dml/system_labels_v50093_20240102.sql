update
	SYSTEM_LABELS
set
	MSG_VALUE = 'Email addresses cannot be updated for active directory users' ,
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	module_name = 'OUMPERSO'
	and msg_key = 'oumperso.adusercannotbechanged' ;

insert
	into
	SYSTEM_LABELS
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
  select
	nextval('lable_id_sequence'),
	'OIDMPITM',
	'oidmpitm.saveandproceed',
	'Save & Proceed',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIDMPITM'
		and msg_key = 'oidmpitm.saveandproceed');

insert
	into
	SYSTEM_LABELS
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
  select
	nextval('lable_id_sequence'),
	'OUMBUNDL',
	'oumbundl.pageTitle',
	'Maintain Property Bundles',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OUMBUNDL'
		and msg_key = 'oumbundl.pageTitle');

insert
	into
	SYSTEM_LABELS
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
  select
	nextval('lable_id_sequence'),
	'OIUIRAME',
	'oiuirame.sealMark',
	'Seal Mark',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIUIRAME'
		and msg_key = 'oiuirame.sealMark');

insert
	into
	SYSTEM_LABELS
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
  select
	nextval('lable_id_sequence'),
	'OIUIRAME',
	'oiuirame.sealNo',
	'Seal No.',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIUIRAME'
		and msg_key = 'oiuirame.sealNo');

insert
	into
	SYSTEM_LABELS
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
  select
	nextval('lable_id_sequence'),
	'OIUIRAME',
	'oiuirame.save',
	'Save',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIUIRAME'
		and msg_key = 'oiuirame.save');

insert
	into
	SYSTEM_LABELS
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
  select
	nextval('lable_id_sequence'),
	'OIUIRAME',
	'oiuirame.cancel',
	'Cancel',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIUIRAME'
		and msg_key = 'oiuirame.cancel');

insert
	into
	SYSTEM_LABELS
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
select
	nextval('LABLE_ID_SEQUENCE'),
	'OWEACPLN',
	'oweacpln.indicatetofinalize',
	'Indicate To Finalize',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OWEACPLN'
		and msg_key = 'oweacpln.indicatetofinalize');

insert
	into
	SYSTEM_LABELS
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
select
	nextval('LABLE_ID_SEQUENCE'),
	'OCDPAROR',
	'ocdparor.lengthBtn',
	'Length',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDPAROR'
		and msg_key = 'ocdparor.lengthBtn');

insert
	into
	SYSTEM_LABELS
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
select
	nextval('LABLE_ID_SEQUENCE'),
	'OCDEXPOW',
	'ocdexpow.surname',
	'Surname',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDEXPOW'
		and msg_key = 'ocdexpow.surname');

insert
	into
	SYSTEM_LABELS
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
select
	nextval('LABLE_ID_SEQUENCE'),
	'OCDOAPOP',
	'ocdoapop.remove',
	'Remove',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDOAPOP'
		and msg_key = 'ocdoapop.remove');
		
insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OUMPERSO',
	'oumperso.emailmustbeunique',
	'Email id must be unique.',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OUMPERSO'
		and msg_key = 'oumperso.emailmustbeunique');		