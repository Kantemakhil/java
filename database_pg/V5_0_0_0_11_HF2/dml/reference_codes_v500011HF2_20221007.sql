--17507
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('OBJECT_TYPE', 'EMAIL', 'Email', 99, 'Y', 'Y', NULL, NULL, NULL, NULL, NULL,current_timestamp, 'OMS_OWNER',current_timestamp, NULL);

--17057,17058,17059
 insert into oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag) 
values('TASK_TYPE', 'REMINDER', 'Email/Sms Reminer', 99, 'Y', 'Y', null, null, null, null, null, current_timestamp, 'OMS_OWNER', current_timestamp, null);

-- insert into oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag) 
-- values('TASK_SUBTYPE', 'SMS', 'SMS', 99, 'Y', 'Y', null, null, null, 'REMINDER', 'TASK_TYPE', current_timestamp, 'OMS_OWNER', current_timestamp, null);
 
 
-- insert into oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag) 
-- values('TASK_SUBTYPE', 'EMAIL', 'Email', 99, 'Y', 'Y', null, null, null, 'REMINDER', 'TASK_TYPE', current_timestamp, 'OMS_OWNER', current_timestamp, null);
 
DELETE FROM oms_owner.reference_codes where domain = 'TASK_SUBTYPE' AND  code = 'SMS';
DELETE FROM oms_owner.reference_codes where domain = 'TASK_SUBTYPE' AND  code = 'EMAIL';
 
 --temporary work around to make it work
insert into oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag) 
 values('TASK_SUBTYPE', 'SMS', 'SMS', 99, 'Y', 'Y', null, null, null, 'CNOTE', 'TASK_TYPE', current_timestamp, 'OMS_OWNER', current_timestamp, null);
 
 
 insert into oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag) 
 values('TASK_SUBTYPE', 'EMAIL', 'Email', 99, 'Y', 'Y', null, null, null, 'CNOTE', 'TASK_TYPE', current_timestamp, 'OMS_OWNER', current_timestamp, null);
 
   