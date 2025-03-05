insert
	into
	legal_custody_statuses(status_code,
	status_description,
	status_rank,
	intake_flag,
	release_flag,
	always_display_flag,
	active_flag,
	expiry_date,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
SELECT 'SENT',
'Sentenced',
1,
'N',
'N',
'N',
'Y',
null,
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM legal_custody_statuses WHERE status_code = 'SENT');

insert
	into
	legal_custody_statuses(status_code,
	status_description,
	status_rank,
	intake_flag,
	release_flag,
	always_display_flag,
	active_flag,
	expiry_date,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
 SELECT 'REM',
'Remanded',
2,
'N',
'N',
'Y',
'Y',
null,
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM legal_custody_statuses WHERE status_code = 'REM');

insert
	into
	legal_custody_statuses(status_code,
	status_description,
	status_rank,
	intake_flag,
	release_flag,
	always_display_flag,
	active_flag,
	expiry_date,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
 SELECT 'APP',
'Awaiting Appeal',
3,
'N',
'N',
'N',
'Y',
null,
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM legal_custody_statuses WHERE status_code = 'APP');

insert
	into
	legal_custody_statuses(status_code,
	status_description,
	status_rank,
	intake_flag,
	release_flag,
	always_display_flag,
	active_flag,
	expiry_date,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
 SELECT 'DEPORT',
'Awaiting Deportation',
4,
'N',
'N',
'N',
'Y',
null,
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM legal_custody_statuses WHERE status_code = 'DEPORT');

insert
	into
	legal_custody_statuses(status_code,
	status_description,
	status_rank,
	intake_flag,
	release_flag,
	always_display_flag,
	active_flag,
	expiry_date,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
 SELECT 'POST_SENT',
'Post-Sentence Warrant/Order',
5,
'N',
'N',
'N',
'Y',
null,
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM legal_custody_statuses WHERE status_code = 'POST_SENT');


insert
	into
	legal_custody_statuses(status_code,
	status_description,
	status_rank,
	intake_flag,
	release_flag,
	always_display_flag,
	active_flag,
	expiry_date,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
 SELECT 'NO_STATUS',
'No Custody Status',
6,
'Y',
'N',
'N',
'Y',
null,
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM legal_custody_statuses WHERE status_code = 'NO_STATUS');

insert
	into
	legal_custody_statuses(status_code,
	status_description,
	status_rank,
	intake_flag,
	release_flag,
	always_display_flag,
	active_flag,
	expiry_date,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
 SELECT 'REL',
'Released',
99,
'N',
'Y',
'N',
'Y',
null,
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null 
 WHERE NOT EXISTS (SELECT 1 FROM legal_custody_statuses WHERE status_code = 'REL');
 
