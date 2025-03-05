OIMIEPLV_INSERT_IEP_LEVEL_CODES{
INSERT INTO incentives_earn_privs (iep_level_code,iep_level_description,list_seq,review_days,intake_iep,canteen_limits,active_flag,expiry_date,create_user_id,create_datetime,modify_datetime)
VALUES (:iepLevelCode,:iepLeveldescription,:sequence,:reviewDays,:intakeIpe,:canteenLimit,:activeFlag,:expiryDate,:createUserId,CURRENT_TIMESTAMP,NULL)
}

OIMIEPLV_UPDATE_IEP_LEVEL_CODES{
update incentives_earn_privs set iep_level_description =:iepLeveldescription, list_seq =:sequence, review_days =:reviewDays, canteen_limits =:canteenLimit, intake_iep =:intakeIpe, active_flag =:activeFlag, expiry_date =:expiryDate, modify_datetime =current_timestamp , modify_user_id =:modifyUserId where iep_level_code =:iepLevelCode
}
OIMIEPLV_DELETE_IEP_LEVEL_BY_IEP_LEVEL_CODE
{
DELETE FROM incentives_earn_privs WHERE iep_level_code=:iepLevelCode
}
OIMIEPLV_GET_ALL_IEP_LEVEL_CODES
{
select * from incentives_earn_privs ilm order by create_datetime desc
}
OIMIEPLV_CHECK_UPDATE_OR_DELETE_ACTIVE_FALG
{
select (case when (select count(*)>0 from off_incentives_earn_privs where iep_level_code=:iepLevelCode) then 2 else 0 end) as num
}

OIMIEPLV_GET_PROFILE_VALUE{
select profile_value from system_profiles sp where sp.profile_code = 'IEP_MAN_REV'
}