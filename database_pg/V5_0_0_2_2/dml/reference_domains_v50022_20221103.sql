Insert into REFERENCE_DOMAINS (DOMAIN,DESCRIPTION,DOMAIN_STATUS,OWNER_CODE,APPLN_CODE, create_datetime, create_user_id, modify_datetime) 
values ('INT_PARTIES','Interested Parties','ACTIVE','OMS_OWNER','ITAG', current_timestamp, 'OMS_OWNER', current_timestamp);

INSERT INTO reference_domains(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id, super_set_domain, seal_flag)
VALUES('PROCESS_CATE', 'Process Category Indicator', 'ACTIVE', 'ADMIN', 'OMS', NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);
