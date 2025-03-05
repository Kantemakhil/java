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
	'OIMSLEVL',
	'oimslevl.onlyoneoverridetotalflagshouldbeactive',
	'Only one override total flag should be active',
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
		MODULE_NAME = 'OIMSLEVL'
		and msg_key = 'oimslevl.onlyoneoverridetotalflagshouldbeactive');

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
	'OIMSTRIP',
	'oimstrip.pleaseenterstaffname',
	'Please enter Staff Name',
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
		MODULE_NAME = 'OIMSTRIP'
		and msg_key = 'oimstrip.pleaseenterstaffname');

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
	'CALSCH',
	'calsch.thiscourteventhaslinkedappointmentoutcomes',
	'This court event has linked appointment outcomes',
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
		module_name = 'CALSCH'
		and msg_key = 'calsch.thiscourteventhaslinkedappointmentoutcomes');

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
	'OIDCRTEV',
	'oidcrtev.thiscourteventhaslinkedappointmentoutcomes',
	'This court event has linked appointment outcomes',
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
		module_name = 'OIDCRTEV'
		and msg_key = 'oidcrtev.thiscourteventhaslinkedappointmentoutcomes');

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
	'OCMTIDET',
	'ocmtidet.thisscheduleisalreadyassignedtooffenderyoucannotdeletethisrecord',
	'Schedules already assigned to offender cannot be deleted.',
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
		MODULE_NAME = 'OCMTIDET'
		and msg_key = 'ocmtidet.thisscheduleisalreadyassignedtooffenderyoucannotdeletethisrecord');

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
	'OCUVERIF',
	'ocuverif.status',
	'Status',
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
		module_name = 'OCUVERIF'
		and msg_key = 'ocuverif.status');

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
	'OCUVERIF',
	'ocuverif.commnt',
	'Comment',
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
		module_name = 'OCUVERIF'
		and msg_key = 'ocuverif.commnt');

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
	'OCUVERIF',
	'ocuverif.actionmustbeentered',
	'Action Must be entered',
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
		module_name = 'OCUVERIF'
		and msg_key = 'ocuverif.actionmustbeentered');

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
	'OIDGENST',
	'oidgenst.generateschedule',
	'Generate Schedule',
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
		MODULE_NAME = 'OIDGENST'
		and msg_key = 'oidgenst.generateschedule');

update
	system_labels
set
	msg_value = 'At least one primary check flag should be checked',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'ocdiplac.atleastOnePrimaryCheckFalg'
	and module_name = 'OCDIPLAC';

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
	'OIDINCDE',
	'oidincde.reportableincident',
	'Reportable Incident',
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
		module_name = 'OIDINCDE'
		and msg_key = 'oidincde.reportableincident');

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
	'OIUREPIN',
	'oiurepin.reportableincidenttype',
	'Reportable Incident Type',
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
		module_name = 'OIUREPIN'
		and msg_key = 'oiurepin.reportableincidenttype');

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
	'OIUREPIN',
	'oiurepin.incident',
	'Incident',
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
		module_name = 'OIUREPIN'
		and msg_key = 'oiurepin.incident');

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
	'OIUREPIN',
	'oiurepin.comment',
	'Comment',
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
		module_name = 'OIUREPIN'
		and msg_key = 'oiurepin.comment');

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
	'OIUREPIN',
	'oiurepin.username',
	'User Name',
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
		module_name = 'OIUREPIN'
		and msg_key = 'oiurepin.username');

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
	'OIUREPIN',
	'oiurepin.date',
	'Date',
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
		module_name = 'OIUREPIN'
		and msg_key = 'oiurepin.date');

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
	'OIUREPIN',
	'oiurepin.rowalreadyexistswithsamereportableincidenttype',
	'Row already exists with same reportable incident type',
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
		module_name = 'OIUREPIN'
		and msg_key = 'oiurepin.rowalreadyexistswithsamereportableincidenttype');

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
	'OUMSYSET',
	'oumsyset.incidentreporting',
	'Incident Reporting',
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
		module_name = 'OUMSYSET'
		and msg_key = 'oumsyset.incidentreporting');

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
	'UPLOADDOC',
	'uploaddoc.docnamemust',
	'Document Name Must be entered',
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
		module_name = 'UPLOADDOC'
		and msg_key = 'uploaddoc.docnamemust');

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
	'UPLOADDOC',
	'uploaddoc.filenamemaxlength',
	'File name contain more than 100 Characters,You Need to Enter the Document Name manually',
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
		module_name = 'UPLOADDOC'
		and msg_key = 'uploaddoc.filenamemaxlength');

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
	'oummerof.fromoffendershouldbeactive',
	'From offender should be active.',
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
		and msg_key = 'oummerof.fromoffendershouldbeactive');

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
	'oummerof.sameoffenderscannotbemerged',
	'Same offenders can not be merged.',
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
		and msg_key = 'oummerof.sameoffenderscannotbemerged');

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
	'oummerof.fromoffenderbookingstatusshouldbeopen',
	'From offender booking status should be open.',
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
		and msg_key = 'oummerof.fromoffenderbookingstatusshouldbeopen');

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
	'oummerof.tooffenderbookingstatusshouldbeclose',
	'To offender booking status should be close.',
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
		and msg_key = 'oummerof.tooffenderbookingstatusshouldbeclose');

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
	'OIDSIAPP',
	'oidsiapp.cancelflag',
	'Cancel?',
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
		module_name = 'OIDSIAPP'
		and msg_key = 'oidsiapp.cancelflag');

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
	'OIDSIAPP',
	'oidsiapp.cancelreason',
	'Cancel Reason',
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
		module_name = 'OIDSIAPP'
		and msg_key = 'oidsiapp.cancelreason');

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
	'OIDBSIAP',
	'oidbsiap.cancelall',
	'Cancel?',
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
		module_name = 'OIDBSIAP'
		and msg_key = 'oidbsiap.cancelall');

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
	'OIDBSIAP',
	'oidbsiap.cancelreason',
	'Cancel Reason',
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
		module_name = 'OIDBSIAP'
		and msg_key = 'oidbsiap.cancelreason');

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
	'OIDBSIAP',
	'oidbsiap.cancelflag',
	'Cancel?',
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
		module_name = 'OIDBSIAP'
		and msg_key = 'oidbsiap.cancelflag');

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
	'CALSCH',
	'calsch.schedulereason',
	'Schedule Reason',
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
		module_name = 'CALSCH'
		and msg_key = 'calsch.schedulereason');

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
	'CALSCH',
	'calsch.scheduledate',
	'Schedule Date',
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
		module_name = 'CALSCH'
		and msg_key = 'calsch.scheduledate');

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
	'CALSCH',
	'calsch.scheduletime',
	'Schedule Time',
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
		module_name = 'CALSCH'
		and msg_key = 'calsch.scheduletime');

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
	'CALSCH',
	'calsch.cancelreason',
	'Cancel Reason',
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
		module_name = 'CALSCH'
		and msg_key = 'calsch.cancelreason');

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
	'CALSCH',
	'calsh.cancelappointment',
	'Cancel appointment',
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
		module_name = 'CALSCH'
		and msg_key = 'calsch.cancelreason');

delete
from
	SYSTEM_LABELS
where
	msg_key = 'ocdiplac.atleastOnePrimaryCheckFalg'
	and module_name = 'OCDIPLAC';

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
	'OCDIPLAC',
	'ocdiplac.atleastoneprimarycheckflag',
	'At least one primary check flag should be checked',
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
		MODULE_NAME = 'OCDIPLAC'
		and msg_key = 'ocdiplac.atleastoneprimarycheckflag');


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
	'OCDRECEI',
	'ocdrecei.amountmustbeentered',
	'Amount must be entered',
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
		module_name = 'OCDRECEI'
		and msg_key = 'ocdrecei.amountmustbeentered');

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
	'OCDRECEI',
	'ocdrecei.printreceripvalidation',
	'Receipt Flag is not checked for selected Payment Method in Maintain Trancations Operations Screen.',
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
		module_name = 'OCDRECEI'
		and msg_key = 'ocdrecei.printreceripvalidation');

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
	'OCDRECEI',
	'ocdrecei.offenderfeesgrid',
	'Offender Fees',
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
		module_name = 'OCDRECEI'
		and msg_key = 'ocdrecei.offenderfeesgrid');

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
	'OCDRECEI',
	'ocdrecei.amountenteredcannotbegrtrthanbalanceowing',
	'Amount entered cannot be greater than the Balance Owing.',
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
		module_name = 'OCDRECEI'
		and msg_key = 'ocdrecei.amountenteredcannotbegrtrthanbalanceowing');

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
	'OCDBRECI',
	'ocdbreci.billstatus',
	'Bill Status',
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
		module_name = 'OCDBRECI'
		and msg_key = 'ocdbreci.billstatus');

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
	'OCDBRECI',
	'ocdbreci.balance',
	'Balance',
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
		module_name = 'OCDBRECI'
		and msg_key = 'ocdbreci.balance');

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
	'OCDBRECI',
	'ocdbreci.amount',
	'Amount',
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
		module_name = 'OCDBRECI'
		and msg_key = 'ocdbreci.amount');

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
	'OIDBSIAP',
	'oidbsiap.cancelreasonmand',
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
		system_labels
	where
		module_name = 'OIDBSIAP'
		and msg_key = 'oidbsiap.cancelreasonmand');