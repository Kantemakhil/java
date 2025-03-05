INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
    VALUES('OUMSYSET', 'System Settings', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);
      	
INSERT into oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES('OUMSYSET', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

---
INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
   VALUES('OCMXPSTM', 'Maintain Specialized Program Status', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);

INSERT into oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES('OCMXPSTM', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

----   
INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
   VALUES('OCSPROGR', 'Offender Specialized Programs Inquiry', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);

INSERT into oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES('OCSPROGR', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

----
INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
   VALUES('OCONDAWAIT', 'Conditions Awaiting Assignment', 'SCREEN', NULL, NULL, NULL, 'LEGAL', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);

INSERT into oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
  VALUES('OCONDAWAIT', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

----
INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
   VALUES('OCONDTRF', 'Transfer Conditions', 'SCREEN', NULL, NULL, NULL, 'LEGAL', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);

INSERT into oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES('OCONDTRF', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

----

INSERT INTO oms_owner.oms_modules(module_name, description, module_type, print_format_code, preview_flag, default_copy, appln_code, help_directory, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, output_type)
   VALUES('OTKCONDTRF', 'Track Conditions Transfer', 'SCREEN', NULL, NULL, NULL, 'LEGAL', NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL, NULL);

INSERT into oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES('OTKCONDTRF', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
	