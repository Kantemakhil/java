delete
from
	OMS_MODULES
where
	module_name = 'OCDIPLANDLG';

insert
	into
	OMS_MODULES (MODULE_NAME,
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
	OUTPUT_TYPE,
	PROCESS_WORKFLOW,
	USER_TASK,
	DYNAMIC_FORM,
	INS_DASHBOARD)
select
	'OCDIPLAC',
	'Assign Case Plan Staff',
	'SCREEN',
	null,
	null,
	null,
	'OMS4',
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		OMS_MODULES
	where
		MODULE_NAME = 'OCDIPLAC');

insert
	into
	OMS_MODULES (MODULE_NAME,
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
	SEAL_FLAG)
select
	'OIMCUSTS',
	'Maintain Custody Statuses',
	'SCREEN',
	null,
	null,
	null,
	'OMS4',
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		OMS_MODULES
	where
		module_name = 'OIMCUSTS');

insert
	into
	oms_modules (module_name,
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
 select
	'OCUDCOND',
	'Delete Conditions',
	'SCREEN',
	null,
	null,
	null,
	'INST',
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER' ,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		oms_modules
	where
		module_name = 'OCUDCOND');

insert
	into
	oms_modules (module_name,
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
 select
	'OCUCHGSE',
	'Related Orders',
	'SCREEN',
	null,
	null,
	null,
	'OMS4',
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER' ,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		oms_modules
	where
		module_name = 'OCUCHGSE');
