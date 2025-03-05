OIIOBALX_EXTERNAL_ACCOUNT_BALANCES {
	select account_balance_id, account_id,root_offender_id, account_type, balance, last_changed, account_details, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag from off_external_account_balances where root_offender_id = :rootOffenderId
}

OFFENDER_EXTERNAL_ACCOUNT_BALANCE {
	select account_balance_id, account_id, root_offender_id, account_type, balance, last_changed, account_details, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag from off_external_account_balances where root_offender_id = :rootOffenderId and account_type=:accountType 
}

UPDATE_OFFENDER_EXTERNAL_ACCOUNT_BALANCE{ 
   UPDATE off_external_account_balances set balance=:balance ,last_changed=:lastChanged,account_details=:accountDetails,modify_datetime=current_timestamp,modify_user_id=:modifyUserId  where root_offender_id=:rootOffenderId and account_type=:accountType
   }
   
  INSERT_OFFENDER_EXTERNAL_BALANCES{ 
  INSERT INTO off_external_account_balances(account_balance_id,account_id,root_offender_id,account_type,balance, last_changed, account_details, create_datetime,seal_flag,create_user_id) values
  (nextval('EXT_ACCOUNT_BALANCE_ID'),:accountId,:rootOffenderId,:accountType,:balance,:lastChanged,:accountDetails,current_timestamp,:sealFlag,:createUserId)
  }
  
  GET_ROOT_OFFENDER_IDS {
 SELECT ROOT_OFFENDER_ID,OFFENDER_ID_DISPLAY FROM OFFENDERS off WHERE off.offender_id_display In(:offenderIdDisplay)
}
GET_LATEST_MODIFIED_DATE{ 
select max(modify_datetime)  latestModifiedDate  from off_external_account_balances;
}
