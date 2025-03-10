
OIDCIPON_FIND_RGPLACEMENTREASON {
	SELECT CODE , DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN= 'PLACE_RSN' AND PARENT_CODE = :PLACEMENTTYPE AND 
	ACTIVE_FLAG ='Y' AND EXPIRED_DATE IS NULL  ORDER BY LIST_SEQ;
}

OIDCIPON_FIND_RGPLACEMENTTYPE {
 	SELECT CODE , DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN= 'PLACE_TYPE' AND ((ACTIVE_FLAG ='Y' AND EXPIRED_DATE IS NULL ) OR :MODE = 'QUERY' ) 
 	ORDER BY LIST_SEQ
}

OIDCIPON_FIND_RGAGYLOCS {
 	SELECT AL.AGY_LOC_ID code , AL.DESCRIPTION description FROM AGENCY_LOCATIONS AL , CASELOAD_AGENCY_LOCATIONS CAL WHERE CAL.CASELOAD_ID = :CASELOADID 
 	AND CAL.AGY_LOC_ID NOT IN ('TRN' ,'OUT' ) AND CAL.AGY_LOC_ID = AL.AGY_LOC_ID AND ((AL.DEACTIVATION_DATE IS NULL ) OR :MODE = 'QUERY' ) 
 	ORDER BY AL.AGY_LOC_ID
}

OIDCIPON_FIND_RGREQUESTEDBY {
  SELECT CODE , DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN= 'CIP_REQ_BY' AND ((ACTIVE_FLAG ='Y' AND EXPIRED_DATE IS NULL ) OR '' = 'QUERY' ) 
  ORDER BY LIST_SEQ
}

OIDCIPON_FIND_RGAUTHORIZEDBY {
 	SELECT CODE , DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN= 'CIP_AUTH' AND ((ACTIVE_FLAG ='Y' AND EXPIRED_DATE IS NULL ) OR ''= 'QUERY' ) ORDER BY LIST_SEQ

}

OIDCIPON_FIND_RGDURATIONTYPE {
SELECT DURATION_TYPE , MINIMUM_DURATION  MINIMUM_DURATION , MAXIMUM_DURATION  MAXIMUM_DURATION, ACTIVE_FLAG FROM PLACEMENT_DURATIONS
WHERE PLACEMENT_TYPE = :PLACEMENTTYPE AND ((ACTIVE_FLAG ='Y' AND EXPIRY_DATE IS NULL ) OR :MODE = 'QUERY' ) ORDER BY LIST_SEQ

}

OIDCIPON_FIND_RGRELEASEDBY {
 	SELECT CODE , DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN= 'CIP_AUTH' AND ((ACTIVE_FLAG ='Y' AND coalesce (EXPIRED_DATE::timestamp::text,'' )= '')) 
 	ORDER BY LIST_SEQ
}

OIDCIPON_OFFCIPDETAILS_FIND_OFFENDER_CIP_DETAILS {
 	SELECT OFFENDER_BOOK_ID ,PLACEMENT_SEQ ,PLACEMENT_TYPE ,PLACEMENT_REASON_CODE ,AGY_LOC_ID ,REQ_BY_PER_CODE ,AUTH_BY_PER_CODE ,AUTH_BY_PER_NAME 
 	,EFFECTIVE_DATE ,EFFECTIVE_TIME ,DURATION_TYPE ,DURATION ,REVIEW_DATE ,EXPIRY_DATE ,EXPIRY_TIME ,COMMENT_TEXT ,REL_BY_PER_CODE ,REL_BY_PER_NAME 
 	,RELEASE_DATE ,RELEASE_TIME ,MODIFY_USER_ID ,MODIFY_DATETIME ,CREATE_DATETIME ,CREATE_USER_ID ,SEAL_FLAG   FROM OFFENDER_CIP_DETAILS 
}
OIDCIPON_OFFCIPDETAILS_INSERT_OFFENDER_CIP_DETAILS {
insert into OFFENDER_CIP_DETAILS(OFFENDER_BOOK_ID , PLACEMENT_SEQ , PLACEMENT_TYPE , PLACEMENT_REASON_CODE , AGY_LOC_ID , REQ_BY_PER_CODE , AUTH_BY_PER_CODE , AUTH_BY_PER_NAME , EFFECTIVE_DATE , EFFECTIVE_TIME , DURATION_TYPE , DURATION , REVIEW_DATE , EXPIRY_DATE , EXPIRY_TIME , COMMENT_TEXT , REL_BY_PER_CODE , REL_BY_PER_NAME , RELEASE_DATE , RELEASE_TIME , CREATE_DATETIME , CREATE_USER_ID , MODIFY_USER_ID,MODIFY_DATETIME,SEAL_FLAG)
values(:offenderBookId , :placementSeq , :placementType , :placementReasonCode , :agyLocId , :reqByPerCode , :authByPerCode , :authByPerName , :effectiveDate , :effectiveTime , :durationType , :duration , :reviewDate , :expiryDate , :expiryTime , :commentText , :relByPerCode , :relByPerName , :releaseDate , :releaseTime , CURRENT_TIMESTAMP, :createUserId, null, null, :sealFlag)
}

OIDCIPON_OFFCIPDETAILS_UPDATE_OFFENDER_CIP_DETAILS {
	UPDATE OFFENDER_CIP_DETAILS set OFFENDER_BOOK_ID  = :offenderBookId ,PLACEMENT_SEQ  = :placementSeq ,PLACEMENT_TYPE  = :placementType ,PLACEMENT_REASON_CODE  = :placementReasonCode ,AGY_LOC_ID  = :agyLocId ,REQ_BY_PER_CODE  = :reqByPerCode ,AUTH_BY_PER_CODE  = :authByPerCode ,AUTH_BY_PER_NAME  = :authByPerName 
	,EFFECTIVE_DATE  = :effectiveDate ,EFFECTIVE_TIME  = :effectiveTime ,DURATION_TYPE  = :durationType ,DURATION  = :duration ,REVIEW_DATE  = :reviewDate ,EXPIRY_DATE  = :expiryDate ,EXPIRY_TIME  = :expiryTime ,COMMENT_TEXT  = :commentText ,REL_BY_PER_CODE  = :relByPerCode ,REL_BY_PER_NAME  = :relByPerName ,
	RELEASE_DATE  = :releaseDate ,RELEASE_TIME  = :releaseTime ,MODIFY_USER_ID  = :modifyUserId ,MODIFY_DATETIME  = CURRENT_TIMESTAMP,SEAL_FLAG  = :sealFlag where OFFENDER_BOOK_ID  = :offenderBookId and PLACEMENT_SEQ  = :placementSeq
}

OIDCIPON_OFFCIPDETAILS_DELETE_OFFENDER_CIP_DETAILS { 
	DELETE FROM OFFENDER_CIP_DETAILS where OFFENDER_BOOK_ID  = :offenderBookId and PLACEMENT_SEQ  = :placementSeq
}

OIDCIPON_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID 
 	,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES 
}

OIDCIPON_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_CIP_DETAILS O WHERE O.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIDCIPON_OFF_CIP_DETAILS_PREINSERT {
	SELECT coalesce(MAX(PLACEMENT_SEQ),0) + 1 FROM OFFENDER_CIP_DETAILS WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIDCIPON_OFF_CIP_DETAILS_POSTQUERY_ {
	SELECT DESCRIPTION FROM AGENCY_LOCATIONS WHERE AGY_LOC_ID = :AGYLOCID
}

OIDCIPON_OFF_CIP_DETAILS_POSTQUERY_ {
	SELECT MINIMUM_DURATION, MAXIMUM_DURATION FROM PLACEMENT_DURATIONS WHERE PLACEMENT_TYPE = :PLACEMENTTYPE AND DURATION_TYPE = :DURATIONTYPE
}

OIDCIPON_OFF_CIP_DETAILS_POSTQUERY_ {
	SELECT DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'CIP_REQ_BY' AND CODE   = :REQBYPERCODE
}

OIDCIPON_OFF_CIP_DETAILS_POSTQUERY_ {
	SELECT DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'PLACE_TYPE' AND CODE   = :PLACEMENTTYPE
}

OIDCIPON_OFF_CIP_DETAILS_POSTQUERY_ {
	SELECT DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'PLACE_RSN' AND PARENT_CODE = :PLACEMENTTYPE AND CODE   = :PLACEMENTREASONCODE
}

OIDCIPON_OFF_CIP_DETAILS_POSTQUERY_ {
	SELECT DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'CIP_AUTH' AND CODE   = :AUTHBYPERCODE
}

OIDCIPON_OFF_CIP_DETAILS_POSTQUERY_ {
	SELECT DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'CIP_AUTH' AND CODE   = :RELBYPERCODE
}

OIDCIPON_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT current_timestamp , user FROM DUAL
}

OIDCIPON_CREATE_FORM_GLOBALSCREATE_FORM_GLOBALS {
	SELECT DESCRIPTION  FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OIDCIPON_DEFAULT_AGY_LOC_ID_ {
	SELECT CAL.AGY_LOC_ID, AL.DESCRIPTION FROM CASELOAD_AGENCY_LOCATIONS CAL, AGENCY_LOCATIONS AL WHERE CAL.CASELOAD_ID = :CASELOADID 
	AND CAL.AGY_LOC_ID NOT IN ('TRN','OUT') AND CAL.AGY_LOC_ID = AL.AGY_LOC_ID  limit  3
}

OIDCIPON_DEFAULT_DURATION_TYPE {
	SELECT DURATION_TYPE, MINIMUM_DURATION, MAXIMUM_DURATION FROM PLACEMENT_DURATIONS WHERE PLACEMENT_TYPE = :PLACEMENTTYPE AND ACTIVE_FLAG ='Y' limit 3
}

OIDCIPON_DT_VALIDATION_FOR_INACTIVE_OFF {
	SELECT OEM.MOVEMENT_TIME, TO_CHAR(OEM.MOVEMENT_TIME,'MON DD YYYY, HH:MI(AM)') TIME_STRING, RC.DESCRIPTION DSP_DESCRIPTION FROM OFFENDER_EXTERNAL_MOVEMENTS
	OEM, REFERENCE_CODES RC WHERE OEM.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND OEM.MOVEMENT_TYPE = RC.CODE AND RC.DOMAIN = 'MOVE_TYPE'
	ORDER BY OEM.MOVEMENT_TIME DESC
}

OIDCIPON_CHECK_FOR_ACTIVE_CP_REC {
	SELECT PLACEMENT_SEQ FROM OFFENDER_CIP_DETAILS WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID AND ((DURATION_TYPE != 'INDEFINITE' AND ((EXPIRY_TIME > current_timestamp AND
	RELEASE_TIME IS NULL) OR (RELEASE_TIME IS NOT NULL AND  RELEASE_TIME > current_timestamp))) OR (DURATION_TYPE = 'INDEFINITE' AND (RELEASE_TIME IS NULL OR RELEASE_TIME 
	> current_timestamp)))
}

OIDCIPON_CHECK_DATE {
	SELECT RELEASE_TIME, EXPIRY_TIME FROM OFFENDER_CIP_DETAILS WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID ORDER BY EFFECTIVE_DATE DESC,EFFECTIVE_TIME DESC
}

OIDCIPON_CHECK_DATE_ {
	SELECT COUNT(*) FROM OFFENDER_CIP_DETAILS WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIDCIPON_OFF_CIP_DETAILS_COMPUTE_HOURS {
SELECT ROUND(EXTRACT(EPOCH FROM (:releaseTime::timestamp - :effectiveTime::timestamp)) / 3600, 2) AS HOURS FROM dual
}

OIDCIPON_OFF_CIP_DETAILS_COMPUTE_MINUTES {
SELECT ROUND((MOD(:LVTTINHOURS::numeric,24)* 60),2) FROM DUAL
}
OIDCIPON_OFF_CIP_DETAILS_COMPUTE_HOURS_FORMAT {
SELECT LPAD(LTRIM( (FLOOR(:lvRelTime::numeric/60))::text),2,'0')||':'||LPAD(LTRIM((ROUND(MOD(:lvRelTime::numeric,60)))::text),2,'0') FROM DUAL
}

OIDCIPON_OFF_CIP_DETAILS_COMPUTE_FLOOR_HOURS {
SELECT  FLOOR(:lvTtInHours/24) FROM DUAL
}
