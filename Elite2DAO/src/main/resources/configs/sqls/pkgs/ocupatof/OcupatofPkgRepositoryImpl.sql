UPDATE_OFF_CRSE_ATTEND{
update OFFENDER_COURSE_ATTENDANCES set event_outcome = :p_event_outcome, in_time = :p_in_time, out_time = :p_out_time, engagement_code = :p_engagement_code, understanding_code = :p_understanding_code, comment_text = :p_comment_text, modify_user_id = :modifyUserId, modify_datetime = current_timestamp where event_id = :p_event_id
}