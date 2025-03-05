update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "checkbox", "editable": true, "field": "select", "fieldName": "ocdchgsu.select", "required": false }, { "field": "chargeId", "dataType": "number", "editable": false, "hide": true }, { "field": "matter", "fieldName": "ocdchgsu.matter", "dataType": "text", "editable": false }, { "field": "description", "fieldName": "ocdchgsu.descriptionchild", "dataType": "text", "editable": false }, { "field": "outcome", "fieldName": "ocdchgsu.outcome", "dataType": "lov", "source": "link", "url": "ocmpconf/populateOutcome", "sourceType": "OCMORCOD", "required": true, "editable": true }, { "field": "type", "fieldName": "ocdchgsu.type", "dataType": "lov", "source": "domain", "url": "OFFENCE_TYPE", "editable": false }, { "field": "incidentDate", "dataType": "text", "editable": false, "hide": true }, { "field": "Range", "dataType": "text", "editable": false, "hide": true }, { "field": "plea", "dataType": "text", "editable": false, "hide": true }, { "field": "particulars", "dataType": "text", "editable": false, "hide": true }, { "fieldName": "ocdchgsu.details", "field": "details", "dataType": "hyperlink", "link": "/OCDCHGDT" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDCORDS'
	and grid_name = 'chargesChild'
	and db_table_name = 'OCDLEGLO_DATA';





update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "checkbox", "editable": true, "field": "select", "fieldName": "ocdchgsu.select", "required": false }, { "field": "chargeId", "dataType": "number", "editable": false, "hide": true }, { "field": "matter", "fieldName": "ocdchgsu.matter", "dataType": "text", "editable": false }, { "field": "description", "fieldName": "ocdchgsu.descriptionchild", "dataType": "text", "editable": false }, { "field": "outcome", "fieldName": "ocdchgsu.outcome", "dataType": "lov", "source": "link", "url": "ocmpconf/populateOutcome", "sourceType": "OCMORCOD", "required": true, "editable": true }, { "field": "type", "fieldName": "ocdchgsu.type", "dataType": "lov", "source": "domain", "url": "OFFENCE_TYPE", "editable": false }, { "field": "incidentDate", "dataType": "text", "editable": false, "hide": true }, { "field": "Range", "dataType": "text", "editable": false, "hide": true }, { "field": "plea", "dataType": "text", "editable": false, "hide": true }, { "field": "particulars", "dataType": "text", "editable": false, "hide": true }, { "fieldName": "ocdchgsu.details", "field": "details", "dataType": "hyperlink", "link": "/OCDCHGDT" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDBAILO'
	and grid_name = 'bailCharges'
	and db_table_name = 'OCDCHGSU_DATA';