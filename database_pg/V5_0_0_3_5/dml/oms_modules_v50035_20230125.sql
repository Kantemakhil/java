INSERT INTO oms_owner.oms_modules
(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, process_workflow, user_task, dynamic_form, ins_dashboard)
SELECT 'EOFFENDER', 'All Offender Documents', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL,current_timestamp, 'OMS_OWNER',Null, NULL, NULL, NULL, NULL, NULL, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.oms_modules WHERE module_name = 'EOFFENDER');

update oms_modules set description ='All Offender Documents',
MODIFY_USER_ID = 'OMS_OWNER' ,
modify_datetime = current_timestamp
where module_name = 'EOFFENDER';

INSERT INTO oms_owner.oms_modules
(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, process_workflow, user_task, dynamic_form, ins_dashboard)
VALUES('OCUACOND', 'Add Conditions', 'SCREEN', NULL, NULL, NULL, 'INST', NULL,current_timestamp, 'OMS_OWNER',Null, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO oms_owner.oms_modules
(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, process_workflow, user_task, dynamic_form, ins_dashboard)
VALUES('OCUUCOND', 'Update Conditions', 'SCREEN', NULL, NULL, NULL, 'INST', NULL,current_timestamp, 'OMS_OWNER',Null, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO oms_owner.module_privileges (module_name, role_id, access_privilege, verification_flag, create_user_id, create_datetime) 
 VALUES ('EOFFENDER', 444, 'A', 'Y', 'OMS_OWNER', current_timestamp);
 
INSERT INTO oms_owner.module_privileges (module_name, role_id, access_privilege, verification_flag, create_user_id, create_datetime) 
 VALUES ('OCUACOND', 444, 'A', 'Y', 'OMS_OWNER', current_timestamp); 
 
INSERT INTO oms_owner.module_privileges (module_name, role_id, access_privilege, verification_flag, create_user_id, create_datetime) 
 VALUES ('OCUUCOND', 444, 'A', 'Y', 'OMS_OWNER', current_timestamp);  