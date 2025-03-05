
OCMSVPHA_FIND_RGPSMODTYPE {
 	SELECT
    ref_code.description,
    ref_code.code code
FROM
    reference_codes ref_code
WHERE
    domain = 'PS_MOD_TYPE'
    AND active_flag = 'Y'
ORDER BY
    list_seq,
    description,
    code
}

OCMSVPHA_FIND_RGPSPHASE {
 	SELECT
    ref_code.description description,
    ref_code.code code
FROM
    reference_codes ref_code
WHERE
    domain = 'PS_PHASE'
    AND active_flag = 'Y'
ORDER BY
    list_seq,
    description,
    code
    
}

OCMSVPHA_VPRGPHSS_FIND_V_PROGRAM_PHASES {
 SELECT
    program_phase_id,
    program_id,
    list_seq,
    phase_type,
    phase_type_desc,
    description,
    comment_text,
    no_of_sessions,
    capacity,
    session_length,
    module_flag,
    module_type,
    module_type_desc,
    active_flag,
    expiry_date,
    break_allowed_flag
     FROM v_program_phases
     where  
     program_id=:programId
     ORDER BY list_seq asc, description
}
OCMSVPHA_VPRGPHSS_INSERT_V_PROGRAM_PHASES {
insert into PROGRAM_SERVICES(PROGRAM_ID, LIST_SEQ, DESCRIPTION, PROGRAM_CLASS, NO_OF_SESSIONS, MODULE_FLAG, ACTIVE_FLAG, capacity, SESSION_LENGTH, MODULE_TYPE, PARENT_PROGRAM_ID, CREATE_USER_ID , CREATE_DATETIME , MODIFY_DATETIME , COMMENT_TEXT) values(:programPhaseId, :listSeq, :description, 'PRG_PH', :noOfSessions, :moduleFlag, :activeFlag, :capacity, :sessionLength, :moduleType, :programId, :createUserId , current_timestamp , null , :commentText)
}

OCMSVPHA_VPRGPHSS_UPDATE_V_PROGRAM_PHASES {
 update program_services set LIST_SEQ =:listSeq, DESCRIPTION =:description, NO_OF_SESSIONS =:noOfSessions, module_type =:moduleType , capacity =:capacity , session_length =:sessionLength, COMMENT_TEXT =:commentText, MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :userName where program_id =:programPhaseId 
}

OCMSVPHA_VPRGPHSS_DELETE_V_PROGRAM_PHASES { 
	DELETE FROM program_services  where PROGRAM_ID=:programPhaseId
}
OCMSVPHA_GET_SESSION_LENGTH {
select
	trunc(:SESSIONLENGTH / 60)|| ':' || lpad(mod(:SESSIONLENGTH, 60)::char, 2, '0')
from
	program_services
where
	program_id =:programPhaseId
}
OCMSVPHA_GET_DESCRIPTION {
	SELECT CODE FROM   REFERENCE_CODES REF_CODE WHERE DOMAIN = 'PS_PHASE' AND DESCRIPTION = :CODE
}

OCMSVPHA_GET_DESCRIPTION_ONE {
	SELECT DESCRIPTION FROM   REFERENCE_CODES REF_CODE WHERE DOMAIN = 'PS_PHASE' AND CODE = :CODE
}





OCMSVPHA_COUNT_OF_COURCE_ACTIVITY{
 SELECT count(*)
           FROM COURSE_ACTIVITIES
          WHERE program_id = :programId
           limit 1
}
OCMSVPHA_PROGRAM_ID{
        select NEXTVAL('program_id')
 }
OCMSVPHA_GET_NEXT_PHASE_LIST_SEQ{ 
 SELECT COALESCE ( MAX ( list_seq ), 0 ) + 1
           FROM v_program_phases
          WHERE program_id = :programId
          
 }  
 
 OCMSVPHA_MODULE_TYPE{
 SELECT DESCRIPTION  FROM REFERENCE_CODES REF_CODE WHERE DOMAIN = 'PS_MOD_TYPE' AND CODE=:CODE
 }
 
 OCMSVPHA_CONSTRAINT_VALIDATIONS{
   select distinct table_name from all_constraints ac where constraint_name = :CONSTRAINTNAME
   }
