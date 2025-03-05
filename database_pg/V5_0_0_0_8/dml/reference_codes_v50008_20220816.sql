insert into oms_owner.reference_codes(domain,code,description,list_seq,active_flag,system_data_flag,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 values('SENT_TERM','LIFE','Life Term',99,'Y','Y',CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
 
insert into oms_owner.reference_codes(domain,code,description,list_seq,active_flag,system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 values('SENT_TYPE','SNCS','Supervised Non-Custodial Sentence',99,'Y','N',CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
 
insert into oms_owner.reference_codes(domain,code,description,list_seq,active_flag,system_data_flag, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 values('SENT_TYPE','UNCS','Unsupervised Non-Custodial Sentence',99,'Y','N',CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
 
--
INSERT INTO oms_owner.reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('GENDER', 'M', 'Male', 1, 'Y', 'N', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);

INSERT INTO oms_owner.reference_codes(domain, code, description, list_seq, active_flag, system_data_flag, expired_date, new_code, parent_code, parent_domain, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('GENDER', 'F', 'Female', 2, 'Y', 'N',  NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);

--
insert into oms_owner.reference_codes(domain,code,description,list_seq,active_flag,system_data_flag,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 values('LO_REL_TYPE','RFC','Upon Release from Custody',99,'Y','Y', CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
 
insert into oms_owner.reference_codes(domain,code,description,list_seq,active_flag,system_data_flag,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 values('CATEGORY','NCUS','Non-Custodial',99,'Y','Y', CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
 
update oms_owner.reference_codes set DESCRIPTION='Community' where domain='SENT_TERM' and code='SUP'; 