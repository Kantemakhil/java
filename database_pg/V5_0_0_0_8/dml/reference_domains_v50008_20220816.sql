INSERT INTO oms_owner.reference_domains(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
   VALUES('GENDER', 'Socially specified Gender', 'ACTIVE', 'ADMIN', 'OMS', NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);

INSERT INTO oms_owner.reference_domains(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
   VALUES('LO_REL_TYPE', 'Leg Ord commence/relationship types', 'ACTIVE', 'ADMIN', 'OMS', NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);


UPDATE oms_owner.reference_domains
   SET description='Actual Gender', modify_datetime=current_timestamp, modify_user_id=user
 WHERE domain='SEX';
