OIMPROST_EXECUTE_QUERY{
select setting_code,
	setting_description,
	setting_value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag  from property_settings where setting_code = :settingCode
}

OIMPROST_UPDATE_PROPERTY_SETTING_DATA {
	UPDATE property_settings SET SETTING_DESCRIPTION = :settingDescription, SETTING_VALUE = :settingValue, MODIFY_USER_ID = :modifyUserId, MODIFY_DATETIME = current_timestamp WHERE SETTING_CODE = :settingCode
}