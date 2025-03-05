update menu_securities set modify_datetime = current_timestamp 
   ,modify_user_id = 'OMS_OWNER' 
   ,parent_menu_id =
(select parent_menu_id from menu_securities where module_name='OIDADMIS' and sort_order=2), 
menu_item ='Offender Health' where module_name ='OCDHEALT';

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'),(select max(menu_id) from menu_securities where menu_item='Maintenance' and parent_menu_id =(select menu_id from menu_securities where menu_item='Legals/Sentence Calculation')
), 'Maintain Statuses', 'OCMSTATS',6, current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL);