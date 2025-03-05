
OCUAVLOC_AVLLOC_FIND_V_OCUAVLOC_AVAILABLE {
 	SELECT AGY_LOC_ID ,WEEK_DAY ,INTERNAL_LOCATION_ID ,AGENCY_VISIT_SLOT_ID ,MAX_GROUPS ,MAX_ADULTS ,CAPACITY ,GROUPS_BOOKED ,TOTAL_BOOKED ,ADULTS_BOOKED ,DESCRIPTION ,START_TIME ,END_TIME ,VISIT_DATE   FROM V_OCUAVLOC_AVAILABLE  /* where  */
}
OCUAVLOC_FBOLOC_FIND_V_OCUAVLOC_UNAVAILABLE {
 	SELECT AGY_LOC_ID ,WEEK_DAY ,INTERNAL_LOCATION_ID ,AGENCY_VISIT_SLOT_ID ,MAX_GROUPS ,MAX_ADULTS ,CAPACITY ,GROUPS_BOOKED ,TOTAL_BOOKED ,ADULTS_BOOKED ,DESCRIPTION ,START_TIME ,END_TIME ,VISIT_DATE   FROM V_OCUAVLOC_UNAVAILABLE  /* where  */
}

OCUAVLOC_AVL_LOC_PREQUERYPRE-QUERY {
	SELECT INTERNAL_LOCATION_ID FROM V_OCUAVLOC_UNAVAILABLE WHERE VISIT_DATE = :PARAMETER.P_VISIT_DATE AND AGY_LOC_ID = :PARAMETER.P_AGY_LOC_ID AND WEEK_DAY = :PARAMETER.P_WEEK_DAY AND START_TIME = :PARAMETER.P_START_TIME)
}
OCUAVLOC_AVL_LOC_RECHECK_TIME_SLOT {
SELECT AVS.MAX_GROUPS,
                AVS.MAX_ADULTS,
                ILU.CAPACITY
           FROM AGENCY_VISIT_SLOTS      AVS,
                INT_LOC_USAGE_LOCATIONS ILU
          WHERE AVS.AGENCY_VISIT_SLOT_ID = :AGENCY_VISIT_SLOT_ID
            AND AVS.INTERNAL_LOCATION_ID = ILU.INTERNAL_LOCATION_ID
}
OCUAVLOC_AVL_LOC_RECHECK_TIME_SLOT_CURSOR_C_VIS {          
select
	COUNT(distinct ov.offender_visit_id) groups_booked,
	COUNT(distinct ovv.offender_visit_visitor_id) total_booked,
	SUM(tag_visits_is_adult(case when ovv.offender_book_id is null then ovv.person_id else ovv.offender_book_id end, case 
    when ovv.offender_book_id is null then 'PER' else 'OFF' end, sp.profile_value::bigint, ov.visit_date)) adults_booked
from
	offender_visits ov
inner join offender_visit_visitors ovv on
	ovv.offender_visit_id = ov.offender_visit_id,
	system_profiles sp
where
	ovv.outcome_reason_code is null
	and ov.offender_visit_id = :OFFENDER_VISIT_ID
	and sp.profile_type = 'CLIENT'
	and sp.profile_code = 'VISIT_AGE'
	and ov.visit_status in (
        'SCH',
        'A'
    )
	and ov.visit_date = :VISIT_DATE
group by
	ov.agency_visit_slot_id
}
OCUAVLOC_AVL_LOC_RECHECK_TIME_SLOT_CURSOR_C_ALL {
select
	COUNT(distinct OV.OFFENDER_VISIT_ID) GROUPS_BOOKED,
	COUNT(distinct OVV.OFFENDER_VISIT_VISITOR_ID) TOTAL_BOOKED,
	SUM(TAG_VISITS_IS_ADULT(case when OVV.OFFENDER_BOOK_ID is null then OVV.PERSON_ID else OVV.OFFENDER_BOOK_ID end, case when OVV.OFFENDER_BOOK_ID is null then 'PER' else 'OFF' end, SP.PROFILE_VALUE::bigint, OV.VISIT_DATE)) ADULTS_BOOKED
from
	OFFENDER_VISITS OV,
	OFFENDER_VISIT_VISITORS OVV,
	SYSTEM_PROFILES SP
where
	OV.OFFENDER_VISIT_ID = OVV.OFFENDER_VISIT_ID
	and OVV.OUTCOME_REASON_CODE is null
	and OV.AGENCY_VISIT_SLOT_ID = :P_AGENCY_VISIT_SLOT_ID
	and OV.OFFENDER_VISIT_ID != :P_OFFENDER_VISIT_ID
	and SP.PROFILE_TYPE = 'CLIENT'
	and SP.PROFILE_CODE = 'VISIT_AGE'
	and OV.VISIT_STATUS in ('SCH', 'A')
	and OV.VISIT_DATE =:VISIT_DATE
group by
	OV.AGENCY_VISIT_SLOT_ID
 }
 
OCUAVLOC_TEMPORARY_TABLE_CREATE_TABLE_AVAILABLE{
 CREATE OR REPLACE VIEW v_ocuavloc_available (agy_loc_id, week_day, internal_location_id, agency_visit_slot_id, max_groups, max_adults, capacity, groups_booked, total_booked, adults_booked, description, start_time, end_time, visit_date) AS SELECT avs.AGY_LOC_ID,
avs.WEEK_DAY,
avs.INTERNAL_LOCATION_ID,
avs.AGENCY_VISIT_SLOT_ID,
avs.MAX_GROUPS,
avs.MAX_ADULTS,
avs.CAPACITY,
avs.GROUPS_BOOKED,
avs.TOTAL_BOOKED,
avs.ADULTS_BOOKED,
avs.DESCRIPTION,
avs.START_TIME,
avs.END_TIME,
avs.VISIT_DATE
 FROM ocuavloc_tmp avs, ocuavloc_tmp ot
WHERE avs.agy_loc_id = ot.agy_loc_id
AND avs.week_day = ot.week_day
AND avs.internal_location_id = ot.internal_location_id
AND avs.agency_visit_slot_id = ot.agency_visit_slot_id
AND (((coalesce(avs.max_groups, 0)      = 0
  AND coalesce(avs.max_adults, 0)         = 0)
  AND coalesce(ot.total_booked, 0)       < coalesce(avs.capacity, 0))
  OR ((coalesce(avs.max_groups, 0)       != 0
  AND coalesce(avs.max_adults, 0)         = 0)
  AND (coalesce(ot.groups_booked, 0)     < coalesce(avs.max_groups, 0)))
  OR ((coalesce(avs.max_groups, 0)        = 0
  AND coalesce(avs.max_adults, 0)        != 0)
  AND coalesce(ot.adults_booked, 0)      < coalesce(avs.max_adults, 0))
  OR ((coalesce(avs.max_groups, 0)       != 0
  AND coalesce(avs.max_adults, 0)        != 0)
  AND coalesce(ot.adults_booked, 0)      < coalesce(avs.max_adults, 0)
  AND (coalesce(ot.groups_booked, 0)     < coalesce(avs.max_groups, 0))))
 }
OCUAVLOC_TEMPORARY_TABLE_CREATE_TABLE_UN_AVAILABLE{
 create or replace VIEW V_OCUAVLOC_UNAVAILABLE ( AGY_LOC_ID, WEEK_DAY, INTERNAL_LOCATION_ID, AGENCY_VISIT_SLOT_ID, MAX_GROUPS, MAX_ADULTS, CAPACITY, GROUPS_BOOKED, TOTAL_BOOKED, ADULTS_BOOKED, DESCRIPTION, START_TIME, END_TIME, VISIT_DATE ) AS
SELECT
avs.AGY_LOC_ID,
avs.WEEK_DAY,
avs.INTERNAL_LOCATION_ID,
avs.AGENCY_VISIT_SLOT_ID,
avs.MAX_GROUPS,
avs.MAX_ADULTS,
avs.CAPACITY,
avs.GROUPS_BOOKED,
avs.TOTAL_BOOKED,
avs.ADULTS_BOOKED,
avs.DESCRIPTION,
avs.START_TIME,
avs.END_TIME,
avs.VISIT_DATE
FROM ocuavloc_tmp avs, ocuavloc_tmp ot
WHERE avs.agy_loc_id = ot.agy_loc_id
AND avs.week_day = ot.week_day
AND avs.internal_location_id = ot.internal_location_id
AND avs.agency_visit_slot_id = ot.agency_visit_slot_id
AND  ((coalesce(ot.total_booked, 0) >= coalesce(avs.capacity, 999)) OR
      (coalesce(ot.groups_booked, 0) >= coalesce(avs.max_groups, 999)) OR
      (coalesce(ot.adults_booked, 0) >= coalesce(avs.max_adults, 999)))
}