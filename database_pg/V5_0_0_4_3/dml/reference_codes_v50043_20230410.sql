INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'WRKLD_TASK', 'DUTY', 'Duty', 1, 'Y', 'Y', NULL, NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'WRKLD_TASK' AND code = 'DUTY');

INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'WRKLD_TASK', 'MEETINGS', 'Meetings', 2, 'Y', 'Y', NULL, NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'WRKLD_TASK' AND code = 'MEETINGS');

INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'WRKLD_TASK', 'RP', 'RP', 3, 'Y', 'Y', NULL, NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'WRKLD_TASK' AND code = 'RP');

INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'WRKLD_TASK', 'READING', 'Reading', 4, 'Y', 'Y', NULL, NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'WRKLD_TASK' AND code = 'READING');

INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'WRKLD_TASK', 'TRAVEL', 'Travel', 5, 'Y', 'Y', NULL, NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'WRKLD_TASK' AND code = 'TRAVEL');

--

INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'EAOCSU' , 'YES' , 'YES' , 1, 'Y', 'Y', NULL, NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'EAOCSU' AND code = 'YES');

INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'EAOCSU' , 'NO' , 'NO' , 2, 'Y', 'Y', NULL, NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'EAOCSU' AND code = 'NO');

--

INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'KEY_DATES', 'BOOKING_CRD', 'Confirmed Release Date', 10, 'Y', 'N', NULL, NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_CRD');
 
--
 
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'ATTEND_VIEW', 'CONFIRMED', 'CONFIRMED', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'ATTEND_VIEW' and code = 'CONFIRMED');

 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'ATTEND_VIEW', 'OUTSTANDING1', 'OUTSTANDING', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'ATTEND_VIEW' and code = 'OUTSTANDING1'); 
