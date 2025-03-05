insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMAGENC',
	'OUMAGENC',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMAGENC'
		and module_name = 'OUMAGENC');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMAGENC',
	'OIDOMAIL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMAGENC'
		and module_name = 'OIDOMAIL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUCORPT',
	'OIDOMAIL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCORPT'
		and module_name = 'OIDOMAIL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCDADDRE',
	'OIDOMAIL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDADDRE'
		and module_name = 'OIDOMAIL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCDOAPOP',
	'OIDOMAIL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDOAPOP'
		and module_name = 'OIDOMAIL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCDCCONT',
	'OIDOMAIL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDCCONT'
		and module_name = 'OIDOMAIL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCDGNUMB',
	'OIDOMAIL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDGNUMB'
		and module_name = 'OIDOMAIL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUCLOFF',
	'OIDOMAIL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCLOFF'
		and module_name = 'OIDOMAIL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OIUIWPVE',
	'OIDOWREL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUIWPVE'
		and module_name = 'OIDOWREL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUNOTCM',
	'OIDOWREL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUNOTCM'
		and module_name = 'OIDOWREL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUTASAT',
	'OIDOWREL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUTASAT'
		and module_name = 'OIDOWREL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCDTWORK',
	'OIDOWREL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDTWORK'
		and module_name = 'OIDOWREL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCMSOSCH',
	'OIDOWREL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSOSCH'
		and module_name = 'OIDOWREL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUCORPT',
	'OUMAGENC',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCORPT'
		and module_name = 'OUMAGENC');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCDADDRE',
	'OUMAGENC',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDADDRE'
		and module_name = 'OUMAGENC');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMPERSO',
	'OUMUCREAT',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMPERSO'
		and module_name = 'OUMUCREAT');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMUSERS',
	'OUMUCREAT',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMUSERS'
		and module_name = 'OUMUCREAT');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMROLES',
	'OUMASSMU',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMROLES'
		and module_name = 'OUMASSMU');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCDADDRE',
	'OUMPERSO',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDADDRE'
		and module_name = 'OUMPERSO');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMSTAFC',
	'OUMPERSO',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMSTAFC'
		and module_name = 'OUMPERSO');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OIUIWPVE',
	'OCDTWORK',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUIWPVE'
		and module_name = 'OCDTWORK');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OIUIWPVE',
	'OCDCSCH',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUIWPVE'
		and module_name = 'OCDCSCH');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUNOTCM',
	'OCDCLOGS',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUNOTCM'
		and module_name = 'OCDCLOGS');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUNOTCM',
	'OCDCSCH',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUNOTCM'
		and module_name = 'OCDCSCH');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMSTAFC',
	'OCDAWORK',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMSTAFC'
		and module_name = 'OCDAWORK');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMSTAFC',
	'OCDTWORK',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMSTAFC'
		and module_name = 'OCDTWORK');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCMSSVCT',
	'OCISCATA',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSSVCT'
		and module_name = 'OCISCATA');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMUSERS',
	'OUMUSERS',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMUSERS'
		and module_name = 'OUMUSERS');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMUCREAT',
	'OUMUSERS',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMUCREAT'
		and module_name = 'OUMUSERS');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMUCREAT',
	'OUMUCREAT',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMUCREAT'
		and module_name = 'OUMUCREAT');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCDONOST',
	'OUMSMALA',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDONOST'
		and module_name = 'OUMSMALA');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OUMPERSO',
	'OUMPERSO',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OUMPERSO'
		and module_name = 'OUMPERSO');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag) 
select
	'OIUSELVE',
	'OIMSTRIP',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists
(
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUSELVE'
		and module_name = 'OIMSTRIP');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag) 
select
	'OIDGENST',
	'OIMSTRIP',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists
(
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIDGENST'
		and module_name = 'OIMSTRIP');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag) 
select
	'OCDADDRE',
	'OUMAGLOC',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists
(
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDADDRE'
		and module_name = 'OUMAGLOC');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag) 
select
	'OCUMAOFF',
	'OCMSHIER',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists
(
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUMAOFF'
		and module_name = 'OCMSHIER');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag) 
select
	'OCDTWORK',
	'OCDAWORK',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists
(
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDTWORK'
		and module_name = 'OCDAWORK');


insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUWARNG',
	'OIDADMIS',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUWARNG'
		and module_name = 'OIDADMIS');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUWARNG',
	'OMUAVBED',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUWARNG'
		and module_name = 'OMUAVBED');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUWARNG',
	'OCISCATA',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUWARNG'
		and module_name = 'OCISCATA');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUWARNG',
	'OIDCHLOC',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUWARNG'
		and module_name = 'OIDCHLOC');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUWARNG',
	'OIDCHOLO',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUWARNG'
		and module_name = 'OIDCHOLO');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUWARNG',
	'OIDRHLOC',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUWARNG'
		and module_name = 'OIDRHLOC');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUWARNG',
	'OIDVIRES',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUWARNG'
		and module_name = 'OIDVIRES');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUWARNG',
	'OIDINTMV',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUWARNG'
		and module_name = 'OIDINTMV');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCUWARNG',
	'OIDARHPL',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUWARNG'
		and module_name = 'OIDARHPL');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OCDADDRE',
	'OCDEDEMP',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDADDRE'
		and module_name = 'OCDEDEMP');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OIUSCINQ',
	'OIDVISIT',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUSCINQ'
		and module_name = 'OIDVISIT');

insert
	into
	MODULE_CALL_FORM_RELATION (call_form,
	module_name,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	'OIUOVRES',
	'OIDVIRES',
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
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUOVRES'
		and module_name = 'OIDVIRES');

delete
from
	module_call_form_relation
where
	module_name = 'OIDARFPL'
	and call_form = 'OIDTRWJU';