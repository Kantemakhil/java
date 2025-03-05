
OCMSVACP_FIND_RGREFCODE {
 	SELECT ref_code.description description, ref_code.code code, ref_code.list_seq FROM reference_codes ref_code WHERE domain = 'PS_PROV_TYPE' AND (ACTIVE_FLAG = 'Y' ) ORDER BY list_seq, description, code
}

OCMSVACP_FIND_RGPROVIDER_FN {
SELECT PARTY_NAME  DESCRIPTION ,DECODE(:providerType || :caseLoadType , 'INTINST' , PARTY_CODE , PARTY_ID ) PROVIDER ,DECODE(PARTY_CLASS , 'TEAM' , NULL , PARTY_CODE ) PARTY_CODE CODE , PARTY_ID   FROM V_PROGRAM_PROVIDERS_FN(:userId)  WHERE (:providerType = 'INT' AND :caseLoadType = 'INST' AND  PARTY_CLASS = 'AGY' AND  PARTY_CODE IN  (SELECT AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS WHERE CASELOAD_ID = :caseLoadId ) )  OR (:providerType = 'INT' AND :caseLoadId = 'COMM' AND   PARTY_CLASS = 'TEAM' AND PARTY_ID IN  (SELECT TM.TEAM_ID   FROM TEAM_MEMBERS  TM ,  TEAM_FUNCTIONS TF   WHERE TM.ACTIVE_FLAG = 'Y'  AND TM.TEAM_ID = TF.TEAM_ID   AND TF.FUNCTION_TYPE = 'ACP'  AND TM.STAFF_ID IN (SELECT STAFF_ID   FROM STAFF_MEMBERS   WHERE USER_ID = USER ) ) )  OR (:providerType = 'EXT' AND PARTY_CLASS = 'CORP' )  ORDER BY PARTY_NAME
}

OCMSVACP_FIND_RGTEAMAGYLOCS {
 select
	at2.description description,
	at2.team_id,
	at2.team_code code
from
	automation_teams at2
where
	active_flag = 'Y'
	and team_id in (
	select
		distinct team_id
	from
		TEAM_AGENCY_LOC
	where
		agy_loc_id in (
		select
			agy_loc_id
		from
			caseload_agency_locations cal
		where
			caseload_id = :caseloadId))
	and
  team_id in (
	select
		team_id
	from
		team_functions
	where
		function_type = 'ACP')
}

OCMSVACP_FIND_RGCORPLOCS {
 	SELECT TO_CHAR(C.CORPORATE_ID) CODE,C.CORPORATE_NAME DESCRIPTION   FROM CORPORATES C ,CORPORATE_TYPES CT  WHERE C.CORPORATE_ID = CT.CORPORATE_ID    AND CT.CORPORATE_TYPE = 'PRG'    AND C.ACTIVE_FLAG = 'Y' ORDER BY CORPORATE_NAME
}

OCMSVACP_FIND_RGAGYLOCS {
 	SELECT DESCRIPTION ,AGY_LOC_ID CODE FROM AGENCY_LOCATIONS AL WHERE AL.AGY_LOC_ID IN ( SELECT CA.AGY_LOC_ID  FROM CASELOAD_AGENCY_LOCATIONS CA  WHERE CA.CASELOAD_ID = :caseLoadId   AND CA.AGY_LOC_ID = AL.AGY_LOC_ID ) AND AL.AGY_LOC_ID NOT IN ('TRN' , 'OUT' )  ORDER BY LIST_SEQ ,DESCRIPTION ,AGY_LOC_ID
}

OCMSVACP_FIND_RGACCPROGRAM {
 	SELECT  DESCRIPTION , PROGRAM_CODE , TO_CHAR(PROGRAM_ID) CODE,
CASE WHEN (SELECT COUNT(*)  FROM PROGRAM_SERVICES  WHERE PROGRAM_ID = PGS.PROGRAM_ID AND PROGRAM_CATEGORY = 'ACP' AND PROGRAM_CLASS = 'PRG' AND ACTIVE_FLAG ='Y' ) >0  THEN 'Y' ELSE 'N' END ACTIVE_FLAG
 FROM PROGRAM_SERVICES PGS ORDER BY LIST_SEQ ,DESCRIPTION ,PROGRAM_CODE
}

OCMSVACP_FIND_RGINTLOCATION {
 	SELECT
    c.description,
    c.internal_location_code,
    c.internal_location_id code
FROM
    int_loc_usage_locations     a,
    internal_location_usages    b,
    agency_internal_locations   c
WHERE
    a.internal_location_usage_id = b.internal_location_usage_id
    AND b.internal_location_usage = 'PROG'
    AND b.agy_loc_id = :providerpartycode
    AND a.internal_location_id = c.internal_location_id
    AND ( c.active_flag = 'Y' )
    AND NOT EXISTS (
        SELECT
            1
        FROM
            int_loc_usage_locations
        WHERE
            parent_usage_location_id = a.usage_location_id
    )
ORDER BY
    c.list_seq,
    c.description,
    c.internal_location_code
}

OCMSVACP_FIND_RGADDRESS {
 select
	 address_line1,
     CITY_NAME,
    PROV_STATE_DESC,
	ZIP_POSTAL_CODE, 
	CORPORATE_NAME AGENCY ,
	SUITE_NUMBER ,
	COUNTRY ,
	ADDRESS_ID,
	city_code ,
	prov_state_code 
from
	V_ADDRESSES VA ,
	CORPORATES C
where
	OWNER_CLASS = 'CORP'
	and OWNER_ID = C.CORPORATE_ID
	and OWNER_ID = :providerPartyId
	and SERVICES_FLAG = 'Y'
	and VA.ACTIVE_FLAG = 'Y'
order by
	FULL_ADDRESS
}

OCMSVACP_FIND_RGAGYADDRESS {
select
    address_line1 ,
	AL.DESCRIPTION AGENCY ,
	SUITE_NUMBER ,
    CITY_NAME,
    PROV_STATE_DESC,
	
	ZIP_POSTAL_CODE ,
	COUNTRY ,
	ADDRESS_ID,
	city_code ,
	prov_state_code 
from
	V_ADDRESSES VA ,
	AGENCY_LOCATIONS AL
where
	OWNER_CLASS = 'AGY'
	and OWNER_CODE = :providerPartyCode
	and OWNER_CODE = AL.AGY_LOC_ID
	and VA.ACTIVE_FLAG = 'Y'
order by
	FULL_ADDRESS
 	
}

OCMSVACP_FIND_RGALLAGYADDRESS {
 	SELECT
    address_line1,
    al.description   agency,
    CITY_NAME,
    PROV_STATE_DESC,
	ZIP_POSTAL_CODE ,
    country,
    address_id,
    suite_number ,
    city_code ,
	prov_state_code 
FROM
    v_addresses        va,
    agency_locations   al
WHERE
    owner_class = 'AGY'
    AND va.active_flag = 'Y'
    AND owner_code IN (
        SELECT
            al.agy_loc_id
        FROM
            automation_teams     t,
            team_agency_loc  ta,
            agency_locations   al
        WHERE
        ta.team_id=t.team_id and 
            t.team_id = :providerPartyId
            AND ta.AGY_LOC_ID = al.AGY_LOC_ID
    )
    AND owner_code NOT IN (
        'TRN',
        'OUT'
    )
    AND owner_code = al.agy_loc_id
ORDER BY
    full_address
}

OCMSVACP_CRSACT_FIND_COURSE_ACTIVITIES {
 	SELECT  CRS_ACTY_ID,AGY_LOC_ID,CASELOAD_ID,DESCRIPTION,ACTIVE_FLAG,EXPIRY_DATE,CAPACITY,SCHEDULE_START_DATE,SCHEDULE_END_DATE,CASELOAD_TYPE,PROGRAM_ID,
PROVIDER_PARTY_ID,PROVIDER_PARTY_CODE,PROVIDER_PARTY_CLASS,COMMENT_TEXT,CODE,HOLIDAY_FLAG,COURSE_CLASS,COURSE_ACTIVITY_TYPE,CREATE_DATETIME,CREATE_USER_ID,
MODIFY_DATETIME,MODIFY_USER_ID,SERVICES_ADDRESS_ID,INTERNAL_LOCATION_ID,LIST_SEQ,AGENCY_LOCATION_TYPE,to_char(INTERNAL_LOCATION_ID) INTERNAL_LOCATION_ID_VAL,
PROVIDER_TYPE,NO_OF_SESSIONS,SESSION_LENGTH,MULTI_PHASE_SCHEDULING_FLAG, SCHEDULE_NOTES,SEAL_FLAG,ALLOW_DOUBLE_BOOK_FLAG,
(select count(1) from course_activities where parent_crs_acty_id  = CA.CRS_ACTY_ID) as ca_count,
	(select count(1) from course_activity_reviews where CRS_ACTY_ID = CA.CRS_ACTY_ID) as car_count,
	(select count(1) from course_schedule_rules where CRS_ACTY_ID = CA.CRS_ACTY_ID) as csr_count,
	(select count(1) from course_schedules where CRS_ACTY_ID = CA.CRS_ACTY_ID) as cs_count,
	(select count(1) from offender_course_attendances where CRS_ACTY_ID = CA.CRS_ACTY_ID) as oca_count,
	(select count(1) from offender_program_profiles where CRS_ACTY_ID = CA.CRS_ACTY_ID) as opp_count,
	(select count(1) from prg_pay_batch_off_crs where CRS_ACTY_ID = CA.CRS_ACTY_ID) as ppbofc_count,
	(select count(1) from programs_pay_compensation where CRS_ACTY_ID = CA.CRS_ACTY_ID) as ppc_count FROM COURSE_ACTIVITIES  CA
}
OCMSVACP_CRSACT_INSERT_COURSE_ACTIVITIES {
	INSERT INTO COURSE_ACTIVITIES(CRS_ACTY_ID,AGY_LOC_ID,CASELOAD_ID,DESCRIPTION,ACTIVE_FLAG,EXPIRY_DATE,CAPACITY,SCHEDULE_START_DATE,SCHEDULE_END_DATE,CASELOAD_TYPE,PROGRAM_ID,
PROVIDER_PARTY_ID,PROVIDER_PARTY_CODE,PROVIDER_PARTY_CLASS,COMMENT_TEXT,CODE,HOLIDAY_FLAG,COURSE_CLASS,COURSE_ACTIVITY_TYPE,CREATE_DATETIME,CREATE_USER_ID,
MODIFY_DATETIME,SERVICES_ADDRESS_ID,INTERNAL_LOCATION_ID,LIST_SEQ,AGENCY_LOCATION_TYPE,
PROVIDER_TYPE,NO_OF_SESSIONS,SESSION_LENGTH,MULTI_PHASE_SCHEDULING_FLAG, SCHEDULE_NOTES,SEAL_FLAG,ALLOW_DOUBLE_BOOK_FLAG)
 VALUES(:crsActyId, :agyLocId, :caseloadId, :description, :activeFlag, :expiryDate, :capacity, :scheduleStartDate, :scheduleEndDate, :caseloadType, :programId, 
 :providerPartyId, :providerPartyCode, :providerPartyClass, :commentText, :code, :holidayFlag, 'COURSE', :courseActivityType, CURRENT_TIMESTAMP, :createUserId,
 null, :servicesAddressId, :internalLocationId, :listSeq, :agencyLocationType, :providerType, :noOfSessions, :sessionLength, :multiPhaseSchedulingFlag,
 :scheduleNotes, :sealFlag, :allowDoubleBookFlag)
}

OCMSVACP_CRSACT_UPDATE_COURSE_ACTIVITIES {
	UPDATE COURSE_ACTIVITIES set AGY_LOC_ID=:agyLocId ,CASELOAD_ID=:caseloadId ,DESCRIPTION=:description,ACTIVE_FLAG=:activeFlag ,EXPIRY_DATE=:expiryDate ,CAPACITY=:capacity ,SCHEDULE_START_DATE=:scheduleStartDate ,SCHEDULE_END_DATE=:scheduleEndDate ,CASELOAD_TYPE=:caseloadType ,PROGRAM_ID=:programId ,
PROVIDER_PARTY_ID=:providerPartyId ,PROVIDER_PARTY_CODE=:providerPartyCode ,PROVIDER_PARTY_CLASS=:providerPartyClass ,COMMENT_TEXT=:commentText,CODE=:code,HOLIDAY_FLAG=:holidayFlag,COURSE_ACTIVITY_TYPE=:courseActivityType,
MODIFY_DATETIME=CURRENT_TIMESTAMP ,MODIFY_USER_ID=:modifyUserId,SERVICES_ADDRESS_ID=:servicesAddressId ,INTERNAL_LOCATION_ID=:internalLocationId ,LIST_SEQ=:listSeq ,AGENCY_LOCATION_TYPE=:agencyLocationType ,
PROVIDER_TYPE=:providerType ,NO_OF_SESSIONS=:noOfSessions ,SESSION_LENGTH=:sessionLength ,MULTI_PHASE_SCHEDULING_FLAG=:multiPhaseSchedulingFlag , SCHEDULE_NOTES=:scheduleNotes ,SEAL_FLAG=:sealFlag ,ALLOW_DOUBLE_BOOK_FLAG =:allowDoubleBookFlag where CRS_ACTY_ID=:crsActyId
}

OCMSVACP_CRSACT_DELETE_COURSE_ACTIVITIES { 
	DELETE FROM COURSE_ACTIVITIES where CRS_ACTY_ID = :crsActyId
}

OCMSVACP_VCRSPHS_FIND_V_COURSE_PHASE_OFFERINGS {
 	SELECT PROGRAM_ID,PROGRAM_PHASE_ID,COURSE_ID,COURSE_CASELOAD_TYPE,PROVIDER_PARTY_CLASS,PROVIDER_PARTY_ID,PROVIDER_PARTY_CODE,COURSE_PHASE_ID,OFFERING_FLAG,
PH_LIST_SEQ,PH_DESCRIPTION,PH_CAPACITY,PH_NO_OF_SESSIONS,PH_SESSION_LENGTH,PH_MODULE_FLAG,CP_CASELOAD_TYPE,CP_CASELOAD_TYPE_DESC,CP_INTERNAL_LOCATION_ID,
CP_INTERNAL_LOCATION_DESC,CP_SERVICES_ADDRESS_ID,CP_LIST_SEQ,CP_ACTIVE_FLAG,CP_EXPIRY_DATE,CP_START_DATE,CP_END_DATE,CP_NO_OF_SESSIONS,CP_SESSION_LENGTH,
CP_CAPACITY,CP_PLACEMENT_CORPORATE_ID,CP_COMMENT_TEXT,CP_COURSE_ACTIVITY_TYPE,CP_CHECK_SUM  FROM V_COURSE_PHASE_OFFERINGS  
}
OCMSVACP_VCRSPHS_UPDATE_V_COURSE_PHASE_OFFERINGS {
	UPDATE V_COURSE_PHASE_OFFERINGS set CP_CAPACITY=:cpCapacity ,CP_START_DATE=:cpStartDate ,CP_END_DATE=:cpEndDate ,
PROVIDER_PARTY_ID=:providerPartyId ,PROVIDER_PARTY_CODE=:providerPartyCode ,PROVIDER_PARTY_CLASS=:providerPartyClass ,CP_COURSE_ACTIVITY_TYPE=:cpCourseActivityType,
CP_SERVICES_ADDRESS_ID=:cpServicesAddressId, OFFERING_FLAG=:offeringFlag,CP_COMMENT_TEXT =:cpCommentText,
CP_NO_OF_SESSIONS=:cpNoOfSessions ,CP_SESSION_LENGTH=:cpSessionLength,MODIFY_DATETIME=CURRENT_TIMESTAMP ,MODIFY_USER_ID=:modifyUserId  where COURSE_ID=:courseId AND PROGRAM_PHASE_ID = :programPhaseId
}

OCMSVACP_ADDRESS_FIND {
 	SELECT PROGRAM_ID,PROGRAM_PHASE_ID,COURSE_ID,COURSE_CASELOAD_TYPE,PROVIDER_PARTY_CLASS,PROVIDER_PARTY_ID,PROVIDER_PARTY_CODE,COURSE_PHASE_ID,OFFERING_FLAG,
PH_LIST_SEQ,PH_DESCRIPTION,PH_CAPACITY,PH_NO_OF_SESSIONS,PH_SESSION_LENGTH,PH_MODULE_FLAG,CP_CASELOAD_TYPE,CP_CASELOAD_TYPE_DESC,CP_INTERNAL_LOCATION_ID,
CP_INTERNAL_LOCATION_DESC,CP_SERVICES_ADDRESS_ID,CP_LIST_SEQ,CP_ACTIVE_FLAG,CP_EXPIRY_DATE,CP_START_DATE,CP_END_DATE,CP_NO_OF_SESSIONS,CP_SESSION_LENGTH,
CP_CAPACITY,CP_PLACEMENT_CORPORATE_ID,CP_COMMENT_TEXT,CP_COURSE_ACTIVITY_TYPE,CP_CHECK_SUM  FROM V_COURSE_PHASE_OFFERINGS WHERE  
}

ADDRESS_FIND_BY_OWN{
SELECT * FROM V_COURSE_PHASE_OFFERINGS  WHERE COURSE_ID = :courseId AND PROGRAM_PHASE_ID = :programPhaseId
}
ADDRESS_FIND_BY_OWN_DIALOG{
SELECT
    FULL_ADDRESS,
    CORPORATE_NAME  AGENCY,
    SUITE_NUMBER,
    STREET_INFORMATION,
    AREA,
    ZIP_POSTAL_CODE   POSTAL_CODE,
    COUNTRY,
    ADDRESS_ID,
    OWNER_ID
FROM
    V_ADDRESSES A,  
    CORPORATES  B 
WHERE
    OWNER_CLASS = 'CORP'
    AND OWNER_ID = B.CORPORATE_ID
  AND OWNER_ID = :ProviderPartyId
    AND SERVICES_FLAG = 'Y'
    AND A.ACTIVE_FLAG = 'Y'
ORDER BY FULL_ADDRESS
}

SELECT_CRS_ACTY_ID_NEXTVAL  {
SELECT NEXTVAL('CRS_ACTY_ID')
}
SELECT_DESCRIPTION_FROM_AGENCY_INTERNAL_LOCATIONS {
SELECT DESCRIPTION FROM AGENCY_INTERNAL_LOCATIONS WHERE INTERNAL_LOCATION_ID = :internalLocationId
}
OCMSVACP_V_ADDRESS_DETAILS {
SELECT address_line1,
    SUITE_NUMBER,
    CITY_NAME,
    city_code ,
    PROV_STATE_DESC,
    prov_state_code ,
	ZIP_POSTAL_CODE ,
    COUNTRY,
    ADDRESS_ID,
    OWNER_ID
           FROM V_ADDRESSES
         WHERE ADDRESS_ID = :P_ADDRESS_ID

}
ON_DELETE_VALIDATION{
SELECT 1 FROM COURSE_ACTIVITIES  WHERE PARENT_CRS_ACTY_ID = :crsActyId
}
OCMSVACP_CONSTRAINT_VALIDATIONS_NEW {
select distinct  table_name from all_constraints ac where  constraint_name = :CONSTRAINTNAME
}

SELECT *
           FROM v_addresses
          WHERE owner_class = 'AGY'
            AND owner_code = p_provider_code
            AND primary_flag = 'Y';
}

OCMSVACP_DEFAULT_ADDR_W_AGY {

	SELECT *
           FROM v_addresses
          WHERE owner_class = 'AGY'
            AND owner_code = p_provider_code
            AND primary_flag = 'Y'

}

OCMSVACP_DEFAULT_ADDR_W_CORP {

	SELECT *
           FROM v_addresses
          WHERE owner_class = 'CORP'
            AND owner_id = p_provider_id
            AND services_flag = 'Y'

}

OCMSVACP_CONSTRAINT_VALIDATIONS_CRS_ACT_DELETE {
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

OCMSVACP_FIND_RG_ALL_ADDRESS_DEATAILS{
select 
address_line1,
CITY_NAME,
    PROV_STATE_DESC,
	ZIP_POSTAL_CODE ,
    country,
    address_id,
    suite_number ,
    city_code ,
	prov_state_code ,
	(select description  from AGENCY_LOCATIONS where agy_loc_id =vaa.agy_loc_id) as agency

from V_AGENCY_ADDRESSES vaa where agy_loc_id in (
select agy_loc_id from AGENCY_LOCATIONS where agy_loc_id in (select
	agy_loc_id 
from
	CASELOAD_AGENCY_LOCATIONS
	where caseload_id = :caseLoadId
) and deactivation_date is null) 
and address_type ='PROG'
}
