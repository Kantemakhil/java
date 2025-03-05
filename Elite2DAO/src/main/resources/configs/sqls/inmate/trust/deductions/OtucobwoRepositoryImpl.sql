
OTUCOBWO_FIND_CGFKCOBWOADJUSTMENTREASO{
 	SELECT TT.TXN_TYPE , TT.DESCRIPTION FROM TRANSACTION_OPERATIONS TOP , TRANSACTION_TYPES TT WHERE TOP.TXN_TYPE = TT.TXN_TYPE AND TOP.MODULE_NAME = 'OTUCOBWO' AND TOP.CASELOAD_ID = :caseloadId
}
OTUCOBWO_GET_TXN_ID {
SELECT TXN_ID.nextVal txn_id from dual
}
OTUCOBWO_INSERT_INTO_GL_TRANSACTIONS_DR {
INSERT INTO gl_transactions
               (txn_id,
                txn_entry_seq,
                account_period_id,
                gl_entry_seq,
                account_code,
                txn_entry_date,
                txn_type,
                txn_post_usage,
                caseload_id,
                offender_id,
                offender_book_id,
                txn_entry_amount,
                txn_entry_desc,
                recon_clear_flag,
                txn_reversed_flag,
                create_user_id,
                create_date,
                info_number,
                deduction_id,
                txn_entry_time 
               )
      SELECT :txnId,
             :txnEntrySeq,
             account_period_id,
             :glSeq,
             :accountCode,
            TRUNC (SYSDATE),
             :txnType,
             'DR',
             :caseloadId,
             :offenderId,
             :offenderBookId,
             :txnEntryAmount,
             :txnEntryDesc,
             'N',
             'N',
             USER,
             TRUNC (SYSDATE),
             NULL,
             :deductionId,
             SYSDATE 
        FROM account_periods
       WHERE start_date <= TRUNC (SYSDATE)
         AND end_date >= TRUNC (SYSDATE)
}
OTUCOBWO_INSERT_INTO_GL_TRANSACTIONS_CR {
INSERT INTO gl_transactions
               (txn_id,
                txn_entry_seq,
                account_period_id,
                gl_entry_seq,
                account_code,
                txn_entry_date,
                txn_type,
                txn_post_usage,
                caseload_id,
                offender_id,
                offender_book_id,
                txn_entry_amount,
                txn_entry_desc,
                recon_clear_flag,
                txn_reversed_flag,
                create_user_id,
                create_date,
                info_number,
                deduction_id,
                txn_entry_time 
               )
      SELECT :txnId,
             1,
             account_period_id,
             2,
             :accountCode,
            TRUNC (SYSDATE),
             :txnType,
             'CR',
             :caseloadId,
             :offenderId,
             :offenderBookId,
             :txnEntryAmount,
             :txnEntryDesc,
             'N',
             'N',
             USER,
             TRUNC (SYSDATE),
             NULL,
             :deductionId,
             SYSDATE 
        FROM account_periods
       WHERE start_date <= TRUNC (SYSDATE)
         AND end_date >= TRUNC (SYSDATE)
}

OTUCOBWO_INSERT_INTO_OFFENDER_ADJUSTMENT_TXNS {
INSERT INTO Offender_Adjustment_Txns
	(
	TXN_ID                 
	,TXN_ENTRY_SEQ          
	,OFFENDER_ID            
	,OFFENDER_DEDUCTION_ID  
	,TXN_POSTING_TYPE       
	,ADJUSTMENT_DATE        
	,ADJUSTMENT_AMOUNT      
	,ADJUSTMENT_REASON_CODE
	,ADJUSTMENT_TEXT        
	,ADJUSTMENT_USER_ID     
	)
   VALUES
	( :txnId
	,1
	,:offenderId
	,:offenderDeductionId
	,'CR'
	,TRUNC(SYSDATE)
	,:txnAmount
	,:reason
	,:commentText
	,USER)
}

OTUCOBWO_UPDATE_OFFENDER_DEDUCTIONS {
UPDATE Offender_Deductions   
      SET Adjustment_Amount = :writeOffamount,
          Adjustment_Reason_Code = :reason	
    WHERE Offender_Deduction_Id = :offenderDeductionId
    }
    OTUCOBWO_OFFENDER_DEDUCTION_C {
SELECT Deduction_Type,
	     Max_Total_Amount,
	     Deduction_Amount,
             Adjustment_Amount		
        FROM Offender_Deductions
       WHERE Offender_Deduction_Id = :deductionId
       }
OTUCOBWO_WRITEOFFGL {
       SELECT a.dr_account_code,
          b.txn_posting_type,
          c.account_code,
          'CR' CR,
          d.txn_type
     
     FROM transaction_operations a,
          account_codes b,
          caseload_deduction_profiles c,
          transaction_types d
    WHERE a.dr_account_code = b.account_code
      AND c.deduction_type = :deductionType
      AND a.txn_type = d.txn_type
      AND module_name = 'OTUCOBWO'
      AND c.caseload_id = :caseloadId
      AND a.caseload_id = :caseloadId
      AND b.caseload_type = :caseloadType
      AND a.txn_type = :txnType
      }