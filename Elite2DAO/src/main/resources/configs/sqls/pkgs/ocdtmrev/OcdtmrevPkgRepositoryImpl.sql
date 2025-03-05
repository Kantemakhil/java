GET_REVIEW_DATE{
SELECT review_date FROM video_review_summaries WHERE crs_sch_id = :p_crs_sch_id
}