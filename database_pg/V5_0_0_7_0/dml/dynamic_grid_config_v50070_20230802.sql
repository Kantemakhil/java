update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "date", "editable": false, "field": "sentDate", "fieldName": "ocdsench.sentDate" }, { "dataType": "time", "editable": false, "field": "sentTime", "fieldName": "ocdsench.sentTime" }, { "dataType": "text", "editable": false, "field": "staffName", "fieldName": "ocdsench.staffName" }, { "dataType": "lov", "editable": false, "field": "calculationReason", "fieldName": "ocdsench.calculationReason", "source": "domain", "url": "CALC_REASON" }, { "dataType": "text", "editable": false, "field": "commentText", "fieldName": "ocdsench.comment" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDSENCH'
	and grid_name = 'calcEventgrid'
	and db_table_name = 'OCDLEGLS_DATA_HTY';