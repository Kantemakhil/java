C_CHK_CODE{
   SELECT 1 FROM PROGRAM_SERVICES WHERE program_code = :P_PROGRAM_CODE AND program_class = 'PRG' limit 1
}

C_CHK{
SELECT count(*)
           FROM PROGRAM_SERVICES
          WHERE program_category = 'UW'
            AND program_class = 'PRG'
            AND active_flag = 'Y'
            AND program_id !=:P_PROGRAM_ID 
}

DO_UPDATE_ON_CRS_PHASE_COURSE_ACTIVITIES {
update COURSE_ACTIVITIES set NO_OF_SESSIONS = :P_TOTAL, MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where CRS_ACTY_ID = :P_CRS_ACTY_ID
}
DO_UPDATE_ON_PHASE_UPDATE_V_PROGRAM_PHASES {
	 UPDATE V_PROGRAM_PHASES
         SET NO_OF_SESSIONS = :P_TOTAL
       WHERE PROGRAM_PHASE_ID = :P_PROGRAM_PHASE_ID
}

DELETE_COURSE_ACTIVITY_AREAS_DELETE_OPERATION{
DELETE FROM COURSE_ACTIVITY_AREAS  WHERE crs_acty_id = :crsActyId
}


GET_ASSESSMENT_DETAILS{
  SELECT assessment_code, description
           FROM ASSESSMENTS
          WHERE assessment_id = :P_ASSESSMENT_ID
   }    
   
     GET_ALLOCATION_INFO_ONE{
   SELECT code ,
			    provider_party_class, provider_party_id, provider_party_code
			
        FROM COURSE_ACTIVITIES
	   WHERE crs_acty_id =
	         (SELECT parent_crs_acty_id
			    FROM COURSE_ACTIVITIES
			   WHERE crs_acty_id = :P_CRS_ACTY_ID)
}	
SELECT_C_AREA{
SELECT description Description, area_class areaClass FROM areas WHERE area_code = :areaCode
}
GET_COMM_DEFAULT{
  SELECT DISTINCT a.description, a.agy_loc_id FROM TEAM_MEMBERS t, AGENCY_LOCATIONS a, STAFF_USER_ACCOUNTS s
  WHERE t.staff_id = s.staff_id AND s.username =:P_USER_ID AND a.agy_loc_id = t.agy_loc_id AND a.active_flag = 'Y'
   AND a.deactivation_date IS NULL
}

GET_CRS_DETAILS{
 SELECT * FROM COURSE_ACTIVITIES WHERE crs_acty_id = :pCrsActyId
}

GET_CRS_DETAILS_TWO{
SELECT ca.schedule_start_date, ca.schedule_end_date, ps.program_id FROM COURSE_ACTIVITIES ca, PROGRAM_SERVICES ps WHERE ca.crs_acty_id = :pPhaseInstanceId AND ca.program_id = ps.program_id
}


GET_PRG_SRV_DETAILS{
SELECT * FROM program_services WHERE program_id = :lvProgramId
}

GET_NEXT_PRG_SRV_LIST_SEQ {
 SELECT NVL ( MAX ( list_seq ), 0 ) + 1 FROM PROGRAM_SERVICES WHERE parent_program_id = :p_parent_prog_id AND program_class = 'PRG_BLK'
}

CHECK_NEXT_PRG_SRV_SEQ_UNIQUE {
SELECT 1 FROM PROGRAM_SERVICES WHERE parent_program_id = :p_parent_prog_id AND list_seq = :p_list_seq AND program_class = 'PRG_BLK'	
}

GET_PROGRAM_ID_SEQ {
	SELECT nextval('program_id') 
}

GET_CRS_SESSION_COUNT_SUM  {
	SELECT SUM ( NVL ( no_of_sessions, 0 ) ) FROM COURSE_ACTIVITIES WHERE parent_crs_acty_id = :p_crs_acty_id
}

GET_CRS_ACTY_CHECKSUM_CSM_CUR {
 SELECT to_char((coalesce(modify_datetime, create_datetime)), 'YYYYMMDDHH24MISSFF4') ::bigint FROM course_activities WHERE crs_acty_id = :p_crs_acty_id
}


COURSE_SCHEDULE_RULE_ID{
SELECT NEXTVAL('course_schedule_rule_id') FROM DUAL
}

PRE_INSERT_PROGRAM_SERVICE{
SELECT 1 FROM PROGRAM_SERVICES   WHERE program_category = 'UW' AND program_class = 'PRG' AND active_flag = 'Y' limit 1

}

CRS_SCH_ID{
 select NEXTVAL('crs_sch_id')

 }
 
 GET_LAST_SCHED_DATE {
 
 	SELECT MAX ( CS.SCHEDULE_DATE ) 
           FROM COURSE_SCHEDULES CS
          WHERE CS.CRS_ACTY_ID = :P_CRS_ACTY_ID
 
 
 }
 
 GET_WORKING_CASELOAD{
 SELECT C.CASELOAD_TYPE, R.DESCRIPTION FROM STAFF_USER_ACCOUNTS S, CASELOADS C, REFERENCE_CODES R WHERE S.USERNAME = :P_USER_ID AND S.WORKING_CASELOAD_ID = C.CASELOAD_ID AND R.DOMAIN = 'CSLD_CODE' AND R.CODE = C.CASELOAD_TYPE
 }
 
 
 GET_CA_DATES{
   SELECT schedule_start_date, schedule_end_date
           FROM COURSE_ACTIVITIES
          WHERE crs_acty_id = :p_crs_acty_id
 }
 
 DELETE_COURSE_ACTIVITY_PARTIES_DELETE_OPERATION{
 DELETE FROM COURSE_ACTIVITY_PARTIES WHERE crs_acty_id = :p_crs_acty_id
 }
 
 DELETE_COURSE_ACTIVITY_PROF_DELETE_OPERATION{
 DELETE FROM COURSE_ACTIVITY_PROFILES WHERE crs_acty_id IN ( SELECT crs_acty_id  FROM COURSE_ACTIVITIES WHERE crs_acty_id =  :p_crs_acty_id)
 }
 
 