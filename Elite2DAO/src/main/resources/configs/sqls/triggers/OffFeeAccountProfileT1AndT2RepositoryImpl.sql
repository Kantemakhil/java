OFF_FEE_ACCOUNT_PROFILE_HTY_FIRST{
INSERT INTO OFF_FEE_ACCOUNT_PROFILE_HTY (OFFENDER_FEE_ID, RECORD_DATETIME, AMOUNT, DAY_OF_MONTH, START_DATE, EFFECTIVE_DATE, EXPIRY_DATE, FEE_ACT_STATUS, STATUS_EFFECTIVE_DATE, COMMENT_TEXT, create_datetime, modify_datetime, create_user_id) 
values (:offenderFeeId, current_date, :amount, :dayOfMonth, :startDate, :effectiveDate, :expiryDate, :feeActStatus, :statusEffectiveDate, :commentText, current_timestamp, null, :createUserId)
}

OFF_FEE_ACCOUNT_PROFILE_HTY_SECOND{
INSERT INTO OFF_FEE_ACCOUNT_PROFILE_HTY (OFFENDER_FEE_ID, RECORD_DATETIME, AMOUNT, DAY_OF_MONTH, START_DATE, EFFECTIVE_DATE, EXPIRY_DATE, FEE_ACT_STATUS, STATUS_EFFECTIVE_DATE, create_datetime, modify_datetime, create_user_id) 
values (:offenderFeeId, current_date, :amount, :dayOfMonth, :startDate, :effectiveDate, :expiryDate, :feeActStatus, :statusEffectiveDate, current_timestamp, null, :createUserId);
}