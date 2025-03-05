DYNAMIC_SENTENCE_HISTORY_DATA{
	SELECT id,form_info_json form_info_json_blob ,form_identifier,create_datetime,create_user_id,modify_datetime,modify_user_id FROM %formName%_data_hty WHERE action_type  <> 'Verification'and  LOWER(FORM_IDENTIFIER::text) LIKE LOWER('%
}