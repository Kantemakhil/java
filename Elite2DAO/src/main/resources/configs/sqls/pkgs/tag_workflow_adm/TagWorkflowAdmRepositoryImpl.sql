GET_OFF_DETAILS_TRANS_OFF_DET_CUR {
SELECT OFF.ROOT_OFFENDER_ID, OFF.OFFENDER_ID,
                OFF.OFFENDER_ID_DISPLAY, OFF.LAST_NAME, OFF.FIRST_NAME
           FROM OFFENDERS OFF, OFFENDER_BOOKINGS OFF_BKG
          WHERE OFF_BKG.OFFENDER_BOOK_ID = :P_OFF_BOOK_ID
            AND OFF_BKG.OFFENDER_ID = OFF.OFFENDER_ID
  }
  GET_OFF_DETAILS_WAIT_OFF_DET_CUR {
SELECT OFF_NAME.OFFENDER_ID, OFF_NAME.OFFENDER_ID_DISPLAY,
                OFF_NAME.LAST_NAME, OFF_NAME.FIRST_NAME
           FROM OFFENDERS OFF_NAME, OFFENDER_BOOKINGS OFF_BKG
          WHERE OFF_NAME.ROOT_OFFENDER_ID = :P_ROOT_OFF_ID
            AND OFF_BKG.OFFENDER_ID = OFF_NAME.OFFENDER_ID
           ORDER BY DECODE(OFF_BKG.MODIFY_DATETIME,NULL,TO_DATE('11/11/1111','DD/MM/YYYY'),OFF_BKG.MODIFY_DATETIME) DESC
           }
                    
 SELECT_TEAM_MEMBERS{
    SELECT staff_id FROM TEAM_MEMBERS
     WHERE team_member_id =:team_member_id      
 }   
 
 DEFAULT_TEAM_TEAM_CNT_CUR{
 SELECT COUNT ( 1 ) FROM TEAM_MEMBERS WHERE STAFF_ID = :P_STAFF_ID AND ACTIVE_FLAG = 'Y' AND TEAM_ID IN ( SELECT T.TEAM_ID FROM TEAMS T, TEAM_FUNCTIONS TF WHERE T.ACTIVE_FLAG = 'Y' AND T.TEAM_ID = TF.TEAM_ID AND TF.FUNCTION_TYPE = 'OM' )
}               

DEFAULT_TEAM_GET_TEAM_ID{
 SELECT T.TEAM_ID FROM TEAM_MEMBERS T, TEAM_FUNCTIONS TF WHERE T.TEAM_ID = TF.TEAM_ID AND TF.FUNCTION_TYPE = 'OM' AND STAFF_ID = :P_STAFF_ID 
 }
 P_OFF_MSG_REC_NEW{
--SELECT tah.offender_book_id, tah.workflow_history_id, tah.work_id, tah.assignment_date, tah.due_date, tah.team_id, tms.team_code, tms.description, tah.team_member_id, sm.last_name, sm.first_name,tah.function_type,
--(Select description from reference_codes where domain = 'FUNCTION' and code = tah.function_type and rownum = 1) nbt_function_type_desc FROM task_assignment_hty tah, teams tms, staff_members sm WHERE tah.offender_book_id =:p_off_book_id AND tah.workflow_history_id = TO_NUMBER (:p_workflow_id ) 
--AND tms.team_id = tah.team_id AND sm.staff_id(+) = tah.staff_id AND tah.task_assignment_hty_id = ( SELECT MAX ( task_assignment_hty_id ) FROM task_assignment_hty WHERE offender_book_id =:p_off_book_id AND workflow_history_id = TO_NUMBER (:p_workflow_id ) GROUP BY offender_book_id )
select
tah.offender_book_id,
tah.workflow_history_id,
tah.work_id,
tah.assignment_date,
tah.due_date,
tah.team_id,
tms.team_code,
tms.description,
tah.team_member_id,
sm.last_name,
sm.first_name,
tah.function_type,
(
select
description
from
reference_codes
where
domain = 'FUNCTION'
and code = tah.function_type
limit 1) nbt_function_type_desc
from
teams tms,
task_assignment_hty tah
left outer join staff_members sm on
(tah.staff_id = sm.staff_id)
where
tah.offender_book_id = :p_off_book_id
and tah.workflow_history_id = (:p_workflow_id)::numeric
and tms.team_id = tah.team_id
and tah.task_assignment_hty_id = (
select
MAX(task_assignment_hty_id)
from
task_assignment_hty
where
offender_book_id = :p_off_book_id
and workflow_history_id = (:p_workflow_id)::numeric
group by
offender_book_id );
}

TAG_WORKFLOW_ADM_GET_OFFENDER_DETAILS{
SELECT LAST_NAME, FIRST_NAME, OFFENDER_ID_DISPLAY, ROOT_OFFENDER_ID FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK WHERE OFFENDER_BOOK_ID = :P_OFFENDER_BOOK_ID
}

TEAM_CNT_CUR {
	SELECT COUNT ( 1 )
           FROM team_members
          WHERE staff_id = :p_staff_id
            AND active_flag = 'Y'
            AND team_id IN (
                   SELECT t.team_id
                     FROM teams t, team_functions tf
                    WHERE t.active_flag = 'Y'
                      AND t.team_id = tf.team_id
                      AND tf.function_type = 'OM' )

}

TEAM_CUR {
	
SELECT t.team_id
           FROM team_members t, team_functions tf
          WHERE t.team_id = tf.team_id
            AND tf.function_type = 'OM'
            AND staff_id = :p_staff_id

}


GET_WORK_ID{
SELECT work_id FROM works WHERE workflow_type = :task AND work_type = :report AND work_sub_type = :crtrepreq AND active_flag = 'Y'
}

CREATE_TEAM_ASSIGN_HTY_NEW_UPDATE{
-- UPDATE orders SET workflow_id = :workflowId WHERE offender_book_id = :pOffenderBookId AND order_id = :pOrderId
update orders set workflow_id = :workflowId, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id = :pOffenderBookId and order_id = :pOrderId
}

GET_WORK_EXISTS_CUR {

	SELECT workflow_type 
           FROM works 
          WHERE work_id = :p_work_id

}

CO_CURSOR {
	select * from V_CB_SENT_TERMS order by start_date
	
}

CO_CURSOR_ONE {
	select * from V_CB_CUSTODY_PERIOD order by ADMISSION_DATE
	
}

GET_WORK_CUR{
SELECT workflow_type, work_type, work_sub_type, manual_close_flag, module_name FROM works WHERE work_id = :P_WORK_ID
}    

CASE_LOAD_TYPE_CUR{
select c.caseload_type from caseloads c where c.caseload_id = :caseload_id
}

DELETE_OFF_VTEAM_DTLS{
DELETE FROM OFFENDER_TEAM_ASSIGNMENTS WHERE OFFENDER_BOOK_ID = :P_OFF_BOOK_ID AND FUNCTION_TYPE = :P_FUNCTION_TYPE AND TEAM_ID = :P_TEAM_ID AND ASSIGNMENT_DATE = :P_ASSIGN_DATE
}

C_TEAM_DESC{
 SELECT team_code, description FROM automation_teams WHERE team_id =:p_team_id
   }
   
ASSIGMENT_CUR{
 SELECT COUNT ( 1 )  FROM offender_team_assignments  WHERE offender_book_id = :p_off_book_id
 }
 
 OFFENDER_TEAM_ASSIGNMENTS{
 DELETE FROM offender_team_assignments  WHERE offender_book_id = :p_off_book_id  AND function_type = 'OM'
}

 OFFENDER_TEAM_ASSIGNMENTS_INSERT{
insert into offender_team_assignments ( offender_book_id, function_type, team_id, assignment_date , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( :p_off_book_id, 'OM', :p_team_id, CURRENT_TIMESTAMP , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
 }

GET_TEAM_DETAILS{
SELECT T.TEAM_ID, T.DESCRIPTION FROM TEAMS T, TEAM_FUNCTIONS TF WHERE T.TEAM_CODE = :P_TEAM_CODE AND T.TEAM_ID = TF.TEAM_ID AND TF.FUNCTION_TYPE = :P_FUNCTION_TYPE
}

GET_TEAM_DESC{
SELECT TEAM_CODE, DESCRIPTION FROM TEAMS WHERE TEAM_ID = :P_TEAM_ID
}

GET_ACK_WORK_ID_CUR {
SELECT WORK_ID 
        FROM WORKS 
       WHERE WORK_TYPE     = :CP_WORK_TYPE 
         AND WORK_SUB_TYPE = :CP_WORK_SUBTYPE 
         AND ACTIVE_FLAG   = 'Y'; 
}

LV_ORIGINATOR_STAFF_ID {
SELECT STAFF_ID 
           FROM STAFF_MEMBERS 
          WHERE USER_ID = :P_ORIGINAL_SENDER_ID
}

QUERY_TEAM_TASKS{
SELECT tah.TASK_ASSIGNMENT_HTY_ID, tah.WORKFLOW_HISTORY_ID,
                tah.OFFENDER_BOOK_ID, offs.offender_id_display, offs.last_name OFFENDER_LAST_NAME, offs.first_name OFFENDER_FIRST_NAME,
                tah.work_id, ws.work_type, (select description
                                            from reference_codes
                                            where domain = 'TASK_TYPE'
                                            and code = ws.work_type) WORK_TYPE_DESC,
                ws.work_sub_type, (select description
                                     from reference_codes
                                    where domain = 'TASK_SUBTYPE'
                                      and code = ws.work_sub_type) WORK_TYPE_DESC,
                tah.details, tah.team_id, ts.team_code, ts.description TEAM_DESCRIPTION,
                tah.STAFF_ID, sm.last_name STAFF_LAST_NAME, sm.first_name STAFF_FIRST_NAME,
                DECODE(tah.STAFF_ID, NULL, NULL, sm.last_name||', '||sm.first_name) OFFICER_NAME,
                tah.team_member_id, tah.assignment_date, tah.assignment_status,tah.due_date,
                decode(tah.completion_date, NULL, 'N', 'Y') completion_flag,
                tah.completion_date, tah.complete_reason_code, (select description
                                                                  from reference_codes
                                                                 where domain = 'COMPLETE_RSN'
                                                                   and code = tah.complete_reason_code) COMPLETE_REASON_DESC,
                tah.complete_comment_text, tah.complete_user_id,
                tah.message_id, tah.original_message_id,
                (SELECT count(task_assignment_hty_id)
                   FROM task_assignment_hty task1
                  WHERE task1.offender_book_id = tah.offender_book_id
                    AND task1.workflow_history_id = tah.workflow_history_id
                    AND task1.work_id = tah.work_id) task_instances_count
           FROM task_assignment_hty tah, offender_bookings ob, offenders offs,
                works ws, teams ts, staff_members sm
          WHERE ob.offender_book_id = tah.offender_book_id
            AND ((ob.active_flag = 'Y'
                  and exists (select agy_loc_id
                                from caseload_agency_locations
                               where caseload_id = :p_caseload_id
                                and agy_loc_id = ob.agy_loc_id))
               OR
                 (ob.community_active_flag = 'Y'
                 and exists (select agy_loc_id
                               from caseload_agency_locations
                              where caseload_id = :p_caseload_id
                                and agy_loc_id = ob.community_agy_loc_id))
            )
            AND tah.TASK_ASSIGNMENT_HTY_ID in (
                select max(tasks.TASK_ASSIGNMENT_HTY_ID) max_task_assignment_hty_id
                  from task_assignment_hty tasks, works
                 where tasks.offender_book_id = ob.offender_book_id
                   and tasks.work_id = works.work_id
                   and works.workflow_type = 'TASK'
              group by tasks.offender_book_id, tasks.workflow_history_id, tasks.work_id
            )
            AND offs.offender_id = ob.offender_id
            AND ws.work_id = tah.work_id
            AND ts.team_id(+) = tah.team_id
            AND sm.staff_id(+) = tah.staff_id
            AND (:p_offender_book_id IS NULL or tah.offender_book_id = :p_offender_book_id)
            AND (:p_work_type IS NULL OR ws.work_type = :p_work_type)
            AND (:p_work_sub_type IS NULL OR ws.work_sub_type =:p_work_sub_type)
            AND (tah.team_id = :p_team_id)
            AND (:p_staff_id IS NULL OR tah.staff_id =:p_staff_id)
            AND (:p_completion_status = 'ALL'
               OR (:p_completion_status = 'COM' AND tah.completion_date IS NOT NULL)
               OR (:p_completion_status = 'INC' AND tah.completion_date IS NULL))
            AND (:p_due_from_date IS NULL OR tah.due_date >= :p_due_from_date )
           -- AND (:p_due_to_date IS NULL OR tah.due_date <=:p_due_to_date +0.99999 ) 
          ORDER BY tah.assignment_date DESC;
}

QUERY_OFFENDER_TASKS{
select
	tah.TASK_ASSIGNMENT_HTY_ID,
	tah.WORKFLOW_HISTORY_ID,
	tah.OFFENDER_BOOK_ID,
	offs.offender_id_display,
	offs.last_name OFFENDER_LAST_NAME,
	offs.first_name OFFENDER_FIRST_NAME,
	tah.work_id,
	ws.work_type,
	(
	select
		description
	from
		reference_codes
	where
		domain = 'TASK_TYPE'
		and code = ws.work_type) WORK_TYPE_DESC,
	ws.work_sub_type,
	(
	select
		description
	from
		reference_codes
	where
		domain = 'TASK_SUBTYPE'
		and code = ws.work_sub_type) WORK_TYPE_DESC,
	tah.details,
	tah.team_id,
	ts.team_code,
	ts.description TEAM_DESCRIPTION,
	tah.STAFF_ID,
	sm.last_name STAFF_LAST_NAME,
	sm.first_name STAFF_FIRST_NAME,
	case
		when coalesce(tah.STAFF_ID::text, '') = '' then null
		else sm.last_name || ', ' || sm.first_name
	end OFFICER_NAME,
	tah.team_member_id,
	tah.assignment_date,
	tah.assignment_status,
	tah.due_date,
	case
		when coalesce(tah.completion_date::text, '') = '' then 'N'
		else 'Y'
	end completion_flag,
	tah.completion_date,
	tah.complete_reason_code,
	(
	select
		description
	from
		reference_codes
	where
		domain = 'COMPLETE_RSN'
		and code = tah.complete_reason_code) COMPLETE_REASON_DESC,
	tah.complete_comment_text,
	tah.complete_user_id,
	tah.message_id,
	tah.original_message_id,
	(
	select
		count(task_assignment_hty_id)
	from
		task_assignment_hty task1
	where
		task1.offender_book_id = :p_offender_book_id
		and task1.workflow_history_id = tah.workflow_history_id
		and task1.work_id = tah.work_id) task_instances_count
from
	works ws,
	offenders offs,
	offender_bookings ob,
	task_assignment_hty tah
left outer join teams ts on
	(tah.team_id = ts.team_id)
left outer join staff_members sm on
	(tah.staff_id = sm.staff_id)
where
	tah.offender_book_id = :p_offender_book_id
	and ob.offender_book_id = tah.offender_book_id
	and tah.TASK_ASSIGNMENT_HTY_ID in (
	select
		max(tasks.TASK_ASSIGNMENT_HTY_ID) max_task_assignment_hty_id
	from
		task_assignment_hty tasks,
		works
	where
		tasks.offender_book_id = :p_offender_book_id
		and tasks.work_id = works.work_id
		and works.workflow_type = 'TASK'
	group by
		tasks.offender_book_id,
		tasks.workflow_history_id,
		tasks.work_id )
	and offs.offender_id = ob.offender_id
	and ws.work_id = tah.work_id
	and (coalesce(:p_work_type::text, '') = ''
		or ws.work_type = :p_work_type)
	and (coalesce(:p_work_sub_type::text, '') = ''
		or ws.work_sub_type = :p_work_sub_type)
	and (coalesce(:p_team_id::text, '') = ''
		or tah.team_id = :p_team_id)
	and (coalesce(:p_staff_id::text, '') = ''
		or tah.staff_id = :p_staff_id)
	and (coalesce(:p_complete_rsn_code::text, '') = ''
		or tah.complete_reason_code = :p_complete_rsn_code)
order by
	tah.assignment_date desc
}

GET_OLD_REC_OFFENDER_TEAM_ASSIGNMENTS{
SELECT OFFENDER_BOOK_ID, FUNCTION_TYPE, TEAM_ID , ASSIGNMENT_DATE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG FROM OFFENDER_TEAM_ASSIGNMENTS WHERE OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID AND FUNCTION_TYPE = :FUNCTION_TYPE AND TEAM_ID = :P_TEAM_ID AND ASSIGNMENT_DATE = :P_ASSIGN_DATE
}

GET_OLD_RECORD_ORDERS{
SELECT ORDER_ID , OFFENDER_BOOK_ID, CASE_ID, COURT_DATE, ORDER_TYPE, ISSUING_AGY_LOC_ID, COURT_INFO_ID, ORDER_STATUS, CREATE_USER_ID, MODIFY_USER_ID, MODIFY_DATETIME, CREATE_DATETIME , DUE_DATE, COURT_SERIOUSNESS_LEVEL, ORDER_SERIOUSNESS_LEVEL, REQUEST_DATE , STAFF_WORK_ID, EVENT_ID, COMPLETE_DATE , COMPLETE_STAFF_ID, INTERVENTION_TIER_CODE, NON_REPORT_FLAG , CPS_RECEIVED_DATE , COMMENT_TEXT, ISSUE_DATE, MESSAGE_ID, WORKFLOW_ID , OFFENDER_PROCEEDING_ID , SEAL_FLAG FROM ORDERS WHERE ORDER_ID = :ORDER_ID
}
