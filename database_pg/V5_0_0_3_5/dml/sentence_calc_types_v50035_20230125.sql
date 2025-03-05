INSERT INTO oms_owner.sentence_calc_types (sentence_calc_type,description,expiry_date,active_flag,list_seq,sentence_category,sentence_type,modify_datetime,modify_user_id,create_datetime,create_user_id) 
  VALUES ('SUSP','Suspended Imprisonment',NULL,'Y',99,'CUST','IMPS', current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER');

INSERT INTO oms_owner.sentence_calc_types (sentence_calc_type,description,expiry_date,active_flag,list_seq,sentence_category,sentence_type,modify_datetime,modify_user_id,create_datetime,create_user_id) 
  VALUES ('PSUS','Partially Suspended Sentence',NULL,'Y',99,'NCUS','SNCS', current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER');

INSERT INTO oms_owner.sentence_calc_types (sentence_calc_type,description,expiry_date,active_flag,list_seq,sentence_category,sentence_type,modify_datetime,modify_user_id,create_datetime,create_user_id) 
  VALUES ('WSUS','Wholly Suspended Sentence',NULL,'Y',99,'NCUS','SNCS', current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER');
  