insert into OMS_OWNER.SYSTEM_SETTINGS
  (SETTING_TYPE,SETTING_PROVIDER_CODE,SETTING_VALUE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME)
values
('EliteDoc','ELITE_DOC','[{"KEY_DESC":"AUTOSAVE TIME", "KEY_CODE":"AUTOSAVE_TIME", "VALUE":"6000"}]',CURRENT_TIMESTAMP, 'OMS_OWNER', NULL);

UPDATE OMS_OWNER.SYSTEM_SETTINGS set SETTING_VALUE = '[{"KEY_DESC":"ENABLE NEW TAB", "KEY_CODE":"ENABLE_NEWTAB", "VALUE":"Y"}, {"KEY_DESC":"AUTOSAVE TIME", "KEY_CODE":"AUTOSAVE_TIME", "VALUE":"6000"}]' where SETTING_TYPE = 'EliteDoc' AND SETTING_PROVIDER_CODE = 'ELITE_DOC';
insert
	into
	OMS_OWNER.SYSTEM_SETTINGS(SETTING_TYPE,
	SETTING_PROVIDER_CODE,
	SETTING_VALUE,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME)
values ('PHONEFORMAT',
'PHONEMASKING',
'[ { "maskingCode": "MOB", "maskingDescription": "Mobile", "maskFormat": "", "sequence": 1, "formatStatus": true, "expiryDate": "" }, { "maskingCode": "LAND", "maskingDescription": "Landline", "maskFormat": "", "sequence": 2, "formatStatus": true, "expiryDate": "" }, { "maskingCode": "INT", "maskingDescription": "International", "maskFormat": "", "sequence": 3, "formatStatus": true, "expiryDate": "" }, { "maskingCode": "LOC", "maskingDescription": "Local Rate", "maskFormat": "", "sequence": 4, "formatStatus": true, "expiryDate": "" } ]',
CURRENT_TIMESTAMP,
'OMS_OWNER',
NULL);

update OMS_OWNER.SYSTEM_SETTINGS set SETTING_VALUE = '[ { "maskingCode": "MOB", "maskingDescription": "Mobile", "maskFormat": "9999-999-999", "sequence": 1, "formatStatus": true, "expiryDate": "" }, { "maskingCode": "LAND", "maskingDescription": "Landline", "maskFormat": "(99) 9999 9999", "sequence": 2, "formatStatus": true, "expiryDate": "" }, { "maskingCode": "INT", "maskingDescription": "International", "maskFormat": "9-999-9999", "sequence": 3, "formatStatus": true, "expiryDate": "" }, { "maskingCode": "LOC", "maskingDescription": "Local Rate", "maskFormat": "999 999 999", "sequence": 4, "formatStatus": true, "expiryDate": "" },{ "maskingCode": "UNF", "maskingDescription": "UnFormatted", "maskFormat": "", "sequence": 5, "formatStatus": true, "expiryDate": "" } ]' where SETTING_TYPE = 'PHONEFORMAT' and SETTING_PROVIDER_CODE = 'PHONEMASKING'; 