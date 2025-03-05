
OCIPBENE_PER_FIND_PERSONS {
 	SELECT PERSON_ID ,LAST_NAME ,FIRST_NAME ,MIDDLE_NAME ,BIRTHDATE ,OCCUPATION_CODE ,CRIMINAL_HISTORY_TEXT ,NAME_TYPE ,ALIAS_PERSON_ID ,ROOT_PERSON_ID ,LANGUAGE_CODE ,COMPREHEND_ENGLISH_FLAG ,SEX ,BIRTH_PLACE ,EMPLOYER ,PROFILE_CODE ,INTERPRETER_REQUIRED ,PRIMARY_LANGUAGE_CODE ,MEMO_TEXT ,SUSPENDED_FLAG ,MARITAL_STATUS ,CITIZENSHIP ,DECEASED_DATE ,CORONER_NUMBER ,ATTENTION ,CARE_OF ,SUSPENDED_DATE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,STAFF_FLAG ,REMITTER_FLAG ,LAST_NAME_SOUNDEX ,FIRST_NAME_KEY ,MIDDLE_NAME_KEY ,LAST_NAME_KEY ,SEAL_FLAG   FROM PERSONS  /* where  */
}
OCIPBENE_PER_UPDATE_PERSONS {
	UPDATE PERSONS set PERSON_ID  = :personId ,LAST_NAME  = :lastName ,FIRST_NAME  = :firstName ,MIDDLE_NAME  = :middleName ,BIRTHDATE  = :birthdate ,OCCUPATION_CODE  = :occupationCode ,CRIMINAL_HISTORY_TEXT  = :criminalHistoryText ,NAME_TYPE  = :nameType ,ALIAS_PERSON_ID  = :aliasPersonId ,ROOT_PERSON_ID  = :rootPersonId ,LANGUAGE_CODE  = :languageCode ,COMPREHEND_ENGLISH_FLAG  = :comprehendEnglishFlag ,SEX  = :sex ,BIRTH_PLACE  = :birthPlace ,EMPLOYER  = :employer ,PROFILE_CODE  = :profileCode ,INTERPRETER_REQUIRED  = :interpreterRequired ,PRIMARY_LANGUAGE_CODE  = :primaryLanguageCode ,MEMO_TEXT  = :memoText ,SUSPENDED_FLAG  = :suspendedFlag ,MARITAL_STATUS  = :maritalStatus ,CITIZENSHIP  = :citizenship ,DECEASED_DATE  = :deceasedDate ,CORONER_NUMBER  = :coronerNumber ,ATTENTION  = :attention ,CARE_OF  = :careOf ,SUSPENDED_DATE  = :suspendedDate ,CREATE_DATETIME  = :createDatetime ,CREATE_USER_ID  = :createUserId ,MODIFY_DATETIME  = current_timestamp ,MODIFY_USER_ID  = :modifyUserId ,STAFF_FLAG  = :staffFlag ,REMITTER_FLAG  = :remitterFlag ,LAST_NAME_SOUNDEX  = :lastNameSoundex ,FIRST_NAME_KEY  = :firstNameKey ,MIDDLE_NAME_KEY  = :middleNameKey ,LAST_NAME_KEY  = :lastNameKey ,SEAL_FLAG  = :sealFlag  where PERSON_ID  = :personId
}

OCIPBENE_OFFBNC_FIND_OFFENDER_BENEFICIARIES {
 	SELECT BENEFICIARY_ID ,OFFENDER_DEDUCTION_ID ,OFFENDER_ID ,PERSON_ID ,CORPORATE_ID ,PRIORITY ,AMOUNT ,PERCENT ,OVERRIDE_AMOUNT ,RECEIVED_AMOUNT ,MODIFY_USER_ID ,MODIFY_DATETIME ,UNKNOWN_BEN_ID ,COMMENT_TEXT ,MONTHLY_AMOUNT ,RECURSIVE_AMOUNT ,TBD_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,SEAL_FLAG   FROM OFFENDER_BENEFICIARIES  WHERE PERSON_ID = :PERSONID/* where  */
}
OCIPBENE_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OCIPBENE_PER_POSTQUERY {
select
	SUITE_NUMBER ,
	case
		when coalesce(STREET_NUMBER::text, '') = '' then null
		else STREET_NUMBER || ', '
	end || STREET STREET ,
	STREET_DIRECTION ,
	ZIP_POSTAL_CODE ,
	CITY_DESC ,
	STATE_DESC ,
	COUNTRY_DESC
from
	PERSON_ADDRESS_V
where
	PERSON_ID = :PERSONID;
}


OCIPBENE_CGFKCHK_OFF_BNC_OFF_BNC_OFF_D {
SELECT OFF_DED.OFFENDER_ID ,OFF_DED.INFORMATION_NUMBER ,OFF_DED.DEDUCTION_TYPE ,OFF_DED.CASELOAD_ID ,OFF_DED.DEDUCTION_PERCENTAGE ,OFF_DED.PROCESS_PRIORITY_NUMBER ,OFF_DED.EFFECTIVE_DATE ,OFF_DED.MODIFY_DATE ,OFF_DED.MODIFY_USER_ID ,OFF_DED.FIFO_FLAG ,OFF_DED.PAYEE_PERSON_ID ,OFF_DED.PAYEE_CORPORATE_ID ,OFF_DED.MAX_MONTHLY_AMOUNT ,OFF_DED.MAX_TOTAL_AMOUNT ,OFF_DED.DEDUCTION_AMOUNT ,OFF_DED.ADJUSTMENT_REASON_CODE ,OFF_DED.ADJUSTMENT_AMOUNT ,OFF_DED.ADJUSTMENT_TXN_ID ,OFF_DED.ADJUSTMENT_TEXT ,OFF_DED.COMMENT_TEXT ,OFF_DED.ADJUSTMENT_USER_ID ,OFF_DED.DEDUCTION_PRIORITY ,OFF_DED.DEDUCTION_STATUS ,OFF_DED.CREDIT_LIMIT ,OFF_DED.PAY_DEDUCTION_FLAG ,OFF_DED.MAX_RECURSIVE_AMOUNT FROM   OFFENDER_DEDUCTIONS OFF_DED WHERE  OFF_DED.OFFENDER_DEDUCTION_ID = :OFFENDERDEDUCTIONID
}

OCIPBENE_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}
OCIPBENE_OFFENDER_DETAILS {

SELECT off.offender_id_display, off.last_name , off.first_name
           FROM offenders off, offender_bookings off_bkg
          WHERE off.root_offender_id = :OFFENDERID
            AND off.root_offender_id = off_bkg.root_offender_id
            AND off.offender_id = off_bkg.offender_id
            AND off_bkg.offender_book_id = (SELECT MAX (offender_book_id)
                                              FROM offender_bookings off_bkg1
                                             WHERE off_bkg1.root_offender_id = off_bkg.root_offender_id)

}
-- SELECT OFFENDER_ID_DISPLAY,LAST_NAME,FIRST_NAME FROM V_HEADER_BLOCK WHERE OFFENDER_ID_DISPLAY IN(SELECT OFFENDER_ID_DISPLAY FROM OFFENDERS WHERE OFFENDER_ID = :OFFENDERID )

OCIPBENE_BANK_CHEQUE_BENEFICIARIES_TXN_ID {
SELECT DISTINCT bcb.person_id,bcb.cheque_txn_id 
                       FROM bank_cheque_beneficiaries bcb
                      WHERE bcb.person_id = :PERSONID
                        AND bcb.cheque_txn_id in (select bcr.txn_id from bank_cheque_registers bcr where bcr.cheque_status = 'PRINTED')
}

OCIPBENE_BANK_CHEQUE_BENEFICIARIES_CHEQUE_AMOUNT {
SELECT distinct CHEQUE_AMOUNT
                       FROM bank_cheque_beneficiaries
                      WHERE cheque_txn_id = :chequeTxnId
 }
 OCIPBENE_OFFENDER_CREDIT_PRIOR_PAYMENTS {
  SELECT coalesce (SUM (ocpp.payment_amount), 0) as sum_cfpp FROM offender_credit_prior_payments ocpp, beneficiary_transactions ben WHERE ben.person_id = :personId AND coalesce (ben.rev_txn_flag, 'N') = 'N' AND ben.txn_type NOT IN ('GBEN', 'BBEN') AND ocpp.txn_id = ben.txn_id AND ocpp.txn_entry_seq = ben.txn_entry_seq
}
--  SELECT NVL (SUM (ocpp.payment_amount), 0)
--              as sum_cfpp
--              FROM offender_credit_prior_payments ocpp, beneficiary_transactions ben
--             WHERE ben.person_id = :personId
--               AND NVL (ben.rev_txn_flag, 'N') = 'N'
--               AND ben.txn_type NOT IN ('GBEN', 'BBEN')
--               AND ocpp.txn_id = ben.txn_id
--               AND ocpp.txn_entry_seq = ben.txn_entry_seq
           
OCIPBENE_OFFENDER_MON_BENEFICIARIES_PAYMENTS { 
SELECT coalesce (received_amount, 0) FROM offender_mon_beneficiaries WHERE offender_deduction_id = :offenderDeductionId AND person_id = :personId AND monthly_deduction_date =date_trunc('month', LOCALTIMESTAMP)


 }
-- SELECT NVL (received_amount, 0)
--              FROM offender_mon_beneficiaries
--             WHERE offender_deduction_id = :offenderDeductionId
--               AND person_id = :personId
--               AND monthly_deduction_date = TRUNC (SYSDATE, 'MM')

 OCIPBENE_OFFENDER_REC_BENEFICIARIES_PAYMENTS { 
SELECT MONTHS_BETWEEN (TRUNC (SYSDATE, 'MM'), TRUNC (effective_date, 'MM')) + 1
              as rec_month
              FROM offender_deductions
             WHERE offender_deduction_id = :offenderDeductionId
 }              
OCIPBENE_PROCEDURE_QUERY_ONE {
 select SUM(coalesce ( case when amount = 999999999.99 then 0 when amount = 99999999.99 then 0 else amount end , 0)) amount, SUM(coalesce(monthly_amount, 0)) monthly_amount, SUM(coalesce(recursive_amount, 0)) recursive_amount, SUM(coalesce( case when monthly_amount::text = '' then received_amount else 0 end , 0)) received_amount from offender_beneficiaries where person_id = :PERSONID
}
--SELECT SUM(nvl(decode(amount, 999999999.99, 0, 99999999.99,0,amount), 0)) amount,
--           SUM(nvl(monthly_amount, 0)) monthly_amount,
--           SUM(nvl(recursive_amount, 0)) recursive_amount,
--           SUM(nvl(decode(monthly_amount, '', received_amount, 0), 0)) received_amount
--      FROM offender_beneficiaries
--     WHERE person_id = :PERSONID

OCIPBENE_PROCEDURE_QUERY_TWO {
 SELECT SUM(received_amount) FROM offender_mon_beneficiaries WHERE monthly_deduction_date =date_trunc('month', LOCALTIMESTAMP) AND person_id = :PERSONID;
}
--SELECT SUM(received_amount)
--      FROM offender_mon_beneficiaries
--     WHERE monthly_deduction_date = trunc(SYSDATE, 'MM')
--       AND person_id = :PERSONID

OCIPBENE_PROCEDURE_QUERY_THREE {
select
	SUM((months_between(date_trunc('month', CURRENT_TIMESTAMP)::timestamp, date_trunc('month', od.effective_date)::timestamp) + 1) * coalesce (od.max_recursive_amount, 0))
from
	offender_deductions od,
	offender_beneficiaries ob
where
	od.offender_deduction_id = ob.offender_deduction_id
	and ob.recursive_amount > 0
	and ob.person_id = :PERSONID
}

--SELECT SUM((months_between(trunc(SYSDATE, 'MM'),
--                                 trunc(od.effective_date, 'MM')) + 1) *
--                 nvl(od.max_recursive_amount, 0))
--        FROM offender_deductions od, offender_beneficiaries ob
--       WHERE od.offender_deduction_id = ob.offender_deduction_id
--         AND ob.recursive_amount > 0
--         AND ob.person_id = :PERSONID

         
         