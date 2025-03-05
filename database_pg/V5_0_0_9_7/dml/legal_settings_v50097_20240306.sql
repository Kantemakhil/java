insert
	into
	legal_settings (code,
	description,
	value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'BAMHWD',
	'Bail Amount Mandatory for HWDs?',
	'YES',
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
		code = 'BAMHWD' );