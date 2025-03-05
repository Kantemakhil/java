insert
	into
	oms_modules (module_name,
	description,
	module_type,
	print_format_code,
	preview_flag,
	default_copy,
	appln_code,
	help_directory,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag,
	output_type,
	process_workflow,
	user_task,
	dynamic_form,
	ins_dashboard)
 select
	'OCUINCWP',
	'Offender Weapons',
	'SCREEN',
	null,
	null,
	null,
	'OMS4',
	null,
	CURRENT_TIMESTAMP,
	'OMS_OWNER' ,
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null,
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		oms_modules
	where
		module_name = 'OCUINCWP');