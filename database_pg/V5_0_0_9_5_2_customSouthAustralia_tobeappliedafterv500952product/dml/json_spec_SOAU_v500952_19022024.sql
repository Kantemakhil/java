update
    json_spec
set
    json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.releasedPrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.releasedPrisonerDetails[#2].demographics.firstName","middle_name":"body.releasedPrisonerDetails[#2].demographics.middleName","second_middle_name":"body.releasedPrisonerDetails[#2].demographics.secondMiddleName","last_name":"body.releasedPrisonerDetails[#2].demographics.surName","sex_code":"body.releasedPrisonerDetails[#2].demographics.gender","birth_date":"body.releasedPrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_begin_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.intakeDatetime"}},"offenderAddresses":{"*":{"address_line1":"body.releasedPrisonerDetails[#2].address.address_line","street":"body.releasedPrisonerDetails[#2].address.street","street_number":"body.releasedPrisonerDetails[#2].address.street_number","street_direction":"body.releasedPrisonerDetails[#2].address.street_direction","suite_number":"body.releasedPrisonerDetails[#2].address.suite_number","city":"body.releasedPrisonerDetails[#2].address.city_name","city_code":"body.releasedPrisonerDetails[#2].address.city_code","state_code":"body.releasedPrisonerDetails[#2].address.state_code","country_code":"body.releasedPrisonerDetails[#2].address.country_code","postal":"body.releasedPrisonerDetails[#2].address.postal_code","address":"body.releasedPrisonerDetails[#2].address.unstructured_address_line","no_fixed_address_flag":"body.releasedPrisonerDetails[#2].address.no_address_flag"}},"offNextCourtDate":{"*":{"event_date":"body.releasedPrisonerDetails[#2].appearance.nextCourtDate"}},"section68Details":{"*":{"section68":"body.releasedPrisonerDetails[#2].sentence.section68Flag"}},"homeDestinationDetails":{"*":{"homedestination":"body.releasedPrisonerDetails[#2].sentence.homeDetentionFlag"}},"releaseDates":{"*":{"expected_release_date":"body.releasedPrisonerDetails[#2].sentence.expectedReleaseDate"}},"releaseDetails":{"*":{"release_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.releaseDateTime"}}}},{"operation":"default","spec":{"header":{"intId":"UpdatedOffenderDetails"}}},{"operation":"modify-default-beta","spec":{"body":{"releasedPrisonerDetails":{"*":{"demographics":{"otherName":"TBD","aboriginalIndicator":"TBD"},"sentence":{"courtFileNumber":"TBD","lifeImprisonmentFlag":"TBD","fineAmount":"TBD"}}}}}}]',
    modify_datetime = current_timestamp,
    modify_user_id = 'OMS_OWNER'
where
    spec_key = 'UPDATED_RELEASE_OFF_FORMATTER';
	

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails[#2].imprisonmentDetails.pin","paroleDateDetails":{"*":{"parolecommencedate":"body.prisonerDetails[#4].release[#2].releasestartDate","paroleexpirydate":"body.prisonerDetails[#4].release[#2].releasedueEndDate"}},"paroleOrderDetails":{"*":{"number":"body.prisonerDetails[#4].release[#2].order.number","typecode":"body.prisonerDetails[#4].release[#2].order.type","origincode":"body.prisonerDetails[#4].release[#2].order.origin","issueddate":"body.prisonerDetails[#4].release[#2].order.issuedDate","orderstartdate":"body.prisonerDetails[#4].release[#2].order.orderedStartDate","orderenddate":"body.prisonerDetails[#4].release[#2].order.orderedEndDate"}},"paroleConditionDetails":{"*":{"conditions":{"value":{"*":"body.prisonerDetails[#4].release[#2].order.condition[]"}}}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"release":{"*":{"orderCourtFileNumber":"TBD","order":{"condition":{}}}}}}}}},{"operation":"default","spec":{"header":{"intId":"ParoleConditions"},"trailer":{"respCode":"TBD","errMsg":""}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PAROLE_COND_RESPONSE_FORMATTER';
	

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails[#2].imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"ParoleConditions"},"trailer":{"respCode":"TBD","errMsg":"The prisoner does not Exist in Elite"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PAROLE_COND_ERROR_FORMATTER';
	

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"correlationId":"header.intCorrelationId","identifier":"body.personDetails.imprisonmentDetails.pin","nameDetails":{"*":{"first_name":"body.personDetails.demographics.firstName","last_name":"body.personDetails.demographics.surName","middle_name":"body.personDetails.demographics.middleName","second_middle_name":"body.personDetails.demographics.secondMiddleName","sex_code":"body.personDetails.demographics.gender","birth_date":"body.personDetails.demographics.dob"}},"deathDetails":{"*":{"death_date":"body.personDetails.demographics.dod"}},"aliasDetails":{"*":{"givenname":"body.personDetails.alias[#2].firstName","othergivennames":"body.personDetails.alias[#2].middleName","second_middle_name":"body.personDetails.alias[#2].secondMiddleName","familyname":"body.personDetails.alias[#2].surName","dateofbirth":"body.personDetails.alias[#2].dob"}},"warningDetails":{"*":{"description":"body.personDetails.alerts[#2].alertCode","alert_type":"body.personDetails.alerts[#2].alertType","createdate":"body.personDetails.alerts[#2].alertStartDate","expirydate":"body.personDetails.alerts[#2].alertEndDate","comment_text":"body.personDetails.alerts[#2].alertComment"}},"residenceDetails":{"*":{"address_line1":"body.personDetails.address.address_line","street":"body.personDetails.address.street","street_number":"body.personDetails.address.street_number","street_direction":"body.personDetails.address.street_direction","suite_number":"body.personDetails.address.suite_number","city_name":"body.personDetails.address.city_name","city_code":"body.personDetails.address.city_code","state_code":"body.personDetails.address.state_code","country_code":"body.personDetails.address.country_code","postal":"body.personDetails.address.postal_code","address":"body.personDetails.address.unstructured_address_line","no_fixed_address_flag":"body.personDetails.address.no_address_flag"}},"occupation":{"*":{"occupation":"body.personDetails.demographics.occupation"}},"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"wantedDetails":{"*":{"wanted_type":"body.personDetails.wanted.wantedType","escape_date_time":"body.personDetails.wanted.wantedDateTime","escape_comment_text":"body.personDetails.wanted.wantedComment"}}}},{"operation":"modify-default-beta","spec":{"body":{}}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"},"body":{"personDetails":{"imprisonmentDetails":[],"demographics":[],"address":[],"alerts":[],"alias":[],"wanted":[]}},"trailer":{"respCode":"Success","errMsg":""}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_EXTERNAL';
	
	
update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","identifier":"body.personDetails.imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"},"trailer":{"respCode":"Error","errMsg":"The prisoner does not Exist in Elite"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_ERROR';	