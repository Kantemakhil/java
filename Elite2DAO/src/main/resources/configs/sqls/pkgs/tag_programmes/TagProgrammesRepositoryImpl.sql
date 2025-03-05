SELECT_OFF_COURSE_ATTEND{
SELECT  crs_sch_id FROM offender_course_attendances WHERE  crs_sch_id =:p_crs_sch_id AND event_outcome IS NULL 
 }
 
UPDATE_OFF_COURSE_ATTEND{
update offender_course_attendances set event_outcome = 'CANC', comment_text = 'Session cancelled by provider', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where crs_sch_id =:p_crs_sch_id
}
UPDATE_COURSE_ATTENDANCE_DATES_OCA_CUR {
	  select
	CRS_SCH_ID
from
	OFFENDER_COURSE_ATTENDANCES
where
	CRS_SCH_ID = :P_CRS_SCH_ID
	and EVENT_OUTCOME is null
         for
update
	nowait;
}

UPDATE_COURSE_ATTENDANCE_DATES_UPDATE_OFFENDER_COURSE_ATTENDANCES {
update offender_course_attendances set event_date = :p_event_date, start_time = :p_start_time, end_time = :p_end_time, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where crs_sch_id =:crs_sch_id

}

UPDATE_STATUS_GET_OFF_PRG_OBLI{
SELECT  'X'
            FROM offender_prg_obligations
           WHERE offender_prg_obligation_id = :P_OFFENDER_PRG_OBLIGATION_ID
 }     
 
 UPDATE_STATUS_UPDATE_OFF_PRG_OBLI{
 update offender_prg_obligations set status = :P_STATUS, status_change_reason = :P_REASON, status_change_date = :P_DATE, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_prg_obligation_id = :P_OFFENDER_PRG_OBLIGATION_ID
 }  
 INSERT_OFFENDER_COURSE_ATTENDANCE{
 insert into OFFENDER_COURSE_ATTENDANCES (EVENT_ID, OFFENDER_BOOK_ID, EVENT_DATE, START_TIME, END_TIME, EVENT_SUB_TYPE, EVENT_STATUS, TO_INTERNAL_LOCATION_ID, CRS_SCH_ID, OFF_PRGREF_ID, IN_TIME, OUT_TIME, REFERENCE_ID, CRS_ACTY_ID, AGY_LOC_ID, EVENT_CLASS, SESSION_NO, EVENT_TYPE, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values(:eventId, :offenderBookings, :scheduleDate, :startTime, :endTime, :eventSubType, :eventStatus, :toInternalLocationId, :crsSchId, :offPrgrefId, :startTime, :endTime, :referencesId, :crsActyId, :agyLocId, :eventClass, :sessionNo, :eventType, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
}
SELECT_OFFENDER_COURSE_ATTENDANCE{
SELECT EVENT_ID.NEXTVAL, OFFENDER_BOOK_ID,SCHEDULE_DATE, START_TIME,END_TIME, EVENT_SUB_TYPE, EVENT_STATUS, TO_INTERNAL_LOCATION_ID,NEW_CRS_SCH_ID, OFF_PRGREF_ID, START_TIME, END_TIME, REFERENCE_ID, CRS_ACTY_ID, AGY_LOC_ID, EVENT_CLASS, SESSION_NO, EVENT_TYPE FROM OFFENDER_COURSE_ATTENDANCES WHERE CRS_SCH_ID = P_OLD_CRS_SCH_ID AND OFFENDER_BOOK_ID = P_OFFENDER_BOOK_ID
}

GET_PGM_CUR{

with recursive cte as (
select
	ps.program_id,
	opp.parent_off_prgref_id,
	ps.program_class,
	ps.description,
	opp.off_prgref_id,
	opp.profile_class
from
	offender_program_profiles opp,
	program_services ps
where
	opp.program_id = ps.program_id
	and
	opp.off_prgref_id = :p_off_prgref_id
union all
select
	ps.program_id,
	opp.parent_off_prgref_id,
	ps.program_class,
	ps.description,
	opp.off_prgref_id,
	opp.profile_class
from
	offender_program_profiles opp
join 
	program_services ps on
	(opp.program_id = ps.program_id)
join cte c on
	(c.parent_off_prgref_id = opp.off_prgref_id)

)
select
	*
from
	cte c
where
	c.off_prgref_id = :p_off_prgref_id
	and c.profile_class = 'PRG'
	and c.program_class in ( 'PRG_MOD', 'PRG_PH' )
}

GET_PHASE_CUR{
SELECT ps.program_id, ps.description FROM program_services ps, offender_program_profiles opp WHERE opp.off_prgref_id = :v_parent_off_prgref_id AND ps.program_id = opp.program_id AND opp.profile_class = 'PRG'
}


UPDATE_CREATE_OFF_WR_RETURN_SCHEDULE{
UPDATE v_offender_course_events SET direction_code = 'IN' WHERE event_id = :p_event_id
}


LOCK_OFF_PRG_OBLIGATION_ID{
 SELECT OPO.MODIFY_DATETIME, OPO.CREATE_DATETIME FROM OFFENDER_PRG_OBLIGATIONS OPO WHERE OPO.OFFENDER_PRG_OBLIGATION_ID = :P_OFFENDER_PRG_OBLIGATION_ID FOR UPDATE NOWAIT
}

GET_PRG_PROFILE{
SELECT off_prgref_id FROM offender_program_profiles WHERE offender_prg_obligation_id = :pOffenderPrgObligationId AND program_id = :lvProgramId AND profile_class = 'PRG'
}



UPDATE_STATUS_SELECT{
SELECT 'X' FROM offender_prg_obligations WHERE offender_prg_obligation_id = :pOffenderPrgObligationId 
}

UPDATE_STATUS_UP_OPERATION{
update offender_prg_obligations set status = 'ALLOC', status_change_reason = null, status_change_date = CURRENT_TIMESTAMP, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_prg_obligation_id = :pOffenderPrgObligationId
}


GET_RET_OFF_PRG_REF_ID{
select NEXTVAL('off_prgref_id') from dual
}

INSERT_OFF_PROGRAM_PROFILES{
insert into OFFENDER_PROGRAM_PROFILES ( OFF_PRGREF_ID, OFFENDER_BOOK_ID, PROGRAM_ID, CRS_ACTY_ID, OFFENDER_PRG_OBLIGATION_ID, PARENT_OFF_PRGREF_ID, PROGRAM_OFF_PRGREF_ID, OFFENDER_START_DATE, START_SESSION_NO, OFFENDER_END_DATE, OFFENDER_PROGRAM_STATUS, PROFILE_CLASS , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( :RETOFFPRGREFID, :P_OFFENDER_BOOK_ID, :LV_PROGRAM_ID, :P_CRS_ACTY_ID, :P_OFFENDER_PRG_OBLIGATION_ID, :P_PARENT_OFF_PRGREF_ID, :LV_PROGRAM_OFF_PRGREF_ID, :LV_SCHEDULE_START_DATE, :LV_START_SESSION_NO, :LV_SCHEDULE_END_DATE, 'ALLOC', 'CRS' , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
}

INSERT_CREATE_OPP_ATTENDANCES{
insert
	into
	offender_course_attendances (event_id,
	offender_book_id,
	event_class,
	event_type,
	event_sub_type,
	off_prgref_id,
	reference_id,
	crs_sch_id,
	crs_acty_id,
	event_date,
	start_time,
	end_time,
	in_time,
	out_time,
	event_status,
	event_outcome,
	agy_loc_id,
	to_internal_location_id,
	to_address_id,
	to_agy_loc_id,
	session_no,
	offender_prg_obligation_id,
	program_id,
	CREATE_USER_ID,
	CREATE_DATETIME,
	MODIFY_DATETIME )
select
	NEXTVAL('event_id'),
	opp.offender_book_id,
	tag_prg_prg_event_class(opp.off_prgref_id),
	'ACP' event_type,
	ca.course_activity_type,
	opp.off_prgref_id,
	cs.crs_sch_id,
	cs.crs_sch_id,
	cs.crs_acty_id,
	cs.schedule_date,
	start_time,
	end_time,
	start_time,
	end_time,
	case
		sign(schedule_date::date - CURRENT_TIMESTAMP::date)
         when -1 then
        'EXP'
		else 'SCH'
	end,
	null,
	opp.agy_loc_id,
	ca.internal_location_id,
	ca.services_address_id,
	addr.owner_code,
	cs.session_no,
	opp.offender_prg_obligation_id,
	opp.program_id,
	:createUserId,
	CURRENT_TIMESTAMP ,
	CURRENT_TIMESTAMP
from
	offender_program_profiles opp,
	course_schedules cs,
	course_activities ca
left outer join addresses addr on
	(ca.services_address_id = addr.address_id)
where
	opp.off_prgref_id = :p_off_prgref_id
	and opp.crs_acty_id = cs.crs_acty_id
	and ca.crs_acty_id = opp.crs_acty_id
	and (ca.course_activity_type is not null
		and ca.course_activity_type::text <> '')
	and opp.offender_program_status = 'ALLOC'
	and ca.active_flag = 'Y'
	and coalesce(cs.catch_up_crs_sch_id::text, '') = ''
	and cs.session_no >= opp.start_session_no
	and opp.offender_start_date::date <= cs.schedule_date::date
	and coalesce(coalesce(opp.offender_end_date,
        ca.schedule_end_date),
        cs.schedule_date ) >= cs.schedule_date
	and not exists (
	select
		'x'
	from
		offender_course_attendances oca
	where
		oca.offender_book_id = opp.offender_book_id
		and oca.crs_sch_id = cs.crs_sch_id
		and oca.event_date = cs.schedule_date)

}

GET_MOD_CUR{
SELECT crs_acty_id FROM course_activities WHERE parent_crs_acty_id = :pPhaseInstanceId
}

CS_BUILD_ACP_SCHEDULE_ALLOC_CUR {
 SELECT OPP.OFF_PRGREF_ID OFF_PRGREF_ID, OPP.OFFENDER_START_DATE OFFENDER_START_DATE, OPP.START_SESSION_NO START_SESSION_NO, OPP.OFFENDER_END_DATE OFFENDER_END_DATE, PS.MODULE_FLAG SEAL_FLAG FROM OFFENDER_PROGRAM_PROFILES OPP, PROGRAM_SERVICES PS WHERE OPP.CRS_ACTY_ID = :P_CRS_ACTY_ID AND OPP.PROFILE_CLASS = 'CRS' AND OPP.OFFENDER_PROGRAM_STATUS = 'ALLOC' AND OPP.PROGRAM_ID = PS.PROGRAM_ID FOR UPDATE OF OPP NOWAIT 
}

CS_BUILD_ACP_SCHEDULE_END_CUR {
 SELECT MAX (NVL (OFFENDER_END_DATE, :LV_BIG_DATE)) FROM OFFENDER_PROGRAM_PROFILES WHERE PARENT_OFF_PRGREF_ID = :P_OFF_PRGREF_ID AND PROFILE_CLASS = 'CRS' AND OFFENDER_PROGRAM_STATUS = 'ALLOC' 
}
 
  CS_BUILD_ACP_SCHEDULE_UPDATE_OFFENDER_PROGRAM_PROFILES {
 update OFFENDER_PROGRAM_PROFILES set OFFENDER_START_DATE = :LV_OFFENDER_START_DATE, OFFENDER_END_DATE = :LV_OFFENDER_END_DATE, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where OFF_PRGREF_ID =:OFF_PRGREF_ID
  }

GET_CRS_SCH_ID{
SELECT nextval('crs_sch_id') FROM DUAL
}  

INSERT_COURSE_SCHEDULES{
insert into course_schedules ( crs_sch_id, crs_acty_id, schedule_date, start_time, end_time, session_no, catch_up_crs_sch_id , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) select :v_crs_sch_id, cs.crs_acty_id, :p_schedule_date, :p_start_time, :p_end_time, cs.session_no, :p_catch_up_crs_sch_id, :createUserId, CURRENT_TIMESTAMP , null from course_schedules cs where cs.crs_sch_id = :p_catch_up_crs_sch_id
}

GET_OFFENDER_DATES{
SELECT booking_begin_date FROM offender_bookings WHERE offender_book_id =:p_offender_book_id
}

GET_COURSE_SCHEDULE_REC{
SELECT * FROM course_schedules  WHERE crs_sch_id =:P_CRS_SCH_ID
}

GET_OFF_COURSE_ATTEND_CHECKSUM{
SELECT TO_NUMBER (TO_CHAR (NVL (oca.modify_datetime, oca.create_datetime), 'YYYYMMDDHH24MISSFF4' ) FROM offender_course_attendances oca WHERE oca.event_id =:p_event_id
}
TAG_PROGRAMMES_OFF_PRG_PROF_CUR{
SELECT DISTINCT OFFENDER_PRG_OBLIGATION_ID    FROM OFFENDER_PRG_OBLIGATIONS       WHERE OFFENDER_BOOK_ID = :offenderBookId  AND EVENT_TYPE = 'WR'
}
TAG_PROGRAMMES_OFFENDER_PROGRAM_PROFILES_UPDATE{
update
	OFFENDER_PROGRAM_PROFILES
set
	OFFENDER_PROGRAM_STATUS = 'END',
	OFFENDER_END_REASON = 'REL',
	OFFENDER_END_DATE = current_timestamp,
	modify_datetime =current_timestamp ,
	modify_user_id =:modifyUserId
where
	OFFENDER_BOOK_ID = :offenderBookId
	and OFFENDER_PRG_OBLIGATION_ID = :offenPrgObligaId
	and OFFENDER_PROGRAM_STATUS = 'ALLOC'
}
TAG_PROGRAMMES_OFFENDER_PRG_OBLIGATIONS_UPDATE{
UPDATE OFFENDER_PRG_OBLIGATIONS SET STATUS = 'END',END_DATE =current_timestamp , STATUS_CHANGE_DATE =current_timestamp  WHERE OFFENDER_BOOK_ID = :offenderBookId AND EVENT_TYPE = 'WR'

}

GET_OLD_RECORD_FOR_UPDATE_STATUS{
SELECT offender_prg_obligation_id,	offender_book_id	,program_id	,status	,offender_sent_cond_act_id	,start_date	,end_date	,event_type	,event_sub_type,	comment_text,	sentence_seq,	length	,length_unit,	offender_sent_condition_id,	referral_date	,status_change_date	,status_change_reason,	special_need_flag,	availability_code	,obligation_source,	create_datetime	,create_user_id	,modify_datetime,	modify_user_id	,seal_flag,	referral_priority	,decision_date FROM OFFENDER_PRG_OBLIGATIONS WHERE OFFENDER_PRG_OBLIGATION_ID = :OFFENDER_PRG_OBLIGATION_ID
}

INSERT_OFFENDER_COURSE_ATTENDANCES_FOR_CATCH_UP{
insert into offender_course_attendances (event_id, offender_book_id, event_date, start_time, end_time, event_sub_type, event_status, to_internal_location_id, crs_sch_id, off_prgref_id, in_time, out_time, reference_id, crs_acty_id, agy_loc_id, event_class, session_no, event_type, create_user_id, create_datetime, modify_datetime) select NEXTVAL('event_id'), offender_book_id, :P_SCHEDULE_DATE, :P_START_TIME, :P_END_TIME, event_sub_type, event_status, to_internal_location_id, :P_NEW_CRS_SCH_ID, off_prgref_id, :P_START_TIME, :P_END_TIME, reference_id, crs_acty_id, agy_loc_id, event_class, session_no, event_type, :createUserId, current_timestamp, null from offender_course_attendances where crs_sch_id = :P_OLD_CRS_SCH_ID and offender_book_id = :P_OFFENDER_BOOK_ID
}

GET_LIST_SEQ_RANGE{
select coalesce (MIN (list_seq), 0) as module_from, coalesce (MAX (list_seq), 0) as module_to from course_activities where parent_crs_acty_id = :P_CRS_ACTY_ID
}

PGM_CUR_LIST{
with recursive cte as ( select program_id program_id, parent_program_id parent_program_id from program_services where program_id = :P_PROGRAM_ID union all select ps.program_id program_id, ps.parent_program_id parent_program_id from program_services ps join cte c on (c.program_id = ps.parent_program_id)) select program_id, parent_program_id, NEXTVAL('off_prgref_id') off_prgref_id from cte
}

 INSERT_OFFENDER_PROGRAM_PROFILES{
insert into offender_program_profiles (off_prgref_id, program_id, offender_prg_obligation_id, offender_book_id, parent_off_prgref_id , create_user_id, create_datetime, modify_datetime ) values (:offPrgrefId, :programId, :offenderPrgObligationId, :offenderBookId, :parentOffPrgrefId , :createUserId, current_timestamp, null )
 }
 
 CHECK_ALLOCATION_EXISTS{
select
	count(*)
from
	offender_program_profiles
where
	offender_prg_obligation_id = :p_offender_prg_obligation_id
	and crs_acty_id = :p_crs_acty_id
	and (  coalesce(:p_parent_off_prgref_id, 0) = 0
		or (  
		coalesce (:p_parent_off_prgref_id,0) != 0
			and parent_off_prgref_id = :p_parent_off_prgref_id ) )
	and offender_program_status = 'ALLOC'

 }
 
 CHECK_ATTENDANCE_OUTCOMES{
 select count(*) from offender_course_attendances where off_prgref_id = :p_off_prgref_id and event_outcome is null
 }
 
 CHECK_ATTENDANCE_TAKEN{
 select 'Y' where exists (( select 'Y' from offender_course_attendances where off_prgref_id in (with recursive cte as ( select off_prgref_id from offender_program_profiles where off_prgref_id = :p_off_prgref_id union all select opp.off_prgref_id from offender_program_profiles opp join cte c on (c.off_prgref_id = parent_off_prgref_id) ) select * from cte ) and coalesce(event_outcome, 'CANC') != 'CANC' and event_date > :p_end_date)) 
 }
 
 UPDATE_CANCEL_MODULE_ALLOCATIONS{
  update offender_program_profiles set offender_program_status = 'CANC', modify_user_id = :modifyUserId, modify_datetime = current_timestamp where parent_off_prgref_id = :p_parent_off_prgref_id and profile_class = 'CRS'
 }
 
 UPDATE_END_MODULE_ALLOCATIONS{
  update offender_program_profiles set offender_program_status = 'END', offender_end_date = least (:p_end_date, coalesce (offender_end_date, :lv_big_date)),	modify_user_id =:modifyUserId,modify_datetime = current_timestamp  where parent_off_prgref_id = :p_parent_off_prgref_id and profile_class = 'CRS'
 }
 
 GET_ALLOCATION_LIST_SEQ_RANGE{
 select coalesce (MIN (ca.list_seq), 0) as module_from, coalesce (MAX (ca.list_seq), 0) as module_to from offender_program_profiles opp, course_activities ca where opp.parent_off_prgref_id = :p_parent_off_prgref_id and opp.crs_acty_id = ca.crs_acty_id and opp.profile_class = 'CRS'
 }
 
 CHECK_SESSION_ALLOCATED{
select count(*) from DUAL where exists ( select 'Y' from offender_course_attendances where offender_book_id = :p_offender_book_id and crs_sch_id in ( select cs.crs_sch_id from course_schedules cs, offender_program_profiles opp, course_activities ca where opp.offender_book_id = :p_offender_book_id and opp.crs_acty_id = :p_crs_acty_id and opp.crs_acty_id = cs.crs_acty_id and ca.crs_acty_id = opp.crs_acty_id and ca.course_activity_type is not null and opp.offender_program_status != 'CANC' and ca.active_flag = 'Y' and cs.catch_up_crs_sch_id is null and cs.session_no >= :p_start_session_no and :p_offender_start_date <= cs.schedule_date and coalesce (coalesce (:p_offender_end_date, ca.schedule_end_date ), cs.schedule_date ) >= cs.schedule_date)) 
 }
 
 CHECK_MODULE_ALLOCATED{
 select count(*) from DUAL where exists ( select 'Y' from offender_course_attendances where offender_book_id = :p_offender_book_id and crs_sch_id in ( select cs.crs_sch_id from course_schedules cs, offender_program_profiles opp, course_activities ca where opp.offender_book_id = :p_offender_book_id and opp.crs_acty_id in ( select crs_acty_id from course_activities where parent_crs_acty_id = :p_crs_acty_id and list_seq between :p_module_from and :p_module_to) and opp.crs_acty_id = cs.crs_acty_id and ca.crs_acty_id = opp.crs_acty_id and ca.course_activity_type is not null and opp.offender_program_status != 'CANC' and ca.active_flag = 'Y' and cs.catch_up_crs_sch_id is null))
 }
 
 VALID_ALLOCATION_END_DATE{
 select count(*) from offender_course_attendances where off_prgref_id = :p_off_prgref_id and case when  coalesce(event_outcome,'N') = 'N' then  event_outcome is null  else event_outcome <> 'CANC' end and event_date between date_trunc('day',TIMESTAMP :p_end_date) and date_trunc('day',current_date)
 }
 
 ATT_CUR{
 select * from offender_course_attendances where off_prgref_id in (with recursive cte as ( select off_prgref_id from offender_program_profiles where off_prgref_id = :p_off_prgref_id union all select opp.off_prgref_id from offender_program_profiles opp join cte c on (c.off_prgref_id = parent_off_prgref_id) ) select * from cte ) and event_date > coalesce(:p_end_date, TO_DATE('01/01/1900', 'DD/MM/YYYY')) for update
 }
 
 DELETE_OFFENDER_COURSE_ATTENDANCES{
 DELETE FROM offender_course_attendances WHERE event_id = :eventId
 }
 
 CRS_CUR{
 select crs_acty_id, list_seq from course_activities where parent_crs_acty_id = :p_phase_crs_acty_id
 }
 
 OFFENDER_COURSE_ATTENDANCES_DELETE{
 delete from offender_course_attendances where off_prgref_id in ( select off_prgref_id from offender_program_profiles where offender_prg_obligation_id = :p_offender_prg_obligation_id and crs_acty_id = :p_crs_acty_id and parent_off_prgref_id = :p_parent_off_prgref_id)
 }
 
 OFFENDER_PROGRAM_PROFILES_DELETE{
 delete from offender_program_profiles where offender_prg_obligation_id = :p_offender_prg_obligation_id and crs_acty_id = :p_crs_acty_id and parent_off_prgref_id = :p_parent_off_prgref_id
 }
 
 GET_OLD_REC_OFFENDER_PRG_OBLIGATIONS{
select offender_prg_obligation_id, offender_book_id, program_id , status , offender_sent_cond_act_id, start_date, end_date, event_type, event_sub_type, comment_text, sentence_seq, length, length_unit, offender_sent_condition_id, referral_date, status_change_date, status_change_reason, special_need_flag, availability_code, obligation_source, create_datetime, create_user_id, modify_datetime , modify_user_id, seal_flag, referral_priority, decision_date from offender_prg_obligations where offender_prg_obligation_id = :offenderPrgObligationId
}
