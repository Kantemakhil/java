insert
	into
	oms_owner.iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'OFFENDER_PROCEEDING_ID',
	'U',
	'Court Action Id',
	'ORD_CRT_ACT',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	null,
	null
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'ORD_CRT_ACT'
		and parameter_name = 'OFFENDER_PROCEEDING_ID');
		
		
delete
from
	iwp_bookmark_parameters
where
	bookmark_name = 'OFF_IN_CHARG';

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'PARTY_SEQ ',
	'C',
	'PartySequence',
	'OFF_IN_CHARG',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'105',
	'partySeq'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFF_IN_CHARG'
		and parameter_name = 'PARTY_SEQ');

insert
	into
	iwp_bookmark_parameters
(parameter_name,
	parameter_type,
	description,
	bookmark_name,
	date_created,
	user_created,
	parameter_data_type,
	create_datetime,
	create_user_id,
	modify_datetime,
	modify_user_id,
	active_flag,
	seal_flag,
	module_block_code,
	field)
select
	'AGENCY_INCIDENT_ID',
	'C',
	'AgencyIncidentId',
	'OFF_IN_CHARG',
	current_timestamp,
	'OMS_OWNER',
	'N',
	current_timestamp,
	'OMS_OWNER',
	null,
	null,
	'Y',
	null,
	'105',
	'agencyIncidentId'
where
	not exists (
	select
		1
	from
		iwp_bookmark_parameters
	where
		bookmark_name = 'OFF_IN_CHARG'
		and parameter_name = 'AGENCY_INCIDENT_ID');