insert
	into
	iwp_bookmarks
(bookmark_name,
	active_flag,
	bookmark_type,
	description,
	sql_text,
	date_created,
	user_created,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	expiry_date,
	sql_verified_flag,
	seal_flag)
select
	'ACP_DATE',
	'Y',
	'TEXT',
	'Acp Event Date',
	'select date(event_date)   from offender_course_attendances oca where event_id =?',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'N',
	null
where
	not exists (
	select
		1
	from
		iwp_bookmarks
	where
		bookmark_name = 'ACP_DATE');

insert
	into
	iwp_bookmarks
(bookmark_name,
	active_flag,
	bookmark_type,
	description,
	sql_text,
	date_created,
	user_created,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	expiry_date,
	sql_verified_flag,
	seal_flag)
select
	'AP_STARTTIME',
	'Y',
	'TEXT',
	'Start time',
	'select to_char(start_time, ''HH24:MI'') from offender_course_attendances oca where event_id =? ',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'N',
	null
where
	not exists (
	select
		1
	from
		iwp_bookmarks
	where
		bookmark_name = 'AP_STARTTIME');

insert
	into
	iwp_bookmarks
(bookmark_name,
	active_flag,
	bookmark_type,
	description,
	sql_text,
	date_created,
	user_created,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	expiry_date,
	sql_verified_flag,
	seal_flag)
select
	'ACP_SUB_TYPE',
	'Y',
	'TEXT',
	'Type Description',
	'select event_sub_type from offender_course_attendances oca where event_id =?',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'N',
	null
where
	not exists (
	select
		1
	from
		iwp_bookmarks
	where
		bookmark_name = 'ACP_SUB_TYPE');