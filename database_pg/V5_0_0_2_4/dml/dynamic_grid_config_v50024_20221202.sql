update
	DYNAMIC_GRID_CONFIG
set  modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,CONFIG_JSON = '[{"field":"displayNo","fieldName":"custorder.no","dataType":"text","editable":false},{"field":"no","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"custorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=CUST","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"custorder.termtypeandlength","dataType":"text","editable":false},{"field":"commenceType","fieldName":"custorder.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","editable":false},{"field":"relatedTo","fieldName":"custorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"custorder.commencedate","dataType":"text","editable":false},{"field":"erd","fieldName":"custorder.erd","dataType":"text","editable":false},{"field":"ped","fieldName":"custorder.ped","dataType":"date","editable":false},{"field":"lrd","fieldName":"custorder.lrd","dataType":"text","editable":false},{"field":"holdExpiryDate","fieldName":"ocdleglo.holdExpirydate","dataType":"text","editable":false},{"field":"status","fieldName":"custorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"orderType","hide":true,"dataType":"text","editable":false}]'
where
	MODULE_NAME = 'OCDLEGLS'
	and GRID_NAME = 'custOrders';

update
	DYNAMIC_GRID_CONFIG
set  modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,CONFIG_JSON = '[{"field":"displayNo","fieldName":"ncustorder.no","dataType":"text","editable":false},{"field":"no","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"ncustorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=NCUS","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"ncustorder.termtypeandlength","dataType":"text","editable":false},{"field":"commenceType","fieldName":"ncustorder.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","editable":false},{"field":"relatedTo","fieldName":"ncustorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"ncustorder.commencedate","dataType":"text","editable":false},{"field":"expiryDate","fieldName":"ncustorder.expiryDate","dataType":"text","editable":true},{"field":"status","fieldName":"ncustorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"orderType","hide":true,"dataType":"text","editable":false}]'
where
	MODULE_NAME = 'OCDLEGLS'
	and GRID_NAME = 'nCustOrders';

	