insert
	into
	reference_domains (domain,
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
	modify_user_id)
select
	'EMPLOY_CRA',
	'Release Plan Employment Status',
	'ACTIVE',
	'OMS_OWNER',
	'OMS',
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
		reference_domains
	where
		domain = 'EMPLOY_CRA');
