 
OCDRECEI_FIND_CGFKOFFTXNDSPINFORMATIONN {
SELECT  INFO_NUMBER CODE,INFO_NUMBER DESCRIPTION FROM OFF_FEE_ACCOUNT_PROFILE WHERE OFFENDER_BOOK_ID = :offenderBookId AND INFO_NUMBER  IS NOT NULL
}

OCDRECEI_OFFTXNTXNTYPERECORDGROUP {
 select
	TXN_TYPE.TXN_TYPE CODE 
	,
	TXN_TYPE.DESCRIPTION
from
	TRANSACTION_TYPES TXN_TYPE
where
	CASELOAD_TYPE = (
	select
		CS.CASELOAD_TYPE
	from
		CASELOADS CS
	where
		CASELOAD_ID = (
		select
			WORKING_CASELOAD_ID
		from
			STAFF_MEMBERS
		where
			USER_ID = :createUserId ) )
	and TXN_TYPE.TXN_TYPE in (
	select
		TXN_TYPE
	from
		TRANSACTION_OPERATIONS
	where
		MODULE_NAME = 'OCDRECEI'
		and CASELOAD_ID = (
		select
			WORKING_CASELOAD_ID
		from
			STAFF_MEMBERS
		where
			USER_ID = :createUserId ) )
	and TXN_TYPE.TXN_TYPE not in (
	select
		CREDIT_OBLIGATION_TYPE
	from
		TRANSACTION_TYPES
	where
		(CREDIT_OBLIGATION_TYPE is not null
			and CREDIT_OBLIGATION_TYPE::text <> '') )
order by
	1

}

OCDRECEI_OFFTXN_FIND_OFFENDER_TRANSACTIONS {
 	SELECT  * FROM OFFENDER_TRANSACTIONS where OFFENDER_ID =  :offenderId
}
OCDRECEI_OFFTXN_INSERT_OFFENDER_TRANSACTIONS {
	INSERT INTO OFFENDER_TRANSACTIONS() VALUES(:)
}

OCDRECEI_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT *  FROM SYSTEM_PROFILES  
}

OCDRECEI_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_TRANSACTIONS O WHERE O.CASELOAD_ID = :CASELOADID AND O.OFFENDER_ID = :ROOTOFFENDERID
}

OCDRECEI_CGFKCHK_OFF_TXN_OFF_TXN_OFF_D_ {
	SELECT OD.INFORMATION_NUMBER
FROM deduction_types dt, caseload_deduction_profiles cdp, offender_deductions od
LEFT OUTER JOIN offender_deduction_shadows ods ON (OD.OFFENDER_DEDUCTION_ID = ODS.OFFENDER_DEDUCTION_ID)
LEFT OUTER JOIN offender_mon_deductions omd ON (OD.OFFENDER_DEDUCTION_ID = OMD.OFFENDER_DEDUCTION_ID)
WHERE OD.OFFENDER_ID = :OFFENDERID AND OD.DEDUCTION_TYPE = DT.DEDUCTION_TYPE AND DT.CASELOAD_CODE IN ('BOTH', :CASELOADTYPE) AND EXISTS (SELECT 1
     FROM OFFENDER_BENEFICIARIES OB
     WHERE
       OB.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND
       OB.OFFENDER_ID = :OFFENDERID) AND EXISTS (SELECT 1
     FROM OFFENDER_DEDUCTION_RECEIPTS
     WHERE
       RECEIPT_TXN_TYPE = :TXNTYPE AND
       OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) AND coalesce(OD.ADJUSTMENT_REASON_CODE::text, '') = '' AND date_trunc('day', OD.EFFECTIVE_DATE) <= date_trunc('day', LOCALTIMESTAMP)  AND OD.CASELOAD_ID = CDP.CASELOAD_ID AND CDP.DEDUCTION_TYPE = OD.DEDUCTION_TYPE AND (DT.DEDUCTION_CATEGORY <> 'ALCN' AND ( (coalesce(OD.MAX_TOTAL_AMOUNT,0) > coalesce(OD.DEDUCTION_AMOUNT,0) AND (OD.MAX_TOTAL_AMOUNT IS NOT NULL AND OD.MAX_TOTAL_AMOUNT::text <> '')) OR (coalesce(OD.MAX_MONTHLY_AMOUNT,0) > coalesce(OMD.DEDUCTION_AMOUNT,0) AND (OD.MAX_MONTHLY_AMOUNT IS NOT NULL AND OD.MAX_MONTHLY_AMOUNT::text <> '')) OR (OD.MAX_RECURSIVE_AMOUNT*(MONTHS_BETWEEN(date_trunc('month', LOCALTIMESTAMP),date_trunc('month', OD.EFFECTIVE_DATE))+1) > coalesce(OD.DEDUCTION_AMOUNT,0) AND (OD.MAX_RECURSIVE_AMOUNT IS NOT NULL AND OD.MAX_RECURSIVE_AMOUNT::text <> '')) ))  AND ( ( (OD.MAX_MONTHLY_AMOUNT IS NOT NULL AND OD.MAX_MONTHLY_AMOUNT::text <> '') AND ( NOT EXISTS (SELECT '1'
     FROM OFFENDER_MON_DEDUCTIONS OMD4
     WHERE
       OMD4.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) OR EXISTS (SELECT '1'
     FROM OFFENDER_MON_DEDUCTIONS OMD4
     WHERE
       OMD4.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) AND date_trunc('month', LOCALTIMESTAMP) = OMD.MONTHLY_DEDUCTION_DATE ) ) OR (coalesce(OD.MAX_MONTHLY_AMOUNT::text, '') = '' AND coalesce(OD.MAX_RECURSIVE_AMOUNT::text, '') = '') OR ((OD.MAX_RECURSIVE_AMOUNT IS NOT NULL AND OD.MAX_RECURSIVE_AMOUNT::text <> '') AND (NOT EXISTS (SELECT '1'
     FROM OFFENDER_MON_DEDUCTIONS OMD1
     WHERE
       OMD1.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) OR EXISTS
    (SELECT '1'
     FROM OFFENDER_MON_DEDUCTIONS OMD1
     WHERE
       OMD1.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND
       OMD1.MONTHLY_DEDUCTION_DATE =
         (SELECT MAX(OMD2.MONTHLY_DEDUCTION_DATE)
          FROM OFFENDER_MON_DEDUCTIONS OMD2
          WHERE
            OMD2.OFFENDER_DEDUCTION_ID = OMD1.OFFENDER_DEDUCTION_ID)) AND OMD.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND OMD.MONTHLY_DEDUCTION_DATE =
    (SELECT MAX(OMD3.MONTHLY_DEDUCTION_DATE)
     FROM OFFENDER_MON_DEDUCTIONS OMD3
     WHERE
       OMD3.OFFENDER_DEDUCTION_ID = OMD.OFFENDER_DEDUCTION_ID) ) ) )
}

OCDRECEI_CGFKLKP_OFF_TXN_OFF_TXN_OFF_D_ {
	SELECT OD.OFFENDER_ID
FROM deduction_types dt, caseload_deduction_profiles cdp, offender_deductions od
LEFT OUTER JOIN offender_deduction_shadows ods ON (OD.OFFENDER_DEDUCTION_ID = ODS.OFFENDER_DEDUCTION_ID)
LEFT OUTER JOIN offender_mon_deductions omd ON (OD.OFFENDER_DEDUCTION_ID = OMD.OFFENDER_DEDUCTION_ID)
WHERE OD.INFORMATION_NUMBER = :DSPINFORMATIONNUMBER AND OD.DEDUCTION_TYPE = DT.DEDUCTION_TYPE AND DT.CASELOAD_CODE IN ('BOTH', :CASELOADTYPE) AND EXISTS (SELECT 1
     FROM OFFENDER_BENEFICIARIES OB
     WHERE
       OB.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND
       OB.OFFENDER_ID = :OFFENDERID) AND EXISTS (SELECT 1
     FROM OFFENDER_DEDUCTION_RECEIPTS
     WHERE
       RECEIPT_TXN_TYPE = :TXNTYPE AND
       OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) AND coalesce(OD.ADJUSTMENT_REASON_CODE::text, '') = '' AND date_trunc('day', OD.EFFECTIVE_DATE) <= date_trunc('day', LOCALTIMESTAMP)  AND OD.CASELOAD_ID = CDP.CASELOAD_ID AND CDP.DEDUCTION_TYPE = OD.DEDUCTION_TYPE AND (DT.DEDUCTION_CATEGORY <> 'ALCN' AND ( (coalesce(OD.MAX_TOTAL_AMOUNT,0) > coalesce(OD.DEDUCTION_AMOUNT,0) AND (OD.MAX_TOTAL_AMOUNT IS NOT NULL AND OD.MAX_TOTAL_AMOUNT::text <> '')) OR (coalesce(OD.MAX_MONTHLY_AMOUNT,0) > coalesce(OMD.DEDUCTION_AMOUNT,0) AND (OD.MAX_MONTHLY_AMOUNT IS NOT NULL AND OD.MAX_MONTHLY_AMOUNT::text <> '')) OR (coalesce(OD.MAX_MONTHLY_AMOUNT::text, '') = '' AND coalesce(OD.MAX_RECURSIVE_AMOUNT::text, '') = '' AND coalesce(OD.MAX_TOTAL_AMOUNT::text, '') = '') OR (OD.MAX_RECURSIVE_AMOUNT*(MONTHS_BETWEEN(date_trunc('month', LOCALTIMESTAMP),date_trunc('month', OD.EFFECTIVE_DATE))+1) > coalesce(OD.DEDUCTION_AMOUNT,0) AND (OD.MAX_RECURSIVE_AMOUNT IS NOT NULL AND OD.MAX_RECURSIVE_AMOUNT::text <> '')) ))  AND ( ( (OD.MAX_MONTHLY_AMOUNT IS NOT NULL AND OD.MAX_MONTHLY_AMOUNT::text <> '') AND ( NOT EXISTS (SELECT '1'
     FROM OFFENDER_MON_DEDUCTIONS OMD4
     WHERE
       OMD4.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) OR EXISTS (SELECT '1'
     FROM OFFENDER_MON_DEDUCTIONS OMD4
     WHERE
       OMD4.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) AND date_trunc('month', LOCALTIMESTAMP) = OMD.MONTHLY_DEDUCTION_DATE ) ) OR (coalesce(OD.MAX_MONTHLY_AMOUNT::text, '') = '' AND coalesce(OD.MAX_RECURSIVE_AMOUNT::text, '') = '') OR ((OD.MAX_RECURSIVE_AMOUNT IS NOT NULL AND OD.MAX_RECURSIVE_AMOUNT::text <> '') AND (NOT EXISTS (SELECT '1'
     FROM OFFENDER_MON_DEDUCTIONS OMD1
     WHERE
       OMD1.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID) OR EXISTS
    (SELECT '1'
     FROM OFFENDER_MON_DEDUCTIONS OMD1
     WHERE
       OMD1.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND
       OMD1.MONTHLY_DEDUCTION_DATE =
         (SELECT MAX(OMD2.MONTHLY_DEDUCTION_DATE)
          FROM OFFENDER_MON_DEDUCTIONS OMD2
          WHERE
            OMD2.OFFENDER_DEDUCTION_ID = OMD1.OFFENDER_DEDUCTION_ID)) AND OMD.OFFENDER_DEDUCTION_ID = OD.OFFENDER_DEDUCTION_ID AND OMD.MONTHLY_DEDUCTION_DATE =
    (SELECT MAX(OMD3.MONTHLY_DEDUCTION_DATE)
     FROM OFFENDER_MON_DEDUCTIONS OMD3
     WHERE
       OMD3.OFFENDER_DEDUCTION_ID = OMD.OFFENDER_DEDUCTION_ID) ) ) )
}

OCDRECEI_CGFKCHK_OFF_TXN_OFF_TXN_TXN_T_ {
	SELECT TXN_TYPE.DESCRIPTION ,TXN_TYPE.LIST_SEQ FROM   TRANSACTION_TYPES TXN_TYPE WHERE  TXN_TYPE.TXN_TYPE = :TXNTYPE AND     CASELOAD_TYPE = :CASELOADTYPE AND TXN_TYPE.TXN_TYPE IN (SELECT TXN_TYPE.TXN_TYPE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OCDRECEI' AND CASELOAD_ID = :CASELOADID ) AND TXN_TYPE.TXN_TYPE NOT IN (SELECT CREDIT_OBLIGATION_TYPE FROM TRANSACTION_TYPES WHERE CREDIT_OBLIGATION_TYPE IS NOT NULL)
}

OCDRECEI_CGFKLKP_OFF_TXN_OFF_TXN_TXN_T_ {
	SELECT TXN_TYPE.TXN_TYPE ,TXN_TYPE.LIST_SEQ FROM   TRANSACTION_TYPES TXN_TYPE WHERE (TXN_TYPE.DESCRIPTION = :DSPDESCRIPTION OR (coalesce(TXN_TYPE.DESCRIPTION::text, '') = '' AND coalesce(:DSPDESCRIPTION::text, '') = '' )) AND     CASELOAD_TYPE = :CASELOADTYPE AND TXN_TYPE.TXN_TYPE IN (SELECT TXN_TYPE.TXN_TYPE FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OCDRECEI' AND CASELOAD_ID = :CASELOADID ) AND TXN_TYPE.TXN_TYPE NOT IN (SELECT CREDIT_OBLIGATION_TYPE FROM TRANSACTION_TYPES WHERE (CREDIT_OBLIGATION_TYPE IS NOT NULL AND CREDIT_OBLIGATION_TYPE::text <> ''))
}

OCDRECEI_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE(), upper(user) FROM   DUAL
}
OCDRECEI_GET_PROD_FLAG_DETAILS {
	SELECT coalesce(tno.cheque_production_flag, 'N') checkInd, coalesce(tno.receipt_production_flag, 'N') receiptProductionFlag FROM transaction_types tt, transaction_operations tno
        WHERE tt.txn_type    = :txntype AND tt.txn_usage     = 'R' AND tt.txn_type  = tno.txn_type AND tt.caseload_type = 'COMM'
        AND tno.module_name  = 'OCDRECEI' AND tno.caseload_id  = :csldId
}
OCDRECEI_GET_OFFENDER_DEDUCTION_ID {
	SELECT od.offender_deduction_id FROM offender_deductions od WHERE od.offender_id = :rootOffId AND od.deduction_status IN ('S')
}
OCDRECEI_CHK_OFFENDER_DEDUCTION_RECEIPTS {
SELECT 'Y' FROM OFFENDER_DEDUCTION_RECEIPTS ODR
                   WHERE ODR.OFFENDER_DEDUCTION_ID = :TMPOFFDEDID
                     AND ODR.RECEIPT_TXN_TYPE = :TXNTYPE
}
OCDRECEI_GET_P_OFFENDER_ID_COUNT {
SELECT COUNT (0)
        FROM GL_TRANSACTIONS
       WHERE OFFENDER_ID = :OFFENDERID
         AND REVERSAL_REASON_CODE = 'BOUNCED'
}
OCDRECEI_GET_PROFILE_VALUE {
SELECT PROFILE_VALUE
        FROM SYSTEM_PROFILES
       WHERE SYSTEM_PROFILES.PROFILE_TYPE = 'CLIENT'
         AND SYSTEM_PROFILES.PROFILE_CODE = 'BOUNCE_MSG'
}
OCDRECEI_GET_REFERENCE_CODES_VALID {
SELECT COUNT(0)
        FROM REFERENCE_CODES
       WHERE DOMAIN = 'BOUNCED_CHK'
         AND CODE = :PPAYMENTMETHOD
         AND ACTIVE_FLAG = 'Y'
         AND coalesce(EXPIRED_DATE::text, '') = ''
}
OCDRECEI_GET_SESSION_ID {
SELECT cast(userenv_number('SESSIONID') as int) FROM DUAL
}

OCDRECEI_DELETE_PRINT_RECEIPTS_TMP{
	DELETE FROM print_receipts_tmp prt WHERE prt.session_id = :pSessionId
}   

OCDRECEI_INSERT_PRINT_RECEIPTS_TMP{
	INSERT INTO print_receipts_tmp(module_name, txn_id, offender_id, receipt_number, session_id, create_datetime, create_user_id, modify_datetime)
              VALUES ('OCDRECEI', :pTxnId, :pOffId, :pReceiptNo, :pSessionId, current_timestamp, :createUserId, current_timestamp)
}  

OCDRECEI_INSERT_PRINT_RECEIPTS_TMP_INSERT{
	INSERT INTO PRINT_RECEIPTS_TMP(MODULE_NAME, TXN_ID, OFFENDER_ID, RECEIPT_NUMBER, SESSION_ID, create_datetime, create_user_id, modify_datetime)
              VALUES ('OCDRECEI', :txnId, :rootOffenderId, :receiptNumber, :sessionId, current_timestamp, :createUserId, current_timestamp)
}  
OCDRECEI_CHK_ACCOUNT_CLOSED_FLAG {
SELECT ACCOUNT_CLOSED_FLAG 
        FROM OFFENDER_TRUST_ACCOUNTS
       WHERE CASELOAD_ID = :PCASELOADID
         AND OFFENDER_ID = :POFFENDERID
}

OCDRECEI_GET_PROFILE_VALUE_PAYMENT_PLAN {
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES
                   WHERE PROFILE_TYPE = 'CLIENT'
                     AND PROFILE_CODE = 'PAYMENT_PLAN'
}

OCDRECEI_CHK_MISSING_PAYMENT_PLAN {
SELECT 'Y'
         FROM OFFENDER_DEDUCTIONS OD
        WHERE OFFENDER_ID = :OFFENDERID
          AND DEDUCTION_STATUS = 'A'
          AND DEDUCTION_TYPE IN (SELECT DEDUCTION_TYPE
                                   FROM DEDUCTION_TYPES
                                  WHERE DEDUCTION_CATEGORY ='FXOB')
          AND DEDUCTION_AMOUNT <= MAX_TOTAL_AMOUNT
          AND (INFORMATION_NUMBER = :INFONUM or coalesce(:INFONUM::text, '') = '')
          AND (OFFENDER_ID, GROUP_ID, INFORMATION_NUMBER)
                  NOT IN (SELECT OFFENDER_ID, GROUP_ID, INFORMATION_NUMBER
                            FROM OFFENDER_PAYMENT_PLANS
                           WHERE PAYMENT_CLOSED_FLAG = 'N'
                             AND OFFENDER_ID = :OFFENDERID
                             AND (INFORMATION_NUMBER = :INFONUM or coalesce(:INFONUM::text, '') = '')
                             AND GROUP_ID = od.GROUP_ID);

}

OCDRECEI_TXN_ID {
	SELECT NEXTVAL('TXN_ID') FROM DUAL
}

OCDRECEI_RECEIPT_NUMBER {
	--SELECT #SEQ.NEXTVAL NEXT_SEQ FROM DUAL
	SELECT NEXTVAL('#SEQ') FROM DUAL
}

GET_SYS_PFL {
	SELECT PROFILE_VALUE
       FROM System_Profiles
      WHERE Profile_Type = 'CLIENT'
        AND Profile_Code = 'SUBMIT_COMM'
}

OCDRECEI_GET_AUTOSUBMIT_PFL_VAL {
	SELECT PROFILE_VALUE
       FROM System_Profiles
      WHERE Profile_Type = 'CLIENT'
        AND Profile_Code = 'AUTO_SUBMIT'
}

OCDRECEI_GET_ROLEBASE_PFL_VAL {
	OCDRECEI_GET_AUTOSUBMIT_PFL_VAL {
	SELECT PROFILE_VALUE
       FROM System_Profiles
      WHERE Profile_Type = 'CLIENT'
        AND Profile_Code = 'AUTO_SUBMIT'
}
}

OCDRECEI_GET_OFFENDER_ID_DATA {
SELECT V1.ROOT_OFFENDER_ID, V1.LAST_NAME, V1.FIRST_NAME, V1.OFFENDER_BOOK_ID
              FROM V_TRUST_HEADER_A V1
             WHERE V1.OFFENDER_ID_DISPLAY = :OFFIDDISPLAY
               AND V1.OFFENDER_BOOK_ID =(SELECT MAX(OFFENDER_BOOK_ID)
                                           FROM V_TRUST_HEADER_A V2
                                          WHERE V2.ROOT_OFFENDER_ID = V1.ROOT_OFFENDER_ID)
               AND V1.CASELOAD_ID = :CSLDID
}

OCDRECEI_UPDATE_OFNDR_TRUST_ACC {
	UPDATE offender_trust_accounts SET account_closed_flag = 'N', modify_user_id = :modifyUserId, modify_datetime = current_timestamp WHERE offender_id = :off_id AND caseload_id = :csld_id
} 

OCDRECEI_UPDATE_OFNDR_TRUST_ACC {
	SELECT DISTINCT coalesce (unique_obligation_flag, 'N') FROM offenders WHERE offender_id = :off_id
}
OCDRECEI_INSERT_INTO_OFFENDER_TRANSACTIONS {
INSERT INTO OFFENDER_TRANSACTIONS (
          TXN_ID,
          TXN_ENTRY_SEQ,
          CASELOAD_ID,
          OFFENDER_ID,
          OFFENDER_BOOK_ID,
          TXN_POSTING_TYPE,
          TXN_TYPE,
          TXN_ENTRY_DESC,
          TXN_ENTRY_AMOUNT,
          TXN_ENTRY_DATE,
          SUB_ACCOUNT_TYPE,
          MODIFY_DATE,
          MODIFY_USER_ID,
          SLIP_PRINTED_FLAG,
          PRE_WITHHOLD_AMOUNT,
          DEDUCTION_FLAG,
          PAYEE_CODE,
          PAYEE_CORPORATE_ID,
          PAYEE_PERSON_ID,
          PAYEE_NAME_TEXT,
          DEDUCTION_TYPE,
          INFO_NUMBER,
	  	  REMITTER_NAME,
	 	  REMITTER_ID,
          RECEIPT_NUMBER ,
          TXN_REFERENCE_NUMBER, 
          create_user_id)
   SELECT :P_trans_number,
          :P_trans_seq,
          :P_csld_id,
          :P_off_id,
          :P_off_book_id,
          :P_trans_post_type,
          :P_trans_type,
          :p_trans_desc,
          :p_trans_amount,
          :P_trans_date,
          :p_sub_act_type,
           current_timestamp,
          :modifyUserId,
          :p_slip_print_flag,
          :p_pre_ded_amount,
          :p_deduction_flag,
          :p_payee_code,
          :p_payee_corp_id,
          :p_payee_person_id,
          :p_payee_name_text,
          :p_deduction_type,
          :p_info_number,
	  	  :p_remitter_name,
	  	  :p_remitter_id,
          :p_receipt_number,
          :p_txn_reference_number,
		  :createUserId
   FROM   DUAL
}

OCDRECEI_VALIDATE_DSPINFONUMBER {
select
	OD.OFFENDER_ID
from
	deduction_types dt,
	caseload_deduction_profiles cdp,
	offender_deductions od
left outer join offender_deduction_shadows ods on
	(OD.offender_deduction_id = ODS.offender_deduction_id)
left outer join offender_mon_deductions omd on
	(OD.offender_deduction_id = OMD.offender_deduction_id)
where
	OD.INFORMATION_NUMBER = :dspInfNum
	and OD.DEDUCTION_TYPE = DT.DEDUCTION_TYPE
	and DT.caseload_code in ('BOTH', :caseLoadType)
	and exists (
	select
		1
	from
		offender_beneficiaries OB
	where
		OB.offender_deduction_id = OD.offender_deduction_id
		and OB.offender_id = :offenderId
  )
	and exists (
	select
		1
	from
		OFFENDER_DEDUCTION_RECEIPTS
	where
		receipt_txn_type = :txnType
		and offender_deduction_id = od.offender_deduction_id
  )
	and coalesce(OD.adjustment_reason_code::text, '') = ''
	and date_trunc('day', OD.effective_date) = date_trunc('day', LOCALTIMESTAMP)
	and OD.caseload_id = CDP.caseload_id
	and CDP.deduction_type = OD.deduction_type
	and (DT.DEDUCTION_CATEGORY > 'ALCN'
		and ( (coalesce(OD.max_total_amount,
                0) > coalesce(OD.deduction_amount,
                0)
			and (OD.max_total_amount is not null
				and OD.max_total_amount::text <> ''))
		or (coalesce(OD.max_monthly_amount,
                0) > coalesce(OMD.deduction_amount,
                0)
			and (OD.max_monthly_amount is not null
				and OD.max_monthly_amount::text <> ''))
		or (coalesce(od.max_monthly_amount::text, '') = ''
			and coalesce(od.max_recursive_amount::text, '') = ''
				and coalesce(od.max_total_amount::text, '') = '')
			or (OD.max_recursive_amount *(MONTHS_BETWEEN(date_trunc('month', LOCALTIMESTAMP),
			date_trunc('month', OD.effective_date))+ 1) > coalesce(OD.deduction_amount,
                0)
				and (OD.max_recursive_amount is not null
					and OD.max_recursive_amount::text <> '')) ))
	and ( ( (OD.max_monthly_amount is not null
		and OD.max_monthly_amount::text <> '')
	and ( not exists (
	select
		'1'
	from
		offender_mon_deductions OMD4
	where
		OMD4.offender_deduction_id = OD.offender_deduction_id
  )
	or exists (
	select
		'1'
	from
		offender_mon_deductions OMD4
	where
		OMD4.offender_deduction_id = OD.offender_deduction_id
  )
	and date_trunc('month', LOCALTIMESTAMP) = OMD.monthly_deduction_date ) )
		or (coalesce(OD.max_monthly_amount::text, '') = ''
			and coalesce(OD.max_recursive_amount::text, '') = '')
			or ((OD.max_recursive_amount is not null
				and OD.max_recursive_amount::text <> '')
			and (not exists (
			select
				'1'
			from
				offender_mon_deductions OMD1
			where
				OMD1.offender_deduction_id = OD.offender_deduction_id
  )
			or exists
  (
			select
				'1'
			from
				offender_mon_deductions OMD1
			where
				OMD1.offender_deduction_id = OD.offender_deduction_id
				and OMD1.monthly_deduction_date =
    (
				select
					MAX(OMD2.monthly_deduction_date)
				from
					offender_mon_deductions OMD2
				where
					OMD2.offender_deduction_id = OMD1.offender_deduction_id
    )
  )
			and OMD.offender_deduction_id = OD.offender_deduction_id
			and OMD.monthly_deduction_date =
  (
			select
				MAX(OMD3.monthly_deduction_date)
			from
				offender_mon_deductions OMD3
			where
				OMD3.offender_deduction_id = OMD.offender_deduction_id
  ) ) ) )
}



OCDRECEI_MONTHS_BETWEEN {
SELECT MONTHS_BETWEEN(
                   date_trunc('month', LOCALTIMESTAMP),
                   date_trunc('month', effective_date)
                ) +
                1 from
(select effective_date
           FROM offender_deductions
          WHERE offender_deduction_id = :DEDID) A
}

OCDRECEI_EXIST_FLAG {
	SELECT DISTINCT 'Y'
        FROM offender_beneficiaries
       WHERE offender_deduction_id = :DEDID
}

OCDRECEI_DED_FLAG {
SELECT 'Y'
        FROM deduction_types dt
       WHERE dt.deduction_type = :DEDTYPE
         AND dt.deduction_category IN ('FXOB', 'CROB')
}

OCDRECEI_REPORT_DATA {
	SELECT  OD.OFFENDER_ID,
		OD.OFFENDER_DEDUCTION_ID,
		OD.MAX_TOTAL_AMOUNT,
		OD.MAX_MONTHLY_AMOUNT, 
		OD.MAX_RECURSIVE_AMOUNT,
		OD.DEDUCTION_TYPE
	FROM     OFFENDER_DEDUCTIONS     OD WHERE OFFENDER_ID = :OFFENDERID
}

OCDRECEI_OFFENDER_BENEFICIARIES_OWING_AMOUNT {
SELECT SUM(coalesce(amount,0)) AMOUNT, SUM(coalesce(received_amount,0)) RECEIVED_AMOUNT
    from offender_beneficiaries ob,
         offender_deductions od,
         deduction_types dt
   where ob.offender_id = :OFFENDERID
     and ob.offender_deduction_id = od.offender_deduction_id
     and od.deduction_type = dt.deduction_type
     and dt.deduction_category in ('FXOB', 'CROB')
}

OCDRECEI_TXNFEE_AMOUNT {
SELECT SUM(TXN_ENTRY_AMOUNT)
        FROM OFFENDER_TRANSACTIONS OT
       WHERE OT.TXN_ID = :TXNID
         AND OT.TXN_ENTRY_SEQ > :TXNSEQ
         AND OT.TXN_ENTRY_SEQ < (SELECT coalesce(MIN(OT1.TXN_ENTRY_SEQ), 9999999999)
                                   FROM OFFENDER_TRANSACTIONS OT1
                                  WHERE OT1.TXN_ID = OT.TXN_ID
                                    AND OT1.TXN_ENTRY_SEQ > :TXNSEQ
                                    AND OT1.TXN_TYPE = :TXNTYPE)
         AND EXISTS (SELECT 1
                       FROM DEDUCTION_TYPES DT
                      WHERE DT.DEDUCTION_CATEGORY = 'DTF'
                        AND DT.DEDUCTION_TYPE = OT.DEDUCTION_TYPE)
}

OCDRECEI_GET_OFFENDER_NAME {
select LAST_NAME, FIRST_NAME, MIDDLE_NAME from V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK where V_HEADER_BLOCK.OFFENDER_BOOK_ID = ( select MAX(VHB2.OFFENDER_BOOK_ID) from V_HEADER_BLOCK_FN(:USERID) VHB2 where VHB2.ROOT_OFFENDER_ID = V_HEADER_BLOCK.ROOT_OFFENDER_ID and (VHB2.COMMUNITY_ACTIVE_FLAG = 'Y' or (not exists ( select 'X' from V_HEADER_BLOCK_FN(:USERID) VHB3 where VHB3.COMMUNITY_ACTIVE_FLAG = 'Y' and VHB3.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID ) ) ) and ((VHB2.INTAKE_AGY_LOC_ID <> 'CLOSE' and exists ( select 'X' from OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 where CAL1.CASELOAD_ID = :CASELOAD and CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID and LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID and coalesce(LOCS.REMOVED_DATE::text, '') = '' ) ) or (VHB2.INTAKE_AGY_LOC_ID = 'CLOSE' and exists ( select 'X' from OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 where CAL1.CASELOAD_ID = :CASELOAD and CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID and LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID ) ) ) ) and V_HEADER_BLOCK.ROOT_OFFENDER_ID = :OFFENDERID
}

OCDRECEI_GET_AMOUNT {
	SELECT TRUNC(:AMOUNT,0) FROM DUAL
}
OCDRECEI_AMOUNT_IN_WORDS {
  SELECT
CASE
when :TOTAMT > 3442447 THEN
     replace(:TOTAMT::money::text,'$','') || '  '
    WHEN AMT > 0 AND CENTS > 0 THEN
        to_text(AMT)|| '' || to_text(CENTS) || ''
    WHEN AMT > 0 THEN
        to_text(AMT) || ''
    WHEN CENTS > 0 THEN
        to_text(CENTS)|| ' '
    ELSE
    'ZERO'
END AMTWARDS FROM
 (select CENTS::int,AMT::int from (SELECT (:TOTAMT - AMT) * 100 CENTS, AMT FROM (SELECT ROUND(:TOTAMT,0) AMT FROM dual)A)A)A;
 
}

OCDRECEI_GET_OFFENDER_ID_DISPLAY {
SELECT offender_id_display
FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK
WHERE
  V_HEADER_BLOCK.OFFENDER_BOOK_ID = 
    (SELECT MAX(VHB2.OFFENDER_BOOK_ID)
     FROM V_HEADER_BLOCK_FN(:USERID) VHB2
     WHERE
       VHB2.ROOT_OFFENDER_ID = V_HEADER_BLOCK.ROOT_OFFENDER_ID AND
       (VHB2.COMMUNITY_ACTIVE_FLAG = 'Y' OR (NOT EXISTS 
         (SELECT 'X'
          FROM V_HEADER_BLOCK_FN(:USERID) VHB3
          WHERE
            VHB3.COMMUNITY_ACTIVE_FLAG = 'Y' AND
            VHB3.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID ) ) ) AND
       ((VHB2.INTAKE_AGY_LOC_ID <> 'CLOSE' AND EXISTS 
         (SELECT 'X'
          FROM 
            OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1
          WHERE
            CAL1.CASELOAD_ID = :CASELOAD AND
            CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND
            LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID AND
            coalesce(LOCS.REMOVED_DATE::text, '') = '' ) ) OR (VHB2.INTAKE_AGY_LOC_ID = 'CLOSE' AND EXISTS 
         (SELECT 'X'
          FROM 
            OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1
          WHERE
            CAL1.CASELOAD_ID = :CASELOAD AND
            CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND
            LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID ) ) ) ) AND
  V_HEADER_BLOCK.ROOT_OFFENDER_ID = :OFFENDERID
}

OCDRECEI_REPORTS_QUERY {
SELECT REF.domain, UPPER(sp.description) client, cl.description FOFFNAME, ot.txn_entry_date FTXNDATE, ot.receipt_number FRECNO, ot.remitter_name remitter, ot.txn_entry_amount AMT, ot.offender_id, ot.txn_entry_desc FTXNDESC, ot.modify_user_id USERID, ot.txn_id TXNID, ot.txn_entry_seq TXNSEQ, ot.txn_type TXNTYPE FROM system_profiles sp, caseloads cl, offender_transactions ot, reference_domains ref, transaction_types tt, print_receipts_tmp pr WHERE UPPER(sp.profile_type) = 'CLIENT' AND UPPER(sp.profile_code) = 'CLIENT' AND coalesce(ot.receipt_printed_flag, 'N') LIKE CASE WHEN UPPER('N')='Y' THEN 'N' ELSE '%' END AND (ot.receipt_number IS NOT NULL AND ot.receipt_number::text <> '') AND cl.caseload_id = ot.caseload_id AND ot.caseload_id = :caseload AND REF.domain IN (SELECT domain FROM reference_domains LIMIT 2) AND tt.txn_type = ot.txn_type AND tt.txn_usage = 'R' AND ot.receipt_number = pr.receipt_number AND pr.module_name = :from_module AND pr.session_id = :session_id ORDER BY ot.receipt_number
}

OCDRECEI_CF_CASELOAD_DESC {
  SELECT description FROM   caseloads WHERE  caseload_id = :CASELOAD
}

OCDRECEI_CF_PROFILE_CLIENT {                    
  SELECT DESCRIPTION,
   (SELECT UPPER(PROFILE_VALUE)||':'  
  FROM SYSTEM_PROFILES 
  WHERE PROFILE_CODE = 'INST_AGENCY' AND PROFILE_TYPE = 'LABEL') AS PROFILE_VALUE
  FROM   SYSTEM_PROFILES WHERE  PROFILE_TYPE = 'CLIENT' AND    PROFILE_CODE = 'CLIENT' 
}

OCDRECEI_UPDATE_OFFENDER_TRANSACTIONS_RPT {
  UPDATE offender_transactions
            SET receipt_printed_flag = 'Y', modify_user_id = :modifyUserId, modify_datetime = current_timestamp
          WHERE receipt_number IN (SELECT receipt_number
                                     FROM print_receipts_tmp
                                    WHERE module_name = :from_module
                                      AND session_id = :session_id)
  } 
OCDRECEI_PRINT_RECEIPTS_TMP_DELETE_QUERY_RPT {
DELETE FROM print_receipts_tmp
    WHERE module_name = 'OCDRECEI'  AND session_id = :session_id
}
OCDRECEI_GET_SYSTEM_PROFILE_VALUE{
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_CODE = 'CF_DEDUCTION'
}

OCDRECEI_GET_CF_PAYMENT_SYSTEM_PROFILE_VALUE{
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_CODE = 'CF_PAYMENT'
}

OCDRECEI_GET_OFFENDER_FEE_SECTION_QUERY{
SELECT
    A.BOOKING_NO,
    A.BILL_DATE BILL_GENERATE_DATETIME,
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
            OFAP.FEE_CODE
        FROM
            OFF_FEE_BILLS           OFB,
            OFF_FEE_ACCOUNT_PROFILE OFAP,
            V_HEADER_BLOCK_FN(:USERID)          VHB
        WHERE
                OFAP.OFFENDER_BOOK_ID = VHB.OFFENDER_BOOK_ID
            AND VHB.offender_id_display = :OFFENDER_ID_DISPLAY
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
    ) AND OFBT.BILL_STATUS != 'PAID' and OFBT.BILL_TXN_AMOUNT > 0 
ORDER BY
    A.CREATE_DATETIME asc
}

OCDRECEI_GET_LONGEST_SUPV_EXPIRE_DATE{
SELECT LONGEST_SUPV_EXP_DATE FROM OFF_SUPERVISION_DETAILS  WHERE OFFENDER_BOOK_ID = :offenderBookId
}

OCDRECEI_INSERT_OFFENDER_FEES{
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
        txn_statement_generated_flag,
        billing_statement_id,
        create_datetime,
        create_user_id,
        modify_datetime,
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
        date_trunc('day',:billAgingStartDate::timestamp),
        date_trunc('day',:billAgingEndDate::timestamp),
        :billTxnComment,
        :originalBillId,
        :originalBillTxnNo,
        :originalOffAdjTxnId,
        :txnStatementGeneratedFlag,
        :billingStatementId,
        current_timestamp,
        :createUserId,
        :modifyDatetime,
        :sealFlag
    )
}

OCDRECEI_OFF_FEE_BILLS_TRANSACTIONS_PRE_INSERT{
SELECT coalesce(MAX(BILL_TXN_NO),0)+1 FROM OFF_FEE_BILL_TRANSACTIONS WHERE BILL_ID = :BILL_ID
}
OCDRECEI_GET_SUB_ACCOUNT_DESC{
SELECT DESCRIPTION FROM TRANSACTION_TYPES WHERE TXN_TYPE = :txnType
}
OCDRECEI_GET_CR_DEDUCT_TO{
select cdp.account_code from off_fee_account_profile ofap,caseload_deduction_profiles cdp where ofap.offender_fee_id = :offenderFeeId and 
cdp.deduction_type = ofap.fee_code and ofap.caseload_id = cdp.caseload_id
}
OCDRECEI_GET_DR_ACCOUNT_CODE {
SELECT cr_account_code FROM TRANSACTION_OPERATIONS WHERE  MODULE_NAME = 'OCDRECEI' AND TXN_TYPE = :txnType AND CASELOAD_ID = :caseloadId
}

OCDRECEI_GET_MAX_TXN_ENTRY_SEQ{
select max(txn_entry_seq) txn_entry_seq from offender_transactions where offender_id = :offenderId and txn_id = :txnId
}
OCDRECEI_GET_PAYMENT_OBLIGATION_PROPERTY{
SELECT  COUNT(*)  FROM OFFENDER_PAYMENT_PLANS WHERE OFFENDER_ID = :offenderId
}

OCDRECEI_GET_ADDRESS_ONE_TWO_DETAILS {
select * from v_agency_addresses where agy_loc_ID=:agyLocId and MAIL_FLAG='Y'
}

OCDRECEI_GET_CLIENT_NAME_FOR_RECEIPT_REPORT {
select LAST_NAME||', '||FIRST_NAME from V_HEADER_BLOCK_FN(:USERID) v_header_block where offender_book_id=:offenderBookId 
}
OCDRECEI_GET_CASEPLANDETAILS_TO_GET_PONAME {
select OFFENDER_BOOK_ID, CASE_PLAN_ID, FROM_DATE, POSITION, ROLE, SAC_STAFF_ID, CAL_AGY_LOC_ID, AGY_LOC_ID, CASE_PLAN_STATUS, CREATION_DATE, CREATION_USER, END_DATE, SUPERVISION_LEVEL, CHANGES, NEXT_REVIEW_DATE, START_DATE, CASELOAD_TYPE, VERIFIED_FLAG, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, INST_SAC_STAFF_ID, INST_CAL_AGY_LOC_ID, INST_FROM_DATE, INST_POSITION, INST_ROLE, AUTO_ASSESS_MODIFY_DATETIME, AUTO_CONDITION_MODIFY_DATETIME, SEAL_FLAG from (select OFFENDER_BOOK_ID, CASE_PLAN_ID, FROM_DATE, POSITION, ROLE, SAC_STAFF_ID, CAL_AGY_LOC_ID, AGY_LOC_ID, CASE_PLAN_STATUS, CREATION_DATE, CREATION_USER, END_DATE, SUPERVISION_LEVEL, CHANGES, NEXT_REVIEW_DATE, START_DATE, CASELOAD_TYPE, VERIFIED_FLAG, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, INST_SAC_STAFF_ID, INST_CAL_AGY_LOC_ID, INST_FROM_DATE, INST_POSITION, INST_ROLE, AUTO_ASSESS_MODIFY_DATETIME, AUTO_CONDITION_MODIFY_DATETIME, SEAL_FLAG, row_number() over(order by CREATION_DATE desc)RN from CASE_PLANS where offender_book_id =:offenderBookId) where RN=1
}

OCDRECEI_GET_TRANSACTION_TYPE_DESCRIPTION {
	SELECT DESCRIPTION from transaction_types  where TXN_TYPE=:txnType
}

OCDRECEI_GET_STAFF_ID{
SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID
}

OCDRECEI_GET_PREPAID_ACNT_FEE_CODE{
SELECT B.FEE_CODE CODE,  A.DEDUCTION_DESC DESCRIPTION,B.EXPIRY_DATETIME,B.ACCOUNT_CODE FROM DEDUCTION_TYPES A, FEE_ACCOUNTS B WHERE B.FEE_CODE = A.DEDUCTION_TYPE
}

OCDRECEI_GET_FEE_BILL_PREVIOUS_AND_CURRENT_BALANCE{
SELECT
OFAP.FEE_CODE,
OFAP.CASELOAD_ID,
    OFBT.*,
    (
        SELECT
            O.BILL_TXN_AMOUNT
        FROM
            OFF_FEE_BILL_TRANSACTIONS O
        WHERE
                O.BILL_TXN_NO = ( OFBT.BILL_TXN_NO - 1 )
            AND O.BILL_ID = OFBT.BILL_ID
    ) PREVIOUS_AMOUNT
FROM
    OFF_FEE_BILL_TRANSACTIONS OFBT,
    OFF_FEE_ACCOUNT_PROFILE OFAP
WHERE
    OFBT.TRUST_TXN_ID = :trustTxnId AND
    OFAP.OFFENDER_FEE_ID = (SELECT OFFENDER_FEE_ID FROM OFF_FEE_BILLS WHERE BILL_ID = OFBT.BILL_ID)
}


OCDRECEI_GET_REMITTER_NAME_FOR_REPORT {
 select REMITTER_NAME from offender_transactions where  TXN_ID =:txnId AND  TXN_ENTRY_SEQ =:txnEntrySeq
}

OCDRECEI_UPDATE_OFF_FEE_BILLS_DATE{
UPDATE OFF_FEE_BILLS SET BILL_LDPP_START_DATE =DATE_TRUNC('day', :billLdppStartDate::timestamp)::date, 
BILL_LDPP_END_DATE =DATE_TRUNC('day', :billLdppStartDate::timestamp)::date+89 , modify_user_id = :modifyUserId,
modify_datetime = current_timestamp WHERE BILL_ID = :billId
}

OCDRECEI_GET_SYS_PFL_VALUE{
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_CODE = 'BILL_END_DAY'
}

OCDRECEI_OFF_BILL_STMT_INSERT_QUERY {
 INSERT INTO OFF_BILLING_STATEMENTS (BILLING_STATEMENT_ID,CASELOAD_ID,ROOT_OFFENDER_ID,BILLING_CYCLE_START_DATE,BILLING_CYCLE_END_DATE,
STATEMENT_GENERATE_DATETIME,STATEMENT_GENERATE_STAFF_ID,STATEMENT_GENERATE_USER,BEGINING_BALANCE_AMOUNT,PAYMENTS_CREDITS_AMOUNT,
BILLINGS_AMOUNT,ENDING_BALANCE_AMOUNT,OFFENDER_BOOK_ID,CASE_PLAN_ID, create_datetime, create_user_id, modify_datetime)
VALUES (:billingStatementId,:caseloadId,:rootOffenderId,date_trunc('day',:billingCycleStartDate::timestamp)::date,date_trunc('day',:billingCycleEndDate::timestamp)::date,
date_trunc('day',:statementGenerateDatetime::timestamp)::date,:statementGenerateStaffId,:statementGenerateUser,:beginingBalanceAmount,:paymentsCreditsAmount,
:billingsAmount,:endingBalanceAmount,:offenderBookId,:casePlanId, current_timestamp, :createUserId, current_timestamp);


}

OCDRECEI_UPDATE_OFF_FEE_BILLS {
update
	OFF_FEE_BILLS
set
	STATEMENT_GENERATED_FLAG = 'Y',
	BILLING_STATEMENT_ID = :billingStatementId,
	BILL_STATEMENT_DATE = date_trunc('day', :statementGenerateDatetime),
	BILL_AR_DUE_DATE = date_trunc('day', :statementGenerateDatetime)+ 30 * interval '1 day',
	BILL_LDPP_START_DATE = date_trunc('day', :statementGenerateDatetime)+ 31 * interval '1 day',
	BILL_LDPP_END_DATE = date_trunc('day', :statementGenerateDatetime)+ 30 * interval '1 day'
	modify_user_id = :modifyUserId, modify_datetime = current_timestamp
where
	BILL_ID = :billId;
 }
 
 OCDRECEI_GET_PRINT_RECEIPTS_TMP{
  SELECT * FROM print_receipts_tmp WHERE MODULE_NAME='OCDBRECI' AND SESSION_ID = :sessionId
 }
  
OCDRECEI_GET_PRINT_RECEIPTS_BY_SESSIONID{
    SELECT * FROM print_receipts_tmp WHERE SESSION_ID = :sessionId
 }
  
 OCDRECEI_GET_OFFENDER_PREPAID_BALANCE {
select  BALANCE from OFFENDER_SUB_ACCOUNTS where trust_account_code = (select ACCOUNT_CODE from FEE_ACCOUNTS where fee_code = :feeCode and EXPIRY_DATETIME is null) and offender_id = :offenderId and CASELOAD_ID =:caseloadId
}

OCDRECEI_UPDATE_FEE_ACCOUNT_STATUS{
UPDATE OFF_FEE_ACCOUNT_PROFILE SET FEE_ACT_STATUS = :feeActStatus , STATUS_EFFECTIVE_DATE = current_timestamp WHERE OFFENDER_BOOK_ID = :offenderBookId AND FEE_CODE = :feeCode AND CASELOAD_ID = :caseloadId
}
OCDRECEI_GET_OFF_FEE_ID_TOTAL_BALANCE_OWNING{
SELECT sum(ofbt.bill_txn_amount) FROM off_fee_bill_transactions ofbt, ( SELECT bill_id FROM off_fee_bills WHERE offender_fee_id IN ( SELECT offender_fee_id FROM off_fee_account_profile WHERE offender_book_id = :offenderBookId AND fee_code = :feeCode AND  CASELOAD_ID = :caseloadId ) ) ofb WHERE ofbt.bill_id = ofb.bill_id AND ofbt.bill_txn_no = ( SELECT MAX(bill_txn_no) FROM off_fee_bill_transactions WHERE bill_id = ofb.bill_id )
}

OCDRECEI_OLD_DATA_OFF_FEE_BILLS{
select BILL_AR_START_DATE , BILL_AR_DUE_DATE from OFF_FEE_BILLS where bill_id =:billId ;
}

