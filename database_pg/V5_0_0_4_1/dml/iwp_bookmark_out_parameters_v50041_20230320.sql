INSERT INTO oms_owner.iwp_bookmark_out_parameters
(bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('OFFEN_ALERT', 'EXPIRY_DATE1', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL);
INSERT INTO oms_owner.iwp_bookmark_out_parameters
(bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('OFFEN_ALERT', 'EXPIRY_DATE2', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL);
INSERT INTO oms_owner.iwp_bookmark_out_parameters
(bookmark_name, parameter_name, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('OFFEN_ALERT', 'EXPIRY_DATE3', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL);

delete from iwp_bookmark_out_parameters where bookmark_name='OFFEN_ALERT' and parameter_name ='EXPIRY_DATE';