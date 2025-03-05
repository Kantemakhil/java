INSERT INTO MODULE_TRIGGERS(MODULE_NAME, DTO_NAME, MODULE_DESCRIPTION, TRIGGER_NAME, TRIGGER_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME) 
VALUES('OIDRELSC', 'net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails', 'Release Schedules', 'Offender Details', '100', current_timestamp, 'OMS_OWNER', NULL);
INSERT INTO MODULE_TRIGGERS(MODULE_NAME, DTO_NAME, MODULE_DESCRIPTION, TRIGGER_NAME, TRIGGER_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME) 
VALUES('OCUACOND', 'net.syscon.s4.inst.legals.beans.OffenderSentConditions', 'Add Conditions', 'Conditions', '101', current_timestamp, 'OMS_OWNER', NULL);
INSERT INTO oms_owner.module_triggers
(module_name, dto_name, create_datetime, create_user_id, modify_datetime, modify_user_id, trigger_name, module_description, trigger_id, seal_flag)
VALUES('OTKCONDTRF', 'net.syscon.s4.inst.legals.beans.OffenderCondTransfer',current_timestamp,'OMS_OWNER', NULL, NULL, 'Conditions', 'Track Conditions Transfer', '87', NULL);
