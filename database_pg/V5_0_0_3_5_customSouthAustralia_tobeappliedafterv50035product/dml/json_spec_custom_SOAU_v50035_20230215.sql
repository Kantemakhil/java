update
	json_spec
set
	modify_datetime = current_timestamp
   ,
	modify_user_id = 'OMS_OWNER'
   ,
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.offenderNumber","first_name":"body.prisonerDetails[#2].demographics.firstName","last_name":"body.prisonerDetails[#2].demographics.surName"}},"offenderBookingDetails":{"*":{"agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"computerAccessFlag":{"*":{"coalesce":"body.prisonerDetails[#2].computerAccess"}}}},{"operation":"default","spec":{"header":{"intId":"LearningSystem"}}}]'
where
	spec_key = 'LEARNING_SYSTEM_FORMATTER';

update
	json_spec
set
	modify_datetime = current_timestamp
   ,
	modify_user_id = 'OMS_OWNER'
   ,
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails[#2].demographics.firstName","last_name":"body.prisonerDetails[#2].demographics.surName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell"}},"warnings":{"*":{"code":"body.prisonerDetails[#4].alerts[#2].alertCode","createdate":"body.prisonerDetails[#4].alerts[#2].alertStartDate","expirydate":"body.prisonerDetails[#4].alerts[#2].alertEndDate","comment_text":"body.prisonerDetails[#4].alerts[#2].alertComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"housing":{"regime":" "},"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkWARN"}}}]'
where
	spec_key = 'TRANSFORM_WARNING';

update
	json_spec
set
	modify_datetime = current_timestamp
   ,
	modify_user_id = 'OMS_OWNER'
   ,
	json_specs = '[{"operation":"shift","spec":{"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"correlationId":"header.intCorrelationId","nameDetails":{"*":{"offender_id_display":"body.personDetails.imprisonmentDetails.pin","first_name":"body.personDetails.demographics.firstName","last_name":"body.personDetails.demographics.surName","sex_code":"body.personDetails.demographics.gender","birth_date":"body.personDetails.demographics.dob"}},"aliasDetails":{"*":{"givenname":"body.personDetails.alias[#2].firstName","eliteoffenderid":"body.personDetails.alias[#2].offenderId","familyname":"body.personDetails.alias[#2].lastname","dateofbirth":"body.personDetails.alias[#2].dob"}},"warningDetails":{"*":{"description":"body.personDetails.alerts[#2].alertCode","alert_type":"body.personDetails.alerts[#2].alertType","createdate":"body.personDetails.alerts[#2].alertStartDate","expirydate":"body.personDetails.alerts[#2].alertEndDate","comment_text":"body.personDetails.alerts[#2].alertComment"}},"residenceDetails":{"*":{"streetnumber":"body.personDetails.address.road.roadNumber","streetname":"body.personDetails.address.road.roadName","city":"body.personDetails.address.localityName","state":"body.personDetails.address.stateOrTerritoryCode","country":"body.personDetails.address.countryName","postal":"body.personDetails.address.postCode","address":"body.personDetails.address.unstructuredAddressLine","recordeddate":"body.personDetails.address.recordedDate"}},"occupation":{"*":{"occupation":"body.personDetails.demographics.occupation"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"}}}]'
where
	spec_key = 'TRANSFORM_EXTERNAL';

update
	json_spec
set
	modify_datetime = current_timestamp
   ,
	modify_user_id = 'OMS_OWNER'
   ,
	json_specs = '[{"operation":"shift","spec":{"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"},"body":{"Error":"The prisioner does not Exist in Elite"}}}]'
where
	spec_key = 'TRANSFORM_ERROR';

update
	json_spec
set
	modify_datetime = current_timestamp
   ,
	modify_user_id = 'OMS_OWNER'
   ,
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.activePrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.activePrisonerDetails[#2].demographics.firstName","middle_name":"body.activePrisonerDetails[#2].demographics.middleName","last_name":"body.activePrisonerDetails[#2].demographics.surName","sex_code":"body.activePrisonerDetails[#2].demographics.gender","birth_date":"body.activePrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.activePrisonerDetails[#2].imprisonmentDetails.pin","booking_begin_date":"body.activePrisonerDetails[#2].imprisonmentDetails.intakeDatetime","booking_status":"body.activePrisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.activePrisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.activePrisonerDetails[#2].housing.unit"}},"offenderReleaseDetails":{"*":{"release_date":"body.activePrisonerDetails[#2].imprisonmentDetails.releaseDateTime"}}}},{"operation":"default","spec":{"header":{"intId":"CurrentPrisoners"}}}]'
where
	spec_key = 'ACTIVE_PRISONER_FORMATTER';

update
	json_spec
set
	modify_datetime = current_timestamp
   ,
	modify_user_id = 'OMS_OWNER'
   ,
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.releasedPrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.releasedPrisonerDetails[#2].demographics.firstName","middle_name":"body.releasedPrisonerDetails[#2].demographics.middleName","last_name":"body.releasedPrisonerDetails[#2].demographics.surName","sex_code":"body.releasedPrisonerDetails[#2].demographics.gender","birth_date":"body.releasedPrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.releasedPrisonerDetails[#2].imprisonmentDetails.pin","booking_begin_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.intakeDatetime","booking_status":"body.releasedPrisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.releasedPrisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.releasedPrisonerDetails[#2].housing.unit"}},"offenderReleaseDetails":{"*":{"release_date":"body.releasedPrisonerDetails[#2].imprisonmentDetails.releaseDateTime"}}}},{"operation":"default","spec":{"header":{"intId":"CurrentPrisoners"}}}]'
where
	spec_key = 'RELEASED_PRISONER_FORMATTER';

update
	json_spec
set
	modify_datetime = current_timestamp
   ,
	modify_user_id = 'OMS_OWNER'
   ,
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.escapedPrisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.escapedPrisonerDetails[#2].demographics.firstName","middle_name":"body.escapedPrisonerDetails[#2].demographics.middleName","last_name":"body.escapedPrisonerDetails[#2].demographics.surName","sex_code":"body.escapedPrisonerDetails[#2].demographics.gender","birth_date":"body.escapedPrisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.escapedPrisonerDetails[#2].imprisonmentDetails.pin","booking_begin_date":"body.escapedPrisonerDetails[#2].imprisonmentDetails.intakeDatetime","booking_status":"body.escapedPrisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.escapedPrisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.escapedPrisonerDetails[#2].housing.unit"}},"offenderReleaseDetails":{"*":{"release_date":"body.escapedPrisonerDetails[#2].imprisonmentDetails.releaseDateTime"}}}},{"operation":"default","spec":{"header":{"intId":"CurrentPrisoners"}}}]'
where
	spec_key = 'ESCAPED_PRISONER_FORMATTER';

update
	work_items
set
	modify_datetime = current_timestamp
   ,
	modify_user_id = 'OMS_OWNER'
   ,
	update_trigger = 'N',
	delete_trigger = 'N'
where
	process = (
	select
		max(process_id)
	from
		bpmn_process
	where
		process_key = 'OIDEHLOC_EXCHANGE_OFFENDER_HOUSING_NOTIFY');

update
	json_spec
set
	modify_datetime = current_timestamp
   ,
	modify_user_id = 'OMS_OWNER'
   ,
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails[#2].demographics.firstName","last_name":"body.prisonerDetails[#2].demographics.surName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell"}},"warnings":{"*":{"code":"body.prisonerDetails[#4].alerts[#2].alertCode","createdate":"body.prisonerDetails[#4].alerts[#2].alertStartDate","expirydate":"body.prisonerDetails[#4].alerts[#2].alertEndDate","comment_text":"body.prisonerDetails[#4].alerts[#2].alertComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"housing":{"regime":" "},"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkWARN"}}}]'
where
	spec_key = 'TRANSFORM_WARNING';

update
	json_spec
set
	modify_datetime = current_timestamp
   ,
	modify_user_id = 'OMS_OWNER'
   ,
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId","first_name":"body.prisonerDetails[#2].demographics.firstName","last_name":"body.prisonerDetails[#2].demographics.surName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails[#2].imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails[#2].imprisonmentDetails.releaseReason"}},"offenderWarnings":{"*":{"code":"body.prisonerDetails[#4].alerts[#2].alertCode","createdate":"body.prisonerDetails[#4].alerts[#2].alertStartDate","expirydate":"body.prisonerDetails[#4].alerts[#2].alertEndDate","comment_text":"body.prisonerDetails[#4].alerts[#2].alertComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"housing":{"regime":" "},"financials":{"transactionSpendLimit":" "}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkLOCA"}}}]'
where
	spec_key = 'PRISONER_MOVEMENTS_FORMATTER';

insert
	into
	json_spec(id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
values(17,
'PRISONER_ADMISSION_FORMATTER',
'[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails[#2].demographics.firstName","last_name":"body.prisonerDetails[#2].demographics.surName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell"}},"warnings":{"*":{"description":"body.prisonerDetails[#4].warnings[#2].warningCode","createdate":"body.prisonerDetails[#4].warnings[#2].warningStartDate","expirydate":"body.prisonerDetails[#4].warnings[#2].warningEndDate","comment_text":"body.prisonerDetails[#4].warnings[#2].warningComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails[#2].imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails[#2].imprisonmentDetails.releaseReason"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"housing":{"regime":" "},"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkADMI"}}}]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER');

insert
	into
	json_spec(id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
values(18,
'PRISONER_DISCHARGE_FORMATTER',
'[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails[#2].demographics.firstName","last_name":"body.prisonerDetails[#2].demographics.surName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell"}},"warnings":{"*":{"description":"body.prisonerDetails[#4].warnings[#2].warningCode","createdate":"body.prisonerDetails[#4].warnings[#2].warningStartDate","expirydate":"body.prisonerDetails[#4].warnings[#2].warningEndDate","comment_text":"body.prisonerDetails[#4].warnings[#2].warningComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails[#2].imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails[#2].imprisonmentDetails.releaseReason"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"housing":{"regime":" "},"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkDISC"}}}]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER');

insert
	into
	json_spec(id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
values(19,
'PRISONER_DETAILS_FORMATTER',
'[ { "operation": "shift", "spec": { "staffDetails": { "*": { "first_name": "header.intUser.intUserName", "user_id": "header.intUser.intUserId", "date_time": "header.intDatetime" } }, "offenderDetails": { "*": { "first_name": "body.prisonerDetails[#2].demographics.firstName", "last_name": "body.prisonerDetails[#2].demographics.surName", "sex_code": "body.prisonerDetails[#2].demographics.gender", "birth_date": "body.prisonerDetails[#2].demographics.dob", "offender_id_display": "body.prisonerDetails[#2].imprisonmentDetails.dcsId" } }, "offenderBookingDetails": { "*": { "booking_no": "body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId", "booking_begin_date": "body.prisonerDetails[#2].imprisonmentDetails.intakeDate", "booking_status": "body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus", "agy_loc_id": "body.prisonerDetails[#2].housing.facility" } }, "offenderHousingDetails": { "*": { "housing_lev_1_code": "body.prisonerDetails[#2].housing.pod", "housing_lev_2_code": "body.prisonerDetails[#2].housing.unit", "housing_lev_3_code": "body.prisonerDetails[#2].housing.cell" } }, "warnings": { "*": { "description": "body.prisonerDetails[#4].warnings[#2].warningCode", "createdate": "body.prisonerDetails[#4].warnings[#2].warningStartDate", "expirydate": "body.prisonerDetails[#4].warnings[#2].warningEndDate", "comment_text": "body.prisonerDetails[#4].warnings[#2].warningComment" } }, "offenderFinancialDetails": { "*": { "current_balance": "body.prisonerDetails[#2].financials.accountBalance", "balance": "body.prisonerDetails[#2].financials.resettlementAccountBalance" } }, "offenderReleaseDetails": { "*": { "movement_date": "body.prisonerDetails[#2].imprisonmentDetails.releaseDateTime", "movement_reason_code": "body.prisonerDetails[#2].imprisonmentDetails.releaseReason" } }, "offenderProfileDetails": { "*": { "nationality": "body.prisonerDetails[#2].demographics.nationality", "ethnicity": "body.prisonerDetails[#2].demographics.ethnicity", "religion": "body.prisonerDetails[#2].demographics.religion" } } } }, { "operation": "modify-default-beta", "spec": { "body": { "prisonerDetails": { "*": { "housing": { "regime": " " }, "financials": { "transactionSpendLimit": " " }, "imprisonmentDetails": { "releaseDateTime": "", "releaseReason": "" } } } } } }, { "operation": "default", "spec": { "header": { "intId": "UnilinkPRDE" } } } ]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER');

insert
	into
	json_spec(id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
values(20,
'PHOTOGRAPH_LIST_REQ_FORMATTER',
'[{ "operation": "shift", "spec": { "ProcessInput": { "header": { "intCorrelId": "correlationId" }, "body": { "imprisonmentDetails": { "pin": "identifier" }, "maxResultsReturned": "maxResultsReturned" } } } }]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER');

insert
	into
	json_spec(id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
values(21,
'PHOTOGRAPH_REQ_FORMATTER',
'[{"operation":"shift","spec":{"ProcessInput":{"header":{"intCorrelId":"correlationId"},"body":{"photoId":"photoId"}}}}]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER');

insert
	into
	json_spec(id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
values(22,
'PHOTOGRAPH_LIST_RES_FORMATTER',
'[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"correlationId":"header.intCorrelId","photographListDetails":{"*":{"image_id":"body.photos[#2].photoId","capture_date":"body.photos[#2].photographedDate","image_thumbnail":"body.photos[#2].Photo"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhotoList"}}},{"operation":"modify-default-beta","spec":{"trailer":{"respCode":"TBD","errMsg":""}}}]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER');

insert
	into
	json_spec(id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
values(23,
'PHOTOGRAPH_RES_FORMATTER',
'[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"correlationId":"header.intCorrelId","photographDetails":{"*":{"capture_date":"body.details.photographedDate","image_thumbnail":"body.details.photo"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhoto"}}},{"operation":"modify-default-beta","spec":{"trailer":{"respCode":"TBD","errMsg":""}}}]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER');

insert
	into
	json_spec(id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
values(24,
'PHOTOGRAPH_LIST_ERROR_FORMATTER',
'[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"trailer.responseDatetime"}},"correlationId":"header.intCorrelId"}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhotoList"}}},{"operation":"modify-default-beta","spec":{"body":{"photos":[]},"trailer":{"respCode":"POORFORM","errMsg":"Invalid or Incorrect request document"}}}]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER');

insert
	into
	json_spec(id,
	spec_key,
	json_specs,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id)
values(25,
'PHOTOGRAPH_ERROR_FORMATTER',
'[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"trailer.responseDatetime"}},"correlationId":"header.intCorrelId"}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhoto"}}},{"operation":"modify-default-beta","spec":{"body":{"photos":[]},"trailer":{"respCode":"POORFORM","errMsg":"Invalid or Incorrect request document"}}}]',
current_timestamp,
'OMS_OWNER',
current_timestamp,
'OMS_OWNER');