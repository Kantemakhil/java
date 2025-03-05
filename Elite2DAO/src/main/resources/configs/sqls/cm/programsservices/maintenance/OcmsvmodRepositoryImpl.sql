
OCMSVMOD_VPRGMDLS_FIND_V_PROGRAM_MODULES {
 SELECT
    program_module_id,
    list_seq,
    description,
    program_phase_id,
    no_of_sessions,
    session_length,
    start_flag,
    active_flag
FROM
    v_program_modules
WHERE
    program_phase_id =:programPhaseId
ORDER BY
    list_seq,
    description
}
OCMSVMOD_INSERT_MODULES{
insert into PROGRAM_SERVICES(PROGRAM_ID, LIST_SEQ, DESCRIPTION, PROGRAM_CLASS, NO_OF_SESSIONS, START_FLAG, ACTIVE_FLAG, PARENT_PROGRAM_ID, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME) values(NEXTVAL('program_id'), :listSeq, :description, 'PRG_MOD', :noOfSessions, :startFlag, :activeFlag, :programPhaseId, :createUserId, CURRENT_TIMESTAMP , null)
}

OCMSVMOD_VPRGMDLS_UPDATE_V_PROGRAM_MODULES {
update program_services set LIST_SEQ =:listSeq , DESCRIPTION =:description, NO_OF_SESSIONS =:noOfSessions, START_FLAG =:startFlag, modify_datetime = current_timestamp , modify_user_id = :modifyUserId where program_id =:programModuleId

}


OCMSVMOD_VPRGMDLS_DELETE_V_PROGRAM_MODULES { 
	DELETE FROM program_services  where PROGRAM_ID=:programModuleId
}

OCMSVMOD_GETPROGRAMID{
       SELECT NEXTVAL('program_id') 
        FROM DUAL
}
OCMSVMOD_CHECKUNIQUE_LIST_SEQ{
SELECT count(*)
           FROM v_program_modules
          WHERE program_phase_id = :programPhaseId
            AND list_seq = :listSeq
            AND program_module_id != coalesce (:programModuleId, -1 )

 }
 
 OCMSVDMOD_UPDATE_V_PROGRAM_PHASES{
               UPDATE  v_program_phases
         SET no_of_sessions = :p_total
       WHERE program_phase_id = :p_program_phase_id
       }
    
 OCMSVMOD_CONSTRAINT_VALIDATIONS{
         SELECT 
  DISTINCT C.TABLE_NAME CHILD_TABLE
FROM 
  USER_CONSTRAINTS C, USER_CONSTRAINTS R, USER_CONS_COLUMNS CC, 
  USER_CONS_COLUMNS RC
WHERE
R.TABLE_NAME = 'PROGRAM_SERVICES' AND
 C.CONSTRAINT_NAME = :CONSTRAINTNAME AND
  C.CONSTRAINT_TYPE = 'R' AND
  C.R_OWNER = R.OWNER AND
  C.R_CONSTRAINT_NAME = R.CONSTRAINT_NAME AND
  C.CONSTRAINT_NAME = CC.CONSTRAINT_NAME AND
  C.OWNER = CC.OWNER AND
  R.CONSTRAINT_NAME = RC.CONSTRAINT_NAME AND
  R.OWNER = RC.OWNER AND
  CC.POSITION = RC.POSITION AND
  C.OWNER='OMS_OWNER' AND
  R.OWNER='OMS_OWNER' 
            
   }
  OCMSVMOD_LISTSEQ{
 SELECT coalesce ( MAX ( list_seq ), 0 ) + 1
           FROM v_program_modules
          WHERE program_phase_id = :programPhaseId
}

OCMSVMOD_GET_SESSION_COUNT{
 SELECT SUM ( coalesce ( no_of_sessions, 0 ) )
           FROM v_program_modules
          WHERE program_phase_id = :programPhaseId
 }
