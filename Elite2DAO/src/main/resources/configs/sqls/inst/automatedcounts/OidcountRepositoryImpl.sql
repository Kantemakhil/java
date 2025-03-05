
OIDCOUNT_FIND_CGFKAGYLOCID {
 	SELECT AL.AGY_LOC_ID CODE ,        AL.DESCRIPTION DESCRIPTION   FROM AGENCY_LOCATIONS AL  WHERE AL.AGY_LOC_ID IN       ( SELECT CAL.AGY_LOC_ID          FROM CASELOAD_AGENCY_LOCATIONS CAL , CASELOADS CAS         WHERE CAS.CASELOAD_ID = CAL.CASELOAD_ID           AND CAL.CASELOAD_ID = :CASELOADID           AND CAL.AGY_LOC_ID NOT IN ('OUT' ,'TRN' ,'CRT' ) )    AND AL.AGY_LOC_ID NOT IN ('OUT' , 'TRN' )    AND AL.DEACTIVATION_DATE IS NULL ORDER BY AL.LIST_SEQ  ,AL.AGY_LOC_ID
}

OIDCOUNT_FIND_CGFKCOUNTTYPES {
 	SELECT RC.CODE CODE ,RC.DESCRIPTION DESCRIPTION FROM REFERENCE_CODES RC WHERE DOMAIN = 'COUNT_TYPES'  AND ACTIVE_FLAG = 'Y' AND RC.CODE IN  (SELECT COUNT_TYPE_CODE  FROM AGENCY_COUNT_TYPES ACT WHERE AGY_LOC_ID = :AGYLOCID   AND ACT.ACTIVE_FLAG = 'Y'  ) ORDER BY LIST_SEQ
}

OIDCOUNT_FIND_CGFKSCHEDULEDTIME {
 	select
	case
		when SCHEDULED_TIME = 'NA' then null
		else SCHEDULED_TIME
	end SCHEDULED_TIME  ,
	AGY_LOC_ID ,
	COUNT_TYPE_CODE ,
	COUNT_TYPE_ID
from
	AGENCY_COUNT_TYPES ACT
where
	AGY_LOC_ID = :AGYLOCID
	and COUNT_TYPE_CODE = :COUNTTYPECODE
	and ACT.ACTIVE_FLAG = 'Y'
order by
	SCHEDULED_TIME ,
	AGY_LOC_ID ,
	COUNT_TYPE_CODE
}


OIDCOUNT_GLOBAL_SESSION_ID {
	SELECT USERENV_number('SESSIONID') FROM DUAL
}

OIDCOUNT_CGWHEN_NEW_FORM_INSTANCE_CG_P_SESSION_ID {
	SELECT MODULE_NAME, CASELOAD_ID, USER_ID, LOCKED_DATE, OFFENDER_ID, SESSION_ID, CREATE_DATETIME,   CREATE_USER_ID, MODIFY_DATETIME,   MODIFY_USER_ID, AGY_LOC_ID, SEAL_FLAG, ACCOUNT_CODE FROM LOCKED_MODULES WHERE MODULE_NAME = 'OIDCOUNT' AND CASELOAD_ID = :CASELOADID AND SESSION_ID IS NOT NULL ORDER BY CREATE_DATETIME DESC
}

OIDCOUNT_CGWHEN_NEW_FORM_INSTANCE_COUNT_LOCKED_MODULES {
	SELECT COUNT(1) FROM LOCKED_MODULES WHERE MODULE_NAME = 'OIDCOUNT'
}

OIDCOUNT_CGWHEN_NEW_FORM_INSTANCECG_LOCKED_MODULES_SESSION_ID {
	SELECT USER_ID FROM LOCKED_MODULES WHERE MODULE_NAME = 'OIDCOUNT' AND SESSION_ID = :SESSION_ID AND ROW_ID < 2
}

OIDCOUNT_CGWHEN_NEW_FORM_INSTANCECG$WHEN_NEW_FORM_INSTANCE {
select
	COUNT(1)
from
	CASELOAD_AGENCY_LOCATIONS
where
	CASELOAD_ID in (
	select
		CASELOAD_ID
	from
		STAFF_ACCESSIBLE_CASELOADS
	where
		STAFF_ID in (
		select
			STAFF_ID
		from
			STAFF_MEMBERS
		where
			USER_ID = :USER_ID))
	and AGY_LOC_ID not in ('OUT', 'TRN', 'CRT')
}

OIDCOUNT_CGWHEN_NEW_FORM_INSTANCE_CG_CASELOAD {
select
	AGY_LOC_ID
from
	CASELOAD_AGENCY_LOCATIONS
where
	CASELOAD_ID in (
	select
		CASELOAD_ID
	from
		STAFF_ACCESSIBLE_CASELOADS
	where
		STAFF_ID in (
		select
			STAFF_ID
		from
			STAFF_MEMBERS
		where
			USER_ID = :USER_ID))
	and AGY_LOC_ID not in ('OUT', 'TRN', 'CRT')
}

OIDCOUNT_CGWHEN_NEW_FORM_INSTANCECG_LOCKED_MODULES {
	SELECT COUNT(1) FROM LOCKED_MODULES WHERE AGY_LOC_ID = :agyLocId AND LOCKED_DATE = current_timestamp AND MODULE_NAME = 'OIDCOUNT' AND SESSION_ID <> :SESSION_ID
}

OIDCOUNT_PRINT_LIST_PROFILE_TYPE_CLIENT {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

OIDCOUNT_PRINT_LIST_SALES_MAINTENANCES {
	SELECT * FROM SALES_MAINTENANCES WHERE CASELOAD_ID = :P_CASELOAD_ID AND STOCK_LOC_ID = :STOCKID
}

OIDCOUNT_PRINT_LIST_SYSTEM_PROFILES {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'
}

OIDCOUNT_DEFAULT_AGY_LOC_SESSION_ID {
	SELECT DISTINCT AGY_LOC_ID FROM LOCKED_MODULES WHERE LOCKED_DATE = current_timestamp AND MODULE_NAME = 'OIDCOUNT' AND SESSION_ID = :SESSIONID
}

OIDCOUNT_DEFAULT_AGY_LOC_GLOBAL_CASELOAD {
	SELECT ALAL.AGY_LOC_ID, ALAL.DESCRIPTION DESCRIPTION FROM AGENCY_LOCATIONS ALAL WHERE ALAL.AGENCY_LOCATION_TYPE = 'INST' AND ALAL.AGY_LOC_ID IN (SELECT CA.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CA WHERE CA.CASELOAD_ID = :CASELOAD_ID) AND ALAL.AGY_LOC_ID NOT IN ( 'TRN', 'OUT' )
}

OIDCOUNT_DEFAULT_AGY_LOCDEFAULT_AGY_LOC_ELSE_CONDTION {
	SELECT ALAL.AGY_LOC_ID, ALAL.DESCRIPTION DESCRIPTION FROM AGENCY_LOCATIONS ALAL WHERE ALAL.AGENCY_LOCATION_TYPE = 'INST' AND ALAL.AGY_LOC_ID = :AGY_LOC_ID
}

OIDCOUNT_CGKEY_LISTVAL_AGENCY_COUNT_TYPES {
	select
	case
		when SCHEDULED_TIME = 'NA' then '9999'
		else SCHEDULED_TIME
	end SCHEDULED_TIME , 
	COUNT_TYPE_ID
from
	AGENCY_COUNT_TYPES
where
	AGY_LOC_ID = :AGYLOCID
	and COUNT_TYPE_CODE = :COUNTTYPECODE
order by
	case
		when SCHEDULED_TIME = 'NA' then '9999'
		else SCHEDULED_TIME
	end 
}

OIDCOUNT_CGKEY_LISTVAL_AGENCY_COUNTS {
	SELECT REPORTING_LOC_ID FROM AGENCY_COUNTS WHERE COUNT_TYPE_ID = :COUNTTYPEID AND INITIATED_DATE = current_timestamp AND OUTCOME IS NULL
}

OIDCOUNT_CGWHEN_NEW_ITEM_INSTANCE_ {
	select
	case
		when SCHEDULED_TIME = 'NA' then '9999'
		else SCHEDULED_TIME
	end SCHEDULED_TIME,
	COUNT_TYPE_ID
from
	AGENCY_COUNT_TYPES
where
	AGY_LOC_ID = :AGYLOCID
	and COUNT_TYPE_CODE = :COUNTTYPECODE
order by
	case
		when SCHEDULED_TIME = 'NA' then '9999'
		else SCHEDULED_TIME
	end 
}

OIDCOUNT_CGWHEN_NEW_ITEM_INSTANCE {
	SELECT REPORTING_LOC_ID FROM AGENCY_COUNTS WHERE COUNT_TYPE_ID = :COUNTTYPEID AND INITIATED_DATE = current_timestamp AND OUTCOME IS NULL
}

OIDCOUNT_PRE_INSERT_OF_AGENCY_LOCATION_COUNTS {
	SELECT AGY_SEQ FROM AGENCY_REPORTING_LOCS WHERE COUNT_TYPE_ID = :COUNT_TYPE_ID ORDER BY LIST_SEQ
}

OIDCOUNT_GET_AGENCY_LOCATION_COUNTS_NEXT_VALUE {
	SELECT nextval('REPORTING_LOC_ID')  FROM DUAL
}

OIDCOUNT_CREATE_RECOUNT_RECORDS {
	SELECT AGY_SEQ FROM AGENCY_REPORTING_LOCS WHERE COUNT_TYPE_ID = :COUNT_TYPE_ID ORDER BY LIST_SEQ
}

OIDCOUNT_CREATE_RECOUNT_RECORDSl_agy_reg_locs_cur {
	SELECT COUNT (1) INTO L_COUNT FROM TEMP_OIDCOUNT WHERE SESSION_ID = :SESSION_ID
}

OIDCOUNT_CREATE_RECOUNT_RECORDSl_agy_reg_locs_cur {
	SELECT nextval('REPORTING_LOC_ID')  FROM DUAL
}

OIDCOUNT_UPDATE_AGENCY_COUNTSUPDATE_AGENCY_COUNTS {
	DELETE FROM LOCKED_MODULES WHERE SESSION_ID = :sessionId
}

OIDCOUNT_DELETE_INITIATE_RECORDS {
	SELECT REPORTING_LOC_ID,COUNT_TYPE_ID FROM AGENCY_COUNTS AC, LOCKED_MODULES LM WHERE COUNT_TYPE_ID = :COUNTTYPEID AND LM.SESSION_ID = :SESSIONID AND LM.MODULE_NAME = 'OIDCOUNT' AND INITIATED_DATE = LM.LOCKED_DATE AND COUNT_IN_PROGRESS = 'Y' AND OUTCOME IS NULL
}

OIDCOUNT_DELETE_INITIATE_RECORDS_AGENCY_LOCATION_COUNTS {
	DELETE FROM AGENCY_LOCATION_COUNTS WHERE REPORTING_LOC_ID = :reportingLocId
}

OIDCOUNT_DELETE_INITIATE_RECORDS_AGENCY_COUNTS {
	DELETE FROM AGENCY_COUNTS WHERE REPORTING_LOC_ID = :reportingLocId
}

OIDCOUNT_DELETE_INITIATE_RECORDS_LOCKED_MODULES {
	DELETE FROM LOCKED_MODULES WHERE SESSION_ID = :sessionId
}

OIDCOUNT_CHECK_EXISTING_COUNT_SESSION {
	SELECT COUNT(1) FROM TEMP_OIDCOUNT O WHERE SESSION_ID = :SESSION_ID AND NOT EXISTS (SELECT 1 FROM TEMP_OIDCOUNT OX WHERE SESSION_ID = :SESSION_ID AND AGY_LOC_ID = 'CANCEL' )
}

OIDCOUNT_CHECK_EXISTING_COUNT_SESSIONl{
	SELECT AGY_LOC_ID, COUNT_TYPE_ID, COUNT_TYPE_CODE, SCHEDULED_TIME, ACTUAL_COUNT, TOTAL_MALE, TOTAL_FEMALE, TOTAL_OTHER, REPORTED_COUNT, DISCREPANCY_COUNT, DATE_SUBMITTED, ENTERED_BY_USERID, LOCATION_DESCRIPTION, REPORTING_LOC_ID FROM TEMP_OIDCOUNT WHERE SESSION_ID = :SESSION_ID AND AGY_LOC_ID <> 'CANCEL' ORDER BY LOCATION_TYPE, LIST_SEQ, AGY_SEQ
}

OIDCOUNT_REFRESH_COUNT_REFRESH_COUNT_TEMP_OID_COUNT {
	SELECT AGY_LOC_ID, SCHEDULED_TIME, AGY_SEQ, LOCATION_TYPE, LOWEST_LOCATION_ID, LOCATION_DESCRIPTION, ACTUAL_COUNT, TOTAL_MALE, TOTAL_FEMALE, TOTAL_OTHER, REPORTED_COUNT, COUNT_TYPE_ID, COUNT_TYPE_CODE, REPORTING_LOC_ID, DISCREPANCY_COUNT, DATE_SUBMITTED, ENTERED_BY_USERID FROM TEMP_OIDCOUNT WHERE SESSION_ID = :P_SESSION_ID AND AGY_LOC_ID <> 'CANCEL' ORDER BY LOCATION_TYPE, LIST_SEQ, AGY_SEQ
}

OIDCOUNT_REFRESH_COUNT_USER_CANCELLED_CUR {
	SELECT 'N' FROM LOCKED_MODULES WHERE MODULE_NAME = 'OIDCOUNT' AND SESSION_ID = :P_SESSION_ID AND USER_ID = :P_USER_ID
}

OIDCOUNT_REFRESH_COUNT {
	SELECT COUNT (1) COUNT_TEMP, SUM (coalesce (ACTUAL_COUNT, 0)) ACTUAL_COUNT, SUM (coalesce (TOTAL_MALE, 0)) TOTAL_MALE, SUM (coalesce (TOTAL_FEMALE, 0)) TOTAL_FEMALE, SUM (coalesce (TOTAL_OTHER, 0)) TOTAL_OTHER, SUM (coalesce (REPORTED_COUNT, 0)) REPORTED_COUNT, SUM (coalesce (DISCREPANCY_COUNT, 0)) DISCREPANCY_COUNT, SUM (coalesce (OUT_TOTAL, 0)) OUT_TOTAL, SUM (coalesce (TOTAL_MALE_OUT, 0)) TOTAL_MALE_OUT, SUM (coalesce (TOTAL_FEMALE_OUT, 0)) TOTAL_FEMALE_OUT, SUM (coalesce (TOTAL_OTHER_OUT, 0)) TOTAL_OTHER_OUT, COUNT (ACTUAL_COUNT) ACTUAL_COUNT_TEMP, COUNT (REPORTED_COUNT) REPORTED_COUNT_TEMP FROM TEMP_OIDCOUNT WHERE SESSION_ID = :SESSION_ID AND AGY_LOC_ID <> 'CANCEL'
}

OIDCOUNT_CGKEY_EXIT_CGKEY_EXIT {
	SELECT COUNT(1) FROM AGENCY_REPORTING_LOCS ARL, AGENCY_COUNTS AC WHERE ARL.COUNT_TYPE_ID = :AGENCY_COUNT_TYPES.COUNT_TYPE_ID AND AC.COUNT_TYPE_ID = :AGENCY_COUNT_TYPES.COUNT_TYPE_ID and AC.INITIATED_DATE = (select LOCKED_DATE FROM LOCKED_MODULES WHERE MODULE_NAME = 'OIDCOUNT' AND SESSION_ID = :SESSION_ID AND USER_ID = upper(user) ) AND AC.OUTCOME IS NULL
}

OIDCOUNT_CREATE_INITIATED_RECORDS_REPORTING_LOC_ID {
	SELECT REPORTING_LOC_ID, COUNT_TYPE_ID, INITIATED_DATE, OUTCOME, COUNT_IN_PROGRESS FROM AGENCY_COUNTS WHERE COUNT_TYPE_ID = :PCOUNTTYPEID AND INITIATED_DATE = current_timestamp ORDER BY INITIATED_DATE DESC
}
OIDCOUNT_COUNT_LOCKED_MODULES {
 SELECT CREATE_USER_ID FROM LOCKED_MODULES WHERE AGY_LOC_ID = :AGYLOCID AND MODULE_NAME = 'OIDCOUNT' AND SESSION_ID <> :GLOBALSESSIONID
 }
 OIDCOUNT_COUNT_AGENCY_REPORTING_LOCS {
   SELECT COUNT (1) FROM AGENCY_REPORTING_LOCS WHERE COUNT_TYPE_ID = :COUNTTYPEID
 }
 OIDCOUNT_COUNT_AGENCY_COUNTS_TYPES_CURSOR {
   SELECT COUNT (1) FROM AGENCY_COUNTS AC, AGENCY_COUNT_TYPES ACT WHERE ACT.COUNT_TYPE_ID = AC.COUNT_TYPE_ID AND ACT.AGY_LOC_ID    = :AGYLOCID
         AND AC.COUNT_TYPE_ID  = :COUNTTYPEID
         AND to_char(AC.INITIATED_DATE, 'yyyy/MM/dd') = to_char(current_timestamp, 'yyyy/MM/dd')
         AND coalesce (AC.OUTCOME, 'XXX') = 'CLEARED'
}

OIDCOUNT_CHECK_COUNT_EXISTS {
SELECT 'Y'
        FROM AGENCY_REPORTING_LOCS ARL,
             AGENCY_COUNT_TYPES ACT,
             (SELECT 'LIV' UNIT_TYPE, LIVING_UNIT_ID, DESCRIPTION
                FROM LIVING_UNITS 
               WHERE AGY_LOC_ID = :P_AGY_LOC_ID
               UNION ALL
                 SELECT 'IML', 
                         INTERNAL_LOCATION_ID AGENCY_IML_ID, 
                         INTERNAL_LOCATION_DESC DESCRIPTION                        
                   FROM V_INT_LOC_SUMMARIES
                  WHERE AGY_LOC_ID           =  :P_AGY_LOC_ID ) LU                 
       WHERE ARL.COUNT_TYPE_ID = ACT.COUNT_TYPE_ID
         AND ARL.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
         AND ACT.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
         AND ARL.ACTIVE_FLAG = 'Y'
         AND ACT.ACTIVE_FLAG = 'Y'
         AND ACT.AGY_LOC_ID = :P_AGY_LOC_ID
         AND ACT.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
         AND ACT.SCHEDULED_TIME = :P_SCHEDULED_TIME
         AND (  (   ARL.LOCATION_TYPE <> 'INIT'
                AND (  (   ARL.LOCATION3_ID IS NOT NULL
                       AND ARL.LOCATION3_ID = LU.LIVING_UNIT_ID AND LU.UNIT_TYPE = 'LIV')
                    OR (   ARL.LOCATION2_ID IS NOT NULL
                       AND ARL.LOCATION2_ID = LU.LIVING_UNIT_ID AND LU.UNIT_TYPE = 'LIV'
                       AND ARL.LOCATION3_ID IS NULL)
                    OR (   ARL.LOCATION1_ID IS NOT NULL
                       AND ARL.LOCATION1_ID = LU.LIVING_UNIT_ID AND LU.UNIT_TYPE = 'LIV'
                       AND (   ARL.LOCATION2_ID IS NULL
                           AND ARL.LOCATION3_ID IS NULL))))
             OR (   ARL.LOCATION_TYPE = 'INIT'
                AND ARL.LOCATION1_ID = LU.LIVING_UNIT_ID AND LU.UNIT_TYPE = 'IML'))
         AND ( (ARL.LOCATION_TYPE <> 'INIT'
                OR (ARL.LOCATION_TYPE = 'INIT'
                    AND (EXISTS
                          (SELECT 1
                             FROM OFFENDER_BOOKINGS OBX
                            WHERE OBX.ACTIVE_FLAG = 'Y'
                              AND OBX.AGY_LOC_ID = :P_AGY_LOC_ID
                              AND OBX.AGENCY_IML_ID
                                     = case when ARL.LOCATION3_ID is null 
                              				then case when ARL.LOCATION2_ID is null 
                              						 then  ARL.LOCATION1_ID
                              						 else  ARL.LOCATION2_ID
                              			 		  end
                              				else  ARL.LOCATION3_ID
                              	        end	
                              AND OBX.AGY_LOC_ID = ACT.AGY_LOC_ID
                              AND OBX.IN_OUT_STATUS = 'IN'
                           )
                          OR EXISTS
                          (SELECT 1
                             FROM SYSTEM_PROFILES
                            WHERE PROFILE_TYPE = 'CLIENT'
                              AND PROFILE_CODE = 'ZERO_COUNT'
                              AND PROFILE_VALUE = 'Y'
                          )
                         )
                    )
                  OR EXISTS
                          (SELECT 1
                             FROM SYSTEM_PROFILES
                            WHERE PROFILE_TYPE = 'CLIENT'
                              AND PROFILE_CODE = 'ZERO_COUNT'
                              AND PROFILE_VALUE = 'Y'
                          )                    
                 )
              )
         AND ( (ARL.LOCATION_TYPE = 'INIT'
                OR (ARL.LOCATION_TYPE <> 'INIT'
                    AND (EXISTS
                          (SELECT 1
                             FROM OFFENDER_BOOKINGS OBX,
                                  LIVING_UNITS LUX
                            WHERE OBX.ACTIVE_FLAG = 'Y'
                              AND OBX.AGY_LOC_ID = :P_AGY_LOC_ID
                              AND OBX.LIVING_UNIT_ID = LUX.LIVING_UNIT_ID
                              AND OBX.AGENCY_IML_ID IS NULL
                              AND LUX.AGY_LOC_ID = :P_AGY_LOC_ID
                              AND LUX.DESCRIPTION LIKE LU.DESCRIPTION || '%'
                              AND OBX.AGY_LOC_ID = ACT.AGY_LOC_ID
                              AND OBX.IN_OUT_STATUS = 'IN'
                           )
                         OR EXISTS
                           (SELECT 1
                              FROM SYSTEM_PROFILES
                             WHERE PROFILE_TYPE = 'CLIENT'
                               AND PROFILE_CODE = 'ZERO_COUNT'
                               AND PROFILE_VALUE = 'Y'
                           )
                        )
                    )
                  OR EXISTS
                          (SELECT 1
                             FROM SYSTEM_PROFILES
                            WHERE PROFILE_TYPE = 'CLIENT'
                              AND PROFILE_CODE = 'ZERO_COUNT'
                              AND PROFILE_VALUE = 'Y'
                          )                      
                 )
              )
}
OIDCOUNT_INSERT_LOCKED_MODULES {
INSERT INTO LOCKED_MODULES (MODULE_NAME, CASELOAD_ID, USER_ID, LOCKED_DATE, AGY_LOC_ID, SESSION_ID,CREATE_DATETIME,CREATE_USER_ID ) values ('OIDCOUNT', :caseloadId, :userId, current_timestamp, :agyLocId, :sessionId,current_timestamp,:createUserId)
           
}
OIDCOUNT_PRE_INSERT_LOCKED_MODULES {
SELECT 'OIDCOUNT',
                   :CASELOAD_ID CASELOAD_ID,
                   :userId,
                   current_timestamp ,
                   :AGY_LOC_ID AGY_LOC_ID,
                   :SESSION_ID SESSION_ID
              FROM DUAL
             WHERE NOT EXISTS (SELECT 1
                                 FROM LOCKED_MODULES LM
                                WHERE LM.MODULE_NAME = 'OIDCOUNT'
                                  AND LM.AGY_LOC_ID =
                                         :AGY_LOC_ID)
}
OIDCOUNT_INSERT_AGENCY_COUNTS {
INSERT INTO AGENCY_COUNTS ( REPORTING_LOC_ID, COUNT_TYPE_ID, INITIATED_DATE, PARENT_REPORTING_LOC_ID, CONDUCTED_BY_USERID, COUNT_IN_PROGRESS,CREATE_DATETIME,CREATE_USER_ID )
              VALUES ( :reportingLocId, :countTypeId, current_timestamp, :parentReportingLocId, :conductedByUserid, 'Y',current_timestamp,:createUserId )
}
OIDCOUNT_INSERT_AGENCY_LOCATION_COUNTS {
INSERT INTO AGENCY_LOCATION_COUNTS ( REPORTING_LOC_ID, COUNT_TYPE_ID, AGY_SEQ, DATE_SUBMITTED, ENTERED_BY_USERID, CONDUCTED_DATETIME, RCNT_IN_PROGRESS_FLAG,CREATE_DATETIME,CREATE_USER_ID )
                 VALUES ( :reportingLocId, :countTypeId, :agySeq, current_timestamp, :enteredByUserid, current_timestamp, 'N',current_timestamp,:createUserId)
}
OIDCOUNT_INITIATE_COUNT_SETUP {
select
	ARL.AGY_SEQ,
	ARL.LIST_SEQ,
	ACT.AGY_LOC_ID,
	ARL.LOCATION_TYPE,
	LU.DESCRIPTION LOCATION_DESCRIPTION,
	case
		when coalesce(ARL.LOCATION3_ID::text, '') = '' then
		case
			when coalesce(ARL.LOCATION2_ID::text, '') = '' then ARL.LOCATION1_ID
			else ARL.LOCATION2_ID
		end
		else ARL.LOCATION3_ID
	end LOWEST_LOCATION_ID,
	ALC.COUNT_TYPE_ID,
	ACT.COUNT_TYPE_CODE,
	ALC.REPORTING_LOC_ID,
	ALC.DATE_SUBMITTED,
	ALC.ENTERED_BY_USERID
from
	AGENCY_LOCATION_COUNTS ALC,
	AGENCY_REPORTING_LOCS ARL,
	AGENCY_COUNT_TYPES ACT,
	AGENCY_COUNTS AC,
	LOCKED_MODULES LM,
	(
	select
		'LIV' UNIT_TYPE,
		LIVING_UNIT_ID,
		DESCRIPTION
	from
		LIVING_UNITS
	where
		AGY_LOC_ID = :P_AGY_LOC_ID
union all
	select
		'IML',
		INTERNAL_LOCATION_ID AGENCY_IML_ID,
		INTERNAL_LOCATION_DESC DESCRIPTION
	from
		V_INT_LOC_SUMMARIES
	where
		AGY_LOC_ID = :P_AGY_LOC_ID ) LU
where
	ARL.COUNT_TYPE_ID = ALC.COUNT_TYPE_ID
	and ARL.COUNT_TYPE_ID = ACT.COUNT_TYPE_ID
	and ARL.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
	and ALC.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
	and ACT.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
	and date_trunc('day', ALC.DATE_SUBMITTED) = date_trunc('day', LM.LOCKED_DATE)
	and LM.SESSION_ID = :SESSION_ID
	and LM.MODULE_NAME = 'OIDCOUNT'
	and ARL.AGY_SEQ = ALC.AGY_SEQ
	and ARL.ACTIVE_FLAG = 'Y'
	and ACT.ACTIVE_FLAG = 'Y'
	and ACT.AGY_LOC_ID = :P_AGY_LOC_ID
	and AC.REPORTING_LOC_ID = ALC.REPORTING_LOC_ID
	and AC.COUNT_TYPE_ID = ALC.COUNT_TYPE_ID
	and coalesce(AC.OUTCOME::text, '') = ''
	and AC.COUNT_IN_PROGRESS = 'Y'
	and ACT.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
	and ( coalesce(:P_SCHEDULED_TIME::text, '') = ''
	and ACT.SCHEDULED_TIME = 'NA'
	or ACT.SCHEDULED_TIME = :P_SCHEDULED_TIME)
	and ( ( ARL.LOCATION_TYPE <> 'INIT'
	and ( ( (ARL.LOCATION3_ID is not null
	and ARL.LOCATION3_ID::text <> '')
	and ARL.LOCATION3_ID = LU.LIVING_UNIT_ID
	and LU.UNIT_TYPE = 'LIV' )
	or ( (ARL.LOCATION2_ID is not null
	and ARL.LOCATION2_ID::text <> '')
	and ARL.LOCATION2_ID = LU.LIVING_UNIT_ID
	and LU.UNIT_TYPE = 'LIV'
	and coalesce(ARL.LOCATION3_ID::text, '') = '' )
	or ( (ARL.LOCATION1_ID is not null
	and ARL.LOCATION1_ID::text <> '')
	and ARL.LOCATION1_ID = LU.LIVING_UNIT_ID
	and LU.UNIT_TYPE = 'LIV'
	and ( coalesce(ARL.LOCATION2_ID::text, '') = ''
	and coalesce(ARL.LOCATION3_ID::text, '') = '' ) ) ) )
	or ( ARL.LOCATION_TYPE = 'INIT'
	and ARL.LOCATION1_ID = LU.LIVING_UNIT_ID
	and LU.UNIT_TYPE = 'IML' ) )
	and (ARL.LOCATION_TYPE <> 'INIT'
	or (ARL.LOCATION_TYPE = 'INIT'
	and (exists (
	select
		1
	from
		OFFENDER_BOOKINGS OBX
	where
		OBX.ACTIVE_FLAG = 'Y'
		and OBX.AGY_LOC_ID = :P_AGY_LOC_ID
		and OBX.AGENCY_IML_ID =
		case
			when coalesce(ARL.LOCATION3_ID::text, '') = '' then
			case
				when coalesce(ARL.LOCATION2_ID::text, '') = '' then ARL.LOCATION1_ID
				else ARL.LOCATION2_ID
			end
			else ARL.LOCATION3_ID
		end
		and OBX.AGY_LOC_ID = ACT.AGY_LOC_ID
		and OBX.IN_OUT_STATUS = 'IN' )
	or exists (
	select
		1
	from
		SYSTEM_PROFILES
	where
		PROFILE_TYPE = 'CLIENT'
		and PROFILE_CODE = 'ZERO_COUNT'
		and PROFILE_VALUE = 'Y' ) ) )
	or exists (
	select
		1
	from
		SYSTEM_PROFILES
	where
		PROFILE_TYPE = 'CLIENT'
		and PROFILE_CODE = 'ZERO_COUNT'
		and PROFILE_VALUE = 'Y' ))
	and (ARL.LOCATION_TYPE = 'INIT'
	or (ARL.LOCATION_TYPE <> 'INIT'
	and (exists (
	select
		1
	from
		OFFENDER_BOOKINGS OBX,
		LIVING_UNITS LUX
	where
		OBX.ACTIVE_FLAG = 'Y'
		and OBX.AGY_LOC_ID = :P_AGY_LOC_ID
		and OBX.LIVING_UNIT_ID = LUX.LIVING_UNIT_ID
		and coalesce(OBX.AGENCY_IML_ID::text, '') = ''
		and LUX.AGY_LOC_ID = :P_AGY_LOC_ID
		and LUX.DESCRIPTION like LU.DESCRIPTION || '%'
		and OBX.AGY_LOC_ID = ACT.AGY_LOC_ID
		and OBX.IN_OUT_STATUS = 'IN' )
	or exists (
	select
		1
	from
		SYSTEM_PROFILES
	where
		PROFILE_TYPE = 'CLIENT'
		and PROFILE_CODE = 'ZERO_COUNT'
		and PROFILE_VALUE = 'Y' ) ) )
	or exists (
	select
		1
	from
		SYSTEM_PROFILES
	where
		PROFILE_TYPE = 'CLIENT'
		and PROFILE_CODE = 'ZERO_COUNT'
		and PROFILE_VALUE = 'Y' ))
order by
	ARL.LOCATION_TYPE,
	ARL.LIST_SEQ
}
OIDCOUNT_GET_COUNT_TYPE_ID {
SELECT COUNT_TYPE_ID FROM AGENCY_COUNT_TYPES WHERE AGY_LOC_ID = :AGYLOCID and COUNT_TYPE_CODE = :COUNTTYPECODE
}
PRE_INSERT_OF_TEMP_OIDCOUNT {
 select
	ARL.AGY_SEQ,
	ARL.LIST_SEQ,
	ACT.AGY_LOC_ID,
	ARL.LOCATION_TYPE,
	LU.DESCRIPTION LOCATION_DESCRIPTION,
	case
		when coalesce(ARL.LOCATION3_ID::text, '') = '' then
		case
			when coalesce(ARL.LOCATION2_ID::text, '') = '' then ARL.LOCATION1_ID
			else ARL.LOCATION2_ID
		end
		else ARL.LOCATION3_ID
	end LOWEST_LOCATION_ID,
	ALC.COUNT_TYPE_ID,
	ACT.COUNT_TYPE_CODE,
	ALC.REPORTING_LOC_ID,
	ACT.SCHEDULED_TIME
from
	AGENCY_LOCATION_COUNTS ALC,
	AGENCY_REPORTING_LOCS ARL,
	AGENCY_COUNT_TYPES ACT,
	AGENCY_COUNTS AC,
	LOCKED_MODULES LM,
	(
	select
		'LIV' UNIT_TYPE,
		LIVING_UNIT_ID,
		DESCRIPTION
	from
		LIVING_UNITS
	where
		AGY_LOC_ID = :P_AGY_LOC_ID
union all
	select
		'IML',
		INTERNAL_LOCATION_ID AGENCY_IML_ID,
		INTERNAL_LOCATION_DESC DESCRIPTION
	from
		V_INT_LOC_SUMMARIES
	where
		AGY_LOC_ID = :P_AGY_LOC_ID ) LU
where
	ARL.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
	and ALC.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
	and ACT.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
	and ARL.COUNT_TYPE_ID = ALC.COUNT_TYPE_ID
	and ARL.COUNT_TYPE_ID = ACT.COUNT_TYPE_ID
	and ( ( ARL.LOCATION_TYPE <> 'INIT'
	and ( ( (ARL.LOCATION3_ID is not null
	and ARL.LOCATION3_ID::text <> '')
	and ARL.LOCATION3_ID = LU.LIVING_UNIT_ID
	and LU.UNIT_TYPE = 'LIV')
	or ( (ARL.LOCATION2_ID is not null
	and ARL.LOCATION2_ID::text <> '')
	and ARL.LOCATION2_ID = LU.LIVING_UNIT_ID
	and LU.UNIT_TYPE = 'LIV'
	and coalesce(ARL.LOCATION3_ID::text, '') = '')
	or ( (ARL.LOCATION1_ID is not null
	and ARL.LOCATION1_ID::text <> '')
	and ARL.LOCATION1_ID = LU.LIVING_UNIT_ID
	and LU.UNIT_TYPE = 'LIV'
	and ( coalesce(ARL.LOCATION2_ID::text, '') = ''
	and coalesce(ARL.LOCATION3_ID::text, '') = ''))))
	or ( ARL.LOCATION_TYPE = 'INIT'
	and ARL.LOCATION1_ID = LU.LIVING_UNIT_ID
	and LU.UNIT_TYPE = 'IML'))
	and date_trunc('day', ALC.DATE_SUBMITTED) = date_trunc('day', LM.LOCKED_DATE)
	and LM.SESSION_ID = :P_SESSION_ID
	and LM.MODULE_NAME = 'OIDCOUNT'
	and ARL.AGY_SEQ = ALC.AGY_SEQ
	and ACT.ACTIVE_FLAG = 'Y'
	and ARL.ACTIVE_FLAG = 'Y'
	and ACT.AGY_LOC_ID = :P_AGY_LOC_ID
	and ACT.COUNT_TYPE_ID = :P_COUNT_TYPE_ID
	and AC.REPORTING_LOC_ID = ALC.REPORTING_LOC_ID
	and AC.COUNT_TYPE_ID = ALC.COUNT_TYPE_ID
	and coalesce(AC.OUTCOME::text, '') = ''
	and AC.COUNT_IN_PROGRESS = 'Y'
	and ACT.SCHEDULED_TIME = :P_SCHEDULED_TIME
	and (ARL.LOCATION_TYPE <> 'INIT'
	or ( ARL.LOCATION_TYPE = 'INIT'
	and ( exists (
	select
		1
	from
		OFFENDER_BOOKINGS OBX
	where
		OBX.ACTIVE_FLAG = 'Y'
		and OBX.AGENCY_IML_ID =
		case
			when coalesce(ARL.LOCATION3_ID::text, '') = '' then
			case
				when coalesce(ARL.LOCATION2_ID::text, '') = '' then ARL.LOCATION1_ID
				else ARL.LOCATION2_ID
			end
			else ARL.LOCATION3_ID
		end
		and OBX.AGY_LOC_ID = ACT.AGY_LOC_ID
		and OBX.IN_OUT_STATUS = 'IN')
	or exists (
	select
		1
	from
		SYSTEM_PROFILES
	where
		PROFILE_TYPE = 'CLIENT'
		and PROFILE_CODE = 'ZERO_COUNT'
		and PROFILE_VALUE = 'Y')))
	or exists (
	select
		1
	from
		SYSTEM_PROFILES
	where
		PROFILE_TYPE = 'CLIENT'
		and PROFILE_CODE = 'ZERO_COUNT'
		and PROFILE_VALUE = 'Y'))
	and (ARL.LOCATION_TYPE = 'INIT'
	or ( ARL.LOCATION_TYPE <> 'INIT'
	and ( exists (
	select
		1
	from
		OFFENDER_BOOKINGS OBX,
		LIVING_UNITS LUX
	where
		OBX.ACTIVE_FLAG = 'Y'
		and OBX.AGY_LOC_ID = :P_AGY_LOC_ID
		and OBX.LIVING_UNIT_ID = LUX.LIVING_UNIT_ID
		and coalesce(OBX.AGENCY_IML_ID::text, '') = ''
		and LUX.AGY_LOC_ID = :P_AGY_LOC_ID
		and LUX.DESCRIPTION like LU.DESCRIPTION || '%'
		and OBX.AGY_LOC_ID = ACT.AGY_LOC_ID
		and OBX.IN_OUT_STATUS = 'IN')
	or exists (
	select
		1
	from
		SYSTEM_PROFILES
	where
		PROFILE_TYPE = 'CLIENT'
		and PROFILE_CODE = 'ZERO_COUNT'
		and PROFILE_VALUE = 'Y')))
	or exists (
	select
		1
	from
		SYSTEM_PROFILES
	where
		PROFILE_TYPE = 'CLIENT'
		and PROFILE_CODE = 'ZERO_COUNT'
		and PROFILE_VALUE = 'Y'))
}
OIDCOUNT_INSERT_TEMP_OID_COUNT {
     insert into TEMP_OIDCOUNT(SESSION_ID, AGY_SEQ, LIST_SEQ, AGY_LOC_ID, LOCATION_TYPE, LOCATION_DESCRIPTION, LOWEST_LOCATION_ID, COUNT_TYPE_ID, COUNT_TYPE_CODE, REPORTING_LOC_ID, SCHEDULED_TIME , CREATE_DATETIME, CREATE_USER_ID ) values(:sessionId, :agySeq, :listSeq, :agyLocId, :locationType, :locationDescription, :lowestLocationId, :countTypeId, :countTypeCode, :reportingLocId, :scheduledTime, current_timestamp, :createUserId )
}
OIDCOUNT_TEMP_OIDCOUNT_CURSOR {
SELECT
                AGY_LOC_ID,
                AGY_SEQ,
                LOCATION_TYPE,
                LOWEST_LOCATION_ID,
                LOCATION_DESCRIPTION,
                ACTUAL_COUNT,
                REPORTED_COUNT,
					 OUT_TOTAL,
                COUNT_TYPE_ID,
                REPORTING_LOC_ID,
								TOTAL_MALE,
								TOTAL_FEMALE,
								TOTAL_OTHER,
								TOTAL_MALE_OUT,
								TOTAL_FEMALE_OUT,
								TOTAL_OTHER_OUT
           FROM TEMP_OIDCOUNT
          WHERE SESSION_ID = :P_SESSION_ID
            AND AGY_LOC_ID <> 'CANCEL'
          ORDER BY LOCATION_TYPE, LIST_SEQ, AGY_SEQ
          }
          
OIDCOUNT_GET_ACTUAL_COUNT_CURSOR1 {
select
	COUNT(1)
from
	OFFENDER_BOOKINGS
where
	ACTIVE_FLAG = 'Y'
	and AGY_LOC_ID = :P_AGY_LOC_ID
	and IN_OUT_STATUS = 'IN'
	and AGENCY_IML_ID in (with recursive cte as (
	select
		INTERNAL_LOCATION_ID
	from
		AGENCY_INTERNAL_LOCATIONS
	where
		INTERNAL_LOCATION_ID = :P_LOCATION_ID
union all
	select
		ail.INTERNAL_LOCATION_ID
	from
		AGENCY_INTERNAL_LOCATIONS ail
	join cte c on
		(c.INTERNAL_LOCATION_ID = ail.PARENT_INTERNAL_LOCATION_ID ) )
	select
		*
	from
		cte )
}
OIDCOUNT_GET_ACTUAL_COUNT_CURSOR2 {
  select
	COUNT(1)
from
	OFFENDER_BOOKINGS
where
	ACTIVE_FLAG = 'Y'
	and IN_OUT_STATUS = 'IN'
	and coalesce(AGENCY_IML_ID::text, '') = ''
	and AGY_LOC_ID = :P_AGY_LOC_ID
	and LIVING_UNIT_ID in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) a
	where
		internal_location_id = :P_LOCATION_ID
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ) )
	select
		*
	from
		cte )
 }
 OIDCOUNT_GET_TOTAL_MALE_CURSOR1 {
   select
	COUNT(1)
from
	OFFENDER_BOOKINGS OB,
	OFFENDERS OFD
where
	OB.ACTIVE_FLAG = 'Y'
	and OB.AGY_LOC_ID = :P_AGY_LOC_ID
	and OB.IN_OUT_STATUS = 'IN'
	and OB.OFFENDER_ID = OFD.OFFENDER_ID
	and OFD.SEX_CODE = 'M'
	and AGENCY_IML_ID in (with recursive cte as (
	select
		INTERNAL_LOCATION_ID
	from
		AGENCY_INTERNAL_LOCATIONS
	where
		INTERNAL_LOCATION_ID = :P_LOCATION_ID
union all
	select
		ail.INTERNAL_LOCATION_ID
	from
		AGENCY_INTERNAL_LOCATIONS ail
	join cte c on
		(c.INTERNAL_LOCATION_ID = ail.PARENT_INTERNAL_LOCATION_ID ) )
	select
		*
	from
		cte )
}
OIDCOUNT_GET_TOTAL_MALE_CURSOR2 {
select
	COUNT(1)
from
	OFFENDER_BOOKINGS OB,
	OFFENDERS OFD
where
	OB.ACTIVE_FLAG = 'Y'
	and OB.IN_OUT_STATUS = 'IN'
	and coalesce(OB.AGENCY_IML_ID::text, '') = ''
	and OB.AGY_LOC_ID = :P_AGY_LOC_ID
	and OB.LIVING_UNIT_ID in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) a
	where
		internal_location_id = :P_LOCATION_ID
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ) )
	select
		*
	from
		cte )
	and OB.OFFENDER_ID = OFD.OFFENDER_ID
	and OFD.SEX_CODE = 'M'

}
OIDCOUNT_GET_TOTAL_FEMALE_CURSOR1 {
select
	COUNT(1)
from
	OFFENDER_BOOKINGS OB,
	OFFENDERS OFD
where
	OB.ACTIVE_FLAG = 'Y'
	and OB.AGY_LOC_ID = :P_AGY_LOC_ID
	and OB.IN_OUT_STATUS = 'IN'
	and OB.OFFENDER_ID = OFD.OFFENDER_ID
	and OFD.SEX_CODE = 'F'
	and AGENCY_IML_ID in (with recursive cte as (
	select
		INTERNAL_LOCATION_ID
	from
		AGENCY_INTERNAL_LOCATIONS
	where
		INTERNAL_LOCATION_ID = :P_LOCATION_ID
union all
	select
		ail.INTERNAL_LOCATION_ID
	from
		AGENCY_INTERNAL_LOCATIONS ail
	join cte c on
		(c.INTERNAL_LOCATION_ID = ail.PARENT_INTERNAL_LOCATION_ID ) )
	select
		*
	from
		cte )
}
OIDCOUNT_GET_TOTAL_FEMALE_CURSOR2 {
select
	COUNT(1)
from
	OFFENDER_BOOKINGS OB,
	OFFENDERS OFD
where
	OB.ACTIVE_FLAG = 'Y'
	and OB.IN_OUT_STATUS = 'IN'
	and coalesce(OB.AGENCY_IML_ID::text, '') = ''
	and OB.AGY_LOC_ID = :P_AGY_LOC_ID
	and OB.living_unit_id in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) a
	where
		internal_location_id = :P_LOCATION_ID
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ) )
	select
		*
	from
		cte )
	and OB.OFFENDER_ID = OFD.OFFENDER_ID
	and OFD.SEX_CODE = 'F'

}
OIDCOUNT_UPDATE_TEMP_OIDCOUNT{
UPDATE TEMP_OIDCOUNT SET ACTUAL_COUNT = :actualCount, TOTAL_MALE = :totalMale, TOTAL_FEMALE = :totalFemale WHERE SESSION_ID = :sessionId
}

GET_TIMER_VALUE {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES where PROFILE_CODE='COUNT_TIMER'
}

DELETE_CANCEL_RECORDS {
	delete from TEMP_OIDCOUNT WHERE SESSION_ID = :sessionId AND AGY_LOC_ID = 'CANCEL'
}
GET_LCOUNTEXISTING_VALUE {
SELECT COUNT (1) FROM temp_oidcount WHERE session_id = :sessionId AND agy_loc_id <> 'CANCEL'
}
GET_LCOUNTEXISTING_VALUE_CANCEL {
SELECT COUNT (1) FROM temp_oidcount WHERE session_id = :sessionId AND agy_loc_id = 'CANCEL'
}
CANCEL_COUNT_INSERT_VALUE {
INSERT INTO temp_oidcount (session_id, agy_loc_id) SELECT :sessionId, 'CANCEL' FROM dual WHERE NOT EXISTS (SELECT 1 FROM temp_oidcount WHERE session_id = :sessionId AND agy_loc_id = 'CANCEL')
}

INSERT_PROCEDURE_MIGRATE_TEMPCOUNT_DOCUMENT_INSERT {
insert
	into
	temp_oidcount (session_id,
	agy_seq,
	list_seq,
	agy_loc_id,
	location_type,
	location_description,
	create_user_id,
	CREATE_DATETIME,
	lowest_location_id,
	count_type_id,
	count_type_code,
	reporting_loc_id,
	scheduled_time )
select
	:sessionId,
	arl.agy_seq,
	arl.list_seq,
	act.agy_loc_id,
	arl.location_type,
	lu.description location_description,
	:createUserId,
	current_timestamp,
	case when arl.location3_id is null
		 then  case when arl.location2_id is null
		 			then arl.location1_id
		 			else arl.location2_id
		 		end
		 else  arl.location3_id
	end lowest_location_id,
	alc.count_type_id,
	act.count_type_code,
	alc.reporting_loc_id,
	act.scheduled_time
from
	agency_location_counts alc,
	agency_reporting_locs arl,
	agency_count_types act,
	agency_counts ac,
	locked_modules lm,
	(
	select
		'LIV' unit_type,
		living_unit_id,
		description
	from
		living_units
	where
		agy_loc_id = :agyLocId
union all
	select
		'IML',
		internal_location_id agency_iml_id,
		internal_location_desc description
	from
		v_int_loc_summaries
	where
		agy_loc_id = :agyLocId ) lu
where
	arl.count_type_id = :countTypeId
	and alc.count_type_id = :countTypeId
	and act.count_type_id = :countTypeId
	and arl.count_type_id = alc.count_type_id
	and arl.count_type_id = act.count_type_id
	and ( ( arl.location_type <> 'INIT'
		and ( ( arl.location3_id is not null
			and arl.location3_id = lu.living_unit_id
			and lu.unit_type = 'LIV')
		or ( arl.location2_id is not null
			and arl.location2_id = lu.living_unit_id
			and lu.unit_type = 'LIV'
			and arl.location3_id is null)
		or ( arl.location1_id is not null
			and arl.location1_id = lu.living_unit_id
			and lu.unit_type = 'LIV'
			and ( arl.location2_id is null
				and arl.location3_id is null))))
	or ( arl.location_type = 'INIT'
		and arl.location1_id = lu.living_unit_id
		and lu.unit_type = 'IML'))
	and alc.date_submitted = lm.locked_date
	and lm.session_id = :sessionId
	and lm.module_name = 'OIDCOUNT'
	and arl.agy_seq = alc.agy_seq
	and act.active_flag = 'Y'
	and arl.active_flag = 'Y'
	and act.agy_loc_id = :agyLocId
	and act.count_type_id = :countTypeId
	and ac.reporting_loc_id = alc.reporting_loc_id
	and ac.count_type_id = alc.count_type_id
	and ac.outcome is null
	and ac.count_in_progress = 'Y'
	and (( arl.location_type <> 'INIT'
		or ( arl.location_type = 'INIT'
			and ( exists (
			select
				1
			from
				offender_bookings obx
			where
				obx.active_flag = 'Y'
				and obx.agency_iml_id = case when arl.location3_id is null
												then case when arl.location2_id is null
															then arl.location1_id
															else arl.location2_id
													  end
												else arl.location3_id
										end
				
					and obx.agy_loc_id = act.agy_loc_id
					and obx.in_out_status = 'IN')
			or exists (
			select
				1
			from
				system_profiles
			where
				profile_type = 'CLIENT'
				and profile_code = 'ZERO_COUNT'
				and profile_value = 'Y')))
		or exists (
		select
			1
		from
			system_profiles
		where
			profile_type = 'CLIENT'
			and profile_code = 'ZERO_COUNT'
			and profile_value = 'Y')))
	and (( arl.location_type = 'INIT'
		or ( arl.location_type <> 'INIT'
			and ( exists (
			select
				1
			from
				offender_bookings obx,
				living_units lux
			where
				obx.active_flag = 'Y'
				and obx.agy_loc_id = :agyLocId
				and obx.living_unit_id = lux.living_unit_id
				and obx.agency_iml_id is null
				and lux.agy_loc_id = :agyLocId
				and lux.description like lu.description || '%'
				and obx.agy_loc_id = act.agy_loc_id
				and obx.in_out_status = 'IN')
			or exists (
			select
				1
			from
				system_profiles
			where
				profile_type = 'CLIENT'
				and profile_code = 'ZERO_COUNT'
				and profile_value = 'Y')))
		or exists (
		select
			1
		from
			system_profiles
		where
			profile_type = 'CLIENT'
			and profile_code = 'ZERO_COUNT'
			and profile_value = 'Y')))
}
GET_LIST_OF_REPORTING_LOC_ID {
select
	reporting_loc_id
from
	temp_oidcount
where
	session_id = :sessionId
	and reporting_loc_id is not null
	and agy_loc_id <> 'CANCEL'
	limit 1
}
GET_LTEMP_COUNT_CURSER {
select
	ROW_ID,
	agy_loc_id,
	agy_seq,
	location_type,
	lowest_location_id,
	location_description,
	actual_count,
	reported_count,
	out_total,
	count_type_id,
	reporting_loc_id,
	total_male,
	total_female,
	total_other,
	total_male_out,
	total_female_out,
	total_other_out
from
	temp_oidcount
where
	session_id = :sessionId
	and agy_loc_id <> 'CANCEL'
order by
	location_type,
	list_seq
}
OIDCOUNT_TEMP_COUNT_DELETERECORD {
DELETE FROM temp_oidcount WHERE session_id = :sessionId
}
GET_LTERMINATED_SESSIONCOUNT {
SELECT COUNT (1) FROM locked_modules lm WHERE lm.session_id = :sessionId AND lm.module_name = 'OIDCOUNT'
}
OIDCOUNT_AGYLOC_COUNT_REPORT_DELETERECORD {
DELETE FROM agency_location_counts WHERE reporting_loc_id = :reportLocId
}
OIDCOUNT_AGENCY_COUNT_REPORT_DELETERECORD {
DELETE FROM agency_counts WHERE reporting_loc_id = :reportLocId
}
OCDCOUNT_GET_LCOUNT_INIT_CURSER {
select
	COUNT(1)
from
	offender_bookings
where
	active_flag = 'Y'
	and agy_loc_id = :agyLocId
	and in_out_status = 'IN'
	and agency_iml_id in (with recursive cte as (
	select
		internal_location_id
	from
		agency_internal_locations
	where
		internal_location_id = :lowestLocationId
union all
	select
		ail.internal_location_id
	from
		agency_internal_locations ail
	join cte c on
		(c.internal_location_id = ail.parent_internal_location_id ) )
	select
		*
	from
		cte

)
}
OCDCOUNT_GET_LCOUNT_LIVING_UNIT_CURSER {
select
	COUNT(1)
from
	offender_bookings
where
	active_flag = 'Y'
	and in_out_status = 'IN'
	and coalesce(agency_iml_id::text, '') = ''
	and agy_loc_id = :agyLocId
	and living_unit_id in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) a
	where
		internal_location_id = :lowestLocationId
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ) )
	select
		*
	from
		cte

)
}
OCDCOUNT_UPDATE_TEMP_OIDCOUNT_ACTUALCOUNT_VALUE {
UPDATE temp_oidcount SET actual_count = :actualCount, discrepancy_count = reported_count - actual_count WHERE ROW_ID = :rowId
}
OCDCOUNT_GET_LCOUNT_INIT_MALE_CURSER {
select
	COUNT(1)
from
	offender_bookings ob,
	offenders ofd
where
	ob.active_flag = 'Y'
	and ob.agy_loc_id = :agyLocId
	and ob.in_out_status = 'IN'
	and ob.offender_id = ofd.offender_id
	and ofd.sex_code = 'M'
	and agency_iml_id in (with recursive cte as (
	select
		internal_location_id
	from
		agency_internal_locations
	where
		internal_location_id = :lowestLocationId
union all
	select
		ail.internal_location_id
	from
		agency_internal_locations ail
	join cte c on
		(c.internal_location_id = ail.parent_internal_location_id ) )
	select
		*
	from
		cte

)
}
OCDCOUNT_GET_LCOUNT_LIVING_MALE_INIT_CURSER {
select
	COUNT(1)
from
	offender_bookings ob,
	offenders ofd
where
	ob.active_flag = 'Y'
	and ob.in_out_status = 'IN'
	and coalesce(ob.agency_iml_id::text, '') = ''
	and ob.agy_loc_id = :agyLocId
	and ob.living_unit_id in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) a
	where
		internal_location_id = :lowestLocationId
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ) )
	select
		*
	from
		cte

)
	and ob.offender_id = ofd.offender_id
	and ofd.sex_code = 'M'

}
OIDCOUNT_TOTAL_MALE_UPDATE{
UPDATE temp_oidcount SET total_male = :lTotalMale WHERE ROW_ID = :rowId
}
OCDCOUNT_GET_LCOUNT_INIT_FEMALE_CURSER {
select
	COUNT(1)
from
	offender_bookings ob,
	offenders ofd
where
	ob.active_flag = 'Y'
	and ob.agy_loc_id = :agyLocId
	and ob.in_out_status = 'IN'
	and ob.offender_id = ofd.offender_id
	and ofd.sex_code = 'F'
	and agency_iml_id in (with recursive cte as (
	select
		internal_location_id
	from
		agency_internal_locations
	where
		internal_location_id = :lowestLocationId
union all
	select
		ail.internal_location_id
	from
		agency_internal_locations ail
	join cte c on
		(c.internal_location_id = ail.parent_internal_location_id ) )
	select
		*
	from
		cte

)
}
OCDCOUNT_GET_LCOUNT_LIVING_FEMALE_INIT_CURSER {
select
	COUNT(1)
from
	offender_bookings ob,
	offenders ofd
where
	ob.active_flag = 'Y'
	and ob.in_out_status = 'IN'
	and coalesce(ob.agency_iml_id::text, '') = ''
	and ob.agy_loc_id = :agyLocId
	and ob.living_unit_id in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) a
	where
		internal_location_id = :lowestLocationId
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ) )
	select
		*
	from
		cte
)
	and ob.offender_id = ofd.offender_id
	and ofd.sex_code = 'F'


}
OIDCOUNT_TOTAL_FEMALE_UPDATE {
UPDATE temp_oidcount SET total_female = :lTotalFeMale WHERE ROW_ID = :rowId
}
OCDCOUNT_GET_LCOUNT_INIT_OTHER_CURSER {
select
	COUNT(1)
from
	offender_bookings ob,
	offenders ofd
where
	ob.active_flag = 'Y'
	and ob.agy_loc_id = :agyLocId
	and ob.in_out_status = 'IN'
	and ob.offender_id = ofd.offender_id
	and ofd.sex_code not in ('M', 'F')
	and agency_iml_id in (with recursive cte as (
	select
		internal_location_id
	from
		agency_internal_locations
	where
		internal_location_id = :lowestLocationId
union all
	select
		ail.internal_location_id
	from
		agency_internal_locations ail
	join cte c on
		(c.internal_location_id = ail.parent_internal_location_id ) )
	select
		*
	from
		cte

)
}
OCDCOUNT_GET_LCOUNT_LIVING_OTHER_INIT_CURSER {
select
	COUNT(1)
from
	offender_bookings ob,
	offenders ofd
where
	ob.active_flag = 'Y'
	and ob.in_out_status = 'IN'
	and coalesce(ob.agency_iml_id::text, '') = ''
	and ob.agy_loc_id = :agyLocId
	and ob.living_unit_id in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) a
	where
		internal_location_id = :lowestLocationId
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ) )
	select
		*
	from
		cte )
	and ob.offender_id = ofd.offender_id
	and ofd.sex_code not in ('M', 'F')
}
OIDCOUNT_TOTAL_OTHER_UPDATE{
UPDATE temp_oidcount SET total_other = :lTotalOther WHERE ROW_ID = :rowId
}
OCDCOUNT_GET_LCOUNT_INIT_OUTTOTAL_CURSER {
SELECT COUNT (1) FROM offender_bookings WHERE active_flag = 'Y' AND agency_iml_id = :lowestLocationId AND agy_loc_id = :agyLocId AND in_out_status = 'OUT'
}
OCDCOUNT_GET_LCOUNT_LIVING_OUTTOTAL_INIT_CURSER {
select
	COUNT(1)
from
	offender_bookings
where
	active_flag = 'Y'
	and in_out_status = 'OUT'
	and coalesce(agency_iml_id::text, '') = ''
	and agy_loc_id = :agyLocId
	and living_unit_id in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) a
	where
		internal_location_id = :lowestLocationId
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ) )
	select
		*
	from
		cte)
}
OIDCOUNT_TOTAL_OUTTOTAL_UPDATE {
UPDATE temp_oidcount SET out_total = :lOutTotal WHERE ROW_ID = :rowId
}
OCDCOUNT_GET_LCOUNT_INIT_TOTALMALEOUT_CURSER {
SELECT COUNT (1) FROM offender_bookings ob, offenders ofd WHERE ob.active_flag = 'Y' AND ob.agency_iml_id = :lowestLocationId AND ob.agy_loc_id = :agyLocId AND ob.in_out_status = 'OUT' AND ob.offender_id = ofd.offender_id AND ofd.sex_code = 'M'
}
OCDCOUNT_GET_LCOUNT_LIVING_TOTALMALEOUT_INIT_CURSER {
select
	COUNT(1)
from
	offender_bookings ob,
	offenders ofd
where
	ob.active_flag = 'Y'
	and ob.in_out_status = 'OUT'
	and coalesce(ob.agency_iml_id::text, '') = ''
	and ob.agy_loc_id = :agyLocId
	and ob.living_unit_id in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) a
	where
		internal_location_id = :lowestLocationId
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ) )
	select
		*
	from
		cte )
	and ob.offender_id = ofd.offender_id
	and ofd.sex_code = 'M'

}
OIDCOUNT_TOTAL_TOTALMALEOUT_UPDATE{
UPDATE temp_oidcount SET total_male_out = :lTotalMaleOut WHERE ROW_ID = :rowId
}
OCDCOUNT_GET_LCOUNT_INIT_TOTALFEMALEOUT_CURSER {
SELECT COUNT (1) FROM offender_bookings ob, offenders ofd WHERE ob.active_flag = 'Y' AND ob.agency_iml_id = :lowestLocationId AND ob.agy_loc_id = :agyLocId AND ob.in_out_status = 'OUT' AND ob.offender_id = ofd.offender_id AND ofd.sex_code = 'F'
}
OCDCOUNT_GET_LCOUNT_LIVING_TOTALFEMALEOUT_INIT_CURSER {
select
	COUNT(1)
from
	offender_bookings ob,
	offenders ofd
where
	ob.active_flag = 'Y'
	and ob.in_out_status = 'OUT'
	and coalesce(ob.agency_iml_id::text, '') = ''
	and ob.agy_loc_id = :agyLocId
	and ob.living_unit_id in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null)a
	where
		living_unit_id = :lowestLocationId
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ))
	select
		*
	from
		cte )
	and ob.offender_id = ofd.offender_id
	and ofd.sex_code = 'F'
}
OIDCOUNT_TOTAL_TOTALFEMALEOUT_UPDATE {
UPDATE temp_oidcount SET total_female_out = :lOutTotalFemaleOut WHERE ROW_ID = :rowId
}
OCDCOUNT_GET_LCOUNT_INIT_TOTALOTHEROUT_CURSER{
SELECT COUNT (1) FROM offender_bookings ob, offenders ofd WHERE ob.active_flag = 'Y' AND ob.agency_iml_id = :lowestLocationId AND ob.agy_loc_id = :agyLocId AND ob.in_out_status = 'OUT' AND ob.offender_id = ofd.offender_id AND ofd.sex_code NOT IN ('M','F')
}
OCDCOUNT_GET_LCOUNT_LIVING_TOTALOTHEROUT_INIT_CURSER{
select
	COUNT(1)
from
	offender_bookings ob,
	offenders ofd
where
	ob.active_flag = 'Y'
	and ob.in_out_status = 'OUT'
	and coalesce(ob.agency_iml_id::text, '') = ''
	and ob.agy_loc_id = :agyLocId
	and ob.living_unit_id in (with recursive cte as (
	select
		internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null)a
	where
		internal_location_id = :lowestLocationId
union all
	select
		lu.internal_location_id
	from
		(select
		internal_location_id,
		parent_internal_location_id,
		agy_loc_id
	from
		agency_internal_locations
	where
		unit_type is not null) lu
	join cte c on
		(c.internal_location_id = lu.parent_internal_location_id ) )
	select
		*
	from
		cte )
	and ob.offender_id = ofd.offender_id
	and ofd.sex_code not in ('M', 'F')

}
OIDCOUNT_TOTAL_TOTALOTHEROUT_UPDATE {
UPDATE temp_oidcount SET total_other_out = :lTotalOtherOut WHERE ROW_ID = :rowId
}