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
	create_user_id)
select
	'CLIENT',
	'SPEL_LANG_ID',
	'The language id to map server side dictionary,It Supports EN_US,EN_UK_,EN_AU',
	'EN_US',
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		system_profiles
	where
		profile_type = 'CLIENT'
		and profile_code = 'SPEL_LANG_ID');