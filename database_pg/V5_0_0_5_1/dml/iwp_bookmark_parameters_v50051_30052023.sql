INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'DISPLAYNO', 'C', 'Custodial No', 'LEG_CUST', current_timestamp, 'OMS_OWNER', 'T', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', 'Y', NULL, '61', 'displayNo'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='DISPLAYNO');



INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDERBOOKID', 'C', 'offender Book Id ', 'LEG_CUST', current_timestamp, 'OMS_OWNER', 'T', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', 'Y', NULL, '61', 'offenderBookId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'LEG_CUST' and parameter_name='OFFENDERBOOKID');


INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'DISPLAYNO', 'C', 'Custodial No', 'LEG_CUS_CHRG', current_timestamp, 'OMS_OWNER', 'T', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', 'Y', NULL, '61', 'displayNo'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'LEG_CUS_CHRG' and parameter_name='DISPLAYNO');



INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDERBOOKID', 'C', 'offender Book Id ', 'LEG_CUS_CHRG', current_timestamp, 'OMS_OWNER', 'T', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', 'Y', NULL, '61', 'offenderBookId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'LEG_CUS_CHRG' and parameter_name='OFFENDERBOOKID');


INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDERBOOKID', 'C', 'Offender Book Id', 'LEG_CHRG_DET', current_timestamp, 'OMS_OWNER', 'T',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', 'Y', NULL, '61', 'offenderBookId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'LEG_CHRG_DET' and parameter_name='OFFENDERBOOKID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'CHARGEID', 'C', 'Charge Id', 'LEG_CHRG_DET', current_timestamp, 'OMS_OWNER', 'T', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', 'Y', NULL, '61', 'chargeId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'LEG_CHRG_DET' and parameter_name='CHARGEID');


INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENCE_ID', 'C', 'Offence Id', 'LEG_CHRG_IND', current_timestamp, 'OMS_OWNER', 'N',current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', 'Y', NULL, '61', 'offenceId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'LEG_CHRG_IND' and parameter_name='OFFENCE_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'CODE', 'C', 'Charge Code', 'LEG_CHRG_CAT',current_timestamp, 'OMS_OWNER', 'T', current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', 'Y', NULL, '61', 'code'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'LEG_CHRG_CAT' and parameter_name='CODE');