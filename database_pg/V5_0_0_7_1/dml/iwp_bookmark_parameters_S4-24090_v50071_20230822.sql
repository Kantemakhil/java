INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
select 'EVENT_ID', 'U', 'EVENT_ID ', 'CRT_ESDATE', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, NULL, null
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_ESDATE' and parameter_name='EVENT_ID');

INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
select 'EVENT_ID', 'U', 'event_id ', 'CRT_EDATE', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, NULL, null
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_EDATE' and parameter_name='EVENT_ID');

INSERT INTO oms_owner.iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
select 'EVENT_ID ', 'U', 'EVENT_ID ', 'CRT_NAME', current_timestamp, 'OMS_OWNER', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'CRT_NAME' and parameter_name='EVENT_ID ');