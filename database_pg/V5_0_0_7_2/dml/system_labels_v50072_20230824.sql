update
	oms_owner.system_labels
set
	msg_value = 'Sentence Calculation is currently unavailable. This record will be saved and calculations will completed when it is restored.',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCUCALCR'
	and msg_key = 'ocucalcr.applicationstatusdown';

update
	oms_owner.system_labels
set
	msg_value = 'This offender has pending sentence calculation events. This event has been saved, and will be calculated after the pending events.',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCUCALCR'
	and msg_key = 'ocucalcr.userhaspendingcalcevents';

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
	'COMMON',
	'owheader.legal.expectedreleasedate',
	'Expected Release Date',
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
		and msg_key = 'owheader.legal.expectedreleasedate');

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
	'COMMON',
	'owheader.legal.paroleeligibilitydate',
	'Parole Eligibility Date',
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
		and msg_key = 'owheader.legal.paroleeligibilitydate');

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
	'COMMON',
	'owheader.legal.remissioneligibilitydate',
	'Remission Eligibility Date',
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
		and msg_key = 'owheader.legal.remissioneligibilitydate');

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
	'COMMON',
	'owheader.legal.latestreleasedate',
	'Latest Release Date',
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
		and msg_key = 'owheader.legal.latestreleasedate');

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
	'COMMON',
	'owheader.legal.custodystatus',
	'Custody Status',
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
		and msg_key = 'owheader.legal.custodystatus');

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
	'COMMON',
	'owheader.legal.confirmedreleasedate',
	'Confirmed Release Date',
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
		and msg_key = 'owheader.legal.confirmedreleasedate');

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
	'OIDSTEST',
	'oidstest.cannotdeletesubstancetestingattacheddoc',
	'Cannot be deleted as the record has attached documents',
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
		MODULE_NAME = 'OIDSTEST'
		and msg_key = 'oidstest.cannotdeletesubstancetestingattacheddoc');
		
UPDATE
	SYSTEM_LABELS
SET
	MSG_VALUE = 'Associated Legal Case',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	MODULE_NAME = 'OIVCTMNG'
	AND msg_key = 'oivctmng.associatedlegalcase';

UPDATE
	SYSTEM_LABELS
SET
	MSG_VALUE = 'Legal Summery',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	MODULE_NAME = 'OIVCTMNG'
	AND msg_key = 'oivctmng.legalsummery';

UPDATE
	SYSTEM_LABELS
SET
	MSG_VALUE = 'Preferred Contact Method',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	MODULE_NAME = 'OIVCTMNG'
	AND msg_key = 'oivctmng.preferredcontactmethod';

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
	'OCDIPLAN',
	'ocdiplan.communityofficer',
	'Community Officer',
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
		MODULE_NAME = 'OCDIPLAN'
		and msg_key = 'ocdiplan.communityofficer');	
