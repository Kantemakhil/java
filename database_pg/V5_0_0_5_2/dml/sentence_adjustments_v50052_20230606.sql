update
	sentence_adjustments
set
	description = 'Initial Booking-Level Remission',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'BKG_INI_RM';
	
	
	
	update
	sentence_adjustments
set
	description = 'Loss of Booking-Level Remission',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'BKG_LR';
	
	
	
	update
	sentence_adjustments
set
	description = 'Return of Lost Booking-Level Remission',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'BKG_RLR';
	
	
	update
	sentence_adjustments
set
	description = 'Special Management Credit Days',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'SMCD';
	
	
	
update
	sentence_adjustments
set
	description = 'Escape',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'ESCP';
	
	
	update
	sentence_adjustments
set
	description = 'Return of Escape',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'R_ESCP';
	
	
	update
	sentence_adjustments
set
	description = 'Initial Sentence-Level Remission',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'SENT_INI_REM';
	
	
	update
	sentence_adjustments
set
	description = 'Loss of Sentence-level Remission',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'SENT_LR';
	
	
	update
	sentence_adjustments
set
	description = 'Return of Lost Sentence-Level Remission',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'SENT_RLR';
	
	
	
update
	sentence_adjustments
set
	description = 'Appeal Bail Release',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'APP';
	
	
	
update
	sentence_adjustments
set
	description = 'Return of Appeal',
	modify_datetime = current_timestamp ,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'RAPP';