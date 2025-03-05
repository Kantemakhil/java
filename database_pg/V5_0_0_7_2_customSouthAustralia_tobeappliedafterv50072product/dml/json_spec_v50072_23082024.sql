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
	42,
	'TRANSFORM_OFF_DETAILS_REQUEST',
	'[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"pin":"body.imprisonmentDetails.pin","intCorrelationId":"header.intCorrelationId"}},{"operation":"default","spec":{"header":{"intId":"JTSPINSearch"}}}]',
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
		id = 42
		and spec_key = 'TRANSFORM_OFF_DETAILS_REQUEST');

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
	43,
	'TRANSFRM_OFFDETLS_REQ_WITH_NAMES',
	'[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"pFirstName":"body.demographics.firstName","pLastName":"body.demographics.surName","pMiddleName":"body.demographics.MiddleName","secondMiddleName":"body.demographics.secondMiddleName","dob":"body.demographics.dob","intCorrelationId":"header.intCorrelationId"}},{"operation":"default","spec":{"header":{"intId":"JTSNameSearch","intCorrelationId":""}}}]',
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
		id = 43
		and spec_key = 'TRANSFRM_OFFDETLS_REQ_WITH_NAMES');

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
	40,
	'TRANSFRM_PERDETLS_REQ_WITH_NAMES',
	'[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"pFirstName":"body.demographics.firstName","pLastName":"body.demographics.surName","pMiddleName":"body.demographics.MiddleName","secondMiddleName":"body.demographics.secondMiddleName","pBirthDate":"body.demographics.dob","intCorrelationId":"header.intCorrelationId"}},{"operation":"default","spec":{"header":{"intId":"JTSNameSearch","intCorrelationId":""}}}]',
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
		id = 40
		and spec_key = 'TRANSFRM_PERDETLS_REQ_WITH_NAMES');

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
	41,
	'TRANSFRM_INT_CORRELATION_ID',
	'[{"operation":"shift","spec":{"offenderBookId":{"header":{"intCorrelationId":"correlationId"}}}}]',
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
		id = 41
		and spec_key = 'TRANSFRM_INT_CORRELATION_ID');