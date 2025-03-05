update
	system_settings
set
	selected_provider = 'Y',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	setting_type = 'eMail'
	and setting_provider_code = 'CLICKSEND';