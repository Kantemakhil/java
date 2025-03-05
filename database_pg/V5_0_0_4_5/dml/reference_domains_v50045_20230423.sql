INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
SELECT 'CPLAN_ROLE', 'CPLAN_ROLE', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL
 WHERE NOT exists (SELECT 1 FROM oms_owner.reference_domains WHERE domain = 'CPLAN_ROLE');