insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='Task Maintenance'),'Team Task Inquiry','OCITTASK',4,current_timestamp,user 
  where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'OCITTASK' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='Task Maintenance'));     

insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='Task Maintenance'),'Offender Task History','OCDOTASK',5,current_timestamp,user 
  where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'OCDOTASK' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='Task Maintenance'));    

insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='System Automation'),'Decisions','DMNMAIN',9,current_timestamp,user 
 where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'DMNMAIN' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='System Automation')); 

insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='System Automation'),'Tasks','OUMCAMTASK',17,current_timestamp,user 
 where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'OUMCAMTASK' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='System Automation')); 
 
insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='System Automation'),'Processes','PROSMAIN',17,current_timestamp,user 
 where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'PROSMAIN' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='System Automation')); 
 
 
insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='System Automation'),'Triggers','CMDWORK',10,current_timestamp,user 
 where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'CMDWORK' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='System Automation'));  
 
insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='Fee Accounts'),'Offender Fee Accounts','OCDOFACC',8,current_timestamp,user 
 where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'OCDOFACC' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='Fee Accounts'));    

insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='Fee Accounts'),'Supervision Status History','OCDSUPST',4,current_timestamp,user 
  where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'OCDSUPST' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='Fee Accounts'));    

insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='Maintenance' and parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='Fee Accounts')),'Fee Account Profile','OCMFAPRO',7,current_timestamp,user 
  where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'OCMFAPRO' AND parent_menu_id = (select MENU_ID from menu_securities where menu_item='Maintenance' and parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='Fee Accounts')));      
 
insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='Fee Accounts'),'Intake Review Required','OCIINTRR',4,current_timestamp,user 
  where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'OCIINTRR' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='Fee Accounts'));    

 
insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='System Automation'),'Quick Actions','CMDACTION',15,current_timestamp,user 
  where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'CMDACTION' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='System Automation')); 
 
insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='System Automation'),'Api Maintenance','APIMAIN',16,current_timestamp,user 
  where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'APIMAIN' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='System Automation'));

insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME,SORT_ORDER,CREATE_DATETIME,CREATE_USER_ID) 
 select NEXTVAL('MENU_ID'),(select MENU_ID from menu_securities where menu_item='System Administration'),'Dynamic Forms','ODYNFRM',100,current_timestamp,user 
 where NOT EXISTS (SELECT 1 FROM MENU_SECURITIES  WHERE MODULE_NAME =  'ODYNFRM' AND parent_menu_id = (select MENU_ID from OMS_OWNER.MENU_SECURITIES where MENU_ITEM='System Administration')); 

