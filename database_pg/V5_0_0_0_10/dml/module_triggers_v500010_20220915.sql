INSERT INTO oms_owner.module_triggers (module_name, dto_name, trigger_name, module_description, trigger_id, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('OIDITRAN', 'net.syscon.s4.common.beans.OffenderExternalMovements', NULL, 'Admitting Incoming Transfers', '69', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

update oms_owner.module_triggers set trigger_id='80' where module_name='OCDENFOR';

INSERT INTO oms_owner.module_triggers (module_name, dto_name, trigger_name, module_description, trigger_id, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('OIDSCEXM', 'net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules', NULL, 'Scheduled External Movements', '78', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO MODULE_TRIGGERS(MODULE_NAME, DTO_NAME, TRIGGER_NAME,MODULE_DESCRIPTION, TRIGGER_ID, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  VALUES('OIDSTFRP', 'net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport','StaffReport','StaffReport', '81', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
  
INSERT INTO MODULE_TRIGGERS(MODULE_NAME, DTO_NAME,TRIGGER_NAME, MODULE_DESCRIPTION, TRIGGER_ID, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  VALUES('OIDSTFRP', 'net.syscon.s4.im.incidentsoic.beans.StaffForce', 'UseOfForce', 'StaffReport','82', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
  
INSERT INTO MODULE_TRIGGERS(MODULE_NAME, DTO_NAME, TRIGGER_NAME,MODULE_DESCRIPTION, TRIGGER_ID, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)  
  VALUES('OIDSTFRP', 'net.syscon.s4.im.incidentsoic.beans.StaffEquipment','UseOfEquipment','StaffReport', '83', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
  
