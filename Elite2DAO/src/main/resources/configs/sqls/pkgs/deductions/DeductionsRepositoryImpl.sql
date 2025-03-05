CHK_OFFENDER_DEDUCTIONS_CHK_DED_CUR {
select OD.OFFENDER_DEDUCTION_ID from deduction_types dt, caseload_deduction_profiles cdp, offender_deductions od left outer join offender_deduction_shadows ods on (OD.OFFENDER_DEDUCTION_ID = ODS.OFFENDER_DEDUCTION_ID and :P_SHADOW_ID = ODS.SHADOW_ID) left outer join offender_mon_deductions omd on (OD.OFFENDER_DEDUCTION_ID = OMD.OFFENDER_DEDUCTION_ID) where OD.OFFENDER_ID = :P_OFF_ID and OD.DEDUCTION_TYPE = DT.DEDUCTION_TYPE and DT.CASELOAD_CODE in ('BOTH', :CSLD_TYPE) and OD.DEDUCTION_STATUS = 'A' and exists ( select 1 from OFFENDER_BENEFICIARIES OB where OB.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID and OB.OFFENDER_ID = OD.OFFENDER_ID) and (coalesce(OD.DEDUCTION_AMOUNT, 0) + coalesce(OD.ADJUSTMENT_AMOUNT, 0) < coalesce(OD.MAX_TOTAL_AMOUNT, 0) or coalesce(OD.MAX_TOTAL_AMOUNT::text, '') = '') and date_trunc('day', OD.EFFECTIVE_DATE) <= date_trunc('day', CURRENT_TIMESTAMP) and OD.CASELOAD_ID = CDP.CASELOAD_ID and CDP.DEDUCTION_TYPE = OD.DEDUCTION_TYPE and ((DT.DEDUCTION_CATEGORY <> 'ALCN' and ((coalesce(OD.MAX_TOTAL_AMOUNT, 0) > coalesce(OD.DEDUCTION_AMOUNT, 0) and (OD.MAX_TOTAL_AMOUNT is not null and OD.MAX_TOTAL_AMOUNT::text <> '')) or (coalesce(OD.MAX_MONTHLY_AMOUNT, 0) > coalesce(OMD.DEDUCTION_AMOUNT, 0) and (OD.MAX_MONTHLY_AMOUNT is not null and OD.MAX_MONTHLY_AMOUNT::text <> '')) or (OD.MAX_RECURSIVE_AMOUNT * (MONTHS_BETWEEN(date_trunc('month', CURRENT_TIMESTAMP)::timestamp, date_trunc('month', OD.EFFECTIVE_DATE)::timestamp) + 1) > coalesce(OD.DEDUCTION_AMOUNT, 0) and (OD.MAX_RECURSIVE_AMOUNT is not null and OD.MAX_RECURSIVE_AMOUNT::text <> '')) or (coalesce(OD.MAX_TOTAL_AMOUNT::text, '') = '' and coalesce(OD.MAX_MONTHLY_AMOUNT::text, '') = '' and coalesce(OD.MAX_RECURSIVE_AMOUNT::text, '') = '') )) or (DT.DEDUCTION_CATEGORY = 'ALCN' and (coalesce(OD.MAX_TOTAL_AMOUNT, 0) > ( select coalesce(OSA.BALANCE, 0) from OFFENDER_SUB_ACCOUNTS OSA, ACCOUNT_CODES AC where OSA.TRUST_ACCOUNT_CODE = AC.ACCOUNT_CODE and AC.ACCOUNT_CODE = CDP.ACCOUNT_CODE and OSA.OFFENDER_ID = :P_OFF_ID and OSA.CASELOAD_ID = CDP.CASELOAD_ID and AC.CASELOAD_TYPE = :CSLD_TYPE ) or coalesce(OD.MAX_MONTHLY_AMOUNT, 0) > coalesce(OMD.DEDUCTION_AMOUNT, 0)))) and (((OD.MAX_MONTHLY_AMOUNT is not null and OD.MAX_MONTHLY_AMOUNT::text <> '') and (OMD.MONTHLY_DEDUCTION_DATE = ( select MAX(OMD4.MONTHLY_DEDUCTION_DATE) from OFFENDER_MON_DEDUCTIONS OMD4 where OMD4.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) or not exists ( select '1' from OFFENDER_MON_DEDUCTIONS OMD4 where OMD4.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID))) or (coalesce(OD.MAX_MONTHLY_AMOUNT::text, '') = '' and coalesce(OD.MAX_RECURSIVE_AMOUNT::text, '') = '') or ((OD.MAX_RECURSIVE_AMOUNT is not null and OD.MAX_RECURSIVE_AMOUNT::text <> '') and (not exists ( select '1' from OFFENDER_MON_DEDUCTIONS OMD1 where OMD1.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) or exists ( select '1' from OFFENDER_MON_DEDUCTIONS OMD1 where OMD1.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID and OMD1.MONTHLY_DEDUCTION_DATE = ( select MAX(OMD2.MONTHLY_DEDUCTION_DATE) from OFFENDER_MON_DEDUCTIONS OMD2 where OMD2.OFFENDER_DEDUCTION_ID = OMD1.OFFENDER_DEDUCTION_ID)) and OMD.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID and OMD.MONTHLY_DEDUCTION_DATE = ( select MAX(OMD3.MONTHLY_DEDUCTION_DATE) from OFFENDER_MON_DEDUCTIONS OMD3 where OMD3.OFFENDER_DEDUCTION_ID = OMD.OFFENDER_DEDUCTION_ID))))
}

CHK_OFFENDER_DEDUCTIONS_CHK_RECEIPT_TYPE {
SELECT COUNT(*) FROM OFFENDER_DEDUCTION_RECEIPTS WHERE OFFENDER_DEDUCTION_ID = :DED_ID AND RECEIPT_TXN_TYPE = :P_TRANS_TYPE
}

GET_MIN_SUB_IND_VALUES{
SELECT coalesce(balance, 0) BALANCE, minimum_balance, ind_date FROM offender_sub_accounts WHERE caseload_id = :p_csld_id AND offender_id = :p_off_id AND trust_account_code = :p_acnt_code
}

GET_MIN_BALANCE{
SELECT coalesce(minimum_account_balance, 0) FROM institution_mini_balances WHERE caseload_id = :p_csld_id AND agy_loc_id = ( SELECT agy_loc_id FROM offender_bookings o1 WHERE o1.offender_book_id = ( SELECT MAX(o2.offender_book_id) FROM offender_bookings o2 WHERE o2.root_offender_id = :p_off_id AND booking_type = 'INST' ) ) AND account_code = :p_acnt_code   
}

GET_MIN_BALANCE_ONE{
SELECT coalesce(minimum_account_balance, 0) FROM institution_mini_balances WHERE caseload_id = :p_csld_id AND agy_loc_id = 'OUT' AND account_code = :p_acnt_code
}

UPDATE_OFFENDER_SUB_ACCOUNTS_IND{
update offender_sub_accounts set ind_date = null, MODIFY_USER_ID = :MODIFYUSERID , MODIFY_DATETIME = CURRENT_TIMESTAMP where caseload_id = :p_csld_id and offender_id = :p_off_id and trust_account_code = :p_acnt_code
}

UPDATE_OFFENDER_SUB_ACCOUNTS_ONE_ACC{
update offender_sub_accounts set ind_date = current_timestamp, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where caseload_id = :p_csld_id and offender_id = :p_off_id and trust_account_code = :p_acnt_code
}

GET_C_COND_DED_CURSOR{
SELECT cdp.deduction_type, cdp.caseload_id, cdp.effective_date, cdp.max_monthly_amount, cdp.max_total_amount, cdp.max_recursive_amount, cdp.co_limit_amount, cdp.fifo_flag, cdp.payee_person_id, case coalesce(dt.increment_payables_flag, 'N') || to_char(cdp.payee_person_id) || to_char(cdp.payee_corporate_id)  when 'Y' then  :corporateid  else cdp.payee_corporate_id end corporate FROM caseload_deduction_profiles cdp, deduction_types dt WHERE cdp.comm_condition_type = :pconditiontype AND cdp.comm_condition_code = :pconditioncode AND cdp.category_type = :pconcategorytype AND cdp.deduction_type = dt.deduction_type AND dt.caseload_code IN ( 'BOTH', :csldtype ) AND dt.active_flag = 'Y' AND cdp.caseload_id = :pcsldid AND current_timestamp BETWEEN cdp.effective_date AND coalesce(cdp.expiry_date, current_timestamp);

}

GET_UNI_UI2_SELECT{
SELECT COUNT(*) FROM offender_deductions WHERE caseload_id = :caseloadId AND offender_id = :pOffId AND deduction_type = :deductionType AND information_number = :pInfoNumber
}

GET_VDED_ID_SELECT{
select NEXTVAL('deduction_id')
}

GET_V_ODP_SELECT{
SELECT coalesce(MAX(deduction_priority), 0) + 1 FROM offender_deductions WHERE offender_id = :pOffId AND deduction_type = :deductionType
}

GET_LV_GROUP_ID_SELECT{
SELECT go.group_id FROM grouped_obligations go, obligation_groups og WHERE go.group_id = og.group_id AND go.deduction_type = :deductionType
}

INSERT_OFFENDER_DEDUCTIONS{
insert into offender_deductions (offender_deduction_id , offender_id , deduction_type , information_number , caseload_id , deduction_priority , deduction_status , effective_date , modify_date , max_monthly_amount , max_total_amount , max_recursive_amount , credit_limit , deduction_amount , fifo_flag , payee_person_id , payee_corporate_id , group_id, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME) values ( :offenderDeductionId, :offenderId, :deductionType, :informationNumber, :caseloadId, :deductionPriority, :deductionStatus, :effectiveDate, CURRENT_TIMESTAMP, :maxMonthlyAmount, :maxTotalAmount, :maxRecursiveAmount, :creditLimit, :deductionAmount, :fifoFlag, :payeePersonId, :payeeCorporateId, :groupId, :createUserId, CURRENT_TIMESTAMP , null)
}

INSERT_OFFENDER_BENEFICIARIES{
insert into offender_beneficiaries ( beneficiary_id, offender_deduction_id, offender_id, person_id, corporate_id, unknown_ben_id, priority, amount, percent, override_amount, received_amount, comment_text, modify_datetime, CREATE_USER_ID, CREATE_DATETIME) select beneficiary_id.NEXTVAL, od.offender_deduction_id, :p_off_id, cdb.person_id, cdb.corporate_id, decode(cdb.person_id, null, decode(cdb.corporate_id, null, unknown_ben_id.NEXTVAL, null), null), cdb.priority, :p_total_amount * ( cdb.amount / ( select nvl(SUM(cdb.amount), 0) from caseload_ded_beneficiaries cdb where cdb.caseload_id = :p_csld_id and cdb.deduction_type = cdb.deduction_type )), cdb.percent, null, null, null, :v_sysdate, :createUserId, CURRENT_TIMESTAMP from offender_deductions od, caseload_deduction_profiles cdp, deduction_types dt, caseload_ded_beneficiaries cdb where cdp.comm_condition_type = :p_condition_type and cdp.comm_condition_code = :p_condition_code and cdp.category_type = :p_con_category_type and dt.deduction_type = od.deduction_type and dt.deduction_category = 'FXOB' and dt.deduction_type = cdp.deduction_type and cdp.deduction_type = od.deduction_type and od.offender_id = :p_off_id and cdp.caseload_id = od.caseload_id and cdp.caseload_id = :p_csld_id and cdb.caseload_id = cdp.caseload_id and cdb.deduction_type = cdp.deduction_type and sysdate between cdp.effective_date and nvl(cdp.expiry_date, sysdate) and od.modify_date = :v_sysdate
}

GET_C_RECEIPT_CURSOR{
SELECT od.offender_deduction_id, cdd.receipt_txn_type, cdd.percentage, cdd.flat_rate FROM caseload_deduction_details cdd, offender_deductions od WHERE od.offender_id = :pOffId AND cdd.deduction_type = od.deduction_type AND cdd.caseload_id = :pCsldId AND cdd.caseload_id = od.caseload_id AND NOT EXISTS ( SELECT 1 FROM offender_deduction_receipts odr WHERE odr.offender_deduction_id = od.offender_deduction_id AND odr.receipt_txn_type = cdd.receipt_txn_type ) UNION SELECT od.offender_deduction_id, cdd.receipt_txn_type, cdd.percentage, cdd.flat_rate FROM caseload_deduction_details cdd, offender_deductions od WHERE od.offender_id = :pOffId AND cdd.deduction_type = od.deduction_type AND NOT EXISTS ( SELECT 1 FROM caseloads c WHERE c.caseload_type = :csldType AND c.caseload_id = cdd.caseload_id ) AND NOT EXISTS ( SELECT 1 FROM offender_deduction_receipts odr WHERE odr.offender_deduction_id = od.offender_deduction_id AND odr.receipt_txn_type = cdd.receipt_txn_type )
}

GET_EXIST_FLAG_SELECT{
SELECT COUNT(*) FROM offender_deduction_receipts WHERE offender_deduction_id = :offenderDeductionId AND receipt_txn_type = :receiptTxnType
}

INSERT_OFFENDER_DEDUCTION_RECEIPTS{
insert into offender_deduction_receipts (offender_deduction_id , receipt_txn_type , receipt_percentage , flat_rate, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME) values (:offenderDeductionId , :receiptTxnType , :percentage , :flatRate, :createUserId, CURRENT_TIMESTAMP , null)
}

CHK_CASE_LOAD_C{
SELECT COUNT(*) FROM institution_mini_balances WHERE caseload_id = :p_csld_id
}

CHK_IND_ACC{
SELECT trust_account_code FROM offender_sub_accounts WHERE offender_id = :p_off_id AND caseload_id = :p_csld_id AND trust_account_code IN ( SELECT account_code FROM institution_mini_balances WHERE caseload_id = :p_csld_id ) AND trust_account_code IN ( SELECT account_code FROM account_codes WHERE caseload_type = :csld_type ) UNION SELECT trust_account_code FROM offender_sub_accounts WHERE offender_id = :p_off_id AND caseload_id = :p_csld_id AND minimum_balance IS NOT NULL AND trust_account_code IN ( SELECT account_code FROM account_codes WHERE caseload_type = :csld_type )  
}

SYSTEM_PROFILE_C{
 SELECT profile_value, profile_value_2 FROM system_profiles WHERE profile_type = 'CLIENT' AND profile_code = 'IND_CON'
}

GET_TRUST_ACCOUNT_CODE_C{
SELECT account_code FROM account_codes WHERE sub_account_type = 'REG'
}

SUM_OFF_SUBACT_BAL_C{
SELECT SUM(osa.balance) FROM offender_sub_accounts osa WHERE offender_id = :p_off_id AND trust_account_code = :lv_trust_account_code AND NOT EXISTS ( SELECT DISTINCT caseload_id FROM institution_mini_balances imb WHERE imb.caseload_id = osa.caseload_id )
}

MAX_IND_DATE_C{
SELECT MAX(ind_date) FROM offender_sub_accounts osa WHERE offender_id = :p_off_id AND trust_account_code = :lv_trust_account_code AND NOT EXISTS ( SELECT DISTINCT caseload_id FROM institution_mini_balances imb WHERE imb.caseload_id = osa.caseload_id )
}

UPDATE_OFFENDER_SUB_ACCOUNTS{
update offender_sub_accounts osa set ind_date = :lv_indigent_date, ind_days = :lv_indigent_days_limit, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_id = :p_off_id and trust_account_code = :lv_trust_account_code and ( ind_date is null or :p_admission_date is not null ) and not exists ( select distinct caseload_id from institution_mini_balances imb where imb.caseload_id = osa.caseload_id )
}

UPDATE_OFFENDER_SUB_ACCOUNTS_ONE{
update offender_sub_accounts osa set ind_date = null, ind_days = null, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_id = :p_off_id and trust_account_code = :lv_trust_account_code and not exists ( select distinct caseload_id from institution_mini_balances imb where imb.caseload_id = osa.caseload_id )
}

DEFAULT_DEDUCTION_CURSOR{
select NEXTVAL('deduction_id') offender_deduction_id, cdp.deduction_type , cdp.caseload_id , cdp.max_monthly_amount , cdp.max_total_amount , cdp.max_recursive_amount , cdp.co_limit_amount , cdp.fifo_flag , cdp.payee_person_id , case coalesce(dt.increment_payables_flag, 'N') || to_char(cdp.payee_person_id) || to_char(cdp.payee_corporate_id) when 'Y' then :corporate_id else cdp.payee_corporate_id end corp_id from caseload_deduction_profiles cdp, deduction_types dt where cdp.deduction_type = dt.deduction_type and dt.caseload_code in ( 'BOTH', :csld_type ) and dt.active_flag = 'Y' and cdp.caseload_id = :p_csld_id and cdp.fo_al_all_offender_flag = 'Y' and current_timestamp between cdp.effective_date and coalesce(cdp.expiry_date, current_timestamp) and ( dt.deduction_category != 'ALCN' and not exists ( select 1 from offender_deductions od where od.offender_id = :p_off_id and od.deduction_type = cdp.deduction_type ) or dt.deduction_category in ( 'ALCN', 'CTF' ) and not exists ( select 1 from offender_deductions od where od.offender_id = :p_off_id and od.deduction_type = cdp.deduction_type and od.caseload_id = :p_csld_id ) ) 
}

MAX_ODP_CURSOR{
SELECT coalesce(MAX(deduction_priority), 0) FROM offender_deductions WHERE deduction_type = :lv_ded_type AND offender_id = :p_off_id
}

OFFENDER_DEDUCTIONS_INSER{
insert into offender_deductions (offender_deduction_id , offender_id , deduction_type , information_number , caseload_id , deduction_priority , deduction_status , effective_date , modify_date , max_monthly_amount , max_total_amount , max_recursive_amount , credit_limit , deduction_amount , fifo_flag , payee_person_id , payee_corporate_id, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME) values (NEXTVAL('deduction_id') , :p_off_id , :ded_type , to_char(SYSDATE(), 'MMDDYYYY') , :csld_id , :lv_odp , 'A' , date_trunc('D',SYSDATE()) , :v_sysdate , :max_monthly , :max_total , :max_recursive , :credit_limit , 0.0 , :fifo , :person_id , :corp_id, :createUserId, CURRENT_TIMESTAMP , NULL) 
}

OFFENDER_BENEFICIARIES_INSERT{
insert into offender_beneficiaries (beneficiary_id , offender_deduction_id , offender_id , person_id , corporate_id , unknown_ben_id , priority , amount , percent , override_amount , received_amount , comment_text , modify_datetime, CREATE_USER_ID, CREATE_DATETIME) select NEXTVAL('beneficiary_id') , od.offender_deduction_id , :p_off_id , cdb.person_id , cdb.corporate_id , case when cdb.person_id is null then case when cdb.corporate_id is null then NEXTVAL('unknown_ben_id') else null end else null end , cdb.priority , cdb.amount , cdb.percent , null , null , null , null, :createUserId, CURRENT_TIMESTAMP from offender_deductions od , caseload_deduction_profiles cdp , deduction_types dt , caseload_ded_beneficiaries cdb where dt.deduction_type = od.deduction_type and dt.deduction_category in ('CROB', 'FXOB') and dt.deduction_type = cdp.deduction_type and cdp.deduction_type = od.deduction_type and od.offender_id = :p_off_id and cdp.caseload_id = od.caseload_id and cdp.caseload_id = :p_csld_id and cdp.fo_al_all_offender_flag = 'Y' and cdb.caseload_id = cdp.caseload_id and cdb.deduction_type = cdp.deduction_type and CURRENT_TIMESTAMP between cdp.effective_date and coalesce(cdp.expiry_date, CURRENT_TIMESTAMP) and od.modify_date = :v_sysdate 
}

OFFENDER_BENEFICIARIES_INSERT_ONE{
insert into offender_beneficiaries ( beneficiary_id, offender_deduction_id, offender_id, person_id, corporate_id, unknown_ben_id, priority, amount, percent, override_amount, received_amount, comment_text, modify_datetime, CREATE_USER_ID, CREATE_DATETIME) select NEXTVAL('beneficiary_id'), od.offender_deduction_id, :p_off_id, cdp.payee_person_id, cdp.payee_corporate_id, case when cdp.payee_person_id is null then case when cdp.payee_corporate_id is null then NEXTVAL('unknown_ben_id') else null end else null end, 1, 999999999.99, 100, null, null, null, null, :createUserId, CURRENT_TIMESTAMP from offender_deductions od, caseload_deduction_profiles cdp, deduction_types dt where dt.deduction_type = od.deduction_type and dt.deduction_category in ( 'CTF', 'DTF' ) and dt.deduction_type = cdp.deduction_type and cdp.deduction_type = od.deduction_type and od.offender_id = :p_off_id and cdp.caseload_id = od.caseload_id and cdp.caseload_id = :p_csld_id and cdp.fo_al_all_offender_flag = 'Y' and current_timestamp between cdp.effective_date and coalesce(cdp.expiry_date, current_timestamp) and od.modify_date = :v_sysdate
}

C_RECEIPT_CURSOR_A{
SELECT od.offender_deduction_id, cdd.receipt_txn_type, cdd.percentage, cdd.flat_rate FROM caseload_deduction_details cdd, offender_deductions od WHERE od.offender_id = :p_off_id AND cdd.deduction_type = od.deduction_type AND cdd.caseload_id = :p_csld_id AND cdd.caseload_id = od.caseload_id AND NOT EXISTS ( SELECT 1 FROM offender_deduction_receipts odr WHERE odr.offender_deduction_id = od.offender_deduction_id AND odr.receipt_txn_type = cdd.receipt_txn_type ) UNION SELECT od.offender_deduction_id, cdd.receipt_txn_type, cdd.percentage, cdd.flat_rate FROM caseload_deduction_details cdd, offender_deductions od WHERE od.offender_id = :p_off_id AND cdd.deduction_type = od.deduction_type AND NOT EXISTS ( SELECT 1 FROM caseloads c WHERE c.caseload_type = :csld_type AND c.caseload_id = cdd.caseload_id ) AND NOT EXISTS ( SELECT 1 FROM offender_deduction_receipts odr WHERE odr.offender_deduction_id = od.offender_deduction_id AND odr.receipt_txn_type = cdd.receipt_txn_type )
}

OFFENDER_DEDUCTION_RECEIPTS_SELECT_OPERATION{
SELECT COUNT(*) FROM offender_deduction_receipts WHERE offender_deduction_id = :offender_deduction_id AND receipt_txn_type = :receipt_txn_type
}

OFFENDER_DEDUCTION_RECEIPTS_INSERT{
insert into offender_deduction_receipts ( offender_deduction_id, receipt_txn_type, receipt_percentage, flat_rate , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME) values ( :offender_deduction_id, :receipt_txn_type, :percentage, :flat_rate , :createUserId, CURRENT_TIMESTAMP , NULL )
}

C_COL_FEE_CURSOR{
SELECT od.offender_deduction_id, tfd.receipt_deduction_type FROM transaction_fee_details tfd, offender_deductions od WHERE od.offender_id = :p_off_id AND tfd.caseload_id = :p_csld_id AND tfd.deduction_type = od.deduction_type AND tfd.caseload_id = od.caseload_id AND NOT EXISTS ( SELECT 1 FROM offender_txn_fee_details otfd WHERE otfd.offender_deduction_id = od.offender_deduction_id AND otfd.receipt_deduction_type = tfd.receipt_deduction_type ) UNION SELECT od.offender_deduction_id, tfd.receipt_deduction_type FROM transaction_fee_details tfd, offender_deductions od WHERE od.offender_id = :p_off_id AND tfd.deduction_type = od.deduction_type AND tfd.caseload_id NOT IN ( SELECT caseload_id FROM caseloads WHERE caseload_type = :csld_type ) AND NOT EXISTS ( SELECT 1 FROM offender_txn_fee_details otfd WHERE otfd.offender_deduction_id = od.offender_deduction_id AND otfd.receipt_deduction_type = tfd.receipt_deduction_type )
}

OFFENDER_TXN_FEE_DETAILS_SELECT{
SELECT COUNT(*) FROM offender_txn_fee_details WHERE offender_deduction_id = :offender_deduction_id AND receipt_deduction_type = :receipt_deduction_type
}

OFFENDER_TXN_FEE_DETAILS_INSERT{
insert into offender_txn_fee_details ( offender_deduction_id, receipt_deduction_type , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME) values ( :offender_deduction_id, :receipt_deduction_type , :createUserId, CURRENT_TIMESTAMP , null )
}

C_RECEIPT_CURSOR{
SELECT od.offender_deduction_id , tca.deduction_type, tca.from_amount, tca.to_amount, tca.percentage, tca.fee_amount FROM tiered_transaction_fee_amounts tca, offender_deductions od WHERE od.offender_id = :p_off_id AND tca.caseload_id = :p_csld_id AND tca.deduction_type = od.deduction_type AND tca.caseload_id = od.caseload_id AND NOT EXISTS ( SELECT 1 FROM offender_tier_txn_fee_amounts oca WHERE oca.offender_deduction_id = od.offender_deduction_id )
}

OFFENDER_TIER_TXN_FEE_AMOUNTS_INSERT{
insert into offender_tier_txn_fee_amounts ( offender_deduction_id, from_amount, to_amount, percentage, fee_amount, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME) values ( :offender_deduction_id, :from_amount, :to_amount, :percentage, :fee_amount , :createUserId, CURRENT_TIMESTAMP , NULL ) 
}   

UPDATE_DEDUCTION_STATUS{
update OFFENDER_DEDUCTIONS OD set MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP, DEDUCTION_STATUS = case when :P_SUSPEND_FLAG = 'Y' then 'D' when :P_SUSPEND_FLAG = 'N' then 'A' end where CASELOAD_ID = :P_CASELOAD_ID and exists ( select 1 from SUSPEND_DEDUCTION_TYPES SDT where SUSPEND_DEDUCTION_ID = :P_SUS_DED_ID and SDT.DEDUCTION_TYPE = OD.DEDUCTION_TYPE and SUSPENDED_FLAG = case when :P_SUSPEND_FLAG = 'Y' then 'Y' when :P_SUSPEND_FLAG = 'N' then SUSPENDED_FLAG end and (OD.DEDUCTION_TYPE = :P_DEDUCTION_TYPE or coalesce(:P_DEDUCTION_TYPE::text, '') = '')) and DEDUCTION_STATUS != 'S' and DEDUCTION_STATUS = case when :P_SUSPEND_FLAG = 'Y' then DEDUCTION_STATUS when :P_SUSPEND_FLAG = 'N' then 'D' end
}

SUSPEND_OFF_DEDUCTIONS_C_SUS_DED{
SELECT SUSPEND_DEDUCTION_ID, CASELOAD_ID FROM SUSPEND_DEDUCTIONS WHERE START_DATE = CURRENT_DATE
}
