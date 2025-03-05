INSERT INTO oms_owner.system_settings(setting_type,setting_provider_code,setting_value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
   VALUES ('serverConfig','PRODUCT','[{ "KEY_DESC":"Application Server Url", "KEY_CODE":"APP_SER_URL", "VALUE":"http://10.60.2.235:4500"}, {"KEY_DESC":"Automation Server Url", "KEY_CODE":"AUT_SER_URL", "VALUE":"http://10.60.2.235:5000"}, {"KEY_DESC": "Insights Server Url", "KEY_CODE": "INSI_SER_URL", "VALUE": " "}, {"KEY_DESC": "Sentence Calc Url", "KEY_CODE": "SCAL_SER_URL", "VALUE": " "}, {"KEY_DESC": "Jasper Server Url", "KEY_CODE": "JASP_SER_URL", "VALUE": " "}]', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO oms_owner.system_settings(setting_type,setting_provider_code,setting_value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES ('eMail','SENDGRID','[{"KEY_DESC":"Mail host", "KEY_CODE":"MAIL_HOST", "VALUE":"smtp.clicksend.com"}, {"KEY_DESC":"Mail from", "KEY_CODE":"MAIL_FROM", "VALUE":"Corrections.Dev@syscon.justice.ca"}, {"KEY_DESC":"Mail port", "KEY_CODE":"MAIL_PORT", "VALUE":"465"}, {"KEY_DESC":"Mail password", "KEY_CODE":"MAIL_PWD", "VALUE":"passvalue"}, {"KEY_DESC":"Mail user", "KEY_CODE":"MAIL_USER", "VALUE":"Corrections.Dev@syscon.justice.ca"}]', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


---
INSERT INTO oms_owner.system_settings(setting_type,setting_provider_code,setting_value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES ('AD','AZUREAD','[{"KEY_DESC":"AD Redirect Uri","KEY_CODE":"AD_RED_URI","VALUE":"https%3A%2F%2Fmvp.sysconsvc.com%3A8081"},{"KEY_DESC":"AD Main Link","KEY_CODE":"AD_LINK_MAIN","VALUE":"https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize?response_type=id_token"},{"KEY_DESC":"AD Scope","KEY_CODE":"AD_SCOPE","VALUE":"openid%20profile%20user.read"},{"KEY_DESC":"AD Client Id","KEY_CODE":"AD_CLIENT_ID","VALUE":"d688fe77-6c8b-4003-873d-9c4636176e1a"},{"KEY_DESC":"AD State","KEY_CODE":"AD_STATE","VALUE":"eyJpZCI6IjZkNzViMzVlLTdkMzAtNGIzOC04NGFhLTcyYjVlMTZiZDM4YSIsInRzIjoxNjA2MTIzNDI1LCJtZXRob2QiOiJwb3B1cEludGVyYWN0aW9uIn0%3D"},{"KEY_DESC":"AD Nonce","KEY_CODE":"AD_NONCE","VALUE":"ab00097a-29ac-4895-87f8-7e8ab5bd1ac7"},{"KEY_DESC":"AD Client Info","KEY_CODE":"AD_CLI_INFO","VALUE":"1&x-client-SKU=MSAL.JS&x-client-Ver=1.4.0"},{"KEY_DESC":"AD Client Request Id","KEY_CODE":"AD_CLI_REQ","VALUE":"fdb0dae2-9f46-492b-875d-c1b3108766c4"}]', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO oms_owner.system_settings(setting_type,setting_provider_code,setting_value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES ('SBINTEGRATION','ASB','[{ "KEY_DESC":"Azure Connection Url", "KEY_CODE":"CONNECTION_URL", "CONNECTION_URL":"Endpoint=sb://sb-aue-dev-elitetest-1.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=7ZY1yLEkom5Gl6hUXn35s9LdFnrD65LaQTFcCtDiz5o=", "QUEUE_NAME":"sbq-elitetesting-inbound"}]', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


update system_settings set setting_provider_code='CLICKSEND' where setting_provider_code='SENDGRID';

INSERT INTO oms_owner.system_settings(setting_type,setting_provider_code,setting_value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES ('eMail','SENDGRID','[{"KEY_DESC":"Sendgrid Api Key","KEY_CODE":"SG_API_KEY","VALUE":"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"}]', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

---
INSERT INTO oms_owner.system_settings(setting_type,setting_provider_code,setting_value, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES ('AD','CLICKSEND_SMS','[{"KEY_DESC":"Clicksend user to post SMS","KEY_CODE":"POST_SMS_USR","VALUE":"Corrections.Dev@syscon.justice.ca"}, {"KEY_DESC":"Clicksend pwd to post SMS","KEY_CODE":"POST_SMS_PWD","VALUE":"L]6g@i6&t#"}]', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

