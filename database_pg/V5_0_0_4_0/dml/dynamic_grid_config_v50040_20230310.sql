update
	dynamic_grid_config
set
	config_json = '[{"field":"displayNo","fieldName":"custorder.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"custorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=CUST","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"custorder.termtypeandlength","dataType":"text","editable":false},{"field":"opTerm","fieldName":"ocdlegls.operativeheading","dataType":"text","editable":false},{"field":"commenceType","fieldName":"custorder.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","editable":false},{"field":"relatedTo","fieldName":"custorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"custorder.commencedate","dataType":"text","editable":false},{"field":"erd","fieldName":"custorder.erd","dataType":"text","editable":false},{"field":"ped","fieldName":"custorder.ped","dataType":"text","editable":false},{"field":"lrd","fieldName":"custorder.lrd","dataType":"text","editable":false},{"field":"hed","fieldName":"ocdleglo.holdExpirydate","dataType":"text","editable":false},{"field":"overridebtn","fieldName":"ocdleglo.overridelaunch","dataType":"hyperlink","editable":false},{"field":"overrides","fieldName":"","dataType":"text","editable":false,"hide":true},{"field":"sentOverrideCheck","dataType":"checkbox","fieldName":"ocdlegls.overrideCheck","editable":false},{"field":"status","fieldName":"custorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"orderType","hide":true,"dataType":"text","editable":false}]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	grid_name = 'custOrders'
	and module_name = 'OCDLEGLS';
	
	
update
	dynamic_grid_config
set
	config_json = '[{"field":"date_type_display","dataType":"text","fieldName":"ocdlegls.datetype","editable":false},{"field":"date_value","dataType":"date","fieldName":"ocdlegls.value","editable":false},{"field":"indefinite","dataType":"custom","fieldName":"ocdlegls.indefinite","editable":false},{"field":"override_date_value","dataType":"date","fieldName":"ocdlegls.valueoverride","editable":false},{"field":"override_indefinite","dataType":"checkbox","fieldName":"ocdlegls.indefiniteoverride","editable":false},{"field":"clearBtn","dataType":"hyperlink","fieldName":"","editable":true}]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	grid_name = 'keyDateSummary'
	and module_name = 'OCDLEGLS'	