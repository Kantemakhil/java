INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.years', 'Years', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.years'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.months', 'Months', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.months'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.weeks', 'Weeks', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.weeks'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.days', 'Days', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.days'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.totallengthcannotbezero', 'Total length can not be zero', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.totallengthcannotbezero'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.pleaseselect', 'Please select years or months or weeks or days', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.pleaseselect'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.negativenotallow', 'Negative value is not allowed ', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.negativenotallow'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.providewholenumbers', 'Please provide only whole numbered values', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.providewholenumbers'); 
 
 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.providedecimals', 'Please provide decimal(only 0.5) values ', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.providedecimals'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.adjustmentType', 'Adjustment Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.adjustmentType'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.transctionType', 'Debit/Credit', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.transctionType'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.PostedDate', 'Posted Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.PostedDate'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.fromDate', 'From Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.fromDate'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.toDate', 'To Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.toDate'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.duration', 'Duration', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
 WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.duration'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.checkremission', 'Please check Remission Eligibity', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.checkremission'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.durationmust', 'Duraion Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.durationmust'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.cannotdeletemaster', 'Cannot delete master Initial Booking Remission ', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.cannotdeletemaster'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.actionnotallowed', 'This action is not allowed when remission adjustments have been entered.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.actionnotallowed'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.save', 'Save', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.save'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.remissionDuration', 'Remission Duration', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.remissionDuration'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.custodyscreenid', 'Custody Adjustments', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.custodyscreenid'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.bookinglevelbooking', 'Current Booking-Level Remission', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.bookinglevelbooking'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.bookingadjustments', 'Booking Adjustments', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.bookingadjustments'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.comment', 'Comment', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.comment'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.remissioneligibility', 'Remission Eligibity', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.remissioneligibility');

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.close', 'Close', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.close'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.youcannotclear', 'you can not clear value', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.youcannotclear'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.firstrecordmust', 'The first record must be Intial Booking Level Remission', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.firstrecordmust'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.fromdatecannotbelater', 'From date cannot be later than To date. ', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.fromdatecannotbelater'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.adjustmentypemust', 'Adjustment Type Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.adjustmentypemust'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.postdatemust', 'Posted Date Must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.postdatemust'); 

 INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.bookingAdjustment', 'Booking Adjustments', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDCUSTAD' AND msg_key = 'oidcustad.bookingAdjustment');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.eventDate', 'Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.eventDate');
   
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.paroleEvent', 'Event', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.paroleEvent');
   
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.addupdateremoverecordfail', 'Record update failled.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.addupdateremoverecordfail');

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.addupdateremoverecordsuccess', 'Record updated successfully.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
  WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.addupdateremoverecordsuccess');   

INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OMUAVBED', 'omuavbed.selectedOffender', 'The selected offender ', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OMUAVBED' AND msg_key = 'omuavbed.selectedOffender');
  
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT nextval('lable_id_sequence'), 'OMUAVBED', 'omuavbed.housedinsameunit', 'has a non-association with the following offender(s) housed at the selected location.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OMUAVBED' AND msg_key = 'omuavbed.housedinsameunit');
   
INSERT INTO system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
  SELECT nextval('lable_id_sequence'), 'OIDPAROE', 'oidparoe.postedDateCannotbelaterthansystemDate', 'Posted date can not be future date.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDPAROE' AND msg_key = 'oidparoe.postedDateCannotbelaterthansystemDate');
   
insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 select nextval('lable_id_sequence'), 'OIDADMIS', 'oidadmis.doYouWant',
'Before proceeding with the location change, please investigate possible risk. Only proceed if you are satisfied with the risk. Do you wish to proceed?', 
'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null 
WHERE NOT EXISTS (SELECT 1 FROM system_labels WHERE module_name = 'OIDADMIS' AND msg_key = 'oidadmis.doYouWant');

update system_labels 
 set msg_value = 'Before proceeding with the location change, please investigate possible risk. Only proceed if you are satisfied with the risk. Do you wish to proceed?',
     modify_datetime = current_timestamp, modify_user_id = 'OMS_OWNER' 
  WHERE module_name = 'OIDADMIS' AND msg_key = 'oidadmis.doYouWant'; 

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.datecannotbefuturedate', 'Date cannot be future date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OIDCUSTAD' and msg_key = 'oidcustad.datecannotbefuturedate'); 

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.rowalreadyexists', 'Row exists already with same Adjustment type,Posted Date,duration', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OIDCUSTAD' and msg_key = 'oidcustad.rowalreadyexists'); 		

insert into system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) select nextval('lable_id_sequence'), 'OIDCUSTAD', 'oidcustad.adjustmentnotallowed', 'This adjustment cannot be entered as the offender is ineligible for remission.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null where not exists ( select 1 from system_labels where module_name = 'OIDCUSTAD' and msg_key = 'oidcustad.adjustmentnotallowed');