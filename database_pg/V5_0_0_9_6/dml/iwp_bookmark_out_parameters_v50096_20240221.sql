delete
from
	iwp_bookmark_out_parameters ibop
where
	bookmark_name = 'SCHEDULES';

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
	'SCHEDULES',
	'SCH_TARELD1',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Release Date (MM-DD-YYYY)'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TARELD1');

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
	'SCHEDULES',
	'SCH_TARELD2',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Release Date (DD-MM-YYYY)'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TARELD2');

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
	'SCHEDULES',
	'SCH_TARELD3',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Release Date (YYYY-MM-DD)'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TARELD3');

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
	'SCHEDULES',
	'SCH_TAAPDT1',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Application Date  (MM-DD-YYYY)'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TAAPDT1');

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
	'SCHEDULES',
	'SCH_TAAPDT2',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Application Date  (DD-MM-YYYY)'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TAAPDT2');

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
	'SCHEDULES',
	'SCH_TAAPDT3',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Application Date  (YYYY-MM-DD)'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TAAPDT3');

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
	'SCHEDULES',
	'SCH_TARELT',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Release Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TARELT');

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
	'SCHEDULES',
	'SCH_TASEVENT',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Event'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TASEVENT');

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
	'SCHEDULES',
	'SCH_TAREAS',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Reason '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TAREAS');

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
	'SCHEDULES',
	'SCH_TASTAT',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Status '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TASTAT');

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
	'SCHEDULES',
	'SCH_TAAPTM',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Application Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TAAPTM');

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
	'SCHEDULES',
	'SCH_TARETD1',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Return Date (MM-DD-YYYY)'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TARETD1');

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
	'SCHEDULES',
	'SCH_TARETD2',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Return Date  (DD-MM-YYYY)'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TARETD2');

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
	'SCHEDULES',
	'SCH_TARETD3',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Return Date  (YYYY-MM-DD)'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TARETD3');

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
	'SCHEDULES',
	'SCH_TARETT',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Return Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TARETT');

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
	'SCHEDULES',
	'SCH_TADAYO',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Days Out '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TADAYO');

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
	'SCHEDULES',
	'SCH_TAHRO',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Hours Out '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TAHRO');

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
	'SCHEDULES',
	'SCH_TACOM',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Comment '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TACOM');

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
	'SCHEDULES',
	'SCH_TAESC',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Escort '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TAESC');

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
	'SCHEDULES',
	'SCH_TATRANS',
	current_timestamp,
	'OMS_OWNER',
	null,
	'OMS_OWNER',
	null,
	'Transport'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCHEDULES'
		and parameter_name = 'SCH_TATRANS');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGLOC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Agency Location'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGLOC');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGCPER',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Contact Person'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGCPER');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGSUI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Suite Number'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGSUI');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGSTR',
	current_timestamp,
	'OMS_OWNER',
	null,
	null ,
	null,
	'Street Information'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGSTR');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGCIT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'City'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGCIT');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGSTA',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'State'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGSTA');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGPOST',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Postcode'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGPOST');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGCOU',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Country'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGCOU');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGPF',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Phone Format'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGPF');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGEX',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Extension '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGEX');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGPN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Phone Number'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGPN');

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
	'SCH_TEMP_AGY',
	'SCH_TAAGPT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Phone Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_AGY'
		and parameter_name = 'SCH_TAAGPT');

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
	'SCH_TEM_BUSI',
	'SCH_TABLOC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Agency Location'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLOC');

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
	'SCH_TEM_BUSI',
	'SCH_TABLCP',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Contact Person'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLCP');

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
	'SCH_TEM_BUSI',
	'SCH_TABLSUI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Suite Number '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLSUI');

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
	'SCH_TEM_BUSI',
	'SCH_TABLSTR',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Street Information'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLSTR');

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
	'SCH_TEM_BUSI',
	'SCH_TABLCIT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'City'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLCIT');

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
	'SCH_TEM_BUSI',
	'SCH_TABLSTA',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'State'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLSTA');

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
	'SCH_TEM_BUSI',
	'SCH_TABLCOU',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Country'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLCOU');

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
	'SCH_TEM_BUSI',
	'SCH_TABLPOST',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Postcode'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLPOST');

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
	'SCH_TEM_BUSI',
	'SCH_TABLPT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Phone Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLPT');

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
	'SCH_TEM_BUSI',
	'SCH_TABLPN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Phone Number'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLPN');

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
	'SCH_TEM_BUSI',
	'SCH_TABLEX',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Extension'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLEX');

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
	'SCH_TEM_BUSI',
	'SCH_TABLPF',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Phone Format '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEM_BUSI'
		and parameter_name = 'SCH_TABLPF');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLOC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Agency Location'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLOC');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLCP',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Contact Person'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLCP');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLSUI',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Suite Number'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLSUI');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLSTR',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Street Information'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLSTR');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLCIT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'City '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLCIT');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLSTA',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'State'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLSTA');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLCOU',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Country'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLCOU');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLPOST',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Postcode '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLPOST');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLPT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Phone Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLPT');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLPN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Phone Number'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLPN');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLEX',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Extension'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLEX');

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
	'SCH_TEMP_OTH',
	'SCH_TAOLPF',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Phone Format '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SCH_TEMP_OTH'
		and parameter_name = 'SCH_TAOLPF');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_ORDR',
	'CRT_NO',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations No.'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_ORDR'
		and parameter_name = 'CRT_NO');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_ORDR',
	'CRT_MAT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Matter '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_ORDR'
		and parameter_name = 'CRT_MAT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_ORDR',
	'CRT_TYPE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_ORDR'
		and parameter_name = 'CRT_TYPE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_ORDR',
	'CRT_COM_DATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Commence Date '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_ORDR'
		and parameter_name = 'CRT_COM_DATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_ORDR',
	'CRT_EXP_DATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Expiry Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_ORDR'
		and parameter_name = 'CRT_EXP_DATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_ORDR',
	'CRT_SANC_COUNT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Sanction Count'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_ORDR'
		and parameter_name = 'CRT_SANC_COUNT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_ORDR',
	'CRT_ADSANC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Adjourned Sanctions '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_ORDR'
		and parameter_name = 'CRT_ADSANC');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_HEARRSN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Hearing Reason'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_HEARRSN');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_DATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_DATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_TIME',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_TIME');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_COURT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Court'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_COURT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_APPTYPE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Appearance Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_APPTYPE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_COMMENT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Comment'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_COMMENT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_RECSANCOUNT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Recommended Sanction Count '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_RECSANCOUNT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_RECREWCOU',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Recommended Reward Count'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_RECREWCOU');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_MATT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Matter(s) '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_MATT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_ADDCOUCOMM',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Additional Counts Comment'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_ADDCOUCOMM');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_CANCELREAS',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Cancel Reason'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_CANCELREAS');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_VIO_CRT',
	'CRT_CE_CANCEL',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations Cancel '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_VIO_CRT'
		and parameter_name = 'CRT_CE_CANCEL');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_DATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Date '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_DATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_STARTTIME',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Start Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_STARTTIME');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_ENDTIME',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  End Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_ENDTIME');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_OUTCOME',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Outcome'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_OUTCOME');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_SCHTYPE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Schedule Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_SCHTYPE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_SCHSUBTYPE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Schedule Sub Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_SCHSUBTYPE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_LOC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Location'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_LOC');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_RECSANCREWCOUNT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Rec Sanctions Rewards Count'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_RECSANCREWCOUNT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_COUNTTYPE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Count Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_COUNTTYPE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_ADJ',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Adjourned'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_ADJ');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_LINKCOEV',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Link Court Event'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_LINKCOEV');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_EVDATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Court Event Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_EVDATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_APP_OUT',
	'CRT_APPOUT_APPCOMM',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  comment'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_APP_OUT'
		and parameter_name = 'CRT_APPOUT_APPCOMM');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_DATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_DATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_STARTTIME',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Start Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_STARTTIME');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_ENDTIME',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  End Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_ENDTIME');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_ATT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Attendance'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_ATT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_PROG',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Program '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_PROG');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_OCCODE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Occurrence Code '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_OCCODE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_RECSANCREWCOUNT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Recommended Sanctions Rewards Count'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_RECSANCREWCOUNT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_COUNTTYPE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Count Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_COUNTTYPE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_ADJ',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Adjourned '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_ADJ');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_LINKCOEV',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Link Court Event'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_LINKCOEV');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_COEVDATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Court Event Date '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_COEVDATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_OUT',
	'CRT_PO_COMMENT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  comment'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_OUT'
		and parameter_name = 'CRT_PO_COMMENT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_TYPE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Type '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_TYPE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_DATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_DATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_STARTTIME',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Start Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_STARTTIME');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_ENDTIME',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  End Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_ENDTIME');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_ATT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Attendance'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_ATT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_RECSANCREWCOUNT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Recommended Sanctions Rewards Count'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_RECSANCREWCOUNT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_COUNTTYPE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Count Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_COUNTTYPE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_ADJ',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Adjourned '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_ADJ');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_LINKCOEV',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Link Court Event '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_LINKCOEV');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_COEVDATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Court Event Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_COEVDATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_PROG_APP',
	'CRT_PA_COMMENT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Comment'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_PROG_APP'
		and parameter_name = 'CRT_PA_COMMENT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_PROJDESC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Project Description '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_PROJDESC');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_DATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_DATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_STARTTIME',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Start Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_STARTTIME');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_ENDTIME',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  End Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_ENDTIME');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_ATT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Attendance'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_ATT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_RECSANCREWCOUNT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Recommended Sanctions Rewards Count'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_RECSANCREWCOUNT');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_COUNTTYPE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Count Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_COUNTTYPE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_ADJ',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Adjourned '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_ADJ');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_LINKCOEV',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Link Court Event'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_LINKCOEV');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_COEVDATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Court Event Date '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_COEVDATE');

insert
	into
	oms_owner.iwp_bookmark_out_parameters
(bookmark_name,
	parameter_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	parameter_desc)
select
	'SAN_COMM_SER',
	'CRT_CS_COMMENT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Sanctions and Violations  Comment'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'SAN_COMM_SER'
		and parameter_name = 'CRT_CS_COMMENT');
