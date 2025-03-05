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
	'OIUREPIN',
	'Reportable Incident Details',
	'SCREEN',
	null,
	null,
	0,
	null,
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
		MODULE_NAME = 'OIUREPIN');





