delete from iwp_bookmark_parameters where bookmark_name ='SCHEDULES';
   
 INSERT INTO iwp_bookmark_parameters
(parameter_name, parameter_type, description, bookmark_name, date_created, user_created, parameter_data_type, create_datetime, create_user_id, modify_datetime, modify_user_id, active_flag, seal_flag, module_block_code, field)
SELECT 'EVENT_ID', 'C', 'EVENT_ID', 'SCHEDULES', current_timestamp, 'oms_owner', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, 'Y', NULL, '128', 'eventId'
WHERE NOT EXISTS (SELECT 1 FROM iwp_bookmark_parameters WHERE bookmark_name = 'SCHEDULES' and parameter_name='EVENT_ID');