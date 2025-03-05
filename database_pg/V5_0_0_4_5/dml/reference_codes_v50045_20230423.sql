INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'CALC_REASON', 'AUTOUPDATE', 'Automatic Order Status Update', 99, 'N', 'N', NULL, NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'CALC_REASON' AND code = 'AUTOUPDATE');

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'TASK_TYPE', 'CASEOFF', 'Case Plan Note', 4, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'TASK_TYPE' and code = 'CASEOFF');
 
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'TASK_SUBTYPE', 'PCO', 'PCO Case Note', 4, 'Y', 'N', 'OMS_OWNER', NULL, NULL, 'CASEOFF', 'TASK_TYPE', current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'TASK_SUBTYPE' and code = 'PCO');

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CPLAN_ROLE', 'CPLAN_PCO', 'Primary Case Officer', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CPLAN_ROLE' and code = 'CPLAN_PCO');
 
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CPLAN_ROLE', 'CPLAN_SCO', 'Secondary Case Officer', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CPLAN_ROLE' and code = 'CPLAN_SCO');
 
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CPLAN_ROLE', 'CPLAN_IC', 'Indigenous Consultant', 3, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CPLAN_ROLE' and code = 'CPLAN_IC');
 
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CPLAN_ROLE', 'CPLAN_PO', 'Planning Officer', 4, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CPLAN_ROLE' and code = 'CPLAN_PO');
 
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CPLAN_ROLE', 'CPLAN_SPO', 'Senior Planning Officer', 5, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CPLAN_ROLE' and code = 'CPLAN_SPO');
 
 INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
SELECT 'CPLAN_ROLE', 'CPLAN_IO', 'Interventions Officer', 6, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.reference_codes WHERE domain = 'CPLAN_ROLE' and code = 'CPLAN_IO');