delete
from
	iwp_bookmark_parameters ibp
where
	bookmark_name = 'CRT_ACTIONS';

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
	'SENTENCE_SEQ',
	'C',
	'Sentence Sequence',
	'CRT_ACTIONS',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'61',
	'orderNo'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'CRT_ACTIONS'
		and parameter_name = 'SENTENCE_SEQ');

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
	'ORDER_TYPE',
	'C',
	'Order Type',
	'CRT_ACTIONS',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'61',
	'orderType'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'CRT_ACTIONS'
		and parameter_name = 'ORDER_TYPE');

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
	'CRT_ACTIONS',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'61',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'CRT_ACTIONS'
		and parameter_name = 'OFFENDER_BOOK_ID');