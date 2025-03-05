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
	'OSINAMES',
	'OIDONONA',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OSINAMES'
		and module_name = 'OIDONONA');

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
	'OSINAMES',
	'OIDRHLOC',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OSINAMES'
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
	'OSINAMES',
	'OIVCTMNG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OSINAMES'
		and module_name = 'OIVCTMNG');

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
	'OSINAMES',
	'OIDVIRES',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OSINAMES'
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
	'OSINAMES',
	'OUMMEROF',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OSINAMES'
		and module_name = 'OUMMEROF');

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
	'OSINAMES',
	'OSINAMES',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OSINAMES'
		and module_name = 'OSINAMES');

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
	'OIINAMES',
	'OIDINCDE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIDINCDE');

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
	'OIINAMES',
	'OIISCHED',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIISCHED');

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
	'OIINAMES',
	'OIDPAWLI',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIDPAWLI');

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
	'OIINAMES',
	'OIDBSTRN',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIDBSTRN');

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
	'OIINAMES',
	'OIDBUTAB',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIDBUTAB');

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
	'OIINAMES',
	'OIICMOCI',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIICMOCI');

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
	'OIINAMES',
	'OIDCHOLO',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
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
	'OIINAMES',
	'OIDEHLOC',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIDEHLOC');

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
	'OIINAMES',
	'OMURMRES',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OMURMRES');

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
	'OIINAMES',
	'OIDINTMV',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
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
	'OIINAMES',
	'OIDBSIAP',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIDBSIAP');

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
	'OIINAMES',
	'OIDSCMOV',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIDSCMOV');

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
	'OIINAMES',
	'OCITTASK',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OCITTASK');

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
	'OIINAMES',
	'OIDCOASI',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIDCOASI');

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
	'OIINAMES',
	'OIINAMES',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIINAMES');

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
	'OCDPROGR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUSCINQ'
		and module_name = 'OCDPROGR');

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
	'OCUOSCPV',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUSCINQ'
		and module_name = 'OCUOSCPV');

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
	'OWEACPLN',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUSCINQ'
		and module_name = 'OWEACPLN');

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
	'OIDOICUS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUSCINQ'
		and module_name = 'OIDOICUS');

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
	'OIDCRTEV',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUSCINQ'
		and module_name = 'OIDCRTEV');

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
	'OCUCNAME',
	'OIDCNOTE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCNAME'
		and module_name = 'OIDCNOTE');

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
	'OCUCNAME',
	'OCDCLOGS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCNAME'
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
	'OCUCNAME',
	'OIDOWREL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCNAME'
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
	'OCUCNAME',
	'OCDPNOTE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCNAME'
		and module_name = 'OCDPNOTE');

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
	'OCUCNAME',
	'OCDCSCH' ,
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCNAME'
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
	'OCMSCHPR',
	'OCISCATA',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSCHPR'
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
	'OCMSCHPR',
	'OCDPROGR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSCHPR'
		and module_name = 'OCDPROGR');

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
	'OCMSCHPR',
	'OCMSVACP',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSCHPR'
		and module_name = 'OCMSVACP');

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
	'OCUPAOFF',
	'OCISCATA',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUPAOFF'
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
	'OCUMPVAV',
	'OCISCATA',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUMPVAV'
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
	'OCUMPVAV',
	'OCMSUWPJ',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUMPVAV'
		and module_name = 'OCMSUWPJ');

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
	'OCUMPVAV',
	'OIMWORKR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUMPVAV'
		and module_name = 'OIMWORKR');

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
	'OCISCATA',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSOSCH'
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
	'OCMSOSCH',
	'OCMSPRAC',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSOSCH'
		and module_name = 'OCMSPRAC');

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
	'OIMWORKR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSOSCH'
		and module_name = 'OIMWORKR');

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
	'OCUSSESS',
	'OCISCATA',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUSSESS'
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
	'OCUSSESS',
	'OCDPROGR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUSSESS'
		and module_name = 'OCDPROGR');

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
	'OCUMULTI',
	'OCDUATTE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUMULTI'
		and module_name = 'OCDUATTE');

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
	'OCUMULTI',
	'OCDPROGR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUMULTI'
		and module_name = 'OCDPROGR');

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
	'OCUMULTI',
	'OCDPATTE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUMULTI'
		and module_name = 'OCDPATTE');

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
	'OCUUPSTA',
	'OIDOWREL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUUPSTA'
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
	'OCUUPSTA',
	'OCDPROGR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUUPSTA'
		and module_name = 'OCDPROGR');

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
	'OCUUPSTA',
	'OIDPWAIT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUUPSTA'
		and module_name = 'OIDPWAIT');

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
	'OCUUPSTA',
	'OCDXPROG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUUPSTA'
		and module_name = 'OCDXPROG');

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
	'OCUPDETA',
	'OCDCGPAY',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUPDETA'
		and module_name = 'OCDCGPAY');

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
	'OCISCATA',
	'OCDXPROG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCISCATA'
		and module_name = 'OCDXPROG');

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
	'OCISCATA',
	'OIDPWAIT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCISCATA'
		and module_name = 'OIDPWAIT');

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
	'OCISCATA',
	'OCDPROGR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCISCATA'
		and module_name = 'OCDPROGR');

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
	'OCISCATA',
	'OCISCATA',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCISCATA'
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
	'OCMPHMOD',
	'OCMSVACP',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMPHMOD'
		and module_name = 'OCMSVACP');

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
	'OCMPHMOD',
	'OCMSUWPJ',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMPHMOD'
		and module_name = 'OCMSUWPJ');

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
	'OCMSVACP',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSSVCT'
		and module_name = 'OCMSVACP');

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
	'OIMWORKR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSSVCT'
		and module_name = 'OIMWORKR');

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
	'OCMSPRAC',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSSVCT'
		and module_name = 'OCMSPRAC');

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
	'OCMXPROG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSSVCT'
		and module_name = 'OCMXPROG');

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
	'OCMSUWPJ',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSSVCT'
		and module_name = 'OCMSUWPJ');

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
	'OCUSCUPS',
	'OCDPATTE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUSCUPS'
		and module_name = 'OCDPATTE');

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
	'OCMCTOFF',
	'OIMWORKR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMCTOFF'
		and module_name = 'OIMWORKR');

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
	'OCMCTOFF',
	'OCMSVACP',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMCTOFF'
		and module_name = 'OCMSVACP');

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
	'OCMCTOFF',
	'OCMSPRAC',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMCTOFF'
		and module_name = 'OCMSPRAC');

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
	'OCMCTOFF',
	'OCSPROIN',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMCTOFF'
		and module_name = 'OCSPROIN');

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
	'OCMCTOFF',
	'OCMXPROG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMCTOFF'
		and module_name = 'OCMXPROG');

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
	'OCMCTOFF',
	'OCMSUWPJ',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMCTOFF'
		and module_name = 'OCMSUWPJ');

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
	'OCMSVPHA',
	'OCMSERVI',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSVPHA'
		and module_name = 'OCMSERVI');

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
	'OCMSVASS',
	'OCMSERVI',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSVASS'
		and module_name = 'OCMSERVI');

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
	'OCMSTOFF',
	'OCMSERVI',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSTOFF'
		and module_name = 'OCMSERVI');

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
	'OCMSVMOD',
	'OCMSERVI',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSVMOD'
		and module_name = 'OCMSERVI');

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
	'OCMPHBLK',
	'OCMSERVI',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMPHBLK'
		and module_name = 'OCMSERVI');

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
	'OCUPATOF',
	'OCDPROGR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUPATOF'
		and module_name = 'OCDPROGR');

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
	'OCDXPROG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSOSCH'
		and module_name = 'OCDXPROG');

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
	'OIDPWAIT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSOSCH'
		and module_name = 'OIDPWAIT');

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
	'OCDPROGR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSOSCH'
		and module_name = 'OCDPROGR');

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
	'OIUVLCTE',
	'OIDOWREL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUVLCTE'
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
	'OIUVLCTE',
	'OCDXPROG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUVLCTE'
		and module_name = 'OCDXPROG');

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
	'OCUCALCR',
	'OCDBAILO',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCALCR'
		and module_name = 'OCDBAILO');

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
	'OCUCALCR',
	'OCDCORDS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCALCR'
		and module_name = 'OCDCORDS');

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
	'OCUCALCR',
	'OCDNCODE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCALCR'
		and module_name = 'OCDNCODE');

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
	'OCUCALCR',
	'OCDLEGLS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCALCR'
		and module_name = 'OCDLEGLS');

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
	'OCUCALCR',
	'OCDPAROR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCALCR'
		and module_name = 'OCDPAROR');

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
	'OCUCALCR',
	'OIDPAROE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCALCR'
		and module_name = 'OIDPAROE');

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
	'OCUCALCR',
	'OIDCUSTAD',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCALCR'
		and module_name = 'OIDCUSTAD');

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
	'OMUCLASS',
	'OCDNOQUE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists(
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OMUCLASS'
		and module_name = 'OCDNOQUE');

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
	'OCUAVISN',
	'OIDVISIT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUAVISN'
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
	'OCUAVISN',
	'OIUIMAGE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUAVISN'
		and module_name = 'OIUIMAGE');

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
	'OSIPSEAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCLOFF'
		and module_name = 'OSIPSEAR');

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
	'OCDPERSO',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCLOFF'
		and module_name = 'OCDPERSO');

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
	'OUMAGENC',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDOAPOP'
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
	'OCDOAPOP',
	'OCDEDEMP',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDOAPOP'
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
	'OCDOAPOP',
	'OCDPERSO',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDOAPOP'
		and module_name = 'OCDPERSO');

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
	'OUMAGLOC',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDOAPOP'
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
	'OCDOAPOP',
	'OUMPERSO',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDOAPOP'
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
	'OCDOAPOP',
	'OSIPSEAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDOAPOP'
		and module_name = 'OSIPSEAR');

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
	'OCDEDEMP',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDGNUMB'
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
	'OCDGNUMB',
	'OCDPERSO',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDGNUMB'
		and module_name = 'OCDPERSO');

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
	'OSIPSEAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDGNUMB'
		and module_name = 'OSIPSEAR');

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
	'OUMAGLOC',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDGNUMB'
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
	'OCDCCONT',
	'OCDEDEMP',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDCCONT'
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
	'OCDCCONT',
	'OCDPERSO',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDCCONT'
		and module_name = 'OCDPERSO');

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
	'OSIPSEAR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCDCCONT'
		and module_name = 'OSIPSEAR');

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
	'OIUNONAS',
	'OIMULOCA',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUNONAS'
		and module_name = 'OIMULOCA');

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
	'OIUNONAS',
	'OIMILOCA',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIUNONAS'
		and module_name = 'OIMILOCA');

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
	'OCUVERIF',
	'OCDIPLAN',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUVERIF'
		and module_name = 'OCDIPLAN');

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
	'OCUVERIF',
	'OCDALERT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUVERIF'
		and module_name = 'OCDALERT');

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
	'OCUVERIF',
	'OIDRELSC',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUVERIF'
		and module_name = 'OIDRELSC');

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
	'OCUAOFFI',
	'CALSCH',
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
		call_form = 'OCUAOFFI'
		and module_name = 'CALSCH');

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
	'OCUAOFFI',
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
		call_form = 'OCUAOFFI'
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
	'OCUAOFFI',
	'OCDPROGR',
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
		call_form = 'OCUAOFFI'
		and module_name = 'OCDPROGR');

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
	'OCUAOFFI',
	'OCIOCNOT',
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
		call_form = 'OCUAOFFI'
		and module_name = 'OCIOCNOT');

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
	'OCUAOFFI',
	'OCIDIARY',
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
		call_form = 'OCUAOFFI'
		and module_name = 'OCIDIARY');

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
	'OCUAOFFI',
	'OCDTAPOW',
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
		call_form = 'OCUAOFFI'
		and module_name = 'OCDTAPOW');

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
	'OCUAOFFI',
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
		call_form = 'OCUAOFFI'
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
	'OCUAOFFI',
	'OCDATPOW',
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
		call_form = 'OCUAOFFI'
		and module_name = 'OCDATPOW');

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
	'OCUOSCPV',
	'OIDOWREL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUOSCPV'
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
	'OCUOSCPV',
	'OCDUPROJ',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUOSCPV'
		and module_name = 'OCDUPROJ');

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
	'OCUCSTAF',
	'OCDUPROJ',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCSTAF'
		and module_name = 'OCDUPROJ');

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
	'OCUCSTAF',
	'OCDUATTE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCSTAF'
		and module_name = 'OCDUATTE');

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
	'OCISCATA',
	'OCDUPROJ',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCISCATA'
		and module_name = 'OCDUPROJ');

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
	'OCISCATA',
	'OIDOWREL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCISCATA'
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
	'OCUCONDI',
	'OCDPAROR',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCONDI'
		and module_name = 'OCDPAROR');

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
	'OCUCONDI',
	'OCDBAILO',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCONDI'
		and module_name = 'OCDBAILO');

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
	'OCUCONDI',
	'OCDCORDS',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCONDI'
		and module_name = 'OCDCORDS');

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
	'OCUCONDI',
	'OCDNCODE',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCONDI'
		and module_name = 'OCDNCODE');

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
	'OCUCONDI',
	'OIDRPLAN',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCUCONDI'
		and module_name = 'OIDRPLAN');

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
	'OCMSCHPR',
	'OCDXPROG',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSCHPR'
		and module_name = 'OCDXPROG');

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
	'OCMSCHPR',
	'OIDPWAIT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSCHPR'
		and module_name = 'OIDPWAIT');

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
		call_form = 'OIUOVRES'
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
	'OCUVWARN',
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
		call_form = 'OCUVWARN'
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
	'OCUPREST',
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
		call_form = 'OCUPREST'
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
	'OCUOICHN',
	'OIIOICUS',
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
		call_form = 'OCUOICHN'
		and module_name = 'OIIOICUS');

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
	'OCUOICHN',
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
		call_form = 'OCUOICHN'
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
	'OCUOICHN',
	'OIDOICUS',
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
		call_form = 'OCUOICHN'
		and module_name = 'OIDOICUS');

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
	'OCUOICHE',
	'OIDOICUS',
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
		call_form = 'OCUOICHE'
		and module_name = 'OIDOICUS');

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
	'OCUOICAW',
	'OIDOICUS',
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
		call_form = 'OCUOICAW'
		and module_name = 'OIDOICUS');

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
	'OCUOICAW',
	'OIDOICAP',
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
		call_form = 'OCUOICAW'
		and module_name = 'OIDOICAP');

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
	'OIUINCRP',
	'OIIINLOG',
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
		call_form = 'OIUINCRP'
		and module_name = 'OIIINLOG');

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
	'OIDSCHAC',
	'OIDPAATT',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIDSCHAC'
		and module_name = 'OIDPAATT');

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
	'OIDACSEL',
	'OIDPACTI',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIDACSEL'
		and module_name = 'OIDPACTI');

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
	'OIDACSEL',
	'OIDPAWLI',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIDACSEL'
		and module_name = 'OIDPAWLI');

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
	'OCMSSVAS',
	'OCMSVACP',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSSVAS'
		and module_name = 'OCMSVACP');

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
	'OCMSSVAS',
	'OCMSUWPJ',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OCMSSVAS'
		and module_name = 'OCMSUWPJ');

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
	'OIINAMES',
	'OCDCGPAY',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OCDCGPAY');

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
	'OIINAMES',
	'OIDRELSC',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	'OMS_OWNER',
	null
where
	not exists (
	select
		1
	from
		MODULE_CALL_FORM_RELATION
	where
		call_form = 'OIINAMES'
		and module_name = 'OIDRELSC');

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
	current_timestamp,
	'OMS_OWNER',
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
	'OIITGDET',
	'OIMTGNGS',
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
		call_form = 'OIITGDET'
		and module_name = 'OIMTGNGS');

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
	'OIDSTGVL',
	'OIMTGNGS',
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
		call_form = 'OIDSTGVL'
		and module_name = 'OIMTGNGS');

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
	'OIISTGMB',
	'OIMTGNGS',
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
		call_form = 'OIISTGMB'
		and module_name = 'OIMTGNGS');

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
	'OIDSTGAE',
	'OIMTGNGS',
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
		call_form = 'OIDSTGAE'
		and module_name = 'OIMTGNGS');

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
	'OIDSTGAE',
	'OIITGDET',
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
		call_form = 'OIDSTGAE'
		and module_name = 'OIITGDET');

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
	'OIMTGOPT',
	'OIMTGNGS',
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
		call_form = 'OIMTGOPT'
		and module_name = 'OIMTGNGS');

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
	'OIDSTGID',
	'OSISTGKW',
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
		call_form = 'OIDSTGID'
		and module_name = 'OSISTGKW');

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
	'OIDSTGID',
	'OIITGDET',
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
		call_form = 'OIDSTGID'
		and module_name = 'OIITGDET');

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
	'OIDMBRVL',
	'OIDMBRDT',
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
		call_form = 'OIDMBRVL'
		and module_name = 'OIDMBRDT');

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
	'OIDAPPND',
	'OIDMBRDT',
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
		call_form = 'OIDAPPND'
		and module_name = 'OIDMBRDT');

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
	'OIISTGMI',
	'OIDONONA',
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
		call_form = 'OIISTGMI'
		and module_name = 'OIDONONA');

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
	'OIISTGMI',
	'OIITGDET',
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
		call_form = 'OIISTGMI'
		and module_name = 'OIITGDET');

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
	'OIITGDET',
	'OIITGDET',
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
		call_form = 'OIITGDET'
		and module_name = 'OIITGDET');

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
	'OIISTGMB',
	'OIITGDET',
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
		call_form = 'OIISTGMB'
		and module_name = 'OIITGDET');

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
	'OIDSTGVL',
	'OIITGDET',
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
		call_form = 'OIDSTGVL'
		and module_name = 'OIITGDET');

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
	'OIDSTGCN',
	'OIITGDET',
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
		call_form = 'OIDSTGCN'
		and module_name = 'OIITGDET');

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
	'OIDSTGCN',
	'OIMTGNGS',
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
		call_form = 'OIDSTGCN'
		and module_name = 'OIMTGNGS');

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
	'OIDSTGHL',
	'OIITGDET',
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
		call_form = 'OIDSTGHL'
		and module_name = 'OIITGDET');

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
	'OIDSTGHL',
	'OIMTGNGS',
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
		call_form = 'OIDSTGHL'
		and module_name = 'OIMTGNGS');

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
	'OIDSTGIN',
	'OIITGDET',
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
		call_form = 'OIDSTGIN'
		and module_name = 'OIITGDET');

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
	'OIDSTGIN',
	'OIMTGNGS',
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
		call_form = 'OIDSTGIN'
		and module_name = 'OIMTGNGS');

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
	'OIDTRWJU',
	'OIDARFPL',
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
		call_form = 'OIDTRWJU'
		and module_name = 'OIDARFPL');

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
	'OCUCSTAF',
	'OCMTEAMS',
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
		call_form = 'OCUCSTAF'
		and module_name = 'OCMTEAMS');

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
	'OSIPSEAR',
	'OSIOSEAR',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OSIOSEAR');

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
	'OSIPSEAR',
	'OIDDPROP',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OIDDPROP');

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
	'OSIPSEAR',
	'OCUAVISN',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OCUAVISN');

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
	'OSIPSEAR',
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
		call_form = 'OSIPSEAR'
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
	'OCDCCONT',
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
		call_form = 'OCDCCONT'
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
	'OCDGNUMB',
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
		call_form = 'OCDGNUMB'
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
	'OCDOAPOP',
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
		call_form = 'OCDOAPOP'
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
	'OCUCLOFF',
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
		call_form = 'OCUCLOFF'
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
	'OSIPSEAR',
	'OSIPSEAR',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OSIPSEAR');

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
	'OSIPSEAR',
	'OCMFAPRO',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OCMFAPRO');

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
	'OSIPSEAR',
	'OCDOFACC',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OCDOFACC');

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
	'OSIPSEAR',
	'OTMFOPRO',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OTMFOPRO');

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
	'OSIPSEAR',
	'OCDOOBLI',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OCDOOBLI');

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
	'OSIPSEAR',
	'OTDAGJTR',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OTDAGJTR');

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
	'OSIPSEAR',
	'OTDMGJTR',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OTDMGJTR');

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
	'OSIPSEAR',
	'OTDCLOSE',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OTDCLOSE');

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
	'OSIPSEAR',
	'OTDDISBU',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OTDDISBU');

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
	'OSIPSEAR',
	'OTDRDTFU',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OTDRDTFU');

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
	'OSIPSEAR',
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
		call_form = 'OSIPSEAR'
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
	'OSIPSEAR',
	'OCDPERSO',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OCDPERSO');

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
	'OSIPSEAR',
	'OIVCTMNG',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OIVCTMNG');

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
	'OSIPSEAR',
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
		call_form = 'OSIPSEAR'
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
	'OSIPSEAR',
	'OMUVREST',
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
		call_form = 'OSIPSEAR'
		and module_name = 'OMUVREST');

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
	'OMUVREST',
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
		call_form = 'OMUVREST'
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
	'OMUVREST',
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
		call_form = 'OMUVREST'
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
	'OCDADDRE',
	'OCDADDRE',
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
		and module_name = 'OCDADDRE');

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
	'OCDCLIST',
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
		and module_name = 'OCDCLIST');

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
	'OCDOAPOP',
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
		and module_name = 'OCDOAPOP');

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
	'OSIOSEAR',
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
		and module_name = 'OSIOSEAR');

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
		call_form = 'OCDADDRE'
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
	'OMUVREST',
	'OMUVREST',
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
		call_form = 'OMUVREST'
		and module_name = 'OMUVREST');


