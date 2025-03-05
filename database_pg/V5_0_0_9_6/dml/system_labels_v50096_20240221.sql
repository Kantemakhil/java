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
	'OIDOICUS',
	'oidoicus.weaponsused',
	'Weapons Used',
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
		module_name = 'OIDOICUS'
		and msg_key = 'oidoicus.weaponsused');

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
	'OCONDTRF',
	'ocondtrf.applytoall',
	'Apply To All',
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
		module_name = 'OCONDTRF'
		and msg_key = 'ocondtrf.applytoall');

insert
	into
	SYSTEM_LABELS (LABEL_ID,
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
	'OCDEXPOW',
	'ocdexpow.assstaffname',
	'Assigned Staff Name',
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
		module_name = 'OCDEXPOW'
		and msg_key = 'ocdexpow.assstaffname');

insert
	into
	SYSTEM_LABELS (LABEL_ID,
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
	'OCDALERT',
	'ocdalert.caseloadtype',
	'Caseload Type',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OCDALERT'
		and msg_key = 'ocdalert.caseloadtype');

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
	'COMMON',
	'common.emailmustbeunique',
	'This email address is already in use',
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
		and msg_key = 'common.emailmustbeunique');

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
	'oweacpln.thisactionwillcreateanewversionoftheWeeklyactivityplanforthisweek',
	'This action will create a new version of the Weekly Activity Plan for this week. Are you sure you want to proceed?',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OWEACPLN'
		and msg_key = 'oweacpln.thisactionwillcreateanewversionoftheWeeklyactivityplanforthisweek');

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
	'OCDEDEMP',
	'ocdedemp.addupdateaddress',
	'Add/Update Addresses',
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
		module_name = 'OCDEDEMP'
		and msg_key = 'ocdedemp.addupdateaddress');

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
	'OCDINTAK',
	'ocdintak.existingbookings',
	'Existing Bookings',
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
		module_name = 'OCDINTAK'
		and msg_key = 'ocdintak.existingbookings');

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
	'OCDINTAK',
	'ocdintak.createnewbooking',
	'Create New Booking?',
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
		module_name = 'OCDINTAK'
		and msg_key = 'ocdintak.createnewbooking');

insert
	into
	SYSTEM_LABELS (LABEL_ID,
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
	'OCMLESET',
	'ocmleset.displayerd',
	'Display Earliest Release Date(ERD)',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCMLESET'
		and msg_key = 'ocmleset.displayerd');

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
	'ocdparor.thisorderhasactiveaffectedorders',
	'This parole order cannot be reactivated as the linked custodial order is linked to another active parole order. ',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDPAROR'
		and msg_key = 'ocdparor.thisorderhasactiveaffectedorders');

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
	'OIMALLOW',
	'oimallow.maintainallowances',
	'Maintain Allowances',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.maintainallowances');

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
	'OIMALLOW',
	'oimallow.allowancetype',
	'Allowance Type',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.allowancetype');

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
	'OIMALLOW',
	'oimallow.unit',
	'Unit',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.unit');

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
	'OIMALLOW',
	'oimallow.maxunit',
	'Max Unit',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.maxunit');

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
	'OIMALLOW',
	'oimallow.rate',
	'Rate($)',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.rate');

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
	'OIMALLOW',
	'oimallow.sunflag',
	'Sunday',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.sunflag');

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
	'OIMALLOW',
	'oimallow.monflag',
	'Monday',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.monflag');

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
	'OIMALLOW',
	'oimallow.tueflag',
	'Tuesday',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.tueflag');

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
	'OIMALLOW',
	'oimallow.wedflag',
	'Wednesday',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.wedflag');

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
	'OIMALLOW',
	'oimallow.thuflag',
	'Thursday',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.thuflag');

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
	'OIMALLOW',
	'oimallow.friflag',
	'Friday',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.friflag');

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
	'OIMALLOW',
	'oimallow.satflag',
	'Saturday',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.satflag');

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
	'OIMALLOW',
	'oimallow.active',
	'Active',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.active');

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
	'OIMALLOW',
	'oimallow.expirydate',
	'Expiry Date',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.expirydate');

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
	'OIMALLOW',
	'oimallow.allowancetypeshouldnotduplicate',
	'Allowance type should not be duplicate',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.allowancetypeshouldnotduplicate');

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
	'OIMALLOW',
	'oimallow.allowancetypemustbeenter',
	'Allowance type must be enter',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.allowancetypemustbeenter');

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
	'OIMALLOW',
	'oimallow.unitmustbeenter',
	'Unit must be enter',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.unitmustbeenter');

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
	'OIMALLOW',
	'oimallow.maxUnitmustbeenter',
	'Max Unit must be enter',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.maxUnitmustbeenter');

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
	'OIMALLOW',
	'oimallow.ratemustbeenter',
	'Rate must be enter',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMALLOW'
		and msg_key = 'oimallow.ratemustbeenter');

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
	'OIDALLOW',
	'oidallow.offenderallowences',
	'Offender Allowences',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.offenderallowences');

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
	'OIDALLOW',
	'oidallow.allowencetype',
	'Allowence Type',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.allowencetype');

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
	'OIDALLOW',
	'oidallow.unit',
	'Unit',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.unit');

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
	'OIDALLOW',
	'oidallow.rate',
	'Rate',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.rate');

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
	'OIDALLOW',
	'oidallow.startdate',
	'Start Date',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.startdate');

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
	'OIDALLOW',
	'oidallow.enddate',
	'End Date',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.enddate');

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
	'OIDALLOW',
	'oidallow.comment',
	'Comment',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.comment');

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
	'OIDALLOW',
	'oidallow.offenderallowancestartdatecannotbebeforetheadmissiondate',
	'Offender allowance start date cannot be before the admission date.',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.offenderallowancestartdatecannotbebeforetheadmissiondate');

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
	'OIDALLOW',
	'oidallow.offenderallowanceenddatecannotbebeforethecurrentdate',
	'Offender allowance End Date cannot be before the Current Date',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.offenderallowanceenddatecannotbebeforethecurrentdate');

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
	'OIDALLOW',
	'oidallow.offenderallowanceenddatecannotbebeforethestartdate',
	'Offender allowance End Date  cannot be before the start date',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.offenderallowanceenddatecannotbebeforethestartdate');

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
	'OIDALLOW',
	'oidallow.startdateoverlapsapreviousallowance',
	'Start date overlaps a previous allowance.',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.startdateoverlapsapreviousallowance');

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
	'OIDALLOW',
	'oidallow.anallowanceisstillactivefortheoffender',
	'An allowance is still active for the offender.',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.anallowanceisstillactivefortheoffender');