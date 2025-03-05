INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
SELECT 'CRT_CAN_RSN', 'Court Event Cancellation Reason', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL
 WHERE NOT exists (SELECT 1 FROM oms_owner.reference_domains WHERE domain = 'CRT_CAN_RSN');
 
INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
SELECT 'PS_PAY', 'Programs and Services Pay', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL
 WHERE NOT exists (SELECT 1 FROM oms_owner.reference_domains WHERE domain = 'PS_PAY'); 

INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
SELECT 'CONT_METH', 'Preferred Contact Method', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL
 WHERE NOT exists (SELECT 1 FROM oms_owner.reference_domains WHERE domain = 'CONT_METH');

INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
SELECT 'VIC_CONT_CAT', 'Victim Contact Category', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL
 WHERE NOT exists (SELECT 1 FROM oms_owner.reference_domains WHERE domain = 'VIC_CONT_CAT');

INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
SELECT 'VIC_CON_TYPE', 'Victim Contact Type', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL
 WHERE NOT exists (SELECT 1 FROM oms_owner.reference_domains WHERE domain = 'VIC_CON_TYPE');


