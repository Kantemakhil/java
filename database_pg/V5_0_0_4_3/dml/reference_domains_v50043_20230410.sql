INSERT INTO reference_domains (DOMAIN, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'WRKLD_TASK', 'Workload Task Type', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_domains WHERE domain = 'WRKLD_TASK');


INSERT INTO reference_domains (DOMAIN, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'EAOCSU', 'Status Updates', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_domains WHERE domain = 'EAOCSU');
 
INSERT INTO oms_owner.reference_domains
(domain, description, domain_status, owner_code, appln_code, old_code_table, parent_domain, code_length, create_datetime, create_user_id, modify_datetime, modify_user_id)
select 'ATTEND_VIEW', 'Attendance View', 'ACTIVE', 'OMS_OWNER', 'OMS', NULL, NULL, NULL,CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 where not exists (select 1 from oms_owner.reference_domains where domain='ATTEND_VIEW');
