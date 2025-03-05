OFFENDER_BENEFICIARIES_T1_DED_AMOUNT_TYPE {

	SELECT max_total_amount, max_monthly_amount, max_recursive_amount
        FROM offender_deductions
       WHERE offender_deduction_id = :offender_deduction_id LIMIT 1

}

OFFENDER_BENEFICIARIES_T1_THIS_MONTH_BENEFICIARY {

	SELECT 'Y'
        FROM offender_mon_beneficiaries
       WHERE beneficiary_id = :beneficiary_id
         AND monthly_deduction_date = date_trunc('month', current_timestamp)

}

OFFENDER_BENEFICIARIES_T1_UPDATE_OFFENDER_MON_BENEFICIARIES {

	 UPDATE offender_mon_beneficiaries
               SET received_amount = received_amount + :received_amount
             WHERE beneficiary_id = :beneficiary_id
               AND monthly_deduction_date = date_trunc('month', current_timestamp) 

               
             /*  UPDATE offender_mon_beneficiaries
               SET received_amount = received_amount +
                                     (nvl(:NEW.received_amount, 0) -
                                     nvl(:OLD.received_amount, 0))
             WHERE beneficiary_id = :NEW.beneficiary_id
               AND monthly_deduction_date = trunc(SYSDATE, 'MM');*/
}

OFFENDER_BENEFICIARIES_T1_INSERT_OFFENDER_MON_BENEFICIARIES {

	INSERT INTO offender_mon_beneficiaries
              (monthly_deduction_date,
               offender_deduction_id,
               received_amount,
               beneficiary_id,
               person_id,
               corporate_id,
               unknown_ben_id,
               modify_user_id,
               modify_datetime)
            VALUES
             ( current_timestamp,
               :offenderDeductionId,
               :receivedAmount,
               :beneficiaryId,
               :personId,
               :corporateId,
               :unknownBenId,
               :modifyUserId,
               current_timestamp)

}

OFFENDER_BENEFICIARIES_T1_GET_OFFENDER_MON_BENEFICIARIES{
SELECT * FROM OFFENDER_MON_BENEFICIARIES WHERE beneficiary_id = :beneficiaryId
}