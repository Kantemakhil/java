insert
	into
	legal_settings(code,
	description,
	value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag
)select
	'RRTIEA',
	'Release Reason To Initiate Escape Adjustment',
	'ESCP',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		legal_settings
	where
		code = 'RRTIEA');
insert
	into
	legal_settings(code,
	description,
	value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'DEL_CUST',
	'User Role With Permission To Delete Custodial Orders & Conditions',
	'DEL_CUST_ORD',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		legal_settings
	where
		code = 'DEL_CUST');

insert
	into
	legal_settings(code,
	description,
	value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'DEL_NCUS',
	'User Role With Permission To Delete Non-Custodial Orders & Conditions',
	'DEL_NCUS_ORD',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		legal_settings
	where
		code = 'DEL_NCUS');

insert
	into
	legal_settings(code,
	description,
	value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'DEL_BAIL',
	'User Role With Permission To Delete Bail Orders & Conditions',
	'DEL_BAIL_ORD',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		legal_settings
	where
		code = 'DEL_BAIL');

insert
	into
	legal_settings(code,
	description,
	value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'DEL_PAR',
	'User Role With Permission To Delete Parole Orders & Conditions',
	'DEL_PAR_ORD',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		legal_settings
	where
		code = 'DEL_PAR');

insert
	into
	legal_settings(code,
	description,
	value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'DEL_HWD',
	'User Role With Permission To Delete Holds Warrants & Detainers',
	'DEL_HWD',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		legal_settings
	where
		code = 'DEL_HWD');