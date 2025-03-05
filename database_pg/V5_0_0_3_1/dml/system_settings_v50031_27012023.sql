insert
	into
	SYSTEM_SETTINGS (SETTING_TYPE,
	SETTING_PROVIDER_CODE,
	SETTING_VALUE,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
values
	 ('INSIGHTS',
'INSIGHTS',
'[{
	"KEY_DESC": "Insights Server Url",
	"KEY_CODE": "INSI_SER_URL",
	"VALUE": ""
}, {
	"KEY_DESC": "Get Insight Auth Token",
	"KEY_CODE": "INS_AUTH_TOKEN",
	"VALUE": "token"
}, {
	"KEY_DESC": "Get Insights User Groups",
	"KEY_CODE": "INS_USER_GROUPS",
	"VALUE": "v2.0/groups"
}, {
	"KEY_DESC": "Get Insights User Id",
	"KEY_CODE": "INS_USER_ID",
	"VALUE": "v2.0/users"
}, {
	"KEY_DESC": "Add Insights User to a Group",
	"KEY_CODE": "INS_ADD_USER_TO_GRP",
	"VALUE": "v2.0/groups"
}, {
	"KEY_DESC": "Search Insight User",
	"KEY_CODE": "INS_SEARCH_USER",
	"VALUE": "v2.0/users"
}, {
	"KEY_DESC": "Create Insight User",
	"KEY_CODE": "INS_CREATE_USER",
	"VALUE": "v2.0/users"
}, {
	"KEY_DESC": "environment ",
	"KEY_CODE": "INS_ENV",
	"VALUE": "enterprise"
}, {
	"KEY_DESC": "siteIdentifier",
	"KEY_CODE": "INS_SITE_IDF",
	"VALUE": ""
}, {
	"KEY_DESC": "authorizationUrl",
	"KEY_CODE": "INS_AUTH_URL",
	"VALUE": "/embeddetail/get"
}, {
	"KEY_DESC": "EmbedSecret",
	"KEY_CODE": "INS_EMB_SEC",
	"VALUE": ""
}]',
current_timestamp,
'OMS_OWNER',
null,
null,
null);