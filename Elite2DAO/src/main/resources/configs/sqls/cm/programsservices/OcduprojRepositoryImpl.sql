
OCDUPROJ_FIND_RGATTENDANCE {
 	select code,description,cancel_flag as active_flag from (select
	list_seq,code,RC.description,cancel_flag 
from
	(
	select
	    RC.list_seq,
		RC.DESCRIPTION,
		RC.CODE
	from
		REFERENCE_CODES RC
	where
		RC.DOMAIN = 'OUTCOMES'
		and
  RC.CODE not in ('PENDING')
		and (RC.ACTIVE_FLAG = 'Y'
			or '' = 'ENTER-QUERY' ))RC
join (
	select
		EMO.OUTCOME_CODE,
		coalesce(EMO.cancel_flag, 'N') as cancel_flag
	from
		EVENT_MEASURE_OUTCOMES EMO,
		EVENT_MEASURES EM
	where
		EM.EVENT_TYPE = 'UW'
		and
       EMO.EVENT_MEASURE_ID = EM.EVENT_MEASURE_ID
		and
       EM.EVENT_SUB_TYPE = 'WS'
		and
       EM.ACTIVE_FLAG = 'Y'
		and (coalesce(null::text, '') = ''
			or EMO.OUTCOME_CODE in ('AACA' , 'AAME' , 'AAED' , 'AAFA' , 'AAOT' , 'AACU' , 'RES' , 'CANC' , 'AASD' ) ) )EMO
       on
	RC.CODE = EMO.OUTCOME_CODE)A order by  LIST_SEQ, DESCRIPTION, CODE
 	}

OCDUPROJ_FIND_RGSUPERVISOR {
select
	distinct sm.last_name || ', ' || sm.first_name description,
	tm.staff_id,
	sm.user_id code
from
	team_staff_members tm,
	staff_members sm,
	automation_teams at1 
where
	at1.team_id  = (select provider_party_id  from course_activities ca where crs_acty_id = :crsActyId ) and at1.team_id = tm.team_id and tm.staff_id = sm.staff_id
}

OCDUPROJ_FIND_RGBEHAVIOUR {
 	SELECT
  DESCRIPTION, CODE
FROM REFERENCE_CODES
WHERE
  DOMAIN = 'PS_BEHAVIOUR' AND
  ((ACTIVE_FLAG = 'Y' ))
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE
 	}

OCDUPROJ_FIND_RGBEHAVIOUR {
 	SELECT  DESCRIPTION            ,CODE FROM REFERENCE_CODES WHERE DOMAIN = 'PS_BEHAVIOUR' AND ((ACTIVE_FLAG = 'Y' ) OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDUPROJ_FIND_RGWORKQUALITY {
 	SELECT
  DESCRIPTION, CODE
FROM REFERENCE_CODES
WHERE
  DOMAIN = 'PS_UPW_QUAL' AND
  ((ACTIVE_FLAG = 'Y' ))
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE

}

OCDUPROJ_FIND_RGPROJECTCHECK {
 	SELECT
  CA.CODE CODE, CA.CRS_ACTY_ID CRS_ACTY_ID
FROM COURSE_ACTIVITIES CA
WHERE
  CA.COURSE_ACTIVITY_TYPE = 'WS' AND
  CA.COURSE_CLASS = 'COURSE' AND
  CA.CODE = :code AND (CA.CRS_ACTY_ID = :crsActyId OR coalesce(CRS_ACTY_ID::text, '') = '' ) AND (CA.ACTIVE_FLAG = 'Y' ) AND
  CA.PROVIDER_PARTY_CLASS = 'TEAM'
 	}

OCDUPROJ_FIND_RGPROJECT {
 	SELECT
  CA.DESCRIPTION DESCRIPTION, CA.CODE CODE
FROM
  COURSE_ACTIVITIES CA, OFFENDER_PROGRAM_PROFILES OPP
WHERE
  CA.COURSE_ACTIVITY_TYPE = 'WS' AND
  CA.COURSE_CLASS = 'COURSE' AND
  OPP.CRS_ACTY_ID = CA.CRS_ACTY_ID AND
  (CA.ACTIVE_FLAG = 'Y' ) AND
  CA.PROVIDER_PARTY_CLASS = 'TEAM' AND
  OPP.OFFENDER_BOOK_ID = :OFFENDERBOOKID
ORDER BY CA.CODE ASC

}

OCDUPROJ_FIND_RGSKILLS {
 	SELECT
  DESCRIPTION, CODE
FROM REFERENCE_CODES
WHERE
  DOMAIN = 'PS_UPW_SKILL' AND
  ((ACTIVE_FLAG = 'Y' ))
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE

}

OCDUPROJ_FIND_RGSTAFFCHECK {
 	SELECT DISTINCT
  SM.LAST_NAME||', '||SM.FIRST_NAME STAFF_DESC, SLR.ROLE STAFF_ROLE,
  SM.STAFF_ID STAFF_ID
FROM
  STAFF_MEMBERS SM, STAFF_LOCATION_ROLES SLR
WHERE
  SM.STATUS = 'ACTIVE' AND
  (UPPER(SM.LAST_NAME||', '||SM.FIRST_NAME ) = :NBTSTAFFDESC ) AND
  SLR.SAC_STAFF_ID = SM.STAFF_ID AND SLR.ROLE = 'UW' ORDER BY STAFF_DESC

}

OCDUPROJ_FIND_RGADJREASON {
		SELECT
	  DESCRIPTION, CODE, ACTIVE_FLAG
	FROM REFERENCE_CODES
	WHERE
	  DOMAIN = 'PS_ADJ_REA' AND
	  ACTIVE_FLAG = 'Y'
	ORDER BY
	  LIST_SEQ, DESCRIPTION, CODE

}

OCDUPROJ_UNPAIDWK_FIND_V_OFFENDER_SENT_COND_ACTS {
 	SELECT *
FROM V_OFFENDER_SENT_COND_ACTS
WHERE
  OFFENDER_BOOK_ID=:offenderBookId AND
  (SENTENCE_PROGRAM_METHOD = 'UW' OR COMM_PROGRAM_METHOD = 'UW' )

}
OCDUPROJ_PROJALLOC_FIND_OFFENDER_PROGRAM_PROFILES {
 SELECT * , (
	select
		count(*)
	from
		INTERNET_ADDRESSES
	where
		owner_id = (
		select
			ROOT_OFFENDER_ID
		from
			v_header_block
		where
			offender_book_id = :offenderBookId)) email_address_count,
	(
	select
		count(*)
	from
		PHONES
	where
		owner_id = (
		select
			ROOT_OFFENDER_ID
		from
			v_header_block
		where
			offender_book_id = :offenderBookId) and phone_type ='MOB' ) phone_number_count
FROM
    offender_program_profiles
WHERE
    coalesce(offender_prg_obligation_id::text, '') = ''
    AND offender_book_id = :offenderBookId
     AND sentence_seq = :unpaid_wk_sentence_seq
     AND OFFENDER_SENT_CONDITION_ID = :offenderSentConditionId;
}
OCDUPROJ_PROJALLOC_INSERT_OFFENDER_PROGRAM_PROFILES {
insert into OFFENDER_PROGRAM_PROFILES (AGREED_TRAVEL_FARE, AGREED_TRAVEL_HOUR, AGY_LOC_ID, OFF_PRGREF_ID, PROGRAM_ID, OFFENDER_START_DATE, OFFENDER_PROGRAM_STATUS, CRS_ACTY_ID, OFFENDER_END_DATE, SUSPENDED_FLAG, OFFENDER_SENT_CONDITION_ID, SENTENCE_SEQ, CREATE_USER_ID, CREATE_DATETIME, OFFENDER_BOOK_ID, modify_datetime, sms_schedule_hours_before, email_schedule_hours_before, email_flag, sms_flag) values( :agreedTravelFare1, :agreedTravelHour, :agyLocId, :offPrgrefId, :programId, :offenderStartDate, :offenderProgramStatus, :crsActyId, :offenderEndDate, :suspendedFlag, :offenderSentConditionId, :sentenceSeq, :createUserId, current_timestamp, :offenderBookId, null,:smsScheduleHoursBefore, :emailScheduleHoursBefore, :emailFlag, :smsFlag )}  

OCDUPROJ_PROJALLOC_UPDATE_OFFENDER_PROGRAM_PROFILES {
update OFFENDER_PROGRAM_PROFILES set AGREED_TRAVEL_FARE =:agreedTravelFare1, AGREED_TRAVEL_HOUR =:agreedTravelHour, AGY_LOC_ID =:agyLocId, OFFENDER_START_DATE =:offenderStartDate, OFFENDER_PROGRAM_STATUS =:offenderProgramStatus, CRS_ACTY_ID =:crsActyId, OFFENDER_END_DATE =:offenderEndDate, SUSPENDED_FLAG =:suspendedFlag , OFFENDER_SENT_CONDITION_ID =:offenderSentConditionId, SENTENCE_SEQ =:sentenceSeq, HOLIDAY_FLAG = :holidayFlag, PROFILE_CLASS =:profileClass, NEEDED_FLAG = :neededFlag, MODIFY_USER_ID = :modifyUserId , PROGRAM_ID =:programId, MODIFY_DATETIME = current_timestamp,sms_schedule_hours_before=:smsScheduleHoursBefore, email_schedule_hours_before=:emailScheduleHoursBefore, email_flag=:emailFlag, sms_flag=:smsFlag
 where OFFENDER_BOOK_ID = :offenderBookId and OFF_PRGREF_ID =:offPrgrefId
} 

OCDUPROJ_PROJALLOC_DELETE_OFFENDER_PROGRAM_PROFILES { 
	DELETE FROM OFFENDER_PROGRAM_PROFILES WHERE OFFENDER_BOOK_ID= :offenderBookId  AND  OFF_PRGREF_ID=:offPrgrefId  
}

OCDUPROJ_ATTENDANCE_FIND_V_OFFENDER_COURSE_EVENTS {
 	select 
	*
from
	V_OFFENDER_COURSE_EVENTS
where
	OFFENDER_BOOK_ID =:offenderBookId
	and OFF_PRGREF_ID =:offPrgrefId
}
OCDUPROJ_ATTENDANCE_UPDATE_V_OFFENDER_COURSE_EVENTS {
	UPDATE V_OFFENDER_COURSE_EVENTS set EVENT_ID=:eventId,EVENT_CLASS=:eventClass, EVENT_TYPE=:eventType, EVENT_SUB_TYPE=:eventSubType, REFERENCE_ID=:referenceId, CRS_SCH_ID=:crsSchId,DESCRIPTION=:description, CRS_APPT_ID=:crsApptId, PROGRAM_ID=:programId, COURSE_CODE=:courseCode, CRS_ACTY_ID=:crsActyId, WEEKDAY=:weekday, EVENT_DATE=:eventDate, START_TIME=:startTime, END_TIME=:endTime, IN_TIME=:inTime, OUT_TIME=:outTime, EVENT_STATUS=:eventStatus, EVENT_OUTCOME=:eventOutcome, AGY_LOC_ID=:agyLocId, TO_INTERNAL_LOCATION_ID=:toInternalLocationId, COMMENT_TEXT=:commentText, OUTCOME_REASON_CODE=:outcomeReasonCode, PIECE_WORK=:pieceWork, ENGAGEMENT_CODE=:engagementCode, UNDERSTANDING_CODE=:understandingCode, CREDITED_HOURS=:creditedHours, AGREED_TRAVEL_HOUR=:agreedTravelHour, BEHAVIOUR_CODE=:behaviourCode, ACTION_CODE=:actionCode, SICK_NOTE_RECEIVED_DATE=:sickNoteReceivedDate, SICK_NOTE_EXPIRY_DATE=:sickNoteExpiryDate, PERFORMANCE_CODE=:performanceCode, TO_AGY_LOC_ID=:toAgyLocId, TO_ADDRESS_ID=:toAddressId, PERFORMANCE_DESC=:performanceDesc, EVENT_OUTCOME_DESC=:eventOutcomeDesc, SUPERVISOR_STAFF_ID=:supervisorStaffId, UNEXCUSED_ABSENCE_FLAG=:unexcusedAbsenceFlag, DIRECTION_CODE=:directionCode, 
	EXT_MOVE_OUT_TIME=:extMoveOutTime, EXT_MOVE_IN_TIME=:extMoveInTime, SCHEDULE_MOVEMENT_TIME=:scheduleMovementTime, SESSION_NO=:sessionNo, RECORD_SOURCE=:recordSource, CHECK_SUM =:checkSum WHERE OFFENDER_BOOK_ID =:offenderBookId AND OFF_PRGREF_ID=:offPrgrefId AND EVENT_ID=:eventId
}

OCDUPROJ_SKILLS_FIND_OFFENDER_COURSE_SKILLS {
select
	ROW_ID,
	EVENT_ID,
	SKILL_CODE,
	NO_OF_HOURS,
	STAFF_ID,
	COMMENT_TEXT,
	STAFF_ROLE,
	CREATE_DATETIME,
	CREATE_USER_ID,
	MODIFY_DATETIME,
	MODIFY_USER_ID,
	SEAL_FLAG
from
	OFFENDER_COURSE_SKILLS
where
	STAFF_ROLE = 'TUTOR'
	and EVENT_ID =:eventId order by row_id
}
OCDUPROJ_SKILLS_INSERT_OFFENDER_COURSE_SKILLS {
insert into OFFENDER_COURSE_SKILLS (EVENT_ID, SKILL_CODE, NO_OF_HOURS, STAFF_ID, COMMENT_TEXT, STAFF_ROLE, CREATE_DATETIME, CREATE_USER_ID, SEAL_FLAG, modify_datetime) values(:eventId, :skillCode, :noOfHours, :staffId, :commentText, :staffRole, current_timestamp, :createUserId, :sealFlag, null )
}   

OCDUPROJ_SKILLS_UPDATE_OFFENDER_COURSE_SKILLS {
update OFFENDER_COURSE_SKILLS set SKILL_CODE =:skillCode, NO_OF_HOURS =:noOfHours, STAFF_ID =:staffId, COMMENT_TEXT =:commentText, STAFF_ROLE =:staffRole, MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId, SEAL_FLAG =:sealFlag where EVENT_ID =:eventId and ROW_ID =:rowIdOne
} 

OCDUPROJ_SKILLS_DELETE_OFFENDER_COURSE_SKILLS { 
	DELETE FROM OFFENDER_COURSE_SKILLS  where EVENT_ID=:eventId and STAFF_ID=:staffId and SKILL_CODE=:skillCode
}

OCDUPROJ_CREDITADJ_FIND_OFFENDER_UNPAID_WORK_ADJ {
 SELECT *
FROM OFFENDER_UNPAID_WORK_ADJ
where
  OFFENDER_BOOK_ID=:offenderBookId and
  SENTENCE_SEQ=:sentenceSeq and 
  offender_sent_condition_id =:offenderSentConditionId
order by ADJUSTMENT_DATE desc

}
OCDUPROJ_CREDITADJ_INSERT_OFFENDER_UNPAID_WORK_ADJ {
insert into OFFENDER_UNPAID_WORK_ADJ (OFFENDER_UNPAID_WORK_ADJ_ID, OFFENDER_BOOK_ID, SENTENCE_SEQ, ADJUSTMENT_MINUTES, ADJUSTMENT_DATE, ADJUSTMENT_TYPE, REASON_CODE, COMMENT_TEXT, ORDER_TYPE, OFFENDER_SENT_CONDITION_ID, create_datetime, modify_datetime, create_user_id) values(:offenderUnpaidWorkAdjId, :offenderBookId, :sentenceSeq, :adjustmentMinutes, :adjustmentDate, :adjustmentType, :reasonCode, :commentText, :orderType, :offenderSentConditionId, current_timestamp, null , :createUserId)
}

OCDUPROJ_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM V_OFFENDER_SENT_COND_ACTS V WHERE V.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDUPROJ_UNPAID_WK_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_PROGRAM_PROFILES O WHERE O.SENTENCE_SEQ = :SENTENCESEQ AND O.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDUPROJ_UNPAID_WK_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_UNPAID_WORK_ADJ O WHERE O.SENTENCE_SEQ = :SENTENCESEQ AND O.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OCDUPROJ_PROJ_ALLOC_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM V_OFFENDER_COURSE_EVENTS V WHERE V.OFF_PRGREF_ID = :OFFPRGREFID AND V.CRS_ACTY_ID = :CRSACTYID
}

OCDUPROJ_ATTENDANCE_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_COURSE_SKILLS O WHERE O.EVENT_ID = :EVENTID
}

OCDUPROJ_CHECK_ASSIGN_CONFLICT_ {
	SELECT MIN(OFFENDER_START_DATE)
FROM OFFENDER_PROGRAM_PROFILES
WHERE
  OFFENDER_BOOK_ID IN (SELECT DISTINCT NS_OFFENDER_BOOK_ID
     FROM OFFENDER_NA_DETAILS ONA
     WHERE
       OFFENDER_BOOK_ID=:OFFENDERBOOKID AND
       date_trunc('day', LOCALTIMESTAMP) BETWEEN ONA.NS_EFFECTIVE_DATE AND coalesce(ONA.NS_EXPIRY_DATE, TO_DATE('01/01/3000', 'DD/MM/YYYY'))) AND
  coalesce(OFFENDER_END_DATE::text, '') = '' AND
  CRS_ACTY_ID= :CRSACTYID
}

OCDUPROJ_CHECK_ASSIGN_CONFLICT_ {
	SELECT MIN(OFFENDER_START_DATE)
FROM OFFENDER_PROGRAM_PROFILES
WHERE
  OFFENDER_BOOK_ID IN (SELECT DISTINCT NS_OFFENDER_BOOK_ID
     FROM OFFENDER_NA_DETAILS ONA
     WHERE
       OFFENDER_BOOK_ID=:OFFENDERBOOKID AND
       date_trunc('day', LOCALTIMESTAMP) BETWEEN ONA.NS_EFFECTIVE_DATE AND coalesce(ONA.NS_EXPIRY_DATE, TO_DATE('01/01/3000', 'DD/MM/YYYY'))) AND
  CRS_ACTY_ID=:CRSACTYID AND
  OFFENDER_START_DATE<=:OFFENDERENDDATE
}

OCDUPROJOFFENDER_UNPAID_WORK_ADJ_USER_NAME{
SELECT  Last_Name||', '||First_Name
        FROM  STAFF_MEMBERS
        WHERE User_ID  = :P_USER_ID
 }
 
 OCDUPROJ_OFFENDER_UNPAID_WORK_ADJ_ID{
  SELECT nextval('offender_unpaid_work_adj_id') FROM DUAL
  }
  
  OCDUPROJ_GET_OFF_PRGREF_ID_CUR{
       SELECT NEXTVAL('off_prgref_id') FROM DUAL
  }
  
  OCDUPROG_FIRST_NAME_LASTNAME{
  select LAST_NAME,FIRST_NAME from staff_members where staff_id=:staffId
  
  }
  
  OCDUPROJ_PROJ_ALLOCONDELETE_QUERY{
      SELECT count(*) PROGRAM_ID FROM v_offender_course_events v WHERE v.off_prgref_id =:OFFPRGREFID AND v.crs_acty_id =:CRSACTYID
  }
  
  
  OCMSUWPJ_CONSTRAINT_VALIDATIONS {
SELECT 
  DISTINCT C.TABLE_NAME CHILD_TABLE
FROM 
  USER_CONSTRAINTS C, USER_CONSTRAINTS R, USER_CONS_COLUMNS CC, 
  USER_CONS_COLUMNS RC
WHERE
R.TABLE_NAME = 'OFFENDER_COURSE_SKILLS' AND
 C.CONSTRAINT_NAME = :CONSTRAINTNAME AND
  C.CONSTRAINT_TYPE = 'R' AND
  C.R_OWNER = R.OWNER AND
  C.R_CONSTRAINT_NAME = R.CONSTRAINT_NAME AND
  C.CONSTRAINT_NAME = CC.CONSTRAINT_NAME AND
  C.OWNER = CC.OWNER AND
  R.CONSTRAINT_NAME = RC.CONSTRAINT_NAME AND
  R.OWNER = RC.OWNER AND
  CC.POSITION = RC.POSITION AND
  C.OWNER='OMS_OWNER' AND
  R.OWNER='OMS_OWNER' 
}
GET_OLD_REC_V_OFFENDER_COURSE_EVENTS{
select event_id, offender_book_id, event_class, event_type, event_sub_type, off_prgref_id, reference_id, crs_sch_id, crs_appt_id , program_id , course_code, description, crs_acty_id, weekday , event_date , start_time, end_time, in_time, out_time, event_status, event_outcome , agy_loc_id , to_internal_location_id, comment_text, outcome_reason_code, piece_work, engagement_code , understanding_code, credited_hours, agreed_travel_hour, behaviour_code, action_code , sick_note_received_date, sick_note_expiry_date, performance_code, to_agy_loc_id, to_address_id, performance_desc, event_outcome_desc, supervisor_staff_id , unexcused_absence_flag, direction_code, ext_move_out_time, ext_move_in_time, schedule_movement_time, session_no, record_source, check_sum from V_OFFENDER_COURSE_EVENTS where event_id = :event_id
}


GET_PROJECT_ALLOC_DETAIL { 
SELECT code  , description,crs_acty_id,provider_party_id, (select tm.description from automation_teams tm where tm.team_id=ca.provider_party_id) team_description,
                capacity , program_id,
                schedule_start_date, schedule_end_date
           FROM COURSE_ACTIVITIES ca
          WHERE 
         
           crs_acty_id = :crsActyId
            AND active_flag = 'Y'
}

INSERT_COURSE_ATTENDENCE{ 
insert into OFFENDER_COURSE_ATTENDANCES (EVENT_ID, OFFENDER_BOOK_ID, EVENT_DATE, EVENT_OUTCOME, IN_TIME, OUT_TIME, AGREED_TRAVEL_HOUR, CREDITED_HOURS, SUPERVISOR_STAFF_ID, BEHAVIOUR_CODE, PERFORMANCE_CODE, OFF_PRGREF_ID, CRS_ACTY_ID, EVENT_STATUS, EVENT_CLASS, EVENT_TYPE, EVENT_SUB_TYPE, COMMENT_TEXT, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME, MODIFY_USER_ID ) values (:eventId, :offenderBookId, :eventDate, :eventOutcome, :inTime, :outTime, :agreedTravelHour, :creditedHours, :supervisorStaffId, :behaviourCode, :performanceCode, :offPrgrefId, :crsActyId, :eventStatus, :eventClass, :eventType, :eventSubType, :commentText, :createUserId, current_timestamp, null,null )
}
UPDATE_COURSE_ATTENDENCE{ 
UPDATE OFFENDER_COURSE_ATTENDANCES set CREDITED_HOURS=:creditedHours ,SUPERVISOR_STAFF_ID=:supervisorStaffId,EVENT_CLASS=:eventClass,EVENT_STATUS=:eventStatus where OFFENDER_BOOK_ID=:offenderBookId and EVENT_ID=:eventId

}

GET_COURSE_ATTENDENCE_ID {
 SELECT NEXTVAL('EVENT_ID') FROM DUAL
}

OCDUPROJ_GET_ACTIVE_FLAGS{
select COUNT(*) NUMBER_ACTIVE from OFFENDER_NA_DETAILS where OFFENDER_ID =:offenderId and (NS_EXPIRY_DATE>CURRENT_TIMESTAMP or NS_EXPIRY_DATE is null) 
}

OCDUPROJ_GET_LASTNAME_AND_FIRSTNAME{
select V_OFF_BKG.* from V_HEADER_BLOCK V_OFF_BKG where V_OFF_BKG.ROOT_OFFENDER_ID in( select ns_offender_id from OFFENDER_NA_DETAILS where OFFENDER_ID =:offenderid and (NS_EXPIRY_DATE>CURRENT_TIMESTAMP or NS_EXPIRY_DATE is null)) and (V_OFF_BKG.BOOKING_BEGIN_DATE = ( select MAX(VHB2.BOOKING_BEGIN_DATE) from V_HEADER_BLOCK VHB2 where VHB2.ROOT_OFFENDER_ID = V_OFF_BKG.ROOT_OFFENDER_ID and not exists ( select null from V_HEADER_BLOCK VHB3 where VHB3.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID and VHB3.ACTIVE_FLAG = 'Y')) or V_OFF_BKG.ACTIVE_FLAG = 'Y') and V_OFF_BKG.ROOT_OFFENDER_ID != :offenderid
}

OCDUPROJ_CHK_NONASSOCIATIONS {
select
	*
from
	OFFENDER_NON_ASSOCIATIONS ona
where
	ona.offender_book_id =:offenderBookId
	and ona.NS_OFFENDER_ID in (
	select
		nad.NS_OFFENDER_ID
	from
		OFFENDER_NA_DETAILS nad
	where
		nad.offender_book_id =:offenderBookId
		and
		current_date < coalesce(nad.ns_expiry_date, current_date +1)  and current_date >= nad.ns_effective_date)
}

OCDUPROJ_GET_DAILY_SCHEDULES{
	select * from v_offender_course_events 
	where offender_book_id = :offenderBookId 
	and crs_acty_id = :crsActyId 
	and record_source = 'CRS_ATT'
	and event_status = 'SCH'
}

OCDUPROJ_CHK_NONASSOCIATION_PROGRAM_ASSIGNMENT {
 	SELECT * FROM OFFENDER_PROGRAM_PROFILES 
	where offender_book_id = :offenderBookId
	and crs_acty_id = :crsActyId and offender_program_status not in ('END','COMP','CANC','REM')
}

OCDUPROJ_GET_OFFENDER_NAMES {
select * from v_header_block  where offender_book_id=:nsOffenderBookId
}



OCDUPROJ_GET_VOFFENDER_COURSE_EVENTS_DATA{
  SELECT * FROM v_offender_course_events a WHERE off_prgref_id = ( SELECT off_prgref_id FROM offender_program_profiles
  b WHERE a.event_date > :offender_end_date AND b.off_prgref_id = :OFF_PRG_REF_ID )
}

OCDUPROJ_UPDATE_STATUS_VOFFENDER_COURSE_EVENTS{
UPDATE V_OFFENDER_COURSE_EVENTS set CRS_ACTY_ID = :crsActyId  where OFFENDER_BOOK_ID = :offenderBookId and EVENT_ID = :eventId
}

OCDUPROJ_GET_VOFFENDER_COURSE_EVENTS_DATA_ONE{
SELECT * FROM OFFENDER_COURSE_ATTENDANCES  WHERE OFF_PRGREF_ID = :offPrgrefId and event_date<= :offender_EndDate  and event_status= 'CANC'
}

GET_VOFFENDER_COURSE_EVENTS_DATA_WITHOUT_DATE{
 SELECT * FROM OFFENDER_COURSE_ATTENDANCES  WHERE OFF_PRGREF_ID = :offPrgrefId and event_status= 'CANC' and event_date >= current_timestamp
}

GET_SENTENCE_DATA_OCDLEGLO {
select
		FORM_INFO_JSON as FORM_INFO_JSON_BLOB,
		id,
		FORM_IDENTIFIER 
	from
		OCDLEGLO_DATA where SUBSTR(FORM_IDENTIFIER,
		20,
		(INSTR(FORM_IDENTIFIER,
		'"',
		1,
		4)) -(INSTR(FORM_IDENTIFIER,
		'"',
		1,
		3) + 1)) = :OFFENDER_BOOK_ID
}

OCDUPROJ_GET_OLD_RECORD{
SELECT * FROM  offender_program_profiles WHERE  OFF_PRGREF_ID = :OFF_PRGREF_ID 
}
OCDUPROJ_SENTENCE_CATEGORY_TYPE {
select sc.sentence_calc_type as code , (select description from reference_codes rc where domain =  'CATEGORY' and code = sc.sentence_category) as sentence_category,sc.description description from SENTENCE_CALC_TYPES sc where sentence_category = :sentenceCategory
}
OCDUPROJ_COURT_RECORD_EXECUTEQUERY {
select
	al.description
from
	agency_locations al
where al.agy_loc_id = :agyLocId
}

OCDUPROJ_GET_EVENT_ID {
SELECT nextval('Event_ID') FROM DUAL;
}

OCDUPROJ_GET_REFERENCE_CODE_PARENT_VALUES {
select * from reference_codes rc where "domain"=:domainName
}