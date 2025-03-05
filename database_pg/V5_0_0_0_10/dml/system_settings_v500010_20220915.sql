UPDATE SYSTEM_SETTINGS SET setting_value = '[{"KEY_DESC":"Application Server Url","KEY_CODE":"APP_SER_URL","VALUE":"http://172.18.0.206:8080"},{"KEY_DESC":"Automation Server Url","KEY_CODE":"AUT_SER_URL","VALUE":"http://172.18.0.207:8080"},{"KEY_DESC":"Sentence Calc Url","KEY_CODE":"SCAL_SER_URL","VALUE":" "},{"KEY_DESC":"Jasper Server Url","KEY_CODE":"JASP_SER_URL","VALUE":" "}]'
WHERE setting_provider_code = 'PRODUCT' and setting_type = 'serverConfig';

update system_settings set setting_value = '[{"KEY_DESC":"Insights Server Url","KEY_CODE":"INSI_SER_URL","VALUE":"https://bi.elitev4.com/bi"},{"KEY_DESC":"Get Insight Auth Token","KEY_CODE":"INS_AUTH_TOKEN","VALUE":"token"},{"KEY_DESC":"Search Insight User","KEY_CODE":"INS_SEARCH_USER","VALUE":"v2.0/users"},{"KEY_DESC":"Create Insight User","KEY_CODE":"INS_CREATE_USER","VALUE":"v2.0/users"},{"KEY_DESC":"environment ","KEY_CODE":"INS_ENV","VALUE":"enterprise"},{"KEY_DESC":"siteIdentifier","KEY_CODE":"INS_SITE_IDF","VALUE":"site/insightstraining"},{"KEY_DESC":"authorizationUrl","KEY_CODE":"INS_AUTH_URL","VALUE":"/embeddetail/get"},{"KEY_DESC":"EmbedSecret","KEY_CODE":"INS_EMB_SEC","VALUE":"B41pfhuUQKSDMbjuDxuDD7lpD8YqIgRq"}]' 
 where setting_type = 'INSIGHTS' and setting_provider_code = 'INSIGHTS';

insert into system_settings (setting_type, setting_provider_code, setting_value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values('serverConfig', 'AUTOMATION_USER', '[{"KEY_DESC":"Automation elite user","KEY_CODE":"AUTOMATION_ELITE_USER","VALUE":"OMS_OWNER"},{"KEY_DESC":"Automation elite password","KEY_CODE":"AUTOMATION_ELITE_PASSWORD","VALUE":"passvalue"}]', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL); 

