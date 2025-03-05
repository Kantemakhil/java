insert
	into
	OMS_OWNER.legal_settings(code,
	description,
	value,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'SENTCALWARNFLAG',
	'Display Warning If Sentence Calculation Is Offline',
	'YES',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		OMS_OWNER.legal_settings
	where
		code = 'SENTCALWARNFLAG');