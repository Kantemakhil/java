-------------------------------------------------------------------------------------------------------------------
--tables that do not have column seal flag: assessed_treatment_protocols, comm_cheque_temp, current_offenders, db_version_control, db_version_control_patches
--                                     --elite_patches, module_dynamic_forms, ocuchgou_data, offenders_shift_log, oms_module_report_assets, oms_users_list, 
--                                     --property_group_items, property_groups                                             
-------------------------------------------------------------------------------------------------------------------
/*
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
   and cols.column_name = 'seal_flag' 
   and cols.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1')                                 
  order by 1; 
*/

ALTER TABLE oms_owner.assessed_treatment_protocols ADD COLUMN seal_flag varchar(1) NULL;
ALTER TABLE oms_owner.module_dynamic_forms ADD COLUMN seal_flag varchar(1) NULL;
ALTER TABLE oms_owner.ocuchgou_data ADD COLUMN seal_flag varchar(1) NULL;
ALTER TABLE oms_owner.offenders_shift_log ADD COLUMN seal_flag varchar(1) NULL;
ALTER TABLE oms_owner.oms_module_report_assets ADD COLUMN seal_flag varchar(1) NULL;
ALTER TABLE oms_owner.oms_users_list ADD COLUMN seal_flag varchar(1) NULL;
ALTER TABLE oms_owner.property_group_items ADD COLUMN seal_flag varchar(1) NULL;
ALTER TABLE oms_owner.property_groups ADD COLUMN seal_flag varchar(1) NULL;

ALTER TABLE oms_owner.offender_parole_events ADD COLUMN seal_flag varchar(1) NULL;
