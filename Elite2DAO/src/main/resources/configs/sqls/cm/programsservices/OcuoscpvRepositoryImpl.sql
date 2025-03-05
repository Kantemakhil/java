
OCUOSCPV_CRSACT_FIND_COURSE_ACTIVITIES {
 SELECT
    
        crs_acty_id,
        caseload_id,
        agy_loc_id,
        description,
        capacity,
        active_flag,
        expiry_date,
        schedule_start_date,
        schedule_end_date,
        caseload_type,
        services_address_id,
        program_id,
        parent_crs_acty_id,
        internal_location_id,
        provider_party_class,
        provider_party_id,
        provider_party_code,
        beneficiary_name,
        beneficiary_contact,
        list_seq,
        placement_corporate_id,
        comment_text,
        agency_location_type,
        provider_type,
        beneficiary_type,
        placement_text,
        code,
        holiday_flag,
        course_class,
        course_activity_type,
        create_datetime,
        create_user_id,
        modify_datetime,
        modify_user_id,
        iep_level,
        no_of_sessions,
        session_length,
        multi_phase_scheduling_flag,
        schedule_notes,
        seal_flag,
        allow_double_book_flag
        from
        
course_activities
WHERE
    crs_acty_id = :pcrsactyid
}
OCUOSCPV_CRSACT_POST_QUERRY{
 SELECT *
           FROM PROGRAM_SERVICES
          WHERE program_id = :programid
}
OCUOSCPV_CRSSCHEDULERUL_FIND_COURSE_SCHEDULE_RULES {
 SELECT
COURSE_SCHEDULE_RULE_ID,
CRS_ACTY_ID,
WEEK_NO,
MONDAY_FLAG,
TUESDAY_FLAG,
WEDNESDAY_FLAG,
THURSDAY_FLAG,
FRIDAY_FLAG,
SATURDAY_FLAG,
SUNDAY_FLAG,
START_TIME,
END_TIME,
CREATE_DATETIME,
CREATE_USER_ID,
MODIFY_DATETIME,
MODIFY_USER_ID,
SEAL_FLAG,
CAPACITY
FROM
    course_schedule_rules
WHERE
    crs_acty_id = :pcrsactyid
ORDER BY
    tag_service_sort_earliest_weekday(monday_flag, tuesday_flag, wednesday_flag, thursday_flag, friday_flag,
                                      saturday_flag, sunday_flag) ASC,
    to_char(start_time, 'HH24:MI') ASC
}
OCUOSCPV_OFFSCH_FIND_V_OFFENDER_COURSE_EVENTS {
-- 	select event_date ,to_char(event_date , 'DY'::text) AS weekday,start_time ,end_time from OFFENDER_COURSE_ATTENDANCES 
SELECT
    CHECK_SUM,
RECORD_SOURCE,
SESSION_NO,
CRS_ACTY_ID,
WEEKDAY,
EVENT_DATE,
START_TIME,
END_TIME,
IN_TIME,
OUT_TIME,
EVENT_STATUS,
EVENT_OUTCOME,
AGY_LOC_ID,
TO_INTERNAL_LOCATION_ID,
COMMENT_TEXT,
OUTCOME_REASON_CODE,
PIECE_WORK,
ENGAGEMENT_CODE,
UNDERSTANDING_CODE,
CREDITED_HOURS,
AGREED_TRAVEL_HOUR,
BEHAVIOUR_CODE,
ACTION_CODE,
SICK_NOTE_RECEIVED_DATE,
SICK_NOTE_EXPIRY_DATE,
PERFORMANCE_CODE,
TO_AGY_LOC_ID,
TO_ADDRESS_ID,
PERFORMANCE_DESC,
EVENT_OUTCOME_DESC,
SUPERVISOR_STAFF_ID,
UNEXCUSED_ABSENCE_FLAG,
DIRECTION_CODE,
EXT_MOVE_OUT_TIME,
EXT_MOVE_IN_TIME,
SCHEDULE_MOVEMENT_TIME,
EVENT_ID,
OFFENDER_BOOK_ID,
EVENT_CLASS,
EVENT_TYPE,
EVENT_SUB_TYPE,
OFF_PRGREF_ID,
REFERENCE_ID,
CRS_SCH_ID,
CRS_APPT_ID,
PROGRAM_ID,
COURSE_CODE,
DESCRIPTION
FROM
    v_offender_course_events  
}
OCUOSCPV_OFFSCH_INSERT_V_OFFENDER_COURSE_EVENTS {
	INSERT INTO V_OFFENDER_COURSE_EVENTS
	(EVENT_DATE,
	START_TIME,
	END_TIME,
	OFFENDER_BOOK_ID,
	OFF_PRGREF_ID,
	EVENT_ID,
	REFERENCE_ID,
	CRS_APPT_ID
	) VALUES(:eventDate,:startTime,:endTime,:offenderBookId,:offPrgrefId,:eventId,:referenceId,:crsApptId)
}

OCUOSCPV_OFFSCH_DELETE_V_OFFENDER_COURSE_EVENTS { 
	DELETE FROM V_OFFENDER_COURSE_EVENTS WHERE off_prgref_id = :offPrgrefId and 
offender_book_id = :offenderBookId and 
crs_acty_id = :p_crs_acty_id and
crs_sch_id = :p_crs_sch_id 
}

OCUOSCPV_WEEKLYDEF_FIND_OFFENDER_COURSE_APPT_GRPS {
 	select 
OFFENDER_COURSE_APPT_GRP_ID,
START_DATE,
NO_OF_WEEK,
END_DATE,
OFF_PRGREF_ID,
HOLIDAY_FLAG,
CREATE_DATETIME,
CREATE_USER_ID,
MODIFY_DATETIME,
MODIFY_USER_ID,
SEAL_FLAG
from offender_course_appt_grps
where off_prgref_id = :p_off_prgref_id
order by start_date desc

} 
OCUOSCPV_WEEKLYDEF_INSERT_OFFENDER_COURSE_APPT_GRPS {
insert into OFFENDER_COURSE_APPT_GRPS(START_DATE, NO_OF_WEEK, END_DATE, OFFENDER_COURSE_APPT_GRP_ID, OFF_PRGREF_ID, create_user_id, create_datetime, modify_datetime) values(:startDate::date, :noOfWeek, :endDate, :offenderCourseApptGrpId, :offPrgrefId, :createUserId, current_timestamp, null )
}

  OCUOSCPV_WEEKLYDEF_UPDATE_OFFENDER_COURSE_APPT_GRPS {
	UPDATE OFFENDER_COURSE_APPT_GRPS set START_DATE=:startDate,NO_OF_WEEK=:noOfWeek,END_DATE=:endDate, MODIFY_USER_ID = :modifyUserId ,MODIFY_DATETIME=current_timestamp
	 where OFFENDER_COURSE_APPT_GRP_ID=:offenderCourseApptGrpId
}

OCUOSCPV_WEEKLYDEF_DELETE_OFFENDER_COURSE_APPT_GRPS { 
	DELETE FROM OFFENDER_COURSE_APPT_GRPS where OFFENDER_COURSE_APPT_GRP_ID=:offenderCourseApptGrpId
}
OCUOSCPV_WEEKLYDEF_PREDELETE_OFFENDER_COURSE_APPT_GRPS{
 DELETE FROM offender_course_appt_rules o
   WHERE o.OFFENDER_COURSE_APPT_GRP_ID = :offenderCourseApptGrpId
}

OCUOSCPV_OFFSCHDEF_FIND_OFFENDER_COURSE_APPT_RULES {
 SELECT
OFFENDER_COURSE_APPT_RULE_ID,
MONDAY_FLAG,
TUESDAY_FLAG,
WEDNESDAY_FLAG,
THURSDAY_FLAG,
FRIDAY_FLAG,
SATURDAY_FLAG,
SUNDAY_FLAG,
START_TIME,
END_TIME,
OFFENDER_COURSE_APPT_GRP_ID,
CREATE_DATETIME,
CREATE_USER_ID,
MODIFY_DATETIME,
MODIFY_USER_ID,
SEAL_FLAG
FROM
    offender_course_appt_rules
    where offender_course_appt_grp_id = :offender_course_appt_grp_id
ORDER BY
    tag_service_sort_earliest_weekday(monday_flag, tuesday_flag, wednesday_flag, thursday_flag, friday_flag,
                                      saturday_flag, sunday_flag) ASC,
    to_char(start_time, 'HH24:MI') asc

}
OCUOSCPV_OFFSCHDEF_INSERT_OFFENDER_COURSE_APPT_RULES {
insert into OFFENDER_COURSE_APPT_RULES(MONDAY_FLAG, TUESDAY_FLAG, WEDNESDAY_FLAG, THURSDAY_FLAG, FRIDAY_FLAG, SATURDAY_FLAG, SUNDAY_FLAG, START_TIME, END_TIME, OFFENDER_COURSE_APPT_RULE_ID, OFFENDER_COURSE_APPT_GRP_ID, create_user_id, create_datetime, modify_datetime) values(:mondayFlag, :tuesdayFlag, :wednesdayFlag, :thursdayFlag, :fridayFlag, :saturdayFlag, :sundayFlag, :startTime, :endTime, NEXTVAL('offender_course_appt_rule_id'), :offenderCourseApptGrpId, :createUserId, current_timestamp, null )
}
OCUOSCPV_OFFSCHDEF_DELETE_OFFENDER_COURSE_APPT_RULES{
DELETE FROM offender_course_appt_rules WHERE OFFENDER_COURSE_APPT_RULE_ID=:offenderCourseApptRuleId
}
OCUOSCPV_OFFSCHDEF_UPDATE_OFFENDER_COURSE_APPT_RULES{
UPDATE offender_course_appt_rules set  MONDAY_FLAG=:mondayFlag,TUESDAY_FLAG=:tuesdayFlag,WEDNESDAY_FLAG=:wednesdayFlag,
THURSDAY_FLAG=:thursdayFlag,FRIDAY_FLAG=:fridayFlag,SATURDAY_FLAG=:saturdayFlag,SUNDAY_FLAG=:sundayFlag,
START_TIME=:startTime,END_TIME=:endTime,modify_user_id=:modifyUserId,modify_datetime=current_timestamp   where OFFENDER_COURSE_APPT_RULE_ID=:offenderCourseApptRuleId

}

OCUOSCPV_OFFPRGPROFILES_FIND_OFFENDER_PROGRAM_PROFILES {
 select 
OFF_PRGREF_ID,
OFFENDER_BOOK_ID,
PROGRAM_ID,
OFFENDER_START_DATE,
OFFENDER_PROGRAM_STATUS,
CRS_ACTY_ID,
REFERRAL_PRIORITY,
REFERRAL_DATE,
REFERRAL_COMMENT_TEXT,
OFFENDER_END_REASON,
AGREED_TRAVEL_FARE,
AGREED_TRAVEL_HOUR,
OFFENDER_END_COMMENT_TEXT,
REJECT_DATE,
WAITLIST_DECISION_CODE,
REFERRAL_STAFF_ID,
OFFENDER_END_DATE,
CREDIT_WORK_HOURS,
CREDIT_OTHER_HOURS,
SUSPENDED_FLAG,
REJECT_REASON_CODE,
AGY_LOC_ID,
CREATE_DATETIME,
CREATE_USER_ID,
MODIFY_DATETIME,
MODIFY_USER_ID,
REVIEWED_BY,
OFFENDER_SENT_CONDITION_ID,
SENTENCE_SEQ,
HOLIDAY_FLAG,
START_SESSION_NO,
PARENT_OFF_PRGREF_ID,
OFFENDER_PRG_OBLIGATION_ID,
PROGRAM_OFF_PRGREF_ID,
PROFILE_CLASS,
COMPLETION_DATE,
NEEDED_FLAG,
COMMENT_TEXT,
EARLY_END_REASON,
OFFENDER_ID,
MEDICAL_RECORD_SEQ,
PARAMETER_1,
SEAL_FLAG
from offender_program_profiles
where off_prgref_id = :p_off_prgref_id and offender_book_id = :offender_book_id
and crs_acty_id = :crs_acty_id 
and program_id = :program_id
}
OCUOSCPV_OFFPRGPROFILES_INSERT_OFFENDER_PROGRAM_PROFILES {
	INSERT INTO OFFENDER_PROGRAM_PROFILES() VALUES(:)
}

OCUOSCPV_OFFPRGPROFILES_UPDATE_OFFENDER_PROGRAM_PROFILES {
	UPDATE OFFENDER_PROGRAM_PROFILES set /* where */
}


OCUOSCPV_CRS_ACT_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_PROGRAM_PROFILES O WHERE O.CRS_ACTY_ID = :CRSACTYID AND O.PROGRAM_ID = :PROGRAMID
}

OCUOSCPV_CRS_ACT_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM COURSE_SCHEDULE_RULES C WHERE C.CRS_ACTY_ID = :CRSACTYID
}

OCUOSCPV_WEEKLY_DEF_PREDELETE {
	DELETE FROM OFFENDER_COURSE_APPT_RULES O WHERE O.OFFENDER_COURSE_APPT_GRP_ID = :OFFENDERCOURSEAPPTGRPID
}

OCUOSCPV_GET_CRS_DETAILS_ {
	SELECT CORPORATE_NAME FROM   CORPORATES WHERE  CORPORATE_ID = ( SELECT PROVIDER_PARTY_ID FROM   COURSE_ACTIVITIES WHERE  CRS_ACTY_ID = :PCRSACTYID)
}
OCUOSCPV_GET_WEEKDAY {
select
	TO_CHAR (:event_date::timestamp ,
	'DY')
	from dual
}

OCUOSCPV_GETTING_CRSAPPTID{
   select
	:OFFENDER_COURSE_APPT_RULE_ID * 1000000000 +
                       to_char(:start_date::timestamp, 'YYYYMMDD')::int
from
	dual
}

OCUOSCPV_GETTING_REFFERENCE_ID{
        select
	:OFFENDER_COURSE_APPT_RULE_ID * 
	 +
                       to_char(:start_date::timestamp, 'YYYYMMDD')::int
from
	dual
}
OCUOSCPV_GETTING_VRETURN_NUMBER_FORCONFLICT{
select
	count(*)
from
	V_OFFENDER_ALL_SCHEDULES_2
where
	OFFENDER_BOOK_ID = :p_offender_book_id
	and EVENT_STATUS = 'SCH'
	and :p_event_date::timestamp >= EVENT_DATE 
	and :p_event_date::timestamp <= COALESCE(RETURN_DATE, EVENT_DATE)
}

OCUOSCPV_GETTING_MONDAY_LVDAY{
SELECT
    monday_flag,
    to_char(start_time, 'HH24:MI'),
    to_char(end_time, 'HH24:MI')
FROM
    course_schedule_rules
WHERE
    crs_acty_id = :crs_acty_id
    AND course_schedule_rule_id = :course_schedule_rule_id
}
OCUOSCPV_GETTING_TUESDAY_LVDAY{
SELECT
    tuesday_flag,
    to_char(start_time, 'HH24:MI'),
    to_char(end_time, 'HH24:MI')
FROM
    course_schedule_rules
WHERE
    crs_acty_id = :crs_acty_id
    AND course_schedule_rule_id = :course_schedule_rule_id
}
OCUOSCPV_GETTING_WEDNESDAY_LVDAY{
    
  SELECT
    wednesday_flag,
    to_char(start_time, 'HH24:MI'),
    to_char(end_time, 'HH24:MI')
FROM
    course_schedule_rules
WHERE
    crs_acty_id = :crs_acty_id
    AND course_schedule_rule_id = :course_schedule_rule_id
}
OCUOSCPV_GETTING_THURSDAY_LVDAY{
SELECT
    thursday_flag,
    to_char(start_time, 'HH24:MI'),
    to_char(end_time, 'HH24:MI')
FROM
    course_schedule_rules
WHERE
    crs_acty_id = :crs_acty_id
    AND course_schedule_rule_id = :course_schedule_rule_id
}
OCUOSCPV_GETTING_FRIDAY_LVDAY{
SELECT
    friday_flag,
    to_char(start_time, 'HH24:MI'),
    to_char(end_time, 'HH24:MI')
FROM
    course_schedule_rules
WHERE
    crs_acty_id = :crs_acty_id
    AND course_schedule_rule_id = :course_schedule_rule_id
}
OCUOSCPV_GETTING_SATURDAY_LVDAY{
SELECT
    saturday_flag,
    to_char(start_time, 'HH24:MI'),
    to_char(end_time, 'HH24:MI')
FROM
    course_schedule_rules
WHERE
    crs_acty_id = :crs_acty_id
    AND course_schedule_rule_id = :course_schedule_rule_id
}
OCUOSCPV_GETTING_SUNDAY_LVDAY{
SELECT
    sunday_flag,
    to_char(start_time, 'HH24:MI'),
    to_char(end_time, 'HH24:MI')
FROM
    course_schedule_rules
WHERE
    crs_acty_id = :crs_acty_id
    AND course_schedule_rule_id = :course_schedule_rule_id
}

OCUOSCPV_GETTING_GET_SCHEDULES{
    SELECT course_schedule_rule_id, monday_flag, tuesday_flag,
                wednesday_flag, thursday_flag, friday_flag, saturday_flag,
                sunday_flag, start_time, end_time
           FROM COURSE_SCHEDULE_RULES
          WHERE crs_acty_id = :crs_acty_id
}
UPDATION_OF_DIRECTION_CODE_OFFSCH{
 UPDATE v_offender_course_events 
    SET    direction_code = 'OUT'
    WHERE event_id = :event_id
}

OCUOSCPV_OFF_PRG_PROFILES_HOLIDAY_FLAG {
SELECT * FROM  OFFENDER_PROGRAM_PROFILES WHERE OFF_PRGREF_ID = :p_off_prgref_id AND OFFENDER_BOOK_ID = :p_offender_book_id
}

OCUOSCPV_UPDATE_OFF_PRG_PROFILES_HOLIDAY_FLAG {
UPDATE OFFENDER_PROGRAM_PROFILES SET HOLIDAY_FLAG =:holidayFlag WHERE OFF_PRGREF_ID = :offPrgrefId
}

OCUOSCPV_UPDATE_OFF_PRG_PROFILES_STATUS {
update OFFENDER_PROGRAM_PROFILES set offender_program_status = 'ALLOC', modify_user_id =:modifyUserId, modify_datetime =current_timestamp where OFF_PRGREF_ID = :offPrgrefId
}
