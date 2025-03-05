insert
	into
	OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	nextval('LABLE_ID_SEQUENCE'),
	'OUMRCODE',
	'oumrcode.rowalreadyexists',
	'Row exists already with same code',
	'LABEL',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OUMRCODE'
		and msg_key = 'oumrcode.rowalreadyexists');

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
	'OIDVISIT',
	'oidvisit.visitcannotbecreatedforinactiveoffender',
	'Visits cannot be created for inactive offenders',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIDVISIT'
		and msg_key = 'oidvisit.visitcannotbecreatedforinactiveoffender');

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
	'COMMON',
	'common.iwpdocument',
	'Documents',
	'LABEL',
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
		system_labels
	where
		module_name = 'COMMON'
		and msg_key = 'common.iwpdocument');

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
	'OIMILOCA',
	'oimiloca.linkedoffendervalidation',
	'Offender(s) are linked to this internal location. Please transfer the offenders out of this internal location prior to inactivation',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMILOCA'
		and msg_key = 'oimiloca.linkedoffendervalidation');

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
	'OUMTAGRE',
	'oumtagre.duplicatetableName',
	'Row already exists with Same Table Name',
	'LABEL',
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
		system_labels
	where
		module_name = 'OUMTAGRE'
		and msg_key = 'oumtagre.duplicatetableName');

insert
	into
	OMS_OWNER.SYSTEM_LABELS(
 LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
 select
	nextval('LABLE_ID_SEQUENCE'),
	'OSANVIOS',
	'osanvios.textExceedsAllowedLimitOfCharacters',
	'Amend Additional Counts Comment exceeds allowed limit of characters.',
	'LABEL',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MSG_KEY = 'osanvios.textExceedsAllowedLimitOfCharacters'
		and MSG_VALUE = 'Amend Additional Counts Comment exceeds allowed limit of characters.'
);

insert
	into
	OMS_OWNER.SYSTEM_LABELS(
 LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
 select
	nextval('LABLE_ID_SEQUENCE'),
	'OWEACPLN',
	'oweacpln.textExceedsAllowedLimitOfCharacters',
	'Append WAP Comment exceeds allowed limit of characters.',
	'LABEL',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MSG_KEY = 'oweacpln.textExceedsAllowedLimitOfCharacters'
		and MSG_VALUE = 'Append WAP Comment exceeds allowed limit of characters.'
);

insert
	into
	OMS_OWNER.SYSTEM_LABELS(
 LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
 select
	nextval('LABLE_ID_SEQUENCE'),
	'OIDOFFOB',
	'oidoffob.textExceedsAllowedLimitOfCharacters',
	'Append Offender Observation Comment exceeds allowed limit of characters.',
	'LABEL',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MSG_KEY = 'oidoffob.textExceedsAllowedLimitOfCharacters'
		and MSG_VALUE = 'Append Offender Observation Comment exceeds allowed limit of characters.'
);

insert
	into
	OMS_OWNER.SYSTEM_LABELS(
 LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	nextval('LABLE_ID_SEQUENCE'),
	'OWHEADER',
	'owheader.supervision.title',
	'Supervision',
	'LABEL',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MSG_KEY = 'owheader.supervision.title'
		and MSG_VALUE = 'Supervision');

insert
	into
	OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	nextval('LABLE_ID_SEQUENCE'),
	'OIUSTINV',
	'oiustinv.duplicatestaff',
	'Staff Already Exists',
	'LABEL',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OIUSTINV'
		and msg_key = 'oiustinv.duplicatestaff');

insert
	into
	OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	nextval('LABLE_ID_SEQUENCE'),
	'OUMMEROF',
	'oummerof.tooffenderbookingstatusshouldbeclose',
	'To offender booking status should be close.',
	'LABEL',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OUMMEROF'
		and msg_key = 'oummerof.tooffenderbookingstatusshouldbeclose');

insert
	into
	OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	nextval('LABLE_ID_SEQUENCE'),
	'OIDIEPLV',
	'oidieplv.duplicateieplevelcode',
	'A record with same IEP level cannot be assigned to the offender with the same date',
	'LABEL',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDIEPLV'
		and msg_key = 'oidieplv.duplicateieplevelcode');

delete
from
	SYSTEM_LABELS
where
	msg_key = 'oidieplv.duplicateIepLevelCode'
	and module_name = 'OIDIEPLV';

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
	'OCMSVACP',
	'ocmsvacp.phaseisnotmodular',
	'This phase is not modular or is not included in the program.',
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
		module_name = 'OCMSVACP'
		and msg_key = 'ocmsvacp.phaseisnotmodular');

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
	'OCMSVACP',
	'ocmsvacp.cannotdeletemasterchildrecord',
	'Cannot delete master record when child records exist.	',
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
		module_name = 'OCMSVACP'
		and msg_key = 'ocmsvacp.cannotdeletemasterchildrecord');

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
	'OCMSVACP',
	'ocmsvacp.expirydatecannotbelaterthanallocenddate',
	'Expiry Date can not be later than offender allocation End Date',
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
		module_name = 'OCMSVACP'
		and msg_key = 'ocmsvacp.expirydatecannotbelaterthanallocenddate');

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
	'OCMSVACP',
	'ocmsvacp.expirydatecannotbeearlierthanallocenddate',
	'Expiry Date can not be earlier than offender allocation End Date.',
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
		module_name = 'OCMSVACP'
		and msg_key = 'ocmsvacp.expirydatecannotbeearlierthanallocenddate');

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
	'OCMSVACP',
	'ocmsvacp.sessionlengthmust',
	'Session Length must be entered.',
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
		module_name = 'OCMSVACP'
		and msg_key = 'ocmsvacp.sessionlengthmust');

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
	'OCMSVACP',
	'ocmsvacp.sessionlengthmustbegreaterthazero',
	'Session length must be greater than Zero.',
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
		module_name = 'OCMSVACP'
		and msg_key = 'ocmsvacp.sessionlengthmustbegreaterthazero');

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
	'OCMSVACP',
	'ocmsvacp.cannotremovephase',
	'Cannot remove this phase.Scheduled sessions exist for this phase.',
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
		module_name = 'OCMSVACP'
		and msg_key = 'ocmsvacp.cannotremovephase');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.maintaininmateissuetypepermissions',
	'Maintain Inmate Issue Type Permissions',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.maintaininmateissuetypepermissions');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.userroles',
	'User Roles',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.userroles');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.roleid',
	'Role Id',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.roleid');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.code',
	'Code',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.code');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.rolename',
	'Role Name',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.rolename');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.issuetypepermission',
	'Issue Type Permission',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.issuetypepermission');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.issuetype',
	'Issue Type',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.issuetype');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.create/amend',
	'Create/Amend',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.create/amend');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.view',
	'View',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.view');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.issuereason',
	'Issue Reason',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.issuereason');

insert
	into
	system_labels ( label_id,
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
	'OIMIITPS',
	'oimiitps.revokeviewoption',
	'You dont have access to revoke view option when create/amend Issue type is checked',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIMIITPS'
		and msg_key = 'oimiitps.revokeviewoption');

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
	'OIDISSUE',
	'oidissue.youdonothavepermissionstoamendthisissuetype',
	'You do not have permissions to amend this issue type.',
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
		module_name = 'OIDISSUE'
		and msg_key = 'oidissue.youdonothavepermissionstoamendthisissuetype');
		
 insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT
  nextval('LABLE_ID_SEQUENCE'),'OUMTRNBK','oumtrnbk.transferbookingprocesscompleted','Transfer booking process completed.','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OUMTRNBK' and msg_key ='oumtrnbk.transferbookingprocesscompleted');

insert
	into
 OMS_OWNER.SYSTEM_LABELS(
 LABEL_ID, 
 MODULE_NAME,
 MSG_KEY,
 MSG_VALUE, 
 MSG_TYPE,
 create_datetime,
 create_user_id, 
 modify_datetime, 
 modify_user_id, 
 seal_flag)
select 
nextval('LABLE_ID_SEQUENCE'),
'OIDSTEST',
'oidstest.timetaken',
'Time Taken',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
NULL
where not exists (
select
	1
from
	OMS_OWNER.SYSTEM_LABELS
where
	MSG_KEY = 'oidstest.timetaken');

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT
  nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.nocourtactiondataissavedforthisoffender','No court action data is saved for this offender','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OCIMYOFF' and msg_key ='ocimyoff.nocourtactiondataissavedforthisoffender');
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT
  nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.nocourtreportdataissavedforthisoffender','No court report data is saved for this offender','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OCIMYOFF' and msg_key ='ocimyoff.nocourtreportdataissavedforthisoffender');

insert
	into
 OMS_OWNER.SYSTEM_LABELS(
 LABEL_ID, 
 MODULE_NAME,
 MSG_KEY,
 MSG_VALUE, 
 MSG_TYPE,
 create_datetime,
 create_user_id, 
 modify_datetime, 
 modify_user_id, 
 seal_flag)
select 
nextval('LABLE_ID_SEQUENCE'),
'OIDADMIS',
'oidadmis.therearenoactiveagencylocations',
'Currently no active agency locations',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
NULL
where not exists (
select
	1
from
	OMS_OWNER.SYSTEM_LABELS
where
	MSG_KEY = 'oidadmis.therearenoactiveagencylocations');

insert
	into
	OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
select
	nextval('LABLE_ID_SEQUENCE'),
	'OIDINCDE',
	'oidincde.staffreportinvolvement',
	'Staff Report',
	'LABEL',
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
		OMS_OWNER.SYSTEM_LABELS
	where
		MODULE_NAME = 'OIDINCDE'
		and msg_key = 'oidincde.staffreportinvolvement');		