insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.copyoversuccess','Please select Unique Weapon Used.','LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.noschedulestocopy','No Schedules From Previous Week to Copy.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.wapcommentmustbeentered','WAP Comment must be entered.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(NEXTval('LABLE_ID_SEQUENCE'),'OIDADMIS','oidadmis.activestatus','Status','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(NEXTval('LABLE_ID_SEQUENCE'),'OIDADMIS','oidadmis.bookstatus','Status','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 values (nextval('LABLE_ID_SEQUENCE'),'OUMUCREAT','oumucreat.pleaseselectinsightsgroupid','Please select the Insights group Id','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 values (nextval('LABLE_ID_SEQUENCE'),'OUMUCREAT','oumucreat.insightsgroup','Insights Groups','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
 
insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.savebeforefinalize','Please save the weekly activity plan details before finalize.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.savebeforeprint','Please save the weekly activity plan details before Print copy.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.emtagstarttime','EM Tag Start Time','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.emtagendtime','EM Tag End Time','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.emtagstartmustbeentered','EM Tag Start Time must be entered.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.emtagendmustbeentered','EM Tag End Time must be entered.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.emtaginfonotsavedprint','Please save EM Tag details before Print Copy.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.emtaginfonotsavedfinalize','Please save the EM Tage details before finalize.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

delete from SYSTEM_LABELS where MODULE_NAME = 'OWEACPLN' and msg_key = 'oweacpln.copyoversuccess';

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (Nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.copyoversuccess','Copy over successful.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--
UPDATE SYSTEM_LABELS SET MSG_VALUE = 'Elite 5.0.0.0.10' WHERE MODULE_NAME = 'LOGIN' AND MSG_KEY IN ('home.title.header', 'login.header');