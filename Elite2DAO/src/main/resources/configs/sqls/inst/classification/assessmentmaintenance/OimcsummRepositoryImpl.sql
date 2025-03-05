
OIMCSUMM_FIND_RGCASEWORK {
 	SELECT RC.DESCRIPTION , RC.CODE FROM REFERENCE_CODES RC WHERE RC.DOMAIN = 'CASEPLAN_STP' AND (::MODE = 'ENTER-QUERY' OR ACTIVE_FLAG = 'Y' ) ORDER BY DESCRIPTION , CODE
}

OIMCSUMM_FIND_RGPROGRAMID {
 	SELECT PS.DESCRIPTION ,to_char(PS.PROGRAM_ID) CODE FROM PROGRAM_SERVICES PS WHERE PS.ACTIVE_FLAG ='Y' AND PS.PROGRAM_CATEGORY = :PROGRAMCATEGORY   AND PS.EXPIRY_DATE IS NULL
}

OIMCSUMM_FIND_RGCASEPLANASS {
 	SELECT DESCRIPTION , CODE FROM REFERENCE_CODES WHERE DOMAIN = 'CASEPLAN_ASS' AND (::MODE = 'ENTER-QUERY' OR ACTIVE_FLAG = 'Y' ) ORDER BY DESCRIPTION , CODE
}

OIMCSUMM_FIND_CGFKNEXTSECTION {
 	SELECT DESCRIPTION ,ASSESSMENT_ID ,ASSESSMENT_CODE FROM ASSESSMENTS WHERE PARENT_ASSESSMENT_ID = ::ASSESSMENTID AND ASSESSMENT_ID != ::ASSESSMENTID ORDER BY LIST_SEQ
}

OIMCSUMM_FIND_CGFKSECTIONCODE {
 	SELECT DESCRIPTION ,ASSESSMENT_CODE CODE, ASSESSMENT_ID FROM ASSESSMENTS WHERE PARENT_ASSESSMENT_ID = :ASSESSMENTID ORDER BY LIST_SEQ
}

OIMCSUMM_FIND_CGFKSCORETYPE {
 	SELECT 'SECTION SCORE' DESCRIPTION , 'SECTION' SCORE_TYPE FROM DUAL UNION SELECT 'TOTAL SCORE' , 'TOTAL' FROM DUAL
}

OIMCSUMM_FIND_RGPRGCATEGORY {
 	SELECT REF_CD_PS_CAT.DESCRIPTION  DESCRIPTION, REF_CD_PS_CAT.CODE         CODE  FROM   REFERENCE_CODES   REF_CD_PS_CAT        ,REFERENCE_CODES   REF_CD_PLAN_ACT      WHERE  REF_CD_PLAN_ACT.DOMAIN         = 'PLAN_ACT_PRG'   AND  (REF_CD_PLAN_ACT.ACTIVE_FLAG = 'Y' OR ::MODE <> 'NORMAL' )   AND  REF_CD_PLAN_ACT.PARENT_DOMAIN  = REF_CD_PS_CAT.DOMAIN    AND  REF_CD_PLAN_ACT.PARENT_CODE    = REF_CD_PS_CAT.CODE      AND  (REF_CD_PS_CAT.ACTIVE_FLAG = 'Y' OR ::MODE <> 'NORMAL' )    AND  REF_CD_PS_CAT.DOMAIN = 'PS_CATEGORY'    AND  REF_CD_PLAN_ACT.PARENT_CODE = REF_CD_PLAN_ACT.CODE  ORDER BY  REF_CD_PLAN_ACT.LIST_SEQ  ASC            ,REF_CD_PS_CAT.DESCRIPTION ASC
}

OIMCSUMM_ASSESSMENTS_FIND_ASSESSMENTS {
 	SELECT  * FROM ASSESSMENTS  where  PARENT_ASSESSMENT_ID =:assessmentId ORDER BY LIST_SEQ
}
OIMCSUMM_VASSOFFNEEDS_FIND_V_ASS_OFF_NEEDS {
 	SELECT   * FROM V_ASS_OFF_NEEDS   where  ASSESSMENT_ID =:assessmentId
}
OIMCSUMM_VASSOFFNEEDS_INSERT_V_ASS_OFF_NEEDS {
	INSERT INTO V_ASS_OFF_NEEDS(ASSESSMENT_ID,OFF_ASS_NEED_ID,ASSESSED_NEED_CODE,OBJECTIVE,TARGET_DATE,ACTIVE_FLAG,EXPIRY_DATE,create_user_id) VALUES(:assessmentId,nextval('off_ass_need_id'),:assessedNeedCode,:objective,:targetDate,:activeFlag,:expiryDate,:createUserId)
}

OIMCSUMM_VASSOFFNEEDS_UPDATE_V_ASS_OFF_NEEDS {
update ASSESSED_OFFENDER_NEEDS set ASSESSED_NEED_CODE =:assessedNeedCode,OBJECTIVE=:objective,ACTIVE_FLAG=:activeFlag,EXPIRY_DATE=:expiryDate,
	modify_datetime =current_timestamp ,modify_user_id =:modifyUserId  where OFF_ASS_NEED_ID=:offAssNeedId
}

OIMCSUMM_VASSOFFNEEDS_DELETE_V_ASS_OFF_NEEDS { 
DELETE FROM ASSESSED_OFFENDER_NEEDS  where OFF_ASS_NEED_ID=:offAssNeedId
}

OIMCSUMM_VASSTREATPROTS_FIND_V_ASS_TREAT_PROTS {
 	select * from V_ASS_TREAT_PROTS where OFF_ASS_NEED_ID=:offAssNeedId
}
OIMCSUMM_VASSTREATPROTS_INSERT_V_ASS_TREAT_PROTS {
INSERT INTO ASSESSED_TREATMENT_PROTOCOLS (off_ass_need_id
                                             ,treatment_id
                                             ,casework_type
                                             ,program_id
                                             ,max_score
                                             ,min_score
                                             ,referral_flag
                                             ,active_flag
                                             ,expiry_date,
                                             create_user_id,
                                             create_datetime,
                                             MODIFY_USER_ID,
                                             MODIFY_DATETIME
                                             )
                                 VALUES (:offAssNeedId
                                        ,NEXTVAL('TREATMENT_ID')
                                        ,:caseworkType
                                        ,:programId
                                        ,:maxScore
                                        ,:minScore
                                        ,:referralFlag
                                        ,:activeFlag
                                        ,:expiryDate,
                                         :createUserId,
                                          current_timestamp,
                                          null,null
                                          )
}

OIMCSUMM_VASSTREATPROTS_UPDATE_V_ASS_TREAT_PROTS {
update
	ASSESSED_TREATMENT_PROTOCOLS
set
	CASEWORK_TYPE =:caseworkType,
	PROGRAM_ID =:programId,
	REFERRAL_FLAG =:referralFlag,
	MAX_SCORE =:maxScore,
	MIN_SCORE =:minScore,
	ACTIVE_FLAG =:activeFlag,
	EXPIRY_DATE =:expiryDate,
	modify_user_id =:modifyUserId,
	modify_datetime = current_timestamp 
where
	TREATMENT_ID =:treatmentId
}

OIMCSUMM_VASSTREATPROTS_DELETE_V_ASS_TREAT_PROTS { 
 DELETE FROM ASSESSED_TREATMENT_PROTOCOLS ATP  WHERE ATP.TREATMENT_ID = :treatmentId
}


OIMCSUMM_ASSESSMENTS_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM V_ASS_OFF_NEEDS V WHERE V.ASSESSMENT_ID = :ASSESSMENTID
}

OIMCSUMM_V_ASS_OFF_NEEDS_PREINSERT_ {
	SELECT OFF_ASS_NEED_ID.NEXTVAL FROM DUAL
}

OIMCSUMM_V_ASS_OFF_NEEDS_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM V_ASS_TREAT_PROTS V WHERE V.OFF_ASS_NEED_ID = :OFFASSNEEDID
}

OIMCSUMM_V_ASS_TREAT_PROTS_PREINSERT_ {
	SELECT TREATMENT_ID.NEXTVAL FROM DUAL
}

OIMCSUMM_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}
