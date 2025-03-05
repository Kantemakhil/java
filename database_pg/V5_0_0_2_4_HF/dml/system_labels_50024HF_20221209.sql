insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDEDEMP','ocdedemp.validated','Validated','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDEDEMP','ocdedemp.addaddress','Add address','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDEDEMP','ocdedemp.updateaddress','Update address','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
update SYSTEM_LABELS set msg_value='Row already exists with the selected Visit Type' where module_name='OIMVLIMT'  and msg_key ='oimvlimt.rowalredyexist';

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIUZOHOS','oiuzohos.locations','Locations','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMVLIMT','oimvlimt.totaltimeforiepexceeded','The Number of Hours & Min has exceeded for the selected IEP Level','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMVLIMT','oimvlimt.totalvisforiepexceeded','The Number of Total Visits has exceeded for the selected IEP Level','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMVLIMT','oimvlimt.totaltimeforsecurityexceeded','The Number of Hours & Min has exceeded for the selected Security Level','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMVLIMT','oimvlimt.totalvisitforsecurityexceeded','The Number of Total Visits has exceeded for the selected Security Level','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
(nextval('LABLE_ID_SEQUENCE'),'COMMON','common.isvalidated','Validated?','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);


insert into OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
(nextval('LABLE_ID_SEQUENCE'),'COMMON','common.unitnumber','Unit Number','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);
  
----- Script already present in QA and we need to sent it to upper environments.
insert into SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 values( NEXTVAL('LABLE_ID_SEQUENCE') , 'OIDPWAIT', 'oidpwait.bulkAsignForAccreditedProgram', 'offenderName with offenderId having active non-assocation for bulk assignment to an accredited program'
               , 'LABEL', current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL);

--changed as per BA request		   
update
	system_labels
set
	modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,msg_value = 'Based On'
where
	module_name = 'OIMVLIMT'
	and msg_key = 'oimvlimt.iepseclevel';	   
  
-----------------------------------------------------------
update
	SYSTEM_LABELS
set
	modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,MSG_VALUE = 'Elite 5.0.0.2.4_HF'
where
	MODULE_NAME = 'LOGIN'
	and MSG_KEY in ('home.title.header', 'login.header');