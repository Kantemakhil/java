insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_CS',
	'CS Unsuitable',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_CS');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_CS_SO',
	'CS Unsuitable - SO Unsuitable',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_CS_SO');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_CMD_OTH',
	'CMD Unsuitable - Other',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_CMD_OTH');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_CMD_OFT',
	'CMD Unsuitable - Offence Type',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_CMD_OFT');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_CMD_MHD',
	'CMD Unsuitable-Mental Health/ Disability',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_CMD_MHD');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_CMD_UPAR',
	'CMD Unsuitable-Unwilling to Participate',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_CMD_UPAR');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_CMD_PDC',
	'CMD Unsuitable - PDC Alcohol',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_CMD_PDC');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_CMD_SCM',
	'CMD Unsuitable - Supreme Court Matters',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_CMD_SCM');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_HA_CON',
	'HA Unsuitable-Consent',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_HA_CON');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_HA_EM',
	'HA Unsuitable-EM',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_HA_EM');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_HA_INT',
	'HA Unsuitable-Intel',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_HA_INT');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_HA_PRO',
	'HA Unsuitable-Property',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_HA_PRO');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_HA_UNS',
	'Home Assessment Unsuitable',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_HA_UNS');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_REP_UNS',
	'UNS_ELI',
	'Not Eligible',
	99,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_REP_UNS'
		and code = 'UNS_ELI');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_ACT_REC',
	'CONFIRM',
	'Confirm',
	1,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_ACT_REC'
		and code = 'CONFIRM');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_ACT_REC',
	'AMEND_VARY',
	'Amend/Vary',
	2,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_ACT_REC'
		and code = 'AMEND_VARY');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_ACT_REC',
	'CANCEL',
	'Cancel',
	3,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_ACT_REC'
		and code = 'CANCEL');

insert
	into
	reference_codes
(domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	modify_user_id,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	seal_flag)
select
	'CRT_ACT_REC',
	'CAN_RES',
	'Cancel and Resentence',
	4,
	'Y',
	'N',
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CRT_ACT_REC'
		and code = 'CAN_RES');