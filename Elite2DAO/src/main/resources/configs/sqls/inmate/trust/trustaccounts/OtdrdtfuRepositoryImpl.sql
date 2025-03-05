
OTDRDTFU_FIND_CGFKOFFTXNPAYEEPERSONID {
 	SELECT PER.PERSON_ID  PAYEE_PERSON_ID  /* CG$FK */        ,PER.LAST_NAME  DSP_LAST_NAME        ,PER.FIRST_NAME  DSP_FIRST_NAME FROM   PERSONS PER
}

OTDRDTFU_FIND_CGFKOFFTXNTXNTYPE {
 	SELECT TXN_TYPE.TXN_TYPE    CODE /* CG$FK */ ,        TXN_TYPE.DESCRIPTION DESCRIPTION , 			 TXN_TYPE.TXN_USAGE   FROM TRANSACTION_TYPES TXN_TYPE  WHERE TXN_TYPE IN (SELECT TXN_TYPE                       FROM TRANSACTION_OPERATIONS                      WHERE MODULE_NAME = 'OTDRDTFU'                        AND CASELOAD_ID =  :CASELOADID )    AND TXN_TYPE NOT IN        (SELECT CREDIT_OBLIGATION_TYPE           FROM TRANSACTION_TYPES          WHERE CREDIT_OBLIGATION_TYPE IS NOT NULL )   
 	AND CASELOAD_TYPE = (SELECT CASELOAD_TYPE   FROM CASELOADS   WHERE CASELOAD_ID =   (  SELECT WORKING_CASELOAD_ID      FROM STAFF_MEMBERS        WHERE USER_ID = :USER_NAME))   ORDER BY CODE
}

OTDRDTFU_FIND_CGFKOFFTXNPAYEECORPORATEI {
 	SELECT CORP.CORPORATE_ID  PAYEE_CORPORATE_ID  /* CG$FK */        ,CORP.CORPORATE_NAME  DSP_CORPORATE_NAME FROM (SELECT CRP.CORPORATE_ID , CRP.CORPORATE_NAME        FROM CORPORATES CRP , CORPORATE_TYPES CTP       WHERE CRP.CORPORATE_ID = CTP.CORPORATE_ID        AND CTP.CORPORATE_TYPE NOT IN ('VENDOR' ,'PROGRAM' )       UNION       SELECT CORPORATE_ID ,CORPORATE_NAME       FROM CORPORATES CRP2       WHERE NOT EXISTS ( SELECT 1 FROM CORPORATE_TYPES CTP2 WHERE CRP2.CORPORATE_ID = CTP2.CORPORATE_ID  )                      ) CORP
}

OTDRDTFU_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OTDRDTFU_OFF_BKG_POSTQUERYPOST-QUERY {
select OFFENDER_BOOK_ID from OFFENDER_BOOKINGS where ROOT_OFFENDER_ID = :V_ROOT_OFFENDER_ID and OFFENDER_BOOK_ID = (select max (OFFENDER_BOOK_ID) from OFFENDER_BOOKINGS OB2 where OB2.ROOT_OFFENDER_ID = ROOT_OFFENDER_ID );
}

OTDRDTFU_OFF_BKG_POSTQUERYPOST-QUERY {
	SELECT IMPRISONMENT_STATUS FROM OFFENDER_IMPRISON_STATUSES WHERE OFFENDER_BOOK_ID = V_OFFENDER_BOOK_ID
}

OTDRDTFU_CGFKCHK_OFF_TXN_OFF_TXN_PER_F_ {
	SELECT PER.LAST_NAME ,PER.FIRST_NAME FROM   PERSONS PER WHERE  PER.PERSON_ID = :PAYEEPERSONID
}

OTDRDTFU_CGFKCHK_OFF_TXN_OFF_TXN_CORP__ {
	SELECT CORP.CORPORATE_NAME FROM   CORPORATES CORP WHERE  CORP.CORPORATE_ID = :PAYEECORPORATEID
}

OTDRDTFU_CGFKCHK_OFF_TXN_OFF_TXN_TXN_T_ {
	SELECT TXN_TYPE.DESCRIPTION FROM   TRANSACTION_TYPES TXN_TYPE WHERE  TXN_TYPE.TXN_TYPE = :TXNTYPE AND     TXN_TYPE IN (SELECT TXN_TYPE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OTDRDTFU' AND   CASELOAD_ID =:CASELOADID ) AND TXN_TYPE NOT IN (SELECT CREDIT_OBLIGATION_TYPE FROM TRANSACTION_TYPES WHERE CREDIT_OBLIGATION_TYPE IS NOT NULL) AND     CASELOAD_TYPE = 'INST'
}


OTDRDTFU_MAIN_PROCESS_ {
	SELECT 'Y' FROM OFFENDER_TRANSACTIONS OT WHERE OT.TXN_ID = P_TXN_ID
}

OTDRDTFU_MAIN_PROCESS_ {
	SELECT COUNT(1) FROM OFFENDER_TRANSACTIONS OT WHERE OT.TXN_ID = P_TXN_ID AND OT.TXN_ENTRY_SEQ = P_TXN_ENTRY_SEQ
}

OTDRDTFU_MAIN_PROCESS_ {
	IS SELECT 'X' FROM TRANSACTION_TYPES WHERE TXN_TYPE = L_TXN_TYPE AND CASELOAD_TYPE = LV_CASELOAD_TYPE AND CREDIT_OBLIGATION_TYPE IS NOT NULL
}

OTDRDTFU_MAIN_PROCESS_DAYS_CUR {
	SELECT coalesce(DAYS,0) FROM   TRANSACTION_TYPES TT WHERE  TT.TXN_TYPE    = :txnType
}

OTDRDTFU_MAIN_PROCESS_ {
	--SELECT CORPORATE_NAME -- COMMENTED BY MANJUL, ISSUE#18616 SELECT CORPORATE_NAME, ACTIVE_FLAG  -- ADDED BY MANJUL, ISSUE#18616 FROM CORPORATES WHERE CORPORATE_ID = LV_CORP_ID
}

OTDRDTFU_MAIN_PROCESS_ {
	SELECT FIRST_NAME || '  ' || LAST_NAME FROM PERSONS WHERE PERSON_ID = LV_PERSON_ID
}

OTDRDTFU_MAIN_PROCESSl_txn_id_cur(p_txn_id offender_transactions.txn_id%type) {
	select PROFILE_VALUE from SYSTEM_PROFILES where PROFILE_TYPE = 'CLIENT' and PROFILE_CODE = 'AUTO_SUBMIT'
}

OTDRDTFU_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

OTDRDTFU_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'
}

OTDRDTFU_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'REPORT_ALIAS'
}

OTDRDTFU_RUN_REPORT_ {
	SELECT LTRIM(RTRIM(DESCRIPTION)) FROM SYSTEM_PROFILES WHERE PROFILE_CODE = P_PROFILE_CODE AND PROFILE_TYPE = P_PROFILE_TYPE
}
OTDRDTFU_TXN_TYPE_VALIDATION {
SELECT TT.TXN_USAGE TXN_USAGE, coalesce (TNO.CHEQUE_PRODUCTION_FLAG, 'N') CHEQUE_PRODUCTION_FLAG, coalesce (TNO.RECEIPT_PRODUCTION_FLAG, 'N') RECEIPT_PRODUCTION_FLAG, coalesce (TNO.CHEQUE_PAYEE_TYPE, 'N')CHEQUE_PAYEE_TYPE FROM TRANSACTION_TYPES TT, TRANSACTION_OPERATIONS TNO WHERE TT.TXN_TYPE = :TXN_TYPE AND TT.TXN_TYPE = TNO.TXN_TYPE AND TNO.MODULE_NAME = 'OTDRDTFU' AND TT.CASELOAD_TYPE = (SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID = ( SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_NAME)) AND TNO.CASELOAD_ID = :CASELOAD_ID
}
OTDRDTFU_CASELOAD_VALIDATION {
 SELECT 1 FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID = :CASELOAD_ID AND AGY_LOC_ID = :AGY_LOC_ID
}
OTDRDTFU_MODLIB_VALIDATION_H_WHEN_VALIDATE_ITEM_2_SYSTEM_PROFILE_C {
SELECT coalesce(profile_value,'N') profile_value FROM system_profiles WHERE profile_type = 'CLIENT' AND profile_code = 'ZERO_RECEIPT'
}
OTDRDTFU_MODLIB_VALIDATION_H_WHEN_VALIDATE_ITEM_2_GET_TXN_FEE_TYPE {
SELECT deduction_type, offender_deduction_id FROM offender_deductions od WHERE offender_id = :offenderId AND caseload_id = :caseloadId AND EXISTS (SELECT '1' FROM offender_deduction_receipts odr WHERE receipt_txn_type = :transType AND od.offender_deduction_id = odr.offender_deduction_id) AND deduction_type IN (SELECT deduction_type FROM deduction_types WHERE deduction_category = 'DTF' AND caseload_code IN ('BOTH', 'INST')) 
}
OTDRDTFU_OFF_CREDIT_LIMIT_TRANSACTION_TYPES_C {
	SELECT txn_usage CODE, credit_obligation_type DESCRIPTION FROM transaction_types WHERE txn_type = :txnType AND caseload_type IN ('BOTH', 'INST')
}
OTDRDTFU_OFF_CREDIT_LIMIT_AMOUNT_WRITTEN_OFF_C {
SELECT TO_CHAR(coalesce (SUM (coalesce (adjustment_amount, 0)), 0)) FROM offender_deductions WHERE caseload_id = :caseloadid AND offender_id = :offenderid AND effective_date BETWEEN :fromdate AND :todate AND deduction_type = :obligationtype          
}
OTDRDTFU_GET_DEBIT_ACT_CODE {
SELECT dr_account_code FROM transaction_operations WHERE module_name = 'OTDRDTFU' AND txn_type = :txnType AND caseload_id = :csldId
}
OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_CROB {
SELECT 'Y' FROM deduction_types WHERE deduction_type = :obligationtype AND deduction_category = 'CROB' AND caseload_code IN ('BOTH', 'INST')
}

OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_INDIGENTFLAG {
SELECT COALESCE (co_credit_when_indigent_flag, 'N') FROM caseload_deduction_profiles WHERE caseload_id = :caseloadid AND deduction_type = :obligationtype
}
OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_CTR_WASH_SPECIFIC {
SELECT COUNT (*) FROM system_profiles WHERE profile_type = 'CLIENT' AND profile_code = 'WASHINGTON' AND profile_value = 'Y' 
}
OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_MAX_LIMIT_PERIODTYPE {
SELECT to_char(limit_amount) CODE, NVL (period_type, 'WEEK') DESCRIPTION FROM offender_limits WHERE caseload_id = :caseloadid AND offender_id = :offenderid AND limit_type = :obligationtype
}
OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_MAX_LIMIT_PERIODTYPE_WITHOUT_OFFENDER_ID {
SELECT to_char(limit_amount) CODE, COALESCE (period_type, 'WEEK') DESCRIPTION FROM offender_limits WHERE caseload_id = :caseloadid AND offender_id = :offenderid AND limit_type = :obligationtype
}
OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_WEEKDAY {
SELECT TO_CHAR (SYSDATE(), 'D') FROM dual
}
OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_FROMDATE {
SELECT current_date - (:weekday - 1) FROM dual
}
OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_TODATE {
SELECT current_date + (7 - :weekday) FROM dual
}
OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_FROMDATE_ON_MONTH {
select (date_trunc('MONTH', CURRENT_TIMESTAMP) + interval ' - 1 month')::date + interval '1 month'
}
OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_TODATE_ON_MONTH {
SELECT LAST_DAY (SYSDATE()) FROM dual
 }
OTDRDTFU_GET_PREVIOUS_LOAN_AMOUNT_TOTAL_TAKEN_C {
 SELECT coalesce (SUM (coalesce (txn_entry_amount, 0)), 0) FROM offender_transactions WHERE offender_id = :pOffenderId AND deduction_type = :pDedType AND txn_type = :pDedType AND txn_posting_type = 'CR' AND caseload_id = :caseloadid AND txn_entry_date BETWEEN :pFromDate AND :pToDate
}
OTDRDTFU_GET_PREVIOUS_LOAN_AMOUNT_TOTAL_REVERSED_C {
SELECT coalesce (SUM (coalesce (txn_entry_amount, 0)), 0) FROM offender_transactions WHERE offender_id = :pOffenderId AND deduction_type = :pDedType AND txn_type = :pDedType AND txn_posting_type = 'DR' AND caseload_id = :caseloadid AND txn_entry_date BETWEEN :pFromDate AND :pToDate AND (adjust_txn_id, adjust_txn_entry_id) IN (SELECT txn_id, txn_entry_seq FROM offender_transactions WHERE offender_id = :pOffenderId AND deduction_type = :pDedType AND txn_type = :pDedType AND txn_posting_type = 'CR' AND caseload_id = :caseloadid AND txn_entry_date BETWEEN :pFromDate AND :pToDate)
}
OTDRDTFU_REOPEN_OFFENER_TRUST_ACCOUNT {
UPDATE offender_trust_accounts SET account_closed_flag = 'N', MODIFY_DATETIME = CURRENT_TIMESTAMP ,MODIFY_USER_ID = :modifyUserId WHERE offender_id = :offenderId AND caseload_id = :caseloadId
}

OTDRDTFU_GENERATE_OTRDRECE_REPORT {
SELECT ref.domain, sp.description client, cl.description cl_desc, ot.txn_entry_date txn_date, ot.receipt_number rec_num, ot.remitter_name remitter, ot.txn_entry_amount amt, va.last_name || ', ' || va.first_name || ' ' || va.middle_name off_name, va.offender_id_display off_id, ot.txn_entry_desc txn_desc, ot.modify_user_id user_id, ot.payee_name_text payee_name, bcr.cheque_number check_num, lu.description location1, ob.booking_no book_id FROM system_profiles sp, caseloads cl, offender_transactions ot, reference_domains ref, bank_cheque_registers bcr, offenders va, offender_bookings ob, living_units lu, transaction_types tt 
}

OTDRDTFU_GENERATE_OTRRECEI_REPORT {
SELECT ot.txn_entry_date txn_date, ot.receipt_number rec_num, ot.transfer_caseload_id t_csld, ot.txn_entry_amount amt, o.last_name || ', ' || o.first_name || ' ' || o.middle_name off_name, ot.offender_id off_id, ot.txn_entry_desc txn_desc, ot.modify_user_id user_id, ot.payee_name_text payee_name, bcr.cheque_number check_num, lu.description location, ob.offender_book_id book_id, o.offender_id_display off_id_display, ot.remitter_id remitter_id, ot.remitter_name remitter_name, ot.txn_id, ot.txn_posting_type, to_char(ac.account_code) account_code, rc.description account_name, sp.description client, c.description cldesc FROM bank_cheque_registers bcr, agency_internal_locations lu, offender_bookings ob, offenders o, offender_transactions ot, account_codes ac, reference_codes rc, system_profiles sp, caseloads c
}

OTDRDTFU_MAIN_PROCESS_GET_CROP {
SELECT CORPORATE_NAME, ACTIVE_FLAG FROM CORPORATES  WHERE CORPORATE_ID = :corpId
}

OTDRDTFU_MAIN_PROCESS_GET_PERSON {
SELECT FIRST_NAME || '  ' || LAST_NAME LAST_NAME FROM PERSONS WHERE PERSON_ID = :personId
}
OTDRDTFU_MAIN_PROCESS_TXN_ID_CUR {
SELECT 'Y' FROM offender_transactions ot WHERE ot.txn_id = :txnId
}
OTDRDTFU_GEN_TRUST_TRANS {
SELECT nextval(:seq) from dual
 }
OTDRDTFU_CREDIT_OBLIG_EXISTS_C {
SELECT 'X' FROM transaction_types WHERE txn_type = :txnType AND caseload_type = :caseloadType AND credit_obligation_type IS NOT NULL
}
 OTDRDTFU_TXN_SEQ_CUR {
 SELECT COUNT(1) FROM offender_transactions ot WHERE ot.txn_id = :txnId AND ot.txn_entry_seq = :txnEntrySeq
 }
 OTDRDTFU_OFFENDER_TRANSACTIONS_COMMIT {
insert into offender_transactions (txn_id, txn_entry_seq, caseload_id, offender_id, offender_book_id, txn_posting_type, txn_type, txn_entry_desc, txn_entry_amount, txn_entry_date, sub_account_type, txn_reference_number, modify_date, receipt_number, slip_printed_flag, transfer_caseload_id, receipt_printed_flag, pre_withhold_amount, deduction_flag, closing_cheque_number, remitter_name, payee_code, payee_name_text, payee_corporate_id, payee_person_id, adjust_txn_id, adjust_txn_entry_id, adjust_offender_id, adjust_account_code, txn_adjusted_flag, deduction_type, info_number, hold_clear_flag, hold_until_date, hold_number, gross_amount, gross_net_flag, remitter_id, apply_spending_limit_amount, receipt_pending_print_flag, create_datetime, create_user_id, modify_datetime) values(:txnId, :txnEntrySeq, :caseloadId, :offenderId, :offenderBookId, :txnPostingType, :txnType, :txnEntryDesc, :txnEntryAmount, current_date, :subAccountType, :txnReferenceNumber, :modifyDate, :receiptNumber, 'N', '', '', :txnEntryAmount, :deductionFlag, '', :remitterName, '', :payeeNameText, :payeeCorporateId, :payeePersonId, :adjustTxnId, 99, null, null, 'Y', '', '', 'Y', :holdUntilDate, null, null, '', :remitterId, null, '', current_timestamp, :createUserId, current_timestamp)
 }
OTDRDTFU_SUB_ACT_TYPE_D {--not using
SELECT sub_account_type DESCRIPTION, 'DR' CODE FROM account_codes WHERE account_code = (SELECT dr_account_code FROM transaction_operations WHERE module_name = 'OTDRDTFU' AND txn_type = :txnType AND caseload_id = (select CASELOAD_ID from caseloads where caseload_id = ( select working_caseload_id from staff_members where user_id = USER))) AND caseload_type = (select CASELOAD_TYPE from caseloads where caseload_id = ( select working_caseload_id from staff_members where user_id = USER)) 
}
OTDRDTFU_SUB_ACT_TYPE_R {--not using
SELECT sub_account_type DESCRIPTION, 'CR' CODE FROM account_codes WHERE account_code = (SELECT cr_account_code FROM transaction_operations WHERE module_name = 'OTDRDTFU' AND txn_type = :txnType AND caseload_id = (select CASELOAD_ID from caseloads where caseload_id = ( select working_caseload_id from staff_members where user_id = USER))) AND caseload_type = (select CASELOAD_TYPE from caseloads where caseload_id = ( select working_caseload_id from staff_members where user_id = USER)) 
}
OTDRDTFU_GET_R_TXN_TYPE {
 SELECT txn_type FROM transaction_operations WHERE module_name = :moduleName AND caseload_id = :caseloadId
}
OTDRDTFU_PROCESS_GL_TRANS_NEW_POSTING_C {
SELECT a.dr_account_code, b.txn_posting_type a_txn_posting_type, coalesce(b.posting_status_flag, 'N'), a.cr_account_code, c.txn_posting_type b_txn_posting_type, coalesce(c.posting_status_flag, 'N'), a.bank_dr_account_code, d.txn_posting_type c_txn_posting_type, coalesce(d.posting_status_flag, 'N'), a.bank_cr_account_code, e.txn_posting_type d_txn_posting_type, coalesce(e.posting_status_flag, 'N'), a.txn_operation_seq FROM transaction_operations a INNER JOIN account_codes b ON a.dr_account_code = b.account_code INNER JOIN account_codes c ON a.cr_account_code = c.account_code AND c.caseload_type = b.caseload_type LEFT JOIN account_codes d ON a.bank_dr_account_code = d.account_code LEFT JOIN account_codes e ON a.bank_cr_account_code = e.account_code WHERE ( NULL IS NULL OR a.dr_account_code IN ( SELECT account_codes.account_code FROM account_codes WHERE account_codes.sub_account_type = NULL AND account_codes.sub_account_type IS NOT NULL AND account_codes.caseload_type = ( SELECT caseloads.caseload_type FROM caseloads WHERE caseloads.caseload_id = ( SELECT staff_members.working_caseload_id FROM staff_members WHERE staff_members.user_id = :USER_NAME ) ) ) ) AND ( :subaccounttype IS NULL OR a.cr_account_code IN ( SELECT account_codes.account_code FROM account_codes WHERE account_codes.sub_account_type = :subaccounttype AND account_codes.sub_account_type IS NOT NULL AND account_codes.caseload_type = ( SELECT caseloads.caseload_type FROM caseloads WHERE caseloads.caseload_id = ( SELECT staff_members.working_caseload_id FROM staff_members WHERE staff_members.user_id = :USER_NAME ) ) ) ) AND a.txn_type = :txntype AND a.module_name = :modelname AND coalesce(a.txn_operation_type, '0') = coalesce(:txnoperationtype, '0') AND b.caseload_type = ( SELECT caseloads.caseload_type FROM caseloads WHERE caseloads.caseload_id = ( SELECT staff_members.working_caseload_id FROM staff_members WHERE staff_members.user_id = :USER_NAME ) ) AND a.caseload_id = ( SELECT caseloads.caseload_id FROM caseloads WHERE caseloads.caseload_id = ( SELECT staff_members.working_caseload_id FROM staff_members WHERE staff_members.user_id = :USER_NAME ) ) ORDER BY a.txn_operation_seq 
}

OTDRDTFU_GET_AC_AND_SET_IND_DATE_CHK_CASELOAD_C {
	SELECT 'X' FROM institution_mini_balances WHERE caseload_id = :caseloadId
}
OTDRDTFU_GET_AC_AND_SET_IND_DATE_CHK_IND_AC_C {
 SELECT trust_account_code FROM offender_sub_accounts WHERE offender_id = :offenderId AND caseload_id = (select CASELOAD_ID from caseloads where caseload_id = ( select working_caseload_id from staff_members where user_id = :createUserId)) AND trust_account_code IN (SELECT account_code FROM institution_mini_balances WHERE caseload_id = (select CASELOAD_ID from caseloads where caseload_id = ( select working_caseload_id from staff_members where user_id = :createUserId))) AND trust_account_code IN (SELECT account_code FROM account_codes WHERE caseload_type = (select CASELOAD_TYPE from caseloads where caseload_id = ( select working_caseload_id from staff_members where user_id = :createUserId))) UNION SELECT trust_account_code FROM offender_sub_accounts WHERE offender_id = 1020010 AND caseload_id = (select CASELOAD_ID from caseloads where caseload_id = ( select working_caseload_id from staff_members where user_id = :createUserId)) AND minimum_balance IS NOT NULL AND trust_account_code IN (SELECT account_code FROM account_codes WHERE caseload_type = (select CASELOAD_TYPE from caseloads where caseload_id = ( select working_caseload_id from staff_members where user_id = :createUserId))) 
}
OTDRDTFU_GET_AC_AND_SET_IND_DATE_SYSTEM_PROFILE_C {
	SELECT profile_value, profile_value_2  FROM system_profiles   WHERE profile_type = 'CLIENT' AND profile_code = 'IND_CON'
}

OTDRDTFU_GET_AC_AND_SET_IND_DATE_GET_TRUST_ACCOUNT_CODE_C {
	SELECT account_code FROM account_codes WHERE sub_account_type = 'REG' AND CASELOAD_TYPE = 'INST'
}
OTDRDTFU_GET_AC_AND_SET_IND_DATE_SUM_OFF_SUB_ACT_BAL_C {
 SELECT SUM(osa.balance) FROM offender_sub_accounts osa WHERE offender_id = :offenderId AND trust_account_code = :lvTrustAccountCode AND NOT EXISTS (SELECT DISTINCT caseload_id FROM institution_mini_balances imb WHERE imb.caseload_id = osa.caseload_id)
}
OTDRDTFU_GET_AC_AND_SET_IND_DATE_MAX_IND_DATE_C {
 SELECT MAX(ind_date) FROM offender_sub_accounts osa WHERE offender_id = :offenderId AND trust_account_code = :trustAccountCode AND NOT EXISTS (SELECT DISTINCT caseload_id FROM institution_mini_balances imb WHERE imb.caseload_id = osa.caseload_id)
}
OTDRDTFU_GET_AC_AND_SET_IND_UPDATE_OFFENDER_SUB_ACCOUNTS {
UPDATE offender_sub_accounts osa SET ind_date = :indDate, ind_days = :indDays, MODIFY_DATETIME = CURRENT_TIMESTAMP ,MODIFY_USER_ID = :modifyUserId WHERE offender_id = :offId AND trust_account_code = :trustAccountcode AND NOT EXISTS (SELECT DISTINCT caseload_id FROM institution_mini_balances imb WHERE imb.caseload_id = osa.caseload_id) 
}
OTDRDTFU_MAIN_PROCESS_AUTO_SUBMITTING {
 SELECT profile_value FROM system_profiles WHERE profile_type = 'CLIENT' AND profile_code = 'AUTO_SUBMIT'
}
OTDRDTFU_REPORT_RECIEVED_UPDATE {
 UPDATE offender_transactions SET receipt_printed_flag = 'Y', MODIFY_DATETIME = CURRENT_TIMESTAMP ,MODIFY_USER_ID = :modifyUserId WHERE txn_id = :txnId AND receipt_number = :receiptNumber
}
OTDRDTFU_NUMBER_TO_WORD {
select case when AMT > 0 and CENTS > 0 then to_text(AMT) || ' DOLLAR(S) AND ' || TO_text(CENTS) || ' CENT(S) ONLY' when AMT > 0 then to_text(AMT) || ' DOLLAR(S) ONLY' when CENTS > 0 then TO_text(CENTS) || ' CENT(S) ONLY' else 'ZERO' end DOLLERS from ( select ((:amount - AMT) * 100)::int CENTS, AMT from ( select TRUNC(:amount, 0)::int AMT from dual)A)A
}
OTDRDTFU_TRUST_PROCESS_GL_TRANS_NEW {
CALL OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC, :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT, :P_GL_SQNC, :P_OFF_DED_ID)
}
OTDRDTFU_OMS_OWNER_PROCESS_HOLD {
CALL OMS_OWNER.PROCESS_HOLD(:P_TXN_ID, :P_CSLD_ID, :P_OFF_ID, :P_ORG_TXN_TYPE, :P_HOLD_DAYS, :P_SUB_ACCOUNT_TYPE, :P_TOT_AMT, :P_TXN_DESC, :P_TXN_REF_NUM, :P_TXN_NUM, :P_HOLD_NUMBER)
}
OTDRDTFU_OMS_OWNER_FINANCIAL_DO_DEDUCTIONS_FINANCIAL {
CALL OMS_OWNER.FINANCIAL.DO_DEDUCTIONS_FINANCIAL(:P_CSLD_ID, :P_OFF_ID, :P_OFF_BOOK_ID, :P_TRANS_TYPE, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_SUB_ACT_TYPE, :P_DED_FLAG, :P_RECEIPT_AMOUNT, :P_SHADOW_ID, :P_DED_AMOUNT, :TXN_SEQUENCE, :P_INFO_NUMBER)
}
OTDRDTFU_DEDUCTIONS_GET_AC_AND_SET_IND_DATE {
CALL OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:P_OFF_ID, :P_CSLD_ID)
}
OTDRDTFU_GET_CURRENCY_SYMBOL {
	SELECT UPPER(PROFILE_VALUE) FROM   SYSTEM_PROFILES WHERE  PROFILE_TYPE = 'DISPLAY' AND PROFILE_CODE = 'CURRENCY'
}