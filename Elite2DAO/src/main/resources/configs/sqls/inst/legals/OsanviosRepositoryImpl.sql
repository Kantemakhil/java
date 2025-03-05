OSANVIOS_HEARING_REASON_RECORDGROUP {
SELECT ACTIVE_FLAG,MOVEMENT_TYPE,MOVEMENT_REASON_CODE CODE ,DESCRIPTION FROM MOVEMENT_REASONS WHERE MOVEMENT_TYPE='CRT'
}
OSANVIOS_COURT_EVENTS_COURT_DATA {
SELECT   al.description,
         al.agy_loc_id
    FROM agency_locations al
   WHERE agency_location_type = 'CRT'
     AND (active_flag = 'Y' AND deactivation_date IS NULL)
     AND al.agy_loc_id NOT IN ('TRN', 'OUT')
ORDER BY list_seq,description
}

OSANVIOS_CRTEVE_FIND_COURT_EVENTS {
 	SELECT * FROM COURT_EVENTS where offender_book_id=:offender_book_id AND sentence_seq=:sentenseSeq AND ORDER_TYPE=:orderType ORDER BY AGY_LOC_ID , START_TIME 
}

OSANVIOS_CRT_EVE_PREINSERT {
	SELECT NEXTVAL('EVENT_ID') FROM DUAL
}

OSANVIOS_CRTEVE_UPDATE_COURT_EVENTS {
update COURT_EVENTS set EVENT_ID = :eventId , OFFENDER_BOOK_ID = :offenderBookId , EVENT_DATE = :eventDate , START_TIME = :startTime , END_TIME = :endTime, 
JUDGE_NAME = :judgeName , EVENT_STATUS = :eventStatus , PARENT_EVENT_ID = :parentEventId , AGY_LOC_ID = :court , OUTCOME_REASON_CODE = :outcomeReasonCode ,
COMMENT_TEXT = :commentText , EVENT_OUTCOME = :eventOutcome , NEXT_EVENT_REQUEST_FLAG = :nextEventRequestFlag , ORDER_REQUESTED_FLAG = :orderRequestedFlag , 
RESULT_CODE = :resultCode , NEXT_EVENT_DATE = :nextEventDate , NEXT_EVENT_START_TIME = :nextEventStartTime , OUTCOME_DATE = :outcomeDate , 
OFFENDER_PROCEEDING_ID = null , DIRECTION_CODE = :directionCode , HOLD_FLAG = :holdFlag , SEAL_FLAG = :sealFlag , SCHEDULED_TRIP_ID = null , MATTER = :matter,
APPEARANCE_LOCATION = :appearanceLocation , APPEARANCE_TYPE = :appearanceType , COURT_EVENT_TYPE = :hearingReason, modify_user_id =:modifyUserId,
modify_datetime = current_timestamp, sentence_seq =:sentenseSeq, ORDER_TYPE =:orderType, recommended_sanction_count =:recommendedSanctionCount, 
recommended_reward_count =:recommendedRewardCount, additional_counts_comment =:additionalCountsComment, 
email_flag=:emailFlag, sms_flag=:smsFlag, email_schedule_hours_before=:emailScheduleHoursBefore, sms_schedule_hours_before=:smsScheduleHoursBefore where EVENT_ID = :eventId
}

OSANVIOS_CRTEVE_INSERT_COURT_EVENTS_PRE_EVENT_ID {
insert into COURT_EVENTS(EVENT_ID , CASE_ID , OFFENDER_BOOK_ID , EVENT_DATE , START_TIME , END_TIME , COURT_EVENT_TYPE , JUDGE_NAME , EVENT_STATUS , 
PARENT_EVENT_ID , AGY_LOC_ID , OUTCOME_REASON_CODE , COMMENT_TEXT , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , EVENT_OUTCOME , NEXT_EVENT_REQUEST_FLAG , 
ORDER_REQUESTED_FLAG , RESULT_CODE , NEXT_EVENT_DATE , NEXT_EVENT_START_TIME , OUTCOME_DATE , OFFENDER_PROCEEDING_ID , DIRECTION_CODE , HOLD_FLAG , SEAL_FLAG ,
SCHEDULED_TRIP_ID, APPEARANCE_LOCATION, APPEARANCE_TYPE, MATTER, sentence_seq, ORDER_TYPE, recommended_sanction_count, recommended_reward_count, 
additional_counts_comment, email_flag, sms_flag, email_schedule_hours_before, sms_schedule_hours_before ) values
(:eventId, null , :offenderBookId , :eventDate , :startTime , :endTime , :hearingReason , :judgeName , :eventStatus , :parentEventId , :court ,
:outcomeReasonCode , :commentText , current_timestamp , :createUserId , null , :eventOutcome , :nextEventRequestFlag , :orderRequestedFlag , :resultCode , 
:nextEventDate , :nextEventStartTime , :outcomeDate , null , :directionCode , :holdFlag , :sealFlag , null, :appearanceLocation, :appearanceType, :matter, 
:sentenseSeq, :orderType, :recommendedSanctionCount, :recommendedRewardCount, :additionalCountsComment,:emailFlag, :smsFlag, :emailScheduleHoursBefore, 
:smsScheduleHoursBefore )
}

OSANVIOS_GET_SENTENCE_DATA {
select FORM_INFO_JSON_BLOB, FORM_IDENTIFIER from ( select FORM_INFO_JSON as FORM_INFO_JSON_BLOB, SUBSTR(FORM_IDENTIFIER, 20, (INSTR(FORM_IDENTIFIER, '"', 1, 4)) -(INSTR(FORM_IDENTIFIER, '"', 1, 3) + 1)) as FORM_IDENTIFIER from OCDLEGLO_DATA) a where FORM_IDENTIFIER::bigint =:offenderBookId}

OSANVIOS_GET_APPOINTMENTS_DATA {
 select * from(
select 
(select court_event_id from court_evnt_sanctions_details where record_event_id = va.event_id and link_flag = 'Y') as court_event_id,
va.* ,
 (select court_evnt_sanct_dtl_id from court_evnt_sanctions_details where record_event_id = va.event_id) as court_evnt_sanct_dtl_id,
 case when (select count(*) from court_evnt_sanctions_details where record_event_id = va.event_id) > 0 then 'Y' else 'N' end as court_event_avail,
 case when (select count(*) from court_evnt_sanctions_details where record_event_id = va.event_id and link_flag = 'Y') > 0 then 'Y' else 'N' end as court_event_linked 
 from v_offender_all_schedules_2 va, event_measures em where em.sanctions_flag ='Y' and va.event_type = em.event_type and va.event_sub_type = em.event_sub_type 
 and va.event_outcome is not null and va.event_date::date <= :courtEventDate and va.offender_book_id =:offenderBookId
 and  va.event_id  in( select event_id from offender_ind_schedules ois where offender_book_id =:offenderBookId))a
	where  court_event_id is null or court_event_id =:court_event_id order by event_date
 }

OSANVIOS_CRT_EVE_APPOINT_PREINSERT {
	SELECT NEXTVAL('court_evnt_sanct_dtl_id_seq') FROM DUAL
}

OSANVIOS_CRTEVE_INSERT_COURT_EVENTS_APPOINTMENTS_DATA {
insert into court_evnt_sanctions_details(court_evnt_sanct_dtl_id , COURT_EVENT_ID , record_event_id, RECORD_SANCTION_REWARD_COUNT, COUNT_TYPE, adjourned_flag, COMMENT_TEXT, CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , modify_user_id, LINK_FLAG,RECORD_TYPE ) 
values(:courtEvntSanctDtlId, :courtEventId, :sessionEventId , :recordSanctionRewardCount, :countType, :adjournedFlag, :commentText, current_timestamp, :createUserId , NULL, NULL, :linkFlag ,:recordType) 
}

OSANVIOS_CRTEVE_UPDATE_COURT_EVENTS_APPOINTMENTS_DATA {
update court_evnt_sanctions_details set COURT_EVENT_ID =:courtEventId , RECORD_SANCTION_REWARD_COUNT =:recordSanctionRewardCount, COUNT_TYPE =:countType, adjourned_flag =:adjournedFlag, COMMENT_TEXT =:commentText, LINK_FLAG =:linkFlag, modify_datetime=current_timestamp , modify_user_id =:modifyUserId where court_evnt_sanct_dtl_id =:courtEvntSanctDtlId 
}

OSANVIOS_COURT_EVENT_OUTCOME_APPOINTMENT_EVENT_DATA {
select * from court_evnt_sanctions_details where record_event_id =:sessionEventId
}

OSANVIOS_GET_DEFAULT_CANCELATION_REASON
{
select code from reference_codes where domain ='CRT_CAN_RSN' and list_seq =1 and active_flag ='Y'
}
OSANVIOS_COUNT_OUTCOME_REASON
{
select count(*) from 
(select
	case
		when (
		select
			count(*)
		from
			court_evnt_sanctions_details
		where
			record_event_id = va.event_id
			and link_flag = 'Y') > 0 then 'Y'
		else 'N'
	end as court_event_linked
from
	v_offender_all_schedules_2 va,
	event_measures em
where
	em.sanctions_flag = 'Y'
	and va.event_type = em.event_type
	and va.event_sub_type = em.event_sub_type
	and va.event_outcome is not null
	and va.event_date::date <= :courtEventDate
	and va.offender_book_id =:offenderBookId) a where a.court_event_linked='Y'
}

OSANVIOS_COUNT_COURT_EVENT_LINK_DATA {
select count(*) from court_evnt_sanctions_details where court_event_id = :eventId and link_flag='Y'
}

OSANVIOS_PROGRAM_OUTCOME_DATA {

select * from(
select va.* ,
(select court_event_id from court_evnt_sanctions_details where record_event_id = va.event_id  and link_flag = 'Y') as court_event_id,
(select court_evnt_sanct_dtl_id from court_evnt_sanctions_details where record_event_id = va.event_id) as court_evnt_sanct_dtl_id,
(select description from program_services ps where program_id in 
(select program_id from v_offender_prg_obligations vopo where offender_prg_obligation_id =opp.offender_prg_obligation_id)
) programDesc,
(SELECT description FROM COURSE_ACTIVITIES WHERE crs_acty_id in (SELECT parent_crs_acty_id FROM COURSE_ACTIVITIES WHERE crs_acty_id = va.crs_acty_id)) activityDesc
from V_offender_course_attendances va, event_measures em,OFFENDER_PROGRAM_PROFILES opp where 
em.sanctions_flag ='Y' and
va.event_type = em.event_type and va.event_sub_type = em.event_sub_type 
and va.event_outcome is not null 
and va.event_date::date <= :courtEventDate 
and va.offender_book_id =:offenderBookId and opp.off_prgref_id  = va.off_prgref_id and opp.profile_class ='CRS' and opp.offender_program_status != 'CANC')a
	where  court_event_id is null or court_event_id =:court_event_id order by event_date

}
OSANVIOS_PROGRAM_APPOINTMENTS_DATA {
select * from(
select 
(select court_event_id from court_evnt_sanctions_details where record_event_id = va.event_id  and link_flag = 'Y') as court_event_id,
va.* ,(select court_evnt_sanct_dtl_id from court_evnt_sanctions_details where record_event_id = va.event_id) as court_evnt_sanct_dtl_id,
 TAG_REFERENCE_CODES_GETDESCCODE ( 'EVENTS' ,va.event_sub_type )  programDesc
from V_offender_course_attendances va, event_measures em where 
va.event_outcome <> '' and em.sanctions_flag ='Y' and
va.event_type = em.event_type and va.event_sub_type = em.event_sub_type 
and va.event_date::date <= :courtEventDate 
and va.EVENT_ID IN(select EVENT_ID FROM OFFENDER_COURSE_ATTENDANCES off_crs_att
where OFFENDER_PRG_OBLIGATION_ID in (SELECT OFFENDER_PRG_OBLIGATION_ID FROM V_OFFENDER_PRG_OBLIGATIONS VOPO WHERE OFFENDER_BOOK_ID = :offenderBookId AND EVENT_TYPE = 'ACP')
	and CRS_ACTY_ID is null))a where  court_event_id is null or court_event_id =:court_event_id order by event_date
}

OSANVIOS_COMMUNITY_SERVICE_DATA {
  select * from (
select 
(select court_event_id from court_evnt_sanctions_details where record_event_id = va.event_id  and link_flag = 'Y') as court_event_id,
	va.event_id ,va.offender_book_id ,va.start_time ,va.end_time ,va.description as programDesc ,va.event_outcome ,va.event_date ,
	(select court_evnt_sanct_dtl_id from court_evnt_sanctions_details where record_event_id = va.event_id) as court_evnt_sanct_dtl_id
from
	V_OFFENDER_COURSE_EVENTS va
where
	OFFENDER_BOOK_ID =:offenderBookId and va.event_outcome is not null and va.event_date::date <= :courtEventDate
	and OFF_PRGREF_ID in( SELECT off_prgref_id
FROM  offender_program_profiles opp, offender_sent_conditions osc
WHERE opp.offender_book_id = :offenderBookId AND opp.sentence_seq = osc.sentence_seq AND opp.OFFENDER_SENT_CONDITION_ID = osc.offender_sent_condition_id 
 and opp.offender_book_id  = osc.offender_book_id and osc.comm_condition_code IN(SELECT comm_condition_code FROM COMMUNITY_CONDITIONS WHERE program_method  = 'UW')
     ) AND EVENT_STATUS IN ('SCH','EXP','COMP','CANC')) a where  court_event_id is null or court_event_id =:court_event_id order by event_date
     
     }
     
OSANVIOS_GET_COURT_EVNT_SANCTIONS_DETAILS_DATA {

select court_evnt_sanct_dtl_id,record_event_id,record_sanction_reward_count,count_type,adjourned_flag,comment_text,link_flag,record_type from court_evnt_sanctions_details where court_evnt_sanct_dtl_id =:courtEvntSanctDtlId
}