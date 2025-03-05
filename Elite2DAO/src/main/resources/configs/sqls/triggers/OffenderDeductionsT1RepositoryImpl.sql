OFFENDER_DEDUCTIONS_T1_NEXT_DED_SEQ_C {
 SELECT NVL (MAX (DEDUCTION_SEQ), 0) + 1
        FROM OFFENDER_OBLIGATION_HTY
       WHERE OFFENDER_DEDUCTION_ID = :OFFENDER_DEDUCTION_ID
}
OFFENDER_DEDUCTIONS_T1_INSERT {
 INSERT INTO OFFENDER_OBLIGATION_HTY (
                        OFFENDER_DEDUCTION_ID,
                        DEDUCTION_SEQ,
                        INFORMATION_NUMBER,
                        DEDUCTION_TYPE,
                        ADJUSTED_AMOUNT,
                        MODIFIED_USER_ID,
                        MODIFIED_DATETIME,
                        MAX_TOTAL_AMOUNT
                     )
              VALUES (
                 :offenderDeductionId,
                 :deductionSeq,
                 :informationNumber,
                 :deductionType,
                 0,
                 :modifiedUserId,
                 SYSDATE(),
                 :maxTotalAmount
              )
}

OFFENDER_DEDUCTIONS_T1_UPDATE {
 INSERT INTO OFFENDER_OBLIGATION_HTY (
                        OFFENDER_DEDUCTION_ID,
                        DEDUCTION_SEQ,
                        INFORMATION_NUMBER,
                        DEDUCTION_TYPE,
                        ADJUSTED_AMOUNT,
                        MODIFIED_USER_ID,
                        MODIFIED_DATETIME,
                        MAX_TOTAL_AMOUNT
                     )
              VALUES (
                 :offenderDeductionId,
                 :deductionSeq,
                 :informationNumber,
                 :deductionType,
                 COALESCE (:maxTotalAmount, 0) -
                 COALESCE (:oldMaxTotalAmount, 0),
                 :modifiedUserId,
                 SYSDATE(),
                 COALESCE (:maxTotalAmount, 0)
              )
}
OFFENDER_DEDUCTIONS_T1_GETMAX_TOTAL_AMT {
SELECT * FROM OFFENDER_DEDUCTIONS WHERE OFFENDER_DEDUCTION_ID = :OFFENDER_DEDUCTION_ID
}