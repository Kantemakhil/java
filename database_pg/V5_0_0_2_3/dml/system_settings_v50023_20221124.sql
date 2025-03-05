insert
	into
	OMS_OWNER.SYSTEM_SETTINGS(SETTING_TYPE,
	SETTING_PROVIDER_CODE,
	SETTING_VALUE,
	modify_user_id,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
values ('AddressConfig',
'ADDRESSIFY_URL',
'[{"KEY_DESC":"Enable Addressify","KEY_CODE":"ENABLE_ADDRESSIFY","VALUE":"Y"},{"KEY_DESC":"Addressify Url","KEY_CODE":"ADDRSSIFY_URL","VALUE":" "},{"KEY_DESC":"Enable Latitude","KEY_CODE":"ENABLE_LATITUDE","VALUE":"Y"},{"KEY_DESC":"Enable Longitude","KEY_CODE":"ENABLE_LONGITUDE","VALUE":"Y"},{"KEY_DESC":"Enable Meshblock","KEY_CODE":"ENABLE_MESHBLOCK","VALUE":"Y"}]',
null,
current_timestamp,
'OMS_OWNER',
current_timestamp,
null);