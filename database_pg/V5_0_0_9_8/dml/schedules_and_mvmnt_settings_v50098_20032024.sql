insert
	into
	oms_owner.schedules_and_mvmnt_settings
(setting_code,
	setting_description,
	setting_value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
values ('TAP_SCH_CONFLICT',
'TAP Statuses That Initiate Conflict Check',
'SCH' ,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
'OMS_OWNER');