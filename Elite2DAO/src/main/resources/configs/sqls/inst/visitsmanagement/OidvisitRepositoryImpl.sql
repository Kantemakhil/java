
OIDVISIT_FIND_RGVISITTYPE {
 	SELECT REF_CODE.DESCRIPTION ,        REF_CODE.CODE   FROM REFERENCE_CODES REF_CODE  WHERE DOMAIN = 'VISIT_TYPE'      AND (   (ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL )           OR ('' = 'ENTER-QUERY' )           ) ORDER BY LIST_SEQ ,REF_CODE.DESCRIPTION ,REF_CODE.CODE
}

OIDVISIT_FIND_RGMOVECANCRS {
 	SELECT REF_CODE.DESCRIPTION ,        REF_CODE.CODE   FROM REFERENCE_CODES REF_CODE  WHERE DOMAIN = 'MOVE_CANC_RS'      AND (   ( REF_CODE.CODE != 'VO_CANCEL' AND ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL )           OR ('' = 'ENTER-QUERY' )           ) ORDER BY LIST_SEQ ,REF_CODE.DESCRIPTION ,REF_CODE.CODE
}

OIDVISIT_FIND_RGVISITTIMESLOTS {
select distinct TO_CHAR (time_slot_seq) DESCRIPTION , TO_CHAR (START_TIME , 'HH24:MI' ) stimetemp , TO_CHAR (END_TIME , 'HH24:MI' ) WEEK_DAY, to_char(START_TIME, 'DD/MM/YYYY HH24:MI:SS') || ',' || TO_CHAR (END_TIME , 'HH24:MI' ) CODE, ACTIVE_FLAG, case when (ACTIVE_FLAG = 'Y' or '' = 'ENTER-QUERY' ) and AGY_LOC_ID = coalesce(:visitAgyLocId ,:agyLocId ) and WEEK_DAY = TO_CHAR ( to_date(:visitDate::text, :dateFormat::text), 'DY'::text )::text then 'Y' else 'N' end agyLocId from AGENCY_VISIT_TIMES order by AGYLOCID desc, DESCRIPTION asc
}

OIDVISIT_FIND_RGVISCOMPLETE {
 	SELECT REF_CODE.DESCRIPTION ,        REF_CODE.CODE          FROM REFERENCE_CODES REF_CODE  WHERE DOMAIN = 'VIS_COMPLETE'      AND CODE NOT IN ('CANC' ,'SCH' )      AND (   (ACTIVE_FLAG = 'Y' )           OR ('' = 'ENTER-QUERY' )           )  ORDER BY REF_CODE.LIST_SEQ , REF_CODE.DESCRIPTION ,REF_CODE.CODE
}

OIDVISIT_OFFVST_FIND_V_OFFENDER_VISITS {
select ov.offender_visit_id, ov.offender_book_id as visit_offender_book_id, ov.comment_text, ov.raised_incident_type, ov.raised_incident_number, ov.visit_date, ov.start_time, ov.end_time, ov.visit_type, ov.visit_status, ov.visit_internal_location_id, ov.agency_visit_slot_id, ov.agy_loc_id, ovv.offender_visit_visitor_id, ovv.offender_book_id, off.offender_id, off.last_name as offender_last_name, off.first_name as offender_first_name, off.offender_id_display, case when ovv.offender_book_id = ov.offender_book_id then 'Y'::text else 'N'::text end as visit_owner_flag, ovv.event_status, coalesce(ov.event_outcome, 'ATT'::character varying) as event_outcome, ov.outcome_reason_code, ovv.comment_text as visitor_comment_text, ovv.event_id, tag_schedule_check_sum(coalesce(greatest(ovv.modify_datetime, ov.modify_datetime), ovv.create_datetime)) as check_sum, ov.create_datetime, ov.modify_datetime from offender_visits ov, offenders off, offender_visit_visitors ovv left join offender_bookings bkg on ovv.offender_book_id = bkg.offender_book_id where ov.offender_visit_id = ovv.offender_visit_id and bkg.offender_id = off.offender_id and ovv.event_id > 0 and not (coalesce(ovv.offender_book_id::text, ''::text) = ''::text and ov.visit_date is not null and ov.visit_date::text <> ''::text) and ovv.offender_book_id = :offenderBookId order by ov.VISIT_DATE desc
}
OIDVISIT_OFFVST_INSERT_V_OFFENDER_VISITS {
insert into V_OFFENDER_VISITS(OFFENDER_VISIT_ID , VISIT_OFFENDER_BOOK_ID , COMMENT_TEXT , RAISED_INCIDENT_TYPE , RAISED_INCIDENT_NUMBER , VISIT_DATE , START_TIME , END_TIME , VISIT_TYPE , VISIT_STATUS , VISIT_INTERNAL_LOCATION_ID , AGENCY_VISIT_SLOT_ID , AGY_LOC_ID , OFFENDER_VISIT_VISITOR_ID , OFFENDER_BOOK_ID , OFFENDER_ID , OFFENDER_LAST_NAME , OFFENDER_FIRST_NAME , OFFENDER_ID_DISPLAY , VISIT_OWNER_FLAG , EVENT_STATUS , EVENT_OUTCOME , OUTCOME_REASON_CODE , VISITOR_COMMENT_TEXT , EVENT_ID , CHECK_SUM, create_datetime) values(nextval('offender_visit_id'), :visitOffenderBookId , :commentText , :raisedIncidentType , :raisedIncidentNumber , :visitDate , :startTime , :endTime , :visitType , :visitStatus , :visitInternalLocationId , :agencyVisitSlotId , :agyLocId , :offenderVisitVisitorId , :offenderBookId , :offenderId , :offenderLastName , :offenderFirstName , :offenderIdDisplay , :visitOwnerFlag , :eventStatus , :eventOutcome , :outcomeReasonCode , :visitorCommentText , nextval('event_id') , :checkSum, current_timestamp)
}

OIDVISIT_OFFVST_INSERT_OFFENDER_VISITS {
insert into OFFENDER_VISITS (OFFENDER_VISIT_ID, OFFENDER_BOOK_ID, COMMENT_TEXT , RAISED_INCIDENT_TYPE , RAISED_INCIDENT_NUMBER , VISIT_DATE , START_TIME, END_TIME, EVENT_OUTCOME, VISIT_TYPE, VISIT_STATUS, OUTCOME_REASON_CODE, VISIT_INTERNAL_LOCATION_ID, AGENCY_VISIT_SLOT_ID, AGY_LOC_ID, create_user_id, create_datetime, modify_datetime, record_user_id ) values (:offenderVisitId, :offenderBookId, :commentText, :raisedIncidentType , :raisedIncidentNumber , :visitDate , :startTime , :endTime, :eventOutcome , :visitType , :visitStatus , :outcomeReasonCode, :visitInternalLocationId, :agencyVisitSlotId, :agyLocId, :userName, current_timestamp, NULL, :recordUserId )
}
OIDVISIT_OFFVST_UPDATE_OFFENDER_VISITS {
update OFFENDER_VISITS set OFFENDER_BOOK_ID = :offenderBookId, COMMENT_TEXT = :commentText , VISIT_DATE = :visitDate , START_TIME = :startTime, END_TIME = :endTime, EVENT_OUTCOME = :eventOutcome, VISIT_TYPE = :visitType, VISIT_STATUS = :visitStatus, OUTCOME_REASON_CODE = :outcomeReasonCode, VISIT_INTERNAL_LOCATION_ID = :visitInternalLocationId, AGENCY_VISIT_SLOT_ID = :agencyVisitSlotId, AGY_LOC_ID = :agyLocId, modify_user_id = :userName, modify_datetime = current_timestamp, record_user_id =:recordUserId where OFFENDER_VISIT_ID = :offenderVisitId 
}

OIDVISIT_OFFVSTPERS_FIND_V_OFFENDER_VISIT_VISITORS {
  select OFFENDER_VISIT_ORDER_ID , OFFENDER_VISIT_ID , VISIT_OFFENDER_BOOK_ID , OFFENDER_VISIT_VISITOR_ID , OFFENDER_BOOK_ID , PERSON_ID , GROUP_LEADER_FLAG , COMMENT_TEXT , EVENT_OUTCOME , OUTCOME_REASON_CODE , EVENT_ID , EVENT_STATUS, CREATE_DATETIME, MODIFY_DATETIME from V_OFFENDER_VISIT_VISITORS where OFFENDER_BOOK_ID is null and PERSON_ID is not null and ( ( :offenderVisitId is not null and :offenderVisitId = OFFENDER_VISIT_ID and :offenderBookId = VISIT_OFFENDER_BOOK_ID ) ) order by GROUP_LEADER_FLAG desc, tag_visits_visitor_lastname_firstname(PERSON_ID) 
}
OIDVISIT_OFFVSTPERS_INSERT_V_OFFENDER_VISIT_VISITORS {
insert into OFFENDER_VISIT_VISITORS (OFFENDER_VISIT_ID, PERSON_ID, GROUP_LEADER_FLAG, OFFENDER_VISIT_VISITOR_ID, COMMENT_TEXT, EVENT_OUTCOME, OFFENDER_BOOK_ID, EVENT_ID, create_user_id, create_datetime, modify_datetime) values (:offenderVisitId, :personId, coalesce(:groupLeaderFlag, 'N'), nextval('OFFENDER_VISIT_VISITOR_ID'), :commentText, :eventOutcome, :offenderBookId, nextval('event_id'), :userName, current_timestamp, NULL)
}

OIDVISIT_OFFVSTPERS_UPDATE_V_OFFENDER_VISIT_VISITORS {
  update OFFENDER_VISIT_VISITORS set PERSON_ID = :personId, GROUP_LEADER_FLAG = coalesce (:groupLeaderFlag, 'N'), COMMENT_TEXT = :commentText, EVENT_OUTCOME = :eventOutcome, OFFENDER_BOOK_ID = :offenderBookId, modify_user_id = :userName, modify_datetime = current_timestamp where OFFENDER_VISIT_VISITOR_ID = :offenderVisitVisitorId 
}

OIDVISIT_OFFVSTPERS_DELETE_V_OFFENDER_VISIT_VISITORS { 
	DELETE FROM OFFENDER_VISIT_VISITORS where OFFENDER_VISIT_VISITOR_ID = :offenderVisitVisitorId 
}

OIDVISIT_IMAGES1_FIND_IMAGES {
 	SELECT IMAGE_ID ,CAPTURE_DATE ,IMAGE_OBJECT_TYPE ,IMAGE_OBJECT_ID ,IMAGE_OBJECT_SEQ ,IMAGE_VIEW_TYPE ,IMAGE_THUMBNAIL ,ACTIVE_FLAG ,ORIENTATION_TYPE ,SEAL_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID   FROM IMAGES  WHERE  IMAGE_OBJECT_TYPE = 'PERSON' AND IMAGE_OBJECT_ID = :personId AND IMAGE_THUMBNAIL IS NOT NULL AND ACTIVE_FLAG = 'Y'
}
OIDVISIT_IMAGES_OFF_FIND_IMAGES {
SELECT IMAGE_ID ,CAPTURE_DATE ,IMAGE_OBJECT_TYPE ,IMAGE_OBJECT_ID ,IMAGE_OBJECT_SEQ ,IMAGE_VIEW_TYPE ,IMAGE_THUMBNAIL ,ACTIVE_FLAG ,ORIENTATION_TYPE ,SEAL_FLAG ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID   FROM IMAGES  WHERE  IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMAGE_OBJECT_ID = :personId AND IMAGE_THUMBNAIL IS NOT NULL
}
OIDVISIT_IMAGES1_INSERT_IMAGES {
insert into IMAGES(IMAGE_ID , CAPTURE_DATE , IMAGE_OBJECT_TYPE , IMAGE_OBJECT_ID , IMAGE_OBJECT_SEQ , IMAGE_VIEW_TYPE , IMAGE_THUMBNAIL , ACTIVE_FLAG , ORIENTATION_TYPE , SEAL_FLAG , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME ) values(:imageId , :captureDate , :imageObjectType , :imageObjectId , :imageObjectSeq , :imageViewType , :imageThumbnail , :activeFlag , :orientationType , :sealFlag , current_timestamp , :createUserId , NULL) 
}

OIDVISIT_IMAGES1_UPDATE_IMAGES {
 update IMAGES set IMAGE_ID = :imageId , CAPTURE_DATE = :captureDate , IMAGE_OBJECT_TYPE = :imageObjectType , IMAGE_OBJECT_ID = :imageObjectId , IMAGE_OBJECT_SEQ = :imageObjectSeq , IMAGE_VIEW_TYPE = :imageViewType , IMAGE_THUMBNAIL = :imageThumbnail , ACTIVE_FLAG = :activeFlag , ORIENTATION_TYPE = :orientationType , SEAL_FLAG = :sealFlag , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId 
}

OIDVISIT_OFFVSTOFF_FIND_OFFENDER_VISIT_VISITORS {
 SELECT OFFENDER_VISIT_ID ,PERSON_ID ,GROUP_LEADER_FLAG ,OFFENDER_VISIT_VISITOR_ID ,ASSISTED_VISIT_FLAG ,COMMENT_TEXT ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,EVENT_OUTCOME ,OUTCOME_REASON_CODE ,OFFENDER_BOOK_ID ,EVENT_ID ,EVENT_STATUS ,SEAL_FLAG FROM OFFENDER_VISIT_VISITORS WHERE OFFENDER_BOOK_ID is not null and PERSON_ID is null AND OFFENDER_BOOK_ID != :offenderBookId AND :offenderVisitId = OFFENDER_VISIT_ID order by CREATE_DATETIME DESC 
}
OIDVISIT_OFFVST_INSERT_OFFENDER_VISIT_VISITORS {
insert into OFFENDER_VISIT_VISITORS(OFFENDER_VISIT_ID , PERSON_ID , GROUP_LEADER_FLAG , OFFENDER_VISIT_VISITOR_ID , ASSISTED_VISIT_FLAG , COMMENT_TEXT , EVENT_OUTCOME , OUTCOME_REASON_CODE , OFFENDER_BOOK_ID , EVENT_ID , EVENT_STATUS, create_user_id, create_datetime, modify_datetime) values(:offenderVisitId , :personId , :groupLeaderFlag , nextval('OFFENDER_VISIT_VISITOR_ID') , :assistedVisitFlag , :commentText , :eventOutcome , :outcomeReasonCode , :offenderBookId , :eventId , :eventStatus, :userName, current_timestamp, NULL) 
}

OIDVISIT_OFFVSTOFF_INSERT_OFFENDER_VISIT_VISITORS {
insert into OFFENDER_VISIT_VISITORS (OFFENDER_VISIT_ID, PERSON_ID, GROUP_LEADER_FLAG, OFFENDER_VISIT_VISITOR_ID, COMMENT_TEXT, EVENT_OUTCOME, OFFENDER_BOOK_ID, EVENT_ID, create_user_id, create_datetime, modify_datetime) values (:offenderVisitId, :personId, coalesce(:groupLeaderFlag, 'N'), nextval('OFFENDER_VISIT_VISITOR_ID'), :commentText, :eventOutcome, :offenderBookId, nextval('EVENT_ID'), :createUserId, current_timestamp, NULL) 
}
OIDVISIT_OFFVSTOFF_UPDATE_OFFENDER_VISIT_VISITORS {
update offender_visit_visitors set PERSON_ID = :personId, GROUP_LEADER_FLAG = coalesce(:groupLeaderFlag, 'N'), COMMENT_TEXT = :commentText, EVENT_OUTCOME = :eventOutcome, OFFENDER_BOOK_ID = :offenderBookId, modify_user_id = :modifyUserId, modify_datetime = current_timestamp where OFFENDER_VISIT_VISITOR_ID = :offenderVisitVisitorId 
}

OIDVISIT_OFFVSTOFF_DELETE_OFFENDER_VISIT_VISITORS { 
	 DELETE FROM OFFENDER_VISIT_VISITORS WHERE OFFENDER_VISIT_VISITOR_ID = :offenderVisitVisitorId
}


OIDVISIT_OFF_BKG_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM V_OFFENDER_VISITS V WHERE V.OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIDVISIT_DUPLICATE_VISITOR_PERSON {
	SELECT 1 FROM V_OFFENDER_VISIT_VISITORS  WHERE OFFENDER_VISIT_ID = :OFFENDERVISITID AND PERSON_ID = :p_person_id limit 1
}

OIDVISIT_OFF_VST_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM OFFENDER_VISIT_VISITORS O WHERE O.OFFENDER_VISIT_ID = :OFFENDERVISITID
}

OIDVISIT_OFF_VST_PERS_ONDELETE {
	DELETE FROM OFFENDER_VISIT_VISITORS WHERE OFFENDER_VISIT_VISITOR_ID = :OFFENDERVISITVISITORID
}

OIDVISIT_OIDVISIT_WHENNEWRECORDINSTANCE_ {
	SELECT PROFILE_VALUE FROM   SYSTEM_PROFILES WHERE  PROFILE_TYPE = 'CLIENT' AND    PROFILE_CODE = 'PENDING_STAT'
}

OIDVISIT_RECHECK_TIME_SLOT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'VISIT_AGE'
}

OIDVISIT_RECHECK_TIME_SLOT_ { -- below query is not used in repoImpl
	SELECT AVS.MAX_GROUPS, AVS.MAX_ADULTS, ILU.CAPACITY FROM AGENCY_VISIT_SLOTS      AVS, INT_LOC_USAGE_LOCATIONS ILU WHERE AVS.AGENCY_VISIT_SLOT_ID = P_AGENCY_VISIT_SLOT_ID AND AVS.INTERNAL_LOCATION_ID = ILU.INTERNAL_LOCATION_ID
}

OIDVISIT_RECHECK_TIME_SLOTRECHECK_TIME_SLOT { -- below query is not used in repoImpl
	SELECT COUNT(DISTINCT OV.OFFENDER_VISIT_ID) GROUPS_BOOKED, COUNT(DISTINCT OVV.OFFENDER_VISIT_VISITOR_ID) TOTAL_BOOKED, SUM(TAG_VISITS.IS_ADULT(DECODE(OVV.OFFENDER_BOOK_ID, NULL, OVV.PERSON_ID, OVV.OFFENDER_BOOK_ID), DECODE(OVV.OFFENDER_BOOK_ID, NULL, 'PER', 'OFF'), TO_NUMBER(P_ADULT_AGE), OV.VISIT_DATE)) ADULTS_BOOKED FROM OFFENDER_VISITS         OV, OFFENDER_VISIT_VISITORS OVV WHERE OVV.OFFENDER_VISIT_ID = OV.OFFENDER_VISIT_ID AND OVV.OUTCOME_REASON_CODE IS NULL AND OV.OFFENDER_VISIT_ID = P_OFFENDER_VISIT_ID AND OV.VISIT_STATUS IN ('SCH', 'A') AND TRUNC(OV.VISIT_DATE) = TRUNC(P_VISIT_DATE) AND (OVV.OFFENDER_BOOK_ID != P_OFFENDER_BOOK_ID OR OVV.OFFENDER_BOOK_ID IS NULL) GROUP BY OV.AGENCY_VISIT_SLOT_ID
}

OIDVISIT_RECHECK_TIME_SLOTRECHECK_TIME_SLOT { -- below query is not used in repoImpl
	SELECT COUNT(DISTINCT OV.OFFENDER_VISIT_ID) GROUPS_BOOKED, COUNT(DISTINCT OVV.OFFENDER_VISIT_VISITOR_ID) TOTAL_BOOKED, SUM(TAG_VISITS.IS_ADULT(DECODE(OVV.OFFENDER_BOOK_ID, NULL, OVV.PERSON_ID, OVV.OFFENDER_BOOK_ID), DECODE(OVV.OFFENDER_BOOK_ID, NULL, 'PER', 'OFF'), TO_NUMBER(P_ADULT_AGE), OV.VISIT_DATE)) ADULTS_BOOKED FROM OFFENDER_VISITS         OV, OFFENDER_VISIT_VISITORS OVV WHERE OV.OFFENDER_VISIT_ID = OVV.OFFENDER_VISIT_ID AND OVV.OUTCOME_REASON_CODE IS NULL AND OV.AGENCY_VISIT_SLOT_ID = P_AGENCY_VISIT_SLOT_ID AND OV.OFFENDER_VISIT_ID != P_OFFENDER_VISIT_ID AND OV.VISIT_STATUS IN ('SCH', 'A') AND TRUNC(OV.VISIT_DATE) = TRUNC(P_VISIT_DATE) AND (OVV.OFFENDER_BOOK_ID != P_OFFENDER_BOOK_ID OR OVV.OFFENDER_BOOK_ID IS NULL) GROUP BY OV.AGENCY_VISIT_SLOT_ID
}

OIDVISIT_CHECK_VISITOR_LIMITCHECK_VISITOR_LIMIT { -- below query is not used in repoImpl
	SELECT COUNT (DISTINCT OVV.OFFENDER_VISIT_VISITOR_ID) TOTAL_BOOKED FROM OFFENDER_VISITS OV, OFFENDER_VISIT_VISITORS OVV WHERE OVV.OFFENDER_VISIT_ID = OV.OFFENDER_VISIT_ID AND OVV.OUTCOME_REASON_CODE IS NULL AND OV.OFFENDER_VISIT_ID = P_OFFENDER_VISIT_ID AND OV.VISIT_STATUS IN ('SCH', 'A') AND TRUNC(OV.VISIT_DATE) = TRUNC(P_VISIT_DATE) AND (OVV.OFFENDER_BOOK_ID != P_OFFENDER_BOOK_ID OR OVV.OFFENDER_BOOK_ID IS NULL) GROUP BY OV.AGENCY_VISIT_SLOT_ID
}

OIDVISIT_CHECK_VISITOR_LIMIT_ {
	select
	MAX_VISITORS_TYPE
from
	VISIT_TYPE_LIMITS VTL,
	VISIT_CYCLE_LIMITS VCL
where
	VTL.VISIT_CYCLE_LIMIT_ID = VCL.VISIT_CYCLE_LIMIT_ID
	and VCL.AGY_LOC_ID = :P_AGY_LOC_ID
	and VTL.VISIT_TYPE = :P_VISIT_TYPE
}

OIDVISIT_GET_IMAGE_COL_NAME_ { -- below query is not used in repoImpl
	SELECT COLUMN_NAME FROM ALL_TAB_COLS WHERE TABLE_NAME='IMAGES' AND COLUMN_NAME IN ('IMAGE_THUMBNAIL', 'IMAGE_DATA')
}

OIDVISIT_CLASSPENDING {
SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'PENDING_STAT' 
}
GET_TIME_SLOT_DETAILS {
 select T.START_TIME, T.END_TIME, AIL.DESCRIPTION, S.INTERNAL_LOCATION_ID visitInternalLocationId, to_char(T.START_TIME, 'DD/MM/YYYY HH24:MI:SS') || ',' || TO_CHAR (T.END_TIME ,'HH24:MI' ) as code  from AGENCY_VISIT_SLOTS S, AGENCY_VISIT_TIMES T, AGENCY_INTERNAL_LOCATIONS AIL where S.AGENCY_VISIT_SLOT_ID = :P_AGENCY_VISIT_SLOT_ID and T.AGY_LOC_ID = S.AGY_LOC_ID and T.WEEK_DAY = S.WEEK_DAY and T.TIME_SLOT_SEQ = S.TIME_SLOT_SEQ and AIL.INTERNAL_LOCATION_ID = S.INTERNAL_LOCATION_ID 
}
            
GETOFFENDERBOOKID_FROM_OFFENDERIDDISPLAY {
SELECT OFFENDER_BOOK_ID FROM V_NAME_SEARCH2_FN(:userId) WHERE OFFENDER_ID_DISPLAY = :offenderIdDisplay  limit  1
}            
OIDVISIT_DUPLICATE_VISIT {
select 'X' from OFFENDER_VISITS OV where OV.VISIT_DATE = :P_DATE and OV.OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID and OV.VISIT_STATUS = 'SCH' and ((:P_START_TIME > OV.START_TIME and :P_START_TIME < OV.END_TIME) or (:P_END_TIME > OV.START_TIME and :P_END_TIME < OV.END_TIME)) limit 1 
}
OIDVISIT_DUPLICATE_VISIT_QUERY_TWO {
select 'X' from OFFENDER_VISITS OV where OV.VISIT_DATE = :P_DATE and OV.OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID and OV.VISIT_STATUS = 'SCH' and OV.OFFENDER_VISIT_ID != :P_OFFENDER_VISIT_ID and ((:P_START_TIME > OV.START_TIME and :P_START_TIME < OV.END_TIME) or (:P_END_TIME > OV.START_TIME and :P_END_TIME < OV.END_TIME)) limit 1 
}

OIDVISIT_VISITCYCLELIMITS {
select 'X' from visit_cycle_limits vcl where agy_loc_id = :agyLocId  and vcl.active_flag = 'Y' limit 1 		 
}

OIDVISIT_VISITCYCLELIMITS_WITH_VISITTYPE{
 select
	'X'
from
	visit_type_limits vtl
where
	visit_cycle_limit_id = (
	select
		visit_cycle_limit_id
	from
		visit_cycle_limits vcl
	where
		agy_loc_id = :agyLocId
		and vcl.active_flag = 'Y')
	and visit_type = :visitType
	and active_flag = 'Y'
limit 1
}
OIDVIST_GET_ROOT_OFFENDER_ID {
 select O.ROOT_OFFENDER_ID from OFFENDERS O, OFFENDER_BOOKINGS B where B.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID and O.OFFENDER_ID = B.OFFENDER_ID 
}
OIDVISIT_GET_AUTHORISED_OFFENDER {
SELECT 1 FROM OFFENDER_CONTACT_PERSONS WHERE OFFENDER_BOOK_ID = :ROOT_OFFENDER_BOOK_ID AND CONTACT_ROOT_OFFENDER_ID = :CONTACT_ROOT_OFFENDER_ID 
}
OIDVISIT_GET_AUTHORISED_PERSON {
SELECT COUNT(*) FROM OFFENDER_CONTACT_PERSONS WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID AND PERSON_ID = :PERSON_ID AND APPROVED_VISITOR_FLAG ='Y' 
}


OIDVISIT_OVERLAPVISIT_PERSONID {
select ov.offender_book_id l_offender_book_id from offender_visits ov, offender_visit_visitors ovv where ov.offender_visit_id != :p_offender_visit_id and ov.offender_visit_id = ovv.offender_visit_id and ov.visit_date = :p_date and ovv.person_id = :p_person_id and ov.visit_status = 'SCH' and ((:p_start_time > ov.start_time and :p_start_time < ov.end_time) or (:p_end_time > ov.start_time and :p_end_time < ov.end_time)) limit 1 
}

OIDVISIT_OVERLAPVISIT_BOOKID {
 select ov.offender_book_id l_offender_book_id from offender_visits ov, offender_visit_visitors ovv where ov.offender_visit_id != :p_offender_visit_id and ov.offender_visit_id = ovv.offender_visit_id and ov.visit_date = :p_date and ovv.offender_book_id = :p_offender_book_id and ov.visit_status = 'SCH' and ((:p_start_time > ov.start_time and :p_start_time < ov.end_time) or (:p_end_time > ov.start_time and :p_end_time < ov.end_time)) limit 1 
}

OIDVISIT_TOTALBOOKED_VISITOR {
 select COUNT (distinct ovv.offender_visit_visitor_id) total_booked from offender_visits ov, offender_visit_visitors ovv where ovv.offender_visit_id = ov.offender_visit_id and ovv.outcome_reason_code is null and ov.offender_visit_id = :p_offender_visit_id and ov.visit_status in ('SCH', 'A') and ov.visit_date = :p_visit_date and (ovv.offender_book_id != :p_offender_book_id or ovv.offender_book_id is null) group by ov.agency_visit_slot_id 
}

OIDVISIT_MAX_VISITORS_TYPE {
 select
	max_visitors_type
from
	visit_type_limits vtl,
	visit_cycle_limits vcl
where
	vtl.visit_cycle_limit_id = vcl.visit_cycle_limit_id
	and vcl.agy_loc_id = :p_agy_loc_id
	and vtl.visit_type = :p_visit_type limit 1
}

OIDVISIT_FIND_ADULTAGE {
 select profile_value from system_profiles where profile_type = 'CLIENT' and profile_code = 'VISIT_AGE' 
}

OIDVISIT_FIND_C_SLOT{
select avs.max_groups TOTAL_ACTUAL, avs.max_adults OUT_TOTAL, ilu.capacity TOTAL_OTHER_OUT from agency_visit_slots avs, int_loc_usage_locations ilu where avs.agency_visit_slot_id = :p_agency_visit_slot_id and avs.internal_location_id = ilu.internal_location_id limit 1
}

OIDVISIT_FIND_C_VIS {
 select COUNT(distinct ov.offender_visit_id) TOTAL_ACTUAL, COUNT(distinct ovv.offender_visit_visitor_id) OUT_TOTAL, SUM(tag_visits_is_adult(case when ovv.offender_book_id is null then ovv.person_id else ovv.offender_book_id end, case when ovv.offender_book_id is null then 'PER' else 'OFF' end, :p_adult_age::bigint, ov.visit_date)) TOTAL_OTHER_OUT from offender_visits ov, offender_visit_visitors ovv where ovv.offender_visit_id = ov.offender_visit_id and ovv.outcome_reason_code is null and ov.offender_visit_id = :p_offender_visit_id and ov.visit_status in ('SCH', 'A') and ov.visit_date = :p_visit_date and (ovv.offender_book_id != :p_offender_book_id or ovv.offender_book_id is null) group by ov.agency_visit_slot_id
}

OIDVISIT_FIND_C_ALL {
 select COUNT(distinct ov.offender_visit_id) TOTAL_ACTUAL, COUNT(distinct ovv.offender_visit_visitor_id) OUT_TOTAL, SUM(tag_visits_is_adult(case when ovv.offender_book_id is null then ovv.person_id else ovv.offender_book_id end, case when ovv.offender_book_id is null then 'PER' else 'OFF' end, :p_adult_age::bigint, ov.visit_date)) TOTAL_OTHER_OUT from offender_visits ov, offender_visit_visitors ovv where ov.offender_visit_id = ovv.offender_visit_id and ovv.outcome_reason_code is null and ov.agency_visit_slot_id = :p_agency_visit_slot_id and ov.offender_visit_id != :p_offender_visit_id and ov.visit_status in ('SCH', 'A') and ov.visit_date = :p_visit_date and (ovv.offender_book_id != :p_offender_book_id or ovv.offender_book_id is null) group by ov.agency_visit_slot_id 
}

OIDVISIT_DUPLICATE_VISITOR_OFFENDER {
select 1 from offender_visit_visitors where offender_visit_id = :p_offender_visit_id and offender_book_id = :p_offender_book_id limit 1 
}

OIDVISIT_GET_OFFENDER_RESTRICTIONS {
 select count(*) from offender_restrictions ors where ors.offender_book_id = :p_offender_book_id and (ors.effective_date) <= (:p_visit_date) and (ors.expiry_date is null or ors.expiry_date > (:p_visit_date)) 
}

OIDVISIT_GET_OFFENDER_ID{
select O.OFFENDER_ID from OFFENDERS O where O.OFFENDER_ID_DISPLAY = :pOffenderIdDisplay limit 1 
}

OIDVISIT_OFF_BKG_ONCHECKVISITMASTER {
	SELECT OFFENDER_VISIT_ID ,VISIT_OFFENDER_BOOK_ID ,COMMENT_TEXT ,RAISED_INCIDENT_TYPE ,RAISED_INCIDENT_NUMBER ,VISIT_DATE ,START_TIME ,END_TIME ,VISIT_TYPE ,VISIT_STATUS ,VISIT_INTERNAL_LOCATION_ID ,AGENCY_VISIT_SLOT_ID ,AGY_LOC_ID ,OFFENDER_VISIT_VISITOR_ID ,OFFENDER_BOOK_ID ,OFFENDER_ID ,OFFENDER_LAST_NAME ,OFFENDER_FIRST_NAME ,OFFENDER_ID_DISPLAY ,VISIT_OWNER_FLAG ,EVENT_STATUS ,EVENT_OUTCOME ,OUTCOME_REASON_CODE ,VISITOR_COMMENT_TEXT ,EVENT_ID ,CHECK_SUM  FROM V_OFFENDER_VISITS  WHERE OFFENDER_VISIT_ID = :OFFENDERVISITID
}

OIDVISIT_OFF_BKG_ONCHECK_PER_VISITOR_MASTER {
select count(*) from V_OFFENDER_VISIT_VISITORS where OFFENDER_BOOK_ID is null and PERSON_ID is not null and ( ( :offenderVisitId is not null and :offenderVisitId = OFFENDER_VISIT_ID and :offenderBookId = VISIT_OFFENDER_BOOK_ID and EVENT_OUTCOME != 'ABS' ) ) 
}

OIDVISIT_OFF_BKG_ONCHECK_OFF_VISITOR_MASTER {
select count(*) from OFFENDER_VISIT_VISITORS where OFFENDER_BOOK_ID is not null and PERSON_ID is null and :offenderVisitId = OFFENDER_VISIT_ID and EVENT_OUTCOME != 'ABS' 
}

OIDVISIT_OFF_BKG_ONCHECKVISITMASTER {
	SELECT OFFENDER_VISIT_ID ,VISIT_OFFENDER_BOOK_ID ,COMMENT_TEXT ,RAISED_INCIDENT_TYPE ,RAISED_INCIDENT_NUMBER ,VISIT_DATE ,START_TIME ,END_TIME ,VISIT_TYPE ,VISIT_STATUS ,VISIT_INTERNAL_LOCATION_ID ,AGENCY_VISIT_SLOT_ID ,AGY_LOC_ID ,OFFENDER_VISIT_VISITOR_ID ,OFFENDER_BOOK_ID ,OFFENDER_ID ,OFFENDER_LAST_NAME ,OFFENDER_FIRST_NAME ,OFFENDER_ID_DISPLAY ,VISIT_OWNER_FLAG ,EVENT_STATUS ,EVENT_OUTCOME ,OUTCOME_REASON_CODE ,VISITOR_COMMENT_TEXT ,EVENT_ID ,CHECK_SUM  FROM V_OFFENDER_VISITS  WHERE OFFENDER_VISIT_ID = :OFFENDERVISITID AND OFFENDER_BOOK_ID != :offenderBookId  
}

OIDVISIT_GET_VISITORS_RESTRICTIONS{
 select count(*) from ( select vr.effective_date restriction_date, 'GLOB' || rc.code restriction_code from visitor_restrictions vr, reference_codes rc where rc.domain = 'VST_RST_TYPE' and rc.code = vr.visit_restriction_type and vr.person_id = :p_person_id and vr.effective_date <= (:p_visit_date) and (vr.expiry_date is null or vr.expiry_date > (:p_visit_date)) union all select ocr.restriction_effective_date restriction_date, 'REST' || rc.code restriction_code from offender_person_restricts ocr, reference_codes rc where rc.domain = 'VST_RST_TYPE' and rc.code = ocr.restriction_type and ocr.offender_contact_person_id in ( select distinct offender_contact_person_id from offender_contact_persons where person_id = :p_person_id and offender_book_id = :p_offender_book_id) and ocr.restriction_effective_date <= (:p_visit_date) and (ocr.restriction_expiry_date is null or ocr.restriction_expiry_date > (:p_visit_date))) vst 
}

OIDVISIT_GET_VISITORS_RESTRICTIONS_OVERLAPDETAILS {
select o.offender_id_display || ' - ' || o.last_name || ', ' || o.first_name from offenders o, offender_bookings ob where ob.offender_book_id = :l_offender_book_id and ob.offender_id = o.offender_id limit 1 
}

OIDVISIT_OVERLAPVISIT_PERSON_ID {
select ov.offender_book_id from offender_visits ov, offender_visit_visitors ovv where ov.offender_visit_id != :p_offender_visit_id and ov.offender_visit_id = ovv.offender_visit_id and ov.visit_date = (:p_date) and ovv.person_id = :p_person_id and ov.visit_status = 'SCH' and ((:p_start_time > ov.start_time and :p_start_time < ov.end_time) or (:p_end_time > ov.start_time and :p_end_time < ov.end_time)) limit 1 
}

OIDVISIT_OFF_BKG_ONCHECKVISITMASTER_PERS {
	SELECT OFFENDER_VISIT_ID ,VISIT_OFFENDER_BOOK_ID ,COMMENT_TEXT ,RAISED_INCIDENT_TYPE ,RAISED_INCIDENT_NUMBER ,VISIT_DATE ,START_TIME ,END_TIME ,VISIT_TYPE ,VISIT_STATUS ,VISIT_INTERNAL_LOCATION_ID ,AGENCY_VISIT_SLOT_ID ,AGY_LOC_ID ,OFFENDER_VISIT_VISITOR_ID ,OFFENDER_BOOK_ID ,OFFENDER_ID ,OFFENDER_LAST_NAME ,OFFENDER_FIRST_NAME ,OFFENDER_ID_DISPLAY ,VISIT_OWNER_FLAG ,EVENT_STATUS ,EVENT_OUTCOME ,OUTCOME_REASON_CODE ,VISITOR_COMMENT_TEXT ,EVENT_ID ,CHECK_SUM FROM V_OFFENDER_VISITS  WHERE OFFENDER_VISIT_ID = :OFFENDERVISITID AND OFFENDER_BOOK_ID = :OFFENDERBOOKID
}

OIDVISIT_CHECK_CONTACT_ACTIVE {
SELECT COUNT (*) FROM offender_contact_persons WHERE offender_book_id = :p_offender_book_id AND person_id = :p_person_id AND active_flag = 'Y'
}

OIDVISIT_CHECK_LIST_ENTRY {
	SELECT  DISTINCT  START_TIME ,  END_TIME, WEEK_DAY, AGY_LOC_ID FROM AGENCY_VISIT_TIMES WHERE ACTIVE_FLAG = 'Y' 
}

OIDVISIT_CHECK_NONASSOCATION {
select * from V_OFFENDER_VISITS where offender_book_id =:nsOffenderBookId and visit_date=:visitDate::timestamp and visit_internal_location_id=:internalLocationId
}
OIDVISIT_CHECK_IEP_LEVEL{
select iep_level_code from incentives_earn_privs iep where intake_iep ='Y'
}
OIDVISIT_CHECK_IEP_SEC_CONFIG{
select visit_config_type  from agency_visit_config avc where agy_loc_id =:agyLocId
}
OIDVISIT_GET_OFFENDER_IEP_CONFIG_DATA{
select iep_level_code from off_incentives_earn_privs where offender_book_id =:offenderBookId and create_datetime =( select max(create_datetime) from off_incentives_earn_privs where offender_book_id =:offenderBookId)
}


OIDVISIT_VISITCYCLELIMITS_WITH_VISITTYPE_FOR_IP_LEVEL{
 SELECT 'X' FROM visit_type_limits vtl WHERE visit_cycle_limit_id = (SELECT visit_cycle_limit_id FROM visit_cycle_limits vcl WHERE agy_loc_id = :agyLocId AND iep_level = :iepLevel AND vcl.active_flag = 'Y') AND visit_type = :visitType AND active_flag = 'Y' limit 1
}
OIDVISIT_REVIEW_DATE_FOR_CYCLE_END
{
select next_review_date  from off_incentives_earn_privs iep where iep_level_code =:iepLevelCode and offender_book_id=:offenderBookId and 
create_datetime=(select max(create_datetime)  from off_incentives_earn_privs iep where iep_level_code =:iepLevelCode and offender_book_id=:offenderBookId) 
}

OIDVISIT_GET_MAX_VISITORS_COUNT{
select vtl.max_visitors_type from visit_type_limits vtl where visit_cycle_limit_id in ( select distinct vcl.visit_cycle_limit_id from visit_cycle_limits vcl where agy_loc_id = :agyLocId and visit_config_type_value =( select iep_level_code from off_incentives_earn_privs oiep where offender_book_id = :offenderBookId order by create_datetime desc limit 1)) and visit_type = :visitType order by create_datetime desc limit 1
}

OIDVISIT_FIND_RGVISITTYPE {
	select code, description from reference_codes rc where domain = 'VISIT_TYPE' and code in ( select distinct vtl.visit_type from off_incentives_earn_privs oiep, visit_type_limits vtl, visit_cycle_limits vcl where oiep.offender_book_id = :offenderBookId and oiep.iep_level_seq = (select max(iep_level_seq) from off_incentives_earn_privs where offender_book_id = :offenderBookId) and vcl.visit_config_type_value = oiep.iep_level_code and vcl.active_flag = 'Y' and vcl.agy_loc_id = :agyLocId and vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id and vtl.active_flag = 'Y')
}
OIDVISIT_ATTENDED_CHANGES{
select
	COUNT(*)
from
	offender_visit_visitors ovv
where
	ovv.offender_visit_id =:offender_visit_id
	and event_outcome = 'ATT'
}
	
OIDVISIT_UPDATE_COMPLETION{
update offender_visits set visit_status = 'CANC', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_visit_id =:offender_visit_id
}
OIDVISIT_GET_RGVISITTYPE{
select code, description from reference_codes rc where domain = 'VISIT_TYPE' and code in (select distinct vtl.visit_type from visit_type_limits vtl, visit_cycle_limits vcl,OFFENDER_ASSESSMENTS OFF_ASS WHERE OFF_ASS.OFFENDER_BOOK_ID = :offenderBookId and vcl.visit_config_type_value = :supLvlType and vtl.active_flag = 'Y' and vcl.agy_loc_id = :agyLocId and vcl.visit_cycle_limit_id = vtl.visit_cycle_limit_id and vtl.active_flag = 'Y')
}
OIDVISIT_GET_SUP_LEVE_TYPE{
 select  OFF_ASS.REVIEW_SUP_LEVEL_TYPE FROM OFFENDER_ASSESSMENTS OFF_ASS WHERE OFF_ASS.OFFENDER_BOOK_ID = :offenderBookId  and OFF_ASS.ASSESSMENT_SEQ=(select MAX(OFF_ASS.ASSESSMENT_SEQ) FROM OFFENDER_ASSESSMENTS OFF_ASS, ASSESSMENTS ASS  WHERE OFF_ASS.OFFENDER_BOOK_ID = :offenderBookId AND OFF_ASS.ASSESSMENT_TYPE_ID = ASS.ASSESSMENT_ID AND ASS.DETERMINE_SUP_LEVEL_FLAG ='Y' AND ASS.CASELOAD_TYPE = :caseLoadType AND OFF_ASS.REVIEW_SUP_LEVEL_TYPE IS NOT null)
}
OIDVISIT_GET_RG_GROUP{
select code,description from reference_codes rc where domain = 'VISIT_TYPE' and code in (select distinct vtl.visit_type from visit_type_limits vtl,visit_cycle_limits vcl where vcl.visit_config_type_value in (select
}


