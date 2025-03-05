SENDMAIL_GET_CLICKSEND_CONF_DET {
	SELECT PROFILE_CODE, PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE IN('MAIL_HOST', 'MAIL_PORT', 'MAIL_USER', 'MAIL_PWD', 'MAIL_FROM')
}

SENDMAIL_GET_MAIL_TYPE {
	select setting_provider_code from system_settings ss where setting_type  = 'eMail' and selected_provider = 'Y'
}