insert
	into
	oms_owner.system_profiles (profile_type,
	profile_code,
	description,
	profile_value,
	profile_value_2,
	modify_user_id,
	old_table_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CLIENT',
	'INC_STF_INV',
	'Incident-related Staff Involvement is specific to the incident agency location',
	'N',
	null,
    'OMS_OWNER',
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	null
where
	not exists(
	select
		1
	from
		oms_owner.system_profiles
	where
		profile_type = 'CLIENT'
		and profile_code = 'INC_STF_INV') ;