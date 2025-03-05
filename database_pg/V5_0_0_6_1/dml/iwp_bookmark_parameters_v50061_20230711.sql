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
	'OFFENDER_BOOK_ID ',
	'CUS_INT_PART',
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
		bookmark_name = 'CUS_INT_PART'
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
	'RECORD_TYPE',
	'C',
	'Order Type',
	'CUS_INT_PART',
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
		bookmark_name = 'CUS_INT_PART'
		and parameter_name = 'RECORD_TYPE');

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
	'RECORD_ID',
	'C',
	'Order No',
	'CUS_INT_PART',
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
	'recordId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'CUS_INT_PART'
		and parameter_name = 'RECORD_ID');

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
	'OBS_PERIOD_ID',
	'C',
	'Observation Id',
	'OFF_OBS_PERI',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'119',
	'obsPeriodId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFF_OBS_PERI'
		and parameter_name = 'OBS_PERIOD_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters
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
	'OBS_PERIOD_ID',
	'C',
	'Observation Id',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'119',
	'obsPeriodId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'OBS_PERIOD_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters
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
	'OBS_TYPE_VERSION_ID',
	'C',
	'Observation Type Id',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'119',
	'obsTypeVersionId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'OBS_TYPE_VERSION_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters
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
	'OFFENDER_VISIT_ID',
	'C',
	'Offender Visit Id',
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'23',
	'offenderVisitId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'OFFENDER_VISIT_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters
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
	'OFFEN_VISITS',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'23',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFFEN_VISITS'
		and parameter_name = 'OFFENDER_BOOK_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters
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
	'VISIT_OFFENDER_BOOK_ID',
	'C',
	'Visit Offender Id',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'23',
	'visitOffenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'VISIT_OFFENDER_BOOK_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters
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
	'OFFENDER_VISIT_ID',
	'C',
	'Offender Visit Id',
	'OFFN_VISTORS',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'23',
	'offenderVisitId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFFN_VISTORS'
		and parameter_name = 'OFFENDER_VISIT_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters
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
	'OFFENDER_VISIT_ID',
	'C',
	'Offender Visit Id',
	'VISIT_OFFEND',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'23',
	'offenderVisitId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
		and parameter_name = 'OFFENDER_VISIT_ID');

insert
	into
	oms_owner.iwp_bookmark_parameters
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
	'VISIT_OFFEND',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'23',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
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
	'RELEASE_PLAN_ID',
	'C',
	'Release Plan Details',
	'RELEASE_PLAN',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'123',
	'releasePlanId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'RELEASE_PLAN'
		and parameter_name = 'RELEASE_PLAN_ID');