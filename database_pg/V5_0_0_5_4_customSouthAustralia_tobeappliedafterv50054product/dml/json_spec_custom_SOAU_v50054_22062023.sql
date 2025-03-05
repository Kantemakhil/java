update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"correlationId":"header.intCorrelationId","nameDetails":{"*":{"offender_id_display":"body.personDetails.imprisonmentDetails.pin","first_name":"body.personDetails.demographics.firstName","last_name":"body.personDetails.demographics.surName","sex_code":"body.personDetails.demographics.gender","birth_date":"body.personDetails.demographics.dob"}},"aliasDetails":{"*":{"givenname":"body.personDetails.alias[#2].firstName","eliteoffenderid":"body.personDetails.alias[#2].offenderId","familyname":"body.personDetails.alias[#2].lastname","dateofbirth":"body.personDetails.alias[#2].dob"}},"warningDetails":{"*":{"description":"body.personDetails.alerts[#2].alertCode","alert_type":"body.personDetails.alerts[#2].alertType","createdate":"body.personDetails.alerts[#2].alertStartDate","expirydate":"body.personDetails.alerts[#2].alertEndDate","comment_text":"body.personDetails.alerts[#2].alertComment"}},"residenceDetails":{"*":{"streetnumber":"body.personDetails.address.road.roadNumber","streetname":"body.personDetails.address.road.roadName","city":"body.personDetails.address.localityName","state":"body.personDetails.address.stateOrTerritoryCode","country":"body.personDetails.address.countryName","postal":"body.personDetails.address.postCode","address":"body.personDetails.address.unstructuredAddressLine","recordeddate":"body.personDetails.address.recordedDate"}},"occupation":{"*":{"occupation":"body.personDetails.demographics.occupation"}},"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","body.trailer.responseDatetime"]}}}},{"operation":"modify-default-beta","spec":{"body":{}}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"},"body":{"trailer":{"respCode":"Success","errMsg":""},"personDetails":{"imprisonmentDetails":{"pin":""},"demographics":{"firstName":"","surName":"","gender":"","dob":"","dod":"","occupation":""},"address":{"subComplexName":"","unitTypeName":"","subDwellingUnitNumber":"","level":{"typeCode":"","typeName":"","number":""},"addressSiteName":"","road":{"roadNumber":"","roadName":"","roadTypecode":"","roadSuffixName":""},"localityName":"","stateOrTerritoryCode":"","countryName":"","postCode":"","unstructuredAddressLine":"","recordedDate":""},"alerts":[{"alertCode":"","alertType":"","alertStartDate":"","alertEndDate":"","alertComment":""}],"alias":[{"firstName":"","offenderId":"","lastname":"","dob":""}],"wanted":[{"narrative":""}]}}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_EXTERNAL';
	
	
update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","body.trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId"}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"},"body":{"trailer":{"respCode":"Error","errMsg":"The prisioner does not Exist in Elite"}}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_ERROR';
	

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"ProcessInput":{"header":{"intCorrelationId":"correlationId"},"body":{"photoId":"photoId"}}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_REQ_FORMATTER';
	
	
update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","photographDetails":{"*":{"capture_date":"body.details.photographedDate","image_thumbnail":"body.details.photo"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhoto"}}},{"operation":"modify-default-beta","spec":{"trailer":{"respCode":"TBD","errMsg":""}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_RES_FORMATTER';
	
	
update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId"}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhoto"}}},{"operation":"modify-default-beta","spec":{"body":{"photos":[]},"trailer":{"respCode":"POORFORM","errMsg":"Invalid or Incorrect request document"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_EXCEPTION_FORMATTER';
	
	
update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId"}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhoto"}}},{"operation":"modify-default-beta","spec":{"body":{"photos":[]},"trailer":{"respCode":"NOPHOTO ","errMsg":"No Photo Exists on DB"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_ERROR_FORMATTER';
	
	
update
	json_spec
set
	json_specs = '[{"operation": "shift", "spec": { "ProcessInput": { "header": { "intCorrelationId": "correlationId" }, "body": { "imprisonmentDetails": { "pin": "identifier" }, "maxResultsReturned": "maxResultsReturned"} } } }]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_LIST_REQ_FORMATTER';
	
	
update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","photographListDetails":{"*":{"image_id":"body.photos[#2].photoId","capture_date":"body.photos[#2].photographedDate","image_thumbnail":"body.photos[#2].Photo"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhotoList"}}},{"operation":"modify-default-beta","spec":{"trailer":{"respCode":"TBD","errMsg":""}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_LIST_RES_FORMATTER';
	
	
update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId"}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhotoList"}}},{"operation":"modify-default-beta","spec":{"body":{"photos":[]},"trailer":{"respCode":"POORFORM","errMsg":"Invalid or Incorrect request document"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_LIST_ERROR_FORMATTER';
	
update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"seriousOffenders":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.pin","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.prisonerDetails[#2].demographics.firstName","middle_name":"body.prisonerDetails[#2].demographics.middleName","last_name":"body.prisonerDetails[#2].demographics.surName","birth_date":"body.prisonerDetails[#2].demographics.dob"}},"offenderAliasDetails":{"*":{"alias_details":{"value":{"*":"body.prisonerDetails[#4].aliases[]"}}}}}},{"operation":"default","spec":{"header":{"intId":"SeriousOffenders"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'SERIOUS_OFFENDER_FORMATTER';
	
	

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.activePrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.activePrisonerDetails[#2].demographics.firstName","middle_name":"body.activePrisonerDetails[#2].demographics.middleName","last_name":"body.activePrisonerDetails[#2].demographics.surName","sex_code":"body.activePrisonerDetails[#2].demographics.gender","birth_date":"body.activePrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.activePrisonerDetails[#2].imprisonmentDetails.pin","booking_begin_date":"body.activePrisonerDetails[#2].imprisonmentDetails.intakeDatetime","booking_status":"body.activePrisonerDetails[#2].imprisonmentDetails.imprisonmentStatus"}},"offenderHousingDetails":{"*":{"agy_loc_id":"body.activePrisonerDetails[#2].housing.facility","housing_lev_1_code":"body.activePrisonerDetails[#2].housing.unit","assignment_time":"body.activePrisonerDetails[#2].housing.assignDate","assignment_end_time":"body.activePrisonerDetails[#2].housing.endDate"}},"offenderReleaseDetails":{"*":{"release_date":"body.activePrisonerDetails[#2].imprisonmentDetails.releaseDateTime"}},"escapeDetails":{"*":{"escape_date":"body.activePrisonerDetails[#2].imprisonmentDetails.escapeDatetime","recapture_date":"body.activePrisonerDetails[#2].imprisonmentDetails.recaptureDatetime"}},"offenderAliasDetails":{"*":{"alias_details":{"value":{"*":"body.activePrisonerDetails[#4].aliases[]"}}}}}},{"operation":"default","spec":{"header":{"intId":"CurrentPrisoners"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'ACTIVE_PRISONER_FORMATTER';
	


update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.escapedPrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.escapedPrisonerDetails[#2].demographics.firstName","middle_name":"body.escapedPrisonerDetails[#2].demographics.middleName","last_name":"body.escapedPrisonerDetails[#2].demographics.surName","sex_code":"body.escapedPrisonerDetails[#2].demographics.gender","birth_date":"body.escapedPrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.escapedPrisonerDetails[#2].imprisonmentDetails.pin","booking_begin_date":"body.escapedPrisonerDetails[#2].imprisonmentDetails.intakeDatetime","booking_status":"body.escapedPrisonerDetails[#2].imprisonmentDetails.imprisonmentStatus"}},"offenderHousingDetails":{"*":{"agy_loc_id":"body.escapedPrisonerDetails[#2].housing.facility","housing_lev_1_code":"body.escapedPrisonerDetails[#2].housing.unit","assignment_time":"body.escapedPrisonerDetails[#2].housing.assignDate","assignment_end_time":"body.escapedPrisonerDetails[#2].housing.endDate"}},"escapeDetails":{"*":{"escape_date":"body.escapedPrisonerDetails[#2].imprisonmentDetails.escapeDatetime","recapture_date":"body.escapedPrisonerDetails[#2].imprisonmentDetails.recaptureDatetime"}},"offenderAliasDetails":{"*":{"alias_details":{"value":{"*":"body.escapedPrisonerDetails[#4].aliases[]"}}}}}},{"operation":"modify-default-beta","spec":{"body":{"escapedPrisonerDetails":{"*":{"imprisonmentDetails":{"releaseDateTime":null}}}}}},{"operation":"default","spec":{"header":{"intId":"CurrentPrisoners"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'ESCAPED_PRISONER_FORMATTER';



update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.releasedPrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.releasedPrisonerDetails[#2].demographics.firstName","middle_name":"body.releasedPrisonerDetails[#2].demographics.middleName","last_name":"body.releasedPrisonerDetails[#2].demographics.surName","sex_code":"body.releasedPrisonerDetails[#2].demographics.gender","birth_date":"body.releasedPrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.releasedPrisonerDetails[#2].imprisonmentDetails.pin","booking_begin_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.intakeDatetime","booking_status":"body.releasedPrisonerDetails[#2].imprisonmentDetails.imprisonmentStatus"}},"offenderHousingDetails":{"*":{"agy_loc_id":"body.releasedPrisonerDetails[#2].housing.facility","housing_lev_1_code":"body.releasedPrisonerDetails[#2].housing.unit","assignment_time":"body.releasedPrisonerDetails[#2].housing.assignDate","assignment_end_time":"body.releasedPrisonerDetails[#2].housing.endDate"}},"offenderReleaseDetails":{"*":{"release_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.releaseDateTime"}},"escapeDetails":{"*":{"escape_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.escapeDatetime","recapture_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.recaptureDatetime"}},"offenderAliasDetails":{"*":{"alias_details":{"value":{"*":"body.releasedPrisonerDetails[#4].aliases[]"}}}}}},{"operation":"default","spec":{"header":{"intId":"CurrentPrisoners"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'RELEASED_PRISONER_FORMATTER';	