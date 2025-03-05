update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ocdleglo.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocdleglo.ordereddate", "dataType": "date", "required": true }, { "field": "matter", "fieldName": "ocdleglo.matter", "dataType": "text", "editable": false }, { "field": "court", "fieldName": "ocdleglo.court", "dataType": "lov", "source": "link", "url": "ocdccase/populateCourtData", "sourceType": "OUMAGLOC", "required": true }, { "field": "sentenceCalcType", "fieldName": "ocdleglo.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS", "required": true }, { "field": "commenceType", "fieldName": "ocdleglo.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "required": true }, { "field": "relatedTo", "fieldName": "ocdleglo.linkto", "dataType": "text", "editable": false }, { "field": "relatedToLaunch", "dataType": "hyperlink", "link": "/relatedToLine", "parentField": [ "relatedTo" ] }, { "field": "commenceDate", "fieldName": "ocdleglo.commencedate", "dataType": "date" }, { "field": "termTypeAndLength", "fieldName": "ocdleglo.termtypeandlength", "dataType": "text", "required": true, "editable": false }, { "field": "lengthBtn", "dataType": "hyperlink", "link": "/termToLine", "parentField": [ "termTypeAndLength", "terms" ] }, { "field": "status", "fieldName": "ocdleglo.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=", "editable": true, "required": true }, { "field": "intParties", "dataType": "hyperlink", "link": "/OCDINTPA", "fieldName": "ocdleglo.intParties" }, { "field": "terms", "hide": true, "dataType": "text" }, { "field": "charges", "hide": true, "dataType": "text" }, { "field": "orderType", "hide": true, "dataType": "text" }, { "field": "holdExpiryDate", "fieldName": "ocdleglo.holdExpirydate", "dataType": "date" }, { "field": "activeType", "hide": true, "dataType": "text" }, { "field": "iwpButton", "fieldName": "ocdleglo.document", "dataType": "hyperlink", "link": "/EOFFENDER", "queryparam": [ "objectId" ] } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDCORDS'
	and grid_name = 'custOrd'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ocdncode.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocdncode.ordereddate", "dataType": "date", "required": true }, { "field": "matter", "fieldName": "ocdncode.matter", "dataType": "text", "editable": false }, { "field": "court", "fieldName": "ocdncode.court", "dataType": "lov", "source": "link", "url": "ocdccase/populateCourtData", "sourceType": "OUMAGLOC", "required": true }, { "field": "sentenceCalcType", "fieldName": "ocdncode.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS", "required": true }, { "field": "commenceType", "fieldName": "ocdncode.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "required": true }, { "field": "relatedTo", "fieldName": "ocdncode.linkto", "dataType": "text", "editable": false }, { "field": "relatedToLaunch", "dataType": "hyperlink", "link": "/nonCustRelated", "parentField": [ "relatedTo" ] }, { "field": "commenceDate", "fieldName": "ocdncode.commencedate", "dataType": "date" }, { "field": "termTypeAndLength", "fieldName": "ocdncode.termtypeandlength", "dataType": "text", "required": true, "editable": false }, { "field": "lengthBtn", "dataType": "hyperlink", "link": "/nonCustDuration", "parentField": [ "termTypeAndLength", "terms" ] }, { "field": "expiryDate", "fieldName": "ocdncode.expiryDate", "dataType": "date", "editable": true }, { "field": "status", "fieldName": "ocdncode.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=", "editable": true, "required": true }, { "field": "intParties", "dataType": "hyperlink", "link": "/OCDINTPA", "fieldName": "ocdncode.intparties" }, { "field": "terms", "hide": true, "dataType": "text" }, { "field": "orderType", "hide": true, "dataType": "text" }, { "field": "charges", "hide": true, "dataType": "text" }, { "field": "activeType", "hide": true, "dataType": "text" }, { "field": "iwpButton", "fieldName": "ocdleglo.document", "dataType": "hyperlink", "link": "/EOFFENDER", "queryparam": [ "objectId" ] } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDNCODE'
	and grid_name = 'nonCustOrd'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ocdleglo.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "hearingDate", "fieldName": "ocdparor.hearingdate", "dataType": "date", "required": true }, { "field": "authority", "fieldName": "ocdparor.authority", "dataType": "lov", "source": "link", "url": "ocdccase/populateCourtData", "sourceType": "OUMAGLOC", "required": true }, { "field": "sentenceCalcType", "fieldName": "ocdleglo.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=PAR", "sourceType": "OIMSREQS", "required": true }, { "field": "commenceType", "fieldName": "ocdleglo.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "required": true, "editable": false }, { "field": "duration", "fieldName": "ocdparor.duration", "dataType": "text", "required": true, "editable": false }, { "field": "lengthBtn", "dataType": "hyperlink", "link": "/durationToLine", "parentField": [ "duration", "terms" ] }, { "field": "commenceDate", "fieldName": "ocdleglo.commencedate", "dataType": "date", "required": true }, { "field": "expiryDate", "fieldName": "ocdparor.expirydate", "dataType": "date" }, { "field": "intParties", "dataType": "hyperlink", "link": "/OCDINTPA", "fieldName": "ocdleglo.intParties" }, { "field": "status", "fieldName": "ocdleglo.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=PAR", "editable": true, "required": true }, { "field": "affectedOrders", "hide": true, "dataType": "text" }, { "field": "terms", "hide": true, "dataType": "text" }, { "field": "activeType", "hide": true, "dataType": "text" }, { "field": "iwpButton", "fieldName": "ocdleglo.document", "dataType": "hyperlink", "link": "/EOFFENDER", "queryparam": [ "objectId" ] } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDPAROR'
	and grid_name = 'paroleOrders'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ocdbailo.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocdbailo.ordereddate", "dataType": "date", "required": true }, { "field": "matter", "fieldName": "ocdbailo.matter", "dataType": "text", "editable": false }, { "field": "court", "fieldName": "ocdbailo.court", "dataType": "lov", "source": "link", "url": "ocdccase/populateCourtData", "sourceType": "OUMAGLOC", "required": true }, { "field": "sentenceCalcType", "fieldName": "ocdbailo.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS", "required": true }, { "field": "legislation", "fieldName": "ocdbailo.legislation", "dataType": "lov", "source": "link", "url": "ocmpconf/populateStatutes", "sourceType": "OIMSTATU" }, { "field": "commenceDate", "fieldName": "ocdbailo.commencedate", "dataType": "date", "required": true }, { "field": "expiryDate", "fieldName": "ocdbailo.expirydate", "dataType": "date" }, { "field": "intParties", "dataType": "hyperlink", "link": "/OCDINTPA", "fieldName": "ocdleglo.intParties" }, { "field": "signaturerequired", "fieldName": "ocdbailo.signaturerequired", "dataType": "checkbox" }, { "field": "status", "fieldName": "ocdbailo.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=", "editable": true, "required": true }, { "field": "charges", "hide": true, "dataType": "text" }, { "field": "activeType", "hide": true, "dataType": "text" }, { "field": "iwpButton", "fieldName": "ocdleglo.document", "dataType": "hyperlink", "link": "/EOFFENDER", "queryparam": [ "objectId" ] } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDBAILO'
	and grid_name = 'bailOrders'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "select", "fieldName": "ocdparor.select", "dataType": "checkbox", "editable": true }, { "field": "displayNo", "fieldName": "custorder.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "sentenceCalcType", "fieldName": "custorder.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=CUST", "sourceType": "OIMSREQS", "editable": false }, { "field": "termTypeAndLength", "fieldName": "custorder.termtypeandlength", "dataType": "text", "editable": false }, { "field": "relatedTo", "fieldName": "custorder.linkto", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "custorder.commencedate", "dataType": "text", "editable": false }, { "field": "erd", "fieldName": "custorder.erd", "dataType": "text", "editable": false }, { "field": "ped", "fieldName": "custorder.ped", "dataType": "text", "editable": false }, { "field": "lrd", "fieldName": "custorder.lrd", "dataType": "text", "editable": false }, { "field": "status", "fieldName": "custorder.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=CUST", "editable": false } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDPAROR'
	and grid_name = 'affectedOrders'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "text", "editable": false, "field": "dateTypeDisplay", "fieldName": "ocdsench.datetype" }, { "dataType": "date", "editable": false, "field": "dateValue", "fieldName": "ocdsench.value" }, { "dataType": "custom", "editable": false, "field": "indefinite", "fieldName": "ocdsench.indefinite" }, { "dataType": "date", "editable": false, "field": "overrideDateValue", "fieldName": "ocdsench.valueoverride" }, { "dataType": "checkbox", "editable": false, "field": "overrideIndefinite", "fieldName": "ocdsench.indefiniteoverride" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDSENCH'
	and grid_name = 'keyDategrid'
	and db_table_name = 'OCDLEGLS_DATA_HTY';

update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "text", "editable": false, "field": "displayNo", "fieldName": "ocdsench.displayno" }, { "dataType": "text", "editable": false, "field": "orderNo", "fieldName": "ocdsench.orderno", "hide": true }, { "dataType": "lov", "editable": false, "field": "type", "fieldName": "ocdsench.commenceType", "source": "link", "sourceType": "OIMSREQS", "parentField": "sentenceOrderType", "url": "ocmpconf/populateSentType?sentCategory=" }, { "dataType": "text", "editable": false, "field": "sentenceCommenceDate", "fieldName": "ocdsench.sentenceCommenceDate" }, { "dataType": "text", "editable": false, "field": "expirydate", "fieldName": "ocdsench.expiry" }, { "dataType": "lov", "editable": false, "field": "status", "fieldName": "ocdsench.status", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus" }, { "field": "sentenceOrderType", "hide": true, "dataType": "text" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDSENCH'
	and grid_name = 'nonCustgrid'
	and db_table_name = 'OCDSENCH_DATA_HTY';

update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "text", "editable": false, "field": "displayNo", "fieldName": "ocdsench.displayno" }, { "dataType": "text", "editable": false, "field": "orderNo", "fieldName": "ocdsench.orderno", "hide": true }, { "dataType": "lov", "editable": false, "field": "sentenceCommenceType", "fieldName": "ocdsench.commenceType", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS" }, { "dataType": "date", "editable": false, "field": "sentenceCommenceDate", "fieldName": "ocdsench.sentenceCommenceDate" }, { "dataType": "date", "editable": false, "field": "sentenceExpiryDate", "fieldName": "ocdsench.expiry" }, { "dataType": "lov", "editable": false, "field": "status", "fieldName": "ocdsench.status", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=BAIL" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDSENCH'
	and grid_name = 'bailGrid'
	and db_table_name = 'OCDSENCH_DATA_HTY';

update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "text", "editable": false, "field": "displayNo", "fieldName": "ocdsench.displayno" }, { "dataType": "text", "editable": false, "field": "orderNo", "fieldName": "ocdsench.orderno", "hide": true }, { "dataType": "text", "editable": false, "field": "sentenceCommenceDate", "fieldName": "ocdsench.sentenceCommenceDate" }, { "dataType": "lov", "editable": false, "field": "sentenceCalcType", "fieldName": "ocdsench.commenceType", "source": "link", "sourceType": "OIMSREQS", "url": "ocmpconf/populateSentType?sentCategory=CUST" }, { "dataType": "text", "editable": false, "field": "erd", "fieldName": "ocdsench.erd" }, { "dataType": "text", "editable": false, "field": "erdOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "red", "fieldName": "ocdsench.red" }, { "dataType": "text", "editable": false, "field": "redOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "ped", "fieldName": "ocdsench.ped" }, { "dataType": "text", "editable": false, "field": "pedOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "lrd", "fieldName": "ocdsench.lrd" }, { "dataType": "text", "editable": false, "field": "lrdOverride", "fieldName": "ocdsench.override" }, { "dataType": "text", "editable": false, "field": "hed", "fieldName": "ocdsench.holdExpirydate" }, { "dataType": "lov", "editable": false, "field": "status", "fieldName": "ocdsench.status", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDSENCH'
	and grid_name = 'custOrdergrid'
	and db_table_name = 'OCDSENCH_DATA_HTY';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "dateTypeDisplay", "dataType": "text", "fieldName": "ocdlegls.datetype", "editable": false }, { "field": "dateValue", "dataType": "date", "fieldName": "ocdlegls.value", "editable": false }, { "field": "indefinite", "dataType": "custom", "fieldName": "ocdlegls.indefinite", "editable": false }, { "field": "overrideDateValue", "dataType": "date", "fieldName": "ocdlegls.valueoverride", "editable": false }, { "field": "overrideIndefinite", "dataType": "checkbox", "fieldName": "ocdlegls.indefiniteoverride", "editable": false }, { "field": "clearBtn", "dataType": "hyperlink", "fieldName": "", "editable": true } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDLEGLS'
	and grid_name = 'keyDateSummary'
	and db_table_name = 'OCDLEGLS_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "dateTypeDisplay", "dataType": "text", "fieldName": "ocdlegls.datetype", "editable": false }, { "field": "dateValue", "dataType": "date", "fieldName": "ocdlegls.value", "editable": false }, { "field": "indefinite", "dataType": "custom", "fieldName": "ocdlegls.indefinite", "editable": false } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDLEGLS'
	and grid_name = 'additionalKeyDatesGrid'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ocdenfor.no", "dataType": "text", "editable": false }, { "field": "no", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocdenfor.ordereddate", "dataType": "date", "required": true, "editable": false }, { "field": "matter", "fieldName": "ocdenfor.matter", "dataType": "text", "editable": false }, { "field": "court", "fieldName": "ocdenfor.court", "dataType": "lov", "source": "link", "url": "ocdccase/populateCourtData", "sourceType": "OUMAGLOC", "required": true, "editable": false }, { "field": "sentenceCalcType", "fieldName": "ocdenfor.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "parentField": "orderType", "sourceType": "OIMSREQS", "required": true, "editable": false }, { "field": "commenceType", "fieldName": "ocdenfor.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "required": true, "editable": false }, { "field": "relatedTo", "fieldName": "ocdenfor.linkto", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "ocdenfor.commencedate", "dataType": "date", "editable": false }, { "field": "termTypeAndLength", "fieldName": "ocdenfor.duration", "dataType": "text", "required": true, "editable": false }, { "field": "expiryDate", "fieldName": "ocdenfor.expiryDate", "dataType": "date", "editable": false }, { "field": "status", "fieldName": "ocdenfor.status", "dataType": "lov", "source": "link", "url": "ocmpconf/rgOrderStatus", "sourceType": "OCMSTATS" }, { "field": "orderType", "hide": true, "dataType": "text" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDENFOR'
	and grid_name = 'crtOrders'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "orderNo", "fieldName": "ocdleglo.no", "dataType": "number", "editable": false }, { "field": "sentenceCalcType", "fieldName": "ocdleglo.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=NCUS", "sourceType": "OIMSREQS", "required": true }, { "field": "matter", "fieldName": "ocdleglo.matter", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "ocdleglo.commencedate", "dataType": "date" }, { "field": "expiryDate", "fieldName": "Expiry Date", "dataType": "date" }, { "field": "sanctionCount", "fieldName": "Sanction Count", "dataType": "number", "editable": false }, { "field": "adjournedCount", "fieldName": "Adjourned Sanctions", "dataType": "number", "minValue": "0", "maxValue": "999999", "whole": "true" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OSANVIOS'
	and grid_name = 'sanctions'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ocuchgse.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocuchgse.ordereddate", "dataType": "date" }, { "field": "matter", "fieldName": "ocuchgse.matter", "dataType": "text" }, { "field": "sentenceCalcType", "fieldName": "ocuchgse.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS" }, { "field": "termTypeAndLength", "fieldName": "ocuchgse.termtypeandlength", "dataType": "text" }, { "field": "commenceDate", "fieldName": "ocuchgse.commencedate", "dataType": "date" }, { "field": "status", "fieldName": "ocuchgse.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDCORDS'
	and grid_name = 'related'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "dataType": "text", "editable": false, "field": "displayNo", "fieldName": "ocdsench.displayno" }, { "dataType": "text", "editable": false, "field": "orderNo", "fieldName": "ocdsench.orderno", "hide": true }, { "dataType": "lov", "editable": false, "field": "sentenceCalcType", "fieldName": "ocdsench.commenceType", "source": "link", "sourceType": "OIMSREQS", "parentField": "sentenceOrderType", "url": "ocmpconf/populateSentType?sentCategory=" }, { "dataType": "text", "editable": false, "field": "sentenceCommenceDate", "fieldName": "ocdsench.sentenceCommenceDate" }, { "dataType": "text", "editable": false, "field": "expirydate", "fieldName": "ocdsench.expiry" }, { "dataType": "lov", "editable": false, "field": "status", "fieldName": "ocdsench.status", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus" }, { "field": "sentenceOrderType", "hide": true, "dataType": "text" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDSENCH'
	and grid_name = 'nonCustgrid'
	and db_table_name = 'OCDSENCH_DATA_HTY';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "nonCustRelated.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "nonCustRelated.ordereddate", "dataType": "date" }, { "field": "matter", "fieldName": "nonCustRelated.matter", "dataType": "text" }, { "field": "sentenceCalcType", "fieldName": "nonCustRelated.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS" }, { "field": "termTypeAndLength", "fieldName": "nonCustRelated.termtypeandlength", "dataType": "text" }, { "field": "commenceDate", "fieldName": "nonCustRelated.commencedate", "dataType": "date" }, { "field": "status", "fieldName": "nonCustRelated.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDNCODE'
	and grid_name = 'nonCustRelated'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "oidcustad.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "sentenceCalcType", "fieldName": "oidcustad.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS", "required": true }, { "field": "termTypeAndLength", "fieldName": "oidcustad.termtypeandlength", "dataType": "text", "required": true, "editable": false }, { "field": "commenceType", "fieldName": "oidcustad.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "required": true }, { "field": "relatedTo", "fieldName": "oidcustad.linkto", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "oidcustad.commencedate", "dataType": "date" }, { "field": "erd", "fieldName": "custorder.erd", "dataType": "text", "editable": false }, { "field": "ped", "fieldName": "custorder.ped", "dataType": "text", "editable": false }, { "field": "lrd", "fieldName": "custorder.lrd", "dataType": "text", "editable": false }, { "field": "status", "fieldName": "oidcustad.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=", "editable": true, "required": true }, { "field": "remission", "fieldName": "oidcustad.remission", "dataType": "checkbox", "editable": true }, { "field": "remissionDays", "fieldName": "oidcustad.remissionDays", "dataType": "number", "editable": false } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OIDCUSTAD'
	and grid_name = 'custOrd'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "custorder.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "sentenceCalcType", "fieldName": "custorder.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=CUST", "sourceType": "OIMSREQS", "editable": false }, { "field": "termTypeAndLength", "fieldName": "custorder.termtypeandlength", "dataType": "text", "editable": false }, { "field": "opTerm", "fieldName": "ocdlegls.operativeheading", "dataType": "text", "editable": false }, { "field": "commenceType", "fieldName": "custorder.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "editable": false }, { "field": "relatedTo", "fieldName": "custorder.linkto", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "custorder.commencedate", "dataType": "text", "editable": false }, { "field": "erd", "fieldName": "custorder.erd", "dataType": "text", "editable": false }, { "field": "red", "fieldName": "custorder.red", "dataType": "text", "editable": false }, { "field": "ped", "fieldName": "custorder.ped", "dataType": "text", "editable": false }, { "field": "lrd", "fieldName": "custorder.lrd", "dataType": "text", "editable": false }, { "field": "hed", "fieldName": "ocdleglo.holdExpirydate", "dataType": "text", "editable": false }, { "field": "overridebtn", "fieldName": "ocdleglo.overridelaunch", "dataType": "hyperlink", "editable": false }, { "field": "overrides", "fieldName": "", "dataType": "text", "editable": false, "hide": true }, { "field": "sentOverrideCheck", "dataType": "checkbox", "fieldName": "ocdlegls.overrideCheck", "editable": false }, { "field": "status", "fieldName": "custorder.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=CUST", "editable": false }, { "field": "orderType", "hide": true, "dataType": "text", "editable": false } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDLEGLS'
	and grid_name = 'custOrders'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ocdbailo.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "sentenceCalcType", "fieldName": "ocdbailo.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS", "editable": false }, { "field": "legislation", "fieldName": "ocdbailo.legislation", "dataType": "lov", "source": "link", "url": "ocmpconf/populateStatutes", "sourceType": "OIMSTATU", "editable": false }, { "field": "commenceDate", "fieldName": "ocdbailo.commencedate", "dataType": "date", "editable": false }, { "field": "expiryDate", "fieldName": "ocdbailo.expirydate", "dataType": "date", "editable": false }, { "field": "signaturerequired", "fieldName": "ocdbailo.signaturerequired", "dataType": "checkbox", "editable": false }, { "field": "status", "fieldName": "ocdbailo.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=BAIL", "editable": false } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDLEGLS'
	and grid_name = 'bailGrid'
	and db_table_name = 'OCDLEGLO_DATA';

update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ncustorder.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "sentenceCalcType", "fieldName": "ncustorder.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateCatSentType?sentCategory=NCUS_PAR", "sourceType": "OIMSREQS", "editable": false }, { "field": "termTypeAndLength", "fieldName": "ncustorder.termtypeandlength", "dataType": "text", "editable": false }, { "field": "commenceType", "fieldName": "ncustorder.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "editable": false }, { "field": "relatedTo", "fieldName": "ncustorder.linkto", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "ncustorder.commencedate", "dataType": "text", "editable": false }, { "field": "expiryDate", "fieldName": "ncustorder.expiryDate", "dataType": "text", "editable": true }, { "field": "status", "fieldName": "ncustorder.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus", "editable": false }, { "field": "orderType", "hide": true, "dataType": "text", "editable": false } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDLEGLS'
	and grid_name = 'nCustOrders'
	and db_table_name = 'OCDLEGLO_DATA';