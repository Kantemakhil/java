OIMRELSC_GET_RELAESE_SCHEDULE_SETTING_KEY_DATE {
SELECT rel_sch_setting_type,rel_sch_setting_value rel_sch_setting_value_blob,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag FROM release_schedule_settings
}

OIMRELSC_GET_KEY_DATE_COUNT {
select count(*) from release_schedule_settings where rel_sch_setting_type='KEY_DATE'
}

OIMRELSC_UPDATE_RELEASE_SCHEDULE_SETTING_KEY_DATE {
update  release_schedule_settings set rel_sch_setting_value=:relSchSettingValueBlob, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID = :modifyUserId WHERE rel_sch_setting_type='KEY_DATE'
}

OIMRELSC_INSERT_RELEASE_SCHEDULE_SETTING_KEY_DATE {
insert into release_schedule_settings(rel_sch_setting_type,rel_sch_setting_value,CREATE_DATETIME, CREATE_USER_ID, MODIFY_USER_ID, MODIFY_DATETIME
) values(:relSchSettingType,:relSchSettingValueBlob,current_timestamp,:createUserId, null, null)
}
OIMRELSC_GET_RELAESE_SCHEDULE_SETTING_ALERTS_DATA{
SELECT rel_sch_setting_type,rel_sch_setting_value rel_sch_setting_value_blob,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag FROM release_schedule_settings where rel_sch_setting_type=:relSchSettingType
}
OIMRELSC_GET_ALERTS_DATA_COUNT {
select count(*) from release_schedule_settings where rel_sch_setting_type='ALERTS'
}

OIMRELSC_UPDATE_RELEASE_SCHEDULE_SETTING_ALERTS_DATA {
update  release_schedule_settings set rel_sch_setting_value=:relSchSettingValueBlob, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID = :modifyUserId WHERE rel_sch_setting_type='ALERTS'
}

OIMRELSC_INSERT_RELEASE_SCHEDULE_SETTING_KEY_DATE {
insert into release_schedule_settings(rel_sch_setting_type,rel_sch_setting_value,CREATE_DATETIME, CREATE_USER_ID) values(:relSchSettingType,:relSchSettingValueBlob,current_timestamp,:createUserId)
}

OIMRELSC_UPDATE_RELEASE_SCHEDULE_SETTING_CHARGES_DATA {
update  release_schedule_settings set rel_sch_setting_value=:relSchSettingValueBlob, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID = :modifyUserId WHERE rel_sch_setting_type='CHARGE_IND'
}
OIMRELSC_GET_CHARGE_INDICATOR_DATA_COUNT {
select count(*) from release_schedule_settings where rel_sch_setting_type='CHARGE_IND'
}