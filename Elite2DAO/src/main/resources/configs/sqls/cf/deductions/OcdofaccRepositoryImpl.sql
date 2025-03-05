
OCDOFACC_FEE_ACCOUNT_TYPE_RG {
select code, description, deduction_category, from_balance_type, caseload_restricted_flag, increment_payables_flag, expiry_date, active_flag, update_allowed_flag, modify_user_id, modify_date, list_seq, clp_flag, caseload_code, parent_deduction_type, percentage_of_parent, create_datetime, create_user_id, modify_datetime, seal_flag from ( select dt.deduction_type code, dt.deduction_desc description, dt.deduction_category, dt.from_balance_type, dt.caseload_restricted_flag, dt.increment_payables_flag, dt.expiry_date, cdp.active_flag, dt.update_allowed_flag, dt.modify_user_id, dt.modify_date, dt.list_seq, dt.clp_flag, dt.caseload_code, dt.parent_deduction_type, dt.percentage_of_parent, dt.create_datetime, dt.create_user_id, dt.modify_datetime, dt.seal_flag, cdp.effective_date from deduction_types dt, caseload_deduction_profiles cdp where dt.deduction_category = 'FEE' and dt.deduction_type = cdp.deduction_type and cdp.caseload_id = :caseloadId ) a
}

OCDOFACC_FIND_CASELOAD_DEDUCTION_PROFILES {
 SELECT delay_recapture, active_flag, caseload_id, deduction_type, effective_date, fifo_flag, fo_al_all_offender_flag, percentage, internal_priority_no, external_priority_no,ACCOUNT_CODE, co_limit_amount, co_credit_when_indigent_flag, max_monthly_amount, max_total_amount amount, expiry_date, payee_person_id, payee_corporate_id, modify_user_id, modify_date, list_seq, flat_rate, minimum_trust_balance, indigent_mandatory_flag, comm_condition_type, comm_condition_code, max_recursive_amount, create_datetime, create_user_id, modify_datetime, category_type, seal_flag, frequency_code, frequency_type, back_bill, day_of_month,non_billable_status FROM caseload_deduction_profiles where caseload_id = :caseloadId and deduction_type = :deductionType
}

OCDOFACC_CALCULATE_ON {
SELECT FROM_BALANCE_TYPE FROM DEDUCTION_TYPES WHERE DEDUCTION_TYPE = :deductionType
}
OCDOFACC_RECIPT_TYPE_RG {
select A.CODE,A.DESCRIPTION, coalesce(B.active_flag,'N') as active_flag from (SELECT txn_type.txn_type code, txn_type.description description FROM transaction_types txn_type ORDER BY txn_type.txn_type, txn_type.description)A left join (SELECT txn_type.txn_type code, txn_type.description description,'Y' as active_flag FROM transaction_types txn_type WHERE txn_usage = 'R' AND active_flag = 'Y' AND all_caseload_flag = 'Y' AND caseload_type = :caseloadType ORDER BY txn_type.txn_type, txn_type.description)B on A.CODE=B.CODE
}
OCDOFACC_FIND_OFFDED_EXECUTEQUERY{
 SELECT ofap.offender_fee_id, ofap.offender_book_id, ofap.caseload_id, c.description, ofap.fee_code, ofap.odp, ofap.amount, ofap.day_of_month, ofap.start_date, ofap.effective_date, ofap.expiry_date, ofap.fee_act_status, CASE WHEN ofap.fee_act_status = 'A' THEN 1 WHEN ofap.fee_act_status = 'P' THEN 2 WHEN ofap.fee_act_status = 'S' THEN 3 ELSE 4 END sts_seq, ofap.status_effective_date, ofap.info_number, ofap.service_date, ofap.comment_text, ofap.create_datetime, ofap.create_user_id, ofap.modify_datetime, ofap.modify_user_id, ofap.seal_flag, (select max(status_effective_date) from OFF_FEE_ACCOUNT_PROFILE_HTY where offender_fee_id = ofap.offender_fee_id and fee_act_status = 'S') as supvPeriodDate, (select count(*) from off_fee_account_overrides where offender_fee_id=ofap.offender_fee_id) overrideCount, (select count(*) from off_fee_bills where offender_Fee_id = ofap.offender_fee_id) billCount, case when (select offender_book_id from off_supervision_sts_hty osh where ERROR_FLAG='N' and offender_sup_id=(select max(offender_sup_id) from off_supervision_sts_hty where offender_book_id = ofap.offender_book_id) and osh.SUP_STATUS in (SELECT CODE FROM REFERENCE_CODES WHERE DOMAIN ='SUP_STATUS' AND CODE = osh.SUP_STATUS AND PARENT_CODE = 'B'))> 0 then 'Y' else 'N' end BILLABLE_FLAG FROM off_fee_account_profile ofap, caseloads c WHERE ofap.caseload_id = c.caseload_id AND ofap.offender_book_id = :offenderBookId ORDER BY caseload_id, sts_seq, offender_fee_id
}

OCDOFACC_OFFDED_PREINSERT{
	SELECT  NEXTVAL('DEDUCTION_ID') FROM DUAL
}

OCDOFACC_OFFDED_INSERT{
INSERT INTO OFF_FEE_ACCOUNT_PROFILE(CASELOAD_ID,OFFENDER_FEE_ID,ODP,FEE_CODE,AMOUNT,DAY_OF_MONTH,START_DATE,EFFECTIVE_DATE,EXPIRY_DATE,FEE_ACT_STATUS,STATUS_EFFECTIVE_DATE, INFO_NUMBER,SERVICE_DATE,COMMENT_TEXT,OFFENDER_BOOK_ID,CREATE_DATETIME,CREATE_USER_Id) VALUES(:caseloadId,:offenderFeeId,:odp,:feeCode,:amount,:dayOfMonth,:startDate,:effectiveDate,:expiryDate,:feeActStatus,:statusEffectiveDate,:infoNumber,:serviceDate,:commentText,:offenderBookId,current_timestamp,:createUserId) 
}

OCDOFACC_OFFDED_UPDATE{
UPDATE OFF_FEE_ACCOUNT_PROFILE set CASELOAD_ID=:caseloadId,OFFENDER_FEE_ID=:offenderFeeId,FEE_CODE=:feeCode,AMOUNT=:amount,DAY_OF_MONTH=:dayOfMonth, START_DATE=:startDate,EFFECTIVE_DATE=:effectiveDate,EXPIRY_DATE=:expiryDate,FEE_ACT_STATUS=:feeActStatus,STATUS_EFFECTIVE_DATE=:statusEffectiveDate, INFO_NUMBER=:infoNumber,SERVICE_DATE=:serviceDate,COMMENT_TEXT=:commentText, ODP=:odp, MODIFY_USER_ID=:modifyUserId, MODIFY_DATETIME=current_timestamp where OFFENDER_FEE_ID=:offenderFeeId
}

OCDOFACC_OFFDED_DELETE{
	DELETE FROM OFF_FEE_ACCOUNT_PROFILE where OFFENDER_FEE_ID=:offenderFeeId
}

OCDOFACC_FIND_CASELOAD_DED_BENEFICIARIES {
 SELECT ODB.* ,(SELECT CO_CREDIT_WHEN_INDIGENT_FLAG FROM CASELOAD_DEDUCTION_PROFILES WHERE CASELOAD_ID = ODB.CASELOAD_ID AND DEDUCTION_TYPE = ODB.DEDUCTION_TYPE) CO_CREDIT_WHEN_INDIGENT_FLAG FROM CASELOAD_DED_BENEFICIARIES ODB WHERE ODB.CASELOAD_ID = :caseloadId AND ODB.DEDUCTION_TYPE = :deductionType
}

OCDOFACC_CSLDDBEN_PREINSERT{
SELECT  NEXTVAL('OFF_FEE_DED_BENEFICIARY_ID') FROM    DUAL
}


OCDOFACC_CSLDDBEN_INSERT{
 INSERT INTO OFF_FEE_DED_BENEFICIARIES ( OFF_FEE_DED_BENEFICIARY_ID, PERSON_ID, CORPORATE_ID, PRIORITY, AMOUNT, PERCENT, SEAL_FLAG, OFFENDER_FEE_ID,CREATE_DATETIME,CREATE_USER_ID) VALUES (NEXTVAL('OFF_FEE_DED_BENEFICIARY_ID'),:personId,:corporateId,:priority,:amount,:percent,:sealFlag,:offenderFeeId,current_timestamp,:createUserId)
}

OCDOFACC_CSLDDBEN_UPDATE{
 UPDATE OFF_FEE_DED_BENEFICIARIES set PERSON_ID=:personId,CORPORATE_ID=:corporateId,PRIORITY=:priority, AMOUNT = :amount,MODIFY_DATETIME=current_timestamp,MODIFY_USER_ID=:modifyUserId where OFF_FEE_DED_BENEFICIARY_ID=:offFeeDedBeneficiaryId
}

OCDOFACC_CSLDDBEN_DELETE{
	DELETE FROM OFF_FEE_DED_BENEFICIARIES where OFF_FEE_DED_BENEFICIARY_ID = :offFeeDedBeneficiaryId
}

OCDOFACC_FIND_CASELOAD_DEDUCTION_DETAILS {
 SELECT caseload_id, deduction_type, receipt_txn_type, percentage, modify_user_id, modify_date, list_seq, flat_rate, minimum_trust_balance_flag, create_datetime, create_user_id, modify_datetime, percentage RECEIPT_PERCENT, seal_flag FROM CASELOAD_DEDUCTION_DETAILS WHERE CASELOAD_ID = :caseloadId AND DEDUCTION_TYPE = :deductionType
}


OCDOFACC_CSLDDD_INSERT{
 INSERT INTO OFF_FEE_DED_RECEIPTS ( OFF_FEE_DED_RECEIPT_ID, RECEIPT_TXN_TYPE, RECEIPT_PERCENT, LIST_SEQ, FLAT_RATE, MINIMUM_TRUST_BALANCE_FLAG, SEAL_FLAG, OFFENDER_FEE_ID,CREATE_DATETIME,CREATE_USER_ID) VALUES(NEXTVAL('OFF_FEE_DED_RECEIPT_ID'),:receiptTxnType,:receiptPercent,:listSeq,:flatRate,:minimumTrustBalanceFlag,:sealFlag,:offenderFeeId,current_timestamp,:createUserId)
}

OCDOFACC_CSLDDD_UPDATE{
 UPDATE OFF_FEE_DED_RECEIPTS set RECEIPT_PERCENT=:receiptPercent,LIST_SEQ=:listSeq,FLAT_RATE=:flatRate,MINIMUM_TRUST_BALANCE_FLAG=:minimumTrustBalanceFlag, RECEIPT_TXN_TYPE=:receiptTxnType,MODIFY_DATETIME=current_timestamp,MODIFY_USER_ID=:modifyUserId where OFF_FEE_DED_RECEIPT_ID=:offFeeDedReceiptId
}

OCDOFACC_CSLDDD_DELETE{
	DELETE FROM OFF_FEE_DED_RECEIPTS where OFF_FEE_DED_RECEIPT_ID=:offFeeDedReceiptId
}

OCDOFACC_CGFKCHK_CSLD_DBEN_CSLD_DBEN_P {
	SELECT PER.FIRST_NAME ,PER.LAST_NAME, PER.PERSON_ID FROM   PERSONS PER WHERE  PER.PERSON_ID = :personId
}

OCDOFACC_CGFKCHK_CSLD_DBEN_CSLD_DBEN_C {
	SELECT CORP.CASELOAD_ID ,CORP.CORPORATE_NAME, CORP.CORPORATE_ID FROM   CORPORATES CORP WHERE  CORP.CORPORATE_ID = :corporateId
}

OCDOFACC_FEE_OVERRIDE_DETAILS {
	select count(*) from fee_override_details where fee_id=:fee_id
}


OCDOFACC_DED_PRIORITIES {
	SELECT caseload_id, deduction_type, priority, amount, caseload_ded_beneficiary_id FROM caseload_ded_beneficiaries WHERE caseload_id = :caseloadId AND deduction_type = :deductionType
}

OCDOFACC_GET_PRIORITY_AMOUNT {
	SELECT COALESCE (SUM (amount), 0) FROM OFF_FEE_DED_BENEFICIARIES  WHERE  OFFENDER_FEE_ID =:offenderFeeId AND priority = :priority
}

OCDOFACC_CALC_BEN_TOTAL {
SELECT COUNT(*) p_ben_count, COALESCE (SUM (csld_db.amount), 0) p_ben_total FROM OFF_FEE_DED_BENEFICIARIES csld_db WHERE csld_db.OFFENDER_FEE_ID = :offenderFeeId 
}


OCDOFACC_UPDATE_CASELOAD_DED_BENEFICIARIES_PERCENTAGE {
	UPDATE OFF_FEE_DED_BENEFICIARIES SET percent = :percent, MODIFY_DATETIME=current_timestamp,MODIFY_USER_ID=:modifyUserId WHERE OFF_FEE_DED_BENEFICIARY_ID=:offFeeDedBeneficiaryId
}
OCDOFACC_GET_CASELOAD_LOV {
select CASELOAD_ID CODE,DESCRIPTION from caseloads where caseload_type = 'COMM' and trust_accounts_flag = 'Y'
}

OCDOFACC_GET_BACK_BILL {
 select FO_AL_ALL_OFFENDER_FLAG, BACK_BILL, non_billable_status,max_total_amount,FREQUENCY_TYPE,FREQUENCY_CODE,DAY_OF_MONTH from caseload_deduction_profiles where deduction_type = :feeCode AND CASELOAD_ID=:caseloadId
}

OCDOFACC_FEE_FIND_CASELOAD_DED_BENEFICIARIES {
 SELECT * FROM OFF_FEE_DED_BENEFICIARIES WHERE OFFENDER_FEE_ID=:offenderFeeId
}


OCDOFACC_FEE_FIND_CASELOAD_DEDUCTION_DETAILS {
 SELECT OFF_FEE_DED_RECEIPT_ID, OFFENDER_FEE_ID, RECEIPT_TXN_TYPE, RECEIPT_PERCENT, modify_user_id, list_seq, flat_rate, minimum_trust_balance_flag, create_datetime, create_user_id, modify_datetime, seal_flag FROM OFF_FEE_DED_RECEIPTS WHERE OFFENDER_FEE_ID=:offenderFeeId
}
OCDOFACC_FIND_OFFDED_PREVIOUSTAB_EXECUTEQUERY {
SELECT * FROM OFF_FEE_ACCOUNT_PROFILE where OFFENDER_BOOK_ID=:offenderBookId 
}

OCDOFACC_SYSPFL_FIND_SYSTEM_PROFILES {
SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES where PROFILE_CODE = 'CF_AUTOFEECR'
}


OCDOFACC_SYSPFL_FIND_LONG_SUPER_EXPIRY_DATE_SYSTEM_PROFILES {
SELECT * from OFF_SUPERVISION_DETAILS where OFFENDER_BOOK_ID=:offenderBookId
}


OCDOFACC_DED_PRIORITIES_FEE_BENF {
	SELECT amount, OFFENDER_FEE_ID, OFF_FEE_DED_BENEFICIARY_ID, PRIORITY FROM OFF_FEE_DED_BENEFICIARIES WHERE  OFFENDER_FEE_ID =:offenderFeeId
}

OCDOFACC_GET_DESCRIPTION {
    select description from reference_codes where code = :code and domain = :domain
}

OCDOFACC_GET_FREEQUENCY_VALUES {
select FREQUENCY_CODE,FREQUENCY_TYPE,non_billable_status from caseload_deduction_profiles where CASELOAD_ID=:location AND DEDUCTION_TYPE=:feeCode
}

OCDOFACC_SUPV_LONG_UPDATE {
update OFF_SUPERVISION_DETAILS set LONGEST_SUPV_EXP_DATE=:longestSupvExpDate,MODIFY_DATETIME=current_timestamp,MODIFY_USER_ID=:modifyUserId where OFFENDER_BOOK_ID=:offenderBookId
}
OCDOFACC_SUPV_LONG_INSERT {
insert into OFF_SUPERVISION_DETAILS(LONGEST_SUPV_EXP_DATE,OFFENDER_BOOK_ID,CREATE_DATETIME,CREATE_USER_ID) values (:longestSupvExpDate,:offenderBookId,current_timestamp,:createUserId)
}

OCDOFACC_SUPV_FEE_ACT_PROFILES {
select OFAP.OFFENDER_FEE_ID, OFAP.OFFENDER_BOOK_ID, OFAP.CASELOAD_ID, OFAP.FEE_CODE, OFAP.ODP, OFAP.AMOUNT, OFAP.DAY_OF_MONTH, OFAP.START_DATE, OFAP.EFFECTIVE_DATE, OFAP.EXPIRY_DATE, OFAP.FEE_ACT_STATUS, OFAP.STATUS_EFFECTIVE_DATE, OFAP.INFO_NUMBER, OFAP.SERVICE_DATE, OFAP.COMMENT_TEXT, OFAP.CREATE_DATETIME, OFAP.CREATE_USER_ID, OFAP.MODIFY_DATETIME, OFAP.MODIFY_USER_ID, OFAP.SEAL_FLAG, OSD.LONGEST_SUPV_EXP_DATE from OFF_FEE_ACCOUNT_PROFILE OFAP LEFT OUTER JOIN OFF_SUPERVISION_DETAILS OSD ON OFAP.OFFENDER_BOOK_ID = OSD.OFFENDER_BOOK_ID
}

OCDOFACC_UPDATE_FEE_ACT_STATUS_LONG_SUPV_DATE {
    UPDATE OFF_FEE_ACCOUNT_PROFILE set FEE_ACT_STATUS=:feeActStatus, STATUS_EFFECTIVE_DATE=current_timestamp , EXPIRY_DATE=:expiryDate,MODIFY_DATETIME=current_timestamp,MODIFY_USER_ID=:modifyUserId  where OFFENDER_FEE_ID=:offenderFeeId
}

OCDOFACC_RETRIVE_FEE_ACCOUNT_HISTORY_DATA {
select * from OFF_FEE_ACCOUNT_PROFILE_HTY where OFFENDER_FEE_ID=:offenderFeeId
}
OCDOFACC_FEE_ACCOUNT_PROFILE_HISTORY_DELETE {
DELETE FROM OFF_FEE_ACCOUNT_PROFILE_HTY where OFFENDER_FEE_ID=:offenderFeeId
}
OCDOFACC_SYSPFL_DEDUDCTION_FIND_SYSTEM_PROFILES {
SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES where PROFILE_CODE = 'CF_DEDUCTION'
}

OCDOFACC_FIND_OFFENDR_ID_FROM_BOOKINGS_OBJECT {
SELECT * FROM OFFENDER_BOOKINGS where offender_book_id = :offenderBookId
}

OCDOFACC_FIND_OFFENDR_BOOKING_IDS_FROM_BOOKINGS_OBJECT {
SELECT * FROM OFFENDER_BOOKINGS where root_offender_id = :offenderId
}
OCDOFACC_GET_MAXIMUM_FEE_ACCCOUNT_AMOUNT {
select AMOUNT from OFF_FEE_ACCOUNT_PROFILE where OFFENDER_FEE_ID=:offenderFeeId
}

OCDOFACC_OFF_ACNT_PROFILE_UPDATE{
UPDATE off_fee_account_profile SET caseload_id = :caseloadId, offender_fee_id = :offenderFeeId, fee_code = :feeCode, info_number = :infoNumber, service_date = :serviceDate, comment_text = :commentText, odp = :odp,modify_datetime=current_timestamp,modify_user_id=:modifyUserId WHERE offender_fee_id = :offenderFeeId
}

OCDOFACC_FIND_OFFDED_CURRENT_RECORD_EXECUTEQUERY {
SELECT * FROM OFF_FEE_ACCOUNT_PROFILE where OFFENDER_FEE_ID=:offenderFeeId 
}

OCDOFACC_OFF_ACNT_PROFILE_SUPV_STS_HISTORY{
DELETE FROM OFF_SUPERVISION_DETAILS where offender_book_id=:P_off_book_id
}
OCDOFACC_OFF_ACNT_PROFILE_UPDATE_EXPIRE_DATE{
UPDATE off_fee_account_profile fap SET fap.expiry_date = :longestSupvExpDate,modify_datetime=current_timestamp,modify_user_id=:modifyUserId WHERE fap.offender_book_id = :offenderBookId AND fap.fee_code IN ( SELECT deduction_type FROM caseload_deduction_profiles WHERE caseload_id = fap.caseload_id AND non_billable_status = 'Y' )
}

OCDOFACC_GET_OFFENDER_EVENT_DATE{
SELECT EVENT_DATE FROM OFFENDER_BOOKING_EVENTS WHERE BOOKING_EVENT_CODE='CAC' AND  OFFENDER_BOOK_ID = :offenderBookId
}

OCDOFACC_FIND_OFFDED_CASELOAD_BASED_EXECUTEQUERY {
select * from OFF_FEE_ACCOUNT_PROFILE where OFFENDER_BOOK_ID = :offenderBookId and CASELOAD_ID=:caseloadId and FEE_CODE=:feeCode

}

OCDOFACC_GET_OLD_DATA_OFF_FEE_BILLS{
select BILL_AR_START_DATE , BILL_AR_DUE_DATE from OFF_FEE_BILLS where bill_id =:billId
}
