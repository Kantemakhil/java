INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'ORDER_ID', 'C', 'Court Order Id', 'LEG_CRT_REPT',current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '46', 'orderId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'LEG_CRT_REPT' and parameter_name='ORDER_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
 SELECT 'RECORD_ID', 'C', 'Court Order Id', 'CRT_INT_PART', current_timestamp, 'OMS_OWNER', 'T', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '46', 'orderId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_INT_PART' and parameter_name='RECORD_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDER_BOOK_ID', 'C', 'OffenderBookId', 'CRT_INT_PART', current_timestamp, 'OMS_OWNER', 'T', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '46', 'offenderBookId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_INT_PART' and parameter_name='OFFENDER_BOOK_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDER_BOOK_ID', 'C', 'Offender Book Id', 'CRT_RPT_CHRG', current_timestamp, 'OMS_OWNER', 'T', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '46', 'offenderBookId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_RPT_CHRG' and parameter_name='OFFENDER_BOOK_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'ORDER_ID', 'C', 'ORDER_ID', 'CRT_RPT_CHRG', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '46', 'orderId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_RPT_CHRG' and parameter_name='ORDER_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'ORDER_ID', 'C', 'Court Order Id', 'CRT_REP_PROP', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '46', 'orderId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_REP_PROP' and parameter_name='ORDER_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'ORDER_ID', 'C', 'Court Order Id', 'CRT_RPT_COND', current_timestamp, 'OMS_OWNER', 'N',current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '46', 'orderId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_RPT_COND' and parameter_name='ORDER_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'DISPLAYNO', 'C', 'Display No', 'CRT_ACT_ORDR',current_timestamp, 'OMS_OWNER', 'T',current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '61', 'displayNo'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='DISPLAYNO');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDERBOOKID', 'C', 'OffenderBookId', 'CRT_ACT_ORDR',current_timestamp, 'OMS_OWNER', 'T',current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '61', 'offenderBookId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='OFFENDERBOOKID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'ORDERTYPE', 'C', 'Order Type ', 'CRT_ACT_ORDR', current_timestamp, 'OMS_OWNER', 'T',current_timestamp, 'OMS_OWNER', NULL,NULL, 'Y', NULL, '61', 'orderCategory'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_ACT_ORDR' and parameter_name='ORDERTYPE');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'OFFENDER_BOOK_ID', 'C', 'Offender Book Id', 'CRT_ACTIONS',current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '78', 'offenderBookId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='OFFENDER_BOOK_ID');

INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'ORDER_TYPE', 'C', 'Order Type', 'CRT_ACTIONS', current_timestamp, 'OMS_OWNER', 'T', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '78', 'orderType'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_ACTIONS' and parameter_name='ORDER_TYPE');