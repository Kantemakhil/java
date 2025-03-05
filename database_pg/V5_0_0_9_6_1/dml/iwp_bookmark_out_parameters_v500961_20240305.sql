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
	'ORD_CRT_ACT',
	'LEG_ACCOMM',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Actions Comment'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'LEG_ACCOMM');

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
	'ORD_CRT_ACT',
	'LEG_ACHBOD',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Actions Hearing Body'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'LEG_ACHBOD');

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
	'ORD_CRT_ACT',
	'LEG_ACODATE',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Actions Outcome Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'LEG_ACODATE');

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
	'ORD_CRT_ACT',
	'LEG_ACPROC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Actions Proceeding'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'LEG_ACPROC');

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
	'ORD_CRT_ACT',
	'LEG_ACRESP',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Actions Team Responsibility'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'LEG_ACRESP');

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
	'ORD_CRT_ACT',
	'LEG_ACSDAT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Actions Start Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'LEG_ACSDAT');

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
	'ORD_CRT_ACT',
	'LEG_ACSRES',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Actions Staff Responsible'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'LEG_ACSRES');

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
	'ORD_CRT_ACT',
	'LEG_ACSTAT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Actions Status'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'LEG_ACSTAT');

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
	'ORD_CRT_ACT',
	'LEG_CAPURTO',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Court Actions Pursuant To'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'LEG_CAPURTO');

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
	'ORD_CRT_ACT',
	'LEG_CARECDOC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Recommendation Documents'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'LEG_CARECDOC');
		
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
	'OFF_IN_CHARG',
	'OIC_CHEXID',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'External Investigation External ID'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_IN_CHARG'
		and parameter_name = 'OIC_CHEXID');

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
	'OFF_IN_CHARG',
	'OIC_CHEXCD',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'External Investigation Contact Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_IN_CHARG'
		and parameter_name = 'OIC_CHEXCD');

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
	'OFF_IN_CHARG',
	'OIC_CHEXCT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'External Investigation Contact Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_IN_CHARG'
		and parameter_name = 'OIC_CHEXCT');

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
	'OFF_IN_CHARG',
	'OIC_CHEXSTA',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'External Investigation Status '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_IN_CHARG'
		and parameter_name = 'OIC_CHEXSTA');

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
	'OFF_IN_CHARG',
	'OIC_CHEXCOM',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'External Investigation Comment '
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_IN_CHARG'
		and parameter_name = 'OIC_CHEXCOM');

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
	'OFF_IN_CHARG',
	'OIC_CHEXCHA',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'External Investigation Charge'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_IN_CHARG'
		and parameter_name = 'OIC_CHEXCHA');

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
	'OFF_IN_CHARG',
	'OIC_CHEXCHADES',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'External Investigation Charge Description'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFF_IN_CHARG'
		and parameter_name = 'OIC_CHEXCHADES');		