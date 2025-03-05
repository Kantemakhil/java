
OIMSREQS_FIND_RGCAT {
 	SELECT 
  ref_code.code, ref_code.description
FROM reference_codes ref_code
WHERE
  domain = 'CATEGORY' AND
  ( active_flag = 'Y' )
ORDER BY
  ref_code.list_seq, description

}

OIMSREQS_FIND_RGSENT {
 	SELECT
  ref_code.code, ref_code.description
FROM reference_codes ref_code
WHERE
  domain = 'SENT_TYPE' AND
  ( active_flag = 'Y')
ORDER BY
  ref_code.list_seq, description

}

OIMSREQS_FIND_RGSVCOBL {
 	SELECT
  ref_code.description, ref_code.code
FROM reference_codes ref_code
WHERE
  domain = 'PS_CATEGORY' AND
  ( active_flag = 'Y')
ORDER BY
  ref_code.list_seq, description

}

OIMSREQS_FIND_RGTERMCODE {
SELECT
  ref_code.code, ref_code.description
FROM reference_codes ref_code
WHERE
  domain = 'SENT_TERM' AND
  ( active_flag = 'Y')
ORDER BY
  ref_code.list_seq, description

}

OIMSREQS_FIND_RGREASON {
SELECT
  lur.update_reason_code code, lur.description, lur.active_type
FROM
  legal_update_reasons lur, legal_update_usages lu
WHERE
  lur.update_reason_code = lu.update_reason_code AND
  lu.legal_class = 'SENTENCE' AND
  ( lu.active_flag = 'Y')
ORDER BY
  lur.list_seq, lur.description

}

OIMSREQS_FIND_RGFUNCTIONTYPE {
SELECT
  description, code
FROM reference_codes
WHERE
  domain = 'FUNCTION' AND
  ( active_flag = 'Y')
ORDER BY
  list_seq, description, code

}

OIMSREQS_SENCALC_FIND_SENTENCE_CALC_TYPES {
 	SELECT SENTENCE_CALC_TYPE,
DESCRIPTION,
EXPIRY_DATE,
ACTIVE_FLAG,
LIST_SEQ,
SENTENCE_CATEGORY,
SENTENCE_TYPE,
PROGRAM_METHOD,
HEADER_SEQ,
HEADER_LABEL,
MODIFY_DATETIME,
MODIFY_USER_ID,
CREATE_DATETIME,
CREATE_USER_ID,
FUNCTION_TYPE,
SEAL_FLAG,SANCTIONS_FLAG,CHARGES_FLAG FROM SENTENCE_CALC_TYPES 
}
OIMSREQS_SENCALC_INSERT_SENTENCE_CALC_TYPES {
 insert into SENTENCE_CALC_TYPES(SENTENCE_CALC_TYPE, DESCRIPTION, EXPIRY_DATE, ACTIVE_FLAG, LIST_SEQ, SENTENCE_CATEGORY, SENTENCE_TYPE, PROGRAM_METHOD, HEADER_SEQ, HEADER_LABEL, CREATE_DATETIME, CREATE_USER_ID, FUNCTION_TYPE, MODIFY_DATETIME, SEAL_FLAG, SANCTIONS_FLAG, CHARGES_FLAG) values(:sentenceCalcType, :description, :expiryDate, :activeFlag, :listSeq, :sentenceCategory, :sentenceType, :programMethod, :headerSeq, :headerLabel, CURRENT_TIMESTAMP, :createUserId, :functionType, NULL, :sealFlag, :sanctionsFlag, :chargesFlag) 
}  

OIMSREQS_SENCALC_UPDATE_SENTENCE_CALC_TYPES {
	UPDATE SENTENCE_CALC_TYPES set SENTENCE_CALC_TYPE=:sentenceCalcType, DESCRIPTION=:description,EXPIRY_DATE=:expiryDate, ACTIVE_FLAG=:activeFlag,LIST_SEQ=:listSeq,SENTENCE_CATEGORY=:sentenceCategory, SENTENCE_TYPE=:sentenceType,PROGRAM_METHOD=:programMethod,HEADER_SEQ=:headerSeq, HEADER_LABEL=:headerLabel,MODIFY_DATETIME=CURRENT_TIMESTAMP,MODIFY_USER_ID= :modifyUserId,FUNCTION_TYPE=:functionType,SEAL_FLAG=:sealFlag,SANCTIONS_FLAG=:sanctionsFlag,CHARGES_FLAG=:chargesFlag where SENTENCE_CATEGORY=:sentenceCategory AND SENTENCE_CALC_TYPE=:sentenceCalcType
}  

OIMSREQS_SENCALC_DELETE_SENTENCE_CALC_TYPES { 
	DELETE FROM SENTENCE_CALC_TYPES where SENTENCE_CATEGORY=:sentenceCategory AND SENTENCE_CALC_TYPE=:sentenceCalcType
}  

OIMSREQS_SENTERMS_FIND_SENTENCE_TERMS {
 	SELECT sentence_calc_type, term_code, active_flag, expiry_date, create_datetime, create_user_id, modify_datetime, modify_user_id, sentence_category, seal_flag FROM sentence_terms WHERE sentence_category =:sentenceCategory AND sentence_calc_type =:sentenceCalcType 
}
OIMSREQS_SENTERMS_INSERT_SENTENCE_TERMS {
 insert into SENTENCE_TERMS(SENTENCE_CALC_TYPE, TERM_CODE, ACTIVE_FLAG, EXPIRY_DATE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SENTENCE_CATEGORY, SEAL_FLAG) values(:sentenceCalcType, :termCode, :activeFlag, :expiryDate, CURRENT_TIMESTAMP, :createUserId, NULL, :sentenceCategory, :sealFlag) 
} 

OIMSREQS_SENTERMS_UPDATE_SENTENCE_TERMS {
	UPDATE SENTENCE_TERMS set SENTENCE_CALC_TYPE=:sentenceCalcType,TERM_CODE=:termCode,ACTIVE_FLAG=:activeFlag,EXPIRY_DATE=:expiryDate,MODIFY_DATETIME= CURRENT_TIMESTAMP,MODIFY_USER_ID=:modifyUserId,SENTENCE_CATEGORY=:sentenceCategory,SEAL_FLAG=:sealFlag where SENTENCE_CATEGORY=:sentenceCategory AND SENTENCE_CALC_TYPE=:sentenceCalcType AND TERM_CODE=:termCode
} 

OIMSREQS_SENTERMS_DELETE_SENTENCE_TERMS { 
	DELETE FROM SENTENCE_TERMS where SENTENCE_CATEGORY=:sentenceCategory AND SENTENCE_CALC_TYPE=:sentenceCalcType AND TERM_CODE=:termCode
} 

OIMSREQS_SENUPD_FIND_SENTENCE_UPDATE_REASONS {
 	 SELECT  SENTENCE_CATEGORY,SENTENCE_CALC_TYPE,UPDATE_REASON_CODE,DESCRIPTION,ACTIVE_FLAG,EXPIRY_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,LEGAL_CLASS,SEAL_FLAG FROM SENTENCE_UPDATE_REASONS WHERE sentence_category =:sentenceCategory AND sentence_calc_type =:sentenceCalcType
}
OIMSREQS_SENUPD_INSERT_SENTENCE_UPDATE_REASONS {
 insert into SENTENCE_UPDATE_REASONS(SENTENCE_CATEGORY, SENTENCE_CALC_TYPE, UPDATE_REASON_CODE, DESCRIPTION, ACTIVE_FLAG, EXPIRY_DATE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, LEGAL_CLASS, SEAL_FLAG) values(:sentenceCategory, :sentenceCalcType, :updateReasonCode, :description, :activeFlag, :expiryDate, CURRENT_TIMESTAMP, :createUserId, NULL, :legalClass, :sealFlag) 
} 

OIMSREQS_SENUPD_UPDATE_SENTENCE_UPDATE_REASONS {
	UPDATE SENTENCE_UPDATE_REASONS set SENTENCE_CATEGORY=:sentenceCategory,SENTENCE_CALC_TYPE=:sentenceCalcType,UPDATE_REASON_CODE=:updateReasonCode,DESCRIPTION=:description,ACTIVE_FLAG=:activeFlag,EXPIRY_DATE=:expiryDate,MODIFY_DATETIME=CURRENT_TIMESTAMP,MODIFY_USER_ID= :modifyUserId,LEGAL_CLASS=:legalClass,SEAL_FLAG=:sealFlag where SENTENCE_CATEGORY=:sentenceCategory AND SENTENCE_CALC_TYPE=:sentenceCalcType AND UPDATE_REASON_CODE=:updateReasonCode
} 

OIMSREQS_SENUPD_DELETE_SENTENCE_UPDATE_REASONS { 
	DELETE FROM SENTENCE_UPDATE_REASONS where SENTENCE_CATEGORY=:sentenceCategory AND SENTENCE_CALC_TYPE=:sentenceCalcType AND UPDATE_REASON_CODE=:updateReasonCode
} 


OIMSREQS_SEN_CALC_KEYDELREC {
	SELECT COUNT(*) FROM OFFENDER_SENTENCES WHERE SENTENCE_CATEGORY = :SENTENCECATEGORY AND SENTENCE_CALC_TYPE = :SENTENCECALCTYPE
}

OIMSREQS_SEN_CALC_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM SENTENCE_TERMS S WHERE S.SENTENCE_CATEGORY = :SENTENCECATEGORY AND S.SENTENCE_CALC_TYPE = :SENTENCECALCTYPE
}

OIMSREQS_SEN_CALC_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM SENTENCE_UPDATE_REASONS S WHERE S.SENTENCE_CATEGORY = :SENTENCECATEGORY AND S.SENTENCE_CALC_TYPE = :SENTENCECALCTYPE
}

OIMSREQS_GETNBTSTATUS_UPDATE_REASON {
SELECT description FROM REFERENCE_CODES WHERE domain = 'ACTIVE_TYPE' AND code = :NBTSTATUS
}

GET_REASON_DESC{
	SELECT DESCRIPTION, ACTIVE_TYPE FROM LEGAL_UPDATE_REASONS  WHERE UPDATE_REASON_CODE = :P_CODE
}

OIMSREQS_FETCH_ORDERS_STATUS{
select distinct lr.update_reason_code code , lr.description description,lr.active_flag ,lr.expiry_date from legal_update_usages lu full outer join legal_update_reasons lr on lu.update_reason_code = lr.update_reason_code 
}


OIMSREQS_FETCH_CUSTODY_STATUS{
select status_code code,status_description description,active_flag from legal_custody_statuses
}

OIMSREQS_SAVE_CUSTODY_STATUS{
insert into sentence_custody_status(sentence_category, sentence_calc_type, legal_class, sentence_order_status, custody_status, create_datetime, create_user_id, modify_datetime, seal_flag) values (:sentenceCategory, :sentenceCalcType, :legalClass, :sentenceOrderStatus, :custodyStatus, current_timestamp, :createUserId, NULL, :sealFlag) 
}

OIMSREQS_UPDATE_CUSTODY_STATUS{
update sentence_custody_status set sentence_order_status =:sentenceOrderStatus, custody_status =:custodyStatus, legal_class =:legalClass , modify_user_id =:modifyUserId, modify_datetime = current_timestamp where sentence_order_status =:sentenceOrderStatusTemp and custody_status =:sentenceCustodyStatusTemp and sentence_calc_type =:sentenceCalcType and sentence_category =:sentenceCategory and legal_class =:legalClass 
}

OIMSREQS_DELETE_CUSTODY_STATUS{
delete from sentence_custody_status where sentence_calc_type =:sentenceCalcType and sentence_category =:sentenceCategory and sentence_order_status =:sentenceOrderStatus and custody_status =:custodyStatus and legal_class =:legalClass 
}

OIMSREQS_FETCH_ALL_CUSTODY_VALUES{
select sentence_category,sentence_calc_type,legal_class,sentence_order_status,custody_status,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag from sentence_custody_status where sentence_calc_type=:sentenceCalcType and sentence_category=:sentenceCategory
}
