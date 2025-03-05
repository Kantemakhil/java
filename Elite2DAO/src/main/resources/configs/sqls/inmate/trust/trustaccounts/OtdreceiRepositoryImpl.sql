
OTDRECEI_FIND_CGFKOFFTXN1TXNTYPE {
 	SELECT TXN_TYPE.TXN_TYPE CODE /* CG$FK */  ,TXN_TYPE.DESCRIPTION DESCRIPTION  ,TXN_TYPE.LIST_SEQ LIST_SEQ FROM TRANSACTION_TYPES TXN_TYPE WHERE TXN_TYPE IN (SELECT TXN_TYPE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OTDRECEI' AND CASELOAD_ID = :caseloadId ) AND CASELOAD_TYPE = 'INST' ORDER BY TXN_TYPE.TXN_TYPE , TXN_TYPE.LIST_SEQ
}

OTDRECEI_OFFTXN1_FIND_OFFENDER_TRANSACTIONS {
 	SELECT TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID   FROM OFFENDER_TRANSACTIONS  /* where  */
}
OTDRECEI_OFFTXN1_INSERT_OFFENDER_TRANSACTIONS {
	INSERT INTO OFFENDER_TRANSACTIONS(TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME  ) VALUES(:txnId ,:txnEntrySeq ,:caseloadId ,:offenderId ,:offenderBookId ,:txnPostingType ,:txnType ,:txnEntryDesc ,:txnEntryAmount ,:txnEntryDate ,:subAccountType ,:txnReferenceNumber ,:modifyDate ,:receiptNumber ,:slipPrintedFlag ,:transferCaseloadId ,:receiptPrintedFlag ,:preWithholdAmount ,:deductionFlag ,:closingChequeNumber ,:remitterName ,:payeeCode ,:payeeNameText ,:payeeCorporateId ,:payeePersonId ,:adjustTxnId ,:adjustTxnEntryId ,:adjustOffenderId ,:adjustAccountCode ,:txnAdjustedFlag ,:deductionType ,:infoNumber ,:holdClearFlag ,:holdUntilDate ,:holdNumber ,:grossAmount ,:grossNetFlag ,:remitterId ,:applySpendingLimitAmount ,:receiptPendingPrintFlag ,:sealFlag ,:orgTxnType ,current_timestamp ,:createUserId ,current_timestamp  )
}

OTDRECEI_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OTDRECEI_OFF_TXN1_WHENVALIDATERECORDWHEN-VALIDATE-RECORD {
	SELECT SUB_ACCOUNT_TYPE, RECEIPT_PRODUCTION_FLAG INTO SUB_ACT_TYPE, RECEIPT_FLAG FROM ACCOUNT_CODES AC, TRANSACTION_OPERATIONS TOP WHERE AC.SUB_ACCOUNT_TYPE IS NOT NULL AND AC.ACCOUNT_CODE = TOP.CR_ACCOUNT_CODE AND TOP.MODULE_NAME = CURRENT_FORM AND TOP.TXN_TYPE = TRANS_TYPE AND AC.CASELOAD_TYPE = 'INST' AND TOP.CASELOAD_ID = CSLD_ID
}

OTDRECEI_CGFKCHK_OFF_TXN1_OFF_TXN_TXN__ {
	SELECT TXN_TYPE.DESCRIPTION ,TXN_TYPE.LIST_SEQ FROM   TRANSACTION_TYPES TXN_TYPE WHERE  TXN_TYPE.TXN_TYPE = :TXNTYPE AND     TXN_TYPE IN (SELECT TXN_TYPE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OTDRECEI' AND   CASELOAD_ID = :CASELOADID) AND CASELOAD_TYPE = 'INST'
}

OTDRECEI_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}

OTDRECEI_CHK_RECEIPT_FLAG {
	 SELECT RECEIPT_PRODUCTION_FLAG FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OTDRECEI'
      AND TXN_TYPE = :txnType  AND CASELOAD_ID = :caseloadId
}
OTDRECEI_GEN_TRUST_TRANS {
-- SELECT #SEQ.NEXTVAL NEXT_SEQ FROM DUAL
 select NEXTVAL('#SEQ')

 }
 OTDRECEI_INSRT_INTO_OFFENDER_TRANS_FORM {
--INSERT INTO offender_transactions(
--txn_id, txn_entry_seq, caseload_id, offender_id, offender_book_id, txn_posting_type, txn_type, txn_entry_desc, txn_entry_amount, txn_entry_date, sub_account_type, modify_date, modify_user_id, slip_printed_flag, pre_withhold_amount, deduction_flag, payee_code, payee_corporate_id, payee_person_id, payee_name_text, deduction_type, info_number, remitter_name, remitter_id, receipt_number)
--SELECT  :txnId, :txnEntrySeq, :caseloadId, :offenderId, :offenderBookId, :txnPostingType, :txnType, :txnEntryDesc, :txnEntryAmount, TRUNC (SYSDATE), :subAccountType,  TRUNC (SYSDATE),  USER, 'N', NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL, :remitterName, :remitterId, :receiptNumber
--FROM DUAL 
insert
	into
	offender_transactions(txn_id,
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
	slip_printed_flag,
	pre_withhold_amount,
	deduction_flag,
	payee_code,
	payee_corporate_id,
	payee_person_id,
	payee_name_text,
	deduction_type,
	info_number,
	remitter_name,
	remitter_id,
	receipt_number,
	create_datetime,
	create_user_id,
	modify_datetime )
select
	:txnId,
	:txnEntrySeq,
	:caseloadId,
	:offenderId,
	:offenderBookId,
	:txnPostingType,
	:txnType,
	:txnEntryDesc,
	:txnEntryAmount,
	current_date ,
	:subAccountType,
	current_date ,
	'N',
	null,
	'N',
	null,
	null,
	null,
	null,
	null,
	null,
	:remitterName,
	:remitterId,
	:receiptNumber,
	current_timestamp,
	:createUserId,
	current_timestamp
from
	DUAL
}
OTDRECEI_DAYS_CUR {
--SELECT NVL(DAYS, 0) DAYS,TXN_USAGE
--            FROM   TRANSACTION_TYPES TT
--            WHERE  TT.TXN_TYPE = :txnType
SELECT COALESCE(DAYS, 0) DAYS,TXN_USAGE
            FROM   TRANSACTION_TYPES TT
            WHERE  TT.TXN_TYPE = :txnType
            }
OTDRECEI_GET_MAX_TXN_ENTRY_SEQ {        
SELECT MAX(TXN_ENTRY_SEQ)  FROM GL_TRANSACTIONS GT  WHERE GT.TXN_ID = :txnId
}

OTDRECEI_REOPEN_CLOSED_TRUST_ACCOUNT {
UPDATE offender_trust_accounts SET account_closed_flag = 'N', modify_user_id =:modifyUserId, modify_datetime = current_timestamp WHERE offender_id = :offenderId  AND caseload_id = :caseloadId
}
OTDRECEI_GET_R_TXN_TYPE {
SELECT txn_type   FROM transaction_operations  WHERE module_name = :moduleName AND caseload_id = :caseloadId
}
OTDRECEI_GET_SUB_ACC_TYPE {
SELECT sub_account_type, txn_posting_type   FROM account_codes
       WHERE account_code = (SELECT cr_account_code
                               FROM transaction_operations
                              WHERE module_name = :moduleName
                                AND txn_type = :txnType
                                AND caseload_id = :caseloadId)  
         AND caseload_type = 'INST'
}
