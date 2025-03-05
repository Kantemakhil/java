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
	'OIMOFFEN',
	'oimoffen.cannotdeletelinkedwithcharges',
	'Cannot delete this offence as charges are linked with it.',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIMOFFEN'
		and msg_key = 'oimoffen.cannotdeletelinkedwithcharges');

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
	'OCUINCWP',
	'ocuincwp.title',
	'Offender Weapons',
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
		module_name = 'OCUINCWP'
		and msg_key = 'ocuincwp.title');

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
	'OCUINCWP',
	'ocuincwp.offenderweapons',
	'Offender Weapons',
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
		module_name = 'OCUINCWP'
		and msg_key = 'ocuincwp.offenderweapons');

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
	'OCUINCWP',
	'ocuincwp.weaponsused',
	'Weapons Used*',
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
		module_name = 'OCUINCWP'
		and msg_key = 'ocuincwp.weaponsused');

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
	'OCUINCWP',
	'ocuincwp.details',
	'Details',
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
		module_name = 'OCUINCWP'
		and msg_key = 'ocuincwp.details');

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
	'OCDCLOSE',
	'ocdclose.activeordersreportsarepresent',
	'Active orders or court request exist for this offender. Are you sure you want to discharge this offender?',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDCLOSE'
		and msg_key = 'ocdclose.activeordersreportsarepresent');

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
	'OCDCLOSE',
	'ocdclose.proceed',
	'Proceed',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDCLOSE'
		and msg_key = 'ocdclose.proceed');

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
	'OCDCLOSE',
	'ocdclose.stop',
	'Stop',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDCLOSE'
		and msg_key = 'ocdclose.stop');

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
	'OIDINCDE',
	'oidincde.appendReport',
	'Append Report',
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
		module_name = 'OIDINCDE'
		and msg_key = 'oidincde.appendReport');

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
	'OCMXPSTM',
	'ocmxpstm.specializedprogramstatus',
	'Specialized Program Statuses',
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
		module_name = 'OCMXPSTM'
		and msg_key = 'ocmxpstm.specializedprogramstatus');

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
	'OIICLASS',
	'oiiclass.agencyloc',
	'Agency Location',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIICLASS'
		and msg_key = 'oiiclass.agencyloc');

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
	'OIICLASS',
	'oiiclass.assessments',
	'Offender Assessment Questionnaire',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIICLASS'
		and msg_key = 'oiiclass.assessments');

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
	'OIICLASS',
	'oiiclass.primaryofficer',
	'COM Primary Officer',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OIICLASS'
		and msg_key = 'oiiclass.primaryofficer');

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
	'OCDENFOR',
	'ocdenfor.persuantto',
	'Persuant To',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDENFOR'
		and msg_key = 'ocdenfor.persuantto');

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
	'OCDENFOR',
	'ocdenfor.recommendationdocuments',
	'Recommendation Documents',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDENFOR'
		and msg_key = 'ocdenfor.recommendationdocuments');

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
	'OCDENFOR',
	'ocdenfor.selectcondition',
	'Select Condition',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDENFOR'
		and msg_key = 'ocdenfor.selectcondition');

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
	'OCDPSREP',
	'ocdpsrep.notSuitableFlag',
	'Not Suitable',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDPSREP'
		and msg_key = 'ocdpsrep.notSuitableFlag');

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
	'OCDPSREP',
	'ocdpsrep.notSuitableReason',
	'Not Suitable Reason',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDPSREP'
		and msg_key = 'ocdpsrep.notSuitableReason');

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
	'OCDPSREP',
	'ocdpsrep.pleaseenternotsuitablereason',
	'Please select Not Suitable Reason',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDPSREP'
		and msg_key = 'ocdpsrep.pleaseenternotsuitablereason');

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
	'OCDPSREP',
	'ocdpsrep.checknotsuitablecheckbox',
	'Please check the Not Suitable checkbox before selecting a reason. ',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDPSREP'
		and msg_key = 'ocdpsrep.checknotsuitablecheckbox');

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
	'OCDLEGLS',
	'ocdlegls.thischeckboxcannotbeselectedforthiskey',
	'This checkbox cannot be checked for the selected key date.',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDLEGLS'
		and msg_key = 'ocdlegls.thischeckboxcannotbeselectedforthiskey');

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
	'common.sex',
	'Sex',
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
		and msg_key = 'common.sex');

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
	'OCDENFOR',
	'ocdenfor.savetheactionsdatafirst',
	'Save the action data before linking',
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
		SYSTEM_LABELS
	where
		MODULE_NAME = 'OCDENFOR'
		and msg_key = 'ocdenfor.savetheactionsdatafirst');

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
	'common.mustbeentered',
	'Must be entered',
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
		and msg_key = 'common.mustbeentered');