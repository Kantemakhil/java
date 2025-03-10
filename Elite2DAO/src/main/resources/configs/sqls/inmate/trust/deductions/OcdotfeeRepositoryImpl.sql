
OCDOTFEE_FIND_CGFKOFFDEDDSPDESCRIPTION {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION  ,REF_CODE.CODE  CODE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'DED_STATUS' 
 	AND (ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL )  ORDER BY  REF_CODE.CODE ASC ,REF_CODE.DESCRIPTION ASC
}

OCDOTFEE_FIND_CGFKOFFDEDDEDUCTIONTYPE {
 	SELECT DED_TYPE.DEDUCTION_TYPE  DEDUCTION_TYPE  /* CG$FK */        ,DED_TYPE.DEDUCTION_DESC  DEDUCTION_DESC FROM   DEDUCTION_TYPES DED_TYPE WHERE   DEDUCTION_CATEGORY = 'DTF' AND 
 	DED_TYPE.DEDUCTION_TYPE IN (SELECT CDD.DEDUCTION_TYPE  FROM CASELOAD_DEDUCTION_DETAILS CDD WHERE CASELOAD_ID = :caseloadId ) ORDER BY  DED_TYPE.DEDUCTION_TYPE ASC,
 	DED_TYPE.DEDUCTION_DESC ASC
}

OCDOTFEE_FIND_CGFKOFFDRRECEIPTTXNTYPE {
SELECT TXN_TYPE.TXN_TYPE CODE  ,TXN_TYPE.DESCRIPTION DESCRIPTION, 
CASE WHEN TXN_USAGE IN ('R' ,'D' ) AND CREDIT_OBLIGATION_TYPE IS NULL AND ACTIVE_FLAG = 'Y' AND ALL_CASELOAD_FLAG = 'Y' THEN 'Y'
ELSE 'N' END ACTIVE_FLAG FROM TRANSACTION_TYPES TXN_TYPE  ORDER BY TXN_TYPE.TXN_TYPE ASC  ,TXN_TYPE.DESCRIPTION ASC 	
}

OCDOTFEE_OFFDED_FIND_OFFENDER_DEDUCTIONS {
 	SELECT OFFENDER_DEDUCTION_ID ,CASELOAD_ID ,OFFENDER_ID ,CREDIT_LIMIT ,DEDUCTION_TYPE ,DEDUCTION_STATUS ,DEDUCTION_PRIORITY ,INFORMATION_NUMBER ,DEDUCTION_PERCENTAGE ,
 	PROCESS_PRIORITY_NUMBER ,EFFECTIVE_DATE ,COMMENT_TEXT ,FIFO_FLAG ,PAYEE_PERSON_ID ,PAYEE_CORPORATE_ID ,MAX_MONTHLY_AMOUNT ,MAX_TOTAL_AMOUNT ,DEDUCTION_AMOUNT ,ADJUSTMENT_REASON_CODE ,
 	ADJUSTMENT_AMOUNT ,ADJUSTMENT_USER_ID ,ADJUSTMENT_TXN_ID ,ADJUSTMENT_TEXT ,MODIFY_DATE ,PAY_DEDUCTION_FLAG ,MAX_RECURSIVE_AMOUNT ,GROUP_ID ,CASE_ID ,PARENT_DEDUCTION_ID ,JS_STATUS ,
 	COLLECT_AGENCY_AMOUNT ,COLLECT_AGENCY_FLAG ,COLLECT_SENT_DATE ,OFFENDER_PAYMENT_PROFILE_ID ,SEAL_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID  
 	FROM OFFENDER_DEDUCTIONS  where  CASELOAD_ID =:caseloadId and OFFENDER_ID =:offenderId and DEDUCTION_TYPE IN (SELECT cdd.DEDUCTION_TYPE FROM CASELOAD_DEDUCTION_DETAILS cdd
WHERE CASELOAD_ID = :caseloadId) AND deduction_type IN (SELECT dt.deduction_type FROM deduction_types dt WHERE deduction_category = 'DTF' AND caseload_code in ('BOTH', :caseloadType))
}
OCDOTFEE_OFFDED_INSERT_OFFENDER_DEDUCTIONS {
	INSERT INTO OFFENDER_DEDUCTIONS(OFFENDER_DEDUCTION_ID ,CASELOAD_ID ,OFFENDER_ID ,CREDIT_LIMIT ,DEDUCTION_TYPE ,DEDUCTION_STATUS ,DEDUCTION_PRIORITY ,INFORMATION_NUMBER ,DEDUCTION_PERCENTAGE ,PROCESS_PRIORITY_NUMBER ,EFFECTIVE_DATE ,COMMENT_TEXT ,FIFO_FLAG ,PAYEE_PERSON_ID ,PAYEE_CORPORATE_ID ,MAX_MONTHLY_AMOUNT ,MAX_TOTAL_AMOUNT ,DEDUCTION_AMOUNT ,ADJUSTMENT_REASON_CODE ,ADJUSTMENT_AMOUNT ,ADJUSTMENT_USER_ID ,ADJUSTMENT_TXN_ID ,ADJUSTMENT_TEXT ,MODIFY_DATE ,PAY_DEDUCTION_FLAG ,MAX_RECURSIVE_AMOUNT ,GROUP_ID ,CASE_ID ,PARENT_DEDUCTION_ID ,JS_STATUS ,COLLECT_AGENCY_AMOUNT ,COLLECT_AGENCY_FLAG ,COLLECT_SENT_DATE ,OFFENDER_PAYMENT_PROFILE_ID ,SEAL_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME  ) VALUES(:offenderDeductionId ,:caseloadId ,:offenderId ,:creditLimit ,:deductionType ,:deductionStatus ,:deductionPriority ,:informationNumber ,:deductionPercentage ,:processPriorityNumber ,:effectiveDate ,:commentText ,:fifoFlag ,:payeePersonId ,:payeeCorporateId ,:maxMonthlyAmount ,:maxTotalAmount ,:deductionAmount ,:adjustmentReasonCode ,:adjustmentAmount ,:adjustmentUserId ,:adjustmentTxnId ,:adjustmentText ,:modifyDate ,:payDeductionFlag ,:maxRecursiveAmount ,:groupId ,:caseId ,:parentDeductionId ,:jsStatus ,:collectAgencyAmount , 'N' ,:collectSentDate ,:offenderPaymentProfileId ,:sealFlag , current_timestamp ,:createUserId , null  )
}

OCDOTFEE_OFFDED_UPDATE_OFFENDER_DEDUCTIONS {
UPDATE OFFENDER_DEDUCTIONS set DEDUCTION_STATUS  = :deductionStatus, modify_user_id = :modifyUserId, modify_datetime= current_timestamp WHERE OFFENDER_DEDUCTION_ID  = :offenderDeductionId
}

OCDOTFEE_OFFDED_DELETE_OFFENDER_DEDUCTIONS { 
	DELETE FROM OFFENDER_DEDUCTIONS WHERE OFFENDER_DEDUCTION_ID  = :offenderDeductionId
}

OCDOTFEE_OFFDR_FIND_OFFENDER_DEDUCTION_RECEIPTS {
 	SELECT LIST_SEQ ,RECEIPT_PERCENTAGE ,RECEIPT_TXN_TYPE ,OFFENDER_DEDUCTION_ID ,FLAT_RATE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG  
 	FROM OFFENDER_DEDUCTION_RECEIPTS WHERE OFFENDER_DEDUCTION_ID = :offenderDeductionId ORDER BY RECEIPT_TXN_TYPE
}
OCDOTFEE_OFFDR_INSERT_OFFENDER_DEDUCTION_RECEIPTS {
	INSERT INTO OFFENDER_DEDUCTION_RECEIPTS (OFFENDER_DEDUCTION_ID, RECEIPT_TXN_TYPE, RECEIPT_PERCENTAGE, FLAT_RATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME) VALUES (:offenderDeductionId, :receiptTxnType, :receiptPercentage, :flatRate,current_timestamp,:createUserId,current_timestamp)
}

OCDOTFEE_OFFDR_UPDATE_OFFENDER_DEDUCTION_RECEIPTS {
	UPDATE OFFENDER_DEDUCTION_RECEIPTS set RECEIPT_PERCENTAGE = :receiptPercentage, FLAT_RATE = :flatRate ,MODIFY_DATETIME =current_timestamp,MODIFY_USER_ID =:modifyUserId WHERE RECEIPT_TXN_TYPE = :receiptTxnType AND OFFENDER_DEDUCTION_ID = :offenderDeductionId
}

OCDOTFEE_OFFDR_DELETE_OFFENDER_DEDUCTION_RECEIPTS { 
	DELETE FROM OFFENDER_DEDUCTION_RECEIPTS WHERE RECEIPT_TXN_TYPE = :receiptTxnType AND OFFENDER_DEDUCTION_ID = :offenderDeductionId
}

OCDOTFEE_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OCDOTFEE_CGFKCHK_OFF_DED_OFF_DED_REF_C {
	SELECT REF_CODE.DESCRIPTION FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :deductionStatus AND     DOMAIN = 'DED_STATUS' AND
	((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL) OR 'QUERY' = 'QUERY')
}

OCDOTFEE_CGFKLKP_OFF_DED_OFF_DED_REF_C_ {
	SELECT REF_CODE.CODE FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.DESCRIPTION = :DSPDESCRIPTION AND     DOMAIN = 'DED_STATUS' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL) OR :MODE = 'QUERY')
}

OCDOTFEE_CGFKCHK_OFF_DED_OFF_DED_DED_T{
	SELECT DED_TYPE.DEDUCTION_DESC FROM   DEDUCTION_TYPES DED_TYPE WHERE  DED_TYPE.DEDUCTION_TYPE = :deductionType AND     DEDUCTION_CATEGORY = 'DTF' 
	AND DED_TYPE.DEDUCTION_TYPE IN (SELECT CDD.DEDUCTION_TYPE FROM CASELOAD_DEDUCTION_DETAILS CDD WHERE CASELOAD_ID = :caseloadId)
}

OCDOTFEE_CGFKCHK_OFF_DR_OFF_DR_TXN_TYP_ {
	SELECT TXN_TYPE.DESCRIPTION FROM   TRANSACTION_TYPES TXN_TYPE WHERE  TXN_TYPE.TXN_TYPE = :RECEIPTTXNTYPE AND    TXN_USAGE IN ('R','D') --      AND    CREDIT_OBLIGATION_TYPE IS NULL AND    ACTIVE_FLAG = 'Y' AND    ALL_CASELOAD_FLAG = 'Y'
}

OCDOTFEE_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}
OCDOTFEE_CHECK_UNIQUE_CONSTRAINT {
	SELECT 'X' FROM offender_deductions WHERE caseload_id = :caseloadId  AND offender_id = :offenderId AND deduction_type = :deductionType
         AND deduction_priority = 1
}
OCDOTFEE_RECORD_EXISTS_C {
	SELECT offender_deduction_id FROM offender_deduction_receipts WHERE offender_deduction_id = :offenderDeductionId
}
OCDOTFEE_DETAILS_C {
	SELECT RECEIPT_TXN_TYPE, PERCENTAGE RECEIPT_PERCENTAGE, FLAT_RATE FROM caseload_deduction_details  WHERE deduction_type = :deductionType AND caseload_id = :caseloadId
       UNION SELECT receipt_txn_type, percentage, flat_rate  FROM caseload_deduction_details  WHERE deduction_type = :deductionType AND caseload_id NOT IN 
        (SELECT caseload_id FROM caseloads WHERE caseload_type = :caseloadType)
}
OCDOTFEE_BENEFICIARY_ID {
	--SELECT BENEFICIARY_ID.NEXTVAL FROM DUAL
	SELECT NEXTVAL('BENEFICIARY_ID') FROM DUAL;

}
OCDOTFEE_GET_PAYEE_CORPORATE_ID {
	 SELECT payee_corporate_id FROM caseload_deduction_profiles WHERE caseload_id = :caseloadId  AND deduction_type = :deductionType
}
OCDOTFEE_GET_UNKNOWN_BEN_ID {
	--SELECT UNKNOWN_BEN_ID.NEXTVAL FROM DUAL
		SELECT NEXTVAL('UNKNOWN_BEN_ID')FROM DUAL

}
OCDOTFEE_INSERT_INTO_OFFENDER_BENEFICIARIES {
	INSERT INTO OFFENDER_BENEFICIARIES ( BENEFICIARY_ID, OFFENDER_DEDUCTION_ID, OFFENDER_ID, PERSON_ID, CORPORATE_ID, UNKNOWN_BEN_ID, PRIORITY, AMOUNT, PERCENT, OVERRIDE_AMOUNT, RECEIVED_AMOUNT, COMMENT_TEXT, MODIFY_DATETIME,create_user_id,create_datetime)
VALUES (:beneficiaryId, :offenderDeductionId, :offenderId, NULL, :corporateId, :unknownBenId, 1, 99999999, 100, NULL, NULL, NULL, current_timestamp,:createUserId,current_timestamp)

}
OCDOTFEE_GET_DEDUCTION_ID {
	--SELECT DEDUCTION_ID.NEXTVAL FROM DUAL
	select NEXTVAL('DEDUCTION_ID') FROM DUAL

}
OCDOTFEE_GET_INFO_SEQ {
	SELECT COUNT (*)  FROM offender_deductions  WHERE offender_id = :offenderId  AND deduction_type = :deductionType
               AND SUBSTR (information_number, 1, 8) = :informationNumber
}
OCDOTFEE_OFFDED_DELETE_OFFENDER_BENEFICIARIES {
	DELETE FROM offender_beneficiaries    WHERE offender_deduction_id = :offenderDeductionId
}
OCDOTFEE_GET_PERCENTAGE_AND_FLAT_RATE {
	SELECT PERCENTAGE RECEIPT_PERCENTAGE, FLAT_RATE FROM CASELOAD_DEDUCTION_DETAILS WHERE CASELOAD_ID = :caseloadId AND DEDUCTION_TYPE = :deductionType AND RECEIPT_TXN_TYPE = :receiptTxnType
}
OCDOTFEE_OFFENDER_OLD_DATA{
select * from OFFENDER_DEDUCTIONS where  offender_deduction_id = :offender_deduction_id 
}