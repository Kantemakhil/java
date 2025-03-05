OCDONOST_GET_STAFF_NAME{
select last_name || ', '||first_name  from STAFF_MEMBERS where staff_id = :staffId
}

OCDONOST_GET_NON_OFFENDER_SPECIFIC_TASK{
SELECT * FROM wl_non_off_specific_tasks WHERE AGY_LOC_ID = :agyLocId AND staff_position = :staffPosition
}

OCDONOST_GET_DEF_UNITS{
SELECT default_starting_units FROM wl_default_staff_units where staff_position = :staffPosition 
}


OCDONOST_INSERT_OFFICER_NON_OFF_SPECIFIC_TASKS{
insert into wl_officer_non_off_specific_tasks( staff_loc_role_id, workload_task_type, units, active_flag, available_units, create_datetime , create_user_id , modify_datetime, seal_flag) values(:staffLocRoleId, :workloadTaskType, :units, :activeFlag, :availableUnits, CURRENT_TIMESTAMP, :createUserId, null , :sealFlag)
}


OCDONOST_UPDATE_OFFICER_NON_OFF_SPECIFIC_TASKS{
update wl_officer_non_off_specific_tasks set active_flag = :activeFlag , units = :units where staff_loc_role_id =:staffLocRoleId and workload_task_type=:workloadTaskType
}

OCDONOST_DELETE_OFFICER_NON_OFF_SPECIFIC_TASKS{
delete from wl_officer_non_off_specific_tasks where workload_task_type=:workloadTaskType and staff_loc_role_id =:staffLocRoleId
}

OCDONOST_GET_OFFICER_NON_OFFENDER_SPE_TASK{
select staff_loc_role_id, workload_task_type, units, active_flag ,available_units, create_datetime ,create_user_id , modify_datetime, modify_user_id, seal_flag from wl_officer_non_off_specific_tasks where staff_loc_role_id =:staffLocRoleId
}

OCDONOST_UPDATE_AVAILABLE_UNITS{
update wl_officer_non_off_specific_tasks set available_units = :availableUnits,MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID =:modifyUserId where staff_loc_role_id =:staffLocRoleId 
}

OCDONOST_GETTING_UNITS_USED_BY_EACH_OFFENDERS{
 select sum(mtl.workload_value) from offender_tier_levels otl join maintain_tier_levels mtl on (otl.tier_level_code=mtl.tier_level_code) where offender_book_id in (SELECT offender_book_id FROM case_plans WHERE cal_agy_loc_id = :p_agy_loc_id AND sac_staff_id = :p_staff_id and case_plan_status ='ACTIVE') and otl.active_flag ='Y'
}

OCDONOST_GETTING_UNITS_USED_BY_EACH_OFFENDERS{
select sum(mtl.workload_value) from offender_tier_levels otl join maintain_tier_levels mtl on (otl.tier_level_code=mtl.tier_level_code) where offender_book_id in (SELECT offender_book_id FROM case_plans WHERE cal_agy_loc_id = :p_agy_loc_id AND sac_staff_id = :p_staff_id and case_plan_status ='ACTIVE') and otl.active_flag ='Y'
}

