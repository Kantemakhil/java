insert into OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
(nextval('LABLE_ID_SEQUENCE'),'COMMON','common.streetaddress','Street Address','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);


insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OCUNOQUE','ocunoque.nodataismodifiedtosave','No data is modified to save','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDIEPLV','oidieplv.duplicateIepLevelCode','A record with the same IEP Level and Date Assigned already exists','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.parameterSeq','Sequence','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.parameterName','Name','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.description','Description','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.parameterType','Type','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.optionalFlag','Required','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.parameterCode','Parameter Code','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.parameterDomain','Parameter Domain','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.parameterLovGroup','Parameter LOV Group','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.parameterLovSelect','LOV Query','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.parameterLovTitle','LOV Title','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.parameterSource','Parameter Found In','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.conflict','Conflict','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.coflictmessage','Not able to run the report because it has parameter mismatch with configured Database parameters.','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);  


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDADDRE','ocdaddre.enterstreetaddress','Please enter street address','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDADDRE','ocdaddre.entercity','Please enter city','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDADDRE','ocdaddre.enterstate','Please enter state','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDADDRE','ocdaddre.enterpostalcode','Please enter postal code','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDADDRE','ocdaddre.entercountry','Please enter country','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDADDRE','ocdaddre.enterfromdate','Please enter from date','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDADDRE','ocdaddre.fromdatecannotgreaterthancurrentdate','From date can not be grater than current date','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMVLIMT','oimvlimt.hoursmintotalvisitmustbeenter','Either "hours and  min" or total visit must be entered','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);

update system_labels set msg_value =
'The selected offender has a non-association with below offender(s), who has  waitlisted offenders to the same institutional activity with offender information '
where msg_key  = 'oidpawli.nonassociationconflictmsg';  

INSERT INTO oms_owner.system_labels (label_id,module_name,msg_key,msg_value,msg_type,create_user_id,modify_user_id) 
VALUES (NEXTVAL('lable_id_sequence'),'OIRREPORT','oirreport.editParamet','Add/Edit Parameter','LABEL','OMS_OWNER','OMS_OWNER');

INSERT INTO oms_owner.system_labels (label_id,module_name,msg_key,msg_value,msg_type,create_user_id,modify_user_id) 
VALUES (NEXTVAL('lable_id_sequence'),'OIIMPJRP','oiimpjrp.importedReportData','Imported Reports','LABEL','OMS_OWNER','OMS_OWNER');

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSCMOV','oidscmov.apperenceLocationmustentered','Appearance Location must be entered','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO oms_owner.system_labels (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES (NEXTVAL('lable_id_sequence'),'OIRMREPOR','oirmrepor.title','Manage Reports','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);    

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.chargedetails','Charge Details','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.category','Category','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.severity','Severity','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.indicatorcode','Indicator Code','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.plea','Plea','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.incidentDate','Incident Date','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.range',' /Range','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.particulars','Particulars','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.select','Select','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.exit','Exit','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCHGDT','ocdchgdt.details','Details','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
update SYSTEM_LABELS set msg_value='Row already exists with the selected Visit Type' where module_name='OIMVLIMT'  and msg_key ='oimvlimt.rowalredyexist'; 

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDTPCON','oidtpcon.canceltransfer','Cancel Transfer','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDTPCON','oidtpcon.cancelreason','Cancel Reason','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDTPCON','oidtpcon.cancellocation','Cancel Location','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDTPCON','oidtpcon.peningpropcontconfirmation','Pending Property Confirmation','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDTPCON','oidtpcon.cancelreasonmust','Cancel Reason Must be entered','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDTPCON','oidtpcon.cancellocationmust','Cancel Location Must be entered','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDRTCON','oidrtcon.movedto','Move To','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDRTCON','oidrtcon.reject','Reject','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDRTCON','oidrtcon.rejectreason','Reject Reason','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDRTCON','oidrtcon.rejectreasonmust','Reject Reason must be entered','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDRTCON','oidrtcon.movetolocationmust','Move to Location must be entered','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
update SYSTEM_LABELS
set MSG_VALUE = 'Password Reset successfully  and new password has sent to user’s email address. The newly generated password is ''%password%''.',
MODIFY_USER_ID = 'OMS_OWNER' ,
modify_datetime = current_timestamp 
where MSG_KEY = 'oumusers.passwordresetwithmailconfigured'and module_name ='OUMUSERS';



update SYSTEM_LABELS
set MSG_VALUE = 'Password Reset successfully and email could not be sent to User as there is no email configured. The newly generated password is ''%password%''.',
MODIFY_USER_ID = 'OMS_OWNER' ,
modify_datetime = current_timestamp 
where MSG_KEY = 'oumusers.passwordresetwithoutmailconfigured'and module_name ='OUMUSERS';  

update system_labels set modify_user_id = 'OMS_OWNER' ,
          modify_datetime = current_timestamp ,
          msg_value ='Move Selected Items to Container' where msg_key ='oidmpitm.moveTo';

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDMPCON','oidmpcon.containermoveconfirm','Do you want to confirm the conatiner move from one internal location to another?','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIPTRAN','oiiptran.actionreason','Action Reason','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);

-----------------------------------------------------------
update
	SYSTEM_LABELS
set
	modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,MSG_VALUE = 'Elite 5.0.0.2.4'
where
	MODULE_NAME = 'LOGIN'
	and MSG_KEY in ('home.title.header', 'login.header');