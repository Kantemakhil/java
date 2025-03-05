INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'GRIEVANCE_ID', 'C', 'Grievance Id', 'REQ_GRIEVANC', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER',NULL, NULL, 'Y', NULL, '50', 'grievanceId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'REQ_GRIEVANC' and parameter_name='GRIEVANCE_ID');


INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'GRIEVANCE_ID', 'C', 'Grievance Id', 'REQ_GRI_STAF', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER',NULL, NULL, 'Y', NULL, '50', 'grievanceId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'REQ_GRI_STAF' and parameter_name='GRIEVANCE_ID');

INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'GRIEVANCE_ID', 'C', 'Grievance Id', 'REQ_GRI_TRNS', current_timestamp, 'OMS_OWNER', 'N',current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '50', 'grievanceId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'REQ_GRI_TRNS' and parameter_name='GRIEVANCE_ID');

INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OIC_INCIDENT_ID', 'C', 'Incident Id', 'OFF_IN_CSTDY', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER',NULL, NULL, 'Y', NULL, '105', 'oicIncidentId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='OIC_INCIDENT_ID');