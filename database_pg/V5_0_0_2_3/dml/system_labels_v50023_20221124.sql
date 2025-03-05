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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMOFFOB',
'oimoffob.cannotdeleterecordobservationperiodconfiguredforthistype',
'You can not delete this record this observation type is configured for offender observation period',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCMSPRAC',
'ocmsprac.warningtherearechangesthatrequiresavingbeforecontinuing',
'Warning: There are changes that require saving before continuing.',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

update
	OMS_OWNER.SYSTEM_LABELS
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MSG_VALUE = 'Observation Zone must be entered'
where
	MSG_KEY = 'oiioffob.zonemustbeselected';

update
	OMS_OWNER.SYSTEM_LABELS
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MSG_VALUE = 'Observation Type'
where
	MSG_KEY = 'oimoffob.observationtype';

update
	OMS_OWNER.SYSTEM_LABELS
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MSG_VALUE = 'Observation Id'
where
	MSG_KEY = 'oidoffob.observaionid';

update
	OMS_OWNER.SYSTEM_LABELS
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MSG_VALUE = 'Check Frequency'
where
	MSG_KEY = 'oimoffob.checkfreqency';

update
	OMS_OWNER.SYSTEM_LABELS
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MSG_VALUE = 'End Reason must be entered'
where
	MSG_KEY = 'oidoffob.endreasoncodemustbeentered';

update
	OMS_OWNER.SYSTEM_LABELS
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MSG_VALUE = 'Observation Type must be entered'
where
	MSG_KEY = 'oimoffob.observationtypemustbeenterd';

update
	OMS_OWNER.SYSTEM_LABELS
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MSG_VALUE = 'Custodial Facility must be selected'
where
	MSG_KEY = 'oiioffob.custodialfacilitymustbeselected';

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDOFFOB',
'oidoffob.offendermustbehousedinactiveobservationzone',
'Offender must be housed in active Observation Zone.',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.bailorders',
'Bail Orders',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

update
	OMS_OWNER.SYSTEM_LABELS
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MSG_VALUE = 'Check date must be greater than the schedule date time'
where
	MSG_KEY = 'oidoffob.checkdatemustbegreaterthanthescheduledate';

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.Charges',
'Charges',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.Conditions',
'Conditions',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.addConditions',
'Add Conditions',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.updateConditions',
'Update Conditions',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.legalTextofCondition',
'Legal Text of Condition',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.refersh',
'Refresh Data',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.no',
'No',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.ordereddate',
'Ordered Date',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.matter',
'Matter#',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.court',
'Court',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.type',
'Type',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.legislation',
'Legislation',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.commencedate',
'Commence Date',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.expirydate',
'Expiry Date',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.signaturerequired',
'Signature Required',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDBAILO',
'ocdbailo.status',
'status',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMOFFOB',
'oimoffob.observationtypemustbeenterd',
'Observation Type must be entered',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMOFFOB',
'oimoffob.frequencymustbeentered',
'Check Frequency must be entered',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

update
	OMS_OWNER.SYSTEM_LABELS
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,MSG_VALUE = 'Please select atleast one cell conditions data.'
where
	MSG_KEY = 'oimoffob.pleaseselectatleasetonecellcondition';

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIPTRAN',
'oiiptran.value',
'Value',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIPTRAN',
'oiiptran.size',
'Size',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIPTRAN',
'oiiptran.selectedtotalvalue',
'Selected Total Value',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDMPITM',
'oidmpitm.valuemustbeentered',
'Value must be entered',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDCRTEV',
'oidcrtev.doyouwanttocontinue',
'Do you want continue..',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDCRTEV',
'oidcrtev.inPersonCourtData',
'The selected offender has a non-association with below offender(s) who has In Person Court event scheduled on the same date and same location',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDLEGLS',
'ocdlegls.baildetails',
'Active Bail Orders',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDMPCON',
'oidmpcon.trlocationnotpresent',
'The transaction room property storage location does not exist for the agency location %agyLocId%. ',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDOFFOB',
'oidoffob.usercannotenterfuturedate',
'Future Date cannot be entered to the observation period',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIOFFOB',
'oiioffob.zonemustbeselected',
'Observation Zone must be entered',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDPACTI',
'oidpacti.selectedoffender',
'The selected offender',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDPACTI',
'oidpacti.nonassociationwithoffender',
'has a non-association with below offender(s),who has been assigned for same institutional activities.',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

update
	system_labels
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,msg_value = 'Maintain Module Tables Association'
where
	msg_key = 'oumtagre.paneTitle';

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OUMTAGRE',
'oumtagre.tablecomment',
'Table Description',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDCLOGS',
'ocdclogs.go',
'Go',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDCLOGS',
'ocdclogs.d',
'D',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDCLOGS',
'ocdclogs.a',
'A',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.assessmentdetails',
'Assessment Details',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.createddate',
'Created Date',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.assessmentcomment',
'Assessment Comment',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.overridecomment',
'Override Comment',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.overrideassessment',
'Override Assessment',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.status',
'Status',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.assessmenttypemustbeentered',
'Assessment Type must be entered.',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.draftassessmentcannotbeoverridden',
'Draft assessment cannot be overridden',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCUNOQUE',
'ocunoque.needtoanswer',
'Need to answer the mandatory questions',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCMNOQUE',
'ocmnoque.required',
'Required',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.preventedfromoverriddenapproval',
'Prevented from overridden as approval status is inactive',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCUNOQUE',
'ocunoque.youmustansweratleastonequestion',
'You must answer at least one question before saving the assessment',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDCAPPR',
'oidcappr.draftassessmentcannotbeapproved',
'Draft Assessment cannot be approved',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

update
	system_labels
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,msg_value = 'Exit'
where
	msg_key = 'oimsenot.exit';
	
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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDSTFRP',
'oidstfrp.locksin',
'Locks in : ',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.title',
'Maintain Incentives and Earned Privileges',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.iepLevelCode',
'IEP Level Code',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.iepLeveldescription',
'IEP Level Description',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.sequence',
'Sequence',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.reviewDays',
'Review Days',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.intakeIEP',
'Intake IEP',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.canteenLimit',
'Canteen Limits($)',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.activeFlag',
'Active',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.expiryDate',
'Expiry Date',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.iepLevelCodeShouldNotDuplicate',
'IEP_Level code should not be duplicate',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.iepLevelCodeManditateField',
'IEP_Level code must be entered',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.iepLeveldescriptionManditateField',
'IEP_Level description must be entered',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.reviewDaysManditateField',
'ReviewDays must be entered',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.cannotInactive',
'IepLevelCode is used by Offender so,cannot inactive this record',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMIEPLV',
'oimieplv.cannotDelete',
'IepLevelCode is used by Offender so,cannot delete this record',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDIEPLV',
'oidieplv.title',
'Incentives and Earned privileges Level',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDIEPLV',
'oidieplv.iepLevelCode',
'IEP Level',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDIEPLV',
'oidieplv.dateAsigned',
'Date Assigned',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDIEPLV',
'oidieplv.approvedStaff',
'Approved By',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDIEPLV',
'oidieplv.reviewComment',
'Review Comment',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDIEPLV',
'oidieplv.nextReviewDate',
'Next Review Date',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OWHEADER',
'owheader.iep.title',
'IEP Level',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMVLIMT',
'oimvlimt.changingieplevel',
'Your system is currently configured for IEP level for this selected facility. Do you want to change it Security level.',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMVLIMT',
'oimvlimt.changingseclevel',
'Your system is currently configured for Security level for this selected facility. Do you want to change it IEP level.',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIMVLIMT',
'oimvlimt.iepseclevel',
'IepSecLevel',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

update
	system_labels
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,msg_value = 'This account is not active. Please contact your administrator.'
where
	msg_key = 'login.userisinactive';

update
	system_labels sl
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,msg_value = 'Submission Date can not be earlier than the date of previous assessment of same type' 
where
	msg_key = 'ocdnoque.reassessmentprevdatevalidation';

update
	system_labels sl
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,msg_value = 'Re-assessment date cannot be the same as or earlier than the submission date.'
where
	msg_key = 'ocdnoque.reassessmentdatevalidation';

update
	system_labels sl
set modify_datetime = current_timestamp
    ,modify_user_id = 'OMS_OWNER' 
    ,msg_value = 'Submission Date can not be greater than system date'
where
	msg_key = 'ocdnoque.greaterassessmentdatevalidation';

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.submissiondatevalidation',
'Submission Date must be entered.',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OCDNOQUE',
'ocdnoque.submissiondate',
'Submission Date',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIDPWAIT',
'oidpwait.nodataismodifiedtosave',
'No data is modified to save.',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);	

insert
	into
	OMS_OWNER.SYSTEM_LABELS (LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values (nextval('LABLE_ID_SEQUENCE'),
'OCDCHGDT',
'ocdchgdt.save',
'Save',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIRREPORT',
'oirreport.title',
'Report',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIRREPORT',
'oirreport.reportmodule',
'Report Module',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIRREPORT',
'oirreport.reportDescription',
'Report Description',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIRREPORT',
'oirreport.lastsaved',
'Last Saved',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIEXPJRP',
'oiexpjrp.title',
'Export Report',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIEXPJRP',
'oiexppro.export',
'Export',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIEXPJRP',
'oiexpjrp.reportmodule',
'Report Module',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIEXPJRP',
'oiexpjrp.lastsaved',
'Last Saved',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIEXPJRP',
'oiexpjrp.createduser',
'Create User',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIEXPJRP',
'oiexpjrp.modifieduser',
'Modified User',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIEXPJRP',
'oiexpjrp.selectatleaseonerecord',
'Please select atleast one record',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIMPJRP',
'oiimpjrp.title',
'Import Report',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIMPJRP',
'oiimpjrp.browse',
'Browse',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIMPJRP',
'oiimpjrp.import',
'Import',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIMPJRP',
'oiimppro.cancel',
'Cancel',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIMPJRP',
'oiimpjrp.moduleName',
'Module Name',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIMPJRP',
'oiimpjrp.fileName',
'File Name',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIMPJRP',
'oiimppro.pleaseselectaprocess',
'Please Select a report',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIMPJRP',
'oiimpjrp.successfullyimported',
'SuccessFully Imported',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
  (nextval('LABLE_ID_SEQUENCE'),
'OIIMPJRP',
'oiimppro.pleaseselectfile',
'Please Select File',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

insert
	into
	OMS_OWNER.SYSTEM_LABELS (LABEL_ID,
	MODULE_NAME,
	MSG_KEY,
	MSG_VALUE,
	MSG_TYPE,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	seal_flag)
values (nextval('LABLE_ID_SEQUENCE'),
'OCDCHGDT',
'ocdchgdt.indicators',
'Indicators',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

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
values
(nextval('LABLE_ID_SEQUENCE'),
'OCDADDRE',
'ocdaddre.addressvalidornot',
'You have entered Invalid address.Do you want to Continue?',
'LABEL',
current_timestamp,
'OMS_OWNER',
current_timestamp,
null,
null);

update SYSTEM_LABELS set msg_value ='You are updating a validated address. Are you sure you want to proceed?' where msg_key='ocdaddre.addressvalidornot';

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMVLIMT','oimvlimt.iepLevel','IEP Level','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMVLIMT','oimvlimt.theiplevelisalreadyactive','This IEP Level is already Active.	','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDONONA','oidonona.authorisedbymustbe','Authorised By Must Be Entered','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSTFRP','oidstfrp.eqpalreadyexist',' Equipment Used already exists','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSTFRP','oidstfrp.seqalreadyexists','Sequence already exists','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSTFRP','oidstfrp.seqmustdoesnot','Sequence does not accept more then 32767 ','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSTFRP','oidstfrp.seqmustnotbezero','Sequence value must not be zero','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
 
 
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.Entrydateandtimecannotbeinfuture','Entry date and time can not be in future','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OWHEADER','owheader.iep.code','Code','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OWHEADER','owheader.iep.description','Description','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OWHEADER','owheader.iep.nextReviewDate','Next Review Date','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL); 

-----------------------------------------

update
	SYSTEM_LABELS
set
	modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,MSG_VALUE = 'Elite 5.0.0.2.3'
where
	MODULE_NAME = 'LOGIN'
	and MSG_KEY in ('home.title.header', 'login.header');