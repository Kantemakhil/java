GET_CAPACITY{
SELECT c.capacity FROM agency_internal_locations a, internal_location_usages b, int_loc_usage_locations c WHERE a.agy_loc_id = :p_agy_loc_id AND b.internal_location_usage = 'VISIT' AND b.internal_location_usage_id = c.internal_location_usage_id AND c.internal_location_id = :p_int_loc_id limit 1
}
DESCRIPTION_CUR{
SELECT description FROM agency_internal_locations WHERE agy_loc_id =:p_agy_loc_id AND internal_location_id =:p_int_loc_id ORDER BY list_seq, agy_loc_id
}
GET_NAMES_CUR{
SELECT last_name, first_name, birthdate FROM persons WHERE person_id = :p_person_id
}
GL_RESTRICTION_CUR{
SELECT RC.PARENT_CODE FROM VISITOR_RESTRICTIONS VR, REFERENCE_CODES RC WHERE RC.DOMAIN = 'VST_RST_TYPE' AND RC.CODE = VR.VISIT_RESTRICTION_TYPE AND VR.PERSON_ID = :P_PERSON_ID AND VR.EFFECTIVE_DATE <= :P_VISIT_DATE AND (VR.EXPIRY_DATE IS NULL OR VR.EXPIRY_DATE > :P_VISIT_DATE) ORDER BY RC.PARENT_CODE
}
GET_AGE{
SELECT TRUNC(MONTHS_BETWEEN(SYSDATE()::date,TO_DATE(:V_BIRTHDATE,'DD-MM-YYYY')::date)/ 12) FROM DUAL
}

INSERT_OFFENDER_VISIT_VISITOR{
insert into offender_visit_visitors ( offender_visit_id, person_id, group_leader_flag, offender_visit_visitor_id, comment_text, event_outcome, offender_book_id, event_id , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( :p_offender_visit_id, :p_person_id, :p_group_leader_flag, :p_offender_visit_visitor_id, :p_comment_text, :p_event_outcome, :p_offender_book_id, nextval('event_id') , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
}

TYPE_CURSOR{
	SELECT contact_type,relationship_type FROM offender_contact_persons ocr
    WHERE ocr.offender_book_id =:p_offender_book_id AND ocr.contact_root_offender_id =:p_contact_offender_root_id 
 }
 
 OFFENDERCONTACT_CUR{
      SELECT voff.offender_id_display,voff.last_name,voff.first_name,voff.living_unit_description,
      voff.offender_book_id FROM v_name_search2_fn(:userId) voff WHERE voff.offender_book_id =:v_offender_book_id  
   }
   
 OFFENDER_CONTACTS_REF_CUR{
          SELECT rc.parent_code
           FROM offender_restrictions ors,
                reference_codes       rc
          WHERE ors.offender_book_id =:v_offender_book_id
            AND rc.domain = 'VST_RST_TYPE'
            AND rc.code = ors.restriction_type
            AND ors.effective_date::date <=:p_visit_date
            AND (ors.expiry_date IS NULL OR ors.expiry_date::date >:p_visit_date)
          ORDER BY rc.parent_code    
  }
  
  BIRTH_DATE_CUR{
           SELECT o.birth_date
           FROM offenders         o,
                offender_bookings b
          WHERE b.offender_book_id =:v_offender_book_id
            AND o.offender_id = b.offender_id
     }
  OFFENDER_CONTACT_CUR{
SELECT voff.offender_id_display, voff.last_name, voff.first_name, voff.living_unit_description, voff.offender_book_id FROM v_name_search2_fn(:userId) voff WHERE voff.offender_book_id =:v_offender_book_id
}

CONTACT_OFFENDER_REST_CUR{
SELECT rc.parent_code FROM offender_restrictions ors, reference_codes rc WHERE ors.offender_book_id =:v_offender_book_id AND rc.domain = 'VST_RST_TYPE' AND rc.code = ors.restriction_type AND ors.effective_date <= :p_visit_date AND (ors.expiry_date IS NULL OR ors.expiry_date >:p_visit_date) ORDER BY rc.parent_code
}   
GET_NEXT_AGY_VISIT_SLOT_ID_SEQUENCE {
	select nextval('agency_visit_slot_id')
}
CHECK_TIMESLOT_CHECK_SLOT {
	SELECT 'X'
           FROM agency_visit_times a,
                agency_visit_slots b
          WHERE a.agy_loc_id = b.agy_loc_id
            AND a.week_day = b.week_day
            AND a.time_slot_seq = b.time_slot_seq
            AND (:p_start_time >= to_char(a.start_time, 'HH24:MI') AND
                :p_start_time < to_char(a.end_time, 'HH24:MI'))
            AND b.internal_location_id = :p_int_loc_id
            AND b.week_day = :p_week_day
            AND b.agy_loc_id = :p_agy_loc_id
      AND a.active_flag = 'Y'

}
GET_OCUAVLOC_UNAVAILABLE{
select
	AVS.AGY_LOC_ID,
	AVS.WEEK_DAY,
	AVS.INTERNAL_LOCATION_ID,
	AVS.AGENCY_VISIT_SLOT_ID,
	AVS.MAX_GROUPS,
	AVS.MAX_ADULTS,
	ILU.CAPACITY,
	OVV.GROUPS_BOOKED,
	OVV.TOTAL_BOOKED,
	OVV.ADULTS_BOOKED,
	AIL.DESCRIPTION,
	TO_CHAR(AVT.START_TIME, 'HH24:MI') START_TIME,
	TO_CHAR(AVT.END_TIME, 'HH24:MI') END_TIME,
	OVV.VISIT_DATE
from
	internal_location_usages iul,
	int_loc_usage_locations ilu,
	agency_visit_times avt,
	agency_internal_locations ail,
	agency_visit_slots avs
left outer join (
	select
		OV.AGENCY_VISIT_SLOT_ID,
		OV.VISIT_DATE,
		COUNT(distinct OV.OFFENDER_VISIT_ID) GROUPS_BOOKED,
		COUNT(distinct OVV.OFFENDER_VISIT_VISITOR_ID) TOTAL_BOOKED,
		SUM(TAG_VISITS_IS_ADULT(case when coalesce(OVV.OFFENDER_BOOK_ID::text, '') = '' then OVV.PERSON_ID else OVV.OFFENDER_BOOK_ID end , case when coalesce(OVV.OFFENDER_BOOK_ID::text, '') = '' then 'PER' else 'OFF' end , (SP.PROFILE_VALUE)::bigint , OV.VISIT_DATE)) ADULTS_BOOKED
	from
		system_profiles sp,
		offender_visits ov
	left outer join offender_visit_visitors ovv on
		(OV.OFFENDER_VISIT_ID = OVV.OFFENDER_VISIT_ID
			and OVV.OUTCOME_REASON_CODE is null )
	where
		SP.PROFILE_TYPE = 'CLIENT'
		and SP.PROFILE_CODE = 'VISIT_AGE'
		and OV.VISIT_STATUS in ('A', 'SCH')
		and OV.VISIT_DATE =:P_DATE
	group by
		OV.AGENCY_VISIT_SLOT_ID,
		OV.VISIT_DATE) ovv on
	(AVS.AGENCY_VISIT_SLOT_ID = OVV.AGENCY_VISIT_SLOT_ID)
where
	AVT.TIME_SLOT_SEQ = AVS.TIME_SLOT_SEQ
	and AVS.INTERNAL_LOCATION_ID = AIL.INTERNAL_LOCATION_ID
	and AVS.WEEK_DAY = AVT.WEEK_DAY
	and IUL.INTERNAL_LOCATION_USAGE = 'VISIT'
	and IUL.INTERNAL_LOCATION_USAGE_ID = ILU.INTERNAL_LOCATION_USAGE_ID
	and ILU.INTERNAL_LOCATION_ID = AIL.INTERNAL_LOCATION_ID
	and AIL.AGY_LOC_ID = AVT.AGY_LOC_ID
	and AIL.AGY_LOC_ID = IUL.AGY_LOC_ID
	and ((coalesce(OVV.TOTAL_BOOKED,
        0) >= coalesce(ILU.CAPACITY,
        999))
		or (coalesce(OVV.GROUPS_BOOKED,
                0) >= coalesce(AVS.MAX_GROUPS,
                999))
			or (coalesce(OVV.ADULTS_BOOKED,
                        0) >= coalesce(AVS.MAX_ADULTS,
                        999)))
	and AIL.AGY_LOC_ID = :P_AGY_LOC_ID
	and AVS.WEEK_DAY = :P_WEEK_DAY
	and TO_CHAR(AVT.START_TIME, 'HH24:MI') = :P_START_TIME
order by
	ILU.LIST_SEQ
}
GET_OCUAVLOC_AVAILABLE_SELECT{
select
	AVS.AGY_LOC_ID,
	AVS.WEEK_DAY,
	AVS.INTERNAL_LOCATION_ID,
	AVS.AGENCY_VISIT_SLOT_ID,
	AVS.MAX_GROUPS,
	AVS.MAX_ADULTS,
	ILU.CAPACITY,
	OVV.GROUPS_BOOKED,
	OVV.TOTAL_BOOKED,
	OVV.ADULTS_BOOKED,
	AIL.DESCRIPTION,
	TO_CHAR(AVT.START_TIME, 'HH24:MI') START_TIME,
	TO_CHAR(AVT.END_TIME, 'HH24:MI') END_TIME,
	OVV.VISIT_DATE
from
	internal_location_usages iul,
	int_loc_usage_locations ilu,
	agency_visit_times avt,
	agency_internal_locations ail,
	agency_visit_slots avs
left outer join (
	select
		OV.AGENCY_VISIT_SLOT_ID,
		OV.VISIT_DATE,
		COUNT(distinct OV.OFFENDER_VISIT_ID) GROUPS_BOOKED,
		COUNT(distinct OVV.OFFENDER_VISIT_VISITOR_ID) TOTAL_BOOKED,
		SUM(TAG_VISITS_IS_ADULT(case when coalesce(OVV.OFFENDER_BOOK_ID::text, '') = '' then OVV.PERSON_ID else OVV.OFFENDER_BOOK_ID end , case when coalesce(OVV.OFFENDER_BOOK_ID::text, '') = '' then 'PER' else 'OFF' end , (SP.PROFILE_VALUE)::bigint , OV.VISIT_DATE)) ADULTS_BOOKED
	from
		system_profiles sp,
		offender_visits ov
	left outer join offender_visit_visitors ovv on
		(OV.OFFENDER_VISIT_ID = OVV.OFFENDER_VISIT_ID
			and OVV.OUTCOME_REASON_CODE is null )
	where
		SP.PROFILE_TYPE = 'CLIENT'
		and SP.PROFILE_CODE = 'VISIT_AGE'
		and OV.VISIT_STATUS in ('A', 'SCH')
		and OV.VISIT_DATE =:P_DATE
	group by
		OV.AGENCY_VISIT_SLOT_ID,
		OV.VISIT_DATE) ovv on
	(AVS.AGENCY_VISIT_SLOT_ID = OVV.AGENCY_VISIT_SLOT_ID)
where
	AVT.TIME_SLOT_SEQ = AVS.TIME_SLOT_SEQ
	and AVS.INTERNAL_LOCATION_ID = AIL.INTERNAL_LOCATION_ID
	and AVS.WEEK_DAY = AVT.WEEK_DAY
	and IUL.INTERNAL_LOCATION_USAGE = 'VISIT'
	and IUL.INTERNAL_LOCATION_USAGE_ID = ILU.INTERNAL_LOCATION_USAGE_ID
	and ILU.INTERNAL_LOCATION_ID = AIL.INTERNAL_LOCATION_ID
	and AIL.AGY_LOC_ID = AVT.AGY_LOC_ID
	and AIL.AGY_LOC_ID = IUL.AGY_LOC_ID
	and (((coalesce(AVS.MAX_GROUPS,
        0) = 0
		and coalesce(AVS.MAX_ADULTS,
                0) = 0)
		and coalesce(OVV.TOTAL_BOOKED,
                0) < coalesce(ILU.CAPACITY,
                0))
		or ((coalesce(AVS.MAX_GROUPS,
                0) != 0
			and coalesce(AVS.MAX_ADULTS,
                        0) = 0)
			and (coalesce(OVV.GROUPS_BOOKED,
                        0) < coalesce(AVS.MAX_GROUPS,
                        0)))
			or ((coalesce(AVS.MAX_GROUPS,
                        0) = 0
				and coalesce(AVS.MAX_ADULTS,
                                0) != 0)
				and coalesce(OVV.ADULTS_BOOKED,
                                0) < coalesce(AVS.MAX_ADULTS,
                                0))
				or ((coalesce(AVS.MAX_GROUPS,
                                0) != 0
					and coalesce(AVS.MAX_ADULTS,
                                        0) != 0)
					and coalesce(OVV.ADULTS_BOOKED,
                                        0) < coalesce(AVS.MAX_ADULTS,
                                        0)
						and (coalesce(OVV.GROUPS_BOOKED,
                                                0) < coalesce(AVS.MAX_GROUPS,
                                                0))))
	and AIL.AGY_LOC_ID = :P_AGY_LOC_ID
	and AVS.WEEK_DAY = :P_WEEK_DAY
	and TO_CHAR(AVT.START_TIME, 'HH24:MI') =:P_START_TIME
order by
	ILU.LIST_SEQ

}

GET_VISIT_CYCLE_LIMITS {
SELECT cycle_type,
decode(cycle_type, 'WEEK', next_day(:p_vis_dt, start_day) - 7, trunc(p_vis_dt, 'MONTH')) as createDatetime,
                decode(cycle_type, 'WEEK', next_day(:p_vis_dt, start_day) - 1, add_months(trunc(:p_vis_dt, 'MONTH'), 1) - 1) as expiryDate,
                tot_hrs,
                tot_visits,
                vcl.visit_cycle_limit_id
           FROM visit_cycle_limits vcl
          WHERE agy_loc_id = :p_agy_id
            AND sec_level = :p_sec_lvl
            AND vcl.active_flag = 'Y'
}

GET_USED_VIS_CUR {
SELECT COUNT(*) p_used_vis,
                
                NVL(SUM((end_time - start_time) * 24),0) p_used_hrs
           FROM offender_visits
          WHERE offender_visit_id IN (SELECT offender_visit_id FROM OFFENDER_VISIT_VISITORS WHERE OFFENDER_BOOK_ID = :p_bkg_id)
            AND (visit_type IN
                (SELECT DISTINCT visit_type
                   FROM visit_cycle_limits vcl,
                        visit_type_limits  vtl
                  WHERE vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id
                    AND agy_loc_id = :p_agy_id
                    AND sec_level = :p_sec_lvl
                    AND vcl.active_flag = 'Y')
                OR ((visit_type = :v_vis_tp AND :v_vis_tp IS NOT NULL) OR
                v_vis_tp IS NULL)
                )
            AND visit_date BETWEEN :p_cyc_start AND :p_cyc_end
            AND visit_status NOT IN
                (SELECT 'CANC'
                   FROM visit_cycle_limits vcl,
                        visit_type_limits  vtl
                  WHERE vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id
                    AND agy_loc_id = :p_agy_id
                    AND sec_level = :p_sec_lvl
                    AND visit_type = offender_visits.visit_type
                    AND reinstate_flag = 'Y'
                    AND vcl.active_flag = 'Y')
}       


GET_TOT_TYPE_CUR {
SELECT max_hrs_type,
                max_visits_type
           FROM visit_type_limits vtl
          WHERE visit_cycle_limit_id = :p_lim_id
            AND visit_type = :p_vis_tp
            AND active_flag = 'Y'
}

      

UPDATE_OFFENDER_VISIT_VISITORS{
 -- UPDATE OFFENDER_VISIT_VISITORS SET EVENT_STATUS = :P_VISIT_STATUS WHERE OFFENDER_VISIT_ID = :P_OFFENDER_VISIT_ID 
 update OFFENDER_VISIT_VISITORS set EVENT_STATUS = :P_VISIT_STATUS,	visit_status=:P_VISIT_STATUS, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where OFFENDER_VISIT_ID = :P_OFFENDER_VISIT_ID
 }   

CYC_END_CUR {
select
	cycle_type,
	start_day ,
	case
		when cycle_type = 'WEEK' then next_day(to_date(:p_vis_dt::text, 'DD/MM/YYYY'),
		( case when start_day='MON' then 'MONDAY'
		when start_day='TUE' then 'TUESDAY'
		when start_day='WED' then 'WEDNESDAY'
		when start_day='THU' then 'THURSDAY'
		when start_day='FRI' then 'FRIDAY'
		when start_day='SAT' then 'SATURDAY'
		when start_day='SUN' then 'SUNDAY'
		 end)
		) - interval '7 days'
		else
		(case when   start_day='LASTDAY' then date_trunc('MONTH', to_date(:p_vis_dt::text, 'DD/MM/YYYY'))  - interval '1 day' else date_trunc('month', to_date(:p_vis_dt::text, 'DD/MM/YYYY'))end ) 
	end create_datetime,
	case
		when cycle_type = 'WEEK' then next_day(to_date(:p_vis_dt::text, 'DD/MM/YYYY'),
		( case when start_day='MON' then 'MONDAY'
		when start_day='TUE' then 'TUESDAY'
		when start_day='WED' then 'WEDNESDAY'
		when start_day='THU' then 'THURSDAY'
		when start_day='FRI' then 'FRIDAY'
		when start_day='SAT' then 'SATURDAY'
		when start_day='SUN' then 'SUNDAY'
		 end)) - interval '1 days'
		else 
		(case when   start_day='LASTDAY' then (date_trunc('MONTH', to_date(:p_vis_dt::text, 'DD/MM/YYYY'))  + interval '1 month') - interval '1 day' else date_trunc('MONTH', to_date(:p_vis_dt::text, 'DD/MM/YYYY')) + '1 month'::interval - interval '1 day'end ) 
	end expiry_date,
	tot_hrs,
	tot_visits,
	vcl.visit_cycle_limit_id
from
	visit_cycle_limits vcl
where
	agy_loc_id = :p_agy_id
	and visit_config_type_value = :p_sec_lvl
	and vcl.active_flag = 'Y'	
}

USED_VIS_CUR {
--select
--	COUNT(*) count,
--	      coalesce(SUM((end_time - start_time) * 24), 0 * '1 minute'::interval) as time
--	--SUM((end_time - start_time) * 24)  --@@@ Added by Sanjeeva on 23-Feb-2012 for HPQC#153, commented added NVL to the sum below
--from
--	offender_visits
--	--          WHERE offender_book_id = p_bkg_id  --@@@ Added by Sanjeeva on 23-Feb-2012 for HPQC#154, commented to find on book id, instead added code to find on visit id.
--where
--	offender_visit_id in (
--	select
--		offender_visit_id
--	from
--		OFFENDER_VISIT_VISITORS
--	where
--		OFFENDER_BOOK_ID = :p_bkg_id )
--	--@@@ Added by Sanjeeva on 23-Feb-2012 for HPQC#154
--	and (visit_type in (
--	select
--		distinct visit_type
--	from
--		visit_cycle_limits vcl,
--		visit_type_limits vtl
--	where
--		vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id
--		and agy_loc_id = :p_agy_id
--		and sec_level = :p_sec_lvl
--		and vcl.active_flag = 'Y')
--	or ((visit_type = :v_vis_tp
--		and (:v_vis_tp::text is not null
--			and :v_vis_tp::text <> ''))
--	or
--                coalesce(:v_vis_tp::text, '') = '')
--                )
--	and visit_date between :p_cyc_start and :p_cyc_end
--	and visit_status not in (
--	select
--		'CANC'
--	from
--		visit_cycle_limits vcl,
--		visit_type_limits vtl
--	where
--		vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id
--		and agy_loc_id = :p_agy_id
--		and sec_level = :p_sec_lvl
--		and visit_type = offender_visits.visit_type
--		and reinstate_flag = 'Y'
--		and vcl.active_flag = 'Y')


select
	COUNT(*) count,
	      coalesce(SUM((end_time - start_time) * 24), 0 * '1 minute'::interval) as time
	--SUM((end_time - start_time) * 24)  --@@@ Added by Sanjeeva on 23-Feb-2012 for HPQC#153, commented added NVL to the sum below
from
	offender_visits
	--          WHERE offender_book_id = p_bkg_id  --@@@ Added by Sanjeeva on 23-Feb-2012 for HPQC#154, commented to find on book id, instead added code to find on visit id.
where
	offender_visit_id in (
	select
		offender_visit_id
	from
		OFFENDER_VISIT_VISITORS
	where
		OFFENDER_BOOK_ID = :p_bkg_id and offender_visits.visit_config_type='SEC_LEVEL' and visit_type = :v_vis_tp )
	--@@@ Added by Sanjeeva on 23-Feb-2012 for HPQC#154
	and (visit_type in (
	select
		distinct visit_type
	from
		visit_cycle_limits vcl,
		visit_type_limits vtl
	where
		vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id
		and agy_loc_id = :p_agy_id
		and visit_config_type_value = :p_sec_lvl
		and vcl.active_flag = 'Y')
	or ((visit_type = :v_vis_tp
		and (:v_vis_tp::text is not null
			and :v_vis_tp::text <> ''))
	or
                coalesce(:v_vis_tp::text, '') = '')
                )
	and visit_date between :p_cyc_start and :p_cyc_end
	and visit_status not in (
	select
		'CANC'
	from
		visit_cycle_limits vcl,
		visit_type_limits vtl
	where
		vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id
		and agy_loc_id = :p_agy_id
		and visit_config_type_value = :p_sec_lvl
		and visit_type = offender_visits.visit_type
		and reinstate_flag = 'Y'
		and vcl.active_flag = 'Y')
}

TOT_TYPE_CUR {

	 SELECT max_hrs_type,
                max_visits_type
           FROM visit_type_limits vtl
          WHERE visit_cycle_limit_id = :p_lim_id
            AND visit_type = :p_vis_tp
            AND active_flag = 'Y'

}
         
GET_CONTACT_OFFENDER_BOOK_ID{
 select offender_book_id from v_name_search2_fn(:userId) where root_offender_id = :p_contact_root_offender_id
}

GET_ROOT_OFFENDER_ID_FROM_BOOK{
 select o.root_offender_id from offenders o, offender_bookings b where b.offender_book_id = :p_offender_book_id and o.offender_id = b.offender_id
}

GET_NEXT_OFF_VISIT_ID{
select 	nextval('offender_visit_id')
}

TAG_VISITS_GET_EVENT_ID{
select 	nextval('event_id')
}

GET_NEXT_OFF_VISIT_VISITOR_ID{
select 	nextval('offender_visit_visitor_id')
}

GET_OFFENDER_ID{
select o.offender_id from offenders o where o.offender_id_display = :p_offender_id_display
}

GET_L_OFFENDER_BOOK_ID{
SELECT ov.offender_book_id FROM offender_visits ov, offender_visit_visitors ovv WHERE ov.offender_visit_id != :p_offender_visit_id AND ov.offender_visit_id = ovv.offender_visit_id AND ov.visit_date = :p_date AND ovv.person_id = :p_person_id AND ov.visit_status = 'SCH' AND ( ( :p_start_time > ov.start_time AND :p_start_time < ov.end_time ) OR ( :p_end_time > ov.start_time AND :p_end_time < ov.end_time ) ) offset 1  
}

GET_L_OFFENDER_BOOK_ID_ONE{
 select ov.offender_book_id from offender_visits ov, offender_visit_visitors ovv where ov.offender_visit_id != :p_offender_visit_id and ov.offender_visit_id = ovv.offender_visit_id and ov.visit_date = :p_date and ovv.offender_book_id = :p_offender_book_id and ov.visit_status = 'SCH' and ((:p_start_time > ov.start_time and :p_start_time < ov.end_time) or (:p_end_time > ov.start_time and :p_end_time < ov.end_time)) limit 1
}

GET_OVERLAP_DETAILS{
 select o.offender_id_display || ' - ' || o.last_name || ', ' || o.first_name from offenders o, offender_bookings ob where ob.offender_book_id = :l_offender_book_id and ob.offender_id = o.offender_id limit 1
}

PERSON_CUR{
SELECT last_name, first_name, birthdate FROM persons WHERE person_id =:P_PERSON_ID
}

RESTRICTION_CUR{
SELECT rc.parent_code FROM offender_person_restricts ocr, reference_codes rc, offender_contact_persons ocp WHERE rc.domain = 'VST_RST_TYPE' AND
rc.code = ocr.restriction_type AND ocr.offender_contact_person_id = ocp.offender_contact_person_id AND ocp.person_id =:p_person_id AND
ocp.offender_book_id =:p_offender_book_id AND ocr.restriction_effective_date <=:p_visit_date AND (ocr.restriction_expiry_date IS NULL OR 
ocr.restriction_expiry_date >:p_visit_date) ORDER BY rc.parent_code
}

GL_RESTRICTION_CUR_ONE{
SELECT rc.parent_code FROM visitor_restrictions vr, reference_codes rc WHERE rc.domain = 'VST_RST_TYPE' AND rc.code = vr.visit_restriction_type 
AND vr.person_id =:p_person_id AND vr.effective_date <=:p_visit_date AND (vr.expiry_date IS NULL OR vr.expiry_date >:p_visit_date) ORDER BY rc.parent_code limit 1
}
TYPE_CUR{
SELECT contact_type, relationship_type FROM offender_contact_persons ocp WHERE ocp.offender_book_id =:P_OFFENDER_BOOK_ID AND ocp.person_id =:P_PERSON_ID
}
SELECT_PAGE{
select months_between((:P_VISIT_DATE),(:P_BIRTHDATE))  from dual
}

UPDATE_OFFENDER_VISIT_VISITORS{
update offender_visit_visitors set outcome_reason_code =:P_OUTCOME_REASON_CODE, event_outcome = 'ABS', event_status = 'CANC', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_visit_id =:P_OFFENDER_VISIT_ID
}

UPDATE_OFFENDER_VISITS{
update offender_visits set visit_status = 'CANC', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_visit_id =:P_OFFENDER_VISIT_ID
}

OTHER_VISITS_OFFS_CUR{
select
	distinct offender_visit_visitors.offender_book_id
            ,
	offenders.last_name || ', ' || offenders.first_name first_name
from
	offender_visits
            ,
	offender_visit_visitors
            ,
	offender_bookings
            ,
	offenders
where
	offender_visits.offender_visit_id <> :p_offender_visit_id
	and offender_visits.visit_date >= (:p_visit_date)
	and offender_visits.visit_date < (:p_visit_date)
	and TO_CHAR(offender_visits.start_time, 'HH24MI') <= (:p_visit_end_time,
	'HH24MI')::text
	and TO_CHAR(offender_visits.end_time, 'HH24MI') >= (:p_visit_start_time,
	'HH24MI')::text
	and offender_visits.visit_internal_location_id = :p_internal_location_id
	and offender_visits.visit_status = 'SCH'
	and offender_visits.offender_visit_id = offender_visit_visitors.offender_visit_id
	and offender_visit_visitors.offender_book_id is not null
	and offender_visit_visitors.offender_book_id = offender_bookings.offender_book_id
	and offender_bookings.offender_id = offenders.offender_id
}

VISIT_VISITING_OFFS_CUR{
select
	distinct offender_visit_visitors.offender_book_id
            ,
	offenders.last_name || ', ' || offenders.first_name first_name
from
	offender_visits
            ,
	offender_visit_visitors
            ,
	offender_bookings
            ,
	offenders
where
	offender_visits.offender_visit_id =:p_offender_visit_id
	and offender_visits.offender_visit_id = offender_visit_visitors.offender_visit_id
	and offender_visit_visitors.offender_book_id is not null
	and offender_visit_visitors.offender_book_id <> offender_visits.offender_book_id
	and offender_visit_visitors.offender_book_id = offender_bookings.offender_book_id
	and offender_bookings.offender_id = offenders.offender_id
}

VISIT_PERSONS_CUR{
SELECT DISTINCT persons.person_id, persons.last_name || ', ' || persons.first_name first_name
        FROM offender_visits
            ,offender_visit_visitors
            ,persons
       WHERE offender_visits.offender_visit_id = :p_offender_visit_id
         AND offender_visits.offender_visit_id = offender_visit_visitors.offender_visit_id
         AND offender_visit_visitors.person_id IS NOT NULL
         AND offender_visit_visitors.person_id = persons.person_id
}

OTHER_VISITS_PERSONS_CUR{
select
	distinct persons.person_id,
	persons.last_name || ', ' || persons.first_name first_name
from
	offender_visits
            ,
	offender_visit_visitors
            ,
	persons
where
	offender_visits.offender_visit_id <> :p_offender_visit_id
	and offender_visits.visit_date >= (:p_visit_date)
	and offender_visits.visit_date <(:p_visit_date)
	and (offender_visits.start_time,
	'HH24MI')::text <= (:p_visit_end_time,
	'HH24MI')::text
	and (offender_visits.end_time,
	'HH24MI')::text >= (:p_visit_start_time,
	'HH24MI')::text
	and offender_visits.visit_internal_location_id =:p_internal_location_id
	and offender_visits.visit_status = 'SCH'
	and offender_visits.offender_visit_id = offender_visit_visitors.offender_visit_id
	and offender_visit_visitors.person_id is not null
	and offender_visit_visitors.person_id = persons.person_id
}
IS_VICTIM_CUR{
select
	reference_codes.description description
from
	offender_associated_parties
            ,
	reference_codes
where
	offender_associated_parties.offender_book_id =:p_offender_book_id
	and offender_associated_parties.party_type = 'PERSON'
	and offender_associated_parties.party_id = (:p_person_id)::text
	and offender_associated_parties.relationship_code = reference_codes.code
	and reference_codes.domain = 'VIC/CASE_REL'
}

VISITED_OFFENDER_CUR{
select
	offenders.last_name || ', ' || offenders.first_name
from
	offenders,
	offender_bookings
where
	offenders.offender_id = offender_bookings.offender_id
	and offender_bookings.offender_book_id =:p_visited_offender_book_id
}

LV_NON_ASSOC_WARNING_MESSAGEAGESS{
select
	SUBSTR(:LV_NON_ASSOC_WARNING_MESSAGE || ' There is a non-association' ||
                                ' between visited offender: ' || :LV_VISITED_OFFENDER_NAME ||
                                ' and offender: ' || :TB_OTHER_VISITS_OFFS_OFF_NAME || ' scheduled (either as visited or visiting offender) for a visit overlapping the same time and location.' || chr(10),
	1,
	32000) from dual
}

LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK2{
select
	SUBSTR(:LV_NON_ASSOC_WARNING_MESSAGE || ' There is a non-association' ||
                                ' between visited offender: ' || :LV_VISITED_OFFENDER_NAME ||
                                ' and his visiting offender: ' ||:TB_VISIT_VISITING_OFFS_NAME || '.' || chr(10),
	1,
	32000)
from
	dual
}

LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK3{
select
	SUBSTR(:LV_NON_ASSOC_WARNING_MESSAGE || ' There is a non-association' ||
                                   ' between visiting offender: ' || :TB_VISIT_VISITING_OFFS ||
                                   ' and offender: ' || :TB_OTHER_VISITS_OFFS_NAME || ' scheduled (either as visited or visiting offender) for a visit overlapping the same time and location.' || chr(10),
	1,
	32000)
from
	dual
}

LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK4{
select
	SUBSTR(:LV_NON_ASSOC_WARNING_MESSAGE || ' There is a non-association' ||
                                                  ' between visiting offender: ' || :TB_VISIT_VISITING_OFFS_OFF_NAME ||
                                                  ' and visiting offender: ' || :TB_VISIT_VISITING_OFFS_C__OFF_NAME || '.' || chr(10),
	1,
	32000)
from
	dual
}

LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK5{
select
	SUBSTR(:LV_VICTIM_WARNING_MESSAGE || ' There is linkage of type ' || :LV_RELATIONSHIP_DESC ||
                                 ' between visitor: ' || :TB_VISIT_PERSONS_PERS_NAME ||
                                 ' and visited offender: ' || :LV_VISITED_OFFENDER_NAME || '.' || chr(10),
	1,
	32000)
from
	dual
}
LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK6{
select
	SUBSTR(:LV_VICTIM_WARNING_MESSAGE ||' There is linkage of type '|| :LV_RELATIONSHIP_DESC ||
                                 ' between visitor: '|| :TB_VISIT_PERSONS_PERS_NAME ||
                                 ' and visited offender: '|| :LV_VISITED_OFFENDER_NAME ||'.'||chr(10), 1, 32000)
from
	dual
	
}

LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK7{
select
	SUBSTR(:LV_VICTIM_WARNING_MESSAGE || ' There is linkage of type ' || :LV_RELATIONSHIP_DESC ||
                                 ' between visitor: ' || :TB_VISIT_PERSONS_PERS_NAME ||
                                 ' and visiting offender: ' || :TB_VISIT_VISITING_OFFS_OFF_NAME || '.' || chr(10),
	1,
	32000)
from
	dual
}

LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK8{
select
	SUBSTR(:LV_VICTIM_WARNING_MESSAGE || ' There is linkage of type ' ||:LV_RELATIONSHIP_DESC ||
                                 ' between visitor: ' || :TB_VISIT_PERSONS_PERS_NAME ||
                                 ' and offender: ' || :TB_OTHER_VISITS_OFFS_OFF_NAME || ' scheduled (either as visited or visiting offender) for a visit overlapping the same time and location.' || chr(10),
	1,
	32000)
from
	dual
}

LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK9{
elect
	SUBSTR(:LV_VICTIM_WARNING_MESSAGE || ' There is linkage of type ' || :LV_RELATIONSHIP_DESC ||
                                 ' between visitor: ' || :TB_OTHER_VISITS_PERSONS_PERS_NAME || ' scheduled for a visit overlapping the same time and location' ||
                                 ' and visited offender: ' || :LV_VISITED_OFFENDER_NAME || '.' || chr(10),
	1,
	32000)
from
	dual
}

LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK10{
select
	SUBSTR(:LV_VICTIM_WARNING_MESSAGE ||' There is linkage of type '|| :LV_RELATIONSHIP_DESC ||
                                 ' between visitor: '|| :TB_OTHER_VISITS_PERSONS_PERS_NAME || ' scheduled for a visit overlapping the same time and location' ||
                                 ' and visiting offender: '|| :TB_VISIT_VISITING_OFFS_OFF_NAME ||'.'||chr(10), 1, 32000)
from
	dual
}

LV_NON_ASSOC_WARNING_MESSAGEAGESS_CHK11{
select
	SUBSTR('Offenders Non-Associations (includes STG Non-Associations)' || CHR(10) || CHR(10) || :LV_NON_ASSOC_WARNING_MESSAGE,
	1,
	32000)
from
	dual
}
LV_NON_ASSOC_WARNING_MSG{
select
	SUBSTR('Victims Linkages' || CHR(10) || CHR(10) || :LV_VICTIM_WARNING_MESSAGE,
	1,
	32000)
from
	dual
}

LV_NON_ASSOC_WARNING_MSG1{
select
	SUBSTR(:P_WARNING_MESSAGE || CHR(10) || CHR(10) || :LV_VICTIM_WARNING_MESSAGE, 1, 32000)
from
	dual
}

CYC_END_CUR_IEP{
select
	cycle_type,
	start_day ,
	case
		when cycle_type = 'WEEK' then next_day(to_date(:p_vis_dt::text, 'DD/MM/YYYY'),
		( case when start_day='MON' then 'MONDAY'
		when start_day='TUE' then 'TUESDAY'
		when start_day='WED' then 'WEDNESDAY'
		when start_day='THU' then 'THURSDAY'
		when start_day='FRI' then 'FRIDAY'
		when start_day='SAT' then 'SATURDAY'
		when start_day='SUN' then 'SUNDAY'
		 end)
		) - interval '7 days'
		else
		(case when   start_day='LASTDAY' then date_trunc('MONTH', to_date(:p_vis_dt::text, 'DD/MM/YYYY'))  - interval '1 day' else date_trunc('month', to_date(:p_vis_dt::text, 'DD/MM/YYYY'))end ) 
	end create_datetime,
	case
		when cycle_type = 'WEEK' then next_day(to_date(:p_vis_dt::text, 'DD/MM/YYYY'),
		( case when start_day='MON' then 'MONDAY'
		when start_day='TUE' then 'TUESDAY'
		when start_day='WED' then 'WEDNESDAY'
		when start_day='THU' then 'THURSDAY'
		when start_day='FRI' then 'FRIDAY'
		when start_day='SAT' then 'SATURDAY'
		when start_day='SUN' then 'SUNDAY'
		 end)) - interval '1 days'
		else 
		(case when   start_day='LASTDAY' then (date_trunc('MONTH', to_date(:p_vis_dt::text, 'DD/MM/YYYY'))  + interval '1 month') - interval '1 day' else date_trunc('MONTH', to_date(:p_vis_dt::text, 'DD/MM/YYYY')) + '1 month'::interval - interval '1 day'end ) 
	end expiry_date,
	tot_hrs,
	tot_visits,
	vcl.visit_cycle_limit_id
from
	visit_cycle_limits vcl
where
	agy_loc_id = :p_agy_id
	and visit_config_type_value = :iepLevel
	and vcl.active_flag = 'Y'
}

OIMVLIMIT_IEP_USED_VIS_CUR {

--select COUNT(*) count, coalesce(SUM((end_time - start_time) * 24), 0 * '1 minute'::interval) as time from offender_visits where offender_visit_id in ( select offender_visit_id from OFFENDER_VISIT_VISITORS where OFFENDER_BOOK_ID = :p_bkg_id) and (visit_type in ( select distinct visit_type from visit_cycle_limits vcl, visit_type_limits vtl where vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id and agy_loc_id = :p_agy_id and iep_level = :iepLevel and vcl.active_flag = 'Y') or ((visit_type = :v_vis_tp and (:v_vis_tp::text is not null and :v_vis_tp::text <> '')) or coalesce(:v_vis_tp::text, '') = '') ) and visit_date between :p_cyc_start and :p_cyc_end and visit_status not in ( select 'CANC' from visit_cycle_limits vcl, visit_type_limits vtl where vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id and agy_loc_id = :p_agy_id and iep_level = :iepLevel and visit_type = offender_visits.visit_type and reinstate_flag = 'Y' and vcl.active_flag = 'Y')

--select COUNT(*) count, coalesce(SUM((end_time - start_time) * 24), 0 * '1 minute'::interval) as time from offender_visits where offender_visit_id in ( select offender_visit_id from OFFENDER_VISIT_VISITORS where OFFENDER_BOOK_ID = :p_bkg_id and visit_type = :v_vis_tp and offender_visits.visit_config_type =(select visit_config_type from agency_visit_config avc where agy_loc_id =:p_agy_id)) and (visit_type in ( select distinct visit_type from visit_cycle_limits vcl, visit_type_limits vtl where vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id and agy_loc_id = :p_agy_id and visit_config_type_value = :iepLevel and vcl.active_flag = 'Y') or ((visit_type = :v_vis_tp and (:v_vis_tp::text is not null and :v_vis_tp::text <> '')) or coalesce(:v_vis_tp::text, '') = '') ) and visit_date between :p_cyc_start and :p_cyc_end and visit_status not in ( select 'CANC' from visit_cycle_limits vcl, visit_type_limits vtl where vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id and agy_loc_id = :p_agy_id and visit_config_type_value = :iepLevel and visit_type = offender_visits.visit_type and reinstate_flag = 'Y' and vcl.active_flag = 'Y' )

select COUNT(*) count, coalesce(SUM((end_time - start_time) * 24), 0 * '1 minute'::interval) as time from offender_visits where offender_visit_id in ( select offender_visit_id from OFFENDER_VISIT_VISITORS where OFFENDER_BOOK_ID = :p_bkg_id and visit_type = :v_vis_tp ) and (visit_type in ( select distinct visit_type from visit_cycle_limits vcl, visit_type_limits vtl where vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id and agy_loc_id = :p_agy_id and visit_config_type_value = :iepLevel and vcl.active_flag = 'Y') or ((visit_type = :v_vis_tp and (:v_vis_tp::text is not null and :v_vis_tp::text <> '')) or coalesce(:v_vis_tp::text, '') = '') ) and visit_date between :p_cyc_start and :p_cyc_end and visit_status not in ( select 'CANC' from visit_cycle_limits vcl, visit_type_limits vtl where vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id and agy_loc_id = :p_agy_id and visit_config_type_value = :iepLevel and visit_type = offender_visits.visit_type and reinstate_flag = 'Y' and vcl.active_flag = 'Y' )
		}
		
OIMVLIMIT_IEP_TOT_TYPE_CUR {

	 SELECT max_hrs_type,
                max_visits_type
           FROM visit_type_limits vtl
          WHERE visit_cycle_limit_id = :p_lim_id
            AND visit_type = :p_vis_tp
            AND active_flag = 'Y'

}
OIMVLIMIT_IEP_USED_VIS_CUR_TOTAL_USED{
--select
--	COUNT(*) count,
--	      coalesce(SUM((end_time - start_time) * 24), 0 * '1 minute'::interval) as time
--from
--	offender_visits
--where
--	offender_book_id =:p_bkg_id
--	and 
--	visit_config_type =(
--	select
--		visit_config_type
--	from
--		agency_visit_config
--	where
--		agy_loc_id =:pAgyId)
--	and visit_status not in ('CANC') and  visit_date between :startDate and :endDate 

select
	COUNT(*) count,
	      coalesce(SUM((end_time - start_time) * 24), 0 * '1 minute'::interval) as time
from
	offender_visits
where
	offender_book_id =:p_bkg_id
	
	and visit_status not in ('CANC') and  visit_date between :startDate and :endDate 
}