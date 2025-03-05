insert into reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values  ('KEY_DATES', 'BOOKING_PEXD', 'Parole Expiration Date (PEXD)', (select max(list_seq)+1 from  reference_codes where domain = 'KEY_DATES'), 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.reference_codes (domain,code,description,list_seq,active_flag,system_data_flag,modify_user_id,create_datetime,create_user_id,modify_datetime,seal_flag) 
VALUES ('SENT_TERM','CS','Community Service',99,'Y','N',null,current_timestamp,'OMS_OWNER',current_timestamp,NULL); 