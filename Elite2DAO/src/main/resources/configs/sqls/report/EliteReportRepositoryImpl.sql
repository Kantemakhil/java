GET_ELITE_REPORTS_BY_MODULE_NAMES {
	SELECT MODULE_NAME , REPORT_BODY , REPORT_FILE_NAME  FROM OMS_MODULE_REPORT WHERE MODULE_NAME IN ( :MODULE_NAMES )
}
GET_ELITE_REPORTS_BY_MODULE_NAMES {
	SELECT REPORT_ID, MODULE_NAME, REPORT_BODY, REPORT_FILE_NAME, REPORT_VERSION, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID FROM OMS_MODULE_REPORT WHERE MODULE_NAME IN ( :MODULE_NAMES )
}
GET_ALL_ELITE_REPORT {
	select
	omr.REPORT_ID,
	omr.MODULE_NAME,
	omr.REPORT_FILE_NAME,
	omr.REPORT_VERSION,
	omr.CREATE_DATETIME, 
	omr.CREATE_USER_ID,
	case
		when omr.MODIFY_DATETIME is null then omr.CREATE_DATETIME
		else omr.MODIFY_DATETIME
	end as MODIFY_DATETIME,
	omr.MODIFY_USER_ID,
	om.description
	from
	OMS_MODULE_REPORT omr
	join oms_modules om on
	omr.module_name = om.module_name
	join (
	select
		mp.*
	from
		module_privileges mp ,
		staff_member_roles smr,
		staff_members sm
	where
		sm.staff_id = smr.staff_id
		and smr.role_id = mp.role_id
		and sm.user_id =:userId
		) mp 
		on om.module_name = mp.module_name
 
}

GET_NAME_BODY_BY_MODULE_NAMES {
	SELECT MODULE_NAME , REPORT_BODY , REPORT_FILE_NAME  FROM OMS_MODULE_REPORT WHERE MODULE_NAME IN ( :MODULE_NAMES )
}

ELITE_REPORTS_INSERT_PROCESS {
INSERT INTO oms_owner.oms_module_report (
	report_id
	,module_name
	,report_body
	,report_file_name
	,report_version
	,create_datetime
	,create_user_id
	,modify_datetime
	,modify_user_id
	)
VALUES (
	nextval('OMS_MODULE_REPORT_SEQ'),
	:moduleName,
	:reportBody,
	:reportFileName,
	:reportVersion,
	CURRENT_TIMESTAMP,
	:createUserId,
	NULL,
	NULL
	)
}

ELITE_REPORTS_HISTORY_INSERT_PROCESS {
INSERT INTO oms_owner.OMS_MODULE_REPORT_HISTORY (
	report_id
	,module_name
	,report_body
	,report_file_name
	,report_version
	,create_datetime
	,create_user_id
	,modify_datetime
	,modify_user_id,
	version_date
	)
VALUES (
	:reportId,
	:moduleName,
	:reportBody,
	:reportFileName,
	:reportVersion,
	:createDatetime,
	:createUserId,
	:modifyDatetime,
	:modifyUserId,
	CURRENT_TIMESTAMP
	)
}

ELITE_REPORTS_MODULE_NAME_INSERT_PROCESS {
INSERT INTO oms_owner.OMS_MODULES
       (      MODULE_NAME
            , DESCRIPTION
            , MODULE_TYPE
            , APPLN_CODE
            , CREATE_DATETIME
            , CREATE_USER_ID
            , MODIFY_DATETIME
            , MODIFY_USER_ID
       )
       VALUES
       (	  :moduleName
            , :moduleName
            , 'REPORT'
            ,'OMS4'
            , CURRENT_TIMESTAMP
            , :createUserId
            , CURRENT_TIMESTAMP
            , :modifyUserId
       )
}

GET_ELITE_MODULES_BY_MODULE_NAMES {
	SELECT MODULE_NAME  FROM OMS_OWNER.OMS_MODULES WHERE MODULE_NAME IN ( :MODULE_NAMES )
}


ELITE_REPORTS_UPDATE_BY_MODULE_NAME_PROCESS {
	UPDATE oms_owner.oms_module_report SET report_file_name=:reportFileName, REPORT_BODY=:reportBody, report_version=:reportVersion, modify_datetime=CURRENT_TIMESTAMP, modify_user_id=:modifyUserId WHERE module_name=:moduleName
}

HISTORY_REPORT_ID{
    SELECT nextval('OMS_MODULE_HISTORY_REPORT_SEQ') FROM dual
}

GET_OMS_MODULE_PARAMETERS_BY_MODULE_NAME{
	SELECT MODULE_NAME, PARAMETER_SEQ, PARAMETER_CODE, PARAMETER_NAME, PARAMETER_TYPE, PARAMETER_DOMAIN, OPTIONAL_FLAG, COMMENT_TEXT, MULTIVALUE_FLAG, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, PARAMETER_LOV_SELECT, PARAMETER_LOV_TITLE, PARAMETER_LOV_GROUP, SEAL_FLAG, PARENT_LOV
	FROM OMS_OWNER.OMS_MODULE_PARAMETERS
	WHERE MODULE_NAME IN  ( :MODULE_NAMES );
}

GET_OMS_MODULE_PARAM_NAME_BY_MODULE_NAME{
	SELECT PARAMETER_NAME FROM OMS_MODULE_PARAMETERS 
	WHERE MODULE_NAME = :MODULENAME
}

INSERT_OMS_MODULE_PARAMETERS_WITH_REPORTS{
		INSERT INTO OMS_OWNER.OMS_MODULE_PARAMETERS
		(	MODULE_NAME,
			PARAMETER_SEQ,
			PARAMETER_CODE,
			PARAMETER_NAME,
			PARAMETER_TYPE,
			PARAMETER_DOMAIN,
			OPTIONAL_FLAG,
			COMMENT_TEXT,
			MULTIVALUE_FLAG,
			CREATE_DATETIME,
			CREATE_USER_ID,
			MODIFY_DATETIME,
			MODIFY_USER_ID,
			PARAMETER_LOV_SELECT,
			PARAMETER_LOV_TITLE,
			PARAMETER_LOV_GROUP,
			SEAL_FLAG,
			PARENT_LOV
			)
		VALUES(
		:moduleName,
		:parameterSeq,
		:parameterCode,
		:parameterName,
		:parameterType,
		:parameterDomain,
		:optionalFlag,
		:commentText,
		:multivalueFlag,
		 CURRENT_TIMESTAMP,
		:createUserId,
		 NULL,
		:modifyUserId,
		:parameterLovSelect,
		:parameterLovTitle,
		:parameterLovGroup,
		:sealFlag,
		:parentLov
		);
}

UPDATE_MODULE_PARAMS_BY_NAME_SEQ_MODULENAME{
	UPDATE oms_owner.oms_module_parameters
	SET    parameter_code = :parameterCode,
	       parameter_type = :parameterType,
	       parameter_domain = :parameterDomain,
	       optional_flag = :optionalFlag,
	       comment_text = :commentText,
	       multivalue_flag = :multivalueFlag,
	       modify_datetime = CURRENT_TIMESTAMP,
	       modify_user_id = :modifyUserId,
	       parameter_lov_select = :parameterLovSelect,
	       parameter_lov_title = :parameterLovTitle,
	       parameter_lov_group = :parameterLovGroup,
	       seal_flag = :sealFlag,
	       parent_lov = :parentLov
	WHERE  module_name = :moduleName
	       AND parameter_seq = :parameterSeq
	       AND parameter_name = :parameterName
}



DELETE_OMS_MODULE_PARAMETERS_BY_MODULE_NAME{
	DELETE FROM OMS_MODULE_PARAMETERS WHERE MODULE_NAME = :moduleName	
}

GET_OMS_MODULE_PARAMETERS_SQL{
	SELECT PARAMETER_LOV_SELECT FROM OMS_OWNER.OMS_MODULE_PARAMETERS WHERE MODULE_NAME = :MODULE_NAMES
	AND PARAMETER_NAME = :PARAMETER_NAME AND PARAMETER_SEQ = :PARAMETER_SEQ;
}

DELETE_OMS_MODULE_PARAMETERS_BY_SEQ_NAME_MODNAME{
	DELETE FROM OMS_MODULE_PARAMETERS WHERE MODULE_NAME = :moduleName AND PARAMETER_NAME = :parameterName AND PARAMETER_SEQ = :parameterSeq 	
}

GET_MAX_PARAMETER_SEQ_BY_MODULE_NAME{
	SELECT  MAX(PARAMETER_SEQ) as MAXSEQ FROM OMS_MODULE_PARAMETERS WHERE MODULE_NAME = :MODULENAME
}

UPDATE_OMS_MODULES {
	update OMS_MODULES set  DESCRIPTION = :description,  MODIFY_DATETIME = CURRENT_TIMESTAMP , MODIFY_USER_ID = :modifyUserId where MODULE_NAME = :moduleName 
}
DELETE_OMS_MODULE_REPORTS {
	DELETE FROM oms_module_report WHERE MODULE_NAME = :moduleName
}
GET_INSTITUTION_SQL {	
	SELECT description DESCRIPTION, agy_loc_id CODE FROM AGENCY_LOCATIONS WHERE AGY_LOC_ID NOT IN ('OUT','TRN') AND AGENCY_LOCATION_TYPE = 'INST'
}

GET_ASSET_BY_ASSET_CODE{
	SELECT ASSETS_ID, ASSET_CODE, ASSET_BODY, ASSETS_FILENAME, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID FROM OMS_OWNER.OMS_MODULE_REPORT_ASSETS WHERE ASSET_CODE=:ASSETCODE
}
GET_ALL_ASSET{
	SELECT ASSETS_ID, ASSET_CODE, ASSETS_FILENAME, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID FROM OMS_OWNER.OMS_MODULE_REPORT_ASSETS
}

GET_ASSETS_BY_CODES {
	SELECT  ASSET_CODE, ASSETS_FILENAME,ASSET_BODY FROM OMS_OWNER.OMS_MODULE_REPORT_ASSETS WHERE ASSET_CODE IN ( :ASSET_CODES )
}


EXIST_OMS_ASSET_BY_CODE{
	SELECT COUNT(*)  FROM  OMS_MODULE_REPORT_ASSETS WHERE ASSET_CODE=:ASSETCODE
}

INSERT_OMS_ASSET{
	INSERT INTO OMS_MODULE_REPORT_ASSETS
	(
	ASSETS_ID,
	ASSET_CODE,
	ASSET_BODY,
	ASSETS_FILENAME,
	CREATE_DATETIME,
	CREATE_USER_ID
	)
	VALUES(
	nextval('oms_asset_id_seq'),
	:assetCode,
	:assetBody,
	:assetsFilename,
	CURRENT_TIMESTAMP,
	:createUserId
	)
}

UPDATE_OMS_ASSET{
	UPDATE OMS_MODULE_REPORT_ASSETS
	SET  
	ASSET_BODY=:assetBody,
	ASSETS_FILENAME=:assetsFilename,
	MODIFY_DATETIME=CURRENT_TIMESTAMP,
	MODIFY_USER_ID=:modifyUserId
	WHERE 
	ASSET_CODE=:assetCode
}



