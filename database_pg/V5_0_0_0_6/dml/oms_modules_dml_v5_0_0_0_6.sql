INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
VALUES('OCDCSCH', 'Scheduler', 'SCREEN', NULL, NULL, NULL, 'CTAG', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);

INSERT into oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('OCDCSCH', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
