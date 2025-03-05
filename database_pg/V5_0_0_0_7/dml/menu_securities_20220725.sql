INSERT INTO oms_owner.menu_securities(menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 select NEXTVAL('menu_id'),(select menu_id from oms_owner.menu_securities where menu_item = 'System Setup'), 'System Settings', 'OUMSYSET',10, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OUMSYSET' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'System Setup'));

---
INSERT INTO oms_owner.menu_securities(menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 select NEXTVAL('menu_id'),(select max(menu_id) from oms_owner.menu_securities where menu_item = 'Programs & Services'), 'Maintain Specialized Program Status', 'OCMXPSTM', 5, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OCMXPSTM' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Programs & Services'));

 
---
INSERT INTO oms_owner.menu_securities(menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 select NEXTVAL('menu_id'),(select max(menu_id) from oms_owner.menu_securities where menu_item = 'Programs & Services'), 'Offender Specialized Programs Inquiry', 'OCSPROGR', 7, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OCSPROGR' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Programs & Services'));

----
INSERT INTO oms_owner.menu_securities(menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 select NEXTVAL('menu_id'),(select max(menu_id) from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'), 'Conditions Awaiting Assignment', 'OCONDAWAIT',6, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OCONDAWAIT' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'));

 
----
INSERT INTO oms_owner.menu_securities(menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 select NEXTVAL('menu_id'),(select max(menu_id) from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'), 'Transfer Conditions', 'OCONDTRF',7, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OCONDTRF' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'));

----
INSERT INTO oms_owner.menu_securities(menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 select NEXTVAL('menu_id'),(select max(menu_id) from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'), 'Track Conditions Transfer', 'OTKCONDTRF',8, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OTKCONDTRF' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'));
	
	
  