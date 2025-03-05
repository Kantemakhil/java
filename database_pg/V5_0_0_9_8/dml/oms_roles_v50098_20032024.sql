insert
	into
	oms_roles
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
	'Delete Document',
	999,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	'DEL_EDOC',
	null,
	null
where
	not exists (
	select
		1
	from
		oms_roles
	where
		role_code = 'DEL_EDOC');

insert
	into
	oms_roles
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
	'Delete All Elite Doc role ',
	999,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	'ENH_DEL_EDOC',
	null,
	null
where
	not exists (
	select
		1
	from
		oms_roles
	where
		role_code = 'ENH_DEL_EDOC');
