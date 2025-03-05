
OSUNTASK_FIND_RGWORKS {

 SELECT  ref_cd1.description description, ref_cd2.description work_sub_type, to_char(works.work_id ) codes, ref_cd1.code code    FROM WORKS         ,REFERENCE_CODES REF_CD1         ,REFERENCE_CODES REF_CD2   WHERE WORKS.WORKFLOW_TYPE = 'TASK'     AND WORKS.ACTIVE_FLAG   = 'Y'     AND WORKS.MANUAL_SELECT_FLAG   = 'Y'     AND CASE WORKS.CASELOAD_TYPE  WHEN 'BOTH'  THEN  'INST'  ELSE WORKS.CASELOAD_TYPE  END= 'INST'     AND WORKS.WORK_TYPE = REF_CD1.CODE     AND REF_CD1.DOMAIN = 'TASK_TYPE'     AND WORKS.WORK_SUB_TYPE = REF_CD2.CODE     AND REF_CD2.DOMAIN = 'TASK_SUBTYPE'  ORDER BY REF_CD1.LIST_SEQ ASC , REF_CD1.DESCRIPTION ASC , REF_CD2.LIST_SEQ ASC , REF_CD2.DESCRIPTION ASC

}

OSUNTASK_FIND_RGSTAFF {
SELECT staff_name description, staff_id code FROM ( SELECT DISTINCT stf_mbrs.last_name || ', ' || stf_mbrs.first_name staff_name, stf_mbrs.staff_id FROM staff_members stf_mbrs, automation_teams teams, team_staff_members team_mbr WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND team_mbr.active_flag = 'Y' AND stf_mbrs.staff_id = team_mbr.staff_id AND stf_mbrs.status = 'ACTIVE' AND NOT EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workid IS NOT NULL UNION SELECT DISTINCT stf_mbrs.last_name || ', ' || stf_mbrs.first_name staff_name, stf_mbrs.staff_id FROM staff_members stf_mbrs, automation_teams teams, team_staff_members team_mbr, team_functions team_funcs, offender_team_assignments off_teams WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND team_mbr.active_flag = 'Y' AND stf_mbrs.staff_id = team_mbr.staff_id AND stf_mbrs.status = 'ACTIVE' AND teams.team_id = team_funcs.team_id AND team_funcs.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id = :workid ) AND team_funcs.team_id = off_teams.team_id AND team_funcs.function_type = off_teams.function_type AND :poffenderbookid = off_teams.offender_book_id AND EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workid IS NOT NULL UNION SELECT DISTINCT stf_mbrs.last_name || ', ' || stf_mbrs.first_name staff_name, stf_mbrs.staff_id FROM staff_members stf_mbrs, automation_teams teams, team_staff_members team_mbr, team_functions team_funcs WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND team_mbr.active_flag = 'Y' AND stf_mbrs.staff_id = team_mbr.staff_id AND stf_mbrs.status = 'ACTIVE' AND teams.team_id = team_funcs.team_id AND team_funcs.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id = :workid ) AND NOT EXISTS ( SELECT 1 FROM offender_team_assignments off_teams WHERE :poffenderbookid = off_teams.offender_book_id AND off_teams.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id = :workid ) ) AND EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workid IS NOT NULL ) ORDER BY 1
}

OSUNTASK_FIND_RGTEAMS {


SELECT team_name description, team_code code, team_id FROM ( SELECT DISTINCT teams.description team_name, teams.team_code team_code, teams.team_id team_id FROM automation_teams teams, team_staff_members team_mbr WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND ( ( :staffid IS NULL ) OR ( :staffid = team_mbr.staff_id ) ) AND NOT EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workid IS NOT NULL UNION SELECT DISTINCT teams.description team_name, teams.team_code team_code, teams.team_id team_id FROM automation_teams teams, team_staff_members team_mbr, team_functions team_funcs, offender_team_assignments off_teams WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND ( ( :staffid IS NULL ) OR ( :staffid = team_mbr.staff_id ) ) AND teams.team_id = team_funcs.team_id AND team_funcs.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id = :workid ) AND team_funcs.team_id = off_teams.team_id AND team_funcs.function_type = off_teams.function_type AND :poffenderbookid = off_teams.offender_book_id AND EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workid IS NOT NULL UNION SELECT DISTINCT teams.description team_name, teams.team_code team_code, teams.team_id team_id FROM automation_teams teams, team_staff_members team_mbr, team_functions team_funcs WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND ( ( :staffid IS NULL ) OR ( :staffid = team_mbr.staff_id ) ) AND teams.team_id = team_funcs.team_id AND team_funcs.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id = :workid ) AND NOT EXISTS ( SELECT 1 FROM offender_team_assignments off_teams WHERE :poffenderbookid = off_teams.offender_book_id AND off_teams.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id = :workid ) ) AND EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workid IS NOT NULL ) ORDER BY 1
}


OSUNTASK_CREATE_FORM_GLOBALS {

	SELECT DESCRIPTION  FROM OMS_MODULES WHERE MODULE_NAME = :V_FORM_NAME

}

OSUNTASK_GET_DISPLAY_AUTO_ {
--IS SELECT OFFENDERS.LAST_NAME || ', ' || OFFENDERS.FIRST_NAME  || DECODE(OFFENDERS.MIDDLE_NAME, NULL, NULL, ' ' || SUBSTR(OFFENDERS.MIDDLE_NAME, 1,1) || '.' ) || ' (' ||  DECODE(OMS_MISCELLANEOUS.GET_PROFILE_VALUE ('DISPLAY', 'ID_DISPLAY'), 'Y', UPPER( LPAD( LTRIM ( OFFENDERS.OFFENDER_ID_DISPLAY, '0' ), 10, '0')), OFFENDERS.OFFENDER_ID_DISPLAY) || ')' FROM OFFENDER_BOOKINGS, OFFENDERS WHERE OFFENDER_BOOKINGS.OFFENDER_BOOK_ID = :POFFENDERBOOKID AND OFFENDER_BOOKINGS.OFFENDER_ID      = OFFENDERS.OFFENDER_ID

}

OSUNTASK_VALIDATE_ADHOC_WORKFLOW_ {
	SELECT TEAM_MEMBERS.TEAM_MEMBER_ID FROM TEAM_MEMBERS WHERE TEAM_MEMBERS.TEAM_ID  = :TEAMID AND TEAM_MEMBERS.STAFF_ID = :STAFFID AND TEAM_MEMBERS.ACTIVE_FLAG = 'Y'
}

OSUNTASK_GET_TEAM_MEMBER_ID{
 SELECT count(*) FROM team_staff_members team_members WHERE team_members.team_id = :teamId AND team_members.staff_id = :staffId AND team_members.active_flag = 'Y'
}

OSUNMEMO_GET_SUB_STRING{
select SUBSTR(:offName || ':' ||  :details, 1, 4000) from dual
}
OSUNTASK_TEAM_INSERT {

	INSERT INTO TASK_DETAILS(TASK_ID,OFFENDER_BOOK_ID,WORK_ID,WORKFLOW_TYPE,ASSIGNMENT_DATE,
	TEAM_ID,STAFF_ID,TEAM_MEMBER_ID,DUE_DATE,MESSAGE_TEXT,EVENT_DATE,FUNCTION_TYPE,CREATE_USER_ID,SOURCE_NAME) VALUES
	(:taskId,:offenderBookId,:workId,:workflowType,:assignmentDate,:teamId,:staffId,:teamMemberId ,:dueDate ,:messageText ,:eventDate ,:functionType,:createUserId,:sourceName )
}

TASK_SEQ_ID{ 
SELECT task_details_task_id_SEQ.nextval next_seq from dual
}

OSUNTASK_GET_USER_ID{ 

SELECT USER_ID FROM STAFF_MEMBERS WHERE STAFF_ID=:staffId

}

OSUNTASK_GET_TEAM_CODE{ 

SELECT TEAM_CODE FROM automation_teams WHERE TEAM_ID=:teamId

}
