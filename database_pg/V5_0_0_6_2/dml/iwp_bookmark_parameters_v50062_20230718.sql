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
	'ASSESSMENT_SEQ',
	'C',
	'Assessment Seq',
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'118',
	'assessmentSeq'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFF_ASSESS'
		and parameter_name = 'ASSESSMENT_SEQ');

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
	'OFF_ASSESS',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'118',
	'offenderBookId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFF_ASSESS'
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
	'OFFENDERBOOKID',
	'C',
	'Offender Book Id',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
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
		bookmark_name = 'LEG_NCUS'
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
	'DISPLAYNO',
	'C',
	'Display No',
	'LEG_NCUS',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'displayNo'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_NCUS'
		and parameter_name = 'DISPLAYNO');

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
	'Offender Book Id',
	'LEG_NCUS_CHG',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
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
		bookmark_name = 'LEG_NCUS_CHG'
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
	'DISPLAYNO',
	'C',
	'Display No',
	'LEG_NCUS_CHG',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'displayNo'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_NCUS_CHG'
		and parameter_name = 'DISPLAYNO');

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
	'CHARGEID',
	'C',
	'Charge Id',
	'LEG_NCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'chargeId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_NCHG_DET'
		and parameter_name = 'CHARGEID');

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
	'Offender Book Id',
	'LEG_NCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
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
		bookmark_name = 'LEG_NCHG_DET'
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
	'OFFENCE_ID',
	'C',
	'Offence Id',
	'LEG_NCHG_IND',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'offenceId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_NCHG_IND'
		and parameter_name = 'OFFENCE_ID');

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
	'OFFENCEID',
	'C',
	'Offence Id',
	'LEG_NCHG_CAT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'offenceId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_NCHG_CAT'
		and parameter_name = 'OFFENCEID');

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
	'CODE',
	'C',
	'Charge Code',
	'LEG_NCHG_CAT',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'code'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_NCHG_CAT'
		and parameter_name = 'CODE');

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
	'Offender Book Id',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
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
		bookmark_name = 'LEG_BAIL'
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
	'DISPLAYNO',
	'C',
	'Display No',
	'LEG_BAIL',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'displayNo'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_BAIL'
		and parameter_name = 'DISPLAYNO');

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
	'Offender Book Id',
	'LEG_BAIL_CHG',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
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
		bookmark_name = 'LEG_BAIL_CHG'
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
	'DISPLAYNO',
	'C',
	'Display No',
	'LEG_BAIL_CHG',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'displayNo'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_BAIL_CHG'
		and parameter_name = 'DISPLAYNO');

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
	'Offender Book Id',
	'LEG_BCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
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
		bookmark_name = 'LEG_BCHG_DET'
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
	'CHARGEID',
	'C',
	'Charge Id',
	'LEG_BCHG_DET',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'chargeId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_BCHG_DET'
		and parameter_name = 'CHARGEID');

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
	'Offender Book Id',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
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
		bookmark_name = 'LEG_PAR'
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
	'DISPLAYNO',
	'C',
	'Display No',
	'LEG_PAR',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'displayNo'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_PAR'
		and parameter_name = 'DISPLAYNO');

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
	'Offender Book Id',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
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
		bookmark_name = 'LEG_PAR_AFFE'
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
	'DISPLAYNO',
	'C',
	'Display No',
	'LEG_PAR_AFFE',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'displayNo'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_PAR_AFFE'
		and parameter_name = 'DISPLAYNO');

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
	'Offender Book Id',
	'LEG_PAR_DUR',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
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
		bookmark_name = 'LEG_PAR_DUR'
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
	'DISPLAYNO',
	'C',
	'Display No',
	'LEG_PAR_DUR',
	current_timestamp,
	'OMS_OWNER',
	'T',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	'Y',
	null,
	'61',
	'displayNo'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'LEG_PAR_DUR'
		and parameter_name = 'DISPLAYNO');
