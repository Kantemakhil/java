
OCMTEAMS_FIND_RGTEAMAREA {
 	SELECT r.description description, r.area_code code, list_seq FROM areas r WHERE area_class = 'AREA' AND r.active_flag = 'Y' AND r.area_type =:areaType
 	ORDER BY list_seq, description
}

OCMTEAMS_FIND_RGTEAMSTEAMCODE {
 	SELECT TEAM_CODE  code, DESCRIPTION FROM TEAMS ORDER BY LIST_SEQ , TEAM_CODE
}

OCMTEAMS_FIND_RGTEAMSTEAMCATEGORY {
 	SELECT  RS.DESCRIPTION  , RS.CODE    FROM  REFERENCE_CODES RS WHERE  DOMAIN = 'TEAMCATEGORY'       AND   (ACTIVE_FLAG = 'Y' OR ''='ENTER-QUERY' )
 	ORDER BY LIST_SEQ , DESCRIPTION
}
OCMTEAMS_FIND_RGAREATYPE {
 	SELECT ref_code.description, ref_code.code, ref_code.list_seq FROM reference_codes ref_code WHERE domain = 'AREA_TYPE' AND code IN ( 'COMM', 'INST' ) AND ( active_flag = 'Y') ORDER BY list_seq, description
}

OCMTEAMS_FIND_RGFUCTIONTYPE {
 	SELECT code, description, list_seq FROM reference_codes WHERE domain = 'FUNCTION' AND ( active_flag = 'Y') ORDER BY list_seq, description
}

OCMTEAMS_FIND_RGAVAILTEAMTEAMCODE {
 	SELECT TEAM_CODE  , DESCRIPTION  FROM TEAMS   WHERE  ACTIVE_FLAG ='Y'  ORDER BY LIST_SEQ , TEAM_CODe;
}

OCMTEAMS_FIND_RGPOSITION {
 	SELECT REF_CODE.CODE  as POSITION                ,  REF_CODE.LIST_SEQ  DSP_LIST_SEQ6                ,REF_CODE.DESCRIPTION  DESCRIPTION FROM  
 	REFERENCE_CODES REF_CODE WHERE  DOMAIN ='STAFF_POS'  ORDER BY LIST_SEQ , CODE
}

OCMTEAMS_FIND_RGROLE {
 	SELECT REF_CODE1.DESCRIPTION  DSP_DESCRIPTION5 , REF_CODE1.CODE as ROLE   FROM   REFERENCE_CODES REF_CODE1 WHERE 
 	DOMAIN = 'STAFF_ROLE' AND ACTIVE_FLAG = 'Y'   ORDER BY LIST_SEQ , CODE
}

OCMTEAMS_FIND_RGAGYLOCID {
 	SELECT AGY_LOC_ID  ,DESCRIPTION FROM AGENCY_LOCATIONS WHERE AGENCY_LOCATION_TYPE = ::NBTAREATYPE AND ::MODE='ENTER-QUERY' ORDER BY LIST_SEQ , AGY_LOC_ID
}

OCMTEAMS_FIND_RGADMAGYLOC {
 	SELECT agy_loc.description description, agy_loc.agy_loc_id code, CASE WHEN (( active_flag = 'Y') AND AREA_CODE = :areaCode) THEN 'Y' ELSE 'N'  END ACTIVE_FLAG 
 FROM agency_locations agy_loc
}
OCMTEAMS_AVAIL_TEAMS_FIND_TEAMS{
SELECT TEAM_CODE ,DESCRIPTION ,CATEGORY ,LIST_SEQ ,ACTIVE_FLAG ,EXPIRY_DATE ,CREATE_USER_ID ,MODIFY_USER_ID ,MODIFY_DATETIME ,CREATE_DATETIME ,TEAM_ID 
,AREA_CODE ,AGY_LOC_ID ,QUEUE_CLUSTER_ID ,SEAL_FLAG   FROM TEAMS   where  TEAM_ID = :teamId order by list_seq,team_code,description,category
}
OCMTEAMS_TEAMS_FIND_TEAMS {
 	SELECT TEAM_CODE ,DESCRIPTION ,CATEGORY ,LIST_SEQ ,ACTIVE_FLAG ,ACTIVE_FLAG CHECKFLAG,EXPIRY_DATE ,CREATE_USER_ID ,MODIFY_USER_ID ,MODIFY_DATETIME 
 	,CREATE_DATETIME ,TEAM_ID ,AREA_CODE ,AGY_LOC_ID ,QUEUE_CLUSTER_ID ,SEAL_FLAG   FROM TEAMS   where  area_code = :areaCode 
 	order by list_seq,active_flag desc,team_code,description,category
}
OCMTEAMS_TEAMS_INSERT_TEAMS {
	insert into TEAMS(TEAM_CODE , DESCRIPTION , CATEGORY , LIST_SEQ , ACTIVE_FLAG , EXPIRY_DATE , CREATE_USER_ID , MODIFY_DATETIME , CREATE_DATETIME , TEAM_ID , AREA_CODE , AGY_LOC_ID , QUEUE_CLUSTER_ID , SEAL_FLAG )
	values(:teamCode , :description , :category , :listSeq , :activeFlag , :expiryDate , :createUserId , null , CURRENT_TIMESTAMP , :teamId , :areaCode , :agyLocId , :queueClusterId , :sealFlag )
}

OCMTEAMS_TEAMS_UPDATE_TEAMS {
	update TEAMS set TEAM_CODE = :teamCode , DESCRIPTION = :description , CATEGORY = :category , LIST_SEQ = :listSeq , ACTIVE_FLAG = :activeFlag ,
	EXPIRY_DATE = :expiryDate , MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = current_timestamp , TEAM_ID = :teamId , AREA_CODE = :areaCode , 
	AGY_LOC_ID = :agyLocId , QUEUE_CLUSTER_ID = :queueClusterId , SEAL_FLAG = :sealFlag where TEAM_ID = :teamId
}

OCMTEAMS_TEAMS_DELETE_TEAMS { 
	DELETE FROM TEAMS   where TEAM_ID  = :teamId
}

OCMTEAMS_TEAMFUNCTIONS_FIND_TEAM_FUNCTIONS {
 	SELECT TEAM_ID ,FUNCTION_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG, ROW_ID SERVER_CODE  FROM TEAM_FUNCTIONS 
 	where  team_id=:teamId order by function_type
}
OCMTEAMS_TEAMFUNCTIONS_INSERT_TEAM_FUNCTIONS {
	 insert into TEAM_FUNCTIONS(TEAM_ID , FUNCTION_TYPE , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG )
	 values(:teamId , :functionType , current_timestamp , :createUserId ,null, :sealFlag )
}
OCMTEAMS_TEAMFUNCTIONS_UPDATE_TEAM_FUNCTIONS { 
	update TEAM_FUNCTIONS set TEAM_ID = :teamId , FUNCTION_TYPE = :functionType , MODIFY_DATETIME =current_timestamp, MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag where ROW_ID = :serverCode	
}
OCMTEAMS_TEAMFUNCTIONS_DELETE_TEAM_FUNCTIONS { 
	DELETE FROM TEAM_FUNCTIONS where  ROW_ID = :serverCode
}

OCMTEAMS_TEAMMEMBERS_FIND_TEAM_MEMBERS {
 	SELECT TEAM_ID ,STAFF_ID ,LOC_ROLE_FROM_DATE ,POSITION ,ROLE ,TEAM_MEMBER_ID ,AGY_LOC_ID ,ACTIVE_FLAG ,ACTIVE_FLAG CHECKFLAG,EXPIRY_DATE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,NO_OF_TASKS ,SEAL_FLAG   FROM TEAM_MEMBERS   where  TEAM_ID =:teamId 
}
OCMTEAMS_TEAMMEMBERS_INSERT_TEAM_MEMBERS {
	insert into TEAM_MEMBERS(TEAM_ID , STAFF_ID , LOC_ROLE_FROM_DATE , position , role , TEAM_MEMBER_ID , AGY_LOC_ID , ACTIVE_FLAG , EXPIRY_DATE , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , NO_OF_TASKS , SEAL_FLAG )
	values(:teamId , :staffId , :locRoleFromDate , :position , :role , :teamMemberId , :agyLocId , :activeFlag , :expiryDate ,current_timestamp , :createUserId ,null , :noOfTasks , :sealFlag )
}

OCMTEAMS_TEAMMEMBERS_UPDATE_TEAM_MEMBERS {
	 update TEAM_MEMBERS set TEAM_ID = :teamId , STAFF_ID = :staffId , LOC_ROLE_FROM_DATE = :locRoleFromDate , position = :position , role = :role , 
	 TEAM_MEMBER_ID = :teamMemberId , AGY_LOC_ID = :agyLocId , ACTIVE_FLAG = :activeFlag , EXPIRY_DATE = :expiryDate , MODIFY_DATETIME =current_timestamp ,
	 MODIFY_USER_ID = :modifyUserId , NO_OF_TASKS = :noOfTasks , SEAL_FLAG = :sealFlag where TEAM_MEMBER_ID = :teamMemberId
}


OCMTEAMS_TEAMS_ONCHECKDELETEMASTER_ {
	SELECT 1 FROM TEAM_FUNCTIONS T WHERE T.TEAM_ID = :TEAMID
}

OCMTEAMS_AVAIL_TEAM_ONCHECKDELETEMASTER {
	SELECT count(*) FROM TEAM_MEMBERS T WHERE T.TEAM_ID = :teamId
}

OCMTEAMS_OCMTEAMS_POSTQUERY {
	SELECT   DISTINCT SM.LAST_NAME, SM.FIRST_NAME, SLR.SCHEDULE_TYPE, SLR.HOURS_PER_WEEK FROM STAFF_LOCATION_ROLES SLR, STAFF_MEMBERS SM, TEAM_MEMBERS TM 
	WHERE SM.STAFF_ID = :staffId AND SLR.SAC_STAFF_ID = :staffId AND SM.STAFF_ID = :staffId AND SLR.POSITION = :position AND SLR.ROLE = :role AND
	TM.TEAM_MEMBER_ID = :teamMemberId  ORDER BY LAST_NAME
}

OCMTEAMS_OCMTEAMS_KEYDELREC {
	SELECT COUNT ( * ) FROM TEAM_MEMBERS WHERE TEAM_ID = :teamId AND active_flag = 'Y' AND expiry_date is null
}

OCMTEAMS_OCMTEAMS_PREINSERT {
	SELECT NEXTVAL('TEAM_ID') FROM DUAL
}
OCMTEAMS_TEAMCODE_KEYDELREC_EXIST {
	SELECT COUNT ( * ) FROM TEAMS WHERE TEAM_CODE = :teamCode
}


OCMTEAMS_OCMTEAMMEMBERS_PREINSERT {
	SELECT NEXTVAL('TEAM_MEMBER_ID') FROM DUAL
}
OCMTEAMS_AVAIL_TEAMS_ACTIVE_FIND_TEAMS{
SELECT TEAM_CODE ,DESCRIPTION ,CATEGORY ,LIST_SEQ ,ACTIVE_FLAG ,EXPIRY_DATE ,CREATE_USER_ID ,MODIFY_USER_ID ,MODIFY_DATETIME ,CREATE_DATETIME ,TEAM_ID 
,AREA_CODE ,AGY_LOC_ID ,QUEUE_CLUSTER_ID ,SEAL_FLAG   FROM TEAMS   where  AREA_CODE = :areaCode AND ACTIVE_FLAG='Y'
order by list_seq,team_code,description,category
}
OCMTEAMS_UPDATE_ALLOWED_CHECK{
select  count(*) FROM caseload_agency_locations cal WHERE caseload_id = :caseloadId AND agy_loc_id = :agyLocId
}
OCMTEAMS_FUNCTIONTYPE_KEYDELREC_EXIST{
SELECT COUNT ( * ) FROM team_functions WHERE team_id = :teamId AND function_type = :functionType
}
OCMTEAMS_CONSTRAINT_VALIDATIONS{
select
	AC1.TABLE_NAME
from
	ALL_CONSTRAINTS AC1,
	ALL_CONSTRAINTS AC2
where
	upper(AC1.CONSTRAINT_NAME) =:CONSTRAINTNAME
	and
upper(AC2.TABLE_NAME) = 'TEAM_FUNCTIONS'
	and
AC1.CONSTRAINT_TYPE = 'f'
	and
AC2.CONSTRAINT_NAME = AC1.R_CONSTRAINT_NAME
	and
AC2.OWNER = AC1.R_OWNER
	and
upper(AC2.CONSTRAINT_TYPE) in ('P', 'U')
}
OCMTEAMS_TEAMS_GET_TEAMS {
 	SELECT c.team_code 
FROM
STAFF_MEMBERS A, TEAM_STAFF_MEMBERS B,AUTOMATION_TEAMS C
WHERE
A.STAFF_ID=B.STAFF_ID
AND B.TEAM_ID=C.TEAM_ID AND A.USER_ID=:userId

}
GET_TEAMS_DETAIL{ 
SELECT DISTINCT
    t.team_code code,
    t.description
FROM
    automation_teams           t,
    team_staff_members    tm,
    staff_members   sm
WHERE
    tm.team_id = t.team_id
    AND tm.staff_id = sm.staff_id
   
    AND tm.active_flag = 'Y'

}