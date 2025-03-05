INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'AGENCY_INCIDENT_ID', 'C', 'AGENCY_INCIDENT_ID', 'OFF_IN_CSTDY', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '105', 'agencyIncidentId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_IN_CSTDY' and parameter_name='AGENCY_INCIDENT_ID');
	
INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'CHARGE_SEQ', 'C', 'Charge Sequence', 'OFF_IN_CHARG', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '106', 'chargeSeq'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_IN_CHARG' and parameter_name='CHARGE_SEQ');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'AGENCY_INCIDENT_ID', 'C', 'AgencyIncidentId', 'OFF_IN_CHARG', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '106', 'agencyIncidentId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_IN_CHARG' and parameter_name='AGENCY_INCIDENT_ID');
	
INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OIC_HEARING_ID', 'C', 'Offence Hearing Id', 'OFF_SCH_HEAR', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '107', 'oicHearingId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_SCH_HEAR' and parameter_name='OIC_HEARING_ID');
	
INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OIC_HEARING_ID', 'C', 'Offense Hearing Id', 'OFF_SH_NOTIF', current_timestamp, 'OMS_OWNER', 'N',current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '107', 'oicHearingId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_SH_NOTIF' and parameter_name='OIC_HEARING_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'AGY_INC_INVESTIGATION_ID', 'C', 'Agency Investigation Id', 'OFF_CUS_INVE', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '108', 'agyIncInvestigationId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_CUS_INVE' and parameter_name='AGY_INC_INVESTIGATION_ID');
	
	
INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'AGY_INC_INVESTIGATION_ID', 'C', 'Agency Investigation Id', 'OFF_CUS_EVID', current_timestamp, 'OMS_OWNER', 'N',current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '108', 'agyIncInvestigationId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_CUS_EVID' and parameter_name='AGY_INC_INVESTIGATION_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OIC_HEARING_ID', 'C', 'Custody Hearing Id', 'OFF_IN_H&P', current_timestamp, 'OMS_OWNER', 'N',current_timestamp, 'OMS_OWNER',NULL, NULL, 'Y', NULL, '109', 'oicHearingId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_IN_H&P' and parameter_name='OIC_HEARING_ID');


INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OIC_HEARING_ID', 'C', 'Custody Hearing Id', 'OFF_IN_H_RES',current_timestamp, 'OMS_OWNER', 'N',current_timestamp, 'OMS_OWNER',NULL, NULL, 'Y', NULL, '109', 'oicHearingId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_IN_H_RES' and parameter_name='OIC_HEARING_ID');
	
INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDER_BOOK_ID', 'C', 'offender Book Id', 'OFF_IN_C_PEN', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER',NULL, NULL, 'Y', NULL, '110', 'offenderBookId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='OFFENDER_BOOK_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'SANCTION_SEQ', 'C', 'Sanction Sequence', 'OFF_IN_C_PEN',current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER',NULL, NULL, 'Y', NULL, '110', 'sanctionSeq'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_IN_C_PEN' and parameter_name='SANCTION_SEQ');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDER_SAMPLE_ID', 'C', 'Offender Sample Id', 'CMG_ST_SAMPL', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '30', 'offenderSampleId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CMG_ST_SAMPL' and parameter_name='OFFENDER_SAMPLE_ID');


INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDER_SAMPLE_ID', 'C', 'Offender Sample Id', 'CMG_ST_TESTE',current_timestamp, 'oms_owner', 'N', current_timestamp, 'OMS_OWNER',NULL, NULL, 'Y', NULL, '31', 'offenderSampleId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CMG_ST_TESTE' and parameter_name='OFFENDER_SAMPLE_ID');