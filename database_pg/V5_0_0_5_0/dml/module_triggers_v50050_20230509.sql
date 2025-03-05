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
SELECT 'OIDCUSTAD',
'Custody Adjustments',
'111',
'Booking Adjustments',
'net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OIDCUSTAD' AND dto_name = 'net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment' 
                                                   AND module_description = 'Custody Adjustments' AND trigger_name = 'Booking Adjustments' ); 

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
 SELECT 'OIDCUSTAD',
'Custody Adjustments',
'112',
'Sentence Adjustments',
'net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
  WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OIDCUSTAD' AND dto_name = 'net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment' 
                                                   AND module_description = 'Custody Adjustments' AND trigger_name = 'Sentence Adjustments' ); 