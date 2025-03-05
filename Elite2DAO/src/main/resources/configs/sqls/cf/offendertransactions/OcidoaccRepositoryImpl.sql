 
OCIDOACC_OFFSUBA_FIND_OFFENDER_SUB_ACCOUNTS {
 	SELECT *
FROM OFFENDER_SUB_ACCOUNTS
WHERE
  OFFENDER_ID = :offenderId AND
  CASELOAD_ID = :caseloadId AND
  TRUST_ACCOUNT_CODE IN
    (SELECT ACCOUNT_CODE
     FROM ACCOUNT_CODES
     WHERE
       CASELOAD_TYPE = 'COMM')

}
OCIDOACC_PAYSCH_FIND_V_OFF_GROUPED_PAYMENT_PLANS {
/* SELECT 
  OFFENDER_ID, INFORMATION_NUMBER, GROUP_ID, PAYMENT_PLAN_ID, 
  PAYMENT_CLOSED_FLAG, MIN_DEDUCTION_ID, 
  (SELECT SUM (payment_amount - paid_amount)
   FROM v_offender_payment_schedules
   WHERE
     payment_plan_id = opp.PAYMENT_PLAN_ID AND
     payment_amount > paid_amount AND
     payment_closed_flag = 'N') as balowing, 
  (SELECT MIN (payment_date)
   FROM offender_payment_schedules
   WHERE
     payment_plan_id = opp.PAYMENT_PLAN_ID AND
     payment_date > SYSDATE()) as MIN_NEXT_PAYMENT_DATE, 
  (SELECT MAX (payment_date)
   FROM offender_payment_schedules
   WHERE
     payment_plan_id = opp.PAYMENT_PLAN_ID) as MAX_NEXT_PAYMENT_DATE, 
  (SELECT MIN (payment_date)
   FROM v_offender_payment_schedules
   WHERE
     payment_plan_id = opp.PAYMENT_PLAN_ID AND
     payment_date > SYSDATE() AND
     payment_closed_flag = 'N')as MIN_NEXT_PAYMENT_DATE_TWO
FROM OMS_OWNER.V_OFF_GROUPED_PAYMENT_PLANS opp
WHERE
  offender_id = :offender_id
ORDER BY 
  payment_closed_flag, min_deduction_id desc
**/

SELECT 
  OFFENDER_ID, INFORMATION_NUMBER, GROUP_ID, PAYMENT_PLAN_ID, 
  PAYMENT_CLOSED_FLAG, MIN_DEDUCTION_ID, 
  (SELECT SUM (payment_amount - paid_amount)
   FROM v_offender_payment_schedules
   WHERE
     payment_plan_id = opp.PAYMENT_PLAN_ID AND
     payment_amount > paid_amount AND
     payment_closed_flag = 'N') as balowing, 
  (SELECT MIN (payment_date)
   FROM offender_payment_schedules
   WHERE
     payment_plan_id = opp.PAYMENT_PLAN_ID AND
     payment_date > current_date ) as MIN_NEXT_PAYMENT_DATE, 
  (SELECT MAX (payment_date)
   FROM offender_payment_schedules
   WHERE
     payment_plan_id = opp.PAYMENT_PLAN_ID) as MAX_NEXT_PAYMENT_DATE, 
  (SELECT MIN (payment_date)
   FROM v_offender_payment_schedules
   WHERE
     payment_plan_id = opp.PAYMENT_PLAN_ID AND
     payment_date >current_date AND
     payment_closed_flag = 'N')as MIN_NEXT_PAYMENT_DATE_TWO
FROM V_OFF_GROUPED_PAYMENT_PLANS opp
WHERE
  offender_id = :offender_id
ORDER BY 
  payment_closed_flag, min_deduction_id desc


}
OCIDOACC_OFFBNC_FIND_OFFENDER_DEDUCTIONS {
SELECT OFFENDER_DEDUCTION_ID, CASELOAD_ID, OFFENDER_ID, CREDIT_LIMIT, DEDUCTION_TYPE, DEDUCTION_STATUS, DEDUCTION_PRIORITY, INFORMATION_NUMBER, DEDUCTION_PERCENTAGE, PROCESS_PRIORITY_NUMBER, EFFECTIVE_DATE, COMMENT_TEXT,
FIFO_FLAG, PAYEE_PERSON_ID, PAYEE_CORPORATE_ID, MAX_MONTHLY_AMOUNT, MAX_TOTAL_AMOUNT, DEDUCTION_AMOUNT, ADJUSTMENT_REASON_CODE, ADJUSTMENT_AMOUNT, ADJUSTMENT_USER_ID, ADJUSTMENT_TXN_ID, ADJUSTMENT_TEXT, MODIFY_DATE,
PAY_DEDUCTION_FLAG, MAX_RECURSIVE_AMOUNT, GROUP_ID, CASE_ID, PARENT_DEDUCTION_ID, JS_STATUS, COLLECT_AGENCY_AMOUNT, COLLECT_AGENCY_FLAG, COLLECT_SENT_DATE, OFFENDER_PAYMENT_PROFILE_ID, SEAL_FLAG,
CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID ,
CASE WHEN(SELECT COUNT(DEDUCTION_TYPE) FROM deduction_types WHERE deduction_type = ofd.DEDUCTION_TYPE AND deduction_category IN ('FXOB', 'CROB')) > 0 THEN 'Y' ELSE 'N' END DED_FLAG,
case when(SELECT COUNT(BENEFICIARY_ID) FROM offender_beneficiaries  WHERE offender_deduction_id = ofd.OFFENDER_DEDUCTION_ID AND tbd_flag = 'Y') > 0 then 'Y' else 'N' end TBD_FLAG,
 (SELECT DEDUCTION_DESC  FROM   DEDUCTION_TYPES   WHERE  DEDUCTION_TYPE = ofd.DEDUCTION_TYPE) as DEDUCTION_DESC
 FROM OMS_OWNER.OFFENDER_DEDUCTIONS ofd
WHERE INFORMATION_NUMBER =:informationNumber AND  coalesce(GROUP_ID,0)=coalesce(:groupId,0)  AND OFFENDER_ID = OFFENDER_ID  AND
 DEDUCTION_TYPE IN (SELECT DEDUCTION_TYPE
        FROM DEDUCTION_TYPES    WHERE CASELOAD_CODE IN ('COMM', 'BOTH')) ORDER BY OFFENDER_DEDUCTION_ID
 	
 	
}
OCIDOACC_SYSPFL_FIND_SYSTEM_PROFILES {
 	 SELECT COUNT (0)
           FROM gl_transactions
          WHERE offender_id = :offender_id
            AND reversal_reason_code = 'BOUNCED'
}

OCIDOACC_GET_ACCOUNT_CODES_TYPE_NAME {
 SELECT AC_CODE.SUB_ACCOUNT_TYPE
            ,AC_CODE.ACCOUNT_NAME
      FROM   ACCOUNT_CODES AC_CODE
      WHERE  AC_CODE.ACCOUNT_CODE =:accountCode
      }
OCIDOACC_GET_MON_AMT{
SELECT coalesce(deduction_amount, 0) deduction_amount
                    FROM offender_mon_deductions
                   WHERE offender_deduction_id = :offender_deduction_id
                     AND monthly_deduction_date = date_trunc('month', current_timestamp)
                     }
 OCIDOACC_GET_REC_MONTH {
  SELECT MONTHS_BETWEEN(date_trunc('month', current_timestamp), date_trunc('month', effective_date)) + 1
           FROM offender_deductions WHERE offender_deduction_id = :offender_ded_id
}
OCIDOACC_GET_ACT_CLOSED_FLAG {
SELECT account_closed_flag
FROM offender_trust_accounts
WHERE
  offender_id = :offenderId
            AND caseload_id in (select TRUST_TRUST_COMMUNITY_CSLD(:caseloadid) FROM DUAL)
}
OCIDOACC_LV_NON_COMPLETED_SCH {
 SELECT coalesce(COUNT(*), 0)
              FROM offender_payment_plans
             WHERE payment_plan_id = :payment_plan_id
               AND coalesce(payment_completion_date::text, '') = ''
               AND payment_closed_flag = 'N'
}

OCIDOACC_GETAMOUNT {
   SELECT SUM(coalesce(payment_amount, 0)) - SUM(coalesce(paid_amount, 0))
              FROM v_offender_payment_schedules
             WHERE payment_plan_id = :payment_plan_id
               AND payment_date <= :dueDate
               AND payment_closed_flag = 'N'
}

OCIDOACC_GET_DAYS_LATE {
 SELECT coalesce(MIN(payment_date), date_trunc('day', :next_payment_date::timestamp))
              FROM v_offender_payment_schedules
             WHERE payment_plan_id = :planId
               AND payment_date < :next_payment_date
               AND payment_amount > coalesce(paid_amount, 0)
               AND payment_closed_flag = 'N'
 }

 OCIDOACC_GET_ARREARS {
--  SELECT SUM(payment_amount - paid_amount)
--                 FROM v_offender_payment_schedules
--                WHERE payment_plan_id = :paymentPlanId
--                  AND payment_amount > paid_amount
--                  AND payment_date BETWEEN(:d_date - 1) AND date_trunc('day', current_timestamp)
--                  AND payment_closed_flag = 'N'
      SELECT SUM(payment_amount - paid_amount)
                 FROM v_offender_payment_schedules
                WHERE payment_plan_id = :paymentPlanId
                  AND payment_amount > paid_amount
                  AND payment_date BETWEEN(:d_date::date - 1) AND date_trunc('day', current_timestamp::date)
                  AND payment_closed_flag = 'N'
 }
 OCIDOACC_GET_REASON {
-- SELECT COUNT(payment_amount)
--                 FROM v_offender_payment_schedules
--                WHERE payment_plan_id = :plan_id
--                  AND payment_amount > paid_amount
--                  AND payment_date BETWEEN(:tempdate - 1) AND date_trunc('day', current_timestamp)
--                  AND payment_closed_flag = 'N'     
        SELECT COUNT(payment_amount)
                 FROM v_offender_payment_schedules
                WHERE payment_plan_id = :plan_id
                  AND payment_amount > paid_amount
                  AND payment_date BETWEEN(:tempdate::date - 1) AND date_trunc('day', current_timestamp::date)
                  AND payment_closed_flag = 'N'  
}
 OCIDOACC_DAYS_LATE_QUERY{
-- select greatest(date_trunc('day', sysdate())::date - date_trunc('day', :tempDate)::date,0) from dual
-- select greatest(date_trunc('day',current_timestamp)::date - date_trunc('day', :tempDate)::date, 0) from dual
   select greatest(current_timestamp::date - :tempDate::date, 0) from dual

 }