OUMSYSET_EXECUTE_QUERY {
	SELECT SETTING_TYPE, SETTING_PROVIDER_CODE, SETTING_VALUE SETTING_VALUE_BLOB, MODIFY_USER_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME FROM SYSTEM_SETTINGS
}

OUMSYSET_UPDATE_SYSSETTING_DATA {
	UPDATE SYSTEM_SETTINGS SET SETTING_TYPE = :settingType, SETTING_PROVIDER_CODE = :settingProviderCode, SETTING_VALUE = :settingValueBlob, SELECTED_PROVIDER = :selectedProvider, MODIFY_USER_ID = :modifyUserId, MODIFY_DATETIME = current_timestamp WHERE SETTING_TYPE = :settingType AND SETTING_PROVIDER_CODE = :settingProviderCode 
}

OUMSYSET_POST_UPDATE {
	update system_settings set selected_provider = 'N',  MODIFY_USER_ID = :modifyUserId, MODIFY_DATETIME = current_timestamp where setting_type = :settingType and setting_provider_code <> :settingProviderCode
}

OUMSYSET_GET_SELECTED_PROVIDER {
	select setting_type , setting_provider_code  from system_settings ss where setting_type  = :settingType and selected_provider = 'Y'
}

OUMSYSET_GET_PHONES_DATA{
select distinct format from phones
}