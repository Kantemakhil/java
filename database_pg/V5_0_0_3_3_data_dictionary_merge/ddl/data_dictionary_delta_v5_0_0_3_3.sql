--V5_0_0_0_1 --
-----ported_tables_V4_3_2_8_2_5_20220526.sql 
comment on column oms_owner.CASELOAD_DEDUCTION_PROFILES.FREQUENCY_TYPE is 'Frequence type [domain = FEE_ACT_FREQ (ex:recurrent/one time)]';
comment on column oms_owner.CASELOAD_DEDUCTION_PROFILES.FREQUENCY_CODE is 'Frequency code  when frequency type is reccuring [domain = RECUR_FREQ (ex: monthly /daily)]';
comment on column oms_owner.CASELOAD_DEDUCTION_PROFILES.DAY_OF_MONTH is 'Day of the month';
comment on column oms_owner.CASELOAD_DEDUCTION_PROFILES.NON_BILLABLE_STATUS is 'Indicates if the status of the offender fee account will be suspended or completed';
comment on column oms_owner.CASELOAD_DEDUCTION_PROFILES.BACK_BILL is 'Indicates if back billing is permitted';

comment on table oms_owner.OFF_FEE_ACCOUNT_PROFILE is 'Offender fee account details';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.OFFENDER_FEE_ID is 'Unique fee account record id [existing sequence=DEDUCTION_ID ]';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.OFFENDER_BOOK_ID is 'The system generated identifier for an offender booking';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.CASELOAD_ID is 'Caseload id as per Maintain Fee Accounts Profile (OCMFAPRO)';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.FEE_CODE is 'Deduction type as per Maintain Fee Accounts Profile (OCMFAPRO)';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.ODP is 'Obligation priority';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.AMOUNT is 'Offender fee account amount'; 
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.DAY_OF_MONTH is 'Billing day';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.START_DATE is 'Fee Account start date ';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.EFFECTIVE_DATE is 'Effective date of the account';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.EXPIRY_DATE is 'Expiry date of an fee account';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.FEE_ACT_STATUS is 'Fee account status';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.STATUS_EFFECTIVE_DATE is 'Fee account status effective date';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.INFO_NUMBER is 'Record docket / info number';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.SERVICE_DATE is 'Service date ';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.COMMENT_TEXT is 'The comment text of the Fee Account';

comment on table oms_owner.OFF_FEE_ACCOUNT_OVERRIDES is 'Offender fee account override details';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OFF_FEE_OVERRIDE_ID is 'Unique record identifier [sequence=OFF_FEE_OVERRIDE_ID]';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OFFENDER_FEE_ID is 'Offender fee account id';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OVERRIDE_TYPE is 'Override type [domain=CF_FOV_TYP]';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OVERRIDE_START_DATE is 'Start date of the fee override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OVERRIDE_END_DATE is 'End date of the fee override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OVERRIDE_AMOUNT is 'Amount of the fee override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.COMMENT_TEXT  is 'Comment text of the override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.PRIORITY_INDICATOR is 'Priority of the fee override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.ADDED_BY_STAFF_ID is 'The staff who created the override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.ADDED_DATE is 'The date and time when the override was created';

comment on table oms_owner.OFF_FEE_ACCOUNT_PROFILE_HTY is 'History of offender fee account profile for items that affect billing or reporting';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE_HTY.OFFENDER_FEE_ID is 'Unique fee account record id';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE_HTY.RECORD_DATETIME is 'Date and time when the history record was created';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.AMOUNT is 'Offender fee account amount'; 
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.DAY_OF_MONTH is 'Billing day';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.START_DATE is 'Fee Account start date ';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.EFFECTIVE_DATE is 'Effective date of the account';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.EXPIRY_DATE is 'Expiry date of an fee account';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.FEE_ACT_STATUS is 'Fee account status';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.STATUS_EFFECTIVE_DATE is 'Fee account status effective date';

comment on table  oms_owner.OFF_FEE_DED_BENEFICIARIES is 'Beneficiary details for the offender fee account';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.OFF_FEE_DED_BENEFICIARY_ID is 'Unique record id [existing sequence=BENEFICIARY_ID]'; 
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.OFFENDER_FEE_ID is 'Offender fee account id';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.PERSON_ID is 'For person beneficiaries, uniquely identifies the person';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.CORPORATE_ID is 'For corporate beneficiaries, uniquely identifies the corporation';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.PRIORITY is 'The priority for the collection against the beneficiary in the context of multiple beneficiaries';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.AMOUNT is 'The Amount to be collected for the beneficiary';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.PERCENT is 'Percentage of the amount collected to be applied to the beneficiary';

comment on table oms_owner.OFF_FEE_DED_RECEIPTS is 'Offender fee account deduction details';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.OFF_FEE_DED_RECEIPT_ID is 'Unique record id [sequence=OFF_FEE_DED_RECEIPT_ID]';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.OFFENDER_FEE_ID is 'Offender fee account id';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.RECEIPT_TXN_TYPE is 'The receipt type, as defined in TRANSACTION_TYPES table with TXN_USAGE = R';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.RECEIPT_PERCENT is 'The percentage of the receipt amount that will be deducted';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.FLAT_RATE is 'The flat rate amount that will be collected against the receipt amount';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.MINIMUM_TRUST_BALANCE_FLAG is 'Minimum trust balance flag';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.LIST_SEQ is 'Controls the screen display order';

comment on table oms_owner.OFF_INTAKE_REVIEW_QUEUE is 'Offender intake review queue';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.OFFENDER_BOOK_ID is 'The system generated identifier for an offender booking'; 
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.QUEUE_DATETIME is 'The date and time on which the offender was added to the intake review queue';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.AGY_LOC_ID_FROM is 'The community agency location from where the inmate is admitted';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.AGY_LOC_ID_TO is 'The community agency location to which the inmate is admitted';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.INTAKE_REASON is 'Intake reason [ domain=INTAKE_RSN]';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.INTAKE_DATE is 'The community intake date'; 
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.SUP_STATUS is 'Supervision status [ domain=SUP_STATUS, parent code = B denotes billable supervision]';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.SUP_STATUS_DATETIME is 'Supervision status start datetime';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.ACCEPTED_FLAG is 'Indicates if the intake was accepted after review(Y/N values)';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.ACCEPTED_DATE is 'The date the review was accepted';

comment on table oms_owner.OFF_SUPERVISION_DETAILS  is 'Offender supervision details';
comment on column oms_owner.OFF_SUPERVISION_DETAILS.OFFENDER_BOOK_ID is 'The system generated identifier for an offender booking';
comment on column oms_owner.OFF_SUPERVISION_DETAILS.LONGEST_SUPV_EXP_DATE is 'The longest supervision expiry date';

comment on table oms_owner.OFF_SUPERVISION_STS_HTY is 'Offender supervision status history';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.OFFENDER_SUP_ID is 'Unique record id [sequence=OFFENDER_SUP_ID]';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.OFFENDER_BOOK_ID is 'The system generated identifier for an offender booking';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.SUP_STATUS is 'Supervision status [ domain=SUP_STATUS, parent code = B denotes billable supervision]';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.START_DATETIME is 'Supervision start date and time (hour and minutes only)';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.END_DATETIME is 'Supervision end date and time (hour and minutes only)'; 
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.COMMENT_TEXT is 'The comment text of the supervision';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.ERROR_FLAG is 'The record is marked as error instead of deleting it';

COMMENT ON COLUMN OMS_OWNER.STAFF_MEMBERS.AD_USER IS 'Store "Y" for Active Directory users';
-----ported_foreign_keys_V4_3_2_8_2_5_20220526.sql - NO COMMENTS ADDED
-----new_row_id_implementation_V5_0_0_0_1.sql - NO COMMENTS ADDED
-----ported_sequences_V4_3_2_8_2_5_20220526.sql - NO COMMENTS ADDED
-----ported_views_V4_3_2_8_2_5.sql - NO COMMENTS ADDED
-----ported_functions_V4_3_2_8_2_5.sql - NO COMMENTS ADDED
-----new_tables_V5_0_0_0_1.sql - NO COMMENTS ADDED


--V5_0_0_0_2 --
-----pk_uk_fk_indexes_V5_0_0_0_2.sql - NO COMMENTS ADDED

--V5_0_0_0_3 --
-----view_v50003_20220615.sql - NO COMMENTS ADDED
-----functions_v50003_20220615.sql - NO COMMENTS ADDED

--V5_0_0_0_4 --
-----trigger_fct_v_offender_all_schedules_2_tu_5_0_0_0_4.sql - NO COMMENTS ADDED

-- V5_0_0_0_5 --
-----views_v5_0_0_0_5.sql - NO COMMENTS ADDED

-- V5_0_0_0_6 --
-----tables_ddl_v5_0_0_0_6.sql - NO COMMENTS ADDED

--V5_0_0_0_6_hf_1 -- NO DDL FOLDER

--V5_0_0_0_6_hf_2 -- 
-----functions_v50006_hf_2_20220826.fun - NO COMMENTS ADDED

--V5_0_0_0_7 --
-----external_message_id.seq - NO COMMENTS ADDED
-----automation_error_log.tab - NO COMMENTS ADDED
-----event_measure_outcomes_v50007.tab
comment on column oms_owner.event_measure_outcomes.cancel_flag is 'If Outcome Cancel Flag is set Y for that Schedule type when cancel an apppointment need to send notification for that appointment';
-----event_measures_v50007.tab
comment on column oms_owner.event_measures.email_flag is 'If Email Flag is set Y for that Schedule type need to send notification for that appointment';
comment on column oms_owner.event_measures.sms_flag is 'If SMS Flag is set Y for that Schedule type need to send notification for that appointment';
-----maintain_specialized_prg_sts.tab
comment on column maintain_specialized_prg_sts.code  is 'Reference code';
comment on column maintain_specialized_prg_sts.description  is 'Description of the code';
comment on column maintain_specialized_prg_sts.update_flaG  is 'For restricting update when we check the flag.';
comment on column maintain_specialized_prg_sts.update_reason_flag is 'For restricting update reason when we check the flag.';
-----offender_cond_transfer.tab
comment on column OFFENDER_COND_TRANSFER.CON_TRANSFER_ID is 'A sequence number used to uniquely identify the conditonal transfer of ownership. Incremented starting at 1 within each transfer.';
comment on column OFFENDER_COND_TRANSFER.OFFENDER_BOOK_ID is 'Parent OFFENDER_BOOK_ID (identifying the offender to whom this record belongs).';
comment on column OFFENDER_COND_TRANSFER.OFFENDER_SENT_CONDITION_ID is 'Condition Which is Transferred';
comment on column OFFENDER_COND_TRANSFER.STAFF_ID is 'By Whom the condition is being assigned to the staff';
comment on column OFFENDER_COND_TRANSFER.TEAM_ID is 'By Whom the condition is being assigned to the Team';
comment on column OFFENDER_COND_TRANSFER.TO_TEAM_ID is 'Condition which was tranfered from the TEAM_ID to TO_TEAM_ID';
comment on column OFFENDER_COND_TRANSFER.TEAM_MEMBER_ID is 'By Whom the condition is being assigned to the Team Member';
comment on column OFFENDER_COND_TRANSFER.AGY_LOC_ID is 'To Identify where the condition is located';
comment on column OFFENDER_COND_TRANSFER.TO_AGY_LOC_ID is 'To Identify where the condition is transfer to';
comment on column OFFENDER_COND_TRANSFER.CONDI_STATUS is 'This Field is used to Store The Condition Is awiting or Transfer or Cancelled State';
comment on column OFFENDER_COND_TRANSFER.TO_STAFF_ID is 'Condition which was tranfered from the STAFF_ID/TEAM_ID to TO_STAFF_ID';
comment on column OFFENDER_COND_TRANSFER.TO_TEAM_MEMBER_ID is 'Condition which was tranfered from the TEAM_MEMBER_ID to TO_TEAM_MEMBER_ID';
COMMENT ON COLUMN offender_cond_transfer.PARENT_CON_TRANSFER_ID    IS 'Refers to  where the Previous condition is assigned';
COMMENT ON COLUMN offender_cond_transfer.SENTENCE_SEQ    IS 'To Identify the sentence related to the particular condition';
-----offender_cond_transfer_id.seq - NO COMMENTS ADDED
-----offender_ind_schedules_v50007.tab
comment on column oms_owner.offender_ind_schedules.email_sent_flag is  'When email scheduled was sent successfully , this flag will set to Y';
comment on column oms_owner.offender_ind_schedules.sms_sent_flag is  'When SMS scheduled was sent successfully , this flag will set to Y';
comment on column oms_owner.offender_ind_schedules.sms_schedule_hours_before is 'To set the time before sms notificaion to be sent';
comment on column oms_owner.offender_ind_schedules.email_schedule_hours_before is 'To set the time before email notificaion to be sent';
comment on column oms_owner.offender_ind_schedules.email_flag is 'If Email Flag is set Y for that Schedule type need to send notification for that appointment';
comment on column oms_owner.offender_ind_schedules.sms_flag is 'If SMS Flag is set Y for that Schedule type need to send notification for that appointment';
-----offender_prg_obligation_hty_v50007.tab
comment on column oms_owner.offender_prg_obligation_hty.comment_text is 'Free text comment related to the program obligation.';
-----staff_members_v50007.tab - NO COMMENTS ADDED
-----system_settings.tab - NO COMMENTS ADDED
-----oms_utils_combine_date_time.fun - NO COMMENTS ADDED
-----v_offender_all_schedules_2.vew - NO COMMENTS ADDED
-----v_offender_all_schedules.vew - NO COMMENTS ADDED
-----v_offender_all_schedules_new.vew - NO COMMENTS ADDED

--V5_0_0_0_8 --
-----get_comm_staff_cur_type.typ - NO COMMENTS ADDED
-----court_evnt_appoint_outcome_id.seq - NO COMMENTS ADDED
-----ocdleglo_sanction_hty_id.seq - NO COMMENTS ADDED
-----servicebus_queue_id.seq - NO COMMENTS ADDED
-----court_events_v50008.tab 
comment on column oms_owner.court_events.sentence_seq is 'This field is used to save the Sentence number';
comment on column oms_owner.court_events.order_type is 'This Field is used to save the Sentence order type ';
comment on column oms_owner.court_events.recommended_sanction_count is 'This field is used to save the recommended sanction count for court event';
comment on column oms_owner.court_events.recommended_reward_count is 'This field is used to save the recommended reward count for court event';  
comment on column oms_owner.court_events.additional_counts_comment is 'This field is used to store the Additional comment which is entered for sanction or reward';
-----court_evnt_appointment_outcome.tab
comment on column oms_owner.court_evnt_appointment_outcome.court_event_appointment_id is 'This field is used to store the unique court event  linked with appoinment outcome';
comment on column oms_owner.court_evnt_appointment_outcome.court_event_id is 'This field is used to store the court event id which is linking to the appointment outcome';
comment on column oms_owner.court_evnt_appointment_outcome.appointment_event_id is 'This field is used to identify the Event id of Schedule type and schedule sub type ';
comment on column oms_owner.court_evnt_appointment_outcome.record_sanction_reward_count is 'This field is used to store the sanction count or reward count for offender outcome ';
comment on column oms_owner.court_evnt_appointment_outcome.count_type is 'This field is used to identify if the count is sanction or reward';
comment on column oms_owner.court_evnt_appointment_outcome.adjourned_flag is 'This is field is used to store the adjourned flag for out come';
comment on column oms_owner.court_evnt_appointment_outcome.comment_text is 'This field is used to store the comment text for linking appointment outcome of an offender';
comment on column oms_owner.court_evnt_appointment_outcome.link_flag is 'This flag indicates that weather the appointment is linked or unlinked to the court event';
comment on column oms_owner.court_evnt_appointment_outcome.create_datetime is 'The timestamp when the record is created';
comment on column oms_owner.court_evnt_appointment_outcome.create_user_id is 'The user who creates the record';
comment on column oms_owner.court_evnt_appointment_outcome.modify_datetime is 'The timestamp when the record is modified ';
comment on column oms_owner.court_evnt_appointment_outcome.modify_user_id is 'The user who modifies the record';
-----dynamic_grid_config.tab
comment on table oms_owner.dynamic_grid_config is 'To Maintain dynamic grids config';
comment on column oms_owner.dynamic_grid_config.id  is 'To save the unique id';
comment on column oms_owner.dynamic_grid_config.module_name  is 'To save the module name';
comment on column oms_owner.dynamic_grid_config.config_json  is 'To save grid config json';
comment on column oms_owner.dynamic_grid_config.grid_name  is 'To save the grid name';
comment on column oms_owner.dynamic_grid_config.db_table_name  is 'To save the db table name';
comment on column oms_owner.dynamic_grid_config.create_datetime  is 'To save create date and time';
comment on column oms_owner.dynamic_grid_config.create_user_id  is 'To save create user id';
comment on column oms_owner.dynamic_grid_config.modify_datetime  is 'To save modify date and time';
comment on column oms_owner.dynamic_grid_config.modify_user_id  is 'To save modify user id';
-----event_measures_v50008.tab
comment on column oms_owner.event_measures.non_association_flag is 'If Non-Association Flag is set  Y for that Schedule type need to check non-association for that appointment';
comment on column oms_owner.event_measures.sanctions_flag is 'This column is used to configure the schedule type and sub type is applicable for sanctions or not.';
-----ocdleglo_sanction_hty.tab
comment on table  oms_owner.ocdleglo_sanction_hty is 'This table is used to save the sanction count history';
comment on column oms_owner.ocdleglo_sanction_hty.off_sanction_sent_hty_id is 'This column is used to store the sequence number for history record';
comment on column oms_owner.ocdleglo_sanction_hty.offender_book_id is 'This column is used to store the offender book id';
comment on column oms_owner.ocdleglo_sanction_hty.sentence_seq is 'This column is used to store the Sentence Seq';
comment on column oms_owner.ocdleglo_sanction_hty.order_type is 'This Column is used to store the order type';
comment on column oms_owner.ocdleglo_sanction_hty.adjust_reason is 'Reason for the change.  Reference Code(SENT_CHG_RSN)';
comment on column oms_owner.ocdleglo_sanction_hty.staff_id is 'This field is used to store the staff who is adding the sanction count';
comment on column oms_owner.ocdleglo_sanction_hty.adjust_date is 'This column is used to store the adjust date';
comment on column oms_owner.ocdleglo_sanction_hty.adjust_time is 'This column is used to store the adjust  time';
comment on column oms_owner.ocdleglo_sanction_hty.new_counter is 'This column is used to store the counter values';
comment on column oms_owner.ocdleglo_sanction_hty.create_datetime is 'The timestamp when the record is created';
comment on column oms_owner.ocdleglo_sanction_hty.create_user_id is 'The user who creates the record';
comment on column oms_owner.ocdleglo_sanction_hty.modify_datetime is 'The timestamp when the record is modified ';
comment on column oms_owner.ocdleglo_sanction_hty.modify_user_id is 'The user who modifies the record';
comment on column oms_owner.ocdleglo_sanction_hty.COMMENT_TEXT is 'This field is used to store the comment text for counter comment';
-----offender_sentence_hty_v50008.tab
comment on column oms_owner.offender_sentence_hty.comment_text is 'This field is used to save the comment information when giving count';
-----offenders_external_search_v50008.tab
comment on column oms_owner.offenders_external_search.gender_code is 'Reference Code ( Gender).  The Socially defined gender of the offender.';
-----offenders_v50008.tab
comment on column oms_owner.offenders.gender_code is 'Reference Code ( GENDER).  The Socially defined gender of the offender.';
comment on column oms_owner.offenders.sex_code is 'Reference Code ( SEX).  The actual gender of the offender.';
-----offenders_jn_v50008.tab
comment on column oms_owner.offenders_jn.gender_code is 'Reference Code ( GENDER).  The Socially defined gender of the offender.';
comment on column oms_owner.offenders_jn.sex_code is 'Reference Code ( SEX).  The actual gender of the offender.';
-----oms_module_ins_dashboard.tab - NO COMMENTS ADDED
-----oms_modules_v50008.tab - NO COMMENTS ADDED
-----or_audit_v50008.tab
comment on column oms_owner.or_audit.gender_code is 'Reference Code ( Gender).  The Socially defined gender of the offender.';
-----schedule_series_rule.tab - NO COMMENTS ADDED
-----sentence_calc_types_v50008.tab
comment on column oms_owner.sentence_calc_types.sanctions_flag is 'This column is used to store weather the saving sentence is eligible for sanctions or not';
-----servicebus_failed_msg_queue.tab
comment on table oms_owner.servicebus_failed_msg_queue is 'To Maintain Failed Service Bus Messages';
comment on column oms_owner.servicebus_failed_msg_queue.queue_id is 'sequence fo queue message';
comment on column oms_owner.servicebus_failed_msg_queue.queue_name is 'Name of the Azure Service Bus queue';
comment on column oms_owner.servicebus_failed_msg_queue.connection_key is 'Connection string for Azure Service Bus Namespace.';
comment on column oms_owner.servicebus_failed_msg_queue.message is 'The message which we tried to send to the Azure Service Bus';
comment on column oms_owner.servicebus_failed_msg_queue.error_message is 'Reason for the Failure of Connection to Azure Service Bus';
comment on column oms_owner.servicebus_failed_msg_queue.create_datetime is 'The timestamp when the record was created';
comment on column oms_owner.servicebus_failed_msg_queue.create_user_id is 'The user account that created the record';
comment on column oms_owner.servicebus_failed_msg_queue.modify_datetime is 'The timestamp when the record was last modified ';
comment on column oms_owner.servicebus_failed_msg_queue.modify_user_id is 'The user account that modified the record';
comment on column oms_owner.servicebus_failed_msg_queue.seal_flag is 'Flag to indicate whether the record has been sealed or not (Y/N).';
-----functions_v50008_1.fun - NO COMMENTS ADDED
-----functions_v50008_2.fun - NO COMMENTS ADDED
-----v_header_block_fn.fun - NO COMMENTS ADDED
-----drop_views_v50008.sql - NO COMMENTS ADDED
-----v_header_block.vew - NO COMMENTS ADDED
-----v_off_observation_periods_res.vew - NO COMMENTS ADDED
-----v_gang_affiliations.vew - NO COMMENTS ADDED
-----v_offender_cip_inquiry.vew - NO COMMENTS ADDED
-----v_trust_header.vew - NO COMMENTS ADDED

--V5_0_0_0_9 --
-----orders_hty_order_hty_id.seq - NO COMMENTS ADDED
-----schedule_series_rule_series_id.seq - NO COMMENTS ADDED
-----bpmn_process_v50009.tab - NO COMMENTS ADDED
-----offender_em_tag_details.tab 
comment on table offender_em_tag_details is 'This table is used to store the the Offender EM tag Datils ';

comment on column offender_em_tag_details.em_tag_id is 'This field is used to store the em tag id for an offender';
comment on column offender_em_tag_details.offender_book_id is 'This field is used to save the offender book id';
comment on column offender_em_tag_details.em_tag_data is 'This field is used to save the Em Tag data';
comment on column offender_em_tag_details.em_tag_strap_size is 'This Field is use to store the configuration value of em tag strap size ';
comment on column offender_em_tag_details.em_daily_charging_period is 'This field is used to store the charging period time';
comment on column offender_em_tag_details.create_datetime is 'This field is used to save when the activity plan is created ';
comment on column offender_em_tag_details.create_user_id is 'This field is used to save Who the activity plan is created ';
comment on column offender_em_tag_details.modify_datetime is 'This field is used to save when the activity plan is modified';
comment on column offender_em_tag_details.modify_user_id is 'This field is used to save Who the activity plan is modified';
comment on column offender_em_tag_details.seal_flag is 'This field is used to identify the seal flag';
-----offender_ind_schedules_v50009.tab 
comment on column offender_ind_schedules.series_id is 'To indentify created scheduled is manually scheduled or scheduled from recurring';
-----offender_prg_obligations_v50009.tab 
comment on column offender_prg_obligations.order_type is 'The order_type indicates the sentence type';
-----offender_proceedings_v50009.tab - NO COMMENTS ADDED
-----schedule_series_rule_v50009.tab
comment on column schedule_series_rule.start_date is 'Start Date of the recurring series';
comment on column schedule_series_rule.start_time is 'Start Time of the recurring series';
comment on column schedule_series_rule.end_time is 'End Time of the recurring series';

comment on column schedule_series_rule.ui_rules is 'Recurrence rule output formula'; 
-----orders_v50009.tab
comment on column orders.defence_solicitor is 'This coulmn is used to maintain defence solicitor';
comment on column orders.requesting_officer is 'This coulmn is used to maintain request officer';
comment on column orders.team_id is 'This indicates that the report assign to the particular team';
comment on column orders.status_date is 'Capturing status updation';
comment on column orders.staff_member_id is 'This indicates that the report assign to the particular staff member';
-----orders_hty.tab
comment on column orders_hty.order_hty_id is 'The ID of  order history';
comment on column orders_hty.order_id is 'The order ID';
comment on column orders_hty.order_type is ' The Reference Code ( ORDER_TYPE )type of legal order - ie. Remand, Detainer,Sentence ...';
comment on column orders_hty.issuing_agy_loc_id is 'Court issuing the legal order.';
comment on column orders_hty.requesting_officer is 'This coulmn is used to maintain request officer';
comment on column orders_hty.request_date is 'The request date (Reports)';
comment on column orders_hty.due_date is 'The due date (Reports)';
comment on column orders_hty.team_id is 'This indicates that the report assign to the particular team';
comment on column orders_hty.staff_member_id is 'This indicates that the report assign to the particular staff member';
comment on column orders_hty.order_status is 'Reference Code ( ORDER_STS ) THE CURRENT ORDER STATUS - ACTIVE, INACTIVE,EXPIRED..';
comment on column orders_hty.create_user_id is 'The user who creates the record';
comment on column orders_hty.create_datetime is 'The timestamp when the record is created';
comment on column orders_hty.modify_user_id is 'The user who modifies the record';
comment on column orders_hty.modify_datetime is 'The timestamp when the record is modifies';
comment on column orders_hty.defence_solicitor is 'This coulmn is used to maintain defence solicitor';
-----wap_comments.tab 
comment on table  wap_comments is 'This Table is used to store the weekly activity comments';
comment on column wap_comments.offender_book_id is 'This field is used to save the offender book id';
comment on column wap_comments.wap_start_date is 'This field is used to maintain the comment of activity plan week start date';
comment on column wap_comments.wap_end_date is 'This field is used to maintain the comment of activity plan week end date';
comment on column wap_comments.comment_text is 'This feld is used to store the comment data of weekly activity plan';
comment on column wap_comments.create_datetime is 'This field is used to save when the comment data is created ';
comment on column wap_comments.create_user_id is 'This field is used to save Who thecomment data is created ';
comment on column wap_comments.modify_datetime is 'This field is used to save when the comment data is modified';
comment on column wap_comments.modify_user_id is 'This field is used to save Who the comment data is modified';
comment on column wap_comments.seal_flag is 'This field is used to identify the seal flag'; 
-----weekly_activity_plan.tab 
comment on table  weekly_activity_plan is 'To save the weekly activity details';
comment on column weekly_activity_plan.weekly_activity_plan_id is 'This Field is used to store the sequence of Weekly activity plan';
comment on column weekly_activity_plan.offender_book_id is 'This field is used to save the offender book id';
comment on column weekly_activity_plan.activity_date is 'This field is used to save the Scheduled activity date';
comment on column weekly_activity_plan.wap_start_date is 'This field is used to save the Scheduled activities Week Start date';
comment on column weekly_activity_plan.wap_end_date is 'This field is used to save the Scheduled activities Week End date';
comment on column weekly_activity_plan.activity_id is 'This Field is used to save the system generated activity id';
comment on column weekly_activity_plan.activity is 'This field is used to save the activity for scheduled wap';
comment on column weekly_activity_plan.activity_address is 'This Field is used to save the scheduled activity address';
comment on column weekly_activity_plan.depart_start_time is 'This field used to save Depart Start time for perticular activity';
comment on column weekly_activity_plan.activity_start is 'This field used to save Activity start time for perticular activity';
comment on column weekly_activity_plan.activity_finish is 'This field is used to save the Activity end  time for perticular activity';
comment on column weekly_activity_plan.return_time is 'This field is used to save the depart return time for activity';
comment on column weekly_activity_plan.mode_of_transport is 'This field is used to store the mode of transport';
comment on column weekly_activity_plan.update_indicator is 'This field is used to save activity plan is updated or added or canceled after amend';
comment on column weekly_activity_plan.comment_text is 'This field is used to save the comment text';
comment on column weekly_activity_plan.create_datetime is 'This field is used to save when the activity plan is created ';
comment on column weekly_activity_plan.create_user_id is 'This field is used to save Who the activity plan is created ';
comment on column weekly_activity_plan.modify_datetime is 'This field is used to save when the activity plan is modified';
comment on column weekly_activity_plan.modify_user_id is 'This field is used to save Who the activity plan is modified';
comment on column weekly_activity_plan.seal_flag is 'This field is used to identify the seal flag';
comment on COLUMN  weekly_activity_plan.finalized is 'To save the Finalize status of scheduled activity';
comment on COLUMN  weekly_activity_plan.version_no is 'To save the version number of the scheduled activity';
-----weekly_activity_plan_hty.tab  
comment on table  weekly_activity_plan_hty is 'To save the weekly activity history details';
comment on column weekly_activity_plan_hty.weekly_activity_plan_hty_id is 'This Field is used to store the sequence of Weekly activity plan history data';
comment on column weekly_activity_plan_hty.weekly_activity_plan_id is 'This Field is used to store the sequence of Weekly activity plan';
comment on column weekly_activity_plan_hty.offender_book_id is 'This field is used to save the offender book id';
comment on column weekly_activity_plan_hty.activity_date is 'This field is used to save the Scheduled activity date';
comment on column weekly_activity_plan_hty.wap_start_date is 'This field is used to save the Scheduled activities Week Start date';
comment on column weekly_activity_plan_hty.wap_end_date is 'This field is used to save the Scheduled activities Week End date';
comment on column weekly_activity_plan_hty.activity_id is 'This Field is used to save the system generated activity id';
comment on column weekly_activity_plan_hty.activity is 'This field is used to save the activity for scheduled wap';
comment on column weekly_activity_plan_hty.activity_address is 'This Field is used to save the scheduled activity address';
comment on column weekly_activity_plan_hty.depart_start_time is 'This field used to save Depart Start time for perticular activity';
comment on column weekly_activity_plan_hty.activity_start is 'This field used to save Activity start time for perticular activity';
comment on column weekly_activity_plan_hty.activity_finish is 'This field is used to save the Activity end  time for perticular activity';
comment on column weekly_activity_plan_hty.return_time is 'This field is used to save the depart return time for activity';
comment on column weekly_activity_plan_hty.mode_of_transport is 'This field is used to store the mode of transport';
comment on column weekly_activity_plan_hty.update_indicator is 'This field is used to save activity plan is updated or added or canceled after amend';
comment on column weekly_activity_plan_hty.comment_text is 'This field is used to save the comment text';
comment on column weekly_activity_plan_hty.version_no is 'This field is used to save the version no of history';
comment on column weekly_activity_plan_hty.finalized is 'To save the Finalize status of scheduled activity';
comment on column weekly_activity_plan_hty.create_datetime is 'This field is used to save when the activity plan is created ';
comment on column weekly_activity_plan_hty.create_user_id is 'This field is used to save Who the activity plan is created ';
comment on column weekly_activity_plan_hty.modify_datetime is 'This field is used to save when the activity plan is modified';
comment on column weekly_activity_plan_hty.modify_user_id is 'This field is used to save Who the activity plan is modified';
comment on column weekly_activity_plan_hty.seal_flag is 'This field is used to identify the seal flag';
-----v_offender_all_schedules_2.vew - NO COMMENTS ADDED
-----v_offender_all_schedules.vew - NO COMMENTS ADDED 
-----v_offender_all_schedules_new.vew - NO COMMENTS ADDED 
-----v_route_locations.vew - NO COMMENTS ADDED
-----v_offender_scheduled_trips.vew - NO COMMENTS ADDED 
-----v_local_trip_occupancy.vew - NO COMMENTS ADDED 
-----v_route_occupancy.vew - NO COMMENTS ADDED 
-----v_transfer_waiting_lists.vew - NO COMMENTS ADDED 
-----v_assign_offender_trips.vew - NO COMMENTS ADDED 
-----v_local_trip_offenders.vew - NO COMMENTS ADDED 
-----v_trust_header1.vew - NO COMMENTS ADDED 
-----v_offender_all_schedules_5.vew - NO COMMENTS ADDED 
-----v_transfer_waiting_lists_3.vew - NO COMMENTS ADDED 
-----v_header_block_fn1.vew - NO COMMENTS ADDED


--V5_0_0_0_10 --
-----offender_em_tag_details_v500010.tab
comment on column offender_em_tag_details.em_tag_start_time is 'This field is used to save start time of em tag';
comment on column offender_em_tag_details.em_tag_end_time is 'This field is used to save end time of em tag';
-----v_offender_visit_visitors.vew - NO COMMENTS ADDED
-----v_offender_visits.vew - NO COMMENTS ADDED

--V5_0_0_0_11 --
-----foreign_keys_v500011.tab - NO COMMENTS ADDED
-----offender_unpaid_work_adj_v500011.tab - NO COMMENTS ADDED
COMMENT ON COLUMN OFFENDER_UNPAID_WORK_ADJ.ORDER_TYPE IS 'THE ASSOCIATED ORDER TYPE FOR THE ADJUSTMENT RECORD';
-----non_association_chk_na_liv_unit_conflict.fun 


--V5_0_0_0_11_HF --
-----tag_prg_provider_name.fun - NO COMMENTS ADDED
-----tag_prg_prg_event_type.fun - NO COMMENTS ADDED
-----sequences_remove_cache_v500011HF.seq - NO COMMENTS ADDED
-----offender_unpaid_work_adj_v500011HF.tab
comment on column offender_unpaid_work_adj.offender_sent_condition_id is 'Reference to the  offender_sent_conditions';

--V5_0_0_0_11_HF1 --
-----off_obs_add_check_details_v500011HF1.tab 
-----off_obs_characteristics_v500011HF1.tab 
comment on column off_obs_characteristics.characteristics_type is 'This field is used to store the type of the characteristic code'; 
-----off_obs_period_checks_v500011HF1.tab 
comment on column off_obs_period_checks.comment_text is 'This field is used to store the comment text for period check';
-----offender_observation_types_v500011HF1.tab 
comment on column offender_observation_types.cell_condition_flag is 'This field is used to store the observation type cell_condition_flag is configured of offender period Check';
comment on column offender_observation_types.activity_flag is 'This field is used to store the observation type activity_flag is configured of offender period Check';
comment on column offender_observation_types.demeanor_flag is 'This field is used to store the observation type demeanor_flag is configured of offender period Check';
comment on column offender_observation_types.not_in_cell_flag is 'This field is used to store the observation type not_in_cell_flag is configured of offender period Check';
comment on column offender_observation_types.officer_notes_flag is 'This field is used to store the observation type officer_notes_flag is configured of offender period Check';

--V5_0_0_0_11_HF2 --
-----v_offender_course_appts_2.vew - NO COMMENTS ADDED

--V5_0_0_2_1 --
-----drop_views_v500012.sql - NO COMMENTS ADDED
-----drop_tables_v500012.sql - NO COMMENTS ADDED
-----obs_type_version_id_seq.seq - NO COMMENTS ADDED
-----obs_period_id_seq.seq - NO COMMENTS ADDED 
-----check_id_seq.seq - NO COMMENTS ADDED  
-----community_conditions_v500012.tab
comment on column oms_owner.community_conditions.allocation_flag is 'To indentify whether the condition is eligible for allocation.';
-----iwp_bookmark_out_parameters.tab 
comment on table iwp_bookmark_out_parameters is 'Tracks the output parameters of composite bookmarks';

comment on column iwp_bookmark_out_parameters.id is 'Unique Id';
comment on column iwp_bookmark_out_parameters.bookmark_name is 'Stores the bookmark name';
comment on column iwp_bookmark_out_parameters.parameter_name is 'Stores the output parameter name';
comment on column iwp_bookmark_out_parameters.create_datetime is 'Stores the created date and time';
comment on column iwp_bookmark_out_parameters.create_user_id is 'Stores the created user id';
comment on column iwp_bookmark_out_parameters.modify_datetime is 'Stores the modified date and time';
comment on column iwp_bookmark_out_parameters.modify_user_id is 'Stores the modified user id';
-----iwp_documents_v500012.tab - NO COMMENTS ADDED  
-----iwp_templates_v500012.tab 
comment on column oms_owner.iwp_templates.signature_access is 'It indicates if signature access is allowed for the template';

comment on column oms_owner.iwp_documents.signed_doc is 'It stores the signed document in blob format';
-----offender_observation_types_v500012.tab 
comment on table oms_owner.offender_observation_types is 'Stores versions of offender observation types (maintenance table)';
comment on column oms_owner.offender_observation_types.obs_type_version_id is 'Unique record identifier (db seq=obs_type_version_id_seq)';
comment on column oms_owner.offender_observation_types.observation_type is 'Observation Type [ref domain=OBSRVATN_TYP]';
comment on column oms_owner.offender_observation_types.frequency is 'The frequency (in minutes) for performing observation checks. The value is used to calculate the scheduled time of the next check from the actual time of the last performed check';
comment on column oms_owner.offender_observation_types.notification_flag is 'Indicates if observation type sends notifications';
comment on column oms_owner.offender_observation_types.notification_timing is 'Indicates how many minutes before the next scheduled check the system sends a notification'; 
comment on column oms_owner.offender_observation_types.list_seq is 'Observation type sequence';
comment on column oms_owner.offender_observation_types.active_flag is 'Indicates if the configured observation type is active (Y/N)';
comment on column oms_owner.offender_observation_types.expiry_date is 'The observation type expiry date';
comment on column oms_owner.offender_observation_types.officer_notes_flag is 'Indicates that officer note has to be recorded against for the observation checks (Y/N)'; 
-----off_obs_add_details_v500012.tab 
comment on table oms_owner.off_obs_add_details is 'Stores details options for the offender observation type  (maintenance table)';
comment on column oms_owner.off_obs_add_details.obs_type_version_id is 'Unique identifier for a specific version of an observation type';
comment on column oms_owner.off_obs_add_details.detail_type is 'Observation detail type (static values CELL_CNDITNS,ACTIVITY,COM_DET_CAT,NOT_IN_CELL)';
comment on column oms_owner.off_obs_add_details.detail_code is 'Observation detail type [ref domain=CELL_CNDITNS,ACTIVITY,COM_DET_CAT,NOT_IN_CELL]';
-----offender_observation_zones_v500012.tab 
comment on table oms_owner.offender_observation_zones is 'Stores the offender observation zones (maintenance table)';
comment on column oms_owner.offender_observation_zones.agy_loc_id is 'The agency location of the zone';
comment on column oms_owner.offender_observation_zones.zone_code is 'The zone code [ref domain=OBSRVTN_ZONE]';
comment on column oms_owner.offender_observation_zones.list_seq is 'The zone sequence';
comment on column oms_owner.offender_observation_zones.active_flag is 'Indicates if the configured zone is active (Y/N)';
comment on column oms_owner.offender_observation_zones.expiry_date is 'The zone expiry datetime';
-----off_obs_zone_details_v500012.tab 
comment on table oms_owner.off_obs_zone_details is 'Stores the housing locations for offender observation zones (maintenance table)';
comment on column oms_owner.off_obs_zone_details.agy_loc_id is 'The agency location of the zone';
comment on column oms_owner.off_obs_zone_details.zone_code is 'The zone code [ref domain=OBSRVTN_ZONE]';
comment on column oms_owner.off_obs_zone_details.internal_location_id is 'This system identifier of the housing location configured under the zone'; 
comment on column oms_owner.off_obs_zone_details.sensor_id is 'Sensor id (free text)';
comment on column oms_owner.off_obs_zone_details.list_seq is 'The sequence of housing location';
comment on column oms_owner.off_obs_zone_details.active_flag is 'Indicates if the housing location configured under the zone is active (Y/N)';
comment on column oms_owner.off_obs_zone_details.expiry_date is 'The zone housing location expiry datetime';
-----off_observation_periods_v500012.tab 
comment on table oms_owner.off_observation_periods is 'Stores the offender observation periods (offender data)';
comment on column oms_owner.off_observation_periods.obs_period_id is 'Surogate unique identifier for offender observation period (db seq=obs_period_id_seq)';
comment on column oms_owner.off_observation_periods.offender_book_id is 'System identifier for offender booking.';
comment on column oms_owner.off_observation_periods.obs_period_seq is 'Offender observation period sequence, starts at 1 for each booking';
comment on column oms_owner.off_observation_periods.obs_type_version_id is 'Unique identifier for a specific version of an observation type';
comment on column oms_owner.off_observation_periods.start_datetime is 'This field is used to store the observation period start datetime';
comment on column oms_owner.off_observation_periods.end_reason_code is 'End Reason Code [ref domain=END_RSN_CODE]'; 
comment on column oms_owner.off_observation_periods.end_datetime is 'This field is used to store the end datetime of offender observation period';
comment on column oms_owner.off_observation_periods.status_code is 'Status of observation period, static value: ACTIVE, EXPIRED'; 
-----off_obs_period_checks_v500012.tab 
comment on table oms_owner.off_obs_period_checks is 'Stores the Offender observation period checks details (offender data)';
comment on column oms_owner.off_obs_period_checks.check_id is 'Surogate unique identifier for check (db seq=check_id_seq)';
comment on column oms_owner.off_obs_period_checks.obs_period_id is 'Unique identifier for offender observation period';
comment on column oms_owner.off_obs_period_checks.check_seq is 'Check sequence, starts at 1 for each offender observation period';
comment on column oms_owner.off_obs_period_checks.schedule_datetime is 'The datetime when the check is scheduled to occur';
comment on column oms_owner.off_obs_period_checks.check_datetime is 'Actual datetime when the check is performed';
comment on column oms_owner.off_obs_period_checks.performing_staff_id is 'The staff who performs the period check (records the check datetime, thus executes the check)';  
comment on column oms_owner.off_obs_period_checks.comment_text is 'The officer check detail note';
-----off_obs_add_check_details_v500012.tab 
comment on table oms_owner.off_obs_add_check_details is 'Stores the offender observation additional check details (offender data)';
comment on column oms_owner.off_obs_period_checks.check_id is 'Unique identifier for offender observation period check';
comment on column oms_owner.off_obs_add_check_details.obs_type_version_id is 'Unique identifier for a specific version of an observation type';
comment on column oms_owner.off_obs_add_check_details.detail_type is 'Check detail type (static values CELL_CNDITNS,ACTIVITY,COM_DET_CAT,NOT_IN_CELL)';  
comment on column oms_owner.off_obs_add_check_details.detail_code is 'Check detail code [ref domain=CELL_CNDITNS,ACTIVITY,COM_DET_CAT,NOT_IN_CELL]';
comment on column oms_owner.off_obs_add_check_details.detail_datetime is 'The datetime when the check detail is created';
comment on column oms_owner.off_obs_add_check_details.reporting_staff_id is 'The staff who records the check detail'; 
-----automation_api_query_v500012.tab 
comment on column automation_api_query.category is 'Indicates the quick action category';
-----bpmn_process_v500012.tab 
comment on column bpmn_process.category is 'Indicates the Process category';
-----dmn_process_v500012.tab 
comment on column dmn_process.category is 'Indicates the decision process category';
-----incident_staff_forces_v500012.tab 
comment on column incident_staff_forces.reason_force_used is 'Reason for using force'; 

--V5_0_0_2_2 --
-----off_external_account_balances_v50022.tab
comment on table oms_owner.off_external_account_balances is 'Stores the most recent offender external balances (no history)';

comment on column oms_owner.off_external_account_balances.account_balance_id is 'Unique record identifier (db seq=ext_account_balance_id)';
comment on column oms_owner.off_external_account_balances.root_offender_id is 'The root offender id of the offender.';
comment on column oms_owner.off_external_account_balances.account_id is 'The inmate identifier in the external system.';
comment on column oms_owner.off_external_account_balances.account_details is 'Additional details of inmate (ex: offender name).';
comment on column oms_owner.off_external_account_balances.account_type is 'The type of financial account that comes from the external system (static values PRIVATE, EARNINGS).'; 
comment on column oms_owner.off_external_account_balances.balance is 'The balance of the external financial account.';
comment on column oms_owner.off_external_account_balances.last_changed is 'The date and time the financial account balance was last updated in the external system.';
-----staff_reports_maint_v50022.sql 
comment on table oms_owner.staff_reports_maint is 'Configuration table for automatic lock on staff reports';

comment on column oms_owner.staff_reports_maint.report_type is 'To report type (codes from ref domain=STAFF_REPORT)';
comment on column oms_owner.staff_reports_maint.automatic_flag is 'Indicates automatic lock of staff report.';
comment on column oms_owner.staff_reports_maint.length is 'Indicates lock interval';
comment on column oms_owner.staff_reports_maint.length_unit is 'Indicates lock interval unit (static values DAYS,HOURS,MONTHS)';
-----agency_incident_parties_v50022.tab
comment on column agency_incident_parties.staff_report_type is 'Staff report type to lock the report as configured in maintenance table(ref domain=STAFF_REPORT)';

comment on column agency_incident_parties.lock_reference_time is 'Reference for automatic locking of the report(the create time or the re-open time of staff report)';
-----incident_staff_reports_v50022.tab
comment on column incident_staff_reports.locked_by is 'The staff who locked the record.';
-----sentence_calc_types_v50022.sql
comment on column sentence_calc_types.charges_flag is 'Indicates if the recording of charges is required for custodial holding orders(Y/N values)'; 
-----case_note_permissions_v50022.tab 
comment on table oms_owner.case_note_permissions is 'Maintains the user access for create, view and amend case notes';
comment on column oms_owner.case_note_permissions.role_id is 'Case note permission based role security from User Groups';
comment on column oms_owner.case_note_permissions.work_id is 'Case note permission based on work item';
comment on column oms_owner.case_note_permissions.create_flag is 'Indicates create privilege for case notes';
comment on column oms_owner.case_note_permissions.view_flag is 'Indicates view privilege for case notes';
-----offender_case_conditions_v50022.sql
comment on column oms_owner.offender_case_conditions.team_id is 'The team the condition has been assigned to.';

comment on column oms_owner.offender_case_conditions.team_member_id is 'The team member the condition has been assigned to (populated only if team_id is populated and it has to be a member of the team).';

comment on column oms_owner.offender_case_conditions.staff_id is 'The staff the condition has been assigned to (populated only if team_id and team_member_id are not populated).';
-----offender_interested_parties_v50022.tab 
comment on table oms_owner.offender_interested_parties is 'Offenders Interested Parties';

comment on column oms_owner.offender_interested_parties.party_id  is 'Unique record identifier';
comment on column oms_owner.offender_interested_parties.offender_book_id  is 'Unique identifier for booking';
comment on column oms_owner.offender_interested_parties.party_type  is 'The party type (ref domain=INST_PARTIES)';
comment on column oms_owner.offender_interested_parties.party_description  is 'The party Description (free text, usualy the name of the party)';
comment on column oms_owner.offender_interested_parties.party_comment  is 'Party details';
comment on column oms_owner.offender_interested_parties.record_type  is '(static values CUST,NCUS,CEVT,BAIL)';  
comment on column oms_owner.offender_interested_parties.record_id  is 'Record link';   
-----tables_audit_v50022.tab
comment on table oms_owner.tables_audit is 'List of tables being audited or audited in the past (not the entire set of tables available in the database)';

comment on column oms_owner.tables_audit.object_name is 'The table name being audited or audited in the past';
comment on column oms_owner.tables_audit.audit_flag is 'Indicates if the table is being audited';
-----elite_generic_log_v5022.tab - NO COMMENTS ADDED
-----elite_generic_log_v5022.fun - NO COMMENTS ADDED
-----oms_modules_help_v50022.tab - NO COMMENTS ADDED

--V5_0_0_2_3 --
-----court_evnt_appointment_outcome_v50023.tab - NO COMMENTS ADDED
-----incident_follow_up_id_v50023.seq - NO COMMENTS ADDED
-----incident_follow_up_details_v50023.tab
comment on table oms_owner.incident_follow_up_details is 'Stores the incident follow up details';

comment on column oms_owner.incident_follow_up_details.incident_follow_up_id is 'Incident followup id unique identifier (sequence=incident_follow_up_id)';
comment on column oms_owner.incident_follow_up_details.agency_incident_id is 'Agency incident ID Log number';
comment on column oms_owner.incident_follow_up_details.policy is 'Policy  [ref domain=IN_POLICY]';
comment on column oms_owner.incident_follow_up_details.compliance is 'Compliance [ref domain=IN_COMP]';
comment on column oms_owner.incident_follow_up_details.comment_text is 'This column is used to save the incident follow up comment';
-----offender_ppty_con_txns_v50023.tab
comment on column oms_owner.offender_ppty_con_txns.action_reason is 'The reason for canceling or rejecting an action of type transfer, [ref domain=P_TRN_CAN,P_TRN_REJ]';
-----offender_ppty_items_v50023.tab
comment on column  oms_owner.offender_ppty_items.property_size is 'Records the property size';

comment on column  oms_owner.offender_ppty_items.property_value is  'Records the property value';
-----offender_ppty_item_txns_v50023.tab
comment on column oms_owner.offender_ppty_item_txns.action_code is 'Code which specifies the action performed on the item';

comment on column oms_owner.offender_ppty_item_txns.action_reason is 'The reason for canceling or rejecting an action of type transfer, [ref domain=P_TRN_CAN,P_TRN_REJ]';
-----oms_module_report_seq_v50023.seq - NO COMMENTS ADDED
-----oms_module_report_v50023.tab
comment on table oms_owner.oms_module_report is 'It stores the Jasper report templates as bytea for each report module.';	

COMMENT ON COLUMN oms_owner.oms_module_report.report_id IS 'Unique number assigned to each report (sequence=oms_module_report_seq).';
COMMENT ON COLUMN oms_owner.oms_module_report.module_name IS 'The code used to uniquely identify the name of the module.';
COMMENT ON COLUMN oms_owner.oms_module_report.report_body IS 'Content of the report';
COMMENT ON COLUMN oms_owner.oms_module_report.report_file_name IS 'File name of the inserted report';
COMMENT ON COLUMN oms_owner.oms_module_report.report_version IS 'Version upgraded every time the report is updated';
-----v_header_block_fn_v50023.fun - NO COMMENTS ADDED
-----oms_intake_get_offender_supervision_u_v50023.fun - NO COMMENTS ADDED
-----pims_weight_get_caseload_type_u1_v50023.fun - NO COMMENTS ADDED
-----pims_weight_get_sup_level_u_v50023.fun - NO COMMENTS ADDED
-----offender_cond_transfer_v50023.tab
comment on column oms_owner.offender_cond_transfer.rcvd_from_loc is 'To identify from which location the condition is recieved';

comment on column oms_owner.offender_cond_transfer.rcvd_from_team is 'To identify from which team the condition is recieved';
-----drop_views_v50023.vew - NO COMMENTS ADDED
-----addresses_v50023.tab
comment on column addresses.longitude is 'The longitude of address';

comment on column addresses.latitude is 'The latitude of address';

comment on column addresses.mesh_block is 'The mesh block of address';

comment on column addresses.is_address_valid is 'NULL=validation was not preformed, Y=validation was performed and the address is valid, N=validation was performed but the address is invalid';

comment on column addresses.address_line1 is 'Full address information that includes street number, street, street direction';
-----addresses_jn_v50023.tab - NO COMMENTS ADDED
-----assessments_v50023.tab
comment on column ASSESSMENTS.Required_Flag is 'Mandatory questions flag';

comment on column offender_assessments.assess_status is 'Assessment status, Reference(ASSESS_STATS) ie. A-Active, P-Pending, I-Inactive, D-Draft';
-----agency_visit_config_v50023.tab
comment on table oms_owner.agency_visit_config is 'Maintains the type of current Security /IEP Level configuration';
comment on column oms_owner.agency_visit_config.agy_loc_id is 'The agency location';
comment on column oms_owner.agency_visit_config.visit_config_type is 'The  value of iep security level [domain=VISIT_CONFIG]';
-----incentives_earn_privs_v50023.tab
comment on table oms_owner.incentives_earn_privs is 'System configured incentives and earned privillages';

comment on column oms_owner.incentives_earn_privs.iep_level_code is 'IEP level code';
comment on column oms_owner.incentives_earn_privs.iep_level_description is 'Description of the code';
comment on column oms_owner.incentives_earn_privs.list_seq is 'List seq';
comment on column oms_owner.incentives_earn_privs.review_days is 'Time period in days';
comment on column oms_owner.incentives_earn_privs.intake_iep is 'IEP code level to be used when custody intake is completed (only one code can be marked as such)';
comment on column oms_owner.incentives_earn_privs.canteen_limits is 'Amount in dollars';
comment on column oms_owner.incentives_earn_privs.active_flag is 'Status of  IEPLevelCode (Y/N)';
comment on column oms_owner.incentives_earn_privs.expiry_date is 'Expiry date for inactive IEP level codes';
-----visit_cycle_limits_v50023.tab
comment on column visit_cycle_limits.visit_config_type is 'Indicates Visit cycle config type (ref domain=VISIT_CONFIG)';

comment on column visit_cycle_limits.visit_config_type_Value is 'Code used to identify IEP/SEC LEVEL configuration value';
-----off_incentives_earn_privs_v50023.tab
comment on table oms_owner.off_incentives_earn_privs is 'Stores the offender incentives earned privileges level details';

comment on column oms_owner.off_incentives_earn_privs.iep_level_seq is 'Unique identification number for each offender assigned to the iep level(sequence=iep_level_seq)';
comment on column oms_owner.off_incentives_earn_privs.offender_book_id is ' The system generated identifier for an offender booking.';
comment on column oms_owner.off_incentives_earn_privs.iep_level_code is 'The IEP LEVEL code';
comment on column oms_owner.off_incentives_earn_privs.assigned_date is ' The date when the IEP_LEVEL is assigned to the offender';
comment on column oms_owner.off_incentives_earn_privs.approved_staff_id is 'The staff who approved the iep level';
comment on column oms_owner.off_incentives_earn_privs.next_review_date is ' The IEP_LEVEL Next Review date';
comment on column oms_owner.off_incentives_earn_privs.review_comment is 'Review comment';
-----offender_visits_v50023.tab
comment on column oms_owner.offender_visits.visit_config_type is 'Indicates whether the Visit type is security level or iep level [domain=VISIT_CONFIG]';
-----v_address_usages_v50023.vew - NO COMMENTS ADDED
-----corporate_address_v_v50023.vew - NO COMMENTS ADDED
-----v_offender_course_events_v50023.vew - NO COMMENTS ADDED
-----v_offender_all_schedules_2_v50023.vew - NO COMMENTS ADDED
-----v_offender_all_schedules_new_v50023.vew - NO COMMENTS ADDED
-----v_offender_all_schedules_v50023.vew - NO COMMENTS ADDED
-----v_addresses_v50023.vew - NO COMMENTS ADDED
-----person_address_v_v50023.vew - NO COMMENTS ADDED
-----v_corporate_addresses_v50023.vew - NO COMMENTS ADDED
-----v_offender_employ_addresses_v50023.vew - NO COMMENTS ADDED
-----v_offender_addresses_v50023.vew - NO COMMENTS ADDED
-----v_staff_addresses_v50023.vew - NO COMMENTS ADDED
-----v_course_activities_v50023.vew - NO COMMENTS ADDED
-----v_agency_addresses_v50023.vew - NO COMMENTS ADDED
-----v_offender_education_addresses_v50023.vew - NO COMMENTS ADDED
-----v_person_addresses_v50023.vew - NO COMMENTS ADDED
-----v_off_ext_movements_v50023.vew - NO COMMENTS ADDED
-----v_offender_scheduled_trips_v50023.vew - NO COMMENTS ADDED
-----v_route_locations_v50023.vew - NO COMMENTS ADDED
-----v_local_trip_offenders_v50023.vew - NO COMMENTS ADDED
-----v_local_trip_occupancy_v50023.vew - NO COMMENTS ADDED
-----v_route_occupancy_v50023.vew - NO COMMENTS ADDED
-----v_corporate_address_phones_v50023.vew - NO COMMENTS ADDED
-----v_program_placements_v50023.vew - NO COMMENTS ADDED
-----v_off_addresses_v50023.vew - NO COMMENTS ADDED
-----v_curfew_addresses_v50023.vew - NO COMMENTS ADDED
-----offender_address_v_v50023.vew - NO COMMENTS ADDED
-----v_assign_offender_trips_v50023.vew - NO COMMENTS ADDED
-----v_transfer_waiting_lists_v50023.vew - NO COMMENTS ADDED

--V5_0_0_2_4 --
-----offender_assessments_v50024.tab 
comment on column oms_owner.offender_assessments.assess_status is 'Assessment status, Reference(ASSESS_STATS) ie. A-Active, P-Submitted, I-Inactive, D-Draft';
-----oms_module_parameters_v50024.tab
comment on column oms_owner.oms_module_parameters.parent_lov  is 'The parameter name of the parent LOV';
-----tag_utils_get_agency_descp_v50024.fun - NO COMMENTS ADDED
-----tag_utils_get_ref_desc_v50024.fun - NO COMMENTS ADDED
-----offender_ppty_item_txns_v50024.tab - NO COMMENTS ADDED
-----v_court_events_v50024.vew - NO COMMENTS ADDED
-----off_external_account_balances_v50024.tab - NO COMMENTS ADDED

--V5_0_0_2_4_HF -- NO DDL FOLDER

--V5_0_0_2_4_HF1 --
-----iep_level_seq_v50024HF1.seq - NO COMMENTS ADDED

--V5_0_0_2_4_HF2 -- NO DDL FOLDER

--V5_0_0_3_1 --
-----address_id_v50031.seq - NO COMMENTS ADDED
-----assessments_v50031.tab 
comment on column oms_owner.assessments.bookmark_condition is 'Identifies the bookmark answer type [domain=BOOKMRK_COND]';

comment on column oms_owner.assessments.bookmark_name is 'The bookmark name associated with the answer.';

comment on column oms_owner.assessments.min_value is 'The minimum value of the bookmark answer';

comment on column oms_owner.assessments.max_value is 'The maximum value of the bookmark answer';

comment on column oms_owner.assessments.enforce_min_max_flag is 'Indicates if the assessment Min/Max score range is enforced (Y/N)';

comment on column oms_owner.offender_assessments.override_score is 'The assessment override score';
-----offender_mail_logs_v50031.tab 
comment on column oms_owner.offender_mail_logs.contact_type is 'Identifies the contact type [domain=CONTACT_TYPE]';

comment on column oms_owner.offender_mail_logs.mail_type is 'Identifies the mail type [domain=MAIL_TYPE]';

comment on column oms_owner.offender_mail_logs.log_seq is 'Log counter for each booking';
-----offender_mail_restrictions_v50031.tab 
comment on column oms_owner.offender_mail_restrictions.contact_type is 'Identifies the contact type [domain=CONTACT_TYPE]';

comment on column oms_owner.offender_mail_restrictions.restriction_type is 'Identifies the mail restriction type [domain=MAIL_RST_TYP]';

comment on column oms_owner.offender_mail_restrictions.comment_text is 'Captures mail restriction comment';
-----release_schedule_settings_v50031.tab 
comment on table oms_owner.release_schedule_settings is 'The table stores the release schedule settings information (configuration for key date, alerts and charge indicators).'; 
comment on column oms_owner.release_schedule_settings.rel_sch_setting_type is 'Setting type [static values: KEY_DATE, ALERTS, CHARGE_IND]'; 
comment on column oms_owner.release_schedule_settings.rel_sch_setting_value is 'Stores the settings';
-----v_offender_education_addresses_v50031.vew - NO COMMENTS ADDED
-----v_offass_ass_v50031.vew - NO COMMENTS ADDED 

--V5_0_0_3_2 --
-----agency_incident_charges_ext_inv_detls_v50032.tab
comment on table agency_incident_charges_ext_inv_detls is 'This table holds the data of External Investigations done one Agency Indicent Charges';

comment on column agency_incident_charges_ext_inv_detls.agency_incident_id is 'FK to Agency Incident Charges';
comment on column agency_incident_charges_ext_inv_detls.charge_seq is 'FK to Agency Incident Charges';
comment on column agency_incident_charges_ext_inv_detls.eid_seq is 'External Investigation Details seq as part of the PK';
comment on column agency_incident_charges_ext_inv_detls.external_id is 'To capture the External Id';
comment on column agency_incident_charges_ext_inv_detls.contact_datetime is 'To capture the date and time of contact with the external agency';
comment on column agency_incident_charges_ext_inv_detls.ext_inv_status is 'To capture the status of the external investigation at the time of contact [domain=EXT_INV_STAT]';
comment on column agency_incident_charges_ext_inv_detls.ext_inv_comment is 'To capture the the communication with the external investigating agency';
-----agency_incident_charges_v50032.tab
comment on column agency_incident_charges.evidence_dispose_text is 'Charge disposition status. [domain=CHAR_DISP]';
-----drop_foreign_keys_v50032.tab - NO COMMENTS ADDED 
-----agency_shift_logs_v50032.tab
comment on column AGENCY_SHIFT_LOGS.REASON is 'Activity Reason [domain=ACT_REAS].';

comment on column AGENCY_SHIFT_LOGS.START_TIME is 'Activity Start time.';

comment on column AGENCY_SHIFT_LOGS.END_TIME is 'Activity End time.';

comment on column AGENCY_SHIFT_LOGS.amend_flag is 'Indicates the records is amended';

comment on column AGENCY_SHIFT_LOGS.error_flag is 'Indicates the records was entered in error';
-----ocuchgou_data_v50032.tab
comment on table ocuchgou_data is 'This is used to store the historical data of charges';
comment on column ocuchgou_data.id is 'Sequence for data maintenance';
comment on column ocuchgou_data.form_identifier is 'Stores the search criteria Offender_Book_Id';
comment on column ocuchgou_data.form_info_json is 'Stores the Json Data of charges history';
-----offence_id_seq_v50032.seq - NO COMMENTS ADDED 
-----offences_v50032.tab
comment on column oms_owner.offences.offence_id is 'Unique identified of the offence [db sequence=offence_id_seq]';
-----offence_indicators_v50032.tab
comment on column oms_owner.offence_indicators.offence_id is 'Unique identified of the offence';
-----allowable_offence_types_v50032.tab 
comment on column oms_owner.allowable_offence_types.offence_id is 'Unique identified of the offence';
-----offence_code_groupings_v50032.tab
comment on column oms_owner.offence_code_groupings.offence_id is 'Unique identified of the offence';
-----offender_release_details_v50032.tab - NO COMMENTS ADDED 
-----offenders_shift_log_v50032.tab
comment on table offenders_shift_log is 'The Table Displays Related Offenders to a Shift Log Record';
comment on column offenders_shift_log.shift_log_seq is 'Unique identifier for Shift Log Seq';
comment on column offenders_shift_log.offender_book_id is 'System identifier for offender booking';
comment on column offenders_shift_log.reason is 'Reason  [domain=ACT_OFFREA]';
comment on column offenders_shift_log.CREATE_DATETIME is 'The timestamp when the record is created ';
comment on column offenders_shift_log.CREATE_USER_ID is 'The user who created  the record';
comment on column offenders_shift_log.MODIFY_DATETIME is 'The timestamp when the record is modified ';
comment on column offenders_shift_log.MODIFY_USER_ID is 'The user who modifies the record'; 
-----phones_v50032.tab
comment on column PHONES.FORMAT is 'It Indicate the Phone Number Format ,it will configure in system setting screen(Maintain Phone Formats tab)';
-----property_group_items_v50032.tab - NO COMMENTS ADDED 
-----property_groups_v50032.tab
comment on column property_groups.caseload_id is 'The caseload identifier';
comment on column property_groups.property_seq is 'Maintains the property group sequence';
comment on column property_groups.active_flag is 'Indicates if the record is active (Y/N), default value will be Y';
comment on column property_groups.expiry_date is 'It stores the expriy date to maintain when group item is activate or deactivated';
-----property_item_id_v50032.seq - NO COMMENTS ADDED 
-----v_agency_addresses_v50032.vew - NO COMMENTS ADDED 
-----v_corporate_addresses_v50032.vew - NO COMMENTS ADDED 
-----v_offender_employ_addresses_v50032.vew - NO COMMENTS ADDED 
-----v_person_addresses_v50032.vew - NO COMMENTS ADDED 
-----v_staff_addresses_v50032.vew - NO COMMENTS ADDED 
