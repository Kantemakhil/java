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
select
	'OCIPENSC',
	'Pending Sentence Calculation Events',
	'SCREEN',
	null,
	null,
	null,
	'OMS4',
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	null
where
	not exists (
	select
		1
	from
		OMS_OWNER.OMS_MODULES
	where
		MODULE_NAME = 'OCIPENSC');
		
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
select
	'OUISYSST',
	'System Status',
	'SCREEN',
	null,
	null,
	null,
	'OMS4',
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	null
where
	not exists (
	select
		1
	from
		OMS_OWNER.OMS_MODULES
	where
		MODULE_NAME = 'OUISYSST');
		
update
	OMS_MODULES
set
	USER_TASK = 'Y',
	modify_datetime = current_timestamp,
	modify_user_id = 'oms_owner'
where
	MODULE_TYPE = 'SCREEN'
	and MODULE_NAME = 'OIDCUSTAD';		