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
	'OIMIITPS',
	'Maintain Inmate Issue Type Permissions',
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
		MODULE_NAME = 'OIMIITPS');

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
	'OIMIITPS',
	'Maintain Inmate Issue Type Permissions',
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
		MODULE_NAME = 'OIMIITPS');
		
update
	OMS_MODULES
set
	USER_TASK = 'Y' ,
	modify_user_id = 'OMS_OWNER' ,
	modify_datetime = current_timestamp
where
	MODULE_TYPE = 'SCREEN'
	and MODULE_NAME = 'OIDINCDE';		