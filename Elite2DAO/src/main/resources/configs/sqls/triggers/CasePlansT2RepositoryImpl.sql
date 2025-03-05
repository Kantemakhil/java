WORK_FLOW_ID_COUNT{
SELECT count(work_flow_id)
     FROM work_flows
    WHERE object_id  = :offender_book_id
      AND object_seq = :case_plan_id
      AND object_code = 'CASE_PLANS'
}

WORK_FLOW_ID_NEXTVAL{
 SELECT nextval('work_flow_id')
     FROM dual
}

INSERT_INTO_WORK_FLOWS{

insert into work_flows( work_flow_id, object_code, object_id, object_seq , create_datetime, modify_datetime, create_user_id) 
values ( :lv_work_flow_id, 'CASE_PLANS', :offender_book_id, :case_plan_id , current_timestamp, null, :createUserId)
}

INSERT_INTO_WORK_FLOWS_LOGS{

insert into work_flow_logs( work_flow_id, work_flow_seq, work_action_code, work_action_date, action_user_id, work_flow_status, create_date, locate_agy_loc_id, create_user_id, create_datetime, modify_datetime)
values ( :lv_work_flow_id, 1, 'ENT', current_timestamp, null, 'DONE', current_timestamp, :locateAgyLocId, :USERNAME, current_timestamp, null)
}