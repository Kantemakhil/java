INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPWAIT', 'oidpwait.removeFromList', 'is removed from this program service?', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPWAIT' AND msg_key = 'oidpwait.removeFromList');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCISCATA', 'ociscata.removeFromList', 'is removed from this program service?', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCISCATA' AND msg_key = 'ociscata.removeFromList');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'PROSDEAC', 'prosdeac.title', 'Process Activation/Deactivation', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'PROSDEAC' AND msg_key = 'prosdeac.title');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'PROSDEAC', 'prosdeac.process', 'Process', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'PROSDEAC' AND msg_key = 'prosdeac.process');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'PROSDEAC', 'prosdeac.suspend', 'Suspend', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'PROSDEAC' AND msg_key = 'prosdeac.suspend');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'PROSDEAC', 'prosdeac.activate', 'Activate', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'PROSDEAC' AND msg_key = 'prosdeac.activate');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'PROSDEAC', 'prosdeac.delete', 'Delete', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'PROSDEAC' AND msg_key = 'prosdeac.delete');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'PROSDEAC', 'prosdeac.processes', 'Processes', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'PROSDEAC' AND msg_key = 'prosdeac.processes');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'PROSDEAC', 'prosdeac.activationordeacyivation', 'Activation/Deactivation', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'PROSDEAC' AND msg_key = 'prosdeac.activationordeacyivation');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.maintaintierdefaultevents', 'Maintain Tier Default Events', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.maintaintierdefaultevents');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.selecttier', 'Select Tier', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.selecttier');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.scheduletype', 'Schedule Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.scheduletype');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.schedulesubtype', 'Schedule Sub Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.schedulesubtype');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.rowalreadyexists', 'Row already exists.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.rowalreadyexists');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.recordcannotbecreatedormodified', 'Record cannot be created or modified.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.recordcannotbecreatedormodified');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.scheduletypemustbeentered', 'Schedule Type Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.scheduletypemustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.schedulesubtypemustbeentered', 'Schedule Sub Type Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.schedulesubtypemustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.enddatemustbeentered', 'End Date Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.enddatemustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.numberofoccurrencesmustbeentered', 'Number of Occurrences Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.numberofoccurrencesmustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.repeateverymustbeentered', 'Repeat Every Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.repeateverymustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.pleaseselectatleastoneweekday', 'Please select atleast one weekday', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.pleaseselectatleastoneweekday');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMTIDET', 'ocmtidet.enddateshouldbefuturedate', 'End Date should be future date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIDET' AND msg_key = 'ocmtidet.enddateshouldbefuturedate');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.maintaindefstaffposworkloads', 'Maintain Default Staff Position Workloads', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.maintaindefstaffposworkloads');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.assignstartingworkloadunits', 'Assign Starting Workload Units', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.assignstartingworkloadunits');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.nonoffspecifictasksforposition', 'Non-Offender-Specific Tasks for Position', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.nonoffspecifictasksforposition');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.position', 'Position', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.position');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.defaultstartingunits', 'Default Starting Units', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.defaultstartingunits');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.agencylocation', 'Agency Location', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.agencylocation');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.workloadtasktype', 'Workload Task Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.workloadtasktype');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.units', 'Units', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.units');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.positionmustbeentered', 'Position Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.positionmustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.defaultstartingunitsmustbeentered', 'Default Starting Units Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.defaultstartingunitsmustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.positionisalreadyexists', 'Position is already exists', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.positionisalreadyexists');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.agencylocationmustbeentered', 'Agency Location Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.agencylocationmustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.workloadtasktypemustbeentered', 'Workload Task Type Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.workloadtasktypemustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.unitsmustbeentered', 'Units Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.unitsmustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMDSPWD', 'ocmdspwd.agylocandwrkloadtaskalreadyexist', 'Agency Location and Workload task type already exist', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMDSPWD' AND msg_key = 'ocmdspwd.agylocandwrkloadtaskalreadyexist');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMSMALA', 'oumsmala.fte', 'FTE', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMSMALA' AND msg_key = 'oumsmala.fte');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMSMALA', 'oumsmala.ftemustbeentered', 'FTE must be entered.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMSMALA' AND msg_key = 'oumsmala.ftemustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMSMALA', 'oumsmala.fterange', 'Must be in range 00.00 to 99.99.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMSMALA' AND msg_key = 'oumsmala.fterange');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDLEGLO', 'ocdleglo.pleasedeactivateconditionfirst', 'Please deactivate conditions associated with this order prior to deactivating the order.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDLEGLO' AND msg_key = 'ocdleglo.pleasedeactivateconditionfirst');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDNCODE', 'ocdncode.pleasedeactivateconditionfirst', 'Please deactivate conditions associated with this order prior to deactivating the order.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDNCODE' AND msg_key = 'ocdncode.pleasedeactivateconditionfirst');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDPAROR', 'ocdparor.pleasedeactivateconditionfirst', 'Please deactivate conditions associated with this order prior to deactivating the order.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDPAROR' AND msg_key = 'ocdparor.pleasedeactivateconditionfirst');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDBAILO', 'ocdbailo.pleasedeactivateconditionfirst', 'Please deactivate conditions associated with this order prior to deactivating the order.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDBAILO' AND msg_key = 'ocdbailo.pleasedeactivateconditionfirst');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUUCOND', 'ocuucond.linkedorderisinactive', 'Linked order is not active', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUUCOND' AND msg_key = 'ocuucond.linkedorderisinactive');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.linkedorderisinactive', 'Linked order is not active', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.linkedorderisinactive');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMLESET', 'ocmleset.screenId', 'Legals Settings', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMLESET' AND msg_key = 'ocmleset.screenId');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMLESET', 'ocmleset.orderstiltle', 'Order & Condition Statuses', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMLESET' AND msg_key = 'ocmleset.orderstiltle');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMLESET', 'ocmleset.setting', 'Setting', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMLESET' AND msg_key = 'ocmleset.setting');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMLESET', 'ocmleset.active', 'Active', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMLESET' AND msg_key = 'ocmleset.active');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMLESET', 'ocmleset.save', 'Save', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMLESET' AND msg_key = 'ocmleset.save');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMLESET', 'ocmleset.activemusenter', 'Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMLESET' AND msg_key = 'ocmleset.activemusenter');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMLESET', 'ocmleset.savebtn', 'Save', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMLESET' AND msg_key = 'ocmleset.savebtn');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCMLESET', 'ocmleset.clearbtn', 'Clear', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMLESET' AND msg_key = 'ocmleset.clearbtn');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.paroleEvents', 'Parole Events', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.paroleEvents');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.date', 'Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.date');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.event', 'Event', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.event');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.comment', 'Comment', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.comment');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.adjustments', 'Adjustments', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.adjustments');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.type', 'Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.type');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.postedDate', 'Posted Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.postedDate');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.fromDate', 'From Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.fromDate');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.toDate', 'To Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.toDate');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.days', 'Days', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.days');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.eventDateCannotbelaterthansystemDate', 'Event Date cannot be later than system date.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.eventDateCannotbelaterthansystemDate');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.initialParoleEventnotCreated', 'Initial parole event not created.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.initialParoleEventnotCreated');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDRELSC', 'oidrelsc.confirmedreleasedatemustbeentered', 'Confirmed Release Date must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDRELSC' AND msg_key = 'oidrelsc.confirmedreleasedatemustbeentered');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OUMSMALA', 'oumsmala.workloadcapacity', 'Workload Capacity', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMSMALA' AND msg_key = 'oumsmala.workloadcapacity');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.officernonoffenderspecifictasks', 'Officer Non-Offender-Specific Tasks', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.officernonoffenderspecifictasks');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.officername', 'Officer Name', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.officername');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.ftestatus', 'FTE Status', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.ftestatus');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.position', 'Position', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.position');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.defaultstartingunits', 'Default Starting Units', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.defaultstartingunits');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.unitsavailableforoffenders', 'Units Available for Offenders', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.unitsavailableforoffenders');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.nonoffenderspecifictasks', 'Non-Offender-Specific Tasks', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.nonoffenderspecifictasks');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.active', 'Active', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.active');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.nonoffenderspecifictasksmustbeenterd', 'Non-Offender-Specific Tasks must be enterd.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.nonoffenderspecifictasksmustbeenterd');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.resetdefaulttaskasignment', 'Reset Default Task Asignment', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.resetdefaulttaskasignment');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDORASS', 'ocdorass.staffmemberselected', 'Staff Member Selected', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDORASS' AND msg_key = 'ocdorass.staffmemberselected');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDORASS', 'ocdorass.remainingworkloadunits', 'Remaining Workload Units', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDORASS' AND msg_key = 'ocdorass.remainingworkloadunits');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDORASS', 'ocdorass.workloadunits', 'Workload Units', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDORASS' AND msg_key = 'ocdorass.workloadunits');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUCOFFE', 'ocucoffe.secondmiddlename', 'Second Middle Name', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUCOFFE' AND msg_key = 'ocucoffe.secondmiddlename');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OSIOSEAR', 'osiosear.secondmiddlename', 'Second Middle Name', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OSIOSEAR' AND msg_key = 'osiosear.secondmiddlename');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCDALIAS', 'ocdalias.secondmiddlename', 'Second Middle Name', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDALIAS' AND msg_key = 'ocdalias.secondmiddlename');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUCNPER', 'ocucnper.secondmiddlename', 'Second Middle Name', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUCNPER' AND msg_key = 'ocucnper.secondmiddlename');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OSIPSEAR', 'osipsear.secondmiddlename', 'Second Middle Name', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OSIPSEAR' AND msg_key = 'osipsear.secondmiddlename');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.applytoall', 'Apply To all', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCONDAWAIT' AND msg_key = 'ocondawait.applytoall');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.selectall', 'Select all', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCONDAWAIT' AND msg_key = 'ocondawait.selectall');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.enddateshouldbelessthansentenceenddate', 'End Date should not be later than Order End Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.enddateshouldbelessthansentenceenddate');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OCUACOND', 'ocuacond.startshouldnotbeearlierthansentenceorderdate', 'Start Date should not be earlier than Order Start Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCUACOND' AND msg_key = 'ocuacond.startshouldnotbeearlierthansentenceorderdate');
 
 
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDVCONT', 'oidvcont.recordnotsavedpleaseselectdifferenttransaction', 'Record not saved. Please select a different transaction or proceed with breaking the seal', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDVCONT' AND msg_key = 'oidvcont.recordnotsavedpleaseselectdifferenttransaction');  
   
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'COMMON', 'common.warning', 'Warning', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'COMMON' AND msg_key = 'common.warning');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIITGDET', 'oiitgdet.detailaccessible', 'Detail Accessible', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIITGDET' AND msg_key = 'oiitgdet.detailaccessible');
  
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIMTGOPT', 'oimtgopt.history', 'History', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIMTGOPT' AND msg_key = 'oimtgopt.history');
  
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDEHLOC', 'oidehloc.nonassocationmsg', 'The selected offender has a non-association with the following offender(s) housed at the selected location.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDEHLOC' AND msg_key = 'oidehloc.nonassocationmsg');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDEHLOC', 'oidehloc.doyouwanttoproced', 'Before proceeding with the location change, please investigate possible risk. Only proceed if you are satisfied with the risk. Do you wish to proceed?', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDEHLOC' AND msg_key = 'oidehloc.doyouwanttoproced');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDEHLOC', 'oidehloc.overrideLocation', 'This user do not have acess to override the location', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDEHLOC' AND msg_key = 'oidehloc.overrideLocation');
   
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OCDONOST', 'ocdonost.nonoffenderspecifictaskalreadyexist', 'Non-Offender-Specific Task already exist', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDONOST' AND msg_key = 'ocdonost.nonoffenderspecifictaskalreadyexist');
   
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
  SELECT nextval('lable_id_sequence'), 'OCDNCODE', 'ocdncode.orderisdependent', 'Order cannot be excluded. Other active orders are dependent on it.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDNCODE' AND msg_key = 'ocdncode.orderisdependent');
   
 
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 SELECT nextval('lable_id_sequence'), 'OUMBMARK', 'oumbmark.moduleblock', 'Module Block', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMBMARK' AND msg_key = 'oumbmark.moduleblock');


   INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 SELECT nextval('lable_id_sequence'), 'OUMBMARK', 'oumbmark.field', 'Field', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OUMBMARK' AND msg_key = 'oumbmark.field');   
  
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OCMTIRLV', 'ocmtirlv.assingedtierlvldateshouldbegreaterthanorequaltooffbkngdate', 'Assinged Tier Level date should be greater than or equal to Offender Booking date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCMTIRLV' AND msg_key = 'ocmtirlv.assingedtierlvldateshouldbegreaterthanorequaltooffbkngdate');
   
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIIOSCED', 'oiiosced.time', 'Time', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIIOSCED' AND msg_key = 'oiiosced.time');
   
  INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIIOSCED', 'oiiosced.court', 'Court', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIIOSCED' AND msg_key = 'oiiosced.court');
   
    INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIIOSCED', 'oiiosced.scheduletype', 'Schedule Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIIOSCED' AND msg_key = 'oiiosced.scheduletype');
  
  
      INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIIOSCED', 'oiiosced.schedulereason', 'Schedule Reason', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIIOSCED' AND msg_key = 'oiiosced.schedulereason');
   
  
      INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIIOSCED', 'oiiosced.appearanceType', 'Appearance Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIIOSCED' AND msg_key = 'oiiosced.appearanceType');
  
  
      INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIIOSCED', 'oiiosced.appearanceLocation', 'Appearance Location', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIIOSCED' AND msg_key = 'oiiosced.appearanceLocation');
  
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIIOSCED', 'oiiosced.eventDate', 'Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIIOSCED' AND msg_key = 'oiiosced.eventDate');
   
  
  INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIISCHED', 'oiisched.appearanceType', 'Appearance Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIISCHED' AND msg_key = 'oiisched.appearanceType');
  
  INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIISCHED', 'oiisched.appearanceLocation', 'Appearance Location', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIISCHED' AND msg_key = 'oiisched.appearanceLocation');  