insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OCMCONDI',
	'ocmcondi.completelegaltextofcondition',
	'Complete Legal Text Of Condition',
	'LABEL',
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
		module_name = 'OCMCONDI'
		and msg_key = 'ocmcondi.completelegaltextofcondition');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OCMWORKS',
	'ocmworks.defaultcasenotetext',
	'Default Case Note Text',
	'LABEL',
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
		module_name = 'OCMWORKS'
		and msg_key = 'ocmworks.defaultcasenotetext');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OUMDTEMP',
	'oumdtemp.import',
	'Import',
	'LABEL',
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
		module_name = 'OUMDTEMP'
		and msg_key = 'oumdtemp.import');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OUMDTEMP',
	'oumdtemp.view',
	'View',
	'LABEL',
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
		module_name = 'OUMDTEMP'
		and msg_key = 'oumdtemp.view');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OUMDTEMP',
	'oumdtemp.managedoc',
	'Manage Document',
	'LABEL',
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
		module_name = 'OUMDTEMP'
		and msg_key = 'oumdtemp.managedoc');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OMUVREST',
	'omuvrest.exit',
	'Exit',
	'LABEL',
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
		and msg_key = 'omuvrest.exit');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OIDRHLOC',
	'oidrhloc.asn',
	'Asn',
	'LABEL',
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
		module_name = 'OIDRHLOC'
		and msg_key = 'oidrhloc.asn');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OIDBUTAB',
	'oidbutab.fieldc',
	'C',
	'LABEL',
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
		module_name = 'OIDBUTAB'
		and msg_key = 'oidbutab.fieldc');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OCISCATA',
	'ociscata.suite',
	'Suite',
	'LABEL',
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
		module_name = 'OCISCATA'
		and msg_key = 'ociscata.suite');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OCIDIARY',
	'ocidiary.from',
	'From',
	'LABEL',
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
		module_name = 'OCIDIARY'
		and msg_key = 'ocidiary.from');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OCDCSCH',
	'ocdcsch.from',
	'From',
	'LABEL',
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
		module_name = 'OCDCSCH'
		and msg_key = 'ocdcsch.from');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OIDSCMOV',
	'oidscmov.applytoall',
	'Apply To All',
	'LABEL',
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
		module_name = 'OIDSCMOV'
		and msg_key = 'oidscmov.applytoall');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OSANVIOS',
	'osanvios.amendadditionalcountscommenttitle',
	'Amend Additional Counts Comment',
	'LABEL',
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
		module_name = 'OSANVIOS'
		and msg_key = 'osanvios.amendadditionalcountscomment');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OSANVIOS',
	'osanvios.existingadditionalcountscomment',
	'Existing Additional Counts Comment',
	'LABEL',
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
		module_name = 'OSANVIOS'
		and msg_key = 'osanvios.existingadditionalcountscomment');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OSANVIOS',
	'osanvios.amendadditionalcountscommentfield',
	'Amend Additional Counts Comment',
	'LABEL',
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
		module_name = 'OSANVIOS'
		and msg_key = 'osanvios.amendadditionalcountscommentfield');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OSANVIOS',
	'osanvios.additionalcountcommentmandatory',
	'Additional Counts Comment',
	'LABEL',
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
		module_name = 'OSANVIOS'
		and msg_key = 'osanvios.additionalcountcommentmandatory');

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
	'COMMON',
	'common.selectedcharges',
	'Selected Charges',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.selectedcharges');

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
	'COMMON',
	'common.norecordselected',
	'No Record Selected',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.norecordselected');

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
	'COMMON',
	'common.charge',
	'Charge',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.charge');

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
	nextval('lable_id_sequence'),
	'OCUCONDI',
	'ocucondi.conditioncannotduplicate',
	'Condition cannot duplicate',
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
		module_name = 'OCUCONDI'
		and msg_key = 'ocucondi.conditioncannotduplicate');

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
	nextval('lable_id_sequence'),
	'OCUCONDI',
	'ocucondi.categoryalreadyexists',
	'Category already exists',
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
		module_name = 'OCUCONDI'
		and msg_key = 'ocucondi.categoryalreadyexists');

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
	nextval('lable_id_sequence'),
	'OCUCONDI',
	'ocucondi.enddatevalidation',
	'End date  cannot be earlier than start date',
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
		module_name = 'OCUCONDI'
		and msg_key = 'ocucondi.enddatevalidation');

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
	nextval('lable_id_sequence'),
	'OCUCONDI',
	'ocucondi.startdatemustbeenter',
	'Start date must be enter',
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
		module_name = 'OCUCONDI'
		and msg_key = 'ocucondi.startdatemustbeenter');
		
insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'CALSCH',
	'calsch.offendertier',
	'Offender Tier',
	'LABEL',
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
		module_name = 'CALSCH'
		and msg_key = 'calsch.offendertier');


insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'CALSCH',
	'calsch.staffname',
	'Staff Name',
	'LABEL',
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
		module_name = 'CALSCH'
		and msg_key = 'calsch.staffname');


insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'CALSCH',
	'calsch.scheduledeventdetails',
	'Scheduled Event Details',
	'LABEL',
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
		module_name = 'CALSCH'
		and msg_key = 'calsch.scheduledeventdetails');
	
insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OCDCLOSE',
	'ocdclose.warning',
	'Warning',
	'LABEL',
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
		module_name = 'OCDCLOSE'
		and msg_key = 'ocdclose.warning');		