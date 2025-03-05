INSERT INTO legal_settings (code, description, value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 SELECT 'EMRE', 'Enable Manual Remission Eligibilty', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM legal_settings WHERE code = 'EMRE');

INSERT INTO legal_settings (code, description, value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 SELECT 'EAOSU', 'Enable Automatic Order Status Update', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM legal_settings WHERE code = 'EAOSU');

INSERT INTO legal_settings (code, description, value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 SELECT 'EACSU', 'Enable Automatic Condition Status Update', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM legal_settings WHERE code = 'EACSU');

INSERT INTO legal_settings (code, description, value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 SELECT  'EASUIOLPO', 'Enable Automatic Status Update for Imprisonment Orders Linked to Parole Orders', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM legal_settings WHERE code = 'EASUIOLPO');

