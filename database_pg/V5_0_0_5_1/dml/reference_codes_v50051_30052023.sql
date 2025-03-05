INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CRT_CAN_RSN', 'ERROR', 'Entered in Error', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CRT_CAN_RSN' and code = 'ERROR');
  
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CRT_CAN_RSN', 'CRT_CAN', 'Cancelled by Court', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CRT_CAN_RSN' and code = 'CRT_CAN');


update reference_codes set parent_code ='INT',modify_user_id ='OMS_OWNER',modify_datetime=current_timestamp where code ='INT' and domain='CRT_APP_TYPE';

update reference_codes set parent_code ='EXT',modify_user_id ='OMS_OWNER',modify_datetime =current_timestamp where code ='EXT' and domain='CRT_APP_TYPE';

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'PS_PAY', 'Y', 'Yes', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'PS_PAY' and code = 'Y');

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'PS_PAY', 'N', 'No', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'PS_PAY' and code = 'N');
  
update reference_codes set parent_code  = 'PSPAY',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where domain = 'UNIT' and CODE = 'HRS';  

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CONT_METH', 'SMS', 'SMS', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CONT_METH' and code = 'SMS');
  
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CONT_METH', 'EMAIL', 'Email', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CONT_METH' and code = 'EMAIL');
 
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CONT_METH', 'PHONE', 'Phone', 3, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CONT_METH' and code = 'PHONE');
 
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CONT_METH', 'IN_PERSON', 'In-Person', 4, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CONT_METH' and code = 'IN_PERSON');
  
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CONT_METH', 'MAIL', 'Mail', 5, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CONT_METH' and code = 'MAIL');

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'VIC_CONT_CAT', 'VIC_NOTIF', 'Victim Notification', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'VIC_CONT_CAT' and code = 'VIC_NOTIF');
  
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'VIC_CONT_CAT', 'VIC_REGIST', 'Victim Registration', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'VIC_CONT_CAT' and code = 'VIC_REGIST');

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'VIC_CON_TYPE', 'PAR_DEC', 'Parole Decision', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL,'VIC_NOTIF', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'VIC_CON_TYPE' and code = 'PAR_DEC');
  
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'VIC_CON_TYPE', 'COMM_EVENT', 'Community Event', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'VIC_NOTIF', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'VIC_CON_TYPE' and code = 'COMM_EVENT');
  
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'VIC_CON_TYPE', 'PEND', 'Pending', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL,'VIC_REGIST', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'VIC_CON_TYPE' and code = 'PEND');
  
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'VIC_CON_TYPE', 'COMP', 'Completed', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'VIC_REGIST', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'VIC_CON_TYPE' and code = 'COMP');
  
update reference_codes set parent_code  = 'PAY',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where domain = 'PS_PAY' and CODE = 'Y';  



