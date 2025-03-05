insert
	into
	oms_owner.oms_roles
(role_id,
	role_name,
	role_seq,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	role_code,
	parent_role_code,
	seal_flag)
select
	nextval('ROLE_ID'),
	'Delete CUST Orders Conditions',
	1,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'DEL_CUST_ORD',
	null,
	null
where
	not exists (
	select
		1
	from
		oms_roles
	where
		role_code = 'DEL_CUST_ORD');

insert
	into
	oms_owner.oms_roles
(role_id,
	role_name,
	role_seq,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	role_code,
	parent_role_code,
	seal_flag)
select
	nextval('ROLE_ID'),
	'Delete NCUS Orders Conditions',
	1,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'DEL_NCUS_ORD',
	null,
	null
where
	not exists (
	select
		1
	from
		oms_roles
	where
		role_code = 'DEL_NCUS_ORD');

insert
	into
	oms_owner.oms_roles
(role_id,
	role_name,
	role_seq,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	role_code,
	parent_role_code,
	seal_flag)
select
	nextval('ROLE_ID'),
	'Delete Bail Orders Conditions',
	1,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'DEL_BAIL_ORD',
	null,
	null
where
	not exists (
	select
		1
	from
		oms_roles
	where
		role_code = 'DEL_BAIL_ORD');

insert
	into
	oms_owner.oms_roles
(role_id,
	role_name,
	role_seq,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	role_code,
	parent_role_code,
	seal_flag)
select
	nextval('ROLE_ID'),
	'Delete PAR Orders Conditions',
	1,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'DEL_PAR_ORD',
	null,
	null
where
	not exists (
	select
		1
	from
		oms_roles
	where
		role_code = 'DEL_PAR_ORD');

insert
	into
	oms_owner.oms_roles
(role_id,
	role_name,
	role_seq,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	role_code,
	parent_role_code,
	seal_flag)
select
	nextval('ROLE_ID'),
	'Delete Holds/Warrants/Detainer',
	1,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'DEL_HWD',
	null,
	null
where
	not exists (
	select
		1
	from
		oms_roles
	where
		role_code = 'DEL_HWD');