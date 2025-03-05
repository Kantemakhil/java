insert into oms_owner.oms_users_list(user_id, password, create_datetime, create_user_id, modified_datetime, modify_user_id)
  SELECT 'SYSTEM', encode(lower('SYSTEM')::bytea, 'base64'), CURRENT_TIMESTAMP, 'OMS_OWNER' , CURRENT_TIMESTAMP, 'OMS_OWNER' 
     WHERE NOT EXISTS (SELECT 1 FROM oms_users_list WHERE user_id = 'SYSTEM');
	 
	 
 