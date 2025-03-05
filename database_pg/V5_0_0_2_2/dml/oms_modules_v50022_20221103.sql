INSERT INTO OMS_MODULES(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, process_workflow, user_task, dynamic_form, ins_dashboard) 
VALUES('OCDINTPA', 'Interested Parties', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, process_workflow, user_task, dynamic_form, ins_dashboard)
VALUES('OIMSRLUC', 'Staff Report Lock/Unlock configuration', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO OMS_MODULES(MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES('OIDOWREL', 'Offender Work Release Projects', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL);

INSERT INTO OMS_MODULES(MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES('OIDOWREL', 'Offender Work Release Projects', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL);

INSERT INTO OMS_MODULES(MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES('OCICNSRC', 'Case Notes For Offenders', 'SCREEN', NULL, NULL, NULL, 'CTAG', NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL);

INSERT INTO OMS_MODULES (MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG, OUTPUT_TYPE, PROCESS_WORKFLOW, USER_TASK, DYNAMIC_FORM, INS_DASHBOARD) 
VALUES('OCMCNPER', 'Maintain Case Note Permissions', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL, NULL, NULL, NULL, NULL);

update oms_modules  set dynamic_form = NULL where module_name  = 'OCDLEGLS';
update oms_modules  set dynamic_form = NULL where module_name  = 'OCDCHGSU';
update oms_modules  set dynamic_form = NULL where module_name  = 'OCDNCODE';
update oms_modules  set dynamic_form = NULL where module_name  = 'OCDLEGLO';
update oms_modules  set dynamic_form = NULL where module_name  = 'OIDCRTEV';
