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
	'CRT_ESDATE',
	'Y',
	'COMP',
	'Court event Start date',
	'select to_char(start_time, ''HH24:MI'') from court_events  where event_id = ?',
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
		bookmark_name = 'CRT_ESDATE');

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
	'CRT_EDATE',
	'Y',
	'COMP',
	'Court event Date',
	'select date(event_date) from court_events  where event_id = ?',
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
		bookmark_name = 'CRT_EDATE');

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
	'CRT_NAME',
	'Y',
	'COMP',
	'Court Name',
	'select
			al.description
		from
			agency_locations al
		where
			agency_location_type = ''CRT''
			and al.agy_loc_id not in (''TRN'', ''OUT'')
				and al.agy_loc_id = (select agy_loc_id from court_events  where event_id = ?)',
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
		bookmark_name = 'CRT_NAME');