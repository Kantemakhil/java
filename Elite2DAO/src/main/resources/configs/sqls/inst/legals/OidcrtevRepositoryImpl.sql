OIDCRTEV_CRTEVE_FIND_COURT_EVENTS {
 	select EVENT_ID, CASE_ID, OFFENDER_BOOK_ID, EVENT_DATE, START_TIME, END_TIME, COURT_EVENT_TYPE, JUDGE_NAME, EVENT_STATUS, PARENT_EVENT_ID, AGY_LOC_ID, OUTCOME_REASON_CODE, COMMENT_TEXT, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, EVENT_OUTCOME, NEXT_EVENT_REQUEST_FLAG, ORDER_REQUESTED_FLAG, RESULT_CODE, NEXT_EVENT_DATE, NEXT_EVENT_START_TIME, OUTCOME_DATE, OFFENDER_PROCEEDING_ID, DIRECTION_CODE, HOLD_FLAG, SEAL_FLAG, SCHEDULED_TRIP_ID, APPEARANCE_LOCATION, APPEARANCE_TYPE, MATTER, SENTENCE_SEQ, ORDER_TYPE, RECOMMENDED_SANCTION_COUNT, RECOMMENDED_REWARD_COUNT, ADDITIONAL_COUNTS_COMMENT,email_flag,sms_flag,sms_schedule_hours_before,email_schedule_hours_before
 	, (select count(*) from court_evnt_sanctions_details where court_event_id = event_id and link_flag='Y') as LINK_DATA 
 	from COURT_EVENTS where offender_book_id =:offender_book_id and DIRECTION_CODE = 'OUT' order by AGY_LOC_ID , START_TIME
}

COURT_DATA {
SELECT   al.description,
         al.agy_loc_id
    FROM agency_locations al
   WHERE agency_location_type = 'CRT'
     AND (active_flag = 'Y' AND deactivation_date IS NULL)
     AND al.agy_loc_id NOT IN ('TRN', 'OUT')
ORDER BY list_seq,description
}

OIDCRTEV_CHECK_SCHEDULE_CONFLICT {
SELECT COUNT (*) FROM V_OFFENDER_ALL_SCHEDULES_2 WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID   AND EVENT_STATUS = 'SCH' AND to_char(EVENT_DATE,'DD/MM/YYYY') = TO_char(to_date(:EVENT_DATE,'dd-MM-yyyy'),'DD/MM/YYYY') 
}

OIDCRTEV_CRTEVE_INSERT_COURT_EVENTS_PRE_EVENT_ID {
INSERT INTO COURT_EVENTS(EVENT_ID , CASE_ID , OFFENDER_BOOK_ID , EVENT_DATE , START_TIME , END_TIME , COURT_EVENT_TYPE , JUDGE_NAME , EVENT_STATUS , PARENT_EVENT_ID , AGY_LOC_ID , OUTCOME_REASON_CODE , COMMENT_TEXT , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , EVENT_OUTCOME , NEXT_EVENT_REQUEST_FLAG , ORDER_REQUESTED_FLAG , RESULT_CODE , NEXT_EVENT_DATE , NEXT_EVENT_START_TIME , OUTCOME_DATE , OFFENDER_PROCEEDING_ID , DIRECTION_CODE , HOLD_FLAG , SEAL_FLAG , SCHEDULED_TRIP_ID, APPEARANCE_LOCATION, APPEARANCE_TYPE, MATTER, email_flag, sms_flag, email_schedule_hours_before, sms_schedule_hours_before ) VALUES(:eventId, NULL , :offenderBookId , :eventDate , :startTime , :endTime , :hearingReason , :judgeName , :eventStatus , :parentEventId , :court , :outcomeReasonCode , :commentText , current_timestamp , :createUserId , NULL , :eventOutcome , :nextEventRequestFlag , :orderRequestedFlag , :resultCode , :nextEventDate , :nextEventStartTime , :outcomeDate , NULL , :directionCode , :holdFlag , :sealFlag , NULL, :appearanceLocation, :appearanceType, :matter, :emailFlag, :smsFlag, :emailScheduleHoursBefore, :smsScheduleHoursBefore )
}

OIDCRTEV_CRTEVE_UPDATE_COURT_EVENTS {
	UPDATE COURT_EVENTS set EVENT_ID  = :eventId ,OFFENDER_BOOK_ID  = :offenderBookId ,EVENT_DATE  = :eventDate ,START_TIME  = :startTime ,END_TIME  = :endTime,JUDGE_NAME  = :judgeName ,EVENT_STATUS  = :eventStatus ,PARENT_EVENT_ID  = :parentEventId ,AGY_LOC_ID  = :court ,OUTCOME_REASON_CODE  = :outcomeReasonCode ,COMMENT_TEXT  = :commentText ,EVENT_OUTCOME  = :eventOutcome ,NEXT_EVENT_REQUEST_FLAG  = :nextEventRequestFlag ,ORDER_REQUESTED_FLAG  = :orderRequestedFlag ,RESULT_CODE  = :resultCode ,NEXT_EVENT_DATE  = :nextEventDate ,NEXT_EVENT_START_TIME  = :nextEventStartTime ,OUTCOME_DATE  = :outcomeDate ,OFFENDER_PROCEEDING_ID  = NULL ,DIRECTION_CODE  = :directionCode ,HOLD_FLAG  = :holdFlag ,SEAL_FLAG  = :sealFlag ,SCHEDULED_TRIP_ID  = NULL  ,MATTER = :matter,
	APPEARANCE_LOCATION = :appearanceLocation , APPEARANCE_TYPE = :appearanceType ,COURT_EVENT_TYPE  = :hearingReason,modify_user_id =:modifyUserId, modify_datetime = current_timestamp,
	email_flag=:emailFlag,sms_flag=:smsFlag,sms_schedule_hours_before=:smsScheduleHoursBefore,email_schedule_hours_before=:emailScheduleHoursBefore
	where  EVENT_ID = :eventId 
}

OIDCRTEV_CRT_EVE_PREINSERT {
	SELECT NEXTVAL('EVENT_ID') FROM DUAL
}

OIDCRTEV_HEARING_REASON_RECORDGROUP{
SELECT ACTIVE_FLAG,MOVEMENT_TYPE,MOVEMENT_REASON_CODE CODE ,DESCRIPTION FROM MOVEMENT_REASONS WHERE MOVEMENT_TYPE='CRT'
}

OIDCRTEV_APPERANCE_LOCATION_RECORDGROUP{

select
	*
from
	(
	select
		ail.ili::text code,
		description description,
		ail.agy_loc_id
	from
		(
		select
			*
		from
			(
			select
				*
			from
				int_loc_usage_locations) loc
		where
			(
			select
				case
					when COUNT(*)= 0 then 'Y'
					else 'N'
				end lowest_level_flag
			from
				int_loc_usage_locations ail2
			where
				ail2.parent_usage_location_id = loc.usage_location_id ) = 'Y') ilul,
		(
		select
			internal_location_usage_id iluid
		from
			internal_location_usages
		where
			internal_location_usage = 'CEL'
			 ) ilu,
		(
		select
			internal_location_id ili,
			INTERNAL_LOCATION_CODE ,
			description,
			agy_loc_id
		from
			agency_internal_locations ) ail
	where
		ilul.internal_location_usage_id = ilu.iluid
		and ilul.internal_location_id = ail.ili ) a
}
OIDCRTEV_NONASSOCIATION_FIND_COURT_EVENTS_BY_INP{
select * from court_events where OFFENDER_BOOK_ID =:nsOffenderBookId and agy_loc_id=:agyLocId and event_date=:eventDate::timestamp and appearance_type='INP'
}
OIDCRTEV_NONASSOCIATION_FIND_COURT_EVENTS_BY_OEM_OR_VID{
select * from court_events where OFFENDER_BOOK_ID =:nsOffenderBookId and appearance_location=:appearanceLocation and event_date=:eventDate::timestamp and appearance_type in('VID','OME')
}
OIDCRTEV_NONASSOCIATATION_TYPES{
select
	int_loc_profile_code
from
	agy_int_loc_profiles
where
	internal_location_id in (
	select
		internal_location_id
	from
		agency_internal_locations
	where
		internal_location_code =:internalLocationCode)
	and int_loc_profile_type = 'NON_ASSO_TYP'
}

OIDCRTEV_SCH_NONASSOCIATION_OFFENDERS_OME{
	select * from OFFENDER_NON_ASSOCIATIONS where OFFENDER_BOOK_ID =:offenderBookId and ns_reason_code in (:profileCodeList);
}
OIDCRTEV_GET_NON_ASSOCATION_OFFENDER_DETAILS {
select
	vhb.*,
	ce.start_time as startTime,
	ce.event_date as eventDate
from
	court_events ce,
	(
	select
		O.OFFENDER_ID_DISPLAY ,
		O.LAST_NAME ,
		O.FIRST_NAME ,
		OB.OFFENDER_BOOK_ID
	from
		OFFENDERS O,
		OFFENDER_BOOKINGS OB
	where
		OB.OFFENDER_ID = O.OFFENDER_ID ) as vhb
where
	vhb.offender_book_id = ce.offender_book_id
	and ce.offender_book_id in (
	select
		ns_offender_book_id
	from
		OFFENDER_NON_ASSOCIATIONS ona
	where
		ona.offender_book_id =:offenderBookId
		and ona.NS_OFFENDER_ID in 
	(
		select
			nad.NS_OFFENDER_ID
		from
			OFFENDER_NA_DETAILS nad
		where
			nad.offender_book_id =:offenderBookId
			and 
	current_date < coalesce(nad.ns_expiry_date, current_date + 1)
				and current_date >= nad.
	ns_effective_date)
		and ns_reason_code in ((
		select
			int_loc_profile_code
		from
			agy_int_loc_profiles
		where
			internal_location_id = (
			select
				internal_location_id
			from
				int_loc_usage_locations ilul
			where
				internal_location_usage_id =(
				select
					internal_location_usage_id
				from
					internal_location_usages
				where
					internal_location_usage = 'CEL'
					and 
agy_loc_id = :caseLoad)
				and ilul.internal_location_id in 
(
				select
					internal_location_id
				from
					agency_internal_locations
				where
					internal_location_code =:internalLocationCode))
			and int_loc_profile_type = 'NON_ASSO_TYP'))
	)
	and ce.appearance_location =:internalLocationCode
	and ce.event_date =:eventDate::timestamp
	and ce.appearance_type in('VID', 'OME')
}
OIDCRTEV_GET_DEFAULT_CANCELATION_REASON
{
select code from reference_codes where domain ='CRT_CAN_RSN' and list_seq =1 and active_flag ='Y'
}
OIDCRTEV_SCREEN_ACESS_CHECK
{
select count(*)  from MODULE_PRIVILEGES where role_id in (SELECT role_id  FROM STAFF_MEMBER_ROLES   where  STAFF_ID = (select staff_id  from staff_members where user_id =:userId))
and module_name ='OIDCRTEV' and access_privilege ='A'
}
OIDCRTEV_EMAIL_AND_PHONE_NUMBER_CHECK
{
select
	(
	select
		count(*)
	from
		INTERNET_ADDRESSES
	where
		owner_id = 
(
		select
			ROOT_OFFENDER_ID
		from
			v_header_block
		where
			offender_book_id = :offenderBookId)) as emailCount,
	(
	select
		COUNT(*)
	from
		PHONES
	where
		owner_id =
(
		select
			ROOT_OFFENDER_ID
		from
			v_header_block
		where
			offender_book_id = :offenderBookId )
		and format = 'MOB' and owner_class='OFF') as phoneNumberCount
}

OIDCRTEV_GET_APPERANCE_LOCATION_RECORDGROUP{
select * from ( select ail.ili::text code, description description from (select * from (select * from int_loc_usage_locations) loc where (select case when COUNT(*)= 0 then 'Y' else 'N' end lowest_level_flag from int_loc_usage_locations ail2 where ail2.parent_usage_location_id = loc.usage_location_id ) = 'Y') ilul, ( select internal_location_usage_id iluid from internal_location_usages where internal_location_usage = 'CEL' and agy_loc_id = :AGY_LOC_ID ) ilu, ( select internal_location_id ili, INTERNAL_LOCATION_CODE , description from agency_internal_locations ) ail where ilul.internal_location_usage_id = ilu.iluid and ilul.internal_location_id = ail.ili ) a

}

