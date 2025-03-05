
/*
----------------------------------------------------------------------------------------------------------------------------------- 
--non-audit column that use a default of user, or have the mistake of quoted user. 
--these columns should not have a default at all 
--these columns should reflect a true user id value
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
   
   
*/


ALTER TABLE oms_owner.agency_location_amendments  ALTER COLUMN  amend_user DROP DEFAULT;
ALTER TABLE oms_owner.agy_int_loc_amendments  ALTER COLUMN  amend_user_id DROP DEFAULT;
ALTER TABLE oms_owner.bio_event_logs  ALTER COLUMN  operator DROP DEFAULT;
ALTER TABLE oms_owner.bio_scans  ALTER COLUMN  operator DROP DEFAULT;
ALTER TABLE oms_owner.caseload_payment_profiles  ALTER COLUMN  record_user_id DROP DEFAULT;
ALTER TABLE oms_owner.client_object_versions  ALTER COLUMN  modify_userid DROP DEFAULT;
ALTER TABLE oms_owner.grievance_reasons  ALTER COLUMN  modified_user_id DROP DEFAULT;
ALTER TABLE oms_owner.grievance_types  ALTER COLUMN  modified_user_id DROP DEFAULT;
ALTER TABLE oms_owner.ids_passwords  ALTER COLUMN  user_created DROP DEFAULT;
ALTER TABLE oms_owner.ids_search_files  ALTER COLUMN  user_created DROP DEFAULT;
ALTER TABLE oms_owner.iep_levels  ALTER COLUMN  record_user_id DROP DEFAULT;
ALTER TABLE oms_owner.iwp_bookmark_parameters  ALTER COLUMN  user_created DROP DEFAULT;
ALTER TABLE oms_owner.iwp_bookmarks  ALTER COLUMN  user_created DROP DEFAULT;
ALTER TABLE oms_owner.iwp_documents  ALTER COLUMN  user_created DROP DEFAULT;
ALTER TABLE oms_owner.iwp_parameter_values  ALTER COLUMN  user_created DROP DEFAULT;
ALTER TABLE oms_owner.iwp_templates  ALTER COLUMN  user_created DROP DEFAULT;
ALTER TABLE oms_owner.non_adm_inm_mvmt_details  ALTER COLUMN  recorded_by DROP DEFAULT;
ALTER TABLE oms_owner.offender_fingerprints  ALTER COLUMN  user_created DROP DEFAULT;
ALTER TABLE oms_owner.offender_loc_chng_dtls  ALTER COLUMN  recorded_by DROP DEFAULT;
ALTER TABLE oms_owner.offender_movement_details  ALTER COLUMN  recorded_by DROP DEFAULT;
ALTER TABLE oms_owner.offender_obligation_hty  ALTER COLUMN  modified_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_payment_profiles  ALTER COLUMN  record_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_sent_calculations  ALTER COLUMN  record_user_id DROP DEFAULT;
ALTER TABLE oms_owner.offender_visits  ALTER COLUMN  record_user_id DROP DEFAULT;


UPDATE oms_owner.agency_location_amendments  SET   amend_user = 'OMS_OWNER'  WHERE amend_user  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.agency_location_amendments.amend_user);
UPDATE oms_owner.agy_int_loc_amendments  SET   amend_user_id = 'OMS_OWNER'  WHERE amend_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.agy_int_loc_amendments.amend_user_id);
UPDATE oms_owner.bio_event_logs  SET   operator = 'OMS_OWNER'  WHERE operator  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.bio_event_logs.operator);
UPDATE oms_owner.bio_scans  SET   operator = 'OMS_OWNER'  WHERE operator  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.bio_scans.operator);
UPDATE oms_owner.caseload_payment_profiles  SET   record_user_id = 'OMS_OWNER'  WHERE record_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.caseload_payment_profiles.record_user_id);
UPDATE oms_owner.client_object_versions  SET   modify_userid = 'OMS_OWNER'  WHERE modify_userid  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.client_object_versions.modify_userid);
UPDATE oms_owner.grievance_reasons  SET   modified_user_id = 'OMS_OWNER'  WHERE modified_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.grievance_reasons.modified_user_id);
UPDATE oms_owner.grievance_types  SET   modified_user_id = 'OMS_OWNER'  WHERE modified_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.grievance_types.modified_user_id);
UPDATE oms_owner.ids_passwords  SET   user_created = 'OMS_OWNER'  WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.ids_passwords.user_created);
UPDATE oms_owner.ids_search_files  SET   user_created = 'OMS_OWNER'  WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.ids_search_files.user_created);
UPDATE oms_owner.iep_levels  SET   record_user_id = 'OMS_OWNER'  WHERE record_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iep_levels.record_user_id);
UPDATE oms_owner.iwp_bookmark_parameters  SET   user_created = 'OMS_OWNER'  WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iwp_bookmark_parameters.user_created);
UPDATE oms_owner.iwp_bookmarks  SET   user_created = 'OMS_OWNER'  WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iwp_bookmarks.user_created);
UPDATE oms_owner.iwp_documents  SET   user_created = 'OMS_OWNER'  WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iwp_documents.user_created);
UPDATE oms_owner.iwp_parameter_values  SET   user_created = 'OMS_OWNER'  WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iwp_parameter_values.user_created);
UPDATE oms_owner.iwp_templates  SET   user_created = 'OMS_OWNER'  WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.iwp_templates.user_created);
UPDATE oms_owner.non_adm_inm_mvmt_details  SET   recorded_by = 'OMS_OWNER'  WHERE recorded_by  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.non_adm_inm_mvmt_details.recorded_by);
UPDATE oms_owner.offender_fingerprints  SET   user_created = 'OMS_OWNER'  WHERE user_created  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_fingerprints.user_created);
UPDATE oms_owner.offender_loc_chng_dtls  SET   recorded_by = 'OMS_OWNER'  WHERE recorded_by  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_loc_chng_dtls.recorded_by);
UPDATE oms_owner.offender_movement_details  SET   recorded_by = 'OMS_OWNER'  WHERE recorded_by  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_movement_details.recorded_by);
UPDATE oms_owner.offender_obligation_hty  SET   modified_user_id = 'OMS_OWNER'  WHERE modified_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_obligation_hty.modified_user_id);
UPDATE oms_owner.offender_payment_profiles  SET   record_user_id = 'OMS_OWNER'  WHERE record_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_payment_profiles.record_user_id);
UPDATE oms_owner.offender_sent_calculations  SET   record_user_id = 'OMS_OWNER'  WHERE record_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_sent_calculations.record_user_id);
UPDATE oms_owner.offender_visits  SET   record_user_id = 'OMS_OWNER'  WHERE record_user_id  IS NOT NULL AND  NOT EXISTS (SELECT 1 FROM oms_owner.staff_members where oms_owner.staff_members.user_id = oms_owner.offender_visits.record_user_id);

   