--missed --v50035
delete from oms_owner.reference_codes where domain ='SENT_TERM' and code ='SUSP';

INSERT INTO oms_owner.reference_codes (domain,code,description,list_seq,active_flag,system_data_flag,modify_user_id,create_datetime,create_user_id,modify_datetime,seal_flag) 
  SELECT 'SENT_TERM','SUSP','Suspended',99,'Y','Y','OMS_OWNER',current_timestamp,'OMS_OWNER',current_timestamp,NULL 
  WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'SENT_TERM' AND code = 'SUSP');

--

delete from oms_owner.REFERENCE_CODES where DOMAIN = 'CHOUTUPREA' and CODE = 'LTO';

Insert into oms_owner.REFERENCE_CODES (DOMAIN,CODE,DESCRIPTION,LIST_SEQ,ACTIVE_FLAG,SYSTEM_DATA_FLAG,PARENT_CODE, create_datetime,create_user_id,modify_datetime, modify_user_id, seal_flag) 
   SELECT 'CHOUTUPREA','LTO','Linked to Order',1,'N','Y',null,current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER', NULL 
    WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'CHOUTUPREA' AND code = 'LTO');

--

delete from oms_owner.reference_codes rc where domain = 'KEY_DATES';

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT 'KEY_DATES', 'BOOKING_HED', 'Hold Expiry Date', 1, 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
    WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_HED');

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT 'KEY_DATES', 'BOOKING_PCD', 'Parole Commence Date', 2, 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
    WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_PCD');
	
insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT 'KEY_DATES', 'BOOKING_BCD', 'Bail Commence Date', 3, 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
    WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_BCD');

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT 'KEY_DATES', 'BOOKING_LRD', 'Latest Release Date', 4, 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
    WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_LRD');
	
insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT 'KEY_DATES', 'BOOKING_PED', 'Parole Eligibility Date', 5, 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
    WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_PED');	

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT 'KEY_DATES', 'BOOKING_RED', 'Remission Eligibility Date', 6, 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
    WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_RED');

insert into oms_owner.reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT 'KEY_DATES', 'BOOKING_ERD', 'Earliest Release Date', 7, 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
    WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_ERD');


--v50036 -- nothing related

--v50036HF

insert into reference_codes (domain,code,description,list_seq,active_flag, system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 SELECT 'KEY_DATES', 'BOOKING_PEXD', 'Parole Expiration Date (PEXD)', (select max(list_seq)+1 from  reference_codes where domain = 'KEY_DATES'), 'Y', 'N', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL 
   WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_PEXD');

INSERT INTO oms_owner.reference_codes (domain,code,description,list_seq,active_flag,system_data_flag,modify_user_id,create_datetime,create_user_id,modify_datetime,seal_flag) 
 SELECT 'SENT_TERM','CS','Community Service',99,'Y','N','OMS_OWNER',current_timestamp,'OMS_OWNER',current_timestamp,NULL 
     WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'SENT_TERM' AND code = 'CS');

--v50036HF1 -- nothing related 

--v50036HF2 -- nothing related 

--v50037 -- nothing related 

--v50040 -- nothing related 

--v50041 -- nothing related 

--v50042 -- nothing related 

--v50043

INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain,
	create_datetime, create_user_id, modify_datetime, modify_user_id)
 SELECT 'KEY_DATES', 'BOOKING_CRD', 'Confirmed Release Date', 10, 'Y', 'N', NULL, NULL, NULL, NULL,
   CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_CRD');
 
 --v50044
 
INSERT INTO reference_codes (DOMAIN, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id) 
 SELECT 'KEY_DATES', 'BOOKING_RED', 'Remission Eligibity Date', 99, 'Y', 'Y', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, 'OMS_OWNER' 
 WHERE NOT EXISTS (SELECT 1 FROM reference_codes WHERE domain = 'KEY_DATES' AND code = 'BOOKING_RED');
 