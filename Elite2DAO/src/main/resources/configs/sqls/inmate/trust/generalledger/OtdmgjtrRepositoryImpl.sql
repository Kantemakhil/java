
OTDMGJTR_FIND_CGFKGLTXNPAYEECORPORATEID {
 	SELECT CORP.CORPORATE_ID   PAYEE_CORPORATE_ID  ,        CORP.CORPORATE_NAME DSP_CORPORATE_NAME   FROM (SELECT CRP.CORPORATE_ID , CRP.CORPORATE_NAME , CRP.CASELOAD_ID           FROM CORPORATES CRP , CORPORATE_TYPES CTP          WHERE CRP.CORPORATE_ID = CTP.CORPORATE_ID            AND CTP.CORPORATE_TYPE NOT IN ('VENDOR' , 'PROGRAM' )         UNION         SELECT CRP2.CORPORATE_ID , CRP2.CORPORATE_NAME , CRP2.CASELOAD_ID           FROM CORPORATES CRP2          WHERE NOT EXISTS          (SELECT 1                   FROM CORPORATE_TYPES CTP2                  WHERE CRP2.CORPORATE_ID = CTP2.CORPORATE_ID ) ) CORP  ORDER BY CORP.CORPORATE_ID
}

OTDMGJTR_FIND_CGFKGLTXNACCOUNTCODE  {
 	SELECT AC_CODE1.ACCOUNT_CODE     ACCOUNT_CODE  ,        AC_CODE1.ACCOUNT_NAME     DSP_ACCOUNT_NAME ,      
  AC_CODE1.TXN_POSTING_TYPE DSP_TXN_POSTING_TYPE ,        AC_CODE1.ACCOUNT_TYPE     DSP_ACCOUNT_TYPE   FROM ACCOUNT_CODES AC_CODE1 
 WHERE POSTING_STATUS_FLAG = 'Y'    AND AC_CODE1.ACCOUNT_CODE NOT IN        (SELECT INVALID_ACCOUNT_CODE           FROM TXN_OPS_INVALID_ACCOUNTS   
       WHERE MODULE_NAME = 'OTDMGJTR'            AND TXN_TYPE = 'GJ' )    AND AC_CODE1.CASELOAD_TYPE = :caseloadType   AND AC_CODE1.ACCOUNT_CODE IN 
       (SELECT CAS1.ACCOUNT_CODE           FROM CASELOAD_ACCOUNT_SUMMARIES CAS1          WHERE CAS1.CASELOAD_ID =:caseloadId         
  AND ACCOUNT_PERIOD_ID =                TRUST_GJ_GET_ACCOUNT_PERIOD(:txnEntryDate ) )
  ORDER BY AC_CODE1.ACCOUNT_CODE
}

   OTDMGJTR_FIND_CGFKGLTXN1ACCOUNTCODE {
 	SELECT AC_CODE.ACCOUNT_CODE     ACCOUNT_CODE  ,        AC_CODE.ACCOUNT_NAME     ACCOUNT_NAME ,        AC_CODE.TXN_POSTING_TYPE TXN_POSTING_TYPE
 	FROM ACCOUNT_CODES AC_CODE  WHERE POSTING_STATUS_FLAG = 'Y'    AND AC_CODE.ACCOUNT_CODE NOT IN        (SELECT INVALID_ACCOUNT_CODE           FROM TXN_OPS_INVALID_ACCOUNTS          WHERE MODULE_NAME = 'OTDMGJTR'            AND TXN_TYPE = 'GJ' ) 
 	AND CASELOAD_TYPE = :CASELOADTYPE    AND AC_CODE.ACCOUNT_CODE IN        (SELECT CAS.ACCOUNT_CODE           FROM CASELOAD_ACCOUNT_SUMMARIES CAS 
 	WHERE CAS.CASELOAD_ID = :CASELOADID )  ORDER BY ACCOUNT_CODE
}

OTDMGJTR_FIND_CGFKGLTXNPAYEEPERSONID {
 	SELECT PER.PERSON_ID  PAYEE_PERSON_ID        ,PER.LAST_NAME  DSP_LAST_NAME        ,PER.FIRST_NAME  DSP_FIRST_NAME FROM   PERSONS PER
}

OTDMGJTR_GLTXN_FIND_GL_TRANSACTIONS {
 	SELECT TXN_ID ,TXN_ENTRY_SEQ ,GL_ENTRY_SEQ ,ACCOUNT_PERIOD_ID ,ACCOUNT_CODE ,TXN_ENTRY_DATE ,TXN_TYPE ,TXN_POST_USAGE ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DESC ,TXN_REFERENCE_NUMBER ,BANK_STATEMENT_DATE ,RECON_CLEAR_FLAG ,TXN_REVERSED_FLAG ,REVERSED_TXN_ID ,PAYEE_PERSON_ID ,REVERSED_TXN_ENTRY_SEQ ,REVERSED_GL_ENTRY_SEQ ,PAYEE_CORPORATE_ID ,PAYEE_NAME_TEXT ,TXN_OBJECT_CODE ,LIST_SEQ ,TXN_OBJECT_ID ,CREATE_USER_ID ,CREATE_DATE ,INFO_NUMBER ,DEDUCTION_ID ,TXN_ENTRY_TIME ,RECEIPT_NUMBER ,REVERSAL_REASON_CODE ,TXN_LOC_ID ,PAYEE_CLEAR_FLAG ,CREATE_DATETIME ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM GL_TRANSACTIONS  /* where  */
}
OTDMGJTR_GLTXN_INSERT_GL_TRANSACTIONS {
insert into GL_TRANSACTIONS(TXN_ID , TXN_ENTRY_SEQ , GL_ENTRY_SEQ , ACCOUNT_PERIOD_ID , ACCOUNT_CODE , TXN_ENTRY_DATE , TXN_TYPE , TXN_POST_USAGE , CASELOAD_ID , OFFENDER_ID , OFFENDER_BOOK_ID , TXN_ENTRY_AMOUNT , TXN_ENTRY_DESC , TXN_REFERENCE_NUMBER , BANK_STATEMENT_DATE , RECON_CLEAR_FLAG , TXN_REVERSED_FLAG , REVERSED_TXN_ID , PAYEE_PERSON_ID , REVERSED_TXN_ENTRY_SEQ , REVERSED_GL_ENTRY_SEQ , PAYEE_CORPORATE_ID , PAYEE_NAME_TEXT , TXN_OBJECT_CODE , LIST_SEQ , TXN_OBJECT_ID , CREATE_USER_ID , CREATE_DATE , INFO_NUMBER , DEDUCTION_ID , TXN_ENTRY_TIME , RECEIPT_NUMBER , REVERSAL_REASON_CODE , TXN_LOC_ID , PAYEE_CLEAR_FLAG , CREATE_DATETIME , MODIFY_DATETIME , SEAL_FLAG ) values(:txnId , :txnEntrySeq , :glEntrySeq , :accountPeriodId , :accountCode , :txnEntryDate , :txnType , :txnPostUsage , :caseloadId , :offenderId , :offenderBookId , :txnEntryAmount , :txnEntryDesc , :txnReferenceNumber , :bankStatementDate , :reconClearFlag , :txnReversedFlag , :reversedTxnId , :payeePersonId , :reversedTxnEntrySeq , :reversedGlEntrySeq , :payeeCorporateId , :payeeNameText , :txnObjectCode , :listSeq , :txnObjectId , :createUserId , :createDate , :infoNumber , :deductionId , :txnEntryTime , :receiptNumber , :reversalReasonCode , :txnLocId , :payeeClearFlag , CURRENT_TIMESTAMP , null , :sealFlag ) 
}

OTDMGJTR_CGFKCHK_GL_TXN_GL_TXN_CORP_F8_ {
	SELECT CORP.CORPORATE_NAME FROM   CORPORATES CORP WHERE  CORP.CORPORATE_ID = :PAYEECORPORATEID
}

OTDMGJTR_CGFKCHK_GL_TXN_GL_TXN_PER_F7_ {
	SELECT PER.LAST_NAME ,PER.FIRST_NAME FROM   PERSONS PER WHERE  PER.PERSON_ID = :PAYEEPERSONID
}
OTDMGJTR_GET_DESC_TYPE {
 SELECT AC_CODE.ACCOUNT_NAME
            ,AC_CODE.TXN_POSTING_TYPE
      FROM   ACCOUNT_CODES AC_CODE
      WHERE  AC_CODE.ACCOUNT_CODE = :code::int
      AND     POSTING_STATUS_FLAG = 'Y'
      AND    AC_CODE.CASELOAD_TYPE =:caseloadType
}

OTDMGJTR_CGFKCHK_GL_TXN_GL_TXN_AC_CODE_ {
	SELECT AC_CODE.ACCOUNT_NAME ,AC_CODE.TXN_POSTING_TYPE FROM   ACCOUNT_CODES AC_CODE WHERE  AC_CODE.ACCOUNT_CODE = :ACCOUNTCODE AND     POSTING_STATUS_FLAG = 'Y' AND    AC_CODE.CASELOAD_TYPE = :CASELOADTYPE
}

OTDMGJTR_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}

OTDMGJTR_CGFKCHK_GL_TXN1_GL_TXN_AC_COD_ {
	SELECT AC_CODE1.ACCOUNT_NAME ,AC_CODE1.TXN_POSTING_TYPE ,AC_CODE1.ACCOUNT_TYPE FROM   ACCOUNT_CODES AC_CODE1 WHERE  AC_CODE1.ACCOUNT_CODE = :ACCOUNTCODE AND     POSTING_STATUS_FLAG = 'Y' AND      AC_CODE1.CASELOAD_TYPE = :CASELOADTYPE
}

OTDMGJTR_CGRICHK_GL_TRANSACTIONS_ {
	SELECT  '1' FROM    GL_TRANSACTIONS GL_TXN WHERE   (GL_TXN.REVERSED_TXN_ID = P_TXN_ID OR (GL_TXN.REVERSED_TXN_ID IS NULL AND P_TXN_ID IS NULL )) AND     (GL_TXN.REVERSED_TXN_ENTRY_SEQ = P_TXN_ENTRY_SEQ OR (GL_TXN.REVERSED_TXN_ENTRY_SEQ IS NULL AND P_TXN_ENTRY_SEQ IS NULL )) AND     (GL_TXN.REVERSED_GL_ENTRY_SEQ = P_GL_ENTRY_SEQ OR (GL_TXN.REVERSED_GL_ENTRY_SEQ IS NULL AND P_GL_ENTRY_SEQ IS NULL ))
}
OTDMGJTR_LVLLASTCLOSEDPERIOD {
select trust_gj_get_last_closed_period (:caseloadId) lvlLastclosedPeriod from dual
}
OTDMGJTR_LVALLOWEDREOPENPERIOD {
select trust_gj_get_allowed_back_dated_period (:caseloadId) lvAllowedReopenPeriod from dual
}
OTDMGJTR_LVENTERACCOUNTPERIOD{
select coalesce (trust_gj_get_account_period (:txnEntryDate), 0) lvEnteraccountPeriod from dual
}
OTDMGJTR_ISPERIODVALID {
SELECT coalesce(COUNT(*), 0) isperiodValid
        FROM CASELOAD_ACCOUNT_SUMMARIES
       WHERE CASELOAD_ID = :caseloadId
         AND ACCOUNT_PERIOD_ID = :lventerAccountPeriod
         AND ACCOUNT_PERIOD_ID <>
             (SELECT MIN(ACCOUNT_PERIOD_ID)
              -- The first ever period should be considered as invalid as this is a dummy account period
                FROM ACCOUNT_PERIODS
               WHERE START_DATE IS NOT NULL
                 AND END_DATE IS NOT NULL)
                 }
OTDMGJTR_LVACCOUNTSTATUS {
SELECT cap.account_period_status
    FROM caseload_account_periods cap
   WHERE cap.account_period_id = :lventerAccountPeriod
     AND cap.caseload_id =:caseloadId
     }
 OTDMGJTR_GETPERIODSTARTDATE {
 select trust_gj_get_period_start_date(:lventerAccountPeriod) PERIOD_START_DATE from dual
   }
 OTDMGJTR_GETPERIODENDDATE {
     select trust_gj_get_period_end_date(:lvlastClosedPeriod) PERIOD_END_DATE from dual
     
     }
 OTDMGJTR_ISACCOUNTCHECKING {
      SELECT COUNT(*)   FROM CASELOAD_CURRENT_ACCOUNTS_BASE
      -- Patrick 24-Feb-2010.  Change from CASELOAD_CURRENT_ACCOUNTS
       WHERE CASELOAD_ID = :caseloadId
         AND BANK_ACCOUNT_TYPE = 'CHK'
         AND ACCOUNT_CODE = :accountCode
      }
OTDMGJTR_GET_NEXTVAL {
 SELECT NEXTVAL('TXN_ID')FROM DUAL
 }
 OTDMGJTR_OMS_OWNER_TRUST_INSERT_INTO_CHEQUE_DATA {
 CALL OMS_OWNER.TRUST.INSERT_INTO_CHEQUE_DATA(:P_CSLD_ID, :P_TRANS_NUMBER, :P_TRANS_AMOUNT, :P_CHEQUE_FLAG, :P_START_TXN_ID, :P_END_TXN_ID, :P_PERS_PAYEE_ID, :P_CORP_PAYEE_ID, :P_PAYEE_NAME, :P_OFFENDER_PAYEE, :P_SINGLE_ENTRY, :P_BANK_ACT_CODE, :P_MODULE_NAME, :P_TRANS_TYPE)
 }
