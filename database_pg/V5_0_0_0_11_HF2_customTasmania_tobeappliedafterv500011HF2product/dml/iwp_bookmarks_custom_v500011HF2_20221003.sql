--17057,17058,17059
 INSERT INTO iwp_bookmarks (bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag) 
 VALUES ('S_FIRST_NAME','Y','TEXT','Scheduled Offender First NAme','select initcap(first_name)  from offenders where offender_id=(select offender_id from offender_bookings where offender_book_id=?)',current_timestamp,user,current_timestamp,user,current_timestamp,NULL,NULL,'N',NULL);

INSERT INTO iwp_bookmarks (bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag)  
VALUES ('S_FROM_TIME','Y','TEXT','offender schedule start time','select start_time from offender_ind_schedules where event_id = ? ',current_timestamp,user,current_timestamp,USER,current_timestamp,NULL,NULL,'N',NULL);
	 
INSERT INTO iwp_bookmarks (bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag)  
VALUES ('S_EVENT_DATE','Y','TEXT','offender scheduled date','select event_date from offender_ind_schedules where event_id = ? ',current_timestamp,USER,current_timestamp,USER,current_timestamp,NULL,NULL,'N',NULL);
	 	 
INSERT INTO iwp_bookmarks (bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag)  
VALUES ('S_OFF_LOC','Y','TEXT','Offender Scheduled Location','select description from agency_locations where agy_loc_id = (select TO_AGY_LOC_ID from OFFENDER_IND_SCHEDULES where event_id = ?)',current_timestamp,USER,current_timestamp,USER,current_timestamp,NULL,NULL,'N',NULL);	 
	 
INSERT INTO iwp_bookmarks (bookmark_name,active_flag,bookmark_type,description,sql_text,date_created,user_created,create_datetime,create_user_id,modify_datetime,modify_user_id,expiry_date,sql_verified_flag,seal_flag)  
VALUES ('S_SUB_TYPE','Y','TEXT','offender scheduled sub type','select event_sub_type from offender_ind_schedules where event_id = ? ',current_timestamp,USER,current_timestamp,USER,current_timestamp,NULL,NULL,'N',NULL);
	 	 
--	 
INSERT INTO iwp_bookmark_parameters (parameter_name,parameter_type,description,bookmark_name,date_created,user_created,parameter_data_type,create_datetime,create_user_id,modify_datetime,modify_user_id,active_flag,seal_flag)  
VALUES ('EVENT_ID','U','event id','S_EVENT_DATE',current_timestamp,USER,'N',current_timestamp,USER,current_timestamp,NULL,'Y',NULL);
	 
INSERT INTO iwp_bookmark_parameters (parameter_name,parameter_type,description,bookmark_name,date_created,user_created,parameter_data_type,create_datetime,create_user_id,modify_datetime,modify_user_id,active_flag,seal_flag) 
VALUES ('OFFENDER_BOOK_ID','U','Offender Book Id','S_FIRST_NAME',current_timestamp,USER,'N',current_timestamp,USER,current_timestamp,NULL,'Y',NULL);
	 
INSERT INTO iwp_bookmark_parameters (parameter_name,parameter_type,description,bookmark_name,date_created,user_created,parameter_data_type,create_datetime,create_user_id,modify_datetime,modify_user_id,active_flag,seal_flag) 
VALUES ('EVENT_ID','U','Event Id','S_FROM_TIME',current_timestamp,USER,'N',current_timestamp,USER,current_timestamp,NULL,'Y',NULL);
	 
INSERT INTO iwp_bookmark_parameters (parameter_name,parameter_type,description,bookmark_name,date_created,user_created,parameter_data_type,create_datetime,create_user_id,modify_datetime,modify_user_id,active_flag,seal_flag) 
VALUES ('EVENT_ID','U','EVENT_ID','S_OFF_LOC',current_timestamp,USER,'N',current_timestamp,USER,current_timestamp,NULL,'Y',NULL);
	 
INSERT INTO iwp_bookmark_parameters (parameter_name,parameter_type,description,bookmark_name,date_created,user_created,parameter_data_type,create_datetime,create_user_id,modify_datetime,modify_user_id,active_flag,seal_flag) 
VALUES ('EVENT_ID','U','Event id','S_SUB_TYPE',current_timestamp,USER,'N',current_timestamp,USER,current_timestamp,NULL,'Y',NULL);



