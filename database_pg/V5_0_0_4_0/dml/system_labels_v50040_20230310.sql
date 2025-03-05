update SYSTEM_LABELS
set MSG_VALUE = 'Password Reset successfully and new password has sent to users email address. The newly generated password is ''%password%''.',
MODIFY_USER_ID = 'OMS_OWNER' ,
modify_datetime = current_timestamp 
where MSG_KEY = 'oumusers.passwordresetwithmailconfigured' and module_name ='OUMUSERS';

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIMYOFF','oiimyoff.lastname','Last Name','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIMYOFF','oiimyoff.firstname','First Name','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIMYOFF','oiimyoff.middlename','Middle Name','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIMYOFF','oiimyoff.pid','PID','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIMYOFF','oiimyoff.intakenumber','Intake Number','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIMYOFF','oiimyoff.facility','Facility','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIMYOFF','oiimyoff.housinglocation','Housing Location','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIMYOFF','oiimyoff.myoffenders','My Offenders','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.lastname','Last Name','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.firstname','First Name','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.middlename','Middle Name','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.pid','PID','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.intakenumber','Intake Number','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.communityoffice','Community Office','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.primaryownership','Primary Ownership','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.conditionallocated','Condition Allocated','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.courtreport','Court Report','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.courtaction','Court Action','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCIMYOFF','ocimyoff.myoffenders','My Offenders','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.title','Related Modules','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.modules','Modules','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.relatedModules','Related Modules','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.accessModuleName','AccessModuleName','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.description','Description','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.moduleName','ModuleName','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.listSeq','Sequence ','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.accessModuleNameMand','AccessModuleName must be entered','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.listSeqMand','Sequence must be entered','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.accessModuleNameAndModuleName','AccessModuleName and ModuleName should not be same','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.accessModuleNameDuplicate','AccessModuleName should not be duplicate','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMRELMD','oumrelmd.listSeqDuplicate','Sequence cannot be duplicate','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMUSERS','oumusers.removeInsightUser','Revoke Insights Access','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDSABUS','ocdsabus.sttablerror','Cannot delete Substance record when matching Treatment records exist.','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.overrideCheck','O','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'EOFFENDER','eoffender.documents','Documents','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCUVERKD','ocuverkd.action','Action','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCUVERKD','ocuverkd.date','Date','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCUVERKD','ocuverkd.time','Time','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCUVERKD','ocuverkd.userid','User Id','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.refersh','Refresh Data','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL);
  
  
update SYSTEM_LABELS set modify_datetime = current_timestamp ,modify_user_id = 'OMS_OWNER' ,MSG_VALUE = 'Verify' where MODULE_NAME = 'OCUVERKD' and MSG_KEY = 'ocuverkd.ok';

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIRREPORT','oirreport.errorMsg','Not able to generate report. Please contact to the Administrator.','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER',NULL); 
  
------------------------------------------------------------------
update
	SYSTEM_LABELS
set
	modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,MSG_VALUE = 'Elite 5.0.0.4.0'
where
	MODULE_NAME = 'LOGIN'
	and MSG_KEY in ('home.title.header', 'login.header');    