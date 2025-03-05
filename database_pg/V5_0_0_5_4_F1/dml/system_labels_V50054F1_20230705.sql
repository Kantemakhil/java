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
	'COMMON',
	'common.iwpdocument',
	'Documents',
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
		module_name = 'COMMON'
		and msg_key = 'common.iwpdocument');

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
	'OUMTAGRE',
	'oumtagre.duplicatetableName',
	'Row already exists with Same Table Name',
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
		module_name = 'OUMTAGRE'
		and msg_key = 'oumtagre.duplicatetableName');

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
	'OUMRCODE',
	'oumrcode.rowalreadyexists',
	'Row exists already with same code',
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
		MODULE_NAME = 'OUMRCODE'
		and msg_key = 'oumrcode.rowalreadyexists');

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
	'OIDVISIT',
	'oidvisit.visitcannotbecreatedforinactiveoffender',
	'Visits cannot be created for inactive offenders',
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
		module_name = 'OIDVISIT'
		and msg_key = 'oidvisit.visitcannotbecreatedforinactiveoffender');

insert
	into
	OMS_OWNER.SYSTEM_LABELS(
 LABEL_ID,
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
	'OSANVIOS',
	'osanvios.textExceedsAllowedLimitOfCharacters',
	'Amend Additional Counts Comment exceeds allowed limit of characters.',
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
		MSG_KEY = 'osanvios.textExceedsAllowedLimitOfCharacters'
		and MSG_VALUE = 'Amend Additional Counts Comment exceeds allowed limit of characters.'
);

insert
	into
	OMS_OWNER.SYSTEM_LABELS(
 LABEL_ID,
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
	'oweacpln.textExceedsAllowedLimitOfCharacters',
	'Append WAP Comment exceeds allowed limit of characters.',
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
		MSG_KEY = 'oweacpln.textExceedsAllowedLimitOfCharacters'
		and MSG_VALUE = 'Append WAP Comment exceeds allowed limit of characters.'
);

insert
	into
	OMS_OWNER.SYSTEM_LABELS(
 LABEL_ID,
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
	'OIDOFFOB',
	'oidoffob.textExceedsAllowedLimitOfCharacters',
	'Append Offender Observation Comment exceeds allowed limit of characters.',
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
		MSG_KEY = 'oidoffob.textExceedsAllowedLimitOfCharacters'
		and MSG_VALUE = 'Append Offender Observation Comment exceeds allowed limit of characters.'
);