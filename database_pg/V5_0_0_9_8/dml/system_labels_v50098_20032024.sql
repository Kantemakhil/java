update
	system_labels
set
	msg_value = 'Lost/Stolen',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	msg_key = 'oidmpitm.missing'
	and module_name = 'OIDMPITM';

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
	'OIMWORKR',
	'oimworkr.mainworkrels',
	'Maintain Work Release',
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
		module_name = 'OIMWORKR'
		and msg_key = 'oimworkr.mainworkrels');

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
	'OIMWORKR',
	'oimworkr.workrelsprojects',
	'Work Release Projects',
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
		module_name = 'OIMWORKR'
		and msg_key = 'oimworkr.workrelsprojects');

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
	'OCDENFOR',
	'ocdenfor.iwpdocument',
	'Documents',
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
		system_labels
	where
		module_name = 'OCDENFOR'
		and msg_key = 'ocdenfor.iwpdocument');

update
	system_labels
set
	msg_value = 'Append',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	module_name = 'OIDSTFRP'
	and msg_key = 'oidstfrp.appendreport';

update
	system_labels
set
	msg_value = 'Append Report',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	module_name = 'OIUIRAME'
	and msg_key = 'oiuirame.amendreport';

update
	system_labels
set
	msg_value = 'Append',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	module_name = 'OIUIRAME'
	and msg_key = 'oiuirame.amend';

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
	'OCUACOND',
	'ocuacond.programRefTitle',
	'Program Referral',
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
		module_name = 'OCUACOND'
		and msg_key = 'ocuacond.programRefTitle');

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
	'OCUACOND',
	'ocuacond.code',
	'Code',
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
		module_name = 'OCUACOND'
		and msg_key = 'ocuacond.code');

insert
	into
	system_labels
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
	'OIDPACTI',
	'oidpacti.offstartdateearlierthanadmissiondate',
	'offender start date cannot be earlier than admission date',
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
		module_name = 'OIDPACTI'
		and msg_key = 'oidpacti.offstartdateearlierthanadmissiondate');

insert
	into
	system_labels
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
	'OIDPACTI',
	'oidpacti.offstartdateearlierthanprogramschstartdate',
	'Offender start date cannot be earlier than Program Schedule start date',
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
		module_name = 'OIDPACTI'
		and msg_key = 'oidpacti.offstartdateearlierthanprogramschstartdate');

insert
	into
	system_labels (LABEL_ID,
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
	'OIMWORKR',
	'oimworkr.projectschedule',
	'Project Schedule/Availability',
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
		system_labels
	where
		MODULE_NAME = 'OIMWORKR'
		and msg_key = 'oimworkr.projectschedule');

insert
	into
	system_labels (LABEL_ID,
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
	'OIDINTMV',
	'oidintmv.movementdate',
	'Movement Date',
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
		system_labels
	where
		MODULE_NAME = 'OIDINTMV'
		and msg_key = 'oidintmv.movementdate');

insert
	into
	system_labels (LABEL_ID,
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
	'OIDINTMV',
	'oidintmv.movementtime',
	'Movement Time',
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
		system_labels
	where
		MODULE_NAME = 'OIDINTMV'
		and msg_key = 'oidintmv.movementtime');

insert
	into
	system_labels (LABEL_ID,
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
	'OIDINTMV',
	'oidintmv.movementtype',
	'Movement Type',
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
		system_labels
	where
		MODULE_NAME = 'OIDINTMV'
		and msg_key = 'oidintmv.movementtype');

insert
	into
	system_labels (LABEL_ID,
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
	'OIDINTMV',
	'oidintmv.applytoall',
	'Apply To All',
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
		system_labels
	where
		MODULE_NAME = 'OIDINTMV'
		and msg_key = 'oidintmv.applytoall');

insert
	into
	system_labels (LABEL_ID,
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
	'OIDINTMV',
	'oidintmv.id',
	'PID #',
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
		system_labels
	where
		MODULE_NAME = 'OIDINTMV'
		and msg_key = 'oidintmv.id');

insert
	into
	system_labels (LABEL_ID,
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
	'OIDINTMV',
	'oidintmv.schedulereason',
	'Schedule Reason',
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
		system_labels
	where
		MODULE_NAME = 'OIDINTMV'
		and msg_key = 'oidintmv.schedulereason');

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
	'OIMSREQS',
	'oimsreqs.sanctionsflag',
	'Sanctions Flag',
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
		module_name = 'OIMSREQS'
		and msg_key = 'oimsreqs.sanctionsflag');

insert
	into
	system_labels
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
	'common.offenderid',
	'offenderid',
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
		system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.offenderid');

insert
	into
	system_labels
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
	'common.dateofbirth',
	'dateofbirth',
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
		system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.dateofbirth');

insert
	into
	system_labels
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
	'common.bookingid',
	'bookingid',
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
		system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.bookingid');

insert
	into
	system_labels
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
	'common.dob',
	'dob',
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
		system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.dob');

insert
	into
	system_labels
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
	'common.bookingno',
	'bookingno',
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
		system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.bookingno');

insert
	into
	system_labels
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
	'common.bookingdate',
	'bookingdate',
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
		system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.bookingdate');

insert
	into
	system_labels
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
	'common.alerts',
	'alerts',
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
		system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.alerts');

insert
	into
	system_labels
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
	'common.outsidecurrentcaseload',
	'outsidecurrentcaseload',
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
		system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.outsidecurrentcaseload');

insert
	into
	system_labels
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
	'common.saveandcalculate',
	'save and calculate',
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
		system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.saveandcalculate');

insert
	into
	system_labels
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
	'common.documents',
	'Documents',
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
		system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.documents');

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
	NEXTVAL('LABLE_ID_SEQUENCE'),
	'OIMVLIMT',
	'oimvlimt.visitlimitconfigurationforsec',
	'Visit Limit configuration for a Security / IEP level must match configuration for visit types',
	'LABEL',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIMVLIMT'
		and msg_key = 'oimvlimt.visitlimitconfigurationforsec');

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
	'OIDRELSC',
	'oidrelsc.keydate',
	'Key Dates',
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
		module_name = 'OIDRELSC'
		and msg_key = 'oidrelsc.keydate');

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
	'OIDRELSC',
	'oidrelsc.chargeindicator',
	'Charge Indicator',
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
		module_name = 'OIDRELSC'
		and msg_key = 'oidrelsc.chargeindicator');

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
	'OIDRELSC',
	'oidrelsc.alerttype',
	'Alert Type',
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
		module_name = 'OIDRELSC'
		and msg_key = 'oidrelsc.alerttype');

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
	'OIDRELSC',
	'oidrelsc.alert',
	'Alert',
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
		module_name = 'OIDRELSC'
		and msg_key = 'oidrelsc.alert');

insert
	into
	system_labels
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
	'OIDSMSET',
	'oidsmset.panetitle',
	'Schedules and Movements Settings',
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
		system_labels
	where
		MODULE_NAME = 'OIDSMSET'
		and msg_key = 'oidsmset.panetitle');

insert
	into
	system_labels
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
	'OIDSMSET',
	'oidsmset.tapstatus',
	'TAP Statuses That Initiate Conflict Check',
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
		system_labels
	where
		MODULE_NAME = 'OIDSMSET'
		and msg_key = 'oidsmset.tapstatus');

insert
	into
	system_labels
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
	'OIDALLOW',
	'oidallow.enddateCannotberemovedonceitissaved',
	'End Date Cannot be removed once it is saved',
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
		system_labels
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.enddateCannotberemovedonceitissaved');

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
	'EOFFENDER',
	'eoffender.template',
	'Template',
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
		module_name = 'EOFFENDER'
		and msg_key = 'eoffender.template');

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
	'EOFFENDER',
	'eoffender.generate',
	'Generate',
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
		module_name = 'EOFFENDER'
		and msg_key = 'eoffender.generate');

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
	'EOFFENDER',
	'eoffender.upload',
	'Upload',
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
		module_name = 'EOFFENDER'
		and msg_key = 'eoffender.upload');

insert
	into
	system_labels
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
	'OIMPROST',
	'oimprost.manageproperty',
	'Manage Property',
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
		system_labels
	where
		MODULE_NAME = 'OIMPROST'
		and msg_key = 'oimprost.manageproperty');

insert
	into
	system_labels
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
	'OIMPROST',
	'oimprost.keymustbeentered',
	'Key Desc must be entered.',
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
		system_labels
	where
		MODULE_NAME = 'OIMPROST'
		and msg_key = 'oimprost.keymustbeentered');

insert
	into
	system_labels
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
	'OIMPROST',
	'oimprost.valuemustbeentered',
	'Value must be entered.',
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
		system_labels
	where
		MODULE_NAME = 'OIMPROST'
		and msg_key = 'oimprost.valuemustbeentered');

insert
	into
	system_labels
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
	'OIMPROST',
	'oimprost.recordUpdatedSuccessfully',
	'Record Updated Successfully',
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
		system_labels
	where
		MODULE_NAME = 'OIMPROST'
		and msg_key = 'oimprost.recordUpdatedSuccessfully');

insert
	into
	system_labels
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
	'OIMPROST',
	'oimprost.recordNotUpdated',
	'Record Not Updated',
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
		system_labels
	where
		MODULE_NAME = 'OIMPROST'
		and msg_key = 'oimprost.recordNotUpdated');

insert
	into
	system_labels
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
	'OIMPROST',
	'oimprost.propertysettings',
	'Property Settings',
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
		system_labels
	where
		MODULE_NAME = 'OIMPROST'
		and msg_key = 'oimprost.propertysettings');
