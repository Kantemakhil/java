GL_TRANSACTIONS_T2_AC_COUNT{
select
	COUNT (0)
from
	caseload_account_summaries
where
	caseload_id = :caseloadId
	and account_code = :accountCode
	and account_period_id = (
	select
		account_period_id
	from
		account_periods
	where
		start_date <= TO_DATE(:txnEntryDate::text,'DD-MM-YYYY')
			and end_date >= TO_DATE(:txnEntryDate::text, 'DD-MM-YYYY'))
}

GL_TRANSACTIONS_T2_P_ACNT_POSTING{
SELECT txn_posting_type FROM account_codes WHERE account_code = :accountCode
}

GL_TRANSACTIONS_T2_AC_PD_ID{
SELECT account_period_id  FROM account_periods  WHERE TO_DATE(:txnEntryDate::text,'DD-MM-YYYY') BETWEEN start_date AND end_date
}

GL_TRANSACTIONS_T2_ACNT_ID{
select nextval( 'caseload_current_account_id' )FROM dual
}

GL_TRANSACTIONS_T2_CASELOAD_CURRENT_ACCOUNTS_TXNS{
INSERT INTO caseload_current_accounts_txns(caseload_current_account_id, caseload_id, account_code, account_period_id, current_balance, modify_date, create_user_id, CREATE_DATETIME ,MODIFY_DATETIME, txn_id, txn_entry_seq, gl_entry_seq)
VALUES (:caseloadCurrentAccountId, :caseloadId, :accountCode, :accountPeriodId, :currentBalance, current_date, :createUserId, CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP, :txnId, :txnEntrySeq, :glEntrySeq)
}