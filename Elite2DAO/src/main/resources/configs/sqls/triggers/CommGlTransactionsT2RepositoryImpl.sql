GET_COMM_GL_TRANSACTIONS{
SELECT TXN_ID,
TXN_ENTRY_SEQ,
GL_ENTRY_SEQ,
ACCOUNT_PERIOD_ID,
ACCOUNT_CODE,
TXN_ENTRY_DATE,
TXN_TYPE,
TXN_POST_USAGE,
CASELOAD_ID,
OFFENDER_ID,
TXN_ENTRY_AMOUNT,
TXN_ENTRY_DESC,
TXN_REFERENCE_NUMBER,
BANK_STATEMENT_DATE,
RECON_CLEAR_FLAG,
TXN_REVERSED_FLAG,
REVERSED_TXN_ID,
REVERSED_TXN_ENTRY_SEQ,
REVERSED_GL_ENTRY_SEQ,
PAYEE_NAME_TEXT,
PAYEE_CORPORATE_ID,
PAYEE_PERSON_ID,
STOCK_ITEM_ID,
SALES_ORDER_ID,
SHIPMENT_PAYMENT_ID,
MODIFY_USER_ID,
MODIFY_DATETIME,
RETURN_ID,
TXN_LOC_ID,
OFF_TXN_ID,
CREATE_DATETIME,
CREATE_USER_ID,
SEAL_FLAG,
  FROM COMM_GL_TRANSACTIONS WHERE  TXN_ID = :TXN_ID
}

COMM_CASELOAD_AC_SUMMARIES_COUNT{
SELECT COUNT (0)
        FROM comm_caseload_ac_summaries
       WHERE caseload_id = :caseload_id
         AND account_code = :account_code
         AND account_period_id = (SELECT account_period_id
                                    FROM account_periods
                                   WHERE start_date <= TRUNC (:txn_entry_date)
                                     AND end_date >= TRUNC (:txn_entry_date)
}
GET_TXN_POSTING_TYPE{
SELECT txn_posting_type
        FROM comm_account_codes
       WHERE account_code = :account_code
}

GET_ACCOUNT_PERIOD_ID{
SELECT account_period_id
        FROM account_periods
       WHERE TRUNC (:txn_entry_date) BETWEEN start_date AND end_date

}
GET_COM_CSLD_CURRENT_ACCOUNT_ID{
SELECT COM_CSLD_CURRENT_ACCOUNT_ID.nextval
           FROM dual
}
INSERT_COM_CSLD_CURRENT_ACCOUNTS_TXNS{
INSERT INTO COM_CSLD_CURRENT_ACCOUNTS_TXNS
                  (COM_CSLD_CURRENT_ACCOUNT_ID, caseload_id, account_code, account_period_id, current_balance, modify_user_id, modify_date)
           VALUES (:acnt_id, :caseload_id, :account_code, :ac_pd_id, :pstng_amount, :USERNAME, TRUNC (SYSDATE))

}
	