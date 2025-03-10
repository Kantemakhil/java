
OTDCRVOI_FIND_CGFKBANKCRCHEQUESTATUS {
 	SELECT REF_CODE.CODE ,REF_CODE.DOMAIN DSP_DOMAIN  ,REF_CODE.DESCRIPTION  FROM REFERENCE_CODES REF_CODE WHERE DOMAIN = 'CHEQUE_STS' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR '' = 'QUERY' )
}

OTDCRVOI_FIND_CGFKBANKCRACCOUNTCODE {
 	SELECT TO_CHAR(CSLD_CA.ACCOUNT_CODE)  CODE  ,CSLD_CA.CASELOAD_ID  CASELOAD_ID        ,AC_CODE.ACCOUNT_NAME  DESCRIPTION FROM CASELOAD_CURRENT_ACCOUNTS_FN(:userId) CSLD_CA        ,ACCOUNT_CODES AC_CODE WHERE  AC_CODE.ACCOUNT_CODE = CSLD_CA.ACCOUNT_CODE AND     BANK_ACCOUNT_TYPE = 'CHK' AND CASELOAD_ID = :caseloadId
}

OTDCRVOI_BANKCR_FIND_BANK_CHEQUE_REGISTERS {
 	SELECT CASELOAD_ID ,ACCOUNT_CODE ,CHEQUE_NUMBER ,CHEQUE_STATUS ,TRANSACTION_DATE ,START_TXN_ID ,TXN_ID ,END_TXN_ID ,ORIGIN_TYPE ,REASON_TEXT ,CREATE_USER_ID ,CREATE_DATE ,LIST_SEQ ,CHEQUE_PAID_DATE ,RCNL_CLR ,CREATE_DATETIME ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM BANK_CHEQUE_REGISTERS  
}
OTDCRVOI_BANKCR_UPDATE_BANK_CHEQUE_REGISTERS {
	UPDATE BANK_CHEQUE_REGISTERS SET CHEQUE_STATUS = :chequeStatus ,   REASON_TEXT = :reasonText , MODIFY_DATETIME=CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId WHERE CASELOAD_ID = :caseLoadId  AND ACCOUNT_CODE= :accountCode AND CHEQUE_NUMBER = :chequeNumber 
}

OTDCRVOI_BNKCD_FIND_BANK_CHEQUE_DATA {
 	SELECT TXN_ID ,BANK_ACCOUNT_CODE ,CASELOAD_ID ,MODIFY_USER_ID ,MODIFY_DATE ,START_TXN_ID ,END_TXN_ID ,TXN_ENTRY_AMOUNT ,PAYEE_PERSON_ID ,PAYEE_CORPORATE_ID ,PAYEE_NAME_TEXT ,SINGLE_ENTRY_FLAG ,CHEQUE_FLAG ,CHEQUE_DATE ,TXN_ENTRY_DATE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG
   			FROM BANK_CHEQUE_DATA WHERE TXN_ID = :TXN_ID
}

OTDCRVOI_BANK_CR_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM BANK_CHEQUE_DATA WHERE TXN_ID = :TXNID
}

OTDCRVOI_CGFKCHK_BANK_CR_BANK_CR_REF_C {
	SELECT REF_CODE.DOMAIN ,REF_CODE.DESCRIPTION FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :CHEQUESTATUS AND    DOMAIN = 'CHEQUE_STS' -- @@@ JOE WONG, 02/27/2002. TRACKING#11090. AND    ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL) OR :MODE = 'QUERY')
}

OTDCRVOI_CGFKCHK_BANK_CR_BANK_CR_GL_AC {
	SELECT AC_CODE.ACCOUNT_NAME FROM   CASELOAD_CURRENT_ACCOUNTS CSLD_CA ,ACCOUNT_CODES AC_CODE WHERE  CSLD_CA.CASELOAD_ID = :CASELOADID AND    CSLD_CA.ACCOUNT_CODE = :ACCOUNTCODE AND    AC_CODE.ACCOUNT_CODE = CSLD_CA.ACCOUNT_CODE AND     BANK_ACCOUNT_TYPE = 'CHK' AND CASELOAD_ID = :CASELOADID
}

OTDCRVOI_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}
OTDCRVOI_WHEN_VALIDATE_GET_CHECK_TXN_TYPE {
	SELECT DISTINCT gl.txn_type FROM gl_transactions gl WHERE gl.txn_id = :txnId
            AND EXISTS (SELECT '1' FROM offender_trust_transfers ott WHERE ott.txn_id = :txnId AND ott.posted_flag = 'Y')
}
OTDCRVOI_WHEN_VALIDATE_GET_CHECK_CLEAR_TXN {
	SELECT DISTINCT RECON_CLEAR_FLAG FROM GL_TRANSACTIONS GL WHERE GL.TXN_ID = :txnId AND RECON_CLEAR_FLAG = 'Y'
}
OTDCRVOI_VERIFY_TXN_TYPE_COUNT {
	SELECT DISTINCT TXN_TYPE TXN_TYPE FROM GL_TRANSACTIONS WHERE TXN_ID = :txnId
}
OTDCRVOI_GET_C_ONE_OFFENDER_DETAIL {
	SELECT   glt.txn_entry_seq, glt.gl_entry_seq, glt.caseload_id, glt.account_code, glt.txn_entry_amount, glt.txn_type, glt.txn_post_usage, acc.txn_posting_type, glt.offender_id,
         glt.offender_book_id, glt.payee_person_id, glt.payee_corporate_id FROM gl_transactions glt, account_codes acc
         WHERE glt.account_code = acc.account_code AND txn_id = :txnId AND (   (    :chequeFlag = 'O' AND glt.txn_entry_seq IN (SELECT txn_entry_seq FROM offender_transactions
         WHERE txn_id = :txnId AND (   payee_person_id IS NOT NULL OR payee_corporate_id IS NOT NULL  OR :payeeNameText IS NOT NULL )))  OR :chequeFlag IN ('P', 'G'))
         AND txn_reversed_flag = 'N' ORDER BY glt.txn_entry_seq DESC
}
OTDCRVOI_UPDATE_OFFENDER_TRUST_ACCOUNTS_RE_OPEN {
	UPDATE OFFENDER_TRUST_ACCOUNTS SET ACCOUNT_CLOSED_FLAG = 'N', MODIFY_DATETIME=CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId WHERE OFFENDER_ID = :offenderId AND CASELOAD_ID = :caseloadId
}
OTDCRVOI_GEN_TRUST_TRANS {
 select nextval('#SEQ') FROM DUAL; 
 }
OTDCRVOI_TRUST_INSERT_INTO_OFFENDER_TRANS {
	CALL OMS_OWNER.TRUST.INSERT_INTO_OFFENDER_TRANS(:P_TRANS_NUMBER, :P_TRANS_SEQ, :P_CSLD_ID, :P_OFF_ID, :P_OFF_BOOK_ID, :P_TRANS_POST_TYPE, :P_TRANS_TYPE, :P_TRANS_DESC, :P_TRANS_AMOUNT, :P_TRANS_DATE, :P_SUB_ACT_TYPE, :P_DEDUCTION_FLAG, :P_PRE_DED_AMOUNT, :P_DEDUCTION_TYPE, :P_PAYEE_CORP_ID, :P_PAYEE_PERSON_ID, :P_INFO_NUMBER, :P_SLIP_PRINT_FLAG, :P_ALLOW_OVERDRAWN)
}
OTDCRVOI_TRUST_PROCESS_GL_TRANS_NEW {
CALL OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC, :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT, :P_GL_SQNC, :P_OFF_DED_ID)
}
OTDCRVOI_UPDATE_GL_TRANSACTION_REVERSED_FLAG {
	 UPDATE GL_TRANSACTIONS SET TXN_REVERSED_FLAG = 'Y', MODIFY_DATETIME=CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId WHERE TXN_ID = :txnId  AND TXN_ENTRY_SEQ = :txnEntrySeq AND GL_ENTRY_SEQ = :glEntrySeq
}
OTDCRVOI_TRUST_INSERT_GL_TRANS_NEW {
CALL OMS_OWNER.TRUST.INSERT_GL_TRANS_NEW(:P_POST_TYPE, :P_ACCOUNT_CODE, :P_ACNT_POSTING, :P_CSLD_ID, :P_TRANS_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC, :P_TRANS_SEQ, :P_GL_SQNC, :P_OFF_ID, :P_OFF_BOOK_ID, :P_INFO_NUMBER, :P_PAYEE_PERSON_ID, :P_PAYEE_CORPORATE_ID, :P_PAYEE_NAME_TEXT, :P_REVR_TXN_ID, :P_REVR_TXN_ENTRY_SEQ, :P_REVR_GL_ENTRY_SEQ, :P_TXN_REF_NUMBER, :P_OFF_DED_ID)
}
OTDCRVOI_UPDATE_GL_TRANSACTION_REVERSED_FLAG_AND_VALUES {
	 UPDATE GL_TRANSACTIONS SET TXN_REVERSED_FLAG = :vTxnReversedFlag, REVERSED_TXN_ID = :reversedTxnId, REVERSED_TXN_ENTRY_SEQ = :reversedTxnEntrySeq, REVERSED_GL_ENTRY_SEQ = :reversedGlEntrySeq,  MODIFY_DATETIME=CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId
        WHERE TXN_ID = :txnId AND TXN_ENTRY_SEQ = :txnEntrySeq AND GL_ENTRY_SEQ = :glEntrySeq
}
OTDCRVOI_C_OFF_TXN_OFFENDER_DETAIL {
	SELECT   ot.txn_entry_seq, ot.caseload_id, ot.txn_posting_type,  ot.sub_account_type, ot.txn_entry_amount, ot.txn_type, ot.offender_id, ot.offender_book_id, ot.payee_person_id,
         ot.payee_corporate_id  FROM offender_transactions ot  WHERE txn_id = :txnId  AND (   (    :chequeFlag = 'O' AND (   payee_person_id IS NOT NULL OR payee_corporate_id IS NOT NULL
         OR :payeeNameText IS NOT NULL)) OR :chequeFlag IN ('P', 'G')) AND adjust_txn_id IS NULL ORDER BY ot.txn_entry_seq DESC
}
OTDCRVOI_INSERT_INTO_OFFENDER_TRANSACTIONS_VIA_QUERY {
	INSERT INTO offender_transactions (txn_id, txn_entry_seq, txn_entry_date, caseload_id, offender_id, offender_book_id, modify_user_id, modify_date, slip_printed_flag,
            txn_entry_amount, txn_entry_desc, txn_reference_number, txn_type, txn_posting_type, receipt_number, sub_account_type, payee_code, payee_name_text, 
            payee_corporate_id, payee_person_id, adjust_txn_id, adjust_txn_entry_id, adjust_offender_id, adjust_account_code, txn_adjusted_flag)
            SELECT :txnId, :txnEntrySeq, current_timestamp,  caseload_id, offender_id, offender_book_id, CREATE_USER_ID, current_timestamp, 'N', txn_entry_amount,
            SUBSTR ('REV GJ#' || TO_CHAR (:oldTxnId) ||' '|| RTRIM (txn_entry_desc), 1, 40), txn_reference_number, txn_type, :txnPostingType, receipt_number, 
            sub_account_type, payee_code, payee_name_text, payee_corporate_id, payee_person_id, txn_id, txn_entry_seq, offender_id, adjust_account_code, 'Y'
            FROM offender_transactions WHERE txn_id = :oldTxnId AND txn_entry_seq = :oldTxnEntrySeq
}
OTDCRVOI_UPDATE_OFFENDER_TRANSACTION_TXN_ADJECENMENT_FLAG {
UPDATE offender_transactions SET txn_adjusted_flag = 'Y', MODIFY_DATETIME=CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId WHERE txn_id = :txnId AND txn_entry_seq = :txnEntrySeq
}
OTDCRVOI_UPDATE_OFFENDER_BALANCE {
CALL OMS_OWNER.TRUST.UPDATE_OFFENDER_BALANCE(:P_CSLD_ID, :P_OFF_ID, :P_TRANS_POST_TYPE, current_timestamp, :P_TRANS_NUMBER, :P_TRANS_TYPE, :P_TRANS_AMOUNT, :P_SUB_ACT_TYPE, :P_ALLOW_OVERDRAWN)
}
OTDCRVOI_DEDUCTION_GET_AC_AND_SET_IND_DATE {
	CALL OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:P_OFF_ID, :P_CSLD_ID)
}
OTDCRVOI_GET_BENEFICIARIES_INFORMATION {
SELECT TXN_ID, PERSON_ID, CORPORATE_ID, AMOUNT, OFFENDER_DEDUCTION_ID FROM BANK_CHEQUE_BENEFICIARIES  WHERE CHEQUE_TXN_ID = :chequeTxnId
}
OTDCRVOI_UPDATE_BENEFICIARY_TRANSACTIONS {
UPDATE beneficiary_transactions  SET beneficiary_cleared_flag = 'N', MODIFY_DATETIME=CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId  WHERE txn_id = :txnId  AND coalesce (person_id, 0) = coalesce (:personId, 0)
AND coalesce (corporate_id, 0) = coalesce (:corporateId, 0) AND OFFENDER_DEDUCTION_ID = :offenderDeductionId
}
OTDCRVOI_C_GET_ACCOUNT_CODE {
SELECT account_code FROM beneficiary_transactions WHERE txn_id = :txnId  AND coalesce (person_id, 0) = coalesce (:personId, 0) AND coalesce (corporate_id, 0) = coalesce (:corporateId, 0)
         AND offender_deduction_id = :offenderDeductionId
}
OTDCRVOI_C_GET_OFFENDER_ID {
WITH RESULTS AS (SELECT distinct OFFENDER_ID, DENSE_RANK () OVER (ORDER BY TXN_ENTRY_SEQ) AS OFFENDERID FROM GL_TRANSACTIONS WHERE GL_TRANSACTIONS.TXN_ID = :TXNID)
SELECT * FROM RESULTS WHERE RESULTS.OFFENDERID=1

}