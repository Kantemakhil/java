insert
	into
	oms_owner.system_settings(setting_type,
	setting_provider_code,
	setting_value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
 select
	'INCEDENT_REPORTING',
	'INCEDENT_REPORTING',
	'[{"KEY_DESC":"Show reportable incident popup icon",
"KEY_CODE":"REP_INC_FORM",
"VALUE":"Y" }]',
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
		system_settings
	where
		setting_type = 'INCEDENT_REPORTING'
		and setting_provider_code = 'INCEDENT_REPORTING');