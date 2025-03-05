UPDATE
	system_settings
SET
	setting_value = '[{"KEY_DESC":"Enable Addressify","KEY_CODE":"ENABLE_ADDRESSIFY","VALUE":"Y"},{"KEY_DESC":"Addressify Url","KEY_CODE":"ADDRSSIFY_URL","VALUE":"https://api.addressify.com.au/"},{"KEY_DESC":"Addressify APIKEY","KEY_CODE":"ADDRSSIFY_APIKEY","VALUE":"NjQ1YzMzZmYtYzYyMi00ZGEzLWJjMDctNzM3YzQ3YThlYWM2"},{"KEY_DESC":"Enable Latitude","KEY_CODE":"ENABLE_LATITUDE","VALUE":"Y"},{"KEY_DESC":"Enable Longitude","KEY_CODE":"ENABLE_LONGITUDE","VALUE":"Y"},{"KEY_DESC":"Enable Meshblock","KEY_CODE":"ENABLE_MESHBLOCK","VALUE":"Y"}]' ,
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
WHERE
	setting_type = 'AddressConfig'
	AND setting_provider_code = 'ADDRESSIFY_URL';