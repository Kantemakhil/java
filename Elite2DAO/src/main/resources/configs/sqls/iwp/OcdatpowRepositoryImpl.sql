
OCDATPOW_FIND_RGPOSITION {
 	 SELECT CODE , DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'STAFF_POS' ORDER BY LIST_SEQ , CODE , DESCRIPTION
}

OCDATPOW_FIND_RGROLE {
 	 SELECT CODE , DESCRIPTION FROM REFERENCE_CODES WHERE DOMAIN = 'STAFF_ROLE' ORDER BY LIST_SEQ , CODE , DESCRIPTION
}

OCDATPOW_FIND_RGSEXCODE {
 	 SELECT CODE , DESCRIPTION  FROM   REFERENCE_CODES WHERE   DOMAIN = 'SEX' 
}

OCDATPOW_FIND_RGSCHEDULETYPE {
 	SELECT CODE        ,DESCRIPTION  FROM   REFERENCE_CODES SCHEDULE_TYPE WHERE   DOMAIN = 'SCHEDULE_TYP' ORDER BY LIST_SEQ , CODE , DESCRIPTION
}

OCDATPOW_FIND_RGTEAM {
select
	DESCRIPTION,
	TO_CHAR(team_id) CODE
from
	teams
where
	ACTIVE_FLAG = 'Y'
	and TEAM_ID in (
	select
		TM.TEAM_ID
	from
		TEAM_MEMBERS TM,
		TEAM_FUNCTIONS TF,
		STAFF_LOCATION_ROLES SLR
	where
		TM.STAFF_ID =:STAFFID
		and TM.ACTIVE_FLAG = 'Y'
		and TF.FUNCTION_TYPE = 'OM'
		and TF.TEAM_ID = TM.TEAM_ID
		and TM.STAFF_ID = SLR.SAC_STAFF_ID
		and TM.AGY_LOC_ID = SLR.CAL_AGY_LOC_ID
		and TM.LOC_ROLE_FROM_DATE = SLR.FROM_DATE
		and TM.POSITION = SLR.POSITION
		and TM.ROLE = SLR.ROLE
		and ( TM.POSITION =:position
			or coalesce(:position ::text, '')= '' )
			and ( TM.ROLE =:role
				or coalesce(:role ::text, '')= '' )
    )
}

OCDATPOW_FIND_CGFKSTAFFLRDSPDESCRIPTION {
SELECT
    AGY_LOC.DESCRIPTION            DESCRIPTION ,
    AGY_LOC.AGENCY_LOCATION_TYPE   DSP_AGENCY_LOCATION_TYPE,
    AGY_LOC.AGY_LOC_ID             CODE,
    ACTIVE_FLAG
FROM
    AGENCY_LOCATIONS AGY_LOC
WHERE
    AGY_LOC.AGENCY_LOCATION_TYPE = 'COMM'
    AND EXISTS (
        SELECT
            CAL.AGY_LOC_ID
        FROM
            CASELOAD_AGENCY_LOCATIONS CAL
        WHERE
            CAL.CASELOAD_ID = :CASELOADTYPE
            AND CAL.AGY_LOC_ID = AGY_LOC.AGY_LOC_ID
    )
ORDER BY
    AGY_LOC.DESCRIPTION
}


OCDATPOW_FIND_CGFKSTAFFLRDSPLASTNAME {--DOUBT
 	SELECT STAFF.LAST_NAME  DSP_LAST_NAME    ,STAFF.FIRST_NAME  DSP_FIRST_NAME        ,STAFF.STAFF_ID  SAC_STAFF_ID FROM   STAFF_MEMBERS STAFF WHERE   STAFF.STAFF_ID  IN (SELECT SL.SAC_STAFF_ID FROM STAFF_LOCATION_ROLES SL WHERE  SL.CAL_AGY_LOC_ID = ::CALAGYLOCID  AND SL.TO_DATE IS NULL ) ORDER BY  STAFF.LAST_NAME ASC
}

 
OCDATPOW_OFFBKG1_FIND_OFFENDER_BOOKINGS{
SELECT
    offender_book_id,
    booking_begin_date,
    booking_end_date,
    booking_no,
    offender_id,
    booking_status, 
    root_offender_id,    
    case_officer_id,   
    status_reason,
    total_unexcused_absences,
    record_user_id,
    intake_agy_loc_assign_date
FROM
    offender_bookings
WHERE
    offender_bookings.booking_status = 'O'
    AND community_active_flag = 'Y'
   AND EXISTS 
    (
        SELECT
            '1'
        FROM
            offender_booking_agy_locs obal
        WHERE
           obal.offender_book_id = offender_bookings.offender_book_id
           AND obal.removed_date IS NULL
           AND obal.agy_loc_id = :intakeCaseLoadId
    )
    AND offender_book_id IN (
        SELECT
            offender_book_id
        FROM
            case_plans cp1
        WHERE
            cp1.sac_staff_id =:commonstaffId
            AND cp1.position =:position
            AND cp1.role =:commstaffrole
            AND cp1.end_date IS NULL
            AND cp1.case_plan_status = 'ACTIVE'
    )
 }
 
OCDATPOW_FIND_STARTDATE{
	SELECT  start_date FROM case_plans WHERE offender_book_id =:OFFENDERBOOKID AND case_plan_status = 'ACTIVE'
}

OCDATPOW_VOFFDET_FIND_V_OM_TEAM_MEMBERS {
    SELECT staff_id,staff_name,position,role,sex_code,schedule_type,cal_agy_loc_id,from_date, ( SELECT count(*) FROM teams
    WHERE ACTIVE_FLAG = 'Y' AND TEAM_ID IN ( SELECT TM.TEAM_ID FROM TEAM_MEMBERS TM, TEAM_FUNCTIONS TF, STAFF_LOCATION_ROLES SLR WHERE 
    TM.STAFF_ID = v_om_team_members.staff_id AND TM.ACTIVE_FLAG = 'Y' AND TF.FUNCTION_TYPE = 'OM' AND TF.TEAM_ID = TM.TEAM_ID AND TM.STAFF_ID =
    SLR.SAC_STAFF_ID AND TM.AGY_LOC_ID = SLR.CAL_AGY_LOC_ID AND TM.LOC_ROLE_FROM_DATE = SLR.FROM_DATE AND TM.POSITION = SLR.POSITION AND TM.ROLE = SLR.ROLE AND 
    ( TM.POSITION = v_om_team_members.position OR v_om_team_members.position IS NULL ) AND ( TM.ROLE = v_om_team_members.role OR v_om_team_members.role IS NULL )))
    as count FROM v_om_team_members v_om_team_members
}

OCDATPOW_CGFKCHK_STAFF_LR_STAFF_LR_AGY{
	SELECT AGY_LOC.DESCRIPTION ,AGY_LOC.AGENCY_LOCATION_TYPE FROM   AGENCY_LOCATIONS AGY_LOC WHERE  AGY_LOC.AGY_LOC_ID = :CALAGYLOCID AND     AGY_LOC.AGENCY_LOCATION_TYPE = 'COMM'
}

OCDATPOW_CGFKLKP_STAFF_LR_STAFF_LR_AGY{--doubt
	SELECT AGY_LOC.AGY_LOC_ID ,AGY_LOC.AGENCY_LOCATION_TYPE FROM AGENCY_LOCATIONS AGY_LOC WHERE (AGY_LOC.DESCRIPTION = :DSPDESCRIPTION OR (AGY_LOC.DESCRIPTION IS NULL AND :DSPDESCRIPTION IS NULL )) AND  AGY_LOC.AGENCY_LOCATION_TYPE = 'COMM' AND  EXISTS (SELECT 'X' --ADDED EXISTS CONDITION BY RAJESH FOR HPQC#6341 FROM CASELOAD_AGENCY_LOCATIONS CAL WHERE CAL.CASELOAD_ID = :CASELOADID AND CAL.AGY_LOC_ID  = AGY_LOC.AGY_LOC_ID )
}

OCDATPOW_CGFKCHK_STAFF_LR_STAFF_LR_STA{
	SELECT STAFF.LAST_NAME ,STAFF.FIRST_NAME FROM   STAFF_MEMBERS STAFF WHERE  STAFF.STAFF_ID = :SACSTAFFID AND     STAFF.STAFF_ID  IN (SELECT SL.SAC_STAFF_ID FROM STAFF_LOCATION_ROLES SL WHERE  SL.CAL_AGY_LOC_ID = :CALAGYLOCID AND SL.TO_DATE IS NULL)
}
OCDATPOW_CGFKLKP_STAFF_LR_STAFF_LR_STA{
	SELECT STAFF.STAFF_ID FROM STAFF_MEMBERS STAFF WHERE (STAFF.LAST_NAME = :DSPLASTNAME OR (STAFF.LAST_NAME IS NULL AND :DSPLASTNAME IS NULL)) AND (STAFF.FIRST_NAME = :DSPFIRSTNAME OR (STAFF.FIRST_NAME IS NULL AND :DSPFIRSTNAME IS NULL)) AND STAFF.STAFF_ID IN (SELECT SL.SAC_STAFF_ID FROM STAFF_LOCATION_ROLES SL WHERE SL.CAL_AGY_LOC_ID = :CALAGYLOCID AND SL.TO_DATE IS NULL)
}

OCDATPOW_CGFKCHK_OFF_BKG_OFF_BKG_OFF_N_ { 
SELECT OFF_NAME.OFFENDER_ID_DISPLAY ,OFF_NAME.OFFENDER_ID ,OFF_NAME.LAST_NAME ,OFF_NAME.FIRST_NAME  
FROM OFFENDERS OFF_NAME,
OFFENDER_BOOKINGS OB
WHERE
OB.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
AND OFF_NAME.OFFENDER_ID = OB.OFFENDER_ID
ORDER by OFF_NAME.LAST_NAME, OFF_NAME.FIRST_NAME
    
  }
  
OCDATPOW_GET_OFFICER_ID{
  SELECT case_officer_id FROM offender_bookings WHERE offender_book_id = :offenderBookId;
}

  
 AGENCY_LOCATIONS{
  	SELECT AGY_LOC.AGY_LOC_ID
            ,AGY_LOC.AGENCY_LOCATION_TYPE
        FROM AGENCY_LOCATIONS AGY_LOC
       WHERE (AGY_LOC.DESCRIPTION = :DSP_DESCRIPTION OR 
             (AGY_LOC.DESCRIPTION IS NULL AND    
              :RDSP_DESCRIPTION IS NULL ))
         AND  AGY_LOC.AGENCY_LOCATION_TYPE = 'COMM'
         AND  EXISTS (SELECT 'X'
                        FROM caseload_agency_locations cal 
                       WHERE cal.caseload_id = :CASELOAD_ID 
                         AND cal.agy_loc_id  = AGY_LOC.agy_loc_id)
 }
  
CASE_PLAN_CUR{
  SELECT  COUNT(0)
   	   FROM  case_plans
   	  WHERE  offender_book_id = :offenderBookId
}
  
NEXT_ID_CUR{
SELECT coalesce ( MAX( case_plan_id ), 0)
       FROM case_plans
      WHERE offender_book_id = :offenderBookId
}
 
 VS_SAC_CUR{
     SELECT from_date
       FROM staff_location_roles
      WHERE sac_staff_id    = :STAFF_ID
        AND cal_agy_loc_id  = :AGY_LOC_ID
        AND position        = :POSITION
        AND role            = :ROLE 
        AND to_date IS NULL
 }
 
 REVIEW_DATE_CUR{
      SELECT crp.review_period
       FROM case_review_periods crp
      WHERE crp.supervision_level = :V_SUPERVISION_LEVEL
 }
 
 V_ID{
    SELECT nextval('ASSTRA_SEQ') FROM dual

 }
 
 CREATE_USER_CUR{
   SELECT creation_user
    FROM case_plans
   WHERE offender_book_id = :P_OFFENDER_BOOK_ID
     AND case_plan_id = :V_ID
 }
 
 GET_INST_INFO{
       SELECT cp.inst_from_date
            ,cp.inst_position
            ,cp.inst_role
            ,cp.inst_sac_staff_id            
            ,cp.inst_cal_agy_loc_id   
            ,cp.auto_assess_modify_datetime
            ,cp.auto_condition_modify_datetime                 
      FROM   case_plans cp
      WHERE  cp.offender_book_id = :P_OFFENDER_BOOK_ID
      AND    cp.case_plan_id = :V_ID
 
 }
 
 
CGNBT_SKILL_SUB_TYPE2{
SELECT 'Y'
             FROM assignment_transfers asst, 
                  offender_work_assignments owass  
            WHERE asst.offass_id = owass.offass_id
              AND (owass.offender_book_id IN
                      (SELECT b.offender_book_id
                         FROM offender_bookings a, offender_bookings b
                        WHERE a.root_offender_id = b.root_offender_id
                          AND a.offender_book_id = :OFFENDER_BOOK_ID)
                  OR owass.offender_book_id_request IN
                      (SELECT b.offender_book_id
                         FROM offender_bookings a, offender_bookings 
                        WHERE a.root_offender_id = b.root_offender_id
                          AND a.offender_book_id = :OFFENDER_BOOK_ID))
              AND asst.sac_staff_id = :STAFF_ID
           UNION
           SELECT 'Y'
             FROM case_plans
            WHERE offender_book_id IN
                      (SELECT b.offender_book_id
                         FROM offender_bookings a, offender_bookings b
                        WHERE a.root_offender_id = b.root_offender_id
                          AND a.offender_book_id = :OFFENDER_BOOK_ID)
              AND sac_staff_id = :STAFF_ID
}

VS_GET_PREV_ASSIGN_CUR{
SELECT  OWA.FROM_DATE
                 ,OWA.POSITION
                 ,OWA.ROLE
                 ,OWA.CAL_AGY_LOC_ID
                 ,OWA.STATUS
                 ,OWA.OFFASS_ID
            FROM  OFFENDER_WORK_ASSIGNMENTS OWA
           WHERE (OWA.OFFENDER_BOOK_ID =     :offender_book_id
	      OR  OWA.OFFENDER_BOOK_ID_REQUEST = :offender_book_id)
             AND  OWA.SAC_STAFF_ID        =  :sac_staff_id
           ORDER  BY OWA.CREATION_DATE DESC
}

VS_SACCAL_CUR{
     SELECT from_date
       FROM staff_location_roles
      WHERE sac_staff_id    = :sac_staff_id
        AND cal_agy_loc_id  = :agy_loc_id
        AND position        = :position
        AND role            = :role
        AND to_date IS NULL
}

ASSTRA_SEQ{
      SELECT nextval('ASSTRA_SEQ') FROM dual
}

CASE_PLANS_INSERT{
 INSERT INTO 
  CASE_PLANS (OFFENDER_BOOK_ID , CASE_PLAN_ID, FROM_DATE, POSITION, ROLE, SAC_STAFF_ID, CAL_AGY_LOC_ID, AGY_LOC_ID, CASE_PLAN_STATUS , CREATION_DATE , CREATION_USER , END_DATE , SUPERVISION_LEVEL , CHANGES , NEXT_REVIEW_DATE , START_DATE , INST_FROM_DATE , INST_POSITION , INST_ROLE , INST_SAC_STAFF_ID , INST_CAL_AGY_LOC_ID , AUTO_ASSESS_MODIFY_DATETIME , AUTO_CONDITION_MODIFY_DATETIME ,VERIFIED_FLAG, SEAL_FLAG, CASELOAD_TYPE,CREATE_USER_ID,CREATE_DATETIME,MODIFY_DATETIME) 
      VALUES ( :offenderBookId, :casePlanId, :fromDate, :position, :role,:sacStaffId1, :calAgyLocId, :agyLocId, :casePlanStatus, CURRENT_TIMESTAMP,:createUserId, :endDate, :supervisionLevel, CASE :casePlanId WHEN 0 THEN NULL ELSE 'Re-Assigned' END, :nextReviewDate, CURRENT_TIMESTAMP, :instFromDate, :instPosition, :instRole, :instSacStaffId, :instCalAgyLocId, :autoAssessModifyDatetime, :autoConditionModifyDatetime, :verifiedFlag, :sealFlag,:caseloadType,:createUserId,CURRENT_TIMESTAMP,null)  
}


      
  CASE_PLANES_UPDATE {
       UPDATE case_plans
       SET case_plan_status =:casePlanStatus
         , end_date = CURRENT_TIMESTAMP,
         MODIFY_DATETIME=CURRENT_TIMESTAMP,
         MODIFY_USER_ID=:modifyUserId
     WHERE offender_book_id =:offenderBookId
       AND case_plan_id =:casePlanId
   }
   
  ASSIGNMENT_TRANSFERS_INSERT{
                 INSERT INTO ASSIGNMENT_TRANSFERS
                  (ASSTRA_ID, FROM_DATE_FROM, POSITION_FROM, ROLE_FROM, SAC_STAFF_ID_FROM, 
                   CAL_AGY_LOC_ID_FROM, STATUS_FROM, AGY_LOC_ID, FROM_DATE, POSITION, ROLE,
                   SAC_STAFF_ID, CAL_AGY_LOC_ID, STATUS_TO, AGY_LOC_ID_TO, TRANSFER_DATE
                  ,TRANSFER_DATE_TO, CREATION_DATE, CREATION_USER, OFFASS_ID,CREATE_DATETIME,MODIFY_DATETIME,CREATE_USER_ID)
               VALUES
                  (:asstraId,:fromDateFrom,:positionFrom,:roleFrom,:sacStaffIdFrom, 
                   :calAgyLocIdFrom,  :statusFrom,:calAgyLocId,:fromDate,:position, 
                   :role,:sacStaffId,:calAgyLocId,:statusTo,:calAgyLocId,CURRENT_TIMESTAMP
                  ,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,:createUserId,:offassId,CURRENT_TIMESTAMP,null,:createUserId)
  }
  
 ASSIGNMENT_TRANSFERS_UPDATE{ 
                 UPDATE OFFENDER_WORK_ASSIGNMENTS OFFASS
                  SET OFFASS.FROM_DATE       = :fromDate
                     ,OFFASS.POSITION        = :position
                     ,OFFASS.ROLE            = :role
                     ,OFFASS.SAC_STAFF_ID    = :sacStaffId
                     ,OFFASS.CAL_AGY_LOC_ID  = :calAgyLocId
                     ,OFFASS.STATUS          = :status,
                     MODIFY_DATETIME         = CURRENT_TIMESTAMP,
                     MODIFY_USER_ID          = :modifyUserId
                WHERE OFFASS.OFFASS_ID       = :offassId
 }
  
  
  OFFENDER_WORK_ASSIGNMENTS{
  UPDATE offender_work_assignments offass
                  SET offass.from_date       = v_from_date
                     ,offass.position        = v_position
                     ,offass.role            = v_role
                     ,offass.sac_staff_id    = v_sac_staff_id
                     ,offass.cal_agy_loc_id  = v_cal_agy_loc_id
                     ,offass.status          = v_status_to
                WHERE offass.offass_id       = v_offass_id
  }
  
  
  OM_TEAM_MANDATORY{
    SELECT OMS_MISCELLANEOUS_GET_PROFILE_VALUE('CLIENT','TEAM_REQ') FROM DUAL 
  }

ASSESSMENT_SUMMARIES_Insert{
       INSERT INTO ASSESSMENT_SUMMARIES
                 (OFFENDER_BOOK_ID
                 ,CASE_PLAN_ID
                 ,ASSESSMENT_SEQ
                 ,CATEGORY
                 ,SUMMARY)
  }
  
 ASSESSMENT_SUMMARIES_SELECT_DETAILS{
                          SELECT OFFENDER_BOOK_ID
                               ,(:casePlanId + 1)
                               ,ASSESSMENT_SEQ
                               ,CATEGORY
                               ,SUMMARY  
                           FROM ASSESSMENT_SUMMARIES
                          WHERE OFFENDER_BOOK_ID =:offenderBookId
                            AND CASE_PLAN_ID =:casePlanId
 }
 
V_OM_TEAM_MEMBERS{
SELECT
    'Y'
FROM
    assignment_transfers        asst,
    offender_work_assignments   owass
WHERE
    asst.offass_id = owass.offass_id
    AND ( owass.offender_book_id IN (
        SELECT
            b.offender_book_id
        FROM
            offender_bookings   a,
            offender_bookings   b
        WHERE
            a.root_offender_id = b.root_offender_id
            AND a.offender_book_id = :offenderBookId
    )
          OR owass.offender_book_id_request IN (
        SELECT
            b.offender_book_id
        FROM
            offender_bookings   a,
            offender_bookings   b
        WHERE
            a.root_offender_id = b.root_offender_id
            AND a.offender_book_id = :offenderBookId
    ) )
    AND asst.sac_staff_id = :staffid
UNION
SELECT
    'Y'
FROM
    case_plans
WHERE
    offender_book_id IN (
        SELECT
            b.offender_book_id
        FROM
            offender_bookings   a,
            offender_bookings   b
        WHERE
            a.root_offender_id = b.root_offender_id
            AND a.offender_book_id = :offenderBookId
    )
    AND sac_staff_id = :staffid
  }
  
  
  FILE_INFO_CUR{
       SELECT  OFFENDER_FILE_SEQ,
             HOLDING_STAFF_ID,
             NON_OFFICER_STATUS
       FROM  OFFENDER_COMMUNITY_FILES
      WHERE  OFFENDER_ID =:OFFENDER_ID
  }
  
  VS_GET_OFF_ID_CUR{
       SELECT OFF_NAME.OFFENDER_ID
       FROM OFFENDER_BOOKINGS OFF_BKG
           ,OFFENDERS OFF_NAME
      WHERE OFF_BKG.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID
        AND OFF_BKG.ROOT_OFFENDER_ID = :ROOT_OFFENDER_ID
        AND OFF_NAME.ALIAS_OFFENDER_ID IS NULL
    }
    
  LV_PROFILE_VALUE{
           SELECT OMS_MISCELLANEOUS_GET_PROFILE_VALUE('FILE_TRANS','FILE') FROM DUAL
  }
  
  UPDATE_OFFENDER_COMMUNITY_FILES{
              UPDATE OFFENDER_COMMUNITY_FILES
               SET NON_OFFICER_STATUS = NULL,
                   HOLDING_STAFF_ID = staffId,
                   STORAGE = NULL,
                   HOLDING_AGY_LOC_ID =:holdingAgyLocId,
                   MODIFY_USER_ID=:modifyUserId,
                   MODIFY_DATETIME=CURRENT_TIMESTAMP
             WHERE OFFENDER_ID =:offenderId
               AND OFFENDER_FILE_SEQ =:offenderFileSeq
       }
   OCDATPOW_FIND_RGTEAM_TOTAL{
    SELECT DISTINCT DESCRIPTION, TO_CHAR(team_id) CODE FROM automation_teams
   }
       