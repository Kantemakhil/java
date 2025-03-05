insert
	into
	oms_owner.reference_codes (domain,
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
	'SEARCH_TYPE',
	'B',
	'DOB',
	5,
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
		domain = 'SEARCH_TYPE'
		and code = 'B');