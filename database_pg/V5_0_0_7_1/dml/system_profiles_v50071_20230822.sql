insert
	into
	oms_owner.system_profiles
(profile_type,
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
	'PIN',
	'Person Search (OSIPSEAR)/Offender Search (OSIOSEAR) Results PIN column visible?',
	'N',
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
		oms_owner.system_profiles
	where
		profile_type = 'CLIENT'
		and profile_code = 'PIN');

insert
	into
	oms_owner.system_profiles (profile_type,
	profile_code,
	description,
	profile_value,
	profile_value_2,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id) 
 select
	'CLIENT',
	'PIN_ID_TYPE',
	'The Offender and Person identifier type used for PIN search',
	'PIN',
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		oms_owner.system_profiles
	where
		profile_type = 'CLIENT'
		and profile_code = 'PIN_ID_TYPE');
