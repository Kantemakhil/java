insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME
                          ,SORT_ORDER
						  ,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG,DYNAMIC_FORM) 
 values(nextval('menu_id'),(select menu_id from menu_securities where MENU_ITEM = 'Inmate Case Management'), 'Offender Observation', null
        ,(select max(sort_order)+ 1 from menu_securities where parent_menu_id =( select menu_id from menu_securities where MENU_ITEM = 'Inmate Case Management'))
		,current_timestamp,'OMS_OWNER',current_timestamp,null,null,'N');


insert into menu_securities(MENU_ID,PARENT_MENU_ID,MENU_ITEM,MODULE_NAME
                           ,SORT_ORDER
						   ,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG,DYNAMIC_FORM)
 values(nextval('menu_id'),(select menu_id from menu_securities where MENU_ITEM = 'Offender Observation' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Inmate Case Management')), 'Maintenance', null 
        ,3
		,current_timestamp,'OMS_OWNER',current_timestamp,null,null,'N');


update menu_securities
set parent_menu_id =(select menu_id from menu_securities where MENU_ITEM = 'Offender Observation' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Inmate Case Management'))
	,sort_order = 1 
	,MODIFY_DATETIME =current_timestamp
	,MODIFY_USER_ID ='OMS_OWNER'
where module_name = 'OIDOFFOB';

update menu_securities
set parent_menu_id =(select menu_id from menu_securities where MENU_ITEM = 'Maintenance' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Offender Observation' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Inmate Case Management')))
   ,sort_order = 1
   ,MODIFY_DATETIME =current_timestamp
   ,MODIFY_USER_ID ='OMS_OWNER'
where module_name = 'OIMOFFOB';

update menu_securities
set parent_menu_id =(select menu_id from menu_securities where MENU_ITEM = 'Offender Observation' and parent_menu_id = (select menu_id from menu_securities where MENU_ITEM = 'Inmate Case Management'))
   ,sort_order = 2
   ,MODIFY_DATETIME =current_timestamp
   ,MODIFY_USER_ID ='OMS_OWNER'	
where module_name = 'OIIOFFOB';


INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME
          , SORT_ORDER
		  , CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
 VALUES(nextval('MENU_ID'),(select menu_id from menu_securities ms where menu_item ='System Search'), 'External Account Balance', 'OIIOBALX'
          ,9
		  , CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL );
		  