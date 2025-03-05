insert
	into
	MODULE_TRIGGERS(MODULE_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	TRIGGER_NAME,
	DTO_NAME,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
select
	'OIDSTABS',
	'Schedule Temporary Absence',
	'130',
	null,
	'net.syscon.s4.inst.schedules.bean.OffenderIndSchedules',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		module_triggers
	where
		module_name = 'OIDSTABS'
		and dto_name = 'net.syscon.s4.inst.schedules.bean.OffenderIndSchedules'
		and module_description = 'Schedule Temporary Absence'
		and trigger_name = null
		and TRIGGER_ID = '130');

insert
	into
	MODULE_TRIGGERS(MODULE_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	TRIGGER_NAME,
	DTO_NAME,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
select
	'EDITDOC',
	'Generate Document',
	'131',
	null,
	'net.syscon.s4.im.beans.IwpDocuments',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		module_triggers
	where
		module_name = 'EDITDOC'
		and dto_name = 'net.syscon.s4.im.beans.IwpDocuments'
		and module_description = 'Generate Document'
		and trigger_name = null
		and TRIGGER_ID = '131');

insert
	into
	MODULE_TRIGGERS(MODULE_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	TRIGGER_NAME,
	DTO_NAME,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
select
	'OCDCORDS',
	'Legals Update Notify',
	'132',
	null,
	'net.syscon.s4.common.beans.OdynfrmSubmitDataBean',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		*
	from
		module_triggers
	where
		module_name = 'OCDCORDS'
		and dto_name = 'net.syscon.s4.common.beans.OdynfrmSubmitDataBean'
		and module_description = 'Legals Update Notify'
		and trigger_name is null
		and TRIGGER_ID = '132');