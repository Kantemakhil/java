update
	MENU_SECURITIES
set
	MODULE_NAME = 'OCDCORDS',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	MODULE_NAME = 'OCDLEGLO';
	
delete from OMS_MODULES where module_name ='OCDLEGLO';