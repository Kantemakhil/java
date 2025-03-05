insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'ALLOWANCE',
	'BASIC',
	'Basic Allowance',
	99,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	null,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'ALLOWANCE'
		and code = 'BASIC');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'ALLOWANCE',
	'MEDICAL',
	'Medical Allowance',
	99,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	null,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'ALLOWANCE'
		and code = 'MEDICAL');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'ALLOWANCE',
	'SEGREGATION',
	'Segregation Allowance',
	99,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	null,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'ALLOWANCE'
		and code = 'SEGREGATION');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'ALLOWANCE',
	'STANDBY',
	'Standby Allowance',
	99,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	null,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'ALLOWANCE'
		and code = 'STANDBY');
