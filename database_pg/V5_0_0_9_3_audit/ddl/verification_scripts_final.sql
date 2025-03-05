----------------------------------------------------------------------------------------------------------------------
--tables that do not have any of the audit column: 1_fix_tables_missing_all_audit_columns.sql
---------------------------------------------------------------------------------------------------------------------
/*
select * from agency_internal_loc_hotspot;
select * from dfmt;
select * from ext_session_local_tz;
select * from facility_placement;
select * from floor_plan;
select * from format_models;
select * from nfmt;
select * from offender_configuration;
select * from offender_deductions_temp;
select * from oms_modules_help;
select * from tdfmt;
select * from tnfmt;
*/
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
--tables that do not have column create_datetime: only journal tables in product 
-- 2_fix_tables_missing_create_datetime_column.sql
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1') 
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list') 
   and RIGHT(tabs.table_name, 3) <> '_jn'  
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
   and cols.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1')    
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list') 
   and RIGHT(tabs.table_name, 3) <> '_jn'   
  order by 1;      

------------------------------------------------------------------------------------------------------------------------------------
--tables that do not have column create_user_id: none 
--3_fix_tables_missing_create_user_id_column.sql
------------------------------------------------------------------------------------------------------------------------------------
select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1') 
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list') 
   and RIGHT(tabs.table_name, 3) <> '_jn'   
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
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list') 
   and RIGHT(tabs.table_name, 3) <> '_jn'   
  order by 1;      
  
 
----------------------------------------------------------------------------------------------------------------
--tables that do not have column modify_datetime: addresses_jn, current_offenders, db_version_control, db_version_control_patches
--                                            --offender_deductions_jn, offenders_jn, oms_users_list, orders_jn
--
--only table oms_users_list matters
--4_fix_tables_missing_modify_datetime_column.sql
----------------------------------------------------------------------------------------------------------------
select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1') 
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list') 
   and RIGHT(tabs.table_name, 3) <> '_jn'   
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
   and cols.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1')        
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list') 
   and RIGHT(tabs.table_name, 3) <> '_jn'   
  order by 1;  


-------------------------------------------------------------------------------------------------------------------
--tables that do not have column modify_user_id: current_offenders, db_version_control, db_version_control_patches
--5_fix_tables_missing_modify_user_id_column.sql
-------------------------------------------------------------------------------------------------------------------

select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1') 
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list') 
   and RIGHT(tabs.table_name, 3) <> '_jn'   
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
   and cols.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1')        
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list') 
   and RIGHT(tabs.table_name, 3) <> '_jn'   
  order by 1;      
 
-------------------------------------------------------------------------------------------------------------------
--tables that do not have column seal flag: assessed_treatment_protocols, comm_cheque_temp, current_offenders, db_version_control, db_version_control_patches
--                                     --elite_patches, module_dynamic_forms, ocuchgou_data, offenders_shift_log, oms_module_report_assets, oms_users_list, 
--                                     --property_group_items, property_groups             
--
--only assessed_treatment_protocols, module_dynamic_forms, ocuchgou_data, offenders_shift_log, oms_module_report_assets, oms_users_list, property_group_items, property_groups
--6_fix_tables_missing_seal_flag_column.sql                                
-------------------------------------------------------------------------------------------------------------------
select tabs.table_name as table_name 
  from information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name NOT IN ('agency_internal_loc_hotspot', 'dfmt', 'ext_session_local_tz', 'facility_placement', 'floor_plan', 'format_models', 'nfmt'
                                , 'offender_configuration', 'offender_deductions_temp', 'oms_modules_help', 'tdfmt', 'tnfmt') 
   and tabs.table_name NOT IN ('elite_generic_log')     
   and tabs.table_name NOT IN ('agency_internal_loc_hotspotba', 'btest', 'floor_plan_back', 'oms_deleted_rows', 'qm_queuetable', 'safe_floor_plan', 'tag_baseline_mb', 'test_bytea', 'testt1') 
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list')  
   and RIGHT(tabs.table_name, 3) <> '_jn'   
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
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list')  
   and RIGHT(tabs.table_name, 3) <> '_jn'   
  order by 1; 
 
 
-------------------------------------------------------------------------------- 
--tables that have column create_user_id columns nullable
--action_api, addresses_jn, automation_api_query,automation_query_parameters
--bpmn_process,ce_audit,current_offenders,dmn_process,images,incident_follow_up_details
--module_dynamic_forms,module_triggers,offender_case_notes,offender_course_attendances
--offender_deductions_jn,offender_external_movements,offenders_jn,oms_module_report
--oms_users_list,or_audit,orders_jn, vine_seeding_source,work_items 
--
--7_fix_tables_with_create_user_id_nullable.sql 
-------------------------------------------------------------------------------- 
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
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list')  
   and RIGHT(tabs.table_name, 3) <> '_jn'
  order by  tabs.table_name;  
  
------------------------------------------------------------------------------------------------------------------------------
--tables that have a default on column create_user_id -- we need to remove the default to force the application to send the user
--
--8_fix_tables_remove_default_for_create_user_id.sql 
-----------------------------------------------------------------------------------------------------------------------------------
 select  count(*) 
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'create_user_id' 
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list')  
   and RIGHT(tabs.table_name, 3) <> '_jn'   
   and cols.column_default IS NOT NULL;
 

--------------------------------------------------------------------------------
--tables that do NOT have column create_datetime as mandatory : column is mandatory
--tables that have a default on column create_datetimne : column no longer has a default
--correct tables where default is NOT current_timestamp
--9_fix_tables_create_datetime_nullable_default.sql
--------------------------------------------------------------------------------
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
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list')  
   and RIGHT(tabs.table_name, 3) <> '_jn'
  order by  tabs.table_name; 
  
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
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list')  
   and RIGHT(tabs.table_name, 3) <> '_jn'  
  order by  tabs.table_name;    

/*
--no longer required
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
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list')  
   and RIGHT(tabs.table_name, 3) <> '_jn' 
  order by  tabs.table_name;   
*/  

---------------------------------------------------------------------------------------------------------------------------------------
--modify_datetime is NOT mandatory --all tables have it not mandatory at the moment except client_object_versions and qm_objects
--modify_datetime should NOT have a default need to remove default
--10_fix_tables_modify_datetime_nullable_default.sql
---------------------------------------------------------------------------------------------------------------------------------------
 select cols.column_name, cols.column_default, cols.is_nullable,tabs.table_name  
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'modify_datetime'
   and cols.is_nullable <> 'YES' 
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list')  
   and RIGHT(tabs.table_name, 3) <> '_jn'   
  order by  tabs.table_name;  
  
select  count(*) 
  from information_schema.columns cols, information_schema.tables tabs
 where tabs.table_catalog = current_database()
   and tabs.table_schema = 'oms_owner' 
   and tabs.table_type = 'BASE TABLE' 
   and tabs.table_name = cols.table_name 
   and cols.table_catalog = current_database()
   and cols.table_schema = 'oms_owner' 
   and cols.column_name = 'modify_datetime'
   and cols.column_default IS NOT NULL 
   and tabs.table_name NOT IN ('db_version_control', 'db_version_control_patches', 'elite_generic_log', 'utility_tables_list')  
   and RIGHT(tabs.table_name, 3) <> '_jn';     
  

--------------------------------------------------------------------------
--non-audit column that use a default of user, or have the mistake of quoted user. 
--these columns should not have a default at all 
--these columns should reflect a true user id value
--20_fix_other_userid_columns.sql
-----------------------------------------------------------------------------------------------------------------------------------
 
select table_name, information_schema.columns.*
 from	information_schema.columns
where table_schema = 'oms_owner'
  and column_default is not null
  and (   upper(column_default) like '%USER%'
      )
   and column_name <> 'create_user_id'   
   and column_name <> 'modify_user_id'  
   order by table_name,column_name;  


-----------------------------------------------------------------------
SELECT count(*) FROM oms_owner.agency_location_amendments  WHERE amend_user  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.agency_location_amendments.amend_user);
SELECT count(*) FROM  oms_owner.agy_int_loc_amendments  WHERE amend_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.agy_int_loc_amendments.amend_user_id);
SELECT count(*) FROM  oms_owner.bio_event_logs   WHERE operator  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.bio_event_logs.operator);
SELECT count(*) FROM  oms_owner.bio_scans   WHERE operator  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.bio_scans.operator);
SELECT count(*) FROM  oms_owner.caseload_payment_profiles  WHERE record_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.caseload_payment_profiles.record_user_id);
SELECT count(*) FROM  oms_owner.client_object_versions WHERE modify_userid  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.client_object_versions.modify_userid);
SELECT count(*) FROM  oms_owner.grievance_reasons   WHERE modified_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.grievance_reasons.modified_user_id);
SELECT count(*) FROM  oms_owner.grievance_types   WHERE modified_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.grievance_types.modified_user_id);
SELECT count(*) FROM  oms_owner.ids_passwords   WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.ids_passwords.user_created);
SELECT count(*) FROM  oms_owner.ids_search_files    WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.ids_search_files.user_created);
SELECT count(*) FROM  oms_owner.iep_levels  WHERE record_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iep_levels.record_user_id);
SELECT count(*) FROM  oms_owner.iwp_bookmark_parameters  WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iwp_bookmark_parameters.user_created);
SELECT count(*) FROM  oms_owner.iwp_bookmarks   WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iwp_bookmarks.user_created);
SELECT count(*) FROM  oms_owner.iwp_documents   WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iwp_documents.user_created);
SELECT count(*) FROM  oms_owner.iwp_parameter_values   WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iwp_parameter_values.user_created);
SELECT count(*) FROM  oms_owner.iwp_templates   WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iwp_templates.user_created);
SELECT count(*) FROM  oms_owner.non_adm_inm_mvmt_details  WHERE recorded_by  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.non_adm_inm_mvmt_details.recorded_by);
SELECT count(*) FROM  oms_owner.offender_fingerprints    WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_fingerprints.user_created);
SELECT count(*) FROM  oms_owner.offender_loc_chng_dtls    WHERE recorded_by  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_loc_chng_dtls.recorded_by);
SELECT count(*) FROM  oms_owner.offender_movement_details   WHERE recorded_by  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_movement_details.recorded_by);
SELECT count(*) FROM  oms_owner.offender_obligation_hty   WHERE modified_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_obligation_hty.modified_user_id);
SELECT count(*) FROM  oms_owner.offender_payment_profiles    WHERE record_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_payment_profiles.record_user_id);
SELECT count(*) FROM  oms_owner.offender_sent_calculations   WHERE record_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_sent_calculations.record_user_id);
SELECT count(*) FROM  oms_owner.offender_visits   WHERE record_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_visits.record_user_id);

----------------------------------------------------------------------