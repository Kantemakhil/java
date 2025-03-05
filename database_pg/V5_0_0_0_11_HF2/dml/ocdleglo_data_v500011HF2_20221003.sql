update ocdleglo_data
  set  modify_datetime = current_timestamp
      ,modify_user_id = 'OMS_OWNER' 
      ,form_identifier=replace(form_identifier,'offenderid','offenderBookId')
where upper(form_identifier) like upper('%offenderid%');
