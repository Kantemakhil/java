ALTER TABLE off_fee_account_profile DROP CONSTRAINT off_fee_account_profile_fk1;
ALTER TABLE off_fee_account_profile DROP CONSTRAINT off_fee_account_profile_fk2;
ALTER TABLE off_fee_account_overrides DROP CONSTRAINT off_fee_account_overrides_fk1;
ALTER TABLE off_fee_account_profile_hty DROP CONSTRAINT off_fee_account_prof_hty_fk1;
ALTER TABLE off_fee_ded_beneficiaries DROP CONSTRAINT off_fee_ded_beneficiaries_fk1;
ALTER TABLE off_fee_ded_beneficiaries DROP CONSTRAINT off_fee_ded_beneficiaries_fk2;
ALTER TABLE off_fee_ded_beneficiaries DROP CONSTRAINT off_fee_ded_beneficiaries_fk3;
ALTER TABLE off_fee_ded_receipts DROP CONSTRAINT off_fee_ded_receipts_fk1;
ALTER TABLE off_fee_ded_receipts DROP CONSTRAINT off_fee_ded_receipts_fk2;
ALTER TABLE off_intake_review_queue DROP CONSTRAINT off_intake_review_queue_fk1;
ALTER TABLE off_supervision_details DROP CONSTRAINT off_supervision_details_fk1;
ALTER TABLE off_supervision_sts_hty DROP CONSTRAINT off_supervision_sts_hty_fk1;
ALTER TABLE team_agency_loc DROP CONSTRAINT team_agency_loc_fk1;
ALTER TABLE team_agency_loc DROP CONSTRAINT team_agency_loc_fk2;
ALTER TABLE team_staff_members DROP CONSTRAINT team_staff_members_fk1;
ALTER TABLE team_staff_members DROP CONSTRAINT team_staff_members_fk2;

ALTER TABLE off_case_note_recipients DROP CONSTRAINT off_cn_recpt_team_fk;


---
ALTER TABLE off_fee_account_profile ADD CONSTRAINT off_fee_account_profile_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings (offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_fee_account_profile ADD CONSTRAINT off_fee_account_profile_fk2 FOREIGN KEY (caseload_id, fee_code) REFERENCES caseload_deduction_profiles (caseload_id, deduction_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_fee_account_overrides ADD CONSTRAINT off_fee_account_overrides_fk1 FOREIGN KEY (offender_fee_id) REFERENCES off_fee_account_profile (offender_fee_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_fee_account_profile_hty ADD CONSTRAINT off_fee_account_prof_hty_fk1 FOREIGN KEY (offender_fee_id) REFERENCES off_fee_account_profile (offender_fee_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_fee_ded_beneficiaries ADD CONSTRAINT off_fee_ded_beneficiaries_fk1 FOREIGN KEY (offender_fee_id) REFERENCES off_fee_account_profile (offender_fee_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_fee_ded_beneficiaries ADD CONSTRAINT off_fee_ded_beneficiaries_fk2 FOREIGN KEY (corporate_id) REFERENCES corporates (corporate_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_fee_ded_beneficiaries ADD CONSTRAINT off_fee_ded_beneficiaries_fk3 FOREIGN KEY (person_id) REFERENCES persons (person_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_fee_ded_receipts ADD CONSTRAINT off_fee_ded_receipts_fk1 FOREIGN KEY (offender_fee_id) REFERENCES off_fee_account_profile (offender_fee_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_fee_ded_receipts ADD CONSTRAINT off_fee_ded_receipts_fk2 FOREIGN KEY (receipt_txn_type) REFERENCES transaction_types (txn_type) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_intake_review_queue ADD CONSTRAINT off_intake_review_queue_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings (offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_supervision_details ADD CONSTRAINT off_supervision_details_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings (offender_book_id)  ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE off_supervision_sts_hty ADD CONSTRAINT off_supervision_sts_hty_fk1 FOREIGN KEY (offender_book_id) REFERENCES offender_bookings (offender_book_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE team_agency_loc ADD CONSTRAINT team_agency_loc_fk1 FOREIGN KEY (team_id) REFERENCES automation_teams (team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE team_agency_loc ADD CONSTRAINT team_agency_loc_fk2 FOREIGN KEY (agy_loc_id) REFERENCES agency_locations (agy_loc_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE team_staff_members ADD CONSTRAINT team_staff_members_fk1 FOREIGN KEY (team_id) REFERENCES automation_teams (team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE team_staff_members ADD CONSTRAINT team_staff_members_fk2 FOREIGN KEY (staff_id) REFERENCES staff_members (staff_id)  ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE off_case_note_recipients ADD CONSTRAINT off_cn_recpt_team_fk FOREIGN KEY (team_id) REFERENCES automation_teams(team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;


