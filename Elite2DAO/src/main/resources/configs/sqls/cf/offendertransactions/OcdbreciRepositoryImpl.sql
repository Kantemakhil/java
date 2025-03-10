
OCDBRECI_FIND_CGFKOFFTXN1_TXNTYPE {
 	SELECT TXN_TYPE.TXN_TYPE CODE /* CG$FK */  ,TXN_TYPE.DESCRIPTION FROM TRANSACTION_TYPES TXN_TYPE WHERE TXN_TYPE.TXN_TYPE IN (SELECT TXN_OPER.TXN_TYPE FROM TRANSACTION_OPERATIONS TXN_OPER WHERE MODULE_NAME = 'OCDBRECI' AND CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER) ) AND CASELOAD_TYPE = ( SELECT CS.CASELOAD_TYPE FROM CASELOADS CS WHERE CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER)) ORDER BY TXN_TYPE.LIST_SEQ ASC  ,TXN_TYPE.TXN_TYPE ASC
}

OCDBRECI_FIND_CGFKOFFTXNDSPINFORMATIONN {
 	select OD.INFORMATION_NUMBER DESCRIPTION, OD.INFORMATION_NUMBER CODE from deduction_types dt, caseload_deduction_profiles cdp, offender_deductions od left outer join offender_deduction_shadows ods on (OD.offender_deduction_id = ODS.offender_deduction_id) left outer join offender_mon_deductions omd on (OD.offender_deduction_id = OMD.offender_deduction_id) where OD.OFFENDER_ID = :OFFENDERID and OD.DEDUCTION_TYPE = DT.DEDUCTION_TYPE and DT.caseload_code in ('BOTH', :CASELOADTYPE) and exists ( select 1 from offender_beneficiaries OB where OB.offender_deduction_id = OD.offender_deduction_id and OB.offender_id = :OFFENDERID) and exists ( select 1 from OFFENDER_DEDUCTION_RECEIPTS where receipt_txn_type = :TXNTYPE and offender_deduction_id = od.offender_deduction_id) and coalesce(OD.adjustment_reason_code::text, '') = '' and date_trunc('day', OD.effective_date) <= date_trunc('day', LOCALTIMESTAMP) and OD.caseload_id = CDP.caseload_id and CDP.deduction_type = OD.deduction_type and (DT.DEDUCTION_CATEGORY <> 'ALCN' and ( (coalesce(OD.max_total_amount, 0) > coalesce(OD.deduction_amount, 0) and (OD.max_total_amount is not null and OD.max_total_amount::text <> '')) or (coalesce(OD.max_monthly_amount, 0) > coalesce(OMD.deduction_amount, 0) and (OD.max_monthly_amount is not null and OD.max_monthly_amount::text <> '')) or (OD.max_recursive_amount *(MONTHS_BETWEEN(date_trunc('month', LOCALTIMESTAMP), date_trunc('month', OD.effective_date))+ 1) > coalesce(OD.deduction_amount, 0) and (OD.max_recursive_amount is not null and OD.max_recursive_amount::text <> '')) )) and ( ( (OD.max_monthly_amount is not null and OD.max_monthly_amount::text <> '') and ( not exists ( select '1' from offender_mon_deductions OMD4 where OMD4.offender_deduction_id = OD.offender_deduction_id) or exists ( select '1' from offender_mon_deductions OMD4 where OMD4.offender_deduction_id = OD.offender_deduction_id) and date_trunc('month', LOCALTIMESTAMP) = OMD.monthly_deduction_date ) ) or (coalesce(OD.max_monthly_amount::text, '') = '' and coalesce(OD.max_recursive_amount::text, '') = '') or ((OD.max_recursive_amount is not null and OD.max_recursive_amount::text <> '') and (not exists ( select '1' from offender_mon_deductions OMD1 where OMD1.offender_deduction_id = OD.offender_deduction_id) or exists ( select '1' from offender_mon_deductions OMD1 where OMD1.offender_deduction_id = OD.offender_deduction_id and OMD1.monthly_deduction_date = ( select MAX(OMD2.monthly_deduction_date) from offender_mon_deductions OMD2 where OMD2.offender_deduction_id = OMD1.offender_deduction_id)) and OMD.offender_deduction_id = OD.offender_deduction_id and OMD.monthly_deduction_date = ( select MAX(OMD3.monthly_deduction_date) from offender_mon_deductions OMD3 where OMD3.offender_deduction_id = OMD.offender_deduction_id) ) ) )
}

OCDBRECI_OFFTXN1_FIND_OFFENDER_TRANSACTIONS {
 	SELECT  * FROM OFFENDER_TRANSACTIONS  /* where  */
}
OCDBRECI_OFFTXN1_INSERT_OFFENDER_TRANSACTIONS {
	INSERT INTO OFFENDER_TRANSACTIONS() VALUES(:)
}

OCDBRECI_OFFTXN1_UPDATE_OFFENDER_TRANSACTIONS {
	UPDATE OFFENDER_TRANSACTIONS set /* where */
}

OCDBRECI_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT  * FROM SYSTEM_PROFILES  /* where  */
}

OCDBRECI_CGFKCHK_OFF_TXN1_OFF_TXN_TXN__ {
	SELECT TXN_TYPE.DESCRIPTION ,TXN_TYPE.LIST_SEQ FROM   TRANSACTION_TYPES TXN_TYPE WHERE  TXN_TYPE.TXN_TYPE = :TXNTYPE AND     TXN_TYPE.TXN_TYPE IN (SELECT TXN_OPER.TXN_TYPE FROM TRANSACTION_OPERATIONS TXN_OPER WHERE MODULE_NAME = 'OCDBRECI' AND CASELOAD_ID = :CASELOADID) AND CASELOAD_TYPE = :CASELOADTYPE
}

OCDBRECI_CGWHEN_NEW_FORM_INSTANCE_ {
SELECT  current_timestamp , USER FROM    DUAL
}

OCDBRECI_CGFKCHK_OFF_TXN_OFF_TXN_OFF_D_ {
	SELECT OD.INFORMATION_NUMBER FROM   OFFENDER_DEDUCTIONS OD, OFFENDER_DEDUCTION_SHADOWS  ODS, OFFENDER_MON_DEDUCTIONS     OMD, CASELOAD_DEDUCTION_PROFILES CDP, DEDUCTION_TYPES             DT WHERE  OD.OFFENDER_ID = :OFFENDERID AND    OD.DEDUCTION_TYPE   = DT.DEDUCTION_TYPE AND    DT.CASELOAD_CODE IN ('BOTH', :CASELOADTYPE) AND    EXISTS (SELECT 1 FROM OFFENDER_BENEFICIARIES OB WHERE OB.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND   OB.OFFENDER_ID = :OFFENDERID) AND    EXISTS (SELECT 1 FROM OFFENDER_DEDUCTION_RECEIPTS WHERE  RECEIPT_TXN_TYPE = :TXNTYPE AND OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) AND    OD.ADJUSTMENT_REASON_CODE IS NULL AND    TRUNC(OD.EFFECTIVE_DATE,'DD')    <= TRUNC(SYSDATE,'DD') AND    OD.OFFENDER_DEDUCTION_ID = ODS.OFFENDER_DEDUCTION_ID (+) AND    OD.CASELOAD_ID           = CDP.CASELOAD_ID AND    CDP.DEDUCTION_TYPE       = OD.DEDUCTION_TYPE AND   (DT.DEDUCTION_CATEGORY <> 'ALCN' AND ( (NVL(OD.MAX_TOTAL_AMOUNT,0)   > NVL(OD.DEDUCTION_AMOUNT,0) AND OD.MAX_TOTAL_AMOUNT IS NOT NULL) OR (NVL(OD.MAX_MONTHLY_AMOUNT,0) > NVL(OMD.DEDUCTION_AMOUNT,0) AND OD.MAX_MONTHLY_AMOUNT IS NOT NULL) OR (OD.MAX_RECURSIVE_AMOUNT*(MONTHS_BETWEEN(TRUNC(SYSDATE,'MM'),TRUNC(OD.EFFECTIVE_DATE,'MM'))+1) > NVL(OD.DEDUCTION_AMOUNT,0) AND OD.MAX_RECURSIVE_AMOUNT IS NOT NULL) )) AND    OD.OFFENDER_DEDUCTION_ID       = OMD.OFFENDER_DEDUCTION_ID (+) AND  ( (  OD.MAX_MONTHLY_AMOUNT IS NOT NULL AND ( NOT EXISTS (SELECT '1' FROM OFFENDER_MON_DEDUCTIONS OMD4 WHERE OMD4.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) OR  EXISTS (SELECT '1' FROM OFFENDER_MON_DEDUCTIONS OMD4 WHERE OMD4.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) AND   TRUNC(SYSDATE,'MM')  = OMD.MONTHLY_DEDUCTION_DATE ) ) OR (OD.MAX_MONTHLY_AMOUNT IS NULL AND OD.MAX_RECURSIVE_AMOUNT IS NULL) OR (OD.MAX_RECURSIVE_AMOUNT IS NOT NULL AND (NOT EXISTS (SELECT '1' FROM OFFENDER_MON_DEDUCTIONS OMD1 WHERE OMD1.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) OR  EXISTS (SELECT '1' FROM OFFENDER_MON_DEDUCTIONS OMD1 WHERE OMD1.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND   OMD1.MONTHLY_DEDUCTION_DATE = (SELECT MAX(OMD2.MONTHLY_DEDUCTION_DATE) FROM OFFENDER_MON_DEDUCTIONS OMD2 WHERE OMD2.OFFENDER_DEDUCTION_ID = OMD1.OFFENDER_DEDUCTION_ID)) AND OMD.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND OMD.MONTHLY_DEDUCTION_DATE = (SELECT MAX(OMD3.MONTHLY_DEDUCTION_DATE) FROM OFFENDER_MON_DEDUCTIONS OMD3 WHERE OMD3.OFFENDER_DEDUCTION_ID = OMD.OFFENDER_DEDUCTION_ID) ) ) )
}

OCDBRECI_CGFKLKP_OFF_TXN_OFF_TXN_OFF_D_ {
	SELECT OD.OFFENDER_ID FROM   OFFENDER_DEDUCTIONS OD, OFFENDER_DEDUCTION_SHADOWS  ODS, OFFENDER_MON_DEDUCTIONS     OMD, CASELOAD_DEDUCTION_PROFILES CDP, DEDUCTION_TYPES             DT WHERE  OD.INFORMATION_NUMBER = :DSPINFORMATIONNUMBER AND    OD.DEDUCTION_TYPE   = DT.DEDUCTION_TYPE AND    DT.CASELOAD_CODE IN ('BOTH', :CASELOADTYPE) AND    EXISTS (SELECT 1 FROM OFFENDER_BENEFICIARIES OB WHERE OB.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND   OB.OFFENDER_ID = :OFFENDERID) AND    EXISTS (SELECT 1 FROM OFFENDER_DEDUCTION_RECEIPTS WHERE  RECEIPT_TXN_TYPE = :TXNTYPE AND OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) AND    OD.ADJUSTMENT_REASON_CODE IS NULL AND    TRUNC(OD.EFFECTIVE_DATE,'DD')    <= TRUNC(SYSDATE,'DD') AND    OD.OFFENDER_DEDUCTION_ID = ODS.OFFENDER_DEDUCTION_ID (+) AND    OD.CASELOAD_ID           = CDP.CASELOAD_ID AND    CDP.DEDUCTION_TYPE       = OD.DEDUCTION_TYPE AND   (DT.DEDUCTION_CATEGORY <> 'ALCN' AND ( (NVL(OD.MAX_TOTAL_AMOUNT,0)   > NVL(OD.DEDUCTION_AMOUNT,0) AND OD.MAX_TOTAL_AMOUNT IS NOT NULL) OR (OD.MAX_MONTHLY_AMOUNT IS NULL AND OD.MAX_RECURSIVE_AMOUNT IS NULL AND OD.MAX_TOTAL_AMOUNT IS NULL) OR (NVL(OD.MAX_MONTHLY_AMOUNT,0) > NVL(OMD.DEDUCTION_AMOUNT,0) AND OD.MAX_MONTHLY_AMOUNT IS NOT NULL) OR (OD.MAX_RECURSIVE_AMOUNT*(MONTHS_BETWEEN(TRUNC(SYSDATE,'MM'),TRUNC(OD.EFFECTIVE_DATE,'MM'))+1) > NVL(OD.DEDUCTION_AMOUNT,0) AND OD.MAX_RECURSIVE_AMOUNT IS NOT NULL) )) AND    OD.OFFENDER_DEDUCTION_ID       = OMD.OFFENDER_DEDUCTION_ID (+) AND  ( (  OD.MAX_MONTHLY_AMOUNT IS NOT NULL AND ( NOT EXISTS (SELECT '1' FROM OFFENDER_MON_DEDUCTIONS OMD4 WHERE OMD4.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) OR  EXISTS (SELECT '1' FROM OFFENDER_MON_DEDUCTIONS OMD4 WHERE OMD4.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) AND   TRUNC(SYSDATE,'MM')  = OMD.MONTHLY_DEDUCTION_DATE ) ) OR (OD.MAX_MONTHLY_AMOUNT IS NULL AND OD.MAX_RECURSIVE_AMOUNT IS NULL) OR (OD.MAX_RECURSIVE_AMOUNT IS NOT NULL AND (NOT EXISTS (SELECT '1' FROM OFFENDER_MON_DEDUCTIONS OMD1 WHERE OMD1.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) OR  EXISTS (SELECT '1' FROM OFFENDER_MON_DEDUCTIONS OMD1 WHERE OMD1.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND   OMD1.MONTHLY_DEDUCTION_DATE = (SELECT MAX(OMD2.MONTHLY_DEDUCTION_DATE) FROM OFFENDER_MON_DEDUCTIONS OMD2 WHERE OMD2.OFFENDER_DEDUCTION_ID = OMD1.OFFENDER_DEDUCTION_ID)) AND OMD.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND OMD.MONTHLY_DEDUCTION_DATE = (SELECT MAX(OMD3.MONTHLY_DEDUCTION_DATE) FROM OFFENDER_MON_DEDUCTIONS OMD3 WHERE OMD3.OFFENDER_DEDUCTION_ID = OMD.OFFENDER_DEDUCTION_ID) ) ) )
}

OCDBRECI_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

OCDBRECI_RUN_REPORTrole_cur {
	SELECT TO_NUMBER(USERENV('SESSIONID') ) INTO P_SESSION_ID FROM DUAL
}
GET_OFFENDER_ID_DATA {
SELECT V1.ROOT_OFFENDER_ID, V1.LAST_NAME, V1.FIRST_NAME, V1.OFFENDER_BOOK_ID FROM V_TRUST_HEADER_A V1 WHERE V1.OFFENDER_ID_DISPLAY = :OFFIDDISPLAY AND V1.OFFENDER_BOOK_ID =(SELECT MAX(OFFENDER_BOOK_ID) FROM V_TRUST_HEADER_A V2 WHERE V2.ROOT_OFFENDER_ID = V1.ROOT_OFFENDER_ID) AND V1.CASELOAD_ID = :CSLDID
}
GET_P_OFFENDER_ID_COUNT {
SELECT COUNT (0) FROM GL_TRANSACTIONS WHERE OFFENDER_ID = :OFFENDERID AND REVERSAL_REASON_CODE = 'BOUNCED'
}
GET_PROFILE_VALUE {
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE SYSTEM_PROFILES.PROFILE_TYPE = 'CLIENT' AND SYSTEM_PROFILES.PROFILE_CODE = 'BOUNCE_MSG'
}
GET_REFERENCE_CODES_VALID {
SELECT COUNT (0) FROM REFERENCE_CODES WHERE DOMAIN = 'BOUNCED_CHK' AND CODE = :PPAYMENTMETHOD AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL
}
CHK_OFFENDER_DEDUCTION_ID {
SELECT od.offender_deduction_id
                 FROM offender_deductions od
                WHERE od.offender_id = :ROOTOFFID
                  AND od.deduction_status IN('D', 'S')
}
CHK_OFFENDER_DEDUCTION_RECEIPTS {
SELECT 'Y' FROM OFFENDER_DEDUCTION_RECEIPTS ODR WHERE ODR.OFFENDER_DEDUCTION_ID = :TMPOFFDEDID AND ODR.RECEIPT_TXN_TYPE = :TXNTYPE
}
CHK_UNIQUE_OBLIGATION_FLAG {
SELECT DISTINCT COALESCE(UNIQUE_OBLIGATION_FLAG, 'N') FROM OFFENDERS WHERE OFFENDER_ID = :OFFENDERID
}
GET_PROFILE_VALUE_PAYMENT_PLAN {
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'PAYMENT_PLAN'
}
CHK_MISSING_PAYMENT_PLAN {
SELECT 'Y' FROM OFFENDER_DEDUCTIONS OD WHERE OFFENDER_ID = :OFFENDERID AND DEDUCTION_STATUS = 'A' AND DEDUCTION_TYPE IN (SELECT DEDUCTION_TYPE FROM DEDUCTION_TYPES WHERE DEDUCTION_CATEGORY ='FXOB') AND DEDUCTION_AMOUNT <= MAX_TOTAL_AMOUNT AND (INFORMATION_NUMBER = :INFONUM or :INFONUM is null) AND (OFFENDER_ID, GROUP_ID, INFORMATION_NUMBER) NOT IN (SELECT OFFENDER_ID, GROUP_ID, INFORMATION_NUMBER FROM OFFENDER_PAYMENT_PLANS WHERE PAYMENT_CLOSED_FLAG = 'N' AND OFFENDER_ID = :OFFENDERID AND (INFORMATION_NUMBER = :INFONUM or :INFONUM is null) AND GROUP_ID = od.GROUP_ID)
}
CHK_ACCOUNT_CLOSED_FLAG {
SELECT ACCOUNT_CLOSED_FLAG FROM OFFENDER_TRUST_ACCOUNTS WHERE CASELOAD_ID = :PCASELOADID AND OFFENDER_ID = :POFFENDERID
}
OCDBRECI_TXN_ID {
SELECT NEXTVAL('TXN_ID') FROM DUAL
}
OCDBRECI_RECEIPT_PRODUCTION_FLAG {
 SELECT RECEIPT_PRODUCTION_FLAG FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OCDBRECI' AND TXN_TYPE = :TRANSTYPE AND CASELOAD_ID = :CSLDID limit 1 
}
OCDBRECI_RECEIPT_NUMBER {
SELECT NEXTVAL(:seq) NEXT_SEQ FROM DUAL
}
OCDBRECI_DESCR_TXN_TYPE {
SELECT DESCRIPTION FROM TRANSACTION_TYPES WHERE TXN_TYPE = :TRANSTYPE
}
OCDBRECI_INSERT_INTO_OFFENDER_TRANSACTIONS {
INSERT INTO OFFENDER_TRANSACTIONS
( TXN_ID, TXN_ENTRY_SEQ, CASELOAD_ID, OFFENDER_ID, OFFENDER_BOOK_ID, TXN_POSTING_TYPE, TXN_TYPE, TXN_ENTRY_DESC, TXN_ENTRY_AMOUNT, TXN_ENTRY_DATE, SUB_ACCOUNT_TYPE,CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME,SLIP_PRINTED_FLAG, PRE_WITHHOLD_AMOUNT, DEDUCTION_FLAG, PAYEE_CODE, PAYEE_CORPORATE_ID, PAYEE_PERSON_ID, PAYEE_NAME_TEXT, DEDUCTION_TYPE, INFO_NUMBER, REMITTER_NAME, REMITTER_ID, RECEIPT_NUMBER , modify_date, modify_user_id )
SELECT :P_trans_number, :P_trans_seq, :P_csld_id, :P_off_id, :P_off_book_id, :P_trans_post_type, :P_trans_type, :p_trans_desc, :p_trans_amount, :P_trans_date, :p_sub_act_type,current_timestamp,:create_user_id, current_timestamp ,  :p_slip_print_flag, :p_pre_ded_amount, :p_deduction_flag, :p_payee_code, :p_payee_corp_id, :p_payee_person_id, :p_payee_name_text, :p_deduction_type, :p_info_number, :p_remitter_name, :p_remitter_id, :p_receipt_number , current_timestamp , :modify_user_id FROM DUAL
}

OCDBRECI_RUN_REPORT_ROLE_CUR {
	SELECT cast(userenv_number('SESSIONID') as int) FROM DUAL
}
OCDBRECI_PRINT_RECEIPTS_TMP_DELETEQUERY {
	DELETE FROM print_receipts_tmp prt WHERE prt.session_id = :p_session_id
}
OCDBRECI_PRINT_RECEIPTS_TMP_INSERT_QUERY {
INSERT INTO print_receipts_tmp(MODULE_NAME,TXN_ID,OFFENDER_ID,RECEIPT_NUMBER,SESSION_ID) VALUES ('OCDBRECI', :txnId, :offenderId, :receiptNumber, :sessionId)
}
OCDBRECI_CF_PROFILE_CLIENT {                    
 SELECT DESCRIPTION, (SELECT UPPER(PROFILE_VALUE)||':' FROM SYSTEM_PROFILES WHERE PROFILE_CODE = 'INST_AGENCY' AND PROFILE_TYPE = 'LABEL') AS PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'CLIENT' 
}
OCDBRECI_CF_CASELOAD_DESC {
  SELECT description FROM   caseloads WHERE  caseload_id = :CASELOAD
}
OCDBRECI_REPORTS_QUERY {
SELECT REF.domain, UPPER(sp.description) client, cl.description FOFFNAME, ot.txn_entry_date FTXNDATE, ot.receipt_number FRECNO, ot.remitter_name remitter, ot.txn_entry_amount AMT, ot.offender_id, ot.txn_entry_desc FTXNDESC, ot.modify_user_id USERID, ot.txn_id TXNID, ot.txn_entry_seq TXNSEQ, ot.txn_type TXNTYPE FROM system_profiles sp, caseloads cl, offender_transactions ot, reference_domains ref, transaction_types tt, print_receipts_tmp pr WHERE UPPER (sp.profile_type) = 'CLIENT' AND UPPER (sp.profile_code) = 'CLIENT' AND COALESCE (ot.receipt_printed_flag, 'N') LIKE CASE UPPER ('N') WHEN 'Y' THEN 'N' ELSE '%' END AND ot.receipt_number IS NOT NULL AND cl.caseload_id = ot.caseload_id AND ot.caseload_id = :caseload AND REF.domain IN (SELECT domain FROM reference_domains limit 2) AND tt.txn_type = ot.txn_type AND tt.txn_usage = 'R' AND ot.receipt_number = pr.receipt_number AND pr.module_name = :from_module AND pr.session_id = :session_id ORDER BY ot.receipt_number
}
OCDBRECI_UPDATE_OFFENDER_TRANSACTIONS_RPT {
 UPDATE offender_transactions SET receipt_printed_flag = 'Y',MODIFY_DATETIME= CURRENT_TIMESTAMP,modify_user_id = :modify_user_id
		  WHERE receipt_number IN (SELECT receipt_number FROM print_receipts_tmp WHERE module_name = :from_module AND session_id = :session_id)
 }
OCDBRECI_PRINT_RECEIPTS_TMP_DELETE_QUERY_RPT {
DELETE FROM print_receipts_tmp WHERE module_name = 'OCDBRECI' AND session_id = :session_id
}
OCDBRECI_GET_OFFENDER_ID_DISPLAY {
SELECT offender_id_display FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK WHERE V_HEADER_BLOCK.OFFENDER_BOOK_ID = (SELECT MAX(VHB2.OFFENDER_BOOK_ID) FROM V_HEADER_BLOCK_FN(:USERID) VHB2 WHERE VHB2.ROOT_OFFENDER_ID = V_HEADER_BLOCK.ROOT_OFFENDER_ID AND (VHB2.COMMUNITY_ACTIVE_FLAG = 'Y' OR (NOT EXISTS (SELECT 'X' FROM V_HEADER_BLOCK_FN(:USERID) VHB3 WHERE VHB3.COMMUNITY_ACTIVE_FLAG = 'Y' AND VHB3.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID ) ) ) AND ((VHB2.INTAKE_AGY_LOC_ID <> 'CLOSE' AND EXISTS (SELECT 'X' FROM OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 WHERE CAL1.CASELOAD_ID = :CASELOAD AND CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID AND LOCS.REMOVED_DATE IS NULL ) ) OR (VHB2.INTAKE_AGY_LOC_ID = 'CLOSE' AND EXISTS ( SELECT 'X' FROM OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 WHERE CAL1.CASELOAD_ID = :CASELOAD AND CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID ) ) ) ) AND V_HEADER_BLOCK.ROOT_OFFENDER_ID = :OFFENDERID
}
OCDBRECI_GET_OFFENDER_NAME {
SELECT LAST_NAME, FIRST_NAME, MIDDLE_NAME FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK WHERE V_HEADER_BLOCK.OFFENDER_BOOK_ID = (SELECT MAX(VHB2.OFFENDER_BOOK_ID) FROM V_HEADER_BLOCK_FN(:USERID) VHB2 WHERE VHB2.ROOT_OFFENDER_ID = V_HEADER_BLOCK.ROOT_OFFENDER_ID AND (VHB2.COMMUNITY_ACTIVE_FLAG = 'Y' OR (NOT EXISTS (SELECT 'X' FROM V_HEADER_BLOCK_FN(:USERID) VHB3 WHERE VHB3.COMMUNITY_ACTIVE_FLAG = 'Y' AND VHB3.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID ) ) ) AND ((VHB2.INTAKE_AGY_LOC_ID <> 'CLOSE' AND EXISTS (SELECT 'X' FROM OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 WHERE CAL1.CASELOAD_ID = :CASELOAD AND CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID AND LOCS.REMOVED_DATE IS NULL ) ) OR (VHB2.INTAKE_AGY_LOC_ID = 'CLOSE' AND EXISTS ( SELECT 'X' FROM OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 WHERE CAL1.CASELOAD_ID = :CASELOAD AND CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID ) ) ) ) AND V_HEADER_BLOCK.ROOT_OFFENDER_ID = :OFFENDERID
}
OCDBRECI_GET_AMOUNT {
	SELECT TRUNC(:AMOUNT,0) FROM DUAL
}
OCDBRECI_AMOUNT_IN_WORDS {
	SELECT TO_CHAR(TO_DATE(:AMT,'J'),'JSP') FROM DUAL
}
OCDBRECI_TXNFEE_AMOUNT {
SELECT SUM (TXN_ENTRY_AMOUNT) FROM OFFENDER_TRANSACTIONS OT WHERE OT.TXN_ID = :TXNID AND OT.TXN_ENTRY_SEQ > :TXNSEQ AND OT.TXN_ENTRY_SEQ < (SELECT COALESCE (MIN (OT1.TXN_ENTRY_SEQ), 9999999999) FROM OFFENDER_TRANSACTIONS OT1 WHERE OT1.TXN_ID = OT.TXN_ID AND OT1.TXN_ENTRY_SEQ > :TXNSEQ AND OT1.TXN_TYPE = :TXNTYPE) AND EXISTS (SELECT 1 FROM DEDUCTION_TYPES DT WHERE DT.DEDUCTION_CATEGORY = 'DTF' AND DT.DEDUCTION_TYPE = OT.DEDUCTION_TYPE)
}
OCDBRECI_OFFENDER_BENEFICIARIES_OWING_AMOUNT {
SELECT SUM(COALESCE(amount,0)) AMOUNT, SUM(COALESCE(received_amount,0)) RECEIVED_AMOUNT from offender_beneficiaries ob, offender_deductions od, deduction_types dt where ob.offender_id = :OFFENDERID and ob.offender_deduction_id = od.offender_deduction_id and od.deduction_type = dt.deduction_type and dt.deduction_category in ('FXOB', 'CROB')
}

OCDBRECI_GET_AMOUNT_DATA {
SELECT SUM (TXN_ENTRY_AMOUNT) - :CF_TXNFEE FROM OFFENDER_TRANSACTIONS OT WHERE OT.TXN_ID = :TXNID AND OT.TXN_ENTRY_SEQ > :TXNSEQ AND OT.TXN_ENTRY_SEQ < (SELECT COALESCE (MIN (OT1.TXN_ENTRY_SEQ), 9999999999) FROM OFFENDER_TRANSACTIONS OT1 WHERE OT1.TXN_ID = OT.TXN_ID AND OT1.TXN_ENTRY_SEQ > :TXNSEQ AND OT1.TXN_TYPE = :TXNTYPE) AND EXISTS (SELECT 1 FROM DEDUCTION_TYPES DT WHERE DT.DEDUCTION_TYPE = OT.DEDUCTION_TYPE)
}
OCDBRECI_REPORT_DATA {
 SELECT OD.OFFENDER_ID, OD.OFFENDER_DEDUCTION_ID, OD.MAX_TOTAL_AMOUNT, OD.MAX_MONTHLY_AMOUNT, OD.MAX_RECURSIVE_AMOUNT, OD.DEDUCTION_TYPE FROM OFFENDER_DEDUCTIONS OD WHERE OFFENDER_ID = :OFFENDERID
}
OCDBRECI_DED_FLAG {
SELECT 'Y' FROM deduction_types dt WHERE dt.deduction_type = :DEDTYPE AND dt.deduction_category IN ('FXOB', 'CROB')
}
OCDBRECI_EXIST_FLAG {
SELECT DISTINCT 'Y' FROM offender_beneficiaries WHERE offender_deduction_id = :DEDID
}
OCDBRECI_MONTHS_BETWEEN {
SELECT MONTHS_BETWEEN ( date_trunc ('month', LOCALTIMESTAMP), date_trunc ('month',effective_date) ) + 1 FROM offender_deductions WHERE offender_deduction_id = :DEDID
}

OCUACHIS_UPDATE_OFF_FEE_ACCOUNT_PROFILE_HTY{
  UPDATE OFF_FEE_ACCOUNT_PROFILE_HTY SET FEE_ACT_STATUS = :status , STATUS_EFFECTIVE_DATE = :statusEffectiveDate WHERE offender_fee_id = :offenderFeeId
}

SELECT_PROFILE_VALUE{
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_CODE = 'CF_DEDUCTION'
}

OCDBRECI_OFF_FEE_BILL_TRN_DATA{
SELECT
    A.OFFENDER_BOOK_ID,
    A.BOOKING_NO,
    A.BILL_GENERATE_DATETIME,
    A.CASELOAD_ID,
    A.OFFENDER_FEE_ID,
    A.FEE_CODE,
    OFBT.*
FROM
    (
        SELECT
            OFB.*,
            VHB.BOOKING_NO,
            OFAP.CASELOAD_ID,
            OFAP.FEE_CODE,
            OFAP.OFFENDER_BOOK_ID
        FROM
            OFF_FEE_BILLS           OFB,
            OFF_FEE_ACCOUNT_PROFILE OFAP,
            V_HEADER_BLOCK          VHB
        WHERE
                OFAP.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
            AND VHB.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
            AND OFAP.OFFENDER_FEE_ID = OFB.OFFENDER_FEE_ID
    )                         A,
    OFF_FEE_BILL_TRANSACTIONS OFBT
WHERE
        OFBT.BILL_ID = A.BILL_ID
    AND OFBT.BILL_TXN_NO = (
        SELECT
            MAX(B.BILL_TXN_NO)
        FROM
            OFF_FEE_BILL_TRANSACTIONS B
        WHERE
            B.BILL_ID = A.BILL_ID
    ) AND OFBT.BILL_STATUS != 'PAID'  AND OFBT.BILL_TXN_AMOUNT > 0 
ORDER BY
    A.CREATE_DATETIME ASC
}

OCDBRECI_LONGEST_SUPER_VISION_DATE{
SELECT LONGEST_SUPV_EXP_DATE FROM OFF_SUPERVISION_DETAILS WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDBRECI_OFF_FEE_BILL_TRANSACTIONS_UPDATE{
  UPDATE OFF_FEE_BILL_TRANSACTIONS SET BILL_TXN_AMOUNT = :balance WHERE BILL_ID = :billId AND BILL_TXN_NO = :billTxnNo
 }
 OCDBRECI_GET_CURRENT_STAFFDETAILS_STAFF_ID{
  SELECT STAFF_ID FROM   STAFF_MEMBERS  STAFF WHERE  USER_ID = :USER	
 }
OCDBRECI_OFF_FEE_BILLS_TRANSACTIONS_PRE_INSERT{
 select coalesce(max(BILL_TXN_NO),0)+1 from OFF_FEE_BILL_TRANSACTIONS where BILL_ID = :BILL_ID
}
OCDBRECI_OFF_BILL_TRN_INSERT {
INSERT INTO off_fee_bill_transactions (
        bill_id,
        bill_txn_no,
        bill_txn_datetime,
        bill_txn_staff_id,
        bill_txn_user,
        bill_txn_type,
        bill_txn_amount,
        trust_txn_id,
        trust_txn_entry_seq,
        off_adj_canc_rsn,
        off_adj_sub_rsn,
        off_adj_txn_id,
        off_adj_rev_rsn,
        bill_status,
        bill_aging_start_date,
        bill_aging_end_date,
        bill_txn_comment,
        original_bill_id,
        original_bill_txn_no,
        original_off_adj_txn_id,
        create_datetime,
        create_user_id,
        modify_datetime,
        modify_user_id,
        seal_flag
    ) VALUES (
        :billId,
        :billTxnNo,
         current_timestamp,
        :billTxnStaffId,
        :billTxnUser,
        :billTxnType,
        :billTxnAmount,
        :trustTxnId,
        :trustTxnEntrySeq,
        :offAdjCancRsn,
        :offAdjSubRsn,
        :offAdjTxnId,
        :offAdjRevRsn,
        :billStatus,
        :billAgingStartDate,
        :billAgingEndDate,
        :billTxnComment,
        :originalBillId,
        :originalBillTxnNo,
        :originalOffAdjTxnId,
        current_timestamp,
        :createUserId,
        current_timestamp,
        :modifyUserId,
        :sealFlag
    )
}

OCDBRECI_GET_SUB_ACCOUNT_DESC{
SELECT DESCRIPTION FROM TRANSACTION_TYPES WHERE TXN_TYPE = :txnType
}

OCDBRECI_CR_DEDUCT_TO{
select cdp.account_code from off_fee_account_profile ofap,caseload_deduction_profiles cdp where ofap.offender_fee_id = :offenderFeeId and 
cdp.deduction_type = ofap.fee_code and ofap.caseload_id = cdp.caseload_id
}

OCDBRECI_DR_ACCOUNT_CODE {
SELECT cr_account_code FROM TRANSACTION_OPERATIONS WHERE  MODULE_NAME = 'OCDBRECI' AND TXN_TYPE = :txnType AND CASELOAD_ID = :caseloadId
}

OCDBRECI_DOCKET_TYPE_RECORD_GROUP{
SELECT INFO_NUMBER  code , INFO_NUMBER  description   from OFF_FEE_ACCOUNT_PROFILE where offender_book_id =:OFFENDER_BOOK_ID and info_number is not  null
}

OCDBRECI_DOCKET_TYPE_RECORD_GROUP_COUNT{
SELECT count(*)  description   from OFF_FEE_ACCOUNT_PROFILE where offender_book_id =:OFFENDER_BOOK_ID and info_number is not  null
}

OCDBRECI_GET_TXN_ENTRYSEQ{
select  coalesce(MAX(TXN_ENTRY_SEQ),0)+1 from OFFENDER_TRANSACTIONS where txn_id=:txnId  --and OFFENDER_ID=:offenderId
}

OCDBRECI_GET_GL_ENTRY_SEQ_TXN{
SELECT  coalesce(MAX(GL_ENTRY_SEQ),0) FROM GL_TRANSACTIONS WHERE TXN_ID=:txnId  --and OFFENDER_ID=:offenderId
}

OCDBRECI_GET_PROD_FLAG_DETAILS{
SELECT coalesce (tno.cheque_production_flag, 'N') checkInd, coalesce  (tno.receipt_production_flag, 'N') receiptProductionFlag FROM transaction_types tt, transaction_operations tno
	WHERE tt.txn_type    = :txntype AND tt.txn_usage     = 'R' AND tt.txn_type  = tno.txn_type AND tt.caseload_type = 'COMM'
	AND tno.module_name  = 'OCDBRECI' AND tno.caseload_id  = :csldId
}
OCDBRECI_OFFENDER_FEE_ID{
  SELECT OFFENDER_FEE_ID FROM OFF_FEE_ACCOUNT_PROFILE WHERE OFFENDER_BOOK_ID =:offenderBookId AND FEE_CODE =:feeCode
 }
 
 OCDBRECI_GET_FEE_BILL_PREVIOUS_AND_CURRENT_BALANCE{
SELECT OFAP.FEE_CODE, OFAP.CASELOAD_ID, OFBT.*, ( SELECT O.BILL_TXN_AMOUNT FROM OFF_FEE_BILL_TRANSACTIONS O WHERE O.BILL_TXN_NO = ( OFBT.BILL_TXN_NO - 1 ) AND O.BILL_ID = OFBT.BILL_ID ) PREVIOUS_AMOUNT FROM OFF_FEE_BILL_TRANSACTIONS OFBT, OFF_FEE_ACCOUNT_PROFILE OFAP WHERE OFBT.TRUST_TXN_ID = :trustTxnId AND OFAP.OFFENDER_FEE_ID = (SELECT OFFENDER_FEE_ID FROM OFF_FEE_BILLS WHERE BILL_ID = OFBT.BILL_ID) AND OFAP.OFFENDER_BOOK_ID=:offenderBookId
}

GET_OLD_DATA_OFF_FEE_ACCOUNT_PROFILE{
SELECT * FROM OFF_FEE_ACCOUNT_PROFILE WHERE OFFENDER_BOOK_ID = :offenderBookId AND FEE_CODE = :feeCode AND CASELOAD_ID = :caseloadId;
}
