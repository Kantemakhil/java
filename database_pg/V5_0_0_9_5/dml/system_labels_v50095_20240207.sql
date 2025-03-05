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
	'OSIPSEAR',
	'osipsear.additionalnames',
	'Additional Names',
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
		module_name = 'OSIPSEAR'
		and msg_key = 'osipsear.additionalnames');

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
	'OIIPTRAN',
	'oiiptran.actionreason',
	'Action Reason',
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
		module_name = 'OIIPTRAN'
		and msg_key = 'oiiptran.actionreason');

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
	'OIDMPCON',
	'oidmpcon.containermoveconfirm',
	'Do you want to confirm the conatiner move from one internal location to another?',
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
		module_name = 'OIDMPCON'
		and msg_key = 'oidmpcon.containermoveconfirm');

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
	'OCDEXPOW',
	'ocdexpow.transfer',
	'Transfer',
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
		and msg_key = 'ocdexpow.transfer');

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
	'CALSCH',
	'calsch.backdatedvalidation',
	'Backdate schedules are not allowed.Please select a date on or after the offender intake date.',
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
		MODULE_NAME = 'CALSCH'
		and msg_key = 'calsch.backdatedvalidation');

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
	'OIDPACTI',
	'oidpacti.deleteisnotpermittedasattendancehasalreadyrecorded',
	'Delete is not permitted as attendance has already been recorded.',
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
		MODULE_NAME = 'OIDPACTI'
		and msg_key = 'oidpacti.deleteisnotpermittedasattendancehasalreadyrecorded');

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
	'OIIVISIT',
	'oiivisit.visitsearchinquiry',
	'Visit Search Inquiry',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.visitsearchinquiry');

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
	'OIIVISIT',
	'oiivisit.fromdate',
	'From Date',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.fromdate');

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
	'OIIVISIT',
	'oiivisit.todate',
	'To Date',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.todate');

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
	'OIIVISIT',
	'oiivisit.dayoftheweek',
	'Day of the Week',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.dayoftheweek');

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
	'OIIVISIT',
	'oiivisit.timeslot',
	'Time Slot',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.timeslot');

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
	'OIIVISIT',
	'oiivisit.visitlocation',
	'Visit Location',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.visitlocation');

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
	'OIIVISIT',
	'oiivisit.status',
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
		system_labels
	where
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.status');

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
	'OIIVISIT',
	'oiivisit.prisoner',
	'Prisoner',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.prisoner');

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
	'OIIVISIT',
	'oiivisit.exception',
	'Exception',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.exception');

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
	'OIIVISIT',
	'oiivisit.bulkupdatestatus',
	'Bulk Update Status',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.bulkupdatestatus');

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
	'OIIVISIT',
	'oiivisit.bulkcancelreason',
	'Bulk Cancel Reason',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.bulkcancelreason');

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
	'OIIVISIT',
	'oiivisit.bulkcommentupdate',
	'Bulk Comment Update',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.bulkcommentupdate');

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
	'OIIVISIT',
	'oiivisit.facility',
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
		system_labels
	where
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.facility');

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
	'OIIVISIT',
	'oiivisit.cancelreason',
	'Cancel Reason',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.cancelreason');

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
	'OIIVISIT',
	'oiivisit.comment',
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
		system_labels
	where
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.comment');

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
	'OIIVISIT',
	'oiivisit.facilitymustbeentered',
	'Facility must be entered',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.facilitymustbeentered');

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
	'OIIVISIT',
	'oiivisit.datefrommustbeentered',
	'Date From must be entered',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.datefrommustbeentered');

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
	'OIIVISIT',
	'oiivisit.datetomustbeequaltoorlaterthandatefrom',
	'Date To must be equal to or later than Date From',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.datetomustbeequaltoorlaterthandatefrom');

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
	'OIIVISIT',
	'oiivisit.dayoftheweekmustbeselected',
	'Day of the Week must be selected',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.dayoftheweekmustbeselected');

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
	'OIIVISIT',
	'oiivisit.selectatleastonevisitrecordbeforeapply',
	'Select atleast one visit record before apply',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.dayoftheweekmustbeselected');

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
	'OIIVISIT',
	'oiivisit.selectatleaseonevisitrecordbeforesave',
	'Select atlease one visit record before save',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.selectatleaseonevisitrecordbeforesave');

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
	'OCDCORDS',
	'ocdcords.navigatemsg',
	'You have unsaved records on the Custodial Orders screen, do you wish to proceed? If you proceed, your records will not be saved.',
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
		MODULE_NAME = 'OCDCORDS'
		and msg_key = 'ocdcords.navigatemsg');

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
	'OCDCORDS',
	'ocdcords.changeslostmsg',
	'There are unsaved records on this screen. Do you wish to proceed? If you proceed, your records will not be saved.',
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
		MODULE_NAME = 'OCDCORDS'
		and msg_key = 'ocdcords.changeslostmsg');

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
	'OIIDMOVE',
	'oiidmove.fromdate',
	'From Date',
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
		MODULE_NAME = 'OIIDMOVE'
		and msg_key = 'oiidmove.fromdate');

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
	'OIIDMOVE',
	'oiidmove.todate',
	'To Date',
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
		MODULE_NAME = 'OIIDMOVE'
		and msg_key = 'oiidmove.todate');

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
	'OIIDMOVE',
	'oiidmove.retrieve',
	'Retrieve',
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
		MODULE_NAME = 'OIIDMOVE'
		and msg_key = 'oiidmove.retrieve');

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
	'OIIDMOVE',
	'oiidmove.clear',
	'Clear',
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
		MODULE_NAME = 'OIIDMOVE'
		and msg_key = 'oiidmove.clear');

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
	'OIIDMOVE',
	'oiidmove.fromdatemustbeequaltoorlessthancurrentdate',
	'From Date must be equal to or less than current date.',
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
		MODULE_NAME = 'OIIDMOVE'
		and msg_key = 'oiidmove.fromdatemustbeequaltoorlessthancurrentdate');

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
	'OIIDMOVE',
	'oiidmove.todatemustequalorbegreaterthanFromDateandnotgreaterthancurrentdate',
	'To Date must equal or be greater than From Date and not greater than current date.',
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
		MODULE_NAME = 'OIIDMOVE'
		and msg_key = 'oiidmove.todatemustequalorbegreaterthanFromDateandnotgreaterthancurrentdate');

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
	'OCDEXPOW',
	'ocdexpow.transferalldisplayeditems',
	'Transfer All Displayed Items',
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
		and msg_key = 'ocdexpow.transferalldisplayeditems');

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
	'OUMSYSET',
	'oumsyset.viewauditconfiguration',
	'V5 Audit Configuration',
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
		module_name = 'OUMSYSET'
		and msg_key = 'oumsyset.viewauditconfiguration');

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
	'OUMSYSET',
	'oumsyset.pleaseentervalue',
	'Please enter the value(s)',
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
		module_name = 'OUMSYSET'
		and msg_key = 'oumsyset.pleaseentervalue');

insert
	into
	system_labels(LABEL_ID,
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
		system_labels
	where
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.lastname');

insert
	into
	system_labels(LABEL_ID,
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
		system_labels
	where
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.firstname');

insert
	into
	system_labels(LABEL_ID,
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
		system_labels
	where
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.middlename');

insert
	into
	system_labels(LABEL_ID,
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
		system_labels
	where
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.secondmiddlename');

insert
	into
	system_labels(LABEL_ID,
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
		system_labels
	where
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.nametype');

insert
	into
	system_labels(LABEL_ID,
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
		system_labels
	where
		MODULE_NAME = 'OSIPSEARIDIALOG'
		and msg_key = 'osipsearidialog.personadditionalnames');

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
	'OIDSTFRP',
	'oidstfrp.screenwillbelocked',
	'Screen will be locked in 10 seconds save the changes',
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
		module_name = 'OIDSTFRP'
		and msg_key = 'oidstfrp.screenwillbelocked');

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
	'OIDSTOJU',
	'oidstoju.thisactioncannotbeperformedforaninactiveoffender',
	'This action cannot be performed for an inactive offender',
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
		module_name = 'OIDSTOJU'
		and msg_key = 'oidstoju.thisactioncannotbeperformedforaninactiveoffender');

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
	'OIIVISIT',
	'oiivisit.selectatleastonevisitrecordbeforeapply',
	'Select atleast one visit record before apply',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.selectatleastonevisitrecordbeforeapply');

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
	'OIIVISIT',
	'oiivisit.cancelreasonmustbeentered',
	'Cancel reason must be entered',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.cancelreasonmustbeentered');

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
	'OIIVISIT',
	'oiivisit.visitstatusmustbeentered',
	'visit status must be entered',
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
		MODULE_NAME = 'OIIVISIT'
		and msg_key = 'oiivisit.visitstatusmustbeentered');

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
	'OIDOICAP',
	'oidoicap.offenseincustodyincidentcharges',
	'Offense In Custody Incident Charges',
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
		module_name = 'OIDOICAP'
		and msg_key = 'oidoicap.offenseincustodyincidentcharges');

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
	'OIDOICAP',
	'oidoicap.offenderoicappealpenalties',
	'Offender OIC Appeal Penalties',
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
		module_name = 'OIDOICAP'
		and msg_key = 'oidoicap.offenderoicappealpenalties');

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
	'OCUOICAW',
	'ocuoicaw.otheroffendercharges',
	'Other Offender Charges',
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
		module_name = 'OCUOICAW'
		and msg_key = 'ocuoicaw.otheroffendercharges');

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
	'common.caseloadchangedsuccess',
	'Caseload changed successfully.',
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
		and msg_key = 'common.caseloadchangedsuccess');

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
	'OCDCORDS',
	'ocdcords.changeslostmsg',
	'There are unsaved records on this screen. Do you wish to proceed? If you proceed, your records will not be saved.',
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
		MODULE_NAME = 'OCDCORDS'
		and msg_key = 'ocdcords.changeslostmsg');
