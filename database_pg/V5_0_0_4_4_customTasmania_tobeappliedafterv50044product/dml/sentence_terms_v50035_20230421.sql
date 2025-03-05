INSERT INTO oms_owner.sentence_terms (sentence_calc_type,term_code,active_flag,expiry_date,create_datetime,create_user_id,modify_datetime,modify_user_id,sentence_category) 
 SELECT 'SUSP','IMP','Y',NULL,current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER','CUST' 
 WHERE NOT EXISTS (SELECT 1 FROM sentence_terms WHERE sentence_calc_type = 'SUSP' AND term_code = 'IMP' AND sentence_category = 'CUST');

INSERT INTO oms_owner.sentence_terms (sentence_calc_type,term_code,active_flag,expiry_date,create_datetime,create_user_id,modify_datetime,modify_user_id,sentence_category) 
 SELECT 'SUSP','SUSP','Y',NULL,current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER','CUST' 
 WHERE NOT EXISTS (SELECT 1 FROM sentence_terms WHERE sentence_calc_type = 'SUSP' AND term_code = 'SUSP' AND sentence_category = 'CUST'); 

INSERT INTO oms_owner.sentence_terms (sentence_calc_type,term_code,active_flag,expiry_date,create_datetime,create_user_id,modify_datetime,modify_user_id,sentence_category) 
 SELECT 'PSUS','CS','Y',NULL,current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER','NCUS' 
  WHERE NOT EXISTS (SELECT 1 FROM sentence_terms WHERE sentence_calc_type = 'PSUS' AND term_code = 'CS' AND sentence_category = 'NCUS');

INSERT INTO oms_owner.sentence_terms (sentence_calc_type,term_code,active_flag,expiry_date,create_datetime,create_user_id,modify_datetime,modify_user_id,sentence_category) 
 SELECT 'WSUS','CS','Y',NULL,current_timestamp,'OMS_OWNER',current_timestamp,'OMS_OWNER','NCUS' 
 WHERE NOT EXISTS (SELECT 1 FROM sentence_terms WHERE sentence_calc_type = 'WSUS' AND term_code = 'CS' AND sentence_category = 'NCUS'); 
 