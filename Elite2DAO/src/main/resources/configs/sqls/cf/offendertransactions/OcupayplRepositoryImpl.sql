
OCUPAYPL_FIND_CGFKPAYPLNINFORMATIONNUMBE {
 	select 
	distinct OFF_DED.INFORMATION_NUMBER description ,
	to_char(off_ded.group_id) CASELOADID ,
	OFF_DED.INFORMATION_NUMBER code
from
	OFFENDER_DEDUCTIONS OFF_DED
where
	OFFENDER_ID = :OFFENDER_ID
	and CASELOAD_ID = :CASELOADID
	and OFF_DED.DEDUCTION_TYPE in (
	select
		DT.DEDUCTION_TYPE
	from
		DEDUCTION_TYPES DT
	where
		DT.DEDUCTION_CATEGORY = 'FXOB' )
	and DEDUCTION_STATUS = 'A'
}
OCUPAYPL_GETTING_GROUP_ID {
SELECT DISTINCT GROUP_ID
FROM
    offender_deductions off_ded
WHERE
    information_number = :GRPID
    AND offender_id = :OFFENDER_ID
    AND caseload_id = :CASELOADID 
    AND off_ded.deduction_type IN (
        SELECT
            dt.deduction_type
        FROM
            deduction_types dt
        WHERE
            dt.deduction_category = 'FXOB'
    )
    AND deduction_status = 'A'
}
OCUPAYPL_FIND_CGFKPAYPLNDSPDESCRIPTION4 {
 	
SELECT
    ref_code.description   dsp_description4,
    ref_code.code          weekly
FROM
    reference_codes ref_code
WHERE
    domain = 'PS_WEEKDAYS'
    AND ( ( active_flag = 'Y'
            AND expired_date IS NULL ) ) ORDER by list_seq
}

OCUPAYPL_FIND_CGFKPAYPLNDSPDESCRIPTION3 {
 	
SELECT
    ref_code1.description   dsp_description3,
    ref_code1.code          bi_weekly
FROM
    reference_codes ref_code1
WHERE
    domain = 'PS_WEEKDAYS'
    AND ( ( active_flag = 'Y'
            AND expired_date IS NULL ) ) ORDER by list_seq
}

OCUPAYPL_FIND_CGFKPAYPLNDSPDESCRIPTION {
 	SELECT
    ref_code2.description   dsp_description,
    ref_code2.code          frequency
FROM
    reference_codes ref_code2
WHERE
    domain = 'CF_FREQ'
    AND   ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ) )
    AND REF_CODE2.CODE = 'MONTHLY'
 ORDER by list_seq
}

OCUPAYPL_PAYPLN_FIND_OFFENDER_PAYMENT_PLANS {
 SELECT PAYMENT_PLAN_ID,PAYMENT_PLAN_SEQ,OFFENDER_ID,START_DATE,END_DATE,FREQUENCY,MONTHLY,WEEKLY, TWICE_MONTHLY_1, TWICE_MONTHLY_2,
 AMOUNT, MODIFY_USER_ID, MODIFY_DATETIME, INFORMATION_NUMBER, OFFENDER_DEDUCTION_ID, PAYMENT_COMPLETION_DATE, GROUP_ID, LENIENCY_FLAG,
 PAYMENT_CLOSED_FLAG,   PAYMENT_CLOSED_FLAG  CLOSED_FLAG, PAYMENT_CLOSED_DATE, REGENERATION_FLAG, REGENERATION_DATE, ORIGINAL_OWING_AMOUNT, CASE_ID, CREATE_DATETIME,
 CREATE_USER_ID, SEAL_FLAG FROM offender_payment_plans
 

}

OCUPAYPL_PAY_PLN_RECUR_EXISTS {
SELECT DISTINCT 'Y'   FROM offender_payment_plans WHERE INFORMATION_NUMBER = :informationNumber AND GROUP_ID = :groupId  AND OFFENDER_ID = :offenderId
       AND PAYMENT_CLOSED_FLAG = 'N'
}
OCUPAYPL_PAY_PLN_MAX_PLAN_ID_SEQ{
SELECT MAX(payment_plan_id) payment_plan_id , MAX(payment_plan_seq) + 1 payment_plan_seq FROM offender_payment_plans WHERE INFORMATION_NUMBER =:INFORMATION_NUMBER AND GROUP_ID = :GROUP_ID
                 AND OFFENDER_ID = :OFFENDER_ID

}

OCUPAYPL_PAY_PLN_GET_PAYMENT_AMOUNT {
SELECT coalesce(SUM(payment_amount), 0)
        FROM v_offender_payment_schedules
       WHERE (payment_plan_id, payment_plan_seq) IN (SELECT payment_plan_id, payment_plan_seq
                                                       FROM offender_payment_plans
                                                      WHERE INFORMATION_NUMBER = :INFORMATION_NUMBER
                                                        AND GROUP_ID = :GROUP_ID
                                                        AND OFFENDER_ID = :OFFENDER_ID)
         AND payment_closed_flag <> 'Y'
}

OCUPAYPL_PAY_PLN_GET_PAID_AMOUNT {

SELECT coalesce(SUM(paid_amount), 0)
        FROM v_offender_payment_schedules
       WHERE (payment_plan_id, payment_plan_seq) IN (SELECT payment_plan_id, payment_plan_seq
                                                       FROM offender_payment_plans
                                                      WHERE INFORMATION_NUMBER = :INFORMATION_NUMBER
                                                        AND GROUP_ID = :GROUP_ID
                                                        AND OFFENDER_ID = :OFFENDER_ID)
         AND paid_amount > 0
         AND payment_closed_flag = 'Y'

}
OCUPAYPL_PAY_PLN_GET_OWING_AMOUNT {

SELECT original_owing_amount

           FROM offender_payment_plans opp
          WHERE opp.OFFENDER_ID = :OFFENDER_ID
            AND opp.INFORMATION_NUMBER = :INFORMATION_NUMBER
            AND opp.GROUP_ID = :GROUP_ID
            AND opp.payment_plan_seq = (SELECT coalesce(MIN(payment_plan_seq), 1)
                                          FROM offender_payment_plans opp2
                                         WHERE opp2.OFFENDER_ID = :OFFENDER_ID
                                           AND opp2.INFORMATION_NUMBER = :INFORMATION_NUMBER
                                           AND opp2.GROUP_ID = :GROUP_ID);

}
OCUPAYPL_PAY_PLN_TOT_DED_AMT {

SELECT coalesce(SUM(od.max_total_amount), 0)  MAX_TOTAL_AMOUNT,  coalesce(SUM(od.deduction_amount), 0) DEDUCTION_AMOUNT
        FROM offender_deductions od
       WHERE od.OFFENDER_ID = :OFFENDER_ID
         AND od.INFORMATION_NUMBER = CASE WHEN :INFORMATION_NUMBER='-1' THEN  od.information_number  ELSE :INFORMATION_NUMBER END
         AND od.GROUP_ID = CASE WHEN :GROUP_ID=-1 THEN  od.group_id  ELSE :GROUP_ID END
         AND od.effective_date <= date_trunc('day', LOCALTIMESTAMP)
         AND  od.DEDUCTION_STATUS = 'A'
         AND EXISTS (SELECT 1
                                FROM deduction_types dt
                      WHERE od.deduction_type = dt.deduction_type
                        AND dt.deduction_category IN ('FXOB', 'CROB')
                        AND dt.caseload_code IN ('BOTH', 'COMM'))
         AND EXISTS (SELECT 1
                       FROM caseloads c
                      WHERE od.caseload_id = c.caseload_id)

}

OCUPAYPL_PAY_PLN_GET_TOT_ARREARS {

SELECT coalesce(SUM(payment_amount - paid_amount), 0)
          FROM offender_payment_schedules ops
         WHERE ops.payment_date <= date_trunc('day', LOCALTIMESTAMP)
           AND (payment_plan_id, payment_plan_seq) IN (SELECT payment_plan_id, payment_plan_seq
                                                        FROM offender_payment_plans opp
                                                       WHERE opp.OFFENDER_ID =:OFFENDER_ID
                                                         AND opp.payment_closed_flag <> 'Y'
                                                         AND opp.GROUP_ID = :GROUP_ID
                                                         AND opp.INFORMATION_NUMBER = :INFORMATION_NUMBER)

}
OCUPAYPL_PAY_PLN_JS_STATUS {
 SELECT 'Y'
   	 FROM OFFENDER_DEDUCTIONS
   	WHERE OFFENDER_ID = :OFFENDER_ID
   		AND INFORMATION_NUMBER = :INFORMATION_NUMBER
   		AND GROUP_ID = :GROUP_ID
   		AND coalesce(JS_STATUS,'N') = 'Y'
   		limit 1

}
OCUPAYPL_PAY_PLN_ARREARS {

SELECT coalesce(SUM(payment_amount - paid_amount), 0) TOTAL_ARREARS
          FROM offender_payment_schedules ops
        WHERE ops.payment_date <= date_trunc('day', LOCALTIMESTAMP)
        AND EXISTS (SELECT '1'
                FROM offender_payment_plans opp
               WHERE opp.OFFENDER_ID = :OFFENDER_ID
                 AND opp.payment_closed_flag <> 'Y'
                 AND opp.GROUP_ID = :GROUP_ID
                 AND opp.INFORMATION_NUMBER = :INFORMATION_NUMBER
                 AND opp.payment_plan_id = ops.payment_plan_id
                 AND opp.payment_plan_seq = ops.payment_plan_seq)

}

OCUPAYPL_PAY_SCH_MOST_CURRENT {
 SELECT COUNT(*)+1
      FROM V_OFFENDER_PAYMENT_SCHEDULES OPS
     WHERE OPS.PAYMENT_DATE < date_trunc('day', LOCALTIMESTAMP)
     AND OPS.PAYMENT_PLAN_ID = :PAYMENT_PLAN_ID;    

}

OCUPAYPL_PAY_PLN_CHECK_EVER_PAID {
SELECT coalesce(SUM(PAID_AMOUNT),0)
FROM OFFENDER_PAYMENT_SCHEDULES
WHERE PAYMENT_PLAN_ID = :PAYMENT_PLAN_ID
AND   PAYMENT_PLAN_SEQ = :PAYMENT_PLAN_SEQ

}

OCUPAYPL_PAY_PLN_MODIFY_DEL_SCHEDULE {

DELETE
        FROM offender_payment_schedules ops
       WHERE ops.PAYMENT_PLAN_ID = :PAYMENT_PLAN_ID
         AND ops.PAYMENT_PLAN_SEQ = :PAYMENT_PLAN_SEQ
         AND ops.paid_amount = 0
         AND ops.payment_date >= date_trunc('day', LOCALTIMESTAMP)
         AND ops.payment_date > (SELECT MAX (payment_date)
                                   FROM payment_plan_transactions ppt
                                  WHERE ppt.payment_plan_id = ops.payment_plan_id
                                    AND ppt.payment_plan_seq = ops.payment_plan_seq)

}developer need to verify




PAY_PLN_DEL{
SELECT COUNT(*)
           FROM DUAL
          WHERE EXISTS ( SELECT 'Y'
                          FROM V_OFFENDER_PAYMENT_SCHEDULES
                         WHERE PAYMENT_PLAN_ID = :PAYMENT_PLAN_ID )
}

OCUPAYPL_PAY_PLN_DELTE_SCHEDULE{
DELETE FROM offender_payment_schedules
        WHERE PAYMENT_PLAN_ID = :PAYMENT_PLAN_ID
         AND PAYMENT_PLAN_SEQ = :PAYMENT_PLAN_SEQ

}

OCUPAYPL_PAY_PLN_DELETE{
 DELETE FROM OFFENDER_PAYMENT_PLANS
      WHERE PAYMENT_PLAN_ID = :PAYMENT_PLAN_ID
 	AND PAYMENT_PLAN_SEQ = :PAYMENT_PLAN_SEQ
}

OCUPAYPL_PAY_PLN_RECORD_COUNT {
 SELECT COUNT(*)
	   FROM OFFENDER_PAYMENT_PLANS
	   WHERE OFFENDER_ID = :OFFENDER_ID
	   }

OCUPAYPL_PAY_PLN_GET_AMOUNT {
select MAX_TOTAL_AMOUNT, INFORMATION_NUMBER  from OFFENDER_DEDUCTIONS where OFFENDER_ID = :OFFENDER_ID and INFORMATION_NUMBER = :INFORMATION_NUMBER
}
OCUPAYPL_PAYPLN_INSERT_OFFENDER_PAYMENT_PLANS {
	
INSERT INTO OMS_OWNER.OFFENDER_PAYMENT_PLANS(PAYMENT_PLAN_ID, PAYMENT_PLAN_SEQ, OFFENDER_ID, START_DATE, END_DATE, FREQUENCY, MONTHLY, WEEKLY, BI_WEEKLY, 
TWICE_MONTHLY_1, TWICE_MONTHLY_2, AMOUNT, MODIFY_USER_ID, MODIFY_DATETIME, INFORMATION_NUMBER, OFFENDER_DEDUCTION_ID, PAYMENT_COMPLETION_DATE, GROUP_ID,
LENIENCY_FLAG, PAYMENT_CLOSED_FLAG, PAYMENT_CLOSED_DATE, REGENERATION_FLAG, REGENERATION_DATE, ORIGINAL_OWING_AMOUNT, CASE_ID, CREATE_DATETIME, CREATE_USER_ID, SEAL_FLAG)

    VALUES(:paymentPlanId, :paymentPlanSeq, :offenderId, :startDate, :endDate, :frequency, :monthly,:weekly, :biWeekly,
    :twiceMonthly1, :twiceMonthly2, :amount, :modifyUserId, current_timestamp, :informationNumber, :offenderDeductionId, :paymentCompletionDate, :groupId,
    :leniencyFlag, :paymentClosedFlag, :paymentClosedDate, :regenerationFlag, :regenerationDate, :originalOwingAmount, :caseId, current_timestamp, :createUserId,:sealFlag)
}  

OCUPAYPL_PAYPLN_UPDATE_OFFENDER_PAYMENT_PLANS {
	UPDATE OFFENDER_PAYMENT_PLANS set PAYMENT_PLAN_ID = :paymentPlanId,PAYMENT_PLAN_SEQ = :paymentPlanSeq,OFFENDER_ID = :offenderId, START_DATE = :startDate,END_DATE = :endDate,
	FREQUENCY =:frequency,MONTHLY = :monthly,WEEKLY = :weekly,BI_WEEKLY = :biWeekly,TWICE_MONTHLY_1 = :twiceMonthly1,TWICE_MONTHLY_2 = :twiceMonthly2 ,AMOUNT =:amount,
	MODIFY_USER_ID = :modifyUserId,MODIFY_DATETIME = current_timestamp,INFORMATION_NUMBER =:informationNumber,OFFENDER_DEDUCTION_ID = :offenderDeductionId,
	PAYMENT_COMPLETION_DATE = :paymentCompletionDate,GROUP_ID =:groupId,LENIENCY_FLAG =:leniencyFlag,PAYMENT_CLOSED_FLAG =:paymentClosedFlag,
	PAYMENT_CLOSED_DATE = :paymentClosedDate,REGENERATION_FLAG = :regenerationFlag,REGENERATION_DATE = :regenerationDate,ORIGINAL_OWING_AMOUNT = :originalOwingAmount,
	CASE_ID = :caseId,CREATE_DATETIME = current_timestamp,CREATE_USER_ID = :createUserId,SEAL_FLAG = :sealFlag
	where  PAYMENT_PLAN_ID = :paymentPlanId and PAYMENT_PLAN_SEQ = :paymentPlanSeq
	
} 

OCUPAYPL_PAYPLN_DELETE_OFFENDER_PAYMENT_PLANS { 
	DELETE FROM OFFENDER_PAYMENT_PLANS 
	where  PAYMENT_PLAN_ID = :paymentPlanId and PAYMENT_PLAN_SEQ = :paymentPlanSeq
} 

OCUPAYPL_PAYSCH_FIND_V_OFFENDER_PAYMENT_SCHEDULES {
 	SELECT  PAYMENT_PLAN_ID,PAYMENT_PLAN_SEQ,PAYMENT_DATE,PAYMENT_AMOUNT,RECURSIVE_AMOUNT,PAID_AMOUNT,PAID_RECURSIVE_AMOUNT,INFORMATION_NUMBER,OFFENDER_DEDUCTION_ID,
 	MODIFY_USER_ID,MODIFY_DATETIME,GROUP_ID,PAYMENT_CLOSED_FLAG
 	FROM V_OFFENDER_PAYMENT_SCHEDULES  where payment_plan_id = :payment_plan_id ORDER BY PAYMENT_DATE
}
OCUPAYPL_PPTXN_FIND_PAYMENT_PLAN_TRANSACTIONS {
 	SELECT  PAYMENT_PLAN_ID,PAYMENT_PLAN_SEQ,PAYMENT_DATE,TRANSACTION_SEQ,TRANSACTION_DATE,TRANSACTION_AMOUNT,INFORMATION_NUMBER,GROUP_ID,CREATE_USER_ID,
 	CREATE_DATETIME,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG
 	FROM PAYMENT_PLAN_TRANSACTIONS where PAYMENT_PLAN_ID = :PAYMENT_PLAN_ID and PAYMENT_PLAN_SEQ = :PAYMENT_PLAN_SEQ  and PAYMENT_DATE  =:PAYMENT_DATE
 	ORDER BY TRANSACTION_DATE DESC
}
OCUPAYPL_SYSPFL_FIND_SYSTEM_PROFILES {
 	SELECT *  FROM SYSTEM_PROFILES  /* where  */
}

OCUPAYPL_PAY_PLN_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM V_OFFENDER_PAYMENT_SCHEDULES WHERE PAYMENT_PLAN_ID = :PAYMENTPLANID
}

OCUPAYPL_PAY_PLN_PREINSERT_PLAN_SEQ_ID {

}

OCUPAYPL_PAY_PLN_PREINSERT_SEQ_ID {
	SELECT coalesce (MAX(PAYMENT_PLAN_SEQ),0) + 1 FROM   OFFENDER_PAYMENT_PLANS WHERE  PAYMENT_PLAN_ID = :PAYMENT_PLAN_ID
}

OCUPAYPL_PAY_PLN_PREINSERT_PLAN_ID {

SELECT DISTINCT payment_plan_id FROM offender_payment_plans
       WHERE INFORMATION_NUMBER = :INFORMATION_NUMBER
            AND GROUP_ID = :GROUP_ID
            AND OFFENDER_ID = :OFFENDER_ID
            
}
OCUPAYPL_PAY_PLN_PREINSERT_SEQ {

SELECT  NEXTVAL('PAYMENT_PLAN_ID') FROM  DUAL


}

OCUPAYPL_PAY_PLN_GET_CASELOAD {
 SELECT description  FROM caseloads WHERE caseload_id = :CASELOAD_ID

}

OCUPAYPL_PAY_PLN_GET_PROFILE {
 SELECT DESCRIPTION FROM SYSTEM_PROFILES WHERE PROFILE_TYPE ='LABEL' AND PROFILE_CODE = :PROFILE_CODE

}

OCUPAYPL_GET_OFFENDER_ID_DATA {

SELECT va.OFFENDER_ID_DISPLAY,
       va.root_offender_id root_offender_id,
       INITCAP (last_name || ', ' || first_name || ' ' || middle_name) 
  FROM v_trust_header_a va
 WHERE va.root_offender_id = :OFFENDER_ID
   AND va.caseload_id = :CASELOAD_ID

}

OCUPAYPL_PAY_PLN_SUMMARY {

SELECT MAX (b.end_date) END_DATE,
       MIN (b.start_date) START_DATE,
       MAX (b.frequency) FREQUENCY,
       MAX (b.information_number) INFORMATION_NUMBER, 
       sum(a.PAYMENT_AMOUNT) PAYMENTAMOUNT
   FROM offender_payment_plans b ,  OFFENDER_PAYMENT_SCHEDULES a
   WHERE b.information_number = :INFORMATION_NUMBER
   AND b.group_id =:GROUP_ID
   AND b.offender_id = :OFFENDER_ID
   AND b.payment_closed_flag != 'Y'
   and b.PAYMENT_PLAN_ID = a.PAYMENT_PLAN_ID

}

OCUPAYPL_PAY_PLN_GET_TOT_DUE {

}

OCUPAYPL_PAY_SCH_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM PAYMENT_PLAN_TRANSACTIONS P WHERE P.PAYMENT_PLAN_ID = :PAYMENTPLANID AND P.PAYMENT_PLAN_SEQ = :PAYMENTPLANSEQ AND P.PAYMENT_DATE = :PAYMENTDATE
}

OCUPAYPL_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION  FROM   OMS_MODULES WHERE  MODULE_NAME = :V_FORM_NAME
}

OCUPAYPL_CGFKCHK_PAY_PLN_OFF_PLN_OFF_D_ {
	SELECT  '1' FROM   OFFENDER_DEDUCTIONS OFF_DED WHERE   OFF_DED.GROUP_ID = :GROUPID AND OFF_DED.INFORMATION_NUMBER = :INFORMATIONNUMBER AND     OFFENDER_ID = :PPOFFENDERID AND CASELOAD_ID = :TRUSTCASELOADID
}

OCUPAYPL_CGFKCHK_PAY_PLN_PAY_PLN_REF_C_ {
	SELECT REF_CODE2.DESCRIPTION FROM   REFERENCE_CODES REF_CODE2 WHERE  REF_CODE2.CODE = :FREQUENCY AND     DOMAIN = 'CF_FREQ' AND ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL) OR :MODE = 'QUERY')
}

OCUPAYPL_CGFKLKP_PAY_PLN_PAY_PLN_REF_C_ {
	select
	REF_CODE2.CODE
from
	REFERENCE_CODES REF_CODE2
where
	REF_CODE2.DESCRIPTION = :DSPDESCRIPTION
	and domain = 'CF_FREQ'
	and ((ACTIVE_FLAG = 'Y'
	and EXPIRED_DATE is null)
	
	)
}

OCUPAYPL_CGFKCHK_PAY_PLN_PAY_PLN_RE2_ {
SELECT REF_CODE1.DESCRIPTION
FROM REFERENCE_CODES REF_CODE1
WHERE
  REF_CODE1.CODE = :BIWEEKLY AND
  DOMAIN = 'PS_WEEKDAYS' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL))

}

OCUPAYPL_CGFKLKP_PAY_PLN_PAY_PLN_RE2_ {
	SELECT REF_CODE1.CODE
FROM REFERENCE_CODES REF_CODE1
WHERE
  (REF_CODE1.DESCRIPTION = :DSPDESCRIPTION3 OR (REF_CODE1.DESCRIPTION IS NULL AND :DSPDESCRIPTION3 IS NULL )) AND
  DOMAIN = 'PS_WEEKDAYS' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL))

}

OCUPAYPL_CGFKCHK_PAY_PLN_PAY_PLN_RE3_ {
	SELECT REF_CODE.DESCRIPTION
FROM REFERENCE_CODES REF_CODE
WHERE
  REF_CODE.CODE = :WEEKLY AND
  DOMAIN = 'PS_WEEKDAYS' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL))

}

OCUPAYPL_CGFKLKP_PAY_PLN_PAY_PLN_RE3_ {
	SELECT REF_CODE.CODE
FROM REFERENCE_CODES REF_CODE
WHERE
  (REF_CODE.DESCRIPTION = :DSPDESCRIPTION4 OR (REF_CODE.DESCRIPTION IS NULL AND :DSPDESCRIPTION4 IS NULL )) AND
  DOMAIN = 'PS_WEEKDAYS' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL))

}

OCUPAYPL_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE(), upper(USER) FROM  DUAL
}

OCUPAYPL_PAY_PLN_REPORT_LIST {
select a.payment_date as DUE_DATE
,b.information_number as INFORMATION_NUMBER
,a.payment_amount as PAYMENT_AMOUNT
,b.FREQUENCY,c.GROUP_CODE,
to_char('Monthly on the '||to_char(payment_date,'DD')||'th day of each month.') as PAYMENTFREQ
from offender_payment_schedules a
,offender_payment_plans b,obligation_groups c
where a.payment_plan_id = :PAYMENT_PLAN_ID
and a.payment_plan_seq=:PAYMENT_PLAN_SEQ
and b.payment_closed_flag <> :PAYMENT_CLOSED_FLAG
and c.group_id = a.group_id
and a.group_id = b.group_id
and a.group_id=1
and b.information_number=:INFORMATION_NUMBER
and a.payment_plan_id = b.payment_plan_id
}

OCUPAYPL_PAY_PLN_GET_DAY {
-- select to_number(to_char(to_date(:START_DATE,'dd-MM-yyyy'),'dd')) from dual
select
	extract( day
from
	to_date(:START_DATE::text, 'dd-MM-yyyy'))::int from dual
 }

