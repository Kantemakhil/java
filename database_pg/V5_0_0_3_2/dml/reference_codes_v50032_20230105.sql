insert into reference_codes (domain, code, description, list_seq, active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime) values('CATEGORY', 'PAR', 'Parole', 99, 'Y', 'Y', current_timestamp, 'OMS_OWNER', current_timestamp);
INSERT INTO oms_owner.reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ACT_TYPE', 'LETGO', 'Let Go', (select max(list_seq)+1 from reference_codes where domain = 'ACT_TYPE'),'Y', 'Y',NULL, NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP,'OMS_OWNER',NULL, NULL);
INSERT INTO oms_owner.reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ACT_REAS', 'ROUT', 'Routine',  1, 'Y', 'Y',NULL, NULL, NULL, 'LETGO', 'ACT_TYPE', CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ACT_REAS', 'UNSC', 'Unscheduled',  2, 'Y', 'Y',NULL, NULL, NULL, 'LETGO', 'ACT_TYPE', CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ACT_REAS', 'ROUTI', 'Routine',  3, 'Y', 'Y',NULL, NULL, NULL, 'LOCK', 'ACT_TYPE', CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ACT_REAS', 'SREDEP', 'Staff Re-deployed', 4, 'Y', 'Y',NULL, NULL, NULL, 'LOCK', 'ACT_TYPE', CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ACT_REAS', 'EMER', 'Emergency', 5, 'Y', 'Y',NULL, NULL, NULL, 'LOCK', 'ACT_TYPE', CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);INSERT INTO oms_owner.reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ACT_OFFREA', 'CONF', 'Confined',  1, 'Y', 'Y',NULL, NULL, NULL, null, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('ACT_OFFREA', 'SICK', 'Sick in Cell',  1, 'Y', 'Y',NULL, NULL, NULL, null, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL);

update
	reference_codes
set
	description = 'Hold Expiry Date',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER'
where
	code = 'BOOKING_HED'
	and "domain" = 'KEY_DATES';

update
	reference_codes
set
	description = 'Parole Commence Date',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER'
where
	code = 'BOOKING_PCD'
	and "domain" = 'KEY_DATES';

update
	reference_codes
set
	description = 'Bail Commence Date',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER'
where
	code = 'BOOKING_BCD'
	and "domain" = 'KEY_DATES';

update
	reference_codes
set
	description = 'Latest Release Date',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER'
where
	code = 'BOOKING_LRD'
	and "domain" = 'KEY_DATES';

update
	reference_codes
set
	description = 'Parole Eligibility Date',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER'
where
	code = 'BOOKING_PED'
	and "domain" = 'KEY_DATES';

update
	reference_codes
set
	description = 'Remission Eligibility Date',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER'
where
	code = 'BOOKING_RED'
	and "domain" = 'KEY_DATES';

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('CHAR_DISP', 'EINV', 'External Investigation', 1, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('CHAR_DISP', 'AINV', 'Awaiting Investigation', 2, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('CHAR_DISP', 'INVG', 'Investigating', 3, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('CHAR_DISP', 'INVH', 'Investigation On Hold', 4, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('CHAR_DISP', 'ECHR', 'Expect Charges to be laid', 5, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('CHAR_DISP', 'CHRG', 'charged', 6, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('CHAR_DISP', 'CCNS', 'Closed - Charges not Substantiated', 7, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('CHAR_DISP', 'CCNP', 'Closed - Charges Not Pursued', 8, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('EXT_INV_STAT', 'IO', 'Investigation Ongoing', 1, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('EXT_INV_STAT', 'IP', 'Investigation Paused', 2, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('EXT_INV_STAT', 'IC', 'Investigation Complete', 3, 'Y', 'Y','OMS_OWNER', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP,'OMS_OWNER', NULL, NULL);

Insert into REFERENCE_CODES (DOMAIN,CODE,DESCRIPTION,LIST_SEQ,ACTIVE_FLAG,SYSTEM_DATA_FLAG,PARENT_CODE) values ('CHOUTUPREA','LTO','Linked to Order',1,'Y','Y',null);