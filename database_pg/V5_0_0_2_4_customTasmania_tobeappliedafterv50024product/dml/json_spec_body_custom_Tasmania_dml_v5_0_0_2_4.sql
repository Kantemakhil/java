insert into json_spec(id, spec_key, json_specs, create_datetime, create_user_id, modify_datetime, modify_user_id) 
values(16,'TRANSFORM_ACCOUNT_BALANCE', '[
  {
    "operation": "shift",
    "spec": {
      "inputData": {
        "prisonerAccounts": {
          "Balances": "accountBalances"
        }
      }
    }
  }
]', current_timestamp, 'OMS_OWNER', current_timestamp, NULL);