INSERT INTO oms_owner.sentence_adjustments
(sentence_adjust_code, description, debit_credit_code, usage_code, create_datetime, create_user_id, modify_datetime, modify_user_id, expiry_date, active_flag, seal_flag)
VALUES('CUST', 'In Custody', 'CR', 'PAR',current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL, 'Y', NULL);