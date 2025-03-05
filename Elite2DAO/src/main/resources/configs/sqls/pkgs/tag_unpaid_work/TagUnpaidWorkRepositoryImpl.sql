
INSERT_OFFENDER_COURSE_ATTENDANCES{
insert into OFFENDER_COURSE_ATTENDANCES (event_id, offender_book_id, event_date, event_outcome, in_time, out_time, agreed_travel_hour, credited_hours, supervisor_staff_id, behaviour_code, performance_code, off_prgref_id, crs_acty_id, event_status, event_class, event_type, event_sub_type, comment_text, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (NEXTVAL('event_id'), :offender_book_id, :event_date, :event_outcome, :in_time, :out_time, :agreed_travel_hour, :credited_hours, :supervisor_staff_id, :behaviour_code, :performance_code, :off_prgref_id, :crs_acty_id, :event_status, 'COMM', :event_type, :event_sub_type, :comment_text, :userName, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
}
GET_PROJECT_ALLOC_DETAILS{

SELECT ca.code, ca.description, tm.team_id, tm.description as NAME, tm.area_code, tm.agy_loc_id, ca.capacity, ca.program_id, ca.schedule_start_date, ca.schedule_end_date FROM course_activities ca, AUTOMATION_TEAMS tm WHERE ca.course_activity_type = 'WS' AND ca.course_class = 'COURSE' AND ca.crs_acty_id = :p_crs_acty_id AND ca.active_flag = 'Y' AND tm.team_id = ca.provider_party_id AND ca.provider_party_class = 'TEAM'
}
SELECYT_SUM_OFFENDER_COURSE_ATTENDANCE{ 
    SELECT SUM (COALESCE (credited_hours, 0)) FROM OFFENDER_COURSE_ATTENDANCES
    WHERE offender_book_id =:p_off_book_id AND crs_acty_id =:p_crs_acty_id  
     AND off_prgref_id =:p_off_prgref_id
  }
 
  UPDATE_OFFENDER_PROGRAM_PROFILES{
  update OFFENDER_PROGRAM_PROFILES set credit_work_hours =:lv_tot_credit_hrs, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id =:p_off_book_id and crs_acty_id =:p_crs_acty_id and off_prgref_id =:p_off_prgref_id
   }

 GET_DISP_ACTY_DESC{
SELECT ca.code, ca.description FROM course_activities ca, offender_program_profiles opp WHERE ca.course_activity_type = 'WS' AND ca.course_class = 'COURSE' AND ca.active_flag = 'Y' AND ca.provider_party_class = 'TEAM' AND opp.offender_book_id = :p_offender_book_id AND ca.crs_acty_id = :p_crs_acty_id AND opp.crs_acty_id = ca.crs_acty_id
}

ALLOC_EXISTS{
 SELECT COUNT (*) FROM OFFENDER_PROGRAM_PROFILES WHERE CRS_ACTY_ID = :P_CRS_ACTY_ID AND ( OFFENDER_END_DATE IS NULL OR OFFENDER_END_DATE > :P_SCHEDULE_END_DATE )
}

GET_OFF_SENT_TERM_HRS{

SELECT coalesce(hours, 0) FROM offender_sentence_terms WHERE offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}

GET_HOLIDAY_COUNT{
SELECT COUNT(*) FROM system_events WHERE :p_event_date BETWEEN event_date AND event_end_date
}

GET_WEEKLY_DEF_START_DATE{
SELECT MAX(end_date) FROM offender_course_appt_grps WHERE off_prgref_id = :p_off_prgref_id GROUP BY off_prgref_id 
}

GET_CRS_APPT_GRP_ID{
select nextval('offender_course_appt_grp_id') 
}

C1_SELECT{
(SELECT off_prgref_id, offender_end_date FROM OFFENDER_PROGRAM_PROFILES WHERE crs_acty_id = :p_crs_acty_id AND ( offender_end_date IS NULL OR offender_end_date > :p_schedule_end_date )) 
  }
             
 OFFENDER_PROGRAM_PROFILES_UPDATE{
 update OFFENDER_PROGRAM_PROFILES set offender_end_date = :p_schedule_end_date, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where off_prgref_id = :v_off_prgref_id
 }
          
 V_OFFENDER_COURSE_EVENTS_DELETE{
 DELETE FROM v_offender_course_events WHERE event_date > :p_schedule_end_date AND off_prgref_id = :v_off_prgref_id
}
