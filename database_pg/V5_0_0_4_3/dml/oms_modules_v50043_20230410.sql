INSERT INTO OMS_MODULES (MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY,
	                    CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
SELECT 'PROSDEAC', 'Process Activation/Deactivation', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL,
         CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL
 WHERE NOT EXISTS (SELECT 1 FROM OMS_MODULES WHERE module_name = 'PROSDEAC');
 
INSERT INTO OMS_MODULES (MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY,
	                    CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
SELECT 'OCDSENCH', 'Sentence History Calculation', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL,
         CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL
 WHERE NOT EXISTS (SELECT 1 FROM OMS_MODULES WHERE module_name = 'OCDSENCH'); 

INSERT INTO OMS_MODULES (MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY,
	                    CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
SELECT 'OCMDSPWD', 'Maintain Default Staff Position Workloads', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL,
         CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL
 WHERE NOT EXISTS (SELECT 1 FROM OMS_MODULES WHERE module_name = 'OCMDSPWD');

INSERT INTO OMS_MODULES (MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY,
	                    CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
SELECT 'OCMLESET', 'Legal Settings', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, 
         CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL
 WHERE NOT EXISTS (SELECT 1 FROM OMS_MODULES WHERE module_name = 'OCMLESET');

UPDATE
	OMS_MODULES
SET
	DESCRIPTION = 'Legals Settings',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	MODULE_NAME = 'OCMLESET';

INSERT INTO OMS_MODULES (MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY,
	                    CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
SELECT 'OIDPAROE', 'Parole Events', 'SCREEN', NULL, NULL, NULL, 'OMS4', NULL, 
         CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL
 WHERE NOT EXISTS (SELECT 1 FROM OMS_MODULES WHERE module_name = 'OIDPAROE');

INSERT INTO OMS_MODULES (MODULE_NAME, DESCRIPTION, MODULE_TYPE, PRINT_FORMAT_CODE, PREVIEW_FLAG, DEFAULT_COPY, APPLN_CODE, HELP_DIRECTORY,
	                    CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
SELECT 'OCDONOST', 'Officer Non-Offender-Specific Tasks','SCREEN', NULL, NULL, NULL, 'OMS4', NULL, 
         CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL
 WHERE NOT EXISTS (SELECT 1 FROM OMS_MODULES WHERE module_name = 'OCDONOST');
 