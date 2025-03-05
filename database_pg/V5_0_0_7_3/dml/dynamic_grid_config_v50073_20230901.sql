update
	dynamic_grid_config
set
	config_json = '[ { "field": "termType", "fieldName": "termToLine.termtype", "dataType": "lov", "source": "link", "sourceType": "OIMSREQS", "url": "ocmpconf/populateTermType?sentCategory=:orderType&sentType=:sentType", "required": true }, { "field": "years", "fieldName": "termToLine.years", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "months", "fieldName": "termToLine.months", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "weeks", "fieldName": "termToLine.weeks", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1", "restrictCharacters": [ "." ] }, { "field": "days", "fieldName": "termToLine.days", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "indefinite", "fieldName": "termToLine.indefinite", "dataType": "checkbox" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDCORDS'
	and grid_name = 'terms'
	and db_table_name = 'OCDLEGLO_DATA';
	


update
	dynamic_grid_config
set
	config_json = '[ { "field": "termType", "fieldName": "ncusTerm.termtype", "dataType": "lov", "source": "link", "sourceType": "OIMSREQS", "url": "ocmpconf/populateTermType?sentCategory=:orderType&sentType=:sentType", "required": true }, { "field": "years", "fieldName": "ncusTerm.years", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "months", "fieldName": "ncusTerm.months", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "weeks", "fieldName": "ncusTerm.weeks", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1", "restrictCharacters": [ "." ] }, { "field": "days", "fieldName": "ncusTerm.days", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "fixedExpiry", "fieldName": "ncusTerm.fixedExpiry", "dataType": "checkbox" }, { "field": "indefinite", "fieldName": "ncusTerm.indefinite", "dataType": "checkbox" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDNCODE'
	and grid_name = 'nonCustTerm'
	and db_table_name = 'OCDLEGLO_DATA';
	


update
	dynamic_grid_config
set
	config_json = '[ { "field": "termType", "fieldName": "termToLine.termtype", "dataType": "lov", "source": "link", "sourceType": "OIMSREQS", "url": "ocmpconf/populateTermType?sentCategory=:orderType&sentType=:sentType", "required": true, "editable": true }, { "field": "years", "fieldName": "termToLine.years", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "months", "fieldName": "termToLine.months", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "weeks", "fieldName": "termToLine.weeks", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1", "restrictCharacters": [ "." ] }, { "field": "days", "fieldName": "termToLine.days", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "fixedExpiry", "fieldName": "durationToLine.fixedexpiry", "dataType": "checkbox" }, { "field": "indefinite", "fieldName": "termToLine.indefinite", "dataType": "checkbox" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDPAROR'
	and grid_name = 'duration'
	and db_table_name = 'OCDLEGLO_DATA';