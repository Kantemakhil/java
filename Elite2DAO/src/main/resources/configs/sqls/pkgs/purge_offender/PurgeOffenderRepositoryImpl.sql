BOOKING_GET_OFFENDERS_BOOKING{
SELECT offender_id FROM offenders WHERE 1=1 AND offender_id = :p_offender_id FOR UPDATE NOWAIT
}
BOOKING_GET_BIO_SUBJECTS{
SELECT subject_id FROM bio_subjects WHERE 1=1 AND root_offender_id = :p_root_offender_id FOR UPDATE NOWAIT
}
BOOKING_GET_BIO_SCANS{
SELECT subject_id, scan_id FROM bio_scans WHERE 1=1 AND subject_id = :p_subject_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_BIO_FP_SAMPLES{
DELETE FROM bio_fp_samples WHERE 1=1 AND scan_id = :p_scan_id AND subject_id = :p_subject_id
}
BOOKING_DELETE_BIO_SCANS{
DELETE FROM bio_scans WHERE 1=1 AND subject_id = p_subject_id
}
BOOKING_DELETE_BIO_EVENT_LOGS{
DELETE FROM bio_event_logs WHERE 1=1 AND subject_id = :p_subject_id
}

BOOKING_DELETE_BIO_SUBJECTS{
DELETE FROM bio_subjects WHERE 1=1 AND root_offender_id = :p_root_offender_id
}
BOOKING_UPDATE_GL_TRANSACTIONS{
UPDATE gl_transactions SET offender_id = NULL WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_GET_OFFENDERS1{
SELECT offender_id FROM offenders WHERE 1=1 AND alias_offender_id = :p_alias_offender_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_BALANCES{
DELETE FROM offender_balances WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_GET_OFFENDER_BOOKINGS{
SELECT offender_book_id FROM offender_bookings WHERE 1=1 AND offender_id = :p_offender_id FOR UPDATE NOWAIT
}
BOOKING_GET_AGENCY_INCIDENT_PARTIES{
SELECT agency_incident_id, party_seq, oic_incident_id FROM agency_incident_parties WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_GET_AGENCY_INCIDENT_CHARGES{
SELECT agency_incident_id, oic_charge_id, charge_seq FROM agency_incident_charges WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq FOR UPDATE NOWAIT
}
BOOKING_GET_OIC_HEARING_RESULTS{
SELECT result_seq, agency_incident_id, charge_seq, oic_hearing_id FROM oic_hearing_results WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND charge_seq = :p_charge_seq FOR UPDATE NOWAIT
}
BOOKING_GET_OFFENDER_OIC_APPEAL_INCIDENTS{
SELECT result_seq, oic_hearing_id, oic_appreal_id FROM offender_oic_appeal_incidents WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_OIC_APPEAL_PENALTIES{
DELETE FROM offender_oic_appeal_penalties WHERE 1=1 AND oic_appreal_id = :p_oic_appreal_id AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq
}
BOOKING_DELETE_OFFENDER_OIC_APPEAL_INCIDENTS{
DELETE FROM offender_oic_appeal_incidents WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq
}
BOOKING_DELETE_OFFENDER_OIC_SANC_REVIEWS{
DELETE FROM offender_oic_sanc_reviews WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sanction_seq = :p_sanction_seq
}
BOOKING_GET_OFFENDER_OIC_SANCTIONS{
SELECT offender_book_id, sanction_seq FROM offender_oic_sanctions WHERE 1=1 AND consecutive_offender_book_id = :p_consecutiv_offndr_book_id AND consecutive_sanction_seq = :p_consecutive_sanction_seq FOR UPDATE NOWAIT 
}
BOOKING_DELETE_OFFENDER_OIC_SANCTIONS{
DELETE FROM offender_oic_sanctions WHERE 1=1 AND consecutive_offender_book_id = :p_consecutiv_offndr_book_id AND consecutive_sanction_seq = :p_consecutive_sanction_seq
}
BOOKING_GET_OFFENDER_OIC_SANCTIONS1{
SELECT offender_book_id, sanction_seq FROM offender_oic_sanctions WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_OIC_SANCTIONS1{
DELETE FROM offender_oic_sanctions WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq 
}
BOOKING_DELETE_OFFENDER_CASE_NOTE_SENTS{
DELETE FROM offender_case_note_sents WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_COMMUNITY_CONDITIONS{
DELETE FROM offender_community_conditions WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_IND_SCH_SENTS{
DELETE FROM offender_ind_sch_sents WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_LICENCE_SENTENCES{
DELETE FROM offender_licence_sentences WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_LICENCE_SENTENCES1{
DELETE FROM offender_licence_sentences WHERE 1=1 AND offender_book_id = :p_offender_book_id AND licence_sentence_seq = :p_licence_sentence_seq
}
BOOKING_DELETE_OFFENDER_PROCEEDING_SENTS{
DELETE FROM offender_proceeding_sents WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_SENTENCE_ADJUSTS{
DELETE FROM offender_sentence_adjusts WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_SENTENCE_CHARGES{
DELETE FROM offender_sentence_charges WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_SENTENCE_HTY{
DELETE FROM offender_sentence_hty WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_SENTENCE_STATUSES{
DELETE FROM offender_sentence_statuses WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_SENTENCE_TERMS{
DELETE FROM offender_sentence_terms WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_SENTENCE_UA_EVENTS{
DELETE FROM offender_sentence_ua_events WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_UNPAID_WORK_ADJ{
DELETE FROM offender_unpaid_work_adj WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_GET_OFFENDER_SENTENCES{
SELECT offender_book_id, sentence_seq FROM offender_sentences WHERE 1=1 AND order_id = :p_order_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_COURT_EVENT_SENTENCES{
DELETE FROM court_event_sentences WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
BOOKING_DELETE_OFFENDER_SENTENCES{
DELETE FROM offender_sentences WHERE 1=1 AND order_id = :p_order_id
}
BOOKING_DELETE_OFFENDER_COMMUNITY_FILES{
DELETE FROM offender_community_files WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_DELETE_OFFENDER_IDENTIFIERS{
DELETE FROM offender_identifiers WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_DELETE_OFFENDER_OBLI_VERIFICATIONS{
DELETE FROM offender_obli_verifications WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_GET_OFFENDER_PAYMENT_PROFILES{
SELECT payment_mode, start_date, caseload_id, offender_payment_profile_id, offender_id, txn_type FROM offender_payment_profiles WHERE 1=1 AND offender_id = :p_offender_id FOR UPDATE NOWAIT
}
BOOKING_GET_OFFENDER_DEDUCTIONS{
SELECT information_number, deduction_priority, offender_deduction_id, caseload_id, offender_id, deduction_type FROM offender_deductions WHERE 1=1 AND offender_payment_profile_id = :p_offender_paymnt_profil_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_ADJUSTMENT_TXNS{
DELETE FROM offender_adjustment_txns WHERE 1=1 AND offender_deduction_id = :p_offender_deduction_id
}
BOOKING_GET_OFFENDER_BENEFICIARIES{
SELECT beneficiary_id, person_id, offender_deduction_id, corporate_id FROM offender_beneficiaries WHERE 1=1 AND offender_deduction_id = :p_offender_deduction_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_BENEFICIARY_TRANSACTIONS{
DELETE FROM beneficiary_transactions WHERE 1=1 AND beneficiary_id = :p_beneficiary_id
}
BOOKING_DELETE_OFFENDER_CREDIT_PRIOR_PAYMENTS{
DELETE FROM offender_credit_prior_payments WHERE 1=1 AND beneficiary_id = :p_beneficiary_id
}
BOOKING_DELETE_OFFENDER_MON_BENEFICIARIES{
DELETE FROM offender_mon_beneficiaries WHERE 1=1 AND beneficiary_id = :p_beneficiary_id
}
BOOKING_DELETE_OFFENDER_BENEFICIARIES{
DELETE FROM offender_beneficiaries WHERE 1=1 AND offender_deduction_id = :p_offender_deduction_id
}
BOOKING_DELETE_OFFENDER_DEDUCTION_RECEIPTS{
DELETE FROM offender_deduction_receipts WHERE 1=1 AND offender_deduction_id = :p_offender_deduction_id
}
BOOKING_DELETE_OFFENDER_DEDUCTION_SHADOWS{
DELETE FROM offender_deduction_shadows WHERE 1=1 AND offender_deduction_id = :p_offender_deduction_id
}
BOOKING_DELETE_OFFENDER_MON_BENEFICIARIES1{
DELETE FROM offender_mon_beneficiaries WHERE 1=1 AND offender_deduction_id = :p_offender_deduction_id
}
BOOKING_DELETE_OFFENDER_MON_DEDUCTIONS{
DELETE FROM offender_mon_deductions WHERE 1=1 AND offender_deduction_id = :p_offender_deduction_id
}
BOOKING_DELETE_OFFENDER_TIER_TXN_FEE_AMOUNTS{
DELETE FROM offender_tier_txn_fee_amounts WHERE 1=1 AND offender_deduction_id = :p_offender_deduction_id
}
BOOKING_DELETE_OFFENDER_TXN_FEE_DETAILS{
DELETE FROM offender_txn_fee_details WHERE 1=1 AND offender_deduction_id = :p_offender_deduction_id
}
BOOKING_GET_OFFENDER_DEDUCTIONS1{
SELECT information_number, deduction_priority, offender_deduction_id, caseload_id, offender_id, deduction_type FROM offender_deductions WHERE 1=1 AND parent_deduction_id = :p_parent_deduction_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_DEDUCTIONS{
DELETE FROM offender_deductions WHERE 1=1 AND parent_deduction_id = :p_parent_deduction_id
}
BOOKING_DELETE_OFFENDER_DEDUCTIONS1{
DELETE FROM offender_deductions WHERE 1=1 AND offender_payment_profile_id = :p_offender_paymnt_profil_id
}
BOOKING_DELETE_OFFENDER_PLACEMENT_SCORES{
DELETE FROM offender_placement_scores WHERE 1=1 AND root_offender_id = :p_root_offender_id
}
BOOKING_GET_OFFENDER_TRANSACTIONS{
SELECT txn_entry_seq, txn_id FROM offender_transactions WHERE 1=1 AND offender_id = :p_offender_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_CREDIT_PRIOR_PAYMENTS1{
DELETE FROM offender_credit_prior_payments WHERE 1=1 AND txn_id = :p_txn_id AND txn_entry_seq = :p_txn_entry_seq
}
BOOKING_DELETE_OFFENDER_PAYROLL_TXNS{
DELETE FROM offender_payroll_txns WHERE 1=1 AND txn_id = :p_txn_id AND txn_entry_seq = :p_txn_entry_seq
}
BOOKING_GET_OFFENDER_TRANSACTIONS1{
SELECT txn_entry_seq, txn_id FROM offender_transactions WHERE 1=1 AND adjust_txn_id = :p_adjust_txn_id AND adjust_txn_entry_id = :p_adjust_txn_entry_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_TRANSACTIONS{
DELETE FROM offender_transactions WHERE 1=1 AND adjust_txn_id = :p_adjust_txn_id AND adjust_txn_entry_id = :p_adjust_txn_entry_id
}
BOOKING_DELETE_OFFENDER_TRANSACTIONS1{
DELETE FROM offender_transactions WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_DELETE_OFFENDER_TRUST_ACCOUNTS_TEMP{
DELETE FROM offender_trust_accounts_temp WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_DELETE_OFFENDER_VITAL_SIGNS{
DELETE FROM offender_vital_signs WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_GET_ADDRESSES_BOOKING{
SELECT address_id FROM addresses WHERE 1=1 AND owner_id = :p_owner_id AND owner_class = :p_owner_class FOR UPDATE NOWAIT
}
BOOKING_DELETE_ADDRESS_USAGES{
DELETE FROM address_usages WHERE 1=1 AND address_id = :p_address_id
}
BOOKING_GET_COURSE_ACTIVITIES_BOOKING{
SELECT crs_acty_id FROM course_activities WHERE 1=1 AND services_address_id = :p_services_address_id FOR UPDATE NOWAIT
}
BOOKING_GET_COURSE_ACTIVITIES1{
SELECT crs_acty_id FROM course_activities WHERE 1=1 AND parent_crs_acty_id = :p_parent_crs_acty_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_COURSE_ACTIVITY_AREAS{
DELETE FROM course_activity_areas WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
BOOKING_DELETE_COURSE_ACTIVITY_PARTIES{
DELETE FROM course_activity_parties WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
BOOKING_DELETE_COURSE_ACTIVITY_PROFILES{
DELETE FROM course_activity_profiles WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
BOOKING_DELETE_COURSE_ACTIVITY_REVIEWS{
DELETE FROM course_activity_reviews WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
BOOKING_GET_COURSE_SCHEDULES{
SELECT crs_sch_id FROM course_schedules WHERE 1=1 AND crs_acty_id = :p_crs_acty_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_COURSE_SCHEDULE_RULES{
DELETE FROM course_schedule_rules WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
BOOKING_GET_OFFENDER_COURSE_ATTENDANCES_BOOKING{
select event_id from offender_course_attendances where 1 = 1 and crs_acty_id = :p_crs_acty_id for update nowait
}
BOOKING_DELETE_OFFENDER_COURSE_SKILLS{
DELETE FROM offender_course_skills WHERE 1=1  AND event_id = :p_event_id
}
BOOKING_DELETE_OFFENDER_COURSE_ATTENDANCES{
DELETE FROM offender_course_attendances WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
BOOKING_GET_OFFENDER_PROGRAM_PROFILES_BOOKING{
SELECT off_prgref_id FROM offender_program_profiles WHERE 1=1 AND crs_acty_id = :p_crs_acty_id FOR UPDATE NOWAIT
}
BOOKING_GET_OFFENDER_COURSE_APPT_GRPS_BOOKING{
SELECT offender_course_appt_grp_id FROM offender_course_appt_grps WHERE 1=1 AND off_prgref_id = :p_off_prgref_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_COURSE_APPT_RULES{
DELETE FROM offender_course_appt_rules WHERE 1=1 AND offender_course_appt_grp_id = :p_offender_cours_ppt_grp_id
}
BOOKING_DELETE_OFFENDER_COURSE_APPT_GRPS{
DELETE FROM offender_course_appt_grps WHERE 1=1 AND off_prgref_id = :p_off_prgref_id
}
BOOKING_GET_OFFENDER_COURSE_ATTENDANCES1_BOOKING{
SELECT event_id FROM offender_course_attendances WHERE 1=1 AND off_prgref_id = :p_off_prgref_id FOR UPDATE NOWAIT
}
BOOKING_GET_OFFENDER_PROGRAM_PROFILES1_BOOKING{
SELECT off_prgref_id FROM offender_program_profiles WHERE 1=1 AND program_off_prgref_id = :p_program_off_prgref_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_PROGRAM_PROFILES{
DELETE FROM offender_program_profiles WHERE 1=1 AND program_off_prgref_id = :p_program_off_prgref_id
}
BOOKING_DELETE_COURSE_ACTIVITIES{
DELETE FROM course_activities WHERE 1=1 AND parent_crs_acty_id = :p_parent_crs_acty_id
}
BOOKING_DELETE_COURSE_ACTIVITIES1{
DELETE FROM course_activities WHERE 1=1 AND services_address_id = :p_services_address_id
}
BOOKING_GET_CURFEW_ADDRESSES_BOOKING{
SELECT curfew_address_id FROM curfew_addresses WHERE 1=1 AND address_id = :p_address_id FOR UPDATE nowait
}
BOOKING_DELETE_CURFEW_ADDRESS_OCCUPANTS{
DELETE FROM curfew_address_occupants WHERE 1=1 AND curfew_address_id = :p_curfew_address_id
}
BOOKING_DELETE_OFFENDER_EXTERNAL_MOVEMENTS{
DELETE FROM offender_external_movements WHERE 1=1 AND to_address_id = :p_to_address_id
}
BOOKING_DELETE_OFFENDER_EXTERNAL_MOVEMENTS1{
DELETE FROM offender_external_movements WHERE 1=1 AND from_address_id = :p_from_address_id
}
BOOKING_DELETE_OFFENDER_MAIL_LOGS{
DELETE FROM offender_mail_logs WHERE 1=1 AND mail_address_id = :p_mail_address_id
}
BOOKING_DELETE_OFFENDER_MAIL_RESTRICTIONS{
DELETE FROM offender_mail_restrictions WHERE 1=1 AND restriction_address_id = :p_restriction_address_id
}
BOOKING_DELETE_PHONES{
DELETE FROM phones WHERE 1=1 AND owner_id = :p_owner_id AND owner_class = :p_owner_class
}
BOOKING_DELETE_ADDRESSES{
DELETE FROM addresses WHERE 1=1 AND owner_id = :p_owner_id AND owner_class = :p_owner_class
}
BOOKING_GET_OFFENDER_NON_ASSOCIATIONS{
SELECT ns_offender_id, offender_id FROM offender_non_associations WHERE 1=1 AND offender_id = :p_offender_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_NA_DETAILS{
DELETE FROM offender_na_details WHERE 1=1 AND offender_id = :p_offender_id AND ns_offender_id = :p_ns_offender_id
}
BOOKING_DELETE_OFFENDER_NON_ASSOCIATIONS{
DELETE FROM offender_non_associations WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_GET_OFFENDER_NON_ASSOCIATIONS1{
SELECT ns_offender_id, offender_id FROM offender_non_associations WHERE 1=1 AND ns_offender_id = :p_ns_offender_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_NON_ASSOCIATIONS1{
DELETE FROM offender_non_associations WHERE 1=1 AND ns_offender_id = :p_ns_offender_id
}
BOOKING_GET_OFFENDER_CONTACT_PERSONS{
SELECT offender_contact_person_id FROM offender_contact_persons WHERE 1=1 AND contact_root_offender_id = :p_contact_root_offender_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_PERSON_RESTRICTS{
DELETE FROM offender_person_restricts WHERE 1=1 AND offender_contact_person_id = :p_offender_contact_prson_id
}
BOOKING_DELETE_OFFENDER_CONTACT_PERSONS{
DELETE FROM offender_contact_persons WHERE 1=1 AND contact_root_offender_id = :p_contact_root_offender_id
}
BOOKING_GET_OFFENDER_TRUST_ACCOUNTS{
SELECT caseload_id, offender_id FROM offender_trust_accounts WHERE 1=1 AND offender_id = :p_offender_id FOR UPDATE NOWAIT
}
BOOKING_GET_OFFENDER_DEDUCTIONS2{
SELECT information_number, deduction_priority, offender_deduction_id, caseload_id, offender_id, deduction_type FROM offender_deductions WHERE 1=1 AND caseload_id = :p_caseload_id AND offender_id = :p_offender_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_PAYMENT_PROFILES{
DELETE FROM offender_payment_profiles WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_DELETE_OFFENDERS{
DELETE FROM offenders WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_DELETE_OFFENDER_FOREIGN_CURRENCIES{
DELETE FROM offender_foreign_currencies WHERE 1=1 AND caseload_id = :p_caseload_id AND offender_id = :p_offender_id
}
BOOKING_DELETE_OFFENDER_SUB_ACCOUNTS{
DELETE FROM offender_sub_accounts WHERE 1=1 AND caseload_id = :p_caseload_id AND offender_id = :p_offender_id
}
BOOKING_DELETE_OFFENDER_TRUST_ACCOUNTS{
DELETE FROM offender_trust_accounts WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_DELETE_ARRESTS_BOOKING{
DELETE FROM arrests WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_BED_ASSIGNMENT_HISTORIES_BOOKING{
DELETE FROM bed_assignment_histories WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_CASE_ASSOCIATED_PERSONS_BOOKING{
DELETE FROM case_associated_persons WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_CASE_NOTES_BOOKING{
DELETE FROM case_notes WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_CASE_PLANS_BOOKING{
SELECT offender_book_id, case_plan_id FROM case_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_ASSESSMENT_SUMMARIES_BOOKING{
DELETE FROM assessment_summaries WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id
}
BOOKING_GET_OFFENDER_CASE_CONDITIONS_BOOKING{
SELECT off_case_cond_id FROM offender_case_conditions WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_ACTION_PLANS_BOOKING{
DELETE FROM offender_action_plans WHERE 1=1 AND off_case_cond_id = :p_off_case_cond_id
}
BOOKING_DELETE_OFFENDER_CASE_CONDITIONS_BOOKING{
DELETE FROM offender_case_conditions WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id
}
BOOKING_GET_OFFENDER_CRIMINOGENIC_NEEDS_BOOKING{
SELECT off_crim_need_id FROM offender_criminogenic_needs WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_ACTION_PLANS_BOOKING_CRIM{
DELETE FROM offender_action_plans WHERE 1=1 AND off_crim_need_id = :p_off_crim_need_id
}
BOOKING_DELETE_OFFENDER_CRIMINOGENIC_NEEDS_BOOKING{
DELETE FROM offender_criminogenic_needs WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id
}
BOOKING_GET_PLAN_DETAILS_BOOKING{
SELECT offender_book_id, detail_seq, case_plan_id FROM plan_details WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_CASEWORK_STEPS_BOOKING{
DELETE FROM casework_steps WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id AND detail_seq = :p_detail_seq 
}
BOOKING_DELETE_PLAN_DETAILS_BOOKING{
DELETE FROM plan_details WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id
}
BOOKING_DELETE_CASE_PLANS1{
DELETE FROM case_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_COURT_EVENTS_BOOKING{
SELECT event_id FROM court_events WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_COURT_EVENT_CHARGES1_BOOKING{
DELETE FROM court_event_charges WHERE 1=1 AND event_id = :p_event_id
}
BOOKING_DELETE_COURT_EVENT_SENTENCES1{
DELETE FROM court_event_sentences WHERE 1=1 AND event_id = :p_event_id
}
BOOKING_DELETE_LINK_CASE_TXNS_BOOKING_EVENT_ID{
DELETE FROM link_case_txns WHERE 1=1 AND event_id = :p_event_id
}
BOOKING_DELETE_COURT_EVENTS_BOOKING{
DELETE FROM court_events WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_DOCUMENT_TEMPLATE_QUEUES{
DELETE FROM document_template_queues WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_UPDATE_GL_TRANSACTIONS1{
UPDATE gl_transactions SET offender_book_id = NULL WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_HDC_REQUEST_REFERRALS_BOOKING{
SELECT hdc_request_referral_id FROM hdc_request_referrals WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_HDC_BOARD_DECISIONS_BOOKING{
DELETE FROM hdc_board_decisions WHERE 1=1 AND hdc_request_referral_id = :p_hdc_request_referral_id
}
BOOKING_DELETE_HDC_GOVERNOR_DECISIONS_BOOKING{
DELETE FROM hdc_governor_decisions WHERE 1=1 AND hdc_request_referral_id = :p_hdc_request_referral_id
}
BOOKING_DELETE_HDC_PROB_STAFF_COMMENTS_BOOKING{
DELETE FROM hdc_prob_staff_comments WHERE 1=1 AND hdc_request_referral_id = :p_hdc_request_referral_id
}
BOOKING_DELETE_HDC_PROB_STAFF_RESPONSES_BOOKING{
DELETE FROM hdc_prob_staff_responses WHERE 1=1 AND hdc_request_referral_id = :p_hdc_request_referral_id
}
BOOKING_GET_HDC_REQUEST_REFERRALS1_BOOKING{
SELECT hdc_request_referral_id FROM hdc_request_referrals WHERE 1=1 AND parent_hdc_request_referral_id = :p_parent_hdc_reqst_rfrrl_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_HDC_REQUEST_REFERRALS_BOOKING{
DELETE FROM hdc_request_referrals WHERE 1=1 AND parent_hdc_request_referral_id = :p_parent_hdc_reqst_rfrrl_id
}
BOOKING_DELETE_HDC_REQUEST_REFERRALS1_BOOKING{
DELETE FROM hdc_request_referrals WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_INCIDENT_CASE_PARTIES_BOOKING{
DELETE FROM incident_case_parties WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_IWP_DOCUMENTS_BOOKING{
DELETE FROM iwp_documents WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_ACTIVITY_EVENTS_BOOKING{
DELETE FROM offender_activity_events WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_ALERTS_BOOKING{
DELETE FROM offender_alerts WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_ASSESSMENTS{
DELETE FROM offender_assessments WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_ASSETS_BOOKING{
DELETE FROM offender_assets WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_ASSOCIATED_PARTIES_BOOKING{
SELECT associated_party_id FROM offender_associated_parties WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_ASSOC_PARTY_NOTES_BOOKING{
DELETE FROM offender_assoc_party_notes WHERE 1=1 AND associated_party_id = :p_associated_party_id
}
BOOKING_GET_OFFENDER_ASSOC_PRTY_CONTACTS_BOOKING{
SELECT associated_party_id, party_seq FROM offender_assoc_prty_contacts WHERE 1=1 AND associated_party_id = :p_associated_party_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_ASSOC_P_CNT_NOTIFS_BOOKING{
DELETE FROM offender_assoc_p_cnt_notifs WHERE 1=1 AND associated_party_id = :p_associated_party_id AND party_seq = :p_party_seq
}
BOOKING_DELETE_OFFENDER_ASSOC_PRTY_CONTACTS_BOOKING{
DELETE FROM offender_assoc_prty_contacts WHERE 1=1 AND associated_party_id = :p_associated_party_id
}
BOOKING_DELETE_OFFENDER_ASSOC_P_NOTIFS_BOOKING{
DELETE FROM offender_assoc_p_notifs WHERE 1=1 AND associated_party_id = :p_associated_party_id
}
BOOKING_DELETE_OFFENDER_ASSOCIATED_PARTIES_BOOKING{
 DELETE FROM offender_associated_parties WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_AUTHORISED_VISITORS_BOOKING{
DELETE FROM offender_authorised_visitors WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_AUTHORISED_VISITORS1_BOOKING{
DELETE FROM offender_authorised_visitors WHERE 1=1 AND visitor_offender_book_id = :p_visitor_offender_book_id
}
BOOKING_DELETE_OFFENDER_BAIL_DETAILS_BOOKING{
DELETE FROM offender_bail_details WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_BOOKING_AGY_LOCS_BOOKING{
DELETE FROM offender_booking_agy_locs WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_BOOKING_DETAILS_BOOKING{
DELETE FROM offender_booking_details WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_BOOKING_EVENTS_BOOKING{
DELETE FROM offender_booking_events WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_CASES_BOOKING{
SELECT case_id FROM offender_cases WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_GET_COURT_EVENTS1_BOOKING{
SELECT event_id FROM court_events WHERE 1=1 AND case_id = :p_case_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_COURT_EVENTS1_BOOKING{
DELETE FROM court_events WHERE 1=1 AND case_id = :p_case_id
}
BOOKING_DELETE_LINK_CASE_TXNS_BOOKING{
DELETE FROM link_case_txns WHERE 1=1 AND case_id = :p_case_id
}
BOOKING_DELETE_LINK_CASE_TXNS1_BOOKING{
DELETE FROM link_case_txns WHERE 1=1 AND combined_case_id = :p_combined_case_id
}
BOOKING_GET_OFFENDER_ASSOCIATED_PARTIES1_BOOKING{
SELECT associated_party_id FROM offender_associated_parties WHERE 1=1 AND case_id = :p_case_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_ASSOCIATED_PARTIES1{
DELETE FROM offender_associated_parties WHERE 1=1 AND case_id = :p_case_id
}
BOOKING_GET_OFFENDER_CASES1{
SELECT case_id FROM offender_cases WHERE 1=1 AND combined_case_id = :p_combined_case_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_CASE_IDENTIFIERS{
DELETE FROM offender_case_identifiers WHERE 1=1 AND case_id = :p_case_id
}
BOOKING_DELETE_OFFENDER_CASE_STATUSES_BOOKING{
DELETE FROM offender_case_statuses WHERE 1=1 AND case_id = :p_case_id
}
BOOKING_GET_OFFENDER_CHARGES_BOOKING{
SELECT offender_charge_id FROM offender_charges WHERE 1=1 AND case_id = :p_case_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_COURT_EVENT_CHARGES_BOOKING{
DELETE FROM court_event_charges WHERE 1=1 AND offender_charge_id = :p_offender_charge_id
}
BOOKING_DELETE_LINK_CASE_TXNS2_BOOKING{
DELETE FROM link_case_txns WHERE 1=1 AND offender_charge_id = :p_offender_charge_id
}
BOOKING_DELETE_OFFENDER_SENTENCE_CHARGES1_BOOKING{
DELETE FROM offender_sentence_charges WHERE 1=1 AND offender_charge_id = :p_offender_charge_id
}
BOOKING_DELETE_OFFENDER_CHARGES_BOOKING{
DELETE FROM offender_charges WHERE 1=1 AND case_id = :p_case_id
}
BOOKING_DELETE_OFFENDER_LICENCE_SENTENCES2_BOOKING{
DELETE FROM offender_licence_sentences WHERE 1=1 AND case_id = :p_case_id
}
BOOKING_GET_OFFENDER_SENTENCES1{
SELECT offender_book_id, sentence_seq FROM offender_sentences WHERE 1=1 AND case_id = :p_case_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_SENTENCES1{
DELETE FROM offender_sentences WHERE 1=1 AND case_id = :p_case_id
}
BOOKING_GET_ORDERS_BOOKING{
SELECT order_id FROM orders WHERE 1=1 AND case_id = :p_case_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_ORDER_PURPOSES_BOOKING{
DELETE FROM order_purposes WHERE 1=1 AND order_id = :p_order_id
}
BOOKING_DELETE_ORDERS_BOOKING{
DELETE FROM orders WHERE 1=1 AND case_id = :p_case_id
}
BOOKING_DELETE_OFFENDER_CASES{
DELETE FROM offender_cases WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_CASE_NOTES_BOOKING{
SELECT case_note_id FROM offender_case_notes WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_CASE_NOTE_SENTS1{
DELETE FROM offender_case_note_sents WHERE 1=1 AND case_note_id = :p_case_note_id
}
BOOKING_DELETE_OFF_CASE_NOTE_RECIPIENTS_BOOKING{
DELETE FROM off_case_note_recipients WHERE 1=1 AND case_note_id = :p_case_note_id
}
BOOKING_DELETE_OFFENDER_CASE_NOTES_BOOKING{
DELETE FROM offender_case_notes WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_CHARGES1_BOOKING{
SELECT offender_charge_id FROM offender_charges WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_CHARGES1{
DELETE FROM offender_charges WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_CIP_DETAILS_BOOKING{
DELETE FROM offender_cip_details WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_CLASS_PROGRAMS_BOOKING{
DELETE FROM offender_class_programs WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_CLOTHES_BOOKING{
DELETE FROM offender_clothes WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_CLOTHING_BUNDLES_BOOKING{
DELETE FROM offender_clothing_bundles WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_CLOTHING_ISSUE_BOOKING{
SELECT offender_clothing_issue_id FROM offender_clothing_issue WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_CLOTHING_ITEMS_BOOKING{
DELETE FROM offender_clothing_items WHERE 1=1 AND offender_clothing_issue_id = :p_offender_clothing_issu_id
}
BOOKING_DELETE_OFFENDER_CLOTHING_ISSUE_BOOKING{
DELETE FROM offender_clothing_issue WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_CLOTHING_ITEMS1_BOOKING{
DELETE FROM offender_clothing_items WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_CODEFENDANTS_BOOKING{
DELETE FROM offender_codefendants WHERE 1=1 AND cod_offender_book_id = :p_cod_offender_book_id
}
BOOKING_DELETE_OFFENDER_CODEFENDANTS1_BOOKING{
DELETE FROM offender_codefendants WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_COMMENTS_BOOKING{
DELETE FROM offender_comments WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_COMMUNITY_CONDITIONS_BOOKING{
DELETE FROM offender_community_conditions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_COMM_SUP_HISTORIES_BOOKING{
DELETE FROM offender_comm_sup_histories WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_COMPACT_AGREEMENTS_BOOKING{
DELETE FROM offender_compact_agreements WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_CONTACT_PERSONS1{
SELECT offender_contact_person_id FROM offender_contact_persons WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_CONTACT_PERSONS1_BOOKING{
DELETE FROM offender_contact_persons WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_COURSE_ATTENDANCES2{
SELECT event_id FROM offender_course_attendances WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_COURSE_ATTENDANCES1{
DELETE FROM offender_course_attendances WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_CURFEWS_BOOKING{
SELECT offender_curfew_id FROM offender_curfews WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_GET_CURFEW_ADDRESSES1_BOOKING{
SELECT curfew_address_id FROM curfew_addresses WHERE 1=1 AND offender_curfew_id = :p_offender_curfew_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_CURFEW_ADDRESSES_BOOKING{
DELETE FROM curfew_addresses WHERE 1=1 AND offender_curfew_id = :p_offender_curfew_id
}
BOOKING_DELETE_HDC_PRISON_STAFF_COMMENTS_BOOKING{
DELETE FROM hdc_prison_staff_comments WHERE 1=1 AND offender_curfew_id = :p_offender_curfew_id
}
BOOKING_GET_HDC_REQUEST_REFERRALS_BOOKING{
SELECT hdc_request_referral_id FROM hdc_request_referrals WHERE 1=1 AND offender_curfew_id = :p_offender_curfew_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_HDC_REQUEST_REFERRALS2_BOOKING{
DELETE FROM hdc_request_referrals WHERE 1=1 AND offender_curfew_id = :p_offender_curfew_id
}
BOOKING_DELETE_OFFENDER_CURFEWS_BOOKING{
DELETE FROM offender_curfews WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_DRUG_ADMISSIONS_BOOKING{
SELECT offender_drug_admission_id FROM offender_drug_admissions WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_DRUG_ADM_DRUGS_BOOKING{
DELETE FROM offender_drug_adm_drugs WHERE 1=1 AND offender_drug_admission_id = :p_offender_drug_dmission_id
}
BOOKING_DELETE_OFFENDER_DRUG_ADMISSIONS_BOOKING{
DELETE FROM offender_drug_admissions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_DRUG_SAMPLES_BOOKING{
SELECT reference_id, offender_drug_sample_id FROM offender_drug_samples WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_DRUG_RESULTS_BOOKING{
DELETE FROM offender_drug_results WHERE 1=1 AND offender_drug_sample_id = :p_offender_drug_sample_id
}
BOOKING_DELETE_OFFENDER_DRUG_SAMPLES_BOOKING{
DELETE FROM offender_drug_samples WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_EDUCATIONS_BOOKING{
DELETE FROM offender_educations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_EMPLOYMENTS_BOOKING{
DELETE FROM offender_employments WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_ESCAPES_BOOKING{
DELETE FROM offender_escapes WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_EXPENSES_BOOKING{
DELETE FROM offender_expenses WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_EXTERNAL_MOVEMENTS_BOOKING{
DELETE FROM offender_external_movements WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_FILE_LOCATIONS_BOOKING{
DELETE FROM offender_file_locations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_GANG_AFFILIATIONS_BOOKING{
DELETE FROM offender_gang_affiliations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_GANG_EVIDENCES_BOOKING{
DELETE FROM offender_gang_evidences WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_GANG_INVESTS_BOOKING{
DELETE FROM offender_gang_invests WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_GRIEVANCE_TXNS_BOOKING{
DELETE FROM offender_grievance_txns WHERE 1=1 AND grievance_id = :p_grievance_id
}
BOOKING_DELETE_OFFENDER_GRIEV_STAFFS_BOOKING{
DELETE FROM offender_griev_staffs WHERE 1=1 AND grievance_id = :p_grievance_id 
}
BOOKING_DELETE_OFFENDER_GRIEVANCES_BOOKING{
DELETE FROM offender_grievances WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_HOSPITAL_VISITS_BOOKING{
DELETE FROM offender_hospital_visits WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_HWD_BOOKING{
SELECT hwd_id FROM offender_hwd WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_HWD_CHARGES_BOOKING{
DELETE FROM offender_hwd_charges WHERE 1=1 AND hwd_id = :p_hwd_id
}
BOOKING_DELETE_OFFENDER_HWD_HTY_BOOKING{
DELETE FROM offender_hwd_hty WHERE 1=1 AND hwd_id = :p_hwd_id
}
BOOKING_DELETE_OFFENDER_HWD_BOOKING{
DELETE FROM offender_hwd WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_IDENTIFYING_MARKS_BOOKING{
DELETE FROM offender_identifying_marks WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_IEP_LEVELS_BOOKING{
DELETE FROM offender_iep_levels WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_IMAGES_BOOKING{
DELETE FROM offender_images WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_IMPRISON_STATUSES_BOOKING{
DELETE FROM offender_imprison_statuses WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_INCOME_BOOKING{
DELETE FROM offender_income WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_IND_SCHEDULES_BOOKING{
SELECT event_id FROM offender_ind_schedules WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_IND_SCH_SENTS1_BOOKING{
DELETE FROM offender_ind_sch_sents WHERE 1=1 AND event_id = :p_event_id
}
BOOKING_DELETE_OFFENDER_IND_SCHEDULES_BOOKING{
DELETE FROM offender_ind_schedules WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_INTERNAL_MOVEMENTS_BOOKING{
SELECT offender_book_id, movement_seq FROM offender_internal_movements WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_INT_MOV_PERSONS_BOOKING{
DELETE FROM offender_int_mov_persons WHERE 1=1 AND offender_book_id = :p_offender_book_id AND movement_seq = :p_movement_seq
}
BOOKING_DELETE_OFFENDER_INTERNAL_MOVEMENTS_BOOKING{
DELETE FROM offender_internal_movements WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_INTERNAL_STATUSES_BOOKING{
DELETE FROM offender_internal_statuses WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_INTERVENTION_TIERS_BOOKING{
DELETE FROM offender_intervention_tiers WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_LANGUAGES_BOOKING{
DELETE FROM offender_languages WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_LIABILITIES_BOOKING{
DELETE FROM offender_liabilities WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_MAIL_LOGS1{
DELETE FROM offender_mail_logs WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_MAIL_RESTRICTIONS1_BOOKING{
DELETE FROM offender_mail_restrictions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_MILITARY_DISC_ACTIONS_BOOKING{
DELETE FROM offender_military_disc_actions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_MILITARY_RECORDS_BOOKING{
DELETE FROM offender_military_records WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_MILITARY_WAR_ZONES_BOOKING{
DELETE FROM offender_military_war_zones WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_OASYS_PLANS{
SELECT offender_book_id, plan_seq FROM offender_oasys_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_OASYS_CONCERNS_BOOKING{
DELETE FROM offender_oasys_concerns WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq
}
BOOKING_DELETE_OFFENDER_OASYS_RISK_TO_OTHERS_BOOKING{
DELETE FROM offender_oasys_risk_to_others WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq
}
BOOKING_GET_OFFENDER_OASYS_SECTIONS_BOOKING{
SELECT offender_book_id, section_code, plan_seq FROM offender_oasys_sections WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq FOR UPDATE NOWAIT
}
BOOKING_GET_OFFENDER_OASYS_OBJECTIVES_BOOKING{
SELECT objective_id FROM offender_oasys_objectives WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq AND section_code = :p_section_code FOR UPDATE NOWAIT
}
BOOKING_GET_OFFENDER_OASYS_ACTIONS_BOOKING{
SELECT action_seq, objective_id FROM offender_oasys_actions WHERE 1=1 AND objective_id = :p_objective_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_OASYS_SUPERVISIONS_BOOKING{
DELETE FROM offender_oasys_supervisions WHERE 1=1 AND objective_id = :p_objective_id AND action_seq = :p_action_seq
}
BOOKING_DELETE_OFFENDER_OASYS_ACTIONS{
DELETE FROM offender_oasys_actions WHERE 1=1 AND objective_id = :p_objective_id
}
BOOKING_DELETE_OFFENDER_OASYS_OBJECTIVES_BOOKING{
DELETE FROM offender_oasys_objectives WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq AND section_code = :p_section_code
}
BOOKING_DELETE_OFFENDER_OASYS_SECTIONS{
DELETE FROM offender_oasys_sections WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq
}
BOOKING_DELETE_OFFENDER_OASYS_PLANS{
DELETE FROM offender_oasys_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_OIC_SANCTIONS2_BOOKING{
SELECT offender_book_id, sanction_seq FROM offender_oic_sanctions WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_OIC_SANCTIONS2{
DELETE FROM offender_oic_sanctions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_PAROLE_HEARINGS_BOOKING{
SELECT offender_book_id, parole_hearing_seq FROM offender_parole_hearings WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_GET_OFFENDER_PAROLE_DECISIONS_BOOKING{
SELECT schedule_id, parole_decision_seq FROM offender_parole_decisions WHERE 1=1 AND offender_book_id = :p_offender_book_id AND parole_hearing_seq = :p_parole_hearing_seq FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_PAROLE_STIPULATIONS{
DELETE FROM offender_parole_stipulations WHERE 1=1 AND schedule_id = :p_schedule_id AND parole_decision_seq = :p_parole_decision_seq
}
BOOKING_DELETE_OFFENDER_PAROLE_CONDITIONS_BOOKING{
DELETE FROM offender_parole_conditions WHERE 1=1 AND schedule_id = :p_schedule_id AND parole_decision_seq = :p_parole_decision_seq
}
BOOKING_DELETE_OFFENDER_PAROLE_DECISIONS1{
DELETE FROM offender_parole_decisions WHERE 1=1 AND offender_book_id = :p_offender_book_id AND parole_hearing_seq = :p_parole_hearing_seq
}
BOOKING_DELETE_OFFENDER_PAROLE_HEARINGS{
DELETE FROM offender_parole_hearings WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_PAROLE_PLANS_BOOKING{
DELETE FROM offender_parole_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_PLANED_ACTIVITIES_BOOKING{
DELETE FROM offender_planed_activities WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_PPTY_CONTAINERS_BOOKING{
DELETE FROM offender_ppty_containers WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_PPTY_ITEMS_BOOKING{
DELETE FROM offender_ppty_items WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_PPTY_ITEM_EVENTS_BOOKING{
SELECT event_seq, offender_book_id FROM offender_ppty_item_events WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_PPTY_ITEM_TXNS_BOOKING{
DELETE FROM offender_ppty_item_txns WHERE 1=1 AND offender_book_id = :p_offender_book_id AND event_seq = :p_event_seq
}
BOOKING_DELETE_OFFENDER_PPTY_ITEM_EVENTS_BOOKING{
DELETE FROM offender_ppty_item_events WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_PRG_OBLIGATIONS_BOOKING{
SELECT offender_prg_obligation_id FROM offender_prg_obligations WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_GET_OFFENDER_COURSE_ATTENDANCES3_BOOKING{
SELECT event_id FROM offender_course_attendances WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_COURSE_SKILLS{
DELETE FROM offender_course_skills WHERE 1=1 AND event_id = :p_event_id
}
BOOKING_DELETE_OFFENDER_COURSE_ATTENDANCES2_BOOKING{
DELETE FROM offender_course_attendances WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id
}
BOOKING_GET_OFFENDER_IND_SCHEDULES1_BOOKING{
SELECT event_id FROM offender_ind_schedules WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_OBLIGATION_ADJS_BOOKING{
DELETE FROM offender_obligation_adjs WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id
}
BOOKING_DELETE_OFFENDER_PRG_OBLIGATION_HTY_BOOKING{
DELETE FROM offender_prg_obligation_hty WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id
}
BOOKING_GET_OFFENDER_PROGRAM_PROFILES2_BOOKING{
SELECT off_prgref_id FROM offender_program_profiles WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_PROGRAM_PROFILES2_BOOKING{
DELETE FROM offender_program_profiles WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id
}
BOOKING_DELETE_OFFENDER_PRG_OBLIGATIONS_BOOKING{
DELETE FROM offender_prg_obligations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_PROCEEDINGS_BOOKING{
SELECT offender_proceeding_id FROM offender_proceedings WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_GET_COURT_EVENTS2{
SELECT event_id FROM court_events WHERE 1=1 AND offender_proceeding_id = :p_offender_proceeding_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_COURT_EVENTS2_BOOKING{
DELETE FROM court_events WHERE 1=1 AND offender_proceeding_id = :p_offender_proceeding_id
}
BOOKING_DELETE_OFFENDER_PROCEEDING_SENTS1_BOOKING{
DELETE FROM offender_proceeding_sents WHERE 1=1 AND offender_proceeding_id = :p_offender_proceeding_id
}
BOOKING_DELETE_OFFENDER_PROCEEDINGS_BOOKING{
DELETE FROM offender_proceedings WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_PROFILES{
DELETE FROM offender_profiles WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_PROPERTY_BUNDLES_BOOKING{
DELETE FROM offender_property_bundles WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_PROGRAM_PROFILES4_BOOKING{
SELECT off_prgref_id FROM offender_program_profiles WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_PROGRAM_PROFILES3_BOOKING{
DELETE FROM offender_program_profiles WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_PROPOSED_MVMNTS_BOOKING{
SELECT offender_book_id, movement_seq FROM offender_proposed_mvmnts WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_MOVEMENT_DETAILS_BOOKING{
DELETE FROM offender_movement_details WHERE 1=1 AND offender_book_id = :p_offender_book_id AND movement_seq = :p_movement_seq
}
BOOKING_DELETE_OFFENDER_PROPOSED_MVMNTS_BOOKING{
DELETE FROM offender_proposed_mvmnts WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_PTR_BOOKING{
SELECT ptr_id FROM offender_ptr WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_EXT_OWNERSHIP_TRANSFER_BOOKING{
DELETE FROM ext_ownership_transfer WHERE 1=1 AND ptr_id = :p_ptr_id
}
BOOKING_DELETE_OFFENDER_BOOKING_AGY_LOCS1_BOOKING{
DELETE FROM offender_booking_agy_locs WHERE 1=1 AND ptr_id = :p_ptr_id
}
BOOKING_DELETE_OFFENDER_PTR_DETAILS_BOOKING{
DELETE FROM offender_ptr_details WHERE 1=1 AND ptr_id = :p_ptr_id
}
BOOKING_DELETE_OFFENDER_PTR_BOOKING{
DELETE FROM offender_ptr WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_REFERRALS_BOOKING{
DELETE FROM offender_referrals WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_RELEASE_DETAILS_BOOKING{
DELETE FROM offender_release_details WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_RESIDENCES_BOOKING{
DELETE FROM offender_residences WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_RESTRICTIONS{
DELETE FROM offender_restrictions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_RISK_PREDICTORS_BOOKING{
DELETE FROM offender_risk_predictors WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_SENT_CALCULATIONS_BOOKING{
DELETE FROM offender_sent_calculations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_STG_AFFILIATIONS_BOOKING{
SELECT stg_seq, offender_book_id FROM offender_stg_affiliations WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_STG_DETAILS_BOOKING{
DELETE FROM offender_stg_details WHERE 1=1 AND offender_book_id = :p_offender_book_id AND stg_seq = :p_stg_seq
}
BOOKING_DELETE_OFFENDER_STG_AFFILIATIONS_BOOKING{
DELETE FROM offender_stg_affiliations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_STG_ASSOCIATIONS_BOOKING{
DELETE FROM offender_stg_associations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_SUBSTANCE_ABUSES_BOOKING{
DELETE FROM offender_substance_abuses WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_SUPERVISING_COURTS_BOOKING{
DELETE FROM offender_supervising_courts WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_SUPERVISION_LEVELS_BOOKING{
SELECT offender_book_id, supervision_level_type FROM offender_supervision_levels WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_GET_OFFENDER_SUPERVISION_PLANS_BOOKING{
SELECT offender_book_id, plan_seq FROM offender_supervision_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id AND supervision_level_type = :p_supervision_level_type FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_PLANED_ACTIVITIES1_BOOKING{
DELETE FROM offender_planed_activities WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq
}
BOOKING_DELETE_OFFENDER_SUPERVISION_PLANS_BOOKING{
DELETE FROM offender_supervision_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id AND supervision_level_type = :p_supervision_level_type
}
BOOKING_DELETE_OFFENDER_SUPERVISION_RATES_BOOKING{
DELETE FROM offender_supervision_rates WHERE 1=1 AND offender_book_id = :p_offender_book_id AND supervision_type = :p_supervision_type
}
BOOKING_DELETE_OFFENDER_SUPERVISION_LEVELS_BOOKING{
DELETE FROM offender_supervision_levels WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_TEAM_ASSIGNMENTS_BOOKING{
DELETE FROM offender_team_assignments WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_TEAM_ASSIGN_HTY_BOOKING{
DELETE FROM offender_team_assign_hty WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_TEMPORARY_ABSENCES_BOOKING{
DELETE FROM offender_temporary_absences WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_TMP_REL_SCHEDULES_BOOKING{
DELETE FROM offender_tmp_rel_schedules WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_OFFENDER_VISITS_BOOKING{
SELECT offender_visit_id FROM offender_visits WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_OFFENDER_VISIT_VISITORS_BOOKING{
DELETE FROM offender_visit_visitors WHERE 1=1 AND offender_visit_id = :p_offender_visit_id
}
BOOKING_DELETE_OFFENDER_VISITS_BOOKING{
DELETE FROM offender_visits WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OFFENDER_VITAL_SIGNS1{
DELETE FROM offender_vital_signs WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_ORDERS1{
SELECT order_id FROM orders WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_ORDERS1{
DELETE FROM orders WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_RELEASE_PLANS_BOOKING{
SELECT release_plan_id FROM release_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_RP_OTHER_OCCUPANTS_BOOKING{
DELETE FROM rp_other_occupants WHERE 1=1 AND release_plan_id = :p_release_plan_id
}
BOOKING_DELETE_RELEASE_PLANS_BOOKING{
DELETE FROM release_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_GET_STAFF_WORKS_BOOKING{
SELECT staff_work_id FROM staff_works WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_STAFF_WORK_ASSIGNMENTS_BOOKING{
DELETE FROM staff_work_assignments WHERE 1=1 AND staff_work_id = :p_staff_work_id
}
BOOKING_DELETE_STAFF_WORKS_BOOKING{
DELETE FROM staff_works WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_SUPERVISION_TRANSACTIONS_BOOKING{
DELETE FROM supervision_transactions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_TASK_ASSIGNMENT_HTY_BOOKING{
DELETE FROM task_assignment_hty WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_ASSIGNMENTS_BOOKING{
DELETE FROM assignments WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
BOOKING_DELETE_OIC_HEARING_RESULTS_BOOKING{
DELETE FROM oic_hearing_results WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND charge_seq = :p_charge_seq
}
BOOKING_DELETE_OFFENDER_COURSE_ATTENDANCES3_BOOKING{
DELETE FROM offender_course_attendances WHERE 1=1 AND off_prgref_id = :p_off_prgref_id
}
BOOKING_DELETE_OFFENDER_CONTACT_PERSONS2{
DELETE FROM offender_contact_persons WHERE 1=1 AND contact_root_offender_id = :p_contact_root_offender_id
}
BOOKING_DELETE_OFFENDER_BOOKINGS_BOOKING{
DELETE FROM offender_bookings WHERE 1=1 AND offender_id = :p_offender_id
}
BOOKING_OFFENDER_GRIEVANCES_BOOKING{
SELECT grievance_id FROM offender_grievances WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
BOOKING_DELETE_DOCUMENT_TEMPLATE_QUEUES_BOOKING{
DELETE FROM document_template_queues WHERE 1=1 AND offender_book_id = :p_offender_book_id
}   
BOOKING_DELETE_OFFENDER_CUSTODY_STATUS{
DELETE FROM offender_custody_status WHERE  offender_book_id = :p_offender_book_id
}  
     