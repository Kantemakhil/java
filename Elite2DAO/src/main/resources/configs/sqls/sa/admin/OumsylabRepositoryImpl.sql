
OUMRESTA_FIND_CGFKRLEINARCMODULENAME {
 	SELECT OMS_MOD.MODULE_NAME  CODE, OMS_MOD.MODULE_NAME  DESCRIPTION ,OMS_MOD.DESCRIPTION newCode FROM   OMS_MODULES OMS_MOD
}

OUMRESTA_FIND_CGFKRLEINARC1DOMAIN {
 	SELECT DOMAIN AS CODE, DOMAIN DESCRIPTION FROM (SELECT DISTINCT REF_CODE.DOMAIN FROM REFERENCE_CODES REF_CODE) AS REFERENCE_CODES 

}

OUMRESTA_FIND_CGFKRLEINARC1CODE {
 	SELECT REF_CODE1.CODE  CODE ,REF_CODE1.DESCRIPTION  DESCRIPTION FROM   REFERENCE_CODES REF_CODE1 WHERE   DOMAIN = :DOMAIN
}

OUMRESTA_RLEINARC_FIND_OMS_MODULES {
 	SELECT OMS_MOD.MODULE_NAME ,OMS_MOD.DESCRIPTION   FROM   OMS_MODULES OMS_MOD order by OMS_MOD.MODULE_NAME
}
OUMRESTA_RLEINARC_INSERT_OMS_MODULES {
	INSERT INTO OMS_MODULES(MODULE_NAME ,DESCRIPTION ,MODULE_TYPE ,PRINT_FORMAT_CODE ,PREVIEW_FLAG ,DEFAULT_COPY ,APPLN_CODE ,HELP_DIRECTORY ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG ,OUTPUT_TYPE ) VALUES(:moduleName ,:description ,:moduleType ,:printFormatCode ,:previewFlag ,:defaultCopy ,:applnCode ,:helpDirectory ,:createDatetime ,:createUserId ,:modifyDatetime ,:modifyUserId ,:sealFlag ,:outputType )
}

OUMRESTA_RLEINARC_UPDATE_OMS_MODULES {
	UPDATE OMS_MODULES set MODULE_NAME  = :moduleName ,DESCRIPTION  = :description ,MODULE_TYPE  = :moduleType ,PRINT_FORMAT_CODE  = :printFormatCode ,PREVIEW_FLAG  = :previewFlag ,DEFAULT_COPY  = :defaultCopy ,APPLN_CODE  = :applnCode ,HELP_DIRECTORY  = :helpDirectory ,CREATE_DATETIME  = :createDatetime ,CREATE_USER_ID  = :createUserId ,MODIFY_DATETIME  = :modifyDatetime ,MODIFY_USER_ID  = :modifyUserId ,SEAL_FLAG  = :sealFlag ,OUTPUT_TYPE  = :outputType
	where MODULE_NAME = :moduleName
}

OUMRESTA_MODPRIV_FIND_MODULE_PRIVILEGES {
 	select ACCESS_PRIVILEGE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, MODULE_NAME, ROLE_ID, SEAL_FLAG, VERIFICATION_FLAG from MODULE_PRIVILEGES where module_name = :moduleName
}
OUMRESTA_RLEINARC1_FIND_ROLE_INACCESSIBLE_REF_CODES {
 	SELECT CODE, CREATE_DATETIME, CREATE_USER_ID, DOMAIN, MODIFY_DATETIME, MODIFY_USER_ID, MODULE_NAME, ROLE_ID, SEAL_FLAG FROM ROLE_INACCESSIBLE_REF_CODES where module_name = :moduleName and role_id = :roleId
}
OUMRESTA_RLEINARC1_INSERT_ROLE_INACCESSIBLE_REF_CODES {
	INSERT INTO ROLE_INACCESSIBLE_REF_CODES(CODE, CREATE_DATETIME, CREATE_USER_ID, DOMAIN, MODIFY_DATETIME, MODIFY_USER_ID, MODULE_NAME, ROLE_ID, SEAL_FLAG) VALUES(:code, :createDatetime, :createUserId, :domain, :modifyDatetime, :modifyUserId, :moduleName, :roleId, :sealFlag)
}

OUMRESTA_RLEINARC1_DELETE_ROLE_INACCESSIBLE_REF_CODES { 
	DELETE FROM ROLE_INACCESSIBLE_REF_CODES where ROLE_ID = :roleId and MODULE_NAME = :moduleName and CODE = :code and DOMAIN = :domain
}

OUMRESTA_MOD_PRIV_POSTQUERY{
	SELECT OMS_ROLE.ROLE_NAME FROM   OMS_ROLES OMS_ROLE WHERE  OMS_ROLE.ROLE_ID = :ROLEID
}




OUMRESTA_RLE_INARC_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM MODULE_PRIVILEGES WHERE MODULE_NAME = :MODULENAME
}

OUMRESTA_MOD_PRIV_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM ROLE_INACCESSIBLE_REF_CODES WHERE ROLE_ID = :ROLEID
}

OUMRESTA_CREATE_FORM_GLOBALSCREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO   V_FORM_DESC FROM   OMS_MODULES WHERE  MODULE_NAME = V_FORM_NAME
}

OUMRESTA_CGFKCHK_RLE_INARC_RLE_INARC_O_ {
	SELECT OMS_MOD.DESCRIPTION FROM   OMS_MODULES OMS_MOD WHERE  OMS_MOD.MODULE_NAME = :MODULENAME
}


OUMRESTA_CGFKCHK_RLE_INARC1_RLE_INARC__ {
	SELECT REF_CODE1.DESCRIPTION FROM   REFERENCE_CODES REF_CODE1 WHERE  REF_CODE1.CODE = :CODE AND     DOMAIN = :DOMAIN
}

OUMRESTA_CGFKCHK_RLE_INARC1_RLE_INA2_ {
	SELECT  '1' FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.DOMAIN = :DOMAIN
}
OUMSYLAB_EXECUTE_SYSTEM_LABEL{
SELECT * from SYSTEM_LABELS where MODULE_NAME=:moduleName
}
OUMSYLAB_EXECUTE_BYKEY_SYSTEM_LABEL{
SELECT * from SYSTEM_LABELS where MSG_KEY=:msgKey
}

OUMSYLAB_LABEL_UPDATE_DATA{
UPDATE SYSTEM_LABELS SET MSG_VALUE = :msgValue , MSG_TYPE= :msgType , modify_datetime =current_timestamp , modify_user_id =:modifyUserId WHERE LABEL_ID=:labelId
}

OUMSYLAB_SET_LABLE_ID{
SELECT NEXTVAL('LABLE_ID_SEQUENCE') FROM DUAL

}

OUMSYLAB_EXECUTE_ALL_SYSTEM_LABEL{
SELECT * from SYSTEM_LABELS 
}

OUMSYLAB_COUNT_LABELS{
SELECT count(*) from SYSTEM_LABELS where MODULE_NAME !='PROFILE'
}

OUMSYLAB_COUNT_PROFILE{
SELECT count(*) from SYSTEM_LABELS where MODULE_NAME='PROFILE'
}
OUMRESTA_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}

OUMSYLAB_SET_SYSTEM_LABLES{
insert into SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE, CREATE_USER_ID , create_datetime, modify_datetime ) values (:labelId, :moduleName, :msgKey, :msgValue, :msgType, :createUserId , current_timestamp , null )
}
OUMSYLAB_EXECUTE_SYSTEM_PROFILE{
select * from SYSTEM_PROFILES
}

OUMSYLAB_GET_ALL_SYSTEM_LABEL{
SELECT
    label_id,
    module_name,
    msg_key,
    msg_value,
    msg_type,
    create_datetime,
    create_user_id,
    modify_datetime,
    modify_user_id
FROM
    (
        SELECT
            ROW_NUMBER() OVER(
                ORDER BY
                    label_id
            ) rn,
            label_id,
            module_name,
            msg_key,
            msg_value,
            msg_type,
            create_datetime,
            create_user_id,
            modify_datetime,
            modify_user_id
        FROM
            system_labels
    ) a
WHERE
    rn BETWEEN :startIndex AND :lastIndex
}

