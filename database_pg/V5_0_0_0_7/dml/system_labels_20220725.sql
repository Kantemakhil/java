INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
   VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLO', 'ocdchgsu.selectonechg', 'Please select atleast one charge', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
   
INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
   VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLO', 'ocdchgsu.cantunlink', 'Cannot unlink Inactive/ Final charge', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
-----   
INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
   VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLO', 'termToLine.duplicateterms', 'Term Type cannot be duplicate', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES (NEXTVAL('lable_id_sequence'),'OCDCLOGS','ocdclogs.emailScheduleHrsBeforemust','Email Schedule Hours Before must be entered','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

-------
INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES (NEXTVAL('lable_id_sequence'),'OCDCLOGS','ocdclogs.smsScheduleHrsBeforemust','Sms Schedule Hours Before must be entered','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES(nextval('lable_id_sequence'), 'OCMXPSTM', 'ocmxpstm.updateallowflag', 'Update Allow Flag', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES(nextval('lable_id_sequence'), 'OCMXPSTM', 'ocmxpstm.updatereasonflag', 'Update Reason Flag', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES(nextval('lable_id_sequence'),'OCUUPSTA','ocuupsta.user','User','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES(nextval('lable_id_sequence'),'OCUUPSTA','ocuupsta.comment','Comment','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES(nextval('lable_id_sequence'),'OCUUPSTA','ocuupsta.warnflag','This action cannot be undone. Do you wish to proceed?','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES(nextval('lable_id_sequence'),'OCUUPSTA','ocuupsta.pleaseselectnewstatus','Please Update The Status','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES(nextval('lable_id_sequence'),'OCUUPSTA','ocuupsta.reasonforsuspendorendprogrammustbeenter','Reason for Suspending or Ending Program must be entered.','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

-----
INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCSPROGR', 'ocsprogr.referraldate', 'Referral Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCSPROGR', 'ocsprogr.ordercommencementdate', 'Order Commencement Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCSPROGR', 'ocsprogr.expectedcompletiondate', 'Expected Completion Date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCSPROGR', 'ocsprogr.providertype', 'Provider Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCSPROGR', 'ocsprogr.programtype', 'Program Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCSPROGR', 'ocsprogr.legalorderdetails', 'Legal Order Details', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCSPROGR', 'ocsprogr.offenderdetails', 'Offender Details', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCSPROGR', 'ocsprogr.provider', 'Provider', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCSPROGR', 'ocsprogr.pleaseselectheprovidervalue', 'Please select provider value', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCSPROGR', 'ocsprogr.offenderspecialisedprograminquiry', 'Offender Specialized Programs Inquiry', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
   VALUES ( NEXTVAL('lable_id_sequence'), 'OCDXPROG', 'ocdxprog.proceedwithsaving', 'Do you want proceed with saving.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCDXPROG', 'ocdxprog.enddateisgreaterthansentenceenddate', 'End date is later than order end date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCDXPROG', 'ocdxprog.enddateisgreaterthanoffenderstartdate', 'End Date must be greater than or equal to the Offender Start date	', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCDXPROG', 'ocdxprog.expectedcompletiondateismorethan2years', 'Expected completion date is more than 2 years', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCDXPROG', 'ocdxprog.enddateisgreaterthansentencestartdate', 'Expected completion date later than sentence order date', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCDXPROG', 'ocdxprog.sentence', 'Sentence', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( nextval('lable_id_sequence'), 'OCDXPROG', 'ocdxprog.unlinksentence', 'Unlink Sentence', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)  
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.transferconditions', 'Transfer Conditions', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.sentencetype', 'sentence Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.assigntostaffmember', 'Assign to Staff Member', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.assigntoteam', 'Assign to Team', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.assigntoteammember', 'Assign to Team Member', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.assigntoanotherlocation', 'Assign to Another Location', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.offendersandcommunitysentences', 'Offenders', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.condition', 'Conditions', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.staffmember', 'Staff Member', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.team', 'Team', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.teammember', 'Team Member', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.matter', 'Matter(s)', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.select', 'Select', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.conditiondescription', 'Condition Description', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)  
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.assignedteam', 'Assigned Team', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.assignedofficer', 'Assigned Officer', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.Legaltextcondition', 'Legal Text Of Condition', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)  
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.toanotherstaffmember', 'To Another Staff Member', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.toanotherteam', 'To Another Team', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.toanotherteammember', 'To Another Team Member', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.toanotherlocation', 'To Another Location', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDTRF', 'ocondtrf.numberofoffenders', 'Number of Transfered Offender(s)', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.trackconditionstransfer', 'Track Conditions Transfer', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.fromlocation', 'From Location', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)  
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.offenders', 'Offenders', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.conditions', 'Conditions', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.matter', 'Matter(s)', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.sentencetype', 'Sentence Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)  
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.cancel', 'Cancel', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.conditiondescription', 'Condition Description', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.previouslyallocatedto', 'Previously Allocated To', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.transfertoteam', 'Transfer To(Team)', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.transfertoloc', 'Transfer To(Location)', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.selectcondition', 'Please select atleast one Condition', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.teamcode', 'Team Code', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.description', 'Description', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OTKCONDTRF', 'otkcondtrf.numberofoffenders', 'Number of Assigned Offender(s)', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

------
INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.sentenceType', 'Sentence Type', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.matter', 'Matter(s)', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.select', 'Select', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.assignedTeam', 'Assigned Team', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.legalTextOfCondition', 'Legal Text Of Condition', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.receivedfromteam', 'Received from(Team)', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.receivedfromlocation', 'Received from(Location)', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.assigntostaffmember', 'Assign to Staff Member', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.assigntoteam', 'Assign to Team', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.assigntoteammember', 'Assign to Team Member', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.assigntoanotherlocation', 'Assign to Another Location', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.teamcode', 'Team Code', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.description', 'description', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.panetitle', 'Conditions Awaiting Assignment', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.offenders', 'Offenders', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.conditions', 'Conditions', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.conditiondescription', 'Condition Description', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.selectonecondition', 'Please select atleast one Condition', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.selectoneassignee', 'Please Select Any One of the Assignee', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.multipleteamsassignedtocond', 'Multiple Teams have been assigned to this Condition', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels( label_id , module_name , msg_key , msg_value , msg_type , create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCONDAWAIT', 'ocondawait.numberofoffenders', 'Number of Awaiting Offender(s)', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

----
UPDATE SYSTEM_LABELS SET MSG_VALUE = 'Elite 5_0_0_0_7' WHERE MODULE_NAME = 'LOGIN' AND MSG_KEY IN ('home.title.header', 'login.header');


