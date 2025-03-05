
OCUSSESS_CRSSCH_FIND_COURSE_SCHEDULES {
SELECT
    crs_sch_id,
    program_instance_id as CRS_ACTY_ID,
    TO_CHAR(schedule_date, 'Day') as weekday,
    schedule_date,
    start_time,
    end_time,
    session_no
FROM
    v_acp_schedules  WHERE phase_instance_id=:crsActyId  and schedule_status = 'SCH' order by start_time asc
}
