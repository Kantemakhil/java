update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"seriousOffenders":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.pin","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.prisonerDetails[#2].demographics.firstName","middle_name":"body.prisonerDetails[#2].demographics.middleName","second_middle_name":"body.prisonerDetails[#2].demographics.secondMiddleName","last_name":"body.prisonerDetails[#2].demographics.surName","birth_date":"body.prisonerDetails[#2].demographics.dob"}},"offenderAliasDetails":{"*":{"alias_details":{"value":{"*":"body.prisonerDetails[#4].aliases[]"}}}}}},{"operation":"default","spec":{"header":{"intId":"SeriousOffenders"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'SERIOUS_OFFENDER_FORMATTER';

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.activePrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.activePrisonerDetails[#2].demographics.firstName","middle_name":"body.activePrisonerDetails[#2].demographics.middleName","second_middle_name":"body.activePrisonerDetails[#2].demographics.secondMiddleName","last_name":"body.activePrisonerDetails[#2].demographics.surName","sex_code":"body.activePrisonerDetails[#2].demographics.gender","birth_date":"body.activePrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.activePrisonerDetails[#2].imprisonmentDetails.pin","booking_begin_date":"body.activePrisonerDetails[#2].imprisonmentDetails.intakeDatetime","booking_status":"body.activePrisonerDetails[#2].imprisonmentDetails.imprisonmentStatus"}},"offenderHousingDetails":{"*":{"agy_loc_id":"body.activePrisonerDetails[#2].housing.facility","agy_loc_id_desc":"body.activePrisonerDetails[#2].housing.facilityName","housing_lev_1_code":"body.activePrisonerDetails[#2].housing.unit","assignment_time":"body.activePrisonerDetails[#2].housing.assignDate","assignment_end_time":"body.activePrisonerDetails[#2].housing.endDate"}},"offenderReleaseDetails":{"*":{"release_date":"body.activePrisonerDetails[#2].imprisonmentDetails.releaseDateTime"}},"escapeDetails":{"*":{"escape_date":"body.activePrisonerDetails[#2].imprisonmentDetails.escapeDatetime","recapture_date":"body.activePrisonerDetails[#2].imprisonmentDetails.recaptureDatetime"}},"offenderAliasDetails":{"*":{"alias_details":{"value":{"*":"body.activePrisonerDetails[#4].aliases[]"}}}}}},{"operation":"default","spec":{"header":{"intId":"CurrentPrisoners"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'ACTIVE_PRISONER_FORMATTER';

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.escapedPrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.escapedPrisonerDetails[#2].demographics.firstName","middle_name":"body.escapedPrisonerDetails[#2].demographics.middleName","second_middle_name":"body.escapedPrisonerDetails[#2].demographics.secondMiddleName","last_name":"body.escapedPrisonerDetails[#2].demographics.surName","sex_code":"body.escapedPrisonerDetails[#2].demographics.gender","birth_date":"body.escapedPrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.escapedPrisonerDetails[#2].imprisonmentDetails.pin","booking_begin_date":"body.escapedPrisonerDetails[#2].imprisonmentDetails.intakeDatetime","booking_status":"body.escapedPrisonerDetails[#2].imprisonmentDetails.imprisonmentStatus"}},"offenderHousingDetails":{"*":{"agy_loc_id":"body.escapedPrisonerDetails[#2].housing.facility","agy_loc_id_desc":"body.escapedPrisonerDetails[#2].housing.facilityName","housing_lev_1_code":"body.escapedPrisonerDetails[#2].housing.unit","assignment_time":"body.escapedPrisonerDetails[#2].housing.assignDate","assignment_end_time":"body.escapedPrisonerDetails[#2].housing.endDate"}},"escapeDetails":{"*":{"escape_date":"body.escapedPrisonerDetails[#2].imprisonmentDetails.escapeDatetime","recapture_date":"body.escapedPrisonerDetails[#2].imprisonmentDetails.recaptureDatetime"}},"offenderAliasDetails":{"*":{"alias_details":{"value":{"*":"body.escapedPrisonerDetails[#4].aliases[]"}}}}}},{"operation":"modify-default-beta","spec":{"body":{"escapedPrisonerDetails":{"*":{"imprisonmentDetails":{"releaseDateTime":null}}}}}},{"operation":"default","spec":{"header":{"intId":"CurrentPrisoners"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'ESCAPED_PRISONER_FORMATTER';

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.releasedPrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.releasedPrisonerDetails[#2].demographics.firstName","middle_name":"body.releasedPrisonerDetails[#2].demographics.middleName","second_middle_name":"body.releasedPrisonerDetails[#2].demographics.secondMiddleName","last_name":"body.releasedPrisonerDetails[#2].demographics.surName","sex_code":"body.releasedPrisonerDetails[#2].demographics.gender","birth_date":"body.releasedPrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.releasedPrisonerDetails[#2].imprisonmentDetails.pin","booking_begin_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.intakeDatetime","booking_status":"body.releasedPrisonerDetails[#2].imprisonmentDetails.imprisonmentStatus"}},"offenderHousingDetails":{"*":{"agy_loc_id":"body.releasedPrisonerDetails[#2].housing.facility","agy_loc_id_desc":"body.releasedPrisonerDetails[#2].housing.facilityName","housing_lev_1_code":"body.releasedPrisonerDetails[#2].housing.unit","assignment_time":"body.releasedPrisonerDetails[#2].housing.assignDate","assignment_end_time":"body.releasedPrisonerDetails[#2].housing.endDate"}},"offenderReleaseDetails":{"*":{"release_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.releaseDateTime"}},"escapeDetails":{"*":{"escape_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.escapeDatetime","recapture_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.recaptureDatetime"}},"offenderAliasDetails":{"*":{"alias_details":{"value":{"*":"body.releasedPrisonerDetails[#4].aliases[]"}}}}}},{"operation":"default","spec":{"header":{"intId":"CurrentPrisoners"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'RELEASED_PRISONER_FORMATTER';

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.prisonerDetails[#2].demographics.firstName","middle_name":"body.prisonerDetails[#2].demographics.middleName","second_middle_name":"body.prisonerDetails[#2].demographics.secondMiddleName","last_name":"body.prisonerDetails[#2].demographics.surName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell","iep_level":"body.prisonerDetails[#2].housing.regime"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails[#2].imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails[#2].imprisonmentDetails.releaseReason"}},"offenderWarnings":{"*":{"code":"body.prisonerDetails[#4].alerts[#2].alertCode","createdate":"body.prisonerDetails[#4].alerts[#2].alertStartDate","expirydate":"body.prisonerDetails[#4].alerts[#2].alertEndDate","comment_text":"body.prisonerDetails[#4].alerts[#2].alertComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"financials":{"transactionSpendLimit":" "}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkLOCA"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PRISONER_MOVEMENTS_FORMATTER';

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails[#2].demographics.firstName","middle_name":"body.prisonerDetails[#2].demographics.middleName","second_middle_name":"body.prisonerDetails[#2].demographics.secondMiddleName","last_name":"body.prisonerDetails[#2].demographics.surName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell","iep_level":"body.prisonerDetails[#2].housing.regime"}},"warnings":{"*":{"code":"body.prisonerDetails[#4].alerts[#2].alertCode","createdate":"body.prisonerDetails[#4].alerts[#2].alertStartDate","expirydate":"body.prisonerDetails[#4].alerts[#2].alertEndDate","comment_text":"body.prisonerDetails[#4].alerts[#2].alertComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkWARN"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_WARNING';

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"correlationId":"header.intCorrelationId","nameDetails":{"*":{"offender_id_display":"body.personDetails.imprisonmentDetails.pin","first_name":"body.personDetails.demographics.firstName","last_name":"body.personDetails.demographics.surName","middle_name":"body.personDetails.demographics.middleName","second_middle_name":"body.personDetails.demographics.secondMiddleName","sex_code":"body.personDetails.demographics.gender","birth_date":"body.personDetails.demographics.dob"}},"aliasDetails":{"*":{"givenname":"body.personDetails.alias[#2].firstName","othergivennames":"body.personDetails.alias[#2].middleName","second_middle_name":"body.personDetails.alias[#2].secondMiddleName","familyname":"body.personDetails.alias[#2].surName","dateofbirth":"body.personDetails.alias[#2].dob"}},"warningDetails":{"*":{"description":"body.personDetails.alerts[#2].alertCode","alert_type":"body.personDetails.alerts[#2].alertType","createdate":"body.personDetails.alerts[#2].alertStartDate","expirydate":"body.personDetails.alerts[#2].alertEndDate","comment_text":"body.personDetails.alerts[#2].alertComment"}},"residenceDetails":{"*":{"streetnumber":"body.personDetails.address.road.roadNumber","streetname":"body.personDetails.address.road.roadName","city":"body.personDetails.address.localityName","state":"body.personDetails.address.stateOrTerritoryCode","country":"body.personDetails.address.countryName","postal":"body.personDetails.address.postCode","address":"body.personDetails.address.unstructuredAddressLine","recordeddate":"body.personDetails.address.recordedDate"}},"occupation":{"*":{"occupation":"body.personDetails.demographics.occupation"}},"bookingDetails":{"*":{"dateofdeath":"body.personDetails.demographics.dod"}},"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","body.trailer.responseDatetime"]}},"wantedDetails":{"*":{"wanted_type":"body.personDetails.wanted.wantedType","escape_comment_text":"body.personDetails.wanted.wantedComment"}}}},{"operation":"modify-default-beta","spec":{"body":{}}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"},"body":{"trailer":{"respCode":"Success","errMsg":""},"personDetails":{"imprisonmentDetails":[],"demographics":[],"address":[],"alerts":[],"alias":[],"wanted":[]}}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_EXTERNAL';

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails[#2].demographics.firstName","last_name":"body.prisonerDetails[#2].demographics.surName","middle_name":"body.prisonerDetails[#2].demographics.middleName","second_middle_name":"body.prisonerDetails[#2].demographics.secondMiddleName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell","iep_level":"body.prisonerDetails[#2].housing.regime"}},"warnings":{"*":{"code":"body.prisonerDetails[#4].warnings[#2].warningCode","createdate":"body.prisonerDetails[#4].warnings[#2].warningStartDate","expirydate":"body.prisonerDetails[#4].warnings[#2].warningEndDate","comment_text":"body.prisonerDetails[#4].warnings[#2].warningComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkIMPR"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_PRISONER_SENT_STATUS';

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails[#2].demographics.firstName","middle_name":"body.prisonerDetails[#2].demographics.middleName","second_middle_name":"body.prisonerDetails[#2].demographics.secondMiddleName","last_name":"body.prisonerDetails[#2].demographics.surName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell","iep_level":"body.prisonerDetails[#2].housing.regime"}},"warnings":{"*":{"description":"body.prisonerDetails[#4].warnings[#2].warningCode","createdate":"body.prisonerDetails[#4].warnings[#2].warningStartDate","expirydate":"body.prisonerDetails[#4].warnings[#2].warningEndDate","comment_text":"body.prisonerDetails[#4].warnings[#2].warningComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails[#2].imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails[#2].imprisonmentDetails.releaseReason"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""},"warnings":[]}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkADMI"},"body":{"prisonerDetails":{"*":{"warnings":[""]}}}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PRISONER_ADMISSION_FORMATTER';

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails[#2].demographics.firstName","last_name":"body.prisonerDetails[#2].demographics.surName","middle_name":"body.prisonerDetails[#2].demographics.middleName","second_middle_name":"body.prisonerDetails[#2].demographics.secondMiddleName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell","iep_level":"body.prisonerDetails[#2].housing.regime"}},"warnings":{"*":{"description":"body.prisonerDetails[#4].warnings[#2].warningCode","createdate":"body.prisonerDetails[#4].warnings[#2].warningStartDate","expirydate":"body.prisonerDetails[#4].warnings[#2].warningEndDate","comment_text":"body.prisonerDetails[#4].warnings[#2].warningComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails[#2].imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails[#2].imprisonmentDetails.releaseReason"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"housing":{"regime":" "},"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{" releaseDateTime":""," releaseReason":""},"warnings":[],"demographics":{"nationality":[""],"ethnicity":[""],"religion":[""]}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkPRDE"},"body":{"prisonerDetails":{"*":{"warnings":[""],"demographics":{"nationality":[],"ethnicity":[],"religion":[]}}}}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PRISONER_DETAILS_FORMATTER';

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails[#2].demographics.firstName","last_name":"body.prisonerDetails[#2].demographics.surName","middle_name":"body.prisonerDetails[#2].demographics.middleName","second_middle_name":"body.prisonerDetails[#2].demographics.secondMiddleName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell","iep_level":"body.prisonerDetails[#2].housing.regime"}},"warnings":{"*":{"description":"body.prisonerDetails[#4].warnings[#2].warningCode","createdate":"body.prisonerDetails[#4].warnings[#2].warningStartDate","expirydate":"body.prisonerDetails[#4].warnings[#2].warningEndDate","comment_text":"body.prisonerDetails[#4].warnings[#2].warningComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails[#2].imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails[#2].imprisonmentDetails.releaseReason"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkDISC"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PRISONER_DISCHARGE_FORMATTER';

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
select
	35,
	'PHYSICAL_DESC_REQ_FORMATTER',
	'[{"operation":"shift","spec":{"ProcessInput":{"header":{"intCorrelationId":"correlationId"},"body":{"prisonerDetails":{"imprisonmentDetails":{"pin":"pin"}}}}}}]',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null,
	null
where
	not exists (
	select
		1
	from
		json_spec
	where
		id = 35
		and spec_key = 'PHYSICAL_DESC_REQ_FORMATTER');

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
select
	36,
	'PHYSICAL_DESC_ERROR_FORMATTER',
	'[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","body.trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails.imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhysicalDescription"},"body":{"trailer":{"respCode":"TBD","errMsg":"The prisoner does not Exist in Elite"}}}}]',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null,
	null
where
	not exists (
	select
		1
	from
		json_spec
	where
		id = 36
		and spec_key = 'PHYSICAL_DESC_ERROR_FORMATTER');

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
select
	37,
	'PHYSICAL_DESC_FORMATTER',
	'[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails.imprisonmentDetails.pin","phyAttributeDetails":{"*":{"height_cm":"body.prisonerDetails.description.height","weight_kg":"body.prisonerDetails.description.weight"}},"offenderProfileDetails":{"*":{"build":"body.prisonerDetails.description.build","hair":"body.prisonerDetails.description.hairColour","eye":"body.prisonerDetails.description.eyeColour","ethnicity":"body.prisonerDetails.description.race"}},"phyMarkDetails":{"*":{"mark_type":"body.prisonerDetails.smts[#2].mark_type","body_part":"body.prisonerDetails.smts[#2].body_part","side":"body.prisonerDetails.smts[#2].side","part_orientation":"body.prisonerDetails.smts[#2].orientation","comment_text":"body.prisonerDetails.smts[#2].comment"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhysicalDescription"}}},{"operation":"modify-default-beta","spec":{"trailer":{"respCode":"TBD","errMsg":""}}}]',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null,
	null
where
	not exists (
	select
		1
	from
		json_spec
	where
		id = 37
		and spec_key = 'PHYSICAL_DESC_FORMATTER');

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
select
	38,
	'POLICE_WARNINGS_REQ_FORMATTER',
	'[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails.imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"SAPOLWarnings","intCorrelationId":"TBD"}}}]',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null,
	null
where
	not exists (
	select
		1
	from
		json_spec
	where
		id = 38
		and spec_key = 'POLICE_WARNINGS_REQ_FORMATTER');

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
select
	39,
	'POLICE_WARNINGS_RES_FORMATTER',
	'[{"operation":"shift","spec":{"ProcessInput":{"header":{"intCorrelationId":"correlationId","intUser":{"intUserId":"userId","intUserName":"userName"}},"body":{"prisonerDetails":{"imprisonmentDetails":{"pin":"pin"},"warningDetail":"warningDetail"}},"trailer":{"responseDatetime":"responseDatetime","respCode":"respCode","errMsg":"errMsg"}}}}]',
	current_timestamp,
	'OMS_OWNER',
	current_timestamp,
	null,
	null
where
	not exists (
	select
		1
	from
		json_spec
	where
		id = 39
		and spec_key = 'POLICE_WARNINGS_RES_FORMATTER');