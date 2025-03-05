insert
	into
	oms_owner.system_settings
(setting_type,
	setting_provider_code,
	setting_value,
	modify_user_id,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag,
	selected_provider)
select
	'PAGE_CONFIG',
	'PAGE_CONFIG',
	'[{"KEY_DESC":"Institution Landing page",
"KEY_CODE":"INSTLP",
"VALUE":"" },
{"KEY_DESC":"Community Landing page",
"KEY_CODE":"COMMLP",
"VALUE":"" },
{"KEY_DESC":"Offender Dashboard",
"KEY_CODE":"OFDSHB",
"VALUE":" " }]',
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null,
	'N'
where
	not exists (
	select
		1
	from
		system_settings
	where
		setting_type = 'PAGE_CONFIG'
		and setting_provider_code = 'PAGE_CONFIG');
		