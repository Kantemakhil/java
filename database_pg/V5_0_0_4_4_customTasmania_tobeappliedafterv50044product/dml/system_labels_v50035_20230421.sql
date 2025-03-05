INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDLEGLO', 'termToLine.pleaseenterwholenumbers', 'Please provide only whole numbered values for', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDLEGLO' AND msg_key = 'termToLine.pleaseenterwholenumbers'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMSYSET', 'oumsyset.unFormattedWillBeBlank', 'Leave the UnFormatted Description blank', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMSYSET' AND msg_key = 'oumsyset.unFormattedWillBeBlank'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDSCMOV', 'oidscmov.individualNonAss', 'INDIVIDUAL NON-ASSOCIATION CONFLICTS', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDSCMOV' AND msg_key = 'oidscmov.individualNonAss'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDSCMOV', 'oidscmov.gangNonAss', 'GANG NON-ASSOCIATION CONFLICTS', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDSCMOV' AND msg_key = 'oidscmov.gangNonAss'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDSCMOV', 'oidscmov.cctvORonline', 'The selected offender has active non-association with below offender(s) who has Online/Video Link(CC TV) Court event scheduled on the same date and same location', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDSCMOV' AND msg_key = 'oidscmov.cctvORonline'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDSCMOV', 'oidscmov.inperson', 'The selected offender has active non-association with below offender(s) who has In Person Court event scheduled on the same date and same location', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDSCMOV' AND msg_key = 'oidscmov.inperson'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDSCMOV', 'oidscmov.appranceLocationMustBeEntered', 'Appearance Location must be entered', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDSCMOV' AND msg_key = 'oidscmov.appranceLocationMustBeEntered'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDSCMOV', 'oidscmov.beingScheduled', 'While scheduling', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDSCMOV' AND msg_key = 'oidscmov.beingScheduled'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDSCMOV', 'oidscmov.alreadyScheduled', 'Already scheduled', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDSCMOV' AND msg_key = 'oidscmov.alreadyScheduled'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDSCMOV', 'oidscmov.doYouWantExit', 'Do you want continue..', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDSCMOV' AND msg_key = 'oidscmov.doYouWantExit'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDADMIS', 'oidadmis.doYouWant', 'Do you want continue..', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDADMIS' AND msg_key = 'oidadmis.doYouWant'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDADMIS', 'oidadmis.nonAssWarningMsg', ' you are trying to give admission is having Active Non-Assocations with below offenders who are in the same selected location', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDADMIS' AND msg_key = 'oidadmis.nonAssWarningMsg'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIMIEPLV', 'oimieplv.agencyLocation', 'Agency Location', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIMIEPLV' AND msg_key = 'oimieplv.agencyLocation'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIMIEPLV', 'oimieplv.livingUnit', 'Living Unit', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIMIEPLV' AND msg_key = 'oimieplv.livingUnit'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIMMHOLO', 'oimmholo.ieplevel', 'IEP Level', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIMMHOLO' AND msg_key = 'oimmholo.ieplevel'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMAGLOC', 'oumagloc.ieplevelmustbeentered', 'IEP Level Must Be Entered', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMAGLOC' AND msg_key = 'oumagloc.ieplevelmustbeentered'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIMMHOLO', 'oimmholo.ieplevelmustbeentered', 'IEP Level Must Be Entered', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIMMHOLO' AND msg_key = 'oimmholo.ieplevelmustbeentered'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDIPLAN', 'ocdiplan.verification', 'Verification', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDIPLAN' AND msg_key = 'ocdiplan.verification'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDCLOSE', 'ocdclose.closecommunitybooking', 'Close Community Booking', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDCLOSE' AND msg_key = 'ocdclose.closecommunitybooking'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDLEGLO', 'ocdleglo.charges', 'Charges', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDLEGLO' AND msg_key = 'ocdleglo.charges'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDRPLAN', 'oidrplan.housingaddresses', 'Housing Addresses', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDRPLAN' AND msg_key = 'oidrplan.housingaddresses'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDRPLAN', 'oidrplan.address', 'Address', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDRPLAN' AND msg_key = 'oidrplan.address'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDRPLAN', 'oidrplan.type', 'Type', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDRPLAN' AND msg_key = 'oidrplan.type'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDRPLAN', 'oidrplan.employments', 'Employments', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDRPLAN' AND msg_key = 'oidrplan.employments'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDRPLAN', 'oidrplan.employment', 'Employment', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDRPLAN' AND msg_key = 'oidrplan.employment'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDRPLAN', 'oidrplan.rplan-inst', '[Case Manager]*', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDRPLAN' AND msg_key = 'oidrplan.rplan-inst'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDRPLAN', 'oidrplan.rplan-comm', '[Parole Agent]', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDRPLAN' AND msg_key = 'oidrplan.rplan-comm'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDRPLAN', 'oidrplan.rplan-assess', '[Community Risk Assessment]', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDRPLAN' AND msg_key = 'oidrplan.rplan-assess'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDCLIST', 'ocdclist.appearancetype', 'Appearance Type', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDCLIST' AND msg_key = 'ocdclist.appearancetype'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDCLIST', 'ocdclist.appearancelocation', 'Appearance Location', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDCLIST' AND msg_key = 'ocdclist.appearancelocation'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'EOFFENDER', 'eoffender.view', 'View', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'EOFFENDER' AND msg_key = 'eoffender.view'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'EOFFENDER', 'eoffender.offenderalldoc', 'Offender All Documents', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'EOFFENDER' AND msg_key = 'eoffender.offenderalldoc'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDLEGLO', 'ocdleglo.saveAndCalculate', 'Save & Calculate', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDLEGLO' AND msg_key = 'ocdleglo.saveAndCalculate'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.addconditions', 'Add Conditions', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.addconditions'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.conditionCategory', 'Condition Category', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.conditionCategory'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.conditionsTitle', 'Conditions', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.conditionsTitle'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.legalTextofCondition', 'Legal Text of Condition', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.legalTextofCondition'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.endDateShouldNotLessThanStartDate', 'End Date Should Not Less Than Start Date', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.endDateShouldNotLessThanStartDate'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.thisprogramalreadyexists', 'This Program Already Exists', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.thisprogramalreadyexists'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.whencreatingcommunityconditiondeductions', 'When Creating Community Condition Deductions', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.whencreatingcommunityconditiondeductions'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.recordcannotdeleted', 'Record Cannot Deleted', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.recordcannotdeleted'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDPATTE', 'ocdpatte.providertype', 'Provider Type', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDPATTE' AND msg_key = 'ocdpatte.providertype'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDLEGLO', 'termToLine.pleaseaddimp', 'Please add Imprisonment term', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDLEGLO' AND msg_key = 'termToLine.pleaseaddimp'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDLEGLO', 'termToLine.susplessthanimp', 'The suspended term must be less than or equal to the imprisonment term', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDLEGLO' AND msg_key = 'termToLine.susplessthanimp'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDLEGLS', 'ocdlegls.operativeterm', 'Operative:', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDLEGLS' AND msg_key = 'ocdlegls.operativeterm'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDLEGLS', 'ocdlegls.operativeheading', 'Operative Term', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDLEGLS' AND msg_key = 'ocdlegls.operativeheading'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUUCOND', 'ocuucond.pleaseSelectLength', 'Please Select Length', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUUCOND' AND msg_key = 'ocuucond.pleaseSelectLength'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUUCOND', 'ocuucond.pleaseSelectUnit', 'Please Select Unit', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUUCOND' AND msg_key = 'ocuucond.pleaseSelectUnit'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDVISIT', 'oidvisit.theoffenderhasalreadybeenassignedtosameoffender', 'The Offender has already been assigned to the visit.', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDVISIT' AND msg_key = 'oidvisit.theoffenderhasalreadybeenassignedtosameoffender'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIMMHOLO', 'oimmholo.facilityieplevel', 'Facility IEP Level', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIMMHOLO' AND msg_key = 'oimmholo.facilityieplevel'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTEAMMAIN', 'ocmteammain.teamteamcode', 'Team Code*', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTEAMMAIN' AND msg_key = 'ocmteammain.teamteamcode'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTEAMMAIN', 'ocmteammain.adminlocation', 'Administrative Location', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTEAMMAIN' AND msg_key = 'ocmteammain.adminlocation'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTEAMMAIN', 'ocmteammain.tabtwotitle', 'Assign/Remove Staff', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTEAMMAIN' AND msg_key = 'ocmteammain.tabtwotitle'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTEAMMAIN', 'ocmteammain.maintainteams', 'Maintain Teams', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTEAMMAIN' AND msg_key = 'ocmteammain.maintainteams'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTEAMMAIN', 'ocmteammain.assignremovestaff', 'Assign/Remove Staff', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTEAMMAIN' AND msg_key = 'ocmteammain.assignremovestaff'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMUSERS', 'oumusers.firstname', 'First Name', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMUSERS' AND msg_key = 'oumusers.firstname'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMUSERS', 'oumusers.middleName', 'Middle Name', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMUSERS' AND msg_key = 'oumusers.middleName'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMUSERS', 'oumusers.insightsgroup', 'Insights Groups', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMUSERS' AND msg_key = 'oumusers.insightsgroup'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMUSERS', 'oumusers.lastname', 'Last Name', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMUSERS' AND msg_key = 'oumusers.lastname'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMSMALA', 'oumsmala.firstname', 'First Name', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMSMALA' AND msg_key = 'oumsmala.firstname'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMSMALA', 'oumsmala.middleName', 'Middle Name', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMSMALA' AND msg_key = 'oumsmala.middleName'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMSMALA', 'oumsmala.lastname', 'Last Name', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMSMALA' AND msg_key = 'oumsmala.lastname'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMSMALA', 'oumsmala.staff-role', 'Role', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMSMALA' AND msg_key = 'oumsmala.staff-role'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMSMALA', 'oumsmala.staff-unit', 'Staff Unit', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMSMALA' AND msg_key = 'oumsmala.staff-unit'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMCAMBPMN', 'oumcambpmn.servicebusinput', 'ServiceBus payLoad', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMCAMBPMN' AND msg_key = 'oumcambpmn.servicebusinput'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMCAMBPMN', 'oumcambpmn.queuename', 'ServiceBus QueueName', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMCAMBPMN' AND msg_key = 'oumcambpmn.queuename'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'EOFFENDER', 'eoffender.objectType', 'Screen', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'EOFFENDER' AND msg_key = 'eoffender.objectType'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDSCMOV', 'oidscmov.apperancetype', 'Appearance Type', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDSCMOV' AND msg_key = 'oidscmov.apperancetype'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDSCMOV', 'oidscmov.apperancelocation', 'Appearance Location', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDSCMOV' AND msg_key = 'oidscmov.apperancelocation'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIRREPORT', 'oirreport.edit', 'Edit', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIRREPORT' AND msg_key = 'oirreport.edit'); 

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIRREPORT', 'oirreport.parentLov', 'Parent LOV', 'LABEL',  current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIRREPORT' AND msg_key = 'oirreport.parentLov'); 

update SYSTEM_LABELS
set MSG_VALUE = 'Court must be entered',
MODIFY_USER_ID = 'OMS_OWNER' ,
modify_datetime = current_timestamp 
where MSG_KEY = 'common.courtmustbeentered'and module_name ='COMMON';  

update SYSTEM_LABELS
set MSG_VALUE = 'Matter(s)',
MODIFY_USER_ID = 'OMS_OWNER' ,
modify_datetime = current_timestamp 
where MSG_KEY = 'ocdclist.matter'and module_name ='OCDCLIST';  

update SYSTEM_LABELS
set MSG_VALUE = 'Staff I D',
MODIFY_USER_ID = 'OMS_OWNER' ,
modify_datetime = current_timestamp 
where MSG_KEY = 'oumsmala.staffid'and module_name ='OUMSMALA'; 

update system_labels set msg_value ='Screen',
MODIFY_USER_ID = 'OMS_OWNER' ,
modify_datetime = current_timestamp  where msg_key ='eoffender.objectType';  


