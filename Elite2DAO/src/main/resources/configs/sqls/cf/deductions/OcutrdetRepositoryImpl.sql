OCUTRDET_OVERRIDE_TYPE_RG {
	SELECT REF_CODE.CODE  CODE       ,REF_CODE.DESCRIPTION  DESCRIPTION FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'RF_FOV_TYP'  AND ACTIVE_FLAG = 'Y'  AND EXPIRED_DATE  IS NULL ORDER BY REF_CODE.LIST_SEQ ,REF_CODE.CODE ,REF_CODE.DESCRIPTION
}

OCUTRDET_FIND_BILL_OVERRIDE_DETAILS {
--	SELECT * FROM off_fee_bills where OFFENDER_FEE_ID = :offenderFeeId
SELECT (SELECT coalesce(SUM(BILL_TXN_AMOUNT),0) FROM off_fee_bill_transactions OBT WHERE BILL_ID = OFB.BILL_ID AND
EXISTS(SELECT 1 FROM TRANSACTION_TYPES WHERE TXN_TYPE=OBT.BILL_TXN_TYPE AND TXN_USAGE=(SELECT CODE FROM REFERENCE_CODES WHERE DOMAIN='AC_TXN_USG' AND CODE='BI'))) AS BI
,(SELECT coalesce(SUM(BILL_TXN_AMOUNT),0) FROM off_fee_bill_transactions OBT WHERE BILL_ID = OFB.BILL_ID AND
EXISTS(SELECT 1 FROM TRANSACTION_TYPES WHERE TXN_TYPE=OBT.BILL_TXN_TYPE AND TXN_USAGE=(SELECT CODE FROM REFERENCE_CODES WHERE DOMAIN='AC_TXN_USG' AND CODE='BD'))) AS BD
,OFB.* FROM off_fee_bills OFB where OFFENDER_FEE_ID = :offenderFeeId
}

OCUTRDET_FIND_BILL_TRANS_HIST_DETAILS{
--SELECT ofbt.*, tt.txn_usage, ( SELECT description FROM transaction_types a WHERE a.txn_type = bill_txn_type ) AS description, ( SELECT description FROM reference_codes WHERE code = bill_status AND domain = 'BILL_STATUS' ) AS bill_status_description FROM off_fee_bill_transactions ofbt, transaction_types tt WHERE ofbt.bill_txn_type = tt.txn_type AND bill_id = :billId ORDER BY ofbt.BILL_TXN_NO ASC
select
	ABS(ADJ-ADJREV) adjustment_amount,
	ABS(PAYMENT-PAYMENTREV) PAYMENTAMOUNT,
	BILLAMOUNT BILL_TXN_AMOUNT,
	ABS(PLUSAMOUNT-MINUSAMOUNT) OWINGAMOUNT,
	(
	select
		TXN_USAGE
	from
		transaction_types
	where
		txn_type = bill_txn_type) as txnUsage,
	(
	select
		description
	from
		transaction_types a
	where
		a.txn_type = bill_txn_type ) as description,
	(
	select
		description
	from
		reference_codes
	where
		code = bill_status
		and domain = 'BILL_STATUS' ) as BILL_STATUS,
	case
		when TRUST_TXN_ID is not null then TRUST_TXN_ID
		when OFF_ADJ_TXN_ID is not null then OFF_ADJ_TXN_ID
		else null
	end BILL_TXN_NO,
	BILL_ID,
	BILL_TXN_DATETIME,
	BILL_TXN_STAFF_ID,
	BILL_TXN_USER,
	BILL_TXN_TYPE,
	BILL_TXN_AMOUNT ORG_BILL_TXN_AMOUNT,
	TRUST_TXN_ID,
	TRUST_TXN_ENTRY_SEQ,
	OFF_ADJ_CANC_RSN,
	OFF_ADJ_SUB_RSN,
	OFF_ADJ_TXN_ID,
	OFF_ADJ_REV_RSN,
	BILL_AGING_START_DATE,
	BILL_AGING_END_DATE,
	BILL_TXN_COMMENT,
	ORIGINAL_BILL_ID,
	ORIGINAL_BILL_TXN_NO,
	ORIGINAL_OFF_ADJ_TXN_ID,
	TXN_STATEMENT_GENERATED_FLAG,
	BILLING_STATEMENT_ID,
	CREATE_DATETIME,
	CREATE_USER_ID
from
	(
	select
		ofbt.BILL_TXN_NO TXNNO,
		case
			when OFF_ADJ_TXN_ID is not null
				and ORIGINAL_OFF_ADJ_TXN_ID is null then BILL_TXN_AMOUNT
				else 0
			end ADJ,
			case
				when OFF_ADJ_TXN_ID is not null
				and ORIGINAL_OFF_ADJ_TXN_ID is not null then BILL_TXN_AMOUNT
				else 0
			end ADJREV,
			case
				when TRUST_TXN_ID is not null
				and ORIGINAL_BILL_ID is null then BILL_TXN_AMOUNT
				else 0
			end PAYMENT,
			case
				when TRUST_TXN_ID is not null
				and ORIGINAL_BILL_ID is not null then BILL_TXN_AMOUNT
				else 0
			end PAYMENTREV,
			case
				when OFF_ADJ_TXN_ID is null
				and TRUST_TXN_ID is null then BILL_TXN_AMOUNT
				else 0
			end BILLAMOUNT
,
			(
			select
				coalesce(SUM(BILL_TXN_AMOUNT), 0)
			from
				off_fee_bill_transactions
			where
				BILL_ID = OFBT.BILL_ID
				and BILL_TXN_NO <= OFBT.BILL_TXN_NO
				and (BILL_TXN_TYPE in (
				select
					TXN_TYPE
				from
					TRANSACTION_TYPES
				where
					TXN_TYPE = BILL_TXN_TYPE
					and TXN_USAGE in (
					select
						CODE
					from
						REFERENCE_CODES
					where
						domain = 'AC_TXN_USG'
						and CODE in ('BI', 'B')))
					or (TRUST_TXN_ID is not null
						and ORIGINAL_BILL_ID is not null)
					or(OFF_ADJ_TXN_ID is not null
						and ORIGINAL_OFF_ADJ_TXN_ID is not null))) PLUSAMOUNT
,
			(
			select
				coalesce(SUM(BILL_TXN_AMOUNT), 0)
			from
				off_fee_bill_transactions
			where
				BILL_ID = OFBT.BILL_ID
				and BILL_TXN_NO <= OFBT.BILL_TXN_NO
				and (BILL_TXN_TYPE in (
				select
					TXN_TYPE
				from
					TRANSACTION_TYPES
				where
					TXN_TYPE = BILL_TXN_TYPE
					and TXN_USAGE in (
					select
						CODE
					from
						REFERENCE_CODES
					where
						domain = 'AC_TXN_USG'
						and CODE = ('BD')))
					or (TRUST_TXN_ID is not null
						and ORIGINAL_BILL_ID is null)
					or(OFF_ADJ_TXN_ID is not null
						and ORIGINAL_OFF_ADJ_TXN_ID is null))) as MINUSAMOUNT
,
			ofbt.*
		from
			off_fee_bill_transactions ofbt,
			transaction_types tt
		where
			ofbt.bill_txn_type = tt.txn_type
			and bill_id :: text = :billId :: text) as a
order by
	TXNNO desc


}


OCUTRDET_GET_DEDUCTION_DESC {
    select DEDUCTION_DESC from DEDUCTION_TYPES where deduction_type = :deductionType
}

OCUTRDET_GET_FREQUENCY {
    select description from reference_codes where code = :code and  domain = 'RECUR_FREQ'
}

OCUTRDET_GET_LOCATION {
    SELECT description FROM CASELOADS where CASELOAD_ID = :location
}

OCUTRDET_GET_FREQUENCYTYPE {
    select description from reference_codes where code = :code and  domain = 'FEE_ACT_FREQ'
}

OCUTRDET_GET_FEEACCOUNT_STATUS_DESC {
select description from reference_codes where code = :code and  domain = 'DED_STATUS'
}

OCUTRDET_GET_DESCRIPTION_TXN_TYPE {
SELECT description from TRANSACTION_TYPES where txn_type=:billTxnType
}

OCUTRDET_GET_BILL_STATUS_FOR_BILL_ID {
 SELECT a.BILL_STATUS FROM (SELECT BILL_STATUS FROM off_fee_bill_transactions where BILL_ID::text=:billId::text ORDER BY BILL_TXN_NO desc) as a limit 1
 }
 OCUTRDET_GET_BILL_STATUS_FOR_BILL_ID_DATE {
 SELECT a.CREATE_DATETIME FROM (SELECT CREATE_DATETIME FROM off_fee_bill_transactions where BILL_ID::text=:billId::text ORDER BY BILL_TXN_NO desc) as a limit 1
 }
 
 OCUTRDET_GET_BILL_STATUS_FOR_BILL_ID_TRANSDETAILS_SAVE {
 SELECT * FROM (SELECT * FROM off_fee_bill_transactions where BILL_ID::text=:billId::text ORDER BY BILL_TXN_NO desc) as a limit 1
 }
 
 OCUTRDET_OFF_FEE_BILLS_TRANSACTIONS_INSERT {
 INSERT INTO OFF_FEE_BILL_TRANSACTIONS (BILL_ID,BILL_TXN_NO,BILL_TXN_DATETIME,BILL_TXN_STAFF_ID,BILL_TXN_USER,
BILL_TXN_TYPE,BILL_TXN_AMOUNT,TRUST_TXN_ID,TRUST_TXN_ENTRY_SEQ,OFF_ADJ_CANC_RSN,OFF_ADJ_SUB_RSN,OFF_ADJ_TXN_ID,OFF_ADJ_REV_RSN,
BILL_STATUS,BILL_AGING_START_DATE,BILL_AGING_END_DATE,BILL_TXN_COMMENT,ORIGINAL_BILL_ID,ORIGINAL_BILL_TXN_NO,
ORIGINAL_OFF_ADJ_TXN_ID,TXN_STATEMENT_GENERATED_FLAG,BILLING_STATEMENT_ID,CREATE_DATETIME,CREATE_USER_ID,SEAL_FLAG)
values(:billId,:billTxnNo,:billTxnDatetime,:billTxnStaffId,:billTxnUser,
:billTxnType,:billTxnAmount,:trustTxnId,:trustTxnEntrySeq,:offAdjCancRsn,:offAdjSubRsn,:offAdjTxnId,:offAdjRevRsn,
:billStatus,:billAgingStartDate,:billAgingEndDate,:billTxnComment,:originalBillId,:originalBillTxnNo,
:originalOffAdjTxnId,:txnStatementGeneratedFlag,:billingStatementId,current_timestamp,:createUserId,:sealFlag)
 }
 OCUTRDET_OFF_FFE_BILL_STATUS_DESCRIPTION {
 select description from reference_codes where code = :code and  domain = 'BILL_STATUS'
 }
 
 OCUTRDET_OFF_FFE_BILL_GL_TRANS_DETAILS {
 select * from gl_transactions where TXN_ID=:trustTxnId and TXN_ENTRY_SEQ=:trustTxnEntrySeq
 }
 
 OCUTRDET_OFF_FFE_BILL_OFFENDER_TRANS_DETAILS {
 select * from offender_transactions where TXN_ID=:trustTxnId and TXN_ENTRY_SEQ=:trustTxnEntrySeq
 }
 
 OCUTRDET_SYSPFL_BILL_ADJUS_FIND_SYSTEM_PROFILES {
SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES where PROFILE_CODE = 'BILL_ADJSTMT'
}

OCUTRDET_SYSPFL_BILL_STATUS_FIND_SYSTEM_PROFILES {
SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES where PROFILE_CODE = 'UPD_BILL_STS'
}

OCUTRDET_GET_BILL_TRANACTION_COMMENT_RECENT {
select a.bill_txn_comment from ( select a.bill_txn_type, b.txn_usage, a.bill_id, bill_txn_no, row_number() over( order by bill_txn_no desc ) rn, bill_txn_comment from ( select bill_txn_type, bill_id, bill_txn_no, bill_txn_comment from off_fee_bill_transactions where bill_id::text = :billId::text ) a, transaction_types b where a.bill_txn_type = b.txn_type and b.txn_usage in ( 'BI', 'BD' ) ) as a where rn = 1 
}