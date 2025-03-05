insert
	into
	OMS_OWNER.MODULE_PRIVILEGES(MODULE_NAME,
	ROLE_ID,
	ACCESS_PRIVILEGE,
	VERIFICATION_FLAG,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
select
	'OIUREPIN',
	444,
	'A',
	'Y',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		OMS_OWNER.MODULE_PRIVILEGES
	where
		MODULE_NAME = 'OIUREPIN'
		and role_id = 444);
