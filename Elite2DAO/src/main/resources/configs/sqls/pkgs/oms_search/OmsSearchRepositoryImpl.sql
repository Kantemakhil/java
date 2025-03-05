
OMS_SEARCH_CHECK_OFFENDER_ID_DISPLAY{
select 1 from offenders where offender_id_display = :p_offender_id_display limit 1
}