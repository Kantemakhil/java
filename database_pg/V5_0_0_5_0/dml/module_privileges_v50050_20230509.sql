delete
from
	MODULE_PRIVILEGES
where
	module_name = 'OCDIPLANDLG';

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
	'OCDIPLAC',
	444,
	'A',
	'Y',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	CURRENT_TIMESTAMP,
	null,
	null
where
	not exists (
	select
		1
	from
		MODULE_PRIVILEGES
	where
		MODULE_NAME = 'OCDIPLAC'
		and role_id = 444);

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
	'OIMCUSTS',
	444,
	'A',
	'Y',
	current_timestamp,
	'',
	null,
	'',
	''
where
	not exists (
	select
		1
	from
		MODULE_PRIVILEGES
	where
		module_name = 'OIMCUSTS');