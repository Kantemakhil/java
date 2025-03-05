Update system_profiles 
  set modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,profile_value='orange-color-theme' 
 where profile_code='DEFAULT_THEM';