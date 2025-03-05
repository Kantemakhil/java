insert into oms_modules(MODULE_NAME,DESCRIPTION,MODULE_TYPE,APPLN_CODE,CREATE_DATETIME,CREATE_USER_ID,DYNAMIC_FORM) 
  select 'FRMBLDR','Form Builder','SCREEN','CTAG',current_timestamp,user,null 
  where NOT EXISTS (select 1 from oms_modules where module_name = 'FRMBLDR'); 

insert into oms_modules(MODULE_NAME,DESCRIPTION,MODULE_TYPE,APPLN_CODE,CREATE_DATETIME,CREATE_USER_ID,DYNAMIC_FORM) 
 select 'FRMRENDER','Form Renderer','SCREEN','CTAG',current_timestamp,user,null 
 where NOT EXISTS (select 1 from oms_modules where module_name = 'FRMRENDER'); 

insert into oms_modules(MODULE_NAME,DESCRIPTION,MODULE_TYPE,APPLN_CODE,CREATE_DATETIME,CREATE_USER_ID,DYNAMIC_FORM) 
  select 'OCDCHGSU','OCDCHGSU','SCREEN',null,current_timestamp,user,'Y' 
   where NOT EXISTS (select 1 from oms_modules where module_name = 'OCDCHGSU'); 

insert into oms_modules(MODULE_NAME,DESCRIPTION,MODULE_TYPE,APPLN_CODE,CREATE_DATETIME,CREATE_USER_ID,DYNAMIC_FORM) 
  select 'OCDCSCH','Scheduler','SCREEN','CTAG',current_timestamp,user,null 
  where NOT EXISTS (select 1 from oms_modules where module_name = 'OCDCSCH'); 

insert into oms_modules(MODULE_NAME,DESCRIPTION,MODULE_TYPE,APPLN_CODE,CREATE_DATETIME,CREATE_USER_ID,DYNAMIC_FORM) 
  select 'OCDLEGLO','OCDLEGLO','SCREEN',null,current_timestamp,user,'Y' 
  where NOT EXISTS (select 1 from oms_modules where module_name = 'OCDLEGLO'); 

insert into oms_modules(MODULE_NAME,DESCRIPTION,MODULE_TYPE,APPLN_CODE,CREATE_DATETIME,CREATE_USER_ID,DYNAMIC_FORM) 
 select 'OCDLEGLS','OCDLEGLS','SCREEN',null,current_timestamp,user,'Y' 
   where NOT EXISTS (select 1 from oms_modules where module_name = 'OCDLEGLS'); 

insert into oms_modules(MODULE_NAME,DESCRIPTION,MODULE_TYPE,APPLN_CODE,CREATE_DATETIME,CREATE_USER_ID,DYNAMIC_FORM) 
 select 'ODYNFRM','Dynamic Forms','SCREEN','CTAG',current_timestamp,user,null 
   where NOT EXISTS (select 1 from oms_modules where module_name = 'ODYNFRM'); 
 
 