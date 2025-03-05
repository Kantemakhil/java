insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag, 
	parent_domain, 
	create_datetime,
	create_user_id,
	seal_flag)
select
	'TASK_SUBTYPE',
	'REQ_SMU_ASS',
	'Request SMU Assessment',
	99,
	'Y',
	'N',
	'TASK_TYPE',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'TASK_SUBTYPE'
		and code = 'REQ_SMU_ASS');

insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'TASK_SUBTYPE',
	'EDU_ASS_REF',
	'Education Assessment Refusal',
	1,
	'Y',
	'Y',
	null,
	null,
	null,
	'TASK_TYPE',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'TASK_SUBTYPE'
		and code = 'EDU_ASS_REF');

insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'TASK_SUBTYPE',
	'REQ_RPB_ASS',
	'Request RPB Assessment',
	99,
	'Y',
	'Y',
	null,
	null,
	null,
	'TASK_TYPE',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'TASK_SUBTYPE'
		and code = 'REQ_RPB_ASS');