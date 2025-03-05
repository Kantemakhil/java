DELETE FROM works WHERE workflow_type = 'CNOTE' AND work_type = 'CNOTE' AND work_sub_type = 'EMAIL';
DELETE FROM works WHERE workflow_type = 'CNOTE' AND work_type = 'CNOTE' AND work_sub_type = 'SMS';

INSERT INTO works (work_id,workflow_type,work_type,work_sub_type,manual_close_flag,module_name,active_flag,expiry_date,caseload_type,manual_select_flag,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag,email_subject,email_body,case_note_text) 
  VALUES (nextval('work_id'),'CNOTE','CNOTE','EMAIL','Y',NULL,'Y',NULL,'COMM','Y',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,NULL,NULL,NULL,NULL,NULL);
  
INSERT INTO works (work_id,workflow_type,work_type,work_sub_type,manual_close_flag,module_name,active_flag,expiry_date,caseload_type,manual_select_flag,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag,email_subject,email_body,case_note_text) 
  VALUES (nextval('work_id'),'CNOTE','CNOTE','SMS','Y',NULL,'Y',NULL,'COMM','Y',CURRENT_TIMESTAMP,'OMS_OWNER',CURRENT_TIMESTAMP,NULL,NULL,NULL,NULL,NULL);
  