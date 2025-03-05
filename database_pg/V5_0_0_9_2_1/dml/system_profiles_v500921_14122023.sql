insert
	into
	system_profiles (profile_type,
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
	'WAP_FOO_LBL',
	'WAP Staff Copy (OCRWAPST) Report footer label',
	'Staff Only',
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
		system_profiles
	where
		profile_type = 'CLIENT'
		and profile_code = 'WAP_FOO_LBL') ;