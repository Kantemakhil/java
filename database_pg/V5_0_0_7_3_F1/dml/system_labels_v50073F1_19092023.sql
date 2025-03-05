update
	system_labels
set
	module_name = 'COMMON',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	msg_key in ('osiperso.title', 'osiperso.notes');

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
	'OMUVREST',
	'omuvrest.visitorRestriction',
	'Visitor Restrictions',
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
		module_name = 'OMUVREST'
		and msg_key = 'omuvrest.visitorRestriction');

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
	'EOFFENDER',
	'eoffender.docnamelengthexceed',
	'Document Name can not more than 250 characters',
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
		module_name = 'EOFFENDER'
		and msg_key = 'eoffender.docnamelengthexceed');

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
	'OCDADDRE',
	'ocdaddre.phonenumberduplicate',
	'Multiple rows with same Mobile number are not allowed',
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
		module_name = 'OCDADDRE'
		and msg_key = 'ocdaddre.phonenumberduplicate');

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
	'OCDADDRE',
	'ocdaddre.phonealreadyexist',
	'This phone number has already been entered.',
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
		module_name = 'OCDADDRE'
		and msg_key = 'ocdaddre.phonealreadyexist');

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
	'OCDADDRE',
	'ocdaddre.emailduplicate',
	'Multiple rows with same email address are not allowed',
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
		module_name = 'OCDADDRE'
		and msg_key = 'ocdaddre.emailduplicate');

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
	'OCDADDRE',
	'ocdaddre.emailalreadyexist',
	'This email address has already been entered',
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
		module_name = 'OCDADDRE'
		and msg_key = 'ocdaddre.emailalreadyexist');

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
	'OUMMEROF',
	'oummerof.cantmergetwooffendersactive',
	'The two offenders are both active. The merge cannot proceed.',
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
		MODULE_NAME = 'OUMMEROF'
		and msg_key = 'oummerof.cantmergetwooffendersactive');

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
	'OCDPATTE',
	'ocdpatte.futurecanbesavedwithcancflag',
	'Future appointments can be saved with "Cancelled" status only.',
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
		MODULE_NAME = 'OCDPATTE'
		and msg_key = 'ocdpatte.futurecanbesavedwithcancflag');

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
	'OCDUPROJ',
	'ocduproj.futureappointmentcanbesavewithcancel',
	'Future appointments can be saved with "Cancelled" status only.',
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
		MODULE_NAME = 'OCDUPROJ'
		and msg_key = 'ocduproj.futureappointmentcanbesavewithcancel');

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
	'OCDUATTE',
	'ocduatte.futureappointmentcanbesavewithcancel',
	'Future appointments can be saved with "Cancelled" status only.',
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
		MODULE_NAME = 'OCDUATTE'
		and msg_key = 'ocduatte.futureappointmentcanbesavewithcancel');

update
	system_labels
set
	msg_value = 'Override Reason must be entered when Override Result is selected',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'ocdnoque.overrideReasonValid';

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
	'OIMVDTSL',
	'oimvdtsl.thisvisittimealreadyassignedtotheslotsocannotbemodifiedordeleted',
	'This visit time already assigned to the slot so cannot be modified or deleted',
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
		MODULE_NAME = 'OIMVDTSL'
		and msg_key = 'oimvdtsl.thisvisittimealreadyassignedtotheslotsocannotbemodifiedordeleted');

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
	'OCDIPLAN',
	'ocdiplan.youmustassignacnofficer',
	'You must assign a CN officer before saving this case plan',
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
		MODULE_NAME = 'OCDIPLAN'
		and msg_key = 'ocdiplan.youmustassignacnofficer');

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
	'OSANVIOS',
	'osanvios.counttypemustbeentered',
	'Count type must be entered',
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
		MODULE_NAME = 'OSANVIOS'
		and msg_key = 'osanvios.counttypemustbeentered');
