
OTMCNSER_CSLDCA_FIND_CASELOAD_CURRENT_ACCOUNTS {
 	SELECT * FROM CASELOAD_CURRENT_ACCOUNTS 
}
OTMCNSER_BANKCB_FIND_BANK_CHEQUE_BOOKS {
 	 SELECT  * FROM BANK_CHEQUE_BOOKS  WHERE 
}
OTMCNSER_BANKCB_INSERT_BANK_CHEQUE_BOOKS {
	 insert into BANK_CHEQUE_BOOKS(CASELOAD_ID, ACCOUNT_CODE, CHEQUE_BOOK_SEQ, ACCOUNT_NUMBER, FIRST_CHECK_NUMBER, LAST_CHECK_NUMBER, NEXT_CHECK_NUMBER, TXN_ENTRY_DATE, MODIFY_DATE, LIST_SEQ, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG ) values(:caseloadId, :accountCode, :chequeBookSeq, :accountNumber, :firstCheckNumber, :lastCheckNumber, :nextCheckNumber, :txnEntryDate, :modifyDate, :listSeq, current_timestamp, :createUserId, CURRENT_TIMESTAMP , :sealFlag) 
}

OTMCNSER_BANKCB_DELETE_BANK_CHEQUE_BOOKS { 
	DELETE FROM BANK_CHEQUE_BOOKS  where CASELOAD_ID=:caseloadId and ACCOUNT_CODE =:accountCode and CHEQUE_BOOK_SEQ	=:chequeBookSeq	
}


OTMCNSER_CGFKCHK_CSLD_CA_CSLD_AC_AC_CO {
	SELECT AC_CODE.ACCOUNT_NAME FROM   ACCOUNT_CODES AC_CODE WHERE  AC_CODE.ACCOUNT_CODE = :ACCOUNTCODE AND     ACCOUNT_CODE IN (SELECT ACCOUNT_CODE FROM CASELOAD_CURRENT_ACCOUNTS WHERE BANK_ACCOUNT_TYPE = 'CHK')
}

OTMCNSER_CGWHEN_NEW_FORM_INSTANCE_ {
--	SELECT  SYSDATE, USER FROM    SYS.DUAL
             select current_timestamp ,USER
}
OTMCNSER_GETACCOUNT_NAME {
SELECT AC_CODE.ACCOUNT_NAME
      FROM   ACCOUNT_CODES AC_CODE
      WHERE  AC_CODE.ACCOUNT_CODE = :accountCode
      AND     account_code IN (SELECT account_code 
                                         FROM CASELOAD_CURRENT_ACCOUNTS
                                      WHERE BANK_ACCOUNT_TYPE = 'CHK')
                                      }
  OTMCNSER_CHECKCHECQUEBOOKS {
  SELECT 'X' Val
           FROM BANK_CHEQUE_BOOKS
          WHERE CASELOAD_ID = :caseloadId
            AND ACCOUNT_CODE = :accountCode
            AND FIRST_CHECK_NUMBER = :firstChecknumLong
            AND LAST_CHECK_NUMBER = :lastCheckNumLong
  }
  OTMCNSER_CHECKIFNEWSERIES {
  SELECT 'Y' VAL                                                       
     FROM   BANK_CHEQUE_BOOKS                                           
     WHERE  :firstChecknum BETWEEN FIRST_CHECK_NUMBER AND LAST_CHECK_NUMBER                    
     AND    NEXT_CHECK_NUMBER <= LAST_CHECK_NUMBER                                    
     AND    ACCOUNT_CODE = :accountCode                              
     AND    CASELOAD_ID = :caseloadId  
  
  }
  
  OTMCNSER_GETACCOUNTNUMBER {
  SELECT BANK_ACCOUNT_NUMBER
        FROM CASELOAD_CURRENT_ACCOUNTS 
       WHERE CASELOAD_ID = :caseloadId
         AND ACCOUNT_CODE = :accountCode  
  
  }
  OTMCNSER_GETCHEQUEBOOKSEQ {
--  SELECT CHEQUE_BOOK_SEQ.nextVal from  DUAL
  select nextVal('CHEQUE_BOOK_SEQ')
  
  }
  
  
