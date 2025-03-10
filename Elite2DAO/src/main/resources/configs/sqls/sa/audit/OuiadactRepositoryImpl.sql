
OUIADACT_FIND_RGTABLENAME {
 	SELECT  OBJECT_NAME  CODE,OBJECT_NAME DESCRIPTION    FROM ALL_OBJECTS  WHERE OBJECT_TYPE = 'TABLE'    AND NOT OBJECT_NAME LIKE 'BIN%'     AND TEMPORARY = 'N'     AND OWNER = 'OMS_OWNER'  ORDER BY OBJECT_NAME
}


OUIADACT_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OUIADACT_FIND_DETAILS {
SELECT sessionid session_id, TO_DATE(TO_CHAR(CAST( (from_tz(ntimestamp#,'00:00') AT LOCAL) AS DATE),'MM/DD/YYYY HH24:MI'),'MM/DD/YYYY HH24:MI') stamp, DECODE (INSTR (clientid, ':', 1, 2), 1, 'INTERNAL', SUBSTR (clientid, INSTR (clientid, ':', 1, 2) + 1) ) os_user, dbuid db_user, DECODE (INSTR (clientid, ':'), 1, 'INTERNAL', SUBSTR (clientid, 1, INSTR (clientid, ':') - 1) ) clientip FROM fga_log$ WHERE obj$name = :P_TABLENAME AND ( ntimestamp# >= SYS_EXTRACT_UTC (TO_TIMESTAMP(TO_CHAR(:P_DATEFROM,'MM/DD/YYYY')||TO_CHAR(:P_TIMEFROM,'HH24:MI'),'MM/DD/YYYY HH24:MI') AT TIME ZONE 'UTC') AND ntimestamp# <= SYS_EXTRACT_UTC (TO_TIMESTAMP(TO_CHAR(:P_DATETO,'MM/DD/YYYY')||TO_CHAR(:P_TIMETO,'HH24:MI'),'MM/DD/YYYY HH24:MI') AT TIME ZONE 'UTC') )
ORDER BY ntimestamp# DESC
}
OUIADACT_GET_STAFF_NAME {
 select initcap(last_name ||', '||first_name) Name from oms_owner.staff_members where user_id = :dbUser
 }
