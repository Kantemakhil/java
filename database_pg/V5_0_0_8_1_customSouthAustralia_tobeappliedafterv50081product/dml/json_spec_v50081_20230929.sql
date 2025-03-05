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
	44,
	'PAROLE_COND_REQ_FORMATTER',
	'[{"operation":"shift","spec":{"ProcessInput":{"header":{"intCorrelationId":"correlationId"},"body":{"prisonerDetails":{"*":{"imprisonmentDetails":{"pin":"pin"}}}}}}}]',
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
		id = 44
		and spec_key = 'PAROLE_COND_REQ_FORMATTER');

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
	33,
	'PAROLE_COND_RESPONSE_FORMATTER',
	'[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","body.trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails[#2].imprisonmentDetails.pin","paroleDateDetails":{"*":{"parolecommencedate":"body.prisonerDetails[#4].release.releasestartDate","paroleexpirydate":"body.prisonerDetails[#4].release.releasedueEndDate"}},"paroleOrderDetails":{"*":{"number":"body.prisonerDetails[#4].release.order[#2].number","typecode":"body.prisonerDetails[#4].release.order[#2].typeCode","origincode":"body.prisonerDetails[#4].release.order[#2].originCode","issueddate":"body.prisonerDetails[#4].release.order[#2].issuedDate","orderstartdate":"body.prisonerDetails[#4].release.order[#2].orderedStartDate","orderenddate":"body.prisonerDetails[#4].release.order[#2].orderedEndDate"}},"paroleConditionDetails":{"*":{"conditions":{"value":{"*":"body.prisonerDetails[#6].release.order[#4].condition[]"}}}}}},{"operation":"modify-default-beta","spec":{"body":{"prisonerDetails":{"*":{"release":{"order":{"*":{"courtFileNumber":"TBD"}},"orderCourtFileNumber":"TBD"}}}}}},{"operation":"default","spec":{"header":{"intId":"ParoleConditions"},"body":{"trailer":{"respCode":"TBD","errMsg":""}}}}]',
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
		id = 33
		and spec_key = 'PAROLE_COND_RESPONSE_FORMATTER');

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
	34,
	'PAROLE_COND_ERROR_FORMATTER',
	'[{"operation":"shift","spec":{"staffDetails":{"*":{"first_name":"header.intUser.intUserName","user_id":"header.intUser.intUserId","date_time":["header.intDatetime","body.trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","pin":"body.prisonerDetails[#2].imprisonmentDetails.pin"}},{"operation":"default","spec":{"header":{"intId":"ParoleConditions"},"body":{"trailer":{"respCode":"TBD","errMsg":"The prisoner does not Exist in Elite"}}}}]',
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
		id = 34
		and spec_key = 'PAROLE_COND_ERROR_FORMATTER');