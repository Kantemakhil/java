ALTER TABLE accessible_form_tables ADD CONSTRAINT access_tab_faf_f1 FOREIGN KEY (originating_form,destination_form) REFERENCES form_accessible_forms(originating_form,destination_form) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE accessible_form_tables ADD CONSTRAINT access_tab_oms_mod_f1 FOREIGN KEY (originating_form) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE accessible_form_tables ADD CONSTRAINT access_tab_oms_mod_f2 FOREIGN KEY (destination_form) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE account_codes ADD CONSTRAINT ac_code_ac_code_f5 FOREIGN KEY (parent_account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE account_periods ADD CONSTRAINT ac_prd_ac_prd_f1 FOREIGN KEY (parent_account_period_id) REFERENCES account_periods(account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE address_usages ADD CONSTRAINT address_usages_addresses_fk FOREIGN KEY (address_id) REFERENCES addresses(address_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE adhoc_email_recipients ADD CONSTRAINT adhoc_email_recipients_fk FOREIGN KEY (email_id) REFERENCES create_adhoc_email(email_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_clothing_bundles ADD CONSTRAINT agy_clth_b_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_counts ADD CONSTRAINT agy_count_agy_cnt_t_fk1 FOREIGN KEY (count_type_id) REFERENCES agency_count_types(count_type_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agency_counts ADD CONSTRAINT agy_count_agy_count_fk1 FOREIGN KEY (parent_reporting_loc_id) REFERENCES agency_counts(reporting_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_count_types ADD CONSTRAINT agy_cnt_t_agy_loc_fk1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_incidents ADD CONSTRAINT agy_inc_agy_intl_f1 FOREIGN KEY (internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agency_incidents ADD CONSTRAINT agy_inc_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_incident_asso_tostg ADD CONSTRAINT agy_inc_as_agy_inc_fk1 FOREIGN KEY (agency_incident_id) REFERENCES agency_incidents(agency_incident_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_incident_charges ADD CONSTRAINT agency_incident_charges_fk1 FOREIGN KEY (charged_oic_offence_id) REFERENCES oic_offences(oic_offence_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agency_incident_charges ADD CONSTRAINT agency_incident_charges_fk2 FOREIGN KEY (result_oic_offence_id) REFERENCES oic_offences(oic_offence_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agency_incident_charges ADD CONSTRAINT agy_inc_chg_agy_inc_pty_fk FOREIGN KEY (agency_incident_id,party_seq) REFERENCES agency_incident_parties(agency_incident_id,party_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_incident_decisions ADD CONSTRAINT agy_inc_dec_agy_inc_fk FOREIGN KEY (agency_incident_id) REFERENCES agency_incidents(agency_incident_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_incident_parties ADD CONSTRAINT agy_incp_agy_inc_f1 FOREIGN KEY (agency_incident_id) REFERENCES agency_incidents(agency_incident_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agency_incident_parties ADD CONSTRAINT agy_incp_staff_f1 FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agency_incident_parties ADD CONSTRAINT agy_inc_pty_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agency_incident_parties ADD CONSTRAINT agy_inc_pty_per_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_incident_repairs ADD CONSTRAINT agy_inc_rpr_agy_inc_fk FOREIGN KEY (agency_incident_id) REFERENCES agency_incidents(agency_incident_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_incident_types ADD CONSTRAINT agy_inc_t_agy_inc_fk1 FOREIGN KEY (agency_incident_id) REFERENCES agency_incidents(agency_incident_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_internal_locations ADD CONSTRAINT agy_inc_loc_agy_int_loc_fk FOREIGN KEY (parent_internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agency_internal_locations ADD CONSTRAINT agy_int_loc_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE NOT VALID;
ALTER TABLE agency_location_authorities ADD CONSTRAINT agy_loc_auth_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_location_counts ADD CONSTRAINT agy_loc_c_agy_count_fk1 FOREIGN KEY (reporting_loc_id) REFERENCES agency_counts(reporting_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agency_location_counts ADD CONSTRAINT agy_loc_c_agy_rpt_l_fk1 FOREIGN KEY (count_type_id,agy_seq) REFERENCES agency_reporting_locs(count_type_id,agy_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_reporting_locs ADD CONSTRAINT agy_rep_l_agy_cnt_t_fk1 FOREIGN KEY (count_type_id) REFERENCES agency_count_types(count_type_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_visit_days ADD CONSTRAINT agy_vis_dy_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_visit_slots ADD CONSTRAINT agy_vis_slot_agy_int_loc_fk FOREIGN KEY (internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agency_visit_slots ADD CONSTRAINT agy_vis_slot_agy_vis_dt_fk FOREIGN KEY (agy_loc_id,week_day,time_slot_seq) REFERENCES agency_visit_times(agy_loc_id,week_day,time_slot_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agency_visit_times ADD CONSTRAINT agy_vis_dt_agy_vis_day_fk FOREIGN KEY (agy_loc_id,week_day) REFERENCES agency_visit_days(agy_loc_id,week_day) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agy_inc_investigations ADD CONSTRAINT agy_inc_inv_stf_fk FOREIGN KEY (investigator_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agy_inc_investigations ADD CONSTRAINT agy_inv_agy_inc_pty_fk FOREIGN KEY (agency_incident_id,party_seq) REFERENCES agency_incident_parties(agency_incident_id,party_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agy_inc_inv_statements ADD CONSTRAINT agy_ii_stmt_agy_ii_fk FOREIGN KEY (agy_inc_investigation_id) REFERENCES agy_inc_investigations(agy_inc_investigation_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agy_int_loc_amendments ADD CONSTRAINT agy_il_amends_agy_int_loc_fk FOREIGN KEY (internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agy_int_loc_profiles ADD CONSTRAINT agy_int_loc_prof_agy_int_loc_f FOREIGN KEY (internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agy_loc_establishments ADD CONSTRAINT agy_loc_est_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agy_loc_feed_details ADD CONSTRAINT agy_fed_d_agy_loc_fk1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agy_loc_feed_details ADD CONSTRAINT agy_fed_d_agy_loc_fk2 FOREIGN KEY (feed_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agy_loc_team_functions ADD CONSTRAINT agy_loc_team_fn_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE agy_loc_team_functions ADD CONSTRAINT agy_loc_team_fn_team_fn_fk FOREIGN KEY (team_id,function_type) REFERENCES team_functions(team_id,function_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE agy_prisoner_pay_profiles ADD CONSTRAINT agy_prisoner_pay_profiles_fk1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE allowable_offence_types ADD CONSTRAINT allow_ot_ofn_f1 FOREIGN KEY (offence_code,statute_code) REFERENCES offences(offence_code,statute_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE NOT VALID;
ALTER TABLE allowable_orders ADD CONSTRAINT alw_ord_ord_f1 FOREIGN KEY (order_type) REFERENCES order_types(order_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE areas ADD CONSTRAINT areas_areas_fk FOREIGN KEY (parent_area_code) REFERENCES areas(area_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE arrests ADD CONSTRAINT arrest_agy_loc_f1 FOREIGN KEY (control_arrest_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE arrests ADD CONSTRAINT arrest_agy_loc_f2 FOREIGN KEY (enforcement_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE arrests ADD CONSTRAINT arst_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assessed_offender_needs ADD CONSTRAINT ass_off_needs_fk1 FOREIGN KEY (assessment_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assessed_treatment_protocols ADD CONSTRAINT ass_tre_prots_fk1 FOREIGN KEY (off_ass_need_id) REFERENCES assessed_offender_needs(off_ass_need_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assessments ADD CONSTRAINT assess_assess_f1 FOREIGN KEY (parent_assessment_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assessment_fields ADD CONSTRAINT ass_fld_oms_mod_f1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assessment_field_values ADD CONSTRAINT ass_fdv_ass_f1 FOREIGN KEY (assessment_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE assessment_field_values ADD CONSTRAINT ass_fdv_ass_fld_f1 FOREIGN KEY (module_name,table_name,column_name) REFERENCES assessment_fields(module_name,table_name,column_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assessment_results ADD CONSTRAINT ass_result_assess_f1 FOREIGN KEY (assessment_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assessment_summaries ADD CONSTRAINT ass_summ_cas_pln_fk FOREIGN KEY (offender_book_id,case_plan_id) REFERENCES case_plans(offender_book_id,case_plan_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assessment_supervisions ADD CONSTRAINT ass_sup_assess_f1 FOREIGN KEY (assessment_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assess_section_notifications ADD CONSTRAINT asse_secn_assess_fk1 FOREIGN KEY (assessment_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE assess_section_notifications ADD CONSTRAINT asse_secn_assess_fk2 FOREIGN KEY (next_assessment_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE asset_locations ADD CONSTRAINT ast_loc_ast_loc_f1 FOREIGN KEY (parent_asset_location_id) REFERENCES asset_locations(asset_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE asset_location_assignments ADD CONSTRAINT ast_loa_fxd_ast_f1 FOREIGN KEY (asset_id) REFERENCES fixed_assets(asset_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE asset_location_assignments ADD CONSTRAINT ast_loc_ast_loa_f1 FOREIGN KEY (asset_location_id) REFERENCES asset_locations(asset_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assignments ADD CONSTRAINT asgn_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE assignment_transfers ADD CONSTRAINT afftra_slr_f1 FOREIGN KEY (sac_staff_id_from,cal_agy_loc_id_from,from_date_from,position_from,role_from) REFERENCES staff_location_roles(sac_staff_id,cal_agy_loc_id,from_date,position,role) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE assignment_transfers ADD CONSTRAINT asstra_al_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE assignment_transfers ADD CONSTRAINT asstra_al_to_fk FOREIGN KEY (agy_loc_id_to) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE assignment_transfers ADD CONSTRAINT asstra_slr_f2 FOREIGN KEY (sac_staff_id,cal_agy_loc_id,from_date,position,role) REFERENCES staff_location_roles(sac_staff_id,cal_agy_loc_id,from_date,position,role) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bail_bond_profiles ADD CONSTRAINT bail_bp_corp_fk1 FOREIGN KEY (corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bail_details ADD CONSTRAINT bail_det_corp_f1 FOREIGN KEY (corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE bail_details ADD CONSTRAINT bail_det_per_f1 FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE bail_details ADD CONSTRAINT bail_sts_bail_bp_fk1 FOREIGN KEY (bail_bond_profile_id) REFERENCES bail_bond_profiles(id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bail_detail_charges ADD CONSTRAINT bail_dtlch_bail_dtl_fk1 FOREIGN KEY (order_id,bail_seq) REFERENCES bail_details(order_id,bail_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bank_cheque_books ADD CONSTRAINT bank_cb_csld_cab_f1 FOREIGN KEY (caseload_id,account_code) REFERENCES caseload_current_accounts_base(caseload_id,account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bank_cheque_data ADD CONSTRAINT bnk_cd_csld_cab_f1 FOREIGN KEY (caseload_id,bank_account_code) REFERENCES caseload_current_accounts_base(caseload_id,account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bank_cheque_registers ADD CONSTRAINT bank_cr_csld_cab_f1 FOREIGN KEY (caseload_id,account_code) REFERENCES caseload_current_accounts_base(caseload_id,account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bank_recon_audits ADD CONSTRAINT bank_ra_csld_cab_f1 FOREIGN KEY (caseload_id,account_code) REFERENCES caseload_current_accounts_base(caseload_id,account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bed_assignment_histories ADD CONSTRAINT bed_ah_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE beneficiary_transactions ADD CONSTRAINT ben_txn_off_bnc_fk1 FOREIGN KEY (beneficiary_id) REFERENCES offender_beneficiaries(beneficiary_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE beneficiary_transactions ADD CONSTRAINT ben_txn_txn_typ_fk1 FOREIGN KEY (receipt_txn_type) REFERENCES transaction_types(txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bio_fp_samples ADD CONSTRAINT bio_fp_samples_fk FOREIGN KEY (scan_id,subject_id) REFERENCES bio_scans(scan_id,subject_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bio_scans ADD CONSTRAINT bio_scans_fk FOREIGN KEY (subject_id) REFERENCES bio_subjects(subject_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE bio_subjects ADD CONSTRAINT bio_subjects_offenders_fk FOREIGN KEY (root_offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE bio_subjects ADD CONSTRAINT bio_subjects_persons_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseloads ADD CONSTRAINT csld_fk1 FOREIGN KEY (commissary_trust_caseload) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseloads ADD CONSTRAINT csld_fk2 FOREIGN KEY (trust_commissary_caseload) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseloads ADD CONSTRAINT csld_fk3 FOREIGN KEY (payroll_trust_caseload) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseloads ADD CONSTRAINT csld_fk4 FOREIGN KEY (community_trust_caseload) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_account_codes ADD CONSTRAINT csld_acd_ac_code_f1 FOREIGN KEY (account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_account_codes ADD CONSTRAINT csld_acd_csld_f2 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_account_periods ADD CONSTRAINT csld_ap_ac_prd_f1 FOREIGN KEY (account_period_id) REFERENCES account_periods(account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_account_periods ADD CONSTRAINT csld_ap_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_account_summaries ADD CONSTRAINT csld_ah_csld_ap_f1 FOREIGN KEY (caseload_id,account_period_id) REFERENCES caseload_account_periods(caseload_id,account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_account_summaries ADD CONSTRAINT csld_ah_csld_cab_f1 FOREIGN KEY (caseload_id,account_code) REFERENCES caseload_current_accounts_base(caseload_id,account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_adm_alert_profiles ADD CONSTRAINT csld_admap_sys_msg_fk1 FOREIGN KEY (appln_code,message_number) REFERENCES system_messages(appln_code,message_number) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_adm_other_profiles ADD CONSTRAINT csld_admop_agy_int_loc_fk FOREIGN KEY (living_unit_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_adm_other_profiles ADD CONSTRAINT csld_admop_agy_loc_fk1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_adm_other_profiles ADD CONSTRAINT csld_admop_sys_msg_fk1 FOREIGN KEY (appln_code,message_number) REFERENCES system_messages(appln_code,message_number) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_agency_locations ADD CONSTRAINT csld_al_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_current_accounts_bak ADD CONSTRAINT csld_ac_ac_code_f1 FOREIGN KEY (account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_current_accounts_bak ADD CONSTRAINT csld_ac_ac_prd_f2 FOREIGN KEY (account_period_id) REFERENCES account_periods(account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_current_accounts_bak ADD CONSTRAINT csld_ac_corp_f3 FOREIGN KEY (payee_corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_current_accounts_base ADD CONSTRAINT csld_cab_ac_code_f1 FOREIGN KEY (account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_current_accounts_base ADD CONSTRAINT csld_cab_ac_prd_f1 FOREIGN KEY (account_period_id) REFERENCES account_periods(account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_current_accounts_base ADD CONSTRAINT csld_cab_corp_f3 FOREIGN KEY (payee_corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_current_accounts_txns ADD CONSTRAINT csld_cat_ac_code_f1 FOREIGN KEY (account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_current_accounts_txns ADD CONSTRAINT csld_cat_ac_prd_f2 FOREIGN KEY (account_period_id) REFERENCES account_periods(account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_deduction_details ADD CONSTRAINT csld_dd_dedprof_f1 FOREIGN KEY (caseload_id,deduction_type) REFERENCES caseload_deduction_profiles(caseload_id,deduction_type) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_deduction_details ADD CONSTRAINT csld_dd_txntype_f2 FOREIGN KEY (receipt_txn_type) REFERENCES transaction_types(txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_deduction_profiles ADD CONSTRAINT dedprof_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_deduction_profiles ADD CONSTRAINT dedprof_dedtype_f2 FOREIGN KEY (deduction_type) REFERENCES deduction_types(deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_ded_beneficiaries ADD CONSTRAINT csld_dben_corp_fk1 FOREIGN KEY (corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_ded_beneficiaries ADD CONSTRAINT csld_dben_csld_dp_fk1 FOREIGN KEY (caseload_id,deduction_type) REFERENCES caseload_deduction_profiles(caseload_id,deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_ded_beneficiaries ADD CONSTRAINT csld_dben_per_fk1 FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_format_printers ADD CONSTRAINT csld_fp_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_format_printers ADD CONSTRAINT csld_fp_prtr_f1 FOREIGN KEY (default_printer_id) REFERENCES printers(printer_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_limits ADD CONSTRAINT csld_lim_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_payment_profiles ADD CONSTRAINT csl_pay_prfl_csl_fk FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_printers ADD CONSTRAINT csld_prtr_prtr_f1 FOREIGN KEY (printer_id) REFERENCES printers(printer_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_sequence_numbers ADD CONSTRAINT clsd_sn_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_transaction_types ADD CONSTRAINT csld_tt_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE caseload_transaction_types ADD CONSTRAINT csld_ty_txn_type_f2 FOREIGN KEY (txn_type) REFERENCES transaction_types(txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_works ADD CONSTRAINT csld_wrk_csld_wrkg_f1 FOREIGN KEY (caseload_id,work_group_id) REFERENCES caseload_work_groups(caseload_id,work_group_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE caseload_work_schedules ADD CONSTRAINT csld_wrks_csld_wrk_f1 FOREIGN KEY (caseload_id,work_code) REFERENCES caseload_works(caseload_id,work_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE casework_steps ADD CONSTRAINT cas_ste_pla_dtls_fk FOREIGN KEY (offender_book_id,case_plan_id,detail_seq) REFERENCES plan_details(offender_book_id,case_plan_id,detail_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE case_associated_persons ADD CONSTRAINT case_ass_person_off_booking_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE case_associated_persons ADD CONSTRAINT case_ass_person_persons_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE case_notes ADD CONSTRAINT cas_not_ob_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE case_notes ADD CONSTRAINT cas_not_staff_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE case_plans ADD CONSTRAINT case_plans_f1 FOREIGN KEY (sac_staff_id,cal_agy_loc_id,from_date,position,role) REFERENCES staff_location_roles(sac_staff_id,cal_agy_loc_id,from_date,position,role) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE case_plans ADD CONSTRAINT cas_pln_al_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE case_plans ADD CONSTRAINT cas_pln_ob_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE case_plans ADD CONSTRAINT cas_pln_slr_f2 FOREIGN KEY (inst_sac_staff_id,inst_cal_agy_loc_id,inst_from_date,inst_position,inst_role) REFERENCES staff_location_roles(sac_staff_id,cal_agy_loc_id,from_date,position,role) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE case_plan_goal_conditions ADD CONSTRAINT case_pgc_case_pg_fk1 FOREIGN KEY (goal_type,goal_sub_type) REFERENCES case_plan_goals(goal_type,goal_sub_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE cash_transfer_txns ADD CONSTRAINT cas_tra_t_staf_mem_fk1 FOREIGN KEY (from_user_id) REFERENCES staff_members(user_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE cash_transfer_txns ADD CONSTRAINT cas_tra_t_staf_mem_fk2 FOREIGN KEY (to_user_id) REFERENCES staff_members(user_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE cash_verifications ADD CONSTRAINT cas_veri_csld_fk1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE cash_verifications ADD CONSTRAINT cas_veri_staf_mem_fk1 FOREIGN KEY (user_id) REFERENCES staff_members(user_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE cash_verifications ADD CONSTRAINT cas_veri_staf_mem_fk2 FOREIGN KEY (supervisor_user_id) REFERENCES staff_members(user_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE cash_verifications ADD CONSTRAINT cas_veri_staf_mem_fk3 FOREIGN KEY (reconciled_by) REFERENCES staff_members(user_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE check_list_category_forms ADD CONSTRAINT check_list_categories FOREIGN KEY (caseload_id,check_list_category) REFERENCES check_list_categories(caseload_id,check_list_category) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE check_list_types ADD CONSTRAINT c_lt_c_lc_f1 FOREIGN KEY (caseload_id,check_list_category) REFERENCES check_list_categories(caseload_id,check_list_category) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE committee_meetings ADD CONSTRAINT com_mt_rsp_authl_f1 FOREIGN KEY (response_auth_loc_id) REFERENCES response_authority_locations(response_auth_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE committee_meetings ADD CONSTRAINT com_mt_rsp_autht_f1 FOREIGN KEY (response_auth_time_id) REFERENCES response_authurity_times(response_auth_time_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE community_questions ADD CONSTRAINT comm_qn_com_qn_cat_fk FOREIGN KEY (com_qn_cat_id) REFERENCES community_question_categories(com_qn_cat_id) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE community_question_categories ADD CONSTRAINT com_qn_cat_comm_qaire_fk FOREIGN KEY (comm_qaire_id) REFERENCES community_questionnaires(comm_qaire_id) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_bank_cheque_books ADD CONSTRAINT cm_bank_cb_cm_csld_cab_f1 FOREIGN KEY (caseload_id,account_code) REFERENCES com_csld_current_accounts_base(caseload_id,account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_bank_cheque_data ADD CONSTRAINT cm_bank_cd_cm_csld_cab_f1 FOREIGN KEY (caseload_id,bank_account_code) REFERENCES com_csld_current_accounts_base(caseload_id,account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_bank_cheque_registers ADD CONSTRAINT cm_bank_cr_cm_csld_cab_f1 FOREIGN KEY (caseload_id,account_code) REFERENCES com_csld_current_accounts_base(caseload_id,account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_bank_recon_audits ADD CONSTRAINT cm_bank_ra_cm_csld_cab_f1 FOREIGN KEY (caseload_id,account_code) REFERENCES com_csld_current_accounts_base(caseload_id,account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_caseload_account_periods ADD CONSTRAINT cm_csld_ap_ac_prd_f1 FOREIGN KEY (account_period_id) REFERENCES account_periods(account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_caseload_account_periods ADD CONSTRAINT cm_csld_ap_csld_f2 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_caseload_ac_summaries ADD CONSTRAINT cm_csld_as_cm_csld_ap_f1 FOREIGN KEY (caseload_id,account_period_id) REFERENCES comm_caseload_account_periods(caseload_id,account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_caseload_ac_summaries ADD CONSTRAINT cm_csld_as_cm_csld_cab_f1 FOREIGN KEY (caseload_id,account_code) REFERENCES com_csld_current_accounts_base(caseload_id,account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_category_restrictions ADD CONSTRAINT com_cr_stk_itm_f1 FOREIGN KEY (stock_item_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_gl_transactions ADD CONSTRAINT cm_gl_txn_corp_f1 FOREIGN KEY (payee_corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_gl_transactions ADD CONSTRAINT cm_gl_txn_per_f1 FOREIGN KEY (payee_person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_receipt_maintenances ADD CONSTRAINT crm_oms_mod_fk1 FOREIGN KEY (screen_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_receipt_maintenances ADD CONSTRAINT crm_oms_mod_fk2 FOREIGN KEY (report_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_report_messages ADD CONSTRAINT comm_rep_msg_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_transaction_operations ADD CONSTRAINT cm_txn_o_cm_ac_code_f1 FOREIGN KEY (dr_account_code) REFERENCES comm_account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_transaction_operations ADD CONSTRAINT cm_txn_o_cm_ac_code_f2 FOREIGN KEY (cr_account_code) REFERENCES comm_account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_transaction_operations ADD CONSTRAINT cm_txn_o_cm_ac_code_f3 FOREIGN KEY (bank_dr_account_code) REFERENCES comm_account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_transaction_operations ADD CONSTRAINT cm_txn_o_cm_ac_code_f4 FOREIGN KEY (bank_cr_account_code) REFERENCES comm_account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_unit_spending_limits ADD CONSTRAINT comm_unit_sl_agy_int_loc_fk1 FOREIGN KEY (living_unit_1) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_unit_spending_limits ADD CONSTRAINT comm_unit_sl_agy_int_loc_fk2 FOREIGN KEY (living_unit_2) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_unit_spending_limits ADD CONSTRAINT comm_unit_sl_agy_int_loc_fk3 FOREIGN KEY (living_unit_3) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_unit_spending_limits ADD CONSTRAINT comm_unit_sl_agy_int_loc_fk4 FOREIGN KEY (living_unit_4) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_vendor_receipts ADD CONSTRAINT cm_vrs_cm_vr_f1 FOREIGN KEY (return_id) REFERENCES comm_vendor_returns(return_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_vendor_receipts ADD CONSTRAINT cm_vrs_stk_item_f1 FOREIGN KEY (stock_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE comm_vendor_returns ADD CONSTRAINT cm_vr_corp_f1 FOREIGN KEY (vendor_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_vendor_returns ADD CONSTRAINT cm_vr_stk_item_f1 FOREIGN KEY (return_stock_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE comm_vendor_returns ADD CONSTRAINT cm_vr_stk_item_f2 FOREIGN KEY (received_stock_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE com_bank_recon_audits ADD CONSTRAINT cm_bnk_ra_cm_ac_code_f1 FOREIGN KEY (account_code) REFERENCES comm_account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE com_bank_recon_audits ADD CONSTRAINT cm_bnk_ra_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE com_csld_current_accounts_bak ADD CONSTRAINT cm_csld_ac_ac_prd_f2 FOREIGN KEY (account_period_id) REFERENCES account_periods(account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE com_csld_current_accounts_bak ADD CONSTRAINT cm_csld_ac_corp_f3 FOREIGN KEY (payee_corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE com_csld_current_accounts_bak ADD CONSTRAINT cm_csld_ca_cm_ac_code_f1 FOREIGN KEY (account_code) REFERENCES comm_account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE com_csld_current_accounts_base ADD CONSTRAINT cm_csld_cab_cm_ac_code_f1 FOREIGN KEY (account_code) REFERENCES comm_account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE com_csld_current_accounts_base ADD CONSTRAINT cm_csld_cab_cm_ac_prd_f1 FOREIGN KEY (account_period_id) REFERENCES account_periods(account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE com_csld_current_accounts_base ADD CONSTRAINT cm_csld_cab_corp_f3 FOREIGN KEY (payee_corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE com_csld_current_accounts_txns ADD CONSTRAINT cm_csld_cat_cm_ac_code_f1 FOREIGN KEY (account_code) REFERENCES comm_account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE com_csld_current_accounts_txns ADD CONSTRAINT cm_csld_cat_cm_ac_prd_f2 FOREIGN KEY (account_period_id) REFERENCES account_periods(account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE corporate_types ADD CONSTRAINT corporate_types_corporates_fk FOREIGN KEY (corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE course_activities ADD CONSTRAINT crs_acty_addr_fk FOREIGN KEY (services_address_id) REFERENCES addresses(address_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE course_activities ADD CONSTRAINT crs_acty_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE course_activities ADD CONSTRAINT crs_acty_corp_fk FOREIGN KEY (placement_corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE course_activities ADD CONSTRAINT crs_act_agy_int_loc_fk FOREIGN KEY (internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE course_activities ADD CONSTRAINT crs_act_crs_act_fk FOREIGN KEY (parent_crs_acty_id) REFERENCES course_activities(crs_acty_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE course_activities ADD CONSTRAINT csr_acty_prg_serv_fk FOREIGN KEY (program_id) REFERENCES program_services(program_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE course_activity_areas ADD CONSTRAINT csr_acty_area_crs_acty_fk FOREIGN KEY (crs_acty_id) REFERENCES course_activities(crs_acty_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE course_activity_parties ADD CONSTRAINT crs_acty_pty_crs_acty_fk FOREIGN KEY (crs_acty_id) REFERENCES course_activities(crs_acty_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE course_activity_parties ADD CONSTRAINT cts_acty_pty_per_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE course_activity_parties ADD CONSTRAINT cts_acty_pty_stf_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE course_activity_profiles ADD CONSTRAINT crs_acty_prof_crs_acty_fk FOREIGN KEY (crs_acty_id) REFERENCES course_activities(crs_acty_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE course_activity_reviews ADD CONSTRAINT crs_acty_rev_stf_fk FOREIGN KEY (review_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE course_activity_reviews ADD CONSTRAINT crs_acty_rvw_crs_acty_fk FOREIGN KEY (crs_acty_id) REFERENCES course_activities(crs_acty_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE course_schedules ADD CONSTRAINT crs_sch_crs_sch_fk FOREIGN KEY (catch_up_crs_sch_id) REFERENCES course_schedules(crs_sch_id) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE course_schedules ADD CONSTRAINT csr_sch_crs_acty_fk FOREIGN KEY (crs_acty_id) REFERENCES course_activities(crs_acty_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE course_schedule_rules ADD CONSTRAINT crs_sch_rule_crs_acty_fk FOREIGN KEY (crs_acty_id) REFERENCES course_activities(crs_acty_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE course_schedule_staffs ADD CONSTRAINT crs_sch_stf_crs_sch_fk FOREIGN KEY (crs_sch_id) REFERENCES course_schedules(crs_sch_id) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE course_schedule_staffs ADD CONSTRAINT crs_sch_stf_stf_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE court_events ADD CONSTRAINT court_events_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE court_events ADD CONSTRAINT court_events_off_cases_fk FOREIGN KEY (case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE court_events ADD CONSTRAINT court_events_sch_trips FOREIGN KEY (scheduled_trip_id) REFERENCES scheduled_trips(scheduled_trip_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE court_events ADD CONSTRAINT court_event_off_proceed_fk FOREIGN KEY (offender_proceeding_id) REFERENCES offender_proceedings(offender_proceeding_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE court_events ADD CONSTRAINT crt_events_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE court_event_charges ADD CONSTRAINT court_event_chg_off_chg_fk FOREIGN KEY (offender_charge_id) REFERENCES offender_charges(offender_charge_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE court_event_charges ADD CONSTRAINT crt_event_chg_crt_event_fk FOREIGN KEY (event_id) REFERENCES court_events(event_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE court_event_sentences ADD CONSTRAINT crt_event_sent_crt_event_fk FOREIGN KEY (event_id) REFERENCES court_events(event_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE court_event_sentences ADD CONSTRAINT crt_sent_off_sent_fk FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE curfew_addresses ADD CONSTRAINT curfew_addr_off_curfew_fk FOREIGN KEY (offender_curfew_id) REFERENCES offender_curfews(offender_curfew_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE curfew_addresses ADD CONSTRAINT cur_addr_addr_fk FOREIGN KEY (address_id) REFERENCES addresses(address_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE curfew_address_occupants ADD CONSTRAINT cur_addr_occp_per_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE curfew_address_occupants ADD CONSTRAINT cur_addr_occ_cur_addr_fk FOREIGN KEY (curfew_address_id) REFERENCES curfew_addresses(curfew_address_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE deduction_limit_types ADD CONSTRAINT ded_lt_tp_ded_lt_fk1 FOREIGN KEY (compensation_code,pay_range_code,receipt_type) REFERENCES deduction_limits(compensation_code,pay_range_code,receipt_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE deduction_types ADD CONSTRAINT ded_type_ded_type_fk1 FOREIGN KEY (parent_deduction_type) REFERENCES deduction_types(deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE default_hearing_types ADD CONSTRAINT def_ht_prob_ot_fk1 FOREIGN KEY (order_type,order_code) REFERENCES probation_order_types(order_type,order_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE document_template_queues ADD CONSTRAINT doc_tmp_que_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE document_template_roles ADD CONSTRAINT doc_tmp_ro_doc_temp_fk1 FOREIGN KEY (template_code) REFERENCES document_templates(template_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE document_template_roles ADD CONSTRAINT doc_tmp_ro_oms_role_fk1 FOREIGN KEY (role_id) REFERENCES oms_roles(role_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE durations ADD CONSTRAINT dur_in_status_fk1 FOREIGN KEY (internal_status,int_sts_reason_code) REFERENCES internal_status_reasons(internal_status,int_sts_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE employment_limit_levels ADD CONSTRAINT emp_lev_iep_lev_fk FOREIGN KEY (iep_level,agy_loc_id) REFERENCES iep_levels(iep_level,agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE event_measure_outcomes ADD CONSTRAINT event_measure_outcomes_fk1 FOREIGN KEY (event_measure_id) REFERENCES event_measures(event_measure_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE event_order_types ADD CONSTRAINT eod_pot_fk FOREIGN KEY (order_type,order_code) REFERENCES probation_order_types(order_type,order_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE ext_ownership_transfer ADD CONSTRAINT ext_ot_offptr_f1 FOREIGN KEY (ptr_id) REFERENCES offender_ptr(ptr_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE ext_ownership_transfer ADD CONSTRAINT ext_ot_staff_f1 FOREIGN KEY (ass_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE file_delivery_details ADD CONSTRAINT file_dd_staff_f1 FOREIGN KEY (submitted_by) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE fixed_asset_events ADD CONSTRAINT fxd_ae_fxd_ast_f1 FOREIGN KEY (asset_id) REFERENCES fixed_assets(asset_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE fixed_asset_staffs ADD CONSTRAINT fxd_as_fxd_ast_f1 FOREIGN KEY (asset_id) REFERENCES fixed_assets(asset_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE freeze_disbursements ADD CONSTRAINT fre_dis_act_code_fk1 FOREIGN KEY (account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE freeze_disbursements ADD CONSTRAINT fre_dis_txn_type_fk1 FOREIGN KEY (txn_type) REFERENCES transaction_types(txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE gang_non_associations ADD CONSTRAINT gang_na_gang_f1 FOREIGN KEY (gang_code) REFERENCES gangs(gang_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE gang_non_associations ADD CONSTRAINT gang_na_gang_f2 FOREIGN KEY (ns_gang_code) REFERENCES gangs(gang_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE gl_transactions ADD CONSTRAINT gl_txn_ac_code_f1 FOREIGN KEY (account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE gl_transactions ADD CONSTRAINT gl_txn_ac_prd_f2 FOREIGN KEY (account_period_id) REFERENCES account_periods(account_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE gl_transactions ADD CONSTRAINT gl_txn_csld_f3 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE gl_transactions ADD CONSTRAINT gl_txn_off_bkg_f4 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE gl_transactions ADD CONSTRAINT gl_txn_off_name_f6 FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE gl_transactions ADD CONSTRAINT gl_txn_txn_type_f5 FOREIGN KEY (txn_type) REFERENCES transaction_types(txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE goodtime_rates ADD CONSTRAINT gt_rate_gt_type_f1 FOREIGN KEY (goodtime_type) REFERENCES goodtime_types(goodtime_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE grievance_reasons ADD CONSTRAINT gvnc_rns_gvnc_typ_fk1 FOREIGN KEY (griev_type) REFERENCES grievance_types(griev_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE grievance_txns ADD CONSTRAINT gvnc_txn_gvnc_typ_fk1 FOREIGN KEY (griev_type) REFERENCES grievance_types(griev_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE grouped_obligations ADD CONSTRAINT grp_ob_ded_type_fk1 FOREIGN KEY (deduction_type) REFERENCES deduction_types(deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE grouped_obligations ADD CONSTRAINT grp_ob_ob_grp_fk1 FOREIGN KEY (group_id) REFERENCES obligation_groups(group_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE hdc_board_decisions ADD CONSTRAINT hdc_boards_dec_req_refer_fk FOREIGN KEY (hdc_request_referral_id) REFERENCES hdc_request_referrals(hdc_request_referral_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE hdc_board_decisions ADD CONSTRAINT hdc_boards_dec_staff_mem_fk FOREIGN KEY (chair_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE hdc_governor_decisions ADD CONSTRAINT hdc_gov_dec_req_refer_fk FOREIGN KEY (hdc_request_referral_id) REFERENCES hdc_request_referrals(hdc_request_referral_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE hdc_governor_decisions ADD CONSTRAINT hdc_gov_dec_staff_mem_fk1 FOREIGN KEY (governors_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE hdc_governor_decisions ADD CONSTRAINT hdc_gov_dec_staff_mem_fk2 FOREIGN KEY (referred_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE hdc_prison_staff_comments ADD CONSTRAINT hdc_pri_staf_comm_staff_mem_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE hdc_prison_staff_comments ADD CONSTRAINT hdc_ps_cmt_off_curfew_fk FOREIGN KEY (offender_curfew_id) REFERENCES offender_curfews(offender_curfew_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE hdc_prob_staff_comments ADD CONSTRAINT hdc_prob_staf_com_req_refer_fk FOREIGN KEY (hdc_request_referral_id) REFERENCES hdc_request_referrals(hdc_request_referral_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE hdc_prob_staff_comments ADD CONSTRAINT hdc_prob_staf_com_staff_mem_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE hdc_prob_staff_responses ADD CONSTRAINT hdc_prob_staf_res_req_refer_fk FOREIGN KEY (hdc_request_referral_id) REFERENCES hdc_request_referrals(hdc_request_referral_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE hdc_prob_staff_responses ADD CONSTRAINT hdc_prob_staf_res_staff_mem_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE hdc_request_referrals ADD CONSTRAINT hdc_request_refer_off_book_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE hdc_request_referrals ADD CONSTRAINT hdc_request_ref_staff_mem_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE hdc_request_referrals ADD CONSTRAINT hdc_req_ref_hdc_req_ref_fk FOREIGN KEY (parent_hdc_request_referral_id) REFERENCES hdc_request_referrals(hdc_request_referral_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE hdc_request_referrals ADD CONSTRAINT hdc_req_ref_off_curfew_fk FOREIGN KEY (offender_curfew_id) REFERENCES offender_curfews(offender_curfew_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE ids_search_results ADD CONSTRAINT ids_search_results_fk FOREIGN KEY (search_system_id) REFERENCES ids_search_files(search_system_id) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE iep_levels ADD CONSTRAINT iep_levels_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE image_originals ADD CONSTRAINT image_originals_images_fk FOREIGN KEY (image_id) REFERENCES images(image_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE image_properties ADD CONSTRAINT image_properties_images FOREIGN KEY (image_id) REFERENCES images(image_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE image_report_attributes ADD CONSTRAINT image_reports_fk FOREIGN KEY (report_name,object_name) REFERENCES image_reports(report_name,object_name) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE image_report_columns ADD CONSTRAINT image_reports_fk2 FOREIGN KEY (report_name,object_name) REFERENCES image_reports(report_name,object_name) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE imp_agy_billing_details ADD CONSTRAINT imp_abd_agy_bp_fk1 FOREIGN KEY (bill_to_agency_id,caseload_id,billing_type) REFERENCES agency_billing_profiles(agency_id,caseload_id,billing_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE imp_agy_billing_details ADD CONSTRAINT imp_abm_imp_abd_fk1 FOREIGN KEY (imp_agy_billing_master_id) REFERENCES imp_agy_billing_masters(imp_agy_billing_master_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE incident_cases ADD CONSTRAINT inc_case_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_cases ADD CONSTRAINT inc_case_inc_sts_fk FOREIGN KEY (incident_status) REFERENCES incident_statuses(code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_cases ADD CONSTRAINT inc_case_que_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaires(questionnaire_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_cases ADD CONSTRAINT inc_case_staff_fk FOREIGN KEY (reported_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE incident_case_parties ADD CONSTRAINT inc_case_pty_inc_case_fk FOREIGN KEY (incident_case_id) REFERENCES incident_cases(incident_case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_case_parties ADD CONSTRAINT inc_case_pty_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_case_parties ADD CONSTRAINT inc_case_pty_stf_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE incident_case_questions ADD CONSTRAINT inc_case_que_que_que_fk FOREIGN KEY (questionnaire_que_id) REFERENCES questionnaire_questions(questionnaire_que_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_case_questions ADD CONSTRAINT inc_case_req_inc_case_fk FOREIGN KEY (incident_case_id) REFERENCES incident_cases(incident_case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE incident_case_requirements ADD CONSTRAINT inc_case_req_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_case_requirements ADD CONSTRAINT inc_case_req_inc_case_fk1 FOREIGN KEY (incident_case_id) REFERENCES incident_cases(incident_case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_case_requirements ADD CONSTRAINT inc_case_req_stf_fk FOREIGN KEY (record_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE incident_case_responses ADD CONSTRAINT inc_case_resp_inc_case_que_fk FOREIGN KEY (incident_case_id,question_seq) REFERENCES incident_case_questions(incident_case_id,question_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_case_responses ADD CONSTRAINT inc_case_resp_que_ans_fk FOREIGN KEY (questionnaire_ans_id) REFERENCES questionnaire_answers(questionnaire_ans_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE NOT VALID;
ALTER TABLE incident_questionnaire_hty ADD CONSTRAINT inc_que_hty_inc_case_fk FOREIGN KEY (incident_case_id) REFERENCES incident_cases(incident_case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_questionnaire_hty ADD CONSTRAINT inc_que_hty_que_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaires(questionnaire_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE incident_que_question_hty ADD CONSTRAINT inc_que_que_hty_que_que_fk FOREIGN KEY (questionnaire_que_id) REFERENCES questionnaire_questions(questionnaire_que_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_que_question_hty ADD CONSTRAINT inc_que_que_inc_que_hty_fk FOREIGN KEY (incident_questionnaire_id) REFERENCES incident_questionnaire_hty(incident_questionnaire_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE incident_que_response_hty ADD CONSTRAINT inc_que_resp_hty_que_ans_fk FOREIGN KEY (questionnaire_ans_id) REFERENCES questionnaire_answers(questionnaire_ans_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE incident_que_response_hty ADD CONSTRAINT inc_que_rh_inc_que_hty_fk FOREIGN KEY (incident_questionnaire_id,question_seq) REFERENCES incident_que_question_hty(incident_questionnaire_id,question_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE institution_mini_balances ADD CONSTRAINT inst_mb_ac_code_f1 FOREIGN KEY (account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE interest_transaction_types ADD CONSTRAINT int_tt_ac_code_f1 FOREIGN KEY (trust_account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE interest_transaction_types ADD CONSTRAINT int_tt_txn_types_f1 FOREIGN KEY (txn_type) REFERENCES transaction_types(txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE int_loc_usage_locations ADD CONSTRAINT iloc_usg_loc_iloc_usg_loc_fk FOREIGN KEY (parent_usage_location_id) REFERENCES int_loc_usage_locations(usage_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE int_loc_usage_locations ADD CONSTRAINT int_loc_usg_loc_int_loc_usg_fk FOREIGN KEY (internal_location_usage_id) REFERENCES internal_location_usages(internal_location_usage_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE iwp_bookmarks_groups ADD CONSTRAINT iwp_bk_grp_iwp_bk_fk FOREIGN KEY (bookmark_name) REFERENCES iwp_bookmarks(bookmark_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE iwp_bookmarks_groups ADD CONSTRAINT iwp_bk_grp_iwp_tmpl_fk FOREIGN KEY (template_id) REFERENCES iwp_templates(template_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE iwp_bookmark_parameters ADD CONSTRAINT iwp_para_iwp_bknk_fk FOREIGN KEY (bookmark_name) REFERENCES iwp_bookmarks(bookmark_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE iwp_documents ADD CONSTRAINT iwp_documents_fk9 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE iwp_parameter_mappings ADD CONSTRAINT iwp_param_map_iwp_temp_mod_fk FOREIGN KEY (template_module_id) REFERENCES iwp_template_modules(template_module_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE iwp_parameter_mappings ADD CONSTRAINT iwp_parm_map_iwp_book_parm_fk FOREIGN KEY (parameter_name,bookmark_name) REFERENCES iwp_bookmark_parameters(parameter_name,bookmark_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE iwp_parameter_values ADD CONSTRAINT iwp_para_val_iwp_para_fk FOREIGN KEY (parameter_name,bookmark_name) REFERENCES iwp_bookmark_parameters(parameter_name,bookmark_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE iwp_parameter_values ADD CONSTRAINT iwp_para_val_iwp_tmpl_fk FOREIGN KEY (template_id) REFERENCES iwp_templates(template_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE iwp_templates_object_types ADD CONSTRAINT iwp_temp_obj_type_iwp_templ_fk FOREIGN KEY (template_id) REFERENCES iwp_templates(template_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE iwp_template_modules ADD CONSTRAINT iwp_tag_rel_iwp_tmpl_fk FOREIGN KEY (template_id) REFERENCES iwp_templates(template_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE iwp_template_modules ADD CONSTRAINT iwp_templat_modul_oms_modul_fk FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE iwp_template_objects ADD CONSTRAINT iwp_temp_obj_iwp_templ_fk FOREIGN KEY (template_id) REFERENCES iwp_templates(template_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE iwp_template_roles ADD CONSTRAINT iwp_template_roles_fk1 FOREIGN KEY (role_code) REFERENCES oms_roles(role_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE iwp_template_roles ADD CONSTRAINT iwp_tmpl_role_iwp_tmpl_fk FOREIGN KEY (template_id) REFERENCES iwp_templates(template_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE legal_update_usages ADD CONSTRAINT lgl_upd_usg_legl_upd_rsn_fk FOREIGN KEY (update_reason_code) REFERENCES legal_update_reasons(update_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE link_case_txns ADD CONSTRAINT link_case_txns_fk3 FOREIGN KEY (event_id) REFERENCES court_events(event_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE link_case_txns ADD CONSTRAINT link_case_txn_off_case_fk1 FOREIGN KEY (case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE link_case_txns ADD CONSTRAINT link_case_txn_off_case_fk2 FOREIGN KEY (combined_case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE link_case_txns ADD CONSTRAINT link_case_txn_off_chg_fk FOREIGN KEY (offender_charge_id) REFERENCES offender_charges(offender_charge_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE magnetic_output_temp ADD CONSTRAINT mag_ot_mag_sm_fk1 FOREIGN KEY (msm_id) REFERENCES magnetic_submission_maint(msm_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE magnetic_submission_maint ADD CONSTRAINT mag_sm_csld_fk1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE magnetic_submission_maint ADD CONSTRAINT mag_sm_oms_mod_fk1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE magnetic_submission_maint ADD CONSTRAINT mag_sm_prtr_fk1 FOREIGN KEY (printer_id) REFERENCES printers(printer_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE map_modules ADD CONSTRAINT mod_map_oms_mod_fk1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE medical_categories_6i ADD CONSTRAINT med_cat_med_qutn_6i_f1 FOREIGN KEY (medical_questionaire_code) REFERENCES medical_questionaires_6i(medical_questionaire_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE medical_questions_6i ADD CONSTRAINT med_que_med_qutn_6i_f1 FOREIGN KEY (medical_questionaire_code) REFERENCES medical_questionaires_6i(medical_questionaire_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE menu_securities ADD CONSTRAINT menu_sec_oms_mod_fk1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE merge_transaction_processes ADD CONSTRAINT merg_txn_proc_merg_proc_fk FOREIGN KEY (process_id) REFERENCES merge_processes(process_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE minimum_payable_balances ADD CONSTRAINT min_pb_ac_code_f1 FOREIGN KEY (account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE modify_tables ADD CONSTRAINT modify_tab_move_rsn_f1 FOREIGN KEY (movement_type,movement_reason_code) REFERENCES movement_reasons(movement_type,movement_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE module_accessible_documents ADD CONSTRAINT mod_ac_doc_doc_temp_fk1 FOREIGN KEY (template_code) REFERENCES document_templates(template_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE NOT VALID;

ALTER TABLE module_accessible_documents ADD CONSTRAINT mod_ac_doc_oms_mod_fk1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE module_privileges ADD CONSTRAINT mod_priv_oms_mod_f1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE module_privileges ADD CONSTRAINT mod_priv_oms_role_f1 FOREIGN KEY (role_id) REFERENCES oms_roles(role_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE NOT VALID;
ALTER TABLE module_sort_orders ADD CONSTRAINT mod_sord_oms_mod_fk1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE module_tables ADD CONSTRAINT mod_tabs_oms_mod_fk1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE module_tab_columns ADD CONSTRAINT mod_tabc_mod_tabs_fk1 FOREIGN KEY (module_tab_id) REFERENCES module_tables(module_tab_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE module_tab_columns ADD CONSTRAINT mod_tabc_oms_mod_fk1 FOREIGN KEY (setup_module) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE module_versions ADD CONSTRAINT mod_ver_oms_mod_f1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE non_admitted_inmate_mvmts ADD CONSTRAINT non_aim_agy_loc_fk1 FOREIGN KEY (from_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE non_admitted_inmate_mvmts ADD CONSTRAINT non_aim_agy_loc_fk2 FOREIGN KEY (to_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE non_admitted_inmate_mvmts ADD CONSTRAINT non_aim_agy_loc_fk3 FOREIGN KEY (alternate_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE non_admitted_inmate_mvmts ADD CONSTRAINT non_aim_sch_trps_fk1 FOREIGN KEY (scheduled_trip_id) REFERENCES scheduled_trips(scheduled_trip_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE non_adm_inm_mvmt_details ADD CONSTRAINT non_aim_non_aimd_fk1 FOREIGN KEY (non_adm_inmate_id) REFERENCES non_admitted_inmate_mvmts(non_adm_inmate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE notify_movement_types ADD CONSTRAINT not_mt_move_rsn_f1 FOREIGN KEY (movement_type,movement_reason_code) REFERENCES movement_reasons(movement_type,movement_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE notify_movement_types ADD CONSTRAINT not_mt_off_rn_f1 FOREIGN KEY (offender_book_id,noti_seq) REFERENCES offender_release_notis(offender_book_id,noti_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offences ADD CONSTRAINT offen_ho_codes_fk FOREIGN KEY (ho_code) REFERENCES ho_codes(ho_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offences ADD CONSTRAINT ofn_stt_f1 FOREIGN KEY (statute_code) REFERENCES statutes(statute_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offences ADD CONSTRAINT ofn_stt_f2 FOREIGN KEY (old_statute_code) REFERENCES statutes(statute_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offence_code_groupings ADD CONSTRAINT ofn_cg_ofn_f1 FOREIGN KEY (offence_code,statute_code) REFERENCES offences(offence_code,statute_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offence_group_var_scores ADD CONSTRAINT ofn_gvs_ofn_gv_f1 FOREIGN KEY (offence_group,assess_variable_code) REFERENCES offence_group_variables(offence_group,assess_variable_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offence_indicators ADD CONSTRAINT off_ind_offence_fk1 FOREIGN KEY (offence_code,statute_code) REFERENCES offences(offence_code,statute_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offenders ADD CONSTRAINT off_off_f1 FOREIGN KEY (alias_offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_action_plans ADD CONSTRAINT off_action_plns_fk1 FOREIGN KEY (off_crim_need_id) REFERENCES offender_criminogenic_needs(off_crim_need_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_action_plans ADD CONSTRAINT off_action_plns_fk2 FOREIGN KEY (off_case_cond_id) REFERENCES offender_case_conditions(off_case_cond_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_action_plans ADD CONSTRAINT off_action_plns_fk3 FOREIGN KEY (program_id) REFERENCES program_services(program_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_activity_events ADD CONSTRAINT off_ae_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_adjustment_txns ADD CONSTRAINT off_ad_txn_off_deduct_fk1 FOREIGN KEY (offender_deduction_id) REFERENCES offender_deductions(offender_deduction_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_alerts ADD CONSTRAINT off_alert_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_applications ADD CONSTRAINT off_app_al_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_appl_sentences ADD CONSTRAINT oas_off_app_fk FOREIGN KEY (off_app_id) REFERENCES offender_applications(off_app_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_assessments ADD CONSTRAINT off_ass_agy_loc_f1 FOREIGN KEY (place_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_assessments ADD CONSTRAINT off_ass_agy_loc_f2 FOREIGN KEY (review_place_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_assessments ADD CONSTRAINT off_ass_agy_loc_f3 FOREIGN KEY (assessment_create_location) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_assessments ADD CONSTRAINT off_ass_ass_f1 FOREIGN KEY (assessment_type_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_assessments ADD CONSTRAINT off_ass_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_assessments ADD CONSTRAINT off_ass_staff_f2 FOREIGN KEY (assessor_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_assessment_items ADD CONSTRAINT off_ai_assess_f1 FOREIGN KEY (assessment_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_assessment_items ADD CONSTRAINT off_ai_assess_f2 FOREIGN KEY (parent_assessment_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_assets ADD CONSTRAINT off_ase_book_id_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_associated_parties ADD CONSTRAINT off_assp_off_cases_fk1 FOREIGN KEY (case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_associated_parties ADD CONSTRAINT off_v_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_assoc_party_notes ADD CONSTRAINT off_apn_off_ap_fk1 FOREIGN KEY (associated_party_id) REFERENCES offender_associated_parties(associated_party_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_assoc_prty_contacts ADD CONSTRAINT off_apc_off_ap_fk1 FOREIGN KEY (associated_party_id) REFERENCES offender_associated_parties(associated_party_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_assoc_prty_contacts ADD CONSTRAINT off_apc_person_fk1 FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_assoc_p_cnt_notifs ADD CONSTRAINT off_apcn_off_apc_fk1 FOREIGN KEY (associated_party_id,party_seq) REFERENCES offender_assoc_prty_contacts(associated_party_id,party_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_assoc_p_notifs ADD CONSTRAINT off_apnf_off_ap_fk1 FOREIGN KEY (associated_party_id) REFERENCES offender_associated_parties(associated_party_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_authorised_visitors ADD CONSTRAINT off_auth_visitors_off_book_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_authorised_visitors ADD CONSTRAINT off_auth_visitors_off_book_fk2 FOREIGN KEY (visitor_offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_authorised_visitors ADD CONSTRAINT off_auth_visitors_persons_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_bail_details ADD CONSTRAINT off_baild_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_balances ADD CONSTRAINT off_bal_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_balances ADD CONSTRAINT off_bal_off_name_f1 FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_beneficiaries ADD CONSTRAINT off_bnc_corp_f1 FOREIGN KEY (corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_beneficiaries ADD CONSTRAINT off_bnc_off_ded_f1 FOREIGN KEY (offender_deduction_id) REFERENCES offender_deductions(offender_deduction_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_beneficiaries ADD CONSTRAINT off_bnc_per_f1 FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_bookings ADD CONSTRAINT off_bkg_agy_int_loc_fk FOREIGN KEY (living_unit_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_bookings ADD CONSTRAINT off_bkg_agy_int_loc_fk2 FOREIGN KEY (agency_iml_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_bookings ADD CONSTRAINT off_bkg_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_bookings ADD CONSTRAINT off_bkg_off_f1 FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_booking_agy_locs ADD CONSTRAINT offagy_ob_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_booking_agy_locs ADD CONSTRAINT offagy_offptr_f1 FOREIGN KEY (ptr_id) REFERENCES offender_ptr(ptr_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_booking_details ADD CONSTRAINT off_bkg_dtl_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_booking_events ADD CONSTRAINT off_bkge_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_cases ADD CONSTRAINT off_cs_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_cases ADD CONSTRAINT off_cs_off_cs_f1 FOREIGN KEY (combined_case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_case_conditions ADD CONSTRAINT off_case_conds_fk1 FOREIGN KEY (offender_book_id,case_plan_id) REFERENCES case_plans(offender_book_id,case_plan_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_case_conditions ADD CONSTRAINT off_case_conds_fk2 FOREIGN KEY (offender_sent_condition_id) REFERENCES offender_sent_conditions(offender_sent_condition_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_case_identifiers ADD CONSTRAINT off_ci_off_cs_f1 FOREIGN KEY (case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_case_issues ADD CONSTRAINT oci_off_rq_det_fk FOREIGN KEY (off_rq_det_id) REFERENCES offender_request_details(off_rq_det_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_case_notes ADD CONSTRAINT off_cn_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_case_notes ADD CONSTRAINT off_cn_stf_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_case_note_sents ADD CONSTRAINT off_cn_sent_off_sent_fk FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_case_officers ADD CONSTRAINT off_co_staff_f1 FOREIGN KEY (case_officer_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_case_statuses ADD CONSTRAINT off_case_sts_lgl_upd_rsn_fk FOREIGN KEY (status_update_reason) REFERENCES legal_update_reasons(update_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_case_statuses ADD CONSTRAINT off_case_sts_off_case_fk FOREIGN KEY (case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_charges ADD CONSTRAINT off_chg_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_charges ADD CONSTRAINT off_chg_off_case_fk FOREIGN KEY (case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_charges ADD CONSTRAINT off_chg_ofn_fk FOREIGN KEY (offence_code,statute_code) REFERENCES offences(offence_code,statute_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_charges_hty ADD CONSTRAINT off_chgh_ofn_f1 FOREIGN KEY (offence_code,statute_code) REFERENCES offences(offence_code,statute_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_cip_details ADD CONSTRAINT off_cipd_agy_loc_fk1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_cip_details ADD CONSTRAINT off_cipd_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_cip_details ADD CONSTRAINT off_cipd_pla_dur_fk1 FOREIGN KEY (placement_type,duration_type) REFERENCES placement_durations(placement_type,duration_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_class_programs ADD CONSTRAINT off_co_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_clothes ADD CONSTRAINT off_clt_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_clothing_bundles ADD CONSTRAINT off_clb_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_clothing_issue ADD CONSTRAINT off_cl_is_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_clothing_items ADD CONSTRAINT off_clth_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_clothing_items ADD CONSTRAINT off_clth_off_cl_is_f1 FOREIGN KEY (offender_clothing_issue_id) REFERENCES offender_clothing_issue(offender_clothing_issue_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_codefendants ADD CONSTRAINT o9ff_cod_off_bkg_f2 FOREIGN KEY (cod_offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_codefendants ADD CONSTRAINT off_cod_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_comments ADD CONSTRAINT off_cmt_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_community_conditions ADD CONSTRAINT off_cc_com_cond_fk FOREIGN KEY (comm_condition_type,comm_condition_code,category_type) REFERENCES community_conditions(comm_condition_type,comm_condition_code,category_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_community_conditions ADD CONSTRAINT off_coco_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_community_conditions ADD CONSTRAINT osc_off_sent_f1 FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_community_files ADD CONSTRAINT file_trans_off_file_fk1 FOREIGN KEY (holding_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_community_files ADD CONSTRAINT off_file_al_fk2 FOREIGN KEY (holding_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_community_files ADD CONSTRAINT off_file_off_fk1 FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_comm_sup_histories ADD CONSTRAINT off_csh_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_compact_agreements ADD CONSTRAINT off_ca_agy_loc_f1 FOREIGN KEY (jurisdiction_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_compact_agreements ADD CONSTRAINT off_ca_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_contact_persons ADD CONSTRAINT off_contact_persons_persons_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_contact_persons ADD CONSTRAINT off_cp_cont_pt_f1 FOREIGN KEY (contact_type,relationship_type) REFERENCES contact_person_types(contact_type,relationship_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_contact_persons ADD CONSTRAINT off_cp_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_contact_restrictions ADD CONSTRAINT off_cont_r_staf_mem_fk1 FOREIGN KEY (authorized_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_course_appt_grps ADD CONSTRAINT off_crs_ag_off_prg_prof_fk FOREIGN KEY (off_prgref_id) REFERENCES offender_program_profiles(off_prgref_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_course_appt_rules ADD CONSTRAINT off_crs_ar_off_crs_ag_fk FOREIGN KEY (offender_course_appt_grp_id) REFERENCES offender_course_appt_grps(offender_course_appt_grp_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_course_attendances ADD CONSTRAINT off_crs_attd_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_course_attendances ADD CONSTRAINT off_crs_attd_off_prg_prof_fk FOREIGN KEY (off_prgref_id) REFERENCES offender_program_profiles(off_prgref_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_course_attendances ADD CONSTRAINT off_crs_att_crs_acty_fk FOREIGN KEY (crs_acty_id) REFERENCES course_activities(crs_acty_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_course_attendances ADD CONSTRAINT off_crs_att_off_prg_ob_fk FOREIGN KEY (offender_prg_obligation_id) REFERENCES offender_prg_obligations(offender_prg_obligation_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_course_attendances ADD CONSTRAINT off_crs_att_prg_serv_fk FOREIGN KEY (program_id) REFERENCES program_services(program_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_credit_prior_payments ADD CONSTRAINT off_cpp_off_bnc_fk1 FOREIGN KEY (beneficiary_id) REFERENCES offender_beneficiaries(beneficiary_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_credit_type_histories ADD CONSTRAINT off_cr_typ_his_off_cr_typ_f1 FOREIGN KEY (offender_book_id,credit_type) REFERENCES offender_credit_types(offender_book_id,credit_type) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_criminogenic_needs ADD CONSTRAINT off_cri_needs_fk FOREIGN KEY (offender_book_id,case_plan_id) REFERENCES case_plans(offender_book_id,case_plan_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_curfews ADD CONSTRAINT off_curfew_info_off_booking_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_deductions ADD CONSTRAINT off_ded_csld_dd_f1 FOREIGN KEY (caseload_id,deduction_type) REFERENCES caseload_deduction_profiles(caseload_id,deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_deductions ADD CONSTRAINT off_ded_off_ded_fk1 FOREIGN KEY (parent_deduction_id) REFERENCES offender_deductions(offender_deduction_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_deductions ADD CONSTRAINT off_ded_off_pay_prfl_fk FOREIGN KEY (offender_payment_profile_id) REFERENCES offender_payment_profiles(offender_payment_profile_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_deductions ADD CONSTRAINT off_ded_off_ta_f1 FOREIGN KEY (caseload_id,offender_id) REFERENCES offender_trust_accounts(caseload_id,offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_deduction_receipts ADD CONSTRAINT off_dr_off_ded_f1 FOREIGN KEY (offender_deduction_id) REFERENCES offender_deductions(offender_deduction_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_deduction_receipts ADD CONSTRAINT off_dr_txn_type_f1 FOREIGN KEY (receipt_txn_type) REFERENCES transaction_types(txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_deduction_shadows ADD CONSTRAINT off_ds_off_ded_f1 FOREIGN KEY (offender_deduction_id) REFERENCES offender_deductions(offender_deduction_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_demogrant_imp_status ADD CONSTRAINT off_dis_off_demo_fk1 FOREIGN KEY (demogrant_id) REFERENCES offender_demogrants(demogrant_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_document_templates ADD CONSTRAINT off_doctmp_mod_ac_doc_fk1 FOREIGN KEY (module_name,template_code) REFERENCES module_accessible_documents(module_name,template_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_drug_admissions ADD CONSTRAINT off_drug_adm_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_drug_adm_drugs ADD CONSTRAINT off_drg_adm_d_off_drg_adm_fk FOREIGN KEY (offender_drug_admission_id) REFERENCES offender_drug_admissions(offender_drug_admission_id) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_drug_results ADD CONSTRAINT off_drug_rsl_off_drug_sam_fk FOREIGN KEY (offender_drug_sample_id) REFERENCES offender_drug_samples(offender_drug_sample_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_drug_samples ADD CONSTRAINT off_drug_samp_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_educations ADD CONSTRAINT off_edu_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_employments ADD CONSTRAINT off_emp_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_escapes ADD CONSTRAINT off_esc_agy_loc_f1 FOREIGN KEY (readmiss_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_escapes ADD CONSTRAINT off_esc_agy_loc_f2 FOREIGN KEY (escape_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_escapes ADD CONSTRAINT off_esc_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_expenses ADD CONSTRAINT off_exe_book_id_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_external_movements ADD CONSTRAINT off_em_agy_loc_f2 FOREIGN KEY (from_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_external_movements ADD CONSTRAINT off_em_agy_loc_f3 FOREIGN KEY (to_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_external_movements ADD CONSTRAINT off_em_int_sr_f1 FOREIGN KEY (internal_schedule_type,internal_schedule_reason_code) REFERENCES internal_schedule_reasons(internal_schedule_type,internal_schedule_rsn_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_external_movements ADD CONSTRAINT off_em_move_rsn_f1 FOREIGN KEY (movement_type,movement_reason_code) REFERENCES movement_reasons(movement_type,movement_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_external_movements ADD CONSTRAINT off_em_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_external_movements ADD CONSTRAINT off_ext_mov_addr_fk FOREIGN KEY (to_address_id) REFERENCES addresses(address_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_external_movements ADD CONSTRAINT off_ext_mov_from_addr_fk FOREIGN KEY (from_address_id) REFERENCES addresses(address_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_file_deliveries ADD CONSTRAINT off_fid_fil_dd_f1 FOREIGN KEY (file_delivery_id) REFERENCES file_delivery_details(file_delivery_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_file_locations ADD CONSTRAINT off_fl_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_file_locations ADD CONSTRAINT off_fl_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_file_transactions ADD CONSTRAINT file_trans_al_fk1 FOREIGN KEY (agy_loc_id_to) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_file_transactions ADD CONSTRAINT file_trans_al_fk2 FOREIGN KEY (agy_loc_id_from) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_file_transactions ADD CONSTRAINT file_trans_sm_fk1 FOREIGN KEY (staff_id_from) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_file_transactions ADD CONSTRAINT file_trans_sm_fk2 FOREIGN KEY (staff_id_to) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_fine_defaults ADD CONSTRAINT ofd_al_f1 FOREIGN KEY (imposing_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_fine_payments ADD CONSTRAINT off_fine_pay_staff_mem_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_foreign_currencies ADD CONSTRAINT off_fc_off_ta_f1 FOREIGN KEY (caseload_id,offender_id) REFERENCES offender_trust_accounts(caseload_id,offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_gang_affiliations ADD CONSTRAINT off_ga_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_gang_evidences ADD CONSTRAINT off_ge_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_gang_invests ADD CONSTRAINT off_gi_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_grievances ADD CONSTRAINT off_gvnc_agy_incp_fk1 FOREIGN KEY (agency_incident_id,party_seq) REFERENCES agency_incident_parties(agency_incident_id,party_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_grievances ADD CONSTRAINT off_gvnc_agy_loc_fk1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_grievances ADD CONSTRAINT off_gvnc_gvnc_rsn_fk1 FOREIGN KEY (griev_type,griev_reason_code) REFERENCES grievance_reasons(griev_type,griev_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_grievances ADD CONSTRAINT off_gvnc_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_grievance_txns ADD CONSTRAINT off_grvct_grvc_txn_fk1 FOREIGN KEY (griev_type,txn_type) REFERENCES grievance_txns(griev_type,txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_grievance_txns ADD CONSTRAINT off_grvct_off_grvc_fk1 FOREIGN KEY (grievance_id) REFERENCES offender_grievances(grievance_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_grievance_txns ADD CONSTRAINT off_grvct_staf_mem_fk1 FOREIGN KEY (assigned_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_griev_staffs ADD CONSTRAINT off_grvns_off_grvn_fk1 FOREIGN KEY (grievance_id) REFERENCES offender_grievances(grievance_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_griev_staffs ADD CONSTRAINT off_grvns_stf_mem_fk1 FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_hospital_visits ADD CONSTRAINT off_hv_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_hwd ADD CONSTRAINT offender_hwd_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_hwd_charges ADD CONSTRAINT offender_hwd_charges_fk1 FOREIGN KEY (hwd_id) REFERENCES offender_hwd(hwd_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_hwd_hty ADD CONSTRAINT offender_hwd_hty_fk1 FOREIGN KEY (hwd_id) REFERENCES offender_hwd(hwd_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_identifiers ADD CONSTRAINT off_id_off_f1 FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_identifying_marks ADD CONSTRAINT off_im_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_iep_levels ADD CONSTRAINT off_iep_levels_iep_level_fk FOREIGN KEY (iep_level,agy_loc_id) REFERENCES iep_levels(iep_level,agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_iep_levels ADD CONSTRAINT off_iep_levels_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_images ADD CONSTRAINT off_img_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_imprison_statuses ADD CONSTRAINT off_imps_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_imprison_statuses ADD CONSTRAINT off_ints_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_incident_details ADD CONSTRAINT off_incd_agy_incp_fk1 FOREIGN KEY (agency_incident_id,party_seq) REFERENCES agency_incident_parties(agency_incident_id,party_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_incident_details ADD CONSTRAINT off_incd_pfl_type_fk1 FOREIGN KEY (profile_type) REFERENCES profile_types(profile_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_income ADD CONSTRAINT off_inc_book_id_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ind_schedules ADD CONSTRAINT off_ind_sch_agy_int_loc_fk FOREIGN KEY (to_internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ind_schedules ADD CONSTRAINT off_ind_sch_agy_loc_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ind_schedules ADD CONSTRAINT off_ind_sch_agy_loc_fk2 FOREIGN KEY (to_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE NOT VALID;

ALTER TABLE offender_ind_schedules ADD CONSTRAINT off_ind_sch_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ind_schedules ADD CONSTRAINT off_ind_sch_off_prg_ob_fk FOREIGN KEY (offender_prg_obligation_id) REFERENCES offender_prg_obligations(offender_prg_obligation_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ind_schedules ADD CONSTRAINT off_ind_sch_sch_trips FOREIGN KEY (scheduled_trip_id) REFERENCES scheduled_trips(scheduled_trip_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ind_schedules ADD CONSTRAINT off_ind_sch_stf_fk FOREIGN KEY (in_charge_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ind_sch_sents ADD CONSTRAINT off_ind_ss_off_ind_sch_fk FOREIGN KEY (event_id) REFERENCES offender_ind_schedules(event_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ind_sch_sents ADD CONSTRAINT off_ind_ss_off_sent_fk FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ind_sch_wait_lists ADD CONSTRAINT off_iswl_staff_fk FOREIGN KEY (approved_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_inst_requests ADD CONSTRAINT off_instr_inter_sr_fk1 FOREIGN KEY (request_type,request_reason) REFERENCES internal_status_reasons(internal_status,int_sts_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_inst_requests ADD CONSTRAINT off_instr_staff_fk1 FOREIGN KEY (requested_by_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_inst_responses ADD CONSTRAINT off_irr_off_instr_fk1 FOREIGN KEY (off_inst_req_id) REFERENCES offender_inst_requests(off_inst_req_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_inst_responses ADD CONSTRAINT off_irr_rsp_authm_fk1 FOREIGN KEY (response_auth_member_id) REFERENCES response_authurity_members(response_auth_member_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_inst_responses ADD CONSTRAINT off_irr_rsp_autht_fk1 FOREIGN KEY (response_auth_time_id) REFERENCES response_authurity_times(response_auth_time_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_inst_responses ADD CONSTRAINT off_irr_staff_fk1 FOREIGN KEY (authrized_by) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_inst_response_members ADD CONSTRAINT off_instrm_com_mt_f1 FOREIGN KEY (committee_meeting_id) REFERENCES committee_meetings(committe_meeting_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_inst_response_members ADD CONSTRAINT off_instrm_rsp_authl_f1 FOREIGN KEY (review_auth_member_id) REFERENCES response_authurity_members(response_auth_member_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_inst_response_results ADD CONSTRAINT off_instrr_off_instr_fk1 FOREIGN KEY (off_response_inst_id) REFERENCES offender_inst_responses(off_inst_response_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_internal_movements ADD CONSTRAINT off_im_off_bkg_f2 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_internal_movements ADD CONSTRAINT off_im_staff_members_fk FOREIGN KEY (authorised_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_internal_movements ADD CONSTRAINT off_int_mov_agy_int_loc_fk FOREIGN KEY (living_unit_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_internal_statuses ADD CONSTRAINT off_ints_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_internal_statuses ADD CONSTRAINT off_is_int_sts_f1 FOREIGN KEY (internal_status,int_sts_reason_code) REFERENCES internal_status_reasons(internal_status,int_sts_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_internal_statuses ADD CONSTRAINT off_is_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_intervention_tiers ADD CONSTRAINT off_intv_tier_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_inter_mvmt_locations ADD CONSTRAINT off_il_agy_loc_fk1 FOREIGN KEY (agency_location_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_inter_mvmt_locations ADD CONSTRAINT off_iml_agy_int_loc_fk1 FOREIGN KEY (agency_iml_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_inter_mvmt_locations ADD CONSTRAINT off_iml_agy_int_loc_fk2 FOREIGN KEY (from_agency_iml_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_int_loc_defaults ADD CONSTRAINT off_intl_def_agy_intl_fk1 FOREIGN KEY (internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_int_loc_defaults ADD CONSTRAINT off_intl_def_agy_loc_fk1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_int_mov_persons ADD CONSTRAINT off_imp_off_im_f1 FOREIGN KEY (offender_book_id,movement_seq) REFERENCES offender_internal_movements(offender_book_id,movement_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_languages ADD CONSTRAINT off_lang_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_liabilities ADD CONSTRAINT off_lia_book_id_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_licence_sentences ADD CONSTRAINT offender_licence_sentences_fk1 FOREIGN KEY (case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_licence_sentences ADD CONSTRAINT offender_licence_sentences_fk2 FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_licence_sentences ADD CONSTRAINT offender_licence_sentences_fk3 FOREIGN KEY (offender_book_id,licence_sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_loc_chng_dtls ADD CONSTRAINT off_lcd_off_p_loc_fk1 FOREIGN KEY (offender_book_id,location_seq) REFERENCES offender_proposed_intlocs(offender_book_id,location_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_mail_logs ADD CONSTRAINT off_ml_logs_addr_fk1 FOREIGN KEY (mail_address_id) REFERENCES addresses(address_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_mail_logs ADD CONSTRAINT off_ml_logs_off_bk_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_mail_restrictions ADD CONSTRAINT off_ml_r_addr_fk1 FOREIGN KEY (restriction_address_id) REFERENCES addresses(address_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_mail_restrictions ADD CONSTRAINT off_ml_r_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_medical_answers_6i ADD CONSTRAINT off_ma_off_ms_f1 FOREIGN KEY (offender_book_id,screen_seq) REFERENCES offender_medical_screenings_6i(offender_book_id,screen_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_medical_screenings_6i ADD CONSTRAINT off_meds_off_bkg_6i_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_medical_treatments ADD CONSTRAINT off_med_trt_off_hlth_prob_fk FOREIGN KEY (offender_health_problem_id) REFERENCES offender_health_problems(offender_health_problem_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_military_disc_actions ADD CONSTRAINT off_mr_tech_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_military_records ADD CONSTRAINT off_mr_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_military_war_zones ADD CONSTRAINT off_mr_war_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_mon_beneficiaries ADD CONSTRAINT off_monben_corp_fk1 FOREIGN KEY (corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_mon_beneficiaries ADD CONSTRAINT off_monben_off_ben_fk1 FOREIGN KEY (beneficiary_id) REFERENCES offender_beneficiaries(beneficiary_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_mon_beneficiaries ADD CONSTRAINT off_monben_off_ded_fk1 FOREIGN KEY (offender_deduction_id) REFERENCES offender_deductions(offender_deduction_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_mon_beneficiaries ADD CONSTRAINT off_monben_persons_fk1 FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_mon_deductions ADD CONSTRAINT off_md_off_ded_f1 FOREIGN KEY (offender_deduction_id) REFERENCES offender_deductions(offender_deduction_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_movement_details ADD CONSTRAINT off_mvmt_d_off_p_mvmt_fk1 FOREIGN KEY (offender_book_id,movement_seq) REFERENCES offender_proposed_mvmnts(offender_book_id,movement_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_na_details ADD CONSTRAINT off_nad_off_na_f1 FOREIGN KEY (offender_id,ns_offender_id) REFERENCES offender_non_associations(offender_id,ns_offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_not_completions ADD CONSTRAINT off_nc_corp_f1 FOREIGN KEY (noti_corp_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_not_completions ADD CONSTRAINT off_nc_off_pn_f1 FOREIGN KEY (offender_book_id,noti_seq,noti_move_seq) REFERENCES offender_pend_notifications(offender_book_id,noti_seq,noti_move_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_not_completions ADD CONSTRAINT off_nc_per_f1 FOREIGN KEY (noti_person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oasys_actions ADD CONSTRAINT off_oasys_act_off_oasys_obj_fk FOREIGN KEY (objective_id) REFERENCES offender_oasys_objectives(objective_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oasys_concerns ADD CONSTRAINT off_oasys_con_off_oasys_pln_fk FOREIGN KEY (offender_book_id,plan_seq) REFERENCES offender_oasys_plans(offender_book_id,plan_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oasys_objectives ADD CONSTRAINT off_oasys_obj_off_oasys_sec_fk FOREIGN KEY (offender_book_id,plan_seq,section_code) REFERENCES offender_oasys_sections(offender_book_id,plan_seq,section_code) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oasys_plans ADD CONSTRAINT off_oasys_plans_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oasys_risk_to_others ADD CONSTRAINT off_oasys_rsk_off_oasys_pln_fk FOREIGN KEY (offender_book_id,plan_seq) REFERENCES offender_oasys_plans(offender_book_id,plan_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oasys_sections ADD CONSTRAINT off_oasys_sec_oasys_sec_fk FOREIGN KEY (section_code) REFERENCES oasys_sections(section_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_oasys_sections ADD CONSTRAINT off_oasys_sec_off_oasys_pln_fk FOREIGN KEY (offender_book_id,plan_seq) REFERENCES offender_oasys_plans(offender_book_id,plan_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oasys_supervisions ADD CONSTRAINT off_oasys_sup_off_oasys_act_fk FOREIGN KEY (objective_id,action_seq) REFERENCES offender_oasys_actions(objective_id,action_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_obligation_adjs ADD CONSTRAINT offender_obligation_adjs_fk1 FOREIGN KEY (offender_prg_obligation_id) REFERENCES offender_prg_obligations(offender_prg_obligation_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_obli_verifications ADD CONSTRAINT off_oblv_offe_fk1 FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_obli_verifications ADD CONSTRAINT off_oblv_pers_fk1 FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_observation_zones ADD CONSTRAINT offender_observation_zones_fk1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oic_appeals ADD CONSTRAINT off_oica_staff_fk1 FOREIGN KEY (heard_by) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oic_appeal_incidents ADD CONSTRAINT off_oicai_off_oica_fk1 FOREIGN KEY (oic_appreal_id) REFERENCES offender_oic_appeals(oic_appreal_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_oic_appeal_incidents ADD CONSTRAINT off_oicai_oic_hea_r_fk1 FOREIGN KEY (oic_hearing_id,result_seq) REFERENCES oic_hearing_results(oic_hearing_id,result_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oic_appeal_penalties ADD CONSTRAINT off_oicap_off_oicai_fk1 FOREIGN KEY (oic_appreal_id,oic_hearing_id,result_seq) REFERENCES offender_oic_appeal_incidents(oic_appreal_id,oic_hearing_id,result_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oic_sanctions ADD CONSTRAINT off_os_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_oic_sanctions ADD CONSTRAINT off_os_off_os_f1 FOREIGN KEY (consecutive_offender_book_id,consecutive_sanction_seq) REFERENCES offender_oic_sanctions(offender_book_id,sanction_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_oic_sanctions ADD CONSTRAINT oic_os_oic_hr_fk1 FOREIGN KEY (oic_hearing_id,result_seq) REFERENCES oic_hearing_results(oic_hearing_id,result_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_oic_sanc_reviews ADD CONSTRAINT off_osv_off_oics_f1 FOREIGN KEY (offender_book_id,sanction_seq) REFERENCES offender_oic_sanctions(offender_book_id,sanction_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_parole_conditions ADD CONSTRAINT off_pc_off_pd_f1 FOREIGN KEY (schedule_id,parole_decision_seq) REFERENCES offender_parole_decisions(schedule_id,parole_decision_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_parole_decisions ADD CONSTRAINT off_pd_off_ph_f1 FOREIGN KEY (offender_book_id,parole_hearing_seq) REFERENCES offender_parole_hearings(offender_book_id,parole_hearing_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_parole_decisions ADD CONSTRAINT off_pd_staff_f1 FOREIGN KEY (authorized_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_parole_hearings ADD CONSTRAINT off_ph_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_parole_hearings ADD CONSTRAINT off_ph_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_parole_plans ADD CONSTRAINT pff_pp_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_parole_stipulations ADD CONSTRAINT off_par_sp_off_pd_f1 FOREIGN KEY (schedule_id,parole_decision_seq) REFERENCES offender_parole_decisions(schedule_id,parole_decision_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_payment_profiles ADD CONSTRAINT off_pay_prf_csl_fk FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_payment_profiles ADD CONSTRAINT off_sch_pay_off_bkg_fk FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_payment_schedules ADD CONSTRAINT pay_sch_pay_pln_f1 FOREIGN KEY (payment_plan_id,payment_plan_seq) REFERENCES offender_payment_plans(payment_plan_id,payment_plan_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_penalty_options ADD CONSTRAINT opo_off_rq_det_fk FOREIGN KEY (off_rq_det_id) REFERENCES offender_request_details(off_rq_det_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_pend_notifications ADD CONSTRAINT off_pen_no_mov_res_f1 FOREIGN KEY (movement_type,movement_reason_code) REFERENCES movement_reasons(movement_type,movement_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_pend_notifications ADD CONSTRAINT off_pn_off_rn_fk1 FOREIGN KEY (offender_book_id,noti_seq) REFERENCES offender_release_notis(offender_book_id,noti_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_person_restricts ADD CONSTRAINT off_per_rest_off_cont_per_fk FOREIGN KEY (offender_contact_person_id) REFERENCES offender_contact_persons(offender_contact_person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_person_restricts ADD CONSTRAINT off_per_rest_staf_mem_fk1 FOREIGN KEY (authorized_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_person_restricts ADD CONSTRAINT off_per_rest_stf_fk2 FOREIGN KEY (entered_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_placement_scores ADD CONSTRAINT off_pl_ass_off_fk FOREIGN KEY (root_offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_planed_activities ADD CONSTRAINT off_pa_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_planed_activities ADD CONSTRAINT off_pa_off_sp_f1 FOREIGN KEY (offender_book_id,plan_seq) REFERENCES offender_supervision_plans(offender_book_id,plan_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_post_dated_txns ADD CONSTRAINT off_pdt_per_f1 FOREIGN KEY (payee_person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_post_dated_txns ADD CONSTRAINT off_pdt_txn_type_f1 FOREIGN KEY (txn_type) REFERENCES transaction_types(txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ppty_containers ADD CONSTRAINT off_con_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_containers ADD CONSTRAINT off_con_agy_loc_f2 FOREIGN KEY (trn_to_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_containers ADD CONSTRAINT off_con_agy_loc_f3 FOREIGN KEY (trn_from_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_containers ADD CONSTRAINT off_con_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_containers ADD CONSTRAINT off_con_ppty_stg_f1 FOREIGN KEY (property_storage_id) REFERENCES property_storages(property_storage_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_containers ADD CONSTRAINT off_ppty_con_agy_int_loc_fk FOREIGN KEY (internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ppty_con_txns ADD CONSTRAINT con_tx_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_con_txns ADD CONSTRAINT con_tx_agy_loc_f2 FOREIGN KEY (trn_to_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_con_txns ADD CONSTRAINT con_tx_agy_loc_f3 FOREIGN KEY (trn_from_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_con_txns ADD CONSTRAINT off_pct_agy_int_loc_fk FOREIGN KEY (internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ppty_items ADD CONSTRAINT off_pi_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_items ADD CONSTRAINT off_pi_corp_f1 FOREIGN KEY (disposed_to_corp_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_items ADD CONSTRAINT off_pi_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ppty_item_events ADD CONSTRAINT item_evt_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ppty_item_txns ADD CONSTRAINT itm_tx_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_item_txns ADD CONSTRAINT itm_tx_corp_f1 FOREIGN KEY (disposed_to_corp_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_item_txns ADD CONSTRAINT itm_tx_itm_evt_f1 FOREIGN KEY (offender_book_id,event_seq) REFERENCES offender_ppty_item_events(offender_book_id,event_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ppty_item_txns ADD CONSTRAINT itm_tx_per_f1 FOREIGN KEY (disposed_to_person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_prg_obligations ADD CONSTRAINT off_prg_ob_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_prg_obligations ADD CONSTRAINT off_prg_ob_off_sent_con_act_fk FOREIGN KEY (offender_sent_cond_act_id) REFERENCES offender_sent_cond_acts(offender_sent_cond_act_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_prg_obligations ADD CONSTRAINT off_prg_ob_prg_serv_fk FOREIGN KEY (program_id) REFERENCES program_services(program_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_prg_obligation_hty ADD CONSTRAINT off_prg_ohty_off_prg_obl_fk FOREIGN KEY (offender_prg_obligation_id) REFERENCES offender_prg_obligations(offender_prg_obligation_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_proceedings ADD CONSTRAINT off_proceed_agy_loc_fk FOREIGN KEY (proceeding_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_proceedings ADD CONSTRAINT off_proceed_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_proceeding_sents ADD CONSTRAINT off_prod_sent_off_prod_fk FOREIGN KEY (offender_proceeding_id) REFERENCES offender_proceedings(offender_proceeding_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_proceeding_sents ADD CONSTRAINT off_prod_sent_off_sent_fk FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_profiles ADD CONSTRAINT off_pfl_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_program_profiles ADD CONSTRAINT off_prgref_ob_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_program_profiles ADD CONSTRAINT off_prg_prfl_off_cond_fk FOREIGN KEY (offender_sent_condition_id) REFERENCES offender_sent_conditions(offender_sent_condition_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_program_profiles ADD CONSTRAINT off_prg_prfl_off_prg_obg_fk FOREIGN KEY (offender_prg_obligation_id) REFERENCES offender_prg_obligations(offender_prg_obligation_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_program_profiles ADD CONSTRAINT off_prg_prfl_off_prg_prfl_fk2 FOREIGN KEY (program_off_prgref_id) REFERENCES offender_program_profiles(off_prgref_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_program_profiles ADD CONSTRAINT off_prg_prfl_stf_fk FOREIGN KEY (referral_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_program_profiles ADD CONSTRAINT off_prg_prof_crs_acty_fk FOREIGN KEY (crs_acty_id) REFERENCES course_activities(crs_acty_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_program_profiles ADD CONSTRAINT off_prg_prof_prg_serv_fk FOREIGN KEY (program_id) REFERENCES program_services(program_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_property_bundles ADD CONSTRAINT off_pb_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_property_bundles ADD CONSTRAINT off_pb_ppty_stg_f1 FOREIGN KEY (property_storage_id) REFERENCES property_storages(property_storage_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_property_bundles ADD CONSTRAINT of_pb_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_proposed_intlocs ADD CONSTRAINT off_p_loc_int_s_rsn_fk1 FOREIGN KEY (movement_type,movement_reason) REFERENCES internal_schedule_reasons(internal_schedule_type,internal_schedule_rsn_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_proposed_mvmnts ADD CONSTRAINT off_p_mvmt_agy_loc_fk1 FOREIGN KEY (from_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_proposed_mvmnts ADD CONSTRAINT off_p_mvmt_agy_loc_fk2 FOREIGN KEY (to_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_proposed_mvmnts ADD CONSTRAINT off_p_mvmt_agy_loc_fk3 FOREIGN KEY (alternate_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_proposed_mvmnts ADD CONSTRAINT off_p_mvmt_mov_rsn_fk1 FOREIGN KEY (movement_type,movement_reason) REFERENCES movement_reasons(movement_type,movement_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_proposed_mvmnts ADD CONSTRAINT off_p_mvmt_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_proposed_mvmnts ADD CONSTRAINT off_p_mvmt_sch_trps_fk1 FOREIGN KEY (scheduled_trip_id) REFERENCES scheduled_trips(scheduled_trip_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ptr ADD CONSTRAINT offender_ptr_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ptr ADD CONSTRAINT offender_ptr_fk2 FOREIGN KEY (from_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ptr ADD CONSTRAINT offender_ptr_fk3 FOREIGN KEY (to_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ptr ADD CONSTRAINT offender_ptr_fk4 FOREIGN KEY (ptr_cancel_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ptr ADD CONSTRAINT offender_ptr_fk5 FOREIGN KEY (assign_receiv_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ptr ADD CONSTRAINT offender_ptr_fk6 FOREIGN KEY (transfer_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ptr ADD CONSTRAINT offender_ptr_fk7 FOREIGN KEY (transfer_cancel_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ptr ADD CONSTRAINT offender_ptr_fk8 FOREIGN KEY (decision_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ptr_details ADD CONSTRAINT offender_ptr_details_fk1 FOREIGN KEY (ptr_id) REFERENCES offender_ptr(ptr_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ptr_details ADD CONSTRAINT offender_ptr_details_fk2 FOREIGN KEY (ptr_detail_type) REFERENCES ptr_detail_types(type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_ptr_details ADD CONSTRAINT offender_ptr_details_fk3 FOREIGN KEY (ptr_detail_type,ptr_detail_code) REFERENCES ptr_type_codes(type,code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_referrals ADD CONSTRAINT off_ref_agy_loc_f1 FOREIGN KEY (from_acy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_referrals ADD CONSTRAINT off_ref_agy_loc_f2 FOREIGN KEY (to_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_referrals ADD CONSTRAINT off_ref_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_referrals ADD CONSTRAINT off_ref_ord_type_f1 FOREIGN KEY (order_type) REFERENCES order_types(order_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_release_details ADD CONSTRAINT off_relde_mov_rsn_fk1 FOREIGN KEY (movement_type,movement_reason_code) REFERENCES movement_reasons(movement_type,movement_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_release_details ADD CONSTRAINT off_relde_off_bk_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_release_notis ADD CONSTRAINT off_rn_corp_f1 FOREIGN KEY (noti_corp_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_release_notis ADD CONSTRAINT off_rn_per_f1 FOREIGN KEY (noti_person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_requests ADD CONSTRAINT off_req_pot_fk FOREIGN KEY (order_type,order_code) REFERENCES probation_order_types(order_type,order_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_request_details ADD CONSTRAINT offender_requests FOREIGN KEY (offender_book_id,charge_seq,request_seq) REFERENCES offender_requests(offender_book_id,charge_seq,request_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_request_details ADD CONSTRAINT off_rq_det_al_f1 FOREIGN KEY (requesting_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_request_details ADD CONSTRAINT off_rq_det_al_f2 FOREIGN KEY (remanded_to_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_request_recommendatio ADD CONSTRAINT orr_off_rq_det_fk FOREIGN KEY (off_rq_det_id) REFERENCES offender_request_details(off_rq_det_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_residences ADD CONSTRAINT off_res_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_restrictions ADD CONSTRAINT off_rest_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_restrictions ADD CONSTRAINT off_rest_stf_fk1 FOREIGN KEY (entered_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_restrictions ADD CONSTRAINT off_rest_stf_fk2 FOREIGN KEY (authorised_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_risk_predictors ADD CONSTRAINT off_rsik_pred_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_schedule_persons ADD CONSTRAINT off_schp_per_f1 FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sentences ADD CONSTRAINT off_sent_off_case_fk FOREIGN KEY (case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_sentences ADD CONSTRAINT off_sent_order_fk FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_sentences ADD CONSTRAINT off_sent_sent_calc_type_fk FOREIGN KEY (sentence_category,sentence_calc_type) REFERENCES sentence_calc_types(sentence_category,sentence_calc_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE NOT VALID;
ALTER TABLE offender_sentence_adjusts ADD CONSTRAINT off_sent_adj_off_kd_adj_fk FOREIGN KEY (offender_key_date_adjust_id) REFERENCES offender_key_date_adjusts(offender_key_date_adjust_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_sentence_adjusts ADD CONSTRAINT off_sent_adj_off_sent_fk FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sentence_charges ADD CONSTRAINT off_sent_chg_off_chg_fk FOREIGN KEY (offender_charge_id) REFERENCES offender_charges(offender_charge_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_sentence_charges ADD CONSTRAINT off_snet_chg_off_sent_fk FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sentence_hty ADD CONSTRAINT off_sent_hty_off_sent_fk FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_sentence_hty ADD CONSTRAINT off_sent_hty_stf_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sentence_statuses ADD CONSTRAINT off_sent_sts_lgl_upd_rsn_fk FOREIGN KEY (status_update_reason) REFERENCES legal_update_reasons(update_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_sentence_statuses ADD CONSTRAINT off_sent_sts_off_sent_fk FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sentence_terms ADD CONSTRAINT off_sent_term_off_sent_fk FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sentence_ua_events ADD CONSTRAINT off_sent_uae_off_sent_fk FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sent_calculations ADD CONSTRAINT off_sent_calc_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sent_conditions ADD CONSTRAINT off_sent_cond_comm_cond_fk FOREIGN KEY (comm_condition_type,comm_condition_code,category_type) REFERENCES community_conditions(comm_condition_type,comm_condition_code,category_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sent_cond_acts ADD CONSTRAINT off_sent_ca_off_sent_cond_fk FOREIGN KEY (offender_sent_condition_id) REFERENCES offender_sent_conditions(offender_sent_condition_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sent_cond_statuses ADD CONSTRAINT off_sent_csts_lgl_upd_rsn_fk FOREIGN KEY (status_update_reason) REFERENCES legal_update_reasons(update_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_sent_cond_statuses ADD CONSTRAINT off_sent_csts_off_sent_cond_fk FOREIGN KEY (offender_sent_condition_id) REFERENCES offender_sent_conditions(offender_sent_condition_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_stg_affiliations ADD CONSTRAINT off_stg_a_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_stg_affiliations ADD CONSTRAINT off_stg_a_stg_fk1 FOREIGN KEY (stg_id) REFERENCES security_threat_groups(stg_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_stg_associations ADD CONSTRAINT off_stg_as_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_stg_details ADD CONSTRAINT off_stg_d_off_stg_a_fk1 FOREIGN KEY (offender_book_id,stg_seq) REFERENCES offender_stg_affiliations(offender_book_id,stg_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_stock_item_limits ADD CONSTRAINT off_sil_stk_itm_fk1 FOREIGN KEY (stock_item_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_substance_abuses ADD CONSTRAINT osab_ob_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_substance_details ADD CONSTRAINT off_sd_off_su_f1 FOREIGN KEY (offender_book_id,substance_type) REFERENCES offender_substance_uses(offender_book_id,substance_type) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_substance_treatments ADD CONSTRAINT off_st_off_su_f1 FOREIGN KEY (offender_book_id,substance_type) REFERENCES offender_substance_uses(offender_book_id,substance_type) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_sub_accounts ADD CONSTRAINT off_suba_off_ta_f3 FOREIGN KEY (caseload_id,offender_id) REFERENCES offender_trust_accounts(caseload_id,offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_supervising_courts ADD CONSTRAINT off_sup_crt_agy_loc_fk FOREIGN KEY (court_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_supervising_courts ADD CONSTRAINT off_sup_crt_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_supervision_levels ADD CONSTRAINT off_sl_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_supervision_levels ADD CONSTRAINT off_sl_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_supervision_plans ADD CONSTRAINT off_sp_off_sl_f1 FOREIGN KEY (offender_book_id,supervision_level_type) REFERENCES offender_supervision_levels(offender_book_id,supervision_level_type) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_supervision_rates ADD CONSTRAINT off_sr_off_sl_f1 FOREIGN KEY (offender_book_id,supervision_type) REFERENCES offender_supervision_levels(offender_book_id,supervision_level_type) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_tax_profiles ADD CONSTRAINT off_taxp_csld FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_ta_schedules ADD CONSTRAINT off_tas_tag_f1 FOREIGN KEY (ta_id) REFERENCES temp_absence_groups(ta_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_team_assignments ADD CONSTRAINT off_team_assig_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_team_assign_hty ADD CONSTRAINT off_team_asgn_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_team_assign_hty ADD CONSTRAINT off_team_asgn_team_fk FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_temporary_absences ADD CONSTRAINT off_ta_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_test_selections ADD CONSTRAINT off_test_sel_rtp_f1 FOREIGN KEY (rtp_id) REFERENCES random_testing_programs(rtp_id) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_tier_txn_fee_amounts ADD CONSTRAINT off_ttf_off_ded_fk1 FOREIGN KEY (offender_deduction_id) REFERENCES offender_deductions(offender_deduction_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_tmp_rel_schedules ADD CONSTRAINT off_trel_sch_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_tmp_rel_schedules ADD CONSTRAINT off_trs_mov_rsn_fk1 FOREIGN KEY (movement_type,movement_reason_code) REFERENCES movement_reasons(movement_type,movement_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_transactions ADD CONSTRAINT off_txn_corp_f1 FOREIGN KEY (payee_corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_transactions ADD CONSTRAINT off_txn_off_name_f1 FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_transactions ADD CONSTRAINT off_txn_txn_type_f1 FOREIGN KEY (txn_type) REFERENCES transaction_types(txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_trust_accounts ADD CONSTRAINT off_ta_off_name_f2 FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_trust_accounts_temp ADD CONSTRAINT off_ta_temp_off_name_f2 FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_txn_fee_details ADD CONSTRAINT off_tfd_ded_type_fk1 FOREIGN KEY (receipt_deduction_type) REFERENCES deduction_types(deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_txn_fee_details ADD CONSTRAINT off_tfd_off_ded_f1 FOREIGN KEY (offender_deduction_id) REFERENCES offender_deductions(offender_deduction_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_unpaid_work_adj ADD CONSTRAINT offender_unpaid_work_adj_fk1 FOREIGN KEY (offender_book_id,sentence_seq) REFERENCES offender_sentences(offender_book_id,sentence_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_visits ADD CONSTRAINT off_vist_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_visits ADD CONSTRAINT off_vis_agy_vis_slot_fk FOREIGN KEY (agency_visit_slot_id) REFERENCES agency_visit_slots(agency_visit_slot_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_visit_visitors ADD CONSTRAINT off_vis_visr_off_vis_fk FOREIGN KEY (offender_visit_id) REFERENCES offender_visits(offender_visit_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_visit_visitors ADD CONSTRAINT off_vis_visr_per_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_vital_signs ADD CONSTRAINT off_vits_offenders_fk1 FOREIGN KEY (offender_id) REFERENCES offenders(offender_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_vital_signs ADD CONSTRAINT off_vit_s_off_bkg_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_works ADD CONSTRAINT off_wrk_csld_wrk_f1 FOREIGN KEY (caseload_id,work_code) REFERENCES caseload_works(caseload_id,work_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_works ADD CONSTRAINT off_wrk_off_wrk_f1 FOREIGN KEY (summary_offender_work_id) REFERENCES offender_works(offender_work_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offender_work_assignments ADD CONSTRAINT offass_al_fk FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE offender_work_assignments ADD CONSTRAINT off_wa_slr_f1 FOREIGN KEY (sac_staff_id,cal_agy_loc_id,from_date,position,role) REFERENCES staff_location_roles(sac_staff_id,cal_agy_loc_id,from_date,position,role) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE offices ADD CONSTRAINT ofc_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_case_note_recipients ADD CONSTRAINT off_cn_recpt_stf_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE off_case_note_recipients ADD CONSTRAINT off_cn_recpt_team_fk FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_obs_add_check_details ADD CONSTRAINT off_obs_add_check_details_fk1 FOREIGN KEY (offender_book_id,obs_period_id,check_id) REFERENCES off_obs_period_checks(offender_book_id,obs_period_id,check_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_obs_add_details ADD CONSTRAINT off_obs_add_details_fk1 FOREIGN KEY (observation_type) REFERENCES offender_observation_types(observation_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_obs_characteristics ADD CONSTRAINT off_obs_characteristics_fk1 FOREIGN KEY (observation_type) REFERENCES offender_observation_types(observation_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_obs_period_checks ADD CONSTRAINT off_obs_period_checks_fk1 FOREIGN KEY (offender_book_id,obs_period_id) REFERENCES off_observation_periods(offender_book_id,obs_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_obs_prd_characteristics ADD CONSTRAINT off_obs_prd_char_fk1 FOREIGN KEY (offender_book_id,obs_period_id) REFERENCES off_observation_periods(offender_book_id,obs_period_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_obs_zone_details ADD CONSTRAINT off_obs_zone_details_fk1 FOREIGN KEY (agy_loc_id,zone_code) REFERENCES offender_observation_zones(agy_loc_id,zone_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE oic_hearings ADD CONSTRAINT oic_hear_agy_int_loc_fk FOREIGN KEY (internal_location_id) REFERENCES agency_internal_locations(internal_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE oic_hearings ADD CONSTRAINT oic_hr_agy_inc_pty_fk FOREIGN KEY (oic_incident_id) REFERENCES agency_incident_parties(oic_incident_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE oic_hearing_comments ADD CONSTRAINT oic_hc_oic_hear_fk1 FOREIGN KEY (oic_hearing_id) REFERENCES oic_hearings(oic_hearing_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE oic_hearing_notices ADD CONSTRAINT oic_hn_oic_hr_fk FOREIGN KEY (oic_hearing_id) REFERENCES oic_hearings(oic_hearing_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE oic_hearing_notices ADD CONSTRAINT oic_hn_oic_staff_fk FOREIGN KEY (delivery_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE oic_hearing_results ADD CONSTRAINT oic_hearing_results_fk1 FOREIGN KEY (oic_offence_id) REFERENCES oic_offences(oic_offence_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE oic_hearing_results ADD CONSTRAINT oic_hr_oic_agy_inc_chg_fk FOREIGN KEY (agency_incident_id,charge_seq) REFERENCES agency_incident_charges(agency_incident_id,charge_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE oic_hearing_results ADD CONSTRAINT oic_hr_oic_hear_f1 FOREIGN KEY (oic_hearing_id) REFERENCES oic_hearings(oic_hearing_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE oic_hearing_reviews ADD CONSTRAINT oic_hrev_oic_hear_f1 FOREIGN KEY (oic_hearing_id) REFERENCES oic_hearings(oic_hearing_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE oic_hearing_reviews ADD CONSTRAINT oic_hrev_staff_f1 FOREIGN KEY (govener_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE oic_offence_indicators ADD CONSTRAINT oic_offence_indicators_fk1 FOREIGN KEY (oic_offence_id) REFERENCES oic_offences(oic_offence_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE oms_module_parameters ADD CONSTRAINT oms_mp_oms_mod_f1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE oms_module_parameters ADD CONSTRAINT oms_mp_ref_dom_f1 FOREIGN KEY (parameter_domain) REFERENCES reference_domains(domain) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE oms_requests ADD CONSTRAINT oms_req_oms_mod_f1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE oms_requests ADD CONSTRAINT oms_req_printer_f1 FOREIGN KEY (printer_id) REFERENCES printers(printer_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE oms_request_parameters ADD CONSTRAINT oms_reqp_oms_req_f1 FOREIGN KEY (request_id) REFERENCES oms_requests(request_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE oms_roles ADD CONSTRAINT oms_roles_oms_roles_fk FOREIGN KEY (parent_role_code) REFERENCES oms_roles(role_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE orders ADD CONSTRAINT off_ord_agy_loc_f1 FOREIGN KEY (issuing_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE orders ADD CONSTRAINT off_ord_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE orders ADD CONSTRAINT off_ord_off_cas_fk FOREIGN KEY (case_id) REFERENCES offender_cases(case_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE order_block_fields ADD CONSTRAINT blk_fields_fk FOREIGN KEY (order_id,order_blocks) REFERENCES order_module_blocks(order_id,order_blocks) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE order_info_requests ADD CONSTRAINT ord_info_req_staff_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE order_module_blocks ADD CONSTRAINT mod_blocks_fk FOREIGN KEY (order_id) REFERENCES order_modules(order_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE order_ppsl_cond_activities ADD CONSTRAINT ord_pc_act_ord_pc_fk FOREIGN KEY (order_proposal_condition_id) REFERENCES order_proposal_conditions(order_proposal_condition_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE order_proposals ADD CONSTRAINT order_pprl_sent_calc_fk FOREIGN KEY (sentence_category,sentence_calc_type) REFERENCES sentence_calc_types(sentence_category,sentence_calc_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE order_proposal_conditions ADD CONSTRAINT ord_ppsl_cond_comm_cond_fk FOREIGN KEY (comm_condition_type,comm_condition_code,category_type) REFERENCES community_conditions(comm_condition_type,comm_condition_code,category_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE order_proposal_conditions ADD CONSTRAINT ord_pps_cond_ord_ppsl_fk FOREIGN KEY (order_id,order_proposal_code) REFERENCES order_proposals(order_id,order_proposal_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE order_purposes ADD CONSTRAINT order_purposes_order_fk FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE other_privileges_levels ADD CONSTRAINT oth_priv_iep_lev_fk FOREIGN KEY (iep_level,agy_loc_id) REFERENCES iep_levels(iep_level,agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE parole_commission_sittings ADD CONSTRAINT prl_cs_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE parole_decisions ADD CONSTRAINT par_dec_off_ploc_fk1 FOREIGN KEY (offender_book_id,parole_location_seq) REFERENCES offender_parole_locations(offender_book_id,parole_location_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE parole_decision_reasons ADD CONSTRAINT prl_dst_prl_dt_f1 FOREIGN KEY (parole_decision_type,parole_hearing_type) REFERENCES parole_decision_types(parole_decision_type,parole_hearing_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE parole_hearing_waivers ADD CONSTRAINT par_hewai_off_ploc_fk1 FOREIGN KEY (offender_book_id,parole_location_seq) REFERENCES offender_parole_locations(offender_book_id,parole_location_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE parole_sitting_attendances ADD CONSTRAINT prl_sa_per_f1 FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE parole_sitting_attendances ADD CONSTRAINT prl_sa_prl_cs_f1 FOREIGN KEY (parole_sitting_id) REFERENCES parole_commission_sittings(parole_sitting_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE payee_account_balances ADD CONSTRAINT payee_ab_ac_code_f1 FOREIGN KEY (account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE payee_account_balances ADD CONSTRAINT payee_ab_corp_f1 FOREIGN KEY (payee_corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE payee_account_balances ADD CONSTRAINT payee_ab_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE payee_account_balances ADD CONSTRAINT payee_ab_per_f1 FOREIGN KEY (payee_person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE payment_plan_transactions ADD CONSTRAINT pp_txn_pay_sch_fk1 FOREIGN KEY (payment_plan_id,payment_plan_seq,payment_date) REFERENCES offender_payment_schedules(payment_plan_id,payment_plan_seq,payment_date) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE payroll_batches ADD CONSTRAINT pay_bat_csld_wrk_f1 FOREIGN KEY (caseload_id,work_code) REFERENCES caseload_works(caseload_id,work_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE personal_identifications ADD CONSTRAINT prid_sm_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE personnel_identifications ADD CONSTRAINT personnel_id_staff FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE personnel_issued_cards ADD CONSTRAINT per_issued_cards_staff FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE persons ADD CONSTRAINT persons_persons_fk FOREIGN KEY (root_person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE person_employments ADD CONSTRAINT person_employments_persons_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE person_identifiers ADD CONSTRAINT person_identifiers_persons_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE person_profiles ADD CONSTRAINT person_profiles_persons_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE plan_details ADD CONSTRAINT pla_det_cas_pln_fk FOREIGN KEY (offender_book_id,case_plan_id) REFERENCES case_plans(offender_book_id,case_plan_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE possible_recommendations ADD CONSTRAINT poss_rcmd_rcmd_f1 FOREIGN KEY (recommend_code) REFERENCES recommendations(recommend_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE pot_accumulate_to_types ADD CONSTRAINT pot_acc_eod_accumulate_to_fk FOREIGN KEY (eod_id_accumulate_to) REFERENCES event_order_types(eod_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE pot_accumulate_to_types ADD CONSTRAINT pot_acc_eod_fk FOREIGN KEY (eod_id) REFERENCES event_order_types(eod_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE pot_calculations ADD CONSTRAINT pot_calc_pot_fk FOREIGN KEY (order_type,order_code) REFERENCES probation_order_types(order_type,order_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE pot_concurrencies ADD CONSTRAINT pot_con_pot_concurrent_to_fk FOREIGN KEY (order_type_concurrent_to,order_code_concurrent_to) REFERENCES probation_order_types(order_type,order_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE pot_concurrencies ADD CONSTRAINT pot_con_pot_fk FOREIGN KEY (order_type,order_code) REFERENCES probation_order_types(order_type,order_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE pot_ranges ADD CONSTRAINT pot_range_pot_fk FOREIGN KEY (order_type,order_code) REFERENCES probation_order_types(order_type,order_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE pot_weightings ADD CONSTRAINT pot_wtg_pot_fk FOREIGN KEY (order_type,order_code) REFERENCES probation_order_types(order_type,order_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE pot_weightings ADD CONSTRAINT pot_wtg_tsr_fk FOREIGN KEY (range_code) REFERENCES time_served_ranges(range_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE printers ADD CONSTRAINT prtr_prtr_type_f1 FOREIGN KEY (printer_type) REFERENCES printer_types(printer_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE printing_abilities ADD CONSTRAINT prtg_ab_prtr_type_f1 FOREIGN KEY (printer_type) REFERENCES printer_types(printer_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE profile_types ADD CONSTRAINT prfl_type_prfl_cat_fk FOREIGN KEY (profile_category) REFERENCES profile_categories(profile_category) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE program_assessments ADD CONSTRAINT prg_assess_assess_fk FOREIGN KEY (assessment_id) REFERENCES assessments(assessment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE program_assessments ADD CONSTRAINT prg_assess_prg_fk FOREIGN KEY (program_id) REFERENCES program_services(program_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE program_contacts ADD CONSTRAINT prg_ctact_staff_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE program_questionnaires ADD CONSTRAINT ques_ques_cat_fk1 FOREIGN KEY (question_category_id) REFERENCES questionnair_categories(question_category_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE program_services ADD CONSTRAINT prg_serv_prg_serv_fk FOREIGN KEY (parent_program_id) REFERENCES program_services(program_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE program_services_profiles ADD CONSTRAINT prg_serv_prof_prg_serv_fk FOREIGN KEY (program_id) REFERENCES program_services(program_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE property_storages ADD CONSTRAINT ppty_stg_ppty_stg FOREIGN KEY (parent_storage_id) REFERENCES property_storages(property_storage_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE property_transactions ADD CONSTRAINT ppty_txn_agy_loc_f1 FOREIGN KEY (from_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE property_transactions ADD CONSTRAINT ppty_txn_agy_loc_f2 FOREIGN KEY (to_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE ptr_type_codes ADD CONSTRAINT ptr_detail_codes_fk1 FOREIGN KEY (type) REFERENCES ptr_detail_types(type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE purchase_orders ADD CONSTRAINT pur_ord_corp_f1 FOREIGN KEY (vendor_corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE purchase_orders ADD CONSTRAINT pur_ord_stk_loc_f1 FOREIGN KEY (caseload_id,stock_loc_id) REFERENCES stock_locations(caseload_id,stock_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE purchase_order_items ADD CONSTRAINT pur_oi_stk_item_f1 FOREIGN KEY (stock_item_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE qm_activities ADD CONSTRAINT refqm_processes72 FOREIGN KEY (process_id) REFERENCES qm_processes(process_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE qm_activities_ins ADD CONSTRAINT refqm_activities74 FOREIGN KEY (activity_id) REFERENCES qm_activities(activity_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_activities_ins ADD CONSTRAINT refqm_processes_ins75 FOREIGN KEY (process_ins_id) REFERENCES qm_processes_ins(process_ins_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_activities_ins ADD CONSTRAINT refreference_codes1221 FOREIGN KEY (qm_act_ins_status_domain,qm_act_ins_status_code) REFERENCES reference_codes(domain,code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_activities_ins ADD CONSTRAINT refstaff_members63 FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_activities_ins ADD CONSTRAINT refteams73 FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE qm_activities_ins_hist ADD CONSTRAINT refqm_processes_ins_hist1224 FOREIGN KEY (process_ins_id) REFERENCES qm_processes_ins_hist(process_ins_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE qm_compositions ADD CONSTRAINT refagency_locations1225 FOREIGN KEY (event_type_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_compositions ADD CONSTRAINT refagency_locations1229 FOREIGN KEY (managing_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_compositions ADD CONSTRAINT refqm_processes1226 FOREIGN KEY (process_id) REFERENCES qm_processes(process_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE qm_con_activity_teams ADD CONSTRAINT refqm_activities1227 FOREIGN KEY (activity_id) REFERENCES qm_activities(activity_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_con_activity_teams ADD CONSTRAINT refqm_compositions1231 FOREIGN KEY (composition_id) REFERENCES qm_compositions(composition_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_con_activity_teams ADD CONSTRAINT refteams1228 FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE qm_objects ADD CONSTRAINT refqm_processes65 FOREIGN KEY (process_id) REFERENCES qm_processes(process_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_objects ADD CONSTRAINT refreference_codes1217 FOREIGN KEY (qm_data_type_domain,qm_data_type_code) REFERENCES reference_codes(domain,code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE qm_object_ins ADD CONSTRAINT refqm_objects66 FOREIGN KEY (object_id) REFERENCES qm_objects(object_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_object_ins ADD CONSTRAINT refqm_processes_ins68 FOREIGN KEY (process_ins_id) REFERENCES qm_processes_ins(process_ins_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE qm_object_ins_hist ADD CONSTRAINT refqm_processes_ins_hist1223 FOREIGN KEY (process_ins_id) REFERENCES qm_processes_ins_hist(process_ins_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE qm_processes_ins ADD CONSTRAINT refqm_compositions1230 FOREIGN KEY (composition_id) REFERENCES qm_compositions(composition_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_processes_ins ADD CONSTRAINT refqm_processes67 FOREIGN KEY (process_id) REFERENCES qm_processes(process_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE qm_processes_ins ADD CONSTRAINT refreference_codes1220 FOREIGN KEY (qm_proc_ins_status_domain,qm_proc_ins_status_code) REFERENCES reference_codes(domain,code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE questionnaire_answers ADD CONSTRAINT que_ans_que_que_fk1 FOREIGN KEY (questionnaire_que_id) REFERENCES questionnaire_questions(questionnaire_que_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE questionnaire_answers ADD CONSTRAINT que_ans_que_que_fk2 FOREIGN KEY (next_questionnaire_que_id) REFERENCES questionnaire_questions(questionnaire_que_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE questionnaire_questions ADD CONSTRAINT que_que_que_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaires(questionnaire_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE questionnaire_roles ADD CONSTRAINT que_role_que_fk FOREIGN KEY (questionnaire_id) REFERENCES questionnaires(questionnaire_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE questionnair_categories ADD CONSTRAINT ques_cat_ques_type_fk1 FOREIGN KEY (question_type,question_sub_type) REFERENCES questionnair_types(question_type,question_sub_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE reference_codes ADD CONSTRAINT ref_code_ref_dmn_f1 FOREIGN KEY (domain) REFERENCES reference_domains(domain) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE reference_domains ADD CONSTRAINT ref_domain_ref_domain_fk1 FOREIGN KEY (parent_domain) REFERENCES reference_domains(domain) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE reference_domains ADD CONSTRAINT ref_domain_ref_domain_fk2 FOREIGN KEY (super_set_domain) REFERENCES reference_domains(domain) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE release_date_label_details ADD CONSTRAINT rel_dat_ld_rel_dat_lp_fk1 FOREIGN KEY (label_field,priority_seq) REFERENCES release_date_label_priorities(label_field,priority_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE release_date_label_priorities ADD CONSTRAINT rel_dat_l_rel_dat_lp_fk1 FOREIGN KEY (label_field) REFERENCES release_date_labels(label_field) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE release_plans ADD CONSTRAINT release_plans_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE report_paragraphs ADD CONSTRAINT report_paragraphs_fk1 FOREIGN KEY (report_id) REFERENCES reports(report_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE report_paragraphs ADD CONSTRAINT report_paragraphs_fk2 FOREIGN KEY (parent_paragraph_id) REFERENCES report_paragraphs(paragraph_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE report_placeholders ADD CONSTRAINT report_placeholders_fk1 FOREIGN KEY (paragraph_id) REFERENCES report_paragraphs(paragraph_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE report_placeholders ADD CONSTRAINT report_placeholders_fk2 FOREIGN KEY (mapping_id) REFERENCES report_placeholder_mappings(mapping_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE response_authurity_members ADD CONSTRAINT rsp_authm_staff_fk1 FOREIGN KEY (response_member_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE role_inaccessible_ref_codes ADD CONSTRAINT rle_inarc_oms_mod_fk1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE role_inaccessible_ref_codes ADD CONSTRAINT rle_inarc_oms_role_fk1 FOREIGN KEY (role_id) REFERENCES oms_roles(role_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE route_legs ADD CONSTRAINT rou_leg_leg_f1 FOREIGN KEY (from_location_id,to_location_id) REFERENCES legs(from_location_id,to_location_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE route_legs ADD CONSTRAINT rou_leg_rou_f1 FOREIGN KEY (route_name) REFERENCES routes(route_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE route_stop_details ADD CONSTRAINT rte_stp_d_agy_loc_fk1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE route_stop_details ADD CONSTRAINT rte_stp_d_routes_fk1 FOREIGN KEY (route_name) REFERENCES routes(route_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE rp_other_occupants ADD CONSTRAINT rp_other_occupants_fk1 FOREIGN KEY (release_plan_id) REFERENCES release_plans(release_plan_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE sales_maintenances ADD CONSTRAINT sale_main_stk_loc_fk1 FOREIGN KEY (caseload_id,stock_loc_id) REFERENCES stock_locations(caseload_id,stock_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE sales_orders ADD CONSTRAINT sal_ord_per_f1 FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE sales_orders ADD CONSTRAINT sal_ord_stk_loc_f1 FOREIGN KEY (caseload_id,stock_loc_id) REFERENCES stock_locations(caseload_id,stock_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE sales_order_items ADD CONSTRAINT sal_oi_sal_ord_f1 FOREIGN KEY (sales_order_id) REFERENCES sales_orders(sales_order_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE sales_order_items ADD CONSTRAINT sal_oi_stk_item_f1 FOREIGN KEY (stock_item_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE scheduled_trips ADD CONSTRAINT sch_trips_routes_fk1 FOREIGN KEY (route_name) REFERENCES routes(route_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE scheduled_trips ADD CONSTRAINT sch_trips_trips_fk1 FOREIGN KEY (trip_code) REFERENCES trips(trip_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE scheduled_trip_assignments ADD CONSTRAINT sch_tp_a_sch_trips_fk1 FOREIGN KEY (scheduled_trip_id) REFERENCES scheduled_trips(scheduled_trip_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE scheduled_trip_details ADD CONSTRAINT sch_trpd_sch_trip_fk1 FOREIGN KEY (scheduled_trip_id) REFERENCES scheduled_trips(scheduled_trip_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE scheduled_trip_parameters ADD CONSTRAINT sch_trpp_routes_fk2 FOREIGN KEY (sunday) REFERENCES routes(route_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE scheduled_trip_parameters ADD CONSTRAINT sch_trpp_trip_fk1 FOREIGN KEY (trip_code) REFERENCES trips(trip_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE security_threat_groups ADD CONSTRAINT stg_stg_fk1 FOREIGN KEY (parent_stg_id) REFERENCES security_threat_groups(stg_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE security_threat_groups_hty ADD CONSTRAINT stg_hty_stg_fk1 FOREIGN KEY (stg_id) REFERENCES security_threat_groups(stg_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE sentence_terms ADD CONSTRAINT sent_term_sent_calc_type_fk FOREIGN KEY (sentence_category,sentence_calc_type) REFERENCES sentence_calc_types(sentence_category,sentence_calc_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE sentence_term_adjustments ADD CONSTRAINT sent_term_adj_sent_adj_f1 FOREIGN KEY (adjust_code) REFERENCES sentence_adjustments(sentence_adjust_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE sentence_term_adjustments ADD CONSTRAINT sent_term_adj_sent_term_fk FOREIGN KEY (sentence_category,sentence_calc_type,term_code) REFERENCES sentence_terms(sentence_category,sentence_calc_type,term_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE sentence_update_reasons ADD CONSTRAINT sent_upd_rsn_lgl_upd_usg_fk FOREIGN KEY (legal_class,update_reason_code) REFERENCES legal_update_usages(legal_class,update_reason_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE sentence_update_reasons ADD CONSTRAINT sent_upd_rsn_sent_fk FOREIGN KEY (sentence_category,sentence_calc_type) REFERENCES sentence_calc_types(sentence_category,sentence_calc_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE shipments ADD CONSTRAINT ship_corp_f1 FOREIGN KEY (vendor_corp_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE shipments ADD CONSTRAINT ship_stk_loc_f1 FOREIGN KEY (caseload_id,stock_loc_id) REFERENCES stock_locations(caseload_id,stock_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE shipment_items ADD CONSTRAINT ship_item_pur_ord_f1 FOREIGN KEY (purchase_order_id) REFERENCES purchase_orders(purchase_order_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE shipment_items ADD CONSTRAINT ship_item_ship_f1 FOREIGN KEY (shipment_id) REFERENCES shipments(shipment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE shipment_items ADD CONSTRAINT ship_item_stock_item_f1 FOREIGN KEY (stock_item_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE staff_accessible_caseloads ADD CONSTRAINT staff_ac_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE staff_accessible_caseloads ADD CONSTRAINT staff_ac_staff_f1 FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE staff_accessible_stock_locs ADD CONSTRAINT staff_ast_staff_f1 FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE staff_accessible_stock_locs ADD CONSTRAINT staff_ast_stk_loc_f1 FOREIGN KEY (caseload_id,stock_loc_id) REFERENCES stock_locations(caseload_id,stock_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE staff_location_roles ADD CONSTRAINT staff_lr_agy_loc_fk1 FOREIGN KEY (cal_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE staff_location_roles ADD CONSTRAINT staff_lr_staff_fk1 FOREIGN KEY (sac_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE staff_location_roles ADD CONSTRAINT staff_lr_staff_lr_fk1 FOREIGN KEY (supervisor_staff_id,supervisor_agy_loc_id,supervisor_from_date,supervisor_position,supervisor_role) REFERENCES staff_location_roles(sac_staff_id,cal_agy_loc_id,from_date,position,role) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE staff_members ADD CONSTRAINT staff_csld_f1 FOREIGN KEY (assigned_caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE staff_members ADD CONSTRAINT staff_csld_f2 FOREIGN KEY (working_caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE staff_members ADD CONSTRAINT staff_ptr_f2 FOREIGN KEY (comm_receipt_printer_id) REFERENCES printers(printer_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE staff_members ADD CONSTRAINT staff_staff_f1 FOREIGN KEY (supervisor_staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE staff_member_roles ADD CONSTRAINT staff_role_oms_role_f1 FOREIGN KEY (role_id) REFERENCES oms_roles(role_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE NOT VALID;

ALTER TABLE staff_member_roles ADD CONSTRAINT staff_role_staff_f1 FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE staff_member_roles ADD CONSTRAINT stf_mbr_roles_oms_roles_fk2 FOREIGN KEY (role_code) REFERENCES oms_roles(role_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE NOT VALID;
ALTER TABLE staff_skills ADD CONSTRAINT stsk_sm_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE staff_works ADD CONSTRAINT stf_wrk_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE staff_works ADD CONSTRAINT stf_wrk_wrkflw_wrk_type_fk1 FOREIGN KEY (workflow_type,work_type,work_sub_type) REFERENCES workflow_work_types(workflow_type,work_type,work_sub_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE staff_work_assignments ADD CONSTRAINT stf_wrk_asgn_stf_wrk_fk1 FOREIGN KEY (staff_work_id) REFERENCES staff_works(staff_work_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stg_case_notes ADD CONSTRAINT stg_cas_n_stg_fk1 FOREIGN KEY (stg_id) REFERENCES security_threat_groups(stg_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stg_identifiers ADD CONSTRAINT stg_id_stg_fk1 FOREIGN KEY (stg_id) REFERENCES security_threat_groups(stg_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stg_identifying_words ADD CONSTRAINT stg_id_w_stg_fk1 FOREIGN KEY (stg_id) REFERENCES security_threat_groups(stg_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stg_locations ADD CONSTRAINT stg_loc_stg_fk1 FOREIGN KEY (stg_id) REFERENCES security_threat_groups(stg_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stg_relationships ADD CONSTRAINT stg_rshp_stg_fk1 FOREIGN KEY (stg_id) REFERENCES security_threat_groups(stg_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stg_validations ADD CONSTRAINT stg_val_stg_fk1 FOREIGN KEY (stg_id) REFERENCES security_threat_groups(stg_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stock_category_item_limits ADD CONSTRAINT stk_cil_stk_itm_f1 FOREIGN KEY (stock_item_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stock_items ADD CONSTRAINT stk_item_corp_f1 FOREIGN KEY (primary_vendor_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE stock_items ADD CONSTRAINT stk_item_corp_f2 FOREIGN KEY (secondary_vendor_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE stock_items ADD CONSTRAINT stk_item_stk_item_f1 FOREIGN KEY (caselot_stock_item_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE stock_items ADD CONSTRAINT stk_item_stk_loc_f1 FOREIGN KEY (caseload_id,stock_loc_id) REFERENCES stock_locations(caseload_id,stock_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stock_item_alerts ADD CONSTRAINT stk_ia_stk_item_f1 FOREIGN KEY (stock_item_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE stock_item_alerts ADD CONSTRAINT stk_ia_stk_loc_f1 FOREIGN KEY (caseload_id,stock_loc_id) REFERENCES stock_locations(caseload_id,stock_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stock_item_audits ADD CONSTRAINT stk_audit_stk_item_fk1 FOREIGN KEY (stock_item_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stock_item_transactions ADD CONSTRAINT stk_it_ship_item_f1 FOREIGN KEY (shipment_item_id) REFERENCES shipment_items(shipment_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE stock_item_transactions ADD CONSTRAINT stk_it_stk_item_f1 FOREIGN KEY (stock_item_id) REFERENCES stock_items(stock_item_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE stock_locations ADD CONSTRAINT stk_loc_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE substance_samples ADD CONSTRAINT sub_sampl_staff_f1 FOREIGN KEY (taken_by) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE substance_samples ADD CONSTRAINT sub_sampl_staff_f2 FOREIGN KEY (witness) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE substance_tests ADD CONSTRAINT sub_test_staff_f1 FOREIGN KEY (tested_by) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE substance_tests ADD CONSTRAINT sub_test_staff_f2 FOREIGN KEY (witness) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE substance_tests ADD CONSTRAINT sub_test_sub_sampl_f1 FOREIGN KEY (offender_book_id,sample_seq) REFERENCES substance_samples(offender_book_id,sample_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE substance_test_results ADD CONSTRAINT sub_tr_sub_test_f1 FOREIGN KEY (offender_book_id,sample_seq,test_seq) REFERENCES substance_tests(offender_book_id,sample_seq,test_seq) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE sub_account_views ADD CONSTRAINT s_av_ac_code_f1 FOREIGN KEY (account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE supervision_conditions ADD CONSTRAINT sup_cond_cond_f1 FOREIGN KEY (condition_code) REFERENCES conditions(condition_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE supervision_rates ADD CONSTRAINT sup_rate_sup_chg_f1 FOREIGN KEY (supervision_type,charge_code) REFERENCES supervision_charges(supervision_type,charge_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE supervision_transactions ADD CONSTRAINT sup_txn_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE supervision_transactions ADD CONSTRAINT sup_txn_off_bkg_f1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE supervision_violations ADD CONSTRAINT sup_vio_sup_cond_f1 FOREIGN KEY (order_id,condition_seq) REFERENCES supervision_conditions(order_id,condition_seq) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE suspend_deductions ADD CONSTRAINT sus_ded_caseload_fk1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE suspend_deduction_types ADD CONSTRAINT sus_dt_ded_type_fk1 FOREIGN KEY (deduction_type) REFERENCES deduction_types(deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE suspend_deduction_types ADD CONSTRAINT sus_dt_sus_ded_fk1 FOREIGN KEY (suspend_deduction_id) REFERENCES suspend_deductions(suspend_deduction_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE task_assignment_hty ADD CONSTRAINT work_assgn_hty_off_bkg_fk FOREIGN KEY (offender_book_id) REFERENCES offender_bookings(offender_book_id) ON DELETE NO ACTION DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE task_assignment_hty ADD CONSTRAINT work_assgn_hty_stf_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE task_assignment_hty ADD CONSTRAINT work_assgn_hty_team_fk FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE task_assignment_hty ADD CONSTRAINT work_assgn_hty_team_membr_fk FOREIGN KEY (team_member_id) REFERENCES team_members(team_member_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE task_assignment_hty ADD CONSTRAINT wrk_assgn_hty_work_fk FOREIGN KEY (work_id) REFERENCES works(work_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE tax_rates ADD CONSTRAINT tax_ra_tax_tp_eff_date_fk1 FOREIGN KEY (tax_type,effective_date) REFERENCES tax_type_effective_date(tax_type,effective_date) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE team_functions ADD CONSTRAINT team_functions_teams_fk FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE team_members ADD CONSTRAINT team_members_teams_fk FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE team_members ADD CONSTRAINT team_membr_stf_fk FOREIGN KEY (staff_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE team_members ADD CONSTRAINT team_mem_staff_lr_fk2 FOREIGN KEY (staff_id,agy_loc_id,loc_role_from_date,position,role) REFERENCES staff_location_roles(sac_staff_id,cal_agy_loc_id,from_date,position,role) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE template_cursor_selections ADD CONSTRAINT tmp_cursel_doc_temp_fk1 FOREIGN KEY (template_code) REFERENCES document_templates(template_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE template_types ADD CONSTRAINT tmp_typ_mod_accd_fk1 FOREIGN KEY (module_name,template_code) REFERENCES module_accessible_documents(module_name,template_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE temp_absence_groups ADD CONSTRAINT ta_grp_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE temp_absence_groups ADD CONSTRAINT ta_grp_csld_f1 FOREIGN KEY (caseload_id) REFERENCES caseloads(caseload_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE temp_absence_group_dtls ADD CONSTRAINT ta_dtl_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE temp_absence_group_dtls ADD CONSTRAINT ta_dtl_ta_grp_f1 FOREIGN KEY (ta_id) REFERENCES temp_absence_groups(ta_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE temp_absence_time_tables ADD CONSTRAINT tag_tatt_f1 FOREIGN KEY (ta_id) REFERENCES temp_absence_groups(ta_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE temp_absence_time_tables ADD CONSTRAINT ta_att_agy_loc_f1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE tiered_fee_amounts ADD CONSTRAINT tier_fa_csld_dd_f1 FOREIGN KEY (caseload_id,deduction_type,receipt_txn_type) REFERENCES caseload_deduction_details(caseload_id,deduction_type,receipt_txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE tiered_transaction_fee_amounts ADD CONSTRAINT tier_tfa_csld_dp_fk1 FOREIGN KEY (caseload_id,deduction_type) REFERENCES caseload_deduction_profiles(caseload_id,deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE transaction_fee_details ADD CONSTRAINT tran_fd_csld_dp_fk1 FOREIGN KEY (caseload_id,deduction_type) REFERENCES caseload_deduction_profiles(caseload_id,deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE transaction_payees ADD CONSTRAINT txn_payee_corp_f1 FOREIGN KEY (payee_corporate_id) REFERENCES corporates(corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE transaction_payees ADD CONSTRAINT txn_payee_per_f1 FOREIGN KEY (payee_person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE transaction_payees ADD CONSTRAINT txn_payee_txn_type_f1 FOREIGN KEY (txn_type) REFERENCES transaction_types(txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE transaction_types ADD CONSTRAINT txn_type_ded_type_f1 FOREIGN KEY (credit_obligation_type) REFERENCES deduction_types(deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE NOT VALID;
ALTER TABLE transfer_table_relationships ADD CONSTRAINT trf_tab_rel_trf_bkg_tab_fk1 FOREIGN KEY (parent_table) REFERENCES transfer_booking_tables(table_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE transfer_table_relationships ADD CONSTRAINT trf_tab_rel_trf_bkg_tab_fk2 FOREIGN KEY (child_table) REFERENCES transfer_booking_tables(table_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE trips ADD CONSTRAINT trp_rou_f1 FOREIGN KEY (route_name) REFERENCES routes(route_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE trips ADD CONSTRAINT trp_veh_f1 FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE txn_ops_invalid_accounts ADD CONSTRAINT txn_inv_ac_code_f1 FOREIGN KEY (invalid_account_code) REFERENCES account_codes(account_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE user_accessible_case_notes ADD CONSTRAINT uac_or_fk FOREIGN KEY (role_id) REFERENCES oms_roles(role_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE user_caseload_roles ADD CONSTRAINT user_caseload_roles_fk1 FOREIGN KEY (caseload_id,username) REFERENCES user_accessible_caseloads(caseload_id,username) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE user_caseload_roles ADD CONSTRAINT user_caseload_roles_fk2 FOREIGN KEY (role_id) REFERENCES oms_roles(role_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE vehicles ADD CONSTRAINT veh_fxd_ast_f1 FOREIGN KEY (asset_id) REFERENCES fixed_assets(asset_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE vehicle_mileages ADD CONSTRAINT veh_mil_fxd_ast_f1 FOREIGN KEY (asset_id) REFERENCES fixed_assets(asset_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE vehicle_mileages ADD CONSTRAINT veh_mil_veh_f1 FOREIGN KEY (asset_id) REFERENCES vehicles(asset_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE version_controls ADD CONSTRAINT ver_con_oms_mod_f1 FOREIGN KEY (file_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE version_control_details ADD CONSTRAINT ver_det_ver_con_f1 FOREIGN KEY (file_name) REFERENCES version_controls(file_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE video_review_sections ADD CONSTRAINT video_review_sections_fk1 FOREIGN KEY (video_review_summary_id) REFERENCES video_review_summaries(video_review_summary_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE video_review_subsections ADD CONSTRAINT video_review_subsections_fk1 FOREIGN KEY (section_code,video_review_summary_id) REFERENCES video_review_sections(section_code,video_review_summary_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE video_review_summaries ADD CONSTRAINT video_review_summaries_fk1 FOREIGN KEY (crs_sch_id) REFERENCES course_schedules(crs_sch_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE visiting_groups ADD CONSTRAINT vis_grps_agy_loc_fk1 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE visiting_groups ADD CONSTRAINT vis_grps_staf_mem_fk1 FOREIGN KEY (approved_by_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE visiting_groups ADD CONSTRAINT vis_grps_staf_mem_fk2 FOREIGN KEY (escorted_by_id) REFERENCES staff_members(staff_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE visiting_members ADD CONSTRAINT vis_membs_vis_grps_fk1 FOREIGN KEY (group_id) REFERENCES visiting_groups(group_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE visitor_restrictions ADD CONSTRAINT visitor_restriction_per_fk FOREIGN KEY (person_id) REFERENCES persons(person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE visit_allowance_levels ADD CONSTRAINT vis_lev_iep_lev_fk FOREIGN KEY (iep_level,agy_loc_id) REFERENCES iep_levels(iep_level,agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE visit_type_limits ADD CONSTRAINT vis_typ_lim_vis_cyc_lim_fk FOREIGN KEY (visit_cycle_limit_id) REFERENCES visit_cycle_limits(visit_cycle_limit_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE workflow_functions ADD CONSTRAINT wrkflw_fcn_wrkflw_type_fk1 FOREIGN KEY (workflow_type,work_type,work_sub_type) REFERENCES workflow_work_types(workflow_type,work_type,work_sub_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE workflow_screens ADD CONSTRAINT wk_folders_wk_screens_fk1 FOREIGN KEY (workflow_code) REFERENCES workflow_folders(workflow_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE workflow_screens ADD CONSTRAINT wk_screens_oms_mod_fk1 FOREIGN KEY (module_name) REFERENCES oms_modules(module_name) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE workflow_triggers ADD CONSTRAINT wrkflw_trg_wrkflw_types_fk1 FOREIGN KEY (workflow_type,work_type,work_sub_type) REFERENCES workflow_work_types(workflow_type,work_type,work_sub_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE work_assignments ADD CONSTRAINT wrk_asg_csld_wrkg_f1 FOREIGN KEY (caseload_id,work_group_id) REFERENCES caseload_work_groups(caseload_id,work_group_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE work_assignments ADD CONSTRAINT wrk_asg_csld_wrk_f1 FOREIGN KEY (caseload_id,work_code) REFERENCES caseload_works(caseload_id,work_code) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE work_assignment_statuses ADD CONSTRAINT wrk_as_st_wrk_as_fk1 FOREIGN KEY (work_assignment_id) REFERENCES work_assignments(work_assignment_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE work_flow_logs ADD CONSTRAINT work_fl_agy_loc_f1 FOREIGN KEY (locate_agy_loc_id) REFERENCES agency_locations(agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE work_flow_logs ADD CONSTRAINT work_fl_work_flow_pk FOREIGN KEY (work_flow_id) REFERENCES work_flows(work_flow_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE work_functions ADD CONSTRAINT work_funrction_work_fk FOREIGN KEY (work_id) REFERENCES works(work_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE work_iwp_templates ADD CONSTRAINT wrk_iwp_templ_iwp_templ_fk FOREIGN KEY (template_id) REFERENCES iwp_templates(template_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE work_iwp_templates ADD CONSTRAINT wrk_iwp_templ_work_fk FOREIGN KEY (work_id) REFERENCES works(work_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE work_triggers ADD CONSTRAINT work_trigger_work_fk FOREIGN KEY (work_id) REFERENCES works(work_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
alter table OFFENDER_PAYROLL_TXNS add constraint OFF_PRT_OFF_TXN_F1 foreign key(TXN_ID,TXN_ENTRY_SEQ) references OFFENDER_TRANSACTIONS(TXN_ID,TXN_ENTRY_SEQ) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
alter table OFFENDER_TRANSACTIONS add constraint OFF_TXN_OFF_TXN_F1 foreign key(ADJUST_TXN_ID,ADJUST_TXN_ENTRY_ID) references OFFENDER_TRANSACTIONS(TXN_ID,TXN_ENTRY_SEQ) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
alter table MERGE_TRANSACTION_LOGS add constraint MERGE_TRANSACTION_LOGS_FK1 foreign key(MERGE_TRANSACTION_ID) references MERGE_TRANSACTIONS(MERGE_TRANSACTION_ID) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
alter table OFFENDER_COURSE_SKILLS add constraint OFF_CRS_SKL_OFF_CRS_ATT_FK foreign key(EVENT_ID) references OFFENDER_COURSE_ATTENDANCES(EVENT_ID) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
alter table OFFENDER_CASE_NOTE_SENTS add constraint OFF_CN_SENT_OFF_CN_FK foreign key(CASE_NOTE_ID) references OFFENDER_CASE_NOTES(CASE_NOTE_ID) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
alter table OFF_CASE_NOTE_RECIPIENTS add constraint OFF_CN_RECPT_OFF_CN_FK foreign key(CASE_NOTE_ID) references OFFENDER_CASE_NOTES(CASE_NOTE_ID) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
alter table MERGE_TRANSACTION_PROCESSES add constraint MERG_TXN_PROC_MERG_TXN_FK foreign key(MERGE_TRANSACTION_ID) references MERGE_TRANSACTIONS(MERGE_TRANSACTION_ID) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
alter table OFFENDER_CREDIT_PRIOR_PAYMENTS add constraint OFF_CPP_OFF_TXN_FK1 foreign key(TXN_ID,TXN_ENTRY_SEQ) references OFFENDER_TRANSACTIONS(TXN_ID,TXN_ENTRY_SEQ) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;