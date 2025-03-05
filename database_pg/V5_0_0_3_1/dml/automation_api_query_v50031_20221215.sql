update automation_api_query 
 set modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,query_text = 'INSERT INTO OCDLEGLS_DATA(ID, FORM_INFO_JSON, FORM_IDENTIFIER, create_user_id) Values (nextval(''ocdlegls_data_id_seq''), :formInfoJson, :formIdentifier,''OMS_OWNER'')'
where  query_key  = 'SAVE_SENT_CALC'; 