-----------------------------------------------------
--tables that do not have column create_user_id: none
-----------------------------------------------------
/*
-----------------------------------------------------
--tables that do not have column create_user_id: none
-----------------------------------------------------
select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1') 
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
   and cols.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1')                                 
  order by 1;    
*/