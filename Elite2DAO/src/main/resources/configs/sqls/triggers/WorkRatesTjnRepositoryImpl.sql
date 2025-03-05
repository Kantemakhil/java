WORK_RATES_JN_INSERT{
INSERT Into work_rates_jn(jn_operation ,jn_oracle_user,jn_datetime ,jn_notes,jn_appln,jn_session,pay_range_code ,unit_code,compensation_code,performance_code,rate_amount,modify_user_id ,create_user_id,seal_flag) values(:jnOperation,:createUserId,:createDatetime,null,null,0,:payRangeCode,:unitCode,:compensationCode,:performanceCode,:rateAmount,:modifyUserId ,:createUserId ,:sealFlag)
}
WORK_RATES_OLD{
SELECT PAY_RANGE_CODE,UNIT_CODE,COMPENSATION_CODE,PERFORMANCE_CODE,RATE_AMOUNT,MODIFY_USER_ID,MODIFY_DATETIME,CREATE_DATETIME,CREATE_USER_ID, SEAL_FLAG FROM WORK_RATES WHERE PAY_RANGE_CODE=:payRangeCode AND UNIT_CODE=:unitCode AND COMPENSATION_CODE=:compensationCode AND PERFORMANCE_CODE=:performanceCode
}