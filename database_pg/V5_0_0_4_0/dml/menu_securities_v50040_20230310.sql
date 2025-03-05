insert into MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID,
 MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG) values(NEXTVAL('LABLE_ID_SEQUENCE'), 
(select menu_id from menu_securities where menu_item = 'System Setup' and parent_menu_id 
=(select menu_id from menu_securities where menu_item = 'System Administration')), 'Related Modules', 
'OUMRELMD', 12, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER', null);

INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, seal_flag, MODIFY_DATETIME, MODIFY_USER_ID, dynamic_form) 
VALUES(nextVal('menu_id'), (select menu_id from menu_securities where parent_menu_id=(select menu_id from menu_securities where menu_item='Inmate Case Management') and menu_item ='System Search'), 'My Offenders', 'OIIMYOFF', 12, current_timestamp, 'OMS_OWNER', NULL, current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, seal_flag, MODIFY_DATETIME, MODIFY_USER_ID, dynamic_form) 
VALUES(nextVal('menu_id'), (select menu_id from menu_securities where parent_menu_id=(select menu_id from menu_securities where menu_item='Community Case Management') and menu_item ='Search / Assign'), 'My Offenders', 'OCIMYOFF', 4, current_timestamp, 'OMS_OWNER', NULL, current_timestamp, 'OMS_OWNER', NULL);

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'General Ledger'  
and ms1.parent_menu_id in (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Community Financials'))
where module_name = 'OTDAACCO' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDAACCO');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDAACCO' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDAACCO');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDCLINA' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDCLINA');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Offender Transactions'  
and ms1.parent_menu_id in (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Community Financials'))
where module_name = 'OTDCLOSE' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDCLOSE');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDCLOSE' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDCLOSE');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDDISBU' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDDISBU');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDHIREM' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDHIREM');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDHOLDT' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDHOLDT');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDOFREZ' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDOFREZ');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Offender Transactions'  
and ms1.parent_menu_id in (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Community Financials'))
where module_name = 'OTDOPCTA' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDOPCTA');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDOPCTA' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDOPCTA');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDRDTFU' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDRDTFU');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDRECEI' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDRECEI');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDRTTFU' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDRTTFU');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDSUBAT' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDSUBAT');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Offender Transactions'  
and ms1.parent_menu_id in (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Community Financials'))
where module_name = 'OTDSUBAT' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDSUBAT');

UPDATE menu_securities 
   set modify_datetime = current_timestamp 
	  ,modify_user_id = 'OMS_OWNER'
	  ,parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts')
where module_name = 'OTDTTACC' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDTTACC');