insert
	into
	reference_domains ("domain",
	description,
	domain_status,
	owner_code,
	appln_code,
	old_code_table,
	parent_domain,
	code_length,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	super_set_domain,
	seal_flag)
select
	'OIC_APP_RSN',
	'OIC APPEAL REASON',
	'ACTIVE',
	'ADMIN',
	'ITAG',
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	null
where
	not exists (
	select
		1
	from
		reference_domains
	where
		domain = 'OIC_APP_RSN');

insert
	into
	reference_domains ("domain",
	description,
	domain_status,
	owner_code,
	appln_code,
	old_code_table,
	parent_domain,
	code_length,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	super_set_domain,
	seal_flag)
select
	'OIC_APP_RSLT',
	'OIC APPEAL RESULT',
	'ACTIVE',
	'ADMIN',
	'ITAG',
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	null
where
	not exists (
	select
		1
	from
		reference_domains
	where
		domain = 'OIC_APP_RSLT');