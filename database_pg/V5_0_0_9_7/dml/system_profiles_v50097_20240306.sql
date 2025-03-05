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
	'IEP_MAN_REV',
	'IEP mandatory review days On/Off',
	'N',
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null
where
	not exists(
	select
		1
	from
		oms_owner.system_profiles
	where
		profile_type = 'CLIENT'
		and profile_code = 'IEP_MAN_REV');