
/*
-------------------------------------------------------------------------------- 
--tables that have column create_user_id columns nullable
--action_api, addresses_jn, automation_api_query,automation_query_parameters
--bpmn_process,ce_audit,current_offenders,dmn_process,images,incident_follow_up_details
--module_dynamic_forms,module_triggers,offender_case_notes,offender_course_attendances
--offender_deductions_jn,offender_external_movements,offenders_jn,oms_module_report
--oms_users_list,or_audit,orders_jn, vine_seeding_source,work_items 
--
--
--only some tables will be updated, see below
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
  order by  tabs.table_name;  
*/

--populate the column where not available
UPDATE  oms_owner.action_api SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.automation_api_query  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.automation_query_parameters  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.bpmn_process  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.ce_audit  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.current_offenders  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.dmn_process  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.images  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.incident_follow_up_details  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.module_dynamic_forms  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.module_triggers  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.offender_case_notes  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.offender_course_attendances  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.offender_external_movements  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.oms_module_report  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.oms_users_list  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.or_audit  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.vine_seeding_source  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;
UPDATE  oms_owner.work_items  SET create_user_id = 'OMS_OWNER' WHERE create_user_id IS NULL;


--make the column NOT Nullable
ALTER TABLE oms_owner.action_api ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.automation_api_query ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.automation_query_parameters ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.bpmn_process ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.ce_audit ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.current_offenders ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.dmn_process ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.images ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.incident_follow_up_details ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.module_dynamic_forms ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.module_triggers ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.offender_case_notes ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.offender_course_attendances ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.offender_external_movements ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.oms_module_report ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.oms_users_list ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.or_audit ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.vine_seeding_source ALTER COLUMN create_user_id SET NOT NULL;
ALTER TABLE oms_owner.work_items ALTER COLUMN create_user_id SET NOT NULL;
