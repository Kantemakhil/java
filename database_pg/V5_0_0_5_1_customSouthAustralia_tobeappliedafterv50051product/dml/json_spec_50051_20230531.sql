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
select 29,
'TRANSFORM_PRISONER_SENT_STATUS',
'[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":"header.intDatetime"}},"offenderDetails":{"*":{"first_name":"body.prisonerDetails[#2].demographics.firstName","last_name":"body.prisonerDetails[#2].demographics.surName","sex_code":"body.prisonerDetails[#2].demographics.gender","birth_date":"body.prisonerDetails[#2].demographics.dob","offender_id_display":"body.prisonerDetails[#2].imprisonmentDetails.dcsId"}},"offenderBookingDetails":{"*":{"booking_no":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentId","booking_begin_date":"body.prisonerDetails[#2].imprisonmentDetails.intakeDate","booking_status":"body.prisonerDetails[#2].imprisonmentDetails.imprisonmentStatus","agy_loc_id":"body.prisonerDetails[#2].housing.facility"}},"offenderHousingDetails":{"*":{"housing_lev_1_code":"body.prisonerDetails[#2].housing.pod","housing_lev_2_code":"body.prisonerDetails[#2].housing.unit","housing_lev_3_code":"body.prisonerDetails[#2].housing.cell"}},"warnings":{"*":{"code":"body.prisonerDetails[#4].warnings[#2].warningCode","createdate":"body.prisonerDetails[#4].warnings[#2].warningStartDate","expirydate":"body.prisonerDetails[#4].warnings[#2].warningEndDate","comment_text":"body.prisonerDetails[#4].warnings[#2].warningComment"}},"offenderFinancialDetails":{"*":{"current_balance":"body.prisonerDetails[#2].financials.accountBalance","balance":"body.prisonerDetails[#2].financials.resettlementAccountBalance"}},"offenderProfileDetails":{"*":{"nationality":"body.prisonerDetails[#2].demographics.nationality","ethnicity":"body.prisonerDetails[#2].demographics.ethnicity","religion":"body.prisonerDetails[#2].demographics.religion"}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"housing":{"regime":" "},"financials":{"transactionSpendLimit":" "},"imprisonmentDetails":{"releaseDateTime":"","releaseReason":""}}}}}},{"operation":"default","spec":{"header":{"intId":"UnilinkIMPR"}}}]',
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
	id = 29
	and spec_key = 'TRANSFORM_PRISONER_SENT_STATUS');