update
	system_settings
set
	setting_value = '[{"KEY_DESC":"Institution Landing page","KEY_CODE":"INSTLP","VALUE":"","isOffenderSpecific":"N"},{"KEY_DESC":"Community Landing page","KEY_CODE":"COMMLP","VALUE":"","isOffenderSpecific":"N"},{"KEY_DESC":"Offender Dashboard","KEY_CODE":"OFDSHB","VALUE":"","isOffenderSpecific":"Y"}]'
where
	setting_type = 'PAGE_CONFIG'
	and setting_provider_code = 'PAGE_CONFIG'; 