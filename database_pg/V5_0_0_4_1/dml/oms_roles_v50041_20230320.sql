insert into OMS_ROLES(ROLE_ID , ROLE_NAME , ROLE_SEQ , CREATE_DATETIME , CREATE_USER_ID , ROLE_CODE , PARENT_ROLE_CODE , SEAL_FLAG ) 
 values( nextval('role_id') , 'Health Records' , (SELECT COALESCE(MAX(ROLE_SEQ),0)+1 FROM OMS_ROLES), current_timestamp , 'OMS_OWNER' , 'HEAL_USER' , NULL , NULL );
 
insert into OMS_ROLES(ROLE_ID , ROLE_NAME , ROLE_SEQ , CREATE_DATETIME , CREATE_USER_ID , ROLE_CODE , PARENT_ROLE_CODE , SEAL_FLAG ) 
 values( nextval('role_id') , 'Adv Off Health Records' , ( select coalesce(MAX(ROLE_SEQ), 0)+ 1 from OMS_ROLES), current_timestamp , 'OMS_OWNER' , 'ADV_HEAL_USER' , null , null );