INSERT_OFFNDER_CASE_NOTES{
insert
	into
	OFFENDER_CASE_NOTES ( offender_book_id,
	case_note_id,
	staff_id,
	contact_date,
	contact_time,
	case_note_type,
	case_note_sub_type,
	CASE_NOTE_TEXT,
	note_source_code,
	event_id,
	date_creation,
	time_creation,
	create_user_id ,
	CREATE_DATETIME,
	MODIFY_DATETIME )
values ( :offenderBookId,
NEXTVAL('case_note_id'),
:staffId,
date_TRUNC ('D',:contactTime::timestamp ),
:contactTime,
:caseNoteType,
:caseNoteSubType,
:caseNoteText,
:noteSourceCode,
:eventId,
date_TRUNC ('D',
CURRENT_TIMESTAMP),
CURRENT_TIMESTAMP,
:senderId ,
CURRENT_TIMESTAMP ,
CURRENT_TIMESTAMP )
}

GET_EVENT_ID{
 SELECT event_id
         FROM orders
         WHERE offender_book_id = :OFFENDER_BOOK_ID
         AND workflow_id = :ORIGINAL_MSGID
         FOR UPDATE OF order_status
 }
 
TEAM_MEMBERS_UPDATE{ 
 update TEAM_MEMBERS set no_of_tasks = coalesce ( no_of_tasks, 0 ) + :p_adjustment, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where team_member_id = :p_team_member_id
 
}
 
COMPLETE_TASK_UPDATE_ORDER_STATUS{ 
update orders set order_status = 'C', complete_date = CURRENT_TIMESTAMP, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where offender_book_id = :OFFENDER_BOOK_ID and workflow_id = :ORIGINAL_MSGID
 }
 
 COMPLETE_TASK_UPDATE_TEAM_MEMBERS_NO_OF_TASKS{
 update TEAM_MEMBERS set no_of_tasks = coalesce ( no_of_tasks, 0 ) + :p_adjustment, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where team_member_id = :p_team_member_id
}  
ASSIGN_TO_TEAM_MEMBER_FROM_TEAM_MEMBERS{
 SELECT staff_id, team_id
        INTO l_staff_id, l_team_id
        FROM TEAM_MEMBERS
       WHERE team_member_id = :P_TEAM_MEMBER_ID
 }      
 
ASSIGN_TO_TEAM_MEMBER_UPDATE_NO_OF_TASKS{ 
update TEAM_MEMBERS set no_of_tasks = coalesce ( no_of_tasks, 0 ) + :P_ADJUSTMENT, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where team_member_id = :P_TEAM_MEMBER_ID
  }  
  
 ASSIGN_TO_TEAM_MEMBER_GET_WORK_ID{ 
   SELECT work_id 
         FROM works
        WHERE work_id = :P_WORK_ID
             AND work_type = :P_WORK_TYPE 
             AND work_sub_type = :P_WORK_SUB_TYPE
} 

ASSIGN_TO_TEAM_MEMBER_GET_PTR_ID{
 SELECT ptr_id
           FROM offender_ptr
         WHERE ptr_id = :P_PTR_ID
         FOR UPDATE OF ptr_id WAIT 1
}         

ASSIGN_TO_TEAM_MEMBER_UPDATE_OFFENDER_PTR{
update offender_ptr set assign_receiv_staff_id = :P_STAFF_ID, assign_receiv_staff_date = :P_ASSIGNMENT_DATE, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where ptr_id = :P_PTR_ID
 }      
         
C_QUEUES_CURSOR{
SELECT owner || '.' || name  description FROM all_queues WHERE owner LIKE 'TAGWFC%' AND ( name LIKE 'TAG%WF%PERSONAL%T%C%' OR name LIKE 'TAG%WF%GENERAL%C%' )
}         
         
SELECT_TEAM_MEMBERS{
SELECT staff_id FROM TEAM_MEMBERS WHERE team_member_id =:team_member_id      
 }   
 
 
 GET_CLUSTER_STAFF_ID{
 SELECT QUEUE_CLUSTER_ID FROM STAFF_MEMBERS WHERE STAFF_ID = :P_STAFF_ID
 }
 
 GET_CLUSTER_TEAM_ID{
 SELECT QUEUE_CLUSTER_ID FROM TEAMS WHERE TEAM_ID = :P_TEAM_ID
 }
 
 REASSIGN_TO_TEAM_MEMBER_SELECT{
 SELECT TEAM_ID, STAFF_ID FROM TEAM_MEMBERS WHERE TEAM_MEMBER_ID = :P_NEW_TEAM_MEMBER_ID
 }
 