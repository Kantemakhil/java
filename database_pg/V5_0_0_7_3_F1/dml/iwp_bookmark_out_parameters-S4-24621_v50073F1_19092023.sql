delete
from
	iwp_bookmark_out_parameters
where
	bookmark_name = 'OFFEN_ADDRES'
	and parameter_name = 'PRIMARY' ;

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
	'OFFEN_ADDRES',
	'PRIMARY_FLAG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null,
	'Primary Address flag'
where
	not exists (
	select
		1
	from
		iwp_bookmark_out_parameters
	where
		bookmark_name = 'OFFEN_ADDRES'
		and parameter_name = 'PRIMARY_FLAG');