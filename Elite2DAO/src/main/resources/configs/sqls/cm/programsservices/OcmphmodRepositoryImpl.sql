OCMPHMOD_COURSEACTIVITIES_FIND_COURSE_ACTIVITIES{
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
    no_of_sessions nosessions,
    session_length,
    multi_phase_scheduling_flag,
    schedule_notes,
    seal_flag,
    allow_double_book_flag
FROM
    course_activities
WHERE
 parent_crs_acty_id = :CRSACTYID
ORDER BY
    list_seq 
}
OCMPHMOD_COURSEACTIVITIES_UPDATE_COURSE_ACTIVITIES{
	UPDATE COURSE_ACTIVITIES  set   CASELOAD_ID=:caseloadId, AGY_LOC_ID=:agyLocId, DESCRIPTION=:description, 
CAPACITY=:capacity, ACTIVE_FLAG=:activeFlag, EXPIRY_DATE=:expiryDate, SCHEDULE_START_DATE=:scheduleStartDate, SCHEDULE_END_DATE=:scheduleEndDate, 
CASELOAD_TYPE=:caseloadType, SERVICES_ADDRESS_ID=:servicesAddressId, PROGRAM_ID=:programId, INTERNAL_LOCATION_ID=:internaLocationId, 
PROVIDER_PARTY_CLASS=:providerPartyClass, PROVIDER_PARTY_ID=:providerPartyId, PROVIDER_PARTY_CODE=:providerPartyCode, BENEFICIARY_NAME=:beneficiaryName, 
BENEFICIARY_CONTACT=:beneficiaryContact, LIST_SEQ=:listSeq, PLACEMENT_CORPORATE_ID=:placementCorporateId, COMMENT_TEXT=:commentText, AGENCY_LOCATION_TYPE=:agencyLocationType, 
PROVIDER_TYPE=:providerType, BENEFICIARY_TYPE=:beneficiaryType, PLACEMENT_TEXT=:placementText, CODE=:code,HOLIDAY_FLAG=:holidayFlag, COURSE_CLASS=:courseClass, 
COURSE_ACTIVITY_TYPE=:courseActivityType, CREATE_DATETIME=:createDatetime,  MODIFY_DATETIME=CURRENT_TIMESTAMP, MODIFY_USER_ID=:modifyUserId, 
IEP_LEVEL=:iepLevel, NO_OF_SESSIONS=:noOfSessions, SESSION_LENGTH=:sessionLength, MULTI_PHASE_SCHEDULING_FLAG=:multiPhaseSchedulingFlag, SCHEDULE_NOTES=:scheduleNotes, 
SEAL_FLAG=:sealFlag, ALLOW_DOUBLE_BOOK_FLAG=:allowDoubleBookFlag 
where CRS_ACTY_ID=:crsActyId
}

OCMPHMOD_GET_PRG_SRV_DETAILS{
SELECT *
           FROM PROGRAM_SERVICES
          WHERE program_id = :programId
}

OCMPHMOD_COUNT_SESSIONS{
 SELECT COUNT ( * )
           FROM course_schedules
          WHERE schedule_status = 'SCH'
            AND catch_up_crs_sch_id IS NULL
            AND crs_acty_id IN ( SELECT    crs_acty_id
                                      FROM course_activities
                                START WITH crs_acty_id = p_crs_acty_id
                                CONNECT BY PRIOR crs_acty_id = parent_crs_acty_id )
  }
  
OCMPHMOD_CONSTRAINT_VALIDATIONS{
SELECT 
  DISTINCT C.TABLE_NAME CHILD_TABLE
FROM 
  USER_CONSTRAINTS C, USER_CONSTRAINTS R, USER_CONS_COLUMNS CC, 
  USER_CONS_COLUMNS RC
WHERE
R.TABLE_NAME = 'COURSE_ACTIVITIES' AND
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

OCMPHMOD_DELETE_COURSE_ACTIVITIES{ 
	DELETE FROM COURSE_ACTIVITIES where CRS_ACTY_ID=:crsActyId
}

OCMPHMOD_SESSION_COUNT{
SELECT SUM ( coalesce ( no_of_sessions, 0 ) )
           FROM COURSE_ACTIVITIES
          WHERE parent_crs_acty_id =:P_CRS_ACTY_ID
}
