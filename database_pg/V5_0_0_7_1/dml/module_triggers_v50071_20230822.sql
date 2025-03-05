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
	'OSIOSEAR',
	'Global Name Search',
	'117',
	'Offender Search',
	'net.syscon.s4.im.beans.TagSearchGetOffenderRecords',
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
		module_name = 'OSIOSEAR'
		and dto_name = 'net.syscon.s4.im.beans.TagSearchGetOffenderRecords'
		and
 module_description = 'Global Name Search'
		and trigger_name = 'Offender Search'
		and TRIGGER_ID = '117');

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
	'OSIPSEAR',
	'Person Search',
	'125',
	'Search Results',
	'net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPersons',
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
		module_name = 'OSIPSEAR'
		and dto_name = 'net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPersons'
		and
 module_description = 'Person Search'
		and trigger_name = 'Search Results'
		and TRIGGER_ID = '125');

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
SELECT 'OCDUATTE',
'Unpaid Work Programme Attendance',
'127',
'Offender',
'net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OCDUATTE' AND dto_name = 'net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents' 
                                                   AND module_description = 'Unpaid Work Programme Attendance' AND trigger_name = 'Offender' And TRIGGER_ID = '127');
												   
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
	'OSIOSEAR',
	'Header',
	'124',
	'Offender Search',
	'net.syscon.s4.common.beans.VHeaderBlock',
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
		module_name = 'OSIOSEAR'
		and dto_name = 'net.syscon.s4.common.beans.VHeaderBlock'
		and module_description = 'Header'
		and trigger_name = 'Offender Search'
		and TRIGGER_ID = '124');												   