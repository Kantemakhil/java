CALCULATE_BENEFICIARIES_TOTAL_SERVICE_CORP_BENEFICIARIES_RECORD{
 SELECT off_ded.offender_id ,off_ded.max_monthly_amount ,off_ded.max_total_amount ,off_ded.max_recursive_amount ,off_bnc.amount ,off_bnc.received_amount ,off_bnc.offender_deduction_id FROM offender_deductions off_ded, offender_beneficiaries off_bnc WHERE off_bnc.offender_deduction_id = off_ded.offender_deduction_id AND off_bnc.corporate_id = :CORPORATEID
 }           
 
 CALCULATE_BENEFICIARIES_TOTAL_MON_AMT{
 select coalesce (SUM(case bt.txn_post_usage when 'CR' then bt.txn_entry_amount when 'DR' then bt.txn_entry_amount * -1 else 0 end), 0) from beneficiary_transactions bt where offender_deduction_id = :offenderDeductionId and corporate_id = :corporateId and date_trunc('month',txn_entry_date) = date_trunc('month', current_timestamp) 
 }       
  
CALCULATE_BENEFICIARIES_TOTAL_MON_AMT{
 select date_part('month',date_trunc('month', current_timestamp)), date_trunc('month', off_ded1.effective_date)+ interval '1 month' from offender_deductions off_ded1 where off_ded1.offender_deduction_id = :offenderDeductionId 
}    

CHK_ACCOUNT_STATUS_SELECT{
SELECT ACCOUNT_CLOSED_FLAG  FROM OFFENDER_TRUST_ACCOUNTS WHERE OFFENDER_ID = :offenderId AND CASELOAD_ID = :caseLoadId
}
GETTING_OFFENDER_SUB_BAL{
 SELECT coalesce (osa.balance, 0) FROM offender_sub_accounts osa, account_codes ac WHERE osa.trust_account_code = ac.account_code AND ac.account_code =:p_account_code AND osa.offender_id =:p_off_id AND osa.caseload_id =:p_org_csld_id AND ac.caseload_type =:p_csld_type
}

TEMP_FLAG_SELECT_OPERATION{
SELECT COUNT(*) FROM FREEZE_DISBURSEMENTS WHERE ( ACCOUNT_CODE = :acntCode OR ( coalesce(:acntCode::int,0)=0 AND ACCOUNT_CODE IN ( SELECT DR_ACCOUNT_CODE FROM TRANSACTION_OPERATIONS WHERE TXN_TYPE = :txnType AND CASELOAD_ID = :caseLoadId AND MODULE_NAME = :moduleName ) ) ) AND TXN_TYPE = :txnType AND CASELOAD_TYPE = :caseLoadType AND FREEZE_FLAG = 'Y'
}

FRZ_FLAG_SELECT_OPERATION{
select COUNT(*) from offender_freeze_disbursements where offender_id = :offenderId and caseload_id = :caseLoadId and coalesce(:date, date_trunc('day',current_timestamp)) between from_date and to_date and coalesce (removed_flag, 'N') = 'N'
}

GET_CASE_LOAD_TYPE{
SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID = :caseLoadId
}

GET_LOW_HIGH_FLAG_INTO_PROFILE_VALUE {
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'LOW_HIGH_SEL'
}

GET_GET_RATE_CUR {
SELECT RECEIPT_PERCENTAGE, FLAT_RATE FROM OFFENDER_DEDUCTION_RECEIPTS WHERE RECEIPT_TXN_TYPE = :DISBU_TYPE AND OFFENDER_DEDUCTION_ID = :DED_ID
}
            
GET_V_TXN_REF_NUM{
 select txn_reference_number from offender_transactions where txn_id = :transNumber and txn_entry_seq = :transSeq
}

GL_TRANS_INSERT{
insert into gl_transactions (txn_id , txn_entry_seq , account_period_id , gl_entry_seq , account_code , txn_entry_date , txn_type , txn_post_usage , caseload_id , offender_id , offender_book_id , txn_entry_amount , txn_entry_desc , txn_reference_number , recon_clear_flag , txn_reversed_flag , create_user_id , create_date , payee_person_id , payee_corporate_id , payee_name_text , reversed_txn_id , reversed_txn_entry_seq , reversed_gl_entry_seq , deduction_id , txn_entry_time , info_number,create_datetime ) select :txnId , :txnEntrySeq , account_period_id , :glEntrySeq , :accountCode , :txnEntryDate , :txnType , :txnPostUsage , :caseloadId , :offenderId , :offenderBookId , :txnEntryAmount , :txnEntryDesc , :txnReferenceNumber , :reconClearFlag , :txnReversedFlag , :createUserId , current_timestamp , :payeePersonId , :payeeCorporateId , :payeeNameText , :reversedTxnId , :reversedTxnEntrySeq , :reversedGlEntrySeq , :deductionId , :txnEntryTime , :infoNumber,current_timestamp FROM account_periods WHERE (:txnEntryDate) BETWEEN start_date AND end_date
}

INSERT_INTO_CHEQUE_DATA_SELECT_OPERATION_FN{
select ACCOUNT_CODE from CASELOAD_CURRENT_ACCOUNTS_FN(:userId) where CASELOAD_ID = :caseloadId and BANK_ACCOUNT_TYPE = 'CHK' and ACCOUNT_CODE in ( select coalesce (BANK_CR_ACCOUNT_CODE, CR_ACCOUNT_CODE) from TRANSACTION_OPERATIONS where MODULE_NAME = :moduleName and TXN_TYPE = :transType and CASELOAD_ID = :caseloadId and CHEQUE_PRODUCTION_FLAG = 'Y')
}

INSERT_INTO_CHEQUE_DATA_INSERT_OPERATION {
insert into bank_cheque_data ( caseload_id, txn_id, modify_date, txn_entry_amount, start_txn_id, end_txn_id, payee_person_id, payee_corporate_id, payee_name_text, cheque_flag, bank_account_code, txn_entry_date, offender_id, single_entry_flag, cheque_date , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) select :caseloadId, :txnId, CURRENT_TIMESTAMP, :txnEntryAmount, :startTxnId, :endTxnId, :payeePersonId, :payeeCorporateId, :payeeNameText, :chequeFlag, :bankAccountCode, CURRENT_TIMESTAMP, :offenderId, :singleEntryFlag, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP from dual
}

UPDATE_OFFENDER_SUB_ACCOUNTS_A {
UPDATE offender_sub_accounts SET modify_user_id = :modifyUserId ,MODIFY_DATETIME = CURRENT_TIMESTAMP,modify_date = :txnEntryDate ,last_txn_id = :txnId ,balance = coalesce(balance, 0) + :postingAmount WHERE caseload_id = :caseloadId AND offender_id = :offenderId AND trust_account_code IN (SELECT account_code FROM account_codes WHERE sub_account_type IS NOT NULL AND sub_account_type = :subAccountType AND caseload_type = :caseLoadType)
}

UPDATE_OFFENDER_TRUST_ACCOUNTS_B {
update offender_trust_accounts set modify_user_id = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP, modify_date = :txnEntryDate , current_balance = coalesce (current_balance, 0) + :postingAmount where caseload_id = :caseloadId and offender_id = :offenderId }
 
 GET_BALANCE_FROM_OFFENDER_SUB_ACOUNTS{
 SELECT balance FROM offender_sub_accounts WHERE caseload_id = :caseloadId AND offender_id = :offenderId AND trust_account_code IN (SELECT account_code FROM account_codes WHERE sub_account_type IS NOT NULL AND sub_account_type = :subAccountType AND caseload_type = :caseLoadType) --FOR UPDATE OF balance
 }
 
 UPDATE_SUB_ACCOUNTS_WHEN_OVERRIDDEN_FLAGN{
 update offender_sub_accounts set modify_user_id = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP, modify_date = :txnEntryDate, last_txn_id = :txnId , balance = coalesce (balance, 0) + :pstng_amount where caseload_id = :caseloadId and offender_id = :offenderId and trust_account_code in ( select account_code from account_codes where sub_account_type is not null and sub_account_type = :subAccountType and caseload_type = :caseLoadType) and (COALESCE (balance, 0) + :pstng_amount >= 0)
 }

GET_SUB_ACT_ND_TXN_POST_TYPES{
SELECT sub_account_type, 'DR' CODE FROM account_codes WHERE account_code = ( SELECT dr_account_code FROM transaction_operations WHERE module_name = :p_module_name AND txn_type = :p_txn_type AND caseload_id = :csld_id ) AND caseload_type = :csld_type
}

GET_SUB_ACT_ND_TXN_POST_TYPES_CR{
SELECT sub_account_type, 'CR' CODE FROM account_codes WHERE account_code = ( SELECT cr_account_code FROM transaction_operations WHERE module_name = :p_module_name AND txn_type = :p_txn_type AND caseload_id = :csld_id ) AND caseload_type = :csld_type
}

POSTING_C_CURSOR{
select a.dr_account_code, b.txn_posting_type b_txn_posting_type, coalesce(b.posting_status_flag, 'N') b_posting_status_flag, a.cr_account_code, c.txn_posting_type c_txn_posting_type, coalesce(c.posting_status_flag, 'N') c_posting_status_flag, a.bank_dr_account_code, d.txn_posting_type d_txn_posting_type, coalesce(d.posting_status_flag, 'N') d_posting_status_flag, a.bank_cr_account_code, e.txn_posting_type e_txn_posting_type, coalesce(e.posting_status_flag, 'N') e_posting_status_flag, a.txn_operation_seq from account_codes c, account_codes b, transaction_operations a left outer join account_codes d on (a.bank_dr_account_code = d.account_code) left outer join account_codes e on (a.bank_cr_account_code = e.account_code) where ( coalesce(:p_sub_act_type_dr::text, '') = '' or a.dr_account_code in ( select account_code from account_codes where sub_account_type = :p_sub_act_type_dr and (sub_account_type is not null and sub_account_type::text <> '') and caseload_type = :csld_type ) ) and ( coalesce(:p_sub_act_type_cr::text, '') = '' or a.cr_account_code in ( select account_code from account_codes where sub_account_type = :p_sub_act_type_cr and (sub_account_type is not null and sub_account_type::text <> '') and caseload_type = :csld_type ) ) and a.dr_account_code = b.account_code and a.cr_account_code = c.account_code and a.txn_type = :p_trans_type and module_name = :p_module_name and coalesce(a.txn_operation_type, '0') = coalesce(:p_operation_type, '0') and c.caseload_type = b.caseload_type and b.caseload_type = :csld_type and a.caseload_id = :p_csld_id order by txn_operation_seq
}

GET_CASELOAD_TYPE_A {
SELECT caseload_type FROM caseloads WHERE caseload_id = :caseloadId
}

GET_TRANS_TYPES{
SELECT txn_usage, credit_obligation_type FROM transaction_types WHERE txn_type = :txnType AND caseload_type = COALESCE(:caseLoadType,:caseLoadType)
}

GET_SYS_PROFILES{
SELECT profile_value, profile_value_2 FROM system_profiles WHERE profile_type = 'CLIENT' AND profile_code ='IND_CON'
}

GET_AGY_LOC_ID{
SELECT agy_loc_id FROM v_header_block2_fn(:userId) WHERE root_offender_id = :offenderId AND offender_book_id = (SELECT MAX(offender_book_id) FROM v_header_block2_fn(:userId) WHERE root_offender_id = :offenderId AND booking_type = :caseLoadType) AND booking_type = :caseLoadType
}

GET_INST_MINI_BALANCE{
 select minimum_account_balance, ind_days from institution_mini_balances where caseload_id = :caseLoadId and (agy_loc_id = :agyLOcId or agy_loc_id = 'OUT') and account_code = :accountCode order by case agy_loc_id when 'OUT' then 2 else 1 end;
}

GET_DR_ACCOUNT_CODE{
SELECT dr_account_code   FROM transaction_operations WHERE module_name = :moduleName AND txn_type = :txnType AND caseload_id = :caseLoadId
}

GET_OFF_SUB_ACCOUNTS{
SELECT COALESCE(balance, 0) balance,minimum_balance ,ind_date ,COALESCE(ind_days, 0) ind_days FROM offender_sub_accounts WHERE caseload_id = :caseloadId AND offender_id = :offenderId AND trust_account_code = :drAccountCode
}

GET_OFF_SUB_ACCOUNTS_TWO{
select coalesce(balance, 0) balance, minimum_balance , ind_date , coalesce(ind_days, 0) ind_days from offender_sub_accounts where caseload_id = :caseloadId and offender_id = :offenderId and trust_account_code = :drAccountCode
}

GET_IND_DAYS{
SELECT COALESCE(ind_days, 0) FROM offender_sub_accounts WHERE caseload_id = :caseloadId AND offender_id = :offenderId AND trust_account_code = :drAccountCode
}

CHK_OVERDRAWN_L_CASELOAD_EXISTS {
	SELECT DISTINCT 'Y'  FROM institution_mini_balances WHERE caseload_id = :p_csld_id
}

CHK_OVERDRAWN_L_AGY_LOC_ID_CUR {
	SELECT agy_loc_id FROM v_header_block2_fn(:userId) WHERE root_offender_id = :p_offender_id AND offender_book_id = (SELECT MAX(offender_book_id) FROM v_header_block2_fn(:userId) WHERE root_offender_id = :p_offender_id AND booking_type = :p_caseload_type) AND booking_type = :p_caseload_type
}

CHK_OVERDRAWN_L_MIN_BALANCE_CUR{
select minimum_account_balance, ind_days from institution_mini_balances where caseload_id = :p_caseload_id and (agy_loc_id = :p_agy_loc_id or agy_loc_id = 'OUT') and account_code = :p_trust_account_code order by case agy_loc_id when 'OUT' then 2 else 1 end
}

CHK_OVERDRAWN_L_SYSTEM_PROFILES_CUR {
	SELECT profile_value, profile_value_2 FROM system_profiles WHERE profile_type = :p_profile_type AND profile_code = :p_profile_code
}

CHK_OVERDRAWN_L_IND_DAYS {
select coalesce (ind_days, 0) from offender_sub_accounts where caseload_id = :p_csld_id and offender_id = :p_off_id and trust_account_code = :trustacccode           
  }
  
  CHK_OVERDRAWN_TRANSACTION_TYPES_C {
select txn_usage, credit_obligation_type from transaction_types where txn_type = :txntype and caseload_type = coalesce (:lv_setup_csld_type, :csld_type)
  }
  
  CHK_OVERDRAWN_CROB {
select 'Y' from deduction_types where deduction_type = :obligationtype and deduction_category = 'CROB' and caseload_code in ('BOTH', coalesce (:lv_setup_csld_type, :csld_type))
  }
  
  CHK_OVERDRAWN_INDIGENTFLAG {
 SELECT coalesce (co_credit_when_indigent_flag, 'N') co_credit_when_indigent_flag FROM caseload_deduction_profiles WHERE caseload_id = :p_csld_id AND deduction_type = :obligationtype 
  }
  
  CHK_OVERDRAWN_COUNT {
  	SELECT COUNT(*) FROM system_profiles WHERE profile_type = 'CLIENT' AND profile_code = 'WASHINGTON' AND profile_value = 'Y'
  }
  
  
  CCHK_OVERDRAWN_HK_IND_AC_OTHERS_C {  
 SELECT trust_account_code FROM offender_sub_accounts WHERE offender_id = :p_off_id AND caseload_id = :p_csld_id AND trust_account_code IN (SELECT account_code FROM institution_mini_balances WHERE caseload_id = :p_csld_id UNION SELECT trust_account_code FROM offender_sub_accounts WHERE offender_id = :p_off_id AND caseload_id = :p_csld_id AND minimum_balance IS NOT NULL) AND trust_account_code != :trstcode
   }

  CCHK_OVERDRAWN_OFFENDER_LIMITS {
select limit_amount, coalesce (period_type, 'WEEK') period_type from offender_limits where caseload_id = :caseloadid and offender_id = :offenderid and limit_type = :limittype   
  }
  
  CCHK_OVERDRAWN_OFFENDER_LIMITS_TWO {
SELECT limit_amount, coalesce (period_type, 'WEEK') period_type FROM caseload_limits WHERE caseload_id = :caseloadid AND limit_type = :limittype 
  }
  
  CHK_OVERDRAWN_WEEKDAY {
  SELECT to_char(SYSDATE(), 'D')  FROM dual;
  }
  
  CHK_OVERDRAWN_FROMDATE  {
  	SELECT current_date - (:weekday - 1)  FROM dual;  
  }
  
  CHK_OVERDRAWN_TODATE {
   SELECT current_date + (7 - :weekday) FROM dual
  }
  
   CHK_OVERDRAWN_FROMDATE_TWO {
   select (date_trunc('MONTH', CURRENT_TIMESTAMP) + interval ' - 1 month')::date + interval '1 month'
   }
   
   CHK_OVERDRAWN_TODATE_TWO {
   	SELECT last_day(SYSDATE())  FROM dual
   }
   
   CHK_OVERDRAWN_GET_TOTAL_TAKEN {
select coalesce (SUM(coalesce (txn_entry_amount, 0)), 0) from offender_transactions where offender_id = :p_offender_id and deduction_type = :ded_type and txn_type = :ded_type and txn_posting_type = 'CR' and caseload_id = :caseloadid and txn_entry_date between :from_date and :to_date    
   }
   
    CHK_OVERDRAWN_TOTAL_REVERSED {
    	SELECT coalesce(SUM(coalesce(txn_entry_amount, 0)), 0) FROM offender_transactions WHERE offender_id = :p_offender_id AND deduction_type = :ded_type AND txn_type = :ded_type AND txn_posting_type = 'DR' AND caseload_id = :caseloadid AND txn_entry_date BETWEEN :from_date AND :to_date AND (adjust_txn_id, adjust_txn_entry_id) IN (SELECT txn_id, txn_entry_seq FROM offender_transactions WHERE offender_id = :p_offender_id AND deduction_type = :ded_type AND txn_type = :ded_type AND txn_posting_type = 'CR' AND caseload_id = :caseloadid AND txn_entry_date BETWEEN :from_date AND :to_date)

    }
    
    CHK_OVERDRAWN_AMOUNT_WRITTEN_OFF {
    	SELECT coalesce(SUM(coalesce(adjustment_amount, 0)), 0) FROM offender_deductions WHERE caseload_id = :caseloadid AND offender_id = :offenderid AND effective_date BETWEEN :fromdate AND :todate AND deduction_type = :limittype
    }

    
    CHK_OVERDRAWN_ACCOUNT_CODES [
    	SELECT opr.dr_account_code ,acc1.txn_posting_type a_txn_posting_type ,acc1.sub_account_type ,opr.cr_account_code ,acc2.txn_posting_type b_txn_posting_type ,acc2.sub_account_type caseload_type ,tty.description FROM transaction_operations opr ,account_codes acc1 ,account_codes acc2 ,transaction_types tty WHERE opr.module_name = :form_name AND opr.dr_account_code = acc1.account_code AND opr.cr_account_code = acc2.account_code AND acc1.posting_status_flag = 'Y' AND acc2.posting_status_flag = 'Y' AND opr.txn_type = :obligationtype AND opr.txn_type = tty.txn_type AND acc1.caseload_type = coalesce(:lv_setupcsld_type, :csld_type) AND acc2.caseload_type = coalesce(:lv_setupcsld_type, :csld_type) AND opr.caseload_id = coalesce(:p_setup_caseload, :caseload) AND tty.caseload_type = coalesce(:lv_setupcsld_type, :csld_type)
    }
    
 CHK_OVERDRAWN_OFFENDER_DEDUCTIONS {
SELECT offender_deduction_id ,payee_person_id ,payee_corporate_id ,adjustment_reason_code ,information_number FROM offender_deductions WHERE caseload_id = :caseload AND offender_id = :offenderid AND offender_deduction_id = (SELECT MAX(offender_deduction_id) FROM offender_deductions WHERE caseload_id = :caseload AND offender_id = :offenderid AND deduction_type = :obligationtype AND effective_date <= date_trunc('date', current_timestamp))
}
    
 CHK_OVERDRAWN_CASELOAD_DEDUCTION_PROFILES {
 SELECT cdp.fifo_flag ,to_char(current_date, 'MMDDYYYY') frequency ,cdp.payee_corporate_id ,c.corporate_name FROM caseload_deduction_profiles cdp ,corporates c ,corporate_types ct WHERE cdp.caseload_id = :caseload AND cdp.deduction_type = :obligationtype AND c.corporate_id = cdp.payee_corporate_id AND ct.corporate_id = c.corporate_id AND ct.corporate_type = 'TRUST'
}
     
     
 CHK_OVERDRAWN_LL_CNT {
SELECT COUNT(*) FROM offender_deductions WHERE offender_id = :offenderid AND deduction_type = :obligationtype AND substr(information_number, 1, 8) = :infonum
 }
     
CHK_OVERDRAWN_PRTY_NMBR {
SELECT coalesce(MAX(deduction_priority), 0) + 1 FROM offender_deductions WHERE offender_id = :offenderid AND deduction_type = :obligationtype
}
     
 CHK_OVERDRAWN_OFFDEDID {
SELECT NEXTVAL ('deduction_id')  FROM dual    
}
    
 CHK_OVERDRAWN_INSERT_OFFENDER_DEDUCTIONS {
 insert into offender_deductions ( offender_deduction_id , caseload_id , offender_id , deduction_type , deduction_priority , information_number , effective_date , fifo_flag , payee_person_id , payee_corporate_id , max_monthly_amount , max_total_amount , deduction_amount , deduction_status , modify_date, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:offenderDeductionId, :caseloadId, :offenderId, :deductionType, :deductionPriority, :informationNumber , trunc(SYSDATE), :fifoFlag , :payeePersonId , :payeeCorporateId , :maxMonthlyAmount , :maxTotalAmount , :deductionAmount, :deductionStatus, trunc(SYSDATE) , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP) 
 }
    
CHK_OVERDRAWN_INSERT_OFFENDER_DEDUCTION_RECEIPTS {
 insert into offender_deduction_receipts (offender_deduction_id, receipt_txn_type, receipt_percentage, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) select od.offender_deduction_id , cdd.receipt_txn_type , cdd.percentage, od.create_user_id , CURRENT_TIMESTAMP , CURRENT_TIMESTAMP from offender_deductions od, caseload_deduction_details cdd where od.caseload_id = :caseload and od.offender_id = :offenderid and od.offender_deduction_id = :offdedid and od.deduction_type = cdd.deduction_type and cdd.caseload_id = od.caseload_id
}
    
CHK_OVERDRAWN_INSERT_OFFENDER_BENEFICIARIES {
 insert into offender_beneficiaries (beneficiary_id , offender_deduction_id , offender_id , person_id , corporate_id , priority , amount , percent , override_amount , received_amount , modify_datetime , unknown_ben_id , comment_text, CREATE_USER_ID, CREATE_DATETIME) select NEXTVAL('beneficiary_id') , :offdedid , :offenderid , null , :payeecorp , 1 , 0 , 100 , null , 0 , CURRENT_TIMESTAMP, null , null, :createUserId, CURRENT_TIMESTAMP from dual
 }
    
CHK_OVERDRAWN_OFF_BOOK_ID {	
SELECT offender_book_id FROM v_header_block2_fn(:userId) WHERE root_offender_id = :p_off_id AND active_flag = 'Y'
 }
    
 CHK_OVERDRAWN_OFFENDER_TRANSACTIONS {
  insert into offender_transactions (txn_id , txn_entry_seq , caseload_id , offender_id , offender_book_id , txn_posting_type , txn_type , txn_entry_desc , txn_entry_amount , txn_entry_date , sub_account_type , modify_date , slip_printed_flag , pre_withhold_amount , deduction_flag , payee_code , payee_corporate_id , payee_person_id , payee_name_text , deduction_type , info_number, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME, MODIFY_USER_ID) select :p_trans_number , :p_trans_seq , :p_csld_id , :p_off_id , :off_book_id , :p_trans_post_type , :p_trans_type , :p_trans_desc , :p_trans_amount , :p_trans_date , :p_sub_act_type , CURRENT_TIMESTAMP , :p_slip_print_flag , :p_pre_ded_amount , :p_deduction_flag , :p_payee_code , :p_payee_corp_id , :p_payee_person_id , :p_payee_name_text , :p_deduction_type , :p_info_number, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP, :createUserId from dual
 }
     
CHK_OVERDRAWN_POSTING_C {   
select a.dr_account_code, b.txn_posting_type b_txn_posting_type, coalesce ( b.posting_status_flag, 'N' ) b_posting_status_flag, a.cr_account_code, c.txn_posting_type c_txn_posting_type, coalesce ( c.posting_status_flag, 'N' ) c_posting_status_flag, a.bank_dr_account_code, d.txn_posting_type d_txn_posting_type, coalesce ( d.posting_status_flag, 'N' ) d_posting_status_flag, a.bank_cr_account_code, e.txn_posting_type, coalesce ( e.posting_status_flag, 'N' ) e_posting_status_flag, a.txn_operation_seq from transaction_operations a inner join account_codes b on a.dr_account_code = b.account_code inner join account_codes c on a.cr_account_code = c.account_code and c.caseload_type = b.caseload_type left join account_codes d on a.bank_dr_account_code = d.account_code left join account_codes e on a.bank_cr_account_code = e.account_code where ( :p_sub_act_type_dr is null or a.dr_account_code in ( select account_codes.account_code from account_codes where account_codes.sub_account_type = :p_sub_act_type_dr and account_codes.sub_account_type is not null and account_codes.caseload_type = :setup_csld_type ) ) and ( :p_sub_act_type_cr is null or a.cr_account_code in ( select account_codes.account_code from account_codes where account_codes.sub_account_type = :p_sub_act_type_cr and account_codes.sub_account_type is not null and account_codes.caseload_type = :setup_csld_type ) ) and a.txn_type = :p_trans_type and a.module_name = :p_module_name and coalesce ( a.txn_operation_type, '0' ) = coalesce ( :p_operation_type, '0' ) and b.caseload_type = :setup_csld_type and a.caseload_id = :p_setup_csld_id order by a.txn_operation_seq
}
    
CHK_OVERDRAWN_UPDATE_OFFENDER_DEDUCTIONS {  
update offender_deductions set max_total_amount = coalesce (max_total_amount, 0) + :overdrawn , credit_limit = :maxlimit , modify_date = :today_date , modify_user_id = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_deduction_id = :offdeductionid
 }
     
    
 CHK_OVERDRAWN_UPDATE_OFFENDER_BENEFICIARIES { 
  update offender_beneficiaries set amount = coalesce (amount, 0) + :overdrawn , modify_datetime = :today_date , modify_user_id = :modifyUserId where offender_deduction_id = :offdeductionid
 }
    
    
TRUST_COMMUNITY_CSLD{
SELECT coalesce(community_trust_caseload, caseload_id) FROM caseloads WHERE caseload_id = :P_CSLD_ID
}

PROCESS_TRANSACTION_GET_OFFENDER_BOOK_ID_CUR{   
 SELECT offender_book_id FROM v_trust_header_a vtha WHERE vtha.offender_book_id = (SELECT MAX(vtha1.offender_book_id) FROM v_trust_header_a vtha1 WHERE vtha1.root_offender_id = :P_OFF_ID AND vtha1.caseload_id = :P_CSLD_ID)
 }
PROCESS_TRANSACTION_INSERT_OFFENDER_TRANSACTIONS{ 
INSERT INTO offender_transactions(txn_id ,txn_entry_seq ,caseload_id ,offender_id ,offender_book_id ,txn_posting_type ,txn_type ,txn_entry_desc ,txn_entry_amount ,txn_entry_date ,sub_account_type ,modify_date ,modify_user_id ,slip_printed_flag ,pre_withhold_amount ,deduction_flag ,payee_corporate_id ,payee_person_id ,deduction_type ,info_number)
values(:p_trans_number ,:p_trans_seq ,:p_csld_id ,:p_off_id ,:off_book_id ,:p_trans_post_type ,:p_trans_type ,:p_trans_desc ,:p_trans_amount ,:p_trans_date ,:p_sub_act_type ,:p_trans_date,:createUserId,:p_slip_print_flag ,:p_pre_ded_amount ,:p_deduction_flag ,:p_payee_corp_id ,:p_payee_person_id ,:p_deduction_type ,:p_info_number)
}  

GET_DR_TXN_OPR{
SELECT a.dr_account_code ,b.txn_posting_type ,coalesce (b.posting_status_flag, 'N') posting_status_flag ,b.sub_account_type FROM transaction_operations a, account_codes b WHERE a.dr_account_code = b.account_code AND a.txn_type = :disbu_type AND module_name = :mod_name AND a.caseload_id = :csld_id AND b.caseload_type = :caseloadtype
}
GET_TXN_FEE_TYPE_A{
SELECT od.deduction_type ,od.offender_deduction_id ,od.information_number ,cdp.account_code ,cdp.payee_corporate_id ,coalesce (ac.posting_status_flag, 'N') posting_status FROM offender_deductions od ,caseload_deduction_profiles cdp ,account_codes ac WHERE od.offender_id = :off_id AND od.caseload_id = :csld_id AND EXISTS (SELECT 1 FROM offender_deduction_receipts odp WHERE odp.offender_deduction_id = od.offender_deduction_id) AND od.deduction_type IN (SELECT dt.deduction_type FROM deduction_types dt WHERE dt.deduction_category = 'DTF' AND dt.caseload_code IN ('BOTH', 'INST')) AND od.caseload_id = cdp.caseload_id AND cdp.deduction_type = od.deduction_type AND ac.caseload_type = :caseloadtype AND ac.account_code = cdp.account_code
}

GETTING_SUB_BAL{
   SELECT coalesce (osa.balance, 0) FROM offender_sub_accounts osa ,account_codes ac ,caseload_deduction_profiles cdp WHERE osa.trust_account_code = ac.account_code AND ac.account_code = cdp.account_code AND cdp.deduction_type = :sub_ac_type AND cdp.caseload_id = :caseload AND osa.offender_id = :off_id AND osa.caseload_id = :caseload AND ac.caseload_type = :csld_type
}
GETTING_SUB_BAL_MORE{
SELECT coalesce (osa.balance, 0) FROM offender_sub_accounts osa, account_codes ac WHERE osa.trust_account_code = ac.account_code AND ac.sub_account_type = :sub_ac_type AND osa.offender_id = :off_id AND osa.caseload_id = :caseload AND ac.caseload_type = :csld_type
}

GETTING_V_MIN_BAL{
SELECT coalesce (osa.balance, 0) FROM offender_sub_accounts osa, account_codes ac WHERE osa.trust_account_code = ac.account_code AND ac.sub_account_type = :sub_ac_type AND osa.offender_id = :off_id AND osa.caseload_id = :caseload AND ac.caseload_type = :csld_type
}
