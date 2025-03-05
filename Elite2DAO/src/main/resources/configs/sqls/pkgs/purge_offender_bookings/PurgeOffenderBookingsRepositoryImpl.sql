PURGE_GET_AGENCY_INCIDENT_PARTIES{
SELECT agency_incident_id, party_seq, oic_incident_id FROM agency_incident_parties WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_GET_AGY_INC_INVESTIGATIONS{
SELECT agy_inc_investigation_id FROM agy_inc_investigations WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_BOOK_ID{
SELECT offender_book_id FROM offender_bookings WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_GET_AGENCY_INCIDENT_CHARGES{
SELECT agency_incident_id, oic_charge_id, charge_seq FROM agency_incident_charges WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq FOR UPDATE NOWAIT
}
PURGE_GET_OIC_HEARING_RESULTS{
SELECT result_seq, agency_incident_id, charge_seq, oic_hearing_id FROM oic_hearing_results WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id FOR UPDATE nowait
}
PURGE_GET_OFFENDER_OIC_APPEAL_INCIDENTS{
SELECT result_seq, oic_hearing_id, oic_appreal_id FROM offender_oic_appeal_incidents WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_OIC_APPEAL_PENALTIES{
DELETE FROM offender_oic_appeal_penalties WHERE 1=1 AND oic_appreal_id = :p_oic_appreal_id AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq
}
PURGE_DELETE_OFFENDER_OIC_APPEAL_INCIDENTS{
DELETE FROM offender_oic_appeal_incidents WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq
}
PURGE_GET_OFFENDER_OIC_SANCTIONS{
SELECT offender_book_id, sanction_seq FROM offender_oic_sanctions WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_OIC_SANCTIONS1{
SELECT offender_book_id, sanction_seq FROM offender_oic_sanctions WHERE 1=1 AND consecutive_offender_book_id = :p_consecutiv_offndr_book_id AND consecutive_sanction_seq = :p_consecutive_sanction_seq FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_OIC_SANC_REVIEWS{
DELETE FROM offender_oic_sanc_reviews WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sanction_seq = :p_sanction_seq
}
PURGE_DELETE_OFFENDER_OIC_SANCTIONS{
DELETE FROM offender_oic_sanctions WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id AND result_seq = :p_result_seq
}
PURGE_DELETE_OIC_HEARING_RESULTS{
DELETE FROM oic_hearing_results WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id
}
PURGE_GET_OIC_HEARING_RESULTS1{
SELECT result_seq, agency_incident_id, charge_seq, oic_hearing_id FROM oic_hearing_results WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND charge_seq = :p_charge_seq FOR UPDATE NOWAIT
}
PURGE_DELETE_OIC_HEARING_RESULTS1{
DELETE FROM oic_hearing_results WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND charge_seq = :p_charge_seq
}
PURGE_DELETE_AGENCY_INCIDENT_CHARGES{
DELETE FROM agency_incident_charges WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq
}
PURGE_DELETE_AGY_INC_INV_STATEMENTS{
DELETE FROM agy_inc_inv_statements WHERE 1=1 AND agy_inc_investigation_id = :p_agy_inc_investigation_id
}
PURGE_DELETE_AGY_INC_INVESTIGATIONS{
DELETE FROM agy_inc_investigations WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq
}
PURGE_GET_OFFENDER_GRIEVANCES{
SELECT grievance_id FROM offender_grievances WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_GRIEVANCE_TXNS{
DELETE FROM offender_grievance_txns WHERE 1=1 AND grievance_id = :p_grievance_id
}
PURGE_DELETE_OFFENDER_GRIEV_STAFFS{
DELETE FROM offender_griev_staffs WHERE 1=1 AND grievance_id = :p_grievance_id
}
PURGE_DELETE_OFFENDER_GRIEVANCES1{
DELETE FROM offender_grievances WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq
}
PURGE_DELETE_OFFENDER_INCIDENT_DETAILS{
DELETE FROM offender_incident_details WHERE 1=1 AND agency_incident_id = :p_agency_incident_id AND party_seq = :p_party_seq
}
PURGE_GET_OIC_HEARINGS{
SELECT oic_hearing_id FROM oic_hearings WHERE 1=1 AND oic_incident_id = :p_oic_incident_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OIC_HEARING_COMMENTS{
DELETE FROM oic_hearing_comments WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id
}
PURGE_DELETE_OIC_HEARING_NOTICES{
DELETE FROM oic_hearing_notices WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id
}
PURGE_DELETE_OIC_HEARING_REVIEWS{
DELETE FROM oic_hearing_reviews WHERE 1=1 AND oic_hearing_id = :p_oic_hearing_id
}
PURGE_DELETE_AGENCY_INCIDENT_PARTIES{
DELETE FROM agency_incident_parties WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_ARRESTS{
DELETE FROM arrests WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_ASSIGNMENTS{
DELETE FROM assignments WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_BED_ASSIGNMENT_HISTORIES{
DELETE FROM bed_assignment_histories WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_CASE_ASSOCIATED_PERSONS_PURGE{
DELETE FROM case_associated_persons WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_CASE_NOTES{
DELETE FROM case_notes WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_CASE_PLANS_PURGE{
SELECT offender_book_id, case_plan_id FROM case_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_ASSESSMENT_SUMMARIES{
DELETE FROM assessment_summaries WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id
}
PURGE_DELETE_OFFENDER_ACTION_PLANS{
DELETE FROM offender_action_plans WHERE 1=1 AND off_case_cond_id = :p_off_case_cond_id
}
PURGE_DELETE_OFFENDER_CASE_CONDITIONS{
DELETE FROM offender_case_conditions WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id
}
PURGE_GET_OFFENDER_CRIMINOGENIC_NEEDS{
SELECT off_crim_need_id FROM offender_criminogenic_needs WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_ACTION_PLANS{
DELETE FROM offender_action_plans WHERE 1=1 AND off_crim_need_id = :p_off_crim_need_id
}
PURGE_DELETE_OFFENDER_CRIMINOGENIC_NEEDS{
DELETE FROM offender_criminogenic_needs WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id 
}
PURGE_GET_PLAN_DETAILS{
SELECT offender_book_id, detail_seq, case_plan_id FROM plan_details WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id FOR UPDATE NOWAIT
}
PURGE_DELETE_CASEWORK_STEPS{
DELETE FROM casework_steps WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id AND detail_seq = :p_detail_seq
}
PURGE_DELETE_PLAN_DETAILS{
DELETE FROM plan_details WHERE 1=1 AND offender_book_id = :p_offender_book_id AND case_plan_id = :p_case_plan_id
}
PURGE_DELETE_CASE_PLANS{
DELETE FROM case_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_COURT_EVENTS_PURGE{
SELECT event_id FROM court_events WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_COURT_EVENT_CHARGES{
DELETE FROM court_event_charges WHERE 1=1 AND event_id = :p_event_id
}
PURGE_DELETE_COURT_EVENT_SENTENCES1{
DELETE FROM court_event_sentences WHERE 1=1 AND event_id = :p_event_id
}
PURGE_DELETE_LINK_CASE_TXNS{
DELETE FROM link_case_txns WHERE 1=1 AND event_id = :p_event_id
}
PURGE_DELETE_COURT_EVENTS{
DELETE FROM court_events WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_DOCUMENT_TEMPLATE_QUEUES{
DELETE FROM document_template_queues WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_UPDATE_GL_TRANSACTIONS_PURGE{
UPDATE gl_transactions SET offender_book_id = NULL WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_HDC_REQUEST_REFERRALS_PURGE{
SELECT hdc_request_referral_id FROM hdc_request_referrals WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_HDC_BOARD_DECISIONS{
DELETE FROM hdc_board_decisions WHERE 1=1 AND hdc_request_referral_id = :p_hdc_request_referral_id
}
PURGE_DELETE_HDC_GOVERNOR_DECISIONS{
DELETE FROM hdc_governor_decisions WHERE 1=1 AND hdc_request_referral_id = :p_hdc_request_referral_id
}
PURGE_DELETE_HDC_PROB_STAFF_COMMENTS{
DELETE FROM hdc_prob_staff_comments WHERE 1=1 AND hdc_request_referral_id = p_hdc_request_referral_id
}
PURGE_DELETE_HDC_PROB_STAFF_RESPONSES{
DELETE FROM hdc_prob_staff_responses WHERE 1=1 AND hdc_request_referral_id = :p_hdc_request_referral_id
}
PURGE_GET_HDC_REQUEST_REFERRALS1{
SELECT hdc_request_referral_id FROM hdc_request_referrals WHERE 1=1 AND parent_hdc_request_referral_id = :p_parent_hdc_reqst_rfrrl_id FOR UPDATE NOWAIT
}
PURGE_DELETE_HDC_REQUEST_REFERRALS{
DELETE FROM hdc_request_referrals WHERE 1=1 AND parent_hdc_request_referral_id = :p_parent_hdc_reqst_rfrrl_id
}
PURGE_DELETE_HDC_REQUEST_REFERRALS1{
DELETE FROM hdc_request_referrals WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_INCIDENT_CASE_PARTIES{
DELETE FROM incident_case_parties WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_IWP_DOCUMENTS{
DELETE FROM iwp_documents WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_ACTIVITY_EVENTS{
DELETE FROM offender_activity_events WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_ALERTS_PURGE{
DELETE FROM offender_alerts WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_ASSETS{
DELETE FROM offender_assets WHERE 1=1 AND offender_book_id = :p_offender_book_id 
}
PURGE_GET_OFFENDER_ASSOCIATED_PARTIES{
SELECT associated_party_id FROM offender_associated_parties WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_ASSOC_PARTY_NOTES{
DELETE FROM offender_assoc_party_notes WHERE 1=1 AND associated_party_id = :p_associated_party_id
}
PURGE_GET_OFFENDER_ASSOC_PRTY_CONTACTS{
SELECT associated_party_id, party_seq FROM offender_assoc_prty_contacts WHERE 1=1 AND associated_party_id = :p_associated_party_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_ASSOC_P_CNT_NOTIFS{
DELETE FROM offender_assoc_p_cnt_notifs WHERE 1=1 AND associated_party_id = :p_associated_party_id AND party_seq = :p_party_seq
}
PURGE_DELETE_OFFENDER_ASSOC_PRTY_CONTACTS{
DELETE FROM offender_assoc_prty_contacts WHERE 1=1 AND associated_party_id = :p_associated_party_id
}
PURGE_DELETE_OFFENDER_ASSOC_P_NOTIFS{
DELETE FROM offender_assoc_p_notifs WHERE 1=1 AND associated_party_id = :p_associated_party_id
}
PURGE_DELETE_OFFENDER_ASSOCIATED_PARTIES{
DELETE FROM offender_associated_parties WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_AUTHORISED_VISITORS{
DELETE FROM offender_authorised_visitors WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_AUTHORISED_VISITORS1{
DELETE FROM offender_authorised_visitors WHERE 1=1 AND visitor_offender_book_id = :p_visitor_offender_book_id
}
PURGE_DELETE_OFFENDER_BAIL_DETAILS{
DELETE FROM offender_bail_details WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_BOOKING_AGY_LOCS{
DELETE FROM offender_booking_agy_locs WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_BOOKING_DETAILS{
DELETE FROM offender_booking_details WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_BOOKING_EVENTS{
DELETE FROM offender_booking_events WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_CASES{
SELECT case_id FROM offender_cases WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_GET_COURT_EVENTS{
SELECT event_id FROM court_events WHERE 1=1 AND case_id = :p_case_id FOR UPDATE NOWAIT
}
PURGE_DELETE_COURT_EVENTS1{
DELETE FROM court_events WHERE 1=1 AND case_id = :p_case_id 
}
PURGE_DELETE_LINK_CASE_TXNS{
DELETE FROM link_case_txns WHERE 1=1 AND case_id = :p_case_id
}
PURGE_DELETE_LINK_CASE_TXNS1{
DELETE FROM link_case_txns WHERE 1=1 AND combined_case_id = :p_combined_case_id
}
PURGE_GET_OFFENDER_ASSOCIATED_PARTIES1{
SELECT associated_party_id FROM offender_associated_parties WHERE 1=1 AND case_id = :p_case_id FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_CASE_NOTES{
SELECT case_note_id FROM offender_case_notes WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_CASE_NOTE_SENTS1{
DELETE FROM offender_case_note_sents WHERE 1=1 AND case_note_id = :p_case_note_id
}
PURGE_DELETE_OFF_CASE_NOTE_RECIPIENTS{
DELETE FROM off_case_note_recipients WHERE 1=1 AND case_note_id = :p_case_note_id
}
PURGE_DELETE_OFFENDER_CASE_NOTES{
DELETE FROM offender_case_notes WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_CHARGES_PURGE{
SELECT offender_charge_id FROM offender_charges WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_COURT_EVENT_CHARGES1{
DELETE FROM court_event_charges WHERE 1=1 AND offender_charge_id = :p_offender_charge_id
}
PURGE_DELETE_LINK_CASE_TXNS2{
DELETE FROM link_case_txns WHERE 1=1 AND offender_charge_id = :p_offender_charge_id
}
PURGE_DELETE_OFFENDER_SENTENCE_CHARGES{
DELETE FROM offender_sentence_charges WHERE 1=1 AND offender_charge_id = :p_offender_charge_id
}
PURGE_DELETE_OFFENDER_CHARGES_PURGE{
DELETE FROM offender_charges WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_CIP_DETAILS{
DELETE FROM offender_cip_details WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_CLASS_PROGRAMS{
DELETE FROM offender_class_programs WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_CLOTHES{
DELETE FROM offender_clothes WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_CLOTHING_BUNDLES{
DELETE FROM offender_clothing_bundles WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_CLOTHING_ISSUE_PURGE{
SELECT offender_clothing_issue_id FROM offender_clothing_issue WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_CLOTHING_ITEMS{
DELETE FROM offender_clothing_items WHERE 1=1 AND offender_clothing_issue_id = :p_offender_clothing_issu_id
}
PURGE_DELETE_OFFENDER_CLOTHING_ISSUE{
DELETE FROM offender_clothing_issue WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_CLOTHING_ITEMS1{
DELETE FROM offender_clothing_items WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_CODEFENDANTS{
DELETE FROM offender_codefendants WHERE 1=1 AND cod_offender_book_id = :p_cod_offender_book_id
}
PURGE_DELETE_OFFENDER_CODEFENDANTS1{
DELETE FROM offender_codefendants WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_COMMENTS{
DELETE FROM offender_comments WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_COMMUNITY_CONDITIONS{
DELETE FROM offender_community_conditions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_COMM_SUP_HISTORIES{
DELETE FROM offender_comm_sup_histories WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_COMPACT_AGREEMENTS{
DELETE FROM offender_compact_agreements WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_CONTACT_PERSONS_PURGE{
SELECT offender_contact_person_id FROM offender_contact_persons WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_PERSON_RESTRICTS{
DELETE FROM offender_person_restricts WHERE 1=1 AND offender_contact_person_id = :p_offender_contact_prson_id
}
PURGE_DELETE_OFFENDER_CONTACT_PERSONS1{
DELETE FROM offender_contact_persons WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_COURSE_ATTENDANCES_PURGE{
SELECT event_id FROM offender_course_attendances WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_COURSE_SKILLS{
DELETE FROM offender_course_skills WHERE 1=1 AND event_id = :p_event_id
}
PURGE_DELETE_OFFENDER_COURSE_ATTENDANCES_PURGE{
DELETE FROM offender_course_attendances WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_CURFEWS{
SELECT offender_curfew_id FROM offender_curfews WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE nowait
}
PURGE_GET_CURFEW_ADDRESSES{
SELECT curfew_address_id FROM curfew_addresses WHERE 1=1 AND offender_curfew_id = :p_offender_curfew_id FOR UPDATE NOWAIT
}
PURGE_DELETE_CURFEW_ADDRESS_OCCUPANTS{
DELETE FROM curfew_address_occupants WHERE 1=1 AND curfew_address_id = :p_curfew_address_id
}
PURGE_DELETE_CURFEW_ADDRESSES{
DELETE FROM curfew_addresses WHERE 1=1 AND offender_curfew_id = :p_offender_curfew_id
}
PURGE_DELETE_HDC_PRISON_STAFF_COMMENTS{
DELETE FROM hdc_prison_staff_comments WHERE 1=1 AND offender_curfew_id = :p_offender_curfew_id
}
PURGE_GET_HDC_REQUEST_REFERRALS2{
SELECT hdc_request_referral_id FROM hdc_request_referrals WHERE 1=1 AND offender_curfew_id = :p_offender_curfew_id FOR UPDATE NOWAIT
}
PURGE_DELETE_HDC_REQUEST_REFERRALS2{
DELETE FROM hdc_request_referrals WHERE 1=1 AND offender_curfew_id = :p_offender_curfew_id
}
PURGE_DELETE_OFFENDER_CURFEWS{
DELETE FROM offender_curfews WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_DRUG_ADMISSIONS{
SELECT offender_drug_admission_id FROM offender_drug_admissions WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_DRUG_ADM_DRUGS{
DELETE FROM offender_drug_adm_drugs WHERE 1=1 AND offender_drug_admission_id = :p_offender_drug_dmission_id
}
PURGE_DELETE_OFFENDER_DRUG_ADMISSIONS{
DELETE FROM offender_drug_admissions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_DRUG_SAMPLES{
SELECT reference_id, offender_drug_sample_id FROM offender_drug_samples WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_DRUG_RESULTS{
DELETE FROM offender_drug_results WHERE 1=1 AND offender_drug_sample_id = :p_offender_drug_sample_id
}
PURGE_DELETE_OFFENDER_DRUG_SAMPLES{
DELETE FROM offender_drug_samples WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_EDUCATIONS{
DELETE FROM offender_educations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_EMPLOYMENTS{
DELETE FROM offender_employments WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_ESCAPES{
DELETE FROM offender_escapes WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_EXPENSES{
DELETE FROM offender_expenses WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_EXTERNAL_MOVEMENTS{
DELETE FROM offender_external_movements WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_FILE_LOCATIONS{
DELETE FROM offender_file_locations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_GANG_AFFILIATIONS{
DELETE FROM offender_gang_affiliations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_GANG_EVIDENCES{
DELETE FROM offender_gang_evidences WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_GANG_INVESTS{
DELETE FROM offender_gang_invests WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_GRIEVANCES1{
SELECT grievance_id FROM offender_grievances WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_GRIEVANCES{
DELETE FROM offender_grievances WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_HOSPITAL_VISITS{
DELETE FROM offender_hospital_visits WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_HWD{
SELECT hwd_id FROM offender_hwd WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_HWD_CHARGES{
DELETE FROM offender_hwd_charges WHERE 1=1 AND hwd_id = :p_hwd_id
}
PURGE_DELETE_OFFENDER_HWD_HTY{
DELETE FROM offender_hwd_hty WHERE 1=1 AND hwd_id = :p_hwd_id
}
PURGE_DELETE_OFFENDER_HWD{
DELETE FROM offender_hwd WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_IDENTIFYING_MARKS{
DELETE FROM offender_identifying_marks WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_IEP_LEVELS{
DELETE FROM offender_iep_levels WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_IMAGES{
DELETE FROM offender_images WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_IMPRISON_STATUSES{
DELETE FROM offender_imprison_statuses WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_INCOME{
DELETE FROM offender_income WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_IND_SCHEDULES_PURGE_SCREEN{
SELECT event_id FROM offender_ind_schedules WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_IND_SCH_SENTS{
DELETE FROM offender_ind_sch_sents WHERE 1=1 AND event_id = :p_event_id
}
PURGE_DELETE_OFFENDER_IND_SCH_WAIT_LISTS{
DELETE FROM offender_ind_sch_wait_lists WHERE 1=1 AND event_id = :p_event_id
}
PURGE_DELETE_OFFENDER_IND_SCHEDULES_PURGE_SCREEN{
DELETE FROM offender_ind_schedules WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_INTERNAL_MOVEMENTS{
SELECT offender_book_id, movement_seq FROM offender_internal_movements WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_INT_MOV_PERSONS{
DELETE FROM offender_int_mov_persons WHERE 1=1 AND offender_book_id = :p_offender_book_id AND movement_seq = :p_movement_seq
}
PURGE_DELETE_OFFENDER_INTERNAL_MOVEMENTS{
DELETE FROM offender_internal_movements WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_INTERNAL_STATUSES{
DELETE FROM offender_internal_statuses WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_INTERVENTION_TIERS{
DELETE FROM offender_intervention_tiers WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_LANGUAGES{
DELETE FROM offender_languages WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_LIABILITIES{
DELETE FROM offender_liabilities WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_MAIL_RESTRICTIONS{
DELETE FROM offender_mail_restrictions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_MILITARY_DISC_ACTIONS{
DELETE FROM offender_military_disc_actions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_MILITARY_RECORDS{
DELETE FROM offender_military_records WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_MILITARY_WAR_ZONES{
DELETE FROM offender_military_war_zones WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_OASYS_CONCERNS{
DELETE FROM offender_oasys_concerns WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq
}
PURGE_DELETE_OFFENDER_OASYS_RISK_TO_OTHERS{
DELETE FROM offender_oasys_risk_to_others WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq
}
PURGE_GET_OFFENDER_OASYS_SECTIONS{
SELECT offender_book_id, section_code, plan_seq FROM offender_oasys_sections WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_OASYS_OBJECTIVES{
SELECT objective_id FROM offender_oasys_objectives WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq AND section_code = :p_section_code FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_OASYS_ACTIONS{
SELECT action_seq, objective_id FROM offender_oasys_actions WHERE 1=1 AND objective_id = :p_objective_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_OASYS_ACTIONS{
DELETE FROM offender_oasys_actions WHERE 1=1 AND objective_id = :p_objective_id
}
PURGE_DELETE_OFFENDER_OASYS_OBJECTIVES{
DELETE FROM offender_oasys_objectives WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq AND section_code = :p_section_code
}
PURGE_GET_OFFENDER_OIC_SANCTIONS2{
SELECT offender_book_id, sanction_seq FROM offender_oic_sanctions WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_OIC_SANCTIONS1{
DELETE FROM offender_oic_sanctions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_PAROLE_HEARINGS{
SELECT offender_book_id, parole_hearing_seq FROM offender_parole_hearings WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_PAROLE_DECISIONS{
SELECT schedule_id, parole_decision_seq FROM offender_parole_decisions WHERE 1=1 AND offender_book_id = :p_offender_book_id AND parole_hearing_seq = :p_parole_hearing_seq FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_PAROLE_CONDITIONS{
DELETE FROM offender_parole_conditions WHERE 1=1 AND schedule_id = :p_schedule_id AND parole_decision_seq = :p_parole_decision_seq
}
PURGE_DELETE_OFFENDER_PAROLE_STIPULATIONS{
DELETE FROM offender_parole_stipulations WHERE 1=1 AND schedule_id = :p_schedule_id AND parole_decision_seq = :p_parole_decision_seq
}
PURGE_DELETE_OFFENDER_PAROLE_DECISIONS{
DELETE FROM offender_parole_decisions WHERE 1=1 AND offender_book_id = :p_offender_book_id AND parole_hearing_seq = :p_parole_hearing_seq
}
PURGE_DELETE_OFFENDER_PAROLE_PLANS{
DELETE FROM offender_parole_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_PLANED_ACTIVITIES{
DELETE FROM offender_planed_activities WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_PPTY_CONTAINERS{
DELETE FROM offender_ppty_containers WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_PPTY_ITEMS{
DELETE FROM offender_ppty_items WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_PPTY_ITEM_EVENTS{
SELECT event_seq, offender_book_id FROM offender_ppty_item_events WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE nowait 
}
PURGE_DELETE_OFFENDER_PPTY_ITEM_TXNS{
DELETE FROM offender_ppty_item_txns WHERE 1=1 AND offender_book_id = :p_offender_book_id AND event_seq = :p_event_seq
}
PURGE_DELETE_OFFENDER_PPTY_ITEM_EVENTS{
DELETE FROM offender_ppty_item_events WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_PRG_OBLIGATIONS{
SELECT offender_prg_obligation_id FROM offender_prg_obligations WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_COURSE_ATTENDANCES1{
SELECT event_id FROM offender_course_attendances WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_COURSE_ATTENDANCES1{
DELETE FROM offender_course_attendances WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id
}
PURGE_GET_OFFENDER_IND_SCHEDULES1{
SELECT event_id FROM offender_ind_schedules WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_IND_SCHEDULES1{
DELETE FROM offender_ind_schedules WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id
}
PURGE_DELETE_OFFENDER_OBLIGATION_ADJS{
DELETE FROM offender_obligation_adjs WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id
}
PURGE_DELETE_OFFENDER_PRG_OBLIGATION_HTY{
DELETE FROM offender_prg_obligation_hty WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id
}
PURGE_GET_OFFENDER_PROGRAM_PROFILES{
SELECT off_prgref_id FROM offender_program_profiles WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_COURSE_APPT_GRPS{
SELECT offender_course_appt_grp_id FROM offender_course_appt_grps WHERE 1=1 AND off_prgref_id = :p_off_prgref_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_COURSE_APPT_RULES{
DELETE FROM offender_course_appt_rules WHERE 1=1 AND offender_course_appt_grp_id = :p_offender_cours_ppt_grp_id
}
PURGE_DELETE_OFFENDER_COURSE_APPT_GRPS{
DELETE FROM offender_course_appt_grps WHERE 1=1 AND off_prgref_id = :p_off_prgref_id
}
PURGE_GET_OFFENDER_COURSE_ATTENDANCES2{
SELECT event_id FROM offender_course_attendances WHERE 1=1 AND off_prgref_id = :p_off_prgref_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_COURSE_SKILLS{
DELETE FROM offender_course_skills WHERE 1=1 AND event_id = :p_event_id
}
PURGE_DELETE_OFFENDER_COURSE_ATTENDANCES2{
DELETE FROM offender_course_attendances WHERE 1=1 AND off_prgref_id = :p_off_prgref_id
}
PURGE_GET_OFFENDER_PROGRAM_PROFILES1{
SELECT off_prgref_id FROM offender_program_profiles WHERE 1=1 AND program_off_prgref_id = :p_program_off_prgref_id FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_PROGRAM_PROFILES2{
SELECT off_prgref_id FROM offender_program_profiles WHERE 1=1 AND program_off_prgref_id = :p_program_off_prgref_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_PROGRAM_PROFILES{
DELETE FROM offender_program_profiles WHERE 1=1 AND program_off_prgref_id = :p_program_off_prgref_id
}
PURGE_DELETE_OFFENDER_PROGRAM_PROFILES1{
DELETE FROM offender_program_profiles WHERE 1=1 AND offender_prg_obligation_id = :p_offender_prg_obligtion_id
}
PURGE_DELETE_OFFENDER_PRG_OBLIGATIONS{
DELETE FROM offender_prg_obligations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_PROCEEDINGS{
SELECT offender_proceeding_id FROM offender_proceedings WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_GET_COURT_EVENTS1{
SELECT event_id FROM court_events WHERE 1=1 AND offender_proceeding_id = :p_offender_proceeding_id FOR UPDATE NOWAIT
}
PURGE_DELETE_COURT_EVENTS2{
DELETE FROM court_events WHERE 1=1 AND offender_proceeding_id = :p_offender_proceeding_id
}
PURGE_DELETE_OFFENDER_PROCEEDING_SENTS{
DELETE FROM offender_proceeding_sents WHERE 1=1 AND offender_proceeding_id = :p_offender_proceeding_id
}
PURGE_DELETE_OFFENDER_PROCEEDINGS{
DELETE FROM offender_proceedings WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_PROGRAM_PROFILES3_PURGE{
SELECT off_prgref_id FROM offender_program_profiles WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_PROGRAM_PROFILES2_PURGE{
DELETE FROM offender_program_profiles WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_PROPERTY_BUNDLES{
DELETE FROM offender_property_bundles WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_PROPOSED_MVMNTS{
SELECT offender_book_id, movement_seq FROM offender_proposed_mvmnts WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_MOVEMENT_DETAILS{
DELETE FROM offender_movement_details WHERE 1=1 AND offender_book_id = :p_offender_book_id AND movement_seq = :p_movement_seq
}
PURGE_DELETE_OFFENDER_PROPOSED_MVMNTS{
DELETE FROM offender_proposed_mvmnts WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_PTR{
SELECT ptr_id FROM offender_ptr WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_EXT_OWNERSHIP_TRANSFER{
DELETE FROM ext_ownership_transfer WHERE 1=1 AND ptr_id = :p_ptr_id
}
PURGE_DELETE_OFFENDER_BOOKING_AGY_LOCS1{
DELETE FROM offender_booking_agy_locs WHERE 1=1 AND ptr_id = :p_ptr_id
}
PURGE_DELETE_OFFENDER_PTR_DETAILS{
DELETE FROM offender_ptr_details WHERE 1=1 AND ptr_id = :p_ptr_id
}
PURGE_DELETE_OFFENDER_PTR{
DELETE FROM offender_ptr WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_REFERRALS{
DELETE FROM offender_referrals WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_RELEASE_DETAILS{
DELETE FROM offender_release_details WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_RESIDENCES{
DELETE FROM offender_residences WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_RISK_PREDICTORS{
DELETE FROM offender_risk_predictors WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_SENT_CALCULATIONS{
DELETE FROM offender_sent_calculations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_STG_AFFILIATIONS{
SELECT stg_seq, offender_book_id FROM offender_stg_affiliations WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_STG_DETAILS{
DELETE FROM offender_stg_details WHERE 1=1 AND offender_book_id = :p_offender_book_id AND stg_seq = :p_stg_seq
}
PURGE_DELETE_OFFENDER_STG_AFFILIATIONS{
DELETE FROM offender_stg_affiliations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_STG_ASSOCIATIONS{
DELETE FROM offender_stg_associations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_SUBSTANCE_ABUSES{
DELETE FROM offender_substance_abuses WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_SUPERVISING_COURTS{
DELETE FROM offender_supervising_courts WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_SUPERVISION_LEVELS{
SELECT offender_book_id, supervision_level_type FROM offender_supervision_levels WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_SUPERVISION_PLANS{
SELECT offender_book_id, plan_seq FROM offender_supervision_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id AND supervision_level_type = :p_supervision_level_type FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_PLANED_ACTIVITIES1{
DELETE FROM offender_planed_activities WHERE 1=1 AND offender_book_id = :p_offender_book_id AND plan_seq = :p_plan_seq
}
PURGE_DELETE_OFFENDER_SUPERVISION_PLANS{
DELETE FROM offender_supervision_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id AND supervision_level_type = :p_supervision_level_type
}
PURGE_DELETE_OFFENDER_SUPERVISION_RATES{
DELETE FROM offender_supervision_rates WHERE 1=1 AND offender_book_id = :p_offender_book_id AND supervision_type = :p_supervision_type
}
PURGE_DELETE_OFFENDER_SUPERVISION_LEVELS{
DELETE FROM offender_supervision_levels WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_TEAM_ASSIGNMENTS{
DELETE FROM offender_team_assignments WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_TEAM_ASSIGN_HTY{
DELETE FROM offender_team_assign_hty WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_TEMPORARY_ABSENCES{
DELETE FROM offender_temporary_absences WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_TMP_REL_SCHEDULES{
DELETE FROM offender_tmp_rel_schedules WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_OFFENDER_VISITS{
SELECT offender_visit_id FROM offender_visits WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_VISIT_VISITORS{
DELETE FROM offender_visit_visitors WHERE 1=1 AND offender_visit_id = :p_offender_visit_id
}
PURGE_DELETE_OFFENDER_VISITS{
DELETE FROM offender_visits WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_VITAL_SIGNS_PURGE{
DELETE FROM offender_vital_signs WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_ORDERS_PURGE{
SELECT order_id FROM orders WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_GET_OFFENDER_SENTENCES{
SELECT offender_book_id, sentence_seq FROM offender_sentences WHERE 1=1 AND order_id = :p_order_id FOR UPDATE NOWAIT
}
PURGE_DELETE_COURT_EVENT_SENTENCES{
DELETE FROM court_event_sentences WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_CASE_NOTE_SENTS{
DELETE FROM offender_case_note_sents WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_COMMUNITY_CONDITIONS1{
DELETE FROM offender_community_conditions WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_IND_SCH_SENTS1{
DELETE FROM offender_ind_sch_sents WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_LICENCE_SENTENCES{
DELETE FROM offender_licence_sentences WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_LICENCE_SENTENCES1{
DELETE FROM offender_licence_sentences WHERE 1=1 AND offender_book_id = :p_offender_book_id AND licence_sentence_seq = :p_licence_sentence_seq
}
PURGE_DELETE_OFFENDER_PROCEEDING_SENTS1{
DELETE FROM offender_proceeding_sents WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_SENTENCE_ADJUSTS{
DELETE FROM offender_sentence_adjusts WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_SENTENCE_CHARGES1{
DELETE FROM offender_sentence_charges WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_SENTENCE_HTY{
DELETE FROM offender_sentence_hty WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_SENTENCE_STATUSES{
DELETE FROM offender_sentence_statuses WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_SENTENCE_TERMS{
DELETE FROM offender_sentence_terms WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_SENTENCE_UA_EVENTS{
DELETE FROM offender_sentence_ua_events WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_UNPAID_WORK_ADJ{
DELETE FROM offender_unpaid_work_adj WHERE 1=1 AND offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
PURGE_DELETE_OFFENDER_SENTENCES{
DELETE FROM offender_sentences WHERE 1=1 AND order_id = :p_order_id
}
PURGE_DELETE_ORDER_PURPOSES{
DELETE FROM order_purposes WHERE 1=1 AND order_id = :p_order_id
}
PURGE_DELETE_ORDERS_PURGE{
DELETE FROM orders WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_RELEASE_PLANS{
SELECT release_plan_id FROM release_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_RP_OTHER_OCCUPANTS{
DELETE FROM rp_other_occupants WHERE 1=1 AND release_plan_id = :p_release_plan_id
}
PURGE_DELETE_RELEASE_PLANS{
DELETE FROM release_plans WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_STAFF_WORKS{
SELECT staff_work_id FROM staff_works WHERE 1=1 AND offender_book_id = :p_offender_book_id FOR UPDATE NOWAIT
}
PURGE_DELETE_STAFF_WORK_ASSIGNMENTS{
DELETE FROM staff_work_assignments WHERE 1=1 AND staff_work_id = :p_staff_work_id
}
PURGE_DELETE_STAFF_WORKS{
DELETE FROM staff_works WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_SUPERVISION_TRANSACTIONS{
DELETE FROM supervision_transactions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_TASK_ASSIGNMENT_HTY{
DELETE FROM task_assignment_hty WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_GET_ADDRESSES{
SELECT address_id FROM addresses WHERE 1=1 AND owner_id = :p_owner_id AND owner_class = :p_owner_class FOR UPDATE NOWAIT
}
PURGE_DELETE_ADDRESS_USAGES{
DELETE FROM address_usages WHERE 1=1 AND address_id = :p_address_id
}
PURGE_GET_COURSE_ACTIVITIES{
SELECT crs_acty_id FROM course_activities WHERE 1=1 AND services_address_id = :p_services_address_id FOR UPDATE NOWAIT
}
PURGE_DELETE_COURSE_ACTIVITY_AREAS{
DELETE FROM course_activity_areas WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
PURGE_DELETE_COURSE_ACTIVITY_PARTIES{
DELETE FROM course_activity_parties WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
PURGE_DELETE_COURSE_ACTIVITY_PROFILES{
DELETE FROM course_activity_profiles WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
PURGE_DELETE_COURSE_ACTIVITY_REVIEWS{
DELETE FROM course_activity_reviews WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
PURGE_GET_COURSE_SCHEDULES{
SELECT crs_sch_id FROM course_schedules WHERE 1=1 AND crs_acty_id = :p_crs_acty_id FOR UPDATE NOWAIT
}
PURGE_GET_COURSE_SCHEDULES1{
SELECT crs_sch_id FROM course_schedules WHERE 1=1 AND catch_up_crs_sch_id = :p_catch_up_crs_sch_id FOR UPDATE NOWAIT
}
PURGE_DELETE_COURSE_SCHEDULE_STAFFS{
DELETE FROM course_schedule_staffs WHERE 1=1 AND crs_sch_id = :p_crs_sch_id
}
PURGE_GET_VIDEO_REVIEW_SUMMARIES{
SELECT video_review_summary_id FROM video_review_summaries WHERE 1=1 AND crs_sch_id = :p_crs_sch_id FOR UPDATE nowait
}
PURGE_GET_VIDEO_REVIEW_SECTIONS{
SELECT video_review_summary_id, section_code FROM video_review_sections WHERE 1=1 AND video_review_summary_id = :p_video_review_summary_id FOR UPDATE NOWAIT
}
PURGE_DELETE_VIDEO_REVIEW_SUBSECTIONS{
DELETE FROM video_review_subsections WHERE 1=1 AND section_code = :p_section_code AND video_review_summary_id = :p_video_review_summary_id
}
PURGE_DELETE_VIDEO_REVIEW_SECTIONS{
DELETE FROM video_review_sections WHERE 1=1 AND video_review_summary_id = :p_video_review_summary_id
}
PURGE_DELETE_VIDEO_REVIEW_SUMMARIES{
DELETE FROM video_review_summaries WHERE 1=1 AND crs_sch_id = :p_crs_sch_id
}
PURGE_DELETE_COURSE_SCHEDULES{
DELETE FROM course_schedules WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
PURGE_DELETE_COURSE_SCHEDULE_RULES{
DELETE FROM course_schedule_rules WHERE 1=1 AND crs_acty_id = :p_crs_acty_id
}
PURGE_GET_OFFENDER_COURSE_ATTENDANCES3{
SELECT event_id FROM offender_course_attendances WHERE 1=1 AND crs_acty_id = :p_crs_acty_id FOR UPDATE NOWAIT
}
PURGE_DELETE_OFFENDER_COURSE_ATTENDANCES3{
DELETE FROM offender_course_attendances WHERE 1=1 AND off_prgref_id = :p_off_prgref_id
}
PURGE_GET_OFFENDER_PROGRAM_PROFILES4{
SELECT off_prgref_id FROM offender_program_profiles WHERE 1=1 AND crs_acty_id = :p_crs_acty_id FOR UPDATE NOWAIT
}
PURGE_DELETE_COURSE_ACTIVITIES{
DELETE FROM course_activities WHERE 1=1 AND services_address_id = :p_services_address_id
}
PURGE_GET_CURFEW_ADDRESSES1{
SELECT curfew_address_id FROM curfew_addresses WHERE 1=1 AND address_id = :p_address_id FOR UPDATE NOWAIT
}
PURGE_DELETE_CURFEW_ADDRESSES{
DELETE FROM curfew_addresses WHERE 1=1 AND address_id = :p_address_id
}
PURGE_DELETE_OFFENDER_EXTERNAL_MOVEMENTS1{
DELETE FROM offender_external_movements WHERE 1=1 AND to_address_id = :p_to_address_id
}
PURGE_DELETE_OFFENDER_EXTERNAL_MOVEMENTS2{
DELETE FROM offender_external_movements WHERE 1=1 AND from_address_id = :p_from_address_id
}
PURGE_DELETE_OFFENDER_MAIL_LOGS{
DELETE FROM offender_mail_logs WHERE 1=1 AND mail_address_id = :p_mail_address_id
}
PURGE_DELETE_OFFENDER_MAIL_RESTRICTIONS1{
DELETE FROM offender_mail_restrictions WHERE 1=1 AND restriction_address_id = :p_restriction_address_id
}
PURGE_DELETE_PHONES_PURGE{
DELETE FROM phones WHERE 1=1 AND owner_id = :p_owner_id AND owner_class = :p_owner_class
}
PURGE_DELETE_ADDRESSES{
DELETE FROM addresses WHERE 1=1 AND owner_id = :p_owner_id AND owner_class = :p_owner_class
}
PURGE_DELETE_INTERNET_ADDRESSES{
DELETE FROM internet_addresses WHERE 1=1 AND owner_id = :p_owner_id AND owner_class = :p_owner_class
}
PURGE_GET_IMAGES{
SELECT image_id FROM images WHERE 1=1 AND image_object_id = :p_image_object_id AND image_object_type = :p_image_object_type FOR UPDATE NOWAIT
}
PURGE_DELETE_IMAGE_ORIGINALS{
DELETE FROM image_originals WHERE 1=1 AND image_id = :p_image_id
}
PURGE_DELETE_IMAGE_PROPERTIES{
DELETE FROM image_properties WHERE 1=1 AND image_id = :p_image_id
}
PURGE_DELETE_IMAGES{
DELETE FROM images WHERE 1=1 AND image_object_id = :p_image_object_id AND image_object_type = :p_image_object_type
}
PURGE_DELETE_OFFENDER_CASE_ASSOCIATIONS_PURGE{
DELETE FROM offender_case_associations WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_CASE_ASSOCIATIONS1_PURGE{
DELETE FROM offender_case_associations WHERE 1=1 AND associated_off_book_id = :p_associated_off_book_id
}
PURGE_GET_WORK_FLOWS{
SELECT work_flow_id FROM work_flows WHERE 1=1 AND object_id = :p_object_id FOR UPDATE NOWAIT
}
PURGE_DELETE_WORK_FLOW_LOGS{
DELETE FROM work_flow_logs WHERE 1=1 AND work_flow_id = :p_work_flow_id
}
PURGE_DELETE_WORK_FLOWS{
DELETE FROM work_flows WHERE 1=1 AND object_id = :p_object_id
}
PURGE_DELETE_OFFENDER_PROFILE_DETAILS{
DELETE FROM offender_profile_details WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_BILLING_PROFILES{
DELETE FROM offender_billing_profiles WHERE 1=1 AND offender_booking_id = :p_offender_booking_id
}
PURGE_GET_QM_PROCESSES_INS{
SELECT process_ins_id FROM qm_processes_ins WHERE 1=1 AND detail = :p_detail FOR UPDATE NOWAIT
}
PURGE_DELETE_QM_ACTIVITIES_INS_HIST{
DELETE FROM qm_activities_ins_hist WHERE 1=1 AND process_ins_id = :p_process_ins_id
}
PURGE_DELETE_QM_OBJECT_INS{
DELETE FROM qm_object_ins WHERE 1=1 AND process_ins_id = :p_process_ins_id
}
PURGE_DELETE_QM_PROCESSES_INS{
DELETE FROM qm_processes_ins WHERE 1=1 AND detail = :p_detail
}
PURGE_GET_QM_PROCESSES_INS_HIST{
SELECT process_ins_id FROM qm_processes_ins_hist WHERE 1=1 AND detail = :p_detail FOR UPDATE NOWAIT
}
PURGE_DELETE_QM_ACTIVITIES_INS_HIST{
DELETE FROM qm_activities_ins_hist WHERE 1=1 AND process_ins_id = :p_process_ins_id 
}
PURGE_DELETE_QM_OBJECT_INS_HIST{
DELETE FROM qm_object_ins_hist WHERE 1=1 AND process_ins_id = :p_process_ins_id
}
PURGE_DELETE_OFFENDER_OASYS_SUPERVISIONS{
DELETE FROM offender_oasys_supervisions WHERE 1=1 AND objective_id = :p_objective_id AND action_seq = :p_action_seq
}
PURGE_DELETE_OFFENDER_BOOKINGS_PURGE{
DELETE FROM offender_bookings WHERE 1=1 AND offender_book_id = :p_offender_id
}
PURGE_DELETE_OFFENDER_RESTRICTIONS_DELETE{
DELETE FROM offender_restrictions WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_OFFENDER_CASE_CONDITIONS  {
select off_case_cond_id from offender_case_conditions where	1 = 1 and offender_book_id = :p_offender_book_id	and case_plan_id = :p_case_plan_id for update nowait
}
PURGE_DELETE_OFFENDER_OFF_INCENTIVES_EARN_PRIVS{
DELETE FROM off_incentives_earn_privs WHERE 1=1 AND offender_book_id = :p_offender_book_id
}

PURGE_OFFENDER_BOOKING_DELETE_OFFENDER_PROFILES{
DELETE FROM offender_profiles WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_BOOKING_DELETE_OFFENDER_ASSESSMENTS{
DELETE FROM offender_assessments WHERE 1=1 AND offender_book_id = :p_offender_book_id
}
PURGE_DELETE_OFFENDER_CUSTODY_STATUS{
DELETE FROM offender_custody_status WHERE  offender_book_id = :p_offender_book_id
}