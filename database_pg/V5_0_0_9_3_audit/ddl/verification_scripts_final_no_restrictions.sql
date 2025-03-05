--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--tables that do not have column create_datetime:  
/*
addresses_jn
dfmt
elite_generic_log
ext_session_local_tz
external_system_response
format_models
nfmt
offender_deductions_jn
offenders_jn
oms_deleted_rows
orders_jn
qm_queuetable
tdfmt
tnfmt
*/
---

select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
 EXCEPT  
select cols.table_name as table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'create_datetime' 
  order by 1;      
  
  
------------------------------------------------------------------------------------------------------------------------------------
--tables that do not have column create_user_id:    

/*
dfmt
elite_generic_log
ext_session_local_tz
external_system_response
format_models
nfmt
oms_deleted_rows
qm_queuetable
tdfmt
tnfmt
*/
---

select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   EXCEPT  
select cols.table_name as table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'create_user_id' 
  order by 1;  


----------------------------------------------------------------------------------------------------------------
--tables that do not have column modify_datetime:

/*
addresses_jn
current_offenders
db_version_control
db_version_control_patches
dfmt
elite_generic_log
ext_session_local_tz
external_system_response
format_models
nfmt
offender_deductions_jn
offenders_jn
oms_deleted_rows
orders_jn
qm_queuetable
tdfmt
tnfmt
*/
---
select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   EXCEPT  
select cols.table_name as table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'modify_datetime' 
  order by 1;    
  
  
-------------------------------------------------------------------------------------------------------------------
--tables that do not have column modify_user_id:

/*
current_offenders
db_version_control
db_version_control_patches
dfmt
elite_generic_log
ext_session_local_tz
external_system_response
format_models
nfmt
oms_deleted_rows
qm_queuetable
tdfmt
tnfmt
*/

---  

select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   EXCEPT  
select cols.table_name as table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'modify_user_id' 
  order by 1;      
  
-------------------------------------------------------------------------------------------------------------------
--tables that do not have column seal flag:

/*
comm_cheque_temp
current_offenders
db_version_control
db_version_control_patches
dfmt
elite_generic_log
elite_patches
ext_session_local_tz
external_system_response
format_models
nfmt
qm_queuetable
tdfmt
tnfmt
*/
---  
select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   EXCEPT  
select cols.table_name as table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'seal_flag' 
  order by 1; 
  
-------------------------------------------------------------------------------- 
--tables that have column create_user_id columns nullable

/*
addresses_jn
offender_deductions_jn
offenders_jn
orders_jn
*/
---
select cols.column_name, cols.column_default, cols.is_nullable,tabs.table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'create_user_id'
   and cols.is_nullable = 'YES'
  order by  tabs.table_name;  

------------------------------------------------------------------------------------------------------------------------------
--tables that have a default on column create_user_id -- we need to remove the default to force the application to send the user

/*

agency_locations_jn
db_version_control
offender_alerts_jn
offender_educations_jn
db_version_control_patches

*/


---
 select  tabs.table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'create_user_id' 
   and cols.column_default IS NOT NULL;  
   
--------------------------------------------------------------------------------
--tables that do NOT have column create_datetime as mandatory : column is mandatory

---
select cols.column_name, cols.column_default, cols.is_nullable,tabs.table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'create_datetime'
   and cols.is_nullable = 'YES' 
  order by  tabs.table_name; 

--------------------------------------------------------------------------------
--tables that have a default on column create_datetimne : column no longer has a default

---
select cols.column_name, cols.column_default, cols.is_nullable,tabs.table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'create_datetime'
   and cols.column_default IS NOT NULL 
  order by  tabs.table_name;    

--------------------------------------------------------------------------------
/*
--no longer required
--tables where create_datetime default is NOT current_timestamp  

---
select cols.column_name, cols.column_default, cols.is_nullable,tabs.table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'create_datetime'
   and cols.column_default IS NOT NULL 
   AND UPPER(cols.column_default) <> 'CURRENT_TIMESTAMP'   
  order by  tabs.table_name;      
*/

---------------------------------------------------------------------------------------------------------------------------------------
--modify_datetime should NOT have a default

/*

agency_locations_jn
caseload_transaction_types_jn
caseload_account_codes_jn
offender_alerts_jn
offender_educations_jn
stock_items_jn
stock_locations_jn
system_events_jn
system_profiles_jn
transaction_types_jn
transaction_payees_jn
work_assignments_jn
work_rates_jn
court_evnt_sanctions_details
gl_transactions_jn

*/

---
select cols.column_name, cols.column_default, cols.is_nullable,tabs.table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'modify_datetime'
   and cols.column_default IS NOT NULL;     
 

---------------------------------------------------------------------------------------------------------------------------------------
--non-audit user columns that have a default (we cannot have a default) 

/*
oms_users_list
*/
 
select table_name, information_schema.columns.column_name 
 from	information_schema.columns
where table_schema = 'oms_owner'
  and column_default is not null
  and (   upper(column_default) like '%USER%'   )
   and column_name <> 'create_user_id'   
   and column_name <> 'modify_user_id'  
   order by table_name,column_name;     
