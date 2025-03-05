OCUMAOFF_EXECUTE_QUERY{
	SELECT SM.last_name
           ,SM.first_name
           ,SM.birthdate
           ,SAC.sac_staff_id
           ,SAC.position
           ,SAC.role
           ,SAC.schedule_type
           ,SAC.hours_per_week
           ,SAC.from_date
           ,SAC.to_date
           ,SAC.cal_agy_loc_id
       FROM staff_location_roles SAC,
            staff_members SM
      WHERE SAC.sac_staff_id   != :sacStaffId
        AND SAC.cal_agy_loc_id = :agyLocId
        AND (( SAC.supervisor_staff_id != :sacStaffId
               OR SAC.supervisor_staff_id IS NULL)
             OR :sacStaffId IS NULL )
        AND SAC.to_date IS NULL
        AND SAC.sac_staff_id = SM.staff_id
      ORDER BY SM.last_name, 
               SM.first_name,
               SAC.position,
               SAC.role
}

OCUMAOFF_UPDATE_SUPERVOSOR{
UPDATE staff_location_roles
               SET supervisor_staff_id   = :supervisorStaffId
                  ,supervisor_position   = :supervisorPosition
                  ,supervisor_role       = :supervisorRole
                  ,supervisor_from_date  = :supervisorFromDate
                  ,supervisor_agy_loc_id = :supervisorAgyLocId
                  ,MODIFY_USER_ID = :modifyUserId 
                  ,MODIFY_DATETIME = CURRENT_TIMESTAMP
             WHERE sac_staff_id     = :sacStaffId
               AND position         = :position
               AND role             = :role
               AND date_TRUNC('D',from_date)::date = date_TRUNC('D',:fromDate::date)
               AND cal_agy_loc_id   = :calAgyLocId

}

