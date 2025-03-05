insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OIDCNOTE',
	'oidcnote.datecannotbebeforetheoffenderbookingbegindate',
	'Date cannot be before the offender booking begin date',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIDCNOTE'
		and msg_key = 'oidcnote.datecannotbebeforetheoffenderbookingbegindate');