insert
	into
	json_spec (id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	SEAL_FLAG)
select 30,
'UPDATED_ACTIVE_OFF_FORMATTER',
'[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.activePrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.activePrisonerDetails[#2].demographics.firstName","middle_name":"body.activePrisonerDetails[#2].demographics.middleName","second_middle_name":"body.activePrisonerDetails[#2].demographics.secondMiddleName","last_name":"body.activePrisonerDetails[#2].demographics.surName","sex_code":"body.activePrisonerDetails[#2].demographics.gender","birth_date":"body.activePrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_begin_date":"body.activePrisonerDetails[#2].imprisonmentDetails.intakeDatetime"}},"offenderAddresses":{"*":{"address_line1":"body.activePrisonerDetails[#2].address.address_line","street":"body.activePrisonerDetails[#2].address.street","street_number":"body.activePrisonerDetails[#2].address.street_number","street_direction":"body.activePrisonerDetails[#2].address.street_direction","suite_number":"body.activePrisonerDetails[#2].address.suite_number","city":"body.activePrisonerDetails[#2].address.city_name","city_code":"body.activePrisonerDetails[#2].address.city_code","state_code":"body.activePrisonerDetails[#2].address.state_code","country_code":"body.activePrisonerDetails[#2].address.country_code","postal":"body.activePrisonerDetails[#2].address.postal_code","address":"body.activePrisonerDetails[#2].address.unstructured_address_line","no_fixed_address_flag":"body.activePrisonerDetails[#2].address.no_address_flag"}},"offNextCourtDate":{"*":{"event_date":"body.activePrisonerDetails[#2].appearance.nextCourtDate"}},"section68Details":{"*":{"section68":"body.activePrisonerDetails[#2].sentence.section68Flag"}},"homeDestinationDetails":{"*":{"homedestination":"body.activePrisonerDetails[#2].sentence.homeDetentionFlag"}},"releaseDates":{"*":{"erd":"body.activePrisonerDetails[#2].sentence.expectedReleaseDate"}}}},{"operation":"default","spec":{"header":{"intId":"UpdatedOffenderDetails"}}},{"operation":"modify-default-beta","spec":{"body":{"activePrisonerDetails":{"*":{"demographics":{"otherName":"TBD","aboriginalIndicator":"TBD"},"sentence":{"courtFileNumber":"TBD","lifeImprisonmentFlag":"TBD","fineAmount":"TBD"}}}}}}]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null
where
not exists (
select
	1
from
	json_spec
where
	id = 30
	and spec_key = 'UPDATED_ACTIVE_OFF_FORMATTER');
	
	
insert
	into
	json_spec (id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	SEAL_FLAG)
select 31,
'UPDATED_RELEASE_OFF_FORMATTER',
'[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.releasedPrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.releasedPrisonerDetails[#2].demographics.firstName","middle_name":"body.releasedPrisonerDetails[#2].demographics.middleName","second_middle_name":"body.releasedPrisonerDetails[#2].demographics.secondMiddleName","last_name":"body.releasedPrisonerDetails[#2].demographics.surName","sex_code":"body.releasedPrisonerDetails[#2].demographics.gender","birth_date":"body.releasedPrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_begin_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.intakeDatetime"}},"offenderAddresses":{"*":{"address_line1":"body.releasedPrisonerDetails[#2].address.address_line","street":"body.releasedPrisonerDetails[#2].address.street","street_number":"body.releasedPrisonerDetails[#2].address.street_number","street_direction":"body.releasedPrisonerDetails[#2].address.street_direction","suite_number":"body.releasedPrisonerDetails[#2].address.suite_number","city":"body.releasedPrisonerDetails[#2].address.city_name","city_code":"body.releasedPrisonerDetails[#2].address.city_code","state_code":"body.releasedPrisonerDetails[#2].address.state_code","country_code":"body.releasedPrisonerDetails[#2].address.country_code","postal":"body.releasedPrisonerDetails[#2].address.postal_code","address":"body.releasedPrisonerDetails[#2].address.unstructured_address_line","no_fixed_address_flag":"body.releasedPrisonerDetails[#2].address.no_address_flag"}},"offNextCourtDate":{"*":{"event_date":"body.releasedPrisonerDetails[#2].appearance.nextCourtDate"}},"section68Details":{"*":{"section68":"body.releasedPrisonerDetails[#2].sentence.section68Flag"}},"homeDestinationDetails":{"*":{"homedestination":"body.releasedPrisonerDetails[#2].sentence.homeDetentionFlag"}},"releaseDates":{"*":{"lrd":"body.releasedPrisonerDetails[#2].imprisonmentDetails.releaseDateTime","erd":"body.releasedPrisonerDetails[#2].sentence.expectedReleaseDate"}}}},{"operation":"default","spec":{"header":{"intId":"UpdatedOffenderDetails"}}},{"operation":"modify-default-beta","spec":{"body":{"releasedPrisonerDetails":{"*":{"demographics":{"otherName":"TBD","aboriginalIndicator":"TBD"},"sentence":{"courtFileNumber":"TBD","lifeImprisonmentFlag":"TBD","fineAmount":"TBD"}}}}}}]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER',
null
where
not exists (
select
	1
from
	json_spec
where
	id = 31
	and spec_key = 'UPDATED_RELEASE_OFF_FORMATTER');
	
	
	
update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"correlationId":"header.intCorrelationId","identifier":"body.personDetails.imprisonmentDetails.pin","nameDetails":{"*":{"first_name":"body.personDetails.demographics.firstName","last_name":"body.personDetails.demographics.surName","middle_name":"body.personDetails.demographics.middleName","second_middle_name":"body.personDetails.demographics.secondMiddleName","sex_code":"body.personDetails.demographics.gender","birth_date":"body.personDetails.demographics.dob"}},"aliasDetails":{"*":{"givenname":"body.personDetails.alias[#2].firstName","othergivennames":"body.personDetails.alias[#2].middleName","second_middle_name":"body.personDetails.alias[#2].secondMiddleName","familyname":"body.personDetails.alias[#2].surName","dateofbirth":"body.personDetails.alias[#2].dob"}},"warningDetails":{"*":{"description":"body.personDetails.alerts[#2].alertCode","alert_type":"body.personDetails.alerts[#2].alertType","createdate":"body.personDetails.alerts[#2].alertStartDate","expirydate":"body.personDetails.alerts[#2].alertEndDate","comment_text":"body.personDetails.alerts[#2].alertComment"}},"residenceDetails":{"*":{"streetnumber":"body.personDetails.address.road.roadNumber","streetname":"body.personDetails.address.road.roadName","city":"body.personDetails.address.localityName","state":"body.personDetails.address.stateOrTerritoryCode","country":"body.personDetails.address.countryName","postal":"body.personDetails.address.postCode","address":"body.personDetails.address.unstructuredAddressLine","recordeddate":"body.personDetails.address.recordedDate"}},"occupation":{"*":{"occupation":"body.personDetails.demographics.occupation"}},"bookingDetails":{"*":{"dateofdeath":"body.personDetails.demographics.dod"}},"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","body.trailer.responseDatetime"]}},"wantedDetails":{"*":{"wanted_type":"body.personDetails.wanted.wantedType","escape_comment_text":"body.personDetails.wanted.wantedComment"}}}},{"operation":"modify-default-beta","spec":{"body":{}}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"},"body":{"trailer":{"respCode":"Success","errMsg":""},"personDetails":{"imprisonmentDetails":[],"demographics":[],"address":[],"alerts":[],"alias":[],"wanted":[]}}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_EXTERNAL';
	
	
	
update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"pinDetails":{"*":{"pin":"body.prisonerDetails[#2].imprisonmentDetails.pin"}},"seriousOffenders":{"*":{"offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.prisonerDetails[#2].demographics.firstName","middle_name":"body.prisonerDetails[#2].demographics.middleName","second_middle_name":"body.prisonerDetails[#2].demographics.secondMiddleName","last_name":"body.prisonerDetails[#2].demographics.surName","birth_date":"body.prisonerDetails[#2].demographics.dob"}},"offenderAliasDetails":{"*":{"alias_details":{"value":{"*":"body.prisonerDetails[#4].aliases[]"}}}}}},{"operation":"default","spec":{"header":{"intId":"SeriousOffenders"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'SERIOUS_OFFENDER_FORMATTER';