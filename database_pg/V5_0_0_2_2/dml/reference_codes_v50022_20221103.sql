Insert into REFERENCE_CODES (DOMAIN,CODE,DESCRIPTION,LIST_SEQ,ACTIVE_FLAG,SYSTEM_DATA_FLAG, create_datetime, create_user_id, modify_datetime) 
 values ('INT_PARTIES','JUD','Judicial Officer',1,'Y','N',current_timestamp, 'OMS_OWNER', current_timestamp);

Insert into REFERENCE_CODES (DOMAIN,CODE,DESCRIPTION,LIST_SEQ,ACTIVE_FLAG,SYSTEM_DATA_FLAG, create_datetime, create_user_id, modify_datetime) 
 values ('INT_PARTIES','DEF','Defence Solicitor',2,'Y','N',current_timestamp, 'OMS_OWNER', current_timestamp);

Insert into REFERENCE_CODES (DOMAIN,CODE,DESCRIPTION,LIST_SEQ,ACTIVE_FLAG,SYSTEM_DATA_FLAG, create_datetime, create_user_id, modify_datetime) 
 values ('INT_PARTIES','PROS','Prosecution',3,'Y','N',current_timestamp, 'OMS_OWNER', current_timestamp);

INSERT INTO reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('PS_PRG_STAT', 'ACCEP', 'Accepted', 1, 'Y', 'Y', 'OMS_OWNER', NULL, NULL, NULL, NULL,current_timestamp, 'OMS_OWNER', current_timestamp, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('PROCESS_CATE', 'PRODUCT', 'Product', 1, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('PROCESS_CATE', 'CLIENT', 'Client', 2, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL);

INSERT INTO oms_owner.reference_codes
(domain, code, description, list_seq, active_flag, system_data_flag, modify_user_id, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, seal_flag)
VALUES('PROCESS_CATE', 'SYSTEM', 'Internal Use', 3, 'Y', 'N', 'OMS_OWNER', NULL, NULL, NULL, NULL, current_timestamp, 'OMS_OWNER', current_timestamp, NULL);