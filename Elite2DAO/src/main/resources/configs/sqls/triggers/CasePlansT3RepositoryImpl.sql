OFFENDER_CRIMINOGENIC_NEEDS_COUNT{
SELECT count(0)
     FROM offender_criminogenic_needs ocn
    WHERE ocn.offender_book_id  = :offender_book_id
      AND ocn.case_plan_id      = :lv_cp_id_o
}

GET_OFFENDER_CRIMINOGENIC_NEEDS_NEW{
SELECT ocn.assessed_need_code
         ,ocn.objective
         ,ocn.target_date
         ,ocn.end_date
         ,ocn.status_code
         ,ocn.off_crim_need_id
     FROM offender_criminogenic_needs ocn
    WHERE ocn.offender_book_id  = :offender_book_id
      AND ocn.case_plan_id      = :lv_cp_id_o
}

CRIM_NEED_ID_NEXTVAL{
SELECT nextval('off_crim_need_id')
     FROM DUAL
}

OFFENDER_ACTION_PLANS_COUNT{
SELECT count(0)
     FROM offender_action_plans oap
    WHERE oap.off_case_cond_id = :v_cn_id
}

GET_OFFENDER_ACTION_PLANS{
SELECT oap.casework_type
         ,oap.program_id
         ,oap.notes
         ,oap.start_date
         ,oap.end_date
     FROM offender_action_plans oap
    WHERE oap.off_case_cond_id = :v_cn_id
}

OFFENDER_CASE_CONDITIONS_COUNT{
SELECT count(0)
     FROM offender_case_conditions occ
    WHERE occ.offender_book_id = :offender_book_id
      AND occ.case_plan_id     = :lv_cp_id_o
}

GET_OFFENDER_CASE_CONDITIONS{
SELECT off_case_cond_id,offender_book_id,case_plan_id,offender_sent_condition_id,objective,comm_condition_type,comm_condition_code,length,length_unit,start_date,end_date,condition_status,category_type,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag 
     FROM offender_case_conditions occ
    WHERE occ.offender_book_id = :offender_book_id
      AND occ.case_plan_id     = :lv_cp_id_o
}

CASE_CONDS_ID_CUR{
SELECT nextval('off_case_cond_id')
     FROM DUAL
}

GET_OFFENDER_ACTION_PLANS_ONE{
SELECT count(0)
     FROM offender_action_plans oap
    WHERE oap.off_case_cond_id = :v_cc_id
}

CASE_PLAN_TRIGGER_THREE_INSERT_OFFENDER_CASE_CONDITIONS{
insert
	into
	offender_case_conditions (off_case_cond_id,
	offender_book_id,
	case_plan_id,
	offender_sent_condition_id,
	objective,
	comm_condition_type,
	comm_condition_code,
	length,
	length_unit,
	start_date,
	end_date,
	condition_status,
	category_type,
	create_datetime,
	create_user_id,
	modify_datetime)
values(:v_off_cc_id,
:v_obi,
:v_caseplan_id,
:offender_sent_condition_id,
:objective,
:comm_condition_type,
:comm_condition_code,
:length,
:length_unit,
:start_date,
:end_date,
:condition_status,
:category_type,
current_timestamp,
:createUserId,
null)
}
                                         
INSERT_OFFENDER_ACTION_PLANS{

insert into Offender_action_plans(off_action_plan_id , casework_type , off_crim_need_id , off_case_cond_id , program_id , notes , start_date , end_date, create_datetime, modify_datetime, create_user_id)
values(nextval('off_action_plan_id') , :casework_type , null , :lv_off_case_cond_id , :program_id , :notes , :start_date , :end_date, current_timestamp, null, :createUserId)
}

INSERT_OFFENDER_CRIMINOGENIC_NEEDS{

insert into offender_criminogenic_needs (off_crim_need_id , offender_book_id , case_plan_id , assessed_need_code , objective , target_date , end_date , status_code, create_datetime, modify_datetime, create_user_id)
values (:lv_off_crim_need_id , :offender_book_id , :case_plan_id , :assessed_need_code , :objective , :target_date , :end_date , :status_code, current_timestamp, null, :createUserId)
}

INSERT_OFFENDER_ACTION_PLANS2{

insert into Offender_action_plans(off_action_plan_id , casework_type , off_crim_need_id , off_case_cond_id , program_id , notes , start_date , end_date, create_datetime, modify_datetime, create_user_id)
values(nextval('off_action_plan_id') , :casework_type , :lv_off_crim_need_id , null , :program_id , :notes , :start_date , :end_date, current_timestamp, null, :createUserId)
}
