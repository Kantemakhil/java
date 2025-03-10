insert
	into
	MODULE_PRIVILEGES (MODULE_NAME,
	ROLE_ID,
	ACCESS_PRIVILEGE,
	VERIFICATION_FLAG,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG) 
select
	'OCIPENSC',
	444,
	'A',
	'Y',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_PRIVILEGES
	where
		MODULE_NAME = 'OCIPENSC');