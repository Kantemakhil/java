INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
VALUES('OIRREPORT', 'Reports', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);  

INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
VALUES('OIIMPJRP', 'Import Reports', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);

INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
VALUES('OIEXPJRP', 'Export Reports', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);  

INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
VALUES('OIRMREPOR', 'Manaage Reports', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);

INSERT INTO oms_owner.oms_modules
(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
VALUES('OCDCHGDT', 'Charge Details', 'SCREEN', NULL, NULL, NULL,'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);

update oms_modules set dynamic_form=null where module_name='OIIOBALX';

