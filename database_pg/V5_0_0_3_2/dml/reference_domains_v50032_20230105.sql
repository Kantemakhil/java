INSERT INTO oms_owner.reference_domains(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
VALUES('ACT_REAS', 'Activity Reason', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, 'ACT_TYPE', NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', NULL,NULL, NULL, NULL);

INSERT INTO oms_owner.reference_domains(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
VALUES('ACT_OFFREA', 'Offender Activity Reason', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', NULL,NULL, NULL, NULL);

INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
VALUES('CHAR_DISP', 'Charge Disposition', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL,CURRENT_TIMESTAMP,'OMS_OWNER', CURRENT_TIMESTAMP,NULL, NULL, NULL);

INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
VALUES('EXT_INV_STAT', 'External Investigation Status', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL,CURRENT_TIMESTAMP, 'OMS_OWNER', NULL,null, NULL, NULL);

Insert into REFERENCE_DOMAINS (DOMAIN,DESCRIPTION,DOMAIN_STATUS,OWNER_CODE,APPLN_CODE,old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag) values ('CHOUTUPREA','Charge Update Reason','ACTIVE','OMS_OWNER','OMS', NULL, NULL, NULL,CURRENT_TIMESTAMP, 'OMS_OWNER', NULL,null, NULL, NULL);