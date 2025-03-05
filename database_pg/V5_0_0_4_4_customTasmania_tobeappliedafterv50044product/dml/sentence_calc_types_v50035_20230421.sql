INSERT INTO oms_owner.sentence_calc_types (sentence_calc_type,description,expiry_date,active_flag,list_seq,sentence_category,sentence_type,modify_datetime,modify_user_id,create_datetime,create_user_id) 
  SELECT 'SUSP','Suspended Imprisonment',NULL,'Y',99,'CUST','IMPS', current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER' 
    WHERE NOT EXISTS (SELECT 1 FROM sentence_calc_types WHERE sentence_calc_type = 'SUSP' AND sentence_category = 'CUST');

INSERT INTO oms_owner.sentence_calc_types (sentence_calc_type,description,expiry_date,active_flag,list_seq,sentence_category,sentence_type,modify_datetime,modify_user_id,create_datetime,create_user_id) 
  SELECT 'PSUS','Partially Suspended Sentence',NULL,'Y',99,'NCUS','SNCS', current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER' 
    WHERE NOT EXISTS (SELECT 1 FROM sentence_calc_types WHERE sentence_calc_type = 'PSUS' AND sentence_category = 'NCUS');  

INSERT INTO oms_owner.sentence_calc_types (sentence_calc_type,description,expiry_date,active_flag,list_seq,sentence_category,sentence_type,modify_datetime,modify_user_id,create_datetime,create_user_id) 
  SELECT 'WSUS','Wholly Suspended Sentence',NULL,'Y',99,'NCUS','SNCS', current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER' 
   WHERE NOT EXISTS (SELECT 1 FROM sentence_calc_types WHERE sentence_calc_type = 'WSUS' AND sentence_category = 'NCUS');  
  
  