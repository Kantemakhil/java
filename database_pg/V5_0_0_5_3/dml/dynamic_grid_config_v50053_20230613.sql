update
	dynamic_grid_config
set
	config_json = '[{"field":"displayNo","fieldName":"ocdleglo.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"orderedDate","fieldName":"ocdleglo.ordereddate","dataType":"date","required":true},{"field":"matter","fieldName":"ocdleglo.matter","dataType":"text","editable":false},{"field":"court","fieldName":"ocdleglo.court","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdleglo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS","required":true},{"field":"commenceType","fieldName":"ocdleglo.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","required":true},{"field":"relatedTo","fieldName":"ocdleglo.linkto","dataType":"text","editable":false},{"field":"relatedToLaunch","dataType":"hyperlink","link":"/relatedToLine","parentField":["relatedTo"]},{"field":"commenceDate","fieldName":"ocdleglo.commencedate","dataType":"date"},{"field":"termTypeAndLength","fieldName":"ocdleglo.termtypeandlength","dataType":"text","required":true,"editable":false},{"field":"lengthBtn","dataType":"hyperlink","link":"/termToLine","parentField":["termTypeAndLength","terms"]},{"field":"status","fieldName":"ocdleglo.status","dataType":"lov","source":"link","sourceType":"OCMSTATS","url":"ocmpconf/rgOrderStatus?orderType=","editable":true,"required":true},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdleglo.intParties"},{"field":"terms","hide":true,"dataType":"text"},{"field":"charges","hide":true,"dataType":"text"},{"field":"orderType","hide":true,"dataType":"text"},{"field":"holdExpiryDate","fieldName":"ocdleglo.holdExpirydate","dataType":"date"},{"field":"activeType","hide":true,"dataType":"text"},{"field":"iwpButton","fieldName":"ocdleglo.document","dataType":"hyperlink","link":"/EOFFENDER","queryparam":["objectId"]}]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDCORDS'
	and grid_name = 'custOrd'
	and db_table_name = 'OCDLEGLO_DATA';
	
	
	
	update
	dynamic_grid_config
set
	config_json = '[{"field":"displayNo","fieldName":"ocdncode.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"orderedDate","fieldName":"ocdncode.ordereddate","dataType":"date","required":true},{"field":"matter","fieldName":"ocdncode.matter","dataType":"text","editable":false},{"field":"court","fieldName":"ocdncode.court","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdncode.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS","required":true},{"field":"commenceType","fieldName":"ocdncode.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","required":true},{"field":"relatedTo","fieldName":"ocdncode.linkto","dataType":"text","editable":false},{"field":"relatedToLaunch","dataType":"hyperlink","link":"/nonCustRelated","parentField":["relatedTo"]},{"field":"commenceDate","fieldName":"ocdncode.commencedate","dataType":"date"},{"field":"termTypeAndLength","fieldName":"ocdncode.termtypeandlength","dataType":"text","required":true,"editable":false},{"field":"lengthBtn","dataType":"hyperlink","link":"/nonCustDuration","parentField":["termTypeAndLength","terms"]},{"field":"expiryDate","fieldName":"ocdncode.expiryDate","dataType":"date","editable":true},{"field":"status","fieldName":"ocdncode.status","dataType":"lov","source":"link","sourceType":"OCMSTATS","url":"ocmpconf/rgOrderStatus?orderType=","editable":true,"required":true},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdncode.intparties"},{"field":"terms","hide":true,"dataType":"text"},{"field":"orderType","hide":true,"dataType":"text"},{"field":"charges","hide":true,"dataType":"text"},{"field":"activeType","hide":true,"dataType":"text"},{"field":"iwpButton","fieldName":"ocdleglo.document","dataType":"hyperlink","link":"/EOFFENDER","queryparam":["objectId"]}]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDNCODE'
	and grid_name = 'nonCustOrd'
	and db_table_name = 'OCDLEGLO_DATA';
	
	update
	dynamic_grid_config
set
	config_json = '[{"field":"displayNo","fieldName":"ocdleglo.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"hearingDate","fieldName":"ocdparor.hearingdate","dataType":"date","required":true},{"field":"authority","fieldName":"ocdparor.authority","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdleglo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=PAR","sourceType":"OIMSREQS","required":true},{"field":"commenceType","fieldName":"ocdleglo.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","required":true,"editable":false},{"field":"duration","fieldName":"ocdparor.duration","dataType":"text","required":true,"editable":false},{"field":"lengthBtn","dataType":"hyperlink","link":"/durationToLine","parentField":["duration","terms"]},{"field":"commenceDate","fieldName":"ocdleglo.commencedate","dataType":"date","required":true},{"field":"expiryDate","fieldName":"ocdparor.expirydate","dataType":"date"},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdleglo.intParties"},{"field":"status","fieldName":"ocdleglo.status","dataType":"lov","source":"link","sourceType":"OCMSTATS","url":"ocmpconf/rgOrderStatus?orderType=PAR","editable":true,"required":true},{"field":"affectedOrders","hide":true,"dataType":"text"},{"field":"terms","hide":true,"dataType":"text"},{"field":"activeType","hide":true,"dataType":"text"},{"field":"iwpButton","fieldName":"ocdleglo.document","dataType":"hyperlink","link":"/EOFFENDER","queryparam":["objectId"]}]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDPAROR'
	and grid_name = 'paroleOrders'
	and db_table_name = 'OCDLEGLO_DATA';
	
	
	update
	dynamic_grid_config
set
	config_json = '[{"field":"displayNo","fieldName":"ocdbailo.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"orderedDate","fieldName":"ocdbailo.ordereddate","dataType":"date","required":true},{"field":"matter","fieldName":"ocdbailo.matter","dataType":"text","editable":false},{"field":"court","fieldName":"ocdbailo.court","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdbailo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS","required":true},{"field":"legislation","fieldName":"ocdbailo.legislation","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU"},{"field":"commenceDate","fieldName":"ocdbailo.commencedate","dataType":"date","required":true},{"field":"expiryDate","fieldName":"ocdbailo.expirydate","dataType":"date"},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdleglo.intParties"},{"field":"signaturerequired","fieldName":"ocdbailo.signaturerequired","dataType":"checkbox"},{"field":"status","fieldName":"ocdbailo.status","dataType":"lov","source":"link","sourceType":"OCMSTATS","url":"ocmpconf/rgOrderStatus?orderType=","editable":true,"required":true},{"field":"charges","hide":true,"dataType":"text"},{"field":"activeType","hide":true,"dataType":"text"},{"field":"iwpButton","fieldName":"ocdleglo.document","dataType":"hyperlink","link":"/EOFFENDER","queryparam":["objectId"]}]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDBAILO'
	and grid_name = 'bailOrders'
	and db_table_name = 'OCDLEGLO_DATA';