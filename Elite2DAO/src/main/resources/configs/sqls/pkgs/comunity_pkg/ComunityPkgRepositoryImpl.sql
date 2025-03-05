PO_NUM_CUR {
	SELECT COUNT (*)
        FROM case_plans
       WHERE cal_agy_loc_id = :p_agy_loc_id
         AND from_date = :p_from_date
         AND POSITION = :p_position
         AND ROLE = :p_role
         AND sac_staff_id = :p_staff_id
         AND end_date IS NULL

}

  CUR_PRIMARY_OWN_PER_OFFICER {
 SELECT COUNT (CASE_PLANS.CASE_PLAN_ID)  FROM CASE_PLANS  WHERE CASE_PLANS.SAC_STAFF_ID = :P_STAFF_ID  AND CASE_PLANS.CASE_PLAN_STATUS = 'ACTIVE'
 }