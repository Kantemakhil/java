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
	'OCDCORDS',
	'ocdcords.orderdeleteconfirmationtitle',
	'Order Delete Confirmation',
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
		module_name = 'OCDCORDS'
		and msg_key = 'ocdcords.orderdeleteconfirmationtitle');

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
	'OCDCORDS',
	'ocdcords.orderdeleteconfirmationmessage',
	'You are about to delete the selected legal order. Do you wish to proceed ?',
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
		module_name = 'OCDCORDS'
		and msg_key = 'ocdcords.orderdeleteconfirmationmessage');

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
	'OUMSYSET',
	'oumsyset.sabrenotificationoverrides',
	'SABRE Notification Overrides',
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
		MODULE_NAME = 'OUMSYSET'
		and msg_key = 'oumsyset.sabrenotificationoverrides');

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
	'OUMSYSET',
	'oumsyset.pleaseenterafootermessage',
	'please enter a footer message',
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
		MODULE_NAME = 'OUMSYSET'
		and msg_key = 'oumsyset.pleaseenterafootermessage');

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
	'OUMSYSET',
	'oumsyset.pleaseenteravalidphonenumber',
	'please enter a valid phone number',
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
		MODULE_NAME = 'OUMSYSET'
		and msg_key = 'oumsyset.pleaseenteravalidphonenumber');

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
	'OUMSYSET',
	'oumsyset.pleaseenteraemailaddress',
	'please enter a email address',
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
		MODULE_NAME = 'OUMSYSET'
		and msg_key = 'oumsyset.pleaseenteraemailaddress');

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
	'OUMSYSET',
	'oumsyset.pleaseenteravalidemailaddress',
	'please enter a valid email address',
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
		MODULE_NAME = 'OUMSYSET'
		and msg_key = 'oumsyset.pleaseenteravalidemailaddress');

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
	'OUMTAGRE',
	'oumtagre.save',
	'Save',
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
		MODULE_NAME = 'OUMTAGRE'
		and msg_key = 'oumtagre.save');

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
	'OUMTAGRE',
	'oumtagre.auditscreenviews',
	'Audit Screen Views',
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
		MODULE_NAME = 'OUMTAGRE'
		and msg_key = 'oumtagre.auditscreenviews');

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
	'OIDCUSTAD',
	'oidcustad.pleasesavefirst',
	'Please save the modified changes',
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
		module_name = 'OIDCUSTAD'
		and msg_key = 'oidcustad.pleasesavefirst');

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
	'oidoicap.oicincidentid',
	'OIC Incident ID',
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
		and msg_key = 'oidoicap.oicincidentid');

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
	'oidoicap.resultseq',
	'Result Seq',
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
		and msg_key = 'oidoicap.resultseq');

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
	'oidoicap.resultingcharge',
	'Resulting Charge',
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
		and msg_key = 'oidoicap.resultingcharge');

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
	'oidoicap.description',
	'Description',
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
		and msg_key = 'oidoicap.description');

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
	'oidoicap.cancel',
	'Cancel',
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
		and msg_key = 'oidoicap.cancel');

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
	'oidoicap.select',
	'Select',
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
		and msg_key = 'oidoicap.select');

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
	'oidoicap.ln',
	'Ln',
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
		and msg_key = 'oidoicap.ln');

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
	'oidoicap.tolog',
	'To Log#',
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
		and msg_key = 'oidoicap.tolog');

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
	'oidoicap.type',
	'Type',
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
		and msg_key = 'oidoicap.type');

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
	'oidoicap.apphearingdate',
	'App Hearing Date',
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
		and msg_key = 'oidoicap.apphearingdate');

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
	'oidoicap.apphearingtime',
	'App Hearing Time',
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
		and msg_key = 'oidoicap.apphearingtime');

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
	'ocuoicaw.adjudication',
	'Adjudication',
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
		and msg_key = 'ocuoicaw.adjudication');

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
	'ocuoicaw.paragraph',
	'Paragraph',
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
		and msg_key = 'ocuoicaw.paragraph');

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
	'ocuoicaw.type',
	'Type',
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
		and msg_key = 'ocuoicaw.type');

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
	'ocuoicaw.description',
	'Description',
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
		and msg_key = 'ocuoicaw.description');

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
	'ocuoicaw.hearingdate',
	'Hearing Date',
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
		and msg_key = 'ocuoicaw.hearingdate');

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
	'ocuoicaw.hearingtime',
	'Hearing Time',
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
		and msg_key = 'ocuoicaw.hearingtime');

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
	'ocuoicaw.cancel',
	'Cancel',
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
		and msg_key = 'ocuoicaw.cancel');

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
	'ocuoicaw.ok',
	'OK',
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
		and msg_key = 'ocuoicaw.ok');

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
	'OCDOTRLV',
	'ocdotrlv.deactivateddate',
	'Deactivated Date',
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
		module_name = 'OCDOTRLV'
		and msg_key = 'ocdotrlv.deactivateddate');

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
	'OCUOICHE',
	'ocuoiche.disciplines',
	'Discip...',
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
		module_name = 'OCUOICHE'
		and msg_key = 'ocuoiche.disciplines');

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
	'OCDUPROJ',
	'ocduproj.panetitle',
	'Community Service Projects',
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
		module_name = 'OCDUPROJ'
		and msg_key = 'ocduproj.panetitle');

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
	'OIDPIDEN',
	'oidpiden.height',
	'Height',
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
		module_name = 'OIDPIDEN'
		and msg_key = 'oidpiden.height');

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
	'OIDPIDEN',
	'oidpiden.weight',
	'Weight',
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
		module_name = 'OIDPIDEN'
		and msg_key = 'oidpiden.weight');

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
	'OIDPIDEN',
	'oidpiden.other',
	'Other',
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
		module_name = 'OIDPIDEN'
		and msg_key = 'oidpiden.other');

update
	SYSTEM_LABELS
set
	MSG_VALUE = 'Please enter an Action record before linking a condition' ,
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	module_name = 'OCDENFOR'
	and msg_key = 'ocdenfor.savetheactionsdatafirst';

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
	'OIDVISIT',
	'oidvisit.cancelreasonmustbeentered',
	'cancel reason must be entered',
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
		MODULE_NAME = 'OIDVISIT'
		and msg_key = 'oidvisit.cancelreasonmustbeentered');
		
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
	'OSIOSEAR',
	'osiosear.id',
	'ID#',
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
		module_name = 'OSIOSEAR'
		and msg_key = 'osiosear.id');

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
	'OSIOSEAR',
	'osiosear.bookid',
	'Intake Number',
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
		module_name = 'OSIOSEAR'
		and msg_key = 'osiosear.bookid');

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
	'OIDSTABS',
	'oidstabs.type',
	'Type must be entered',
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
		module_name = 'OIDSTABS'
		and msg_key = 'oidstabs.type');		
		
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
	'OCDIPAN',
	'ocdipan.securitylevelcodedefinedinsystemprofiles',
	'Security Level code defined in System Profiles is not available in the Reference Codes.',
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
		module_name = 'OCDIPAN'
		and msg_key = 'ocdipan.securitylevelcodedefinedinsystemprofiles');		