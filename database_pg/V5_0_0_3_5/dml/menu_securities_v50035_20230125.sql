INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, dynamic_form)
VALUES(nextVal('menu_id'),(select menu_id from oms_owner.menu_securities where menu_item ='Elite Docs') , 'All Offender Documents', 'EOFFENDER', 1, current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, dynamic_form)
VALUES(nextVal('menu_id'),(select  menu_id from oms_owner.menu_securities where menu_item ='Elite Docs') , 'Maintenance', null, 2,current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL, NULL);

update oms_owner.menu_securities 
set modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,parent_menu_id=(select menu_id from menu_securities ms3 where parent_menu_id =(select menu_id from menu_securities ms2 where parent_menu_id=(select menu_id from menu_securities ms where menu_item='System Administration') and menu_item ='Elite Docs') and menu_item='Maintenance')  
where module_name ='OUMDTEMP';

update oms_owner.menu_securities 
set modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,parent_menu_id=(select menu_id from menu_securities ms3 where parent_menu_id =(select menu_id from menu_securities ms2 where parent_menu_id=(select menu_id from menu_securities ms where menu_item='System Administration') and menu_item ='Elite Docs') and menu_item='Maintenance')  
where module_name ='OUMBMARK';

update oms_owner.menu_securities 
  set modify_datetime = current_timestamp
     ,modify_user_id = 'OMS_OWNER' 
     ,parent_menu_id =1 
  where  menu_item ='Elite Docs';