OFFENDER_BENEFICIARIES_T2_DED_AMOUNT_TYPE {
-- SELECT max_total_amount, max_monthly_amount, max_recursive_amount
      --  FROM offender_deductions
       --WHERE offender_deduction_id = :offender_deduction_id and rownum = 1
       SELECT max_total_amount, max_monthly_amount, max_recursive_amount
        FROM offender_deductions
       WHERE offender_deduction_id = :offender_deduction_id  limit  1
}
