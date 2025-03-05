INSERT INTO module_triggers
(module_name, dto_name, create_datetime, create_user_id, modify_datetime, modify_user_id, trigger_name, module_description, trigger_id, seal_flag)
SELECT 'OIDOICUS', 'net.syscon.s4.im.beans.VOicIncidents', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', 'Offenses In Custody', 'Offenses In Custody', '105', NULL
WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OIDOICUS' AND dto_name = 'net.syscon.s4.im.beans.VOicIncidents' AND module_description = 'Offenses In Custody' AND trigger_name = 'Offenses In Custody' );


INSERT INTO module_triggers
(module_name, dto_name, create_datetime, create_user_id, modify_datetime, modify_user_id, trigger_name, module_description, trigger_id, seal_flag)
SELECT 'OCUOICCH', 'net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', 'Offenses In Custody Charges', 'Offenses In Custody Charges', '106', NULL 
WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OCUOICCH' AND dto_name = 'net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges' AND module_description = 'Offenses In Custody Charges' AND trigger_name = 'Offenses In Custody Charges' );



INSERT INTO module_triggers
(module_name, dto_name, create_datetime, create_user_id, modify_datetime, modify_user_id, trigger_name, module_description, trigger_id, seal_flag)
SELECT 'OCUOICHN', 'net.syscon.s4.im.incidentsoic.beans.OicHearings', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER','Schedule Hearing','Offenses In Custody Hearing', '107', NULL 
WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OCUOICHN' AND dto_name = 'net.syscon.s4.im.incidentsoic.beans.OicHearings' AND module_description = 'Offenses In Custody Hearing' AND trigger_name = 'Schedule Hearing' );



INSERT INTO module_triggers
(module_name, dto_name, create_datetime, create_user_id, modify_datetime, modify_user_id, trigger_name, module_description, trigger_id, seal_flag)
SELECT 'OCUOICIN', 'net.syscon.s4.im.beans.AgyIncInvestigations', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER','Investigation','Offenses In Custody Investigation', '108', NULL
WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OCUOICIN' AND dto_name = 'net.syscon.s4.im.beans.AgyIncInvestigations' AND module_description = 'Offenses In Custody Investigation' AND trigger_name = 'Investigation' );

INSERT INTO module_triggers
(module_name, dto_name, create_datetime, create_user_id, modify_datetime, modify_user_id, trigger_name, module_description, trigger_id, seal_flag)
SELECT 'OCUOICHE', 'net.syscon.s4.im.incidentsoic.beans.OicHearings', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER','Hearings','Offenses In Custody Hearing and Penalties', '109', NULL
WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OCUOICHE' AND dto_name = 'net.syscon.s4.im.incidentsoic.beans.OicHearings' AND module_description = 'Offenses In Custody Hearing and Penalties' AND trigger_name = 'Hearings' );



INSERT INTO module_triggers
(module_name, dto_name, create_datetime, create_user_id, modify_datetime, modify_user_id, trigger_name, module_description, trigger_id, seal_flag)
SELECT 'OCUOICAW', 'net.syscon.s4.common.beans.OffenderOicSanctions', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER','Disciplinary Measures','Offenses In Custody Penalties', '110', NULL
WHERE NOT EXISTS (SELECT 1 FROM module_triggers WHERE module_name = 'OCUOICAW' AND dto_name = 'net.syscon.s4.common.beans.OffenderOicSanctions' AND module_description = 'Offenses In Custody Penalties' AND trigger_name = 'Disciplinary Measures' );