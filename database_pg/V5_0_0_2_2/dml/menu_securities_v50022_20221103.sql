INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id
                                     , menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, dynamic_form) 
VALUES(nextVal('menu_id'), (select menu_id from menu_securities where parent_menu_id=(select menu_id from menu_securities where menu_item='Incidents & Discipline') and menu_item='Maintenance')
                   , 'Staff Report Lock/Unlock configuration', 'OIMSRLUC', 22, current_timestamp,'OMS_OWNER',current_timestamp,NULL, NULL, NULL);

INSERT INTO MENU_SECURITIES(menu_id, parent_menu_id 
                         , menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, dynamic_form)  
VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), (select menu_id from menu_securities where menu_item = 'Work Release')
                  , 'Offender Work Release Projects', 'OIDOWREL', 2, CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP,NULL, NULL, NULL);

INSERT INTO MENU_SECURITIES(menu_id, parent_menu_id 
                           , menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, dynamic_form)  
VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), (select menu_id from menu_securities ms2 where parent_menu_id =(select menu_id from menu_securities ms where menu_item='Community Case Management') and menu_item='Case Management')
                  , 'Case Notes Search', 'OCICNSRC', 2, CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP,NULL, NULL, NULL);

INSERT INTO MENU_SECURITIES (menu_id, parent_menu_id
                           , menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, dynamic_form) 
VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), (select menu_id  from menu_securities ms where parent_menu_id  = (select menu_id from menu_securities ms2 where parent_menu_id =(select menu_id from menu_securities ms where menu_item='Inmate Case Management') and menu_item ='Case Management' ) and menu_item ='Maintenance')
               , 'Maintain Case Note Permissions', 'OCMCNPER', 20, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP,NULL, NULL, NULL); 
			   

