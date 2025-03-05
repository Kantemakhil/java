INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id) 
 SELECT 'USG_CODE', 'BKG', 'Booking', 1, 'Y', 'Y', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'USG_CODE' AND code = 'BKG'); 

INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id) 
 SELECT 'USG_CODE', 'SENT', 'Sentence', 99, 'Y', 'Y', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'USG_CODE' AND code = 'SENT'); 

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
 select 'USG_CODE', 'BKG_REMISS', 'Booking-Level Remission', 99, 'Y', 'Y', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, null 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'USG_CODE' AND code = 'BKG_REMISS');

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
 select 'USG_CODE', 'SENT_REMISS', 'Sentence-Level Remission', 99, 'Y', 'Y', 'OMS_OWNER', NULL, NULL, NULL, NULL,current_timestamp, 'OMS_OWNER', current_timestamp, null 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'USG_CODE' AND code = 'SENT_REMISS');

--
INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id) 
 SELECT 'KEY_DATES', 'BOOKING_RED', 'Remission Eligibity Date', 99, 'Y', 'Y', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_RED');


update
	reference_codes
set
	active_flag = 'N',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	domain = 'PAR_EVENTS'
	and code = 'RPAR';