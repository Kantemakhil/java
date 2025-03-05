insert into OMS_ROLES(ROLE_ID , ROLE_CODE, ROLE_NAME , ROLE_SEQ , CREATE_DATETIME , CREATE_USER_ID , PARENT_ROLE_CODE , SEAL_FLAG ) values
( nextval('role_id') , 'INCIDENT FOLLOW-UP' ,'Incident Follow-up User' ,1, current_timestamp , 'OMS_OWNER' , NULL , NULL );
