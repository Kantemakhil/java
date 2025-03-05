OCMDSPWD_GET_AGY_RECORD_GROUP{
select agy_loc_id,  description, CASE WHEN al.agy_loc_id in (select cl.agy_loc_id  from CASELOAD_AGENCY_LOCATIONS cl where caseload_id = :caseloadId) THEN true ELSE false end as canDisplay from agency_locations al
}

OCMDSPWD_ASS_STARTING_DEF_WL_UNITS_EXECUTE_QUERY{
SELECT * FROM wl_default_staff_units
}

OCMDSPWD_NON_OFF_SPEC_TASK_POS_EXECUTE_QUERY{
SELECT * FROM wl_non_off_specific_tasks WHERE staff_position = :staffPosition 
}

OCMDSPWD_INSERT_MAINTAIN_STARTING_WORKLOAD_UNITS{
INSERT INTO wl_default_staff_units(staff_position,default_starting_units,create_datetime,create_user_id,modify_datetime,seal_flag) 
values(:staffPosition,:defaultStartingUnits,CURRENT_TIMESTAMP,:createUserId,null,:sealFlag)
}

OCMDSPWD_UPDATE_MAINTAIN_STARTING_WORKLOAD_UNITS{
update wl_default_staff_units set default_starting_units = :defaultStartingUnits , modify_datetime = CURRENT_TIMESTAMP, modify_user_id = :modifyUserId where staff_position = :staffPosition
}

OCMDSPWD_DELETE_MAINTAIN_STARTING_WORKLOAD_UNITS{
DELETE FROM wl_default_staff_units WHERE staff_position = :staffPosition  
}

OCMDSPWD_INSERT_NON_OFFENDER_SPECIFIC_TASKS_POSITION{
INSERT INTO wl_non_off_specific_tasks(agy_loc_id,staff_position,workload_task_type,units,create_datetime,create_user_id,modify_datetime,seal_flag) values(:agyLocId,:staffPosition, :workloadTaskType, :units,CURRENT_TIMESTAMP, :createUserId, null ,:sealFlag)
}

OCMDSPWD_UPDATE_NON_OFFENDER_SPECIFIC_TASKS_POSITION{
update wl_non_off_specific_tasks set  UNITS = :units, modify_datetime = CURRENT_TIMESTAMP, modify_user_id = :modifyUserId where AGY_LOC_ID = :agyLocId and WORKLOAD_TASK_TYPE = :workloadTaskType  and  staff_position = :staffPosition
}

OCMDSPWD_DELETE_NON_OFFENDER_SPECIFIC_TASKS_POSITION{
DELETE FROM wl_non_off_specific_tasks  where agy_loc_id = :agyLocId and workload_task_type = :workloadTaskType and staff_position = :staffPosition
}

OCMDSPWD_DELETE_MAINTAIN_STARTING_WORKLOAD_UNITS_PRE_DELETE{
DELETE FROM wl_non_off_specific_tasks 
   WHERE staff_position = :staffPosition
}