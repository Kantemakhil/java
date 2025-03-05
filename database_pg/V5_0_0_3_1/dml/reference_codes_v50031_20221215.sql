INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('CONTACT_TYPE', 'PER', 'Person', 1, 'Y', 'N',NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('CONTACT_TYPE', 'CORP', 'Corporate', 2, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('SENT_RECIVED', 'RECEIVED', 'Received', 2, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('SENT_RECIVED', 'SENT', 'Sent', 1, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_TYPE', 'STANDARD', 'Standard', 1, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_TYPE', 'CONFIDENTIAL', 'Confidential', 2, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_TYPE', 'THREAT', 'Threat', 3, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_TYPE', 'DAMAGED', 'Damaged', 4, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_TYPE', 'CONTRABAND', 'Contraband', 5, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_STAT', 'APP', 'Approved', 1, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_STAT', 'PEN', 'Pending', 2, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_STAT', 'RET', 'Returned', 3, 'Y', 'N', NULL, NULL, current_timestamp,  'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_STAT', 'HELD', 'Held', 4, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_STAT', 'FORWARDED', 'Forwarded', 5, 'Y', 'N', NULL, NULL, current_timestamp,  'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_RST_TYP', 'ADD_BAN', 'Address Ban', 1, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime) 
 VALUES('MAIL_RST_TYP', 'OFF_CNT_CONT', 'Offender Cannot Contact', 2, 'Y', 'N', NULL, NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id,modify_datetime)
VALUES('BOOKMRK_COND', 'BAR', 'Between Answer Range', 1, 'Y', 'Y',NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, create_datetime, create_user_id, modify_datetime)
VALUES('BOOKMRK_COND', 'EAW', 'Equals Answer Wording', 2, 'Y', 'Y',NULL, NULL,CURRENT_TIMESTAMP,'OMS_OWNER', NULL);
