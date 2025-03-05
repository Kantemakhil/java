update menu_securities set parent_menu_id =
(select m.menu_id from menu_securities m  where m.menu_item = 'Security')
where module_name = 'OUMCPASS' and menu_item = 'Change System Password';

update menu_securities set parent_menu_id = (select menu_id from menu_securities ms where 
 ms.menu_item ='Accredited Programs')
where module_name = 'OCDPATTE'
and menu_item = 'Program Bulk Attendance';

update menu_securities set parent_menu_id = (select menu_id from OMS_OWNER.menu_securities where 
 menu_item ='Programs & Services' and parent_menu_id <> 100)
where module_name = 'OCDPATTE'
and menu_item = 'Accredited Program Attendance';

update menu_securities set parent_menu_id = (select menu_id from OMS_OWNER.menu_securities where 
 menu_item ='Community Service')
where module_name = 'OCDUATTE'
and menu_item = 'Community Service Attendance';