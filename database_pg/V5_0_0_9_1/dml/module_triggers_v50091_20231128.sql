insert into MODULE_TRIGGERS(MODULE_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	TRIGGER_NAME,
	DTO_NAME,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
select 'OCUOICAW',
	'Offense in Custody Penalties',
	'129',
	null,
	'net.syscon.s4.common.beans.OffenderOicSanctions',
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
		module_name = 'OCUOICAW'
		and dto_name = 'net.syscon.s4.common.beans.OffenderOicSanctions'
		and module_description = 'Offense in Custody Penalties'
		and trigger_name = null
		and TRIGGER_ID = '129');
