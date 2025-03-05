

---S4-21055

update work_items 
   set modify_datetime = current_timestamp
      ,modify_user_id = 'OMS_OWNER'
      ,update_trigger = 'N', delete_trigger = 'N' 
 where process = (select max(process_id) from bpmn_process where process_key = 'OIDEHLOC_EXCHANGE_OFFENDER_HOUSING_NOTIFY');

   