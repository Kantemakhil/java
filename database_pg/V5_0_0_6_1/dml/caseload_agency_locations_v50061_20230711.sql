UPDATE
	CASELOAD_AGENCY_LOCATIONS
SET
	caseload_id = trim(both from caseload_id),
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp 
 WHERE caseload_id <> trim(both from caseload_id);