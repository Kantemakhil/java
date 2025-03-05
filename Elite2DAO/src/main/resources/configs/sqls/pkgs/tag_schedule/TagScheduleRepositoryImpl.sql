INSERT_OFFENDER_IND_SCHEDULES{
insert into OFFENDER_IND_SCHEDULES (event_id, offender_book_id, event_date, start_time, end_time, event_class, event_type, event_sub_type, event_status, comment_text, hidden_comment_text, application_date, application_time, parent_event_id, agy_loc_id, to_agy_loc_id, to_internal_location_id, from_city, to_city, crs_sch_id, order_id, sentence_seq, outcome_reason_code, judge_name, check_box_1, check_box_2, in_charge_staff_id, credited_hours, report_in_date, piece_work, engagement_code, understanding_code, details, credited_work_hour, agreed_travel_hour, unpaid_work_supervisor, unpaid_work_behaviour, unpaid_work_action, sick_note_received_date, sick_note_expiry_date, court_event_result, unexcused_absence_flag, escort_code, confirm_flag, direction_code, to_city_code, from_city_code, off_prgref_id, in_time, out_time, performance_code, temp_abs_sch_id, reference_id, transport_code, contact_person_name, to_address_owner_class, to_address_id, return_date, return_time, prov_state_code, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (NEXTVAL('event_id'), offender_book_id, event_date, start_time, end_time, event_class, event_type, event_sub_type, event_status, comment_text, hidden_comment_text, application_date, application_time, parent_event_id, agy_loc_id, to_agy_loc_id, to_internal_location_id, from_city, to_city, crs_sch_id, order_id, sentence_seq, outcome_reason_code, judge_name, check_box_1, check_box_2, in_charge_staff_id, credited_hours, report_in_date, piece_work, engagement_code, understanding_code, details, credited_work_hour, agreed_travel_hour, unpaid_work_supervisor, unpaid_work_behaviour, unpaid_work_action, sick_note_received_date, sick_note_expiry_date, court_event_result, unexcused_absence_flag, escort_code, confirm_flag, direction_code, to_city_code, from_city_code, off_prgref_id, in_time, out_time, performance_code, temp_abs_sch_id, reference_id, transport_code, contact_person_name, to_address_owner_class, to_address_id, return_date, return_time, prov_state_code, CREATE_USER_ID, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}
DELETE_SCHEDULE_DELETE_OPERATION{
 DELETE FROM v_offender_all_schedules_2 WHERE event_id = :eventId
}
Get_INTO_V_CHECK_SUM {
SELECT CHECK_SUM   FROM V_TRANSFER_WAITING_LISTS  WHERE EVENT_ID = :P_EVENT_ID
}
 Get_INTO_V_ROW_ID {      
SELECT ROWID FROM OFFENDER_IND_SCH_WAIT_LISTS WHERE EVENT_ID = :P_EVENT_ID
}


CHECK_SUM{
--SELECT
--TO_NUMBER(TO_CHAR(TO_TIMESTAMP(:P_TIMESTAMP, 'YYYY-MM-DD HH:MI:SS'), 'YYYYMMDDHH24MISSFF4'))
--FROM DUAL
SELECT
(TO_CHAR(TO_TIMESTAMP(:P_TIMESTAMP, 'YYYY-MM-DD HH24:MI:SS'), 'YYYYMMDDHH24MISSFF4'))::numeric 
FROM DUAL
}

CHECK_SCHEDULE_CONFLICT_ONE{
SELECT COUNT (*) FROM V_OFFENDER_ALL_SCHEDULES_2 WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND EVENT_STATUS = 'SCH' AND EVENT_DATE = :P_EVENT_DATE
}

CHECK_SCHEDULE_CONFLICT_SECOND{
SELECT COUNT (*) FROM V_OFFENDER_ALL_SCHEDULES_2 WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID AND EVENT_ID <> :P_EVENT_ID AND EVENT_STATUS = 'SCH' AND EVENT_DATE = :P_EVENT_DATE
}
V_OFFENDER_ALL_SCHEDULES_2_UPDATE1{
UPDATE V_OFFENDER_ALL_SCHEDULES_2 SET EVENT_STATUS = :statusCode WHERE EVENT_ID = :eventId
}
V_OFFENDER_ALL_SCHEDULES_2_UPDATE2{
UPDATE V_OFFENDER_ALL_SCHEDULES_2 SET EVENT_STATUS = :statusCode,  OUTCOME_REASON_CODE = :referenceId WHERE OFFENDER_BOOK_ID = :offenderBookId AND EVENT_TYPE = :eventType  AND REFERENCE_ID = :referenceId
}

TAG_SCHEDULE_GET_SCHEDULE_DATE {
select
PROFILE_VALUE::date
from
	SYSTEM_PROFILES
where
	PROFILE_TYPE = 'CLIENT'
	and PROFILE_CODE = 'SCH_DATE'
}

TAG_SCHEDULE_UPDATE_SYSTEM_PROFILES_SCHEDULE_DATE {
UPDATE  SYSTEM_PROFILES
      SET     PROFILE_VALUE = (:P_SCHEDULE_DATE::date)::text
      WHERE   PROFILE_TYPE = 'CLIENT'
      AND     PROFILE_CODE = 'SCH_DATE' 
}

TAG_SCHEDULE_UPDATE_OFFENDER_PROGRAM_PROFILES_STATUS {
UPDATE OFFENDER_PROGRAM_PROFILES OPP
     SET    OFFENDER_PROGRAM_STATUS = 'END'
     WHERE  OFFENDER_PROGRAM_STATUS = 'ALLOC'
     AND    OFFENDER_END_DATE < CURRENT_DATE 
     AND    PROGRAM_ID IN ( SELECT PS.PROGRAM_ID
                            FROM PROGRAM_SERVICES PS
                            WHERE PS.PROGRAM_CATEGORY != 'ACP' )
}

TAG_SCHEDULE_INSERT_OFFENDER_COURSE_MATERIALZE_B {
insert into OFFENDER_COURSE_ATTENDANCES( EVENT_ID, OFFENDER_BOOK_ID, EVENT_CLASS, EVENT_TYPE, EVENT_SUB_TYPE, OFF_PRGREF_ID, REFERENCE_ID, CRS_APPT_ID, CRS_ACTY_ID, EVENT_DATE, START_TIME, END_TIME, IN_TIME, OUT_TIME, EVENT_STATUS, EVENT_OUTCOME, AGY_LOC_ID, TO_INTERNAL_LOCATION_ID, AGREED_TRAVEL_HOUR, DIRECTION_CODE, TO_ADDRESS_ID, TO_AGY_LOC_ID) select nextval('EVENT_ID'), opp.offender_book_id, case rc.parent_code when 'BOTH' then tag_prg_prg_event_class(opp.off_prgref_id) else rc.parent_code end, coalesce( ps.program_category, tag_prg_prg_event_type(ps.program_id) ), ca.course_activity_type, opp.off_prgref_id, voca.crs_appt_id, voca.crs_appt_id, opp.crs_acty_id, voca.schedule_date, voca.start_time, voca.end_time, voca.start_time, voca.end_time, case opp.suspended_flag when 'N' then 'SCH' else 'CANC' end, case opp.suspended_flag when 'N' then null else 'CANC' end, opp.agy_loc_id, ca.internal_location_id, opp.agreed_travel_hour, case case rc.parent_code when 'BOTH' then tag_prg_prg_event_class(opp.off_prgref_id) else rc.parent_code end when 'EXT_MOV' then 'OUT' else null end, ca.services_address_id, addr.owner_code from v_offender_course_appts_2 voca inner join offender_program_profiles opp on voca.off_prgref_id = opp.off_prgref_id inner join course_activities ca on ca.crs_acty_id = opp.crs_acty_id inner join offender_bookings bkg on bkg.offender_book_id = opp.offender_book_id inner join program_services ps on ca.program_id = ps.program_id left join addresses addr on ca.services_address_id = addr.address_id, reference_codes rc where ca.course_activity_type is not null and voca.schedule_date = :P_SCHEDULE_DATE::timestamp(0) and voca.schedule_date between opp.offender_start_date and coalesce( coalesce( opp.offender_end_date, ca.schedule_end_date ), TO_DATE('30001231', 'YYYYMMDD') ) and opp.offender_program_status in ('ALLOC') and rc.domain = 'PS_CATEGORY' and rc.parent_code in ( 'INT_MOV', 'COMM', 'BOTH', 'EXT_MOV' ) and rc.code = coalesce( ps.program_category, tag_prg_prg_event_type(ps.program_id) ) and not exists ( select 'x' from offender_course_attendances oca where oca.offender_book_id = opp.offender_book_id and oca.crs_appt_id = voca.crs_appt_id ) and ( opp.holiday_flag = 'N' or not exists ( select 'x' from system_events se where se.event_type = 'HOL' and voca.schedule_date between se.event_date and se.event_end_date ) )
}

TAG_SCHEDULE_INSERT_OFFENDER_COURSE_MATERIALZE_A {
insert into OFFENDER_COURSE_ATTENDANCES(EVENT_ID, OFFENDER_BOOK_ID, EVENT_CLASS, EVENT_TYPE, EVENT_SUB_TYPE, OFF_PRGREF_ID, REFERENCE_ID, CRS_SCH_ID, CRS_ACTY_ID, EVENT_DATE, START_TIME, END_TIME, IN_TIME, OUT_TIME, EVENT_STATUS, EVENT_OUTCOME, AGY_LOC_ID, TO_INTERNAL_LOCATION_ID, TO_ADDRESS_ID, TO_AGY_LOC_ID, DIRECTION_CODE, SESSION_NO) SELECT NEXTVAL('event_id'), opp.offender_book_id, case  rc.parent_code  when 'BOTH' then  tag_prg_prg_event_class( opp.off_prgref_id )  else rc.parent_code  end, coalesce( ps.program_category, tag_prg_prg_event_type( ps.program_id ) ), ca.course_activity_type, opp.off_prgref_id, cs.crs_sch_id, cs.crs_sch_id, cs.crs_acty_id, cs.schedule_date, cs.start_time, cs.end_time, cs.start_time, cs.end_time, case  opp.suspended_flag  when 'N' then  'SCH'  else 'CANC'  end, case  opp.suspended_flag  when 'N' then  NULL  else 'CANC'  end, opp.agy_loc_id, ca.internal_location_id, ca.services_address_id, addr.owner_code, case  case  rc.parent_code  when 'BOTH' then  tag_prg_prg_event_class( opp.off_prgref_id )  else rc.parent_code  end  when 'EXT_MOV' then  'OUT'  else NULL  end, cs.session_no FROM course_schedules cs INNER JOIN offender_program_profiles opp ON opp.crs_acty_id = cs.crs_acty_id AND opp.offender_start_date <= cs.schedule_date INNER JOIN course_activities ca ON ca.crs_acty_id = opp.crs_acty_id INNER JOIN program_services ps ON ca.program_id = ps.program_id LEFT JOIN addresses addr ON ca.services_address_id = addr.address_id INNER JOIN offender_bookings ob ON ob.offender_book_id = opp.offender_book_id, reference_codes rc WHERE ca.course_activity_type IS NOT NULL AND cs.schedule_date = :P_SCHEDULE_DATE::timestamp(0) AND ( cs.session_no IS NULL OR opp.start_session_no IS NULL OR cs.session_no >= opp.start_session_no ) AND opp.offender_program_status = 'ALLOC' AND rc.domain = 'PS_CATEGORY' AND rc.parent_code IN ( 'INT_MOV', 'COMM', 'BOTH', 'EXT_MOV' ) AND rc.code = coalesce( ps.program_category, tag_prg_prg_event_type( ps.program_id ) ) AND ca.active_flag = 'Y' AND cs.catch_up_crs_sch_id IS NULL AND coalesce( coalesce( opp.offender_end_date, ca.schedule_end_date ), cs.schedule_date ) >= cs.schedule_date AND NOT EXISTS ( SELECT 'x' FROM offender_course_attendances oca WHERE oca.offender_book_id = opp.offender_book_id AND oca.crs_sch_id = cs.crs_sch_id AND oca.event_date = cs.schedule_date )
}

TAG_SCHEDULE_UPDATE_OFFENDER_IND_SCHEDULES {
UPDATE  OFFENDER_IND_SCHEDULES SET     EVENT_STATUS = 'EXP',MODIFY_DATETIME = CURRENT_TIMESTAMP WHERE   EVENT_STATUS = 'SCH' AND     EVENT_DATE   = :P_SCHEDULE_DATE::date-1
}

TAG_SCHEDULE_UPDATE_OFFENDER_COURSE_ATTENDANCES {
UPDATE  OFFENDER_COURSE_ATTENDANCES  SET     EVENT_STATUS = 'EXP',MODIFY_DATETIME = CURRENT_TIMESTAMP  WHERE   EVENT_STATUS = 'SCH'  AND     EVENT_DATE   = :P_SCHEDULE_DATE::date-1 
}

TAG_SCHEDULE_UPDATE_COURSE_SCHEDULE_DAYS {
UPDATE COURSE_SCHEDULE_DAYS SET  SCHEDULE_DATE = current_date +DAY_NO,WEEK_DAY = to_char( current_timestamp + interval '1 day' * day_no, 'DY')  , modify_datetime = current_timestamp 
}

GET_OLD_REC_OF_V_OFFENDER_ALL_SCHEDULES_TWO_ONE{
select * from  V_OFFENDER_ALL_SCHEDULES_2  WHERE   EVENT_DATE = :P_SCHEDULE_DATE  AND     EVENT_ID IS NULL  AND     RECORD_SOURCE <> 'V_OFF_CRS'
}

GET_OLD_REC_OF_V_OFFENDER_ALL_SCHEDULES_TWO_TWO {
select * from V_OFFENDER_ALL_SCHEDULES_2  WHERE   EVENT_STATUS = 'SCH' AND     EVENT_DATE   = :P_SCHEDULE_DATE::date-1
}
