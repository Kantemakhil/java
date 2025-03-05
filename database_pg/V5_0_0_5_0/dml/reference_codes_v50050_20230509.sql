insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'APT_CAN_REA',
	'CANC',
	'Cancelled',
	99,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'APT_CAN_REA'
		and code = 'CANC');

insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'APT_CAN_REA',
	'CONF',
	'Conflicting Schedule',
	99,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'APT_CAN_REA'
		and code = 'CONF');

insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'APT_CAN_REA',
	'RATT',
	'Refused to Attend',
	99,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'APT_CAN_REA'
		and code = 'RATT');

insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'CPLAN_ROLE',
	'CPLAN_PCO',
	'Primary Case Officer',
	1,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CPLAN_ROLE'
		and code = 'CPLAN_PCO');

insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'CPLAN_ROLE',
	'CPLAN_SCO',
	'Secondary Case Officer',
	2,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CPLAN_ROLE'
		and code = 'CPLAN_SCO');

insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'CPLAN_ROLE',
	'CPLAN_IC',
	'Indigenous Consultant',
	3,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CPLAN_ROLE'
		and code = 'CPLAN_IC');

insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'CPLAN_ROLE',
	'CPLAN_PO',
	'Planning Officer',
	4,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CPLAN_ROLE'
		and code = 'CPLAN_PO');

insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'CPLAN_ROLE',
	'CPLAN_SPO',
	'Senior Planning Officer',
	5,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CPLAN_ROLE'
		and code = 'CPLAN_SPO');

insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
 select
	'CPLAN_ROLE',
	'CPLAN_IO',
	'Interventions Officer',
	6,
	'Y',
	'N',
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CPLAN_ROLE'
		and code = 'CPLAN_IO');
		
insert
	into
	reference_codes (domain,
	code,
	description,
	list_seq,
	active_flag,
	system_data_flag,
	expired_date,
	new_code,
	parent_code,
	parent_domain,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
select
	'CALC_REASON',
	'ESCP_RECAP',
	'Escape / Recapture',
	99,
	'Y',
	'Y',
	null,
	null,
	null,
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		reference_codes
	where
		domain = 'CALC_REASON'
		and code = 'ESCP_RECAP');		

update
	oms_owner.reference_codes
set
	description = 'Sentence',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	domain = 'USG_CODE'
	and code = 'SENT';

update
	oms_owner.reference_codes
set
	description = 'Booking',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	domain = 'USG_CODE'
	and code = 'BKG';

update
	reference_codes
set
	system_data_flag = 'N',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	domain = 'CALC_REASON'
	and code = 'ESCP_RECAP';

update
	oms_owner.reference_codes
set
	list_seq = 99 ,
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	domain = 'USG_CODE'
	and code = 'SENT';

update
	oms_owner.reference_codes
set
	list_seq = 99 ,
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	domain = 'USG_CODE'
	and code = 'BKG';

update
	oms_owner.reference_codes
set
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp,
	system_data_flag = 'N'
where
	domain = 'USG_CODE'
	and code = 'BKG_REMISS';

update
	oms_owner.reference_codes
set
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp,
	system_data_flag = 'N'
where
	domain = 'USG_CODE'
	and code = 'SENT_REMISS';

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
	'OUTCOMES',
	'ERROR',
	'Entered in Error ',
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
		domain = 'OUTCOMES'
		and code = 'ERROR');

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
	'OUTCOMES',
	'CRT_CAN',
	'Cancelled by Court ',
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
		domain = 'OUTCOMES'
		and code = 'CRT_CAN');
		
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
 SELECT 'KEY_DATES',
'CUST_STATUS',
'Custody Status',
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
		oms_owner.reference_codes
	where
		domain = 'KEY_DATES'
		and code = 'CUST_STATUS');	
		