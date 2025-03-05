insert into MODULE_TRIGGERS(MODULE_NAME,MODULE_DESCRIPTION,TRIGGER_ID,TRIGGER_NAME,DTO_NAME,CREATE_DATETIME, CREATE_USER_ID,SEAL_FLAG)
SELECT 'OIDCNOTE','Institution Contact Logs','126','Case Notes','net.syscon.s4.im.beans.OffenderCaseNotes',current_timestamp,'OMS_OWNER',null
 WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OIDCNOTE' AND dto_name = 'net.syscon.s4.im.beans.OffenderCaseNotes'
                                                   AND module_description = 'Institution Contact Logs' AND trigger_name = 'Case Notes' And TRIGGER_ID = '126'); 