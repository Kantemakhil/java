insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'ACTIVITY',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Related To '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'ACTIVITY');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CELL_CNDITNS',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Related To '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'CELL_CNDITNS');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'COM_DET_CAT',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Related To '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'COM_DET_CAT');

insert
	into
	iwp_bookmark_out_parameters
(parameter_name,
	bookmark_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'NOT_IN_CELL',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Related To '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'NOT_IN_CELL');

insert
	into
	iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CRT_RPT_CHRG',
	'LEG_NCODPLEA',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'LEG_NCODPLEA'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_RPT_CHRG'
		and parameter_name = 'LEG_NCODPLEA');

insert
	into
	iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CRT_RPT_CHRG',
	'LEG_NCODINDT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'LEG_NCODINDT'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_RPT_CHRG'
		and parameter_name = 'LEG_NCODINDT');

insert
	into
	iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CRT_RPT_CHRG',
	'LEG_NCODRAN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'LEG_NCODRAN'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_RPT_CHRG'
		and parameter_name = 'LEG_NCODRAN');

insert
	into
	iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CRT_RPT_CHRG',
	'LEG_NCODPART',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'LEG_NCODPART'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_RPT_CHRG'
		and parameter_name = 'LEG_NCODPART');

insert
	into
	iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CRT_RPT_CHRG',
	'LEG_CUSINCD',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'LEG_CUSINCD'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_RPT_CHRG'
		and parameter_name = 'LEG_CUSINCD');

insert
	into
	iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CRT_RPT_CHRG',
	'LEG_NCODCAT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'LEG_NCODCAT'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_RPT_CHRG'
		and parameter_name = 'LEG_NCODCAT');

insert
	into
	iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'CRT_RPT_CHRG',
	'LEG_NCODSEV',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'LEG_NCODSEV'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_RPT_CHRG'
		and parameter_name = 'LEG_NCODSEV');

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'ACTIVITY',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'OFF_OBS_CHEC'
	and
	parameter_name = 'ACTIVITY';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'CELL_CNDITNS',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'OFF_OBS_CHEC'
	and
	parameter_name = 'CELL_CNDITNS';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'COM_DET_CAT',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'OFF_OBS_CHEC'
	and
	parameter_name = 'COM_DET_CAT';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'NOT_IN_CELL',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	bookmark_name = 'OFF_OBS_CHEC'
	and
	parameter_name = 'NOT_IN_CELL';