/*
--------------------------------------------------------------------------------
--tables that do NOT have column create_datetime as mandatory : column is mandatory
--tables that do NOT have a default on column create_datetimne : column has to have a default
--correct tables where default is NOT current_timestamp
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
   and cols.column_default IS NULL 
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
   AND UPPER(cols.column_default) <> 'CURRENT_TIMESTAMP'
  order by  tabs.table_name;    
  
*/

--populate the column where not available
UPDATE  oms_owner.action_api SET create_datetime = current_timestamp WHERE create_datetime IS NULL;
UPDATE  oms_owner.automation_api_query  SET create_datetime = current_timestamp WHERE create_datetime IS NULL;
UPDATE  oms_owner.automation_query_parameters  SET create_datetime = current_timestamp WHERE create_datetime IS NULL;
UPDATE  oms_owner.bpmn_process  SET create_datetime = current_timestamp WHERE create_datetime IS NULL;
UPDATE  oms_owner.current_offenders  SET create_datetime = current_timestamp WHERE create_datetime IS NULL;
UPDATE  oms_owner.dmn_process  SET create_datetime = current_timestamp WHERE create_datetime IS NULL;
UPDATE  oms_owner.module_dynamic_forms  SET create_datetime = current_timestamp WHERE create_datetime IS NULL;
UPDATE  oms_owner.module_triggers  SET create_datetime = current_timestamp WHERE create_datetime IS NULL;
UPDATE  oms_owner.oms_users_list  SET create_datetime = current_timestamp WHERE create_datetime IS NULL;
UPDATE  oms_owner.sales_order_items_picklist_tmp  SET create_datetime = current_timestamp WHERE create_datetime IS NULL;
UPDATE  oms_owner.work_items  SET create_datetime = current_timestamp WHERE create_datetime IS NULL;


--set to NOT null
ALTER TABLE oms_owner.action_api ALTER COLUMN create_datetime SET NOT NULL;
ALTER TABLE oms_owner.automation_api_query  ALTER COLUMN create_datetime SET NOT NULL;
ALTER TABLE oms_owner.automation_query_parameters  ALTER COLUMN create_datetime SET NOT NULL;
ALTER TABLE oms_owner.bpmn_process  ALTER COLUMN create_datetime SET NOT NULL;
ALTER TABLE oms_owner.current_offenders  ALTER COLUMN create_datetime SET NOT NULL;
ALTER TABLE oms_owner.dmn_process  ALTER COLUMN create_datetime SET NOT NULL;
ALTER TABLE oms_owner.module_dynamic_forms  ALTER COLUMN create_datetime SET NOT NULL;
ALTER TABLE oms_owner.module_triggers  ALTER COLUMN create_datetime SET NOT NULL;
ALTER TABLE oms_owner.oms_users_list  ALTER COLUMN create_datetime SET NOT NULL;
ALTER TABLE oms_owner.sales_order_items_picklist_tmp  ALTER COLUMN create_datetime SET NOT NULL;
ALTER TABLE oms_owner.work_items  ALTER COLUMN create_datetime SET NOT NULL;

--provide a default when none available
ALTER TABLE oms_owner.action_api ALTER COLUMN create_datetime SET DEFAULT current_timestamp;
ALTER TABLE oms_owner.adhoc_email_recipients ALTER COLUMN create_datetime SET DEFAULT current_timestamp;
ALTER TABLE oms_owner.automation_api_query  ALTER COLUMN create_datetime SET DEFAULT current_timestamp;
ALTER TABLE oms_owner.automation_query_parameters  ALTER COLUMN create_datetime SET DEFAULT current_timestamp;
ALTER TABLE oms_owner.bpmn_process  ALTER COLUMN create_datetime SET DEFAULT current_timestamp;
ALTER TABLE oms_owner.create_adhoc_email  ALTER COLUMN create_datetime SET DEFAULT current_timestamp;
ALTER TABLE oms_owner.current_offenders  ALTER COLUMN create_datetime SET DEFAULT current_timestamp;
ALTER TABLE oms_owner.dmn_process  ALTER COLUMN create_datetime SET DEFAULT current_timestamp;
ALTER TABLE oms_owner.module_triggers  ALTER COLUMN create_datetime SET DEFAULT current_timestamp;
ALTER TABLE oms_owner.work_items  ALTER COLUMN create_datetime SET DEFAULT current_timestamp;
ALTER TABLE oms_owner.order_ppsl_cond_activities  ALTER COLUMN create_datetime SET DEFAULT current_timestamp;

--correct wrong DEFAULT for create_datetime
ALTER TABLE oms_owner.agency_count_types  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.agency_incident_charges_ext_inv_detls  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.agency_reporting_locs  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.agy_loc_feed_details  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.case_ord_chg_sent_statuses  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.maintain_tier_default_events  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.maintain_tier_levels  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.off_health_record_dtls  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.off_health_records  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.offender_assoc_p_cnt_notifs  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.offender_assoc_p_notifs  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.offender_assoc_party_notes  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.offender_assoc_prty_contacts  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.offender_associated_parties  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.offender_loc_chng_dtls  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.offender_proposed_intlocs  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.offender_tier_levels  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.referral_staff_categories  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.route_stop_details  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.scheduled_trip_assignments  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.scheduled_trip_details  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.scheduled_trips  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE oms_owner.victim_contact_logs  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.victim_contact_preferences  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE oms_owner.victim_linked_offenders  ALTER COLUMN  create_datetime SET DEFAULT CURRENT_TIMESTAMP;



