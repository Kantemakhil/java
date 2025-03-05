insert into oms_owner.oms_users_list(user_id, password, create_datetime, create_user_id)
  values('SYSTEM', encode(lower('SYSTEM')::bytea, 'base64'), CURRENT_TIMESTAMP, 'OMS_OWNER');
 