delete
from
	menu_securities
where
	parent_menu_id = 10000000000;
	

insert
	into
	OMS_OWNER.MENU_SECURITIES(MENU_ID,
	PARENT_MENU_ID,
	MENU_ITEM,
	MODULE_NAME,
	SORT_ORDER,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
values(nextval('MENU_ID'),
(
select
		menu_id
from
		menu_securities ms
where
		menu_item = 'Legals/Sentence Calculation'),
'Pending Sentence Calculation Events',
'OCIPENSC',
7,
current_timestamp,
'',
null,
'',
'');
	
insert
	into
	OMS_OWNER.MENU_SECURITIES(MENU_ID,
	PARENT_MENU_ID,
	MENU_ITEM,
	MODULE_NAME,
	SORT_ORDER,
	CREATE_DATETIME,
	CREATE_USER_ID)
select
	nextval('MENU_ID'),
	(
	select
		max(menu_id)
	from
		menu_securities
	where
		menu_item = 'General Ledger'
		and parent_menu_id =(
		select
			menu_id
		from
			menu_securities
		where
			menu_item = 'Trust Accounting')),
	'General Ledger Inquiry/Reverse Transaction',
	'OTDGLIRT',
	(
	select
		max(SORT_ORDER)+ 1
	from
		menu_securities
	where
		menu_item = 'General Ledger'
		and parent_menu_id =(
		select
			menu_id
		from
			menu_securities
		where
			menu_item = 'Trust Accounting')),
	current_timestamp,
	'OMS_OWNER'
where
	not exists (
	select
		1
	from
		menu_securities
	where
		MODULE_NAME = 'OTDGLIRT'
		and 
parent_menu_id = (
		select
			max(menu_id)
		from
			menu_securities
		where
			menu_item = 'General Ledger'
			and parent_menu_id =(
			select
				menu_id
			from
				menu_securities
			where
				menu_item = 'Trust Accounting')));


				