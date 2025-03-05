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
	'OCDPSREP',
	'ocdpsrep.interestedparties',
	'Interested Parties',
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
		MODULE_NAME = 'OCDPSREP'
		and msg_key = 'ocdpsrep.interestedparties');

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
	'OCDENFOR',
	'ocdenfor.save',
	'Save',
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
		MODULE_NAME = 'OCDENFOR'
		and msg_key = 'ocdenfor.save');

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
	'CALSCH',
	'calsch.cancelreasonmustbeentered',
	'Cancel Reason must be entered',
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
		MODULE_NAME = 'CALSCH'
		and msg_key = 'calsch.cancelreasonmustbeentered');

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
	'OCUALLCO',
	'ocuallco.orderno',
	'Order No',
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
		MODULE_NAME = 'OCUALLCO'
		and msg_key = 'ocuallco.orderno');

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
	'OCUALLCO',
	'ocuallco.conditioncategory',
	'Condition Category',
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
		MODULE_NAME = 'OCUALLCO'
		and msg_key = 'ocuallco.conditioncategory');

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
	'OCUALLCO',
	'ocuallco.description',
	'Description',
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
		MODULE_NAME = 'OCUALLCO'
		and msg_key = 'ocuallco.description');

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
	'OCUALLCO',
	'ocuallco.length',
	'Length',
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
		MODULE_NAME = 'OCUALLCO'
		and msg_key = 'ocuallco.length');

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
	'OCUALLCO',
	'ocuallco.startdate',
	'Start Date',
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
		MODULE_NAME = 'OCUALLCO'
		and msg_key = 'ocuallco.startdate');

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
	'OCUALLCO',
	'ocuallco.enddate',
	'End Date',
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
		MODULE_NAME = 'OCUALLCO'
		and msg_key = 'ocuallco.enddate');

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
	'OCUALLCO',
	'ocuallco.referral',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OCUALLCO'
		and msg_key = 'ocuallco.referral');

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
	'OCUALLCO',
	'ocuallco.conditionstatus',
	'Condition Status',
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
		MODULE_NAME = 'OCUALLCO'
		and msg_key = 'ocuallco.conditionstatus');

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
	'OCUALLCO',
	'ocuallco.allocatedcondition',
	'Allocated Conditions',
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
		MODULE_NAME = 'OCUALLCO'
		and msg_key = 'ocuallco.allocatedcondition');

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
	'OCIMYOFF',
	'ocimyoff.noconditionisassignforthisoffender',
	'No condition is assign for this offender',
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
		MODULE_NAME = 'OCIMYOFF'
		and msg_key = 'ocimyoff.noconditionisassignforthisoffender');

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
	'OCDBAILO',
	'ocdbailo.staff',
	'Assigned officer',
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
		MODULE_NAME = 'OCDBAILO'
		and msg_key = 'ocdbailo.staff');

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
	'OCDPAROR',
	'ocdparor.staff',
	'Assigned officer',
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
		MODULE_NAME = 'OCDPAROR'
		and msg_key = 'ocdparor.staff');

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
	'OCDNCODE',
	'ocdncode.staff',
	'Assigned officer',
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
		MODULE_NAME = 'OCDNCODE'
		and msg_key = 'ocdncode.staff');

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
	'OCUALLCO',
	'ocuallco.exit',
	'Exit',
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
		MODULE_NAME = 'OCUALLCO'
		and msg_key = 'ocuallco.exit');

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
	'INSIGHTS',
	'insights.pleasesearchforvalidoffender',
	'Please search for an offender. You can use the Offender Search in the Header Bar, or the Global Name Search / Caseload Name Search screens',
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
		module_name = 'INSIGHTS'
		and msg_key = 'insights.pleasesearchforvalidoffender');

update
	SYSTEM_LABELS
set
	MSG_VALUE = 'Status' ,
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	module_name = 'OCUALLCO'
	and msg_key = 'ocuallco.conditionstatus' ;

update
	SYSTEM_LABELS
set
	MSG_VALUE = 'Referral' ,
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	module_name = 'OCUALLCO'
	and msg_key = 'ocuallco.referral' ;

update
	SYSTEM_LABELS
set
	MSG_VALUE = 'Category' ,
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	module_name = 'OCUALLCO'
	and msg_key = 'ocuallco.conditioncategory' ;

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
	'OCDCORDS',
	'ocdcords.rfcordercannotchangestatustoexpire',
	'Non-custodial orders with a commence type of ‘Upon release from custody’ exist. This order cannot be excluded.',
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
		MODULE_NAME = 'OCDCORDS'
		and msg_key = 'ocdcords.rfcordercannotchangestatustoexpire');

update
	SYSTEM_LABELS
set
	MSG_VALUE = 'No information to display. The offender may not have any bookings, or may not be accessible from the current caseload.' ,
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	module_name = 'COMMON'
	and msg_key = 'common.pleasesearchforvalidoffender';

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
	'OUMPURGE',
	'oumpurge.sealinganactivebookingisnotallowed',
	'Sealing an active booking is not allowed.',
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
		MODULE_NAME = 'OUMPURGE'
		and msg_key = 'oumpurge.sealinganactivebookingisnotallowed');

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
	'OUMPURGE',
	'oumpurge.sealalltheexistingbookingrecordsfirst',
	'Seal all the existing active bookings first.',
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
		MODULE_NAME = 'OUMPURGE'
		and msg_key = 'oumpurge.sealalltheexistingbookingrecordsfirst');

update
	system_labels
set
	msg_value = 'Unsupported File Format,Only pdf ,docx, doc,jpg,jpeg,png,gif,bmp,tiff,tif,rtf,txt,svg,html,xls and xlsx are allowed',
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	msg_key = 'uploaddoc.unsupportedFile';

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
	'OIIGRIEV',
	'oiigriev.tranaction',
	'Transaction',
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
		MODULE_NAME = 'OIIGRIEV'
		and msg_key = 'oiigriev.txnType');

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
	'OIIGRIEV',
	'oiigriev.issuereason',
	'Issue Reason',
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
		MODULE_NAME = 'OIIGRIEV'
		and msg_key = 'oiigriev.issuereason');

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
	'OIIGRIEV',
	'oiigriev.issuetype',
	'Issue Type',
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
		MODULE_NAME = 'OIIGRIEV'
		and msg_key = 'oiigriev.issuetype');