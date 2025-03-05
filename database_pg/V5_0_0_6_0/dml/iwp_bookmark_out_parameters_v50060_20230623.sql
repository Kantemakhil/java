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
	'LEG_CUSTYPE',
	'LEG_CUS_CHRG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'LEG_CUSTYPE'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'LEG_CUS_CHRG'
		and parameter_name = 'LEG_CUSTYPE');