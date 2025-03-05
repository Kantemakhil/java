RECURR_GET_HOLIDAYS_LIST{
SELECT EVENT_TYPE,EVENT_SEQ,DESCRIPTION,EVENT_DATE,MODIFY_DATE,EVENT_END_DATE,MODIFY_USER_ID,SYSTEM_EVENT_ID,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,SEAL_FLAG FROM SYSTEM_EVENTS WHERE EVENT_DATE >= :STARTDATE
}
RECURR_SCHEDULE_CONFLICT{
SELECT EVENT_ID ,OFFENDER_BOOK_ID ,IN_OUT_STATUS ,BOOKING_NO ,BOOKING_ACTIVE_FLAG ,OFFENDER_ID ,OFFENDER_ID_DISPLAY ,OFFENDER_LAST_NAME ,OFFENDER_FIRST_NAME ,EVENT_DATE ,START_TIME ,END_TIME ,EVENT_CLASS ,EVENT_TYPE ,EVENT_TYPE_DESC ,EVENT_SUB_TYPE ,EVENT_SUB_TYPE_DESC ,ACTIVE_FLAG ,EVENT_STATUS ,EVENT_STATUS_DESC ,EVENT_OUTCOME ,EVENT_OUTCOME_DESC ,BUSY_DATE_FLAG ,OUTCOME_REASON_CODE ,REFERENCE_ID ,APPLICATION_DATE ,APPLICATION_TIME ,RETURN_DATE ,RETURN_TIME ,COMMENT_TEXT ,DETAILS ,AGY_LOC_ID ,AGY_LOC_DESC ,LIVING_UNIT_ID ,LIVING_UNIT_DESC ,LU_LEVEL_1_CODE ,LU_LEVEL_2_CODE ,LU_LEVEL_3_CODE ,LU_LEVEL_4_CODE ,AGENCY_IML_ID ,AGENCY_IML_DESC ,AGENCY_IML_LEVEL_1_CODE ,AGENCY_IML_LEVEL_2_CODE ,AGENCY_IML_LEVEL_3_CODE ,TO_AGY_LOC_ID ,TO_AGY_LOC_DESC ,TO_LOC ,TO_LOC_DESC ,ESCORT_CODE ,ESCORT_DESC ,DIRECTION_CODE ,SCHEDULE_MOVEMENT_TIME ,FROM_CITY_CODE ,FROM_CITY_NAME ,TO_CITY_CODE ,TO_CITY_NAME ,TO_INTERNAL_LOCATION_ID ,TO_INTERNAL_LOCATION_DESC ,TO_INT_LOC_LEVEL_1_CODE ,TO_INT_LOC_LEVEL_2_CODE ,TO_INT_LOC_LEVEL_3_CODE ,TO_INT_LOC_USER_DESC ,CREDITED_HOURS ,ENGAGEMENT_CODE ,UNDERSTANDING_CODE ,PIECE_WORK ,IN_TIME ,OUT_TIME ,PERFORMANCE_CODE ,TRANSPORT_CODE ,SICK_NOTE_EXPIRY_DATE ,SICK_NOTE_RECEIVED_DATE ,UNEXCUSED_ABSENCE_FLAG ,UNPAID_WORK_ACTION ,UNPAID_WORK_BEHAVIOUR ,AGREED_TRAVEL_HOUR ,CHECK_BOX_1 ,CHECK_BOX_2 ,HIDDEN_COMMENT_TEXT ,IN_CHARGE_STAFF_ID ,IN_CHARGE_STAFF_NAME ,OFF_PRGREF_ID ,CONTACT_PERSON_NAME ,TO_ADDRESS_OWNER_CLASS ,TO_ADDRESS_ID ,UNPAID_WORK_SUPERVISOR ,TA_ID ,RECORD_SOURCE ,CHECK_SUM ,PROV_STATE_CODE ,PROV_STATE_DESC ,SCHEDULED_TRIP_ID,SERIES_ID FROM V_OFFENDER_ALL_SCHEDULES WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID AND (COALESCE(:SERIESID,0) = 0 OR SERIES_ID != :SERIESID ) AND EVENT_DATE in(:EVENT_DATE) AND EVENT_STATUS = 'SCH' ORDER BY EVENT_DATE DESC ,START_TIME desc
}
RECURR_SCHEDULE_INSERT{
insert into schedule_series_rule ( series_id,  exclude_holiday, active,  create_datetime, create_user_id, seal_flag, start_date, start_time, end_time, ui_rules, MODIFY_USER_ID, MODIFY_DATETIME) 
values( :seriesId, :excludeHoliday, :active, current_timestamp, :createUserId, :sealFlag, :startDate, :startTime, :endTime, :uiRules,null,null )
}
RECURR_SCHEDULE_PRE_INSERT{
select nextval('SCHEDULE_SERIES_RULE_series_id') from dual
}
RECURR_GET_SCHEDULE_SERIES_RULE{
SELECT SERIES_ID,EXCLUDE_HOLIDAY,ACTIVE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,SEAL_FLAG,START_DATE,START_TIME,END_TIME,UI_RULES FROM SCHEDULE_SERIES_RULE WHERE SERIES_ID  = :seriesId 
}

RECURR_SCH_INSERT_OFFENDER_IND_SCHEDULES{
 insert
	into
	OFFENDER_IND_SCHEDULES (event_id,
	offender_book_id,
	event_date,
	start_time,
	end_time,
	event_class,
	event_type,
	event_sub_type,
	event_status,
	comment_text,
	to_agy_loc_id,
	in_charge_staff_id,
	create_datetime,
	create_user_id,
	sms_schedule_hours_before,
	email_schedule_hours_before,
	email_flag,
	sms_flag,
	series_id,
	offender_tier_level_id,
	sch_version_id,
	MODIFY_USER_ID, MODIFY_DATETIME
	)
values (NEXTVAL('EVENT_ID'),
:offenderBookId,
:eventDate,
:startTime,
:endTime,
:eventClass,
:eventType,
:eventSubType,
:eventStatus,
:commentText,
:toAgyLocId,
:inChargeStaffId,
CURRENT_TIMESTAMP,
:createUserId,
:smsScheduleHoursBefore,
:emailScheduleHoursBefore,
:emailFlag,
:smsFlag,
:seriesId,
:offenderTierLevelId,
:versionNo,
null,null
)
}
RECURR_SCH_UPDATE_OFFENDER_IND_SCHEDULES{
UPDATE OFFENDER_IND_SCHEDULES SET EVENT_STATUS = 'DEL',modify_datetime = current_timestamp,MODIFY_USER_ID=:modifyUserId WHERE SERIES_ID = :seriesId AND EVENT_DATE >= :eventDate
}
RECURR_SCH_DELETE_OFFENDER_IND_SCHEDULES{
UPDATE OFFENDER_IND_SCHEDULES SET EVENT_STATUS = 'DEL',modify_datetime = current_timestamp,MODIFY_USER_ID=:modifyUserId WHERE EVENT_ID = :eventId
}

RECURR_SCH_UPDATE_OFFENDER_IND_SCHEDULES_DTE{
UPDATE OFFENDER_IND_SCHEDULES SET event_date =:startDate ,start_time =:startTime ,end_time =:endTime ,event_type =:scheduleType ,event_sub_type=:scheduleSubType ,comment_text=:commentText ,to_agy_loc_id=:location,
modify_datetime = current_timestamp,MODIFY_USER_ID=:modifyUserId ,email_flag =:emailFlag,sms_flag=:smsFlag ,email_schedule_hours_before =:emailSchHoursBefore ,sms_schedule_hours_before=:smsSchHoursBefore,event_status =:eventStatus  WHERE EVENT_ID = :eventId 
}

RECURR_SCH_DELETE_OFFENDER_IND_SCHEDULES_DTE{
UPDATE OFFENDER_IND_SCHEDULES SET EVENT_STATUS = 'DEL',modify_datetime = current_timestamp,MODIFY_USER_ID=:modifyUserId WHERE offender_tier_level_id = :offenderTierLevelId and event_type =:eventType and event_sub_type =:eventSubType and event_status ='SCH' and event_date >= :startDate and sch_version_id =:versionNo
}

RECURR_SCH_GET_DEFAULT_TIER_LEVEL_UIRULES{
select ui_rules from maintain_tier_default_events mtde where tier_level_code =(select tier_level_code from offender_tier_levels otl where offender_book_id =:offenderBookId and offender_tier_level_id =:offenderTierLevelId) and mtde.schedule_type =:eventType and mtde.schedule_sub_type =:eventSubType
}
