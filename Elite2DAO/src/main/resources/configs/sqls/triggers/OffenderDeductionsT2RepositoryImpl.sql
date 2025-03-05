OFFENDER_DEDUCTIONS_T2_INSERT_OFFENDER_OBLIGATION_HTY {

	 INSERT INTO offender_obligation_hty (
                        offender_deduction_id,
                        deduction_seq,
                        information_number,
                        deduction_type,
                        adjusted_amount,
                        modified_user_id,
                        create_user_id,
                        create_datetime,
                        modify_datetime,
                        max_total_amount
                     )
              VALUES (
                 :offenderDeductionId,
                 :deductionSeq,
                 :informationNumber,
                 :deductionType,
                 :adjustedAmount,
                 :modifiedUserId,
                 :createUserId,
                 current_timestamp,
                 current_timestamp,
                 coalesce (:maxTotalAmount::int, 0)
              )

}
	
OFFENDER_DEDUCTIONS_T2_NEXT_DED_SEQ_C {
	SELECT coalesce (MAX (deduction_seq::int), 0) + 1
	        FROM offender_obligation_hty
	       WHERE offender_deduction_id = :offender_deduction_id
	       
	}       