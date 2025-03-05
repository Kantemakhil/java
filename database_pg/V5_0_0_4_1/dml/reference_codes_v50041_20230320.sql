INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('PAR_EVENTS', 'RPAR', 'Released to Parole',99, 'Y', 'Y', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('PAR_EVENTS', 'PB_WARR_ISS', 'PB Warrant Issued',99, 'Y', 'Y', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('PAR_EVENTS', 'PB_WARR_EXE', 'PB Warrant Executed',99, 'Y', 'Y', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('PAR_EVENTS', 'PAR_SUS', 'Parole Suspended',99, 'Y', 'Y', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('PAR_EVENTS', 'PAR_CON', 'Parole Confirmed',99, 'Y', 'Y', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('PAR_EVENTS', 'PAR_REV', 'Parole Revoked',99, 'Y', 'Y', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('USG_CODE', 'PAR', 'Parole',99, 'Y', 'Y', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);

delete from reference_codes rc where domain='LEGAL_BLOCK' and code in('LICENCE','LICENCE_COND','CASE','SENTENCE','CONDITION');


INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('LEGAL_BLOCK','CUST','Custodial Orders',99,'Y', 'Y',NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('LEGAL_BLOCK','NCUS','Non-Custodial Orders',99,'Y', 'Y', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('LEGAL_BLOCK','BAIL','Bail Orders',99, 'Y', 'Y',  NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('LEGAL_BLOCK','PAR','Parole Orders',99,'Y', 'Y', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('LEGAL_BLOCK','COND','Conditions',99,'Y', 'Y', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);
INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('ACTIVE_TYPE', 'E', 'Excluded', 3, 'Y', 'Y',  NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER',current_timestamp, 'OMS_OWNER', NULL);