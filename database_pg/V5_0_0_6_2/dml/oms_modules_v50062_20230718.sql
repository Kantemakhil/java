update
	oms_modules
set
	description = 'Select Charges',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCUCHGSE';