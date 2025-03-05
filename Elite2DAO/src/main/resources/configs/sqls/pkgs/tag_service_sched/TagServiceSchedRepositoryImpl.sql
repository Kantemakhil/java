COURSE_SCHEDULES_SELECT{
         SELECT schedule_date, start_time
           FROM course_schedules
          WHERE crs_sch_id =:p_crs_sch_id
}

COURSE_SCHEDULES_DELETE{
        delete
from
	course_schedules
where
	(schedule_date > :lv_schedule_date
	or (schedule_date = :lv_schedule_date
	and start_time >= :lv_start_time))
	and catch_up_crs_sch_id is null
	and schedule_status = 'SCH'
	and crs_acty_id in (with recursive cte as (
	select
		crs_acty_id
	from
		course_activities
	where
		crs_acty_id = coalesce(:p_phase_instance_id, :p_program_instance_id)::bigint
union all
	select
		ca.crs_acty_id
	from
		course_activities ca
	join cte c on
		(c.crs_acty_id = parent_crs_acty_id) )
	select
		*
	from
		cte )
  }
 COURSE_SCHEDULES_SELECT{
         SELECT schedule_date, start_time,crs_acty_id
           FROM course_schedules
          WHERE crs_sch_id =:p_crs_sch_id
}

OFFENDER_COURSE_ATTENDANCES_DELETE{
      delete
from
	offender_course_attendances
where
	crs_sch_id in (
	select
		crs_sch_id
	from
		course_schedules
	where
		start_time >= (
		select
			start_time
		from
			v_acp_schedules
		where
			crs_sch_id =:p_crs_sch_id
			and program_instance_id =:p_program_instance_id)
		and catch_up_crs_sch_id is null
		and schedule_status = 'SCH'
		and crs_acty_id in (with recursive cte as (
		select
			crs_acty_id
		from
			course_activities
		where
			crs_acty_id = coalesce(:p_phase_instance_id::text, :p_program_instance_id::text)::bigint
	union all
		select
			ca.crs_acty_id
		from
			course_activities ca
		join cte c on
			(c.crs_acty_id = parent_crs_acty_id) )
		select
			*
		from
			cte))
  }
COURSE_SCHEDULES_DELETE{
        delete
from
	course_schedules
where
	(schedule_date > :lv_schedule_date
	or (schedule_date = :lv_schedule_date
	and start_time >= :lv_start_time))
	and catch_up_crs_sch_id is null
		and end_time >current_timestamp 
	--and schedule_status = 'SCH'
	and crs_acty_id in (with recursive cte as (
	select
		crs_acty_id
	from
		course_activities
	where
		crs_acty_id = coalesce(:p_phase_instance_id::text, :p_program_instance_id::text)::bigint
union all
	select
		ca.crs_acty_id
	from
		course_activities ca
	join cte c on
		(c.crs_acty_id = parent_crs_acty_id) )
	select
		*
	from
		cte )
  }
 GET_MAX_START_TIME_CURSOR_PRIOR_CUR_IS {
SELECT MAX(START_TIME) FROM V_ACP_SCHEDULES WHERE PROGRAM_INSTANCE_ID = :P_PROGRAM_INSTANCE_ID
AND SCHEDULE_STATUS = 'SCH' AND CATCH_UP_SESSION_FLAG = 'N' AND (PHASE_LIST_SEQ * 10000000) + SESSION_NO < :LV_SESSION_SEQ
}
GET_MIN_START_TIME_CURSOR_PRIOR_CUR_IS {
SELECT MIN(START_TIME) FROM V_ACP_SCHEDULES WHERE PROGRAM_INSTANCE_ID = :P_PROGRAM_INSTANCE_ID
AND SCHEDULE_STATUS = 'SCH' AND CATCH_UP_SESSION_FLAG = 'N' AND (PHASE_LIST_SEQ * 10000000) + SESSION_NO > :LV_SESSION_SEQ
}
GET_LV_SESSION_SEQ{
SELECT  (:P_PHASE_LIST_SEQ * 10000000) +  :P_SESSION_NO FROM V_ACP_SCHEDULES
}
GET_CRS_ACTY_WITH_GAPS_GAP_CUR {
with recursive cte as (
select
	CA.CRS_ACTY_ID,
	CA.COURSE_CLASS,
	CA.PARENT_CRS_ACTY_ID,
	CA.LIST_SEQ,
	CA.SESSION_LENGTH,
	PS.DESCRIPTION,
	CA.NO_OF_SESSIONS,
	array[ row_number() over (
order by
	CA.LIST_SEQ) ] as hierarchy,
	case
		when exists(
		select
			1
		from
			COURSE_ACTIVITIES CA1,
			PROGRAM_SERVICES PS
		where
			CA.PROGRAM_ID = PS.PROGRAM_ID
			and CA.CRS_ACTY_ID = CA1.PARENT_CRS_ACTY_ID ) then 0
		else 1
	end CONNECT_BY_ISLEAF
from
	COURSE_ACTIVITIES CA,
	PROGRAM_SERVICES PS
where
	CA.PROGRAM_ID = PS.PROGRAM_ID
	and CA.CRS_ACTY_ID = :P_CRS_ACTY_ID
union all
select
	CA.CRS_ACTY_ID,
	CA.COURSE_CLASS,
	CA.PARENT_CRS_ACTY_ID,
	CA.LIST_SEQ,
	CA.SESSION_LENGTH,
	PS.DESCRIPTION,
	CA.NO_OF_SESSIONS,
	array_append(c.hierarchy, row_number() over (order by CA.LIST_SEQ)) as hierarchy,
	case
		when exists(
		select
			1
		from
			COURSE_ACTIVITIES CA1,
			PROGRAM_SERVICES PS
		where
			CA.PROGRAM_ID = PS.PROGRAM_ID
			and CA.CRS_ACTY_ID = CA1.PARENT_CRS_ACTY_ID ) then 0
		else 1
	end CONNECT_BY_ISLEAF
from
	COURSE_ACTIVITIES CA
join PROGRAM_SERVICES PS on
	CA.PROGRAM_ID = PS.PROGRAM_ID
join cte c on
	(c.CRS_ACTY_ID = CA.PARENT_CRS_ACTY_ID) )
select
	*
from
	cte c
where
	c.NO_OF_SESSIONS > (
	select
		COUNT(*)
	from
		COURSE_SCHEDULES CS
	where
		CS.CRS_ACTY_ID = C.CRS_ACTY_ID
		and CS.SCHEDULE_STATUS = 'SCH'
		and coalesce(CS.CATCH_UP_CRS_SCH_ID::text, '') = '' )
	and CONNECT_BY_ISLEAF = 1
order by
	hierarchy
limit 1
}

GET_CRS_ACTY_WITH_GAPS_PHS_CUR {

SELECT PS.DESCRIPTION, CA.LIST_SEQ, CA.SESSION_LENGTH
           FROM COURSE_ACTIVITIES CA, PROGRAM_SERVICES PS
          WHERE CA.CRS_ACTY_ID = :P_PHASE_INSTANCE_ID
            AND CA.PROGRAM_ID = PS.PROGRAM_ID

}
CS_BUILD_RECURRING_SCHEDULE_GET_LAST_SCHED_DATE {

	SELECT MAX ( CS.SCHEDULE_DATE ) 
           FROM COURSE_SCHEDULES CS
          WHERE CS.CRS_ACTY_ID = :P_CRS_ACTY_ID

}
CS_BUILD_RECURRING_SCHEDULE_HOL_CUR_ONE {
	 SELECT coalesce(HOLIDAY_FLAG, 'N') as HOLIDAY_FLAG
           FROM COURSE_ACTIVITIES
          WHERE CRS_ACTY_ID = :P_CRS_ACTY_ID;

}


CS_BUILD_RECURRING_SCHEDULE_CRS_SCH_ID {
	SELECT nextval('CRS_SCH_ID') FROM DUAL
}

CS_BUILD_RECURRING_SCHEDULE_INSERT_CRS_SCHEDULES {
insert into COURSE_SCHEDULES(CRS_SCH_ID, CRS_ACTY_ID, SCHEDULE_DATE, START_TIME, END_TIME, SCHEDULE_STATUS, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME) values(:crsSchId, :crsActyId, :scheduleDate, :startTime, :endTime, :scheduleStatus, CURRENT_TIMESTAMP, :createUserId, null)
}

CS_BUILD_RECURRING_SCHEDULE_HOL_CUR {
	
	select
	EVENT_DATE EVENT_DATE,
	coalesce ( EVENT_END_DATE,
	EVENT_DATE )::DATE - EVENT_DATE::DATE + 1 EVENT_SEQ
from
	SYSTEM_EVENTS
where
	EVENT_DATE > :P_START_DATE
}

CS_BUILD_RECURRING_SCHEDULE_LV_RULE_CUR {
	SELECT WEEK_NO, MONDAY_FLAG, TUESDAY_FLAG, WEDNESDAY_FLAG, THURSDAY_FLAG, FRIDAY_FLAG, SATURDAY_FLAG,
                SUNDAY_FLAG, START_TIME, END_TIME
           FROM COURSE_SCHEDULE_RULES
          WHERE CRS_ACTY_ID = :P_CRS_ACTY_ID

}

GET_SSN_CUR{
SELECT coalesce ( SUM ( no_of_sessions ), 0 ) FROM course_activities WHERE parent_crs_acty_id = :parentCrsActyId AND list_seq < :listSeq 
}

GET_CHECK_RESPECT_HOLS{
SELECT holiday_flag FROM COURSE_ACTIVITIES WHERE crs_acty_id = :pCrsActyId
}
GET_HOL_CUR{
 SELECT event_date EVENT_DATE, NVL ( event_end_date, event_date ) - event_date + 1 NO_DAYS FROM system_events WHERE event_date > :pStartDate
}
GET_LV_RULE_CUR{
SELECT week_no, monday_flag, tuesday_flag, wednesday_flag, thursday_flag, friday_flag, saturday_flag, sunday_flag, start_time, end_time FROM course_schedule_rules WHERE crs_acty_id = :pCrsActyId
}
GET_CRS_CUR{
SELECT course_class, parent_crs_acty_id FROM course_activities WHERE crs_acty_id = :pCrsActyId 
}
GET_SCH_CUR{
SELECT crs_sch_id, phase_instance_id, module_instance_id FROM v_acp_schedules WHERE program_instance_id = :lvProgramInstanceId AND schedule_status = 'SCH' AND catch_up_session_flag = 'N' AND ( :lvPhaseInstanceId IS NULL OR phase_instance_id = :lvPhaseInstanceId ) AND start_time >= ( SELECT start_time FROM v_acp_schedules WHERE crs_sch_id = :pCrsActyId AND program_instance_id = :lvProgramInstanceId ) ORDER BY start_time;
}
GET_ADJ_ALL_PER_CRS_CUR{
with recursive cte as (
select
	crs_acty_id,
	case
		when exists(
		select
			1
		from
			course_activities m
		where
			m.crs_acty_id = ca.parent_crs_acty_id ) then 0
		else 1
	end CONNECT_BY_ISLEAF
from
	course_activities ca
where
	crs_acty_id = :pCrsActyId
union all
select
	ca.crs_acty_id,
	case
		when exists(
		select
			1a
		from
			course_activities m
		where
			m.crs_acty_id = ca.parent_crs_acty_id ) then 0
		else 1
	end CONNECT_BY_ISLEAF
from
	course_activities ca
join cte c on
	(c.crs_acty_id = ca.parent_crs_acty_id) )
select
	crs_acty_id
from
	cte ca
where
	CONNECT_BY_ISLEAF = 1
}
GET_ADJUST_PERIOD_CRS_CUR{
SELECT crs_acty_id, no_of_sessions, course_class, parent_crs_acty_id, list_seq FROM course_activities WHERE course_class IN ( 'CRS_MOD', 'CRS_PH' ) START WITH crs_acty_id = :pCrsActyId CONNECT BY PRIOR parent_crs_acty_id = crs_acty_id
}
GET_SESSION_IMITS{
 SELECT MIN(session_no) session_no_one, MIN(schedule_date) schedule_date_one, MAX(session_no) session_no_two, MAX(schedule_date) schedule_date_two FROM course_schedules WHERE schedule_status = 'SCH' AND catch_up_crs_sch_id IS NULL AND crs_acty_id IN ( SELECT crs_acty_id FROM course_activities START WITH crs_acty_id = :pcrsactyid CONNECT BY PRIOR crs_acty_id = parent_crs_acty_id )
}
GET_SSN_CUR{
SELECT coalesce ( SUM ( no_of_sessions ), 0 ) FROM course_activities WHERE parent_crs_acty_id = :parentCrsActyId AND list_seq < :listSeq 
}
UPDATE_COURSE_ACTIVITIES_OPERATION{
update course_activities ca set ca.schedule_start_date = DECODE ( :LV_MIN_SESSION_NO, :LV_START_SESSION_NO, :LV_MIN_SCHEDULE_DATE, null ), ca.schedule_end_date = DECODE ( :LV_MAX_SESSION_NO, :LV_END_SESSION_NO, :LV_MAX_SCHEDULE_DATE, null ), MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where crs_acty_id = :CRS_ACTY_ID
}
COURSE_ACTIVITIES_S_E_DATES{
SELECT schedule_start_date, schedule_end_date FROM course_activities WHERE crs_acty_id = :pCrsActyId
}
GET_ALLOC_CUR_SELECT{
SELECT opp.off_prgref_id off_prgref_id, opp.offender_start_date offender_start_date, opp.start_session_no start_session_no, opp.offender_end_date offender_end_date, ps.module_flag module_flag FROM offender_program_profiles opp, program_services ps WHERE opp.crs_acty_id = :pCrsActyId AND opp.profile_class = 'CRS' AND opp.offender_program_status = 'ALLOC' AND opp.program_id = ps.program_id FOR UPDATE OF opp.offender_start_date NOWAIT
}
GET_SCHEDULE_DATE_FOR_SESSION{
SELECT schedule_date FROM course_schedules WHERE schedule_status = 'SCH' AND catch_up_crs_sch_id IS NULL AND session_no = :startSessionNo AND crs_acty_id IN ( SELECT crs_acty_id FROM course_activities START WITH crs_acty_id = :crsActyId CONNECT BY PRIOR crs_acty_id = parent_crs_acty_id ) 
}
GET_END_CUR{
 SELECT MAX (NVL (offender_end_date, :lvBigDate)) FROM offender_program_profiles WHERE parent_off_prgref_id = :offPrgrefId AND profile_class = 'CRS' AND offender_program_status = 'ALLOC'
}
UPDATE_OFF_PRO_PROF{
update offender_program_profiles set offender_start_date = :lvOffenderStartDate, offender_end_date = :lvOffenderEndDate, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where OFF_PRGREF_ID = :offPrgrefId
}
CLEAR_COURSE_ATTENDANCES{
DELETE FROM offender_course_attendances WHERE crs_sch_id IN ( SELECT crs_sch_id FROM course_schedules WHERE start_time >= ( SELECT start_time FROM v_acp_schedules WHERE crs_sch_id = :pcrsactyid AND program_instance_id = :lvprograminstanceid ) AND catch_up_crs_sch_id IS NULL AND schedule_status = 'SCH' AND crs_acty_id IN ( SELECT crs_acty_id FROM course_activities START WITH crs_acty_id = nvl(:lvphaseinstanceid, :lvprograminstanceid) CONNECT BY PRIOR crs_acty_id = parent_crs_acty_id ) )
}

CHANGE_COURSE_SCHEDULE_UPDATE_COURSE_SCHEDULE {
update course_schedules set schedule_date = :p_schedule_date, start_time = :p_start_time, end_time = :p_end_time, schedule_status = :p_schedule_status, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where crs_sch_id = :p_crs_sch_id
}

 CS_BUILD_ACP_SCHEDULE_CRS_CUR {
 
 with recursive cte as (
select
	CRS_ACTY_ID,
	NO_OF_SESSIONS,
	COURSE_CLASS,
	PARENT_CRS_ACTY_ID,
	LIST_SEQ
from
	COURSE_ACTIVITIES
where
	CRS_ACTY_ID = :P_CRS_ACTY_ID
union all
select
	ca.CRS_ACTY_ID,
	ca.NO_OF_SESSIONS,
	ca.COURSE_CLASS,
	ca.PARENT_CRS_ACTY_ID,
	ca.LIST_SEQ
from
	COURSE_ACTIVITIES ca
join cte c on
	(c.PARENT_CRS_ACTY_ID = ca.CRS_ACTY_ID)

)
select
	*
from
	cte
where
	COURSE_CLASS in ( 'CRS_MOD', 'CRS_PH' )  
 }
 
 CS_BUILD_ACP_SCHEDULE_GSL_CUR {
 select
	MIN(SESSION_NO) CRS_SCH_ID,
	MIN(SCHEDULE_DATE) START_TIME,
	MAX(SESSION_NO) CRS_ACTY_ID,
	MAX(SCHEDULE_DATE) END_TIME
from
	COURSE_SCHEDULES
where
	SCHEDULE_STATUS = 'SCH'
	and coalesce(CATCH_UP_CRS_SCH_ID::text, '') = ''
	and CRS_ACTY_ID in (with recursive cte as (
	select
		CRS_ACTY_ID
	from
		COURSE_ACTIVITIES
	where
		CRS_ACTY_ID = :P_CRS_ACTY_ID
union all
	select
		ca.CRS_ACTY_ID
	from
		COURSE_ACTIVITIES ca
	join cte c on
		(c.CRS_ACTY_ID = ca.PARENT_CRS_ACTY_ID )

)
	select
		*
	from
		cte

) 
 }
 
 CS_BUILD_ACP_SCHEDULE_UPDATE_COURSE_ACTIVITIES{
 update
	COURSE_ACTIVITIES
set
	SCHEDULE_START_DATE =:LV_MIN_SCHEDULE_DATE :: timestamp,
	SCHEDULE_END_DATE =:LV_MAX_SCHEDULE_DATE :: timestamp,
	MODIFY_USER_ID = :modifyUserId ,
	MODIFY_DATETIME = CURRENT_TIMESTAMP
where
	CRS_ACTY_ID = :CRS_ACTY_ID;

 }
 

 CS_BUILD_ACP_SCHEDULE_GET_SCHEDULE_START_DATE_AND_SCHEDULE_END_DATE {
 	SELECT SCHEDULE_START_DATE,SCHEDULE_END_DATE FROM COURSE_ACTIVITIES  WHERE CRS_ACTY_ID =:CRS_ACTY_ID 
 }
 
CS_BUILD_ACP_SCHEDULE_SCH_CUR {
select SCHEDULE_DATE from COURSE_SCHEDULES where SCHEDULE_STATUS = 'SCH' and coalesce(CATCH_UP_CRS_SCH_ID::text, '') = '' and SESSION_NO = 8 and CRS_ACTY_ID in (with recursive cte as ( select CRS_ACTY_ID from COURSE_ACTIVITIES where CRS_ACTY_ID = 16306 union all select ca.CRS_ACTY_ID from COURSE_ACTIVITIES ca join cte c on (c.CRS_ACTY_ID = ca.PARENT_CRS_ACTY_ID )) select * from cte ) 
}
 
 COUNT_SESSIONS_COUNT {
SELECT COUNT ( * ) FROM course_schedules WHERE schedule_status = 'SCH' AND 
 catch_up_crs_sch_id IS NULL AND crs_acty_id IN 
 ( WITH RECURSIVE CTE_CONNECT_BY AS (
SELECT 1 AS LEVEL, S.* FROM course_activities S WHERE crs_acty_id = :p_crs_acty_id
UNION ALL
SELECT LEVEL + 1 AS LEVEL, S.* FROM CTE_CONNECT_BY R INNER JOIN course_activities S ON  r.crs_acty_id = s.parent_crs_acty_id
)
SELECT crs_acty_id FROM cte_connect_by )  
 }
 
 GET_LAST_PROGRAM_SCHEDULE_DATE_MAX_CUR {
	select
	MAX (start_time)
from
	v_acp_schedules
where
	program_instance_id = :p_program_instance_id
	and schedule_status = 'SCH'
	and catch_up_session_flag = 'N'
	and ( coalesce (:p_phase_list_seq::text ,'') = ''
		or ( coalesce (:p_phase_list_seq::text,'') != ''
			and phase_list_seq <= :p_phase_list_seq ) )
 }

GET_NEXT_PHASE_SCHEDULE_DATE_MIN_CUR {
SELECT MIN ( start_time ) FROM v_acp_schedules WHERE program_instance_id = :p_program_instance_id AND schedule_status = 'SCH' AND catch_up_session_flag = 'N' AND phase_list_seq > :p_phase_list_seq
}

GET_MAX_SESSION_CUR{
 select
	coalesce( MAX(SESSION_NO), 0 )
from
	COURSE_SCHEDULES
where
	coalesce(CATCH_UP_CRS_SCH_ID::text, '') = ''
	and SCHEDULE_STATUS = 'SCH'
	and CRS_ACTY_ID in (with recursive cte as (
	select
		CRS_ACTY_ID
	from
		COURSE_ACTIVITIES
	where
		CRS_ACTY_ID = :P_CRS_ACTY_ID
union all
	select
		ca.CRS_ACTY_ID
	from 
		COURSE_ACTIVITIES ca
	join cte c on
		(c.CRS_ACTY_ID = ca.PARENT_CRS_ACTY_ID )

)
	select
		*
	from
		cte

)
 }     
 
START_CUR{
    SELECT  CRS_ACTY_ID, COURSE_CLASS, LIST_SEQ, PARENT_CRS_ACTY_ID   FROM COURSE_ACTIVITIES WHERE CRS_ACTY_ID = :P_CRS_ACTY_ID
 }
 
 PRIOR_CUR{
SELECT MAX(start_time) FROM v_acp_schedules WHERE program_instance_id =:p_program_instance_id AND schedule_status = 'SCH' AND catch_up_session_flag = 'N' AND (phase_list_seq * 10000000) + session_no < :lv_session_seq
}
CREATE_COURSE_SCHEDULE_INSERT_COURSE_SCHEDULE {
insert into course_schedules ( crs_sch_id,crs_acty_id, schedule_date, start_time, end_time, session_no , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( :crs_sch_id,:p_crs_acty_id, :p_schedule_date, :p_start_time, :p_end_time, :p_session_no , :createUserId, CURRENT_TIMESTAMP , null )


}

CREATE_COURSE_SCHEDULE_CREATE_SCH_ATTENDANCES {
insert into offender_course_attendances (event_id, offender_book_id, event_class, event_type, event_sub_type, off_prgref_id, reference_id, crs_sch_id, crs_acty_id, event_date, start_time, end_time, in_time, out_time, event_status, event_outcome, agy_loc_id, to_internal_location_id, to_address_id, to_agy_loc_id, session_no, offender_prg_obligation_id, program_id, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) select nextval('event_id'), opp.offender_book_id, tag_prg_prg_event_class(opp.off_prgref_id), 'ACP' event_type, ca.course_activity_type, opp.off_prgref_id, cs.crs_sch_id, cs.crs_sch_id, cs.crs_acty_id, cs.schedule_date, start_time, end_time, start_time, end_time, case when SIGN(schedule_date::date - sysdate()::date)=-1 then 'EXP' else 'SCH' end , null, opp.agy_loc_id, ca.internal_location_id, ca.services_address_id, addr.owner_code, cs.session_no, opp.offender_prg_obligation_id, opp.program_id, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP from offender_program_profiles opp, course_schedules cs, course_activities ca left outer join addresses addr on (ca.services_address_id = addr.address_id) where cs.crs_sch_id = :p_crs_sch_id and opp.crs_acty_id = cs.crs_acty_id and ca.crs_acty_id = opp.crs_acty_id and (ca.course_activity_type is not null and ca.course_activity_type::text <> '') and opp.offender_program_status = 'ALLOC' and ca.active_flag = 'Y' and cs.session_no >= opp.start_session_no and coalesce(cs.catch_up_crs_sch_id::text, '') = '' and opp.offender_start_date <= cs.schedule_date and coalesce(coalesce(opp.offender_end_date, ca.schedule_end_date), cs.schedule_date ) >= cs.schedule_date and not exists ( select 'x' from offender_course_attendances oca where oca.offender_book_id = opp.offender_book_id and oca.crs_sch_id = cs.crs_sch_id and oca.event_date = cs.schedule_date) and cs.schedule_date <= date_trunc('day', LOCALTIMESTAMP);
}
GET_CRS_CUR_TWO{
SELECT course_class, parent_crs_acty_id FROM course_activities WHERE crs_acty_id = :pCrsActyId 
}

GET_SCH_CUR_TWO{
SELECT crs_sch_id, phase_instance_id, module_instance_id FROM v_acp_schedules WHERE program_instance_id = :lvProgramInstanceId AND schedule_status = 'SCH' AND catch_up_session_flag = 'N' AND ( coalesce(:lvPhaseInstanceId,0) = 0  OR phase_instance_id = :lvPhaseInstanceId ) AND start_time >= ( SELECT start_time FROM v_acp_schedules WHERE crs_sch_id = :pCrsSchId AND program_instance_id = :lvProgramInstanceId ) ORDER BY start_time
}


CLEAR_COURSE_ATTENDANCES_TWO{
	delete
from
offender_course_attendances
where
crs_sch_id in ((
select
crs_sch_id
from
course_schedules
where
start_time >= (
select
start_time
from
v_acp_schedules
where
crs_sch_id = :pcrsactyid
and program_instance_id = :lvprograminstanceid )
and coalesce(catch_up_crs_sch_id::text, '') = ''
and schedule_status = 'SCH'
and crs_acty_id in (with recursive cte as (
select
crs_acty_id
from
course_activities
where
crs_acty_id = coalesce(:lvphaseinstanceid::bigint, :lvprograminstanceid::bigint)
union all
select
ca.crs_acty_id
from
course_activities ca
join cte c on
(c.crs_acty_id = ca.parent_crs_acty_id ) )
select
*
from
cte ) ))
}

LAST_CUR{
 select
CA.CRS_ACTY_ID,
CA.LIST_SEQ,
CA.COURSE_CLASS,
PS.DESCRIPTION
from
COURSE_ACTIVITIES CA,
PROGRAM_SERVICES PS
where
CA.PROGRAM_ID = PS.PROGRAM_ID
and CA.PARENT_CRS_ACTY_ID = :P_PARENT_CRS_ACTY_ID
and CA.LIST_SEQ = (
select
MAX (CA2.LIST_SEQ)
from
COURSE_ACTIVITIES CA2
where
CA2.PARENT_CRS_ACTY_ID = :P_PARENT_CRS_ACTY_ID
and tag_service_sched_no_sessions_done (CA2.CRS_ACTY_ID) = CA2.NO_OF_SESSIONS )
}    

PRIOR_CUR_TWO {

	 SELECT MIN ( START_TIME )
           FROM V_ACP_SCHEDULES
          WHERE PROGRAM_INSTANCE_ID = :P_PROGRAM_INSTANCE_ID
            AND SCHEDULE_STATUS = 'SCH'
            AND CATCH_UP_SESSION_FLAG = 'N'
            AND ( PHASE_LIST_SEQ * 10000000 ) + SESSION_NO > :LV_SESSION_SEQ

}

CS_BUILD_ACP_SCHEDULE_CA_CUR {
	 SELECT CA.NO_OF_SESSIONS, CA.COURSE_CLASS, CA.PARENT_CRS_ACTY_ID
           FROM COURSE_ACTIVITIES CA
          WHERE CA.CRS_ACTY_ID = :P_CRS_ACTY_ID
}

CS_BUILD_ACP_SCHEDULE_MAX_CUR {
	select
coalesce( MAX(SESSION_NO), 0 )
from
COURSE_SCHEDULES
where
coalesce(CATCH_UP_CRS_SCH_ID::text, '') = ''
and SCHEDULE_STATUS = 'SCH'
and CRS_ACTY_ID in (with recursive cte as (
select
CRS_ACTY_ID
from
COURSE_ACTIVITIES
where
CRS_ACTY_ID = :P_CRS_ACTY_ID
union all
select
ca.CRS_ACTY_ID
from
COURSE_ACTIVITIES ca
join cte c on
(c.CRS_ACTY_ID = PARENT_CRS_ACTY_ID ) )
select
*
from
cte

)
}

CS_BUILD_ACP_SCHEDULE_START_CUR {
	select
TAG_SERVICE_SCHED_GET_START_SESSION_NUMBER ( CRS_ACTY_ID,
COURSE_CLASS,
LIST_SEQ,
PARENT_CRS_ACTY_ID )
from
COURSE_ACTIVITIES
where
CRS_ACTY_ID = :P_CRS_ACTY_ID
}

CS_BUILD_ACP_SCHEDULE_LAST_CUR_NEW {
	SELECT *
           FROM V_ACP_SCHEDULES S1
          WHERE S1.PROGRAM_INSTANCE_ID = :P_PROGRAM_INSTANCE_ID
            AND (    coalesce(:P_PHASE_INSTANCE_ID::bigint, 0) = 0 
                  OR S1.PHASE_INSTANCE_ID = :P_PHASE_INSTANCE_ID )
            AND S1.SCHEDULE_STATUS = 'SCH'
            AND S1.CATCH_UP_SESSION_FLAG = 'N'
            AND ( S1.PHASE_LIST_SEQ * 10000000 ) + S1.SESSION_NO =
                   ( SELECT MAX (  ( S2.PHASE_LIST_SEQ * 10000000 ) + S2.SESSION_NO )
                      FROM V_ACP_SCHEDULES S2
                     WHERE S2.PROGRAM_INSTANCE_ID = :P_PROGRAM_INSTANCE_ID
                       AND (    coalesce(:P_PHASE_INSTANCE_ID::bigint, 0) = 0
                             OR S2.PHASE_INSTANCE_ID = :P_PHASE_INSTANCE_ID )
                       AND S2.SCHEDULE_STATUS = 'SCH'
                       AND S2.CATCH_UP_SESSION_FLAG = 'N' )
                       
           
 }
 
 CS_BUILD_ACP_SCHEDULE_LV_CA_REF {
 	select
CRS_ACTY_ID,
SCHEDULE_START_DATE,
NO_OF_SESSIONS,
LIST_SEQ,
COURSE_CLASS
from
COURSE_ACTIVITIES
where
PARENT_CRS_ACTY_ID = :P_CRS_ACTY_ID
and ( ( COURSE_CLASS = 'CRS_PH'
and LIST_SEQ >= :LV_PHASE_LIST_SEQ )
or ( COURSE_CLASS = 'CRS_MOD'
and LIST_SEQ >= :LV_MODULE_LIST_SEQ ) )
and LIST_SEQ <= coalesce ( :P_LIST_SEQ_END,
9999999 )
order by
LIST_SEQ
 
 }
 
 CS_BUILD_ACP_SCHEDULE_CRS_CUR_ONE {
 
 	with recursive cte as (
select
CRS_ACTY_ID,
NO_OF_SESSIONS,
COURSE_CLASS,
PARENT_CRS_ACTY_ID,
LIST_SEQ
from
COURSE_ACTIVITIES
where
CRS_ACTY_ID = :P_CRS_ACTY_ID
union all
select
ca.CRS_ACTY_ID,
ca.NO_OF_SESSIONS,
ca.COURSE_CLASS,
ca.PARENT_CRS_ACTY_ID,
ca.LIST_SEQ
from
COURSE_ACTIVITIES ca
join cte c on
(c.PARENT_CRS_ACTY_ID = ca.CRS_ACTY_ID ) )
select
*
from
cte
where
COURSE_CLASS in ( 'CRS_MOD', 'CRS_PH' )
 
 }
 
 CS_BUILD_ACP_SCHEDULE_GSL_CUR_ONE {
 
 	select
MIN(SESSION_NO) CRS_SCH_ID,
MIN(SCHEDULE_DATE) START_TIME,
MAX(SESSION_NO) CRS_ACTY_ID,
MAX(SCHEDULE_DATE) END_TIME
from
COURSE_SCHEDULES
where
SCHEDULE_STATUS = 'SCH'
and coalesce(CATCH_UP_CRS_SCH_ID::text, '') = ''
and CRS_ACTY_ID in (with recursive cte as (
select
CRS_ACTY_ID
from
COURSE_ACTIVITIES
where
CRS_ACTY_ID = :P_CRS_ACTY_ID
union all
select
ca.CRS_ACTY_ID
from
COURSE_ACTIVITIES ca
join cte c on
(c.CRS_ACTY_ID = PARENT_CRS_ACTY_ID ) )
select
*
from
cte

)
 
 }
 
 CS_BUILD_ACP_SCHEDULE_SSN_CUR {
 	select
coalesce ( SUM (NO_OF_SESSIONS),
0 )
from
COURSE_ACTIVITIES
where
PARENT_CRS_ACTY_ID = :P_PARENT_CRS_ACTY_ID
and LIST_SEQ < :P_LIST_SEQ
 }
 
 CS_BUILD_ACP_SCHEDULE_UPDATE_COURSE_ACTIVITIES_ONE{
 update
	COURSE_ACTIVITIES
set
	SCHEDULE_START_DATE =:LV_MIN_SCHEDULE_DATE :: timestamp,
	SCHEDULE_END_DATE =:LV_MAX_SCHEDULE_DATE :: timestamp,
	MODIFY_USER_ID = :modifyUserId ,
	MODIFY_DATETIME = CURRENT_TIMESTAMP
where
	CRS_ACTY_ID = :CRS_ACTY_ID
 }
 
 CS_BUILD_ACP_SCHEDULE_GET_SCHEDULE_START_DATE_AND_SCHEDULE_END_DATE_ONE {
 	SELECT SCHEDULE_START_DATE,SCHEDULE_END_DATE FROM COURSE_ACTIVITIES  WHERE CRS_ACTY_ID =:CRS_ACTY_ID 
 }
 
 CS_BUILD_ACP_SCHEDULE_ALLOC_CUR {
 	select
OPP.OFF_PRGREF_ID OFF_PRGREF_ID,
OPP.OFFENDER_START_DATE OFFENDER_START_DATE,
OPP.START_SESSION_NO START_SESSION_NO,
OPP.OFFENDER_END_DATE OFFENDER_END_DATE,
PS.MODULE_FLAG SEAL_FLAG
from
OFFENDER_PROGRAM_PROFILES OPP,
PROGRAM_SERVICES PS
where
OPP.CRS_ACTY_ID = :P_CRS_ACTY_ID
and OPP.PROFILE_CLASS = 'CRS'
and OPP.OFFENDER_PROGRAM_STATUS = 'ALLOC'
and OPP.PROGRAM_ID = PS.PROGRAM_ID for
update
of OPP nowait
 
 }
 
 CS_BUILD_ACP_SCHEDULE_SCH_CUR_ONE {
 	select
	SCHEDULE_DATE
from
	COURSE_SCHEDULES
where
	SCHEDULE_STATUS = 'SCH'
	and coalesce(CATCH_UP_CRS_SCH_ID::text, '') = ''
	and SESSION_NO = :P_SESSION_NUMBER
	and CRS_ACTY_ID in (with recursive cte as (
	select
		CRS_ACTY_ID
	from
		COURSE_ACTIVITIES
	where
		CRS_ACTY_ID = :P_CRS_ACTY_ID
union all
	select
		ca.CRS_ACTY_ID
	from
		COURSE_ACTIVITIES ca
	join cte c on
		(c.CRS_ACTY_ID = PARENT_CRS_ACTY_ID ) )
	select
		*
	from
		cte)
 
 }
 
 CS_BUILD_ACP_SCHEDULE_END_CUR {
 	 SELECT MAX (COALESCE (OFFENDER_END_DATE, :LV_BIG_DATE))
           FROM OFFENDER_PROGRAM_PROFILES
          WHERE PARENT_OFF_PRGREF_ID = :P_OFF_PRGREF_ID
            AND PROFILE_CLASS = 'CRS'
            AND OFFENDER_PROGRAM_STATUS = 'ALLOC'
 }
 
  CS_BUILD_ACP_SCHEDULE_UPDATE_OFFENDER_PROGRAM_PROFILES {
	UPDATE OFFENDER_PROGRAM_PROFILES
	               SET OFFENDER_START_DATE = :LV_OFFENDER_START_DATE,
	                   OFFENDER_END_DATE = :LV_OFFENDER_END_DATE WHERE OFF_PRGREF_ID=:OFF_PRGREF_ID
 }
 
 UPDATE_COURSE_SCHEDULES {
 	
 	 UPDATE course_schedules
            SET schedule_date = :scheduleDate,
                start_time = :startTime,
                end_time = :endTime,
                modify_datetime =current_timestamp,
                modify_user_id =:modifyUserId
          WHERE crs_sch_id = :crsSchId
 
 }
 COURSE_SCHEDULE_DELETE {
 DELETE FROM course_schedules WHERE crs_acty_id = :crsActyId
              AND ( schedule_date > :lv_start_date OR ( schedule_date = :lv_start_date AND start_time >= :lv_start_time ) )
                         
}
 
