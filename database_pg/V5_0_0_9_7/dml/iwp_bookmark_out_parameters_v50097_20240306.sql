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
	'VISIT_OFFEND',
	'COMMENT_TEXT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Comment from the Visiting Offenders Tab based on the record selected in the Create or View Existing Time Slot Card/Block'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'VISIT_OFFEND'
		and parameter_name = 'COMMENT_TEXT');

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
	'COMMENT_TEXT',
	'OFF_OBS_CHEC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'COMMENT_TEXT '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_OBS_CHEC'
		and parameter_name = 'COMMENT_TEXT');

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
	'CRT_REP_PROP',
	'LEG_CRPCON',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Report Proposed Condition'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_REP_PROP'
		and parameter_name = 'LEG_CRPCON');

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
	'CRT_REP_PROP',
	'LEG_CRPCLN',
	'2024-02-27 11:08:57.359',
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Report Proposed Condition Length'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_REP_PROP'
		and parameter_name = 'LEG_CRPCLN');

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
	'CRT_REP_PROP',
	'LEG_CRPCCTY',
	'2024-02-27 11:08:57.359',
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Report Proposed Condition Type'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_REP_PROP'
		and parameter_name = 'LEG_CRPCCTY');

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
	'CRT_REP_PROP',
	'LEG_CRPCCOM',
	'2024-02-27 11:08:57.359',
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Report Proposed Condition Comment'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'CRT_REP_PROP'
		and parameter_name = 'LEG_CRPCCOM');