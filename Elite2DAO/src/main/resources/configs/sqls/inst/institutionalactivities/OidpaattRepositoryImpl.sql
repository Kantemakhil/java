

OIDPAATT_FIND_RGPSACTPERF {
 	select
	REF_CODE.DESCRIPTION ,
	REF_CODE.CODE
from
	REFERENCE_CODES REF_CODE
where
	domain = 'PERFORMANCE'
	and (( ACTIVE_FLAG = 'Y'
		and EXPIRED_DATE is null )
	)
order by
	LIST_SEQ ,
	DESCRIPTION ,
	CODE
}

OIDPAATT_FIND_RGOUTCOMES {
 	select
	REF_CODE.DESCRIPTION ,
	REF_CODE.CODE
from
	REFERENCE_CODES REF_CODE
where
	domain = 'PS_PA_OC'
	and (( ACTIVE_FLAG = 'Y'
		and EXPIRED_DATE is null )
	)
order by
	LIST_SEQ ,
	DESCRIPTION ,
	CODE
}

OIDPAATT_FIND_RGSERVICES {
 	select
	distinct VCP.SERVICE DESCRIPTION ,
	VCP.PROGRAM_CODE CODE ,
	vcp.PROGRAM_ID
from
	V_PRISON_ACTIVITIES VCP
where
	VCP.AGY_LOC_ID = :agyLocId
	and ACTIVE_FLAG = 'Y'
order by
	DESCRIPTION
 	}

OIDPAATT_FIND_RGAGYLOC {
SELECT AL.DESCRIPTION , AL.AGY_LOC_ID CODE  FROM AGENCY_LOCATIONS AL  WHERE AL.AGENCY_LOCATION_TYPE NOT IN ('CRT' )  
  AND AL.AGY_LOC_ID NOT IN ('IN' , 'OUT' , 'TRN' )    AND (AL.ACTIVE_FLAG = 'Y' OR '' = 'ENTER-QUERY' )    AND EXISTS     
   (SELECT AGY_LOC_ID  FROM CASELOAD_AGENCY_LOCATIONS where CASELOAD_ID =:caseloadId and  AGY_LOC_ID=AL.AGY_LOC_ID )    AND EXISTS         (SELECT AGY_LOC_ID     
      FROM COURSE_ACTIVITIES CA     WHERE CA.COURSE_ACTIVITY_TYPE = 'IA'            AND CASELOAD_ID =:caseloadId and  AGY_LOC_ID=AL.AGY_LOC_ID )  ORDER BY AL.LIST_SEQ , AL.DESCRIPTION
 }

OIDPAATT_VACTATT_FIND_V_OFFENDER_COURSE_ATTENDANCES {
SELECT EVENT_ID, OFFENDER_BOOK_ID, EVENT_CLASS, EVENT_TYPE, EVENT_SUB_TYPE, OFF_PRGREF_ID, REFERENCE_ID, CRS_SCH_ID, CRS_APPT_ID, PROGRAM_ID, COURSE_CODE, DESCRIPTION, CRS_ACTY_ID, WEEKDAY, EVENT_DATE, START_TIME,START_TIME DB_START_TIME, END_TIME,END_TIME DB_END_TIME, IN_TIME, OUT_TIME, EVENT_STATUS,  AGY_LOC_ID, TO_INTERNAL_LOCATION_ID, COMMENT_TEXT, OUTCOME_REASON_CODE, PIECE_WORK, ENGAGEMENT_CODE, UNDERSTANDING_CODE, CREDITED_HOURS, AGREED_TRAVEL_HOUR, BEHAVIOUR_CODE, ACTION_CODE, SICK_NOTE_RECEIVED_DATE, SICK_NOTE_EXPIRY_DATE, PERFORMANCE_CODE, TO_AGY_LOC_ID, TO_ADDRESS_ID, PERFORMANCE_DESC, EVENT_OUTCOME_DESC, SUPERVISOR_STAFF_ID, UNEXCUSED_ABSENCE_FLAG, DIRECTION_CODE, EXT_MOVE_OUT_TIME, EXT_MOVE_IN_TIME, SCHEDULE_MOVEMENT_TIME, SESSION_NO, RECORD_SOURCE, CHECK_SUM 
,CASE WHEN (V.EVENT_OUTCOME IS  NULL AND v.EVENT_DATE <= current_timestamp) THEN (SELECT INST_ACT_DEFAULT_ATT_CODE FROM PROGRAMS_PAY_SETTINGS ) ELSE  V.EVENT_OUTCOME END EVENT_OUTCOME
,CASE WHEN V.EVENT_ID IS NOT NULL  THEN (SELECT PAY_FLAG FROM OFFENDER_COURSE_ATTENDANCES WHERE EVENT_ID = V.EVENT_ID) ELSE 'N' END PAY_FLAG
,CASE WHEN V.EVENT_ID IS NOT NULL  THEN (SELECT PAY_BATCH_ID FROM OFFENDER_COURSE_ATTENDANCES WHERE EVENT_ID = V.EVENT_ID) ELSE NULL END PAY_BATCH_ID
,(select offender_id_display from offenders where offender_id in(select offender_id from offender_bookings ob where offender_book_id = v.offender_book_id)) as offender_id_display
,(select  (last_name::text || ', '::text) || first_name::text from offenders where offender_id in(select offender_id from offender_bookings ob where offender_book_id = v.offender_book_id)) as OFFENDER_NAME
 	FROM  V_OFFENDER_COURSE_EVENTS V  WHERE
 	
}
OIDPAATT_VACTATT_UPDATE_V_OFFENDER_COURSE_ATTENDANCES {
	UPDATE offender_course_attendances SET performance_code = :performanceCode, event_outcome = :eventOutcome, start_time = :startTime, end_time = :endTime, in_time = :startTime, out_time = :endTime, piece_work = :pieceWork, comment_text = :commentText, modify_datetime= CURRENT_TIMESTAMP, modify_user_id=:modifyUserId WHERE event_id = :eventId
}


OIDPAATT_DEFAULT_AGENCY {
	SELECT COUNT (*) FROM AGENCY_LOCATIONS AL WHERE AL.AGENCY_LOCATION_TYPE NOT IN ('CRT') AND AL.AGY_LOC_ID NOT IN ('IN', 'OUT', 'TRN') AND (AL.ACTIVE_FLAG = 'Y' OR :MODE = 'ENTER-QUERY' ) AND EXISTS (SELECT CA.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CA WHERE CA.CASELOAD_ID = :CASELOADID AND CA.AGY_LOC_ID = AL.AGY_LOC_ID) AND EXISTS (SELECT AGY_LOC_ID FROM COURSE_ACTIVITIES CA WHERE CA.COURSE_ACTIVITY_TYPE = 'IA' AND CA.CASELOAD_ID = :CASELOADID AND CA.AGY_LOC_ID = AL.AGY_LOC_ID)
}

OIDPAATT_DEFAULT_AGENCY_ONE {
	select
	AL.AGY_LOC_ID,
	AL.DESCRIPTION DSP_DESCRIPTION2
from
	AGENCY_LOCATIONS AL
where
	AL.AGENCY_LOCATION_TYPE not in ('CRT')
	and AL.AGY_LOC_ID not in ('IN', 'OUT', 'TRN')
	and AL.ACTIVE_FLAG = 'Y'
	and exists (
	select
		CA.AGY_LOC_ID
	from
		CASELOAD_AGENCY_LOCATIONS CA
	where
		CA.CASELOAD_ID = :CASELOADID
		and CA.AGY_LOC_ID = AL.AGY_LOC_ID)
	and exists (
	select
		AGY_LOC_ID
	from
		COURSE_ACTIVITIES CA
	where
		CA.COURSE_ACTIVITY_TYPE = 'IA'
		and CA.CASELOAD_ID = :CASELOADID
		and CA.AGY_LOC_ID = AL.AGY_LOC_ID)
}

OIDPAATT_DEFAULT_ATTENDANCE_OUTCOME {
SELECT CODE
        FROM REFERENCE_CODES
       WHERE DOMAIN = 'PS_PA_OC'
         AND ACTIVE_FLAG = 'Y'
         AND LIST_SEQ = 1
        LIMIT 1
}

OIDPAATT_DEFAULT_ATTENDANCE_PERFORMANCE {
        SELECT CODE
        FROM REFERENCE_CODES
       WHERE DOMAIN = 'PERFORMANCE'
         AND ACTIVE_FLAG = 'Y'
         AND LIST_SEQ = 1
          LIMIT 1
}

OIDPAATT_INSERT_OFFENDER_COURSE_ATTENDANCES {
insert into OFFENDER_COURSE_ATTENDANCES(EVENT_ID, event_class, offender_book_id, event_date, off_prgref_id, event_status, Reference_id, crs_sch_id, crs_appt_id, crs_acty_id, event_sub_type, event_type, Start_Time, End_Time, in_time, out_time, event_outcome, agy_loc_ID, comment_text, piece_work, performance_code, outcome_reason_code,  to_internal_location_id, to_address_id, engagement_code, understanding_code, credited_hours, agreed_travel_hour, behaviour_code, supervisor_staff_id, create_datetime, modify_datetime, create_user_id, modify_user_id)
values (:eventId, :eventClass, :offenderBookId, :eventDate, :offPrgrefId, :eventStatus, :referenceId, :crsSchId, :crsApptId, :crsActyId, :eventSubType, :eventType, :startTime, :endTime, :inTime, :outTime, :eventOutcome, :agyLocId, :commentText, :pieceWork, :performanceCode, :outcomeReasonCode,  :toInternalLocationId, :toAddressId, :engagementCode, :understandingCode, :creditedHours, :agreedTravelHour, :behaviourCode, :supervisorStaffId, current_timestamp, null , :createUserId, null )
}
