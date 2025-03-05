insert
	into
	oms_owner.system_labels (LABEL_ID,
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
	'oidieplv.nextreviewdategreatervalidation',
	'Next Review Date must be greater than system date',
	'LABEL',
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
		oms_owner.system_labels
	where
		MODULE_NAME = 'OIDIEPLV'
		and msg_key = 'oidieplv.nextreviewdategreatervalidation');

insert
	into
	system_labels (LABEL_ID,
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
	nextval('lable_id_sequence'),
	'OCDCHGSU',
	'ocdchgsu.bulkmatter',
	'Matter',
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
		module_name = 'OCDCHGSU'
		and msg_key = 'ocdchgsu.bulkmatter');

insert
	into
	system_labels (LABEL_ID,
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
	nextval('lable_id_sequence'),
	'OCDCLIST',
	'ocdclist.matters',
	'Matter(s)',
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
		module_name = 'OCDCLIST'
		and msg_key = 'ocdclist.matters');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OWEACPLN',
	'oweapln.reportcreated',
	'Report Created',
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
		module_name = 'OWEACPLN'
		and msg_key = 'oweapln.reportcreated');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OWEACPLN',
	'oweapln.createdby',
	'Created By',
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
		module_name = 'OWEACPLN'
		and msg_key = 'oweapln.createdby');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OWEACPLN',
	'oweapln.offenderphone',
	'Offender Phone',
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
		module_name = 'OWEACPLN'
		and msg_key = 'oweapln.offenderphone');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OWEACPLN',
	'oweapln.offenderaddress',
	'Offender Address',
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
		module_name = 'OWEACPLN'
		and msg_key = 'oweapln.offenderaddress');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OWEACPLN',
	'oweapln.week',
	'Week',
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
		module_name = 'OWEACPLN'
		and msg_key = 'oweapln.week');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OWEACPLN',
	'oweapln.wapversion',
	'WAP Version',
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
		module_name = 'OWEACPLN'
		and msg_key = 'oweapln.wapversion');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OWEACPLN',
	'oweapln.emdailycharging',
	'Please charge your electronic monitoring device as directed by your Probation Officer',
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
		module_name = 'OWEACPLN'
		and msg_key = 'oweapln.emdailycharging');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OWEACPLN',
	'oweapln.scheduledactivities',
	'SCHEDULED ACTIVITIES',
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
		module_name = 'OWEACPLN'
		and msg_key = 'oweapln.scheduledactivities');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OCDINTAK',
	'ocdintak.intakecanonlybebackdated',
	'Intakes can only be backdated to ',
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
		module_name = 'OCDINTAK'
		and msg_key = 'ocdintak.intakecanonlybebackdated');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OCDINTAK',
	'ocdintak.earlierthansystemdate',
	'days earlier than system date',
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
		module_name = 'OCDINTAK'
		and msg_key = 'ocdintak.earlierthansystemdate');

insert
	into
	oms_owner.system_labels (LABEL_ID,
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
	nextval('lable_id_sequence'),
	'OIMVDTSL',
	'oimvdtsl.recordwithsamefacilitydaystartandendtimeexists',
	'A record with the same facility, day, start time and end time exists.',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIMVDTSL'
		and msg_key = 'oimvdtsl.recordwithsamefacilitydaystartandendtimeexists');
 
insert
	into
	oms_owner.system_labels
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
	'OIDALLOW',
	'oidallow.deletenotallowedasoffenderhasbeenpaidforthisallowance',
	'Delete not allowed as offender has been paid for this allowance',
	'LABEL',
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
		oms_owner.system_labels
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.deletenotallowedasoffenderhasbeenpaidforthisallowance');

insert
	into
	oms_owner.system_labels
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
	'OCDCGPAY',
	'ocdcgpay.todateenteredcannotbegreaterthanthecurrentdate',
	'To Date entered con not be greater than the current date.',
	'LABEL',
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
		oms_owner.system_labels
	where
		MODULE_NAME = 'OCDCGPAY'
		and msg_key = 'ocdcgpay.todateenteredcannotbegreaterthanthecurrentdate');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OCDNCODE',
	'ocdncode.updateoutcome',
	'Update Outcome',
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
		module_name = 'OCDNCODE'
		and msg_key = 'ocdncode.updateoutcome');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OCDNCODE',
	'ocdncode.deselectall',
	'Deselect All',
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
		module_name = 'OCDNCODE'
		and msg_key = 'ocdncode.deselectall');

update
	oms_owner.system_labels
set
	module_name = 'OCDCORDS',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDLEGLO'
	and msg_key = 'ocdleglo.document';

update
	oms_owner.system_labels
set
	module_name = 'OCDCORDS',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCDLEGLO'
	and msg_key = 'ocdleglo.savedaspendingevent';

insert
	into
	oms_owner.system_labels (LABEL_ID,
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
	nextval('lable_id_sequence'),
	'OUMWMENU',
	'oumwmenu.tooltip',
	'Tooltip',
	'LABEL',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OUMWMENU'
		and msg_key = 'oumwmenu.tooltip');

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
	NEXTVAL('LABLE_ID_SEQUENCE'),
	'OIDRELEA',
	'oidrelea.Youarereleasinganoffenderwhodoesnothaveaconfirmedreleasedate',
	'You are releasing an offender who does not have a Confirmed Release Date. Do you wish to proceed?',
	'LABEL',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIDRELEA'
		and msg_key = 'oidrelea.Youarereleasinganoffenderwhodoesnothaveaconfirmedreleasedate');

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
	NEXTVAL('LABLE_ID_SEQUENCE'),
	'OIDRELEA',
	'oidrelea.Youarereleasinganoffenderonadifferentdatethantheirconfirmedreleasedate',
	'You are releasing an offender on a different date than their Confirmed Release Date. Do you wish to proceed?',
	'LABEL',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIDRELEA'
		and msg_key = 'oidrelea.Youarereleasinganoffenderonadifferentdatethantheirconfirmedreleasedate');

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
	NEXTVAL('LABLE_ID_SEQUENCE'),
	'OIDRELEA',
	'oidrelea.Youarereleasinganoffenderwhodoesnothaveaverifiedconfirmedreleasedate',
	'You are releasing an offender who does not have a verified Confirmed Release Date. Do you wish to proceed?',
	'LABEL',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIDRELEA'
		and msg_key = 'oidrelea.Youarereleasinganoffenderwhodoesnothaveaverifiedconfirmedreleasedate');

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
	NEXTVAL('LABLE_ID_SEQUENCE'),
	'OIDCUSTAD',
	'oidcustad.doyouwanttosavemodifiedchanges',
	'Do you want to save changes?',
	'LABEL',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIDCUSTAD'
		and msg_key = 'oidcustad.doyouwanttosavemodifiedchanges');

insert
	into
	system_labels (label_id,
	module_name,
	msg_key,
	msg_value,
	msg_type,
	create_datetime,
	create_user_id,
	seal_flag)
select
	nextval('lable_id_sequence'),
	'OIDINCDE',
	'oidincde.offweapons',
	'Offender Weapon',
	'LABEL',
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
		module_name = 'OIDINCDE'
		and msg_key = 'oidincde.offweapons');

insert
	into
	oms_owner.system_labels
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
	nextval('lable_id_sequence'),
	'OIDSTABS',
	'oidstabs.purpose',
	'Purpose',
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
		module_name = 'OIDSTABS'
		and msg_key = 'oidstabs.purpose');

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
	NEXTVAL('LABLE_ID_SEQUENCE'),
	'OIDSTOJU',
	'oidstoju.thisactioncannotbeperformedforaninactiveoffender',
	'This action cannot be performed for an inactive offender',
	'LABEL',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIDSTOJU'
		and msg_key = 'oidstoju.thisactioncannotbeperformedforaninactiveoffender');

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
	NEXTVAL('LABLE_ID_SEQUENCE'),
	'OIDSTWJU',
	'oidstwju.thisactioncannotbeperformedforaninactiveoffender',
	'This action cannot be performed for an inactive offender',
	'LABEL',
	CURRENT_TIMESTAMP,
	'OMS_OWNER',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		system_labels
	where
		module_name = 'OIDSTWJU'
		and msg_key = 'oidstwju.thisactioncannotbeperformedforaninactiveoffender');

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
	'OIDRELEA',
	'oidrelea.commenttextmustbelessthan240char',
	'Comment text must be less than 240 characters',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIDRELEA'
		and msg_key = 'oidrelea.commenttextmustbelessthan240char');

insert
	into
	oms_owner.system_labels (LABEL_ID,
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
	'OCMPSSET',
	'ocmpsset.hideinstactdelt',
	'Hide Institution Activities Delete function',
	'LABEL',
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
		oms_owner.system_labels
	where
		MODULE_NAME = 'OCMPSSET'
		and msg_key = 'ocmpsset.hideinstactdelt');

insert
	into
	oms_owner.system_labels (LABEL_ID,
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
	'OSIOSEAR',
	'osiosear.selectdoboryear',
	'Please select a Date of Birth or Birth Year before initiating a query.',
	'LABEL',
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
		oms_owner.system_labels
	where
		MODULE_NAME = 'OSIOSEAR'
		and msg_key = 'osiosear.selectdoboryear');

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
	'CONTACTBUSINESS',
	'contactbusiness.title',
	'Contact/Business Hours',
	'LABEL',
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
		system_labels
	where
		module_name = 'CONTACTBUSINESS'
		and msg_key = 'contactbusiness.title');

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
	'CONTACTBUSINESS',
	'contactbusiness.cntprsn',
	'Contact Person',
	'LABEL',
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
		system_labels
	where
		module_name = 'CONTACTBUSINESS'
		and msg_key = 'contactbusiness.cntprsn');

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
	'CONTACTBUSINESS',
	'contactbusiness.bunsshrs',
	'Business Hour',
	'LABEL',
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
		system_labels
	where
		module_name = 'CONTACTBUSINESS'
		and msg_key = 'contactbusiness.bunsshrs');

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
	'OIDRELSC',
	'oidrelsc.appendcomment',
	'Append Comment',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIDRELSC'
		and msg_key = 'oidrelsc.appendcomment');

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
	'OIDRELEA',
	'oidrelea.appendcomment',
	'Append Comment',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIDRELEA'
		and msg_key = 'oidrelea.appendcomment');

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
	'OIUIRAME',
	'oiuirame.appendmustbeentered',
	'Append comment must be entered',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIUIRAME'
		and msg_key = 'oiuirame.appendmustbeentered');

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
	'OIUIRAME',
	'oiuirame.appendmustbelessthan240characters',
	'Append comment cannot be more than 240 characters',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIUIRAME'
		and msg_key = 'oiuirame.appendmustbelessthan240characters');

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
	'OIUIRAME',
	'oiuirame.titlesch',
	'Append Release Schedule Comment',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIUIRAME'
		and msg_key = 'oiuirame.titlesch');

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
	'OIUIRAME',
	'oiuirame.titleinst',
	'Append Institutional Release Comment',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIUIRAME'
		and msg_key = 'oiuirame.titleinst');

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
	'OIUIRAME',
	'oiuirame.appendcommentsch',
	'Append Comment',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIUIRAME'
		and msg_key = 'oiuirame.appendcommentsch');

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
	'OIUIRAME',
	'oiuirame.appendcommentinst',
	'Append Comment',
	'LABEL',
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
		system_labels
	where
		module_name = 'OIUIRAME'
		and msg_key = 'oiuirame.appendcommentinst');

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
	'MANAGEPROPSDLG',
	'managepropsdlg.title',
	'Deactivate Container',
	'LABEL',
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
		system_labels
	where
		module_name = 'MANAGEPROPSDLG'
		and msg_key = 'managepropsdlg.title');

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
	'MANAGEPROPSDLG',
	'managepropsdlg.toperson',
	'To Person',
	'LABEL',
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
		system_labels
	where
		module_name = 'MANAGEPROPSDLG'
		and msg_key = 'managepropsdlg.toperson');

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
	'MANAGEPROPSDLG',
	'managepropsdlg.toagency',
	'To Agency',
	'LABEL',
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
		system_labels
	where
		module_name = 'MANAGEPROPSDLG'
		and msg_key = 'managepropsdlg.toagency');

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
	'MANAGEPROPSDLG',
	'managepropsdlg.toffender',
	'To Offender',
	'LABEL',
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
		system_labels
	where
		module_name = 'MANAGEPROPSDLG'
		and msg_key = 'managepropsdlg.toffender');

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
	'MANAGEPROPSDLG',
	'managepropsdlg.comment',
	'Comment',
	'LABEL',
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
		system_labels
	where
		module_name = 'MANAGEPROPSDLG'
		and msg_key = 'managepropsdlg.comment');

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
	'OSIHRSUM',
	'osihrsum.name',
	'Name',
	'LABEL',
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
		system_labels
	where
		module_name = 'OSIHRSUM'
		and msg_key = 'osihrsum.name');

insert
	into
	oms_owner.system_labels
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
	'common.deselectall',
	'Deselect All',
	'LABEL',
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
		oms_owner.system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.deselectall');

insert
	into
	oms_owner.system_labels
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
	'common.updateoutcome',
	'Update Outcome',
	'LABEL',
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
		oms_owner.system_labels
	where
		MODULE_NAME = 'COMMON'
		and msg_key = 'common.updateoutcome');

insert
	into
	oms_owner.system_labels
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
	'OIDALLOW',
	'oidallow.allowancehasalreadybeenpaidforthedateentered',
	'Allowance has already been paid for the date entered.',
	'LABEL',
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
		oms_owner.system_labels
	where
		MODULE_NAME = 'OIDALLOW'
		and msg_key = 'oidallow.allowancehasalreadybeenpaidforthedateentered');

update
	oms_owner.system_labels
set
	msg_value = 'Offender Allowances',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OIDALLOW'
	and msg_key = 'oidallow.offenderallowences';

update
	oms_owner.system_labels
set
	msg_value = 'Allowance Type',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OIDALLOW'
	and msg_key = 'oidallow.allowencetype';

update
	oms_owner.system_labels
set
	msg_value = 'Rate',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OIMALLOW'
	and msg_key = 'oimallow.rate';
