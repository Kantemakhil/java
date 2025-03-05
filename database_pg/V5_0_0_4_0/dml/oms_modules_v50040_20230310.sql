INSERT INTO OMS_MODULES(MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE,
 HELP_DIRECTORY, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES('OUMRELMD', 'Related Modules', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL);

INSERT INTO oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, process_workflow, user_task, dynamic_form, ins_dashboard)
VALUES('OIIMYOFF', 'My Offenders', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, process_workflow, user_task, dynamic_form, ins_dashboard)
VALUES('OCIMYOFF', 'My Offenders', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, NULL, NULL, NULL);


INSERT INTO oms_owner.oms_modules
(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, process_workflow, user_task, dynamic_form, ins_dashboard)
VALUES('OIRMASSET', 'Manage Asset', 'SCREEN', NULL, NULL, NULL, 'OMS', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL, NULL, NULL, NULL);