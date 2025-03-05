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
	32,
	'TRANSFORM_PRISONER_RECONCILATION',
	'[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":"header.intDatetime"}},"pFromOffBookId":"body.prisonerDetails.imprisonmentId","pToOffIdDisplay":"body.prisonerDetails.oldDcsId","pFromOffIdDisplay":"body.prisonerDetails.newDcsId"}},{"operation":"default","spec":{"header":{"intId":"UnilinkRECO"}}}]',
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
		id = 32
		and spec_key = 'TRANSFORM_PRISONER_RECONCILATION');