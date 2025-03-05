update menu_securities 
   set menu_item = 'Court Actions'
     , parent_menu_id=(select menu_id from menu_securities where menu_item='Legals/Sentence Calculation') 
where module_name = 'OCDENFOR';

update menu_securities 
   set parent_menu_id = (select min(ms1.menu_id) from menu_securities ms1 where ms1.menu_item = 'Programs & Services')
 where module_name = 'OCDPROGR' and menu_item = 'Offender Programs';

update menu_securities 
   set parent_menu_id = (select max(ms1.menu_id) from menu_securities ms1 where ms1.menu_item = 'Programs & Services')
 where module_name = 'OCDPROGR' and menu_item = 'Offender Programs & Evaluation Measures';