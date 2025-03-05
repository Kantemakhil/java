insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'BKG_INI_RM', 'Initial Booking-Level Remission', 'Credit', 'BKG_REMISS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'BKG_INI_RM' ); 

insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'BKG_LR', 'Loss of Booking-Level Remission', 'Debit', 'BKG_REMISS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'BKG_LR' ); 

insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'BKG_RLR', 'Return of Lost Booking-Level Remission', 'Credit', 'BKG_REMISS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'BKG_RLR' ); 

insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'SMCD', 'Specific Management Credit Days', 'Credit', 'BKG', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'SMCD' ); 

insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'ESCP', 'Escape', 'Debit', 'BKG', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'ESCP' ); 
 
 insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'R_ESCP', 'Return of Escape', 'Credit', 'BKG', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'R_ESCP' ); 
 
 insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'SENT_INI_REM', 'Initial Sentence-Level Remission', 'Credit', 'SENT_REMISS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'SENT_INI_REM' ); 
 
 insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'SENT_LR', 'Loss of Sentence-Level Remission', 'Debit', 'SENT_REMISS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'SENT_LR' ); 
 
 insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'SENT_RLR', ' Return of Lost of Sentence-Level Remission', 'Credit', 'SENT_REMISS', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'SENT_RLR' ); 
 
 insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'APP', 'Appeal Bail Release', 'Debit', 'SENT', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER',  null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'APP' ); 
 
 insert into SENTENCE_ADJUSTMENTS(SENTENCE_ADJUST_CODE, DESCRIPTION, DEBIT_CREDIT_CODE, USAGE_CODE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EXPIRY_DATE, ACTIVE_FLAG, SEAL_FLAG) 
 select 'RAPP', ' Return of Appeal', 'Credit', 'SENT', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', null, 'Y', null 
 where not exists ( select 1 from SENTENCE_ADJUSTMENTS where sentence_adjust_code = 'RAPP' );