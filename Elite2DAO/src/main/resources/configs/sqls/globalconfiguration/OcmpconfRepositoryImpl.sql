OCMPCONF_LOAD_DATA {
	SELECT COMP_ID, COMP_TYPE, CREATED_BY, MODIFIED_BY, CREATE_DATE, MODIFY_DATE, COMP_CONFIG, COMP_CONFIG_DEF FROM COMPONENT_CONFIGS
}
OCMPCONF_UPDATE_DATA {
update COMPONENT_CONFIGS set COMP_CONFIG = :compConfig, modify_datetime = current_timestamp, modify_user_id = :modifyUserId where COMP_TYPE = :compType
}
DYNAMIC_FORM_INSERT {
 insert into MODULE_DYNAMIC_FORMS(FORM_ID, MODULE_NAME, FORM_NAME, FORM_JSON, FORM_IDENTIFIER, create_datetime, create_user_id, modify_datetime) values(nextval('MODULE_DYNAMIC_FORMS_SEQ'), :moduleName, :formName, :formJson::json, :formIdentifier, current_timestamp, :createUserId, NULL) 
}
DYNAMIC_FORM_AUDIT_INSERT {
 insert into MODULE_DYNAMIC_FORMS_AUDIT(FORM_ID, MODULE_NAME, FORM_NAME, FORM_JSON, FORM_IDENTIFIER, create_datetime, create_user_id, modify_datetime) values(:formId, :moduleName, :formName, :formJson::json, :formIdentifier, current_timestamp, :createUserId, NULL) 
}
DYNAMIC_FORM_UPDATE {
	 update MODULE_DYNAMIC_FORMS set MODULE_NAME = :moduleName, FORM_NAME =:formName, FORM_JSON =:formJson::json, FORM_IDENTIFIER =:formIdentifier, modify_user_id =:modifyUserId , modify_datetime =current_timestamp where FORM_ID = :formId 
}
DYNAMIC_FORM_GET {
	SELECT form_id,module_name,form_name,form_json::text,form_identifier,create_datetime,create_user_id,modify_datetime,modify_user_id FROM MODULE_DYNAMIC_FORMS
}
DYNAMIC_FORM_GET_ON_MOD_NAME {
	SELECT * FROM MODULE_DYNAMIC_FORMS WHERE MODULE_NAME = :moduleName
}
DYNAMIC_FORM_CREATE_TABLE {
	create table %formName%_data
	(
	id    bigint primary key,
	form_info_json    json,
	form_identifier    varchar(1000),
	create_datetime    timestamp(9) default current_timestamp,
	create_user_id    varchar(32),
	modify_datetime    timestamp(9),
	modify_user_id    varchar(32)
	)
}
DYNAMIC_FORM_MODULE_INSERT {
 insert into OMS_MODULES(MODULE_NAME, DESCRIPTION, MODULE_TYPE, DYNAMIC_FORM, create_datetime, create_user_id, MODIFY_DATETIME) values(:moduleName, :formName, 'SCREEN', 'Y', current_timestamp, :createUserId, NULL) 
}
DYNAMIC_FORM_CREATE_SYNONYM {
	CREATE PUBLIC SYNONYM  %formName%_DATA FOR %formName%_DATA
}
DYNAMIC_FORM_TABLE_GRANT {
	GRANT SELECT, INSERT, UPDATE, DELETE ON %formName%_DATA TO TAG_USER
}
DYNAMIC_FORM_GET_SEQ {
	SELECT MAX(ID)+1 SEQID FROM %formName%_data
}

DYNAMIC_FORM_SUBMIT_DATA {
 insert into %formName%_data(ID, FORM_INFO_JSON, FORM_IDENTIFIER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME) values(:id, :formInfoJsonBlob, :formIdentifier, current_timestamp, :createUserId, NULL) 
}

DYNAMIC_FORM_UPDATE_SUBMIT_DATA {
	UPDATE %formName%_data SET FORM_INFO_JSON = :formInfoJsonBlob, FORM_IDENTIFIER = :formIdentifier, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID = :modifyUserId WHERE ID = :id
}

DYNAMIC_FORM_LOV_FORM_DATA {
	SELECT FORM_ID "_id", MODULE_NAME "title" FROM MODULE_DYNAMIC_FORMS
}

DYNAMIC_FORM_NESTED_FORM_DETAILS {
	SELECT FORM_ID "_id", MODULE_NAME "title", MODULE_NAME "name", MODULE_NAME "path",'form' "display",'form' "type",form_json::text "components" FROM MODULE_DYNAMIC_FORMS WHERE FORM_ID = :formId  
}

OCMPCONF_DYNAMIC_GRID_CONFIG {
	SELECT 	MODULE_NAME, CONFIG_JSON, GRID_NAME, DB_TABLE_NAME FROM DYNAMIC_GRID_CONFIG
}

DYNAMIC_FORM_UPDATE_ACTION_TYPE {
	update ocdlegls_data set action_type = :actionType, modify_datetime = current_timestamp, modify_user_id = :createUserId where form_identifier  = :formIdentifier 
}

DYNAMIC_FORM_INSERT_OCDLEGLS_HYT {
 insert into ocdlegls_data_hty (form_info_json, form_identifier, action_type, create_datetime, create_user_id, modify_datetime) values(:formInfoJsonBlob, :formIdentifier, :actionType, current_timestamp, :createUserId, NULL) 
}

OCMPCONF_GET_STAFF_ROLES {
	select * from STAFF_MEMBER_ROLES where staff_id in (select staff_id from staff_members sm where user_id =:userId) and role_code  in('SENT_VERIFIER','SENT_OVERRIDER')
}

OCMPCONF_GET_OCDLEGLS_HTY_DATA {
	select id,form_info_json form_info_json_blob,form_identifier,action_type,create_datetime,create_user_id,modify_datetime,modify_user_id from ocdlegls_data_hty odh  where form_identifier  = :formdentifier order by create_datetime desc
}

OCMPCONF_RG_CONDITION_CATEGORY {
	select code, description  from reference_codes rc where domain = 'COM_CON_CAT' and code in (select distinct(category_type)  from community_conditions cc where comm_condition_type = :orderType and active_flag = 'Y')
}
OCMPCONF_GET_ORDER_STATUS {
	select update_reason_code as code,description,active_type,active_flag from legal_update_reasons lur where update_reason_code in (select update_reason_code from legal_update_usages where legal_class=:orderType and active_flag ='Y')
}

OCMPCONF_GET_LEGALS_DATA {
	select form_identifier, form_info_json as form_info_json_blob from ocdleglo_data od
}

OCMPCONF_UPDATE_LEGALS_DATA {
	update ocdleglo_data set form_info_json = :formInfoJsonBlob, modify_user_id = :modifyUserId, modify_datetime = current_timestamp where form_identifier = :formIdentifier
} 

OCMPCONF_GET_STATUS_FLAG {
	select value from legal_settings where code = :code
}

OCMPCONF_GET_RESULTING_STATUS {
	select active_type  from legal_update_reasons where update_reason_code = :status
}

OCMPCONF_GET_LEGALSUMNMARY_DATA {
	select form_identifier, form_info_json as form_info_json_blob from ocdlegls_data od
}

OCMPCONF_GET_CONDITIONS_DATA {
	select condition_status, expiry_date, start_date, offender_sent_condition_id,comm_condition_type,offender_book_id,sentence_seq, ( select allocation_flag from community_conditions where comm_condition_type = osc.COMM_CONDITION_TYPE and category_type = osc.CATEGORY_TYPE and COMM_CONDITION_CODE = osc.COMM_CONDITION_CODE) as allocation_flag from offender_sent_conditions osc
}

OCMPCONF_UPDATE_CONDITIONS {
	update offender_sent_conditions set condition_status = :conditionStatus, modify_user_id = :modifyUserId, modify_datetime = current_timestamp  where offender_sent_condition_id = :offenderSentConditionId
}

OCMPCONF_GET_ALL_ORDER_STATUS {
	select update_reason_code as code,description,active_type,update_reason_code,active_flag,list_seq from legal_update_reasons lur
}

OCMPCONF_GET_LEGAL_UPDATE_USAGES {
select legal_class,update_reason_code,active_flag from legal_update_usages where legal_class = :legalClass
}
OCMPCONF_DELETE_ORDER_FLAG {
select 'Y' from legal_settings ls where code = :code and Value in (select role_code from STAFF_MEMBER_ROLES where staff_id in ( select staff_id from staff_members sm where user_id =:userId))
}

OCMPCONF_GET_CUSTODY_STATUS {
	select custody_status from offender_custody_status where offender_Book_Id = :offenderBookId
}

OCMPCONF_GET_STATUS_DESC {
	select status_description from legal_custody_statuses where status_code = :statusCode
}

DYNAMIC_FORM_DELETE_SUBMIT_DATA{
delete from %formName%_data where id = :id
}
OCMPCONF_GET_COURT_EVENTS {
SELECT *,SENTENCE_SEQ AS SENTENSE_SEQ FROM COURT_EVENTS where offender_book_id=:offender_book_id
}
OCMPCONF_DELETE_PAROLE_EVENTS {
delete from offender_parole_events where offender_book_id = :offenderBookId and parole_event_id = :paroleEventId
}
OCMPCONF_GET_ORDER_DOCUMENTS {
select * from iwp_documents id where object_type = :objectType and object_id = :objectId and offender_book_id  =:offenderBookId
}
OCMPCONF_GET_OCDLEGLS_SEQ_ID {
select nextval('ocdlegls_data_id_seq')
}
OCMPCONF_GET_COMMENCE_TYPE { 
select code, description,active_flag,list_seq,parent_code from reference_codes rc where domain = 'LO_REL_TYPE'
}
DYNAMIC_FORM_GET_SUBMIT_DATA {
	SELECT id,form_info_json form_info_json_blob ,form_identifier,create_datetime,create_user_id,modify_datetime,modify_user_id FROM %formName%_data WHERE LOWER(FORM_IDENTIFIER::text) LIKE LOWER('%
}

