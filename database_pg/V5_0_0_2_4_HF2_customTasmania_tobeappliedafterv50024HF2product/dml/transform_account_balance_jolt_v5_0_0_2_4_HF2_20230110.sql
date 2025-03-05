update json_spec set 	modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,json_specs='[
{
"operation": "shift",
"spec": {
"inputData": {
"prisonerAccounts": {
"balances": "accountBalances"
}
}
}
}
]' where spec_key='TRANSFORM_ACCOUNT_BALANCE';