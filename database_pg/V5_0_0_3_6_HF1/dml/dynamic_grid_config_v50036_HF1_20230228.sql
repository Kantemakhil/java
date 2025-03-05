update
	dynamic_grid_config
set
	config_json = '[{"field":"select","fieldName":"ocdparor.select","dataType":"checkbox","editable":true},{"field":"displayNo","fieldName":"custorder.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"custorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=CUST","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"custorder.termtypeandlength","dataType":"text","editable":false},{"field":"relatedTo","fieldName":"custorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"custorder.commencedate","dataType":"text","editable":false},{"field":"erd","fieldName":"custorder.erd","dataType":"text","editable":false},{"field":"ped","fieldName":"custorder.ped","dataType":"text","editable":false},{"field":"lrd","fieldName":"custorder.lrd","dataType":"text","editable":false},{"field":"status","fieldName":"custorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false}]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	grid_name = 'affectedOrders'
	and module_name = 'OCDPAROR';
	
update
	dynamic_grid_config
set
	config_json = '[{"field":"termType","fieldName":"termToLine.termtype","dataType":"lov","source":"link","sourceType":"OIMSREQS","url":"ocmpconf/populateTermType?sentCategory=:orderType&sentType=:sentType","required":true,"editable":true},{"field":"years","fieldName":"termToLine.years","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"months","fieldName":"termToLine.months","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"weeks","fieldName":"termToLine.weeks","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"days","fieldName":"termToLine.days","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"fixedExpiry","fieldName":"durationToLine.fixedexpiry","dataType":"checkbox"},{"field":"indefinite","fieldName":"termToLine.indefinite","dataType":"checkbox"}]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	grid_name = 'duration'
	and module_name = 'OCDPAROR';	