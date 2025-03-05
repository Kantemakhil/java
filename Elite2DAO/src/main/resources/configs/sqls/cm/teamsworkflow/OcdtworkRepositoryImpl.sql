
OCDTWORK_FIND_RGREASON {
 	SELECT REF_CODE.CODE  , REF_CODE.DESCRIPTION  FROM     REFERENCE_CODES REF_CODE WHERE DOMAIN = 'COMPLETE_RSN' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDTWORK_FIND_RGCOMPLETED {
 	SELECT  REF_CODE.DESCRIPTION  , REF_CODE.CODE   FROM REFERENCE_CODES REF_CODE  WHERE DOMAIN = 'WORK_STATUS' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDTWORK_FIND_CGFKCRTMVTMPAGYLOCID {
 	SELECT AGY_LOC.AGY_LOC_ID  CODE, AGY_LOC.DESCRIPTION  DESCRIPTION FROM   AGENCY_LOCATIONS AGY_LOC WHERE   AGY_LOC.AGY_LOC_ID  IN (SELECT CA.AGY_LOC_ID    FROM CASELOAD_AGENCY_LOCATIONS CA WHERE CA.CASELOAD_ID  = :caseLoadId AND CA.AGY_LOC_ID NOT IN ('OUT' , 'TRN' ) ) ORDER BY 1 ,2
}

OCDTWORK_FIND_RGSEX {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION ,REF_CODE.CODE  SEX FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'SEX' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDTWORK_FIND_RGWORKTYPE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION ,REF_CODE.CODE  WORK_TYPE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'TASK_TYPE' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDTWORK_FIND_RGWORKSUBTYPE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION,REF_CODE.CODE  WORK_SUB_TYPE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'TASK_SUBTYPE' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDTWORK_FIND_RGPOSITION {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION ,REF_CODE.CODE  POSITION FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'STAFF_POS' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDTWORK_FIND_RGROLE {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION ,REF_CODE.CODE  ROLE FROM   REFERENCE_CODES REF_CODE WHERE   DOMAIN = 'STAFF_ROLE' AND (ACTIVE_FLAG = 'Y' OR ::MODE='ENTER-QUERY' ) ORDER BY LIST_SEQ ,DESCRIPTION ,CODE
}

OCDTWORK_FIND_RGTEAMSTAFF {
 	SELECT DISTINCT
    t.team_code     team_code,
    t.description   description
FROM
    automation_teams          t,
    team_staff_members   tm
WHERE
    tm.team_id = t.team_id
 	
 }
OCDTWORK_FIND_RGSTAFFSEARCH {
 	SELECT DISTINCT SM.LAST_NAME LAST_NAME, SM.LAST_NAME DESCRIPTION , SM.FIRST_NAME FIRST_NAME , SM.STAFF_ID, SM.STAFF_ID CODE FROM STAFF_MEMBERS SM , TEAM_MEMBERS TM , STAFF_LOCATION_ROLES SR WHERE TM.STAFF_ID = SM.STAFF_ID AND TM.STAFF_ID = SR.SAC_STAFF_ID AND TM.AGY_LOC_ID = SR.CAL_AGY_LOC_ID AND TM.LOC_ROLE_FROM_DATE = SR.FROM_DATE AND TM.POSITION = SR.POSITION AND TM.ROLE = SR.ROLE AND SR.CAL_AGY_LOC_ID = :agylocId AND SR.TO_DATE IS NULL ORDER BY        1 , 2
}

OCDTWORK_STAFFQUEUE_UPDATE_TAG_WORKFLOW.BROWSE_QUEUE {
	UPDATE TAG_WORKFLOW.BROWSE_QUEUE set 
}

OCDTWORK_TEAMMEMBERS_UPDATE {
	UPDATE SELECT SM.LAST_NAME LAST_NAME, SM.FIRST_NAME FIRST_NAME, TM.POSITION POSITION, TM.ROLE ROLE, SM.SEX_CODE SEX_CODE,TM.NO_OF_TASKS NO_OF_TASKS, TM.TEAM_MEMBER_ID TEAM_MEMBER_ID, TM.STAFF_ID STAFF_ID, T.DESCRIPTION TEAM_NAME,TM.AGY_LOC_ID AGY_LOC_ID FROM STAFF_MEMBERS SM, STAFF_LOCATION_ROLES SR, TEAM_MEMBERS TM, TEAMS T WHERE TM.STAFF_ID=SM.STAFF_ID AND TM.TEAM_ID = T.TEAM_ID AND TM.STAFF_ID = SR.SAC_STAFF_ID AND TM.AGY_LOC_ID = SR.CAL_AGY_LOC_ID AND TM.LOC_ROLE_FROM_DATE = SR.FROM_DATE AND TM.POSITION = SR.POSITION AND TM.ROLE = SR.ROLE AND SR.TO_DATE IS NULL AND TM.ACTIVE_FLAG = 'Y' set /* where */
}


OCDTWORK_DEFAULT_AGYRSN_ {
	SELECT COUNT(0) FROM AGENCY_LOCATIONS AGY_LOC WHERE AGY_LOC.AGY_LOC_ID  IN (  SELECT CA.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CA WHERE CA.CASELOAD_ID  = V_GLOBAL_CASELOAD_ID AND CA.AGY_LOC_ID NOT IN ('OUT', 'TRN'))
}

OCDTWORK_DEFAULT_AGYRSN_ {
	SELECT AGY_LOC.AGY_LOC_ID  AGY_LOC_ID ,AGY_LOC.DESCRIPTION  DSP_DESCRIPTION2 FROM AGENCY_LOCATIONS AGY_LOC WHERE AGY_LOC.AGY_LOC_ID  IN ( SELECT CA.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CA WHERE CA.CASELOAD_ID  = V_GLOBAL_CASELOAD_ID AND CA.AGY_LOC_ID NOT IN ('OUT', 'TRN'))
}

OCDTWORK_SELECT_TEAMMEMBERS{
select sm.last_name last_name, sm.first_name first_name, tm.position , tm.role , sm.sex_code gender, tm.no_of_tasks no_of_tasks, tm.team_member_id team_member_id, tm.staff_id staff_id, t.description team_name, tm.agy_loc_id agy_loc_id from staff_members sm, staff_location_roles sr, team_members tm, teams t where tm.staff_id = sm.staff_id and tm.team_id = t.team_id and tm.staff_id = sr.sac_staff_id and tm.agy_loc_id = sr.cal_agy_loc_id and tm.loc_role_from_date = sr.from_date and tm.position = sr.position and tm.role = sr.role and sr.to_date is null and tm.active_flag = 'Y' 
}

OCDTWORK_SELECT_TASKS {
 SELECT   queue_name, team_id, staff_id, team_member_id, work_id,
                   offender_book_id, assignment_date, due_date, msgid,
                   MESSAGE_TEXT, workflow_type, ORIGINAL_MSGID,  
               event_date,function_type,
                   severity_code, acknowledgement_required,
                   acknowledgement_subject, sender_id 
              FROM TABLE ( Tag_Workflow.scan_queue ( :CLUSTER_ID,
                                                     NULL,
                                                     NULL ) ) WHERE STAFF_ID = :staffId
                                                     ORDER BY ORIGINAL_MSGID DESC
}

OCDTWORK_SELECT_MEMO {
 SELECT   queue_name, team_id, staff_id, team_member_id, work_id,
                   offender_book_id, assignment_date, due_date, msgid,
                   MESSAGE_TEXT, workflow_type, ORIGINAL_MSGID,  
               event_date,function_type, 
                   severity_code, acknowledgement_required, 
                   acknowledgement_subject, sender_id 
              FROM TABLE ( Tag_Workflow.scan_queue ( :CLUSTER_ID,
                                                     NULL,
                                                     NULL ) ) WHERE STAFF_ID = :staffId
                                                     ORDER BY ORIGINAL_MSGID DESC
}

GET_OFFENDER_DETAILS {
 SELECT last_name, first_name, offender_id_display, root_offender_id
           FROM V_HEADER_BLOCK_FN(:USERID) v_header_block
          WHERE offender_book_id = :offenderBookId and rownum=1
}
