insert into DYNAMIC_GRID_CONFIG(MODULE_NAME, CONFIG_JSON, GRID_NAME, DB_TABLE_NAME,create_datetime,create_user_id,modify_datetime,modify_user_id) 
 values('OIMRELSC','
[{
		"field": "dateType",
		"fieldName": "oimrelsc.datetype",
		"dataType": "lov",
		"source": "domain",
		"url": "REL_DATE_TYP",
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
]','keyDatesGrid','RELEASE_SCHEDULE_SETTINGS',current_timestamp,'OMS_OWNER',NULL,NULL);

insert into DYNAMIC_GRID_CONFIG(MODULE_NAME, CONFIG_JSON, GRID_NAME, DB_TABLE_NAME,create_datetime,create_user_id,modify_datetime,modify_user_id) 
 values('OIMRELSC','
[{
		"field": "alertType",
		"fieldName": "oimrelsc.alerttype",
		"dataType": "lov",
		"source": "domain",
		"url": "ALERT",
		"required": true

	},
	{
		"field": "alertCode",
		"fieldName": "oimrelsc.alert",
		"dataType": "lov",
		"source": "domain",
		"url": "ALERT_CODE",
		"parentField": ["alertType"],
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
]','alerts','RELEASE_SCHEDULE_SETTINGS',current_timestamp,'OMS_OWNER',NULL,NULL);

insert into DYNAMIC_GRID_CONFIG(MODULE_NAME, CONFIG_JSON, GRID_NAME, DB_TABLE_NAME,create_datetime,create_user_id,modify_datetime,modify_user_id) 
 values('OIMRELSC','
[{
		"field": "chargeIndicator",
		"fieldName": "oimrelsc.chargeindicator",
		"dataType": "lov",
		"source": "domain",
		"url": "OFFENCE_IND",
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
]','chargeIndicatorGrid','RELEASE_SCHEDULE_SETTINGS',current_timestamp,'OMS_OWNER',NULL,NULL);