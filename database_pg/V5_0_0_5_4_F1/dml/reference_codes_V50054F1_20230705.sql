insert
	into
	oms_owner.reference_codes (domain,
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
	'EMAIL_DOMAIN',
	'GMAIL',
	'gmail.com',
	99,
	'Y',
	'Y',
	null,
	null,
	null,
	null,
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
		domain = 'EMAIL_DOMAIN'
		and code = 'GMAIL');