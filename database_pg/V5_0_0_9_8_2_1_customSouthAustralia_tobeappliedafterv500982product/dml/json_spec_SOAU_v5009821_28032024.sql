update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails.imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhysicalDescription"},"trailer":{"respCode":"NOPERSON","errMsg":"A record could not be found in the iSAFE that matches the Subject PIN"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHYSICAL_DESC_ERROR_FORMATTER';
	
	
update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails[#2].imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"ParoleConditions"},"trailer":{"respCode":"NOPERSON","errMsg":"A record could not be found in the iSAFE that matches the Subject PIN"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PAROLE_COND_ERROR_FORMATTER';
	

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","identifier":"body.personDetails.imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"},"trailer":{"respCode":"NOPERSON","errMsg":"A record could not be found in the iSAFE that matches the Subject PIN"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_ERROR';
	
	
update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId"}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhotoList"}}},{"operation":"modify-default-beta","spec":{"body":{"photos":[]},"trailer":{"respCode":"NOPERSON","errMsg":"A record could not be found in the iSAFE that matches the Subject PIN"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_LIST_ERROR_FORMATTER';