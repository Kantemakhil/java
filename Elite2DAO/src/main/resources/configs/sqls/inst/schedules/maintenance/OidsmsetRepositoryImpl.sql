OIDSMSET_TEMP_ABSENCE_SETTING_UPDATE_QUERY{
update
	oms_owner.schedules_and_mvmnt_settings
set
	setting_value =:settingCodeValue ,modify_datetime = CURRENT_TIMESTAMP,modify_user_id = :modifyUserId where 	setting_code =:settingCode;
}

OIDSMSET_TEMP_ABSENCE_SETTING_DATA {
SELECT setting_code, setting_description, setting_value as settingCodeValue, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag
FROM oms_owner.schedules_and_mvmnt_settings


}