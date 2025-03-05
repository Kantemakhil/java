insert
	into
	oms_owner.reference_codes
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
	'FUNCTION',
	'DOP',
	'Director of Prisons',
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
		oms_owner.reference_codes
	where
		domain = 'FUNCTION'
		and code = 'DOP');

insert
	into
	oms_owner.reference_codes
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
	modify_datetime) 
select
	'ID_TYPE',
	'PIN',
	'PIN',
	99,
	'Y',
	'Y',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp
where
	not exists (
	select
		1
	from
		oms_owner.reference_codes
	where
		domain = 'ID_TYPE'
		and code = 'PIN');

insert
	into
	oms_owner.reference_codes
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
	modify_datetime) 
select
	'ID_TYPE',
	'MPIN',
	'Merged PIN',
	99,
	'Y',
	'Y',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp
where
	not exists (
	select
		1
	from
		oms_owner.reference_codes
	where
		domain = 'ID_TYPE'
		and code = 'MPIN');

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
	modify_user_id,
	seal_flag)
select
	'FUNCTION',
	'SMU',
	'Sentence Management Unit',
	99,
	'Y',
	'N',
	null,
	null,
	null,
	null,
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
		reference_codes
	where
		domain = 'FUNCTION'
		and code = 'SMU');		