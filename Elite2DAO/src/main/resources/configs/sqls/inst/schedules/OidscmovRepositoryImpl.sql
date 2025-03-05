
OIDSCMOV_FIND_RGCTRLINST {
 	SELECT AGY_LOC.AGY_LOC_ID           ,AGY_LOC.DESCRIPTION    FROM   AGENCY_LOCATIONS AGY_LOC WHERE   AGENCY_LOCATION_TYPE = 'INST' AND AGY_LOC.AGY_LOC_ID NOT IN ('OUT' , 'TRN' ) AND EXISTS (SELECT 'X' FROM CASELOAD_AGENCY_LOCATIONS CAL WHERE CAL.AGY_LOC_ID = AGY_LOC.AGY_LOC_ID AND CAL.CASELOAD_ID =:CASELOADID  )
}

OIDSCMOV_FIND_RGCTRLREASON {
 	SELECT MOVE_RSN.MOVEMENT_REASON_CODE          ,MOVE_RSN.DESCRIPTION   FROM   MOVEMENT_REASONS MOVE_RSN WHERE  MOVE_RSN.MOVEMENT_TYPE = 'CRT' AND ACTIVE_FLAG = 'Y' AND EXPIRY_DATE IS NULL ORDER BY LIST_SEQ
}

OIDSCMOV_FIND_RGCTRLCOURT {
 	SELECT AGY_LOC_ID ,          DESCRIPTION    FROM   AGENCY_LOCATIONS   WHERE  AGENCY_LOCATION_TYPE = 'CRT'   AND  DEACTIVATION_DATE IS NULL ORDER BY AGY_LOC_ID
}

OIDSCMOV_FIND_RGCOURTREA {
 	SELECT MOVEMENT_REASON_CODE           ,DESCRIPTION    FROM   MOVEMENT_REASONS   WHERE MOVEMENT_TYPE = 'CRT' AND ACTIVE_FLAG = 'Y' AND EXPIRY_DATE IS NULL ORDER BY LIST_SEQ
}

OIDSCMOV_CRTEVE_FIND_COURT_EVENTS {
 	SELECT EVENT_ID ,CASE_ID ,OFFENDER_BOOK_ID ,EVENT_DATE ,START_TIME ,END_TIME ,COURT_EVENT_TYPE ,JUDGE_NAME ,EVENT_STATUS ,PARENT_EVENT_ID ,AGY_LOC_ID ,OUTCOME_REASON_CODE ,COMMENT_TEXT ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,EVENT_OUTCOME ,NEXT_EVENT_REQUEST_FLAG ,ORDER_REQUESTED_FLAG ,RESULT_CODE ,NEXT_EVENT_DATE ,NEXT_EVENT_START_TIME ,OUTCOME_DATE ,OFFENDER_PROCEEDING_ID ,DIRECTION_CODE ,HOLD_FLAG ,SEAL_FLAG ,SCHEDULED_TRIP_ID, APPEARANCE_TYPE, APPEARANCE_LOCATION   FROM COURT_EVENTS  /* where  */
}
OIDSCMOV_CRTEVE_INSERT_COURT_EVENTS {
insert into COURT_EVENTS(EVENT_ID , CASE_ID , OFFENDER_BOOK_ID , EVENT_DATE , START_TIME , END_TIME , COURT_EVENT_TYPE , JUDGE_NAME , EVENT_STATUS , PARENT_EVENT_ID , AGY_LOC_ID , OUTCOME_REASON_CODE , COMMENT_TEXT , CREATE_DATETIME , CREATE_USER_ID , EVENT_OUTCOME , NEXT_EVENT_REQUEST_FLAG , ORDER_REQUESTED_FLAG , RESULT_CODE , NEXT_EVENT_DATE , NEXT_EVENT_START_TIME , OUTCOME_DATE , OFFENDER_PROCEEDING_ID , DIRECTION_CODE , HOLD_FLAG , SEAL_FLAG , SCHEDULED_TRIP_ID, APPEARANCE_TYPE, APPEARANCE_LOCATION, MODIFY_USER_ID, MODIFY_DATETIME) values(NEXTVAL('EVENT_ID') , null , :offenderBookId , :eventDate , :startTime , :endTime , :courtEventType , :judgeName , :eventStatus , :parentEventId , :agyLocId , :outcomeReasonCode , :commentText , current_timestamp , :createUserId  , :eventOutcome , :nextEventRequestFlag , :orderRequestedFlag , :resultCode , :nextEventDate , :nextEventStartTime , :outcomeDate , null , :directionCode , :holdFlag , :sealFlag , null, :appearanceType, :appearanceLocation,null,null )
}
OIDSCMOV_CRTEVE_INSERT_COURT_EVENTS_PRE_EVENT_ID {
	INSERT INTO COURT_EVENTS(EVENT_ID ,CASE_ID ,OFFENDER_BOOK_ID ,EVENT_DATE ,START_TIME ,END_TIME ,COURT_EVENT_TYPE ,JUDGE_NAME ,EVENT_STATUS ,PARENT_EVENT_ID ,AGY_LOC_ID ,OUTCOME_REASON_CODE ,COMMENT_TEXT ,CREATE_DATETIME ,CREATE_USER_ID,EVENT_OUTCOME ,NEXT_EVENT_REQUEST_FLAG ,ORDER_REQUESTED_FLAG ,RESULT_CODE ,NEXT_EVENT_DATE ,NEXT_EVENT_START_TIME ,OUTCOME_DATE ,OFFENDER_PROCEEDING_ID ,DIRECTION_CODE ,HOLD_FLAG ,SEAL_FLAG ,SCHEDULED_TRIP_ID, APPEARANCE_TYPE, APPEARANCE_LOCATION, MODIFY_USER_ID, MODIFY_DATETIME
	 ) VALUES(:eventId, NULL ,:offenderBookId ,:eventDate ,:startTime ,:endTime ,:courtEventType ,:judgeName ,:eventStatus ,:parentEventId ,:agyLocId ,:outcomeReasonCode ,:commentText ,:createDatetime ,:createUserId ,:eventOutcome ,:nextEventRequestFlag ,:orderRequestedFlag ,:resultCode ,:nextEventDate ,:nextEventStartTime ,:outcomeDate ,NULL ,:directionCode ,:holdFlag ,:sealFlag ,NULL, :appearanceType, :appearanceLocation, null, null )
}
OIDSCMOV_CRTEVE_UPDATE_COURT_EVENTS {
update COURT_EVENTS set EVENT_ID = :eventId , CASE_ID = null , OFFENDER_BOOK_ID = :offenderBookId , EVENT_DATE = :eventDate , START_TIME = :startTime , END_TIME = :endTime , COURT_EVENT_TYPE = :courtEventType , JUDGE_NAME = :judgeName , EVENT_STATUS = :eventStatus , PARENT_EVENT_ID = :parentEventId , AGY_LOC_ID = :agyLocId , OUTCOME_REASON_CODE = :outcomeReasonCode , COMMENT_TEXT = :commentText , EVENT_OUTCOME = :eventOutcome , NEXT_EVENT_REQUEST_FLAG = :nextEventRequestFlag , ORDER_REQUESTED_FLAG = :orderRequestedFlag , RESULT_CODE = :resultCode , NEXT_EVENT_DATE = :nextEventDate , NEXT_EVENT_START_TIME = :nextEventStartTime , OUTCOME_DATE = :outcomeDate , OFFENDER_PROCEEDING_ID = null , DIRECTION_CODE = :directionCode , HOLD_FLAG = :holdFlag , SEAL_FLAG = :sealFlag , SCHEDULED_TRIP_ID = null, MODIFY_DATETIME =current_timestamp , MODIFY_USER_ID =:modifyUserId, APPEARANCE_TYPE=:appearanceType, APPEARANCE_LOCATION=:appearanceLocation where EVENT_ID = :eventId 
}

OIDSCMOV_CRTEVE_DELETE_COURT_EVENTS { 
	DELETE FROM COURT_EVENTS where  EVENT_ID = :eventId
}


OIDSCMOV_CRT_EVE_PREINSERT_ {
SELECT NEXTVAL('EVENT_ID') FROM DUAL
}

OIDSCMOV_GET_CURRENT_DATE {
SELECT current_timestamp FROM DUAL
}

OIINAMES_NAMESRCH_SEARCH_V_NAME_SEARCH {
 	SELECT LAST_NAME ,FIRST_NAME,OFFENDER_BOOK_ID ,OFFENDER_ID_DISPLAY,AGY_LOC_ID  FROM V_NAME_SEARCH_FN(:userId)  where OFFENDER_BOOK_ID=:OFFENDER_ID_DISPLAY
}

OIDSCMOV_CHECK_SCHEDULE_CONFLICT {
select
	count(*)
from
	V_OFFENDER_ALL_SCHEDULES_2
where
	OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
	and EVENT_STATUS = 'SCH'
	and :EVENT_DATE::timestamp >= EVENT_DATE 
	and :EVENT_DATE::timestamp <= COALESCE(RETURN_DATE, EVENT_DATE)
}

OIDSCMOV_CHK_NA_CONFLICT_NA_CUR{
SELECT DISTINCT ob.offender_book_id FROM offender_bookings ob WHERE EXISTS ( SELECT 'X' FROM offender_na_details ond WHERE ( ond.offender_book_id = :p_offender_book_id AND ond.ns_offender_book_id = ob.offender_book_id ) OR ( ond.offender_book_id = ob.offender_book_id AND ond.ns_offender_book_id = :p_offender_book_id ) AND (ond.ns_expiry_date > current_timestamp OR ond.ns_expiry_date IS NULL))
}

OIDSCMOV_CHK_NA_CONFLICT_LV_NA_COUNT{
  SELECT COUNT (1)
           FROM offender_na_details
          WHERE offender_book_id = :p_offender_book_id OR ns_offender_book_id = :p_offender_book_id
   }       

 OIDSCMOV_CHK_NA_CONFLICT_CUR{  
 SELECT COUNT (1) FROM v_offender_all_schedules WHERE offender_book_id = :lv_na_off_book_id AND to_agy_loc_id = :p_court_agy_loc_id AND event_date = :p_event_date
 }
 OIDSCMOV_EXTERNAL_IND_NON_ASSOCATION_FOR_INP
 { 
select
	vhb.*,
	ce.start_time  as startTime,
	(select
		DESCRIPTION
	from
		AGENCY_LOCATIONS
	where
		AGENCY_LOCATION_TYPE = 'CRT'
		and DEACTIVATION_DATE is null
		and agy_loc_id = ce.agy_loc_id) as agyLocId,
	ce.offender_book_id,
	ce.create_datetime 
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
		ona.ns_offender_book_id
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
				and current_date >= nad.ns_effective_date))
	and ce.agy_loc_id =:agyLocId
	and ce.event_date =:eventDate::timestamp
	and ce.appearance_type = 'INP'
 }
 OIDSCMOV_EXTERNAL_IND_NON_ASSOCATION_FOR_VID_OR_OME
 {
select
	vhb.*,
	ce.start_time as startTime,
	(
	select
		description
	from
		agency_internal_locations ail
	where
		internal_location_id in (
		select
			internal_location_id
		from
			int_loc_usage_locations
		where
			internal_location_usage_id =(
			select
				internal_location_usage_id
			from
				internal_location_usages
			where
				internal_location_usage = 'CEL'
				and agy_loc_id = :caseLoad )
	)
		and ail.internal_location_code = ce.appearance_location
	) as agyLocId,
	ce.offender_book_id,
	ce.create_datetime
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
		ona.ns_offender_book_id
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
				and current_date >= nad.ns_effective_date)
		and ona.ns_reason_code in(
		select
			int_loc_profile_code
		from
			agy_int_loc_profiles ailp
		where
			internal_location_id =(
			select
				internal_location_id
			from
				agency_internal_locations ail
			where
				internal_location_id in (
				select
					internal_location_id
				from
					int_loc_usage_locations
				where
					internal_location_usage_id =(
					select
						internal_location_usage_id
					from
						internal_location_usages
					where
						internal_location_usage = 'CEL'
						and agy_loc_id = :caseLoad ))
				and ail.internal_location_code =:appearanceLocation )
			and ailp.int_loc_profile_type = 'NON_ASSO_TYP'))
	and ce.appearance_location =:appearanceLocation
	and ce.event_date =:eventDate::timestamp
	and ce.appearance_type in('VID', 'OME')
 }
 OIDSCMOV_INTERNAL_IND_NON_ASSOCATION_FOR_INP_OR_VID_OME{
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
			and current_date >= nad.ns_effective_date)
	and ona.ns_offender_book_id in (:internalList)
 }
 OIDSCMOV_EXTERNAL_GANG_NON_ASSOCATION_FOR_INP
 {
 select
	vhb.*,
	ce.start_time as startTime,
	(select
		DESCRIPTION
	from
		AGENCY_LOCATIONS
	where
		AGENCY_LOCATION_TYPE = 'CRT'
		and DEACTIVATION_DATE is null
		and agy_loc_id = ce.agy_loc_id)  as agyLocId,
	ce.offender_book_id,
	ce.create_datetime ,
	ce.event_date 
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
	select distinct offender_book_id from OFFENDER_STG_AFFILIATIONS osa 
	where osa.active_flag = 'Y' 
	and osa.offender_book_id <> :offenderBookId
	and osa.stg_id in (
	select sr.related_stg_id from STG_RELATIONSHIPS sr 
	where active_flag = 'Y' 
	and sr.stg_id in (
	select osa1.stg_id 
	from OFFENDER_STG_AFFILIATIONS osa1
	where osa1.offender_book_id = :offenderBookId
	and active_flag = 'Y'))
	)
	and ce.agy_loc_id =:agyLocId
	and ce.event_date =:eventDate::timestamp
	and ce.appearance_type = 'INP'
 }
 OIDSCMOV_EXTERNAL_GANG_NON_ASSOCATION_FOR_VID_OME
 {
select
	vhb.*,
	ce.start_time as startTime,
	(
	select
		description
	from
		agency_internal_locations ail
	where
		internal_location_id in (
		select
			internal_location_id
		from
			int_loc_usage_locations
		where
			internal_location_usage_id =(
			select
				internal_location_usage_id
			from
				internal_location_usages
			where
				internal_location_usage = 'CEL'
				and agy_loc_id = :caseLoad ))
		and internal_location_code = ce.appearance_location) as agyLocId,
	ce.offender_book_id,
	ce.create_datetime
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
		distinct offender_book_id
	from
		OFFENDER_STG_AFFILIATIONS osa
	where
		osa.active_flag = 'Y'
		and osa.offender_book_id <> :offenderBookId
		and osa.stg_id in (
		select
			sr.related_stg_id
		from
			STG_RELATIONSHIPS sr
		where
			active_flag = 'Y'
			and sr.stg_id in (
			select
				osa1.stg_id
			from
				OFFENDER_STG_AFFILIATIONS osa1
			where
				osa1.offender_book_id = :offenderBookId
				and active_flag = 'Y')))
	and ce.appearance_location =:appearanceLocation
	and ce.event_date =:eventDate::timestamp
	and ce.appearance_type in('VID', 'OME')
 }
 OIDSCMOV_INTERNAL_GANG_NON_ASSOCATION_FOR_INP_OR_VID_OME
 {
 select distinct offender_book_id from OFFENDER_STG_AFFILIATIONS osa 
	where osa.active_flag = 'Y' 
	and osa.offender_book_id <> :offenderBookId
	and osa.stg_id in (
	select sr.related_stg_id from STG_RELATIONSHIPS sr 
	where active_flag = 'Y' 
	and sr.stg_id in (
	select osa1.stg_id 
	from OFFENDER_STG_AFFILIATIONS osa1
	where osa1.offender_book_id = :offenderBookId
	and active_flag = 'Y')) and osa.offender_book_id in (:inernalList)
 }
 OIDSCMOV_INTERNAL_IND_NON_ASSOCATION_FOR_INP_OR_VID_OME_LOCATION
 {
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
			and current_date >= nad.ns_effective_date
			and ona.ns_reason_code in(
			select
				int_loc_profile_code
			from
				agy_int_loc_profiles ailp
			where
				internal_location_id =(
				select
					internal_location_id
				from
					agency_internal_locations ail
				where
					internal_location_id in (
					select
						internal_location_id
					from
						int_loc_usage_locations
					where
						internal_location_usage_id =(
						select
							internal_location_usage_id
						from
							internal_location_usages
						where
							internal_location_usage = 'CEL'
							and agy_loc_id = :caseLoad ))
					and ail.internal_location_code =:appearanceLocation )
				and ailp.int_loc_profile_type = 'NON_ASSO_TYP'))
	and ona.ns_offender_book_id in (:internalList)
 }