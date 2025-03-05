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
SELECT 'OIDISTAT',
'Imprisonment Status',
'114',
'Offender Imprisonment Status',
'net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatuses',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OIDISTAT' AND dto_name = 'net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatuses' 
                                                   AND module_description = 'Imprisonment Status' AND trigger_name = 'Offender Imprisonment Status');