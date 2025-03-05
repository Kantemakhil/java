
OCMSPRAC_FIND_RGAGYLOC {
 	SELECT   AL.DESCRIPTION          ,AL.AGY_LOC_ID CODE         ,'Y' REQUERY_BLOCK FROM     AGENCY_LOCATIONS AL WHERE           AGENCY_LOCATION_TYPE = 'INST' AND     (          ( ACTIVE_FLAG = 'Y' AND DEACTIVATION_DATE IS NULL  ) OR '' = 'ENTER-QUERY' ) AND      AL.AGY_LOC_ID IN (                     SELECT CA.AGY_LOC_ID                       FROM CASELOAD_AGENCY_LOCATIONS CA                    
 	WHERE CA.CASELOAD_ID = :CASELOADID                            ) AND AL.AGY_LOC_ID NOT IN ('TRN' , 'OUT' ) ORDER BY LIST_SEQ ,DESCRIPTION ,AGY_LOC_ID
}

OCMSPRAC_FIND_RGPRISONACTIVITY {
 	SELECT DESCRIPTION         , PROGRAM_CODE         , to_char(PROGRAM_ID) CODE,
case when (select count(*)  FROM PROGRAM_SERVICES  WHERE PROGRAM_CATEGORY = 'INST_ACT'    AND ((ACTIVE_FLAG = 'Y' AND        EXPIRY_DATE IS NULL ) OR ''= 'ENTER-QUERY' ) and 
PROGRAM_CODE = prg.PROGRAM_CODE)>0 THEN 'Y' ELSE 'N' END ACTIVE_FLAG
   FROM PROGRAM_SERVICES prg
   }
OCMSPRAC_FIND_RGINTERNALLOCATION {	
SELECT AL.DESCRIPTION ,        AL.INTERNAL_LOCATION_CODE LOCCODE,        to_char(AL.INTERNAL_LOCATION_ID) CODE  ,
case when (select count(*)  
 FROM INT_LOC_USAGE_LOCATIONS A , INTERNAL_LOCATION_USAGES B ,AGENCY_INTERNAL_LOCATIONS C  WHERE A.INTERNAL_LOCATION_USAGE_ID = B.INTERNAL_LOCATION_USAGE_ID AND C.INTERNAL_LOCATION_ID = AL.INTERNAL_LOCATION_ID
AND B.INTERNAL_LOCATION_USAGE = 'PROG'    AND B.AGY_LOC_ID = :NBTAGYLOCID       AND A.INTERNAL_LOCATION_ID = C.INTERNAL_LOCATION_ID    AND ((C.ACTIVE_FLAG = 'Y'    AND C.DEACTIVATE_DATE IS NULL )
        OR ('' = 'ENTER-QUERY' ) )    AND NOT EXISTS (SELECT 1                      FROM INT_LOC_USAGE_LOCATIONS                     WHERE PARENT_USAGE_LOCATION_ID = A.USAGE_LOCATION_ID )) >0 THEN 'Y' ELSE 'N' END ACTIVE_FLAG
FROM   AGENCY_INTERNAL_LOCATIONS AL  ORDER BY AL.LIST_SEQ ,AL.DESCRIPTION ,AL.INTERNAL_LOCATION_CODE
}

OCMSPRAC_FIND_RGIEPLEVEL {
 	SELECT DESCRIPTION ,           CODE     FROM REFERENCE_CODES    WHERE DOMAIN = 'IEP_LEVEL'      AND (ACTIVE_FLAG = 'Y' OR '' = 'ENTER-QUERY' ) ORDER BY LIST_SEQ , DESCRIPTION , CODE
}

OCMSPRAC_COURSEACTIVITIES_FIND_COURSE_ACTIVITIES {
SELECT
crs_acty_id,
caseload_id,
agy_loc_id,
description,
capacity,
active_flag,
expiry_date,
schedule_start_date,
schedule_end_date,
schedule_end_date schenddate,
caseload_type,
services_address_id,
program_id,
parent_crs_acty_id,
internal_location_id,
provider_party_class,
provider_party_id,
provider_party_code,
beneficiary_name,
beneficiary_contact,
list_seq,
placement_corporate_id,
comment_text,
agency_location_type,
provider_type,
beneficiary_type,
placement_text,
code,
holiday_flag,
course_class,
course_activity_type,
create_datetime,
create_user_id,
modify_datetime,
modify_user_id,
iep_level,
no_of_sessions,
session_length,
multi_phase_scheduling_flag,
schedule_notes,
seal_flag,
allow_double_book_flag,
row_id
FROM
course_activities
WHERE
course_activity_type = 'IA'
AND provider_party_class = 'AGY'
AND caseload_type = :caseload_type
AND agy_loc_id = :agy_loc_id
ORDER BY
CASE
WHEN coalesce(schedule_end_date, current_timestamp +interval '1DAY') > current_timestamp THEN
2
ELSE
1
END
DESC,
schedule_start_date DESC,
schedule_end_date DESC,
description
}
OCMSPRAC_COURSEACTIVITIES_INSERT_COURSE_ACTIVITIES {
 insert into COURSE_ACTIVITIES(CRS_ACTY_ID, CASELOAD_ID, AGY_LOC_ID, DESCRIPTION, CAPACITY, ACTIVE_FLAG, EXPIRY_DATE, SCHEDULE_START_DATE, SCHEDULE_END_DATE, CASELOAD_TYPE, PROGRAM_ID, INTERNAL_LOCATION_ID, PROVIDER_PARTY_CLASS, PROVIDER_PARTY_ID, PROVIDER_PARTY_CODE, BENEFICIARY_NAME, BENEFICIARY_CONTACT, LIST_SEQ, COMMENT_TEXT, AGENCY_LOCATION_TYPE, PROVIDER_TYPE, BENEFICIARY_TYPE, PLACEMENT_TEXT, CODE, HOLIDAY_FLAG, COURSE_CLASS, COURSE_ACTIVITY_TYPE, CREATE_DATETIME, CREATE_USER_ID, IEP_LEVEL, NO_OF_SESSIONS, SESSION_LENGTH, MULTI_PHASE_SCHEDULING_FLAG, SCHEDULE_NOTES, SEAL_FLAG, ALLOW_DOUBLE_BOOK_FLAG, MODIFY_DATETIME) values(:crsActyId, :caseloadId, :agyLocId, :description, :capacity, 'Y', null, :scheduleStartDate, :scheduleEndDate, :caseloadType, :programId, :internalLocationId, :providerPartyClass, :providerPartyId, :providerPartyCode, :beneficiaryName, :beneficiaryContact, :listSeq, :commentText, :agencyLocationType, :providerType, :beneficiaryType, :placementText, :code, :holidayFlag, :courseClass, :courseActivityType, current_timestamp, :createUserId, :iepLevel, :noOfSessions, :sessionLength, :multiPhaseSchedulingFlag, :scheduleNotes, :sealFlag, :allowDoubleBookFlag, null)
}

OCMSPRAC_COURSEACTIVITIES_UPDATE_COURSE_ACTIVITIES {
	 update COURSE_ACTIVITIES set DESCRIPTION = :description, INTERNAL_LOCATION_ID = :internalLocationId, CAPACITY = :capacity, 
	 SCHEDULE_START_DATE = :scheduleStartDate, SCHEDULE_END_DATE =:scheduleEndDate, COMMENT_TEXT =:commentText,
	 MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where CRS_ACTY_ID =:crsActyId 
}

OCMSPRAC_COURSEACTIVITIES_DELETE_COURSE_ACTIVITIES { 
	DELETE FROM COURSE_ACTIVITIES where CRS_ACTY_ID =:crsActyId
}
OCMSPRAC_CHECK_CODE_EXISTS {
Select  count(*) From    COURSE_ACTIVITIES Where   agy_loc_id IN (:p_agy_loc_id) and     code IN (:p_code) and     course_activity_type = 'PA'
            AND     provider_party_class = 'AGY' AND     caseload_type IN (:p_caseload_type) AND     caseload_id IN (:p_caseload_id)
}
OCMSPRAC_CHECK_CODE_EXISTS_ON_UPDATE {
Select  count(*) From    COURSE_ACTIVITIES Where   agy_loc_id IN (:p_agy_loc_id) and     code IN (:p_code) and     course_activity_type = 'PA'
            AND     provider_party_class = 'AGY' AND     caseload_type IN (:p_caseload_type) AND     caseload_id IN (:p_caseload_id) and row_id NOT iN (:rowid )
}
OK_TO_DELETE_RECORD {
SELECT
    tc.table_name, 

    kcu.column_name

FROM 

    information_schema.table_constraints AS tc 

    JOIN information_schema.key_column_usage AS kcu

      ON tc.constraint_name = kcu.constraint_name

      AND tc.table_schema = kcu.table_schema

    JOIN information_schema.constraint_column_usage AS ccu

      ON ccu.constraint_name = tc.constraint_name

      AND ccu.table_schema = tc.table_schema

WHERE tc.constraint_type = 'FOREIGN KEY' and upper(ccu.table_name)='COURSE_ACTIVITIES' and UPPER(CCU.COLUMN_NAME)=UPPER('CRS_ACTY_ID')
                             }