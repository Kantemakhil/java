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
SELECT 'OCDLEGLS',
'Legal Summary',
'113',
'Update Custody Status',
'net.syscon.s4.common.beans.OdynfrmSubmitDataBean',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OCDLEGLS' AND dto_name = 'net.syscon.s4.common.beans.OdynfrmSubmitDataBean' 
                                                   AND module_description = 'Legal Summary' AND trigger_name = 'Update Custody Status' );
												   
												   
 insert
	into
	MODULE_TRIGGERS(MODULE_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	DTO_NAME,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
SELECT 'OIDIEPLV',
'Incentives and Earned privileges Level',
'115',
'net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OIDIEPLV' AND dto_name = 'net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean' 
                                                   AND module_description = 'Incentives and Earned privileges Level');
												   
insert
	into
	MODULE_TRIGGERS(MODULE_NAME,
	MODULE_DESCRIPTION,
	TRIGGER_ID,
	DTO_NAME,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
SELECT 'OIDIEPLV',
'Incentives and Earned privileges Level',
'115',
'net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OIDIEPLV' AND dto_name = 'net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean' 
                                                   AND module_description = 'Incentives and Earned privileges Level');