update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Address line 1 (Street Number, Street Name)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ADDRESS_TYPE1'
	and bookmark_name = 'OFFEN_ADDRES';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Address line 2 (City, State)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ADDRESS_TYPE2'
	and bookmark_name = 'OFFEN_ADDRES';

update
	iwp_bookmark_out_parameters
set
	parameter_desc = 'Address line 3 (Country, Postal Code)',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	parameter_name = 'ADDRESS_TYPE3'
	and bookmark_name = 'OFFEN_ADDRES';

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
	'OFFEN_ADDRES',
	'ADDRESS_TYPE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Address Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_ADDRES'
		and parameter_name = 'ADDRESS_TYPE');

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
	'OFFEN_ADDRES',
	'FROM_DATE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'From date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_ADDRES'
		and parameter_name = 'FROM_DATE');

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
	'OFFEN_ADDRES',
	'TO_DATE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'To Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_ADDRES'
		and parameter_name = 'TO_DATE');
