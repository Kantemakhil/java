update
	system_profiles
set
	profile_value = 'Y',
	modify_datetime = CURRENT_TIMESTAMP,
	MODIFY_USER_ID = 'OMS_OWNER'
where
	profile_type = 'CLIENT'
	and profile_code = 'CF_DEDUCTION';

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
	'CP_LMT_CPOWN',
	'Limit Case Plan Owner',
	'N',
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
		and profile_code = 'CP_LMT_CPOWN');
