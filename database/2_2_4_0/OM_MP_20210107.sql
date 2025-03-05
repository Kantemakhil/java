insert into OMS_MODULES (module_name, description, module_type, appln_code, create_datetime, create_user_id, modify_datetime )
   values('CALSCH','Offender Calendar','SCREEN','OMS4',SYSTIMESTAMP,USER,SYSTIMESTAMP);
   
insert into MODULE_PRIVILEGES (module_name, role_id, access_privilege, verification_flag, create_datetime, create_user_id, modify_datetime) 
   values('CALSCH',444,'A','Y',SYSTIMESTAMP,USER,SYSTIMESTAMP);

COMMIT;
