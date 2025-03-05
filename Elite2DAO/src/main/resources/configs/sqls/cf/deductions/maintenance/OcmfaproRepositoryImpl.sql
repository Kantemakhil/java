
OCMFAPRO_FEE_ACCOUNT_TYPE_RG {
	SELECT DEDUCTION_TYPE CODE, DEDUCTION_DESC DESCRIPTION, ACTIVE_FLAG, LIST_SEQ  FROM DEDUCTION_TYPES where deduction_category = 'FEE'
}

OCMFAPRO_LOCATION_RG {
    SELECT  AGY_LOC.DESCRIPTION ,  AGY_LOC.AGY_LOC_ID CODE   FROM AGENCY_LOCATIONS AGY_LOC  WHERE AGENCY_LOCATION_TYPE = :agyLocType   AND AGY_LOC_ID IN (SELECT AGY_LOC_ID  FROM CASELOAD_AGENCY_LOCATIONS  WHERE CASELOAD_ID =  (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = USER) )    AND AGY_LOC_ID NOT IN ('OUT' , 'TRN' )    AND (ACTIVE_FLAG = 'Y' OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ , DESCRIPTION , AGY_LOC_ID

}

OCMFAPRO_CREDIT_DEDUCTION_TO_RG {

SELECT to_char(account_code) code,account_name description,
CASE WHEN
posting_status_flag = 'Y'
      AND sub_account_type IS NULL
      AND caseload_type = :caseloadType THEN 'Y' ELSE 'N' END SEAL_FLAG
FROM account_codes
  ORDER BY
      account_code,
      account_name
}

OCMFAPRO_FREQUENCY_RG {
	SELECT REF_CODE.CODE  CODE       ,REF_CODE.DESCRIPTION  DESCRIPTION FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'FEE_ACT_FREQ'  AND ACTIVE_FLAG = 'Y'  AND EXPIRED_DATE  IS NULL ORDER BY REF_CODE.LIST_SEQ ,REF_CODE.CODE ,REF_CODE.DESCRIPTION
}

OCMFAPRO_CODE_RG {
	SELECT REF_CODE.CODE  CODE       ,REF_CODE.DESCRIPTION  DESCRIPTION FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'RECUR_FREQ'  AND ACTIVE_FLAG = 'Y'  AND EXPIRED_DATE  IS NULL ORDER BY REF_CODE.LIST_SEQ ,REF_CODE.CODE ,REF_CODE.DESCRIPTION
}

OCMFAPRO_RECIPT_TYPE_RG {
SELECT
    a.code,
    a.description,
    coalesce(b.active_flag, 'N') AS active_flag
FROM
    (
        SELECT
            txn_type.txn_type      code,
            txn_type.description   description
        FROM
            transaction_types txn_type
        WHERE
            txn_usage = 'R'
            AND active_flag = 'Y'
            AND all_caseload_flag = 'Y'
        ORDER BY
            txn_type.txn_type,
            txn_type.description
    ) a
    LEFT JOIN (
        SELECT
            txn_type.txn_type      code,
            txn_type.description   description,
            'Y' AS active_flag
        FROM
            transaction_types txn_type
        WHERE
            txn_usage = 'R'
            AND active_flag = 'Y'
            AND all_caseload_flag = 'Y'
            AND caseload_type = :caseloadType
        ORDER BY
            txn_type.txn_type,
            txn_type.description
    ) b ON a.code = b.code
}

OCMFAPRO_FIND_CASELOAD_DEDUCTION_PROFILES {
	SELECT delay_recapture, active_flag, caseload_id, deduction_type, effective_date, fifo_flag, fo_al_all_offender_flag, percentage, internal_priority_no, external_priority_no,ACCOUNT_CODE, co_limit_amount, co_credit_when_indigent_flag, max_monthly_amount, expiry_date, payee_person_id, payee_corporate_id, modify_user_id, modify_date, list_seq, flat_rate, minimum_trust_balance, indigent_mandatory_flag, comm_condition_type, comm_condition_code, max_recursive_amount, create_datetime, create_user_id, modify_datetime, category_type, seal_flag, frequency_code, frequency_type, back_bill, day_of_month, max_total_amount amount, non_billable_status
    	FROM caseload_deduction_profiles where  deduction_type IN (select deduction_type from deduction_types where DEDUCTION_CATEGORY = 'FEE') and caseload_id = :caseloadId
}

OCMFAPRO_INSERT_CASELOAD_DEDUCTION_PROFILES {
	INSERT INTO CASELOAD_DEDUCTION_PROFILES(DELAY_RECAPTURE ,ACTIVE_FLAG ,CASELOAD_ID ,DEDUCTION_TYPE ,EFFECTIVE_DATE ,FIFO_FLAG ,FO_AL_ALL_OFFENDER_FLAG ,PERCENTAGE ,INTERNAL_PRIORITY_NO ,EXTERNAL_PRIORITY_NO ,ACCOUNT_CODE ,CO_LIMIT_AMOUNT ,CO_CREDIT_WHEN_INDIGENT_FLAG ,MAX_MONTHLY_AMOUNT ,EXPIRY_DATE ,PAYEE_PERSON_ID ,PAYEE_CORPORATE_ID  ,MODIFY_DATE ,LIST_SEQ ,FLAT_RATE ,MINIMUM_TRUST_BALANCE ,INDIGENT_MANDATORY_FLAG ,COMM_CONDITION_TYPE ,COMM_CONDITION_CODE ,MAX_RECURSIVE_AMOUNT ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,CATEGORY_TYPE ,SEAL_FLAG, FREQUENCY_CODE, FREQUENCY_TYPE, DAY_OF_MONTH, BACK_BILL, MAX_TOTAL_AMOUNT, NON_BILLABLE_STATUS ) VALUES(:delayRecapture ,:activeFlag ,:caseloadId ,:deductionType ,:effectiveDate ,:fifoFlag ,:foAlAllOffenderFlag ,:percentage ,:internalPriorityNo ,:externalPriorityNo ,:accountCode ,:coLimitAmount ,:coCreditWhenIndigentFlag ,:maxMonthlyAmount ,:expiryDate ,:payeePersonId ,:payeeCorporateId ,:modifyDate ,:listSeq ,:flatRate ,:minimumTrustBalance ,:indigentMandatoryFlag ,:commConditionType ,:commConditionCode ,:maxRecursiveAmount ,current_timestamp ,:createUserId ,current_timestamp ,:categoryType ,:sealFlag, :frequencyCode, :frequencyType, :dayOfMonth, :backBill, :amount, :nonBillableStatus )
}

OCMFAPROP_UPDATE_CASELOAD_DEDUCTION_PROFILES {
	UPDATE CASELOAD_DEDUCTION_PROFILES set DELAY_RECAPTURE  = :delayRecapture ,ACTIVE_FLAG  = :activeFlag ,CASELOAD_ID  = :caseloadId ,DEDUCTION_TYPE  = :deductionType ,EFFECTIVE_DATE  = :effectiveDate ,FIFO_FLAG  = :fifoFlag ,FO_AL_ALL_OFFENDER_FLAG  = :foAlAllOffenderFlag ,PERCENTAGE  = :percentage ,INTERNAL_PRIORITY_NO  = :internalPriorityNo ,EXTERNAL_PRIORITY_NO  = :externalPriorityNo ,ACCOUNT_CODE  = :accountCode ,CO_LIMIT_AMOUNT  = :coLimitAmount ,CO_CREDIT_WHEN_INDIGENT_FLAG  = :coCreditWhenIndigentFlag,EXPIRY_DATE  = :expiryDate ,PAYEE_PERSON_ID  = :payeePersonId ,PAYEE_CORPORATE_ID  = :payeeCorporateId ,MODIFY_USER_ID  = :modifyUserId ,MODIFY_DATE  = current_timestamp ,LIST_SEQ  = :listSeq ,FLAT_RATE  = :flatRate ,MINIMUM_TRUST_BALANCE  = :minimumTrustBalance ,INDIGENT_MANDATORY_FLAG  = :indigentMandatoryFlag ,COMM_CONDITION_TYPE  = :commConditionType ,COMM_CONDITION_CODE  = :commConditionCode ,MAX_RECURSIVE_AMOUNT  = :maxRecursiveAmount  ,MODIFY_DATETIME  = current_timestamp ,CATEGORY_TYPE  = :categoryType ,SEAL_FLAG  = :sealFlag, FREQUENCY_CODE =:frequencyCode, FREQUENCY_TYPE = :frequencyType, DAY_OF_MONTH = :dayOfMonth, BACK_BILL = :backBill, MAX_TOTAL_AMOUNT = :amount, NON_BILLABLE_STATUS = :nonBillableStatus  where CASELOAD_ID=:caseloadId AND DEDUCTION_TYPE=:deductionType
}

OCMFAPRO_DELETE_CASELOAD_DEDUCTION_PROFILES {
	DELETE FROM CASELOAD_DEDUCTION_PROFILES  where CASELOAD_ID=:caseloadId AND DEDUCTION_TYPE=:deductionType
}

OCMFAPRO_FIND_CASELOAD_DED_BENEFICIARIES {
 	SELECT ODB.* ,(SELECT CO_CREDIT_WHEN_INDIGENT_FLAG FROM CASELOAD_DEDUCTION_PROFILES WHERE CASELOAD_ID = ODB.CASELOAD_ID AND DEDUCTION_TYPE = ODB.DEDUCTION_TYPE)  CO_CREDIT_WHEN_INDIGENT_FLAG
FROM CASELOAD_DED_BENEFICIARIES ODB  WHERE ODB.CASELOAD_ID = :caseloadId AND ODB.DEDUCTION_TYPE = :deductionType
}

OCMFAPRO_INSERT_CASELOAD_DED_BENEFICIARIES {
insert into CASELOAD_DED_BENEFICIARIES(CASELOAD_DED_BENEFICIARY_ID, CASELOAD_ID, DEDUCTION_TYPE, PERSON_ID, CORPORATE_ID, PRIORITY, AMOUNT, percent, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG ) 
values(:caseloadDedBeneficiaryId, :caseloadId, :deductionType, :personId, :corporateId, :priority, :amount, :percent, current_timestamp, :createUserId, current_timestamp, :sealFlag)
}

OCMFAPRO_UPDATE_CASELOAD_DED_BENEFICIARIES {
update CASELOAD_DED_BENEFICIARIES set PERSON_ID = :personId, CORPORATE_ID = :corporateId, PRIORITY = :priority, AMOUNT = :amount, modify_datetime = current_timestamp, modify_user_id = :modifyUserId where CASELOAD_DED_BENEFICIARY_ID = :caseloadDedBeneficiaryId
}

OCMFAPRO_DELETE_CASELOAD_DED_BENEFICIARIES {
	DELETE FROM CASELOAD_DED_BENEFICIARIES WHERE CASELOAD_DED_BENEFICIARY_ID = :caseloadDedBeneficiaryId
}


OCMFAPRO_CSLD_DBEN_PREINSERT {
SELECT NEXTVAL('CASELOAD_DED_BENEFICIARY_ID')
}

OCMFAPRO_FIND_CASELOAD_DEDUCTION_DETAILS {
 	SELECT CASELOAD_ID,DEDUCTION_TYPE,RECEIPT_TXN_TYPE,PERCENTAGE,MODIFY_USER_ID,MODIFY_DATE,LIST_SEQ,FLAT_RATE,MINIMUM_TRUST_BALANCE_FLAG,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,SEAL_FLAG FROM CASELOAD_DEDUCTION_DETAILS  WHERE CASELOAD_ID = :caseloadId AND DEDUCTION_TYPE = :deductionType
 	--ROWIDTOCHAR(ROWID) ROW_ID 
}
OCMFAPRO_INSERT_CASELOAD_DEDUCTION_DETAILS {
insert into CASELOAD_DEDUCTION_DETAILS(CASELOAD_ID, DEDUCTION_TYPE, RECEIPT_TXN_TYPE, PERCENTAGE, MODIFY_DATE, LIST_SEQ, FLAT_RATE, MINIMUM_TRUST_BALANCE_FLAG, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG) 
values( :caseloadId, :deductionType, :receiptTxnType, :percentage, current_timestamp, :listSeq, :flatRate, :minimumTrustBalanceFlag, current_timestamp, :createUserId, current_timestamp, :sealFlag)
}

OCMFAPRO_UPDATE_CASELOAD_DEDUCTION_DETAILS {
update CASELOAD_DEDUCTION_DETAILS set RECEIPT_TXN_TYPE = :receiptTxnType, PERCENTAGE = :percentage, FLAT_RATE = :flatRate, MINIMUM_TRUST_BALANCE_FLAG = :minimumTrustBalanceFlag, modify_user_id = :modifyUserId, modify_datetime = current_timestamp where DEDUCTION_TYPE = :deductionType and CASELOAD_ID = :caseloadId and ROWID = :rowId
}

OCMFAPRO_DELETE_CASELOAD_DEDUCTION_DETAILS {
	DELETE FROM CASELOAD_DEDUCTION_DETAILS WHERE DEDUCTION_TYPE = :deductionType AND CASELOAD_ID = :caseloadId AND RECEIPT_TXN_TYPE = :receiptTxnType
}

OCMFAPRO_CGFKCHK_CSLD_DBEN_CSLD_DBEN_C {
	SELECT CORP.CASELOAD_ID ,CORP.CORPORATE_NAME, CORP.CORPORATE_ID FROM   CORPORATES CORP WHERE  CORP.CORPORATE_ID = :corporateId
}

OCMFAPRO_CGFKCHK_CSLD_DBEN_CSLD_DBEN_P {
	SELECT PER.FIRST_NAME ,PER.LAST_NAME, PER.PERSON_ID FROM   PERSONS PER WHERE  PER.PERSON_ID = :personId
}

OCMFAPRO_CALCULATE_ON {
	select description from reference_codes where domain = 'BALANCE_TYPE' and code = (SELECT FROM_BALANCE_TYPE FROM DEDUCTION_TYPES WHERE DEDUCTION_TYPE = :deductionType)
}

OCMFAPRO_UPDATE_CASELOAD_DED_BENEFICIARIES_AMOUNT {
	UPDATE caseload_ded_beneficiaries SET amount = 999999999.99,modify_user_id = :modifyUserId, modify_datetime = current_timestamp  WHERE caseload_id = :caseloadId AND deduction_type = :deductionType
}

OCMFAPRO_UPDATE_CASELOAD_DED_BENEFICIARIES_PERCENTAGE {
	UPDATE caseload_ded_beneficiaries SET percent = :percent,modify_user_id = :modifyUserId,modify_datetime = current_timestamp  WHERE caseload_ded_beneficiary_id = :caseloadDedBeneficiaryId
}

OCMFAPRO_DED_PRIORITIES {
	SELECT caseload_id, deduction_type, priority, amount, caseload_ded_beneficiary_id FROM caseload_ded_beneficiaries WHERE caseload_id = :caseloadId AND deduction_type = :deductionType
}

OCMFAPRO_GET_PRIORITY_AMOUNT {
	SELECT coalesce (SUM (amount), 0) FROM caseload_ded_beneficiaries  WHERE caseload_id = :caseloadId AND deduction_type = :deductionType AND priority = :priority
}

OCMFAPRO_CALC_BEN_TOTAL {
	SELECT COUNT(*) p_ben_count, coalesce (SUM (csld_db.amount), 0) p_ben_total FROM caseload_ded_beneficiaries csld_db
       WHERE csld_db.caseload_id = :caseloadId AND csld_db.deduction_type = :deductionType
}

OCMFAPRO_GET_RECEIPT_PERCENTAGE {
	SELECT coalesce(MAX (coalesce (percentage, 0)), 0) FROM caseload_deduction_details  WHERE caseload_id = :caseloadId   AND deduction_type = :deductionType
}
OCMFAPRO_UPDATE_CASELOAD_DEDUCTION_PROFILES_PERCENTAGE {
	UPDATE caseload_deduction_profiles  SET percentage = :percentage,modify_user_id = :modifyUserId,modify_datetime = current_timestamp  WHERE caseload_id = :caseloadId  AND deduction_type = :deductionType
}

OCMFAPRO_FIND_OFFDED_EXECUTEQUERY{
	SELECT * FROM OFF_FEE_ACCOUNT_PROFILE where fee_code = :deductionType
}

OCMFAPRO_OFFDED_UPDATE{
	UPDATE OFF_FEE_ACCOUNT_PROFILE set AMOUNT=:amount,DAY_OF_MONTH=:dayOfMonth,modify_user_id = :modifyUserId, modify_datetime = current_timestamp where OFFENDER_FEE_ID=:offenderFeeId
}

OCMFAPRO_SYSPFL_FIND_SYSTEM_PROFILES {
SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES where PROFILE_CODE = 'CF_DEDUCTION'
}

OCMFAPRO_GET_EXISTING_BEN_DATA {
SELECT * FROM OFF_FEE_DED_BENEFICIARIES WHERE OFFENDER_FEE_ID=:feeId
}

OCMFAPRO_BEN_DATA_UPDATE {
UPDATE OFF_FEE_DED_BENEFICIARIES set PERSON_ID=:personId,CORPORATE_ID=:corporateId,PRIORITY=:priority, AMOUNT = :amount,modify_user_id = :modifyUserId, modify_datetime = current_timestamp
where OFF_FEE_DED_BENEFICIARY_ID=:offFeeDedBeneficiaryId
}

OCMFAPRO_GET_EXISTING_DED_DATA {
SELECT * FROM OFF_FEE_DED_RECEIPTS WHERE OFFENDER_FEE_ID=:feeId
}

OCMFAPRO_DED_DATA_UPDATE {
UPDATE OFF_FEE_DED_RECEIPTS set RECEIPT_PERCENT=:receiptPercent,LIST_SEQ=:listSeq,FLAT_RATE=:flatRate,MINIMUM_TRUST_BALANCE_FLAG=:minimumTrustBalanceFlag,
RECEIPT_TXN_TYPE=:receiptTxnType,modify_user_id = :modifyUserId, modify_datetime = current_timestamp where OFF_FEE_DED_RECEIPT_ID=:offFeeDedReceiptId
}

OCMFAPRO_UPDATE_AMOUNT_OFF_FEE_ACT {
    update OFF_FEE_ACCOUNT_PROFILE set AMOUNT = :amount,modify_user_id = :modifyUserId, modify_datetime = current_timestamp where OFFENDER_FEE_ID = :feeId
}

OCMFAPRO_DELETE_EXSISTING_BEN_DATA {
    DELETE FROM OFF_FEE_DED_BENEFICIARIES where OFFENDER_FEE_ID=:feeId
}

OCMFAPRO_CSLDDBEN_INSERT{
	INSERT INTO OFF_FEE_DED_BENEFICIARIES ( OFF_FEE_DED_BENEFICIARY_ID, PERSON_ID,
CORPORATE_ID, PRIORITY, AMOUNT, PERCENT,  SEAL_FLAG, OFFENDER_FEE_ID,create_user_id, create_datetime, modify_datetime)
VALUES (NEXTVAL('CASELOAD_DED_BENEFICIARY_ID'),:personId,:corporateId,:priority,:amount,:percent,:sealFlag,:offenderFeeId,:createUserId, current_timestamp, current_timestamp)

}

OCMFAPRO_GET_AMOUNT{
select max_total_amount from caseload_deduction_profiles where caseload_id = :caseloadId and deduction_type = :deductionType
}

OCMFAPRO_DELETE_EXSISTING_DED_DETAILS {
    DELETE FROM OFF_FEE_DED_RECEIPTS where OFFENDER_FEE_ID=:feeId
}

OCMFAPRO_CSLDDED_DETAILS_INSERT{
	INSERT INTO OFF_FEE_DED_RECEIPTS (
OFF_FEE_DED_RECEIPT_ID,  RECEIPT_TXN_TYPE, RECEIPT_PERCENT, LIST_SEQ, FLAT_RATE, MINIMUM_TRUST_BALANCE_FLAG,  SEAL_FLAG, OFFENDER_FEE_ID,create_user_id, create_datetime, modify_datetime)
VALUES(NEXTVAL('OFF_FEE_DED_RECEIPT_ID'),:receiptTxnType,:receiptPercent,:listSeq,:flatRate,:minimumTrustBalanceFlag,:sealFlag,:offenderFeeId,:createUserId, current_timestamp, current_timestamp)
}

OCMFAPRO_SYSPFL_FIND_SYSTEM_PROFILES_FOR_ALL_OFFENDER {
SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES where PROFILE_CODE = 'CF_AUTOFEECR'
}

OCMFAPRO_GET_MAXIMUM_FEE_ACCCOUNT_AMOUNT {
select MAX_TOTAL_AMOUNT from CASELOAD_DEDUCTION_PROFILES where DEDUCTION_TYPE=:feeActType AND CASELOAD_ID = :caseloadId
}
OCMFAPRO_SYSPFL_FIND_SYSTEM_PROFILES_UPDATE_FEE_ACCOUNT {
SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES where PROFILE_CODE = 'UPD_OFF_DEDU'
}
