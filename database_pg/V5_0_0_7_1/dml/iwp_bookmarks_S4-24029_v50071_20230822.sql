insert
	into
	iwp_bookmarks (bookmark_name,
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
	'COMM_SER_DAT',
	'Y',
	'TEXT',
	'COMM_SRV Date',
	'select date(event_date) from v_offender_course_events voce where (case when voce.reference_id is not null then voce.reference_id =? else voce.event_id =? end )',
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
	not exists 
(
	select
		1
	from
		iwp_bookmarks
	where
		bookmark_name = 'COMM_SER_DAT');

insert
	into
	iwp_bookmarks (bookmark_name,
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
	'COM_SRV_TIME',
	'Y',
	'TEXT',
	'COMM_SRV Start Time',
	'select to_char(start_time, ''HH24:MI'') from v_offender_course_events voce where (case when voce.reference_id is not null then voce.reference_id =? else voce.event_id =? end )',
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
	not exists 
(
	select
		1
	from
		iwp_bookmarks
	where
		bookmark_name = 'COM_SRV_TIME');

insert
	into
	iwp_bookmarks (bookmark_name,
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
	'COM_SRV_PROJ',
	'Y',
	'TEXT',
	'Project Description',
	'select description from course_activities ca where crs_acty_id =( select crs_acty_id from v_offender_course_events voce where (case when voce.reference_id is not null then voce.reference_id = ? else voce.event_id =? end ))',
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
		bookmark_name = 'COM_SRV_PROJ');