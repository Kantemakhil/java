update
	SENTENCE_ADJUSTMENTS
set
	active_flag = 'N',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	sentence_adjust_code = 'BKG_INI_RM';

update
	sentence_adjustments
set
	active_flag = 'N',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	sentence_adjust_code = 'SENT_INI_REM' ;
	
update SENTENCE_ADJUSTMENTS set debit_credit_code='DR',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where debit_credit_code='Debit';
update SENTENCE_ADJUSTMENTS set debit_credit_code='CR',modify_datetime=current_timestamp,modify_user_id='OMS_OWNER' where debit_credit_code='Credit';	