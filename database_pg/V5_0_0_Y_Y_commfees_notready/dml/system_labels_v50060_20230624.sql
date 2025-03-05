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
	'OCUBADJS',
	'ocubadjs.adjustamount',
	'Adjust Amount',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.adjustamount');

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
	'OCUBADJS',
	'ocubadjs.adjustdate',
	'Adjust Date',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.adjustdate');

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
	'OCUBADJS',
	'ocubadjs.adjustedbillamount',
	'Adjusted Bill Amount',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.adjustedbillamount');

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
	'OCUBADJS',
	'ocubadjs.adjustmenttypemustbeentered',
	'Adjustment Type must be entered',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.adjustmenttypemustbeentered');

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
	'OCUBADJS',
	'cubadjs.amountenteredcannotbegreaterthanthecurrentadjustedbillamount',
	'Amount entered cannot be greater than the current Adjusted Bill Amount.',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'cubadjs.amountenteredcannotbegreaterthanthecurrentadjustedbillamount');

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
	'OCUBADJS',
	'ocubadjs.amountmustbeentered',
	'Amount must be entered',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.amountmustbeentered');

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
	'OCUBADJS',
	'ocubadjs.billadjustments',
	'Bill Adjustments',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.billadjustments');

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
	'OCUBADJS',
	'ocubadjs.billamount',
	'Bill Amount',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.billamount');

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
	'OCUBADJS',
	'ocubadjs.billcannotbeadjustedbelow',
	'Bill cannot be adjusted below $0.00.',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.billcannotbeadjustedbelow');

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
	'OCUBADJS',
	'ocubadjs.billdetails',
	'Bill Details',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.billdetails');

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
	'OCUBADJS',
	'ocubadjs.billnumber',
	'Bill Number',
	'LABEL',
	current_timestamp,
	'OMS_OWNER', 
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.billnumber');

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
	'OCUBADJS',
	'ocubadjs.comment',
	'Comment',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.comment');

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
	'OCUBADJS',
	'ocubadjs.comments',
	'Comments',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',	
	null
where
	not exists 
 (
	select
		1
	from
		system_labels
	where
		module_name = 'OCUBADJS'
		and msg_key = 'ocubadjs.comments');

