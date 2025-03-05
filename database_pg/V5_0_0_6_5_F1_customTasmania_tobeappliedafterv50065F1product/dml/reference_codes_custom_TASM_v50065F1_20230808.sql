insert
	into
	reference_codes  (domain,
	code,
	description,
	list_seq, 
	active_flag,
	system_data_flag,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select 'REP_REQ_STS',
'VET',
'Vetting',
99,
'Y',
'N',
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
	reference_codes
where
	domain = 'REP_REQ_STS'
	and code = 'VET');
	
	
	
insert
	into
	reference_codes  (domain,
	code,
	description,
	list_seq, 
	active_flag,
	system_data_flag,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,	
	seal_flag)
select 'CHAR_DISP',
'RESINV',
'Returned for Internal Investigation',
99,
'Y',
'N',
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
	reference_codes
where
	domain = 'CHAR_DISP'
	and code = 'RESINV');