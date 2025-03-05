
OCUOVROB_OFFBNC_FIND_OFFENDER_BENEFICIARIES {
 	select
        BENEFICIARY_ID ,
        OFFENDER_DEDUCTION_ID ,
        OFFENDER_ID ,
        PERSON_ID ,
        CORPORATE_ID ,
        PRIORITY ,
        AMOUNT ,
        percent ,
        OVERRIDE_AMOUNT ,
        RECEIVED_AMOUNT ,
        MODIFY_USER_ID ,
        MODIFY_DATETIME ,
        UNKNOWN_BEN_ID ,
        COMMENT_TEXT ,
        MONTHLY_AMOUNT ,
        RECURSIVE_AMOUNT ,
        TBD_FLAG ,
        CREATE_DATETIME ,
        CREATE_USER_ID ,
        SEAL_FLAG
from
        OFFENDER_BENEFICIARIES Off_Bnc
where
        Off_Bnc.OFFENDER_DEDUCTION_ID in (
        select
                vod.offender_deduction_id
        FROM offender_deduction_receipts odr, deduction_types dt, account_codes ac, v_offender_deductions vod
LEFT OUTER JOIN offender_mon_deductions omd ON (vod.offender_deduction_id = omd.offender_deduction_id)
WHERE vod.profile_caseload_id = :caseLoadId and odr.offender_deduction_id = vod.offender_deduction_id and date_trunc('day', vod.effective_date) <= date_trunc('day', LOCALTIMESTAMP) and coalesce(vod.delay_recapture,
                0) <= date_trunc('day', LOCALTIMESTAMP)::date - date_trunc('day', vod.effective_date)::date and vod.deduction_status = 'A' and vod.account_code = ac.account_code and ac.caseload_type = 
                :caseLoadType and vod.deduction_type = dt.deduction_type and dt.deduction_category not in ('ALCN', 'DTF', 'CTF') and dt.caseload_code in ('BOTH', :caseLoadType)  and ( ( (vod.max_monthly_amount IS NOT NULL AND vod.max_monthly_amount::text <> '')
                and ( omd.monthly_deduction_date =(
                select
                        MAX(omd4.monthly_deduction_date)
                from
                        offender_mon_deductions omd4
                where
                        omd4.offender_deduction_id = vod.offender_deduction_id)
                or not exists (
                select
                        '1'
                from
                        offender_mon_deductions omd4
                where
                        omd4.offender_deduction_id = vod.offender_deduction_id) ) )
                or ( coalesce(vod.max_monthly_amount::text, '') = ''
                and coalesce(vod.max_recursive_amount::text, '') = '')
                or ( (vod.max_recursive_amount IS NOT NULL AND vod.max_recursive_amount::text <> '')
                and ( not exists (
                select
                        '1'
                from
                        offender_mon_deductions omd1
                where
                        omd1.offender_deduction_id = vod.offender_deduction_id)
                or exists(
                select
                        '1'
                from
                        offender_mon_deductions omd1
                where
                        omd1.offender_deduction_id = vod.offender_deduction_id
                        and omd1.monthly_deduction_date =(
                        select
                                MAX(omd2.monthly_deduction_date)
                        from
                                offender_mon_deductions omd2
                        where
                                omd2.offender_deduction_id = omd1.offender_deduction_id) )
                and omd.offender_deduction_id = vod.offender_deduction_id
                and omd.monthly_deduction_date =(
                select
                        MAX(omd3.monthly_deduction_date)
                from
                        offender_mon_deductions omd3
                where
                        omd3.offender_deduction_id = omd.offender_deduction_id) ) ) ) and ( (coalesce(vod.deduction_amount,
                0) + coalesce(vod.adjustment_amount,
                0) < coalesce(vod.max_total_amount,
                0)
                or coalesce(vod.max_total_amount::text, '') = '')
                or coalesce(vod.max_monthly_amount,
                0) > coalesce((
                select
                        SUM(CASE WHEN bt.txn_post_usage='CR' THEN  bt.txn_entry_amount  ELSE -bt.txn_entry_amount END )
                from
                        beneficiary_transactions bt
                where
                        bt.offender_deduction_id = vod.offender_deduction_id
                        and bt.txn_entry_date between date_trunc('month', LOCALTIMESTAMP) and (date_trunc('month', LOCALTIMESTAMP) + '1 month'::interval)::date - 1),
                0)
                or coalesce(vod.max_recursive_amount,
                0) > coalesce(omd.deduction_amount,
                0)
                or vod.unlimited_deduction = 'Y') and vod.offender_id = :offenderId and odr.receipt_txn_type = :receiptTxnType  )
order by
        OFFENDER_DEDUCTION_ID;
}

OCUOVROB_OFFBNC_FIND_OFFENDER_BENEFICIARIES_QUERY{
SELECT BENEFICIARY_ID ,OFFENDER_DEDUCTION_ID ,OFFENDER_ID ,PERSON_ID ,CORPORATE_ID ,PRIORITY ,AMOUNT ,PERCENT ,OVERRIDE_AMOUNT ,RECEIVED_AMOUNT ,MODIFY_USER_ID ,MODIFY_DATETIME ,UNKNOWN_BEN_ID ,COMMENT_TEXT ,MONTHLY_AMOUNT ,RECURSIVE_AMOUNT ,TBD_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,SEAL_FLAG  
 	FROM OFFENDER_BENEFICIARIES
}

OCUOVROB_OFFBNC_UPDATE_OFFENDER_BENEFICIARIES {
	--UPDATE OFFENDER_BENEFICIARIES set BENEFICIARY_ID  = :beneficiaryId ,OFFENDER_DEDUCTION_ID  = :offenderDeductionId ,OFFENDER_ID  = :offenderId ,PERSON_ID  = :personId ,CORPORATE_ID  = :corporateId ,PRIORITY  = :priority ,AMOUNT  = :amount ,PERCENT  = :percent ,OVERRIDE_AMOUNT  = :overrideAmount ,RECEIVED_AMOUNT  = :receivedAmount ,MODIFY_USER_ID  = :modifyUserId ,UNKNOWN_BEN_ID  = :unknownBenId ,COMMENT_TEXT  = :commentText ,MONTHLY_AMOUNT  = :monthlyAmount ,RECURSIVE_AMOUNT  = :recursiveAmount ,TBD_FLAG  = :tbdFlag ,SEAL_FLAG  = :sealFlag  where BENEFICIARY_ID  = :beneficiaryId
	update OFFENDER_BENEFICIARIES set BENEFICIARY_ID = :beneficiaryId , OFFENDER_DEDUCTION_ID = :offenderDeductionId , OFFENDER_ID = :offenderId , PERSON_ID = :personId , CORPORATE_ID = :corporateId , PRIORITY = :priority , AMOUNT = :amount , percent = :percent , OVERRIDE_AMOUNT = :overrideAmount , RECEIVED_AMOUNT = :receivedAmount , MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP, UNKNOWN_BEN_ID = :unknownBenId , COMMENT_TEXT = :commentText , MONTHLY_AMOUNT = :monthlyAmount , RECURSIVE_AMOUNT = :recursiveAmount , TBD_FLAG = :tbdFlag , SEAL_FLAG = :sealFlag where BENEFICIARY_ID = :beneficiaryId
	
}

OCUOVROB_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}
OCUOVROB_SYSPFL_INSERT_SYSTEM_PROFILES {
	--INSERT INTO SYSTEM_PROFILES(PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG ) VALUES(:profileType ,:profileCode ,:description ,:profileValue ,:profileValue2 ,:modifyUserId ,:oldTableName ,:createDatetime ,:createUserId ,:modifyDatetime ,:sealFlag )
	insert into SYSTEM_PROFILES(PROFILE_TYPE , PROFILE_CODE , DESCRIPTION , PROFILE_VALUE , PROFILE_VALUE_2 , OLD_TABLE_NAME , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG ) values(:profileType , :profileCode , :description , :profileValue , :profileValue2 , :oldTableName , CURRENT_TIMESTAMP , :createUserId , CURRENT_TIMESTAMP , :sealFlag )
}  

OCUOVROB_SYSPFL_DELETE_SYSTEM_PROFILES { 
	DELETE FROM SYSTEM_PROFILES/* where */
}  


OCUOVROB_CGFKCHK_OFF_BNC_OFF_BNC_CORP__ {
	SELECT CORP.CORPORATE_NAME FROM   CORPORATES CORP WHERE  CORP.CORPORATE_ID = :CORPORATEID
}

OCUOVROB_CGFKCHK_OFF_BNC_OFF_BNC_PER_F {
	SELECT PER.LAST_NAME ,PER.FIRST_NAME FROM   PERSONS PER WHERE  PER.PERSON_ID = :PERSONID
}

OCUOVROB_CGFKCHK_OFF_BNC_OFF_BNC_OFF_D{
	SELECT OFF_DED1.DEDUCTION_TYPE ,OFF_DED1.EFFECTIVE_DATE ,OFF_DED1.MAX_TOTAL_AMOUNT ,OFF_DED1.MAX_RECURSIVE_AMOUNT ,OFF_DED1.INFORMATION_NUMBER  FROM   OFFENDER_DEDUCTIONS OFF_DED1 WHERE  OFF_DED1.OFFENDER_DEDUCTION_ID = :OFFENDERDEDUCTIONID
}

OCUOVROB_CGFDGET_OFF_BNC_DRV_AMOUNT {
	SELECT ADJUSTMENT_AMOUNT FROM OFFENDER_DEDUCTIONS WHERE OFFENDER_DEDUCTION_ID = :LV_OFF_DED_ID
}

OCUOVROB_GET_CASELOAD_TYPE {
SELECT CASELOAD_TYPE
FROM CASELOADS
WHERE
  CASELOAD_ID = 
    (SELECT WORKING_CASELOAD_ID
     FROM STAFF_MEMBERS
     WHERE
       USER_ID = :createUserId)
}

OCUOVROB_GET_CORPORATE_NAME {
SELECT CORP.CORPORATE_NAME
        FROM   CORPORATES CORP
        WHERE  CORP.CORPORATE_ID = :corporateId
}

OCUOVROB_CHECK_CFPP_SETUP {
SELECT CASE WHEN COUNT(*)=0 THEN  'N'  ELSE 'Y' END
FROM offender_deduction_receipts odr
WHERE
  odr.offender_deduction_id = :p_off_ded_id AND
  odr.receipt_txn_type = 'CFPP'

}
CHECK_PAYMENT_PLAN {
SELECT profile_value  FROM system_profiles  WHERE profile_type = 'CLIENT' AND profile_code = 'PAYMENT_PLAN'
                     }
GET_CASE_NUMBER {
SELECT off_ded.information_number
                    FROM offender_deductions off_ded
                   WHERE off_ded.offender_deduction_id = :p_ded_id
}
GET_OFFENDER_BOOK_ID_CUR {
                  SELECT offender_id, offender_book_id
                    FROM offender_bookings
                   WHERE root_offender_id = :lv_root_offender_id
                     AND offender_book_id = (SELECT MAX (offender_book_id)
                                               FROM offender_bookings
                                              WHERE root_offender_id = :lv_root_offender_id)
                                              }
 GET_TRANS_DETAIL_CUR {
                  SELECT dr_account_code
                    FROM transaction_operations
                   WHERE module_name = 'OCDCPPAY'
                     AND caseload_id = :lv_caseload_id
                     }

GET_DEDUCTION_INFO_CUR {
                  SELECT deduction_type, information_number, group_id
                    FROM offender_deductions
                   WHERE offender_deduction_id = :lv_deduction_id
                   }
GET_CR_ACC_CODE_CUR {
                  SELECT account_code
                    FROM caseload_deduction_profiles
                   WHERE caseload_id = :lv_caseload_id
                     AND deduction_type = :deduction_type
                     }

GET_SUB_ACCOUNT_TYPE_CUR {
                  SELECT sub_account_type
                    FROM account_codes
                   WHERE account_code = :dr_account_code
                   }
GET_POSTING_TYPE_CUR {
      SELECT txn_posting_type
        FROM account_codes
       WHERE account_code = :cp_account_code
       }
INSERT_INTO_BEN_TRANSACTIONS {
          INSERT INTO BENEFICIARY_TRANSACTIONS
( txn_id, txn_entry_seq, gl_entry_seq, ben_entry_seq, offender_deduction_id, account_code, person_id, corporate_id, unknown_ben_id,txn_post_usage,
txn_entry_date ,txn_entry_time, txn_entry_amount, caseload_id, txn_type, txn_entry_desc, modify_date, rev_txn_id, rev_txn_entry_seq,
rev_gl_entry_seq, rev_ben_entry_seq, rev_txn_flag, receipt_txn_type, beneficiary_id, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME )
VALUES (:txnId,:txnEntrySeq,2,1,:offenderDeductionId,:accountCode,:personId,:corporateId,:unknownBenId,'CR',
current_date, current_timestamp,0,:caseloadId, 'DEDC',:txnEntryDesc,current_date,NULL,NULL, NULL,NULL, NULL,'CFPP',:beneficiaryId, CURRENT_TIMESTAMP , :createUserId , CURRENT_TIMESTAMP)
}

GET_TXN_ID_ONE{
SELECT NEXTVAL('TXN_ID') FROM DUAL
}

INS_OFF_CR_PRIOR_PAYMENTS {
--INSERT INTO offender_credit_prior_payments
  --             (txn_id, caseload_id, offender_id, payment_date, payment_amount, location, comment_text, txn_entry_seq, beneficiary_id)
    --    VALUES (:txnId,:caseloadId,:offenderId,TRUNC (SYSDATE),:overrideAmount,:location,:commentText,:txnEntrySeq,:beneficiaryId)

        INSERT INTO offender_credit_prior_payments
               (txn_id, caseload_id, offender_id, payment_date, payment_amount, location, comment_text, txn_entry_seq, beneficiary_id, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME)
        VALUES (:txnId,:caseloadId,:offenderId,current_date,:overrideAmount,:location,:commentText,:txnEntrySeq,:beneficiaryId, CURRENT_TIMESTAMP, :createUserId, CURRENT_TIMESTAMP)
} 
UPDT_OFFENDER_DEDUCTIONS {
 --UPDATE offender_deductions
   --   SET deduction_amount = NVL (deduction_amount, 0) + :lv_amount,
     --     modify_user_id = :createUserId,
       --   modify_date = current_date
    --WHERE offender_deduction_id = :lv_deduction_id
    UPDATE offender_deductions
      SET deduction_amount = coalesce (deduction_amount, 0) + :lv_amount,
          modify_user_id = :modifyUserId,
          modify_date = current_date,
          MODIFY_DATETIME = CURRENT_TIMESTAMP
    WHERE offender_deduction_id = :lv_deduction_id

    }
    
GET_RECEIVED_AMOUNT{
  select TO_CHAR (:OVERRIDE_AMOUNT::numeric  +  (coalesce (:RECEIVED_AMOUNT::numeric , '0'))) from dual;
}
    
GET_OFFENDER_BENEFICIARIES_OLD_REC{
select * from OFFENDER_BENEFICIARIES where beneficiary_id = :beneficiaryId 
}

GET_OFFENDER_DEDUCTIONS_OLD_REC{
SELECT  * FROM offender_deductions where offender_deduction_id = :offenderDeductionId
}