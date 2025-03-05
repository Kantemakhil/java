
OCDOOBLI_FIND_CGFKOFFDEDDEDUCTIONTYPE {
select DED_TYPE.DEDUCTION_TYPE CODE, DED_TYPE.DEDUCTION_DESC DESCRIPTION , DED_TYPE.FROM_BALANCE_TYPE FROM_BALANCE_TYPE , DED_TYPE.DEDUCTION_CATEGORY DEDUCTION_CATEGORY , DED_TYPE.INCREMENT_PAYABLES_FLAG DSP_INCREMENT_PAYABLES_FLAG, case when (( ACTIVE_FLAG = 'Y' and EXPIRY_DATE is null ) ) and ((DED_TYPE.DEDUCTION_CATEGORY in ('FXOB' , 'CROB' ) ) or ( DED_TYPE.DEDUCTION_CATEGORY in ('FXOB' , 'CROB' , 'TAX' ) and exists ( select '1' from SYSTEM_PROFILES SP where SP.PROFILE_TYPE = 'CLIENT' and SP.PROFILE_CODE = 'TAX_OBLIGN' and SP.PROFILE_VALUE = 'Y' ) ) ) and DED_TYPE.DEDUCTION_TYPE in ( select distinct CDD.DEDUCTION_TYPE from CASELOAD_DEDUCTION_DETAILS CDD where CDD.CASELOAD_ID in ( select WORKING_CASELOAD_ID from STAFF_MEMBERS where USER_ID = :userName) and CDD.DEDUCTION_TYPE in ( select CDP.DEDUCTION_TYPE from CASELOAD_DEDUCTION_PROFILES CDP where CDP.CASELOAD_ID in ( select WORKING_CASELOAD_ID from STAFF_MEMBERS where USER_ID = :userName) and (( CDP.ACTIVE_FLAG = 'Y' and coalesce (CDP.EXPIRY_DATE::timestamp, '2020-12-12')= '2020-12-12' ) ) and date_trunc('day', CDP.EFFECTIVE_DATE) <= date_trunc('day', current_date) ) ) and (PARENT_DEDUCTION_TYPE is null ) then 'Y' else 'N' end ACTIVE_FLAG from DEDUCTION_TYPES DED_TYPE order by DED_TYPE.DEDUCTION_TYPE asc , DED_TYPE.DEDUCTION_DESC asc
}

OCDOOBLI_FIND_CGFKGROUPOBLGROUPID {
 	SELECT to_char(GO.GROUP_ID) CODE  ,OG.GROUP_CODE DESCRIPTION  , to_char(GO.GROUP_ID) GROUP_CODE FROM GROUPED_OBLIGATIONS GO , OBLIGATION_GROUPS OG WHERE GO.GROUP_ID = OG.GROUP_ID AND GO.DEDUCTION_TYPE = :DEDUCTIONTYPE
}

OCDOOBLI_FIND_CGFKOFFDEDDSPDESCRIPTION {
SELECT REF_CODE.DESCRIPTION DSP_DESCRIPTION  , REF_CODE.CODE DEDUCTION_STATUS from REFERENCE_CODES REF_CODE where domain = 'DED_STATUS' and ((ACTIVE_FLAG = 'Y' and coalesce (EXPIRED_DATE::timestamp, '2020-12-12')= '2020-12-12' )) order by REF_CODE.CODE asc , REF_CODE.DESCRIPTION asc
}

OCDOOBLI_FIND_CGFKOFFDED1ADJUSTMENTREASO {
 	SELECT REF_CODE1.CODE  ADJUSTMENT_REASON_CODE        ,REF_CODE1.DESCRIPTION  DSP_DESCRIPTION FROM   REFERENCE_CODES REF_CODE1 WHERE   DOMAIN = 'ADJ_RSN' AND REF_CODE1.CODE = 'WRT'
}

OCDOOBLI_FIND_CGFKOFFDRRECEIPTTXNTYPE {
SELECT TXN_TYPE.TXN_TYPE CODE , TXN_TYPE.DESCRIPTION DESCRIPTION, case when (TXN_USAGE = 'R' and ACTIVE_FLAG = 'Y' and TXN_TYPE.CASELOAD_TYPE = ( select CASELOAD_TYPE from CASELOADS where CASELOAD_ID in ( select WORKING_CASELOAD_ID from STAFF_MEMBERS where USER_ID = :userName))) then 'Y' else 'N' end SEAL_FLAG from TRANSACTION_TYPES TXN_TYPE
}

OCDOOBLI_FIND_CGFKOFFDEDCASEINFONUMBER {
 	SELECT CASE_INFO_NUMBER  , CASE_ID , CASE_STATUS FROM OFFENDER_CASES OC , OFFENDER_BOOKINGS OB WHERE OC.OFFENDER_BOOK_ID = OB.OFFENDER_BOOK_ID AND OB.ROOT_OFFENDER_ID = :ROOTOFFENDERID ORDER BY OC.CASE_STATUS , OC.BEGIN_DATE DESC , OC.CASE_INFO_NUMBER DESC
}

OCDOOBLI_FIND_CGFKOFFBNCPERSONID {
 	SELECT PER.PERSON_ID  PERSON_ID       ,PER.LAST_NAME  DSP_LAST_NAME        ,PER.FIRST_NAME  DSP_FIRST_NAME        ,PER.SUSPENDED_FLAG  DSP_SUSPENDED_FLAG2 FROM   PERSONS PER
}

OCDOOBLI_FIND_CGFKOFFBNCCORPORATEID {
 	SELECT CORP.CORPORATE_ID  CORPORATE_ID          ,CORP.CORPORATE_NAME  DSP_CORPORATE_NAME        ,CORP.SUSPENDED_FLAG  DSP_SUSPENDED_FLAG FROM   CORPORATES CORP ORDER BY 1
}

OCDOOBLI_OFFDED_FIND_OFFENDER_DEDUCTIONS {
 	SELECT OFFENDER_DEDUCTION_ID ,CASELOAD_ID ,OFFENDER_ID ,CREDIT_LIMIT ,DEDUCTION_TYPE ,DEDUCTION_STATUS ,DEDUCTION_PRIORITY ,INFORMATION_NUMBER ,DEDUCTION_PERCENTAGE ,PROCESS_PRIORITY_NUMBER ,
 	EFFECTIVE_DATE ,COMMENT_TEXT ,FIFO_FLAG ,PAYEE_PERSON_ID ,PAYEE_CORPORATE_ID ,MAX_MONTHLY_AMOUNT ,MAX_TOTAL_AMOUNT ,DEDUCTION_AMOUNT ,ADJUSTMENT_REASON_CODE ,
 	ADJUSTMENT_AMOUNT ,ADJUSTMENT_USER_ID ,ADJUSTMENT_TXN_ID ,ADJUSTMENT_TEXT ,MODIFY_DATE ,PAY_DEDUCTION_FLAG ,MAX_RECURSIVE_AMOUNT ,GROUP_ID ,CASE_ID ,PARENT_DEDUCTION_ID ,
 	JS_STATUS ,COLLECT_AGENCY_AMOUNT ,COLLECT_AGENCY_FLAG ,COLLECT_SENT_DATE ,OFFENDER_PAYMENT_PROFILE_ID ,SEAL_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,
 	MODIFY_USER_ID   FROM OFFENDER_DEDUCTIONS WHERE 	
}
OCDOOBLI_OFFDED_INSERT_OFFENDER_DEDUCTIONS {
	INSERT INTO OFFENDER_DEDUCTIONS(OFFENDER_DEDUCTION_ID ,CASELOAD_ID ,OFFENDER_ID ,CREDIT_LIMIT ,DEDUCTION_TYPE ,DEDUCTION_STATUS ,DEDUCTION_PRIORITY ,INFORMATION_NUMBER ,DEDUCTION_PERCENTAGE ,PROCESS_PRIORITY_NUMBER ,EFFECTIVE_DATE ,COMMENT_TEXT ,FIFO_FLAG ,PAYEE_PERSON_ID ,PAYEE_CORPORATE_ID ,MAX_MONTHLY_AMOUNT ,MAX_TOTAL_AMOUNT ,DEDUCTION_AMOUNT ,ADJUSTMENT_REASON_CODE ,ADJUSTMENT_AMOUNT ,ADJUSTMENT_USER_ID ,ADJUSTMENT_TXN_ID ,ADJUSTMENT_TEXT ,MODIFY_DATE ,PAY_DEDUCTION_FLAG ,MAX_RECURSIVE_AMOUNT ,GROUP_ID ,CASE_ID ,PARENT_DEDUCTION_ID ,JS_STATUS ,COLLECT_AGENCY_AMOUNT ,COLLECT_AGENCY_FLAG ,COLLECT_SENT_DATE ,OFFENDER_PAYMENT_PROFILE_ID ,SEAL_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ) VALUES(:offenderDeductionId ,:caseloadId ,:offenderId ,:creditLimit ,:deductionType ,:deductionStatus ,:deductionPriority ,:informationNumber ,:deductionPercentage ,:processPriorityNumber ,:effectiveDate ,:commentText ,:fifoFlag ,:payeePersonId ,:payeeCorporateId ,:maxMonthlyAmount ,:maxTotalAmount ,:deductionAmount ,:adjustmentReasonCode ,:adjustmentAmount ,:adjustmentUserId ,:adjustmentTxnId ,:adjustmentText ,:modifyDate ,:payDeductionFlag ,:maxRecursiveAmount ,:groupId ,:caseId ,:parentDeductionId ,:jsStatus ,:collectAgencyAmount ,:collectAgencyFlag ,:collectSentDate ,:offenderPaymentProfileId ,:sealFlag ,current_timestamp ,:createUserId ,current_timestamp ,:createUserId )
}

OCDOOBLI_OFFDED_UPDATE_OFFENDER_DEDUCTIONS {
update OFFENDER_DEDUCTIONS set CASELOAD_ID = :caseloadId , OFFENDER_ID = :offenderId , CREDIT_LIMIT = :creditLimit , DEDUCTION_TYPE = :deductionType , DEDUCTION_STATUS = :deductionStatus ,COLLECT_AGENCY_FLAG = :collectAgencyFlag ,
DEDUCTION_PRIORITY = :deductionPriority , INFORMATION_NUMBER = :informationNumber , DEDUCTION_PERCENTAGE = :deductionPercentage , PROCESS_PRIORITY_NUMBER = :processPriorityNumber , EFFECTIVE_DATE = :effectiveDate ,
COMMENT_TEXT = :commentText , FIFO_FLAG = :fifoFlag ,PAYEE_PERSON_ID = :payeePersonId , PAYEE_CORPORATE_ID = :payeeCorporateId , MAX_MONTHLY_AMOUNT = :maxMonthlyAmount , MAX_TOTAL_AMOUNT = :maxTotalAmount , DEDUCTION_AMOUNT = :deductionAmount , 
ADJUSTMENT_REASON_CODE = :adjustmentReasonCode , ADJUSTMENT_AMOUNT = :adjustmentAmount , ADJUSTMENT_USER_ID = :adjustmentUserId , ADJUSTMENT_TXN_ID = :adjustmentTxnId , ADJUSTMENT_TEXT = :adjustmentText , MODIFY_DATE = current_date ,
PAY_DEDUCTION_FLAG = :payDeductionFlag , MAX_RECURSIVE_AMOUNT = :maxRecursiveAmount , GROUP_ID = :groupId , CASE_ID = :caseId , PARENT_DEDUCTION_ID = :parentDeductionId , JS_STATUS = :jsStatus , COLLECT_AGENCY_AMOUNT = :collectAgencyAmount ,  
COLLECT_SENT_DATE = :collectSentDate , OFFENDER_PAYMENT_PROFILE_ID = :offenderPaymentProfileId ,SEAL_FLAG = :sealFlag , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId where OFFENDER_DEDUCTION_ID = :offenderDeductionId
}

OCDOOBLI_OFFDED_DELETE_OFFENDER_DEDUCTIONS { 
	DELETE FROM OFFENDER_DEDUCTIONS where OFFENDER_DEDUCTION_ID  = :offenderDeductionId
}

OCDOOBLI_OFFBNC_FIND_OFFENDER_BENEFICIARIES {
select OB.*, case when ( select 'Y' from CASELOAD_DED_BENEFICIARIES where DEDUCTION_TYPE = ( select DEDUCTION_TYPE from OFFENDER_DEDUCTIONS where OFFENDER_DEDUCTION_ID = OB.OFFENDER_DEDUCTION_ID) and CASELOAD_ID = ( select WORKING_CASELOAD_ID from STAFF_MEMBERS where USER_ID = :userName) ) = 'Y' then 'Y' else 'N' end MAINTAINCE_FLAG from OFFENDER_BENEFICIARIES OB where OB.OFFENDER_DEDUCTION_ID = :OFFENDERDEDUCTIONID order by OB.PERSON_ID , OB.CORPORATE_ID 
}
OCDOOBLI_OFFBNC_INSERT_OFFENDER_BENEFICIARIES {
	INSERT INTO OFFENDER_BENEFICIARIES(BENEFICIARY_ID ,OFFENDER_DEDUCTION_ID ,OFFENDER_ID ,PERSON_ID ,CORPORATE_ID ,PRIORITY ,AMOUNT ,PERCENT ,OVERRIDE_AMOUNT ,RECEIVED_AMOUNT ,MODIFY_USER_ID ,MODIFY_DATETIME ,UNKNOWN_BEN_ID ,COMMENT_TEXT ,MONTHLY_AMOUNT ,RECURSIVE_AMOUNT ,TBD_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,SEAL_FLAG )
	VALUES(:beneficiaryId ,:offenderDeductionId ,:offenderId ,:personId ,:corporateId ,:priority ,:amount ,:percent ,:overrideAmount ,:receivedAmount ,:modifyUserId ,:modifyDateTime ,:unknownBenId ,:commentText ,:monthlyAmount ,:recursiveAmount ,:tbdFlag ,Current_timestamp ,:createUserId ,:sealFlag )
}

OCDOOBLI_OFFBNC_UPDATE_OFFENDER_BENEFICIARIES {
	UPDATE OFFENDER_BENEFICIARIES set BENEFICIARY_ID  = :beneficiaryId ,OFFENDER_DEDUCTION_ID  = :offenderDeductionId ,OFFENDER_ID  = :offenderId ,PERSON_ID  = :personId ,CORPORATE_ID  = :corporateId ,PRIORITY  = :priority ,
	AMOUNT  = :amount ,PERCENT  = :percent ,OVERRIDE_AMOUNT  = :overrideAmount ,RECEIVED_AMOUNT  = :receivedAmount ,
	MODIFY_USER_ID  = :modifyUserId ,MODIFY_DATETIME  = current_timestamp , UNKNOWN_BEN_ID  = :unknownBenId ,
	COMMENT_TEXT  = :commentText ,MONTHLY_AMOUNT  = :monthlyAmount ,RECURSIVE_AMOUNT  = :recursiveAmount ,TBD_FLAG  = :tbdFlag ,
	SEAL_FLAG  = :sealFlag  where BENEFICIARY_ID =:beneficiaryId 
}

OCDOOBLI_OFFBNC_DELETE_OFFENDER_BENEFICIARIES { 
	DELETE FROM OFFENDER_BENEFICIARIES  where  BENEFICIARY_ID = :beneficiaryId
}

OCDOOBLI_OFFDR_FIND_OFFENDER_DEDUCTION_RECEIPTS {
 	SELECT LIST_SEQ ,RECEIPT_PERCENTAGE ,RECEIPT_TXN_TYPE ,OFFENDER_DEDUCTION_ID ,FLAT_RATE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG,
	CASE WHEN RECEIPT_TXN_TYPE IN (SELECT RECEIPT_TXN_TYPE FROM CASELOAD_DEDUCTION_DETAILS where DEDUCTION_TYPE = (SELECT DEDUCTION_TYPE FROM OFFENDER_DEDUCTIONS  WHERE OFFENDER_DEDUCTION_ID = ODR.OFFENDER_DEDUCTION_ID)
	AND CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :userName)) THEN 'Y' ELSE 'N' END MAINTENANCE_FLAG
   	FROM OFFENDER_DEDUCTION_RECEIPTS ODR
	WHERE OFFENDER_DEDUCTION_ID = :OFFENDERDEDUCTIONID AND RECEIPT_TXN_TYPE IN (SELECT TXN_TYPE FROM TRANSACTION_TYPES WHERE CASELOAD_TYPE IN (SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID IN (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :userName))) ORDER BY RECEIPT_TXN_TYPE
}
OCDOOBLI_OFFDR_INSERT_OFFENDER_DEDUCTION_RECEIPTS {
	INSERT INTO OFFENDER_DEDUCTION_RECEIPTS(LIST_SEQ ,RECEIPT_PERCENTAGE ,RECEIPT_TXN_TYPE ,OFFENDER_DEDUCTION_ID ,FLAT_RATE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG )
	VALUES(:listSeq ,:receiptPercentage ,:receiptTxnType ,:offenderDeductionId ,:flatRate , current_timestamp ,:createUserId ,current_timestamp ,:modifyUserId ,:sealFlag )
}

OCDOOBLI_OFFDR_UPDATE_OFFENDER_DEDUCTION_RECEIPTS {
update OFFENDER_DEDUCTION_RECEIPTS set LIST_SEQ = :listSeq , RECEIPT_PERCENTAGE = :receiptPercentage , FLAT_RATE = :flatRate , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag where OFFENDER_DEDUCTION_ID = :offenderDeductionId and RECEIPT_TXN_TYPE = :receiptTxnType}

OCDOOBLI_OFFDR_DELETE_OFFENDER_DEDUCTION_RECEIPTS { 
	DELETE FROM OFFENDER_DEDUCTION_RECEIPTS where OFFENDER_DEDUCTION_ID  = :offenderDeductionId and RECEIPT_TXN_TYPE  = :receiptTxnType
}

OCDOOBLI_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG   FROM SYSTEM_PROFILES  /* where  */
}
OCDOOBLI_SYSPFL_INSERT_SYSTEM_PROFILES {
	INSERT INTO SYSTEM_PROFILES(PROFILE_TYPE ,PROFILE_CODE ,DESCRIPTION ,PROFILE_VALUE ,PROFILE_VALUE_2 ,MODIFY_USER_ID ,OLD_TABLE_NAME ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,SEAL_FLAG ) VALUES(:profileType ,:profileCode ,:description ,:profileValue ,:profileValue2 ,:modifyUserId ,:oldTableName ,:createDatetime ,:createUserId ,:modifyDatetime ,:sealFlag )
}

OCDOOBLI_SYSPFL_UPDATE_SYSTEM_PROFILES {
	UPDATE SYSTEM_PROFILES set PROFILE_TYPE  = :profileType ,PROFILE_CODE  = :profileCode ,DESCRIPTION  = :description ,PROFILE_VALUE  = :profileValue ,PROFILE_VALUE_2  = :profileValue2 ,MODIFY_USER_ID  = :modifyUserId ,OLD_TABLE_NAME  = :oldTableName ,CREATE_DATETIME  = :createDatetime ,CREATE_USER_ID  = :createUserId ,MODIFY_DATETIME  = :modifyDatetime ,SEAL_FLAG  = :sealFlag /* where */
}


OCDOOBLI_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_DEDUCTIONS O WHERE O.OFFENDER_ID = :ROOTOFFENDERID
}

OCDOOBLI_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_DEDUCTIONS O WHERE O.OFFENDER_ID = :ROOTOFFENDERID
}

OCDOOBLI_OFF_DED_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_BENEFICIARIES O WHERE O.OFFENDER_DEDUCTION_ID = :OFFENDERDEDUCTIONID
}

OCDOOBLI_OFF_DED_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_DEDUCTION_RECEIPTS O WHERE O.OFFENDER_DEDUCTION_ID = :OFFENDERDEDUCTIONID
}

OCDOOBLI_OFF_BNC_PREINSERT {
	SELECT NEXTVAL('BENEFICIARY_ID') FROM DUAL
}

OCDOOBLI_CGFKCHK_OFF_DED_OFF_DED_CSLD {
select distinct CSLD_DP.FIFO_FLAG , CSLD_DP.FO_AL_ALL_OFFENDER_FLAG , CSLD_DP.PAYEE_PERSON_ID , CSLD_DP.PAYEE_CORPORATE_ID , CSLD_DP.MAX_TOTAL_AMOUNT , CSLD_DP.MAX_RECURSIVE_AMOUNT , CSLD_DP.MAX_MONTHLY_AMOUNT , CSLD_DP.EFFECTIVE_DATE from CASELOAD_DEDUCTION_DETAILS CSLD_DD , CASELOAD_DEDUCTION_PROFILES CSLD_DP where ((CSLD_DD.CASELOAD_ID in ( select WORKING_CASELOAD_ID from STAFF_MEMBERS where USER_ID = :userName) ) or (CSLD_DD.CASELOAD_ID in ( select WORKING_CASELOAD_ID from STAFF_MEMBERS where USER_ID = :userName))) and CSLD_DD.DEDUCTION_TYPE = :DEDUCTIOTYPE and CSLD_DP.CASELOAD_ID = CSLD_DD.CASELOAD_ID and CSLD_DP.DEDUCTION_TYPE = CSLD_DD.DEDUCTION_TYPE
}


OCDOOBLI_CGFKCHK_OFF_DED_OFF_DED_REF_C {
	select REF_CODE.DESCRIPTION, REF_CODE.CODE from REFERENCE_CODES REF_CODE where domain = 'DED_STATUS' and (( ACTIVE_FLAG = 'Y' and EXPIRED_DATE is null))
}

OCDOOBLI_CGFKLKP_OFF_DED_OFF_DED_REF_C_ {
	SELECT REF_CODE.CODE FROM   REFERENCE_CODES REF_CODE WHERE  REF_CODE.DESCRIPTION = :DSPDESCRIPTION AND     DOMAIN = 'DED_STATUS' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL) OR :MODE = 'QUERY')
}

OCDOOBLI_CGFKCHK_OFF_BNC_OFF_BNC_CORP__ {
	SELECT CORP.CORPORATE_NAME ,CORP.SUSPENDED_FLAG FROM   CORPORATES CORP WHERE  CORP.CORPORATE_ID = :CORPORATEID
}

OCDOOBLI_CGFKCHK_OFF_BNC_OFF_BNC_PER_F_ {
	SELECT PER.LAST_NAME ,PER.FIRST_NAME ,PER.SUSPENDED_FLAG FROM   PERSONS PER WHERE  PER.PERSON_ID = :PERSONID
}

OCDOOBLI_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}

OCDOOBLI_CGFKCHK_OFF_DR_OFF_DR_TXN_TYP_ {
	SELECT TXN_TYPE.DESCRIPTION FROM   TRANSACTION_TYPES TXN_TYPE WHERE  TXN_TYPE.TXN_TYPE = :RECEIPTTXNTYPE
}

OCDOOBLI_CGFKCHK_OFF_DED1_OFF_DED_REF__ {
	SELECT REF_CODE1.DESCRIPTION FROM   REFERENCE_CODES REF_CODE1 WHERE  REF_CODE1.CODE = :ADJUSTMENTREASONCODE AND     DOMAIN = 'ADJ_RSN' AND REF_CODE1.CODE = 'WRT'
}

OCDOOBLI_CGFKCHK_OFF_DED_GRP_ID_ {
	SELECT GO.GROUP_ID FROM   GROUPED_OBLIGATIONS GO WHERE  (GO.GROUP_ID = :GROUPID AND    GO.DEDUCTION_TYPE = :DEDUCTIONTYPE) OR     :GROUPID IS NULL
}

OCDOOBLI_CGFKLKP_OFF_DED_CASE_NUMBER{
	SELECT COUNT(*) FROM   OFFENDER_CASES OC, OFFENDER_BOOKINGS OB WHERE  OC.CASE_INFO_NUMBER = :informationNumber 
	AND  OC.OFFENDER_BOOK_ID = OB.OFFENDER_BOOK_ID AND  OB.ROOT_OFFENDER_ID = :rootOffenderId
}
OCDOOBLI_GET_DESCRIPTION {
SELECT DEDUCTION_DESC FROM DEDUCTION_TYPES  WHERE DEDUCTION_TYPE=:deductionType
}
OCDOOBLI_GETDEDUCTIONCATEGORY {
SELECT DEDUCTION_CATEGORY FROM DEDUCTION_TYPES  WHERE DEDUCTION_TYPE=:deductionType
}
OCDOOBLI_GET_OBLFLAG {
SELECT DISTINCT COALESCE (UNIQUE_OBLIGATION_FLAG,'N') OBL_FLAG FROM OFFENDERS WHERE OFFENDER_ID = :offenderId 
}
OCDOOBLI_GET_FIRST_LAST_NAMES_SUSFLG {
 SELECT last_name, first_name, suspended_flag     FROM persons  WHERE person_id = :personId
}
OCDOOBLI_GET_CORPORATE_NAME_SUSFLG {
SELECT corporate_name, suspended_flag   FROM corporates   WHERE corporate_id = :corporateId
 }
 OCDOOBLI_CALCBENPERCENT {
 SELECT SUM (COALESCE (amount, 0)) AMOUNT   FROM offender_beneficiaries  WHERE offender_deduction_id = :offenderDeductionId   AND priority = :priority
 }
 OCDOOBLI_GETRECIEPTTXNTYPEDESC {
  SELECT TXN_TYPE.DESCRIPTION
      FROM   TRANSACTION_TYPES TXN_TYPE WHERE  TXN_TYPE.TXN_TYPE = :receiptTxnType
 }
 OCDOOBLI_GETTCOUNT {
 SELECT COUNT (*) TCOUNT            FROM offender_deductions   WHERE offender_id = :offenderId   AND caseload_id = :caseloadId
               AND deduction_type IN (SELECT deduction_type
                                        FROM deduction_types
                                       WHERE caseload_id = :caseloadId
                                         AND deduction_category IN ('FXOB', 'CROB')) AND deduction_type = :deductionType   AND deduction_priority = :deductionPriority
 }
 
  OCDOOBLI_CHECK_CR_TYPE {
  SELECT DEDUCTION_TYPE
              FROM DEDUCTION_TYPES
             WHERE DEDUCTION_TYPE = :deductionType
               AND DEDUCTION_CATEGORY = 'CROB'
               }
  OCDOOBLI_SET_JS_CONDITION {
        SELECT COUNT (*)
           FROM offender_cases oc, offender_community_conditions occ
          WHERE occ.comm_condition_type = 'J & S'
            AND occ.comm_condition_code = 'REST'
            AND occ.offender_book_id = oc.offender_book_id
            AND oc.case_id = :caseId  
            }
  OCDOOBLI_COMPARE_DATE_TIME {  
  SELECT oms_date_time.compare_date_time(TO_DATE(:effectiveDate,'DD/MM/YYYY'),NULL,TO_DATE(:dspDate,'DD/MM/YYYY'),NULL) FROM DUAL
    }
    
    OCDOOBLI_OMS_UTILS_DISPLAY_USER_MESSAGE{
    SELECT OMS_OWNER.OMS_UTILS.DISPLAY_USER_MESSAGE(44, 'OMS', 'Effective', 'Obligation Code Effective') FROM dual
    }
    
OCDOOBLI_PROFILE_VALUE {
    SELECT profile_value        FROM system_profiles     WHERE profile_type = 'CLIENT'    AND profile_code = 'PAYMENT_PLAN'
   }
OCDOOBLI_GETCHECKONE {    
    SELECT '1' val   FROM offender_payment_plans  WHERE offender_id = :offenderId      AND information_number = :informationNumber    AND group_id = :groupId AND payment_closed_flag = 'N'
    }
    
OCDOOBLI_VS_DAMTCURVAL {
select coalesce (SUM (case txn_post_usage when 'CR' then txn_entry_amount when 'DR' then txn_entry_amount *-1 end), 0) VAL from beneficiary_transactions where offender_deduction_id = :deductionId and date_trunc ('Month',txn_entry_date) = date_trunc('Month',CURRENT_TIMESTAMP)
}
OCDOOBLI_VS_DAMTCUR {
 SELECT max_recursive_amount, effective_date, deduction_amount,OFFENDER_DEDUCTION_ID FROM offender_deductions          WHERE offender_deduction_id = :deductionId   
}
OCDOOBLI_GETDEDUCTIONAMNT {
 SELECT DEDUCTION_AMOUNT FROM OFFENDER_MON_DEDUCTIONS WHERE OFFENDER_DEDUCTION_ID = :deductionId AND MONTHLY_DEDUCTION_DATE = DATE_TRUNC('MONTH',CURRENT_DATE)
 }
 OCDOOBLI_GETLASTFIRSTNAMES {
 SELECT last_name, first_name, suspended_flag FROM persons  WHERE person_id = :personId
 }
 OCDOOBLI_LASTFIRSTNAME {
 select SUBSTR (:lastName || ', ' || :firstName, 1, 65) from dual
 }
 OCDOOBLI_PER_EXIST {
 SELECT COUNT (0)  val FROM offender_beneficiaries  WHERE offender_deduction_id = :deductionId AND person_id = :personId
  }
  OCDOOBLI_UPDATEBENFICIARYTRANSACTIONS {
  UPDATE beneficiary_transactions    SET person_id = :personId,  corporate_id = :corporateId,  unknown_ben_id = NULL,modify_user_id = :modifyUserId,modify_datetime = current_timestamp
          WHERE offender_deduction_id = :offenderDeductionId AND unknown_ben_id = :unknownBenId  
  }
  OCDOOBLI_GETCORPEXISTS {
  SELECT COUNT (0) val FROM offender_beneficiaries    WHERE offender_deduction_id = :deductionId  AND corporate_id = :corporateId  
  }
  
  OCDOOBLI_GET_OFFENDER_DEDUCTION_ID{
  SELECT offender_deduction_id     FROM offender_deduction_receipts  WHERE offender_deduction_id = :offenderDeductionId
  }
  OCDOOBLI_GETRECIEPTTXNTYPE {
  SELECT receipt_txn_type   FROM offender_deduction_receipts    WHERE offender_deduction_id = :offenderDeductionId AND receipt_txn_type = :receipt_type  
  }
  OCDOOBLI_GETRECIEPTPERCENTAGE {
  SELECT percentage, flat_rate     FROM caseload_deduction_details  WHERE caseload_id = :caseloadId   AND deduction_type = :deductionType    AND receipt_txn_type = :receiptTxnType
      }
  OCDOOBLI_GETMONTHS {
      select DATE_PART('month', AGE( current_timestamp , :vEffDate))+1 AS months from dual;
      }
   OCDOOBLI_GETNXTVAL {
    SELECT nextval('DEDUCTION_ID') FROM dual;
   }
   OCDOOBLI_GETUNKNOWNIDNEXTVAL {
   SELECT NEXTVAL('UNKNOWN_BEN_ID')  FROM dual
   }
   OCDOOBLI_GETBENAMNT {
   SELECT BENEFICIARY_ID, COALESCE (AMOUNT, 0) AMT   FROM OFFENDER_BENEFICIARIES  WHERE OFFENDER_DEDUCTION_ID = :offenderDeductionId AND priority = :priority   
   }
   OCDOOBLI_UPDATEOFFENDERBENEFICIARIES {
   UPDATE OFFENDER_BENEFICIARIES   SET PERCENT = (:amount / :totalAmount) * 100, MODIFY_USER_ID = :modifyUserId, modify_datetime = current_timestamp  WHERE beneficiary_id = :beneficiaryId
   }
   OCDOOBLI_ELSEUPDATEOFFENDERBENEFICIARIES {
   UPDATE offender_beneficiaries               SET percent = NULL, modify_user_id = :modifyUserId, modify_datetime = current_timestamp      WHERE beneficiary_id = :beneficiaryId
   }
  OCDOOBLI_GETPERCETANAGE {   
  	SELECT SUM (COALESCE (percent, 0)) TOT_PERCENT    FROM offender_beneficiaries        WHERE offender_deduction_id = :offenderDeductionId      AND priority = :priority
  }
   OCDOOBLI_TOT_PERCETANAGE {
   UPDATE OFFENDER_BENEFICIARIES OB SET PERCENT = percent + (100 - :totPercent), modify_user_id = :modifyUserId, modify_datetime = current_timestamp where percent = ( select MIN (coalesce (percent, 0)) from offender_beneficiaries ob2 where ob2.offender_deduction_id = ob.offender_deduction_id and ob2.priority = ob.priority) and priority = :priority and offender_deduction_id = :offenderDeductionId
   }
   OCDOOBLI_UPDATEOFFENDERPAYMENTS {
    UPDATE offender_payment_plans
                        SET original_owing_amount = original_owing_amount + :maxTotalAmount,modify_user_id = :modifyUserId, modify_datetime = current_timestamp
                      WHERE offender_id = :offenderId
                        AND information_number = :vinfo
                        AND group_id = :groupId
                        AND payment_plan_seq = 1
   }
   OCDOOBLI_GETCURCHILDDEDUCTIONS {
   SELECT DEDUCTION_TYPE, PERCENTAGE_OF_PARENT 
         FROM DEDUCTION_TYPES DT
        WHERE PARENT_DEDUCTION_TYPE = :deductionType
          AND ACTIVE_FLAG = 'Y'
   }
   OCDOOBLI_GETCURCHILDCSLDDED {
   SELECT FIFO_FLAG
         FROM CASELOAD_DEDUCTION_PROFILES
        WHERE DEDUCTION_TYPE = :deductionType
          AND CASELOAD_ID = :caseloadId
          AND ACTIVE_FLAG = 'Y'
   
   }
   OCDOOBLI_CGNBT_MAXMONTHLY_INSERT_OFFENDER_DEDUCTIONS {
   INSERT INTO OFFENDER_DEDUCTIONS ( offender_deduction_id, offender_id, deduction_type, information_number, caseload_id, deduction_priority, deduction_status, create_user_id, create_datetime, effective_date, modify_date, modify_user_id, max_total_amount, max_monthly_amount, max_recursive_amount, deduction_amount, fifo_flag, group_id, case_id, parent_deduction_id ) values ( :offenderDeductionId, :offenderId, :deductionType, :infomationNumber, :caseloadId, :odp, 'A', :createUserId, CURRENT_TIMESTAMP, date_trunc('day', current_date), CURRENT_TIMESTAMP, :current_user, :maxTotalAmnt * (coalesce (:percentage_of_parent, 0) / 100), :maxMonthlyAmnt * (coalesce (:percentage_of_parent, 0) / 100), :maxRecursiveAmnt * (coalesce (:percentage_of_parent, 0) / 100), 0.0, :fifoFlag, :groupId, :caseId, :offenderDeductionId ) 
   }
   
   OCDOOBLI_CGNBT_MAXMONTHLY_INSERT_OFFENDER_BENEFICIARIES {
   INSERT INTO OFFENDER_BENEFICIARIES (beneficiary_id, offender_deduction_id, offender_id, person_id, corporate_id, unknown_ben_id, priority, amount, percent, override_amount, received_amount, comment_text, create_user_id, create_datetime, modify_datetime ) select nextval('beneficiary_id'), :offenderDeductionId, :offenderId, cdb.person_id, cdb.corporate_id, case when cdb.person_id is null then case when cdb.corporate_id is null then nextval('unknown_ben_id') else null end else null end, cdb.priority, (coalesce (:maxTotalAmnt, 0) + coalesce (:maxMonthlyAmnt, 0) + coalesce (:maxRecursiveAmnt, 0)) * (coalesce (:percentageOfParent, 0) / 100), cdb.percent, null, null, null, :createUserId, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP from caseload_ded_beneficiaries cdb where cdb.caseload_id = :caseloadId and cdb.deduction_type = :deductionType limit 1 
   }
                     
 OCDOOBLI_SQLNOTFOUND_INSERT_OFFENDER_BENEFICIARIES {
INSERT INTO offender_beneficiaries
                              (
                                             beneficiary_id,
                                             offender_deduction_id,
                                             offender_id,
                                             person_id,
                                             corporate_id,
                                             unknown_ben_id,
                                             priority,
                                             amount,
                                             percent,
                                             override_amount,
                                             received_amount,
                                             modify_user_id,
                                             comment_text,
                                             modify_datetime
                                          )
                       VALUES (
                          NEXTVAL('beneficiary_id'),
                          :lv_ded_id,
                          :offenderId,
                          NULL,
                          NULL,
                         NEXTVAL('unknown_ben_id'),
                          1,
                          (COALESCE (:maxTotalAmnt, 0) + COALESCE (:maxMonthlyAmnt, 0) + COALESCE (:maxRecursiveAmnt, 0)) *
                             (COALESCE (:percentageOfParent, 0) / 100),
                          100,
                          NULL,
                          NULL,
                          :createUserId,
                          NULL,
                          current_timestamp
                       )
             }
 OCDOOBLI_INSERTINTOOFFENDERDEDUCTIONSRECIEPTS {
 insert into offender_deduction_receipts (offender_deduction_id, receipt_txn_type, receipt_percentage, flat_rate, create_user_id, create_datetime ) select :offenderDeductionId, cdd.receipt_txn_type, cdd.percentage, cdd.flat_rate, :createUserId, current_timestamp from caseload_deduction_details cdd where cdd.caseload_id = :caseloadId and cdd.deduction_type = :deductionType
 }
 OCDOOBLI_GETPRIORITY_TOTAMNT {
 SELECT PRIORITY, SUM (COALESCE (AMOUNT, 0)) TOT_AMT FROM OFFENDER_BENEFICIARIES WHERE OFFENDER_DEDUCTION_ID = :offenderDeductionId GROUP BY PRIORITY ORDER BY 1
 }
 OCDOOBLI_V_DUP_CHECKBENFICIARYINSERTED {
 SELECT COUNT (*)   FROM offender_beneficiaries
       WHERE offender_deduction_id = :offenderDeductionId
 
 }
 OCDOOBLI_INSERTMULTIPLEBENEFICIARIES {
insert into OFFENDER_BENEFICIARIES ( beneficiary_id, offender_deduction_id, offender_id, unknown_ben_id, priority, amount, percent, create_user_id, create_datetime, modify_datetime, person_id, corporate_id ) values (nextval('beneficiary_id'), :offenderDeductionId, :offenderId, :unknownBenId, 1, :dedAmt, 100, :createUserId, current_timestamp, current_timestamp, :personId, :corporateId)
 }
OCDOOBLI_GETVCLIENTWASH {
  SELECT COUNT(0)
              FROM system_profiles
     	       WHERE profile_type = 'CLIENT'
       	       AND profile_code = 'CLIENT'
       	       AND profile_value = 'WASHINGTON'
  
  }
 OCDOOBLI_MULTIBEAN {
 SELECT person_id, corporate_id, priority, amount, percent  FROM caseload_ded_beneficiaries
             WHERE caseload_id = :caseloadId AND deduction_type = :deductionType ORDER BY priority
             }
             
  OCDOOBLI_GET_LV_COUNT {        
   SELECT COUNT (*)  FROM caseload_ded_beneficiaries WHERE caseload_id = :caseloadId  AND deduction_type = :deductionType
               }
 	 OCDOOBLI_GETLV_FLATRATE {
      select COALESCE (SUM (amount), 0) FLAT_RATE from caseload_ded_beneficiaries where caseload_id = :caseloadId and deduction_type = :deductionType and percent = 100      
 	 }
     OCDOOBLI_GET_LV_NON_FLATRATE {  
      select coalesce (SUM (amount), 0) NON_FLATRATE from caseload_ded_beneficiaries where caseload_id = :caseloadId and deduction_type = :deductionType and percent <> 100
               }
      OCDOOBLI_INSERTINTO_OFFENDERBENFICIARIES {
 insert
	into
	OFFENDER_BENEFICIARIES (beneficiary_id,
	offender_deduction_id,
	offender_id,
	person_id,
	corporate_id,
	unknown_ben_id,
	priority,
	amount,
	percent,
	create_user_id,
	create_datetime,
	modify_user_id,
	modify_datetime ) (
	select
		nextval('beneficiary_id'),
		:offenderDeductionId,
		:offenderId,
		:personId,
		:corporateId,
		case
			when coalesce(:personId::bigint,2012)=2012  then
			case
				when  coalesce(:corporateId::bigint,2012)=2012 then nextval('unknown_ben_id')
				else null
			end
			else null
		end,
		:priority,
		:vRectifiedAmnt,
		:percent,
		:createUserId,
		current_timestamp,
		:createUserId,
		current_timestamp
	from
		dual)
}
    OCDOOBLI_CHK_RECEIPT_TYPE_INSERTED {                       
  SELECT COUNT (*)   FROM offender_deduction_receipts  WHERE offender_deduction_id = :offenderDeductionId
  }
OCDOOBLI_GET_RECEIPT_TXNTYPES { 	
SELECT receipt_txn_type, percentage, flat_rate
          FROM caseload_deduction_details
          WHERE caseload_id = :caseloadId
            AND deduction_type = :deductionType 
            }

 OCDOOBLI_GETDEDUCTION_RECIEPTS { 
SELECT RECEIPT_TXN_TYPE, PERCENTAGE, FLAT_RATE
           FROM CASELOAD_DEDUCTION_DETAILS  WHERE CASELOAD_ID = :caseloadId AND DEDUCTION_TYPE = :dedcutionType 
  }
 OCDOOBLI_GET_EXISTFLG {  
     SELECT 'Y'  EXIST_FLAG             
              FROM offender_deduction_receipts
             WHERE offender_deduction_id = :offenderDeductionId
               AND receipt_txn_type = :receiptTxnType 
               }
  OCDOOBLI_INSERTINTOOFFENDER_DEDUCTIONRECEIPTS {
 INSERT INTO OFFENDER_DEDUCTION_RECEIPTS (offender_deduction_id, receipt_txn_type, receipt_percentage, flat_rate, create_user_id, create_datetime) values (:offenderDeductionId, :receiptTxnType, :percentage, :flatRate, :createUserId, current_timestamp)     }
    
    OCDOOBLI_UPDATEOFFENDERS {
    UPDATE offenders SET unique_obligation_flag = :oblFlg,modify_user_id = :modifyUserId,modify_date_time= current_timestamp
          WHERE root_offender_id = :rootOffenderId
    }
    OCDOOBLI_GETDESCRIPTIONFORRECIPTTYPE {
    SELECT TXN_TYPE.DESCRIPTION DESCRIPTION FROM TRANSACTION_TYPES TXN_TYPE WHERE TXN_USAGE = 'R' AND ACTIVE_FLAG = 'Y' AND TXN_TYPE= :code
    }
    OCDOOBLI_CUR_CHILD_OFF_DED {
    SELECT offender_deduction_id, max_total_amount, max_monthly_amount, max_recursive_amount, deduction_amount, percentage_of_parent
         FROM offender_deductions a, deduction_types b
        WHERE parent_deduction_id = :offenderDeductionId
          AND a.deduction_type = b.deduction_type
          }
    OCDOOBLI_UPDATEOFFENDERDEDUCTIONS {      
     update offender_deductions set deduction_priority = :deductionPriority, information_number = :informationNumber, case_id = :caseId, group_id = :groupId, deduction_status = :deductionStatus, modify_date = current_date, modify_user_id = :modifyUserId, modify_datetime = current_timestamp, max_total_amount = greatest ((:maxTotalAmount * (coalesce (:percentageOfParent, 0) / 100)), :deductionAmount), max_monthly_amount = greatest ((:maxMonthlyAmount * (coalesce (:percentageOfParent, 0) / 100)), :deductionAmount), max_recursive_amount = greatest ((:maxRecursiveAmount * (coalesce (:percentageOfParent, 0) / 100)), :deductionAmount) where offender_deduction_id = :offenderDeductionId
             }
  OCDOOBLI_UPDATEOFFENDEROFFENDERBENEFICIARIES {  
 update offender_beneficiaries set modify_user_id = :modifyUserId, modify_datetime = current_timestamp, amount = greatest ( ( (coalesce (:maxTotalAmount, 0) + coalesce (:maxMonthlyAmount, 0) + coalesce (:maxRecursiveAmount, 0)) * (coalesce (:percentageOfParent, 0) / 100) ), coalesce (received_amount, 0) ) where offender_deduction_id = :offenderDeductionId
  }
             
 OCDOOBLI_CGFKCHKOFFDEDOFFDEDT {           
  SELECT DED_TYPE.DEDUCTION_DESC, DED_TYPE.FROM_BALANCE_TYPE, DED_TYPE.DEDUCTION_CATEGORY, DED_TYPE.INCREMENT_PAYABLES_FLAG, DED_TYPE.PARENT_DEDUCTION_TYPE FROM DEDUCTION_TYPES DED_TYPE WHERE DED_TYPE.DEDUCTION_TYPE = :deductionType AND (( ACTIVE_FLAG = 'Y' AND EXPIRY_DATE IS null) ) AND ((DED_TYPE.DEDUCTION_CATEGORY IN ('FXOB', 'CROB')) OR ( DED_TYPE.DEDUCTION_CATEGORY IN ('FXOB', 'CROB', 'TAX') AND EXISTS ( SELECT '1' FROM SYSTEM_PROFILES SP WHERE SP.PROFILE_TYPE = 'CLIENT' AND SP.PROFILE_CODE = 'TAX_OBLIGN' AND SP.PROFILE_VALUE = 'Y'))) AND ((DED_TYPE.DEDUCTION_TYPE IN ( SELECT DISTINCT CDD.DEDUCTION_TYPE FROM CASELOAD_DEDUCTION_DETAILS CDD WHERE CDD.CASELOAD_ID = :caseloadId AND CDD.DEDUCTION_TYPE IN ( SELECT CDP.DEDUCTION_TYPE FROM CASELOAD_DEDUCTION_PROFILES CDP WHERE CDP.CASELOAD_ID = :caseloadId AND (( CDP.ACTIVE_FLAG = 'Y' AND CDP.EXPIRY_DATE IS NULL)) AND DATE_TRUNC('DAY',CDP.EFFECTIVE_DATE) <= DATE_TRUNC ('DAY',CURRENT_DATE)))) )
 }
             
             
  OCDOOBLI_GETPREVIOUSDEDTXN {
  SELECT 'Y' TXN_EXIST_FLG FROM offender_transactions
          WHERE offender_id = :offenderId
            AND deduction_type = :deductionType
            AND info_number = :informationNumber  
  }
  OCDOOBLI_CHECKPREVBENREC {
  SELECT 'Y' V_PREV_BNCFLG
           FROM offender_beneficiaries
          WHERE offender_deduction_id = :offenderDeductionId
            AND received_amount IS NOT NULL
            AND received_amount > 0  
  }
  OCDOOBLI_UPDATEOFFENDERPAYMENTPLANS {
  UPDATE offender_payment_plans
                  SET original_owing_amount = :maxTotalAmount, modify_user_id = :modifyUserId, modify_datetime = current_timestamp
              	  WHERE offender_id = :offnederId
                  AND information_number = :informationNumber
                  AND group_id = :groupId
                  AND payment_plan_seq = 1  
  }
  
  OCDOOBLI_DELETEOFFENDEROBLIGATIONHTY {
  DELETE   FROM offender_obligation_hty   WHERE offender_deduction_id = :offenderDeductionId
  }
  OCDOOBLI_GETMONTHTOTALAMNTFOROBLIGATION {
    SELECT SUM (COALESCE (AMOUNT, 0)) MONTHLY_AMOUNT   FROM offender_beneficiaries  WHERE offender_deduction_id = :offenderDeductionId   AND priority = :priority
  }
  OCDOOBLI_GETMAXMONTHLYAMOUNTFROMDEDUCTIONS {
  SELECT MAX_MONTHLY_AMOUNT FROM OFFENDER_DEDUCTIONS WHERE OFFENDER_DEDUCTION_ID = :offenderDeductionId
  }
  OCDOOBLI_CHECKINFORMATION_AND_DEDUCTIONTYPE {
  SELECT '1' CHECK_ONE   FROM OFFENDER_DEDUCTIONS  WHERE OFFENDER_ID = :offId AND DEDUCTION_TYPE = :dedType AND INFORMATION_NUMBER = :info
  }
  OCDOOBLI_GETMAXTOTALAMNTFROMMAINFIXEDOBLIGATION {
  SELECT MAX_TOTAL_AMOUNT FROM CASELOAD_DEDUCTION_PROFILES WHERE deduction_type=:dedType and caseload_id=:caseloadId
  }
  OCDOOBLI_GETMAXMONTHLYFROMMAINFIXEDOBLIGATION {
  SELECT MAX_MONTHLY_AMOUNT FROM CASELOAD_DEDUCTION_PROFILES WHERE deduction_type=:dedType and caseload_id=:caseloadId
  }
  OCDOOBLI_GETPERCENTFROMMONTHLY {
    select
	TO_NUMBER(TO_CHAR(100 * (coalesce(:monthlyAmount , 0) / SUM(coalesce(MONTHLY_AMOUNT, 0)))),'L9G999g999.999999'::text) PERCENTAGE
from
	offender_beneficiaries
where
	OFFENDER_DEDUCTION_ID =:offenderDeductionId
	and PRIORITY = :priority
  }
  OCDOOBLI_GETPERCENTFROMMAXTOT {
  select TO_CHAR(100 * (coalesce( CAST (:maxtotAmount AS DOUBLE PRECISION), 0) / SUM(coalesce(AMOUNT, 0)))) PERCENTAGE from offender_beneficiaries where OFFENDER_DEDUCTION_ID =:offenderDeductionId and PRIORITY = :priority
  }
  OCDOOBLI_PROFILE_VALUE_TAX_OBLIGN {  
  SELECT profile_value FROM system_profiles     WHERE profile_type = 'CLIENT'    AND profile_code = 'TAX_OBLIGN'
  }
  OCDOOBLI_GET_DISABLE_DBUTTON {  
  SELECT MENU_ITEM FROM MENU_SECURITIES WHERE MENU_ID IN(SELECT PARENT_MENU_ID FROM MENU_SECURITIES WHERE MENU_ID = :PARENTMENUID)
  }
  OCDOOBLI_OFFENDER_DEDUCTIONS_DATA {
  select * from OFFENDER_DEDUCTIONS where  offender_deduction_id = :offenderDeductionId 
  }
  OCDOOBLI_OFFENDER_BENEFICIARIES_DATA{
  SELECT * FROM OFFENDER_BENEFICIARIES WHERE BENEFICIARY_ID = :beneficiaryId
  }
  OCDOOBLI_OFFENDER_BENEFICIARIES_DATA_DEDUCTIONID{
  SELECT * FROM OFFENDER_BENEFICIARIES WHERE offender_deduction_id = :offenderDeductionId
  }