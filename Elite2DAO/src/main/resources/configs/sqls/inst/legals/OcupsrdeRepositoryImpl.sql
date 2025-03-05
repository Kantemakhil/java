DISPLAY_COURT_REPORT {
SELECT ORDER_ID,
ORDER_TYPE,
OFFENDER_BOOK_ID,
WORKFLOW_ID,
COURT_SERIOUSNESS_LEVEL,
ISSUING_AGY_LOC_ID,
REQUEST_DATE,
DUE_DATE,
COMPLETE_DATE,
ORDER_STATUS,
NON_REPORT_FLAG,
WORKFLOW_ID,
COMMENT_TEXT
FROM ORDERS
WHERE EVENT_ID = :eventId AND
ORDER_TYPE IN (SELECT ORDER_TYPE FROM ORDER_TYPES WHERE ORDER_CATEGORY = 'COURT_REPORT')
}

LOV_REPORT_TYPE {
SELECT description description,
       order_type report_type
  FROM order_types
 WHERE order_category = 'COURT_REPORT'
  AND (active_flag = 'Y')
 Order by description,order_type ASC
}

SET_POSTQUERY_REPORTTYPE {
   SELECT description
            FROM order_types
           WHERE order_type = :REPORTTYPE
}

SET_POSTQUERY_AGYLOCDESC {
SELECT  TAG_ESTABLISHMENT_get_agy_loc_desc (:AGENCYLOCID) FROM DUAL
}

SET_POSTQUERY_COURTSERIOUSNESSLEVEL {
SELECT oms_miscellaneous_getdesccode('PSR_LEV_SER',:courtSeriousnessLevel) FROM DUAL
}

SET_POSTQUERY_REPORTSTATUS {
SELECT oms_miscellaneous_getdesccode ('PSR_STATUS',:REPORTSTATUS) FROM DUAL
}

LOV_FUNCTION_TYPE {
Select description,code
from reference_codes
where domain = 'FUNCTION'
and (active_flag = 'Y')
order by list_seq,description,code
}

LOV_AREA_TYPE {
  SELECT RC.DESCRIPTION,RC.CODE
            FROM   REFERENCE_CODES RC
            WHERE  domain = 'AREA_TYPE'
            AND    code in ('COMM','INST')
            AND    ( active_Flag = 'Y')
            ORDER BY LIST_SEQ,DESCRIPTION,CODE
}

LOV_AREA {
SELECT A.DESCRIPTION,A.AREA_CODE
              FROM   AREAS A
              WHERE  A.AREA_TYPE = :AREATYPE
              AND A.AREA_CLASS NOT IN ('REGION', 'SUB_AREA')
              AND  ( active_Flag = 'Y')
              ORDER BY LIST_SEQ,DESCRIPTION,AREA_CODE
}

LOV_TEAM_RESPONSIBLE {
select DESCRIPTION, CODE from (select DESCRIPTION,TEAM_ID, TO_CHAR(TEAM_ID) AS CODE, AREA_CODE from teams) TE,
(select team_id from TEAM_FUNCTIONS where function_type IN (:FUNCTIONTYPE)) TF
where TE.AREA_CODE=:AREACODE AND TF.team_id = te.team_id
}

LOV_STAFF_DETAILS {
SELECT SM.LAST_NAME || ', ' || SM.FIRST_NAME  STAFF_NAME
,RC.DESCRIPTION        						  as POSITION
,TM.ROLE        	   						  as ROLE
,LOC_ROLE_FROM_DATE    						  FROM_DATE	

FROM TEAM_MEMBERS TM, STAFF_MEMBERS SM, REFERENCE_CODES RC
WHERE TM.TEAM_ID = :TEAMID
AND (TM.ACTIVE_FLAG = 'Y')
AND SM.STAFF_ID = TM.STAFF_ID
AND RC.CODE = TM.POSITION
AND RC.DOMAIN = 'STAFF_POS'
AND (RC.ACTIVE_FLAG = 'Y')
ORDER BY SM.LAST_NAME,SM.FIRST_NAME
}

LOV_STAFF_DETAILS_ALTER {
SELECT SM.LAST_NAME || ', ' || SM.FIRST_NAME  STAFF_NAME,
TO_CHAR(TM.TEAM_MEMBER_ID)     TEAM_MEMBER_ID
FROM TEAM_MEMBERS TM, STAFF_MEMBERS SM, REFERENCE_CODES RC
WHERE TM.TEAM_ID = :TEAMID
AND (TM.ACTIVE_FLAG = 'Y')
AND SM.STAFF_ID = TM.STAFF_ID
AND RC.CODE = TM.POSITION
AND RC.DOMAIN = 'STAFF_POS'
AND (RC.ACTIVE_FLAG = 'Y')
ORDER BY SM.LAST_NAME,SM.FIRST_NAME
}

PREINSERT_ORDER_ID {
select NEXTVAL('order_id')
}

INSERT_COURT_REPORT{
INSERT INTO ORDERS (
ORDER_TYPE,ISSUING_AGY_LOC_ID,
REQUEST_DATE,DUE_DATE,
COMPLETE_DATE,ORDER_STATUS,
NON_REPORT_FLAG,COMMENT_TEXT,
CASE_ID,OFFENDER_BOOK_ID,
EVENT_ID,ORDER_ID,COURT_DATE,
CREATE_DATETIME,MODIFY_DATETIME,
CREATE_USER_ID)
VALUES(
:reportType,:agyLocId,
:dateRequested,:dueDate,
:dateOfCompletion,:status,
:nonReportFlag,:commentText,
:caseId,:offenderBookId,
:eventId,:orderId,:courtDate,
current_timestamp,current_timestamp,
:createUserId)
}

IS_REPORT_EXIST {
SELECT count(order_id) FROM orders WHERE offender_book_id   = :p_off_book_id
                         AND court_date=:p_court_date
                         AND order_type=:p_order_type
                         AND issuing_agy_loc_id=:p_iss_agy_loc_id
                         AND request_date=:p_request_date
                         AND due_date=:p_due_date
                         AND event_id= :p_event_id
						 AND case_id= :p_case_id
}

GET_PREINSERT_REPORTSTATUS {
Select oms_miscellaneous_getdesccode('PSR_STATUS', :REPORTSTATUS) from dual
}
GET_STAFF_ID{ 

SELECT STAFF_ID FROM team_staff_members WHERE TEAM_MEMBER_ID=:teamMemberId
}

GET_WORK_ID{ 
SELECT work_id
           FROM works
          WHERE workflow_type = :workFlowType
            AND work_type = :workType
            AND work_sub_type =:workSubType 
            AND active_flag = 'Y'

}