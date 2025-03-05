update menu_securities set sort_order =( select count(*)
 from menu_securities ms where parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Demographics' and parent_menu_id =( select menu_id from menu_securities ms3 where menu_item = 'Community Case Management'))) + 1,modify_datetime = CURRENT_TIMESTAMP,modify_user_id= USER where menu_id =( select menu_id from menu_securities ms where menu_item = 'Maintenance' and parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Demographics' and parent_menu_id =( select menu_id from menu_securities ms3 where menu_item = 'Community Case Management')));

update menu_securities set sort_order =( select count(*)
 from menu_securities ms where parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Demographics' and parent_menu_id =( select menu_id from menu_securities ms3 where menu_item = 'Community Case Management'))),modify_datetime = CURRENT_TIMESTAMP,modify_user_id= USER where menu_id =( select menu_id from menu_securities ms where menu_item = 'Keep Separates' and parent_menu_id =( select menu_id from menu_securities ms where menu_item = 'Demographics' and parent_menu_id =( select menu_id from menu_securities ms3 where menu_item = 'Community Case Management')));
 
 
 UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Transaction'),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OCMCOACT' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OCMCOACT');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Transactions'  
and ms1.parent_menu_id in (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Maintenance')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OCMCOACT' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OCMCOACT');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select max(ms2.menu_id) from menu_securities ms2 where ms2.menu_item = 'Deductions')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OCMDEDUT' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OCMDEDUT');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Deductions Maintenance'  
and ms1.parent_menu_id = (select min(ms3.menu_id) from menu_securities ms3 where ms3.menu_item = 'Deductions')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OCMDEDUT' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OCMDEDUT');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Deductions Maintenance'  
and ms1.parent_menu_id = (select min(ms3.menu_id) from menu_securities ms3 where ms3.menu_item = 'Deductions')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OCMMPBAL' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OCMMPBAL');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select max(ms3.menu_id) from menu_securities ms3 where ms3.menu_item = 'Deductions')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OCMMPBAL' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OCMMPBAL');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Transaction'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Financials Maintenance')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OCMTRANS' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OCMTRANS');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Transactions'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Maintenance' and ms3.parent_menu_id =
(select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Community Financials'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OCMTRANS' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OCMTRANS');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Transactions'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Maintenance' and ms3.parent_menu_id =
(select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Community Financials'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OCMTRANS' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OCMTRANS');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Transaction'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Financials Maintenance')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OCMTROPS' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OCMTROPS');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Offender Transactions'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Community Financials')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTDCNTAC' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDCNTAC');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Financials Maintenance'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Trust Accounting')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTDCNTAC' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTDCNTAC');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Trust Accounts'),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTIDTACC';

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Community Financials'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Jail Management System')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTINAMES' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTINAMES');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Inmate Financials'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Jail Management System')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTINAMES' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTINAMES');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Transaction'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Financials Maintenance')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMACPRD' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMACPRD');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Transactions'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Maintenance' and ms3.parent_menu_id =
(select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Community Financials'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMACPRD' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMACPRD');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Deductions Maintenance'),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMALPRO';

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Checks' 
and ms3.parent_menu_id = (select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Community Financials'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMBACCO' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMBACCO');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Checks' 
and ms3.parent_menu_id = (select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Trust Accounting'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMBACCO' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMBACCO');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Checks' 
and ms3.parent_menu_id = (select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Community Financials'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMCNSER' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMCNSER');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Checks' 
and ms3.parent_menu_id = (select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Trust Accounting'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMCNSER' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMCNSER');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Checks' 
and ms3.parent_menu_id = (select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Community Financials'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMCPRIN' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMCPRIN');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Checks' 
and ms3.parent_menu_id = (select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Trust Accounting'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMCPRIN' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMCPRIN');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Deductions Maintenance'),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMCOPRO';

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select max(ms2.menu_id) from menu_securities ms2 where ms2.menu_item = 'Deductions')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMDPRIO' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMDPRIO');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Deductions Maintenance'),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMDPRIO' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMDPRIO');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select max(ms3.menu_id) from menu_securities ms3 where ms3.menu_item = 'Deductions')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMDPRIO' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMDPRIO');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Deductions Maintenance'),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMFOPRO' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMFOPRO');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select max(ms3.menu_id) from menu_securities ms3 where ms3.menu_item = 'Deductions')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMFOPRO' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMFOPRO');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Financials Maintenance'),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMFREEZ';

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Sub Accounts'),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMMBALA';

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Transaction'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Financials Maintenance')),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMONCOA' and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMONCOA');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Transactions'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Maintenance' and ms3.parent_menu_id =
(select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Community Financials'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMONCOA' and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OTMONCOA');

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Deductions Maintenance'),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OTMTFPRO';

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'System Setup'),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OUMAGENC' and menu_item = 'External Agencies/Corporates';

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Programs & Services' 
and ms3.parent_menu_id = (select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Community Case Management'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OUMAGENC' and menu_item = 'Maintain Agencies/Corporates'  and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OUMAGENC' and ms2.menu_item = 'Maintain Agencies/Corporates' );

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Maintenance'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Work Release' 
and ms3.parent_menu_id = (select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Inmate Case Management'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OUMAGENC' and menu_item = 'Maintain Agencies/Corporates'  and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OUMAGENC' and ms2.menu_item = 'Maintain Agencies/Corporates' );

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Payees'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Maintenance' 
and ms3.parent_menu_id = (select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Community Financials'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OUMAGENC' and menu_item = 'Agencies'  and menu_id = (
select max(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OUMAGENC' and ms2.menu_item = 'Agencies' );

UPDATE menu_securities set parent_menu_id = 
(select ms1.menu_id from menu_securities ms1 where ms1.menu_item = 'Payees'  
and ms1.parent_menu_id = (select ms3.menu_id from menu_securities ms3 where ms3.menu_item = 'Financials Maintenance' 
and ms3.parent_menu_id = (select ms4.menu_id from menu_securities ms4 where ms4.menu_item = 'Trust Accounting'))),modify_datetime =current_timestamp,modify_user_id ='OMS_OWNER'
where module_name = 'OUMAGENC' and menu_item = 'Agencies'  and menu_id = (
select min(ms2.menu_id) from menu_securities ms2 where ms2.module_name = 'OUMAGENC' and ms2.menu_item = 'Agencies' );