update
	dynamic_grid_config
set
	config_json = '[{"field":"displayNo","fieldName":"ocdleglo.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"hearingDate","fieldName":"ocdparor.hearingdate","dataType":"date","required":true},{"field":"authority","fieldName":"ocdparor.authority","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdleglo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=PAR","sourceType":"OIMSREQS","required":true},{"field":"commenceType","fieldName":"ocdleglo.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","required":true,"editable":false},{"field":"duration","fieldName":"ocdparor.duration","dataType":"text","required":true,"editable":false},{"field":"lengthBtn","dataType":"hyperlink","link":"/durationToLine","parentField":["duration","terms"]},{"field":"commenceDate","fieldName":"ocdleglo.commencedate","dataType":"date","required":true},{"field":"expiryDate","fieldName":"ocdparor.expirydate","dataType":"date"},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdleglo.intParties"},{"field":"status","fieldName":"ocdleglo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"affectedOrders","hide":true,"dataType":"text"},{"field":"terms","hide":true,"dataType":"text"}]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	grid_name = 'paroleOrders'
	and module_name = 'OCDPAROR';

update
	dynamic_grid_config
set
	config_json = '[{"field":"displayNo","fieldName":"custorder.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"custorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=CUST","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"custorder.termtypeandlength","dataType":"text","editable":false},{"field":"opTerm","fieldName":"ocdlegls.operativeheading","dataType":"text","editable":false},{"field":"commenceType","fieldName":"custorder.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","editable":false},{"field":"relatedTo","fieldName":"custorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"custorder.commencedate","dataType":"text","editable":false},{"field":"erd","fieldName":"custorder.erd","dataType":"text","editable":false},{"field":"ped","fieldName":"custorder.ped","dataType":"text","editable":false},{"field":"lrd","fieldName":"custorder.lrd","dataType":"text","editable":false},{"field":"hed","fieldName":"ocdleglo.holdExpirydate","dataType":"text","editable":false},{"field":"overridebtn","fieldName":"ocdleglo.overridelaunch","dataType":"hyperlink","editable":false},{"field":"overrides","fieldName":"","dataType":"text","editable":false,"hide":true},{"field":"status","fieldName":"custorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"orderType","hide":true,"dataType":"text","editable":false}]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	grid_name = 'custOrders'
	and module_name = 'OCDLEGLS';