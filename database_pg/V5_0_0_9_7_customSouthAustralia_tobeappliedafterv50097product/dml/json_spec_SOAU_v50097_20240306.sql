update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"pFirstName":"body.demographics.firstName","pLastName":"body.demographics.surName","pMiddleName":"body.demographics.MiddleName","secondMiddleName":"body.demographics.secondMiddleName","pBirthDate":"body.demographics.dob","intCorrelationId":"header.intCorrelationId"}},{"operation":"default","spec":{"header":{"intId":"JTSPersonNameSearch","intCorrelationId":""}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFRM_PERDETLS_REQ_WITH_NAMES';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails.demographics.firstName","last_name":"body.prisonerDetails.demographics.surName","middle_name":"body.prisonerDetails.demographics.middleName","second_middle_name":"body.prisonerDetails.demographics.secondMiddleName","sex_code":"body.prisonerDetails.demographics.gender","birth_date":"body.prisonerDetails.demographics.dob","offender_id_display":"body.prisonerDetails.imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails.imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails.imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails.imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails.housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails.housing.pod","housing_lev_2_code":"body.prisonerDetails.housing.unit","housing_lev_3_code":"body.prisonerDetails.housing.cell","iep_level":"body.prisonerDetails.housing.regime"}},"warnings":{"*":{"description":"body.prisonerDetails.alerts[#2].alertCode","createdate":"body.prisonerDetails.alerts[#2].alertStartDate","expirydate":"body.prisonerDetails.alerts[#2].alertEndDate","comment_text":"body.prisonerDetails.alerts[#2].alertComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails.financials.accountBalance","balance":"body.prisonerDetails.financials.resettlementAccountBalance"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails.imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails.imprisonmentDetails.releaseReason"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails.demographics.nationality","ethnicity":"body.prisonerDetails.demographics.ethnicity","religion":"body.prisonerDetails.demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""},"alerts":[]}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkDISC"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PRISONER_DISCHARGE_FORMATTER';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails.demographics.firstName","last_name":"body.prisonerDetails.demographics.surName","middle_name":"body.prisonerDetails.demographics.middleName","second_middle_name":"body.prisonerDetails.demographics.secondMiddleName","sex_code":"body.prisonerDetails.demographics.gender","birth_date":"body.prisonerDetails.demographics.dob","offender_id_display":"body.prisonerDetails.imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails.imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails.imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails.imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails.housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails.housing.pod","housing_lev_2_code":"body.prisonerDetails.housing.unit","housing_lev_3_code":"body.prisonerDetails.housing.cell","iep_level":"body.prisonerDetails.housing.regime"}},"warnings":{"*":{"description":"body.prisonerDetails.alerts[#2].alertCode","createdate":"body.prisonerDetails.alerts[#2].alertStartDate","expirydate":"body.prisonerDetails.alerts[#2].alertEndDate","comment_text":"body.prisonerDetails.alerts[#2].alertComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails.financials.accountBalance","balance":"body.prisonerDetails.financials.resettlementAccountBalance","apply_spending_limit_amount":"body.prisonerDetails.financials.transactionSpendLimit"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails.imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails.imprisonmentDetails.releaseReason"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails.demographics.nationality","ethnicity":"body.prisonerDetails.demographics.ethnicity","religion":"body.prisonerDetails.demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"housing":{"regime":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""},"alerts":[],"demographics":{"nationality":"","ethnicity":"","religion":""}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkPRDE"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PRISONER_DETAILS_FORMATTER';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails.imprisonmentDetails.pin","phyAttributeDetails":{"*":{"height_cm":"body.prisonerDetails.description.height","weight_kg":"body.prisonerDetails.description.weight"}},"phyMarkDetails":{"*":{"mark_type":"body.prisonerDetails.smts[#2].mark_type","body_part":"body.prisonerDetails.smts[#2].body_part","side":"body.prisonerDetails.smts[#2].side","part_orientation":"body.prisonerDetails.smts[#2].orientation","comment_text":"body.prisonerDetails.smts[#2].comment"}},"descriptionDate":{"*":{"description_date":["body.prisonerDetails.description.descriptionDate","body.prisonerDetails.smts[#2].descriptionDate"]}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhysicalDescription"},"body":{"prisonerDetails":{"smts":[]}}}},{"operation":"modify-default-beta","spec":{"trailer":{"respCode":"SUCCESS","errMsg":""}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHYSICAL_DESC_FORMATTER';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails.imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhysicalDescription"},"trailer":{"respCode":"NOPERSON","errMsg":"The prisoner does not Exist in Elite"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHYSICAL_DESC_ERROR_FORMATTER';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails[#2].imprisonmentDetails.pin","paroleDateDetails":{"*":{"parolecommencedate":"body.prisonerDetails[#4].release[#2].releasestartDate","paroleexpirydate":"body.prisonerDetails[#4].release[#2].releasedueEndDate"}},"paroleOrderDetails":{"*":{"number":"body.prisonerDetails[#4].release[#2].order.number","typecode":"body.prisonerDetails[#4].release[#2].order.type","origincode":"body.prisonerDetails[#4].release[#2].order.origin","issueddate":"body.prisonerDetails[#4].release[#2].order.issuedDate","orderstartdate":"body.prisonerDetails[#4].release[#2].order.orderedStartDate","orderenddate":"body.prisonerDetails[#4].release[#2].order.orderedEndDate"}},"paroleConditionDetails":{"*":{"conditions":{"value":{"*":"body.prisonerDetails[#4].release[#2].order.condition[]"}}}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"release":{"*":{"orderCourtFileNumber":"TBD","order":{"condition":{}}}}}}}}},{"operation":"default","spec":{"header":{"intId":"ParoleConditions"},"trailer":{"respCode":"SUCCESS","errMsg":""}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PAROLE_COND_RESPONSE_FORMATTER';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails[#2].imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"ParoleConditions"},"trailer":{"respCode":"NOPERSON","errMsg":"The prisoner does not Exist in Elite"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PAROLE_COND_ERROR_FORMATTER';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"correlationId":"header.intCorrelationId","identifier":"body.personDetails.imprisonmentDetails.pin","nameDetails":{"*":{"first_name":"body.personDetails.demographics.firstName","last_name":"body.personDetails.demographics.surName","middle_name":"body.personDetails.demographics.middleName","second_middle_name":"body.personDetails.demographics.secondMiddleName","sex_code":"body.personDetails.demographics.gender","birth_date":"body.personDetails.demographics.dob"}},"deathDetails":{"*":{"death_date":"body.personDetails.demographics.dod"}},"aliasDetails":{"*":{"givenname":"body.personDetails.alias[#2].firstName","othergivennames":"body.personDetails.alias[#2].middleName","second_middle_name":"body.personDetails.alias[#2].secondMiddleName","familyname":"body.personDetails.alias[#2].surName","dateofbirth":"body.personDetails.alias[#2].dob"}},"warningDetails":{"*":{"description":"body.personDetails.alerts[#2].alertCode","alert_type":"body.personDetails.alerts[#2].alertType","createdate":"body.personDetails.alerts[#2].alertStartDate","expirydate":"body.personDetails.alerts[#2].alertEndDate","comment_text":"body.personDetails.alerts[#2].alertComment"}},"residenceDetails":{"*":{"address_line1":"body.personDetails.address.address_line","street":"body.personDetails.address.street","street_number":"body.personDetails.address.street_number","street_direction":"body.personDetails.address.street_direction","suite_number":"body.personDetails.address.suite_number","city_name":"body.personDetails.address.city_name","city_code":"body.personDetails.address.city_code","state_code":"body.personDetails.address.state_code","country_code":"body.personDetails.address.country_code","postal":"body.personDetails.address.postal_code","address":"body.personDetails.address.unstructured_address_line","no_fixed_address_flag":"body.personDetails.address.no_address_flag"}},"occupation":{"*":{"occupation":"body.personDetails.demographics.occupation"}},"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"wantedDetails":{"*":{"wanted_type":"body.personDetails.wanted.wantedType","escape_date_time":"body.personDetails.wanted.wantedDateTime","escape_comment_text":"body.personDetails.wanted.wantedComment"}}}},{"operation":"modify-default-beta","spec":{"body":{}}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"},"body":{"personDetails":{"imprisonmentDetails":[],"demographics":[],"address":[],"alerts":[],"alias":[],"wanted":[]}},"trailer":{"respCode":"SUCCESS","errMsg":""}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_EXTERNAL';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"userDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","identifier":"body.personDetails.imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"SAPOLOffenderDetail"},"trailer":{"respCode":"NOPERSON","errMsg":"The prisoner does not Exist in Elite"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'TRANSFORM_ERROR';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","photographListDetails":{"*":{"image_id":"body.photos[#2].photoId","capture_date":"body.photos[#2].photographedDate","image_thumbnail":"body.photos[#2].Photo"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhotoList"}}},{"operation":"modify-default-beta","spec":{"body":{"photos":[]},"trailer":{"respCode":"SUCCESS","errMsg":""}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_LIST_RES_FORMATTER';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId"}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhotoList"}}},{"operation":"modify-default-beta","spec":{"body":{"photos":[]},"trailer":{"respCode":"NOPERSON","errMsg":"The prisoner does not Exist in Elite"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_LIST_ERROR_FORMATTER';

update	
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","photographDetails":{"*":{"capture_date":"body.details.photographedDate","image_thumbnail":"body.details.photo"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhoto"}}},{"operation":"modify-default-beta","spec":{"trailer":{"respCode":"SUCCESS","errMsg":""}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PHOTOGRAPH_RES_FORMATTER';

update
    json_spec
set
      json_specs ='[{"operation":"shift","spec":{"staffDetails":{"":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails.demographics.firstName","last_name":"body.prisonerDetails.demographics.surName","sex_code":"body.prisonerDetails.demographics.gender","birth_date":"body.prisonerDetails.demographics.dob","dcs_id":"body.prisonerDetails.imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"intake_number":"body.prisonerDetails.imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails.imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails.imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails.housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails.housing.pod","housing_lev_2_code":"body.prisonerDetails.housing.unit","housing_lev_3_code":"body.prisonerDetails.housing.cell"}},"warnings":{"*":{"code":"body.prisonerDetails.alerts[#2].alertCode","createdate":"body.prisonerDetails.alerts[#2].alertStartDate","expirydate":"body.prisonerDetails.alerts[#2].alertEndDate","comment_text":"body.prisonerDetails.alerts[#2].alertComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails.financials.accountBalance","balance":"body.prisonerDetails.financials.resettlementAccountBalance"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails.demographics.nationality","ethnicity":"body.prisonerDetails.demographics.ethnicity","religion":"body.prisonerDetails.demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"housing":{"regime":" "},"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkWARN"}}}]',
    modify_datetime = current_timestamp,
    modify_user_id = 'OMS_OWNER'
where
    spec_key = 'TRANSFORM_WARNING'; 

update
	json_spec
set
	json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"dcs_id":"body.prisonerDetails.imprisonmentDetails.dcsId","first_name":"body.prisonerDetails.demographics.firstName","middle_name":"body.prisonerDetails.demographics.middleName","second_middle_name":"body.prisonerDetails.demographics.secondMiddleName","last_name":"body.prisonerDetails.demographics.surName","sex_code":"body.prisonerDetails.demographics.gender","birth_date":"body.prisonerDetails.demographics.dob"}},"offenderBookingDetails":{"*":{"intake_number":"body.prisonerDetails.imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails.imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails.imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails.housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails.housing.pod","housing_lev_2_code":"body.prisonerDetails.housing.unit","housing_lev_3_code":"body.prisonerDetails.housing.cell","iep_level":"body.prisonerDetails.housing.regime"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails.imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails.imprisonmentDetails.releaseReason"}},"offenderWarnings":{"*":{"code":"body.prisonerDetails.alerts[#2].alertCode","createdate":"body.prisonerDetails.alerts[#2].alertStartDate","expirydate":"body.prisonerDetails.alerts[#2].alertEndDate","comment_text":"body.prisonerDetails.alerts[#2].alertComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails.financials.accountBalance","balance":"body.prisonerDetails.financials.resettlementAccountBalance"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails.demographics.nationality","ethnicity":"body.prisonerDetails.demographics.ethnicity","religion":"body.prisonerDetails.demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"financials":{"accountBalance":0,"resettlementAccountBalance":0,"transactionSpendLimit":"TBD"},"alerts":[]}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkLOCA"}}}]',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	spec_key = 'PRISONER_MOVEMENTS_FORMATTER';

update
    json_spec
set
      json_specs ='[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails.demographics.firstName","middle_name":"body.prisonerDetails.demographics.middleName","second_middle_name":"body.prisonerDetails.demographics.secondMiddleName","last_name":"body.prisonerDetails.demographics.surName","sex_code":"body.prisonerDetails.demographics.gender","birth_date":"body.prisonerDetails.demographics.dob","offender_id_display":"body.prisonerDetails.imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"intake_number":"body.prisonerDetails.imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails.imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails.imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails.housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails.housing.pod","housing_lev_2_code":"body.prisonerDetails.housing.unit","housing_lev_3_code":"body.prisonerDetails.housing.cell","iep_level":"body.prisonerDetails.housing.regime"}},"warnings":{"*":{"description":"body.prisonerDetails.alerts[#2].alertCode","createdate":"body.prisonerDetails.alerts[#2].alertStartDate","expirydate":"body.prisonerDetails.alerts[#2].alertEndDate","comment_text":"body.prisonerDetails.alerts[#2].alertComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails.financials.accountBalance","balance":"body.prisonerDetails.financials.resettlementAccountBalance"}},"offenderReleaseDetails":{"*":{"movement_date":"body.prisonerDetails.imprisonmentDetails.releaseDateTime","movement_reason_code":"body.prisonerDetails.imprisonmentDetails.releaseReason"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails.demographics.nationality","ethnicity":"body.prisonerDetails.demographics.ethnicity","religion":"body.prisonerDetails.demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"financials":{"transactionSpendLimit":"TBD"},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""},"alerts":[]}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkADMI"}}}]',
    modify_datetime = current_timestamp,
    modify_user_id = 'OMS_OWNER'
where
    spec_key = 'PRISONER_ADMISSION_FORMATTER';