
OCDPATTE_FIND_RGSCHEDULETYPE {
 	select
	REF_CODE.DESCRIPTION ,
	REF_CODE.CODE
from
	REFERENCE_CODES REF_CODE
where
	( domain = 'EVENTS'
		and (ACTIVE_FLAG = 'Y'
			or :mode <> 'NORMAL' )
		and PARENT_CODE is null )
	and exists (
	select
		'X'
	from
		EVENT_MEASURES EM
	where
		EM.EVENT_TYPE = REF_CODE.CODE
		and (EM.ACTIVE_FLAG = 'Y'
			and EM.EVENT_TYPE not in ('CRT' , 'INTAKE' , 'CCASE' , 'ACP' , 'UW' , 'DRR' , 'SA' , 'PACT' )
				or :mode <> 'NORMAL' ) )
order by
	REF_CODE.LIST_SEQ asc ,
	REF_CODE.DESCRIPTION asc ,
	REF_CODE.CODE asc
}

OCDPATTE_FIND_RGSERVICE {
SELECT description, program_code code, program_id FROM program_services WHERE program_category = 'ACP' AND ( active_flag = 'Y' ) ORDER BY 1, 2
}

OCDPATTE_FIND_RGENGAGEMENT {
select
	rc.description,
	rc.code
from
	reference_codes rc
where
	domain = 'PS_ENGAGE'
	and ( active_flag = 'Y'
		and coalesce (expired_date::timestamp,'11-11-2041')= '11-11-2041' )
order by
	list_seq,
	description,
	code
}
OCDPATTE_FIND_RGINSTPROVIDER {
 	SELECT ALAL.DESCRIPTION DESCRIPTION , ALAL.AGY_LOC_ID CODE   FROM AGENCY_LOCATIONS ALAL  WHERE ALAL.AGENCY_LOCATION_TYPE = 'INST'    AND ALAL.AGY_LOC_ID IN (SELECT CA.AGY_LOC_ID                              FROM CASELOAD_AGENCY_LOCATIONS CA                             WHERE CA.CASELOAD_ID = :CASELOADID )    AND ALAL.AGY_LOC_ID NOT IN ('TRN' , 'OUT' )
}

OCDPATTE_FIND_RGCOMMPROVIDER { 
	SELECT
    t.description description,
    to_char(t.team_id) code
FROM
    automation_teams           t,
    team_staff_members    tm,
    staff_members   sm
WHERE
    t.team_id = tm.team_id
    AND tm.staff_id = sm.staff_id
    AND sm.user_id = :createUserId
    AND t.team_id IN (
        SELECT
            t.team_id
        FROM
            automation_teams            t,
            team_functions   tf
        WHERE
            t.active_flag = 'Y'
            AND t.team_id = tf.team_id
            AND tf.function_type = 'ACP'
    )
ORDER BY
    code

}

OCDPATTE_FIND_RGCONFIRMATTENDANCE {
select
	rc.description,
	emo.outcome_code code
from
	event_measure_outcomes emo,
	event_measures em,
	reference_codes rc
where
	emo.event_measure_id = em.event_measure_id
	and em.event_type = 'ACP'
	and em.event_sub_type = 'PROG_SESS'
	and rc.code = emo.outcome_code
	and rc.domain = 'OUTCOMES'
	and ( emo.active_flag = 'Y'
		and  coalesce (emo.expiry_date::timestamp, '2020-12-12')= '2020-12-12' )
order by
	emo.list_seq,
	rc.description,
	emo.outcome_code
}

OCDPATTE_GET_COURSE_SCHEDULE{
SELECT start_time
               ,end_time
               ,program_instance_code 
               ,module_instance_desc 
               ,session_no
               ,phase_instance_desc 
               ,crs_sch_id
               ,phase_instance_id 
               ,program_instance_id
               ,program_id
               ,program_desc
               ,schedule_status
           FROM v_acp_schedules
       }

OCDPATTE_FIND_RGUNDERSTANDING {
 	SELECT description, code FROM reference_codes WHERE domain = 'PS_UNDER' AND ( active_flag = 'Y') ORDER BY list_seq, description, code
}

OCDPATTE_FIND_RGSTAFFROLE {
 	SELECT
    rc.description,
    rc.code,
    rc.list_seq
FROM
    reference_codes rc
WHERE
    domain = 'PS_ROLE'
    AND ( ( active_flag = 'Y'
            AND coalesce (expired_date::timestamp, '2020-12-12')= '2020-12-12'  )  )
ORDER BY
    list_seq,
    description,
    code
}

OCDPATTE_FIND_RGSTAFFNAME {
 	SELECT SM.LAST_NAME || ', ' || SM.FIRST_NAME  description, SM.STAFF_ID CODE   FROM STAFF_MEMBERS SM , COURSE_ACTIVITY_PARTIES CAP  WHERE SM.STAFF_ID = CAP.STAFF_ID    AND CAP.STAFF_ID IS NOT NULL    AND CAP.CRS_ACTY_ID = :progInstId
}

OCDPATTE_OFFCOURSEATTENDANCES_FIND_OFFENDER_COURSE_ATTENDANCES {
select * from OFFENDER_COURSE_ATTENDANCES where crs_sch_id=:crs_sch_id Order By tag_visits_sort_on_offender_lastname(tag_visits_get_offender_from_book_id(offender_book_id)) 
}
OCDPATTE_OFFCOURSEATTENDANCES_UPDATE_OFFENDER_COURSE_ATTENDANCES {
update OFFENDER_COURSE_ATTENDANCES set EVENT_OUTCOME =:eventOutcome , IN_TIME =:inTime , OUT_TIME =:outTime , ENGAGEMENT_CODE =:engagementCode , UNDERSTANDING_CODE =:understandingCode , AUTHORISED_ABSENCE_FLAG =:authorisedAbsenceFlag , PAY_FLAG =:payFlag , UNEXCUSED_ABSENCE_FLAG =:unexcusedAbsenceFlag , EVENT_STATUS =:eventStatus , COMMENT_TEXT =:commentText, MODIFY_DATETIME =current_timestamp, MODIFY_USER_ID =:modifyUserId where crs_sch_id =:crsSchId and OFFENDER_BOOK_ID =:offenderBookId
}

OCDPATTE_COURSESCHEDULESTAFFS_FIND_COURSE_SCHEDULE_STAFFS {
select * from course_schedule_staffs 
where CRS_SCH_ID=:crschId
}

OCDPATTE_COURSESCHEDULESTAFFS_INSERT_COURSE_SCHEDULE_STAFFS {
insert into COURSE_SCHEDULE_STAFFS(COURSE_SCHEDULE_STAFF_ID, CRS_SCH_ID, STAFF_ROLE, STAFF_ID, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values(NEXTVAL('COURSE_SCHEDULE_STAFF_ID'), :crsSchId, :staffRole, :staffId, :createUserId, current_timestamp, null )
}

OCDPATTE_COURSESCHEDULESTAFFS_UPDATE_COURSE_SCHEDULE_STAFFS {
update COURSE_SCHEDULE_STAFFS set STAFF_ROLE =:staffRole, STAFF_ID =:staffId, MODIFY_USER_ID =:modifyUserId, MODIFY_DATETIME =current_timestamp where COURSE_SCHEDULE_STAFF_ID =:courseScheduleStaffId and CRS_SCH_ID =:crsSchId
}

OCDPATTE_COURSESCHEDULESTAFFS_DELETE_COURSE_SCHEDULE_STAFFS { 
	DELETE FROM COURSE_SCHEDULE_STAFFS  where COURSE_SCHEDULE_STAFF_ID=:courseScheduleStaffId and CRS_SCH_ID=:crsSchId
}

OCDPATTE_DELIVERYDETAILS_FIND_COURSE_SCHEDULES {
 select * from course_schedules where CRS_SCH_ID=:crschId
}
OCDPATTE_DELIVERYDETAILS_UPDATE_COURSE_SCHEDULES {
	update course_schedules set CRS_ACTY_ID=:crsActyId,WEEKDAY=:weekday,SCHEDULE_DATE=:scheduleDate,START_TIME=:startTime,END_TIME=:endTime,SESSION_NO=:sessionNo,DETAILS=:details,MODIFY_DATETIME=current_timestamp ,MODIFY_USER_ID=:modifyUserId,SCHEDULE_STATUS=:scheduleStatus,CATCH_UP_CRS_SCH_ID=:catchUpCrsSchId,VIDEO_REFERENCE_ID=:videoReferenceId,SEAL_FLAG=:sealFlag,CANCELLED_FLAG=:cancelledFlag where CRS_SCH_ID=:crsSchId 
}

OCDPATTE_GET_OFFENDER_DETAILS {
SELECT o.offender_id, o.offender_id_display, o.last_name, o.first_name, o.middle_name, ob.root_offender_id FROM offenders o, offender_bookings ob WHERE ob.offender_book_id = :offBookId AND o.offender_id = ob.offender_id
}

OCDPATTE_CHECK_UA_EVENT_OUTCOME {
SELECT 'Y' FROM EVENT_MEASURES event_measure, EVENT_MEASURE_OUTCOMES event_out WHERE event_measure.event_type = :pEventType AND event_measure.event_sub_type = :pEventSubType AND event_out.outcome_code = :pEventOutcome AND event_out.set_counter_flag = 'Y' AND event_out.event_measure_id = event_measure.event_measure_id
}

OCDPATTE_IS_STAFF_EXISTS {
SELECT SM.STAFF_ID FROM STAFF_MEMBERS SM ,COURSE_ACTIVITY_PARTIES CAP WHERE SM.STAFF_ID = CAP.STAFF_ID AND CAP.STAFF_ID IS NOT NULL AND CAP.CRS_ACTY_ID = :progInsId
}

OCDPATTE_GET_CRS_ACTIVITY_DETAILS {
SELECT * FROM COURSE_ACTIVITIES WHERE crs_acty_id = :progInsId
}
OCDPATTE_GET_PROG_LOC {
SELECT * FROM v_addresses WHERE address_id = :addId
}
OCDPATTE_INT_LOC_DESCRIPTION {
select Tag_Int_Loc_int_loc_desc(:intLocationId) from dual
}
OCDPATTE_GET_ACT_OUT_COME_FLAG{
select PAY_FLAG , AUTHORISED_ABSENCE_FLAG from PAYABLE_ATTENDANCE_OUTCOMES where OUTCOME_CODE = :outComeCode and EVENT_TYPE = :eventType and (to_date(:eventDate, 'DD/MM/YYYY')) between START_DATE and coalesce (END_DATE, current_timestamp)
}
OCDPATTE_COURSESCHED_INSERT_COURSE_SCHEDULES{
	INSERT INTO COURSE_SCHEDULES(CRS_SCH_ID, CRS_ACTY_ID, SCHEDULE_DATE, START_TIME, END_TIME, SCHEDULE_STATUS,VIDEO_REFERENCE_ID) VALUES(:crsSchId,:crsActyId,:scheduleDate,:startTime,:endTime,:scheduleStatus,:videoReferenceId)
}
OCDPATTE_GET_OFFENDER_SENTENCE_EVENTS{
SELECT offender_book_id, sentence_seq, event_date FROM v_offender_sentence_events v_off_sent WHERE v_off_sent.event_id = :eventId
}
OCDPATTE_GET_OFFENDER_SENTENCEUA{
SELECT COUNT (*) FROM OFFENDER_SENTENCE_UA_EVENTS WHERE offender_book_id = :offBookId AND sentence_seq = :sentSeq AND event_date = :eventDate AND event_id != :eventId
}


OCDPATTE_FIND_RGAGYLOCS {
 SELECT DESCRIPTION ,AGY_LOC_ID CODE FROM AGENCY_LOCATIONS AL WHERE AL.AGY_LOC_ID IN ( SELECT CA.AGY_LOC_ID  FROM CASELOAD_AGENCY_LOCATIONS CA  WHERE CA.CASELOAD_ID = :caseLoadId   AND CA.AGY_LOC_ID = AL.AGY_LOC_ID ) AND AL.AGY_LOC_ID NOT IN ('TRN' , 'OUT' )  ORDER BY LIST_SEQ ,DESCRIPTION ,AGY_LOC_ID

}

OCDPATTE_FIND_RGTEAMAGYLOCS {
 select  t.description description, t.team_id,t.team_code  code  from automation_teams t where team_id in (select team_id from TEAM_AGENCY_LOC tl where tl.agy_loc_id in ( SELECT CA.AGY_LOC_ID  FROM CASELOAD_AGENCY_LOCATIONS CA  WHERE CA.CASELOAD_ID = :caseLoadId ))
}
OCDPATTE_FIND_RGCORPLOCS {
 	SELECT TO_CHAR(C.CORPORATE_ID) CODE,C.CORPORATE_NAME DESCRIPTION   FROM CORPORATES C ,CORPORATE_TYPES CT  WHERE C.CORPORATE_ID = CT.CORPORATE_ID    AND CT.CORPORATE_TYPE = 'PRG'    AND C.ACTIVE_FLAG = 'Y' ORDER BY CORPORATE_NAME
}
OCDPATTE_FIND_OUTCOME_CANCELFLAG {
select	* from event_measure_outcomes emo  where emo.cancel_flag = 'Y'
}