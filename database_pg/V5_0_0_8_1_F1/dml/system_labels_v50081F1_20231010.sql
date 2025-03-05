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
select
	nextval('LABLE_ID_SEQUENCE'),
	'LOGIN',
	'login.deploymentcachemsg',
	'Code deployed, Please open new tab',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'LOGIN'
		and msg_key = 'login.deploymentcachemsg');

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
select
	nextval('LABLE_ID_SEQUENCE'),
	'OIUIMAGE',
	'oiuimage.largeimage',
	'Large Image not allowed',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OIUIMAGE'
		and msg_key = 'oiuimage.largeimage');

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
select
	nextval('LABLE_ID_SEQUENCE'),
	'OIDRELSC',
	'oidrelsc.confirmedreleasedateisverified',
	'The Confirmed Release Date has been verified. Are you sure you want to proceed?',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDRELSC'
		and msg_key = 'oidrelsc.confirmedreleasedateisverified');

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
select
	nextval('LABLE_ID_SEQUENCE'),
	'OIDSIAPP',
	'oidsiapp.cancelreasonmustbeentered',
	'Cancel reason must be entered',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDSIAPP'
		and msg_key = 'oidsiapp.cancelreasonmustbeentered');
		
		
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
select
	nextval('LABLE_ID_SEQUENCE'),
	'OCDCLOGS',
	'ocdclogs.pastevent',
	'Reminders cannot be open for past Events',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDCLOGS'
		and msg_key = 'ocdclogs.pastevent');		
