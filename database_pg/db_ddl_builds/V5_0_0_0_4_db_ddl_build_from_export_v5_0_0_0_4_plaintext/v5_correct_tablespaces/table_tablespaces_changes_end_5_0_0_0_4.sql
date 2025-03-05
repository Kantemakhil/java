--move default to tag_data ir tag_setup_data
ALTER TABLE oms_owner.action_api SET TABLESPACE tag_setup_data;
ALTER TABLE oms_owner.automation_api_query SET TABLESPACE tag_setup_data;
ALTER TABLE oms_owner.automation_query_parameters SET TABLESPACE tag_setup_data;
ALTER TABLE oms_owner.beneficiary_transactions SET TABLESPACE tag_data;
ALTER TABLE oms_owner.cash_transfer_txns SET TABLESPACE tag_data;
ALTER TABLE oms_owner.dfmt SET TABLESPACE tag_setup_data;
ALTER TABLE oms_owner.ext_session_local_tz SET TABLESPACE tag_setup_data;
ALTER TABLE oms_owner.format_models SET TABLESPACE tag_setup_data;
ALTER TABLE oms_owner.gl_transactions SET TABLESPACE tag_data;
ALTER TABLE oms_owner.merge_transaction_logs SET TABLESPACE tag_data;
ALTER TABLE oms_owner.merge_transactions SET TABLESPACE tag_data;
ALTER TABLE oms_owner.module_dynamic_forms SET TABLESPACE tag_setup_data;
ALTER TABLE oms_owner.module_dynamic_forms_audit SET TABLESPACE tag_setup_data;
ALTER TABLE oms_owner.nfmt SET TABLESPACE tag_setup_data;
ALTER TABLE oms_owner.off_case_note_recipients SET TABLESPACE tag_data;
ALTER TABLE oms_owner.offender_case_notes SET TABLESPACE tag_data;
ALTER TABLE oms_owner.offender_course_attendances SET TABLESPACE tag_data;
ALTER TABLE oms_owner.offender_inter_mvmt_locations SET TABLESPACE tag_data;
ALTER TABLE oms_owner.offender_supervising_courts SET TABLESPACE tag_data;
ALTER TABLE oms_owner.offender_transactions SET TABLESPACE tag_data;
ALTER TABLE oms_owner.task_assignment_hty SET TABLESPACE tag_data;
ALTER TABLE oms_owner.tdfmt SET TABLESPACE tag_setup_data;
ALTER TABLE oms_owner.tnfmt SET TABLESPACE tag_setup_data;
--move default to tag_iwp
ALTER TABLE oms_owner.iwp_documents SET TABLESPACE tag_iwp;
--move iw_templates to iwp
ALTER TABLE oms_owner.iwp_templates SET TABLESPACE tag_iwp;
--move tag_data to tag_jn
ALTER TABLE oms_owner.agency_locations_jn SET TABLESPACE tag_jn;
ALTER TABLE oms_owner.offenders_jn SET TABLESPACE tag_jn;
ALTER TABLE oms_owner.offender_alerts_jn SET TABLESPACE tag_jn;
ALTER TABLE oms_owner.offender_deductions_jn SET TABLESPACE tag_jn;
ALTER TABLE oms_owner.offender_educations_jn SET TABLESPACE tag_jn;
ALTER TABLE oms_owner.orders_jn SET TABLESPACE tag_jn;
ALTER TABLE oms_owner.stock_items_jn SET TABLESPACE tag_jn;
ALTER TABLE oms_owner.addresses_jn SET TABLESPACE tag_jn;




