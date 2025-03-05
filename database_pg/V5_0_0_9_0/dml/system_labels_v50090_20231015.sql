insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT  nextval('LABLE_ID_SEQUENCE'),'OCDCORDS','ocdcords.orderwithoutchargesconfirmation','You are saving the order(s)  %orderno% without linking charges. Do you want to proceed ?','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OCDCORDS' and msg_key ='ocdcords.orderwithoutchargesconfirmation');

update
	oms_owner.system_labels
set
	msg_value = 'Because this order(s) %orderno% does not have any linked charges, you must record matter number(s).',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDCORDS'
	and msg_key = 'ocdleglo.recordmatternumber';

INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 SELECT nextval('lable_id_sequence'), 'OUMDTEMP', 'oumdtemp.docattached', 'Cannot update the template beacuse already  document generated with this template', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL  
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OUMDTEMP' AND msg_key = 'oumdtemp.docattached'); 


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
SELECT  nextval('LABLE_ID_SEQUENCE'),'OCDIPLAC','ocdiplac.youcannotdeactivateactivecpowner','You cannot deactivate the CPOwner.Please assign new CPOwner before deactivating','LABEL',current_timestamp,'OMS_OWNER',current_timestamp, 'OMS_OWNER',NULL
  WHERE NOT exists (SELECT 1 from OMS_OWNER.SYSTEM_LABELS where MODULE_NAME='OCDIPLAC' and msg_key ='ocdiplac.youcannotdeactivateactivecpowner');


INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 SELECT nextval('lable_id_sequence'), 'OIRMASSET', 'oirmasset.unsupportedfile', 'Unsupported File Format,Only png,jpeg,jpg,jrtx are allowed to import', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL  
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIRMASSET' AND msg_key = 'oirmasset.unsupportedfile');

INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 SELECT nextval('lable_id_sequence'), 'OIIMPJRP', 'oiimpjrp.notallowed', 'Unable to Import: Uploaded file is having not allowed expression syntax', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL  
  WHERE NOT EXISTS (SELECT 1 FROM oms_owner.system_labels WHERE module_name = 'OIIMPJRP' AND msg_key = 'oiimpjrp.notallowed');


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
	'COMMON',
	'common.unauthorize',
	'User donot have access of modules',
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
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.unauthorize');


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
	'COMMON',
	'common.unauthorizeAdministrator',
	'Please contact the System Administrator if you need access to this Page',
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
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.unauthorizeAdministrator');