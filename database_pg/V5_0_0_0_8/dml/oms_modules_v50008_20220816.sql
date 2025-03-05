delete from menu_securities where module_name = 'OCDLEGLN';
delete from module_privileges where MODULE_NAME = 'OCDLEGLN';

delete from oms_owner.oms_modules  where MODULE_NAME = 'OCDLEGLN';

INSERT INTO oms_owner.oms_modules  (module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, dynamic_form)
   VALUES ('OCDNCODE', 'Non Custodial Orders', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL, 'Y');

INSERT INTO oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
  VALUES('OCDNCODE', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--
INSERT INTO oms_owner.oms_modules (module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, dynamic_form)
   VALUES ('OSANVIOS', 'Sanction And Violations', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL, NULL);

INSERT INTO oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES('OSANVIOS', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--
INSERT INTO oms_owner.oms_modules (module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, dynamic_form)
   VALUES ('OUMINSDB', 'Module Dashboard Association', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL, NULL);

INSERT INTO oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
    VALUES('OUMINSDB', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--
INSERT INTO oms_owner.oms_modules  (module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, dynamic_form)
   VALUES ('OCDLEGLN', 'Non Custodial Orders', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL, 'Y');

INSERT INTO oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES('OCDLEGLN', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--
INSERT INTO oms_owner.oms_modules (module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type, dynamic_form)
   VALUES ('OCSPROIN','Community Service Project Inquiry', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL, NULL);

INSERT INTO oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES ('OCSPROIN',444,'A','Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
