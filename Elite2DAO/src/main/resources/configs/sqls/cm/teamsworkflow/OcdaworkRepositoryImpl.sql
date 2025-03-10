
 
OCDBRECI_FIND_CGFKOFFTXN1TXNTYPE {
 	SELECT
  TXN_TYPE.TXN_TYPE CODE /* CG$FK */, TXN_TYPE.DESCRIPTION
FROM TRANSACTION_TYPES TXN_TYPE
WHERE
  TXN_TYPE.TXN_TYPE IN
    (SELECT TXN_OPER.TXN_TYPE
     FROM TRANSACTION_OPERATIONS TXN_OPER
     WHERE
       MODULE_NAME = 'OCDBRECI' AND
       CASELOAD_ID =
         (SELECT WORKING_CASELOAD_ID
          FROM STAFF_MEMBERS
          WHERE
            USER_ID = USER) ) AND
  CASELOAD_TYPE =
    (SELECT CS.CASELOAD_TYPE
     FROM CASELOADS CS
     WHERE
       CASELOAD_ID =
         (SELECT WORKING_CASELOAD_ID
          FROM STAFF_MEMBERS
          WHERE
            USER_ID = USER))
ORDER BY
  TXN_TYPE.LIST_SEQ ASC, TXN_TYPE.TXN_TYPE ASC

}OCDAWORK_FIND_RGREASON {
 	SELECT REF_CODE.CODE  ,            REF_CODE.DESCRIPTION    FROM     REFERENCE_CODES REF_CODE WHERE DOMAIN = 'COMPLETE_RSN' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDAWORK_FIND_RGSEX {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION  /* CG$FK */        ,REF_CODE.CODE  SEX FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'SEX' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDAWORK_FIND_RGWORKTYPE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION  /* CG$FK */        ,REF_CODE.CODE  WORK_TYPE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'TASK_TYPE' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDAWORK_FIND_RGWORKSUBTYPE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION  /* CG$FK */        ,REF_CODE.CODE  CODE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'TASK_SUBTYPE' AND (ACTIVE_FLAG = 'Y' OR ''='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDAWORK_FIND_RGWORKFLOWTYPE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION  /* CG$FK */        ,REF_CODE.CODE  WORKFLOW_TYPE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'ALERT_TASK' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDAWORK_FIND_RGPOSITION {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION  /* CG$FK */        ,REF_CODE.CODE  POSITION FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'STAFF_POS' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDAWORK_FIND_RGROLE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION  /* CG$FK */        ,REF_CODE.CODE  ROLE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'STAFF_ROLE' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDAWORK_FIND_RGTEAMSTAFF {
 SELECT DISTINCT t.team_code code, t.description description, t.team_id FROM automation_teams t, team_staff_members tm, team_agency_loc ta WHERE tm.team_id = t.team_id and ta.team_id = t.team_id AND ta.agy_loc_id = :agylocId AND tm.active_flag = 'Y' ORDER BY 1
}

OCDAWORK_FIND_RGTEAMMEMBERS {
 	SELECT DISTINCT SM.LAST_NAME LAST_NAME , SM.FIRST_NAME FIRST_NAME FROM STAFF_MEMBERS SM , TEAM_MEMBERS TM WHERE TM.STAFF_ID=SM.STAFF_ID AND TM.TEAM_ID=::TEAMID AND ((::OCDAWORKACCESS='SELF' AND TM.STAFF_ID=::STAFFID ) OR (::OCDAWORKACCESS='ALL' ) ) ORDER BY 1 ,2
}

OCDAWORK_TEAMQUEUE_UPDATE_TAG_WORKFLOW.BROWSE_QUEUE {
	UPDATE TAG_WORKFLOW.BROWSE_QUEUE set /* where */
}

OCDAWORK_TEAMMEMBERS_UPDATE_SELECT SM.LAST_NAME LAST_NAME, SM.FIRST_NAME FIRST_NAME, TM.POSITION POSITION, TM.ROLE ROLE, SM.SEX_CODE SEX_CODE, TM.TEAM_MEMBER_ID TEAM_MEMBER_ID, T.TEAM_ID TEAM_ID, T.TEAM_CODE TEAM_CODE, TM.STAFF_ID STAFF_ID, TM.NO_OF_TASKS NO_OF_TASKS FROM STAFF_MEMBERS SM, TEAM_MEMBERS TM, TEAMS T WHERE TM.STAFF_ID=SM.STAFF_ID AND TM.TEAM_ID=T.TEAM_ID AND TM.ACTIVE_FLAG = 'Y' {
	UPDATE SELECT SM.LAST_NAME LAST_NAME, SM.FIRST_NAME FIRST_NAME, TM.POSITION POSITION, TM.ROLE ROLE, SM.SEX_CODE SEX_CODE, TM.TEAM_MEMBER_ID TEAM_MEMBER_ID, T.TEAM_ID TEAM_ID, T.TEAM_CODE TEAM_CODE, TM.STAFF_ID STAFF_ID, TM.NO_OF_TASKS NO_OF_TASKS FROM STAFF_MEMBERS SM, TEAM_MEMBERS TM, TEAMS T WHERE TM.STAFF_ID=SM.STAFF_ID AND TM.TEAM_ID=T.TEAM_ID AND TM.ACTIVE_FLAG = 'Y' set /* where */
}
OCDAWORK_TEAMMEMBERS_EXECUTEQUERY {
 SELECT sm.last_name last_name, sm.first_name first_name, sm.sex_code gender, tm.team_member_id team_member_id, t.team_id team_id, t.team_code team_code, tm.staff_id staff_id FROM staff_members sm, team_staff_members tm, automation_teams t WHERE tm.staff_id = sm.staff_id AND tm.team_id = t.team_id AND tm.active_flag = 'Y' AND tm.team_id = :TEAM_ID AND ( ( 'ALL' = 'SELF' AND sm.staff_id IN ( SELECT staff_id FROM staff_members WHERE user_id = :createUserId ) ) OR ( 'ALL' = 'ALL' ) ) ORDER BY last_name, first_name 
}
OCDAWORK_TEAMQUEUE_EXECUTEQUERY {
SELECT   QUEUE_NAME, TEAM_ID, STAFF_ID, TEAM_MEMBER_ID, WORK_ID,
                   OFFENDER_BOOK_ID, ASSIGNMENT_DATE, DUE_DATE, MSGID,
                   MESSAGE_TEXT, WORKFLOW_TYPE, ORIGINAL_MSGID,   -- ADDED ORIGINAL_MSGID  FOR DEFECT 4458.
                                                                   EVENT_DATE,FUNCTION_TYPE,  -- ADDED NIKO
                   SEVERITY_CODE, ACKNOWLEDGEMENT_REQUIRED, -- 20-FEB-2009 STAFF MEMOS ENHANCEMENT
                   ACKNOWLEDGEMENT_SUBJECT, SENDER_ID -- 20-FEB-2009 STAFF MEMOS ENHANCEMENT
              FROM TABLE (TAG_WORKFLOW.SCAN_QUEUE ( :CLUSTER_ID,
                                                     NULL,
                                                     NULL)) WHERE TEAM_ID = :TEAM_ID
                                                     ORDER BY ORIGINAL_MSGID DESC
  }
OCDAWORK_GET_CLUSTER_STAFFID {
 SELECT QUEUE_CLUSTER_ID FROM STAFF_MEMBERS  WHERE STAFF_ID = :STAFF_ID
  }
OCDAWORK_GET_CLUSTER_TEAMID {
  SELECT QUEUE_CLUSTER_ID FROM TEAMS WHERE TEAM_ID = :TEAM_ID
  }
OCDAWORK_GET_STAFF_ID {
SELECT staff_id FROM    staff_members WHERE user_id = user
    }
    
  OCDAWORK_STAFF_ID_UPDATE{ 
    
        UPDATE TASK_DETAILS SET STAFF_ID=:staffId ,TEAM_MEMBER_ID=:teamMemberId , MODIFY_USER_ID=:modifyUserId WHERE TASK_ID=:taskId
    }
    
    OCDAWORK_NEW_TEAM_ASSIGN{ 
    
        UPDATE TASK_DETAILS SET TEAM_ID=:teamId, MODIFY_USER_ID=:modifyUserId WHERE TASK_ID=:taskId
    }
    
    OCDAWORK_COMPLETE_TASK{ 
    
    UPDATE TASK_DETAILS SET COMPLETION_DATE=:completionDate ,COMPLETE_REASON_CODE=:completeReasonCode , COMPLETE_COMMENT_TEXT=:completeCommentText,COMPLETE_USER_ID=:completeUserId WHERE TASK_ID=:taskId
    }
    
    OCDAWORK_TASK_GET_DETAILS{ 
    
      SELECT TASK_ID,TEAM_ID, STAFF_ID, TEAM_MEMBER_ID, WORK_ID,OFFENDER_BOOK_ID, ASSIGNMENT_DATE, DUE_DATE,MESSAGE_TEXT, WORKFLOW_TYPE,EVENT_DATE,FUNCTION_TYPE FROM TASK_DETAILS WHERE TEAM_ID=:teamId AND STAFF_ID IS NULL AND COMPLETE_REASON_CODE IS NULL
    
    }
    
    OCDAWORK_TASK_DETAILS{ 
    
      SELECT VHB.OFFENDER_ID, TD.TASK_ID, TD.TEAM_ID, TD.STAFF_ID, TD.TEAM_MEMBER_ID,TD.WORK_ID, TD.OFFENDER_BOOK_ID, TD.ASSIGNMENT_DATE, TD.DUE_DATE,TD.MESSAGE_TEXT,TD.SOURCE_NAME, TD.WORKFLOW_TYPE, TD.EVENT_DATE, TD.FUNCTION_TYPE
    FROM TASK_DETAILS TD, V_HEADER_BLOCK_FN(:USERID) VHB WHERE
TD.OFFENDER_BOOK_ID=VHB.OFFENDER_BOOK_ID and
TD.TASK_ID=:taskId
}

OCDAWORK_FIND_RGSTAFFSEARCH { 
SELECT DISTINCT sm.last_name || ', ' || sm.first_name staff_name, sm.user_id user_id, sm.staff_id FROM staff_members sm, team_staff_members tm WHERE tm.staff_id = sm.staff_id AND tm.team_id = :teamId 
}
