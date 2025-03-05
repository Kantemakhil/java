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
	'OIMSATYP',
	'oimsatyp.childsexists',
	'You cannot delete this record child record exists .',
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
		module_name = 'OIMSATYP'
		and msg_key = 'oimsatyp.childsexists');

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
	'OIISCHED',
	'oiisched.canceled',
	'Canceled?',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OIISCHED'
		and msg_key = 'oiisched.canceled');

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
	'OCUBADJS',
	'oiisched.canceledreason',
	'Canceled Reason',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OIISCHED'
		and msg_key = 'oiisched.canceledreason');

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
	'OIUSELVE',
	'oiuselve.optcap',
	'Opt.Cap',
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
		MODULE_NAME = 'OIUSELVE'
		and msg_key = 'oiuselve.optcap');

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
	'OIUSELVE',
	'oiuselve.physopt',
	'Phys.Opt',
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
		MODULE_NAME = 'OIUSELVE'
		and msg_key = 'oiuselve.physopt');

update
	system_labels
set
	msg_value = 'You Are Trying to Insert/Update  a Duplicate  LegID and SeqId',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'oimroute.duplegandseq'
	and module_name = 'OIMROUTE';

update
	system_labels
set
	msg_value = 'Error: Combination of Offender Id, Limit Type and Period Type are not unique.',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'otmcslim.errorcombination';

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
	'OCUPDETA',
	'ocupdeta.savediscardchanges',
	'Do you wish to save the changes you have made?',
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
		module_name = 'OCUPDETA'
		and msg_key = 'ocupdeta.savediscardchanges');

update
	system_labels
set
	msg_value = 'This masking format is in use and cannot be changed',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OUMSYSET'
	and msg_key = 'oumsyset.numberinuse';

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
	'OCDLEGLO',
	'ocdleglo.document',
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
		module_name = 'OCDLEGLO'
		and msg_key = 'ocdleglo.document');