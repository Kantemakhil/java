
OIDBSIAP_FIND_RGSCHINTERNALSCHEDULE {
 	SELECT DESCRIPTION ,        INTERNAL_SCHEDULE_RSN_CODE CODE   FROM INTERNAL_SCHEDULE_REASONS  WHERE INTERNAL_SCHEDULE_TYPE = 'APP'    AND ( ACTIVE_FLAG = 'Y')     ORDER BY LIST_SEQ , DESCRIPTION
}

OIDBSIAP_FIND_RGAGYLOC {
 	SELECT AGY_LOC.DESCRIPTION DESCRIPTION ,  AGY_LOC.AGY_LOC_ID CODE   FROM AGENCY_LOCATIONS AGY_LOC  WHERE AGENCY_LOCATION_TYPE = 'INST'    AND AGY_LOC_ID IN (SELECT AGY_LOC_ID                         FROM CASELOAD_AGENCY_LOCATIONS                        WHERE CASELOAD_ID = :CASELOADID )    AND AGY_LOC_ID NOT IN ('OUT' , 'TRN' ) ORDER BY LIST_SEQ , DESCRIPTION
}

OIDBSIAP_FIND_RGINTERNALMOVELOCATIONS {
 	select C.DESCRIPTION , C.INTERNAL_LOCATION_CODE CODE, C.INTERNAL_LOCATION_ID from INT_LOC_USAGE_LOCATIONS A , INTERNAL_LOCATION_USAGES B , AGENCY_INTERNAL_LOCATIONS C where A.INTERNAL_LOCATION_USAGE_ID = B.INTERNAL_LOCATION_USAGE_ID and B.INTERNAL_LOCATION_USAGE = 'APP' and B.AGY_LOC_ID = :AGENCYLOCID and A.INTERNAL_LOCATION_ID = C.INTERNAL_LOCATION_ID and ((C.ACTIVE_FLAG = 'Y' and coalesce (C.DEACTIVATE_DATE ::timestamp, '2020-12-12')= '2020-12-12' ) or (:mode = 'ENTER-QUERY' ) ) and not exists ( select 1 from INT_LOC_USAGE_LOCATIONS where PARENT_USAGE_LOCATION_ID = A.USAGE_LOCATION_ID ) and C.INTERNAL_LOCATION_CODE <> 'RTU' order by C.LIST_SEQ , DESCRIPTION
}

OIDBSIAP_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES_2 {
 	SELECT EVENT_ID ,OFFENDER_BOOK_ID ,AGY_LOC_ID ,EVENT_DATE ,START_TIME ,END_TIME ,EVENT_CLASS ,EVENT_TYPE ,EVENT_SUB_TYPE ,EVENT_STATUS ,EVENT_OUTCOME ,CONFIRM_FLAG ,OUTCOME_REASON_CODE ,COMMENT_TEXT ,REFERENCE_ID ,APPLICATION_DATE ,APPLICATION_TIME ,RETURN_DATE ,RETURN_TIME ,TO_AGY_LOC_ID ,ESCORT_CODE ,DIRECTION_CODE ,SCHEDULE_MOVEMENT_TIME ,TO_INTERNAL_LOCATION_ID ,FROM_CITY_CODE ,TO_CITY_CODE ,CREDITED_HOURS ,PIECE_WORK ,ENGAGEMENT_CODE ,UNDERSTANDING_CODE ,DETAILS ,UNPAID_WORK_BEHAVIOUR ,UNPAID_WORK_ACTION ,SICK_NOTE_RECEIVED_DATE ,SICK_NOTE_EXPIRY_DATE ,UNEXCUSED_ABSENCE_FLAG ,IN_TIME ,OUT_TIME ,TRANSPORT_CODE ,PERFORMANCE_CODE ,AGREED_TRAVEL_HOUR ,CHECK_BOX_1 ,CHECK_BOX_2 ,HIDDEN_COMMENT_TEXT ,IN_CHARGE_STAFF_ID ,OFF_PRGREF_ID ,CONTACT_PERSON_NAME ,TO_ADDRESS_OWNER_CLASS ,TO_ADDRESS_ID ,TO_CORPORATE_ID ,UNPAID_WORK_SUPERVISOR ,TA_ID ,RECORD_SOURCE ,CHECK_SUM ,PROV_STATE_CODE ,SCHEDULED_TRIP_ID   FROM V_OFFENDER_ALL_SCHEDULES_2  /* where  */
}
OIDBSIAP_OFFSCH_INSERT_V_OFFENDER_ALL_SCHEDULES_2 {
	INSERT INTO V_OFFENDER_ALL_SCHEDULES_2(EVENT_ID ,OFFENDER_BOOK_ID ,AGY_LOC_ID ,EVENT_DATE ,START_TIME ,END_TIME ,EVENT_CLASS ,EVENT_TYPE ,EVENT_SUB_TYPE ,EVENT_STATUS ,EVENT_OUTCOME ,CONFIRM_FLAG ,OUTCOME_REASON_CODE ,COMMENT_TEXT ,REFERENCE_ID ,APPLICATION_DATE ,APPLICATION_TIME ,RETURN_DATE ,RETURN_TIME ,TO_AGY_LOC_ID ,ESCORT_CODE ,DIRECTION_CODE ,SCHEDULE_MOVEMENT_TIME ,TO_INTERNAL_LOCATION_ID ,FROM_CITY_CODE ,TO_CITY_CODE ,CREDITED_HOURS ,PIECE_WORK ,ENGAGEMENT_CODE ,UNDERSTANDING_CODE ,DETAILS ,UNPAID_WORK_BEHAVIOUR ,UNPAID_WORK_ACTION ,SICK_NOTE_RECEIVED_DATE ,SICK_NOTE_EXPIRY_DATE ,UNEXCUSED_ABSENCE_FLAG ,IN_TIME ,OUT_TIME ,TRANSPORT_CODE ,PERFORMANCE_CODE ,AGREED_TRAVEL_HOUR ,CHECK_BOX_1 ,CHECK_BOX_2 ,HIDDEN_COMMENT_TEXT ,IN_CHARGE_STAFF_ID ,OFF_PRGREF_ID ,CONTACT_PERSON_NAME ,TO_ADDRESS_OWNER_CLASS ,TO_ADDRESS_ID ,TO_CORPORATE_ID ,UNPAID_WORK_SUPERVISOR ,TA_ID ,RECORD_SOURCE ,CHECK_SUM ,PROV_STATE_CODE ,SCHEDULED_TRIP_ID,CREATE_USER_ID, CREATE_DATETIME, ) VALUES(:eventId ,:offenderBookId ,:agyLocId ,:eventDate ,:startTime ,:endTime ,:eventClass ,:eventType ,:eventSubType ,:eventStatus ,:eventOutcome ,:confirmFlag ,:outcomeReasonCode ,:commentText ,:referenceId ,:applicationDate ,:applicationTime ,:returnDate ,:returnTime ,:toAgyLocId ,:escortCode ,:directionCode ,:scheduleMovementTime ,:toInternalLocationId ,:fromCityCode ,:toCityCode ,:creditedHours ,:pieceWork ,:engagementCode ,:understandingCode ,:details ,:unpaidWorkBehaviour ,:unpaidWorkAction ,:sickNoteReceivedDate ,:sickNoteExpiryDate ,:unexcusedAbsenceFlag ,:inTime ,:outTime ,:transportCode ,:performanceCode ,:agreedTravelHour ,:checkBox1 ,:checkBox2 ,:hiddenCommentText ,:inChargeStaffId ,:offPrgrefId ,:contactPersonName ,:toAddressOwnerClass ,:toAddressId ,:toCorporateId ,:unpaidWorkSupervisor ,:taId ,:recordSource ,:checkSum ,:provStateCode ,:scheduledTripId, :createUserId, current_timestamp)
}

OIDBSIAP_OFFSCH_UPDATE_V_OFFENDER_ALL_SCHEDULES_2 {
	UPDATE V_OFFENDER_ALL_SCHEDULES_2 set EVENT_ID  = :eventId MODIFY_DATETIME=current_timestamp ,MODIFY_USER_ID=:modifyUserId, OFFENDER_BOOK_ID  = :offenderBookId ,AGY_LOC_ID  = :agyLocId ,EVENT_DATE  = :eventDate ,START_TIME  = :startTime ,END_TIME  = :endTime ,EVENT_CLASS  = :eventClass ,EVENT_TYPE  = :eventType ,EVENT_SUB_TYPE  = :eventSubType ,EVENT_STATUS  = :eventStatus ,EVENT_OUTCOME  = :eventOutcome ,CONFIRM_FLAG  = :confirmFlag ,OUTCOME_REASON_CODE  = :outcomeReasonCode ,COMMENT_TEXT  = :commentText ,REFERENCE_ID  = :referenceId ,APPLICATION_DATE  = :applicationDate ,APPLICATION_TIME  = :applicationTime ,RETURN_DATE  = :returnDate ,RETURN_TIME  = :returnTime ,TO_AGY_LOC_ID  = :toAgyLocId ,ESCORT_CODE  = :escortCode ,DIRECTION_CODE  = :directionCode ,SCHEDULE_MOVEMENT_TIME  = :scheduleMovementTime ,TO_INTERNAL_LOCATION_ID  = :toInternalLocationId ,FROM_CITY_CODE  = :fromCityCode ,TO_CITY_CODE  = :toCityCode ,CREDITED_HOURS  = :creditedHours ,PIECE_WORK  = :pieceWork ,ENGAGEMENT_CODE  = :engagementCode ,UNDERSTANDING_CODE  = :understandingCode ,DETAILS  = :details ,UNPAID_WORK_BEHAVIOUR  = :unpaidWorkBehaviour ,UNPAID_WORK_ACTION  = :unpaidWorkAction ,SICK_NOTE_RECEIVED_DATE  = :sickNoteReceivedDate ,SICK_NOTE_EXPIRY_DATE  = :sickNoteExpiryDate ,UNEXCUSED_ABSENCE_FLAG  = :unexcusedAbsenceFlag ,IN_TIME  = :inTime ,OUT_TIME  = :outTime ,TRANSPORT_CODE  = :transportCode ,PERFORMANCE_CODE  = :performanceCode ,AGREED_TRAVEL_HOUR  = :agreedTravelHour ,CHECK_BOX_1  = :checkBox1 ,CHECK_BOX_2  = :checkBox2 ,HIDDEN_COMMENT_TEXT  = :hiddenCommentText ,IN_CHARGE_STAFF_ID  = :inChargeStaffId ,OFF_PRGREF_ID  = :offPrgrefId ,CONTACT_PERSON_NAME  = :contactPersonName ,TO_ADDRESS_OWNER_CLASS  = :toAddressOwnerClass ,TO_ADDRESS_ID  = :toAddressId ,TO_CORPORATE_ID  = :toCorporateId ,UNPAID_WORK_SUPERVISOR  = :unpaidWorkSupervisor ,TA_ID  = :taId ,RECORD_SOURCE  = :recordSource ,CHECK_SUM  = :checkSum ,PROV_STATE_CODE  = :provStateCode ,SCHEDULED_TRIP_ID  = :scheduledTripId /* where */
}

OIDBSIAP_CHECK_NA_ {
	SELECT OFFENDER_ID from V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID
}

OIDBSIAP_INSERT_OFFENDER_IND_SCHEDULES {
insert into OFFENDER_IND_SCHEDULES(EVENT_ID, OFFENDER_BOOK_ID, EVENT_DATE, START_TIME, END_TIME, EVENT_CLASS, EVENT_TYPE, EVENT_SUB_TYPE, EVENT_STATUS, COMMENT_TEXT, HIDDEN_COMMENT_TEXT, APPLICATION_DATE, PARENT_EVENT_ID, AGY_LOC_ID, TO_AGY_LOC_ID, FROM_CITY, TO_CITY, CRS_SCH_ID, ORDER_ID, SENTENCE_SEQ, OUTCOME_REASON_CODE, JUDGE_NAME, CHECK_BOX_1, CHECK_BOX_2, CREDITED_HOURS, REPORT_IN_DATE, PIECE_WORK, ENGAGEMENT_CODE, UNDERSTANDING_CODE, DETAILS, CREDITED_WORK_HOUR, AGREED_TRAVEL_HOUR, UNPAID_WORK_SUPERVISOR, UNPAID_WORK_BEHAVIOUR, UNPAID_WORK_ACTION, SICK_NOTE_RECEIVED_DATE, SICK_NOTE_EXPIRY_DATE, COURT_EVENT_RESULT, UNEXCUSED_ABSENCE_FLAG, CREATE_USER_ID, CREATE_DATETIME, ESCORT_CODE, CONFIRM_FLAG, DIRECTION_CODE, TO_CITY_CODE, FROM_CITY_CODE, OFF_PRGREF_ID, IN_TIME, OUT_TIME, PERFORMANCE_CODE, TEMP_ABS_SCH_ID, TRANSPORT_CODE, APPLICATION_TIME, CONTACT_PERSON_NAME, TO_ADDRESS_OWNER_CLASS, RETURN_DATE, RETURN_TIME, TO_CORPORATE_ID, TA_ID, EVENT_OUTCOME, PROV_STATE_CODE, SEAL_FLAG, TO_INTERNAL_LOCATION_ID, MODIFY_USER_ID, MODIFY_DATETIME) values (nextval('event_id'), :offenderBookId, :eventDate, :startTime, :endTime, :eventClass, :eventType, :eventSubType, :eventStatus, :commentText, :hiddenCommentText, :applicationDate, :parentEventId, :agyLocId, :toAgyLocId, :fromCity, :toCity, :crsSchId, :orderId, :sentenceSeq, :outcomeReasonCode, :judgeName, :checkBox1, :checkBox2, :creditedHours, :reportInDate, :pieceWork, :engagementCode, :understandingCode, :details, :creditedWorkHour, :agreedTravelHour, :unpaidWorkSupervisor, :unpaidWorkBehaviour, :unpaidWorkAction, :sickNoteReceivedDate, :sickNoteExpiryDate, :courtEventResult, :unexcusedAbsenceFlag, :createUserId, current_timestamp, :escortCode, :confirmFlag, :directionCode, :toCityCode, :fromCityCode, :offPrgrefId, :inTime, :outTime, :performanceCode, :tempAbsSchId, :transportCode, :applicationTime, :contactPersonName, :toAddressOwnerClass, :returnDate, :returnTime, :toCorporateId, :taId, :eventOutcome, :provStateCode, :sealFlag, :toInternalLocationId, null, null) 
    }

OIDBSIAP_UPDATE_OFFENDER_IND_SCHEDULES {
   UPDATE OFFENDER_IND_SCHEDULES SET EVENT_DATE = :eventDate ,MODIFY_DATETIME=current_timestamp ,MODIFY_USER_ID=:modifyUserId, START_TIME = :startTime,EVENT_SUB_TYPE =:eventSubType, COMMENT_TEXT = :commentText, TO_INTERNAL_LOCATION_ID = :toInternalLocationId,EVENT_STATUS=:eventStatus,EVENT_OUTCOME=:eventOutcome
     WHERE EVENT_ID = :eventId
       }
       
 OIDBSIAP_CHECK_SCHEDULE_CONFLICT {
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
    
 OIDBSIAPP_GET_NONASSOCIATION_CONFIG_FOR_LOCATION
{
SELECT
internal_location_id,
int_loc_profile_type,
int_loc_profile_code
FROM
agy_int_loc_profiles
WHERE
internal_location_id = :internalLocationId
AND int_loc_profile_type = 'NON_ASSO_TYP'
}

OIDBSIAPP_SCH_NONASSOCIATION_OFFENDERS{
SELECT
	ona.*,
	(select last_name  from offenders o, offender_bookings ob where ob.offender_book_id  = ona.ns_offender_book_id  and ob.offender_id = o.offender_id) as last_name ,
	(select first_name  from offenders o, offender_bookings ob where ob.offender_book_id  = ona.ns_offender_book_id  and ob.offender_id = o.offender_id) as first_name,
	lpad(ona.offender_id::text , 10, '0') as offender_id_display
FROM
	OFFENDER_NON_ASSOCIATIONS ona
WHERE
	ona.offender_book_id =:offenderBookId
	AND
	ona.NS_OFFENDER_ID IN 
	(
	SELECT
		nad.NS_OFFENDER_ID
	FROM
		OFFENDER_NA_DETAILS nad
	WHERE
		nad.offender_book_id =:offenderBookId
		AND 
	current_date < COALESCE(nad.ns_expiry_date,
		current_date + 1)
			AND current_date >= nad.ns_effective_date)
 }

 OIDBIAPP_CHECK_NONASSOCOATION_SCHEDULE{
SELECT * FROM OFFENDER_IND_SCHEDULES WHERE OFFENDER_BOOK_ID = :offenderBookID AND EVENT_DATE = :eventDate::timestamp and  to_internal_location_id =:toInternalLocationId and event_status = 'SCH' limit 1

}
OIDBIAPP_GET_NS_OFFENDER_BOOK_ID{
select * from offender_non_associations ona where offender_book_id =:offenderBookId
}
OIDBSIAP_GET_OFFENDER_NAME{
select concat(last_name,'  ',first_name) from v_header_block vhb where offender_book_id =:offenderBookId
}

   