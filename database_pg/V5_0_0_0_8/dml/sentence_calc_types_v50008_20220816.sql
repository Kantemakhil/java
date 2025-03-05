INSERT INTO oms_owner.sentence_calc_types (sentence_calc_type, description, expiry_date, active_flag, list_seq, sentence_category, sentence_type, program_method, header_seq, header_label, function_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
  VALUES('SUS', 'Suspended Imprisonment', NULL, 'Y', NULL, 'CUST', 'IMPS', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
 
INSERT INTO oms_owner.sentence_calc_types (sentence_calc_type, description, expiry_date, active_flag, list_seq, sentence_category, sentence_type, program_method, header_seq, header_label, function_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
  VALUES('LIF', 'Life Imprisonment', NULL, 'Y', NULL, 'CUST', 'IMPS', NULL, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
 
INSERT INTO oms_owner.sentence_calc_types (sentence_calc_type, description, expiry_date, active_flag, list_seq, sentence_category, sentence_type, program_method, header_seq, header_label, function_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES('CCO', 'Community Correction Order', NULL, 'Y', 99, 'NCUS', 'SNCS', NULL, 99, NULL, NULL, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);  
