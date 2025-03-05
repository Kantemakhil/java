insert
	into
	oms_owner.reference_codes
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
	'BILL_STATUS',
	'AR',
	'A/R',
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
		oms_owner.reference_codes
	where
		domain = 'BILL_STATUS'
		and code = 'AR');

insert
	into
	oms_owner.reference_codes
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
	'BILL_STATUS',
	'LD_PP',
	'L&D PP',
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
		oms_owner.reference_codes
	where
		domain = 'BILL_STATUS'
		and code = 'LD_PP');

insert
	into
	oms_owner.reference_codes
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
	'BILL_STATUS',
	'LD_EXI',
	'L&D EX (I)',
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
		oms_owner.reference_codes
	where
		domain = 'BILL_STATUS'
		and code = 'LD_EXI');

insert
	into
	oms_owner.reference_codes
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
	'BILL_STATUS',
	'LD_DOR',
	'L&D DOR',
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
		oms_owner.reference_codes
	where
		domain = 'BILL_STATUS'
		and code = 'LD_DOR');

insert
	into
	oms_owner.reference_codes
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
	'BILL_STATUS',
	'LD_PCF',
	'L&D PCF',
	5,
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
		oms_owner.reference_codes
	where
		domain = 'BILL_STATUS'
		and code = 'LD_PCF');

insert
	into
	oms_owner.reference_codes
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
	'BILL_STATUS',
	'LD_WO',
	'L&D WO',
	6,
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
		oms_owner.reference_codes
	where
		domain = 'BILL_STATUS'
		and code = 'LD_WO');

insert
	into
	oms_owner.reference_codes
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
	'BILL_STATUS',
	'LD_WO_RDOR',
	'L&D WO/RDOR',
	7,
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
		oms_owner.reference_codes
	where
		domain = 'BILL_STATUS'
		and code = 'LD_WO_RDOR');

insert
	into
	oms_owner.reference_codes
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
	'BILL_STATUS',
	'PAID',
	'Paid',
	8,
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
		oms_owner.reference_codes
	where
		domain = 'BILL_STATUS'
		and code = 'PAID');