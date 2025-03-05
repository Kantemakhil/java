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
SELECT 'OIDCIPON',
'Offender Care In Placement',
'133',
'net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null
 WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OIDCIPON' AND dto_name = 'net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails'
                                                   AND module_description = 'Offender Care In Placement' And TRIGGER_ID = '133');