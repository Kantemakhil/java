--item 1
--Prisoner Case Management -> Work Release, add ‘Offender Work Release Projects’ (OIDOWREL) at #1
INSERT INTO MENU_SECURITIES(menu_id, parent_menu_id 
                         , menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, dynamic_form)  
SELECT NEXTVAL('LABLE_ID_SEQUENCE'), (select menu_id from menu_securities where menu_item = 'Work Release' and parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Prisoner Case Management'))
                  , 'Offender Work Release Projects', 'OIDOWREL', 2, CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP,NULL, NULL, NULL 
   WHERE NOT EXISTS (SELECT 1 FROM menu_securities WHERE module_name = 'OIDOWREL' and parent_menu_id = (select menu_id from menu_securities where menu_item = 'Work Release' and parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Prisoner Case Management')));


				  
UPDATE MENU_SECURITIES 
   SET modify_user_id = 'OMS_OWNER' ,
       modify_datetime = current_timestamp ,
	   sort_order = 1 
 WHERE module_name = 'OIDOWREL' 
   AND parent_menu_id = (select menu_id from menu_securities where menu_item = 'Work Release' and parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Prisoner Case Management'));				  

--Move Prisoner Case Management -> Work Release -> Maintenance to #2. 
UPDATE MENU_SECURITIES 
   SET modify_user_id = 'OMS_OWNER' ,
       modify_datetime = current_timestamp ,
	   sort_order = 2 
 WHERE menu_item = 'Maintenance' 
   AND parent_menu_id = (select menu_id from menu_securities where menu_item = 'Work Release' and parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Prisoner Case Management'));
   
--item 2
--Under Prisoner Case Management -> Work Release -> Maintenance, add Maintain Work Release (OIMWORKR) at #1   
INSERT INTO MENU_SECURITIES(menu_id, parent_menu_id 
                         , menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, dynamic_form)  
VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), (select menu_id from menu_securities where menu_item = 'Work Release')
                  , 'Maintain Work Release', 'OIMWORKR', 1, CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP,NULL, NULL, NULL);

--item 3
--Under Prisoner Case Management -> Legals/Sentence Calculation, add Bail Orders (OCDBAILO) at #12. 				  
insert into OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID
                                   , MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
 values (nextval('MENU_ID'), (select menu_id from menu_securities ms where parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Prisoner Case Management') and menu_item = 'Legals/Sentence Calculation'),
           'Bail Orders', 'OCDBAILO', 12, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);	

--item 4
--Under Community Case Management -> Court Cases & Orders, add Bail Orders (OCDBAILO) at #10. 		
insert into OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID
                                   , MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
 values (nextval('MENU_ID'), (select menu_id from menu_securities ms where parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Community Case Management') and menu_item = 'Court Cases & Orders'),
           'Bail Orders', 'OCDBAILO', 10, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);	   
		   
--item 5
--Under Prisoner Case Management -> Case Management -> Maintenance, add Maintain Case Note Permissions (OCMCNPER) at #4
INSERT INTO MENU_SECURITIES (menu_id, parent_menu_id
                           , menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, dynamic_form) 
VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), (select menu_id  from menu_securities ms where parent_menu_id  = (select menu_id from menu_securities ms2 where parent_menu_id =(select menu_id from menu_securities ms where menu_item='Prisoner Case Management') and menu_item ='Case Management' ) and menu_item ='Maintenance')
               , 'Maintain Case Note Permissions', 'OCMCNPER', 20, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP,NULL, NULL, NULL); 
			   
--item 6
/*
6.	Under Prisoner Case Management, add a new parent menu – Offender Observations. Add the following menu items:
a.	Offender Observations (OIDOFFOB)
b.	Offender Observations Inquiry (OIIOFFOB)
c.	Maintenance
   i.	Maintain Offender Observations (OIMOFFOB)
*/		 
insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME
                          ,SORT_ORDER
						  ,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG,DYNAMIC_FORM) 
 values(nextval('menu_id'),(select menu_id from menu_securities where MENU_ITEM = 'Prisoner Case Management'), 'Offender Observation', null
        ,(select max(sort_order)+ 1 from menu_securities where parent_menu_id =( select menu_id from menu_securities where MENU_ITEM = 'Prisoner Case Management'))
		,current_timestamp,'OMS_OWNER',current_timestamp,null,null, null);

insert into OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID
                                   , MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
 SELECT nextval('MENU_ID'), (select menu_id from menu_securities ms where parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Prisoner Case Management') and menu_item = 'Offender Observation'),
           'Offender Observations', 'OIDOFFOB', 1, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL
   WHERE NOT EXISTS (SELECT 1 FROM menu_securities WHERE module_name = 'OIDOFFOB');	   
	

update menu_securities
set parent_menu_id =(select menu_id from menu_securities where MENU_ITEM = 'Offender Observation' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Prisoner Case Management'))
	,sort_order = 1 
	,MODIFY_DATETIME =current_timestamp
	,MODIFY_USER_ID ='OMS_OWNER'
where module_name = 'OIDOFFOB';

insert into OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID
                                   , MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
 SELECT nextval('MENU_ID'), (select menu_id from menu_securities ms where parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Prisoner Case Management') and menu_item = 'Offender Observation'),
           'Offender Observations Inquiry', 'OIIOFFOB', 2, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL
   WHERE NOT EXISTS (SELECT 1 FROM menu_securities WHERE module_name = 'OIIOFFOB');	   
	

update menu_securities
set parent_menu_id =(select menu_id from menu_securities where MENU_ITEM = 'Offender Observation' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Prisoner Case Management'))
   ,sort_order = 2
   ,MODIFY_DATETIME =current_timestamp
   ,MODIFY_USER_ID ='OMS_OWNER'	
where module_name = 'OIIOFFOB';


insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME
                           ,SORT_ORDER
						   ,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG,DYNAMIC_FORM)
 SELECT nextval('menu_id'),(select menu_id from menu_securities where MENU_ITEM = 'Offender Observation' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Prisoner Case Management')), 'Maintenance', null 
        ,3
		,current_timestamp,'OMS_OWNER',current_timestamp,null,null,null 
   WHERE NOT EXISTS (SELECT 1 FROM menu_securities WHERE menu_item = 'Maintenance' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Offender Observation' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Prisoner Case Management')));	     

insert into OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID
                                   , MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
 SELECT nextval('MENU_ID'), (select menu_id from menu_securities ms where parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Prisoner Case Management') and menu_item = 'Offender Observation'),
           'i.	Maintain Offender Observations', 'OIMOFFOB', 1, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL
   WHERE NOT EXISTS (SELECT 1 FROM menu_securities WHERE module_name = 'OIMOFFOB' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Maintenance' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Offender Observation' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Prisoner Case Management'))));	   
	
		
update menu_securities
set parent_menu_id =(select menu_id from menu_securities where MENU_ITEM = 'Maintenance' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Offender Observation' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Prisoner Case Management')))
   ,sort_order = 1
   ,MODIFY_DATETIME =current_timestamp
   ,MODIFY_USER_ID ='OMS_OWNER'
where module_name = 'OIMOFFOB';		

--item 7
/*
The product menu scripts will also try to add the following screens:

1.	Inmate Case Management -> Visits Management -> Incentives and Earned Privileges Level (OIDIEPLV)
2.	Inmate Case Management -> Visits Management -> Maintain Incentives and Earned Privileges (OIMIEPLV)
3.	Inmate Case Management -> Intake Processing -> Offender External Balances (OIIOBALX)

These screens will also not be added to the client menu as they don’t have ‘Inmate Case Management’ or ‘Intake Processing’. But in case they are added, they should be removed from the menu. 
*/

delete from menu_securities where module_name = 'OIDIEPLV';
delete from menu_securities where module_name = 'OIMIEPLV';
delete from menu_securities where module_name = 'OIIOBALX';