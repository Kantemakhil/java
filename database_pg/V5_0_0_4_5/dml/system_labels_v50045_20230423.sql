INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.eventDatenotfilled', 'Event Date can not be blank.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.eventDatenotfilled');
   
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.eventtypenotfilled', 'Event type can not be blank.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.eventtypenotfilled');
   
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.adjustmandatoryinfowarn', 'Adjustment mandatory info not filled.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.adjustmandatoryinfowarn');
   
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.dategreaterthanvalidation', 'To date can not be earlier than from date.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.dategreaterthanvalidation');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.childrecordexist', 'Child record exist.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.childrecordexist');

INSERT INTO oms_owner.system_labels 
(label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
SELECT NEXTVAL('lable_id_sequence'),'OIDPAROE','oidparoe.fromDateCannotbelaterthansystemDate','From Date can not be greater than system date.','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.fromDateCannotbelaterthansystemDate');

INSERT INTO oms_owner.system_labels 
(label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
select NEXTVAL('lable_id_sequence'),'OIDPAROE','oidparoe.toDateCannotbelaterthansystemDate','To Date can not be greater than system date.','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.toDateCannotbelaterthansystemDate'); 

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
  SELECT nextval('lable_id_sequence'), 'OCDNCODE', 'ocdncode.conditionsallocatedtoorder', 'This order has conditions configured for allocation. Please ensure that the status of these conditions is up to date.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDNCODE' AND msg_key = 'ocdncode.conditionsallocatedtoorder'); 

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
  SELECT nextval('lable_id_sequence'), 'OCDPAROR', 'ocdparor.conditionsallocatedtoorder', 'This order has conditions configured for allocation. Please ensure that the status of these conditions is up to date.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDPAROR' AND msg_key = 'ocdparor.conditionsallocatedtoorder');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.staffmemberToCasePlan', 'Assigned Case Plan Staff', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.staffmemberToCasePlan'); 

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.staffName', 'Name', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.staffName');
 
insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.caseplanRole', 'Case Plan Role', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.caseplanRole');
 
insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.primaryFlag', 'Primary', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.primaryFlag');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.activeFlag', 'Active', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.activeFlag');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.startDate', 'Start', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.startDate');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.endDate', 'End', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.endDate');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.cannotdeactivatePrimaryStaff', 'you cannot deactive the primary staff member. Please assign a new primary staff member before deactivating.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.cannotdeactivatePrimaryStaff');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.cannotcheckprimaryKeyforInactiveCasePlan', 'Can not check primary key Inactive Case Plan', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.cannotcheckprimaryKeyforInactiveCasePlan');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.staffandcaseplanRole', 'this staff member is already assigned to the case plan', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.staffandcaseplanRole');
 
insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.atleastOnePrimaryCheckFalg', 'At least one primary checkFalg should be checked', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.atleastOnePrimaryCheckFalg');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.staffNameMustBeEntered', 'Staff Name Must Be Entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.staffNameMustBeEntered');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.startDatecannotbelater', 'Start Date Can not be later', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.startDatecannotbelater');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.startDatecannotbelater', 'Start Date Can not be later', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.startDatecannotbelater');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.assign', 'Assign', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.assign');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.exit', 'Exit', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.exit');
 
insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLAN', 'ocdiplan.custodialOfficer', 'Custodial Officer', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLAN' and msg_key = 'ocdiplan.custodialOfficer');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLAN', 'ocdiplan.youmustassignaprimaryofficer', 'You must assign a primary officer before saving this case plan', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLAN' and msg_key = 'ocdiplan.youmustassignaprimaryofficer');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCIIPLAN', 'ociiplan.lastPcoCaseNote', 'Last PCO Case Note', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCIIPLAN' and msg_key = 'ociiplan.lastPcoCaseNote');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OIIMYOFF', 'oiimyoff.myCasePlanRole', 'My Case Plan Role', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OIIMYOFF' and msg_key = 'oiimyoff.myCasePlanRole');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCIIPLAN', 'ociiplan.primaryOfficer', 'Primary custodial Officer', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCIIPLAN' and msg_key = 'ociiplan.primaryOfficer');

update
	system_labels system_labels
set
	msg_value = 'Community Officer ',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	module_name = 'OCIIPLAN'
	and msg_key = 'ociiplan.officer';


insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.primaryFlagChange', 'would you like to designate a new primary staff member?', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.primaryFlagChange');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLAN', 'ocdiplan.onlyoneCasePlanatatime', 'Cannot add more than one caseplan at a time', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLAN' and msg_key = 'ocdiplan.onlyoneCasePlanatatime'); 

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.enddatecannotearilerthenstartdate', 'End date can not eariler then start date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.enddatecannotearilerthenstartdate');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OCDIPLANPOP', 'ocdiplanpop.enddatecannotfeture', 'End Date can not be future', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OCDIPLANPOP' and msg_key = 'ocdiplanpop.enddatecannotfeture');

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.lossofremission', 'Loss of Remission cannot exceed maximum allowed remission.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OIDCUSTAD' and msg_key = 'oidcustad.lossofremission'); 
insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.remissionmore', 'Cannot return more remission than remission lost to date.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OIDCUSTAD' and msg_key = 'oidcustad.remissionmore'); 		
insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.escapemore', 'Cannot return more Escape time than the Escape time accrued to date.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OIDCUSTAD' and msg_key = 'oidcustad.escapemore'); 		

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
  SELECT nextval('lable_id_sequence'), 'OCDLEGLO', 'ocdleglo.orderisdependent', 'Order cannot be excluded. Other active orders are dependent on it.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OCDLEGLO' AND msg_key = 'ocdleglo.orderisdependent');

update system_labels
set
	msg_value = 'Key Dates' ,
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where msg_key = 'ocdsench.keydate';
	
update system_labels
set
	msg_value = 'Custodial Orders' ,
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where msg_key = 'ocdsench.custodailOrders';
	
update system_labels
set
	msg_value = 'Non-Custodial Orders' ,
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where msg_key = 'ocdsench.nonCustodailOrder';   