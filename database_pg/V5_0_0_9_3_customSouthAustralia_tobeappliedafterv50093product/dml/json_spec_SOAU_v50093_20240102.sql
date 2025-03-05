update
    json_spec
set
    json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails.imprisonmentDetails.pin","phyAttributeDetails":{"*":{"height_cm":"body.prisonerDetails.description.height","weight_kg":"body.prisonerDetails.description.weight"}},"offenderProfileDetails":{"*":{"build":"body.prisonerDetails.description.build"}},"phyMarkDetails":{"*":{"mark_type":"body.prisonerDetails.smts[#2].mark_type","body_part":"body.prisonerDetails.smts[#2].body_part","side":"body.prisonerDetails.smts[#2].side","part_orientation":"body.prisonerDetails.smts[#2].orientation","comment_text":"body.prisonerDetails.smts[#2].comment","descriptiondate":"body.prisonerDetails.smts[#2].descriptionDate"}},"raceDetails":{"*":{"race":"body.prisonerDetails.description.race"}},"descriptionDate":{"*":{"description_date":"body.prisonerDetails.imprisonmentDetails.descriptionDate"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhysicalDescription"},"body":{"prisonerDetails":{"smts":[]}}}},{"operation":"modify-default-beta","spec":{"trailer":{"respCode":"TBD","errMsg":""}}}]',
    modify_datetime = current_timestamp,
    modify_user_id = 'OMS_OWNER'
where
    spec_key = 'PHYSICAL_DESC_FORMATTER';
	
update
    json_spec
set
    json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails.demographics.firstName","last_name":"body.prisonerDetails.demographics.surName","middle_name":"body.prisonerDetails.demographics.middleName","second_middle_name":"body.prisonerDetails.demographics.secondMiddleName","sex_code":"body.prisonerDetails.demographics.gender","birth_date":"body.prisonerDetails.demographics.dob","offender_id_display":"body.prisonerDetails.imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails.imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails.imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails.imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails.housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails.housing.pod","housing_lev_2_code":"body.prisonerDetails.housing.unit","housing_lev_3_code":"body.prisonerDetails.housing.cell","iep_level":"body.prisonerDetails.housing.regime"}},"warnings":{"*":{"code":"body.prisonerDetails.alerts[#2].warningCode","createdate":"body.prisonerDetails.alerts[#2].warningStartDate","expirydate":"body.prisonerDetails.alerts[#2].warningEndDate","comment_text":"body.prisonerDetails.alerts[#2].warningComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails.financials.accountBalance","balance":"body.prisonerDetails.financials.resettlementAccountBalance","apply_spending_limit_amount":"body.prisonerDetails.financials.transactionSpendLimit"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails.demographics.nationality","ethnicity":"body.prisonerDetails.demographics.ethnicity","religion":"body.prisonerDetails.demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""},"alerts":[]}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkIMPR"}}}]',
    modify_datetime = current_timestamp,
    modify_user_id = 'OMS_OWNER'
where
    spec_key = 'TRANSFORM_PRISONER_SENT_STATUS';	
	
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
	45,
	'ALIAS_DETAILS_RES_FORMATTER',
	'[{"operation":"shift","spec":{"ProcessInput":{"header":{"intCorrelationId":"correlationId","intUser":{"intUserId":"userId","intUserName":"userName"}},"body":{"imprisonmentDetails":{"pin":"pin"},"aliases":"aliases"}}}}]',
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
		id = 45
		and spec_key = 'ALIAS_DETAILS_RES_FORMATTER');	