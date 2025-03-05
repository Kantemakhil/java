UPDATE dynamic_grid_config 
   SET  modify_datetime = current_timestamp
       ,modify_user_id = 'OMS_OWNER' 
       ,config_json ='[{"field":"date_type","dataType":"text","fieldName":"ocdlegls.datetype","editable":false},{"field":"value","dataType":"date","fieldName":"ocdlegls.value","editable":false},{"field":"indefinite","dataType":"checkbox","fieldName":"ocdlegls.indefinite","editable":false}]' 
  WHERE module_name = 'OCDLEGLS' AND grid_name = 'keyDateSummary';
  