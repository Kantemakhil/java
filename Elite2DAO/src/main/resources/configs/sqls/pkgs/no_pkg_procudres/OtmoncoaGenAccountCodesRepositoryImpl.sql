L_CASELOADS_CUR {
SELECT caseload_id
        FROM caseloads
       WHERE caseload_type = (SELECT caseload_type
                                FROM caseloads
                               WHERE caseload_id = :p_caseload_id)
         AND trust_accounts_flag = 'Y'
}

L_CURRENT_PERIOD_CUR {
SELECT account_period_id
        FROM account_periods
       WHERE date_trunc('day', CURRENT_TIMESTAMP) BETWEEN start_date AND end_date
}

L_LAST_CLOSED_CUR {
SELECT MAX (account_period_id)
        FROM caseload_account_periods
       WHERE caseload_id = :p_caseload_id
         AND account_period_status = 'C'
}

L_CSLD_CURR_ACCT_BASE_CUR {

SELECT DISTINCT c.caseload_id caseload_id,
                      COALESCE (:p_account_code, ac.account_code) account_code
        FROM caseloads c, account_codes ac, account_periods ap
       WHERE c.caseload_id = :p_caseload_id
         AND ac.caseload_type = c.caseload_type  
         AND (  ac.account_code = :p_account_code
             OR :p_account_code IS NULL)
         AND ap.account_period_type = 'MTH'
         AND (  ap.account_period_id > :p_account_period_id
             OR :p_account_period_id IS NULL)
         AND NOT EXISTS (SELECT 1
                           FROM caseload_current_accounts_base ccabx
                          WHERE ccabx.caseload_id = c.caseload_id
                            AND ccabx.account_code =
                                   COALESCE (:p_account_code, ac.account_code))
}

L_CSLD_CURR_ACCT_TXNS_CUR {
SELECT nextval('caseload_current_account_id') caseload_current_account_id,
             c.caseload_id caseload_id,
             COALESCE (:p_account_code, ac.account_code) account_code,
             ap.account_period_id account_period_id,
             0 current_balance,
             CURRENT_USER modify_user_id,
             date_trunc ('day', CURRENT_TIMESTAMP) modify_date
        FROM caseloads c, account_codes ac, account_periods ap
       WHERE c.caseload_id = :p_caseload_id
         AND ac.caseload_type = c.caseload_type  
         AND (  ac.account_code = :p_account_code
             OR :p_account_code IS NULL)
         AND ap.account_period_type = 'MTH'
         AND (  ap.account_period_id > :p_last_period_id
             OR :p_last_period_id IS NULL)
         AND ap.account_period_id <= :p_current_period_id
         AND NOT EXISTS (SELECT 1
                           FROM caseload_current_accounts_txns ccatx
                          WHERE ccatx.caseload_id = c.caseload_id
                            AND ccatx.account_period_id = ap.account_period_id
                            AND ccatx.account_code = ac.account_code)
}

L_CSLD_ACCT_PERIODS_CUR {
SELECT DISTINCT c.caseload_id caseload_id,
                      ap.account_period_id account_period_id,
                      'O' account_period_status,
                      CURRENT_USER closing_user_id,
                      date_TRUNC ('day', CURRENT_TIMESTAMP) closing_date
        FROM caseloads c, account_codes ac, account_periods ap
       WHERE c.caseload_id = :p_caseload_id
         AND ac.caseload_type = c.caseload_type
         AND ap.account_period_type = 'MTH'
         AND (  ap.account_period_id > :p_account_period_id
             OR :p_account_period_id IS NULL)
         AND NOT EXISTS (SELECT 1
                           FROM caseload_account_periods cap
                          WHERE cap.caseload_id = c.caseload_id
                            AND cap.account_period_id = ap.account_period_id)
}

L_CSLD_ACCT_SUMM_CUR {
SELECT DISTINCT c.caseload_id caseload_id,
                      ac.account_code account_code,
                      ap.account_period_id account_period_id,
                      0 open_balance,
                      0 close_balance,
                      0 period_txn_amount,
                      CURRENT_USER modify_user_id,
                      date_TRUNC ('day', CURRENT_TIMESTAMP) modify_date
        FROM caseloads c, account_codes ac, account_periods ap
       WHERE c.caseload_id = :p_caseload_id
         AND ac.caseload_type = c.caseload_type  
         AND (  ac.account_code = :p_account_code
             OR :p_account_code IS NULL)
         AND ap.account_period_type = 'MTH'
         AND (  ap.account_period_id > :p_account_period_id
             OR :p_account_period_id IS NULL)
         AND ac.all_caseload_flag = 'Y'
         AND ac.posting_status_flag = 'Y'
         AND NOT EXISTS (SELECT 1
                           FROM caseload_account_summaries casx
                          WHERE casx.caseload_id = c.caseload_id
                            AND casx.account_period_id = ap.account_period_id
                            AND casx.account_code = ac.account_code)}

INSERT_CASELOAD_CURRENT_ACCOUNTS_BASE {
/*INSERT INTO caseload_current_accounts_base (
                           caseload_id,
                           account_code,
                           account_period_id,
                           current_balance,
                           modify_user_id,
                           modify_date
                        )
                 VALUES (
                    :caseload_id,
                    :account_code,
                    :account_period_id,
                    0,
                    USER,
                    TRUNC(SYSDATE)
                 )*/
 insert into caseload_current_accounts_base ( caseload_id, account_code, account_period_id, current_balance, CREATE_USER_ID, modify_date, CREATE_DATETIME, MODIFY_DATETIME ) values ( :caseload_id, :account_code, :account_period_id, 0, :createUserId, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )                
}

INSERT_CASELOAD_CURRENT_ACCOUNTS_TXNS {
/*INSERT INTO caseload_current_accounts_txns (
                           caseload_current_account_id,
                           caseload_id,
                           account_code,
                           account_period_id,
                           current_balance,
                           modify_user_id,
                           modify_date
                        )
                 VALUES (
                    :caseload_current_account_id,
                    :caseload_id,
                    :account_code,
                    :account_period_id,
                    :current_balance,
                    :modify_user_id,
                    :modify_date
                 )*/
 insert into caseload_current_accounts_txns ( caseload_current_account_id, caseload_id, account_code, account_period_id, current_balance, CREATE_USER_ID, modify_date, CREATE_DATETIME, MODIFY_DATETIME ) values ( :caseloadCurrentAccountId, :caseloadId, :accountCode, :accountPeriodId, :currentBalance, :createUserId, :modifyDate, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}

INSERT_CASELOAD_ACCOUNT_PERIODS{
INSERT INTO caseload_account_periods ( caseload_id, account_period_id, account_period_status, closing_user_id, closing_date, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) VALUES ( :caseloadId, :accountPeriodId, :accountPeriodStatus, :closingUserId, :closingDate, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}
INSERT_CASELOAD_ACCOUNT_SUMMARIES{
INSERT INTO caseload_account_summaries ( caseload_id, account_code, account_period_id, open_balance, close_balance, period_txn_amount, CREATE_USER_ID, modify_date, CREATE_DATETIME, MODIFY_DATETIME) VALUES ( :caseloadId, :accountCode, :accountPeriodId, :openBalance, :closeBalance, :periodTxnAmount, :createUserId, :modifyDate, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
}