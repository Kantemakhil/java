
OUMSYPFL_FIND_CGFKSYSPFLPROFILETYPE {
 	SELECT REF_CODE.CODE , REF_CODE.DESCRIPTION         ,REF_CODE.LIST_SEQ  FROM   REFERENCE_CODES REF_CODE WHERE
 	DOMAIN='PROFILE_TYPE' AND ACTIVE_FLAG='Y' AND EXPIRED_DATE IS NULL ORDER BY  REF_CODE.LIST_SEQ
}

OUMSYPFL_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES
}
OUMSYPFL_SYSPFL_INSERT_SYSTEM_PROFILES {
	INSERT INTO SYSTEM_PROFILES(PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2  ,
	OLD_TABLE_NAME ,
	CREATE_DATETIME ,CREATE_USER_ID , modify_datetime, SEAL_FLAG )
	VALUES(:profileType ,:profileCode ,:description ,:profileValue ,:profileValue2 ,
	:oldTableName ,current_timestamp ,:createUserId, NULL, :sealFlag )
}

OUMSYPFL_SYSPFL_UPDATE_SYSTEM_PROFILES {
	UPDATE SYSTEM_PROFILES set DESCRIPTION  = :description ,PROFILE_VALUE  = :profileValue ,
	PROFILE_VALUE_2  = :profileValue2 ,
	MODIFY_USER_ID  = :modifyUserId ,OLD_TABLE_NAME  = :oldTableName,
	  MODIFY_DATETIME  = current_timestamp ,SEAL_FLAG  = :sealFlag
	 where PROFILE_TYPE  = :profileType and PROFILE_CODE  = :profileCode
}

OUMSYPFL_SYSPFL_DELETE_SYSTEM_PROFILES {
	DELETE FROM SYSTEM_PROFILES where PROFILE_TYPE  = :profileType and PROFILE_CODE  = :profileCode
}


OUMSYPFL_CGFKCHK_SYS_PFL_SYSTEM_PROFIL {
	SELECT REF_CODE.DESCRIPTION ,REF_CODE.LIST_SEQ FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :PROFILETYPE AND     DOMAIN='PROFILE_TYPE' AND ACTIVE_FLAG='Y' AND EXPIRED_DATE IS NULL
}

OUMSYPFL_CGWHEN_NEW_FORM_INSTANCE {
select current_timestamp,upper(user) from dual

}
OUMSYPFL_SYSPFL_FIND_UNQIUE_SYSTEM_PROFILES {
	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES where PROFILE_TYPE  = :profileType and PROFILE_CODE  = :profileCode
}
OUMSYPFL_SYSPFL_EMAIL {
select PROFILE_VALUE from system_profiles where PROFILE_TYPE = 'CLIENT'
         AND PROFILE_CODE = 'SMTP_USER_PS'
}
