insert
	into
	dynamic_grid_config (id,
	module_name,
	config_json,
	grid_name,
	db_table_name,
	create_datetime,
	create_user_id,
	modify_datetime) 
values(59,
'OCDBAILO',
'[{"field":"displayNo","fieldName":"ocdbailo.no","dataType":"text","editable":false},{"field":"no","hide":true,"dataType":"text","editable":false},{"field":"orderedDate","fieldName":"ocdbailo.ordereddate","dataType":"date","required":true},{"field":"matter","fieldName":"ocdbailo.matter","dataType":"text","editable":false},{"field":"court","fieldName":"ocdbailo.court","dataType":"lov","source":"link","url":"ocdccase/populateCourtData","sourceType":"OUMAGLOC","required":true},{"field":"type","fieldName":"ocdbailo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS","required":true},{"field":"legislation","fieldName":"ocdbailo.legislation","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","required":true},{"field":"commenceDate","fieldName":"ocdbailo.commencedate","dataType":"date","required":true},{"field":"expiryDate","fieldName":"ocdbailo.expirydate","dataType":"date"},{"field":"signaturerequired","fieldName":"ocdbailo.signaturerequired","dataType":"checkbox"},{"field":"status","fieldName":"ocdbailo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"charges","hide":true,"dataType":"text"}]',
'bailOrders',
'OCDLEGLO_DATA',
current_timestamp,
'OMS_OWNER',
current_timestamp);

insert
	into
	dynamic_grid_config (id,
	module_name,
	config_json,
	grid_name,
	db_table_name,
	create_datetime,
	create_user_id,
	modify_datetime) 
values(60,
'OCDBAILO',
'[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","editable":false},{"field":"description","fieldName":"ocdchgsu.descriptionchild","dataType":"text","editable":false},{"field":"outcome","fieldName":"ocdchgsu.outcome","dataType":"lov","source":"link","url":"ocmpconf/populateOutcome","sourceType":"OCMORCOD","required":true,"editable":true},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"domain","url":"OFFENCE_TYPE","editable":false},{"field":"details","fieldName":"ocdchgsu.details","dataType":"text","editable":false}]',
'bailCharges',
'OCDCHGSU_DATA',
current_timestamp,
'OMS_OWNER',
current_timestamp);

insert
	into
	DYNAMIC_GRID_CONFIG (MODULE_NAME,
	GRID_NAME,
	DB_TABLE_NAME,
	CONFIG_JSON,
	create_datetime,
	create_user_id,
	modify_datetime) 
values
('OCDLEGLS',
'bailGrid',
'OCDLEGLO_DATA',
'[{"field":"displayNo","fieldName":"ocdbailo.no","dataType":"text","editable":false},{"field":"no","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"ocdbailo.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=","sourceType":"OIMSREQS","editable":false},{"field":"legislation","fieldName":"ocdbailo.legislation","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","editable":false},{"field":"commenceDate","fieldName":"ocdbailo.commencedate","dataType":"date","editable":false},{"field":"expiryDate","fieldName":"ocdbailo.expirydate","dataType":"date","editable":false},{"field":"signaturerequired","fieldName":"ocdbailo.signaturerequired","dataType":"checkbox","editable":false},{"field":"status","fieldName":"ocdbailo.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false}]', 
current_timestamp,
'OMS_OWNER',
current_timestamp);

update
	DYNAMIC_GRID_CONFIG
set  modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
	,CONFIG_JSON = '[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","editable":false},{"field":"description","fieldName":"ocdchgsu.descriptionchild","dataType":"text","editable":false},{"field":"outcome","fieldName":"ocdchgsu.outcome","dataType":"lov","source":"link","url":"ocmpconf/populateOutcome","sourceType":"OCMORCOD","required":true,"editable":true},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"domain","url":"OFFENCE_TYPE","editable":false},{"field":"incidentDate","dataType":"text","editable":false,"hide":true},{"field":"Range","dataType":"text","editable":false,"hide":true},{"field":"plea","dataType":"text","editable":false,"hide":true},{"field":"particulars","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.details","field":"details","dataType":"hyperlink","link":"/OCDCHGDT"}]'
where
	MODULE_NAME = 'OCDLEGLO'
	and GRID_NAME = 'chargesChild';

update
	DYNAMIC_GRID_CONFIG
set modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,CONFIG_JSON = '[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"select","fieldName":"ocdchgsu.select","dataType":"checkbox","editable":false},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","required":true,"editable":true},{"field":"description","fieldName":"ocdchgsu.description","dataType":"text","required":true,"editable":false},{"field":"code","fieldName":"ocdchgsu.code","dataType":"text","required":false,"editable":false},{"field":"act","fieldName":"ocdchgsu.act","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","editable":true},{"field":"descriptionLaunch","dataType":"hyperlink","displayFields":["code","description","act","legislativeBody"],"parentField":["code","description","act"],"lovUrl":"ocmpconf/getOffencesOnStatute?statuteCode=:act","link":"/multiLov"},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"link","sourceType":"OIMOFFEN","parentFields":["code","act"],"url":"ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act","editable":true},{"field":"incidentDate","dataType":"text","editable":false,"hide":true},{"field":"Range","dataType":"text","editable":false,"hide":true},{"field":"plea","dataType":"text","editable":false,"hide":true},{"field":"particulars","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.details","field":"details","dataType":"hyperlink","link":"/OCDCHGDT"}]'
where
	MODULE_NAME = 'OCDCHGSU'
	and GRID_NAME = 'charges';

update
	DYNAMIC_GRID_CONFIG
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,CONFIG_JSON = '[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"select","dataType":"checkbox","fieldName":"ocdchgsu.select","editable":true},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","required":true,"editable":true},{"field":"description","fieldName":"ocdchgsu.description","required":true,"dataType":"text","editable":false},{"field":"code","fieldName":"ocdchgsu.code","dataType":"text","required":false,"editable":false},{"field":"act","fieldName":"ocdchgsu.act","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","editable":true},{"field":"descriptionLaunch","dataType":"hyperlink","displayFields":["code","description","act","legislativeBody"],"parentField":["code","description","act"],"lovUrl":"ocmpconf/getOffencesOnStatute?statuteCode=:act","link":"/multiLov"},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"link","sourceType":"OIMOFFEN","parentFields":["code","act"],"url":"ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act","editable":true},{"field":"incidentDate","dataType":"text","editable":false,"hide":true},{"field":"Range","dataType":"text","editable":false,"hide":true},{"field":"plea","dataType":"text","editable":false,"hide":true},{"field":"particulars","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.details","field":"details","dataType":"hyperlink","link":"/OCDCHGDT"}]'
where
	MODULE_NAME = 'OCDCHGSU'
	and GRID_NAME = 'chargesDlg';

update
	DYNAMIC_GRID_CONFIG
set  modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,CONFIG_JSON = '[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","editable":false},{"field":"description","fieldName":"ocdchgsu.descriptionchild","dataType":"text","editable":false},{"field":"outcome","fieldName":"ocdchgsu.outcome","dataType":"lov","source":"link","url":"ocmpconf/populateOutcome","sourceType":"OCMORCOD","required":true,"editable":true},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"domain","url":"OFFENCE_TYPE","editable":false},{"field":"incidentDate","dataType":"text","editable":false,"hide":true},{"field":"Range","dataType":"text","editable":false,"hide":true},{"field":"plea","dataType":"text","editable":false,"hide":true},{"field":"particulars","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.details","field":"details","dataType":"hyperlink","link":"/OCDCHGDT"}]'
where 
	MODULE_NAME = 'OCDBAILO'
	and GRID_NAME = 'bailCharges';
	