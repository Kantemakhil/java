update
	reference_codes
set
	parent_code = 'POS',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	domain = 'SUB_TES_RSLT'
	and code = 'P';
	
update
	reference_codes
set
	system_data_flag = 'Y',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	domain = 'WRK_FLW_ACT'
	and code = 'VER';	


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
	'WRK_FLW_ST',
	'PRO',
	'In Progress',
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
		domain = 'WRK_FLW_ST'
		and code = 'PRO');
		
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
	'WRK_FLW_ST',
	'COMP',
	'Complete',
	2,
	'Y',
	'Y',
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
		domain = 'WRK_FLW_ST'
		and code = 'COMP');

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
	'WRK_FLW_ST',
	'REJ',
	'Rejected',
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
		domain = 'WRK_FLW_ST'
		and code = 'REJ');		

--
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
	'REPINCTYP',
	'AST',
	'Assault',
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
		domain = 'REPINCTYP'
		and code = 'AST');

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
	'REPINCTYP',
	'FHT',
	'Fight',
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
		domain = 'REPINCTYP'
		and code = 'FHT');

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
	'REPINCTYP',
	'BFC',
	'Body Fluid Contact',
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
		domain = 'REPINCTYP'
		and code = 'BFC');

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
	'REPINCTYP',
	'SAS',
	'Sexual Assault',
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
		domain = 'REPINCTYP'
		and code = 'SAS');

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
	'REPINCTYP',
	'PYI',
	'Physical Injury Sustained',
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
		domain = 'REPINCTYP'
		and code = 'PYI');

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
	'REPINCTYP',
	'RMA',
	'Receive Medical Attention',
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
		domain = 'REPINCTYP'
		and code = 'RMA');

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
	'REPINCTYP',
	'OMF',
	'Overnight Stay in Med. Fac.',
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
		domain = 'REPINCTYP'
		and code = 'OMF');

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
	'REPINCTYP',
	'OMC',
	'Ongoing Medical Care',
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
		domain = 'REPINCTYP'
		and code = 'OMC');

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
	'REPINCTYP',
	'WHS',
	'Workplace Health & Safety',
	9,
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
		domain = 'REPINCTYP'
		and code = 'WHS');



