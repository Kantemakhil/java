delete from oms_owner.reference_codes where domain ='SENT_TERM' and code ='SUSP';

INSERT INTO oms_owner.reference_codes (domain,code,description,list_seq,active_flag,system_data_flag,modify_user_id,create_datetime,create_user_id,modify_datetime,seal_flag) 
  VALUES ('SENT_TERM','SUSP','Suspended',99,'Y','Y','OMS_OWNER',current_timestamp,'OMS_OWNER',current_timestamp,NULL);

delete from oms_owner.REFERENCE_CODES where DOMAIN = 'CHOUTUPREA' and CODE = 'LTO';

Insert into oms_owner.REFERENCE_CODES (DOMAIN,CODE,DESCRIPTION,LIST_SEQ,ACTIVE_FLAG,SYSTEM_DATA_FLAG,PARENT_CODE, create_datetime,create_user_id,modify_datetime, modify_user_id, seal_flag) 
   values ('CHOUTUPREA','LTO','Linked to Order',1,'N','Y',null,current_timestamp,'OMS_OWNER',NULL, NULL, NULL);

delete from oms_owner.reference_codes rc where domain = 'KEY_DATES';

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values  ('KEY_DATES', 'BOOKING_HED', 'Hold Expiry Date', 1, 'Y', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL);

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values ('KEY_DATES', 'BOOKING_PCD', 'Parole Commence Date', 2, 'Y', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL);

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values ('KEY_DATES', 'BOOKING_BCD', 'Bail Commence Date', 3, 'Y', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL);


insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values  ('KEY_DATES', 'BOOKING_LRD', 'Latest Release Date', 4, 'Y', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL);

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values ('KEY_DATES', 'BOOKING_PED', 'Parole Eligibility Date', 5, 'Y', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL);

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values ('KEY_DATES', 'BOOKING_RED', 'Remission Eligibility Date', 6, 'Y', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL);

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values ('KEY_DATES', 'BOOKING_ERD', 'Earliest Release Date', 7, 'Y', 'N', current_timestamp, 'OMS_OWNER', NULL, NULL, NULL);



