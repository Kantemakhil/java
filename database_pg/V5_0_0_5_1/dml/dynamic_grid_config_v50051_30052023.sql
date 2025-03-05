--update
--	dynamic_grid_config
--set
--	config_json = '[ { "field": "chargeId", "dataType": "number", "editable": false, "hide": true }, { "field": "select", "fieldName": "ocdchgsu.select", "dataType": "checkbox", "editable": false }, { "field": "matter", "fieldName": "ocdchgsu.matter", "dataType": "text", "required": true, "editable": true }, { "field": "description", "fieldName": "ocdchgsu.description", "dataType": "text", "required": true, "editable": false }, { "field": "descriptionLaunch", "dataType": "hyperlink", "displayFields": [ "code", "description", "act", "legislativeBody" ], "parentField": [ "code", "description", "act", "offenceId" ], "lovUrl": "ocmpconf/getOffencesOnStatute?statuteCode=:act", "link": "/multiLov" }, { "field": "code", "fieldName": "ocdchgsu.code", "dataType": "text", "required": false, "editable": false }, { "field": "act", "fieldName": "ocdchgsu.act", "dataType": "lov", "source": "link", "url": "ocmpconf/populateStatutes", "sourceType": "OIMSTATU", "editable": true }, { "field": "type", "fieldName": "ocdchgsu.type", "dataType": "lov", "source": "link", "sourceType": "OIMOFFEN", "parentFields": [ "code", "act" ], "url": "ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act", "editable": true }, { "field": "incidentDate", "dataType": "text", "editable": false, "hide": true }, { "field": "Range", "dataType": "text", "editable": false, "hide": true }, { "field": "plea", "dataType": "text", "editable": false, "hide": true }, { "field": "particulars", "dataType": "text", "editable": false, "hide": true }, { "fieldName": "ocdchgsu.chargeoutcome", "field": "outcome", "dataType": "lov", "source": "link", "url": "ocmpconf/populateOutcome", "sourceType": "OCMORCOD", "editable": false, "hide": false }, { "field": "disposition", "fieldName": "ocdchgsu.disposition", "dataType": "lov", "source": "domain", "url": "OFF_RESULT", "required": false, "editable": false }, { "field": "status", "fieldName": "ocdchgsu.status", "dataType": "lov", "source": "domain", "url": "CHARGE_STS", "required": false, "editable": false }, { "field": "updatedDate", "fieldName": "ocdchgsu.updatedDate", "dataType": "date", "editable": false }, { "field": "userId", "fieldName": "ocdchgsu.modifyuserid", "dataType": "text", "editable": false }, { "field": "updateReason", "fieldName": "ocdchgsu.updatereason", "dataType": "lov", "source": "domain", "url": "CHOUTUPREA", "required": false, "editable": true }, { "field": "outcomeBtn", "dataType": "hyperlink", "link": "/OCUCHGOU" }, { "fieldName": "ocdchgsu.details", "field": "details", "dataType": "hyperlink", "link": "/OCDCHGDT" }, { "field": "offenceId", "dataType": "text", "editable": false, "hide": true }]',
--	modify_datetime = current_timestamp ,
--	modify_user_id = 'OMS_OWNER' 
--where
--	module_name = 'OCDCHGSU' and grid_name = 'charges' and db_table_name='OCDCHGSU_DATA';

	

update
	dynamic_grid_config
set
	config_json = '[ { "field": "chargeId", "dataType": "number", "editable": false, "hide": true }, { "field": "select", "fieldName": "ocdchgsu.select", "dataType": "checkbox", "editable": false }, { "field": "matter", "fieldName": "ocdchgsu.matter", "dataType": "text", "required": true, "editable": true }, { "field": "description", "fieldName": "ocdchgsu.description", "dataType": "text", "required": true, "editable": false }, { "field": "descriptionLaunch", "dataType": "hyperlink", "displayFields": [ "code", "description", "act", "legislativeBody" ], "parentField": [ "code", "description", "act", "offenceId" ], "lovUrl": "ocmpconf/getOffencesOnStatute?statuteCode=:act", "link": "/multiLov" }, { "field": "code", "fieldName": "ocdchgsu.code", "dataType": "text", "required": false, "editable": false }, { "field": "act", "fieldName": "ocdchgsu.act", "dataType": "lov", "source": "link", "url": "ocmpconf/populateStatutes", "sourceType": "OIMSTATU", "editable": true }, { "field": "type", "fieldName": "ocdchgsu.type", "dataType": "lov", "source": "link", "sourceType": "OIMOFFEN", "parentFields": [ "code", "act" ], "url": "ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act", "editable": true }, { "field": "incidentDate", "dataType": "text", "editable": false, "hide": true }, { "field": "Range", "dataType": "text", "editable": false, "hide": true }, { "field": "plea", "dataType": "text", "editable": false, "hide": true }, { "field": "particulars", "dataType": "text", "editable": false, "hide": true }, { "fieldName": "ocdchgsu.chargeoutcome", "field": "outcome", "dataType": "lov", "source": "link", "url": "ocmpconf/populateOutcome", "sourceType": "OCMORCOD", "editable": false, "hide": false }, { "field": "outcomeBtn", "dataType": "hyperlink", "link": "/OCUCHGOU" }, { "fieldName": "ocdchgsu.details", "field": "details", "dataType": "hyperlink", "link": "/OCDCHGDT" }, { "field": "offenceId", "dataType": "text", "editable": false, "hide": true } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	module_name = 'OCDCHGSU' and grid_name = 'charges' and db_table_name='OCDCHGSU_DATA';


update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "custorder.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "type", "fieldName": "custorder.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=CUST", "sourceType": "OIMSREQS", "editable": false }, { "field": "termTypeAndLength", "fieldName": "custorder.termtypeandlength", "dataType": "text", "editable": false }, { "field": "opTerm", "fieldName": "ocdlegls.operativeheading", "dataType": "text", "editable": false }, { "field": "commenceType", "fieldName": "custorder.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "editable": false }, { "field": "relatedTo", "fieldName": "custorder.linkto", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "custorder.commencedate", "dataType": "text", "editable": false }, { "field": "erd", "fieldName": "custorder.erd", "dataType": "text", "editable": false }, { "field": "red", "fieldName": "custorder.red", "dataType": "text", "editable": false }, { "field": "ped", "fieldName": "custorder.ped", "dataType": "text", "editable": false }, { "field": "lrd", "fieldName": "custorder.lrd", "dataType": "text", "editable": false }, { "field": "hed", "fieldName": "ocdleglo.holdExpirydate", "dataType": "text", "editable": false }, { "field": "overridebtn", "fieldName": "ocdleglo.overridelaunch", "dataType": "hyperlink", "editable": false }, { "field": "overrides", "fieldName": "", "dataType": "text", "editable": false, "hide": true }, { "field": "sentOverrideCheck", "dataType": "checkbox", "fieldName": "ocdlegls.overrideCheck", "editable": false }, { "field": "status", "fieldName": "custorder.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=CUST", "editable": false }, { "field": "orderType", "hide": true, "dataType": "text", "editable": false } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	module_name = 'OCDLEGLS' and grid_name = 'custOrders' and db_table_name='OCDLEGLO_DATA';
	
	
update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "text", "editable": false, "field": "display_no", "fieldName": "ocdsench.displayno" }, { "dataType": "text", "editable": false, "field": "order_no", "fieldName": "ocdsench.orderno", "hide": true }, { "dataType": "lov", "editable": false, "field": "type", "fieldName": "ocdsench.commenceType", "source": "link", "sourceType": "OIMSREQS", "parentField": "sentence_order_type", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS" }, { "dataType": "text", "editable": false, "field": "sentence_commence_date", "fieldName": "ocdsench.sentenceCommenceDate" }, { "dataType": "text", "editable": false, "field": "expirydate", "fieldName": "ocdsench.expiry" }, { "dataType": "lov", "editable": false, "field": "status", "fieldName": "ocdsench.status", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus" }, { "field": "sentence_order_type", "hide": true,"dataType": "text" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDSENCH'
	and grid_name = 'nonCustgrid'
	and db_table_name = 'OCDSENCH_DATA_HTY';

update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "text", "editable": false, "field": "display_no", "fieldName": "ocdsench.displayno" }, { "dataType": "text", "editable": false, "field": "order_no", "fieldName": "ocdsench.orderno", "hide": true }, { "dataType": "text", "editable": false, "field": "sentence_commence_date", "fieldName": "ocdsench.sentenceCommenceDate" }, { "dataType": "lov", "editable": false, "field": "type", "fieldName": "ocdsench.commenceType", "source": "link", "sourceType": "OIMSREQS", "url": "ocmpconf/populateSentType?sentCategory=CUST", "sourceType": "OIMSREQS" }, { "dataType": "text", "editable": false, "field": "erd", "fieldName": "ocdsench.erd" }, { "dataType": "text", "editable": false, "field": "erdOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "red", "fieldName": "ocdsench.red" }, { "dataType": "text", "editable": false, "field": "redOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "ped", "fieldName": "ocdsench.ped" }, { "dataType": "text", "editable": false, "field": "pedOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "lrd", "fieldName": "ocdsench.lrd" }, { "dataType": "text", "editable": false, "field": "lrdOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "hed", "fieldName": "ocdsench.holdExpirydate" }, { "dataType": "lov", "editable": false, "field": "status", "fieldName": "ocdsench.status", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDSENCH'
	and grid_name = 'custOrdergrid'
	and db_table_name = 'OCDSENCH_DATA_HTY';	

	update
	dynamic_grid_config 
set
	module_name = 'OCDCORDS',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDLEGLO';	
	
	
update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ocdenfor.no", "dataType": "text", "editable": false }, { "field": "no", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocdenfor.ordereddate", "dataType": "date", "required": true, "editable": false }, { "field": "matter", "fieldName": "ocdenfor.matter", "dataType": "text", "editable": false }, { "field": "court", "fieldName": "ocdenfor.court", "dataType": "lov", "source": "link", "url": "ocdccase/populateCourtData", "sourceType": "OUMAGLOC", "required": true, "editable": false }, { "field": "type", "fieldName": "ocdenfor.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "parentField":"orderType", "sourceType": "OIMSREQS", "required": true, "editable": false }, { "field": "commenceType", "fieldName": "ocdenfor.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "required": true, "editable": false }, { "field": "relatedTo", "fieldName": "ocdenfor.linkto", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "ocdenfor.commencedate", "dataType": "date", "editable": false }, { "field": "termTypeAndLength", "fieldName": "ocdenfor.duration", "dataType": "text", "required": true, "editable": false }, { "field": "expiryDate", "fieldName": "ocdenfor.expiryDate", "dataType": "date", "editable": false }, { "field": "status", "fieldName": "ocdenfor.status", "dataType": "lov", "source": "link", "url": "ocmpconf/rgOrderStatus", "sourceType": "OCMSTATS" }, { "field": "orderType", "hide": true, "dataType": "text" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	module_name = 'OCDENFOR' and  db_table_name='OCDLEGLO_DATA';