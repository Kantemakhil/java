insert
	into
	oms_owner.dynamic_grid_config
(module_name,
	config_json,
	grid_name,
	db_table_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
select
		'OIDCUSTAD',
	'[ { "field": "displayNo", "fieldName": "oidcustad.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "type", "fieldName": "oidcustad.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS", "required": true }, { "field": "termTypeAndLength", "fieldName": "oidcustad.termtypeandlength", "dataType": "text", "required": true, "editable": false }, { "field": "commenceType", "fieldName": "oidcustad.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "required": true }, { "field": "relatedTo", "fieldName": "oidcustad.linkto", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "oidcustad.commencedate", "dataType": "date" }, { "field": "erd", "fieldName": "custorder.erd", "dataType": "text", "editable": false },{ "field": "ped", "fieldName": "custorder.ped", "dataType": "text", "editable": false }, { "field": "lrd", "fieldName": "custorder.lrd", "dataType": "text", "editable": false }, { "field": "status", "fieldName": "oidcustad.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=", "editable": true, "required": true }, { "field": "remission", "fieldName": "oidcustad.remission", "dataType": "checkbox", "editable": true }, { "field": "remissionDays", "fieldName": "oidcustad.remissionDays", "dataType": "number", "editable": false } ]' ,
	'custOrd',
		'OCDLEGLO_DATA',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER' 
where
	not exists (
	select
		1
	from
		dynamic_grid_config
	where
		module_name = 'OIDCUSTAD'
		and grid_name = 'custOrd'
		and db_table_name = 'OCDLEGLO_DATA');

update
	dynamic_grid_config
set
	config_json = '[ { "field": "chargeId", "dataType": "number", "editable": false, "hide": true }, { "field": "select", "fieldName": "ocdchgsu.select", "dataType": "checkbox", "editable": false }, { "field": "matter", "fieldName": "ocdchgsu.matter", "dataType": "text", "required": true, "editable": true }, { "field": "description", "fieldName": "ocdchgsu.description", "dataType": "text", "required": true, "editable": false }, { "field": "descriptionLaunch", "dataType": "hyperlink", "displayFields": [ "code", "description", "act", "legislativeBody" ], "parentField": [ "code", "description", "act", "offenceId" ], "lovUrl": "ocmpconf/getOffencesOnStatute?statuteCode=:act", "link": "/multiLov" }, { "field": "code", "fieldName": "ocdchgsu.code", "dataType": "text", "required": false, "editable": false }, { "field": "act", "fieldName": "ocdchgsu.act", "dataType": "lov", "source": "link", "url": "ocmpconf/populateStatutes", "sourceType": "OIMSTATU", "editable": true }, { "field": "type", "fieldName": "ocdchgsu.type", "dataType": "lov", "source": "link", "sourceType": "OIMOFFEN", "parentFields": [ "code", "act" ], "url": "ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act", "editable": true }, { "field": "incidentDate", "dataType": "text", "editable": false, "hide": true }, { "field": "Range", "dataType": "text", "editable": false, "hide": true }, { "field": "plea", "dataType": "text", "editable": false, "hide": true }, { "field": "particulars", "dataType": "text", "editable": false, "hide": true }, { "fieldName": "ocdchgsu.chargeoutcome", "field": "outcome", "dataType": "lov", "source": "link", "url": "ocmpconf/populateOutcome", "sourceType": "OCMORCOD", "editable": false, "hide": false }, { "field": "disposition", "fieldName": "ocdchgsu.disposition", "dataType": "lov", "source": "domain", "url": "OFF_RESULT", "required": false, "editable": false }, { "field": "status", "fieldName": "ocdchgsu.status", "dataType": "lov", "source": "domain", "url": "CHARGE_STS", "required": false, "editable": false }, { "field": "updatedDate", "fieldName": "ocdchgsu.modifieddate", "dataType": "date", "editable": false }, { "field": "modifyUserId", "fieldName": "ocdchgsu.modifyuserid", "dataType": "lov", "source": "link", "url": "ocucalcr/getStaffMembers", "required": false, "editable": false }, { "field": "updateReason", "fieldName": "ocdchgsu.updatereason", "dataType": "lov", "source": "domain", "url": "CHOUTUPREA", "editable": false }, { "field": "outcomeBtn", "dataType": "hyperlink", "link": "/OCUCHGOU" }, { "field": "offenceId", "dataType": "text", "editable": false, "hide": true }, { "fieldName": "ocdchgsu.details", "field": "details", "dataType": "hyperlink", "link": "/OCDCHGDT" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDCHGSU'
	and grid_name = 'charges'
	and db_table_name = 'OCDCHGSU_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "text", "editable": false, "field": "display_no", "fieldName": "ocdsench.displayno" }, { "dataType": "text", "editable": false, "field": "order_no", "fieldName": "ocdsench.orderno", "hide": true }, { "dataType": "text", "editable": false, "field": "sentence_commence_date", "fieldName": "ocdsench.sentenceCommenceDate" }, { "dataType": "lov", "editable": false, "field": "sentence_commence_type", "fieldName": "ocdsench.commenceType", "source": "domain", "url": "LO_REL_TYPE" }, { "dataType": "text", "editable": false, "field": "erd", "fieldName": "ocdsench.erd" }, { "dataType": "text", "editable": false, "field": "erdOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "red", "fieldName": "ocdsench.red" }, { "dataType": "text", "editable": false, "field": "redOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "ped", "fieldName": "ocdsench.ped" }, { "dataType": "text", "editable": false, "field": "pedOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "lrd", "fieldName": "ocdsench.lrd" }, { "dataType": "text", "editable": false, "field": "lrdOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "hed", "fieldName": "ocdsench.holdExpirydate" }, { "dataType": "lov", "editable": false, "field": "status", "fieldName": "ocdsench.status", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=CUST" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDSENCH'
	and grid_name = 'custOrdergrid'
	and db_table_name = 'OCDSENCH_DATA_HTY';

update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "text", "editable": false, "field": "display_no", "fieldName": "ocdsench.displayno" }, { "dataType": "text", "editable": false, "field": "order_no", "fieldName": "ocdsench.orderno", "hide": true }, { "dataType": "lov", "editable": false, "field": "commencetype", "fieldName": "ocdsench.commenceType", "source": "domain", "url": "LO_REL_TYPE" }, { "dataType": "text", "editable": false, "field": "sentence_commence_date", "fieldName": "ocdsench.sentenceCommenceDate" }, { "dataType": "text", "editable": false, "field": "expirydate", "fieldName": "ocdsench.expiry" }, { "dataType": "lov", "editable": false, "field": "status", "fieldName": "ocdsench.status", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDSENCH'
	and grid_name = 'nonCustgrid'
	and db_table_name = 'OCDSENCH_DATA_HTY';
	
update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ocuchgse.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocuchgse.ordereddate", "dataType": "date" }, { "field": "matter", "fieldName": "ocuchgse.matter", "dataType": "text" }, { "field": "type", "fieldName": "ocuchgse.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS" }, { "field": "termTypeAndLength", "fieldName": "ocuchgse.termtypeandlength", "dataType": "text" }, { "field": "commenceDate", "fieldName": "ocuchgse.commencedate", "dataType": "date" }, { "field": "status", "fieldName": "ocuchgse.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus" } ]',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	db_table_name = 'OCDLEGLO_DATA'
	and module_name = 'OCDLEGLO'
	and grid_name = 'related';	
