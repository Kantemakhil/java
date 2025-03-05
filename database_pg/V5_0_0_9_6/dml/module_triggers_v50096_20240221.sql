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
	'OSANVIOS',
	'Sanctions and Violations',
	'135',
	'Court Events',
	'net.syscon.s4.inst.schedules.bean.CourtEvents',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		module_triggers
	where
		module_name = 'OSANVIOS'
		and dto_name = 'net.syscon.s4.inst.schedules.bean.CourtEvents'
		and module_description = 'Sanctions and Violations'
		and trigger_name = 'Court Events'
		and TRIGGER_ID = '135');