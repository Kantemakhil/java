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
SELECT 'OIDSTABS',
'Schedule Temporary Absence',
'128',
'Schedules',
'net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules',
current_timestamp,
'OMS_OWNER',
NULL,
NULL,
NULL 
 WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OIDSTABS' AND dto_name = 'net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules' 
                    AND module_description = 'Schedule Temporary Absence' AND trigger_name = 'Schedules' And TRIGGER_ID = '128');