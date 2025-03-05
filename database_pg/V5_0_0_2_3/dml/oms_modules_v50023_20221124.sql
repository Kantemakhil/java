insert
	into
	OMS_OWNER.OMS_MODULES(MODULE_NAME,
	DESCRIPTION,
	MODULE_TYPE,
	PRINT_FORMAT_CODE,
	PREVIEW_FLAG,
	DEFAULT_COPY,
	APPLN_CODE,
	HELP_DIRECTORY,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG,
	OUTPUT_TYPE)
values('OCDBAILO',
'Bail Orders',
'SCREEN',
NULL,
NULL,
0,
'INST',
NULL,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL,
NULL);

update
	oms_owner.oms_modules
set  modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,description = 'Custodial Orders'
where
	module_name = 'OCDLEGLO';

update
	oms_modules
set  modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,description = 'Maintain Module Tables Association'
where
	module_name = 'OUMTAGRE';

insert
	into
	oms_owner.oms_modules (module_name,
	description,
	module_type,
	print_format_code,
	preview_flag,
	default_copy,
	appln_code,
	help_directory,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	output_type,
	process_workflow,
	user_task,
	dynamic_form,
	ins_dashboard)
values('OIMIEPLV',
'Incentives & Earned Privileges',
'SCREEN',
null,
null,
null,
'OMS4',
null,
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null,
null,
null,
null,
null,
null);

insert
	into
	oms_owner.oms_modules (module_name,
	description,
	module_type,
	print_format_code,
	preview_flag,
	default_copy,
	appln_code,
	help_directory,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	output_type,
	process_workflow,
	user_task,
	dynamic_form,
	ins_dashboard)
values('OIDIEPLV',
'Incentives & Earned Privileges Level',
'SCREEN',
null,
null,
null,
'OMS4',
null,
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null,
null,
null,
null,
null,
null);