
OCDCASHR_FIND_CGFKGLTXNACCOUNTCODE {
 	SELECT AC_CODE.ACCOUNT_CODE /* CG$FK */  ,AC_CODE.ACCOUNT_NAME DESCRIPTION  ,AC_CODE.ACCOUNT_TYPE  ,AC_CODE.TXN_POSTING_TYPE  ,AC_CODE.SUB_ACCOUNT_TYPE FROM ACCOUNT_CODES AC_CODE WHERE AC_CODE.ACCOUNT_CODE IN (SELECT BANK_DR_ACCOUNT_CODE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OCDCASHR' AND CASELOAD_ID = :CASELOADID AND ((TXN_TYPE = 'CR' AND :CASELOADTYPE = 'INST' ) OR (TXN_TYPE = 'CR1' AND :CASELOADTYPE = 'COMM' ) ) ) AND AC_CODE.CASELOAD_TYPE = :CASELOADTYPE
}
OCDCASHR_GLTXN_FIND_GL_TRANSACTIONS {
 	SELECT TXN_ID ,TXN_ENTRY_SEQ ,GL_ENTRY_SEQ ,ACCOUNT_PERIOD_ID ,ACCOUNT_CODE ,TXN_ENTRY_DATE ,TXN_TYPE ,TXN_POST_USAGE ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DESC ,TXN_REFERENCE_NUMBER ,BANK_STATEMENT_DATE ,RECON_CLEAR_FLAG ,TXN_REVERSED_FLAG ,REVERSED_TXN_ID ,PAYEE_PERSON_ID ,REVERSED_TXN_ENTRY_SEQ ,REVERSED_GL_ENTRY_SEQ ,PAYEE_CORPORATE_ID ,PAYEE_NAME_TEXT ,TXN_OBJECT_CODE ,LIST_SEQ ,TXN_OBJECT_ID ,CREATE_USER_ID ,CREATE_DATE ,INFO_NUMBER ,DEDUCTION_ID ,TXN_ENTRY_TIME ,RECEIPT_NUMBER ,REVERSAL_REASON_CODE ,TXN_LOC_ID ,PAYEE_CLEAR_FLAG ,CREATE_DATETIME ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM GL_TRANSACTIONS  /* where  */
}
OCDCASHR_GLTXN_INSERT_GL_TRANSACTIONS {
	INSERT INTO GL_TRANSACTIONS(TXN_ID ,TXN_ENTRY_SEQ ,GL_ENTRY_SEQ ,ACCOUNT_PERIOD_ID ,ACCOUNT_CODE ,TXN_ENTRY_DATE ,TXN_TYPE ,TXN_POST_USAGE ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DESC ,TXN_REFERENCE_NUMBER ,BANK_STATEMENT_DATE ,RECON_CLEAR_FLAG ,TXN_REVERSED_FLAG ,REVERSED_TXN_ID ,PAYEE_PERSON_ID ,REVERSED_TXN_ENTRY_SEQ ,REVERSED_GL_ENTRY_SEQ ,PAYEE_CORPORATE_ID ,PAYEE_NAME_TEXT ,TXN_OBJECT_CODE ,LIST_SEQ ,TXN_OBJECT_ID ,CREATE_USER_ID ,CREATE_DATE ,INFO_NUMBER ,DEDUCTION_ID ,TXN_ENTRY_TIME ,RECEIPT_NUMBER ,REVERSAL_REASON_CODE ,TXN_LOC_ID ,PAYEE_CLEAR_FLAG ,CREATE_DATETIME ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG ) VALUES(:txnId ,:txnEntrySeq ,:glEntrySeq ,:accountPeriodId ,:accountCode ,:txnEntryDate ,:txnType ,:txnPostUsage ,:caseloadId ,:offenderId ,:offenderBookId ,:txnEntryAmount ,:txnEntryDesc ,:txnReferenceNumber ,:bankStatementDate ,:reconClearFlag ,:txnReversedFlag ,:reversedTxnId ,:payeePersonId ,:reversedTxnEntrySeq ,:reversedGlEntrySeq ,:payeeCorporateId ,:payeeNameText ,:txnObjectCode ,:listSeq ,:txnObjectId ,:createUserId ,:createDate ,:infoNumber ,:deductionId ,:txnEntryTime ,:receiptNumber ,:reversalReasonCode ,:txnLocId ,:payeeClearFlag ,CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP ,:modifyUserId ,:sealFlag )
	
}
OCDCASHR_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}
OCDCASHR_SYSPFL_INSERT_SYSTEM_PROFILES {
	INSERT INTO SYSTEM_PROFILES(PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG ) VALUES(:profileType ,:profileCode ,:description ,:profileValue ,:profileValue2 ,:modifyUserId ,:oldTableName ,CURRENT_TIMESTAMP ,:createUserId ,CURRENT_TIMESTAMP ,:sealFlag )
}
OCDCASHR_SYSPFL_DELETE_SYSTEM_PROFILES { 
	DELETE FROM SYSTEM_PROFILES/* where */
}
OCDCASHR_CGFKCHK_GL_TXN_GL_TXN_AC_CODE_ {
	SELECT AC_CODE.ACCOUNT_NAME ,AC_CODE.ACCOUNT_TYPE ,AC_CODE.TXN_POSTING_TYPE ,AC_CODE.SUB_ACCOUNT_TYPE FROM   ACCOUNT_CODES AC_CODE WHERE  AC_CODE.ACCOUNT_CODE = :ACCOUNTCODE AND     AC_CODE.ACCOUNT_CODE IN (SELECT BANK_DR_ACCOUNT_CODE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OCDCASHR' AND CASELOAD_ID = :CASELOADID) AND AC_CODE.CASELOAD_TYPE = :CASELOADTYPE
}
OCDCASHR_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}
OCDCASHR_PROFILE_VALUE_DATA {
SELECT SP.PROFILE_VALUE
   	    FROM SYSTEM_PROFILES SP
   	   WHERE SP.PROFILE_TYPE = 'CLIENT'
   	     AND SP.PROFILE_CODE = 'MULTI_CASH'
}
OCDCASHR_RECEIPT_NUMBER {
	SELECT RECEIPT_NUMBER
            FROM   OFFENDER_TRANSACTIONS
            WHERE  TXN_ID = :TXNID
            AND    TXN_ENTRY_SEQ = :ENTRYSEQ
}
OCDCASHR_OFFENDER_ID_DISPLAY {
	SELECT OFFENDER_ID_DISPLAY
            FROM   OFFENDERS
            WHERE  OFFENDER_ID = :OFFENDERID
}
OCDCASHR_TXN_ENTRY_AMOUNT {
	SELECT ABS(:AMTENT - ABS(:TXNAMT)) 
         FROM   DUAL
}
OCDCASHR_TXN_TYPE_DESCRIPTION {
	SELECT TXN_TYPE,DESCRIPTION FROM   TRANSACTION_TYPES WHERE  TXN_TYPE IN (SELECT TXN_TYPE FROM   TRANSACTION_OPERATIONS WHERE  MODULE_NAME = :MODULENAME AND    CASELOAD_ID = :CASELOADID) AND    CASELOAD_TYPE = :CASELOADTYPE
}
OCDCBENE_TXN_ID {
	--SELECT TXN_ID.NEXTVAL FROM DUAL
	select nextval('txn_id') from dual
}
OCDCASHR_BANK_CR_ACCOUNT_CODE {
	SELECT BANK_CR_ACCOUNT_CODE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OCDCASHR' AND BANK_DR_ACCOUNT_CODE = :ACCODE AND CASELOAD_ID = :CSLDID
}
OCDCASHR_CR_ACCOUNT_CODE {
	SELECT CR_ACCOUNT_CODE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OCDCASHR' AND BANK_DR_ACCOUNT_CODE = :ACCODE AND CASELOAD_ID = :CSLDID
}
OCDCASHR_DR_ACCOUNT_CODE {
	SELECT DR_ACCOUNT_CODE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OCDCASHR' AND BANK_DR_ACCOUNT_CODE = :ACCODE AND CASELOAD_ID = :CSLDID
}
OCDCASHR_ACCOUNT_NAME_TXN_POSTING_TYPE {
	SELECT A.ACCOUNT_NAME,A.TXN_POSTING_TYPE FROM ACCOUNT_CODES A WHERE A.ACCOUNT_CODE = :NBTACCODE
}
OCDCASHR_TXN_POSTING_TYPE {
	SELECT A.TXN_POSTING_TYPE FROM ACCOUNT_CODES A WHERE A.ACCOUNT_CODE = :ACCODE
}
OCDCASHR_PROFILE_VALUE_DATA {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'MULTI_CASH'
}
OCDCASHR_TXN_AMOUNT {
SELECT SUM (TXN_ENTRY_AMOUNT * CASE  AC.TXN_POSTING_TYPE  WHEN GL.TXN_POST_USAGE THEN  1  ELSE -1 END)
           FROM GL_TRANSACTIONS GL, ACCOUNT_CODES AC
          WHERE GL.CASELOAD_ID = :CASEID
            AND AC.ACCOUNT_CODE = :ACCD
            AND GL.ACCOUNT_CODE = :ACCD
            AND GL.TXN_TYPE != 'SDEP' -- SQLINES DEMO *** R#16111
            AND EXISTS (SELECT '1'
                          FROM GL_TRANSACTIONS GL1
                         WHERE GL1.ACCOUNT_CODE = :ACCD
                           AND GL1.TXN_ID = GL.TXN_ID
                           AND GL1.TXN_ENTRY_SEQ = GL.TXN_ENTRY_SEQ)
            AND GL.TXN_ID > (SELECT COALESCE (MAX (GL2.TXN_ID), 0)
                               FROM GL_TRANSACTIONS GL2
                              WHERE GL2.ACCOUNT_CODE = :ACCD
                                AND GL2.TXN_TYPE = CASE  :CASETYPE  WHEN 'COMM' THEN  'CR1'  ELSE 'CR' END
                                AND GL2.CREATE_USER_ID = CASE  :LVMULTICASH  WHEN 'Y' THEN  :CURRENT_USER  ELSE GL2.CREATE_USER_ID END)
            AND GL.CREATE_USER_ID = CASE  :LVMULTICASH  WHEN 'Y' THEN  :CURRENT_USER  ELSE GL.CREATE_USER_ID END
}
OCDCASHR_TXN_AMOUNT_SLASHES {
	SELECT LTRIM (RTRIM (TO_CHAR (:TXNAMOUNT::numeric, '99999990.00'))) DD FROM DUAL
}


OCDCASHR_GET_OLD_RECORDS_SYSTEMPROFILES {
select * from system_profiles where profile_type=:profile_type and profile_code=:profile_code
}