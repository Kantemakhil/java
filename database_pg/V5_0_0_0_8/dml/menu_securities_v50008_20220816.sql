INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 select NEXTVAL('menu_id'),(select menu_id from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'), 'Non Custodial Orders', 'OCDNCODE', 22, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OCDNCODE' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'));

---
INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 select NEXTVAL('menu_id'),(select menu_id from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'), 'Sanction And Violations', 'OSANVIOS',5, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OSANVIOS' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'));

---
update oms_owner.menu_securities ms set parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Community Service') where module_name = 'OCDUPROJ';

---
INSERT INTO  oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name
         , sort_order
		 , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)  
 select NEXTVAL('menu_id'),(select menu_id from oms_owner.menu_securities where menu_item = 'Community Case Management'), 'Conditions Allocation', null 
        ,(select max(sort_order)+1 from oms_owner.menu_securities where parent_menu_id=(select menu_id from oms_owner.menu_securities where MENU_ITEM='Community Case Management'))
		, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where menu_item =  'Conditions Allocation' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Community Case Management'));

update  oms_owner.menu_securities set parent_menu_id=(select menu_id from oms_owner.menu_securities where MENU_ITEM='Conditions Allocation'),sort_order=1 where module_name='OCONDAWAIT';
update  oms_owner.menu_securities set parent_menu_id=(select menu_id from oms_owner.menu_securities where MENU_ITEM='Conditions Allocation'),sort_order=2 where module_name='OCONDTRF';
update  oms_owner.menu_securities set parent_menu_id=(select menu_id from oms_owner.menu_securities where MENU_ITEM='Conditions Allocation'),sort_order=3 where module_name='OTKCONDTRF';

---
INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 select NEXTVAL('menu_id'),(select menu_id from oms_owner.menu_securities where menu_item = 'System Setup' and parent_menu_id = (select menu_id from  oms_owner.menu_securities where menu_item='System Administration')), 'Module Dashboard Association', 'OUMINSDB',11, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OUMINSDB' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'System Setup' and parent_menu_id = (select menu_id from  oms_owner.menu_securities where menu_item='System Administration')));
 
---
INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 select NEXTVAL('menu_id'),(select menu_id from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'), 'Non Custodial Orders', 'OCDLEGLN', 22, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OCDLEGLN' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Legals/Sentence Calculation'));

---
update oms_owner.menu_securities set parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Community Service') where module_name = 'OCDUPROJ';

---
INSERT INTO oms_owner.menu_securities (menu_id, parent_menu_id, menu_item, module_name, sort_order, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 select NEXTVAL('menu_id'),(select menu_id from oms_owner.menu_securities where menu_item = 'Community Service'), 'Community Service Project Inquiry','OCSPROIN',4, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL 
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.menu_securities  where module_name =  'OCSPROIN' AND parent_menu_id = (select menu_id from oms_owner.menu_securities where menu_item = 'Community Service'));


