
OTDGLIRT_FIND_CGFKGLTXNREVERSALREASON {
 	SELECT REF_CODE3.CODE CODE /* CG$FK */  ,REF_CODE3.DESCRIPTION DESCRIPTION , REF_CODE3.LIST_SEQ LIST_SEQ FROM REFERENCE_CODES REF_CODE3 WHERE DOMAIN = 'CF_REV_RSN' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) OR 'QUERY' = 'QUERY' ) ORDER BY REF_CODE3.CODE ASC
}

OTDGLIRT_GLTXN_FIND_GL_TRANSACTIONS {
 	select A.*, B.RECEIPT_NUMBER, C.OFFENDER_ID_DISPLAY from ( select TXN_ID , TXN_ENTRY_SEQ , GL_ENTRY_SEQ , ACCOUNT_PERIOD_ID , ACCOUNT_CODE , TXN_ENTRY_DATE , TXN_TYPE , TXN_POST_USAGE , CASELOAD_ID , OFFENDER_ID , OFFENDER_BOOK_ID , TXN_ENTRY_AMOUNT , TXN_ENTRY_DESC , TXN_REFERENCE_NUMBER , BANK_STATEMENT_DATE , RECON_CLEAR_FLAG , TXN_REVERSED_FLAG , REVERSED_TXN_ID , PAYEE_PERSON_ID , REVERSED_TXN_ENTRY_SEQ , REVERSED_GL_ENTRY_SEQ , PAYEE_CORPORATE_ID , PAYEE_NAME_TEXT , TXN_OBJECT_CODE , LIST_SEQ , TXN_OBJECT_ID , CREATE_USER_ID , CREATE_DATE , INFO_NUMBER , DEDUCTION_ID , TXN_ENTRY_TIME , RECEIPT_NUMBER , REVERSAL_REASON_CODE , TXN_LOC_ID , PAYEE_CLEAR_FLAG , CREATE_DATETIME , MODIFY_DATETIME , MODIFY_USER_ID , SEAL_FLAG from GL_TRANSACTIONS  /* where  */
}
OTDGLIRT_GLTXN_FIND_GL_TRANSACTIONS_ONE {
 	SELECT TXN_ID ,TXN_ENTRY_SEQ ,GL_ENTRY_SEQ ,ACCOUNT_PERIOD_ID ,ACCOUNT_CODE ,TXN_ENTRY_DATE ,TXN_TYPE ,TXN_POST_USAGE ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DESC ,TXN_REFERENCE_NUMBER ,BANK_STATEMENT_DATE ,RECON_CLEAR_FLAG ,TXN_REVERSED_FLAG ,REVERSED_TXN_ID ,PAYEE_PERSON_ID ,REVERSED_TXN_ENTRY_SEQ ,REVERSED_GL_ENTRY_SEQ ,PAYEE_CORPORATE_ID ,PAYEE_NAME_TEXT ,TXN_OBJECT_CODE ,LIST_SEQ ,TXN_OBJECT_ID ,CREATE_USER_ID ,CREATE_DATE ,INFO_NUMBER ,DEDUCTION_ID ,TXN_ENTRY_TIME ,RECEIPT_NUMBER ,REVERSAL_REASON_CODE ,TXN_LOC_ID ,PAYEE_CLEAR_FLAG ,CREATE_DATETIME ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM GL_TRANSACTIONS WHERE TXN_ID = :TXNID AND TXN_ENTRY_SEQ = :TXNENTRYSEQ ORDER BY TXN_ENTRY_SEQ,TXN_POST_USAGE DESC
}
OTDGLIRT_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}
OTDGLIRT_SYSPFL_INSERT_SYSTEM_PROFILES {
	INSERT INTO SYSTEM_PROFILES(PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG ) VALUES(:profileType ,:profileCode ,:description ,:profileValue ,:profileValue2 ,:modifyUserId ,:oldTableName ,CURRENT_TIMESTAMP ,:createUserId ,CURRENT_TIMESTAMP ,:sealFlag )
}

OTDGLIRT_SYSPFL_DELETE_SYSTEM_PROFILES { 
	DELETE FROM SYSTEM_PROFILES/* where */
}


OTDGLIRT_CGFKCHK_GL_TXN_GL_TXN_AC_COD {
	SELECT AC_CODE.ACCOUNT_NAME FROM   ACCOUNT_CODES AC_CODE WHERE  AC_CODE.ACCOUNT_CODE = :ACCOUNTCODE AND    AC_CODE.CASELOAD_TYPE = (SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID))
}

OTDGLIRT_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}

OTDGLIRT_RECEIPT_NUMBER {
    SELECT RECEIPT_NUMBER
              FROM OFFENDER_TRANSACTIONS
             WHERE TXN_ID = :TXNID
               AND TXN_ENTRY_SEQ = :TXNENTRYSEQ
}
OTDGLIRT_OFFENDER_ID_DISPLAY {
--    SELECT DISTINCT OFFENDER_ID_DISPLAY
--              FROM V_TRUST_HEADER_A VTHA
--             WHERE VTHA.ROOT_OFFENDER_ID = :OFFENDERID
  SELECT DISTINCT OFFENDER_ID_DISPLAY
              FROM offenders VTHA
             WHERE VTHA.ROOT_OFFENDER_ID = :OFFENDERID
}

OTDGLIRT_TXN_REVERSED_FLAG_DATA {
select
	coalesce (GL.TXN_REVERSED_FLAG,
	'N') TXN_REVERSED_FLAG
from
	GL_TRANSACTIONS GL
where
	GL.TXN_ID = :TXNID
	and GL.TXN_ENTRY_SEQ = :TXNENTRYSEQ
	      for
update
	nowait
}
OTDGLIRT_ADJUSTMENT_AMT {
	SELECT ADJUSTMENT_AMOUNT
	      FROM OFFENDER_DEDUCTIONS
	     WHERE OFFENDER_DEDUCTION_ID = :DEDUCTIONID
}
OTDGLIRT_OTUCOBWO_TRANSACTION_OPERATIONS {
	SELECT COUNT(*)
	      FROM transaction_operations
	     WHERE txn_type = :TXNTYPE
	       AND module_name = 'OTUCOBWO'
	       AND caseload_id = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID)
}
OTDGLIRT_TXN_USAGE {
	SELECT TXN_USAGE
	    FROM TRANSACTION_TYPES
	   WHERE TXN_TYPE = :TXNTYPE
}
OTDGLIRT_TRANSACTION_OPERATIONS_OODOSALE_OODORETU {
	SELECT COUNT ('1') FROM transaction_operations
	             WHERE module_name IN ('OODOSALE', 'OODORETU')   -- @@@ Abu 03-jun-2002 added OODORETU in check
	               AND txn_type = :TXNTYPE
	               AND caseload_id = :CASELOADID
}
OTDGLIRT_TRANSACTION_OPERATIONS_OTDSHIFT {
	SELECT COUNT ('1') FROM transaction_operations
	             WHERE module_name = 'OTDSHIFT'
	               AND txn_type = :TXNTYPE
	               AND caseload_id = :CASELOADID
}
OTDGLIRT_TXN_TYPE_OTDHIREM {
	SELECT txn_type
	                 FROM transaction_operations
	                WHERE module_name = 'OTDHIREM'
	                  AND txn_type = :TXNTYPE
              	    AND caseload_id = :CASELOADID
}
OTDGLIRT_OFFENDER_ID {
	SELECT OFFENDER_ID 
	  FROM   OFFENDER_TRANSACTIONS 
	  WHERE  TXN_ID = :TXNID and
		 TXN_ENTRY_SEQ = :TXNENTRYSEQ
}
OTDGLIRT_ACCOUNT_CLOSED_FLAG {
	SELECT ACCOUNT_CLOSED_FLAG 
	  FROM 	 OFFENDER_TRUST_ACCOUNTS 
	  WHERE  OFFENDER_ID = :OFFID AND
		 CASELOAD_ID = :CASELOADID
}
REOPEN_CLOSED_TRUST_ACCOUNT {
	UPDATE offender_trust_accounts SET account_closed_flag = 'N' WHERE offender_id = :offenderId  AND caseload_id = :caseloadId
}
OTDGLIRT_MAX_OFFENDER_BOOK_ID {
	SELECT MAX (offender_book_id)
                          FROM v_trust_header_a
                         WHERE root_offender_id = :OFFID
}
OTDGLIRT_TXN_ID {
	SELECT NEXTVAL('TXN_ID') FROM DUAL
}
OTDGLIRT_TXN_TYPE_OTDOPCTA {
	SELECT TXN_TYPE FROM TRANSACTION_OPERATIONS  WHERE MODULE_NAME = 'OTDOPCTA' AND CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID)
}
OTDGLIRT_DEDUCTION_ID {
	SELECT GL.DEDUCTION_ID
	     FROM GL_TRANSACTIONS GL
	    WHERE GL.TXN_ID = :TXNID
	      AND GL.TXN_ENTRY_SEQ = :TXNENTRYSEQ
	      AND GL.DEDUCTION_ID IS NOT NULL
}
OTDGLIRT_DEDUCTION_TYPE {
	SELECT TXN_ENTRY_AMOUNT, INFO_NUMBER, DEDUCTION_TYPE, OFFENDER_ID, DEDUCTION_FLAG
	        FROM OFFENDER_TRANSACTIONS
	       WHERE TXN_ID = :TXNID
	         AND TXN_ENTRY_SEQ = :TXNENTRYSEQ
}
OTDGLIRT_DEDUCTION_CATEGORY {
	SELECT DEDUCTION_CATEGORY
        FROM DEDUCTION_TYPES
       WHERE DEDUCTION_TYPE = :DEDTYPE
         AND CASELOAD_CODE IN ('BOTH', :CSLD_TYPE)
}
OTDGLIRT_DEDUCTION_AMOUNT {
	SELECT DEDUCTION_AMOUNT,
	                MAX_TOTAL_AMOUNT
	           FROM OFFENDER_DEDUCTIONS
	          WHERE OFFENDER_DEDUCTION_ID = :DEDID
}
OTDGLIRT_CHK_HOLD_TRANS {
	SELECT 1
	    FROM   TRANSACTION_OPERATIONS
	    WHERE  TXN_TYPE = :TXNTYPE
	    AND    MODULE_NAME IN('OTDHOLDT','OTDGIREM')
	    AND    CASELOAD_ID = :CASELOADID
}
OTDGLIRT_BANK_CHEQUE_DATA {
	SELECT 'Y' FROM BANK_CHEQUE_DATA WHERE TXN_ID = :TXNID
}
OTDGLIRT_PROFILE_VALUE {
	SELECT PROFILE_VALUE
                 FROM SYSTEM_PROFILES
                WHERE PROFILE_TYPE = 'CALC'
                  AND PROFILE_CODE = 'BEN_REVERSAL'
}
OTDGLIRT_BENEFICIARY_TRANSACTIONS {
	SELECT 'Y' FROM BENEFICIARY_TRANSACTIONS BT
                WHERE BT.TXN_ID = :TXNID
                  AND BT.TXN_ENTRY_SEQ = :TXNENTRYSEQ
                  AND BT.BENEFICIARY_CLEARED_FLAG = 'Y'
}
OTDGLIRT_BANK_CHEQUE_REGISTERS {
	SELECT 'Y' FROM bank_cheque_registers bcr,
                      bank_cheque_data bcd,
                      gl_transactions gl
                WHERE gl.txn_id = :TXNID
                  AND gl.gl_entry_seq = 1
                  AND bcr.txn_id = gl.txn_id
                  AND bcd.txn_id = bcr.txn_id
                  AND bcr.start_txn_id = bcr.end_txn_id
                  AND gl.txn_entry_amount = bcd.txn_entry_amount
}
OTDGLIRT_ACCOUNT_CLOSED_FLAG_TRUST {
	SELECT account_closed_flag
                 FROM offender_trust_accounts
                WHERE caseload_id = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID)
                  AND offender_id = :OFFID
}
OTDGLIRT_INFO_NUMBER_CREDIT_OBLIGATION_TYPE {
	SELECT OT.INFO_NUMBER, TT.CREDIT_OBLIGATION_TYPE TXN_TYPE
                 FROM OFFENDER_TRANSACTIONS OT, TRANSACTION_TYPES TT
                WHERE OT.TXN_ID = :TXNID
                  AND OT.TXN_ENTRY_SEQ = :TRANSENTRYSEQ
                  AND OT.TXN_TYPE = TT.TXN_TYPE
                  AND TT.CASELOAD_TYPE = (SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID =:USER_ID))
}
OTDGLIRT_TXN_TYPE_OFFENDER_ID {
	SELECT TXN_TYPE, OFFENDER_ID  FROM OFFENDER_TRANSACTIONS WHERE 
		  TXN_ID = :TXNID and 
		  TXN_ENTRY_SEQ = :TXNENTRYSEQ
}
OTDGLIRT_BALANCE_IND_DATE {
SELECT BALANCE, IND_DATE 
	FROM   OFFENDER_SUB_ACCOUNTS 
	WHERE  OFFENDER_ID = :OFFID
	AND    CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID =:USER_ID)
        AND    TRUST_ACCOUNT_CODE IN (SELECT ACCOUNT_CODE FROM ACCOUNT_CODES WHERE
                SUB_ACCOUNT_TYPE IN (SELECT SUB_ACCOUNT_TYPE FROM OFFENDER_TRANSACTIONS
			WHERE TXN_ID = :TXNID
			AND   TXN_ENTRY_SEQ = :TXNENTRYSEQ
			AND   OFFENDER_ID = :OFFID	
			AND   TXN_POSTING_TYPE = 'CR'))
}
OTDGLIRT_NEW_GL_TRANSACTIONS {
SELECT
    gl.account_code,
    ot.offender_id,
    gl.txn_entry_amount,
    gl.txn_post_usage,
    gl.txn_type,
    gl.gl_entry_seq,
    gl.payee_person_id,
    gl.payee_corporate_id,
    ot.info_number,
    ot.deduction_type,
    gl.deduction_id,
    ot.txn_type     off_txn_type,
    gl.deduction_id off_deduction_id
FROM
    gl_transactions       gl
    LEFT JOIN offender_transactions ot ON gl.txn_id = ot.txn_id
                                          AND gl.txn_entry_seq = ot.txn_entry_seq
WHERE
    gl.txn_id = :TXNID
    AND gl.txn_entry_seq = :TXNENTRYSEQ
    AND gl.caseload_id = :CSLDID
ORDER BY
    gl.gl_entry_seq
--FOR UPDATE OF txn_reversed_flag
}            
OTDGLIRT_TXN_POSTING_TYPE {
	SELECT txn_posting_type
	   FROM   account_codes
	   WHERE  account_code = :ACCOUNTCODE
	   AND    caseload_type = (SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID))
}
OTDGLIRT_INSERT_INTO_GL_TRANSACTIONS {
INSERT INTO GL_TRANSACTIONS
    ( TXN_ID,
      TXN_ENTRY_SEQ,
      ACCOUNT_PERIOD_ID,
      GL_ENTRY_SEQ,
      ACCOUNT_CODE,
      TXN_ENTRY_DATE,
      TXN_TYPE,
      TXN_POST_USAGE,
      CASELOAD_ID,
      OFFENDER_ID,
      OFFENDER_BOOK_ID,
      TXN_ENTRY_AMOUNT,
      TXN_ENTRY_DESC,
      RECON_CLEAR_FLAG,
      TXN_REVERSED_FLAG,
      CREATE_USER_ID,
      CREATE_DATE,
      REVERSED_TXN_ID,
      REVERSED_TXN_ENTRY_SEQ,
      REVERSED_GL_ENTRY_SEQ,
      REVERSAL_REASON_CODE,
      TXN_ENTRY_TIME,  	   
      DEDUCTION_ID,
      CREATE_DATETIME
    )
    SELECT :txnId,
           :txnEntrySeq,
           ACCOUNT_PERIOD_ID,
           :glSeq,
           :accountCode,
           :txnEntryDate,
           :txnType,
           :txnPostUsage,
           :caseloadId,
      	   :offenderId,
           :offenderBookId,
           :txnEntryAmount,
           :txnEntryDesc,
           'N',
           'Y',
           :userId,
            current_date,
           :reversedTxnId,
           :reversedTxnEntrySeq,
           :reversedGlEntrySeq,
           :reversalReasonCode,
           :txnEntryTime,  	   
           :deductionId,
           current_timestamp
    FROM   account_periods
    WHERE  start_date <= current_date 
    AND    end_date   >= current_date 
}

OTDGLIRT_ACCOUNT_CLOSED_FLAG_Y {
SELECT coalesce(ACCOUNT_CLOSED_FLAG,'Y') TXN_REVERSED_FLAG
     FROM OFFENDER_TRUST_ACCOUNTS
    WHERE OFFENDER_ID = :OFFID
      AND CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID)
}
OTDGLIRT_SUB_ACCOUNT_TYPE_OFF_TRANS {
	SELECT sub_account_type
	      FROM   account_codes
	      WHERE  account_code = :ACCOUNTCODE
	      AND    sub_account_type IS NOT NULL
	      AND    caseload_type = (SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID))
}
OTDGLIRT_UPDATE_OFFENDER_TRANSACTIONS_Y {
	UPDATE OFFENDER_TRANSACTIONS
   SET    TXN_ADJUSTED_FLAG = 'Y',
   MODIFY_USER_ID =:USER_ID,
   MODIFY_DATETIME = CURRENT_TIMESTAMP
   WHERE  TXN_ID        = :TXNID
   AND    TXN_ENTRY_SEQ = :TXNENTRYSEQ
   AND    CASELOAD_ID   = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID)
}
OTDGLIRT_INSERT_INTO_OFFENDER_TRANSACTIONS {
	INSERT INTO OFFENDER_TRANSACTIONS (
                    TXN_ID,
                    TXN_ENTRY_SEQ,
                    OFFENDER_ID,
                    OFFENDER_BOOK_ID,
                    TXN_ENTRY_DATE,
                    CASELOAD_ID,
                    MODIFY_USER_ID,
                    MODIFY_DATE,
                    TXN_ENTRY_AMOUNT,
                    TXN_ENTRY_DESC,
                    TXN_TYPE,
                    TXN_POSTING_TYPE,
                    SUB_ACCOUNT_TYPE,
                    INFO_NUMBER,
                    ADJUST_TXN_ID,
                    ADJUST_TXN_ENTRY_ID,
                    SLIP_PRINTED_FLAG,
                    TXN_ADJUSTED_FLAG,
		            DEDUCTION_TYPE,
		            CREATE_USER_ID,
		            CREATE_DATETIME)
           SELECT  :P_trans_number,
                   :P_trans_seq,
                   :P_off_id,
                   :P_off_book_id,
                   :P_trans_date,
                   :P_csld_id,
                   :createUserId,
                    current_date,
                   :trans_amount,
                   :trans_desc,
                   :P_trans_type,
                   :P_trans_post_type,
                   :sub_acnt_type,
                   :P_info_number,
                   :old_txn_id,
                   :old_txn_seq,
                   'N','N',
		   		   :P_ded_type,
		   		   :createUserId,
		   		   current_timestamp
           FROM    DUAL
}
OTDGLIRT_CASELOAD_ID {
	SELECT 	CASELOAD_ID 
	FROM	OFFENDER_DEDUCTIONS
	WHERE	OFFENDER_DEDUCTION_ID = :DEDID
}
OTDGLIRT_DEDUCTION_CATEGORY_TEMP {
	SELECT 	DEDUCTION_CATEGORY 
	FROM 	DEDUCTION_TYPES 
	WHERE	DEDUCTION_TYPE = :DEDTYPE
}
OTDGLIRT_PAYMENT_AMOUNT {
	SELECT coalesce (PAYMENT_AMOUNT, 0) PAYMENT_AMOUNT,
                               LOCATION,
                               COMMENT_TEXT,
                               --BENEFICIARY_ID,
                               TXN_ID,
                               --TXN_ENTRY_SEQ,
                               OFFENDER_ID,
                               CASELOAD_ID,
                               PAYMENT_DATE
                          FROM OFFENDER_CREDIT_PRIOR_PAYMENTS
                         WHERE TXN_ID = :TXNID
}
OTDGLIRT_UPDATE_OFFENDER_DEDUCTIONS {
	UPDATE OFFENDER_DEDUCTIONS SET MAX_TOTAL_AMOUNT = coalesce (MAX_TOTAL_AMOUNT, 0) + :TXAMNT, MODIFY_USER_ID = :USER_ID, MODIFY_DATE = current_date 
    WHERE CASELOAD_ID = :CASELOADID  AND OFFENDER_ID = :OFFENDERID AND DEDUCTION_TYPE = :DEDTYPE AND OFFENDER_DEDUCTION_ID = :DEDID
    AND coalesce (MAX_TOTAL_AMOUNT, 0) + :TXAMNT >= 0.0 AND DEDUCTION_AMOUNT <= coalesce (MAX_TOTAL_AMOUNT, 0) + :TXAMNT
}
OTDGLIRT_UPDATE_OFFENDER_BENEFICIARIES {
	UPDATE OFFENDER_BENEFICIARIES
  	  			SET    AMOUNT = coalesce(AMOUNT,0) + :TXAMNT
	  				WHERE  OFFENDER_DEDUCTION_ID = :DEDID
	  				AND    coalesce (AMOUNT,0) + :TXAMNT >= 0.0
}
OTDGLIRT_UPDATE_OFFENDER_DEDUCTIONS_TEMP {
	UPDATE OFFENDER_DEDUCTIONS
               SET DEDUCTION_AMOUNT = coalesce (DEDUCTION_AMOUNT, 0) + :TXAMNT,
                   MODIFY_USER_ID = :USER_ID,
                   MODIFY_DATE = current_timestamp 
             WHERE CASELOAD_ID = :CASELOADID
               AND OFFENDER_ID = :OFFENDERID
               AND DEDUCTION_TYPE = :DEDTYPE
               AND OFFENDER_DEDUCTION_ID = :DEDID
               AND coalesce (DEDUCTION_AMOUNT, 0) + :TXAMNT >= 0.0
               AND (  coalesce (DEDUCTION_AMOUNT, 0) + :TXAMNT <= MAX_TOTAL_AMOUNT
                   OR MAX_TOTAL_AMOUNT IS NULL)
}
INSERT_INTO_OFFENDER_CREDIT_PRIOR_PAYMENTS {
INSERT INTO OFFENDER_CREDIT_PRIOR_PAYMENTS (
                                          TXN_ID,
                                          CASELOAD_ID,
                                          OFFENDER_ID,
                                          PAYMENT_DATE,
                                          PAYMENT_AMOUNT,
                                          LOCATION,
                                          COMMENT_TEXT,
                                          CREATE_USER_ID,
                                          CREATE_DATETIME
                                       )
                                VALUES (
                                   :NEWTXNNUMBER,
                                   :CASELOADID,
                                   :OFFID,
                                   current_timestamp,
                                   -1 * :LVREVDEDAMOUNT,
                                   :LOCATION,
                                   'REV ' || :COMMENTTEXT,
                                   :createUserId,
                                    current_timestamp
                                )
}
OTDGLIRT_CORPORATE_ID_PERSON_ID {
SELECT OB.CORPORATE_ID PAYEE_CORPORATE_ID,
                      OB.PERSON_ID PAYEE_PERSON_ID
                 FROM OFFENDER_BENEFICIARIES OB
                WHERE OB.OFFENDER_DEDUCTION_ID = :OFFDEDID
}
OTDGLIRT_TXN_TYPE_OTUCOBWO {
SELECT TXN_TYPE
              FROM TRANSACTION_OPERATIONS
             WHERE MODULE_NAME = 'OTUCOBWO'
               AND TXN_TYPE = :TRANSTYPE
               AND CASELOAD_ID = :CSLDID
}
OTDGLIRT_MAX_TOTAL_AMOUNT_DEDUCTION_AMOUNT {
SELECT MAX_TOTAL_AMOUNT, DEDUCTION_AMOUNT, ADJUSTMENT_AMOUNT
        FROM OFFENDER_DEDUCTIONS
       WHERE OFFENDER_DEDUCTION_ID = :DEDID
}
OTDGLIRT_INSERT_INTO_OFFENDER_ADJUSTMENT_TXNS {
INSERT INTO OFFENDER_ADJUSTMENT_TXNS (
                  TXN_ID,
                  TXN_ENTRY_SEQ,
                  OFFENDER_ID,
                  OFFENDER_DEDUCTION_ID,
                  TXN_POSTING_TYPE,
                  ADJUSTMENT_DATE,
                  ADJUSTMENT_AMOUNT,
                  ADJUSTMENT_REASON_CODE,
                  ADJUSTMENT_TEXT,
                  ADJUSTMENT_USER_ID,
                  CREATE_USER_ID,
                  CREATE_DATETIME
               )
        VALUES (
           :PGLTXNID,
           1,
           :POFFENDERID,
           :POFFDEDID,
           'DR',
           current_timestamp,
           :PTXNENTRYAMOUNT,
           'REV W/O',
           'REV TXN# ' || TO_CHAR (:PREVTXNID),
           :CREATEUSERID,
           :CREATEUSERID,
            current_timestamp
        );
}     
OTDGLIRT_UPDATE_OFFENDER_DEDUCTIONS_ADJUSTMENT_AMOUNT {
  UPDATE OFFENDER_DEDUCTIONS
      SET ADJUSTMENT_AMOUNT = coalesce (TO_NUMBER (:LVADJUSTMENTAMOUNT), 0) - coalesce (:PTXNENTRYAMOUNT, 0),
          ADJUSTMENT_REASON_CODE = :LVADJUSTMENTREASON,          
          ADJUSTMENT_TXN_ID = NULL,
          ADJUSTMENT_TEXT = NULL,
          ADJUSTMENT_USER_ID = NULL,
          MODIFY_DATETIME = current_timestamp,
          MODIFY_USER_ID = :modifyUserId
    WHERE OFFENDER_DEDUCTION_ID = :POFFDEDID
}
OTDGLIRT_UPDATE_GL_TRANSACTIONS {
UPDATE GL_TRANSACTIONS
         SET    TXN_REVERSED_FLAG = :FLAG,
         MODIFY_DATETIME = current_timestamp,
         modify_user_id = :modifyUserId
         WHERE  TXN_ID        = :TXNNUMBER
         AND    TXN_ENTRY_SEQ = :TXNENTRYSEQ
         AND    CASELOAD_ID   = :CASELOADID
}
OTDGLIRT_TXN_TYPE_EXIST {
		SELECT DISTINCT 'Y'
                    FROM TRANSACTION_OPERATIONS
                   WHERE MODULE_NAME = 'OTDPAYRO'
                     AND TXN_TYPE = :TRANSTYPE
                     AND CASELOAD_ID = :CSLDID
}
OTDGLIRT_OFFENDER_WORKS {
	UPDATE OFFENDER_WORKS
                        SET REJECT_FLAG = 'Y',
                            REJECT_REASON_TEXT = 'PAYROLL REVERSED FROM GENERAL LEDGER.',
                            TXN_ID = NULL,   
                            PROCESSED_FLAG = 'N',
                            MODIFY_DATETIME = current_timestamp,
                            MODIFY_USER_ID = :modifyUserId
                      WHERE OFFENDER_ID = :OFFID
                        AND TXN_ID = :TXNNUMBER
}
TXN_ENTRY_DESC_TEMP_DATA {
SELECT SUBSTR (:TRANSDESC || ' - ' || :VREVERSALRSN, 1, 240) FROM DUAL
}
OTDGLIRT_GETTING_RECEIPT_NUMBER_TXN_ID {
		SELECT TXN_ID, TXN_ENTRY_SEQ FROM OFFENDER_TRANSACTIONS WHERE RECEIPT_NUMBER = :RECEIPTNUMBER
}

OTDGLIRT_UPDATE_OFFENDER_DEDUCTIONS_GET_OLD_DATA{
select * from OFFENDER_DEDUCTIONS where offender_deduction_id= :offenderDeductionId
}

OTDGLIRT_GET_OLD_DATA_OFFENDER_BENEFICIARIES{
select * from OFFENDER_BENEFICIARIES where  offender_deduction_id = :offenderDeductionId
}

OTDGLIRT_OFFENDER_TRANSACTIONS_OLD_DATA{
SELECT * FROM OFFENDER_TRANSACTIONS  where TXN_ID = :txnId AND TXN_ENTRY_SEQ = :txnEntrySeq
}

OTDGLIRT_GETTING_OFF_FEE_BILL_TRANSACTIONS{
 SELECT * FROM OFF_FEE_BILL_TRANSACTIONS  WHERE bill_id in(SELECT bill_id from OFF_FEE_BILL_TRANSACTIONS  WHERE   TRUST_TXN_ID = :txnId )
 and :txnEntrySeq = (SELECT max(TXN_ENTRY_SEQ) FROM OFFENDER_TRANSACTIONS WHERE TXN_ID = :txnId)
 order by BILL_TXN_NO  desc 
 fetch first 1 rows only
}

OTDGLIRT_GET_OFF_FEE_BILLS_OLD_DATA{
 select BILL_AR_DUE_DATE  billExpectedArDueDate , BILL_LDPP_START_DATE  billExpectedLdppStartDate , BILL_LDPP_END_DATE  billExpectedLdppEndDate from off_fee_bills where bill_id =:billId
}


OTDGLIRT_OFF_BILL_TRN_INSERT{
  INSERT INTO OFF_FEE_BILL_TRANSACTIONS (BILL_ID,BILL_TXN_NO,BILL_TXN_DATETIME,BILL_TXN_STAFF_ID,BILL_TXN_USER,
BILL_TXN_TYPE,BILL_TXN_AMOUNT,TRUST_TXN_ID,TRUST_TXN_ENTRY_SEQ,OFF_ADJ_CANC_RSN,OFF_ADJ_SUB_RSN,OFF_ADJ_TXN_ID,OFF_ADJ_REV_RSN,
BILL_STATUS,BILL_AGING_START_DATE,BILL_AGING_END_DATE,BILL_TXN_COMMENT,ORIGINAL_BILL_ID,ORIGINAL_BILL_TXN_NO,
ORIGINAL_OFF_ADJ_TXN_ID,TXN_STATEMENT_GENERATED_FLAG,BILLING_STATEMENT_ID,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,SEAL_FLAG)
values(:billId,:billTxnNo,:billTxnDatetime,:billTxnStaffId,:createUserId,
:billTxnType,:billTxnAmount,:trustTxnId,:trustTxnEntrySeq,:offAdjCancRsn,:offAdjSubRsn,:offAdjTxnId,:offAdjRevRsn,
:billStatus,date_trunc('day',:billAgingStartDate::timestamp),date_trunc('day',:billAgingEndDate::timestamp),:billTxnComment,:originalBillId,:originalBillTxnNo,
:originalOffAdjTxnId,:txnStatementGeneratedFlag,:billingStatementId,current_timestamp,:createUserId,current_timestamp,:sealFlag)
}

OTDGLIRT_GETTING_TXN_ID_BASED{
SELECT TXN_ID,CASELOAD_ID,OFFENDER_ID,OFFENDER_BOOK_ID,SLIP_PRINTED_FLAG,DEDUCTION_FLAG,TXN_ENTRY_AMOUNT  FROM OFFENDER_TRANSACTIONS WHERE TXN_ID= :txnId limit 1
}

OTDGLIRT_CR_ACCOUNT_CODE {
SELECT CR_ACCOUNT_CODE FROM TRANSACTION_OPERATIONS WHERE  MODULE_NAME = 'OTDGLIRT' AND TXN_TYPE = :txnType AND CASELOAD_ID = :caseloadId
}

OTDGLIRT_DR_ACCOUNT_CODE {
SELECT DR_ACCOUNT_CODE FROM TRANSACTION_OPERATIONS WHERE  MODULE_NAME = 'OTDGLIRT' AND TXN_TYPE = :txnType AND CASELOAD_ID = :caseloadId
}

OTDGLIRT_GET_GL_TXN_ENTRYSEQ{
SELECT  coalesce(MAX(GL_ENTRY_SEQ),0)+1 FROM GL_TRANSACTIONS where txn_id=:txnId  and OFFENDER_ID=:offenderId
}
