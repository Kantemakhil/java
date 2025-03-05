OCMTIDET_INSERT_TIER_DEFAULT_EVENTS{
insert into maintain_tier_default_events(tier_level_code , schedule_type , schedule_sub_type, ui_rules, create_datetime , create_user_id , modify_datetime, modify_user_id, version_no, tier_event_sch_version_id, version_create_datetime, version_create_staff_id) values(:tierLevelcode, :scheduleType, :scheduleSubType, :uiRules, current_timestamp, :createUserId, null , null , ( select coalesce(max(version_no)+ 1, 1) from maintain_tier_default_events where tier_level_code =:tierLevelcode and schedule_type =:scheduleType and schedule_sub_type =:scheduleSubType), nextval('tier_event_sch_version_id_seq'), current_timestamp, ( select staff_id from staff_members where user_id =:createUserId))
}

OCMTIDET_UPDATE_TIER_DEFAULT_EVENTS{
update maintain_tier_default_events set  ui_rules =:uiRules ,modify_datetime = current_timestamp, modify_user_id = :modifyUserId  where tier_level_code =:tierLevelcode and schedule_type = :scheduleType and schedule_sub_type =:scheduleSubType
}

OCMTIDET_DELETE_TIER_DEFAULT_EVENTS{
delete from maintain_tier_default_events where tier_event_sch_version_id =:tierEventSchVersionId
}

OCMTIDET_EXECUTE_TIER_DEFAULT_EVENTS{
select mtde.tier_event_sch_version_id,mtde.version_no,mtde.tier_level_code,mtde.schedule_type,mtde.schedule_sub_type,mtde.ui_rules,mtde.create_datetime,(select count(*)::text from offender_tier_levels otl where otl.tier_level_code = mtde.tier_level_code) seal_flag from maintain_tier_default_events mtde where version_no = ( select max(version_no) from maintain_tier_default_events mt2 where mt2.tier_level_code = mtde.tier_level_code  and mt2.schedule_type = mtde.schedule_type and mt2.schedule_sub_type = mtde.schedule_sub_type) and mtde.tier_level_code =:tierLevelCode order by mtde.tier_level_code ,mtde.schedule_type,mtde.schedule_sub_type,mtde.create_datetime asc
}

OCMTIDET_RECORD_GROUP_DATA_ACTIVE{
select tier_level_code code ,tier_level_desc description from maintain_tier_levels mtd where mtd.tier_level_code = (select tier_level_code from offender_tier_levels where offender_book_id =:offenderBookId  and active_flag ='Y')
}

OCMTIDET_RG_SCHEDULE_TYPE_RECORDGROUP{
select code ,description from reference_codes where domain ='EVENTS' and parent_code = :parentCode
}

OCMTIDET_GET_TIER_LEVEL_CODE{
select (select tier_level_code from offender_tier_levels otl where offender_tier_level_id = ois.offender_tier_level_id and otl.deactivated_date is null) from offender_ind_schedules ois  where event_id = :eventId
}

OCMTIDET_GET_TIER_LEVEL_CODE_GET_ACTIVE_TIER_LEVEL_DATA{
select
	distinct ois.event_type as schedule_type ,
	ois.event_sub_type as schedule_sub_type,sch_version_id
from
	OFFENDER_IND_SCHEDULES ois
where
	ois.offender_book_id =:offenderBookId and  event_type =:eventType and event_sub_type =:eventSubType 
	and ois.offender_tier_level_id = :offenderTierLevelId and sch_version_id =:TierEventSchVersionId limit 1
}

OCMTIDET_GET_DEAFULT_TIER_EVENT_DATA{
select event_id ,(select MIN(event_date) from  OFFENDER_IND_SCHEDULES where offender_book_id =:offenderBookId and event_date>= current_date  and event_type =:eventType and event_sub_type =:eventSubType and offender_tier_level_id =:offenderTierLevelId  ) startDate ,(select MIN(start_time) from  OFFENDER_IND_SCHEDULES where offender_book_id =:offenderBookId and event_date>= current_date  and event_type =:eventType and event_sub_type =:eventSubType and offender_tier_level_id =:offenderTierLevelId  ) startTime,(select MIN(end_time) from  OFFENDER_IND_SCHEDULES where offender_book_id =:offenderBookId and event_date>= current_date  and event_type =:eventType and event_sub_type =:eventSubType and offender_tier_level_id =:offenderTierLevelId  ) endTime,event_type scheduleType,event_sub_type scheduleSubType,comment_text commentText,to_agy_loc_id location,email_flag emailFlag,sms_flag smsFlag,offender_tier_level_id ,(select concat(last_name,', ',first_name) from staff_members where staff_id = ois.in_charge_staff_id) as staffName,sms_schedule_hours_before smsSchHoursBefore, email_schedule_hours_before emailSchHoursBefore,sch_version_id from OFFENDER_IND_SCHEDULES ois where event_type =:eventType
and event_sub_type =:eventSubType and offender_tier_level_id =:offenderTierLevelId  and offender_book_id = :offenderBookId and event_status ='SCH' and sch_version_id=:versioNo order by event_id asc limit 1
}

OCMTIDET_GET_ACTIVE_TIER_LEVEL_RECORD{
select * from offender_tier_levels where offender_book_id = :offenderBookId and deactivated_date is null
}

OCMTIDET_GET_OFFENDER_TIERLEVEL_ID{
select offender_tier_level_id from OFFENDER_IND_SCHEDULES where event_id = :eventId
}

OCMTIDET_GET_OFFENDER_TIERLEVEL_VERSION_NO{
select sch_version_id from offender_ind_schedules where event_id = :eventId
}

OCMTIDET_GET_OFFENDER_TIERLEVEL_VERSION_NO_BASE_DETAILS{
select * from maintain_tier_default_events where tier_event_sch_version_id =:versionNo and tier_level_code =:tierLevelCode and schedule_type =:scheduleType and schedule_sub_type =:scheduleSubType  
}