insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'EVENT_ID',
	'C',
	'Event Id',
	'SCH_TEMP_OTH',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'128',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'EVENT_ID',
	'C',
	'Event Id',
	'SCH_TEM_BUSI',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'128',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'EVENT_ID',
	'C',
	'Event Id',
	'SCH_TEMP_AGY',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'128',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'OFFENDERBOOKID',
	'C',
	'offenderBookId',
	'SAN_VIO_ORDR',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_VIO_ORDR'
		and parameter_name = 'OFFENDERBOOKID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'ORDERNO',
	'C',
	'orderNo',
	'SAN_VIO_ORDR',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'sentenseSeq'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_VIO_ORDR'
		and parameter_name = 'ORDERNO');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'EVENT_ID',
	'C',
	'Event Id',
	'SAN_VIO_CRT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'OFFENDER_BOOK_ID',
	'C',
	'Offender Book Id',
	'SAN_PROG_OUT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'OFFENDER_BOOK_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'EVENT_ID',
	'C',
	'Event Id',
	'SAN_PROG_OUT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'COURT_EVENT_ID',
	'C',
	'Court Event Id',
	'SAN_PROG_OUT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'COURT_EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'OFFENDER_BOOK_ID',
	'C',
	'Offender Book Id',
	'SAN_COMM_SER',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'OFFENDER_BOOK_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'EVENT_ID',
	'C',
	'Event Id',
	'SAN_COMM_SER',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'COURT_EVENT_ID',
	'C',
	'Court Event Id',
	'SAN_COMM_SER',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'COURT_EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'OFFENDER_BOOK_ID',
	'C',
	'Offender Book Id',
	'SAN_PROG_APP',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'OFFENDER_BOOK_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'EVENT_ID',
	'C',
	'Event Id',
	'SAN_PROG_APP',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'COURT_EVENT_ID',
	'C',
	'Court Event Id',
	'SAN_PROG_APP',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'COURT_EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'OFFENDER_BOOK_ID',
	'C',
	'Offender Book Id',
	'SAN_APP_OUT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'OFFENDER_BOOK_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'EVENT_ID',
	'C',
	'Event Id',
	'SAN_APP_OUT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'EVENT_ID');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'COURT_EVENT_ID',
	'C',
	'Court Event Id',
	'SAN_APP_OUT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'135',
	'eventId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'COURT_EVENT_ID');
