insert
	into
         module_call_form_relation
(call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OIINAMES',
	'OIRREPORT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists(
	select
		1
	from
		module_call_form_relation
	where
		call_form = 'OIINAMES'
		and module_name = 'OIRREPORT');