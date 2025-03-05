UPDATE
	dynamic_grid_config
SET
	config_json = '[ { "field": "displayNo", "fieldName": "ncustorder.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "type", "fieldName": "ncustorder.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateCatSentType?sentCategory=NCUS_PAR", "sourceType": "OIMSREQS", "editable": false }, { "field": "termTypeAndLength", "fieldName": "ncustorder.termtypeandlength", "dataType": "text", "editable": false }, { "field": "commenceType", "fieldName": "ncustorder.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "editable": false }, { "field": "relatedTo", "fieldName": "ncustorder.linkto", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "ncustorder.commencedate", "dataType": "text", "editable": false }, { "field": "expiryDate", "fieldName": "ncustorder.expiryDate", "dataType": "text", "editable": true }, { "field": "status", "fieldName": "ncustorder.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus", "editable": false }, { "field": "orderType", "hide": true, "dataType": "text", "editable": false } ]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	module_name = 'OCDLEGLS'
	AND grid_name = 'nCustOrders' and db_table_name='OCDLEGLO_DATA';

UPDATE
	dynamic_grid_config
SET
	config_json = '[ { "field": "displayNo", "fieldName": "ocdleglo.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocdleglo.ordereddate", "dataType": "date", "required": true }, { "field": "matter", "fieldName": "ocdleglo.matter", "dataType": "text", "editable": false }, { "field": "court", "fieldName": "ocdleglo.court", "dataType": "lov", "source": "link", "url": "ocdccase/populateCourtData", "sourceType": "OUMAGLOC", "required": true }, { "field": "type", "fieldName": "ocdleglo.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS", "required": true }, { "field": "commenceType", "fieldName": "ocdleglo.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "required": true }, { "field": "relatedTo", "fieldName": "ocdleglo.linkto", "dataType": "text", "editable": false }, { "field": "relatedToLaunch", "dataType": "hyperlink", "link": "/relatedToLine", "parentField": [ "relatedTo" ] }, { "field": "commenceDate", "fieldName": "ocdleglo.commencedate", "dataType": "date" }, { "field": "termTypeAndLength", "fieldName": "ocdleglo.termtypeandlength", "dataType": "text", "required": true, "editable": false }, { "field": "lengthBtn", "dataType": "hyperlink", "link": "/termToLine", "parentField": [ "termTypeAndLength", "terms" ] }, { "field": "status", "fieldName": "ocdleglo.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=", "editable": true , "required": true}, { "field": "intParties", "dataType": "hyperlink", "link": "/OCDINTPA", "fieldName": "ocdleglo.intParties" }, { "field": "terms", "hide": true, "dataType": "text" }, { "field": "charges", "hide": true, "dataType": "text" }, { "field": "orderType", "hide": true, "dataType": "text" }, { "field": "holdExpiryDate", "fieldName": "ocdleglo.holdExpirydate", "dataType": "date" }, { "field": "activeType", "hide": true, "dataType": "text" } ]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	module_name = 'OCDLEGLO'
	AND grid_name = 'custOrd' and db_table_name='OCDLEGLO_DATA';

UPDATE
	dynamic_grid_config
SET
	config_json = '[ { "field": "displayNo", "fieldName": "ocdncode.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocdncode.ordereddate", "dataType": "date", "required": true }, { "field": "matter", "fieldName": "ocdncode.matter", "dataType": "text", "editable": false }, { "field": "court", "fieldName": "ocdncode.court", "dataType": "lov", "source": "link", "url": "ocdccase/populateCourtData", "sourceType": "OUMAGLOC", "required": true }, { "field": "type", "fieldName": "ocdncode.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS", "required": true }, { "field": "commenceType", "fieldName": "ocdncode.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "required": true }, { "field": "relatedTo", "fieldName": "ocdncode.linkto", "dataType": "text", "editable": false }, { "field": "relatedToLaunch", "dataType": "hyperlink", "link": "/nonCustRelated", "parentField": [ "relatedTo" ] }, { "field": "commenceDate", "fieldName": "ocdncode.commencedate", "dataType": "date" }, { "field": "termTypeAndLength", "fieldName": "ocdncode.termtypeandlength", "dataType": "text", "required": true, "editable": false }, { "field": "lengthBtn", "dataType": "hyperlink", "link": "/nonCustDuration", "parentField": [ "termTypeAndLength", "terms" ] }, { "field": "expiryDate", "fieldName": "ocdncode.expiryDate", "dataType": "date", "editable": true }, { "field": "status", "fieldName": "ocdncode.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=", "editable": true, "required": true }, { "field": "intParties", "dataType": "hyperlink", "link": "/OCDINTPA", "fieldName": "ocdncode.intparties" }, { "field": "terms", "hide": true, "dataType": "text" }, { "field": "orderType", "hide": true, "dataType": "text" }, { "field": "charges", "hide": true, "dataType": "text" }, { "field": "activeType", "hide": true, "dataType": "text" } ]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	module_name = 'OCDNCODE'
	AND grid_name = 'nonCustOrd' and db_table_name='OCDLEGLO_DATA';

UPDATE
	dynamic_grid_config
SET
	config_json = '[ { "field": "displayNo", "fieldName": "ocdleglo.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "hearingDate", "fieldName": "ocdparor.hearingdate", "dataType": "date", "required": true }, { "field": "authority", "fieldName": "ocdparor.authority", "dataType": "lov", "source": "link", "url": "ocdccase/populateCourtData", "sourceType": "OUMAGLOC", "required": true }, { "field": "type", "fieldName": "ocdleglo.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=PAR", "sourceType": "OIMSREQS", "required": true }, { "field": "commenceType", "fieldName": "ocdleglo.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "required": true, "editable": false }, { "field": "duration", "fieldName": "ocdparor.duration", "dataType": "text", "required": true, "editable": false }, { "field": "lengthBtn", "dataType": "hyperlink", "link": "/durationToLine", "parentField": [ "duration", "terms" ] }, { "field": "commenceDate", "fieldName": "ocdleglo.commencedate", "dataType": "date", "required": true }, { "field": "expiryDate", "fieldName": "ocdparor.expirydate", "dataType": "date" }, { "field": "intParties", "dataType": "hyperlink", "link": "/OCDINTPA", "fieldName": "ocdleglo.intParties" }, { "field": "status", "fieldName": "ocdleglo.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=PAR", "editable": true, "required": true }, { "field": "affectedOrders", "hide": true, "dataType": "text" }, { "field": "terms", "hide": true, "dataType": "text" }, { "field": "activeType", "hide": true, "dataType": "text" } ] ',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	module_name = 'OCDPAROR'
	AND grid_name = 'paroleOrders' and db_table_name='OCDLEGLO_DATA';

UPDATE
	dynamic_grid_config
SET
	config_json = '[ { "field": "displayNo", "fieldName": "ocdbailo.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocdbailo.ordereddate", "dataType": "date", "required": true }, { "field": "matter", "fieldName": "ocdbailo.matter", "dataType": "text", "editable": false }, { "field": "court", "fieldName": "ocdbailo.court", "dataType": "lov", "source": "link", "url": "ocdccase/populateCourtData", "sourceType": "OUMAGLOC", "required": true }, { "field": "sentenceType", "fieldName": "ocdbailo.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS", "required": true }, { "field": "legislation", "fieldName": "ocdbailo.legislation", "dataType": "lov", "source": "link", "url": "ocmpconf/populateStatutes", "sourceType": "OIMSTATU" }, { "field": "commenceDate", "fieldName": "ocdbailo.commencedate", "dataType": "date", "required": true }, { "field": "expiryDate", "fieldName": "ocdbailo.expirydate", "dataType": "date" }, { "field": "intParties", "dataType": "hyperlink", "link": "/OCDINTPA", "fieldName": "ocdleglo.intParties" }, { "field": "signaturerequired", "fieldName": "ocdbailo.signaturerequired", "dataType": "checkbox" }, { "field": "status", "fieldName": "ocdbailo.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=", "editable": true, "required": true }, { "field": "charges", "hide": true, "dataType": "text" }, { "field": "activeType", "hide": true, "dataType": "text" } ]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	module_name = 'OCDBAILO'
	AND grid_name = 'bailOrders' and db_table_name='OCDLEGLO_DATA';


UPDATE
	dynamic_grid_config
SET
	config_json = '[ { "field": "displayNo", "fieldName": "custorder.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "type", "fieldName": "custorder.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=CUST", "sourceType": "OIMSREQS", "editable": false }, { "field": "termTypeAndLength", "fieldName": "custorder.termtypeandlength", "dataType": "text", "editable": false }, { "field": "opTerm", "fieldName": "ocdlegls.operativeheading", "dataType": "text", "editable": false }, { "field": "commenceType", "fieldName": "custorder.commencetype", "dataType": "lov", "source": "domain", "url": "LO_REL_TYPE", "editable": false }, { "field": "relatedTo", "fieldName": "custorder.linkto", "dataType": "text", "editable": false }, { "field": "commenceDate", "fieldName": "custorder.commencedate", "dataType": "text", "editable": false }, { "field": "erd", "fieldName": "custorder.erd", "dataType": "text", "editable": false }, { "field": "ped", "fieldName": "custorder.ped", "dataType": "text", "editable": false }, { "field": "lrd", "fieldName": "custorder.lrd", "dataType": "text", "editable": false }, { "field": "hed", "fieldName": "ocdleglo.holdExpirydate", "dataType": "text", "editable": false }, { "field": "overridebtn", "fieldName": "ocdleglo.overridelaunch", "dataType": "hyperlink", "editable": false }, { "field": "overrides", "fieldName": "", "dataType": "text", "editable": false, "hide": true }, { "field": "sentOverrideCheck", "dataType": "checkbox", "fieldName": "ocdlegls.overrideCheck", "editable": false }, { "field": "status", "fieldName": "custorder.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=CUST", "editable": false }, { "field": "orderType", "hide": true, "dataType": "text", "editable": false } ]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	module_name = 'OCDLEGLS'
	AND grid_name = 'custOrders' and db_table_name='OCDLEGLO_DATA';

UPDATE
	dynamic_grid_config
SET
	config_json = '[ { "field": "displayNo", "fieldName": "ocdbailo.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "sentenceType", "fieldName": "ocdbailo.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS", "editable": false }, { "field": "legislation", "fieldName": "ocdbailo.legislation", "dataType": "lov", "source": "link", "url": "ocmpconf/populateStatutes", "sourceType": "OIMSTATU", "editable": false }, { "field": "commenceDate", "fieldName": "ocdbailo.commencedate", "dataType": "date", "editable": false }, { "field": "expiryDate", "fieldName": "ocdbailo.expirydate", "dataType": "date", "editable": false }, { "field": "signaturerequired", "fieldName": "ocdbailo.signaturerequired", "dataType": "checkbox", "editable": false }, { "field": "status", "fieldName": "ocdbailo.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=BAIL", "editable": false } ]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
	module_name = 'OCDLEGLS'
	AND grid_name = 'bailGrid' and db_table_name='OCDLEGLO_DATA';


DELETE
FROM
	dynamic_grid_config
WHERE
	id IN (
	SELECT
		id
	FROM
		(
		SELECT
			ROW_NUMBER() OVER(PARTITION BY module_name,grid_name,
			db_table_name
		ORDER BY
			id DESC) rn,
			*
		FROM
			dynamic_grid_config dgc)A
	WHERE
		rn>1);

UPDATE
	dynamic_grid_config dgc
SET
	config_json = decode('5B7B226461746154797065223A202264617465222C226564697461626C65223A2066616C73652C226669656C64223A202273656E7444617465222C226669656C644E616D65223A20226F636473656E63682E73656E7444617465227D2C7B226461746154797065223A202274696D65222C226564697461626C65223A2066616C73652C226669656C64223A202273656E7454696D65222C20226669656C644E616D65223A20226F636473656E63682E73656E7454696D65227D2C7B226461746154797065223A202274657874222C226564697461626C65223A2066616C73652C226669656C64223A202273746166664E616D65222C226669656C644E616D65223A20226F636473656E63682E73746166664E616D65227D2C7B226461746154797065223A20226C6F76222C226564697461626C65223A2066616C73652C226669656C64223A202263616C63756C6174696F6E526561736F6E222C226669656C644E616D65223A20226F636473656E63682E63616C63756C6174696F6E526561736F6E222C22736F75726365223A2022646F6D61696E222C2275726C223A202243414C435F524541534F4E227D2C7B226461746154797065223A202274657874222C226564697461626C65223A2066616C73652C226669656C64223A2022636F6D6D656E74222C226669656C644E616D65223A20226F636473656E63682E636F6D6D656E74227D5D',
	'hex'),
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
    module_name= 'OCDSENCH' and
	grid_name = 'calcEventgrid'
	AND db_table_name = 'OCDLEGLS_DATA_HTY';

UPDATE
	dynamic_grid_config dgc
SET
	config_json = decode('5B7B226461746154797065223A202274657874222C226564697461626C65223A2066616C73652C226669656C64223A2022646973706C61795F6E6F222C226669656C644E616D65223A20226F636473656E63682E646973706C61796E6F227D2C7B226461746154797065223A202274657874222C226564697461626C65223A2066616C73652C226669656C64223A20226F726465725F6E6F222C226669656C644E616D65223A20226F636473656E63682E6F726465726E6F222C2268696465223A20747275657D2C7B226461746154797065223A20226C6F76222C226564697461626C65223A2066616C73652C226669656C64223A2022636F6D6D656E636574797065222C226669656C644E616D65223A20226F636473656E63682E636F6D6D656E636554797065222C22736F75726365223A2022646F6D61696E222C2275726C223A20224C4F5F52454C5F54595045227D2C7B226461746154797065223A202264617465222C226564697461626C65223A2066616C73652C226669656C64223A2022636F6D6D656E636564617465222C226669656C644E616D65223A20226F636473656E63682E73656E74656E6365436F6D6D656E636544617465227D2C7B226461746154797065223A202274657874222C226564697461626C65223A2066616C73652C226669656C64223A202265787069727964617465222C226669656C644E616D65223A20226F636473656E63682E657870697279227D2C7B226461746154797065223A20226C6F76222C226564697461626C65223A2066616C73652C226669656C64223A2022737461747573222C226669656C644E616D65223A20226F636473656E63682E737461747573222C22736F75726365223A20226C696E6B222C2022736F7572636554797065223A20224F434D5354415453222C202275726C223A20226F636D70636F6E662F72674F72646572537461747573227D5D',
	'hex'),
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
    module_name= 'OCDSENCH' and
	grid_name = 'bailGrid'
	AND db_table_name = 'OCDSENCH_DATA_HTY';

UPDATE
	dynamic_grid_config dgc
SET
	config_json = decode('5B7B226461746154797065223A202274657874222C226564697461626C65223A2066616C73652C226669656C64223A2022646973706C61795F6E6F222C226669656C644E616D65223A20226F636473656E63682E646973706C61796E6F227D2C7B226461746154797065223A202274657874222C226564697461626C65223A2066616C73652C226669656C64223A20226F726465725F6E6F222C226669656C644E616D65223A20226F636473656E63682E6F726465726E6F222C2268696465223A20747275657D2C7B226461746154797065223A20226C6F76222C226564697461626C65223A2066616C73652C226669656C64223A202273656E74656E63655F636F6D6D656E63655F74797065222C226669656C644E616D65223A20226F636473656E63682E636F6D6D656E636554797065222C22736F75726365223A20226C696E6B222C202275726C223A20226F636D70636F6E662F706F70756C61746553656E74547970653F73656E7443617465676F72793D222C2022736F7572636554797065223A20224F494D5352455153227D2C7B226461746154797065223A202264617465222C226564697461626C65223A2066616C73652C226669656C64223A202273656E74656E63655F636F6D6D656E63655F64617465222C226669656C644E616D65223A20226F636473656E63682E73656E74656E6365436F6D6D656E636544617465227D2C7B226461746154797065223A202264617465222C226564697461626C65223A2066616C73652C226669656C64223A202273656E74656E63655F6578706972795F64617465222C226669656C644E616D65223A20226F636473656E63682E657870697279227D2C7B226461746154797065223A20226C6F76222C226564697461626C65223A2066616C73652C226669656C64223A2022737461747573222C226669656C644E616D65223A20226F636473656E63682E737461747573222C22736F75726365223A20226C696E6B222C2022736F7572636554797065223A20224F434D5354415453222C202275726C223A20226F636D70636F6E662F72674F726465725374617475733F6F72646572547970653D4241494C227D5D',
	'hex'),
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
    module_name= 'OCDSENCH' and
	grid_name = 'bailGrid'
	AND db_table_name = 'OCDSENCH_DATA_HTY';

update
	dynamic_grid_config dgc
set
	config_json = '[{"dataType": "text","editable": false,"field": "display_no","fieldName": "ocdsench.displayno"},{"dataType": "text","editable": false,"field": "order_no","fieldName": "ocdsench.orderno","hide": true},{"dataType": "lov","editable": false,"field": "commencetype","fieldName": "ocdsench.commenceType","source": "domain","url": "LO_REL_TYPE"},{"dataType": "date","editable": false,"field": "sentence_commence_date","fieldName": "ocdsench.sentenceCommenceDate"},{"dataType": "text","editable": false,"field": "expirydate","fieldName": "ocdsench.expiry"},{"dataType": "lov","editable": false,"field": "status","fieldName": "ocdsench.status","source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
    module_name= 'OCDSENCH' and
	grid_name = 'nonCustgrid'
	and db_table_name = 'OCDSENCH_DATA_HTY';

UPDATE
	dynamic_grid_config dgc
SET
	config_json = '[{"dataType": "text","editable": false,"field": "display_no","fieldName": "ocdsench.displayno"},{"dataType": "text","editable": false,"field": "order_no","fieldName": "ocdsench.orderno","hide": true},{"dataType": "text","editable": false,"field": "sentence_commence_date","fieldName": "ocdsench.sentenceCommenceDate"},{"dataType": "lov","editable": false,"field": "sentence_commence_type","fieldName": "ocdsench.commenceType","source": "domain","url": "LO_REL_TYPE"},{"dataType": "text","editable": false,"field": "erd","fieldName": "ocdsench.erd"},{"dataType": "text","editable": false,"field": "erdOverride","fieldName": "ocdsench.override"},{"dataType": "text","editable": false,"field": "red","fieldName": "ocdsench.red"},{"dataType": "text","editable": false,"field": "redOverride","fieldName": "ocdsench.override"},{"dataType": "text","editable": false,"field": "ped","fieldName": "ocdsench.ped"},{"dataType": "text","editable": false,"field": "pedOverride","fieldName": "ocdsench.override"},{"dataType": "text","editable": false,"field": "lrd","fieldName": "ocdsench.lrd"},{"dataType": "text","editable": false,"field": "lrdOverride","fieldName": "ocdsench.override"},{"dataType": "text","editable": false,"field": "hed","fieldName": "ocdsench.holdExpirydate"},{"dataType": "text","editable": false,"field": "hedOverride","fieldName": "ocdsench.override"},{"dataType": "lov","editable": false,"field": "status","fieldName": "ocdsench.status","source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus?orderType=CUST"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
WHERE
    module_name= 'OCDSENCH' and
	grid_name = 'custOrdergrid'
	AND db_table_name = 'OCDSENCH_DATA_HTY';
	
	
update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "ocdleglo.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "ocdleglo.ordereddate", "dataType": "date" }, { "field": "matter", "fieldName": "ocdleglo.matter", "dataType": "text" }, { "field": "type", "fieldName": "ocdleglo.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS" }, { "field": "termTypeAndLength", "fieldName": "ocdleglo.termtypeandlength", "dataType": "text" }, { "field": "commenceDate", "fieldName": "ocdleglo.commencedate", "dataType": "date" }, { "field": "status", "fieldName": "ocdleglo.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus"} ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	module_name = 'OCDLEGLO' and grid_name = 'related' and db_table_name='OCDLEGLO_DATA';



update
	dynamic_grid_config
set
	config_json = '[ { "field": "displayNo", "fieldName": "nonCustRelated.no", "dataType": "text", "editable": false }, { "field": "orderNo", "hide": true, "dataType": "text", "editable": false }, { "field": "orderedDate", "fieldName": "nonCustRelated.ordereddate", "dataType": "date" }, { "field": "matter", "fieldName": "nonCustRelated.matter", "dataType": "text" }, { "field": "type", "fieldName": "nonCustRelated.type", "dataType": "lov", "source": "link", "url": "ocmpconf/populateSentType?sentCategory=", "sourceType": "OIMSREQS" }, { "field": "termTypeAndLength", "fieldName": "nonCustRelated.termtypeandlength", "dataType": "text" }, { "field": "commenceDate", "fieldName": "nonCustRelated.commencedate", "dataType": "date" }, { "field": "status", "fieldName": "nonCustRelated.status", "dataType": "lov", "source": "link", "sourceType": "OCMSTATS", "url": "ocmpconf/rgOrderStatus" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
	where
	module_name = 'OCDNCODE' and grid_name = 'nonCustRelated'  and db_table_name = 'OCDLEGLO_DATA';


update
	dynamic_grid_config
set
	config_json = '[ { "field": "termType", "fieldName": "ncusTerm.termtype", "dataType": "lov", "source": "link", "sourceType": "OIMSREQS", "url": "ocmpconf/populateTermType?sentCategory=:orderType&sentType=:sentType", "required": true }, { "field": "years", "fieldName": "ncusTerm.years", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "months", "fieldName": "ncusTerm.months", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "weeks", "fieldName": "ncusTerm.weeks", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "days", "fieldName": "ncusTerm.days", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "fixedExpiry", "fieldName": "ncusTerm.fixedExpiry", "dataType": "checkbox" }, { "field": "indefinite", "fieldName": "ncusTerm.indefinite", "dataType": "checkbox" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	module_name = 'OCDNCODE' and grid_name = 'nonCustTerm'  and db_table_name = 'OCDLEGLO_DATA';
	

update
	dynamic_grid_config
set
	config_json = '[ { "field": "termType", "fieldName": "termToLine.termtype", "dataType": "lov", "source": "link", "sourceType": "OIMSREQS", "url": "ocmpconf/populateTermType?sentCategory=:orderType&sentType=:sentType", "required": true, "editable": true }, { "field": "years", "fieldName": "termToLine.years", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "months", "fieldName": "termToLine.months", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "weeks", "fieldName": "termToLine.weeks", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "days", "fieldName": "termToLine.days", "dataType": "number", "maxValue": 9999.9, "format": "1.1-1" }, { "field": "fixedExpiry", "fieldName": "durationToLine.fixedexpiry", "dataType": "checkbox" }, { "field": "indefinite", "fieldName": "termToLine.indefinite", "dataType": "checkbox" } ]',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER' 
where
	module_name = 'OCDPAROR' and grid_name = 'duration' and db_table_name = 'OCDLEGLO_DATA';	
	