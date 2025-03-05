INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDER_BOOK_ID', 'C', 'Offender Book Id', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '133', 'offenderBookId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='OFFENDER_BOOK_ID');

INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'PLACEMENT_SEQ', 'C', 'Placement Seq', 'OFF_CARE_PLC', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '133', 'placementSeq'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'OFF_CARE_PLC' and parameter_name='PLACEMENT_SEQ');
