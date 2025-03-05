 INSERT INTO reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'TA_STATUS', 'COMP', 'Completed', 1, 'N', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL
  WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'TA_STATUS' and code = 'COMP');