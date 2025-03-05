
OCUPERPR_FIND_RGPROFILECODE {
 	SELECT
  DESCRIPTION, PROFILE_CODE
FROM PROFILE_CODES
WHERE
  PROFILE_TYPE = :PROFILETYPE AND
  ACTIVE_FLAG = 'Y' AND
  EXPIRY_DATE IS NULL
ORDER BY
  LIST_SEQ, DESCRIPTION

}

OCUPERPR_PROFILES_FIND_PERSON_PROFILES {
 	SELECT PP.PERSON_PROFILE_ID ,PP.PERSON_ID ,PP.PROFILE_TYPE ,PP.PROFILE_CODE ,PP.DISPLAY_SEQ ,PP.MODIFY_USER_ID ,PP.PROFILE_COMMENT ,PP.CREATE_DATETIME ,
 	PP.CREATE_USER_ID ,PP.MODIFY_DATETIME ,PP.SEAL_FLAG, PT.code_value_type AS CODE_VALUE_TYPE   FROM PERSON_PROFILES PP inner join profile_types PT 
 	on (PP.profile_type=PT.profile_type) WHERE PERSON_ID=:PERSONID ORDER BY DISPLAY_SEQ
 	}
OCUPERPR_PROFILES_UPDATE_PERSON_PROFILES {
	UPDATE PERSON_PROFILES set PERSON_ID  = :personId ,PROFILE_TYPE  = :profileType ,
	PROFILE_CODE  = :profileCode ,DISPLAY_SEQ  = :displaySeq ,MODIFY_USER_ID  = :modifyUserId ,PROFILE_COMMENT  = :profileComment ,
	MODIFY_DATETIME  = :modifyDatetime ,SEAL_FLAG  = :sealFlag 
	WHERE PERSON_PROFILE_ID  = :personProfileId 
}

OCUPERPR_PROFILES_PREINSERT_ {
	SELECT NEXTVAL('PERSON_PROFILE_ID') FROM DUAL
}
OCUPERPR_PROFILES_TYPES {
 SELECT DESCRIPTION FROM PROFILE_TYPES WHERE PROFILE_TYPE = :PROFILETYPE AND PROFILE_CATEGORY='VP'
}
OCUPERPR_PROFILES_CODES {

 SELECT DESCRIPTION FROM PROFILE_CODES WHERE PROFILE_CODE = :PROFILE_CODE 
}
OCUPERPR_INSERT_PROFILE_TYPES{
INSERT INTO person_profiles (person_profile_id,
                                 person_id,
                                 profile_type,
                                 display_seq)
       SELECT person_profile_id.NEXTVAL,
              :PERSON_ID,
              pt.profile_type,
              pt.list_seq
         FROM profile_types pt
        WHERE     profile_category = 'VP'
              AND active_flag = 'Y'
              AND expiry_date IS NULL
              AND NOT EXISTS
                         (SELECT 1
                            FROM person_profiles a
                           WHERE a.person_id = :PERSON_ID
                                 AND a.profile_type = pt.profile_type)
} 
