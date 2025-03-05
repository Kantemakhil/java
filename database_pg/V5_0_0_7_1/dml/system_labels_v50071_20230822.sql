update
	SYSTEM_LABELS
set
	MSG_VALUE = 'Reason' ,
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	module_name = 'OTDGLIRT'
	and msg_key = 'otdglirt.reason';

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
	'OCDPAYOB',
	'ocdpayob.amountmustbeentered',
	'Amount must be entered',
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
		MODULE_NAME = 'OCDPAYOB'
		and msg_key = 'ocdpayob.amountmustbeentered');

update
	system_profiles
set
	profile_value = 'N',
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	profile_type = 'CLIENT'
	and profile_code = 'CF_DEDUCTION';

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
	'OIMIEPLV',
	'oimieplv.cannotdeletedefaultiep',
	'Cannot delete default intake iep level',
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
		MODULE_NAME = 'OIMIEPLV'
		and msg_key = 'oimieplv.cannotdeletedefaultiep');

update
	SYSTEM_LABELS
set
	msg_value = 'Time Taken',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OIDSTEST'
	and
		MSG_KEY = 'oidstest.timetaken';

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
'OCDCORDS',
'termToLine.weekwholenumbers',
'Week(s) can only be entered in whole numbers',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
	'OUMDTEMP',
	'oumdtemp.screendeletevalidation',
	'Row Can Not be deleted Because Documents created with Selected Module',
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
		module_name = 'OUMDTEMP'
		and msg_key = 'oumdtemp.screendeletevalidation');

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
	'OUMDTEMP',
	'oumdtemp.screendatavalidation',
	'Cannot delete Template if Screen Access records exist',
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
		module_name = 'OUMDTEMP'
		and msg_key = 'oumdtemp.screendatavalidation');

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
	'OUMDTEMP',
	'oumdtemp.roledataalidation',
	'Cannot delete Template if User Group Access records exist',
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
		module_name = 'OUMDTEMP'
		and msg_key = 'oumdtemp.roledataalidation');

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
	'OUMUSERS',
	'oumusers.reset',
	'Reset Password',
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
		module_name = 'OUMUSERS'
		and msg_key = 'oumusers.reset');

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
	'OIDPACTI',
	'oidpacti.startdatemustbecurrentdate',
	'Assignment start date must be either current date or future date',
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
		and msg_key = 'oidpacti.startdatemustbecurrentdate');

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
	'OSIOSEAR',
	'osiosear.secondstatus',
	'Status',
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
		MODULE_NAME = 'OSIOSEAR'
		and msg_key = 'osiosear.secondstatus');

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
	'OSIOSEAR',
	'osiosear.agency',
	'Agency',
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
		MODULE_NAME = 'OSIOSEAR'
		and msg_key = 'osiosear.agency');

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
	'OSIOSEAR',
	'osiosear.facility',
	'Facility',
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
		MODULE_NAME = 'OSIOSEAR'
		and msg_key = 'osiosear.facility');

update
	OMS_OWNER.SYSTEM_LABELS
set
	MSG_VALUE = 'Preferred Contact Method',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
	where
MSG_KEY = 'oivctmng.preferredcontactmethod'
	and MODULE_NAME = 'OIVCTMNG';

update
	OMS_OWNER.SYSTEM_LABELS
set
	MSG_VALUE = 'Preferred Contact Method',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
	where
MSG_KEY = 'oivctmng.preferredcontactmethod'
	and MODULE_NAME = 'OIVCTMNG';

update
	OMS_OWNER.SYSTEM_LABELS
set
	MSG_VALUE = 'Legal Summary',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
	where
MSG_KEY = 'oivctmng.legalsummery'
	and MODULE_NAME = 'OIVCTMNG';

insert
	into
	OMS_OWNER.SYSTEM_LABELS (LABEL_ID,
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
	'OCUVERIF',
	'ocuverif.close',
	'Close',
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
		MODULE_NAME = 'OCUVERIF'
		and msg_key = 'ocuverif.close');

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
	'OIDONONA',
	'oidonona.duplicateoffender',
	'Offender shpuld not be duplicate for the non-assocation',
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
		MODULE_NAME = 'OIDONONA'
		and msg_key = 'oidonona.duplicateoffender');

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
	'OCUREMIN',
	'ocuremin.hoursbefore',
	'Hour(s) Before',
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
		MODULE_NAME = 'OCUREMIN'
		and msg_key = 'ocuremin.hoursbefore');

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
	'OIMIEPLV',
	'oimieplv.cannotgiveintakeforinactiveiep',
	'We cannot take inactive IEP as intake IEP',
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
		MODULE_NAME = 'OIMIEPLV'
		and msg_key = 'oimieplv.cannotgiveintakeforinactiveiep');

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
	'OIMIEPLV',
	'oimieplv.deactivatingintakeiep',
	'Deactivating this record will uncheck the Intake IEP checkbox. Do you want to proceed?',
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
		MODULE_NAME = 'OIMIEPLV'
		and msg_key = 'oimieplv.deactivatingintakeiep');

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
	'OCDNOQUE',
	'ocdnoque.facility',
	'Facility',
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
		MODULE_NAME = 'OCDNOQUE'
		and MSG_KEY = 'ocdnoque.facility');

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
	'OIDPWAIT',
	'oidpwait.facility',
	'Facility',
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
		MODULE_NAME = 'OIDPWAIT'
		and MSG_KEY = 'oidpwait.facility');

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
	'OUMMENUS',
	'oummenus.pleasedeletethechildrecordsforthismenuitembeforedeletingthisrecord',
	'Please delete the child records for this menu item before deleting this record',
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
		MODULE_NAME = 'OUMMENUS'
		and msg_key = 'oummenus.pleasedeletethechildrecordsforthismenuitembeforedeletingthisrecord');

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
	'OCMSUWPJ',
	'ocmsuwpj.noactiverecordswithacategoryofcommunityservice',
	'No active record with a Category of Community Service found on Maintain Services (OCMSERVI)',
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
		MODULE_NAME = 'OCMSUWPJ'
		and msg_key = 'ocmsuwpj.noactiverecordswithacategoryofcommunityservice');

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
	'OCMSERVI',
	'ocmservi.atatimeonlyonecommunityserviceshouldbeactive',
	'At a time Only One Community Service should be active',
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
		MODULE_NAME = 'OCMSERVI'
		and msg_key = 'ocmservi.atatimeonlyonecommunityserviceshouldbeactive');

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
	'OUISYSST',
	'ouisysst.systemStatus',
	'System Status',
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
		MODULE_NAME = 'OUISYSST'
		and msg_key = 'ouisysst.systemStatus');
		

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
	'OUISYSST',
	'ouisysst.service',
	'Service',
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
		MODULE_NAME = 'OUISYSST'
		and msg_key = 'ouisysst.service');
		
	
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
	'OUISYSST',
	'ouisysst.status',
	'Status',
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
		MODULE_NAME = 'OUISYSST'
		and msg_key = 'ouisysst.status');
		
	
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
	'OUISYSST',
	'ouisysst.lastStatusChange',
	'Last Status Change',
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
		MODULE_NAME = 'OUISYSST'
		and msg_key = 'ouisysst.lastStatusChange');
		
	
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
	'OUISYSST',
	'ouisysst.exit',
	'Exit',
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
		MODULE_NAME = 'OUISYSST'
		and msg_key = 'ouisysst.exit');
		
	
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
	'OUISYSST',
	'ouisysst.systemHealth',
	'System Health',
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
		MODULE_NAME = 'OUISYSST'
		and msg_key = 'ouisysst.systemHealth');	
		
	
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
	'OCDLEGLS',
	'ocdlegls.sentenseEngineWarningMsg',
	'This offender has pending sentence calculation events. Their key dates may not be accurate',
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
		MODULE_NAME = 'OCDLEGLS'
		and msg_key = 'ocdlegls.sentenseEngineWarningMsg');	
		
insert
	into
	OMS_OWNER.SYSTEM_LABELS(LABEL_ID,
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
	'OSIPSEARIDIALOG',
	'osipsearidialog.lastname',
	'Last Name',
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
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.lastname');

insert
	into
	OMS_OWNER.SYSTEM_LABELS(LABEL_ID,
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
	'OSIPSEARIDIALOG',
	'osipsearidialog.firstname',
	'First Name',
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
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.firstname');

insert
	into
	OMS_OWNER.SYSTEM_LABELS(LABEL_ID,
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
	'OSIPSEARIDIALOG',
	'osipsearidialog.middlename',
	'Middle Name',
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
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.middlename');

insert
	into
	OMS_OWNER.SYSTEM_LABELS(LABEL_ID,
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
	'OSIPSEARIDIALOG',
	'osipsearidialog.secondmiddlename',
	'Second Middle Name',
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
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.secondmiddlename');

insert
	into
	OMS_OWNER.SYSTEM_LABELS(LABEL_ID,
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
	'OSIPSEARIDIALOG',
	'osipsearidialog.nametype',
	'Name Type',
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
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.nametype');

insert
	into
	OMS_OWNER.SYSTEM_LABELS(LABEL_ID,
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
	'OSIPSEARIDIALOG',
	'osipsearidialog.personadditionalnames',
	'Person Additional Names',
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
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.personadditionalnames');

insert
	into
	OMS_OWNER.SYSTEM_LABELS(LABEL_ID,
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
	'OSIPERSO',
	'osiperso.pin',
	'PIN',
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
		MODULE_NAME = 'OSIPERSO'
		and msg_key = 'osiperso.pin');

insert
	into
	OMS_OWNER.SYSTEM_LABELS(LABEL_ID,
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
	'OSIPERSO',
	'osiperso.additionalnames',
	'Additional Names',
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
		MODULE_NAME = 'OSIPERSO'
		and msg_key = 'osiperso.additionalnames');

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
	'JISCOMMONCONFIRMBOX',
	'jiscommonconfirmbox.searchinprogresspleasewait',
	'Search In Progress Please Wait...',
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
		MODULE_NAME = 'JISCOMMONCONFIRMBOX'
		and msg_key = 'jiscommonconfirmbox.searchinprogresspleasewait');

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
	'JISCOMMONCONFIRMBOX',
	'jiscommonconfirmbox.awaitingresponsefromjiscommon',
	'Awaiting response from JIS Common...',
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
		MODULE_NAME = 'JISCOMMONCONFIRMBOX'
		and msg_key = 'jiscommonconfirmbox.awaitingresponsefromjiscommon');

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
	'JISCOMMONCONFIRMBOX',
	'jiscommonconfirmbox.unabletoretrieveresults',
	'Unable to Retrieve Results',
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
		MODULE_NAME = 'JISCOMMONCONFIRMBOX'
		and msg_key = 'jiscommonconfirmbox.unabletoretrieveresults');

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
	'JISCOMMONCONFIRMBOX',
	'jiscommonconfirmbox.eithertheconnectiontojiscommonhasbeenlost',
	'Either the connection to JIS Common has been lost or',
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
		MODULE_NAME = 'JISCOMMONCONFIRMBOX'
		and msg_key = 'jiscommonconfirmbox.eithertheconnectiontojiscommonhasbeenlost');

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
	'JISCOMMONCONFIRMBOX',
	'jiscommonconfirmbox.isunavailabeatthistimepleasetryagain',
	'is unavailabe at this time .Please try again.',
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
		MODULE_NAME = 'JISCOMMONCONFIRMBOX'
		and msg_key = 'jiscommonconfirmbox.isunavailabeatthistimepleasetryagain');

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
	'OSIPSEAR',
	'osipsear.pleasecreateanelitepersonrecordtoregisterthisperson',
	'Please create an Elite Person Record to register this person',
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
		MODULE_NAME = 'OSIPSEAR'
		and msg_key = 'osipsear.pleasecreateanelitepersonrecordtoregisterthisperson');

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
	'OSIOSEAR',
	'osiosear.pin',
	'PIN',
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
		MODULE_NAME = 'OSIOSEAR'
		and msg_key = 'osiosear.pin');

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
	'OSIOSEAR',
	'osiosear.pleaseCreateaneliteoffenderrecordtoadmitthisoffender',
	'Please create an Elite Offender Record to admit this offender',
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
		MODULE_NAME = 'OSIOSEAR'
		and msg_key = 'osiosear.pleaseCreateaneliteoffenderrecordtoadmitthisoffender');		
		
		
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
	'OIDADMIS',
	'oidadmis.defaultloccapacityfull',
	'The defalut location capacity is full so,please select another location',
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
		MODULE_NAME = 'OIDADMIS'
		and msg_key = 'oidadmis.defaultloccapacityfull');		

update
	SYSTEM_LABELS
set
	MSG_VALUE = 'Schedule reminder hours should be less than event start time' ,
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	module_name = 'OCUREMIN'
	and msg_key = 'ocuremin.pleaseenterlesshours' ;

update
	SYSTEM_LABELS
set
	MSG_VALUE = 'Please enter When (no of hours before) for SMS reminder' ,
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	module_name = 'OCUREMIN'
	and msg_key = 'ocuremin.smshoursmustbeenter' ;

update
	SYSTEM_LABELS
set
	MSG_VALUE = 'Please enter When (no of hours before) for Email reminder' ,
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	module_name = 'OCUREMIN'
	and msg_key = 'ocuremin.emailhoursmustbeenter' ;
	
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
	'OCIPENSC',
	'ocipensc.lastname',
	'Last Name',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.lastname');

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
	'OCIPENSC',
	'ocipensc.firstname',
	'First Name',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.firstname');

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
	'OCIPENSC',
	'ocipensc.eventdate',
	'Event Date',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.eventdate');

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
	'OCIPENSC',
	'ocipensc.eventtime',
	'Time',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.eventtime');

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
	'OCIPENSC',
	'ocipensc.staffname',
	'Staff Name',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.staffname');

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
	'OCIPENSC',
	'ocipensc.calcreason',
	'Calculation Reason',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.calcreason');

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
	'OCIPENSC',
	'ocipensc.modulename',
	'Screen',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.modulename');

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
	'OCIPENSC',
	'ocipensc.offenders',
	'Offenders',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.offenders');

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
	'OCIPENSC',
	'ocipensc.refersh',
	'Refresh Data',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.refersh');

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
	'OCIPENSC',
	'ocipensc.pendingevents',
	'Pending Events',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.pendingevents');

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
	'OCIPENSC',
	'ocipensc.comment',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.comment');

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
	'OCIPENSC',
	'ocipensc.pendingsentencecalcevents',
	'Pending Sentence Calculation Events',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.pendingsentencecalcevents');

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
	'OCIPENSC',
	'ocipensc.pid',
	'Offender ID Display',
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
		MODULE_NAME = 'OCIPENSC'
		and msg_key = 'ocipensc.pid');

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
	'OCUCALCR',
	'ocucalcr.userhaspendingcalcevents',
	'User has pending calculation. Do you want to continue?',
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
		MODULE_NAME = 'OCUCALCR'
		and msg_key = 'ocucalcr.userhaspendingcalcevents');

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
	'OCUCALCR',
	'ocucalcr.applicationstatusdown',
	'Sentence Engine/ Automation Engine is down. Key dates will not be calculated. Do you want to continue?',
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
		MODULE_NAME = 'OCUCALCR'
		and msg_key = 'ocucalcr.applicationstatusdown');

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
	'OCUCALCR',
	'ocucalcr.warnok',
	'Ok',
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
		MODULE_NAME = 'OCUCALCR'
		and msg_key = 'ocucalcr.warnok');

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
	'OCDNCODE',
	'ocdncode.savedaspendingevent',
	'Record saved but sentence calculation could not be performed because of engine was down.',
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
		MODULE_NAME = 'OCDNCODE'
		and msg_key = 'ocdncode.savedaspendingevent');

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
	'OCDLEGLO',
	'ocdleglo.savedaspendingevent',
	'Record saved but sentence calculation could not be performed because of engine was down.',
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
		MODULE_NAME = 'OCDLEGLO'
		and msg_key = 'ocdleglo.savedaspendingevent');

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
	'OCDPAROR',
	'ocdparor.savedaspendingevent',
	'Record saved but sentence calculation could not be performed because of engine was down.',
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
		MODULE_NAME = 'OCDPAROR'
		and msg_key = 'ocdparor.savedaspendingevent');

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
	'OCDLEGLS',
	'ocdlegls.savedaspendingevent',
	'Record saved but sentence calculation could not be performed because of engine was down.',
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
		MODULE_NAME = 'OCDLEGLS'
		and msg_key = 'ocdlegls.savedaspendingevent');	