
OTDTTACC_FIND_CGFKCSLDTTTXNTYPE {
 	SELECT TXN_TYPE.TXN_TYPE CODE /* CG$FK */  ,TXN_TYPE.DESCRIPTION DESCRIPTION FROM TRANSACTION_TYPES TXN_TYPE WHERE TXN_TYPE IN (SELECT DISTINCT TXN_TYPE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OTDTTACC' AND CASELOAD_ID = :CASELOADID ) ORDER BY TXN_TYPE
}

OTDTTACC_FIND_CGFKOFFTXNOFFENDERID {
 	SELECT V_THA.ROOT_OFFENDER_ID  OFFENDER_ID  /* CG$FK */        ,V_THA.CASELOAD_ID  CASELOAD_ID        ,V_THA.LAST_NAME  DSP_LAST_NAME        ,V_THA.FIRST_NAME  DSP_FIRST_NAME FROM   V_TRUST_HEADER_A V_THA
}

OTDTTACC_FIND_CGFKCSLDTTCASELOADID {
 	SELECT CSLD.CASELOAD_ID  CODE  /* CG$FK */        ,CSLD.DESCRIPTION  FROM   CASELOADS CSLD WHERE  CASELOAD_ID != :CASELOADID /*--ADDED BY ANURAG REF HPQC#18521*/   AND  ACTIVE_FLAG = 'Y'    AND  CASELOAD_TYPE = 'INST'    AND  TRUST_ACCOUNTS_FLAG = 'Y' /*--ENDED HERE REF HPQC#18521*/ ORDER BY CSLD.CASELOAD_ID
}

OTDTTACC_CSLDTT_FIND_CASELOAD_TRANSACTION_TYPES {
 	SELECT CASELOAD_ID ,TXN_TYPE ,MODIFY_USER_ID ,MODIFY_DATE ,LIST_SEQ ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM CASELOAD_TRANSACTION_TYPES  /* where  */
}
OTDTTACC_CSLDTT_INSERT_CASELOAD_TRANSACTION_TYPES {
	INSERT INTO CASELOAD_TRANSACTION_TYPES(CASELOAD_ID ,TXN_TYPE ,MODIFY_USER_ID ,MODIFY_DATE ,LIST_SEQ ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG ) VALUES(:caseloadId ,:txnType ,:modifyUserId ,:modifyDate ,:listSeq ,:createDatetime ,:createUserId ,:modifyDatetime ,:sealFlag )
}

OTDTTACC_CSLDTT_DELETE_CASELOAD_TRANSACTION_TYPES { 
	DELETE FROM CASELOAD_TRANSACTION_TYPES/* where */
}

OTDTTACC_ACPRD_FIND_ACCOUNT_PERIODS {
 	SELECT ACCOUNT_PERIOD_ID ,ACCOUNT_PERIOD_TYPE ,START_DATE ,END_DATE ,PARENT_ACCOUNT_PERIOD_ID ,MODIFY_DATE ,LIST_SEQ ,SEAL_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID   FROM ACCOUNT_PERIODS  /* where  */
}
OTDTTACC_ACPRD_INSERT_ACCOUNT_PERIODS {
	INSERT INTO ACCOUNT_PERIODS(ACCOUNT_PERIOD_ID ,ACCOUNT_PERIOD_TYPE ,START_DATE ,END_DATE ,PARENT_ACCOUNT_PERIOD_ID ,MODIFY_DATE ,LIST_SEQ ,SEAL_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ) VALUES(:accountPeriodId ,:accountPeriodType ,:startDate ,:endDate ,:parentAccountPeriodId ,:modifyDate ,:listSeq ,:sealFlag ,:createDatetime ,:createUserId ,:modifyDatetime ,:modifyUserId )
}

OTDTTACC_ACPRD_UPDATE_ACCOUNT_PERIODS {
	UPDATE ACCOUNT_PERIODS set ACCOUNT_PERIOD_ID  = :accountPeriodId ,ACCOUNT_PERIOD_TYPE  = :accountPeriodType ,START_DATE  = :startDate ,END_DATE  = :endDate ,PARENT_ACCOUNT_PERIOD_ID  = :parentAccountPeriodId ,MODIFY_DATE  = :modifyDate ,LIST_SEQ  = :listSeq ,SEAL_FLAG  = :sealFlag ,CREATE_DATETIME  = :createDatetime ,CREATE_USER_ID  = :createUserId ,MODIFY_DATETIME  = :modifyDatetime ,MODIFY_USER_ID  = :modifyUserId /* where */
}

OTDTTACC_OFFTXN_FIND_OFFENDER_TRANSACTIONS {
 	SELECT TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID   FROM OFFENDER_TRANSACTIONS  /* where  */
}
OTDTTACC_OFFTXN_INSERT_OFFENDER_TRANSACTIONS {
	INSERT INTO OFFENDER_TRANSACTIONS(TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ) VALUES(:txnId ,:txnEntrySeq ,:caseloadId ,:offenderId ,:offenderBookId ,:txnPostingType ,:txnType ,:txnEntryDesc ,:txnEntryAmount ,:txnEntryDate ,:subAccountType ,:txnReferenceNumber ,:modifyDate ,:receiptNumber ,:slipPrintedFlag ,:transferCaseloadId ,:receiptPrintedFlag ,:preWithholdAmount ,:deductionFlag ,:closingChequeNumber ,:remitterName ,:payeeCode ,:payeeNameText ,:payeeCorporateId ,:payeePersonId ,:adjustTxnId ,:adjustTxnEntryId ,:adjustOffenderId ,:adjustAccountCode ,:txnAdjustedFlag ,:deductionType ,:infoNumber ,:holdClearFlag ,:holdUntilDate ,:holdNumber ,:grossAmount ,:grossNetFlag ,:remitterId ,:applySpendingLimitAmount ,:receiptPendingPrintFlag ,:sealFlag ,:orgTxnType ,:createDatetime ,:createUserId ,:modifyDatetime ,:modifyUserId )
}

OTDTTACC_OFFTXN_UPDATE_OFFENDER_TRANSACTIONS {
	UPDATE OFFENDER_TRANSACTIONS set TXN_ID  = :txnId ,TXN_ENTRY_SEQ  = :txnEntrySeq ,CASELOAD_ID  = :caseloadId ,OFFENDER_ID  = :offenderId ,OFFENDER_BOOK_ID  = :offenderBookId ,TXN_POSTING_TYPE  = :txnPostingType ,TXN_TYPE  = :txnType ,TXN_ENTRY_DESC  = :txnEntryDesc ,TXN_ENTRY_AMOUNT  = :txnEntryAmount ,TXN_ENTRY_DATE  = :txnEntryDate ,SUB_ACCOUNT_TYPE  = :subAccountType ,TXN_REFERENCE_NUMBER  = :txnReferenceNumber ,MODIFY_DATE  = :modifyDate ,RECEIPT_NUMBER  = :receiptNumber ,SLIP_PRINTED_FLAG  = :slipPrintedFlag ,TRANSFER_CASELOAD_ID  = :transferCaseloadId ,RECEIPT_PRINTED_FLAG  = :receiptPrintedFlag ,PRE_WITHHOLD_AMOUNT  = :preWithholdAmount ,DEDUCTION_FLAG  = :deductionFlag ,CLOSING_CHEQUE_NUMBER  = :closingChequeNumber ,REMITTER_NAME  = :remitterName ,PAYEE_CODE  = :payeeCode ,PAYEE_NAME_TEXT  = :payeeNameText ,PAYEE_CORPORATE_ID  = :payeeCorporateId ,PAYEE_PERSON_ID  = :payeePersonId ,ADJUST_TXN_ID  = :adjustTxnId ,ADJUST_TXN_ENTRY_ID  = :adjustTxnEntryId ,ADJUST_OFFENDER_ID  = :adjustOffenderId ,ADJUST_ACCOUNT_CODE  = :adjustAccountCode ,TXN_ADJUSTED_FLAG  = :txnAdjustedFlag ,DEDUCTION_TYPE  = :deductionType ,INFO_NUMBER  = :infoNumber ,HOLD_CLEAR_FLAG  = :holdClearFlag ,HOLD_UNTIL_DATE  = :holdUntilDate ,HOLD_NUMBER  = :holdNumber ,GROSS_AMOUNT  = :grossAmount ,GROSS_NET_FLAG  = :grossNetFlag ,REMITTER_ID  = :remitterId ,APPLY_SPENDING_LIMIT_AMOUNT  = :applySpendingLimitAmount ,RECEIPT_PENDING_PRINT_FLAG  = :receiptPendingPrintFlag ,SEAL_FLAG  = :sealFlag ,ORG_TXN_TYPE  = :orgTxnType ,CREATE_DATETIME  = :createDatetime ,CREATE_USER_ID  = :createUserId ,MODIFY_DATETIME  = :modifyDatetime ,MODIFY_USER_ID  = :modifyUserId /* where */
}

OTDTTACC_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OTDTTACC_CGFKCHK_CSLD_TT_CSLD_TY_TXN_T_ {
	SELECT TXN_TYPE.DESCRIPTION FROM   TRANSACTION_TYPES TXN_TYPE WHERE  TXN_TYPE.TXN_TYPE = :TXNTYPE AND     TXN_TYPE IN (SELECT DISTINCT TXN_TYPE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OTDTTACC')
}

OTDTTACC_CGFKCHK_CSLD_TT_CSLD_TT_CSLD__ {
	SELECT CSLD.DESCRIPTION FROM   CASELOADS CSLD WHERE  CSLD.CASELOAD_ID = :CASELOADID AND     CASELOAD_ID != :CASELOADID
}

OTDTTACC_CGWHEN_NEW_FORM_INSTANCE_ {
	--SELECT  SYSDATE, USER FROM    SYS.DUAL
 	SELECT  SYSDATE(), USER FROM DUAL
}

OTDTTACC_CGFKCHK_OFF_TXN_OFF_TXN_V_THA_ {
	SELECT OFF_NAME.LAST_NAME ,OFF_NAME.FIRST_NAME FROM   V_TRUST_HEADER_A OFF_NAME --      WHERE  OFF_NAME.ROOT_OFFENDER_ID = :OFFENDERID
}
OTDTTACC_GET_HOLD_CLEAR_FLAG {
--SELECT  SUBSTR (LTRIM (RTRIM (VTH.STATUS_DISPLAY)), 1, 1) OFFSTATUS
  --               FROM V_TRUST_HEADER_A VTH
    --            WHERE VTH.CASELOAD_ID = :caseloadId
      --            AND VTH.ROOT_OFFENDER_ID = :offenderId
        --          AND VTH.BOOKING_TYPE = :casaeloadType
          --        AND VTH.OFFENDER_BOOK_ID =
            --                (SELECT MAX (OFFENDER_BOOK_ID)
              --                 FROM V_TRUST_HEADER_A VTHA
                --              WHERE VTHA.ROOT_OFFENDER_ID =
                  --                                       VTH.ROOT_OFFENDER_ID
                    --            AND NVL (VTHA.BOOKING_TYPE, 'INST') =:casaeloadType)

 SELECT  SUBSTR (LTRIM (RTRIM (VTH.STATUS_DISPLAY)), 1, 1) OFFSTATUS
                 FROM V_TRUST_HEADER_A VTH
                WHERE VTH.CASELOAD_ID = :caseloadId
                  AND VTH.ROOT_OFFENDER_ID = :offenderId
                  AND VTH.BOOKING_TYPE = :casaeloadType
                  AND VTH.OFFENDER_BOOK_ID =
                            (SELECT MAX (OFFENDER_BOOK_ID)
                               FROM V_TRUST_HEADER_A VTHA
                              WHERE VTHA.ROOT_OFFENDER_ID =
                                                         VTH.ROOT_OFFENDER_ID
                                AND COALESCE (VTHA.BOOKING_TYPE, 'INST') =:casaeloadType)

}

OTDTTACC_GET_HOLD_BAL {
--SELECT SUB_ACCOUNT_TYPE SAT, NVL (BALANCE, 0) BAL,
  --                       NVL (HOLD_BALANCE, 0) HOLDBAL
    --                FROM OFFENDER_SUB_ACCOUNTS, ACCOUNT_CODES
      --             WHERE CASELOAD_ID = :caseloadId
        --             AND OFFENDER_ID = :offenderId
          --           AND TRUST_ACCOUNT_CODE = ACCOUNT_CODE
            --         AND TRUST_ACCOUNT_CODE IN (SELECT DR_ACCOUNT_CODE
              --                                    FROM TRANSACTION_OPERATIONS
                --                                 WHERE MODULE_NAME =
                  --                                                 'OTDTTACC'
                    --                               AND TXN_TYPE = :txnType
                      --                             AND CASELOAD_ID = :caseloadId) 
                                                       
-- SQLINES LICENSE FOR EVALUATION USE ONLY
SELECT SUB_ACCOUNT_TYPE SAT, COALESCE (BALANCE, 0) BAL,
                         COALESCE (HOLD_BALANCE, 0) HOLDBAL
                    FROM OFFENDER_SUB_ACCOUNTS, ACCOUNT_CODES
                   WHERE CASELOAD_ID = :caseloadId
                     AND OFFENDER_ID = :offenderId
                     AND TRUST_ACCOUNT_CODE = ACCOUNT_CODE
                     AND TRUST_ACCOUNT_CODE IN (SELECT DR_ACCOUNT_CODE
                                                  FROM TRANSACTION_OPERATIONS
                                                 WHERE MODULE_NAME =
                                                                   'OTDTTACC'
                                                   AND TXN_TYPE = :txnType
                                                   AND CASELOAD_ID = :caseloadId)  
 

}
OTDTTACC_GET_CORPORATEID_NAMES {
SELECT t.CORPORATE_ID,
             t.CORPORATE_NAME     
      FROM  (SELECT crp.corporate_id , crp.corporate_name, crp.CASELOAD_ID 
               FROM CORPORATES crp, CORPORATE_TYPES ctp
              WHERE crp.corporate_id   = ctp.corporate_id 
                AND ctp.corporate_type = 'TRUST'
              UNION
             SELECT crp2.corporate_id , crp2.corporate_name, crp2.CASELOAD_ID
               FROM CORPORATES crp2
              WHERE NOT EXISTS ( SELECT 1 
                                   FROM CORPORATE_TYPES ctp2 
                                  WHERE crp2.corporate_id = ctp2.corporate_id )
              ) t
      WHERE t.caseload_id = :toCaseload
      }
OTDTTACC_GET_CHECKTXNTYPE {
     -- SELECT 1  FROM OFFENDER_SUB_ACCOUNTS   WHERE CASELOAD_ID = :toCaseload   AND OFFENDER_ID = :txnType  AND TRUST_ACCOUNT_CODE IN
       --                        (SELECT DR_ACCOUNT_CODE --TRUST_ACCOUNT_CODE
         --                         FROM TRANSACTION_OPERATIONS
           --                      WHERE MODULE_NAME = 'OTDTTACC'
             --                      AND TXN_TYPE = TXNTYPE
               --                    AND CASELOAD_ID = :caseloadId)
                  SELECT 1  FROM OFFENDER_SUB_ACCOUNTS   WHERE CASELOAD_ID = :toCaseload   AND OFFENDER_ID = :txnType  AND TRUST_ACCOUNT_CODE IN
                               (SELECT DR_ACCOUNT_CODE --TRUST_ACCOUNT_CODE
                                  FROM TRANSACTION_OPERATIONS
                                 WHERE MODULE_NAME = 'OTDTTACC'
                                   AND TXN_TYPE =:TXNTYPE
                                   AND CASELOAD_ID = :caseloadId)
      
                                   }
 OTDTTACC_GET_NEXT_VAL { 
 --SELECT  TXN_ID.NEXTVAL FROM DUAL
 SELECT  NEXTVAL('TXN_ID') FROM DUAL
 }
OTDTTACC_GET_TXN_AMT_TXN_SUBAC {
 --SELECT TRANSFERED_AMOUNT,
   --       TRUST_ACCOUNT_CODE   
   --FROM   OFFENDER_SUB_AC_SHADOWS
   --WHERE  OFFENDER_ID   =  :offenderId
  -- AND    CASELOAD_ID   =  :caseloadId
  -- AND    ROWNUM        =  1  
   
    SELECT TRANSFERED_AMOUNT,
          TRUST_ACCOUNT_CODE   
   FROM   OFFENDER_SUB_AC_SHADOWS
   WHERE  OFFENDER_ID   =  :offenderId
   AND    CASELOAD_ID   =  :caseloadId
   Limit 1
   }   
   
OTDTTACC_INSRT_INTO_OFFENDER_TRANS_FORM {
-- INSERT INTO OFFENDER_TRANSACTIONS(
--txn_id, txn_entry_seq, caseload_id, offender_id, offender_book_id, txn_posting_type, txn_type, txn_entry_desc, txn_entry_amount, txn_entry_date, sub_account_type,
--modify_date, modify_user_id, slip_printed_flag, pre_withhold_amount,
--deduction_flag, payee_code, payee_corporate_id, payee_person_id, payee_name_text, deduction_type, info_number,TRANSFER_CASELOAD_ID)

--SELECT  :txnId,
--:txnEntrySeq,
--:caseloadId,
--:offenderId,
--:offenderBookId,
 --'DR',
--:txnType,
--:txnEntryDesc,
--:txnEntryAmount,
--TRUNC(SYSDATE),
--:subAccountType,
--TRUNC(SYSDATE),
--USER,
--'N',
--:preWithholdAmount,
--'N',
--'C',
--:payeeCorporateId,
--NULL,
--:payeeNameText,
--NULL,
--NULL,
--:transferCaseloadId
--FROM  DUAL 
insert
	into
	OFFENDER_TRANSACTIONS(
txn_id,
	txn_entry_seq,
	caseload_id,
	offender_id,
	offender_book_id,
	txn_posting_type,
	txn_type,
	txn_entry_desc,
	txn_entry_amount,
	txn_entry_date,
	sub_account_type,
	modify_date,
	create_user_id,
	slip_printed_flag,
	pre_withhold_amount,
	deduction_flag,
	payee_code,
	payee_corporate_id,
	payee_person_id,
	payee_name_text,
	deduction_type,
	info_number,
	TRANSFER_CASELOAD_ID,
	CREATE_DATETIME,  
    MODIFY_DATETIME,
    MODIFY_USER_ID)

select
	:txnId,
	:txnEntrySeq,
	:caseloadId,
	:offenderId,
	:offenderBookId,
	'DR',
	:txnType,
	:txnEntryDesc,
	:txnEntryAmount,
	 current_timestamp,
	:subAccountType,
	current_timestamp,
	:createUserId,
	'N',
	:preWithholdAmount,
	'N',
	'C',
	:payeeCorporateId,
	null,
	:payeeNameText,
	null,
	null,
	:transferCaseloadId,
	current_timestamp,
	current_timestamp,
	:createUserId
from
	DUAL

}

OTDTTACC_INSRT_INTO_OFFENDER_TRANS_TOR_FORM {
 --INSERT INTO OFFENDER_TRANSACTIONS( txn_id, txn_entry_seq, caseload_id, offender_id, offender_book_id, txn_posting_type, txn_type, txn_entry_desc, txn_entry_amount, txn_entry_date, sub_account_type, modify_date, modify_user_id, slip_printed_flag, pre_withhold_amount, deduction_flag, payee_code, payee_corporate_id, payee_person_id, payee_name_text, deduction_type, info_number,trn_caseload_id) SELECT :txnId, :txnEntrySeq, :caseloadId, :offenderId, :offenderBookId, 'CR', :txnType, :txnEntryDesc, :txnEntryAmount, :txnEntryDate, :subAccountType, :modifyDate, :createUserId 'N', :preWithholdAmount, 'N', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', 'NULL', :transferCaseloadId FROM DUAL 
insert
	into
	OFFENDER_TRANSACTIONS(TXN_ID ,
	TXN_ENTRY_SEQ ,
	CASELOAD_ID ,
	OFFENDER_ID ,
	OFFENDER_BOOK_ID ,
	TXN_POSTING_TYPE ,
	TXN_TYPE ,
	TXN_ENTRY_DESC ,
	TXN_ENTRY_AMOUNT ,
	TXN_ENTRY_DATE ,
	SUB_ACCOUNT_TYPE ,
	TXN_REFERENCE_NUMBER ,
	MODIFY_DATE ,
	RECEIPT_NUMBER ,
	SLIP_PRINTED_FLAG ,
	TRANSFER_CASELOAD_ID ,
	RECEIPT_PRINTED_FLAG ,
	PRE_WITHHOLD_AMOUNT ,
	DEDUCTION_FLAG ,
	CLOSING_CHEQUE_NUMBER ,
	REMITTER_NAME ,
	PAYEE_CODE ,
	PAYEE_NAME_TEXT ,
	PAYEE_CORPORATE_ID ,
	PAYEE_PERSON_ID ,
	ADJUST_TXN_ID ,
	ADJUST_TXN_ENTRY_ID ,
	ADJUST_OFFENDER_ID ,
	ADJUST_ACCOUNT_CODE ,
	TXN_ADJUSTED_FLAG ,
	DEDUCTION_TYPE ,
	INFO_NUMBER ,
	HOLD_CLEAR_FLAG ,
	HOLD_UNTIL_DATE ,
	HOLD_NUMBER ,
	GROSS_AMOUNT ,
	GROSS_NET_FLAG ,
	REMITTER_ID ,
	APPLY_SPENDING_LIMIT_AMOUNT ,
	RECEIPT_PENDING_PRINT_FLAG ,
	SEAL_FLAG ,
	ORG_TXN_TYPE ,
	CREATE_DATETIME ,
	CREATE_USER_ID ,
	MODIFY_DATETIME ,
	MODIFY_USER_ID )
values(:txnId ,
:txnEntrySeq ,
:caseloadId ,
:offenderId ,
:offenderBookId ,
:txnPostingType ,
:txnType ,
:txnEntryDesc ,
    :txnEntryAmount ,
:txnEntryDate ,
:subAccountType ,
:txnReferenceNumber ,
:modifyDate ,
:receiptNumber ,
:slipPrintedFlag ,
    :transferCaseloadId ,
:receiptPrintedFlag ,
:preWithholdAmount ,
:deductionFlag ,
:closingChequeNumber ,
:remitterName ,
    :payeeCode ,
:payeeNameText ,
:payeeCorporateId ,
:payeePersonId ,
:adjustTxnId ,
:adjustTxnEntryId ,
:adjustOffenderId ,
    :adjustAccountCode ,
:txnAdjustedFlag ,
:deductionType ,
:infoNumber ,
:holdClearFlag ,
:holdUntilDate ,
:holdNumber ,
    :grossAmount ,
:grossNetFlag ,
:remitterId ,
:applySpendingLimitAmount ,
:receiptPendingPrintFlag ,
:sealFlag ,
:orgTxnType ,
    :createDatetime ,
:createUserId ,
:modifyDatetime ,
:modifyUserId )

}
OTDTTACC_GET_TRUSTACN_TRANSFRAMT {
SELECT TRUST_ACCOUNT_CODE TAC,
	            TRANSFERED_AMOUNT TAMT ,OFFENDER_ID    
             FROM   OFFENDER_SUB_AC_SHADOWS
             WHERE  OFFENDER_ID  = :offenderId
             AND    CASELOAD_ID  = :caseloadId           AND    TRUST_ACCOUNT_CODE != :subAccountType
             }
  OTDTTACC_INSRT_INTO_OFFENDER_CHECKAMT_TRANS_TOR_FORM {
 -- INSERT INTO OFFENDER_TRANSACTIONS(
--TXN_ID, TXN_ENTRY_SEQ, CASELOAD_ID, OFFENDER_ID, OFFENDER_BOOK_ID, TXN_POSTING_TYPE, TXN_TYPE, TXN_ENTRY_DESC, TXN_ENTRY_AMOUNT, TXN_ENTRY_DATE, SUB_ACCOUNT_TYPE,
--MODIFY_DATE, MODIFY_USER_ID, SLIP_PRINTED_FLAG, PRE_WITHHOLD_AMOUNT,
--DEDUCTION_FLAG, PAYEE_CODE, PAYEE_CORPORATE_ID, PAYEE_PERSON_ID, PAYEE_NAME_TEXT, DEDUCTION_TYPE, INFO_NUMBER,TRANSFER_CASELOAD_ID)

--SELECT  :txnId,
--:txnEntrySeq,
--:caseloadId,
--:offenderId,
--:offenderBookId,
 --'DR',
--:txnType,
--:txnEntryDesc,
--:txnEntryAmount,
--TRUNC(SYSDATE),
--:subAccountType,
--TRUNC(SYSDATE),
--USER,
--'N',
--null,
--'N',
--'C',
--:payeeCorporateId,
--NULL,
--:payeeNameText,
--NULL,
--NULL,
--:transferCaseloadId
--FROM  DUAL 

  	
	insert
	into
	OFFENDER_TRANSACTIONS(
TXN_ID,
	TXN_ENTRY_SEQ,
	CASELOAD_ID,
	OFFENDER_ID,
	OFFENDER_BOOK_ID,
	TXN_POSTING_TYPE,
	TXN_TYPE,
	TXN_ENTRY_DESC,
	TXN_ENTRY_AMOUNT,
	TXN_ENTRY_DATE,
	SUB_ACCOUNT_TYPE,
	MODIFY_DATE,
	create_user_id ,
	SLIP_PRINTED_FLAG,
	PRE_WITHHOLD_AMOUNT,
	DEDUCTION_FLAG,
	PAYEE_CODE,
	PAYEE_CORPORATE_ID,
	PAYEE_PERSON_ID,
	PAYEE_NAME_TEXT,
	DEDUCTION_TYPE,
	INFO_NUMBER,
	TRANSFER_CASELOAD_ID,
	CREATE_DATETIME,  
    MODIFY_DATETIME,
    MODIFY_USER_ID)

select
	:txnId,
	:txnEntrySeq,
	:caseloadId,
	:offenderId,
	:offenderBookId,
	'DR',
	:txnType,
	:txnEntryDesc,
	:txnEntryAmount,
	current_timestamp ,
	:subAccountType,
	current_timestamp ,
	:createUserId,
	'N',
	null,
	'N',
	'C',
	:payeeCorporateId,
	null,
	:payeeNameText,
	null,
	null,
	:transferCaseloadId,
	current_timestamp ,
	current_timestamp,
	:createUserId
from
	DUAL
}

   
   OTDTTACC_DELETE_OFFENDER_SUB_AC_SHADOWS {
   DELETE FROM OFFENDER_SUB_AC_SHADOWS  WHERE CASELOAD_ID = :caseloadId   AND   OFFENDER_ID = :offenderId  
  }
  OTDTTACC_CHECK_PRO {
  SELECT DISTINCT 'Y'
       -- INTO check_pro
        FROM transaction_operations
       WHERE module_name = 'OTDTTACC'
         AND txn_type = 'TOR'
         AND cheque_production_flag = 'Y'
         AND caseload_id = :caseloadId
         }
  OTDTTACC_CHECK_BANK_CHEQUE_DATA {
      SELECT '1' FROM BANK_CHEQUE_DATA    WHERE txn_id = :txnId  
                }
  OTDTTACC_UPDATE_BANK_CHEQUE_DATA {
                UPDATE bank_cheque_data
                     SET txn_entry_amount = txn_entry_amount + :chqamt
                   WHERE txn_id = :txnId
                   }
OTDTTACC_TRUST_TRAN_EXIST_CUR {
  SELECT '1' FROM OFFENDER_TRUST_TRANSFERS  WHERE txn_id = :txnId
   }
   OTDTTACC_UPDATE_OFFENDER_TRUST_TRANSFERS {
   --UPDATE OFFENDER_TRUST_TRANSFERS
     --          SET AMOUNT = AMOUNT + :checkAmt
       --      WHERE TXN_ID = :txnId
       UPDATE OFFENDER_TRUST_TRANSFERS
               SET AMOUNT = AMOUNT + :checkAmt,
               MODIFY_DATETIME = CURRENT_TIMESTAMP,
          MODIFY_USER_ID = :modifyUserId
             WHERE TXN_ID = :txnId

             }
             OTDTTACC_INSERT_OFFENDER_TRUST_TRANSFERS {
   --INSERT INTO OFFENDER_TRUST_TRANSFERS ( txn_id, from_caseload, to_caseload,  amount, 
	--					transfer_date, posted_flag, modify_date, modify_user_id) 
      --           VALUES (:txnId, :frcaseload, :tocaseload, :checkAmt, TRUNC (SYSDATE), 'N', TRUNC (SYSDATE), USER)
       INSERT INTO OFFENDER_TRUST_TRANSFERS ( txn_id, from_caseload, to_caseload,  amount, 
						transfer_date, posted_flag, modify_date, create_user_id,modify_datetime,create_datetime) 
                 VALUES (:txnId, :frcaseload, :tocaseload, :checkAmt, current_timestamp, 'N', current_timestamp, :createUserId,current_timestamp,current_timestamp)
    
                 }
 OTDTTACC_GET_ROOT_OFFENDER_ID {
--  SELECT DISTINCT root_offender_id, last_name, first_name
  --                        FROM v_trust_header_a va
    --                     WHERE va.offender_book_id =(SELECT MAX (offender_book_id) FROM v_trust_header_a vtha
      --                                 WHERE vtha.root_offender_id =  va.root_offender_id  AND NVL (vtha.booking_type, 'INST') = :caseloadType)
        --                   AND NVL (va.booking_type, 'INST') = :caseloadType
          --                 AND va.offender_id_display = :offenderIdDisplay
           
 SELECT DISTINCT root_offender_id, last_name, first_name
                          FROM v_trust_header_a va
                         WHERE va.offender_book_id =(SELECT MAX (offender_book_id) FROM v_trust_header_a vtha
                                       WHERE vtha.root_offender_id =  va.root_offender_id  AND COALESCE (vtha.booking_type, 'INST') = :caseloadType)
                           AND COALESCE (va.booking_type, 'INST') = :caseloadType
                           AND ltrim(va.OFFENDER_ID_DISPLAY::text, '0') = ltrim(:offenderIdDisplay::text, '0')
  
     }
 OTDTTACC_INSERT_OFFENDER_SUB_AC_SHADOWS {
    -- INSERT INTO offender_sub_ac_shadows
      --                        (caseload_id, offender_id, trust_account_code,
        --                       transfered_amount, balance)
          --             VALUES (:caseloadid, :offid, :sat,
            --                   :bal, 0)
                 INSERT INTO offender_sub_ac_shadows
                              (caseload_id, offender_id, trust_account_code,
                               transfered_amount, balance,CREATE_USER_ID, CREATE_DATETIME,  MODIFY_DATETIME)
                       VALUES (:caseloadid, :offid, :sat,
                               :bal, 0,:createUserId,current_timestamp,current_timestamp)
    
                               }
 OTDTTACC_RETRIEVE_WHEN_NEWBLOCK_INSTANCE {
 --SELECT o.*
   --        FROM offender_external_movements oem, offender_bookings ob, offenders o
     --     WHERE oem.offender_book_id = ob.offender_book_id
       --     AND ob.offender_id = o.offender_id
        --    AND oem.movement_date BETWEEN :startDate AND :endDate
         --   AND oem.movement_type = 'TRN'
          --  AND oem.direction_code = 'OUT'
           -- AND oem.from_agy_loc_id IN(SELECT agy_loc_id
             --                            FROM caseload_agency_locations
               --                         WHERE caseload_id = :currentCaseload
                 --                         AND agy_loc_id NOT IN('OUT', 'TRN') )
       --     AND oem.to_agy_loc_id IN(SELECT cal.agy_loc_id
         --                              FROM caseload_agency_locations cal
           --                           WHERE cal.caseload_id = :toCaseload
             --                           AND cal.agy_loc_id NOT IN('OUT', 'TRN') )                                          
       --     AND oem.movement_seq =(SELECT MAX(oem2.movement_seq)
         --                            FROM offender_external_movements oem2
           --                         WHERE oem2.offender_book_id = oem.offender_book_id
             --                         AND oem2.from_agy_loc_id = oem.from_agy_loc_id
               --                       AND oem2.direction_code = 'OUT')
          --  AND (   EXISTS(SELECT 1
            --                 FROM offender_external_movements oem2, offender_bookings ob2
              --              WHERE oem2.offender_book_id = ob2.offender_book_id
                --              AND ob2.root_offender_id = o.root_offender_id
                  --            AND oem2.active_flag = 'Y'
                    --         AND oem2.direction_code = 'IN'
                      --        AND oem2.to_agy_loc_id IN(SELECT cal.agy_loc_id
                     --                                   FROM caseload_agency_locations cal
                       --                                  WHERE cal.caseload_id = :toCaseload
                         --                                  AND cal.agy_loc_id NOT IN('OUT', 'TRN') ) )
           --      )
           -- AND EXISTS(SELECT 1
             --            FROM Offender_Trust_Accounts
               --         WHERE Offender_id = o.root_offender_id
                 --         AND Caseload_id = :currentCaseload
					--								AND Account_Closed_Flag = 'N')
          -- AND NOT EXISTS(SELECT 1 FROM offender_sub_accounts osa
            --                      WHERE nvl(balance,0) = 0
              --                      AND nvl(hold_balance,0)=0
                --                    AND Offender_id = o.root_offender_id
                  --                  AND trust_account_code IN (SELECT dr_account_code
                    --                                             FROM transaction_operations
                      --                                          WHERE module_name = 'OTDTTACC'                                                                   
                        --                                          AND txn_type = :txnType
                          --                                        AND caseload_id = :currentCaseload))
                          
                           
                          
 SELECT o.*
           FROM offender_external_movements oem, offender_bookings ob, offenders o
          WHERE oem.offender_book_id = ob.offender_book_id
            AND ob.offender_id = o.offender_id
            AND oem.movement_date BETWEEN :startDate AND :endDate
            AND oem.movement_type = 'TRN'
            AND oem.direction_code = 'OUT'
            AND oem.from_agy_loc_id IN(SELECT agy_loc_id
                                         FROM caseload_agency_locations
                                        WHERE caseload_id = :currentCaseload
                                          AND agy_loc_id NOT IN('OUT', 'TRN') )
            AND oem.to_agy_loc_id IN(SELECT cal.agy_loc_id
                                       FROM caseload_agency_locations cal
                                      WHERE cal.caseload_id = :toCaseload
                                        AND cal.agy_loc_id NOT IN('OUT', 'TRN') )                                          
            AND oem.movement_seq =(SELECT MAX(oem2.movement_seq)
                                     FROM offender_external_movements oem2
                                    WHERE oem2.offender_book_id = oem.offender_book_id
                                      AND oem2.from_agy_loc_id = oem.from_agy_loc_id
                                      AND oem2.direction_code = 'OUT')
            AND (   EXISTS(SELECT 1
                             FROM offender_external_movements oem2, offender_bookings ob2
                            WHERE oem2.offender_book_id = ob2.offender_book_id
                              AND ob2.root_offender_id = o.root_offender_id
                              AND oem2.active_flag = 'Y'
                              AND oem2.direction_code = 'IN'
                              AND oem2.to_agy_loc_id IN(SELECT cal.agy_loc_id
                                                          FROM caseload_agency_locations cal
                                                         WHERE cal.caseload_id = :toCaseload
                                                           AND cal.agy_loc_id NOT IN('OUT', 'TRN') ) )
                 )
            AND EXISTS(SELECT 1
                         FROM Offender_Trust_Accounts
                        WHERE Offender_id = o.root_offender_id
                          AND Caseload_id = :currentCaseload
													AND Account_Closed_Flag = 'N')
           AND NOT EXISTS(SELECT 1 FROM offender_sub_accounts osa
                                  WHERE coalesce(balance,0) = 0
                                    AND coalesce(hold_balance,0)=0
                                    AND Offender_id = o.root_offender_id
                                    AND trust_account_code IN (SELECT dr_account_code
                                                                 FROM transaction_operations
                                                                WHERE module_name = 'OTDTTACC'                                                                   
                                                                  AND txn_type = :txnType
                                                                  AND caseload_id = :currentCaseload))
  
 }
                           
   OTDTTACC_SELECT_OFFENDER_SUB_AC_SHADOWS {   
    SELECT   TRUST_ACCOUNT_CODE , TRANSFERED_AMOUNT ,   BALANCE FROM OFFENDER_SUB_AC_SHADOWS  WHERE       CASELOAD_ID = :caseloadId  AND OFFENDER_ID = :offenderId
   }
   OTDTTACC_RETRIEVE_ROOTOFFENDERS {
   SELECT ROOT_OFFENDER_ID FROM v_trust_header WHERE CASELOAD_ID=:caseloadId AND offender_id_display = :offenderIdDisplay 
   }
OTDTTACC_DEDUCTIONS_GET_AC_AND_SET_IND_DATE {
CALL OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:P_OFF_ID, :P_CSLD_ID)
}
OTDTTACC_GET_OFFENDER_BOOK_IDAND_STATUS_DISPLAY {
--SELECT vth.offender_book_id, vth.status_display
  --               FROM v_trust_header_a vth
    --            WHERE vth.CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = USER)
      --            AND vth.root_offender_id = :rootOffenderId
        --          AND vth.booking_type = :caseloadType
          --        AND vth.offender_book_id =
            --                (SELECT MAX (offender_book_id)
              --                 FROM v_trust_header_a vtha
                --              WHERE vtha.root_offender_id =
                  --                                       vth.root_offender_id
                    --            AND NVL (vtha.booking_type, 'INST') =
                      --                                              :caseloadType)
                      


SELECT vth.offender_book_id, vth.status_display
                 FROM v_trust_header_a vth
                WHERE vth.CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID =:CURRENT_USER)
                  AND vth.root_offender_id = :rootOffenderId
                  AND vth.booking_type = :caseloadType
                  AND vth.offender_book_id =
                            (SELECT MAX (offender_book_id)
                               FROM v_trust_header_a vtha
                              WHERE vtha.root_offender_id =
                                                         vth.root_offender_id
                                AND COALESCE (vtha.booking_type, 'INST') =
                                                                    :caseloadType)
                                                                   
                                                                    }