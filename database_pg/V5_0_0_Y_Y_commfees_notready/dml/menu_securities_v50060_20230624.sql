insert
	into
	oms_owner.menu_securities(menu_id,
	parent_menu_id,
	menu_item,
	module_name,
	sort_order,
	create_datetime,
	create_user_id,
	seal_flag,
	dynamic_form) 
select
	NEXTVAL('LABLE_ID_SEQUENCE'),
	(
	select
		menu_id
	from
		menu_securities ms
	where
		menu_item = 'Fee Accounts'
		and
     parent_menu_id = 
       (
		select
			menu_id
		from
			menu_securities
		where
			menu_item = 'Community Financials')),
	'Offender Fee Accounts',
	'OCDOFACC',
	(
	select
		coalesce(max(sort_order), 0)+ 1
	from
		menu_securities
	where
		parent_menu_id = 
       (
		select
			menu_id
		from
			menu_securities ms
		where
			menu_item = 'Fee Accounts'
			and
          parent_menu_id = 
            (
			select
				menu_id
			from
				menu_securities
			where
				menu_item = 'Community Financials'))),
	current_timestamp,
	'OMS_OWNER',
	null,
	null
where
	not exists 
    (
	select
		1
	from
		menu_securities
	where
		module_name = 'OCDOFACC'
		and
       menu_id = 
         (
		select
			menu_id
		from
			menu_securities ms
		where
			menu_item = 'Fee Accounts'
			and
            parent_menu_id = 
              (
			select
				menu_id
			from
				menu_securities
			where
				menu_item = 'Community Financials')));

insert
	into
	MENU_SECURITIES(MENU_ID,
	PARENT_MENU_ID,
	MENU_ITEM,
	MODULE_NAME,
	SORT_ORDER,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
values(NEXTVAL('LABLE_ID_SEQUENCE'),
(
select
	menu_id
from
	menu_securities
where
	menu_item = 'Offender Transactions'
	and parent_menu_id = 
  (
	select
		menu_id
	from
		menu_securities
	where
		menu_item = 'Community Financials')) ,
'Offender Receipts',
'OCDRECEI',
1,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
'OMS_OWNER',
null);

insert
	into
	MENU_SECURITIES(MENU_ID,
	PARENT_MENU_ID,
	MENU_ITEM,
	MODULE_NAME,
	SORT_ORDER,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
values(NEXTVAL('LABLE_ID_SEQUENCE'),
(
select
	menu_id
from
	menu_securities
where
	menu_item = 'Offender Transactions'
	and parent_menu_id = 
  (
	select
		menu_id
	from
		menu_securities
	where
		menu_item = 'Community Financials')) ,
'Batch Offender Receipts',
'OCDBRECI',
2,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
'OMS_OWNER',
null);

insert
	into
	MENU_SECURITIES(MENU_ID,
	PARENT_MENU_ID,
	MENU_ITEM,
	MODULE_NAME,
	SORT_ORDER,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
values(NEXTVAL('LABLE_ID_SEQUENCE'),
(
select
	menu_id
from
	menu_securities
where
	menu_item = 'Offender Transactions'
	and parent_menu_id = 
  (
	select
		menu_id
	from
		menu_securities
	where
		menu_item = 'Community Financials')) ,
'Offender Adjustment',
'OCDADJUS',
8,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
'OMS_OWNER',
null);

insert
	into
	MENU_SECURITIES(MENU_ID,
	PARENT_MENU_ID,
	MENU_ITEM,
	MODULE_NAME,
	SORT_ORDER,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG)
values(NEXTVAL('LABLE_ID_SEQUENCE'),
(
select
	menu_id
from
	menu_securities
where
	menu_item = 'Offender Transactions'
	and parent_menu_id = 
  (
	select
		menu_id
	from
		menu_securities
	where
		menu_item = 'Community Financials')) ,
'Offender Adjustment Reversals',
'OCDREVER',
9,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
'OMS_OWNER',
null);