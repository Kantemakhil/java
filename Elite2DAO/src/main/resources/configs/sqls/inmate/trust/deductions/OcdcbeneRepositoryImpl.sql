
OCDCBENE_PAYEEAB_FIND_V_CLEAR_ACCOUNT_PAYABLES {
 select
	ACCOUNT_CODE,
	CORPORATE_ID,
	PERSON_ID,
	CORPORATE_NAME,
	TOTAL_AMOUNT,
	CASELOAD_ID
from
	(
	select
		ac.account_code account_code,
		co.corporate_id corporate_id,
		(null)::numeric person_id,
		co.corporate_name corporate_name,
		SUM(case when bt.txn_post_usage = 'DR' then case when ac.txn_posting_type = 'DR' then coalesce(bt.txn_entry_amount, 0) when ac.txn_posting_type = 'CR' then coalesce(bt.txn_entry_amount, 0)  -1 end when bt.txn_post_usage = 'CR' then case when ac.txn_posting_type = 'CR' then coalesce(bt.txn_entry_amount, 0) when ac.txn_posting_type = 'DR' then coalesce(bt.txn_entry_amount, 0)  -1 end end ) total_amount,
		bt.caseload_id caseload_id
	from
		beneficiary_transactions bt,
		account_codes ac,
		corporates co
	where
		(bt.corporate_id is not null
			and bt.corporate_id::text <> '')
		and
     bt.corporate_id = co.corporate_id
		and
     bt.account_code = ac.account_code
		and (bt.beneficiary_cleared_flag = 'N'
			or coalesce(bt.beneficiary_cleared_flag::text, '') = '')
		and
     bt.txn_entry_date <=
       (
		select
			SYSDATE() - coalesce(MAX((s1.profile_value)::numeric ), 0) * interval '1 day'
		from
			system_profiles s1,
			system_profiles s2
		where
			s1.profile_type = 'TRUST_INF'
			and
          s1.profile_code = 'CHECK_AGING'
			and
          s2.profile_type = 'CHECK_AGING'
			and
          s2.profile_code = bt.receipt_txn_type)
	group by
		ac.account_code,
		co.corporate_id,
		(null)::numeric ,
		co.corporate_name,
		bt.caseload_id
union all
	select
		ac.account_code account_code,
		(null)::numeric corporate_id,
		pe.person_id person_id,
		pe.last_name || ', ' || first_name,
		SUM(case when bt.txn_post_usage = 'DR' then case when ac.txn_posting_type = 'DR' then coalesce(bt.txn_entry_amount, 0) when ac.txn_posting_type = 'CR' then coalesce(bt.txn_entry_amount, 0)  -1 end when bt.txn_post_usage = 'CR' then case when ac.txn_posting_type = 'CR' then coalesce(bt.txn_entry_amount, 0) when ac.txn_posting_type = 'DR' then coalesce(bt.txn_entry_amount, 0)  -1 end end ) total_amount,
		bt.caseload_id caseload_id
	from
		beneficiary_transactions bt,
		account_codes ac,
		persons pe
	where
		(bt.person_id is not null
			and bt.person_id::text <> '')
		and
     bt.person_id = pe.person_id
		and
     bt.account_code = ac.account_code
		and (bt.beneficiary_cleared_flag = 'N'
			or coalesce(bt.beneficiary_cleared_flag::text, '') = '')
		and
     bt.txn_entry_date <=
       (
		select
			current_timestamp - coalesce(MAX((s1.profile_value)::numeric ), 0) * interval '1 day'
		from
			system_profiles s1,
			system_profiles s2
		where
			s1.profile_type = 'TRUST_INF'
			and
          s1.profile_code = 'CHECK_AGING'
			and
          s2.profile_type = 'CHECK_AGING'
			and
          s2.profile_code = bt.receipt_txn_type)
	group by
		ac.account_code,
		pe.person_id,
		(null)::numeric ,
		pe.last_name || ', ' || pe.first_name,
		bt.caseload_id) alias28 
}
OCDCBENE_PAYEEAB_UPDATE_V_CLEAR_ACCOUNT_PAYABLES {
	UPDATE V_CLEAR_ACCOUNT_PAYABLES set ACCOUNT_CODE  = :accountCode ,CORPORATE_ID  = :corporateId ,PERSON_ID  = :personId ,CORPORATE_NAME  = :corporateName ,TOTAL_AMOUNT  = :totalAmount ,CASELOAD_ID  = :caseloadId, MODIFY_USER_ID = :modifyUserId, MODIFY_DATETIME  = CURRENT_TIMESTAMP  /* where */
}

OCDCBENE_BENTXN_FIND_BENEFICIARY_TRANSACTIONS {
 	SELECT TXN_ID ,TXN_ENTRY_SEQ ,GL_ENTRY_SEQ ,BEN_ENTRY_SEQ ,OFFENDER_DEDUCTION_ID ,ACCOUNT_CODE ,PERSON_ID ,CORPORATE_ID ,TXN_POST_USAGE ,TXN_ENTRY_DATE ,TXN_ENTRY_TIME ,TXN_ENTRY_AMOUNT ,CASELOAD_ID ,TXN_TYPE ,TXN_ENTRY_DESC ,CHECK_CLEARED_DATE ,REV_TXN_FLAG ,REV_TXN_ID ,REV_TXN_ENTRY_SEQ ,REV_GL_ENTRY_SEQ ,REV_BEN_ENTRY_SEQ ,MODIFY_USER_ID ,MODIFY_DATE ,UNKNOWN_BEN_ID ,BENEFICIARY_CLEARED_FLAG ,RECEIPT_TXN_TYPE ,BENEFICIARY_ID ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM BENEFICIARY_TRANSACTIONS 
}
OCDCBENE_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OCDCBENE_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION  FROM   OMS_MODULES WHERE  MODULE_NAME = :V_FORM_NAME
}

OCDCBENE_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}
OCDCBENE_OFFENDER_NAME_CURSOR {
SELECT O.ROOT_OFFENDER_ID,
                O.OFFENDER_ID_DISPLAY,
                O.LAST_NAME || ', ' || FIRST_NAME LAST_FIRST_NAME
           FROM OFFENDERS O,
                OFFENDER_DEDUCTIONS OD,
                OFFENDER_BOOKINGS OB
          WHERE OD.OFFENDER_DEDUCTION_ID = :OFFENDER_DEDUCTION_ID
            AND OD.OFFENDER_ID = OB.ROOT_OFFENDER_ID
            AND OB.OFFENDER_BOOK_ID 
                     = (SELECT MAX(OBX.OFFENDER_BOOK_ID)
                          FROM OFFENDER_BOOKINGS OBX
                         WHERE OBX.BOOKING_TYPE 
                                   = (SELECT CASELOAD_TYPE
                                        FROM CASELOADS
                                       WHERE CASELOAD_ID = :CASELOAD_ID
                                      )
                           AND OBX.ROOT_OFFENDER_ID = OB.ROOT_OFFENDER_ID
                        )
            AND O.OFFENDER_ID = OB.OFFENDER_ID
}
OCDCBENE_TXN_POSTING_TYPE_CURSOR {
SELECT TXN_POSTING_TYPE FROM ACCOUNT_CODES WHERE ACCOUNT_CODE = :ACCOUNT_CODE
}
OCDCBENE_GET_PERSON_INFO_CURSROR {
SELECT coalesce(SUSPENDED_FLAG,'N') FROM  PERSONS WHERE  PERSON_ID = :PERSON_ID
}
OCDCBENE_GET_CORPORATE_INFO_CURSROR {
SELECT coalesce(SUSPENDED_FLAG,'N') FROM CORPORATES WHERE CORPORATE_ID = :CORPORATE_ID
}
OCDCBENE_GET_MIN_BAL_CURSROR {
SELECT MIN_PAY_AMOUNT FROM MINIMUM_PAYABLE_BALANCES WHERE ACCOUNT_CODE = :ACCOUNT_CODE
}
OCDCBENE_CHECK_LOCK {
 SELECT 'Y' FROM LOCKED_MODULES WHERE MODULE_NAME = 'OCDCBENE' AND CASELOAD_ID = :CASELOAD_ID AND (current_timestamp::date - LOCKED_DATE::date)::bigint >= 1
}

OCDCBENE_PERSON_ID {
select
	bt.*, od.offender_id
from
	offender_deductions od,
	beneficiary_transactions bt
left outer join beneficiary_transactions btx on
	(bt.rev_txn_id = btx.txn_id
		and bt.rev_txn_entry_seq = btx.txn_entry_seq
		and bt.rev_gl_entry_seq = btx.gl_entry_seq
		and bt.ben_entry_seq = btx.ben_entry_seq)
where
	(bt.person_id is not null
		and bt.person_id::text <> '')
	and bt.person_id = :PERSONID
	and bt.account_code = :ACCOUNTCODE
	and bt.caseload_id = :CASELOADID
	and ( bt.beneficiary_cleared_flag = 'N'
		or coalesce(bt.beneficiary_cleared_flag::text, '') = '')
	and case
		when coalesce(bt.rev_txn_id::text, '') = '' then bt.txn_entry_date
		else btx.txn_entry_date
	end <=(select
		current_timestamp - coalesce(MAX((s1.profile_value)::numeric ), 0) * interval '1 day'
	from
		system_profiles s1,
		system_profiles s2
	where
		s1.profile_type = 'TRUST_INF'
		and s1.profile_code = 'CHECK_AGING'
		and s2.profile_type = 'CHECK_AGING'
		and s2.profile_code = bt.receipt_txn_type)
	and bt.offender_deduction_id = od.offender_deduction_id 
order by bt.txn_id
}

OCDCBENE_CORPORATE_ID {
select
	bt.*, od.offender_id
from
offender_deductions od,
	beneficiary_transactions bt
	left outer join beneficiary_transactions btx on
	(bt.rev_txn_id = btx.txn_id
	and bt.rev_txn_entry_seq = btx.txn_entry_seq
	and bt.rev_gl_entry_seq = btx.gl_entry_seq
	and bt.ben_entry_seq = btx.ben_entry_seq)
where
	(bt.corporate_id is not null
	and bt.person_id::text <> '')
	and bt.corporate_id = :corporateid
	and bt.account_code = :accountcode
	and bt.caseload_id = :caseloadid
	and ( bt.beneficiary_cleared_flag = 'N'
		or bt.beneficiary_cleared_flag is null )
	and case
		when coalesce (bt.rev_txn_id::text,'') = '' then
                bt.txn_entry_date
		else
                btx.txn_entry_date
	end <= (select
		current_timestamp - coalesce(MAX((s1.profile_value)::numeric), 0) * interval '1 day'
	from
		system_profiles s1,
		system_profiles s2
	where
		s1.profile_type = 'TRUST_INF'
		and s1.profile_code = 'CHECK_AGING'
		and s2.profile_type = 'CHECK_AGING'
		and s2.profile_code = bt.receipt_txn_type
    )
order by
	bt.txn_id;
}

OCDCBENE_CHEQUE_DETAILS {
SELECT a.cr_account_code,
                a.txn_type,
                a.cheque_production_flag,
                b.description,
                c.txn_posting_type bank_cr_account_code,
                d.txn_posting_type bank_dr_account_code
           FROM transaction_operations a,
                transaction_types b,
                account_codes c,
                account_codes d
          WHERE a.module_name = 'OCDCBENE'
            AND a.caseload_id = :CASELOADID
            AND b.txn_type = a.txn_type
            AND c.account_code = :ACCOUNTCODE
            AND d.account_code = a.cr_account_code
}
OCDCBENE_PERSON_ID_CHECK {
SELECT 1
           FROM offender_beneficiaries
          WHERE person_id = :PERSONID
            AND offender_deduction_id = :OFFENDERDEDUCTIONID
}
OCDCBENE_CORPORATE_ID_CHECK {
SELECT 1
           FROM offender_beneficiaries
          WHERE corporate_id = :CORPORATEID
            AND offender_deduction_id = :OFFENDERDEDUCTIONID
}
OCDCBENE_PERSON_UPDATE_BENEFICIARY_TRANSACTIONS {
UPDATE beneficiary_transactions bt
               SET bt.beneficiary_cleared_flag = 'Y',
                   bt.MODIFY_USER_ID = :modifyUserId,
 				   bt.MODIFY_DATETIME  = CURRENT_TIMESTAMP
             WHERE bt.person_id = :personId
               AND bt.txn_id = :txnId
               AND bt.offender_deduction_id = :offenderDeductionId
               AND bt.account_code = :accountCode
}
OCDCBENE_CORPORATE_UPDATE_BENEFICIARY_TRANSACTIONS {
UPDATE beneficiary_transactions bt
               SET bt.beneficiary_cleared_flag = 'Y',
                   bt.MODIFY_USER_ID = :modifyUserId,
 				   bt.MODIFY_DATETIME  = CURRENT_TIMESTAMP
             WHERE bt.corporate_id = :corporateId
               AND bt.txn_id = :txnId
               AND bt.offender_deduction_id = :offenderDeductionId
               AND bt.account_code = :accountCode
}
OCDCBENE_INSERT_INTO_PERSON_BANK_CHEQUE_BENEFICIARIES {
INSERT INTO bank_cheque_beneficiaries (
                           cheque_txn_id,
                           cheque_amount,
                           person_id,
                           corporate_id,
                           txn_id,
                           offender_id,
                           amount,
                           offender_deduction_id,
                           CREATE_DATETIME,
                           CREATE_USER_ID,
                           MODIFY_DATETIME
                        )
                 VALUES (
                    :chequeTxnId,
                    :chequeAmount,
                    :personId,
                    NULL,
                    :txnId,
                    :offenderId,
                    :amount,
                    :offenderDeductionId,
                    CURRENT_TIMESTAMP,
                    :createUserId,
                    NULL
}
OCDCBENE_INSERT_INTO_CORPORATE_BANK_CHEQUE_BENEFICIARIES {
INSERT INTO bank_cheque_beneficiaries (
                           cheque_txn_id,
                           cheque_amount,
                           person_id,
                           corporate_id,
                           txn_id,
                           offender_id,
                           amount,
                           offender_deduction_id,
                           CREATE_DATETIME,
                           CREATE_USER_ID,
                           MODIFY_DATETIME
                        )
                 VALUES (
                    :chequeTxnId,
                    :chequeAmount,
                    NULL,
                    :corporateId,
                    :txnId,
                    :offenderId,
                    :amount,
                    :offenderDeductionId,
                    CURRENT_TIMESTAMP,
                    :createUserId,
                    NULL
}
OCDCBENE_USER_DATA {
	SELECT USER FROM DUAL
}

OCDCBENE_GET_TXN_ID {
select nextval('TXN_ID') 
}