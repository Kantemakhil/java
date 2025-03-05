INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
 VALUES('SENT_TYPE', 'CHLD', 'Custodial Holds',99, 'Y', 'Y', NULL, NULL, NULL, NULL, NULL,current_timestamp,'OMS_OWNER',current_timestamp,NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
 VALUES('PROCESS_CATE', 'PRODUCT', 'Product', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
 VALUES('PROCESS_CATE', 'CLIENT', 'Client', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL);

INSERT INTO oms_owner.reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
 VALUES('PROCESS_CATE', 'SYSTEM', 'Internal Use', 3, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL);  