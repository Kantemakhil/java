update system_settings 
  set  modify_datetime = current_timestamp 
   ,modify_user_id = 'OMS_OWNER' 
   ,setting_value = '[{"KEY_DESC":"ENABLE NEW TAB", "KEY_CODE":"ENABLE_NEWTAB", "VALUE":"Y"}, {"KEY_DESC":"AUTOSAVE TIME", "KEY_CODE":"AUTOSAVE_TIME",           "VALUE":"6000"}, {"KEY_DESC":"PDF VIEWER SERVICE", "KEY_CODE":"PDF_SERVICE", "VALUE":"http://10.60.2.179:4003/api/pdfviewer"}]' where setting_type = 'EliteDoc' and setting_provider_code = 'ELITE_DOC'; 