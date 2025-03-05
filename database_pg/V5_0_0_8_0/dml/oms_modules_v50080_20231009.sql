INSERT INTO oms_owner.oms_modules (MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY,CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG
                        , INS_DASHBOARD)
SELECT 'OFFDASH', 'OFFDASH', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL,CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, 'Y'
 WHERE NOT EXISTS (SELECT 1 FROM OMS_MODULES WHERE module_name = 'OFFDASH');
 
UPDATE oms_owner.oms_modules 
   SET ins_dashboard = 'Y' 
      ,create_datetime = current_timestamp, create_user_id = 'OMS_OWNER' 
 WHERE module_name = 'OFFDASH';
 
 
INSERT INTO oms_owner.module_privileges(module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT 'OFFDASH', 444, 'A', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null
WHERE NOT EXISTS ( SELECT 1 FROM oms_owner.module_privileges WHERE module_name = 'OFFDASH' AND role_id = 444);
