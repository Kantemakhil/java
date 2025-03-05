/*
select table_name, information_schema.columns.*  --32  --needs fix
 from	information_schema.columns
where table_schema = 'oms_owner'
  and column_default is not null
  and (   upper(column_default) IS NOT NULL 
      )
  and ( column_name = 'modify_user_id' )  
   order by table_name,column_name;     

*/

ALTER TABLE oms_owner.agency_shift_logs ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.allowable_orders ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.caseload_ded_beneficiaries ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.cash_transfer_txns ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.cash_verifications ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.event_measure_outcomes ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.event_measures ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.far_financial_audits ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.grievance_txns ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.map_modules ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.module_sort_orders ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.module_tab_columns ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.module_tables ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_cip_details ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_comm_sup_histories ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_griev_staffs ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_grievances ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_incident_details ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_int_loc_defaults ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_mon_beneficiaries ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_obli_verifications ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_prog_sess_trgt_gps ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.oic_hearing_comments ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.placement_durations ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.release_date_label_priorities ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.release_date_labels ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.sanction_notices ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.system_messages ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.visiting_groups ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.visiting_members ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.workflow_folders ALTER COLUMN modify_user_id DROP DEFAULT;
ALTER TABLE oms_owner.workflow_screens ALTER COLUMN modify_user_id DROP DEFAULT;


select table_name, information_schema.columns.*  
 from	information_schema.columns
where table_schema = 'oms_owner'
  and column_default is not null
  and (   upper(column_default) IS NOT NULL 
      )
  and ( column_name = 'modify_user_id' )  
   order by table_name,column_name; 
   