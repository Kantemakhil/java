UPDATE dynamic_grid_config 
   SET  modify_datetime = current_timestamp
       ,modify_user_id = 'OMS_OWNER' 
       ,config_json ='[{"field":"date_type","dataType":"text","fieldName":"ocdlegls.datetype","editable":false},{"field":"value","dataType":"custom","fieldName":"ocdlegls.value","editable":false}]'::bytea  
 WHERE module_name = 'OCDLEGLS' AND grid_name = 'keyDateSummary';

UPDATE dynamic_grid_config 
   SET  modify_datetime = current_timestamp
       ,modify_user_id = 'OMS_OWNER' 
       ,CONFIG_JSON ='[{"field":"displayNo","fieldName":"custorder.no","dataType":"text","editable":false},{"field":"no","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"custorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=CUST","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"custorder.termtypeandlength","dataType":"text","editable":false},{"field":"commenceType","fieldName":"custorder.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","editable":false},{"field":"relatedTo","fieldName":"custorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"custorder.commencedate","dataType":"date","editable":false},{"field":"erd","fieldName":"custorder.erd","dataType":"text","editable":false},{"field":"ped","fieldName":"custorder.ped","dataType":"date","editable":false},{"field":"lrd","fieldName":"custorder.lrd","dataType":"text","editable":false},{"field":"status","fieldName":"custorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"orderType","hide":true,"dataType":"text","editable":false}]'::bytea  
 WHERE module_name = 'OCDLEGLS' AND grid_name = 'custOrders';
 