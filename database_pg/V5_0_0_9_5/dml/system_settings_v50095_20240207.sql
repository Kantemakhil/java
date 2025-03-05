insert
	into
	SYSTEM_SETTINGS(SETTING_TYPE,
	SETTING_PROVIDER_CODE,
	SETTING_VALUE,
	CREATE_DATETIME,
	CREATE_USER_ID)
select
	'VIEW_AUDIT_LOG',
	'VIEW_AUDIT_LOG',
	'[ { "KEY_DESC": "View Audit Server Url", "KEY_CODE": "VIEW_AUDIT_SERVER_URL", "VALUE": "" }, { "KEY_DESC": "Username", "KEY_CODE": "USERNAME", "VALUE": "" }, { "KEY_DESC": "Password", "KEY_CODE": "PASSWORD", "VALUE": "" }, { "KEY_DESC": "View Audit Exchange", "KEY_CODE": "VIEW_AUDIT_EXCHANGE", "VALUE": "" }, { "KEY_DESC": "View Audit Routing Key", "KEY_CODE": "VIEW_AUDIT_ROUTING_KEY", "VALUE": "" }, { "KEY_DESC": "View Audit Queue", "KEY_CODE": "VIEW_AUDIT_QUEUE", "VALUE": "" } ]',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		SYSTEM_SETTINGS
	where
		SETTING_TYPE = 'VIEW_AUDIT_LOG'
		and SETTING_PROVIDER_CODE = 'VIEW_AUDIT_LOG')
