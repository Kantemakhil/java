insert
	into
	reference_codes
("domain",
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
	'DEL_DOC',
	'ERR',
	'Created in error',
	1,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
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
		domain = 'DEL_DOC'
		and code = 'ERR');

insert
	into
	reference_codes
("domain",
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
	'DEL_DOC',
	'OLD',
	'Old Version',
	2,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
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
		domain = 'DEL_DOC'
		and code = 'OLD');

insert
	into
	reference_codes
("domain",
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
	'DEL_DOC',
	'NLN',
	'No longer needed',
	3,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
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
		domain = 'DEL_DOC'
		and code = 'NLN');