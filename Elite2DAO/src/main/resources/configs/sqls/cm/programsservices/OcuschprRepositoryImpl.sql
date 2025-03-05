
OCUSCHPR_VACPSCHEDULES_FIND_V_ACP_SCHEDULES {
 	SELECT 
    program_id,
    program_code,
    program_desc,
    program_instance_id,
    program_instance_code,
    PROGRAM_INSTANCE_DESC,
    phase_code,
    phase_description,
    phase_provider_party_class,
    phase_provider_party_id,
    phase_provider_party_code,
    phase_provider_name,
    phase_instance_code,
    phase_instance_id,
    phase_list_seq,
    phase_session_length,
    phase_instance_desc,
    module_instance_id,
    module_list_seq,
    module_instance_desc,
    crs_sch_id,
    schedule_date,
    start_time,
    end_time,
    session_no,
    catch_up_session_flag,
    internal_location_desc,
    schedule_status  FROM V_ACP_SCHEDULES  
}

OCUSCHPR_OCDPATTE_GET_COURSE_SCHEDULE{
select
	start_time ,
	end_time ,
	program_instance_code ,
	module_instance_desc ,
	session_no ,
	phase_instance_desc ,
	crs_sch_id ,
	phase_instance_id ,
	PROGRAM_INSTANCE_DESC ,
	program_instance_id ,
	program_id ,
	program_desc ,
	schedule_status ,
	catch_up_session_flag
from
	v_acp_schedules
where
	schedule_date = TO_DATE(:p_schedule_date::text, 'DD-MM-YYYY')
	and (( coalesce(:p_phase_provider_party_id, 0) != 0
		and phase_provider_party_id = :p_phase_provider_party_id::bigint)
	or ( coalesce(:p_phase_provider_party_code, '') != ''
		and phase_provider_party_code = :p_phase_provider_party_code))
	and (coalesce(:p_service_id, 0) = 0
		or program_id = :p_service_id)
order by
	PROGRAM_INSTANCE_DESC,
	PHASE_CODE,
	START_TIME asc
}

OCUSCHPR_OCDPATTE_GET_COURSE_SCHEDULE_ONE{
SELECT start_time
               ,end_time
               ,program_instance_code 
               ,module_instance_desc 
               ,session_no
               ,phase_instance_desc 
               ,crs_sch_id
               ,phase_instance_id 
               ,PROGRAM_INSTANCE_DESC
               ,program_instance_id
               ,program_id
               ,program_desc
               ,schedule_status
               ,catch_up_session_flag
           FROM v_acp_schedules
          WHERE schedule_date = TO_DATE(:p_schedule_date,'DD-MM-YYYY')
            AND ((:p_phase_provider_party_id IS NOT NULL AND
                phase_provider_party_id = :p_phase_provider_party_id) OR
                (:p_phase_provider_party_code IS NOT NULL AND
                phase_provider_party_code =:p_phase_provider_party_code))
           AND program_id = coalesce(:p_service_id, program_id)
            AND catch_up_session_flag = coalesce(:p_catch_up_session, 'N')
            ORDER BY PROGRAM_INSTANCE_DESC, PHASE_CODE, START_TIME ASC
}
