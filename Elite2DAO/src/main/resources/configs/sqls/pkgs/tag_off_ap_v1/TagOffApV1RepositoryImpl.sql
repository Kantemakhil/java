TAG_OFF_AP_V1_OFFENDER_ACTION_PLANS_PRINS{
INSERT INTO offender_action_plans(off_action_plan_id,casework_type,off_crim_need_id,off_case_cond_id,program_id,notes,start_date,end_date,create_datetime,modify_datetime,create_user_id) VALUES (nextval('off_action_plan_id'),:caseworkType,:offCrimNeedId,null,:programId,:notes,:startDate,:endDate,current_timestamp,null,:createUserId)
}
TAG_OFF_AP_V1_OFFENDER_ACTION_PLANS_PRUPD{
 UPDATE offender_action_plans SET program_id = :programId,notes = :notes,start_date   = :startDate ,end_date     = :endDate,modify_datetime=current_timestamp,modify_user_id =:modifyUserId  WHERE off_action_plan_id = :offActionPlanId
}
TAG_OFF_AP_V1_OFFENDER_ACTION_PLANS_PRDEL{
DELETE FROM offender_action_plans WHERE off_action_plan_id = :offActionPlanId
}