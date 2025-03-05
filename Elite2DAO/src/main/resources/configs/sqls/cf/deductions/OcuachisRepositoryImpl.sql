OCUACHIS_FIND_HISTORY_EXECUTEQUERY {
--select OFFENDER_FEE_ID, RECORD_DATETIME, AMOUNT, DAY_OF_MONTH, START_DATE, EFFECTIVE_DATE, EXPIRY_DATE, 
--(select description from reference_codes where code = FEE_ACT_STATUS and domain = 'DED_STATUS') 
--FEE_ACT_STATUS, STATUS_EFFECTIVE_DATE, CREATE_DATETIME, 
--NVL(( SELECT STF.last_name || ', ' || STF.first_name AS full_name FROM staff_members STF WHERE STF.user_id = OFF.create_user_id ),'  ') 
--AS create_user_id, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG from OFF_FEE_ACCOUNT_PROFILE_HTY OFF
--where OFFENDER_FEE_ID=:offenderFeeId order by record_datetime desc
select OFFENDER_FEE_ID, RECORD_DATETIME, AMOUNT, DAY_OF_MONTH, START_DATE, EFFECTIVE_DATE, EXPIRY_DATE, 
(select description from reference_codes where code = FEE_ACT_STATUS and domain = 'DED_STATUS') 
FEE_ACT_STATUS, STATUS_EFFECTIVE_DATE, CREATE_DATETIME, 
coalesce (( SELECT STF.last_name || ', ' || STF.first_name AS full_name FROM staff_members STF WHERE STF.user_id = OFF.create_user_id ),'  ') 
AS create_user_id, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG from OFF_FEE_ACCOUNT_PROFILE_HTY OFF
where OFFENDER_FEE_ID=:offenderFeeId order by record_datetime desc
}
OCUACHIS_FEE_CODE_DESCRIPTION_QUERY {
select deduction_desc from deduction_types where deduction_type=:feeCode
}
OCUACHIS_GET_USER_ID{
SELECT last_name || ', ' || first_name as FULL_NAME FROM STAFF_MEMBERS WHERE USER_ID = :userid
}