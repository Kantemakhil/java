
	OMSS40_FIND_MENURG {
 SELECT -1 ,1 ,1 ,NULL ,1 FROM DUAL
}
	OMSS40_FIND_RGWORKFLOW {
 SELECT -1 ,1 ,1 ,NULL ,1 FROM DUAL
}
	OMSS40_FIND_CGFKSTAFFDSPDESCRIPTION2 {
 SELECT CSLD.CASELOAD_ID WORKING_CASELOAD_ID ,        CSLD.DESCRIPTION DESCRIPTION ,        CSLD.CASELOAD_TYPE CASELOAD_TYPE   FROM CASELOADS CSLD  WHERE CSLD.ACTIVE_FLAG = 'Y' AND CSLD.CASELOAD_ID IN ( SELECT STAFF_AC.CASELOAD_ID FROM STAFF_ACCESSIBLE_CASELOADS STAFF_AC WHERE STAFF_AC.STAFF_ID = ( SELECT STAFF.STAFF_ID FROM STAFF_MEMBERS STAFF WHERE USER = STAFF.USER_ID  )  ) ORDER BY CSLD.LIST_SEQ ASC
}

	OMSS40_OMSS40_PREFORM_CLIENT {
 SELECT PROFILE_VALUE LV_ROLE_BASE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

	OMSS40_OMSS40_PREFORM_SYS {
 SELECT PROFILE_VALUE  ROLE_PSWD FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'
}

	OMSS40_OMSS40_WHENNEWFORMINSTANCE_MENU_NAME_CUR {
 SELECT MENU_ITEM FROM MENU_SECURITIES WHERE MENU_ID = 1 AND ROWNUM = 1
}

	OMSS40_BUILD_WORKFLOW_MENU_RG_MAIN_WORKFLOW_CUR {
 SELECT DISTINCT 1 STATE, 1 DEPTH, RC.DESCRIPTION LABELS, 'OPEN' ICON, WF.WORKFLOW_CODE DATA , WF.WORKFLOW_SEQ FROM WORKFLOW_FOLDERS WF, REFERENCE_CODES RC WHERE RC.DOMAIN = 'WORKFLO_MENU' AND RC.CODE = WF.WORKFLOW_CODE AND RC.ACTIVE_FLAG = 'Y' AND WF.CASELOAD_TYPE = :CASELOAD_TYPE AND WF.WORKFLOW_CODE IN ( SELECT WS.WORKFLOW_CODE FROM WORKFLOW_SCREENS WS WHERE WS.MODULE_NAME = :MODULE_NAME ) ORDER BY WORKFLOW_SEQ
}

	OMSS40_BUILD_WORKFLOW_MENU_RG_SUB_WORKFLOW_CUR {
 SELECT DISTINCT 1 STATE, 2 DEPTH, WS.DESCRIPTION LABELS, 'APP' ICON, WS.MODULE_NAME DATA , WS.WORKFLOW_SEQ FROM WORKFLOW_SCREENS WS WHERE WORKFLOW_CODE = :WORKFLOW_CODE AND WS.MODULE_NAME IN ( SELECT DISTINCT MP.MODULE_NAME FROM MODULE_PRIVILEGES MP, STAFF_MEMBER_ROLES SMR, STAFF_MEMBERS SM WHERE SM.USER_ID = USER AND SM.STAFF_ID = SMR.STAFF_ID AND SMR.ROLE_ID = MP.ROLE_ID ) ORDER BY WORKFLOW_SEQ
}

	OMSS40_GET_CURRENT_CASELOAD {
 SELECT WORKING_CASELOAD_ID LV_LABEL FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID
}

OMSS40_GET_CURRENT_CASELOADTYPE {
    SELECT CASELOAD_TYPE LV_LABEL FROM CASELOADS WHERE CASELOAD_ID = (SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :userId);
}

	OMSS40_GET_CURRENT_STAFFDETAIL {
 SELECT STAFF.LAST_NAME , STAFF.FIRST_NAME , STAFF.STAFF_ID , STAFF.USER_ID FROM   STAFF_MEMBERS  STAFF WHERE  USER_ID = :userId	
}
     OMSS40_GET_CASELOADS{          
              SELECT  CASELOAD_ID, DESCRIPTION,CASELOAD_TYPE from CASELOADS
          }
OMSS40_MAIN_POP_LIST {
 SELECT menu_item, MENU_ID  FROM menu_securities ms
                                 WHERE ( ms.module_name IN ( SELECT DISTINCT mp.module_name
                                                               FROM module_privileges mp,
                                                                    staff_member_roles smr,
                                                                    staff_members sm
                                                              WHERE sm.user_id = USER
                                                                AND sm.staff_id = smr.staff_id
                                                                AND smr.role_id = mp.role_id )
                                    OR (    ms.module_name IS NULL              
                                            AND EXISTS (SELECT 'X'
                                                          FROM menu_securities ms1
                                                         WHERE EXISTS (
                                                                   SELECT 'X' 
                                                                     FROM module_privileges mp,
                                                                          staff_member_roles smr,
                                                                          staff_members sm
                                                                    WHERE sm.user_id = USER
                                                                      AND sm.staff_id = smr.staff_id
                                                                      AND smr.role_id = mp.role_id 
                                                                      AND mp.module_name = ms1.module_name
                                                                         ) 
                                                     CONNECT BY PRIOR menu_id = parent_menu_id
                                                     START WITH parent_menu_id = ms.menu_id 
                                                          )                                    
                                          )                                                                
                                        ) 
                                   AND PARENT_MENU_ID IN (0,1)
                            CONNECT BY PRIOR menu_id = parent_menu_id
                                   AND SORT_ORDER >= 0
                            START WITH parent_menu_id = 1
                            ORDER BY parent_menu_id, sort_order
                            }
                            


OMSS40_USER_ROLEINFO { 
	SELECT 
		MP.MODULE_NAME, 
		MP.ACCESS_PRIVILEGE, 
		MP.ROLE_ID, 
		MP.VERIFICATION_FLAG        
 	FROM MODULE_PRIVILEGES MP,	
          STAFF_MEMBER_ROLES SMR,	
          STAFF_MEMBERS SM	
	WHERE SM.USER_ID = :USER_ID	
      AND SM.STAFF_ID = SMR.STAFF_ID	
      AND SMR.ROLE_ID = MP.ROLE_ID 
	ORDER BY MP.ACCESS_PRIVILEGE DESC
} 

OMSS40_GET_SYSTEM_PROFILES {
    SELECT PROFILE_CODE , DESCRIPTION, PROFILE_VALUE, PROFILE_VALUE_2, OLD_TABLE_NAME, SEAL_FLAG 
    FROM SYSTEM_PROFILES 
    WHERE PROFILE_TYPE in (:profiletype)
} 

OMSS40_GET_USER_RECENT_OFFENDERS {
select * from (SELECT
		VOFF.LAST_NAME, VOFF.FIRST_NAME,VOFF.OFFENDER_ID_DISPLAY,
		VOFF.MIDDLE_NAME, COFF.OFFENDER_ID,
		COFF.OFFENDER_BOOK_ID, COFF.USER_ID,
		COFF.CASELOAD_ID, IMAGE_ID
	FROM V_OFFENDERS VOFF
	INNER JOIN OMS_OWNER.CURRENT_OFFENDERS COFF on VOFF.OFFENDER_BOOK_ID = COFF.OFFENDER_BOOK_ID
	LEFT OUTER JOIN IMAGES IMG on COFF.OFFENDER_BOOK_ID = IMG.IMAGE_OBJECT_ID AND IMG.ACTIVE_FLAG = 'Y'
								AND IMG.IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMG.IMAGE_VIEW_TYPE = 'FACE'
		WHERE COFF.USER_ID = :USER_ID
AND COFF.CASELOAD_ID = :CASELOAD_ID
	ORDER BY COFF.ACTIVE_DATETIME desc)A limit :ROWNUM;
	
}

OMSS40_GET_USER_RECENT_OFFENDER{
    select * from OMS_OWNER.CURRENT_OFFENDERS WHERE OFFENDER_ID = :OFFENDER_ID  AND CASELOAD_ID = :CASELOAD_ID AND USER_ID = :USER_ID
    
    }
    
OMSS40_INSERT_USER_RECENT_OFFENDERS{
		INSERT INTO OMS_OWNER.CURRENT_OFFENDERS (OFFENDER_ID, OFFENDER_BOOK_ID,  USER_ID, CREATE_USER_ID, CASELOAD_ID, ACTIVE_DATETIME, CREATE_DATETIME)
		VALUES (:offenderId, :offenderBookId, :userId, :createuserId, :caseLoadId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP )
}

OMSS40_UPDATE_USER_RECENT_OFFENDERS {
		UPDATE OMS_OWNER.CURRENT_OFFENDERS
  		SET ACTIVE_DATETIME = CURRENT_TIMESTAMP
 		WHERE OFFENDER_ID = :OFFENDER_ID  AND CASELOAD_ID = :CASELOAD_ID AND USER_ID= :USER_ID
 }		

	OMSS40_FIND_MENURG {
 SELECT -1 ,1 ,1 ,NULL ,1 FROM DUAL
}
	OMSS40_FIND_RGWORKFLOW {
 SELECT -1 ,1 ,1 ,NULL ,1 FROM DUAL
}
	OMSS40_FIND_CGFKSTAFFDSPDESCRIPTION2 {
 SELECT CSLD.CASELOAD_ID WORKING_CASELOAD_ID ,        CSLD.DESCRIPTION DESCRIPTION ,        CSLD.CASELOAD_TYPE CASELOAD_TYPE   FROM CASELOADS CSLD  WHERE CSLD.ACTIVE_FLAG = 'Y' AND CSLD.CASELOAD_ID IN ( SELECT STAFF_AC.CASELOAD_ID FROM STAFF_ACCESSIBLE_CASELOADS STAFF_AC WHERE STAFF_AC.STAFF_ID = ( SELECT STAFF.STAFF_ID FROM STAFF_MEMBERS STAFF WHERE OMS_OWNER = STAFF.USER_ID  )  ) ORDER BY CSLD.LIST_SEQ ASC
}

	OMSS40_OMSS40_PREFORM_CLIENT {
 SELECT PROFILE_VALUE LV_ROLE_BASE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

	OMSS40_OMSS40_PREFORM_SYS {
 SELECT PROFILE_VALUE  ROLE_PSWD FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'
}

	OMSS40_OMSS40_WHENNEWFORMINSTANCE_MENU_NAME_CUR {
 SELECT MENU_ITEM FROM MENU_SECURITIES WHERE MENU_ID = 1 AND ROWNUM = 1
}

	OMSS40_BUILD_WORKFLOW_MENU_RG_MAIN_WORKFLOW_CUR {
 SELECT DISTINCT 1 STATE, 1 DEPTH, RC.DESCRIPTION LABELS, 'OPEN' ICON, WF.WORKFLOW_CODE DATA , WF.WORKFLOW_SEQ FROM WORKFLOW_FOLDERS WF, REFERENCE_CODES RC WHERE RC.DOMAIN = 'WORKFLO_MENU' AND RC.CODE = WF.WORKFLOW_CODE AND RC.ACTIVE_FLAG = 'Y' AND WF.CASELOAD_TYPE = :CASELOAD_TYPE AND WF.WORKFLOW_CODE IN ( SELECT WS.WORKFLOW_CODE FROM WORKFLOW_SCREENS WS WHERE WS.MODULE_NAME = :MODULE_NAME ) ORDER BY WORKFLOW_SEQ
}

	OMSS40_BUILD_WORKFLOW_MENU_RG_SUB_WORKFLOW_CUR {
 SELECT DISTINCT 1 STATE, 2 DEPTH, WS.DESCRIPTION LABELS, 'APP' ICON, WS.MODULE_NAME DATA , WS.WORKFLOW_SEQ FROM WORKFLOW_SCREENS WS WHERE WORKFLOW_CODE = :WORKFLOW_CODE AND WS.MODULE_NAME IN ( SELECT DISTINCT MP.MODULE_NAME FROM MODULE_PRIVILEGES MP, STAFF_MEMBER_ROLES SMR, STAFF_MEMBERS SM WHERE SM.USER_ID = USER AND SM.STAFF_ID = SMR.STAFF_ID AND SMR.ROLE_ID = MP.ROLE_ID ) ORDER BY WORKFLOW_SEQ
}

	OMSS40_GET_CURRENT_CASELOAD {
 SELECT WORKING_CASELOAD_ID LV_LABEL FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID
}
     OMSS40_GET_CASELOADS{          
              SELECT  CASELOAD_ID, DESCRIPTION,CASELOAD_TYPE from CASELOADS
          }
OMSS40_MAIN_POP_LIST {
 SELECT menu_item, MENU_ID  FROM menu_securities ms
                                 WHERE ( ms.module_name IN ( SELECT DISTINCT mp.module_name
                                                               FROM module_privileges mp,
                                                                    staff_member_roles smr,
                                                                    staff_members sm
                                                              WHERE sm.user_id = USER
                                                                AND sm.staff_id = smr.staff_id
                                                                AND smr.role_id = mp.role_id )
                                    OR (    ms.module_name IS NULL              
                                            AND EXISTS (SELECT 'X'
                                                          FROM menu_securities ms1
                                                         WHERE EXISTS (
                                                                   SELECT 'X' 
                                                                     FROM module_privileges mp,
                                                                          staff_member_roles smr,
                                                                          staff_members sm
                                                                    WHERE sm.user_id = USER
                                                                      AND sm.staff_id = smr.staff_id
                                                                      AND smr.role_id = mp.role_id 
                                                                      AND mp.module_name = ms1.module_name
                                                                         ) 
                                                     CONNECT BY PRIOR menu_id = parent_menu_id
                                                     START WITH parent_menu_id = ms.menu_id 
                                                          )                                    
                                          )                                                                
                                        ) 
                                   AND PARENT_MENU_ID IN (0,1)
                            CONNECT BY PRIOR menu_id = parent_menu_id
                                   AND SORT_ORDER >= 0
                            START WITH parent_menu_id = 1
                            ORDER BY parent_menu_id, sort_order
                            }
                            


OMSS40_USER_ROLEINFO { 
	SELECT 
		MP.MODULE_NAME, 
		MP.ACCESS_PRIVILEGE, 
		MP.ROLE_ID, 
		MP.VERIFICATION_FLAG        
 	FROM MODULE_PRIVILEGES MP,	
          STAFF_MEMBER_ROLES SMR,	
          STAFF_MEMBERS SM	
	WHERE SM.USER_ID = :USER_ID	
      AND SM.STAFF_ID = SMR.STAFF_ID	
      AND SMR.ROLE_ID = MP.ROLE_ID 
	ORDER BY MP.ACCESS_PRIVILEGE DESC
}

OMSS40_ASSIGNED_OFFENDERS_LIST 
{
SELECT CP.OFFENDER_BOOK_ID, O.OFFENDER_ID_DISPLAY, OB.OFFENDER_ID, OB.ROOT_OFFENDER_ID, O.LAST_NAME , O.FIRST_NAME, IMAGE_ID FROM CASE_PLANS CP, OFFENDER_BOOKINGS OB, OFFENDERS O, IMAGES IMG WHERE SAC_STAFF_ID = (SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID) AND CASE_PLAN_STATUS = 'ACTIVE' AND END_DATE IS NULL AND OB.OFFENDER_BOOK_ID = CP.OFFENDER_BOOK_ID AND OB.COMMUNITY_ACTIVE_FLAG = 'Y' AND O.OFFENDER_ID = OB.OFFENDER_ID AND OB.OFFENDER_BOOK_ID = IMG.IMAGE_OBJECT_ID AND IMG.ACTIVE_FLAG = 'Y' AND IMG.IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMG.IMAGE_VIEW_TYPE = 'FACE' AND EXISTS (SELECT 1 FROM STAFF_ACCESSIBLE_CASELOADS SA, CASELOAD_AGENCY_LOCATIONS CAL WHERE SA.CASELOAD_ID = CAL.CASELOAD_ID AND CAL.AGY_LOC_ID = OB.AGY_LOC_ID AND SA.STAFF_ID = (SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID) AND SA.CASELOAD_ID = :CASELOAD_ID) UNION SELECT CP.OFFENDER_BOOK_ID, O.OFFENDER_ID_DISPLAY, OB.OFFENDER_ID, OB.ROOT_OFFENDER_ID, O.LAST_NAME , O.FIRST_NAME, IMAGE_ID FROM CASE_PLANS CP, OFFENDER_BOOKINGS OB, OFFENDERS O, IMAGES IMG WHERE INST_SAC_STAFF_ID = (SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID) AND CASE_PLAN_STATUS = 'ACTIVE' AND END_DATE IS NULL AND OB.OFFENDER_BOOK_ID = CP.OFFENDER_BOOK_ID AND OB.ACTIVE_FLAG = 'Y' AND O.OFFENDER_ID = OB.OFFENDER_ID AND OB.OFFENDER_BOOK_ID = IMG.IMAGE_OBJECT_ID AND IMG.ACTIVE_FLAG = 'Y' AND IMG.IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMG.IMAGE_VIEW_TYPE = 'FACE' AND EXISTS (SELECT 1 FROM STAFF_ACCESSIBLE_CASELOADS SA, CASELOAD_AGENCY_LOCATIONS CAL WHERE SA.CASELOAD_ID = CAL.CASELOAD_ID AND CAL.AGY_LOC_ID = OB.AGY_LOC_ID AND SA.STAFF_ID = (SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID) AND SA.CASELOAD_ID = :CASELOAD_ID) UNION SELECT OCF.OFFENDER_BOOK_ID, O.OFFENDER_ID_DISPLAY, OB.OFFENDER_ID, OB.ROOT_OFFENDER_ID, O.LAST_NAME , O.FIRST_NAME, IMAGE_ID FROM OFFENDER_CASE_OFFICERS OCF, OFFENDER_BOOKINGS OB, OFFENDERS O, IMAGES IMG WHERE OCF.CASE_OFFICER_ID = (SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID) AND OCF.CASE_ASSIGNED_DATE <= date_TRUNC('day',current_date) AND OCF.CASE_ASSIGNED_TIME = (SELECT MAX(OCF1.CASE_ASSIGNED_TIME) FROM OFFENDER_CASE_OFFICERS OCF1 WHERE OCF1.OFFENDER_BOOK_ID = OCF.OFFENDER_BOOK_ID AND OCF1.CASE_ASSIGNED_DATE <= date_TRUNC('day',current_date)) AND OB.OFFENDER_BOOK_ID = OCF.OFFENDER_BOOK_ID AND OB.ACTIVE_FLAG = 'Y' AND O.OFFENDER_ID = OB.OFFENDER_ID AND OB.OFFENDER_BOOK_ID = IMG.IMAGE_OBJECT_ID AND IMG.ACTIVE_FLAG = 'Y' AND IMG.IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMG.IMAGE_VIEW_TYPE = 'FACE' AND EXISTS (SELECT 1 FROM STAFF_ACCESSIBLE_CASELOADS SA, CASELOAD_AGENCY_LOCATIONS CAL WHERE SA.CASELOAD_ID = CAL.CASELOAD_ID AND CAL.AGY_LOC_ID = OB.AGY_LOC_ID AND SA.STAFF_ID = (SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID) AND SA.CASELOAD_ID = :CASELOAD_ID) UNION SELECT SW.OFFENDER_BOOK_ID, O.OFFENDER_ID_DISPLAY, OB.OFFENDER_ID, OB.ROOT_OFFENDER_ID, O.LAST_NAME , O.FIRST_NAME, IMAGE_ID FROM STAFF_WORKS SW, OFFENDER_BOOKINGS OB, OFFENDERS O, IMAGES IMG WHERE SW.STAFF_WORK_ID IN (SELECT SWA.STAFF_WORK_ID FROM STAFF_WORK_ASSIGNMENTS SWA WHERE SWA.TEAM_MEMBER_ID IN (SELECT TEAM_MEMBER_ID FROM team_staff_members WHERE STAFF_ID = (SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID)) AND SWA.COMPLETED_FLAG = 'N') AND SW.WORKFLOW_TYPE = 'TASK' AND SW.COMPLETED_FLAG = 'N' AND SW.ASSIGNED_FLAG = 'Y' AND OB.OFFENDER_BOOK_ID = SW.OFFENDER_BOOK_ID AND O.OFFENDER_ID = OB.OFFENDER_ID AND OB.OFFENDER_BOOK_ID = IMG.IMAGE_OBJECT_ID AND IMG.ACTIVE_FLAG = 'Y' AND IMG.IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMG.IMAGE_VIEW_TYPE = 'FACE' AND EXISTS (SELECT 1 FROM STAFF_ACCESSIBLE_CASELOADS SA, CASELOAD_AGENCY_LOCATIONS CAL WHERE SA.CASELOAD_ID = CAL.CASELOAD_ID AND CAL.AGY_LOC_ID = OB.AGY_LOC_ID AND SA.STAFF_ID = (SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID) AND SA.CASELOAD_ID = :CASELOAD_ID)
}


	OMSS40_FIND_MENURG {
 SELECT -1 ,1 ,1 ,NULL ,1 FROM DUAL
}
	OMSS40_FIND_RGWORKFLOW {
 SELECT -1 ,1 ,1 ,NULL ,1 FROM DUAL
}
	OMSS40_FIND_CGFKSTAFFDSPDESCRIPTION {
select CSLD.CASELOAD_ID, CSLD.DESCRIPTION DESCRIPTION , CSLD.CASELOAD_TYPE CASELOAD_TYPE, ( select UPDATE_ALLOWED_FLAG from STAFF_ACCESSIBLE_CASELOADS sac where CSLD.CASELOAD_ID = sac.CASELOAD_ID and sac.STAFF_ID = ( select STAFF.STAFF_ID from STAFF_MEMBERS STAFF where STAFF.USER_ID = :userId ) ) as update_allowed_flag from CASELOADS CSLD where CSLD.ACTIVE_FLAG = 'Y' and CSLD.CASELOAD_ID in ( select STAFF_AC.CASELOAD_ID from STAFF_ACCESSIBLE_CASELOADS STAFF_AC where STAFF_AC.STAFF_ID = ( select STAFF.STAFF_ID from STAFF_MEMBERS STAFF where :userId = STAFF.USER_ID ) ) order by CSLD.LIST_SEQ asc
}

	OMSS40_OMSS40_PREFORM_CLIENT {
 SELECT PROFILE_VALUE LV_ROLE_BASE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

	OMSS40_OMSS40_PREFORM_SYS {
 SELECT PROFILE_VALUE  ROLE_PSWD FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'
}

	OMSS40_OMSS40_WHENNEWFORMINSTANCE_MENU_NAME_CUR {
 SELECT MENU_ITEM FROM MENU_SECURITIES WHERE MENU_ID = 1 AND ROWNUM = 1
}

	OMSS40_BUILD_WORKFLOW_MENU_RG_MAIN_WORKFLOW_CUR {
 SELECT DISTINCT 1 STATE, 1 DEPTH, RC.DESCRIPTION LABELS, 'OPEN' ICON, WF.WORKFLOW_CODE DATA , WF.WORKFLOW_SEQ FROM WORKFLOW_FOLDERS WF, REFERENCE_CODES RC WHERE RC.DOMAIN = 'WORKFLO_MENU' AND RC.CODE = WF.WORKFLOW_CODE AND RC.ACTIVE_FLAG = 'Y' AND WF.CASELOAD_TYPE = :CASELOAD_TYPE AND WF.WORKFLOW_CODE IN ( SELECT WS.WORKFLOW_CODE FROM WORKFLOW_SCREENS WS WHERE WS.MODULE_NAME = :MODULE_NAME ) ORDER BY WORKFLOW_SEQ
}

	OMSS40_BUILD_WORKFLOW_MENU_RG_SUB_WORKFLOW_CUR {
 SELECT DISTINCT 1 STATE, 2 DEPTH, WS.DESCRIPTION LABELS, 'APP' ICON, WS.MODULE_NAME DATA , WS.WORKFLOW_SEQ FROM WORKFLOW_SCREENS WS WHERE WORKFLOW_CODE = :WORKFLOW_CODE AND WS.MODULE_NAME IN ( SELECT DISTINCT MP.MODULE_NAME FROM MODULE_PRIVILEGES MP, STAFF_MEMBER_ROLES SMR, STAFF_MEMBERS SM WHERE SM.USER_ID = USER AND SM.STAFF_ID = SMR.STAFF_ID AND SMR.ROLE_ID = MP.ROLE_ID ) ORDER BY WORKFLOW_SEQ
}

	OMSS40_GET_CURRENT_CASELOAD {
 SELECT WORKING_CASELOAD_ID LV_LABEL FROM STAFF_MEMBERS WHERE USER_ID = :USER_ID
}
     OMSS40_GET_CASELOADS{          
              SELECT  CASELOAD_ID, DESCRIPTION,CASELOAD_TYPE from CASELOADS
          }
OMSS40_MAIN_POP_LIST {
 SELECT menu_item, MENU_ID  FROM menu_securities ms
                                 WHERE ( ms.module_name IN ( SELECT DISTINCT mp.module_name
                                                               FROM module_privileges mp,
                                                                    staff_member_roles smr,
                                                                    staff_members sm
                                                              WHERE sm.user_id = USER
                                                                AND sm.staff_id = smr.staff_id
                                                                AND smr.role_id = mp.role_id )
                                    OR (    ms.module_name IS NULL              
                                            AND EXISTS (SELECT 'X'
                                                          FROM menu_securities ms1
                                                         WHERE EXISTS (
                                                                   SELECT 'X' 
                                                                     FROM module_privileges mp,
                                                                          staff_member_roles smr,
                                                                          staff_members sm
                                                                    WHERE sm.user_id = USER
                                                                      AND sm.staff_id = smr.staff_id
                                                                      AND smr.role_id = mp.role_id 
                                                                      AND mp.module_name = ms1.module_name
                                                                         ) 
                                                     CONNECT BY PRIOR menu_id = parent_menu_id
                                                     START WITH parent_menu_id = ms.menu_id 
                                                          )                                    
                                          )                                                                
                                        ) 
                                   AND PARENT_MENU_ID IN (0,1)
                            CONNECT BY PRIOR menu_id = parent_menu_id
                                   AND SORT_ORDER >= 0
                            START WITH parent_menu_id = 1
                            ORDER BY parent_menu_id, sort_order
                            }
                            
OMSS40_MENU_LIST
{
select
	module_name,
	menu_id,
	parent_menu_id,
	sort_order,
	state,
	"level",
	menu_item,
	icon,
	data1,
	"hierarchy",
	(
	select
		dynamic_form
	from
		oms_modules om
	where
		om.module_name = a.module_name) as dynamic_form ,
	(
	select
		ins_dashboard
	from
		oms_modules om2
	where
		om2.module_name = a.module_name)as ins_dashboard
from
	(with recursive subordinates as
(
	select
		module_name,
		MENU_ID,
		PARENT_MENU_ID,
		SORT_ORDER,
		-1 STATE,
		1 as level,
		MENU_ITEM,
		case
			when MODULE_NAME is null then 'DOWN'
			when MODULE_NAME = 'SHOWKEYS' then 'APP'
			when MODULE_NAME = 'EXIT' then 'EXIT'
			else 'APP'
		end ICON,
		case
			MODULE_NAME when 'OIDHMMEN' then MODULE_NAME || '-' || MENU_ID
			else MODULE_NAME
		end DATA1,
		array[ row_number() over (
	order by
		PARENT_MENU_ID,
		SORT_ORDER) ] as hierarchy
	from
		MENU_SECURITIES MS
	where
		( MS.MODULE_NAME in
(
		select
			distinct MP.MODULE_NAME
		from
			MODULE_PRIVILEGES MP,
			STAFF_MEMBER_ROLES SMR,
			STAFF_MEMBERS SM
		where
			SM.USER_ID = :USER_ID
			and
SM.STAFF_ID = SMR.STAFF_ID
			and
SMR.ROLE_ID = MP.ROLE_ID)
		or ( MS.MODULE_NAME is null
			and exists
(
			select
				'X'
			from
				MENU_SECURITIES MS1
			where
				exists
(
				select
					'X'
				from
					MODULE_PRIVILEGES MP,
					STAFF_MEMBER_ROLES SMR,
					STAFF_MEMBERS SM
				where
					SM.USER_ID = :USER_ID
					and
SM.STAFF_ID = SMR.STAFF_ID
					and
SMR.ROLE_ID = MP.ROLE_ID
					and
MP.MODULE_NAME = MS1.MODULE_NAME )) ) )
		and PARENT_MENU_ID = 1
		and SORT_ORDER >= 0
union all
	select
		MS.module_name,
		MS.MENU_ID,
		MS.PARENT_MENU_ID,
		MS.SORT_ORDER,
		-1 STATE,
		S.LEVEL + 1,
		MS.MENU_ITEM,
		case
			when MS.MODULE_NAME is null then 'DOWN'
			when MS.MODULE_NAME = 'SHOWKEYS' then 'APP'
			when MS.MODULE_NAME = 'EXIT' then 'EXIT'
			else 'APP'
		end ICON,
		case
			MS.MODULE_NAME when 'OIDHMMEN' then MS.MODULE_NAME || '-' || MS.MENU_ID
			else MS.MODULE_NAME
		end DATA1,
		array_append(S.hierarchy, row_number() over (order by MS.PARENT_MENU_ID,
        MS.SORT_ORDER,MS.MENU_ITEM)) as hierarchy
	from
		subordinates S
	join MENU_SECURITIES MS on
		S.MENU_ID = MS.PARENT_MENU_ID)
	select
		*
	from
		subordinates
	order by
		hierarchy)a
}

OMSS40_USER_ROLEINFO { 
	SELECT 
		MP.MODULE_NAME, 
		MP.ACCESS_PRIVILEGE, 
		MP.ROLE_ID, 
		MP.VERIFICATION_FLAG        
 	FROM MODULE_PRIVILEGES MP,	
          STAFF_MEMBER_ROLES SMR,	
          STAFF_MEMBERS SM	
	WHERE SM.USER_ID = :USER_ID	
      AND SM.STAFF_ID = SMR.STAFF_ID	
      AND SMR.ROLE_ID = MP.ROLE_ID 
	ORDER BY MP.ACCESS_PRIVILEGE DESC
} 


OMSS40_ASSIGNED_OFFENDERS_LIST 
{
 SELECT   OFFENDER_BOOK_ID, OFFENDER_ID, ROOT_OFFENDER_ID,                OFFENDER_NAME LAST_NAME,
                   OFFENDER_ID_DISPLAY,
  (SELECT IMAGE_ID FROM IMAGES WHERE IMAGE_OBJECT_ID = VMO.OFFENDER_BOOK_ID AND ACTIVE_FLAG = 'Y'
 AND IMAGE_OBJECT_TYPE = 'OFF_BKG' AND IMAGE_VIEW_TYPE = 'FACE' ) AS IMAGE_ID
              FROM V_MY_OFFENDERS VMO
             WHERE (    '' IS NULL
                     OR OFFENDER_NAME LIKE '' )
               AND (    '' IS NULL
                     OR OFFENDER_ID_DISPLAY LIKE '' )
          ORDER BY OFFENDER_NAME ASC

}

OMSS40_GET_SERVER_TIME {
	select to_char(SYSTIMESTAMP()::timestamptz,'YYYY-MM-DD hh24:mi:ss TZH')||':'||substr(to_char(SYSTIMESTAMP()::timestamptz,'YYYY-MM-DD hh24:mi:ss TZM'),instr(to_char(SYSTIMESTAMP()::timestamptz,'YYYY-MM-DD hh24:mi:ss TZM'),' ',-1)+1);
}

OMSS40_GET_CASELOAD_AGENCIES {
	SELECT CASELOAD_ID, AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID in ( SELECT STAFF_AC.CASELOAD_ID FROM STAFF_ACCESSIBLE_CASELOADS STAFF_AC WHERE STAFF_AC.STAFF_ID = 
	( SELECT STAFF.STAFF_ID FROM STAFF_MEMBERS STAFF WHERE STAFF.USER_ID = :USER_ID )  ) 
	order by CASELOAD_ID, AGY_LOC_ID
}

OMSS40_HELP_VIDEO {
	SELECT * FROM OMS_MODULES_HELP where MODULE_NAME = :moduleName ORDER BY HELP_TYPE
}

OMSS40_IWP_MODULE_TEMPLATE_MAPPING {
	select count(TEMPLATE_ID) from iwp_template_modules where module_name = :moduleName
}
OMSS40_IS_EXPIRED {
	SELECT ACCOUNT_STATUS FROM DBA_USERS WHERE USERNAME = :USERNAME
}
OMSS40_GET_USER_ID {
	SELECT USER_ID FROM STAFF_MEMBERS WHERE UPPER(MAIL_ID)= UPPER(:mailId) and ad_user = 'Y'
}
OMSS40_GET_ENCRYPT_PWD {
	SELECT PWD FROM AD_USER WHERE USER_ID = :userId 
}
OMSS40_GET_ENCRY_PASSWORD {
    select password from OMS_USERS_LIST where user_id = :userId
}
OMSS40_GET_STATUS {
	SELECT STATUS FROM STAFF_MEMBERS where user_id = :userId
}
OMSS40_GET_MAIL_ID {
	SELECT MAIL_ID FROM STAFF_MEMBERS where user_id = :userId
}
OMSS40_ASSIGNED_OFFENDERS_LIST_NEW{
select distinct * from V_HEADER_BLOCK VHB where OFFENDER_BOOK_ID in ( select A.OFFENDER_BOOK_ID from ( select MAX(CON_TRANSFER_ID) MAX_CON_TRANS_ID, OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID from OFFENDER_COND_TRANSFER group by OFFENDER_BOOK_ID, OFFENDER_SENT_CONDITION_ID) B, OFFENDER_COND_TRANSFER A where A.OFFENDER_BOOK_ID = B.OFFENDER_BOOK_ID and A.OFFENDER_SENT_CONDITION_ID = B.OFFENDER_SENT_CONDITION_ID and A.CON_TRANSFER_ID = B.MAX_CON_TRANS_ID and (((coalesce(A.STAFF_ID, 0) != 0 and A.STAFF_ID = ( select STAFF_ID from STAFF_MEMBERS SM where UPPER(USER_ID) = UPPER(:USER_ID) )) or (coalesce(A.TO_STAFF_ID, 0) != 0 and A.TO_STAFF_ID = ( select STAFF_ID from STAFF_MEMBERS SM where UPPER(USER_ID) = UPPER(:USER_ID) ))) or (( coalesce(A.TEAM_MEMBER_ID, 0) != 0 and exists ( select 'X' from TEAM_STAFF_MEMBERS TM where TEAM_MEMBER_ID = A.TEAM_MEMBER_ID and STAFF_ID = ( select STAFF_ID from STAFF_MEMBERS SM where UPPER(USER_ID) = UPPER(:USER_ID) )) ) or ( coalesce(A.TO_TEAM_MEMBER_ID, 0) != 0 and exists ( select 'X' from TEAM_STAFF_MEMBERS TM where TEAM_MEMBER_ID = A.TO_TEAM_MEMBER_ID and STAFF_ID = ( select STAFF_ID from STAFF_MEMBERS SM where UPPER(USER_ID) = UPPER(:USER_ID) )) ) ) ))
}
OMSS_SPECIFIC_SYSTEMPROFILE{
SELECT PROFILE_CODE , DESCRIPTION, PROFILE_VALUE, PROFILE_VALUE_2, OLD_TABLE_NAME, SEAL_FLAG FROM SYSTEM_PROFILES WHERE PROFILE_CODE  IN ('DOJ_SUPPORT','LANG_SUPPORT')
}
