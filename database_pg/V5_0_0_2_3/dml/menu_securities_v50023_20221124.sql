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
	parent_menu_id =(
	select
		menu_id
	from
		menu_securities ms
	where
		menu_item = 'Inmate Case Management')
	and menu_item = 'Legals/Sentence Calculation'),
'Bail Orders',
'OCDBAILO',
27,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

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
	menu_securities ms2
where
	parent_menu_id =(
	select
		menu_id
	from
		menu_securities ms
	where
		menu_item = 'Community Case Management')
	and menu_item = 'Legals / Sentence Calculation'),
'Bail Orders',
'OCDBAILO',
27,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
NULL);

update
	OMS_OWNER.MENU_SECURITIES
set  modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MENU_ITEM = 'Custodial Orders'
where
	module_name = 'OCDLEGLO';

update
	menu_securities
set  modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,menu_item = 'Maintain Module Tables Association'
where
	module_name = 'OUMTAGRE';

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
	menu_securities ms2
where
	parent_menu_id =(
	select
		menu_id
	from
		menu_securities ms
	where
		menu_item = 'Inmate Case Management')
	and menu_item = 'Visits Management'),
'Incentives & Earned Privileges',
'OIMIEPLV', 
2,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
null,
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
	menu_securities ms2
where
	parent_menu_id =(
	select
		menu_id
	from
		menu_securities ms
	where
		menu_item = 'Inmate Case Management')
	and menu_item = 'Visits Management'),
'Incentives & Earned Privileges Level',
'OIDIEPLV', 
2,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
null,
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
	menu_securities ms2
where
	parent_menu_id =(
	select
		menu_id
	from
		menu_securities ms
	where
		menu_item = 'Inmate Case Management')
	and menu_item = 'Visits Management'),
'Incentives & Earned Privileges Level',
'OIDIEPLV', 
2,
CURRENT_TIMESTAMP,
'OMS_OWNER',
CURRENT_TIMESTAMP,
NULL,
null);