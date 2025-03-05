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
	'ORDERNO',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'ORDERNO'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'ORDERNO');

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
	'CRED',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'CRED'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'CRED');

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
	'OFFENDER_SENT_CONDITION_ID',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'OFFENDER_SENT_CONDITION_ID'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'OFFENDER_SENT_CONDITION_ID');

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
	'CONDITIONLENGTH',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'CONDITIONLENGTH'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'CONDITIONLENGTH');

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
	'UPW_LINE',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Line'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_LINE');

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
	'UPW_CASNO',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Case Number'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_CASNO');

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
	'UPW_COURT',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Court'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_COURT');

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
	'UPW_LEGOR',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Legal Order'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_LEGOR');

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
	'UPW_STDAT',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Start Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_STDAT');

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
	'UPW_ENDAT',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service End Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_ENDAT');

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
	'UPW_LENG',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Length'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_LENG');

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
	'UPW_CAT',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Category'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_CAT');

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
	'UPW_CRED',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Credited'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_CRED');

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
	'UPW_REMAIN',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Remaining'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_REMAIN');

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
	'UPW_STAT',
	'COMM_UPW',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Status'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'COMM_UPW'
		and parameter_name = 'UPW_STAT');

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
	'UPW_PROJCOD',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Project Code'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
		and parameter_name = 'UPW_PROJCOD');

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
	'UPW_PROJDESC',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Project Description'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
		and parameter_name = 'UPW_PROJDESC');

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
	'UPW_PROJTEAM',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Project Team'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
		and parameter_name = 'UPW_PROJTEAM');

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
	'UPW_PROJMAXCAP',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Project Max Capacity'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
		and parameter_name = 'UPW_PROJMAXCAP');

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
	'UPW_PROJSTDAT',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Project Start Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
		and parameter_name = 'UPW_PROJSTDAT');

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
	'UPW_PROJTRVFAR',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Project Travel Fare'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
		and parameter_name = 'UPW_PROJTRVFAR');

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
	'UPW_PROJTRVTI',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Project Travel Time'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
		and parameter_name = 'UPW_PROJTRVTI');

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
	'UPW_PROJENDAT',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Project End Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
		and parameter_name = 'UPW_PROJENDAT');

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
	'UPW_PROJSTAT',
	'UPW_ASSIGN',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Project Status'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_ASSIGN'
		and parameter_name = 'UPW_PROJSTAT');

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
	'UPW_CRDDBCR',
	'UPW_CREDIT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Credit Debit/Credit'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_CREDIT'
		and parameter_name = 'UPW_CRDDBCR');

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
	'UPW_CRDREA',
	'UPW_CREDIT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Credit Reason'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_CREDIT'
		and parameter_name = 'UPW_CRDREA');

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
	'UPW_CRDCOM',
	'UPW_CREDIT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Credit Comment'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_CREDIT'
		and parameter_name = 'UPW_CRDCOM');

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
	'UPW_CRDCREA',
	'UPW_CREDIT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Credit Created by'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_CREDIT'
		and parameter_name = 'UPW_CRDCREA');

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
	'UPW_CRDDAT',
	'UPW_CREDIT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Credit Date'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_CREDIT'
		and parameter_name = 'UPW_CRDDAT');

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
	'UPW_CRDADJ',
	'UPW_CREDIT',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null,
	'Community Service Credit Adjustment'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'UPW_CREDIT'
		and parameter_name = 'UPW_CRDADJ');
