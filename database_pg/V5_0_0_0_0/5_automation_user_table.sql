CREATE TABLE oms_owner.oms_users_list (
	user_seq bigserial NOT NULL ,
	user_id varchar(50) NOT NULL,
	password varchar(4000) NULL,
	create_datetime timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	failed_login_attempts int8 NULL,
	modified_datetime timestamp NULL,
	create_user_id varchar(50) NULL,
	modify_user_id varchar(50) NULL,
	CONSTRAINT oms_users_list_pk PRIMARY KEY (user_id)
)
TABLESPACE tag_data
;

CREATE TABLE bpmn_process (
        process_id bigint NOT NULL,
        process_key varchar(100),
        deploye_id varchar(100),
        process_desc varchar(4000),
        bpmn_file bytea,
        deploy_flag varchar(32),
        history_flag varchar(32),
        create_datetime timestamp,
        create_user_id varchar(32),
        modify_datetime timestamp,
        modify_user_id varchar(32),
        proc_def_id varchar(255),
        def_version bigint,
        trigger_id varchar(4),
        deploy_datetime timestamp,
        deploy_user_id varchar(32)
)  TABLESPACE TAG_DATA;

CREATE TABLE work_items (
        work_id bigint NOT NULL,
        business_function varchar(100),
        process bigint,
        add_trigger varchar(4),
        update_trigger varchar(4),
        delete_trigger varchar(4),
        create_datetime timestamp,
        create_user_id varchar(32),
        modify_datetime timestamp,
        modify_user_id varchar(32),
        trigger_id varchar(4)
)  TABLESPACE TAG_DATA;

CREATE TABLE module_triggers (
        module_name varchar(100),
        dto_name varchar(100),
        create_datetime timestamp,
        create_user_id varchar(32),
        modify_datetime timestamp,
        modify_user_id varchar(32),
        trigger_name varchar(100),
        module_description varchar(200),
        trigger_id varchar(4)
)  TABLESPACE TAG_DATA;

CREATE table DMN_PROCESS
(
DECISION_ID int8,
DEFINITION_KEY VARCHAR(100),
DEFINITION_DESC VARCHAR(4000),
DEPLOYE_ID VARCHAR(100),
DMN_FILE bytea,
DEPLOY_FLAG VARCHAR(32),
HISTORY_FLAG VARCHAR(32),
CREATE_DATETIME timestamp,
CREATE_USER_ID VARCHAR(32),
MODIFY_DATETIME timestamp,
MODIFY_USER_ID VARCHAR(32),
DEFINITION_ID VARCHAR(255),
DEF_VERSION int8,
MODULE VARCHAR(100),
DEPLOY_DATETIME timestamp,
DEPLOY_USER_ID VARCHAR(32)
) TABLESPACE TAG_DATA;

ALTER TABLE bpmn_process ADD CONSTRAINT bpmn_process_forms_pk PRIMARY KEY (process_id) USING INDEX TABLESPACE TAG_DATA;
ALTER TABLE work_items ADD CONSTRAINT work_items_pk PRIMARY KEY (work_id) USING INDEX TABLESPACE TAG_DATA;
ALTER TABLE work_items ADD CONSTRAINT work_items_uk UNIQUE (trigger_id) USING INDEX TABLESPACE TAG_DATA;
ALTER TABLE dmn_process ADD CONSTRAINT dmn_process_pk PRIMARY KEY (decision_id) USING INDEX TABLESPACE TAG_DATA;
alter table OMS_MODULES add PROCESS_WORKFLOW VARCHAR(1);

alter table OMS_MODULES add USER_TASK varchar(1);
CREATE SEQUENCE bpmn_process_process_id_seq INCREMENT 1 MINVALUE 1 NO MAXVALUE START 6561 CACHE 20;
CREATE SEQUENCE dmn_process_process_id_seq INCREMENT 1 MINVALUE 1 NO MAXVALUE START 841 CACHE 20;
CREATE SEQUENCE work_items_work_id_seq INCREMENT 1 MINVALUE 1 NO MAXVALUE START 1381 CACHE 20;
CREATE SEQUENCE IMAGES_IMAGE_ID_SEQ INCREMENT 1 MINVALUE 1 NO MAXVALUE START 13091 CACHE 20;
CREATE SEQUENCE FLOOR_PLAN_FLOOR_PLAN_ID_SEQ INCREMENT 1 MINVALUE 1 NO MAXVALUE START 728 CACHE 20; 

create table MODULE_DYNAMIC_FORMS
(
FORM_ID    BIGINT PRIMARY KEY,
MODULE_NAME    VARCHAR(100),
FORM_NAME    VARCHAR(100),
FORM_JSON    JSON,
FORM_IDENTIFIER    VARCHAR(100),
CREATE_DATETIME    timestamp DEFAULT CURRENT_TIMESTAMP,
CREATE_USER_ID    VARCHAR(32),
MODIFY_DATETIME    timestamp,
MODIFY_USER_ID    VARCHAR(32)
);


CREATE SEQUENCE MODULE_DYNAMIC_FORMS_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE OMS_MODULES ADD DYNAMIC_FORM VARCHAR(1);
ALTER TABLE MENU_SECURITIES ADD DYNAMIC_FORM VARCHAR(1);

CREATE TABLE AUTOMATION_API_QUERY 
   (	QUERY_ID bigint, 
	QUERY_KEY VARCHAR(100),
	QUERY_TEXT VARCHAR(4000), 
	QUERY_DESC VARCHAR(500), 
	ACTIVE_FLAG VARCHAR(1), 
	VERIFIED_BY VARCHAR(100), 
	VERIFIED_DATE timestamp, 
	CREATE_DATETIME timestamp, 
	CREATE_USER_ID VARCHAR(32), 
	MODIFY_DATETIME timestamp, 
	MODIFY_USER_ID VARCHAR(32)
   );
   
ALTER TABLE AUTOMATION_API_QUERY ADD CONSTRAINT AUTOMATION_API_QUERY_PK PRIMARY KEY (QUERY_ID);
create sequence AUTOMATION_QUERY_ID_SEQ start with 1 increment by 1;
 
CREATE TABLE AUTOMATION_QUERY_PARAMETERS
   (	PARAMETER_ID bigint,
	QUERY_KEY VARCHAR(100), 
	PARAMETER_TYPE VARCHAR(50), 
	CREATE_DATETIME timestamp, 
	CREATE_USER_ID VARCHAR(32), 
	MODIFY_DATETIME timestamp, 
	MODIFY_USER_ID VARCHAR(32), 
	PARAMETER_DESCRIPTION VARCHAR(50), 
	PARAMETER_CODE VARCHAR(50)
  ); 

ALTER TABLE AUTOMATION_QUERY_PARAMETERS ADD CONSTRAINT AUTOMATION_QUERY_PARAMETERS_PK PRIMARY KEY (PARAMETER_ID);

create sequence AUTOMATION_QUERY_PARAM_ID_SEQ start with 1 increment by 1;

CREATE TABLE ACTION_API 
   (	API_ID VARCHAR(50), 
	API_DESCRIPTION VARCHAR(50), 
	QUERY_KEY VARCHAR(50), 
	REQUEST_TYPE VARCHAR(10), 
	URL VARCHAR(100), 
	CREATE_DATETIME timestamp, 
	CREATE_USER_ID VARCHAR(32), 
	MODIFY_DATETIME timestamp, 
	MODIFY_USER_ID VARCHAR(32),
	row_id bigserial
   );