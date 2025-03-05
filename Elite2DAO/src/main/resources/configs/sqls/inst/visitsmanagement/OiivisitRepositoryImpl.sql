
OIIVISIT_OFFVIS_FIND_V_OFFENDER_VISIT_DETAILS {
 	select
	distinct off_vis.offender_visit_id,
	off.offender_id_display,
	off_bkg.offender_book_id,
	off.last_name,
	off.first_name,
	substr(tag_header_get_header_location(off_bkg.active_flag,
	off_bkg.community_active_flag,
	off_bkg.community_agy_loc_id,
	off_bkg.agy_loc_id,
	off_bkg.living_unit_id,
	off_bkg.comm_staff_role,
	off_bkg.comm_staff_id::bigint)::text,
	0,
	100) as living_unit_desc,
	off_vis.visit_status,
	off_vis.visit_date,
	off_vis.start_time,
	off_vis.end_time,
	off_vis.visit_type,
	off_vis.outcome_reason_code,
	off_vis.comment_text,
	ail.description as internal_location_desc,
	off_vis.visit_internal_location_id
	
from
	offenders off,
	offender_bookings off_bkg,
	offender_visits off_vis,
	agency_internal_locations ail,
	agency_visit_times avt
where
off_vis.agy_loc_id =:agyLocId
	and off_vis.visit_date::text <> ''::text
	and
	off_vis.agy_loc_id =:agyLocId 
	and
     off_vis.visit_date::date >=:fromDate::date
	and (:toDate::date is null
		or visit_date::date <= :toDate::date)
	and (:dayOfTheWeek::text is null
		or to_char(off_vis.visit_date, 'DY')=:dayOfTheWeek::text) and off_vis.agy_loc_id =avt.agy_loc_id and
		(:timeSlotSeq::text is null
		or (avt.time_slot_seq = :timeSlotSeq and off_vis.start_time::time>= avt.start_time::time and off_vis.end_time::time <= avt.end_time::time)) and off_vis.visit_date is not null
		and (:visitInternalLocationId::text is null
		or visit_internal_location_id::text= :visitInternalLocationId::text) and 
		(:offenderIdDisplay::text is null
		or off.offender_id_display::text= :offenderIdDisplay::text)
		and (:visitStatus::text is null
		or off_vis.visit_status::text= :visitStatus::text)
	and off.offender_id = off_bkg.offender_id
	and off_bkg.offender_book_id = off_vis.offender_book_id
	and ail.internal_location_id = off_vis.visit_internal_location_id
	order by VISIT_DATE desc,
	START_TIME desc,
	OFFENDER_ID_DISPLAY
}
OIIVISIT_OFFIMP_FIND_V_OFFENDER_ALL_VISITORS {
 	SELECT VISITOR_ID ,VISITOR_TYPE ,LAST_NAME ,FIRST_NAME ,MIDDLE_NAME ,RELATIONSHIP ,OFFENDER_VISIT_ID   FROM V_OFFENDER_ALL_VISITORS  where  VISITOR_ID !=  :offenderIdDisplay and OFFENDER_VISIT_ID = :offenderVisitId
}
OIIVISIT_OFFIMP_INSERT_V_OFFENDER_ALL_VISITORS {
	INSERT INTO V_OFFENDER_ALL_VISITORS(VISITOR_ID ,VISITOR_TYPE ,LAST_NAME ,FIRST_NAME ,MIDDLE_NAME ,RELATIONSHIP ,OFFENDER_VISIT_ID ) VALUES(:visitorId ,:visitorType ,:lastName ,:firstName ,:middleName ,:relationship ,:offenderVisitId )
}

OIIVISIT_FIND_RGAGENCYLOCATIONS_DATA {
 	SELECT   AL.DESCRIPTION ,AL.AGY_LOC_ID, AL.ACTIVE_FLAG FROM  AGENCY_LOCATIONS AL ,CASELOAD_AGENCY_LOCATIONS CAL WHERE  CAL.CASELOAD_ID = :CASELOADID
 	AND  AL.AGY_LOC_ID = CAL.AGY_LOC_ID   AND 
 	AL.AGENCY_LOCATION_TYPE = :CASELOADTYPE   AND  AL.AGY_LOC_ID NOT IN ('TRN' ,'OUT' )
}

OIIVISIT_FIND_RG_DAY_OF_THE_WEEK_LOV_DATA {
	select
	avd.WEEK_DAY as code,
	(select description from reference_codes rc where code=avd.WEEK_DAY and "domain"='WEEK_DAY') as description,
	case
		when WEEK_DAY = 'MON' then 1
		when WEEK_DAY = 'TUE' then 2
		when WEEK_DAY = 'WED' then 3
		when WEEK_DAY = 'THU' then 4
		when WEEK_DAY = 'FRI' then 5
		when WEEK_DAY = 'SAT' then 6
		when WEEK_DAY = 'SUN' then 7
		end as list_seq 
from
	AGENCY_VISIT_DAYS avd
where
	AGY_LOC_ID = :agyLocId
order by list_seq
}

OIIVISIT_FIND_RG_TIME_SLOT_OF_THE_WEEK_LOV_DATA {
select
	TO_CHAR (time_slot_seq) DESCRIPTION ,
	TO_CHAR (START_TIME ,
	'HH24:MI' ) stimetemp ,
	TO_CHAR (END_TIME ,
	'HH24:MI' ) WEEK_DAY,
	TO_CHAR (time_slot_seq) CODE,
	ACTIVE_FLAG
	from
	AGENCY_VISIT_TIMES
	
	where
	AGY_LOC_ID = :agyLocId
	and WEEK_DAY = :weekDay
	
order by
	AGY_LOC_ID desc,
	DESCRIPTION asc
}

OIIVISIT_FIND_RG_VISIT_SLOTS_LOV_DATA {
select
	avs.agency_visit_slot_id,
	avs.agy_loc_id,
	avs.week_day,
	avs.time_slot_seq,
	to_char(avs.internal_location_id) as code,
	
	(select description from agency_internal_locations ail where ail.internal_location_id =avs.internal_location_id) as description
	
from
	agency_visit_slots avs
where
	AGY_LOC_ID = :agyLocId
	and WEEK_DAY = :weekDay
	and TIME_SLOT_SEQ = :timeSlotSeq
}

OIIVISIT_OFFVST_BULK_UPDATE_OFFENDER_VISITS {
update offender_visits set outcome_reason_code=:outcomeReasonCode,visit_status=:visitStatus,comment_text=:commentText,modify_datetime=current_timestamp,
modify_user_id=:modifyUserId,record_user_id=:recordUserId where offender_visit_id=:offenderVisitId
}

OIIVISIT_FIND_RG_VISIT_SLOTS_LOV_DATA_WITHOUT_DAY {
select
distinct to_char(avs.internal_location_id) as code,
	avs.agy_loc_id,
	(select description from agency_internal_locations ail where ail.internal_location_id =avs.internal_location_id) as description
	
from
	agency_visit_slots avs
where
	AGY_LOC_ID = :agyLocId
}