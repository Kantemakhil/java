
OTMCPRIN_FIND_CGFKBANKCRCHEQUESTATUS {
 	SELECT REF_CODE.CODE        CODE ,        REF_CODE.DESCRIPTION DESCRIPTION   FROM REFERENCE_CODES REF_CODE  WHERE DOMAIN = 'CHEQUE_STS'    AND ACTIVE_FLAG = 'Y'    AND EXPIRED_DATE IS NULL 	 AND REF_CODE.DESCRIPTION= :bankChequeStatus  ORDER BY REF_CODE.DESCRIPTION
}

OTMCPRIN_FIND_CGFKBANKCRCHEQSTATUSVOID {
 	SELECT REF_CODE.CODE        CHEQUE_STATUS /* CG$FK */ ,        REF_CODE.DESCRIPTION DSP_DESCRIPTION   FROM REFERENCE_CODES REF_CODE  WHERE DOMAIN = 'CHEQUE_STS'    AND (ACTIVE_FLAG = 'Y'    AND EXPIRED_DATE IS NULL 	 AND REF_CODE.CODE = 'VOID' )  ORDER BY REF_CODE.DESCRIPTION
}
OTMCPRIN_BANKCB_FIND_BANK_CHEQUE_BOOKS {
 	SELECT ROW_ID,CASELOAD_ID,ACCOUNT_CODE,CHEQUE_BOOK_SEQ,ACCOUNT_NUMBER,FIRST_CHECK_NUMBER,LAST_CHECK_NUMBER,NEXT_CHECK_NUMBER,TXN_ENTRY_DATE,
    MODIFY_USER_ID,MODIFY_DATE, LIST_SEQ,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,  SEAL_FLAG FROM   BANK_CHEQUE_BOOKS where

}
OTMCPRIN_BANKCB_UPDATE_BANK_CHEQUE_BOOKS {
	 update BANK_CHEQUE_BOOKS set LAST_CHECK_NUMBER =:lastCheckNumber, NEXT_CHECK_NUMBER =:nextCheckNumber, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where CASELOAD_ID =:caseloadId and ACCOUNT_CODE =:accountCode and CHEQUE_BOOK_SEQ =:chequeBookSeq 
}

OTMCPRIN_BANKCR_FIND_BANK_CHEQUE_REGISTERS {
 	SELECT * FROM BANK_CHEQUE_REGISTERS   WHERE  caseload_id = :caseloadId and account_code = :accountCode
 	and cheque_number BETWEEN :firstCheckNumber AND :lastCheckNumber ORDER BY CHEQUE_NUMBER DESC
}
OTMCPRIN_BANKCR_UPDATE_BANK_CHEQUE_REGISTERS {	
	 update BANK_CHEQUE_REGISTERS set REASON_TEXT =:reasonText, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where CASELOAD_ID =:caseLoadId and ACCOUNT_CODE =:accountCode and CHEQUE_NUMBER =:chequeNumber
}


OTMCPRIN_BANK_CR_KEYDELRECKEY-DELREC {
	SELECT PROFILE_VALUE INTO LV_PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE='CLIENT' AND PROFILE_CODE='CHQ_STAT_DEL'
}

OTMCPRIN_BANK_CR_KEYDELRECKEY-DELREC {
	SELECT  COUNT(*) INTO LV_NONPRINTING_STATUS FROM BANK_CHEQUE_REGISTERS WHERE CASELOAD_ID   = CSLD_ID AND ACCOUNT_CODE  = ACCT_CODE AND CHEQUE_STATUS <> 'PRINTING' AND CHEQUE_NUMBER BETWEEN LV_CHEQUE_NUMBER AND L_CHK_NUM
}

OTMCPRIN_BANK_CR_KEYDELRECKEY-DELREC {
	SELECT  COUNT(*) INTO LV_PRINTING_STATUS FROM BANK_CHEQUE_REGISTERS WHERE CASELOAD_ID   = CSLD_ID AND ACCOUNT_CODE  = ACCT_CODE AND CHEQUE_STATUS = 'PRINTING' AND CHEQUE_NUMBER BETWEEN LV_CHEQUE_NUMBER AND L_CHK_NUM
}

OTMCPRIN_BANK_CR_POSTDELETE {
	SELECT MAX(CHEQUE_NUMBER) CHECK_NUM FROM BANK_CHEQUE_REGISTERS WHERE CASELOAD_ID   = CSLD_ID AND ACCOUNT_CODE  = ACCT_CODE
}

OTMCPRIN_CGFKCHK_BANK_CR_BANK_CR_REF_C_ {
	SELECT REF_CODE.DESCRIPTION FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :CHEQUESTATUS AND     DOMAIN = 'CHEQUE_STS' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL
}

OTMCPRIN_CGWHEN_NEW_FORM_INSTANCE{
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}
OTMCPRIN_GETMAXCHECKNUM {
SELECT MAX(cheque_number) cheque_number      FROM BANK_CHEQUE_REGISTERS
       WHERE caseload_id = :caseLoadId    AND account_code = :accountCode  AND cheque_number BETWEEN :firstCheckNumber AND :lastCheckNumber
}
OTMCPRIN_INSERT_INTO_REGISTERS {
insert into BANK_CHEQUE_REGISTERS (CASELOAD_ID, TXN_ID, CHEQUE_NUMBER, CHEQUE_STATUS, REASON_TEXT, TRANSACTION_DATE, ORIGIN_TYPE, CREATE_USER_ID, CREATE_DATE, START_TXN_ID, END_TXN_ID, ACCOUNT_CODE, CREATE_DATETIME , MODIFY_DATETIME) values (:caseloadId, null, :checkNumber, 'VOID', 'ALIGNMENT', CURRENT_TIMESTAMP, 'SYSTEM', :createUserId , CURRENT_TIMESTAMP, null, null, :accountCode, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP )
}
OTMCPRIN_CHECKIFNEWSERIES {
 SELECT 'X' VAL                                                               
     FROM   BANK_CHEQUE_BOOKS                                           
     WHERE  :lastCheckNumber BETWEEN FIRST_CHECK_NUMBER AND LAST_CHECK_NUMBER                    
     AND    NEXT_CHECK_NUMBER <= LAST_CHECK_NUMBER                                    
     AND    ACCOUNT_CODE = :accountCode                              
     AND    CASELOAD_ID = :caseloadId
     AND    ROW_ID != :rowId

}
OTMCPRIN_CHECKIFOVEROTHSERIES {
SELECT DISTINCT 'Y'
  FROM   bank_cheque_books
  WHERE  caseload_id = :caseloadId
  AND    account_code = :accountCode
  AND    first_check_number BETWEEN :firstCheckNumber AND :lastCheckNumber
  AND    row_id != :rowId

}

OTMCPRIN_BCROWMAXCHECQUENUMBER {
SELECT MAX(cheque_number) cheque_number
                        FROM BANK_CHEQUE_REGISTERS
                        WHERE  caseload_id = :caseloadId
                          AND account_code = :accountCode
                          AND cheque_number BETWEEN :firstCheckNumber AND :lastCheckNumber

}

OTMCPRIN_GETDESC {
SELECT REF_CODE.DESCRIPTION
      FROM   REFERENCE_CODES REF_CODE
      WHERE  REF_CODE.CODE = :chequeStatus
      AND     DOMAIN = 'CHEQUE_STS' AND 
      ACTIVE_FLAG = 'Y' AND 
      EXPIRED_DATE IS NULL
      
      }
      
OTMCPRIN_GETCODEDESC {
      SELECT REF_CODE.CODE        CODE  ,        REF_CODE.DESCRIPTION  DESCRIPTION   FROM REFERENCE_CODES REF_CODE  
      WHERE DOMAIN = 'CHEQUE_STS' ORDER BY REF_CODE.DESCRIPTION
      }