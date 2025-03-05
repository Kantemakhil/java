INSERT INTO reference_domains (domain,description,domain_status,owner_code,appln_code,old_code_table,parent_domain,code_length,create_datetime,create_user_id,modify_datetime,modify_user_id,super_set_domain,seal_flag) 
VALUES ('IN_POLICY','IN_POLICY','ACTIVE','OMS_OWNER','COMM',NULL,NULL,NULL,current_timestamp,'OMS_OWNER',current_timestamp,null,NULL,NULL);

INSERT INTO reference_domains (domain,description,domain_status,owner_code,appln_code,old_code_table,parent_domain,code_length,create_datetime,create_user_id,modify_datetime,modify_user_id,super_set_domain,seal_flag) 
VALUES ('IN_COMP','Complience','ACTIVE','OMS_OWNER','COMM',NULL,NULL,NULL,current_timestamp,'OMS_OWNER',current_timestamp,null,NULL,NULL);

INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
VALUES('P_TRN_CAN', 'Property Transfer Cancel Reason', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, NULL);

INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
VALUES('P_TRN_REJ', 'Property Transfer Reject Reason', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, NULL);

INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
VALUES('ASSESS_STATS', 'Assessment Status', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL,CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL, NULL);