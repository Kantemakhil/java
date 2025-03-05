INSERT INTO oms_owner.system_profiles (profile_type, profile_code, description, profile_value, profile_value_2, modify_user_id, old_table_name, create_datetime, create_user_id, modify_datetime, seal_flag)
 SELECT 'CLIENT', 'WHITELIS_CLS', 'It Defines a list of classes that are allowed in Jasper Report expressions', NULL, NULL, 'OMS_OWNER', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
WHERE NOT EXISTS (SELECT 1 FROM oms_owner.system_profiles WHERE profile_type = 'CLIENT' AND profile_code = 'WHITELIS_CLS'); 

--empty string
UPDATE oms_owner.system_profiles 
   SET profile_value = '' 
 WHERE profile_type = 'CLIENT' AND profile_code = 'WHITELIS_CLS';
