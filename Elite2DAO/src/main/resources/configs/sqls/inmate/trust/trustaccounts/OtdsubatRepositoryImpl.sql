
OTDSUBAT_FIND_CGFKOFFTXN2SUBACCOUNTTYPE {
-- 	SELECT SUB_MAST.CODE  ,SUB_MAST.DESCRIPTION  ,SUB_MAST.DOMAIN DSP_DOMAIN FROM REFERENCE_CODES SUB_MAST WHERE SUB_MAST.DOMAIN = 'SUB_AC_TYPE' AND SUB_MAST.EXPIRED_DATE IS NULL AND SUB_MAST.ACTIVE_FLAG = 'Y' AND EXISTS ( SELECT 'X' FROM TRANSACTION_OPERATIONS OT , ACCOUNT_CODES AC WHERE OT.MODULE_NAME = 'OTDSUBAT' AND OT.CASELOAD_ID = :CASELOADID AND OT.DR_ACCOUNT_CODE = AC.ACCOUNT_CODE AND AC.SUB_ACCOUNT_TYPE = SUB_MAST.CODE  )
SELECT
    DISTINCT AC.ACCOUNT_NAME DESCRIPTION,
    AC.SUB_ACCOUNT_TYPE CODE,
    TOP.DR_ACCOUNT_CODE
FROM
    TRANSACTION_OPERATIONS TOP,
    ACCOUNT_CODES          AC
WHERE
        TOP.MODULE_NAME = 'OTDSUBAT'
    AND TOP.TXN_TYPE = 'OTC'
    AND TOP.CASELOAD_ID = :CASELOADID
    AND AC.ACCOUNT_CODE = TOP.DR_ACCOUNT_CODE AND AC.CASELOAD_TYPE = (SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID = :CASELOADID) AND AC.SUB_ACCOUNT_TYPE IS NOT NULL
}

OTDSUBAT_FIND_CGFKOFFTXNSUBACCOUNTTYPE {
 	SELECT REF_CODE.CODE  ,REF_CODE.DESCRIPTION   ,REF_CODE.DOMAIN DSP_DOMAIN FROM REFERENCE_CODES REF_CODE WHERE REF_CODE.DOMAIN = 'SUB_AC_TYPE' AND REF_CODE.ACTIVE_FLAG = 'Y' AND REF_CODE.EXPIRED_DATE IS NULL AND REF_CODE.CODE != :SUBACCOUNTTYPE AND EXISTS ( SELECT 'X' FROM ACCOUNT_CODES AC WHERE ACCOUNT_CODE IN ( SELECT OT1.CR_ACCOUNT_CODE FROM TRANSACTION_OPERATIONS OT1 , ACCOUNT_CODES AC1 WHERE OT1.DR_ACCOUNT_CODE = AC1.ACCOUNT_CODE AND AC1.SUB_ACCOUNT_TYPE = :SUBACCOUNTTYPE AND OT1.CASELOAD_ID = :CASELOADID AND OT1.MODULE_NAME = 'OTDSUBAT' ) AND AC.SUB_ACCOUNT_TYPE = REF_CODE.CODE )
}

OTDSUBAT_OFFTXN2_FIND_OFFENDER_TRANSACTIONS {
 	SELECT TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID   FROM OFFENDER_TRANSACTIONS  /* where  */
}
OTDSUBAT_OFFTXN2_INSERT_OFFENDER_TRANSACTIONS {
	INSERT INTO OFFENDER_TRANSACTIONS(TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ) VALUES(:txnId ,:txnEntrySeq ,:caseloadId ,:offenderId ,:offenderBookId ,:txnPostingType ,:txnType ,:txnEntryDesc ,:txnEntryAmount ,:txnEntryDate ,:subAccountType ,:txnReferenceNumber ,:modifyDate ,:receiptNumber ,:slipPrintedFlag ,:transferCaseloadId ,:receiptPrintedFlag ,:preWithholdAmount ,:deductionFlag ,:closingChequeNumber ,:remitterName ,:payeeCode ,:payeeNameText ,:payeeCorporateId ,:payeePersonId ,:adjustTxnId ,:adjustTxnEntryId ,:adjustOffenderId ,:adjustAccountCode ,:txnAdjustedFlag ,:deductionType ,:infoNumber ,:holdClearFlag ,:holdUntilDate ,:holdNumber ,:grossAmount ,:grossNetFlag ,:remitterId ,:applySpendingLimitAmount ,:receiptPendingPrintFlag ,:sealFlag ,:orgTxnType ,:createDatetime ,:createUserId ,:modifyDatetime ,:modifyUserId )
}

OTDSUBAT_OFFTXN2_UPDATE_OFFENDER_TRANSACTIONS {
	UPDATE OFFENDER_TRANSACTIONS set TXN_ID  = :txnId ,TXN_ENTRY_SEQ  = :txnEntrySeq ,CASELOAD_ID  = :caseloadId ,OFFENDER_ID  = :offenderId ,OFFENDER_BOOK_ID  = :offenderBookId ,TXN_POSTING_TYPE  = :txnPostingType ,TXN_TYPE  = :txnType ,TXN_ENTRY_DESC  = :txnEntryDesc ,TXN_ENTRY_AMOUNT  = :txnEntryAmount ,TXN_ENTRY_DATE  = :txnEntryDate ,SUB_ACCOUNT_TYPE  = :subAccountType ,TXN_REFERENCE_NUMBER  = :txnReferenceNumber ,MODIFY_DATE  = :modifyDate ,RECEIPT_NUMBER  = :receiptNumber ,SLIP_PRINTED_FLAG  = :slipPrintedFlag ,TRANSFER_CASELOAD_ID  = :transferCaseloadId ,RECEIPT_PRINTED_FLAG  = :receiptPrintedFlag ,PRE_WITHHOLD_AMOUNT  = :preWithholdAmount ,DEDUCTION_FLAG  = :deductionFlag ,CLOSING_CHEQUE_NUMBER  = :closingChequeNumber ,REMITTER_NAME  = :remitterName ,PAYEE_CODE  = :payeeCode ,PAYEE_NAME_TEXT  = :payeeNameText ,PAYEE_CORPORATE_ID  = :payeeCorporateId ,PAYEE_PERSON_ID  = :payeePersonId ,ADJUST_TXN_ID  = :adjustTxnId ,ADJUST_TXN_ENTRY_ID  = :adjustTxnEntryId ,ADJUST_OFFENDER_ID  = :adjustOffenderId ,ADJUST_ACCOUNT_CODE  = :adjustAccountCode ,TXN_ADJUSTED_FLAG  = :txnAdjustedFlag ,DEDUCTION_TYPE  = :deductionType ,INFO_NUMBER  = :infoNumber ,HOLD_CLEAR_FLAG  = :holdClearFlag ,HOLD_UNTIL_DATE  = :holdUntilDate ,HOLD_NUMBER  = :holdNumber ,GROSS_AMOUNT  = :grossAmount ,GROSS_NET_FLAG  = :grossNetFlag ,REMITTER_ID  = :remitterId ,APPLY_SPENDING_LIMIT_AMOUNT  = :applySpendingLimitAmount ,RECEIPT_PENDING_PRINT_FLAG  = :receiptPendingPrintFlag ,SEAL_FLAG  = :sealFlag ,ORG_TXN_TYPE  = :orgTxnType ,CREATE_DATETIME  = :createDatetime ,CREATE_USER_ID  = :createUserId ,MODIFY_DATETIME  = :modifyDatetime ,MODIFY_USER_ID  = :modifyUserId /* where */
}


OTDSUBAT_CGFKCHK_OFF_TXN2_OFF_TXN_REF {
	--SELECT SUB_MAST.DESCRIPTION ,SUB_MAST.DOMAIN FROM   REFERENCE_CODES SUB_MAST WHERE  SUB_MAST.CODE = :SUBACCOUNTTYPE AND     ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL AND DOMAIN = 'SUB_AC_TYPE'
     SELECT SUB_MAST.DESCRIPTION ,SUB_MAST.DOMAIN FROM   REFERENCE_CODES SUB_MAST WHERE  SUB_MAST.CODE = :SUBACCOUNTTYPE AND     ACTIVE_FLAG = 'Y' AND coalesce (EXPIRED_DATE::timestamp, '2020-12-12')= '2020-12-12' AND DOMAIN = 'SUB_AC_TYPE'
}

OTDSUBAT_CGWHEN_NEW_FORM_INSTANCE {
	--SELECT  SYSDATE, USER FROM    SYS.DUAL
	SELECT SYSDATE(),USER FROM DUAL;
}

OTDSUBAT_CGFKCHK_OFF_TXN_OFF_TXN_REF_C {
	--SELECT REF_CODE.DESCRIPTION ,REF_CODE.DOMAIN FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :SUBACCOUNTTYPE AND     ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL AND DOMAIN = 'SUB_AC_TYPE' AND CODE != :SUBACCOUNTTYPE
	SELECT REF_CODE.DESCRIPTION ,REF_CODE.DOMAIN FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :SUBACCOUNTTYPE AND     ACTIVE_FLAG = 'Y' AND coalesce (EXPIRED_DATE::timestamp, '2020-12-12')= '2020-12-12' AND DOMAIN = 'SUB_AC_TYPE' AND CODE != :SUBACCOUNTTYPE
}
OTDSUBAT_GET_DESCRIPTION {
 SELECT DESCRIPTION    FROM TRANSACTION_TYPES    WHERE TXN_TYPE = :txnType  AND CASELOAD_TYPE = :caseloadType
      }
OTDSUBAT_GET_ACCOUNT_CODE {
      SELECT account_code    FROM account_codes  WHERE caseload_type = :caseloadType AND sub_account_type = :fmSubAccountType
      }
OTDSUBAT_GET_CHECK_PRODUCTION_FLAG {
      --SELECT decode(bank_cr_account_code,
        --            null, cr_account_code,
          --                bank_cr_account_code 
            --    )AC_CODE,
          --cheque_production_flag
     --FROM transaction_operations
   -- WHERE module_name = :moduleName
     -- AND txn_type = :txnType
      -- AND cr_account_code = :crAccountCode
     -- AND dr_account_code = :drAcCode
      --AND caseload_id = :caseloadId
      SELECT case 
     when bank_cr_account_code is null then cr_account_code
      else bank_cr_account_code 
                 end AC_CODE 
          , cheque_production_flag
     FROM transaction_operations
    WHERE module_name = :moduleName
      AND txn_type = :txnType
      AND cr_account_code = :crAccountCode
      AND dr_account_code = :drAcCode
      AND caseload_id = :caseloadId 
        }
      
OTDSUBAT_GET_CORPORATE_ID_AND_NAME {      
     -- SELECT payee_corporate_id,
       --   corporate_name
    -- FROM caseload_current_accounts cca,
      --    corporates c
    -- WHERE cca.caseload_id = :caseloadId
      -- AND cca.bank_account_type = 'CHK'
     -- AND cca.payee_corporate_id = c.corporate_id
      -- AND cca.account_code
         --    IN (SELECT decode(bank_cr_account_code,
           --                       null, cr_account_code,
             --                           bank_cr_account_code
               --                )
                 --  FROM transaction_operations
                  -- WHERE module_name = :moduleName
                    -- AND txn_type = :txnType
                    -- AND cr_account_code = :crAccountCode
                   -- AND dr_account_code = :drAcCode
                   -- AND caseload_id = :caseloadId)
                   
   SELECT payee_corporate_id,
          corporate_name
     FROM caseload_current_accounts_fn(:userId) cca,
          corporates c
    WHERE cca.caseload_id = :caseloadId
      AND cca.bank_account_type = 'CHK'
      AND cca.payee_corporate_id = c.corporate_id
      AND cca.account_code
             IN (SELECT case 
                                   when bank_cr_account_code is null then  cr_account_code
                                         else bank_cr_account_code
                                end
                   FROM transaction_operations
                  WHERE module_name = :moduleName
                    AND txn_type = :txnType
                    AND cr_account_code = :crAccountCode
                    AND dr_account_code = :drAcCode
                    AND caseload_id = :caseloadId)
 
  }
OTDSUBAT_GET_TXN_ID {
-- SELECT TXN_ID.NEXTVAL TXN_ID FROM  DUAL
     select nextval('txn_id') from dual;
}
OTDSUBAT_GET_AC_CODE {
SELECT account_code
              FROM account_codes
             WHERE sub_account_type = :code
               AND caseload_type = :caseloadType
}
OTDSUBAT_GET_BAL {
--SELECT balance
  --            FROM offender_sub_accounts
    --         WHERE offender_id = :offenderId
      --         AND caseload_id = :caseloadId
        --       AND trust_account_code = TO_CHAR (:acCode)
           SELECT balance
              FROM offender_sub_accounts
             WHERE offender_id = :offenderId::bigint
               AND caseload_id = :caseloadId
               AND trust_account_code = :acCode::int
}
     OTDSUBAT_ACCOUNT_CLOSED_FLAG {
   SELECT account_closed_flag
              FROM offender_trust_accounts
             WHERE offender_id = :offenderId
               AND caseload_id = :caseloadId
               }   
  OTDSUBAT_GET_ROOTOFFENDER_ID {
  SELECT DISTINCT ROOT_OFFENDER_ID  FROM V_TRUST_HEADER WHERE OFFENDER_ID_DISPLAY=:offenderIdDisplay and CASELOAD_ID=:caseloadId and BOOKING_NO=:bookingNo
  }
  
  OTDSUBAT_UPDATE_PREPAIDE_ACCOUNT_STATUS{
   UPDATE OFF_FEE_ACCOUNT_PROFILE_HTY SET FEE_ACT_STATUS = :STATUS , STATUS_EFFECTIVE_DATE =SYSDATE WHERE offender_fee_id = :OFFENDER_FEE_ID
  }
  
  SELECT_OFF_FEE_ACCOUNT_PROFILE{
  SELECT FEE_CODE,OFFENDER_FEE_ID FROM OFF_FEE_ACCOUNT_PROFILE WHERE OFFENDER_BOOK_ID = :offenderBookId AND FEE_CODE='REG'
  }
  
  SELECT_OFFENDER_BOOK_ID{
  SELECT OFFENDER_BOOK_ID FROM OFFENDER_TRANSACTIONS WHERE CASELOAD_ID =:caseLoadId AND OFFENDER_ID=:offenderId   FETCH FIRST 1 ROWS ONLY
  }
  
  SELECT_OFFENDER_FEE_ID{
  SELECT OFFENDER_FEE_ID FROM OFF_FEE_ACCOUNT_PROFILE WHERE OFFENDER_BOOK_ID =:offenderBookId AND FEE_CODE =:feeCode
  }
  
  SELECT_FEE_ACCOUNT_PROFILE_DATA{
  SELECT FEE_ACT_STATUS FROM OFF_FEE_ACCOUNT_PROFILE WHERE OFFENDER_FEE_ID = :offenderFeeId
  }
  UPDATE_FEE_ACCOUNT_PROFILE{
  UPDATE  OFF_FEE_ACCOUNT_PROFILE SET FEE_ACT_STATUS = :status , STATUS_EFFECTIVE_DATE = SYSDATE WHERE OFFENDER_FEE_ID = :OFFENDERFEEID
  }
  
  SELECT_PREPAID_FEE_CODES{
  SELECT * FROM FEE_ACCOUNTS --WHERE EXPIRY_DATETIME IS NULL
  }
