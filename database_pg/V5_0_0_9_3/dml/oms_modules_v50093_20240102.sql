update
	oms_modules
set
	user_task = 'Y',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	module_name = 'OIDMPITM';