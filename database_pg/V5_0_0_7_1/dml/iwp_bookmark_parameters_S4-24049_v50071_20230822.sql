insert
	into
	oms_owner.iwp_bookmark_parameters (parameter_name,
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
	'U',
	'EVENT_ID',
	'COM_SRV_PROJ',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	null,
	null
where
	not exists 
(
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'COM_SRV_PROJ'
		and parameter_name = 'EVENT_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters (parameter_name,
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
	'REFERENCE_ID',
	'U',
	'REFERENCE_ID',
	'COM_SRV_PROJ',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'COM_SRV_PROJ'
		and parameter_name = 'REFERENCE_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters (parameter_name,
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
	'U',
	'EVENT_ID',
	'COMM_SER_DAT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	null,
	null
where
	not exists 
(
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'COMM_SER_DAT'
		and parameter_name = 'EVENT_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters (parameter_name,
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
	'REFERENCE_ID',
	'U',
	'REFERENCE_ID',
	'COMM_SER_DAT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'COMM_SER_DAT'
		and parameter_name = 'REFERENCE_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters (parameter_name,
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
	'U',
	'EVENT_ID',
	'COM_SRV_TIME',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	null,
	null
where
	not exists 
(
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'COM_SRV_TIME'
		and parameter_name = 'EVENT_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters (parameter_name,
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
	'REFERENCE_ID',
	'U',
	'REFERENCE_ID',
	'COM_SRV_TIME',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'COM_SRV_TIME'
		and parameter_name = 'REFERENCE_ID');