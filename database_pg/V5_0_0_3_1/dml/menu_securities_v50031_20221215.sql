INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag, dynamic_form) 
VALUES(nextVal('menu_id'), (select menu_id from menu_securities ms  where parent_menu_Id=(select menu_id  from menu_securities where menu_item='Schedules') and menu_item='Maintenance'), 'Release Schedule Settings', 'OIMRELSC', 5, current_timestamp, 'oms_owner', NULL,NULL, NULL, NULL);

update menu_securities 
set parent_menu_id = (select menu_id from menu_securities where menu_item = 'Correspondence Tracking')
 ,modify_datetime = current_timestamp 
 ,modify_user_id = 'OMS_OWNER' 
where module_name = 'OIDOMAIL';