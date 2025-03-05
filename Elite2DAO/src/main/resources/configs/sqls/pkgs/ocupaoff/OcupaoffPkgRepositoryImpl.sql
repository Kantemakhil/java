
UPDATE_V_OFF_CRSE_EVENTS_UPDATE_V_OFF_CRS_EVENTS{
 UPDATE V_OFFENDER_COURSE_EVENTS
	       SET event_outcome 	   = :P_EVENT_OUTCOME,
	       	   comment_text        = :P_COMMENT_TEXT
	     WHERE off_prgref_id       = :P_OFF_PRGREF_ID
	       AND crs_acty_id 	   	   = :P_CRS_ACTY_ID
	       AND crs_sch_id          = :P_CRS_SCH_ID
}	       

UPDATE_OFFNEDR_COURSE_ATTENDANCE{

     /* UPDATE OFFENDER_COURSE_ATTENDANCES
	     SET event_outcome 		 = :p_event_outcome,
	     	 in_time             = :p_in_time,
	     	 out_time            = :p_out_time,
	     	 engagement_code     = :p_engagement_code,
	     	 understanding_code  = :p_understanding_code,
	     	 comment_text        = :p_comment_text
       WHERE event_id 			 = :p_event_id */
update OFFENDER_COURSE_ATTENDANCES set event_outcome = :p_event_outcome, in_time = :p_in_time, out_time = :p_out_time, engagement_code = :p_engagement_code, understanding_code = :p_understanding_code, comment_text = :p_comment_text, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where event_id = :p_event_id
  }