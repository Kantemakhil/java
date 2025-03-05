update system_profiles 
   set modify_datetime = current_timestamp
      ,modify_user_id = 'OMS_OWNER'
      ,profile_value = 'N' 
 where profile_type = 'DISPLAY' and profile_code = 'DOJ_SUPPORT';



