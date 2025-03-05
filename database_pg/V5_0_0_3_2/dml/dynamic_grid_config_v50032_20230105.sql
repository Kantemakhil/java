update
	DYNAMIC_GRID_CONFIG
set
	CONFIG_JSON = '[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"select","fieldName":"ocdchgsu.select","dataType":"checkbox","editable":false},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","required":true,"editable":true},{"field":"description","fieldName":"ocdchgsu.description","dataType":"text","required":true,"editable":false},{"field":"descriptionLaunch","dataType":"hyperlink","displayFields":["code","description","act","legislativeBody"],"parentField":["code","description","act"],"lovUrl":"ocmpconf/getOffencesOnStatute?statuteCode=:act","link":"/multiLov"},{"field":"code","fieldName":"ocdchgsu.code","dataType":"text","required":false,"editable":false},{"field":"act","fieldName":"ocdchgsu.act","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","editable":true},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"link","sourceType":"OIMOFFEN","parentFields":["code","act"],"url":"ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act","editable":true},{"field":"incidentDate","dataType":"text","editable":false,"hide":true},{"field":"Range","dataType":"text","editable":false,"hide":true},{"field":"plea","dataType":"text","editable":false,"hide":true},{"field":"particulars","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.details","field":"details","dataType":"hyperlink","link":"/OCDCHGDT"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	MODULE_NAME = 'OCDCHGSU'
	and GRID_NAME = 'charges';

update
	DYNAMIC_GRID_CONFIG
set
	CONFIG_JSON = '[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"select","dataType":"checkbox","fieldName":"ocdchgsu.select","editable":true},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","required":true,"editable":true},{"field":"description","fieldName":"ocdchgsu.description","required":true,"dataType":"text","editable":false},{"field":"descriptionLaunch","dataType":"hyperlink","displayFields":["code","description","act","legislativeBody"],"parentField":["code","description","act"],"lovUrl":"ocmpconf/getOffencesOnStatute?statuteCode=:act","link":"/multiLov"},{"field":"code","fieldName":"ocdchgsu.code","dataType":"text","required":false,"editable":false},{"field":"act","fieldName":"ocdchgsu.act","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","editable":true},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"link","sourceType":"OIMOFFEN","parentFields":["code","act"],"url":"ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act","editable":true},{"field":"incidentDate","dataType":"text","editable":false,"hide":true},{"field":"Range","dataType":"text","editable":false,"hide":true},{"field":"plea","dataType":"text","editable":false,"hide":true},{"field":"particulars","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.details","field":"details","dataType":"hyperlink","link":"/OCDCHGDT"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	MODULE_NAME = 'OCDCHGSU'
	and GRID_NAME = 'chargesDlg';

update
	DYNAMIC_GRID_CONFIG
set
	CONFIG_JSON = '[{"field":"date_type","dataType":"text","fieldName":"ocdlegls.datetype","editable":false},{"field":"value","dataType":"date","fieldName":"ocdlegls.value","editable":false},{"field":"indefinite","dataType":"custom","fieldName":"ocdlegls.indefinite","editable":false}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	MODULE_NAME = 'OCDLEGLS'
	and GRID_NAME = 'keyDateSummary';

insert
	into
	dynamic_grid_config(module_name,
	config_json,
	grid_name,
	db_table_name,
	create_datetime,
	create_user_id)
values('OCDPAROR',
'[{"field":"displayNo","fieldName":"ocdleglo.no","dataType":"text","editable":false},{"field":"no","hide":true,"dataType":"text","editable":false},{"field":"hearingDate","fieldName":"ocdparor.hearingdate","dataType":"date","required":true},{"field":"authority","fieldName":"ocdparor.authority","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdleglo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=PAR","sourceType":"OIMSREQS","required":true},{"field":"commenceType","fieldName":"ocdleglo.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","required":true,"editable":false},{"field":"duration","fieldName":"ocdparor.duration","dataType":"text","required":true,"editable":false},{"field":"lengthBtn","dataType":"hyperlink","link":"/durationToLine","parentField":["duration","orderDurations"]},{"field":"commenceDate","fieldName":"ocdleglo.commencedate","dataType":"date","required":true},{"field":"expiryDate","fieldName":"ocdparor.expirydate","dataType":"date"},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdleglo.intParties"},{"field":"status","fieldName":"ocdleglo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"affectedOrders","hide":true,"dataType":"text"},{"field":"orderDurations","hide":true,"dataType":"text"}]',
'paroleOrders',
'OCDLEGLO_DATA',
current_timestamp,
'OMS_OWNER');

insert
	into
	dynamic_grid_config(module_name,
	config_json,
	grid_name,
	db_table_name,
	create_datetime,
	create_user_id)
values('OCDPAROR',
'[{"field":"select","fieldName":"ocdparor.select","dataType":"checkbox","editable":true},{"field":"displayNo","fieldName":"custorder.no","dataType":"text","editable":false},{"field":"no","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"custorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=CUST","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"custorder.termtypeandlength","dataType":"text","editable":false},{"field":"relatedTo","fieldName":"custorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"custorder.commencedate","dataType":"text","editable":false},{"field":"erd","fieldName":"custorder.erd","dataType":"text","editable":false},{"field":"ped","fieldName":"custorder.ped","dataType":"date","editable":false},{"field":"lrd","fieldName":"custorder.lrd","dataType":"text","editable":false},{"field":"status","fieldName":"custorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false}]',
'affectedOrders',
'OCDLEGLO_DATA',
current_timestamp,
'OMS_OWNER');

insert
	into
	dynamic_grid_config(module_name,
	config_json,
	grid_name,
	db_table_name,
	create_datetime,
	create_user_id)
values('OCDPAROR',
'[{"field":"termType","fieldName":"termToLine.termtype","dataType":"lov","source":"link","sourceType":"OIMSREQS","url":"ocmpconf/populateTermType?sentCategory=:orderType&sentType=:sentType","required":true,"editable":false},{"field":"years","fieldName":"termToLine.years","dataType":"number","maxValue":99.5,"format":"1.1-1"},{"field":"months","fieldName":"termToLine.months","dataType":"number","maxValue":99.5,"format":"1.1-1"},{"field":"weeks","fieldName":"termToLine.weeks","dataType":"number","maxValue":99,"format":"0.0-0"},{"field":"days","fieldName":"termToLine.days","dataType":"number","maxValue":99,"format":"0.0-0"},{"field":"fixedExpiry","fieldName":"durationToLine.fixedexpiry","dataType":"checkbox"},{"field":"indefinite","fieldName":"termToLine.indefinite","dataType":"checkbox"}]',
'duration',
'OCDLEGLO_DATA',
current_timestamp,
'OMS_OWNER');

update
	dynamic_grid_config
set
	config_json = '[{"field":"displayNo","fieldName":"ocdbailo.no","dataType":"text","editable":false},{"field":"no","hide":true,"dataType":"text","editable":false},{"field":"orderedDate","fieldName":"ocdbailo.ordereddate","dataType":"date","required":true},{"field":"matter","fieldName":"ocdbailo.matter","dataType":"text","editable":false},{"field":"court","fieldName":"ocdbailo.court","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdbailo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS","required":true},{"field":"legislation","fieldName":"ocdbailo.legislation","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU"},{"field":"commenceDate","fieldName":"ocdbailo.commencedate","dataType":"date","required":true},{"field":"expiryDate","fieldName":"ocdbailo.expirydate","dataType":"date"},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdleglo.intParties"},{"field":"signaturerequired","fieldName":"ocdbailo.signaturerequired","dataType":"checkbox"},{"field":"status","fieldName":"ocdbailo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"charges","hide":true,"dataType":"text"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDBAILO'
	and grid_name = 'bailOrders';

update
	dynamic_grid_config
set
	config_json = '[{"field":"displayNo","fieldName":"ncustorder.no","dataType":"text","editable":false},{"field":"no","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"ncustorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateCatSentType?sentCategory=NCUS_PAR","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"ncustorder.termtypeandlength","dataType":"text","editable":false},{"field":"commenceType","fieldName":"ncustorder.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","editable":false},{"field":"relatedTo","fieldName":"ncustorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"ncustorder.commencedate","dataType":"text","editable":false},{"field":"expiryDate","fieldName":"ncustorder.expiryDate","dataType":"text","editable":true},{"field":"status","fieldName":"ncustorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"orderType","hide":true,"dataType":"text","editable":false}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDLEGLS'
	and grid_name = 'nCustOrders';

update
	dynamic_grid_config
set
	config_json = '[{"field":"displayNo","fieldName":"ocdleglo.no","dataType":"text","editable":false},{"field":"no","hide":true,"dataType":"text","editable":false},{"field":"hearingDate","fieldName":"ocdparor.hearingdate","dataType":"date","required":true},{"field":"authority","fieldName":"ocdparor.authority","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdleglo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=PAR","sourceType":"OIMSREQS","required":true},{"field":"commenceType","fieldName":"ocdleglo.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","required":true,"editable":false},{"field":"duration","fieldName":"ocdparor.duration","dataType":"text","required":true,"editable":false},{"field":"lengthBtn","dataType":"hyperlink","fieldName":"","link":"/durationToLine","parentField":["duration","orderDurations"]},{"field":"commenceDate","fieldName":"ocdleglo.commencedate","dataType":"date","required":true},{"field":"expiryDate","fieldName":"ocdparor.expirydate","dataType":"date"},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdleglo.intParties"},{"field":"status","fieldName":"ocdleglo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"affectedOrders","hide":true,"dataType":"text"},{"field":"orderDuraions","hide":true,"dataType":"text"}]
',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDPAROR'
	and grid_name = 'paroleOrders';

delete
from
	DYNAMIC_GRID_CONFIG
where
	grid_name = 'keyDatesGrid';

insert
	into
	DYNAMIC_GRID_CONFIG(MODULE_NAME,
	CONFIG_JSON,
	GRID_NAME,
	DB_TABLE_NAME,
	CREATE_USER_ID)
values('OIMRELSC',
'
[{
		"field": "dateType",
		"fieldName": "oimrelsc.datetype",
		"dataType": "lov",
		"source": "domain",
		"url": "KEY_DATES",
		"required": true
	},
	{
		"field": "listSeq",
		"fieldName": "oimrelsc.listseq",
		"dataType": "number",
		"maxValue": 99999,
		"format": "0.0-0",
		"whole": true
	}
]',
'keyDatesGrid',
'RELEASE_SCHEDULE_SETTINGS',
'OMS_OWNER');

update
	DYNAMIC_GRID_CONFIG
set
	CONFIG_JSON = '[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"select","fieldName":"ocdchgsu.select","dataType":"checkbox","editable":false},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","required":true,"editable":true},{"field":"description","fieldName":"ocdchgsu.description","dataType":"text","required":true,"editable":false},{"field":"descriptionLaunch","dataType":"hyperlink","displayFields":["code","description","act","legislativeBody"],"parentField":["code","description","act"],"lovUrl":"ocmpconf/getOffencesOnStatute?statuteCode=:act","link":"/multiLov"},{"field":"code","fieldName":"ocdchgsu.code","dataType":"text","required":false,"editable":false},{"field":"act","fieldName":"ocdchgsu.act","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","editable":true},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"link","sourceType":"OIMOFFEN","parentFields":["code","act"],"url":"ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act","editable":true},{"field":"incidentDate","dataType":"text","editable":false,"hide":true},{"field":"Range","dataType":"text","editable":false,"hide":true},{"field":"plea","dataType":"text","editable":false,"hide":true},{"field":"particulars","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.chargeoutcome","field":"outcome","dataType":"lov","source":"link","url":"ocmpconf/populateOutcome","sourceType":"OCMORCOD","editable":false,"hide":false},{"field":"outcomeBtn","dataType":"hyperlink","link":"/OCUCHGOU"},{"fieldName":"ocdchgsu.details","field":"details","dataType":"hyperlink","link":"/OCDCHGDT"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	MODULE_NAME = 'OCDCHGSU'
	and GRID_NAME = 'charges';

insert
	into
	DYNAMIC_GRID_CONFIG (MODULE_NAME,
	GRID_NAME,
	DB_TABLE_NAME,
	CONFIG_JSON,
	CREATE_USER_ID)
values ('OCUCHGOU',
'chargeOutcome',
'OCUCHGOU_DATA',
'[{"field":"outcome","fieldName":"ocuchgou.outcome","dataType":"lov","source":"link","url":"ocmpconf/populateOutcome","sourceType":"OCMORCOD","required":true,"editable":true},{"field":"disposition","fieldName":"ocuchgou.disposition","dataType":"lov","source":"domain","url":"OFF_RESULT","required":false,"editable":false},{"field":"status","fieldName":"ocuchgou.status","dataType":"lov","source":"domain","url":"CHARGE_STS","required":false,"editable":false},{"field":"orderedDate","fieldName":"ocuchgou.ordereddate","dataType":"date","required":false,"editable":false},{"field":"linkedTo","fieldName":"ocuchgou.linkedTo","dataType":"text","required":false,"editable":false},{"field":"updatedDate","fieldName":"ocuchgou.updatedon","dataType":"date","required":true,"editable":true},{"field":"staffName","fieldName":"ocuchgou.staffname","dataType":"text","required":false,"editable":false},{"field":"updateReason","fieldName":"ocuchgou.updatereason","dataType":"lov","source":"domain","url":"CHOUTUPREA","required":true,"editable":true}]',
'OMS_OWNER');

update
	DYNAMIC_GRID_CONFIG
set
	CONFIG_JSON = '[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"select","dataType":"checkbox","fieldName":"ocdchgsu.select","editable":true},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","required":true,"editable":true},{"field":"description","fieldName":"ocdchgsu.description","required":true,"dataType":"text","editable":false},{"field":"descriptionLaunch","dataType":"hyperlink","displayFields":["code","description","act","legislativeBody"],"parentField":["code","description","act","offenceId"],"lovUrl":"ocmpconf/getOffencesOnStatute?statuteCode=:act","link":"/multiLov"},{"field":"code","fieldName":"ocdchgsu.code","dataType":"text","required":false,"editable":false},{"field":"act","fieldName":"ocdchgsu.act","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","editable":true},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"link","sourceType":"OIMOFFEN","parentFields":["code","act"],"url":"ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act","editable":true},{"field":"incidentDate","dataType":"text","editable":false,"hide":true},{"field":"Range","dataType":"text","editable":false,"hide":true},{"field":"plea","dataType":"text","editable":false,"hide":true},{"field":"particulars","dataType":"text","editable":false,"hide":true},{"field":"offenceId","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.details","field":"details","dataType":"hyperlink","link":"/OCDCHGDT"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	MODULE_NAME = 'OCDCHGSU'
	and GRID_NAME = 'chargesDlg';

update
	DYNAMIC_GRID_CONFIG
set
	CONFIG_JSON = '[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"select","fieldName":"ocdchgsu.select","dataType":"checkbox","editable":false},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","required":true,"editable":true},{"field":"description","fieldName":"ocdchgsu.description","dataType":"text","required":true,"editable":false},{"field":"descriptionLaunch","dataType":"hyperlink","displayFields":["code","description","act","legislativeBody"],"parentField":["code","description","act","offenceId"],"lovUrl":"ocmpconf/getOffencesOnStatute?statuteCode=:act","link":"/multiLov"},{"field":"code","fieldName":"ocdchgsu.code","dataType":"text","required":false,"editable":false},{"field":"act","fieldName":"ocdchgsu.act","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","editable":true},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"link","sourceType":"OIMOFFEN","parentFields":["code","act"],"url":"ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act","editable":true},{"field":"incidentDate","dataType":"text","editable":false,"hide":true},{"field":"Range","dataType":"text","editable":false,"hide":true},{"field":"plea","dataType":"text","editable":false,"hide":true},{"field":"particulars","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.chargeoutcome","field":"outcome","dataType":"lov","source":"link","url":"ocmpconf/populateOutcome","sourceType":"OCMORCOD","editable":false,"hide":false},{"field":"outcomeBtn","dataType":"hyperlink","link":"/OCUCHGOU"},{"field":"offenceId","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.details","field":"details","dataType":"hyperlink","link":"/OCDCHGDT"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	MODULE_NAME = 'OCDCHGSU'
	and GRID_NAME = 'charges';

update
	DYNAMIC_GRID_CONFIG
set
	CONFIG_JSON = '[{"field":"outcome","fieldName":"ocuchgou.outcome","dataType":"lov","source":"link","url":"ocmpconf/populateOutcome","sourceType":"OCMORCOD","required":true,"editable":true},{"field":"disposition","fieldName":"ocuchgou.disposition","dataType":"lov","source":"domain","url":"OFF_RESULT","required":false,"editable":false},{"field":"status","fieldName":"ocuchgou.status","dataType":"lov","source":"domain","url":"CHARGE_STS","required":false,"editable":false},{"field":"orderedDate","fieldName":"ocuchgou.ordereddate","dataType":"date","required":false,"editable":false},{"field":"linkedTo","fieldName":"ocuchgou.linkedTo","dataType":"text","required":false,"editable":false},{"field":"updatedDate","fieldName":"ocuchgou.updatedon","dataType":"text","required":true,"editable":true},{"field":"staffName","fieldName":"ocuchgou.staffname","dataType":"text","required":false,"editable":false},{"field":"updateReason","fieldName":"ocuchgou.updatereason","dataType":"lov","source":"domain","url":"CHOUTUPREA","required":true,"editable":true}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	MODULE_NAME = 'OCUCHGOU'
	and GRID_NAME = 'chargeOutcome';
	
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"displayNo","fieldName":"ocdbailo.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"orderedDate","fieldName":"ocdbailo.ordereddate","dataType":"date","required":true},{"field":"matter","fieldName":"ocdbailo.matter","dataType":"text","editable":false},{"field":"court","fieldName":"ocdbailo.court","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdbailo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS","required":true},{"field":"legislation","fieldName":"ocdbailo.legislation","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU"},{"field":"commenceDate","fieldName":"ocdbailo.commencedate","dataType":"date","required":true},{"field":"expiryDate","fieldName":"ocdbailo.expirydate","dataType":"date"},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdleglo.intParties"},{"field":"signaturerequired","fieldName":"ocdbailo.signaturerequired","dataType":"checkbox"},{"field":"status","fieldName":"ocdbailo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"charges","hide":true,"dataType":"text"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCDBAILO' AND GRID_NAME = 'bailOrders';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"displayNo","fieldName":"ocdleglo.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"orderedDate","fieldName":"ocdleglo.ordereddate","dataType":"date"},{"field":"matter","fieldName":"ocdleglo.matter","dataType":"text"},{"field":"type","fieldName":"ocdleglo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS"},{"field":"termTypeAndLength","fieldName":"ocdleglo.termtypeandlength","dataType":"text"},{"field":"commenceDate","fieldName":"ocdleglo.commencedate","dataType":"date"},{"field":"status","fieldName":"ocdleglo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCDLEGLO' AND GRID_NAME = 'related';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"displayNo","fieldName":"ocdleglo.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"orderedDate","fieldName":"ocdleglo.ordereddate","dataType":"date","required":true},{"field":"matter","fieldName":"ocdleglo.matter","dataType":"text","editable":false},{"field":"court","fieldName":"ocdleglo.court","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdleglo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS","required":true},{"field":"commenceType","fieldName":"ocdleglo.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","required":true},{"field":"relatedTo","fieldName":"ocdleglo.linkto","dataType":"text","editable":false},{"field":"relatedToLaunch","dataType":"hyperlink","link":"/relatedToLine","parentField":["relatedTo"]},{"field":"commenceDate","fieldName":"ocdleglo.commencedate","dataType":"date"},{"field":"termTypeAndLength","fieldName":"ocdleglo.termtypeandlength","dataType":"text","required":true,"editable":false},{"field":"lengthBtn","dataType":"hyperlink","link":"/termToLine","parentField":["termTypeAndLength","terms"]},{"field":"pel","fieldName":"ocdleglo.pel","dataType":"checkbox"},{"field":"status","fieldName":"ocdleglo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdleglo.intParties"},{"field":"terms","hide":true,"dataType":"text"},{"field":"charges","hide":true,"dataType":"text"},{"field":"orderType","hide":true,"dataType":"text"},{"field":"holdExpiryDate","fieldName":"ocdleglo.holdExpirydate","dataType":"date"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCDLEGLO' AND GRID_NAME = 'custOrd';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"displayNo","fieldName":"ncustorder.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"ncustorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateCatSentType?sentCategory=NCUS_PAR","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"ncustorder.termtypeandlength","dataType":"text","editable":false},{"field":"commenceType","fieldName":"ncustorder.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","editable":false},{"field":"relatedTo","fieldName":"ncustorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"ncustorder.commencedate","dataType":"text","editable":false},{"field":"expiryDate","fieldName":"ncustorder.expiryDate","dataType":"text","editable":true},{"field":"status","fieldName":"ncustorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"orderType","hide":true,"dataType":"text","editable":false}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCDLEGLS' AND GRID_NAME = 'nCustOrders';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"displayNo","fieldName":"ocdbailo.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"ocdbailo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS","editable":false},{"field":"legislation","fieldName":"ocdbailo.legislation","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","editable":false},{"field":"commenceDate","fieldName":"ocdbailo.commencedate","dataType":"date","editable":false},{"field":"expiryDate","fieldName":"ocdbailo.expirydate","dataType":"date","editable":false},{"field":"signaturerequired","fieldName":"ocdbailo.signaturerequired","dataType":"checkbox","editable":false},{"field":"status","fieldName":"ocdbailo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCDLEGLS' AND GRID_NAME = 'bailGrid';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"displayNo","fieldName":"custorder.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"custorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=CUST","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"custorder.termtypeandlength","dataType":"text","editable":false},{"field":"commenceType","fieldName":"custorder.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","editable":false},{"field":"relatedTo","fieldName":"custorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"custorder.commencedate","dataType":"text","editable":false},{"field":"erd","fieldName":"custorder.erd","dataType":"text","editable":false},{"field":"ped","fieldName":"custorder.ped","dataType":"date","editable":false},{"field":"lrd","fieldName":"custorder.lrd","dataType":"text","editable":false},{"field":"holdExpiryDate","fieldName":"ocdleglo.holdExpirydate","dataType":"text","editable":false},{"field":"status","fieldName":"custorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"orderType","hide":true,"dataType":"text","editable":false}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCDLEGLS' AND GRID_NAME = 'custOrders';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"displayNo","fieldName":"ocdncode.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"orderedDate","fieldName":"ocdncode.ordereddate","dataType":"date","required":true},{"field":"matter","fieldName":"ocdncode.matter","dataType":"text","editable":false},{"field":"court","fieldName":"ocdncode.court","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdncode.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS","required":true},{"field":"commenceType","fieldName":"ocdncode.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","required":true},{"field":"relatedTo","fieldName":"ocdncode.linkto","dataType":"text","editable":false},{"field":"relatedToLaunch","dataType":"hyperlink","link":"/nonCustRelated","parentField":["relatedTo"]},{"field":"commenceDate","fieldName":"ocdncode.commencedate","dataType":"date"},{"field":"termTypeAndLength","fieldName":"ocdncode.termtypeandlength","dataType":"text","required":true,"editable":false},{"field":"lengthBtn","dataType":"hyperlink","link":"/nonCustDuration","parentField":["termTypeAndLength","terms"]},{"field":"expiryDate","fieldName":"ocdncode.expiryDate","dataType":"date","editable":true},{"field":"status","fieldName":"ocdncode.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdncode.intparties"},{"field":"terms","hide":true,"dataType":"text"},{"field":"orderType","hide":true,"dataType":"text"},{"field":"charges","hide":true,"dataType":"text"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCDNCODE' AND GRID_NAME = 'nonCustOrd';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"displayNo","fieldName":"nonCustRelated.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"orderedDate","fieldName":"nonCustRelated.ordereddate","dataType":"date"},{"field":"matter","fieldName":"nonCustRelated.matter","dataType":"text"},{"field":"type","fieldName":"nonCustRelated.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS"},{"field":"termTypeAndLength","fieldName":"nonCustRelated.termtypeandlength","dataType":"text"},{"field":"commenceDate","fieldName":"nonCustRelated.commencedate","dataType":"date"},{"field":"status","fieldName":"nonCustRelated.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCDNCODE' AND GRID_NAME = 'nonCustRelated';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"select","fieldName":"ocdparor.select","dataType":"checkbox","editable":true},{"field":"displayNo","fieldName":"custorder.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"custorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=CUST","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"custorder.termtypeandlength","dataType":"text","editable":false},{"field":"relatedTo","fieldName":"custorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"custorder.commencedate","dataType":"text","editable":false},{"field":"erd","fieldName":"custorder.erd","dataType":"text","editable":false},{"field":"ped","fieldName":"custorder.ped","dataType":"date","editable":false},{"field":"lrd","fieldName":"custorder.lrd","dataType":"text","editable":false},{"field":"status","fieldName":"custorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCDPAROR' AND GRID_NAME = 'affectedOrders';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"displayNo","fieldName":"ocdleglo.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"hearingDate","fieldName":"ocdparor.hearingdate","dataType":"date","required":true},{"field":"authority","fieldName":"ocdparor.authority","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdleglo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=PAR","sourceType":"OIMSREQS","required":true},{"field":"commenceType","fieldName":"ocdleglo.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","required":true,"editable":false},{"field":"duration","fieldName":"ocdparor.duration","dataType":"text","required":true,"editable":false},{"field":"lengthBtn","dataType":"hyperlink","link":"/durationToLine","parentField":["duration","orderDurations"]},{"field":"commenceDate","fieldName":"ocdleglo.commencedate","dataType":"date","required":true},{"field":"expiryDate","fieldName":"ocdparor.expirydate","dataType":"date"},{"field":"intParties","dataType":"hyperlink","link":"/OCDINTPA","fieldName":"ocdleglo.intParties"},{"field":"status","fieldName":"ocdleglo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"affectedOrders","hide":true,"dataType":"text"},{"field":"orderDurations","hide":true,"dataType":"text"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCDPAROR' AND GRID_NAME = 'paroleOrders';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"orderNo","fieldName":"ocdleglo.no","dataType":"number","editable":false},{"field":"type","fieldName":"ocdleglo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=NCUS","sourceType":"OIMSREQS","required":true},{"field":"matter","fieldName":"ocdleglo.matter","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"ocdleglo.commencedate","dataType":"date"},{"field":"expiryDate","fieldName":"Expiry Date","dataType":"date"},{"field":"sanctionCount","fieldName":"Sanction Count","dataType":"number","editable":false},{"field":"adjournedCount","fieldName":"Adjourned Sanctions","dataType":"number","minValue":"0","maxValue":"999999","whole":"true"}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OSANVIOS' AND GRID_NAME = 'sanctions';
UPDATE DYNAMIC_GRID_CONFIG SET CONFIG_JSON ='[{"field":"outcome","fieldName":"ocuchgou.outcome","dataType":"lov","source":"link","url":"ocmpconf/populateOutcome","sourceType":"OCMORCOD","required":true,"editable":true},{"field":"disposition","fieldName":"ocuchgou.disposition","dataType":"lov","source":"domain","url":"OFF_RESULT","required":false,"editable":false},{"field":"status","fieldName":"ocuchgou.status","dataType":"lov","source":"domain","url":"CHARGE_STS","required":false,"editable":false},{"field":"orderedDate","fieldName":"ocuchgou.ordereddate","dataType":"date","required":false,"editable":false},{"field":"linkedTo","fieldName":"ocuchgou.linkedTo","dataType":"text","required":false,"editable":false},{"field":"updatedDate","fieldName":"ocuchgou.updatedon","dataType":"text","required":true,"editable":true},{"field":"staffName","fieldName":"ocuchgou.staffname","dataType":"lov","source":"link","url":"ocucalcr/getStaffMembers","required":false,"editable":false},{"field":"updateReason","fieldName":"ocuchgou.updatereason","dataType":"lov","source":"domain","url":"CHOUTUPREA","required":true,"editable":true}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER' WHERE MODULE_NAME = 'OCUCHGOU' AND GRID_NAME = 'chargeOutcome';	
