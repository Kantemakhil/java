
OCMCPREV_FIND_RGSUPLEVEL {
 	SELECT REF_CODE.DESCRIPTION  DESCRIPTION  ,REF_CODE.CODE  CODE FROM   REFERENCE_CODES REF_CODE WHERE   (DOMAIN = 'SUP_LVL_TYPE' AND  ACTIVE_FLAG = 'Y' OR '' <> 'NORMAL' )     ORDER BY  REF_CODE.LIST_SEQ ASC         ,REF_CODE.DESCRIPTION ASC
}

OCMCPREV_CASEREVIEWPERIODS_FIND_CASE_REVIEW_PERIODS {
  SELECT
supervision_level,
review_period,
create_datetime,
create_user_id,
modify_datetime,
modify_user_id,
seal_flag,
row_id
FROM
case_review_periods 
}
OCMCPREV_CASEREVIEWPERIODS_INSERT_CASE_REVIEW_PERIODS {
 insert into CASE_REVIEW_PERIODS(SUPERVISION_LEVEL, REVIEW_PERIOD, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SEAL_FLAG) values(:supervisionLevel, :reviewPeriod, current_timestamp, :createUserId, null, :sealFlag) 
}

OCMCPREV_CASEREVIEWPERIODS_UPDATE_CASE_REVIEW_PERIODS {
update CASE_REVIEW_PERIODS set SUPERVISION_LEVEL =:supervisionLevel, REVIEW_PERIOD =:reviewPeriod, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID =:modifyUserId, SEAL_FLAG =:sealFlag where ROW_ID = :rowId
}

OCMCPREV_CASEREVIEWPERIODS_DELETE_CASE_REVIEW_PERIODS { 
	DELETE FROM CASE_REVIEW_PERIODS WHERE SUPERVISION_LEVEL = :supervisionLevel
}


OCMCPREV_CASE_REVIEW_PERIODS_POSTQUERY_ {
	SELECT REF_CODE.DESCRIPTION FROM REFERENCE_CODES REF_CODE WHERE DOMAIN = 'SUP_LVL_TYPE' AND REF_CODE.CODE = :SUPERVISIONLEVEL
}

OCMCPREV_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}
