insert
	into
	oms_owner.property_settings (
	setting_code,
	setting_description,
	setting_value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'PROP_CON_ID',
	'Property Container ID Display',
	'{ "CODE": "PROP_CON_ID", "VALUE": "DEFAULT" }',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		oms_owner.property_settings
	where
		setting_code = 'PROP_CON_ID');