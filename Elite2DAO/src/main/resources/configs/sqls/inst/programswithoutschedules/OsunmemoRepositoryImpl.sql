
OSUNMEMO_FIND_RGWORKS {
 	SELECT
    ref_cd1.description description ,
    ref_cd2.description work_sub_type,
    to_char(works.work_id ) codes,
    ref_cd1.code code
FROM
    works,
    reference_codes ref_cd1,
    reference_codes ref_cd2
WHERE
        works.workflow_type = 'MEMO'
    AND
        works.active_flag = 'Y'
    AND
        works.manual_select_flag = 'Y'
    AND CASE works.caseload_type WHEN 'BOTH' THEN 'INST' ELSE works.caseload_type END ='INST'
    AND
        works.work_type = ref_cd1.code
    AND
        ref_cd1.domain = 'TASK_TYPE'
    AND
        works.work_sub_type = ref_cd2.code
    AND
        ref_cd2.domain = 'TASK_SUBTYPE'
ORDER BY
    ref_cd1.list_seq ASC,
    ref_cd1.description ASC,
    ref_cd2.list_seq ASC,
    ref_cd2.description ASC
}

OSUNMEMO_FIND_RGSTAFF {
SELECT staff_name description, staff_id code FROM ( SELECT DISTINCT stf_mbrs.last_name || ', ' || stf_mbrs.first_name staff_name, stf_mbrs.staff_id FROM staff_members stf_mbrs, automation_teams teams, team_staff_members team_mbr WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND team_mbr.active_flag = 'Y' AND stf_mbrs.staff_id = team_mbr.staff_id AND stf_mbrs.status = 'ACTIVE' AND NOT EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workId IS NOT NULL UNION SELECT DISTINCT stf_mbrs.last_name || ', ' || stf_mbrs.first_name staff_name, stf_mbrs.staff_id FROM staff_members stf_mbrs, automation_teams teams, team_staff_members team_mbr, team_functions team_funcs, offender_team_assignments off_teams WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND team_mbr.active_flag = 'Y' AND stf_mbrs.staff_id = team_mbr.staff_id AND stf_mbrs.status = 'ACTIVE' AND teams.team_id = team_funcs.team_id AND team_funcs.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id =:workId ) AND team_funcs.team_id = off_teams.team_id AND team_funcs.function_type = off_teams.function_type AND :offenderBookId = off_teams.offender_book_id AND EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workId IS NOT NULL UNION SELECT DISTINCT stf_mbrs.last_name || ', ' || stf_mbrs.first_name staff_name, stf_mbrs.staff_id FROM staff_members stf_mbrs, automation_teams teams, team_staff_members team_mbr, team_functions team_funcs WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND team_mbr.active_flag = 'Y' AND stf_mbrs.staff_id = team_mbr.staff_id AND stf_mbrs.status = 'ACTIVE' AND teams.team_id = team_funcs.team_id AND team_funcs.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id =:workId ) AND NOT EXISTS ( SELECT 1 FROM offender_team_assignments off_teams WHERE :offenderBookId = off_teams.offender_book_id AND off_teams.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id =:workId ) ) AND EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workId IS NOT NULL ) ORDER BY 1
 	}

OSUNMEMO_FIND_RGTEAMS {
 
 SELECT team_name description, team_code code, team_id FROM ( SELECT DISTINCT teams.description team_name, teams.team_code team_code, teams.team_id team_id FROM automation_teams teams, team_staff_members team_mbr WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND ( ( :staffId IS NULL ) OR ( :staffId = team_mbr.staff_id ) ) AND NOT EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workId IS NOT NULL UNION SELECT DISTINCT teams.description team_name, teams.team_code team_code, teams.team_id team_id FROM automation_teams teams, team_staff_members team_mbr, team_functions team_funcs, offender_team_assignments off_teams WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND ( ( :staffId IS NULL ) OR ( :staffId = team_mbr.staff_id ) ) AND teams.team_id = team_funcs.team_id AND team_funcs.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id =:workId ) AND team_funcs.team_id = off_teams.team_id AND team_funcs.function_type = off_teams.function_type AND :offenderBookId = off_teams.offender_book_id AND EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workId IS NOT NULL UNION SELECT DISTINCT teams.description team_name, teams.team_code team_code, teams.team_id team_id FROM automation_teams teams, team_staff_members team_mbr, team_functions team_funcs WHERE teams.active_flag = 'Y' AND teams.team_id = team_mbr.team_id AND ( ( :staffId IS NULL ) OR ( :staffId = team_mbr.staff_id ) ) AND teams.team_id = team_funcs.team_id AND team_funcs.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id =:workId ) AND NOT EXISTS ( SELECT 1 FROM offender_team_assignments off_teams WHERE :offenderBookId = off_teams.offender_book_id AND off_teams.function_type IN ( SELECT function_type FROM work_functions WHERE work_functions.work_id =:workId ) ) AND EXISTS ( SELECT 1 FROM system_profiles WHERE system_profiles.profile_type = 'CLIENT' AND system_profiles.profile_code = 'WF_FLTR_FNC' AND system_profiles.profile_value = 'Y' ) AND :workId IS NOT NULL ) ORDER BY 1
 }

OSUNMEMO_FIND_RGSEVERITY {

 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION ,REF_CODE.CODE  SEVERITY_CODE  FROM   REFERENCE_CODES REF_CODE WHERE   REF_CODE.DOMAIN = 'MEMO_SEVTY' AND REF_CODE.ACTIVE_FLAG = 'Y' AND (REF_CODE.EXPIRED_DATE IS NULL OR REF_CODE.EXPIRED_DATE > current_timestamp ) ORDER BY  REF_CODE.LIST_SEQ , REF_CODE.DESCRIPTION

}


OSUNMEMO_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OSUNMEMO_GET_DISPLAY_AUTO {
select
	offd.last_name || ', ' || offd.first_name ||
	case
		when coalesce(offd.middle_name::text, '') = '' then null
		else ' ' || SUBSTR(offd.middle_name,
		1,
		1) || '.'
	end || ' (' ||
	case
		when oms_miscellaneous_get_profile_value('DISPLAY',
		'ID_DISPLAY')= 'Y' then UPPER( LPAD( LTRIM( offd.offender_id_display, '0' ), 10, '0'))
		else offd.offender_id_display
	end || ')' as names
from
	offender_bookings ,
	offenders offd
where
	offender_bookings.offender_book_id = :offenderBookId::bigint
	and offender_bookings.OFFENDER_ID = offd.OFFENDER_ID
}

OSUNMEMO_GET_TEAM_MEMBER_ID{
 SELECT team_members.team_member_id FROM team_staff_members team_members WHERE team_members.team_id = :teamId AND team_members.staff_id = :staffId AND team_members.active_flag = 'Y'
}

OSUNMEMO_GET_SUB_STRING{
select SUBSTR(:offName || ':' ||  :details, 1, 4000) from dual
}

OSUNMEMO_VALIDATE_ADHOC_WORKFLOW_ {
	SELECT TEAM_MEMBERS.TEAM_MEMBER_ID FROM TEAM_MEMBERS WHERE TEAM_MEMBERS.TEAM_ID  = :TEAMID AND TEAM_MEMBERS.STAFF_ID = :STAFFID AND TEAM_MEMBERS.ACTIVE_FLAG = 'Y'
}
