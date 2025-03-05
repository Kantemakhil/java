INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'FUNCTION', 'PRISONER PAY', 'Prisoner Pay Team', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, null, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'FUNCTION' and code = 'PRISONER PAY');


INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'A_STAFF', 'Assault on Staff ', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'BOTH', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'A_STAFF');
  
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'INJ', 'Injury ', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'BOTH', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'INJ');
  
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'DISCOND', 'Disorderly Conduct', 3, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'BOTH', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'DISCOND');

 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'BEH', 'Behavioral Issue', 4, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'BOTH', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'BEH');

 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'DIST', 'Disturbance', 5, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'BOTH', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'DIST');
  
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'A_OFF', 'Assault on Prisoner', 6, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'CUST', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'A_OFF');
  
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'TRAFF', 'Trafficking', 7, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'CUST', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'TRAFF');

 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'UA', 'Unauthorized Activity', 8, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'CUST', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'UA');

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'DRUG', 'Drug Related', 9, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'COMM', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'DRUG');

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'PROP', 'Property Crime', 10, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'COMM', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'PROP');

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'INC_TYPE', 'A_PUB', 'Assault on Public', 11, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'COMM', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'INC_TYPE' and code = 'A_PUB');