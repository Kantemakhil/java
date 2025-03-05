delete from reference_codes where code ='PRISONER PAY' and  domain = 'FUNCTION';


INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'FUNCTION', 'PRISONER_PAY', 'Prisoner Pay Team', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, null, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'FUNCTION' and code = 'PRISONER_PAY');