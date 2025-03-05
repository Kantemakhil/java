update
	system_settings
set
	setting_value = '[{"KEY_DESC":"Sendgrid Api Key","KEY_CODE":"SG_API_KEY","VALUE":"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"},{"KEY_DESC":"Mail mailto:from","KEY_CODE":"MAIL_FROM","VALUE":"donotreply@elitev4.com"}]',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	setting_type = 'eMail'
	and setting_provider_code = 'SENDGRID';