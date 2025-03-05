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
	'CPLAN_REVIEW',
	'Case Plan Review',
	1,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		oms_owner.reference_codes
	where
		domain = 'FUNCTION'
		and code = 'CPLAN_REVIEW');

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
	'CPLAN_VERIFY',
	'Case Plan Verification',
	2,
	'Y',
	'N',
	'OMS_OWNER', 
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		oms_owner.reference_codes
	where
		domain = 'FUNCTION'
		and code = 'CPLAN_VERIFY');

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
	'ASS_APR',
	'Assessments Approval',
	99,
	'Y',
	'N',
	'OMS_OWNER', 
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp, 
	null
where
	not exists (
	select
		1
	from
		oms_owner.reference_codes
	where
		domain = 'FUNCTION'
		and code = 'ASS_APR');
		
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
	'PER_COM',
	'Performance and Compliance',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		oms_owner.reference_codes
	where
		domain = 'FUNCTION'
		and code = 'PER_COM');		

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
	'WHS',
	'Workplace Health & Safety',
	1,
	'Y',
	'N',
	'OMS_OWNER', 
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		oms_owner.reference_codes
	where
		domain = 'FUNCTION'
		and code = 'WHS');

update
	oms_owner.reference_codes
set
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp,
	code = 'TST'
where
	code = ' TST'
	and "domain" = 'APT_CAN_REA';		