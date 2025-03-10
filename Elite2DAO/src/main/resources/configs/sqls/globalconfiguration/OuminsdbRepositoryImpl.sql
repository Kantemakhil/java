OUMINSDB_GET_INS_MOD_DASHBOARD {
	SELECT MODULE, DASHBOARD, ACTIVE_FLAG, OFFENDER_SPECIFIC_FLAG, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG FROM OMS_MODULE_INS_DASHBOARD
}

OUMINSDB_INS_MOD_DASHBOARD_INSERT {
	INSERT INTO OMS_MODULE_INS_DASHBOARD (MODULE, DASHBOARD, ACTIVE_FLAG, OFFENDER_SPECIFIC_FLAG, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID) VALUES (:module, :dashboard, :activeFlag, :offenderSpecificFlag, current_timestamp, :createUserId, NULL, NULL)
}

OUMINSDB_INS_MOD_DASHBOARD_UPDTAE {
	UPDATE OMS_MODULE_INS_DASHBOARD SET DASHBOARD = :dashboard, ACTIVE_FLAG = :activeFlag, OFFENDER_SPECIFIC_FLAG = :offenderSpecificFlag, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID = :modifyUserId WHERE MODULE = :module
}

OUMINSDB_INS_MOD_DASHBOARD_DELETE {
	DELETE FROM OMS_MODULE_INS_DASHBOARD WHERE MODULE = :module
}

OUMINSDB_UPDATE_INSDB_FLAG {
	UPDATE OMS_MODULES SET INS_DASHBOARD = 'Y' WHERE MODULE_NAME = :module
}

OUMINSDB_GET_INS_DASHBOARD_ID {
	SELECT DASHBOARD, OFFENDER_SPECIFIC_FLAG FROM OMS_MODULE_INS_DASHBOARD WHERE MODULE = :moduleName
}

OUMINSDB_POST_INSERT {
 	INSERT INTO OMS_MODULES(MODULE_NAME, DESCRIPTION, MODULE_TYPE, INS_DASHBOARD,CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID) VALUES(:module, :module, 'SCREEN', 'Y',current_timestamp, :createUserId, NULL, NULL)
}

OUMINSDB_POST_DELETE {
	DELETE FROM OMS_MODULES where MODULE_NAME = :module
}
GET_MODULE_CALL_FROM_RELATION_DATA{ 
SELECT COUNT(*) FROM offender_api_specific_modules WHERE MODULE_NAME=:moduleName
}
OFFENDER_SPECIFIC_MOODULE_INSERT {
	INSERT INTO offender_api_specific_modules (MODULE_NAME, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID) SELECT :module,current_timestamp, :createUserId, NULL, NULL
	WHERE NOT EXISTS (SELECT 1 FROM offender_api_specific_modules WHERE MODULE_NAME = :module)
}
OFFENDER_SPECIFIC_MOODULE_DELETE {
  DELETE FROM offender_api_specific_modules WHERE MODULE_NAME=:moduleName
}


