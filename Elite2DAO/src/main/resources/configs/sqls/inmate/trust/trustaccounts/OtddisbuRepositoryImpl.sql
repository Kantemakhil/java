
OTDDISBU_FIND_CGFKOFFTXN1PAYEEPERSONID {
 	SELECT PER.PERSON_ID  PAYEE_PERSON_ID  /* CG$FK */        ,PER.LAST_NAME  DSP_LAST_NAME        ,PER.FIRST_NAME  DSP_FIRST_NAME FROM   PERSONS PER WHERE  PER.PERSON_ID = :PAYEEPERSONID
}

OTDDISBU_FIND_CGFKOFFTXN1TXNTYPE {
 	SELECT TXN_TYPE.TXN_TYPE TXN_TYPE /* CG$FK */  ,TXN_TYPE.DESCRIPTION DSP_DESCRIPTION  ,TXN_TYPE.LIST_SEQ DSP_LIST_SEQ FROM TRANSACTION_TYPES TXN_TYPE 
 	WHERE TXN_TYPE IN (SELECT TXN_TYPE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OTDDISBU' AND CASELOAD_ID = :CASELOADID ) AND
 	TXN_TYPE NOT IN (SELECT CREDIT_OBLIGATION_TYPE FROM TRANSACTION_TYPES WHERE CREDIT_OBLIGATION_TYPE IS NOT NULL AND CASELOAD_TYPE = :CASELOADTYPE )
 	AND CASELOAD_TYPE = :CASELOADTYPE ORDER BY TXN_TYPE.TXN_TYPE , TXN_TYPE.LIST_SEQ
}

OTDDISBU_FIND_CGFKOFFTXN1PAYEECORPORATE {
 	SELECT TO_CHAR(CORP.CORPORATE_ID) CODE ,CORP.CORPORATE_NAME  DESCRIPTION FROM (SELECT CRP.CORPORATE_ID , CRP.CORPORATE_NAME        FROM CORPORATES CRP , CORPORATE_TYPES CTP       WHERE CRP.CORPORATE_ID = CTP.CORPORATE_ID        AND CTP.CORPORATE_TYPE NOT IN ('VENDOR' ,'PROGRAM' )       UNION       SELECT CORPORATE_ID ,CORPORATE_NAME       FROM CORPORATES CRP2       WHERE NOT EXISTS ( SELECT 1 FROM CORPORATE_TYPES CTP2 WHERE CRP2.CORPORATE_ID = CTP2.CORPORATE_ID  )                      ) CORP
}

OTDDISBU_OFFTXN1_FIND_OFFENDER_TRANSACTIONS {
 	SELECT TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID   FROM OFFENDER_TRANSACTIONS  /* where  */
}
OTDDISBU_OFFTXN1_INSERT_OFFENDER_TRANSACTIONS {
	INSERT INTO OFFENDER_TRANSACTIONS(TXN_ID ,TXN_ENTRY_SEQ ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_POSTING_TYPE ,TXN_TYPE ,TXN_ENTRY_DESC ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DATE ,SUB_ACCOUNT_TYPE ,TXN_REFERENCE_NUMBER ,MODIFY_DATE ,RECEIPT_NUMBER ,SLIP_PRINTED_FLAG ,TRANSFER_CASELOAD_ID ,RECEIPT_PRINTED_FLAG ,PRE_WITHHOLD_AMOUNT ,DEDUCTION_FLAG ,CLOSING_CHEQUE_NUMBER ,REMITTER_NAME ,PAYEE_CODE ,PAYEE_NAME_TEXT ,PAYEE_CORPORATE_ID ,PAYEE_PERSON_ID ,ADJUST_TXN_ID ,ADJUST_TXN_ENTRY_ID ,ADJUST_OFFENDER_ID ,ADJUST_ACCOUNT_CODE ,TXN_ADJUSTED_FLAG ,DEDUCTION_TYPE ,INFO_NUMBER ,HOLD_CLEAR_FLAG ,HOLD_UNTIL_DATE ,HOLD_NUMBER ,GROSS_AMOUNT ,GROSS_NET_FLAG ,REMITTER_ID ,APPLY_SPENDING_LIMIT_AMOUNT ,RECEIPT_PENDING_PRINT_FLAG ,SEAL_FLAG ,ORG_TXN_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME  ) VALUES(:txnId ,:txnEntrySeq ,:caseloadId ,:offenderId ,:offenderBookId ,:txnPostingType ,:txnType ,:txnEntryDesc ,:txnEntryAmount ,:txnEntryDate ,:subAccountType ,:txnReferenceNumber ,:modifyDate ,:receiptNumber ,:slipPrintedFlag ,:transferCaseloadId ,:receiptPrintedFlag ,:preWithholdAmount ,:deductionFlag ,:closingChequeNumber ,:remitterName ,:payeeCode ,:payeeNameText ,:payeeCorporateId ,:payeePersonId ,:adjustTxnId ,:adjustTxnEntryId ,:adjustOffenderId ,:adjustAccountCode ,:txnAdjustedFlag ,:deductionType ,:infoNumber ,:holdClearFlag ,:holdUntilDate ,:holdNumber ,:grossAmount ,:grossNetFlag ,:remitterId ,:applySpendingLimitAmount ,:receiptPendingPrintFlag ,:sealFlag ,:orgTxnType ,current_timestamp ,:createUserId ,current_timestamp  )
}

OTDDISBU_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OTDDISBU_CGFKCHK_OFF_TXN1_OFF_TXN_PER__ {
	SELECT PER.LAST_NAME ,PER.FIRST_NAME FROM   PERSONS PER WHERE  PER.PERSON_ID = :PAYEEPERSONID
}

OTDDISBU_CGFKCHK_OFF_TXN1_OFF_TXN_CORP_ {
	SELECT CORP.CORPORATE_NAME FROM   CORPORATES CORP WHERE  CORP.CORPORATE_ID = :PAYEECORPORATEID
}

OTDDISBU_CGFKCHK_OFF_TXN1_OFF_TXN_TXN {
	SELECT TXN_TYPE.DESCRIPTION ,TXN_TYPE.LIST_SEQ FROM   TRANSACTION_TYPES TXN_TYPE WHERE  TXN_TYPE.TXN_TYPE = :TXNTYPE AND     TXN_TYPE IN (SELECT TXN_TYPE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OTDDISBU' ) AND TXN_TYPE NOT IN (SELECT CREDIT_OBLIGATION_TYPE FROM TRANSACTION_TYPES WHERE CREDIT_OBLIGATION_TYPE IS NOT NULL)
}

OTDDISBU_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}

OTDDISBU_CGFKCHK_OFF_TXN_OFF_TXN_OFF_N_ {
	SELECT OFF_NAME.LAST_NAME ,OFF_NAME.FIRST_NAME FROM   OFFENDERS OFF_NAME --      WHERE  OFF_NAME.OFFENDER_ID = :OFFENDERID
}
OTDDISBU_CHEQUE_PRODUCTION_FLAG {

SELECT coalesce(CHEQUE_PRODUCTION_FLAG,'N')
              FROM TRANSACTION_OPERATIONS
             WHERE MODULE_NAME = 'OTDDISBU'
               AND TXN_TYPE    = :TXN_TYPE
               AND CASELOAD_ID = :CASELOADID
               }
GET_DEBIT_ACT_CODE {
SELECT dr_account_code  FROM transaction_operations  WHERE module_name = 'OTDDISBU' AND txn_type = :txntype AND caseload_id = :caseloadId
}               
GET_OFFENDER_SUB_BALANCE_FET_BAL_ONE {
--SELECT NVL (balance, 0) balance, minimum_balance, ind_date, ind_days FROM offender_sub_accounts  
--		WHERE caseload_id = :caseloadId AND offender_id =  :offenderId AND trust_account_code = :trustAccountCode
--        FOR UPDATE OF balance
SELECT coalesce(balance, 0) balance, minimum_balance, ind_date, ind_days FROM offender_sub_accounts  
		WHERE caseload_id = :caseloadId AND offender_id =  :offenderId AND trust_account_code = :trustAccountCode
}
GET_OFFENDER_SUB_BALANCE_FET_BAL_TWO {
--SELECT NVL (balance, 0) balance, minimum_balance, ind_date, ind_days FROM offender_sub_accounts  
--		WHERE caseload_id = :caseloadId AND offender_id =  :offenderId AND trust_account_code = :trustAccountCode
SELECT coalesce (balance, 0) balance, minimum_balance, ind_date, ind_days FROM offender_sub_accounts  
		WHERE caseload_id = :caseloadId AND offender_id =  :offenderId AND trust_account_code = :trustAccountCode
}
GET_IN_DAYS {
	SELECT COALESCE(ind_days, 0) FROM institution_mini_balances WHERE caseload_id = :caseloadId AND agy_loc_id 
        IN (SELECT agy_loc_id FROM v_header_block2_fn(:USERID) v_header_block2 
                WHERE root_offender_id = :offenderId
                    AND offender_book_id = (SELECT MAX (offender_book_id) FROM v_header_block2_fn(:USERID) v_header_block2
                                                WHERE root_offender_id = :offenderId)) AND account_code = :accountCode
}
GET_IN_DAYS_TWO {
SELECT COALESCE(ind_days, 0) FROM institution_mini_balances
                    WHERE caseload_id = :caseloadId AND agy_loc_id = 'OUT' AND account_code = :accountCode
}
GET_MIN_BAL {
	SELECT COALESCE (minimum_account_balance, 0) FROM institution_mini_balances
          WHERE caseload_id = :caseloadId AND agy_loc_id IN (SELECT agy_loc_id
               FROM v_header_block2_fn(:USERID) v_header_block2  WHERE root_offender_id = :offenderId  AND 
                    offender_book_id = (SELECT MAX (offender_book_id)
                                 FROM v_header_block2_fn(:USERID) v_header_block2 WHERE root_offender_id = :offenderId))
                                    AND account_code = :accountCode
}
GET_OFFENDER_BOOK_ID_A {
SELECT offender_book_id FROM v_header_block2_fn(:USERID) v_header_block2 WHERE root_offender_id = :offenderId AND active_flag = 'Y'
}
GET_TXNUSAGE {
	SELECT txn_usage FROM transaction_types WHERE txn_type = :txnType AND caseload_type = 
(SELECT CASELOAD_TYPE  FROM CASELOADS   WHERE
 CASELOAD_ID =   (  SELECT WORKING_CASELOAD_ID      FROM STAFF_MEMBERS        WHERE USER_ID = :USER_ID))
}
GET_SUB_ACT_TYPE {
SELECT sub_account_type, 'DR' DR  FROM account_codes    WHERE 
account_code = (SELECT dr_account_code  FROM transaction_operations  WHERE module_name = :moduleName
AND txn_type = :txnType AND caseload_id = :caseloadId)      AND 
caseload_type = (SELECT CASELOAD_TYPE  FROM CASELOADS   WHERE
 CASELOAD_ID =   (  SELECT WORKING_CASELOAD_ID      FROM STAFF_MEMBERS        WHERE USER_ID = :USER_ID))
}
GET_TXN_FEE_TYPE {
 SELECT deduction_type, offender_deduction_id
	              FROM offender_deductions od
	             WHERE offender_id = :offenderId
	               AND caseload_id = :caseloadId
	               AND EXISTS (SELECT  '1'
	                             FROM offender_deduction_receipts odr
	                            WHERE receipt_txn_type = :txnType
	                              AND od.offender_deduction_id = odr.offender_deduction_id)
	               AND deduction_type IN (SELECT deduction_type
	                                        FROM deduction_types
	                                       WHERE deduction_category = 'DTF'
	                                         AND caseload_code IN ('BOTH', 'INST'))
}
GET_TXN_CREDIT_OBLIGATION {
	SELECT txn_usage, credit_obligation_type  FROM transaction_types WHERE txn_type = :txnType
}
GET_CROB {
SELECT 'Y' FROM deduction_types  WHERE deduction_type = :obligationtype AND deduction_category = 'CROB' AND caseload_code IN ('BOTH', 'INST')
}
GET_INDIGENT_FLAG {
SELECT COALESCE (co_credit_when_indigent_flag, 'N')  FROM caseload_deduction_profiles WHERE caseload_id = :caseloadId AND deduction_type = :obligationtype
}
CTR_WASH_SPECIFIC {
SELECT COUNT (*) FROM system_profiles WHERE profile_type = 'CLIENT'AND profile_code = 'WASHINGTON' AND profile_value = 'Y'
}
GET_LIMIT_AMOUNT_AND_PERIOD_TYPE {
SELECT limit_amount, COALESCE (period_type, 'WEEK') period_type FROM offender_limits WHERE caseload_id = :caseloadId AND offender_id = :offenderId AND limit_type = :obligationtype
}
GET_LIMIT_AMOUNT_AND_PERIOD_TYPE_ONE {
SELECT limit_amount, COALESCE (period_type, 'WEEK') period_type FROM caseload_limits WHERE caseload_id = :caseloadId AND limit_type = :obligationtype
}
GET_WEEK_DAY {
SELECT TO_CHAR (SYSDATE(), 'D')  FROM dual
}
GET_FROM_DATE {
--SELECT SYSDATE - (:weekDay - 1) FROM dual
select
	SYSDATE() - (:weekday - 1)* interval '1 day'
from
	dual
}
GET_TO_DATE {
-- SELECT SYSDATE + (7 - :weekDay) FROM dual 
	SELECT SYSDATE() + (7 - :weekDay)* interval '1 day' FROM dual 
}
GET_FROM_DATE_ONE {
-- SELECT ADD_MONTHS (LAST_DAY (SYSDATE), -1) + 1    FROM dual
select	
	ADD_MONTHS (LAST_DAY (SYSDATE()),
	-1)+ 1 * interval '1 day'
from
	dual
}
GET_TO_DATE_ONE {
 SELECT LAST_DAY (SYSDATE()) FROM dual 
}
GET_AMOUNT_WRITTEN_OFF {
SELECT COALESCE (SUM (COALESCE (adjustment_amount, 0)), 0) FROM offender_deductions  WHERE caseload_id = :caseloadId AND offender_id = :offenderId
               AND effective_date BETWEEN :fromdate AND :todate AND deduction_type = :obligationtype
}
GET_TOTAL_TAKEN {
SELECT COALESCE (SUM (COALESCE (txn_entry_amount, 0)), 0) FROM offender_transactions WHERE offender_id = :offenderId  AND deduction_type = :obligationtype
AND txn_type = :obligationtype  AND txn_posting_type = 'CR' AND caseload_id = :caseloadId   AND txn_entry_date BETWEEN :fromdate AND :todate
}
GET_TOTAL_REVERSED {
	SELECT COALESCE (SUM (COALESCE (txn_entry_amount, 0)), 0)  FROM offender_transactions WHERE offender_id = :offenderId AND deduction_type = :obligationtype
      AND txn_type = :obligationtype AND txn_posting_type = 'DR' AND caseload_id = :caseloadId AND txn_entry_date BETWEEN :fromdate AND :todate
      AND (adjust_txn_id, adjust_txn_entry_id) IN 
    (SELECT txn_id, txn_entry_seq FROM offender_transactions WHERE offender_id = :offenderId AND deduction_type = :obligationtype
       AND txn_type = :obligationtype AND txn_posting_type = 'CR' AND caseload_id = :caseloadId)  AND txn_entry_date BETWEEN :fromdate AND :todate
}
GEN_TRUST_TRANS {
-- SELECT #SEQ.NEXTVAL NEXT_SEQ FROM DUAL
 select nextval('#SEQ') 

 }
 L_MODULE_FLAGS_CUR {
SELECT COALESCE (cheque_production_flag, 'N') cheque_production_flag, COALESCE (receipt_production_flag, 'N') receipt_production_flag,  txn_usage  
FROM transaction_operations top, transaction_types tt
             WHERE top.module_name = 'OTDDISBU'  AND top.txn_type = tt.txn_type AND top.txn_type = :txnType AND caseload_id = :caseloadId
}
 GET_V_PRO_VALUE {
 	SELECT profile_value FROM system_profiles WHERE profile_type = 'CLIENT' AND profile_code = 'ID_DISPLAY'
 }