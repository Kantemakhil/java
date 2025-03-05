 
OCSRECEI_FIND_CGFKOMSREQPRINTERID {
 	select
        PRTR.PRINTER_ID PRINTER_ID /* CG$FK*/
        ,
        PRTR.DESCRIPTION DSP_DESCRIPTION
from
        PRINTERS PRTR
where (coalesce(EXPIRY_DATE::text, '') = ''
        or EXPIRY_DATE > LOCALTIMESTAMP )
order by
        PRTR.DESCRIPTION asc
}

OCSRECEI_FIND_CGFKOMSREQMODULENAME {
 	SELECT
  OMS_MOD.MODULE_NAME CODE /* CG$FK */, OMS_MOD.DESCRIPTION DESCRIPTION
FROM OMS_MODULES OMS_MOD
WHERE
  OMS_MOD.MODULE_TYPE = 'REPORT' AND
  OMS_MOD.MODULE_NAME IN( 'OCRRECEI' , 'OCRDRECE' )

}

OCSRECEI_OMSREQ_FIND_OMS_REQUESTS {
 	SELECT *  FROM OMS_REQUESTS  /* where  */
}
OCSRECEI_OMSREQ_INSERT_OMS_REQUESTS {
	INSERT INTO OMS_REQUESTS(REQUEST_ID, MODULE_NAME, NUMBER_OF_COPY, REQUEST_STATUS, PRINTER_ID, DISPLAY_FLAG, REQUEST_DATE, REQUEST_USER_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG) 
    VALUES(NEXTVAL('REQUEST_ID'), :moduleName, :numberOfCopy, :requestStatus, :printerId, :displayFlag, :requestDate, :requestUserId, :createDatetime, :createUserId, :modifyDatetime, :modifyUserId, :sealFlag)
          
OCSRECEI_OMSREQ_UPDATE_OMS_REQUESTS {
	UPDATE OMS_REQUESTS 
    SET  MODULE_NAME=:moduleName, NUMBER_OF_COPY=:numberOfCopy, REQUEST_STATUS=:requestStatus, PRINTER_ID=:printerId, DISPLAY_FLAG=:displayFlag, REQUEST_DATE=:requestDate, REQUEST_USER_ID=:requestUserId, CREATE_USER_ID=:createUserId, MODIFY_DATETIME=:modifyDatetime, MODIFY_USER_ID=:modifyUserId, SEAL_FLAG=:sealFlag
    WHERE REQUEST_ID=:requestId
}

OCSRECEI_OMSREQ_DELETE_OMS_REQUESTS { 
	DELETE FROM OMS_REQUESTS  WHERE REQUEST_ID=:requestId
} 

OCSRECEI_OFFTXN_FIND_OFFENDER_TRANSACTIONS {
 	SELECT *  FROM OFFENDER_TRANSACTIONS  /* where  */
}
OCSRECEI_OFFTXN_UPDATE_OFFENDER_TRANSACTIONS {
	UPDATE OFFENDER_TRANSACTIONS 
    SET  CASELOAD_ID=:caseloadId, OFFENDER_ID=:offenderId, OFFENDER_BOOK_ID=:offenderBookId, TXN_POSTING_TYPE=:txnPostingType, TXN_TYPE=:txnType, TXN_ENTRY_DESC=:txnEntryDesc, TXN_ENTRY_AMOUNT=:txnEntryAmount, TXN_ENTRY_DATE=:txnEntryDate, SUB_ACCOUNT_TYPE=:subAccountType, TXN_REFERENCE_NUMBER=:txnReferenceNumber, MODIFY_DATE=:modifyDate, RECEIPT_NUMBER=:receiptNumber,
    SLIP_PRINTED_FLAG=:slipPrintedFlag, TRANSFER_CASELOAD_ID=:transferCaseloadId, RECEIPT_PRINTED_FLAG=:receiptPrintedFlag, PRE_WITHHOLD_AMOUNT=:preWithholdAmount, DEDUCTION_FLAG=:deductionFlag, CLOSING_CHEQUE_NUMBER=:closingChequeNumber, REMITTER_NAME=:remitterName, PAYEE_CODE=:payeeCode, PAYEE_NAME_TEXT=:payeeNameText, PAYEE_CORPORATE_ID=:payeeCorporateId,
    PAYEE_PERSON_ID=:payeePersonId, ADJUST_TXN_ID=:adjustTxnId, ADJUST_TXN_ENTRY_ID=:adjustTxnEntryId, ADJUST_OFFENDER_ID=:adjustOffenderId, ADJUST_ACCOUNT_CODE=:adjustAccountCode, TXN_ADJUSTED_FLAG=:txnAdjustedFlag, DEDUCTION_TYPE=:deductionType, INFO_NUMBER=:infoNumber, HOLD_CLEAR_FLAG=:holdClearFlag, HOLD_UNTIL_DATE=:holdUntilDate, HOLD_NUMBER=:holdNumber, 
    GROSS_AMOUNT=:grossAmount, GROSS_NET_FLAG=:grossNetFlag, REMITTER_ID=:remitterId, APPLY_SPENDING_LIMIT_AMOUNT=:applySpendingLimitAmount, RECEIPT_PENDING_PRINT_FLAG=:receiptPendingPrintFlag, SEAL_FLAG=:sealFlag, ORG_TXN_TYPE=:orgTxnType,  CREATE_USER_ID=:createUserId, MODIFY_DATETIME=CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId
    WHERE TXN_ID=:txnId and TXN_ENTRY_SEQ=:txnEntrySeq
} 


OCSRECEI_OMS_REQ_PREINSERT_ {
	SELECT NEXTVAL('REQUEST_ID')
FROM DUAL
}

OCSRECEI_CGFKCHK_OMS_REQ_OMS_REQ_PRINT_ {
	SELECT PRTR.DESCRIPTION FROM   PRINTERS PRTR WHERE  PRTR.PRINTER_ID = :PRINTER_ID
}

OCSRECEI_CGFKCHK_OMS_REQ_OMS_REQ_OMS_M_ {
	SELECT OMS_MOD.DESCRIPTION FROM   OMS_MODULES OMS_MOD WHERE  OMS_MOD.MODULE_NAME = :MODULE_NAME AND    OMS_MOD.MODULE_TYPE = 'REPORT'
}

OCSRECEI_CGWHEN_NEW_FORM_INSTANCE {
	SELECT  SYSDATE(),upper(user) as user FROM    DUAL
}

OCSRECEI_RUN_REPORT_FOR_CLIENT {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

OCSRECEI_RUN_REPORT_FOR_SYS {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'
}

OCSRECEI_RUN_REPORT_GET_PRINT_FORMAT_CODE {
	SELECT PRINT_FORMAT_CODE FROM OMS_MODULES WHERE MODULE_NAME = :P_REPORT_NAME
}

OCSRECEI_RUN_REPORT_ROLE_CUR {
	SELECT cast(USERENV_number('SESSIONID') as text) FROM DUAL
  }
  OCSRECEI_OFF_TXN_POST_QUERY_TWO {
  SELECT
  LAST_NAME|| ', ' || FIRST_NAME as NAME, OFFENDER_ID_DISPLAY
FROM
  OFFENDERS O, OFFENDER_BOOKINGS O_BKG
WHERE
  O.OFFENDER_ID = :off_id AND
  O.ROOT_OFFENDER_ID = O_BKG.ROOT_OFFENDER_ID AND
  O_BKG.OFFENDER_BOOK_ID =
    (SELECT MAX (OFFENDER_BOOK_ID)
     FROM OFFENDER_BOOKINGS O_BKG1
     WHERE
       O_BKG1.ROOT_OFFENDER_ID = O_BKG.ROOT_OFFENDER_ID )

               }
OCSRECEI_PRINT_RECEIPTS_TMP_DELETEQUERY {
DELETE FROM print_receipts_tmp prt WHERE prt.session_id = :p_session_id
}  
OCSRECEI_PRINT_RECEIPTS_TMP_INSERT_QUERY {
INSERT INTO print_receipts_tmp(MODULE_NAME,TXN_ID,OFFENDER_ID,RECEIPT_NUMBER,SESSION_ID, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID)
                    VALUES ('OCSRECEI', :txnId, :offenderId, :receiptNumber, :sessionId,CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP, :modifyUserId)
                    } 
OCSRECEI_CF_PROFILE_CLIENT {                    
  SELECT description,
   (SELECT UPPER(PROFILE_VALUE)||':'  
  FROM SYSTEM_PROFILES 
  WHERE PROFILE_CODE = 'INST_AGENCY' AND PROFILE_TYPE = 'LABEL') as PROFILE_VALUE
  FROM   system_profiles WHERE  profile_type = 'CLIENT' AND    profile_code = 'CLIENT' 
  }
  OCSRECEI_CF_CASELOAD_DESC {
  SELECT description FROM   caseloads WHERE  caseload_id = :CASELOAD
  }
  
  OCSRECEI_UPDATE_OFFENDER_TRANSACTIONS_RPT {
		  UPDATE offender_transactions SET receipt_printed_flag = 'Y',MODIFY_DATETIME= CURRENT_TIMESTAMP,modify_user_id = :modify_user_id
		  WHERE receipt_number IN (SELECT receipt_number FROM print_receipts_tmp WHERE module_name = :from_module AND session_id = :session_id)
  }  
  OCSRECEI_PRINT_RECEIPTS_TMP_DELETE_QUERY_RPT {
DELETE FROM print_receipts_tmp
    WHERE module_name = :module_name  AND session_id = :p_session_id
} 
OCSRECEI_REPORTS_QUERY {

select REF.domain, ot.txn_entry_date FTXNDATE, ot.receipt_number FRECNO, ot.remitter_name remitter, ot.txn_entry_amount TOTALAMOUNT, o.last_name || ', ' || o.first_name || ' ' || o.middle_name FOFFNAME, ot.OFFENDER_ID, lu.description FLOCATION, ot.txn_entry_desc FTXNDESC, ot.modify_user_id USERID, ot.txn_id TXNID, ot.payee_name_text PAYEE from transaction_types tt, reference_domains ref, print_receipts_tmp pr, offender_transactions ot, offenders o, offender_bookings ob left outer join living_units lu on (ob.living_unit_id = lu.living_unit_id and 'Y' = lu.active_flag) where coalesce(ot.receipt_printed_flag, 'N') like case when UPPER('N')= 'Y' then 'N' else '%' end and (ot.receipt_number is not null and ot.receipt_number::text <> '') and ot.caseload_id = :caseload and o.root_offender_id = ot.offender_id and ot.offender_id = o.root_offender_id and o.offender_id = ob.offender_id and exists ( select 1 from caseloads c where c.caseload_type = ob.booking_type and c.caseload_id = :caseload) and ob.offender_book_id = ( select MAX(ob2.offender_book_id) from offender_bookings ob2 where exists ( select 1 from caseloads c where c.caseload_type = ob2.booking_type and c.caseload_id = :caseload) and ob2.root_offender_id = ot.offender_id) and REF.domain in ( select domain from reference_domains limit :COPY) and tt.txn_type = ot.txn_type and tt.txn_usage = 'D' and ot.receipt_number = pr.receipt_number and pr.module_name = 'OCSRECEI' and pr.session_id = session_id order by ot.receipt_number;
}
OCSRECEI_NUMBER_TO_WORD {
SELECT
CASE
when :TOTAMT > 3442447 THEN
     replace(:TOTAMT::money::text,'$','') || ' ONLY '
    WHEN AMT > 0 AND CENTS > 0 THEN
        to_text(AMT)|| ' DOLLAR(S) AND ' || to_text(CENTS) || ' CENT(S) ONLY'
    WHEN AMT > 0 THEN
        to_text(AMT) || ' DOLLAR(S) ONLY'
    WHEN CENTS > 0 THEN
        to_text(CENTS)|| ' CENT(S) ONLY'
    ELSE
    'ZERO'
END AMTWARDS FROM
 (select CENTS::int,AMT::int from (SELECT (:TOTAMT - AMT) * 100 CENTS, AMT FROM (SELECT ROUND(:TOTAMT,0) AMT FROM dual)A)A)A
 
 }
 
 OCSRECEI_GET_PRINT_RECEIPTS_BY_SESSIONID{
    SELECT * FROM print_receipts_tmp WHERE SESSION_ID = :sessionId
 }
  