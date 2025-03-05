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
	'OFFENDER_SENT_CONDITION_ID',
	'C',
	'Offender Sent Condition Id',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'42',
	'offenderSentConditionId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'OFFENDER_SENT_CONDITION_ID');

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
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'42',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'COMM_UPW'
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
	'OFFENDER_BOOK_ID',
	'C',
	'Offender Book Id',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'41',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
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
	'OFFENDER_SENT_CONDITION_ID',
	'C',
	'Offender Sent Condition Id',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'41',
	'offenderSentConditionId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
		and parameter_name = 'OFFENDER_SENT_CONDITION_ID');

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
	'UPW_CREDIT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'44',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'UPW_CREDIT'
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
	'OFFENDER_SENT_CONDITION_ID',
	'C',
	'Offender Sent Condition Id',
	'UPW_CREDIT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'44',
	'offenderSentConditionId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'UPW_CREDIT'
		and parameter_name = 'OFFENDER_SENT_CONDITION_ID');
