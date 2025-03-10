 ACCOUNT_PERIOD_CUR {
 SELECT ACCOUNT_PERIOD_ID FROM ACCOUNT_PERIODS  WHERE :P_DATE BETWEEN START_DATE AND END_DATE
}

TRUSTGT_GET_CURRENT_BALANCE_FN{
SELECT coalesce(CURRENT_BALANCE, 0)
        FROM CASELOAD_CURRENT_ACCOUNTS_FN(:userId)
       WHERE ACCOUNT_CODE = :P_ACCOUNT_CODE
         AND CASELOAD_ID = :P_CASELOAD_ID
}
TRUSTGT_SELECT{
SELECT MAX(ACCOUNT_PERIOD_ID) FROM CASELOAD_ACCOUNT_PERIODS WHERE CASELOAD_ID = :P_CASELOAD_ID AND ACCOUNT_PERIOD_STATUS IN ('C', 'R') AND (ACCOUNT_PERIOD_ID < :P_ACCCOUNT_PERIOD_ID OR coalesce(:P_ACCCOUNT_PERIOD_ID::bigint,0)=0 )
          }
       
             
 TRUSTGT_SELECT_MAX{            
               SELECT MAX(ACCOUNT_PERIOD_ID) + 1
        FROM CASELOAD_ACCOUNT_PERIODS
       WHERE CASELOAD_ID = :P_CASELOAD_ID
         AND ACCOUNT_PERIOD_STATUS = 'C'
         }
         
 LOCK_RECORD_CUR {
 select ACCOUNT_PERIOD_STATUS from CASELOAD_ACCOUNT_PERIODS where CASELOAD_ID = :P_CASELOAD_ID and ACCOUNT_PERIOD_STATUS = 'C' and ACCOUNT_PERIOD_ID >= TRUST_GJ_GET_ACCOUNT_PERIOD(:P_TXN_DATE);
 }
   
         
TRUST_GJ_CASELOAD{  
update CASELOAD_ACCOUNT_PERIODS set ACCOUNT_PERIOD_STATUS = 'R', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where CASELOAD_ID = :P_CASELOAD_ID and ACCOUNT_PERIOD_STATUS = 'C' and ACCOUNT_PERIOD_ID >= TRUST_GJ_GET_ACCOUNT_PERIOD(:P_TXN_DATE) and ACCOUNT_PERIOD_ID <> ( select MIN(ACCOUNT_PERIOD_ID) from ACCOUNT_PERIODS where START_DATE is not null and END_DATE is not null) 
    }
              