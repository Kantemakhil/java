
OTSTASTA_FIND_CGFKOMSREQPRINTERID {
 	SELECT PRTR.PRINTER_ID PRINTER_ID   ,PRTR.DESCRIPTION DSP_DESCRIPTION FROM PRINTERS PRTR WHERE (PRTR.EXPIRY_DATE IS NULL ) OR (PRTR.EXPIRY_DATE > SYSDATE )
}

OTSTASTA_FIND_CGFKACCODESUBACCOUNTTYPE {
 	SELECT REF_CODE.CODE  SUB_ACCOUNT_TYPE          ,REF_CODE.DESCRIPTION  DSP_DESCRIPTION        ,REF_CODE.LIST_SEQ  DSP_LIST_SEQ FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'SUB_AC_TYPE' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL
}

OTSTASTA_ACCODE_FIND_ACCOUNT_CODES {
 	SELECT REC_ACCOUNT_CODE ,ACCOUNT_CODE ,ACCOUNT_NAME ,POSTING_STATUS_FLAG ,ACCOUNT_TYPE ,SUB_ACCOUNT_TYPE ,TXN_POSTING_TYPE ,ALL_CASELOAD_FLAG ,MODIFY_USER_ID ,MODIFY_DATE ,LIST_SEQ ,CASELOAD_TYPE ,PARENT_ACCOUNT_CODE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM ACCOUNT_CODES  /* where  */
}
OTSTASTA_ACCODE_INSERT_ACCOUNT_CODES {
insert into ACCOUNT_CODES(REC_ACCOUNT_CODE , ACCOUNT_CODE , ACCOUNT_NAME , POSTING_STATUS_FLAG , ACCOUNT_TYPE , SUB_ACCOUNT_TYPE , TXN_POSTING_TYPE , ALL_CASELOAD_FLAG , MODIFY_USER_ID , MODIFY_DATE , LIST_SEQ , CASELOAD_TYPE , PARENT_ACCOUNT_CODE , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG ) values(:recAccountCode , :accountCode , :accountName , :postingStatusFlag , :accountType , :subAccountType , :txnPostingType , :allCaseloadFlag , null , :modifyDate , :listSeq , :caseloadType , :parentAccountCode , :createDatetime , :createUserId , null , :sealFlag )
}

OTSTASTA_OFFBKG1_FIND_OFFENDER_BOOKINGS {
 	SELECT OFFENDER_BOOK_ID ,BOOKING_BEGIN_DATE ,BOOKING_END_DATE ,BOOKING_NO ,OFFENDER_ID ,AGY_LOC_ID ,LIVING_UNIT_ID ,DISCLOSURE_FLAG ,IN_OUT_STATUS ,ACTIVE_FLAG ,BOOKING_STATUS ,YOUTH_ADULT_CODE ,FINGER_PRINTED_STAFF_ID ,SEARCH_STAFF_ID ,PHOTO_TAKING_STAFF_ID ,ASSIGNED_STAFF_ID ,CREATE_AGY_LOC_ID ,BOOKING_TYPE ,BOOKING_CREATED_DATE ,ROOT_OFFENDER_ID ,AGENCY_IML_ID ,SERVICE_FEE_FLAG ,EARNED_CREDIT_LEVEL ,EKSTRAND_CREDIT_LEVEL ,INTAKE_AGY_LOC_ID ,ACTIVITY_DATE ,INTAKE_CASELOAD_ID ,INTAKE_USER_ID ,CASE_OFFICER_ID ,CASE_DATE ,CASE_TIME ,COMMUNITY_ACTIVE_FLAG ,CREATE_INTAKE_AGY_LOC_ID ,COMM_STAFF_ID ,COMM_STATUS ,COMMUNITY_AGY_LOC_ID ,NO_COMM_AGY_LOC_ID ,COMM_STAFF_ROLE ,AGY_LOC_ID_LIST ,STATUS_REASON ,TOTAL_UNEXCUSED_ABSENCES ,REQUEST_NAME ,RECORD_USER_ID ,INTAKE_AGY_LOC_ASSIGN_DATE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM OFFENDER_BOOKINGS  /* where  */
}
OTSTASTA_OFFBKG1_INSERT_OFFENDER_BOOKINGS {
insert into OFFENDER_BOOKINGS(OFFENDER_BOOK_ID , BOOKING_BEGIN_DATE , BOOKING_END_DATE , BOOKING_NO , OFFENDER_ID , AGY_LOC_ID , LIVING_UNIT_ID , DISCLOSURE_FLAG , IN_OUT_STATUS , ACTIVE_FLAG , BOOKING_STATUS , YOUTH_ADULT_CODE , FINGER_PRINTED_STAFF_ID , SEARCH_STAFF_ID , PHOTO_TAKING_STAFF_ID , ASSIGNED_STAFF_ID , CREATE_AGY_LOC_ID , BOOKING_TYPE , BOOKING_CREATED_DATE , ROOT_OFFENDER_ID , AGENCY_IML_ID , SERVICE_FEE_FLAG , EARNED_CREDIT_LEVEL , EKSTRAND_CREDIT_LEVEL , INTAKE_AGY_LOC_ID , ACTIVITY_DATE , INTAKE_CASELOAD_ID , INTAKE_USER_ID , CASE_OFFICER_ID , CASE_DATE , CASE_TIME , COMMUNITY_ACTIVE_FLAG , CREATE_INTAKE_AGY_LOC_ID , COMM_STAFF_ID , COMM_STATUS , COMMUNITY_AGY_LOC_ID , NO_COMM_AGY_LOC_ID , COMM_STAFF_ROLE , AGY_LOC_ID_LIST , STATUS_REASON , TOTAL_UNEXCUSED_ABSENCES , REQUEST_NAME , RECORD_USER_ID , INTAKE_AGY_LOC_ASSIGN_DATE , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , MODIFY_USER_ID , SEAL_FLAG ) values(:offenderBookId , :bookingBeginDate , :bookingEndDate , :bookingNo , :offenderId , :agyLocId , :livingUnitId , :disclosureFlag , :inOutStatus , :activeFlag , :bookingStatus , :youthAdultCode , :fingerPrintedStaffId , :searchStaffId , :photoTakingStaffId , :assignedStaffId , :createAgyLocId , :bookingType , :bookingCreatedDate , :rootOffenderId , :agencyImlId , :serviceFeeFlag , :earnedCreditLevel , :ekstrandCreditLevel , :intakeAgyLocId , :activityDate , :intakeCaseloadId , :intakeUserId , :caseOfficerId , :caseDate , :caseTime , :communityActiveFlag , :createIntakeAgyLocId , :commStaffId , :commStatus , :communityAgyLocId , :noCommAgyLocId , :commStaffRole , :agyLocIdList , :statusReason , :totalUnexcusedAbsences , :requestName , :recordUserId , :intakeAgyLocAssignDate , :createDatetime , :createUserId , null , null , :sealFlag )
}

OTSTASTA_OFFBKG1_UPDATE_OFFENDER_BOOKINGS {
 update OFFENDER_BOOKINGS set OFFENDER_BOOK_ID = :offenderBookId , BOOKING_BEGIN_DATE = :bookingBeginDate , BOOKING_END_DATE = :bookingEndDate , BOOKING_NO = :bookingNo , OFFENDER_ID = :offenderId , AGY_LOC_ID = :agyLocId , LIVING_UNIT_ID = :livingUnitId , DISCLOSURE_FLAG = :disclosureFlag , IN_OUT_STATUS = :inOutStatus , ACTIVE_FLAG = :activeFlag , BOOKING_STATUS = :bookingStatus , YOUTH_ADULT_CODE = :youthAdultCode , FINGER_PRINTED_STAFF_ID = :fingerPrintedStaffId , SEARCH_STAFF_ID = :searchStaffId , PHOTO_TAKING_STAFF_ID = :photoTakingStaffId , ASSIGNED_STAFF_ID = :assignedStaffId , CREATE_AGY_LOC_ID = :createAgyLocId , BOOKING_TYPE = :bookingType , BOOKING_CREATED_DATE = :bookingCreatedDate , ROOT_OFFENDER_ID = :rootOffenderId , AGENCY_IML_ID = :agencyImlId , SERVICE_FEE_FLAG = :serviceFeeFlag , EARNED_CREDIT_LEVEL = :earnedCreditLevel , EKSTRAND_CREDIT_LEVEL = :ekstrandCreditLevel , INTAKE_AGY_LOC_ID = :intakeAgyLocId , ACTIVITY_DATE = :activityDate , INTAKE_CASELOAD_ID = :intakeCaseloadId , INTAKE_USER_ID = :intakeUserId , CASE_OFFICER_ID = :caseOfficerId , CASE_DATE = :caseDate , CASE_TIME = :caseTime , COMMUNITY_ACTIVE_FLAG = :communityActiveFlag , CREATE_INTAKE_AGY_LOC_ID = :createIntakeAgyLocId , COMM_STAFF_ID = :commStaffId , COMM_STATUS = :commStatus , COMMUNITY_AGY_LOC_ID = :communityAgyLocId , NO_COMM_AGY_LOC_ID = :noCommAgyLocId , COMM_STAFF_ROLE = :commStaffRole , AGY_LOC_ID_LIST = :agyLocIdList , STATUS_REASON = :statusReason , TOTAL_UNEXCUSED_ABSENCES = :totalUnexcusedAbsences , REQUEST_NAME = :requestName , RECORD_USER_ID = :recordUserId , INTAKE_AGY_LOC_ASSIGN_DATE = :intakeAgyLocAssignDate , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag 
}

OTSTASTA_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}

OTSTASTA_OMS_REQ_PREINSERT_ {
 SELECT NEXTVAL('REQUEST_ID') FROM DUAL
}

OTSTASTA_OTSTASTA_KEYCOMMIT_ {
SELECT BOOKING_BEGIN_DATE FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = LV_OFF_BOOK_ID
}

OTSTASTA_CGFKCHK_OMS_REQ_OMS_REQ_PRINT_ {
	SELECT PRTR.DESCRIPTION FROM   PRINTERS PRTR WHERE  PRTR.PRINTER_ID = :PRINTERID
}

OTSTASTA_CGWHEN_NEW_FORM_INSTANCE{
	SELECT current_timestamp, USER FROM DUAL
}

OTSTASTA_CGFKCHK_AC_CODE_AC_SUB_ACCT_T_ {
	SELECT REF_CODE.DESCRIPTION ,REF_CODE.LIST_SEQ FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.CODE = :SUBACCOUNTTYPE AND     DOMAIN = 'SUB_AC_TYPE' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL
}

OTSTASTA_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

OTSTASTA_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'
}

OTSTASTA_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'REPORT_ALIAS'
}

OTSTASTA_RUN_REPORT_ {
	SELECT LTRIM(RTRIM(DESCRIPTION)) FROM SYSTEM_PROFILES WHERE PROFILE_CODE = P_PROFILE_CODE AND PROFILE_TYPE = P_PROFILE_TYPE
}
OTSTASTA_RUN_REPORT_SUMMARY {
select OFFENDER_ID, CASELOAD_ID, ( select SUB_ACCOUNT_TYPE from ACCOUNT_CODES where ACCOUNT_CODE = osa.TRUST_ACCOUNT_CODE) SUB_ACCOUNT_TYPE, ( select coalesce(SUM(case when SIGN(extract( day from date_trunc('day', ot.txn_entry_date) - TO_DATE(:STARTDATE::Text, 'DD/MM/YYYY')))=-1 then ot.txn_entry_amount * case when ot.txn_posting_type = 'DR' then -1 else 1 end else 0 end ), 0) from offender_transactions ot where OFFENDER_ID = osa.offender_Id and CASELOAD_ID = osa.CASELOAD_ID and SUB_ACCOUNT_TYPE = ( select SUB_ACCOUNT_TYPE from ACCOUNT_CODES where ACCOUNT_CODE = osa.TRUST_ACCOUNT_CODE)) OPENING_BALANCE, ( select coalesce(SUM(case when SIGN(extract( day from date_trunc('day', ot.txn_entry_date) - TO_DATE(:ENDDATE::Text, 'dd/MM/YYYY')))= 1 then 0 else ot.txn_entry_amount * case when ot.txn_posting_type = 'DR' then -1 else 1 end end ), 0) from offender_transactions ot where OFFENDER_ID = osa.offender_Id and CASELOAD_ID = osa.CASELOAD_ID and SUB_ACCOUNT_TYPE = ( select SUB_ACCOUNT_TYPE from ACCOUNT_CODES where ACCOUNT_CODE = osa.TRUST_ACCOUNT_CODE)) CLOSING_BALANCE from offender_sub_accounts osa where osa.offender_Id = :OFFENDERID and osa.CASELOAD_ID = :CASELOADID order by SUB_ACCOUNT_TYPE
}
OTSTASTA_RUN_REPORT_CASELOAD_TYPE {
	SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID = :CASELOAD
}
OTSTASTA_RUN_REPORT_HEADERDATA {
	SELECT description FROM system_profiles WHERE profile_type = 'LABEL' AND profile_code = :PROFILECODE
}
OTSTASTA_HEADER_CASELOAD_NAME {
	SELECT DESCRIPTION FROM   CASELOADS WHERE  CASELOAD_ID = :CASELOAD
}
OTSTASTA_PROFILE_CODE_FACILITY {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_CODE = 'INST_AGENCY'
}
OTSTASTA_PROFILE_CODE_DOB_DATA {
	SELECT UPPER(PROFILE_VALUE) FROM SYSTEM_PROFILES WHERE PROFILE_CODE = 'BIRTH_DATE'
}
OTSTASTA_REPORT_DEBS_OBLIGATIONS {
select ow.offender_id, ow.caseload_id, ow.deduction_type as type, UPPER(wt.deduction_desc) payable, SUBSTR(ow.information_number, 1, 12) info_number, ow.max_total_amount, ow.max_monthly_amount, coalesce(case when coalesce(ow.max_total_amount::text, '') = '' then (ow.max_monthly_amount - coalesce(omw.deduction_amount, 0)) else (ow.max_total_amount - coalesce(ow.deduction_amount, 0)) end , 0 ) amount, coalesce(omw.deduction_amount, ow.deduction_amount) amt_paid, ow.adjustment_reason_code, ow.offender_deduction_id, coalesce( ow.adjustment_amount, 0) wrtoff from deduction_types wt, offender_deductions ow left outer join offender_mon_deductions omw on (ow.offender_deduction_id = omw.offender_deduction_id and date_trunc('month', LOCALTIMESTAMP) = omw.monthly_deduction_date) where ow.deduction_type = wt.deduction_type and wt.deduction_category <> 'ALCN' and coalesce(:DISCLOSUREFLAG, 'Y') = 'Y' and ow.OFFENDER_ID = :OFFENDERID and ow.CASELOAD_ID = :CASELOADID order by ow.deduction_type desc;
}
OTSTASTA_PROFILE_VALUE_ID {
SELECT PROFILE_VALUE
   FROM SYSTEM_PROFILES
  WHERE PROFILE_CODE = 'OFF_ID_CODE'
    AND PROFILE_TYPE = 'LABEL'
}
OTSTASTA_SUB_ACCOUNT_BALANCES_DATA {
SELECT osa.offender_id,
       osa.caseload_id,
       ac.sub_account_type,
       UPPER(ac.account_name) account_name
  FROM offender_sub_accounts osa,
       account_codes ac,
       caseloads csld
 WHERE osa.trust_account_code = ac.account_code
 AND csld.caseload_id = osa.caseload_id
 AND csld.caseload_type = ac.caseload_type AND osa.offender_id = :OFFENDERID AND osa.caseload_id = :CASELOADID
}
OTSTASTA_MAIN_HEADER_QUERY_REPORT {
}
OTSTASTA_SUB_ACCOUNT_TRANSACTIONS_QUERY {
select ott.caseload_id, ott.offender_id, ott.sub_account_type sub_acct_type, txn_entry_date, txn_type, txn_id, TXN_ENTRY_SEQ, ott.offender_book_id, SUBSTR (txn_entry_desc, 1, 70) txn_desc, txn_entry_amount * case ott.txn_posting_type when 'CR' then 1 else -1 end txn_entry_amount from offender_transactions ott, account_codes ac where exists ( select 1 from caseloads c where c.caseload_id = ott.caseload_id and c.caseload_type = ac.caseload_type) and date_trunc ('day', ott.txn_entry_date) between TO_DATE(:STARTDATE::text, 'DD/MM/YYYY') and TO_DATE(:ENDDATE::text, 'DD/MM/YYYY') and ( UPPER (ott.sub_account_type) = UPPER (:SUBACT) or :SUBACT is null) and coalesce (:DISCLOSUREFLAG, 'Y') = 'Y' and ott.sub_account_type = ac.sub_account_type and ott.OFFENDER_ID = :OFFENDERID and ott.CASELOAD_ID = :CASELOADID order by ott.caseload_id, ott.offender_id, ott.sub_account_type, ott.txn_id, ott.txn_entry_seq, txn_entry_date
}

OTSTASTA_SUB_ACCOUNT_TRANSACTIONS_QUERY_WITHOUT_SUB_ACCOUNT_TYPE {
 SELECT ott.caseload_id, ott.offender_id, ott.sub_account_type sub_acct_type, txn_entry_date, txn_type, txn_id, TXN_ENTRY_SEQ, ott.offender_book_id, SUBSTR (txn_entry_desc, 1, 70) txn_desc, txn_entry_amount * CASE ott.txn_posting_type WHEN 'CR' THEN 1 ELSE -1 END txn_entry_amount FROM offender_transactions ott, account_codes ac WHERE EXISTS (SELECT 1 FROM caseloads c WHERE c.caseload_id = ott.caseload_id AND c.caseload_type = ac.caseload_type) AND date_trunc ('day',ott.txn_entry_date) BETWEEN TO_DATE(:STARTDATE::text, 'DD/MM/YYYY') AND TO_DATE(:ENDDATE::text, 'DD/MM/YYYY') AND COALESCE (:DISCLOSUREFLAG, 'Y') = 'Y' and ott.sub_account_type = ac.sub_account_type AND ott.OFFENDER_ID = :OFFENDERID AND ott.CASELOAD_ID = :CASELOADID ORDER BY ott.caseload_id, ott.offender_id, ott.sub_account_type, ott.txn_id, ott.txn_entry_seq, txn_entry_date
}

OTSTASTA_BOOK_LABEL_DATA {
	SELECT UPPER(PROFILE_VALUE) FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'LABEL' AND PROFILE_CODE = 'BOOK_ID'
}
OTSTASTA_BOOKING_NUMBER_QUERY {
	SELECT BOOKING_NO FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = :OFFENDERBOOKID
}
OTSTASTA_DOLLAR_SYMBOL {
	SELECT UPPER(profile_value) FROM system_profiles WHERE profile_type = 'DISPLAY' AND profile_code = 'CURRENCY'
}
OTSTASTA_ACCOUNT_CODE_REG_SAV {
	SELECT UPPER(ACCOUNT_NAME) FROM ACCOUNT_CODES WHERE SUB_ACCOUNT_TYPE = :SUBACCOUNTTYPE AND CASELOAD_TYPE = :CASELOADTYPE
}
OTSTASTA_GETTING_BALANCE {
	SELECT (coalesce (:OPENINGBALANCE, 0) + coalesce(:TXNENTRYAMOUNT, 0)) FROM DUAL
}