
OTDBACRE_FIND_CGFKGLTXNACCOUNTCODE_FN {
 	SELECT TO_CHAR(AC_CODE.ACCOUNT_CODE)  CODE  /* CG$FK */        ,AC_CODE.ACCOUNT_NAME  DESCRIPTION FROM   ACCOUNT_CODES AC_CODE WHERE   AC_CODE.ACCOUNT_CODE IN (   SELECT CCA.ACCOUNT_CODE   FROM CASELOAD_CURRENT_ACCOUNTS_FN(:userId) CCA   WHERE CCA.CASELOAD_ID = :CASELOADID   AND CCA.BANK_ACCOUNT_TYPE IN ('CHK' ,'RS' ,'SAV' ,'OTH' ) ) AND AC_CODE.CASELOAD_TYPE = :CASELOADTYPE ORDER BY  AC_CODE.ACCOUNT_CODE ASC
}

OTDBACRE_GLTXN_FIND_GL_TRANSACTIONS {
 	SELECT TXN_ID ,TXN_ENTRY_SEQ ,GL_ENTRY_SEQ ,ACCOUNT_PERIOD_ID ,ACCOUNT_CODE ,TXN_ENTRY_DATE ,TXN_TYPE ,TXN_POST_USAGE ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DESC ,TXN_REFERENCE_NUMBER ,BANK_STATEMENT_DATE ,RECON_CLEAR_FLAG ,TXN_REVERSED_FLAG ,REVERSED_TXN_ID ,PAYEE_PERSON_ID ,REVERSED_TXN_ENTRY_SEQ ,REVERSED_GL_ENTRY_SEQ ,PAYEE_CORPORATE_ID ,PAYEE_NAME_TEXT ,TXN_OBJECT_CODE ,LIST_SEQ ,TXN_OBJECT_ID ,CREATE_USER_ID ,CREATE_DATE ,INFO_NUMBER ,DEDUCTION_ID ,TXN_ENTRY_TIME ,RECEIPT_NUMBER ,REVERSAL_REASON_CODE ,TXN_LOC_ID ,PAYEE_CLEAR_FLAG ,CREATE_DATETIME ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM GL_TRANSACTIONS  /* where  */
}
OTDBACRE_GLTXN_INSERT_GL_TRANSACTIONS {
	INSERT INTO GL_TRANSACTIONS(TXN_ID ,TXN_ENTRY_SEQ ,GL_ENTRY_SEQ ,ACCOUNT_PERIOD_ID ,ACCOUNT_CODE ,TXN_ENTRY_DATE ,TXN_TYPE ,TXN_POST_USAGE ,CASELOAD_ID ,OFFENDER_ID ,OFFENDER_BOOK_ID ,TXN_ENTRY_AMOUNT ,TXN_ENTRY_DESC ,TXN_REFERENCE_NUMBER ,BANK_STATEMENT_DATE ,RECON_CLEAR_FLAG ,TXN_REVERSED_FLAG ,REVERSED_TXN_ID ,PAYEE_PERSON_ID ,REVERSED_TXN_ENTRY_SEQ ,REVERSED_GL_ENTRY_SEQ ,PAYEE_CORPORATE_ID ,PAYEE_NAME_TEXT ,TXN_OBJECT_CODE ,LIST_SEQ ,TXN_OBJECT_ID ,CREATE_USER_ID ,CREATE_DATE ,INFO_NUMBER ,DEDUCTION_ID ,TXN_ENTRY_TIME ,RECEIPT_NUMBER ,REVERSAL_REASON_CODE ,TXN_LOC_ID ,PAYEE_CLEAR_FLAG ,CREATE_DATETIME ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG ) VALUES(:txnId ,:txnEntrySeq ,:glEntrySeq ,:accountPeriodId ,:accountCode ,:txnEntryDate ,:txnType ,:txnPostUsage ,:caseloadId ,:offenderId ,:offenderBookId ,:txnEntryAmount ,:txnEntryDesc ,:txnReferenceNumber ,:bankStatementDate ,:reconClearFlag ,:txnReversedFlag ,:reversedTxnId ,:payeePersonId ,:reversedTxnEntrySeq ,:reversedGlEntrySeq ,:payeeCorporateId ,:payeeNameText ,:txnObjectCode ,:listSeq ,:txnObjectId ,:createUserId ,:createDate ,:infoNumber ,:deductionId ,:txnEntryTime ,:receiptNumber ,:reversalReasonCode ,:txnLocId ,:payeeClearFlag ,current_timestamp ,current_timestamp ,:modifyUserId ,:sealFlag )
}

OTDBACRE_GLTXN_UPDATE_GL_TRANSACTIONS {
	UPDATE GL_TRANSACTIONS 
        SET    RECON_CLEAR_FLAG = :reconClearFlag,
               BANK_STATEMENT_DATE = :bankStatementDate
        WHERE  TXN_ID = :txnId
}

OTDBACRE_BANKRC_FIND_BANK_RECON_AUDITS {
 	SELECT CASELOAD_ID ,ACCOUNT_CODE ,BANK_STATEMENT_DATE ,BANK_STATEMENT_STATUS ,BANK_BALANCE ,ADJUSTMENT_GL_NUMBER ,ADJUSTMENT_COMMENT ,CREATE_DATE ,LIST_SEQ ,AMNT_LESS_ADJ ,AMNT_PLUS_ADJ ,AMNT_LESS_COMMENT ,AMNT_PLUS_COMMENT ,SEAL_FLAG ,RECON_ID ,GL_OPEN_BAL ,BANK_CLEARED_AMOUNT ,BANK_CLEARED_COUNT ,BOOK_CLEARED_AMOUNT ,BOOK_CLEARED_COUNT ,BANK_UNPOSTED_AMOUNT ,BANK_UNPOSTED_COUNT ,BOOK_OUTSTANDING_AMOUNT ,BOOK_OUTSTANDING_COUNT ,ADJ_BOOK_BAL ,CLEARING_DISCREPANCY ,CLOSING_BOOK_BALANCE ,CLOSING_BANK_BALANCE ,BALANCE_DISCREPANCY ,RECON_USER_ID ,RECON_DATETIME ,STATUS ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID   FROM BANK_RECON_AUDITS  /* where  */
}
OTDBACRE_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}
OTDBACRE_SYSPFL_INSERT_SYSTEM_PROFILES {
	INSERT INTO SYSTEM_PROFILES(PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG ) VALUES(:profileType ,:profileCode ,:description ,:profileValue ,:profileValue2 ,:modifyUserId ,:oldTableName ,current_timestamp ,:createUserId ,current_timestamp ,:sealFlag )
}

OTDBACRE_SYSPFL_DELETE_SYSTEM_PROFILES { 
	DELETE FROM SYSTEM_PROFILES/* where */
}

OTDBACRE_BCRTMP_FIND_BANK_CLEAR_RECONCILES_TMP {
 	select
	TXN_ID ,
	TXN_ENTRY_SEQ ,
	GL_ENTRY_SEQ ,
	TXN_ENTRY_DATE ,
	TXN_POST_USAGE ,
	REFERENCE_NO_TYPE ,
	REFERENCE_NO ,
	DESCRIPTION ,
	TXN_ENTRY_AMOUNT ,
	CASELOAD_ID ,
	CREATE_DATETIME ,
	CREATE_USER_ID ,
	MODIFY_DATETIME ,
	MODIFY_USER_ID ,
	SEAL_FLAG,
	ROW_ID
from
	BANK_CLEAR_RECONCILES_TMP
where
	CASELOAD_ID =:CASELOAD_ID
order by
	REFERENCE_NO_TYPE,
	TXN_POST_USAGE,
	REFERENCE_NO
 	
}
OTDBACRE_BCRTMP_UPDATE_BANK_CLEAR_RECONCILES_TMP {
	UPDATE BANK_CLEAR_RECONCILES_TMP set TXN_ID  = :txnId ,TXN_ENTRY_SEQ  = :txnEntrySeq ,GL_ENTRY_SEQ  = :glEntrySeq ,TXN_ENTRY_DATE  = :txnEntryDate ,TXN_POST_USAGE  = :txnPostUsage ,REFERENCE_NO_TYPE  = :referenceNoType ,REFERENCE_NO  = :referenceNo ,DESCRIPTION  = :description ,TXN_ENTRY_AMOUNT  = :txnEntryAmount ,CASELOAD_ID  = :caseloadId ,CREATE_DATETIME  = current_timestamp ,CREATE_USER_ID  = :createUserId ,MODIFY_DATETIME  = current_timestamp ,MODIFY_USER_ID  = :modifyUserId ,SEAL_FLAG  = :sealFlag /* where */
}


OTDBACRE_CREATE_FORM_GLOBALSCREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO   V_FORM_DESC FROM   OMS_MODULES WHERE  MODULE_NAME = V_FORM_NAME
}

OTDBACRE_CGFKCHK_GL_TXN_GL_TXN_AC_CODE_ {
	SELECT AC_CODE.ACCOUNT_NAME FROM   ACCOUNT_CODES AC_CODE WHERE  AC_CODE.ACCOUNT_CODE = :ACCOUNTCODE AND     AC_CODE.ACCOUNT_CODE IN ( SELECT CCA.ACCOUNT_CODE FROM CASELOAD_CURRENT_ACCOUNTS_FN(:userId) CCA WHERE CCA.CASELOAD_ID = :CASELOADID AND CCA.BANK_ACCOUNT_TYPE IN ('CHK','RS','SAV','OTH')) AND AC_CODE.CASELOAD_TYPE = :CASELOADTYPE
}

OTDBACRE_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}
OTDBACRE_GET_P_MAX_DATE {
SELECT MAX(BANK_STATEMENT_DATE) BANK_STATEMENT_DATE FROM   BANK_RECON_AUDITS WHERE  CASELOAD_ID = :CASELOAD_ID AND    ACCOUNT_CODE = :ACCOUNT_CODE
   	}
 OTDBACRE_POSTQUERY {
 select
	recon_clear_flag,
	bank_statement_date,
	case
		when bcr.cheque_status = 'VOID' then gl.txn_entry_date
		else coalesce(bcr.transaction_date, gl.txn_entry_date)
	end txn_entry_date,
	case
		when coalesce(bcr.cheque_number::text, '') = '' then 'TRN'
		else 'CHK'
	end reference_no_type,
	coalesce(bcr.cheque_number, gl.txn_id) reference_no
from
	gl_transactions gl
left outer join bank_cheque_registers bcr on
	(gl.txn_id = bcr.txn_id)
where
	gl.txn_id = :txnId
	and gl.txn_entry_seq = :txnEntrySeq
	and gl.gl_entry_seq = :glEntrySeq
	and (bcr.caseload_id = :caseloadId
		or coalesce(bcr.caseload_id::text, '') = '')
 }
 OTDBACRE_COMPAREEFFECTIVEDATEC { 
 select
	oms_date_time_compare_date_time (TO_DATE(:effectiveDate::text , 'DD/MM/YYYY')::text,
	null::text,
	TO_DATE(:maxDate::text , 'DD/MM/YYYY')::text,
	null::text) TODATE
from
	dual
 }
 
 OTDBACRE_GET_BALANCES_CREDITS_DEBITS {
 select
	SUM(case when gl.txn_post_usage = 'CR' then GL.TXN_ENTRY_AMOUNT else 0 end ) LESSOUT_CREDITS,
	SUM(case when gl.txn_post_usage = 'DR' then GL.TXN_ENTRY_AMOUNT else 0 end ) PLUSS_OUT_DEBITS
from
	GL_TRANSACTIONS GL
where
	GL.ACCOUNT_CODE = :accountCode
	and GL.CASELOAD_ID = :caseloadId
	and gl.recon_clear_flag != 'Y'
      }
   OTDBACRE_GET_BALANCES_CURRENT_BALANCE {        
       SELECT SUM(CURRENT_BALANCE) BAL   FROM   CASELOAD_CURRENT_ACCOUNTS_TXNS    WHERE  ACCOUNT_CODE = :accountCode  AND CASELOAD_ID LIKE :caseloadId
      }
      
   OTDBACRE_GETCHCQUEFLAG {
   select 'Y' VAL from bank_cheque_registers bcr, gl_transactions gl
where gl.txn_id = :txnId
  and gl.txn_entry_seq = :txnEnterySeq
  and gl.gl_entry_seq = :glEntrySeq
  and gl.reversed_txn_id is not null 
  and gl.reversed_txn_id = bcr.txn_id
  and bcr.cheque_status = 'VOID'      
      }
      
   OTDBACRE_UPDATEGLTRANSACTIONSWITHY {
           UPDATE GL_TRANSACTIONS gl
        SET    gl.RECON_CLEAR_FLAG = 'Y',
               gl.BANK_STATEMENT_DATE = :cgnbtBankStatementDate
        WHERE  gl.TXN_ID = :txnId
   }
   
   OTDBACRE_UPDATEGLTRANSACTIONSWITHN {
   UPDATE GL_TRANSACTIONS gl
        SET    gl.RECON_CLEAR_FLAG = 'N',
               gl.BANK_STATEMENT_DATE = NULL
        WHERE  gl.TXN_ID = :txnId
   }
   OTDBACRE_UPDATEBANKRECONAUDITS {
   
   UPDATE bank_recon_audits
         SET bank_balance = :amountBankBal,
             amnt_less_adj = :amntAdjustMinus,
             amnt_plus_adj = :amntAdjustPlus,
             amnt_plus_comment = :plusComment,
             amnt_less_comment = :minusComment,
             create_user_id = :createUserId,
             create_date = current_timestamp
       WHERE caseload_id = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :createUserId)
         AND account_code = :accountCode
         AND bank_statement_date = :bankStatemntDate   
   }
   OTDBACRE_INSERTBANKRECONAUDITS {
   INSERT INTO bank_recon_audits (
                        caseload_id,
                        account_code,
                        bank_statement_date,
                        bank_balance,
                        create_user_id,
                        amnt_less_adj,
                        amnt_plus_adj,
                        amnt_less_comment,
                        amnt_plus_comment
                     )
              VALUES (
                 :caseloadId,
                 :accountCode,
                 :bankStatemntDate,
                 :amountBankBal,
                 :createUserId,
                 :amntAdjustMinus,
                 :amntAdjustPlus,
                 :minusComment,
                 :plusComment)
              }
 OTDBACRE_GET_OLD_RECORDS_SYSTEMPROFILES {
select * from system_profiles where profile_type=:profile_type and profile_code=:profile_code
}