
OCMXPROG_FIND_RGPSPROVTYPE {
 	SELECT ref_code.description description, ref_code.code code, ref_code.list_seq list_seq FROM reference_codes ref_code WHERE domain = 'PS_PROV_TYPE' AND ( active_flag = 'Y') ORDER BY list_seq, description, code
}

OCMXPROG_FIND_RGPROVIDER_FN {
select DESCRIPTION , AGY_LOC_ID CODE from AGENCY_LOCATIONS AL where AL.AGY_LOC_ID in ( select CA.AGY_LOC_ID from CASELOAD_AGENCY_LOCATIONS CA where CA.CASELOAD_ID = :caseLoadId and CA.AGY_LOC_ID = AL.AGY_LOC_ID ) and AL.AGY_LOC_ID not in ('TRN' , 'OUT' ) order by LIST_SEQ , DESCRIPTION , AGY_LOC_ID

}

OCMXPROG_FIND_RGPROVIDER_TEAM {
SELECT DISTINCT
    t.description   description,
    t.team_id party_id,
    t.team_code  code
FROM 
    automation_teams           t,
    team_staff_members    tm,
    staff_members   sm
WHERE
    t.team_id = tm.team_id
    AND t.active_flag = 'Y'
    AND t.expiry_date IS NULL
    AND t.team_id IN (
        SELECT
            team_id
        FROM
            team_functions
        WHERE
            function_type = 'PWS'
    )
    AND tm.staff_id = sm.staff_id
    AND sm.user_id = :createUserId
ORDER BY
    t.description
}

OCMXPROG_FIND_RGPROGRAMTYPE {
 	SELECT PROGRAM_CODE, description, to_char(PROGRAM_ID) CODE, CASE WHEN ( SELECT COUNT(*) FROM program_services WHERE prg.program_category = 'PWS' AND ( prg.active_flag = 'Y' ) ) > 0 THEN 'Y' ELSE 'N' END active_flag FROM program_services prg ORDER BY list_seq, description, program_code
}

OCMXPROG_FIND_RGINTLOC {
 	SELECT c.description, c.internal_location_code, to_char(c.internal_location_id) code, C.ACTIVE_FLAG FROM int_loc_usage_locations a, internal_location_usages b, agency_internal_locations c WHERE a.internal_location_usage_id = b.internal_location_usage_id AND b.internal_location_usage = 'PROG' AND b.agy_loc_id =:agyLocID AND A.INTERNAL_LOCATION_ID = C.INTERNAL_LOCATION_ID  AND NOT EXISTS ( SELECT 1 FROM int_loc_usage_locations WHERE parent_usage_location_id = a.usage_location_id ) ORDER BY c.list_seq, c.description, c.internal_location_code
}

OCMXPROG_CRSACT_FIND_COURSE_ACTIVITIES {
 	SELECT  CRS_ACTY_ID,AGY_LOC_ID,CASELOAD_ID,DESCRIPTION,ACTIVE_FLAG,EXPIRY_DATE,CAPACITY,SCHEDULE_START_DATE,SCHEDULE_END_DATE,CASELOAD_TYPE,PROGRAM_ID,
PROVIDER_PARTY_ID,PROVIDER_PARTY_CODE,PROVIDER_PARTY_CLASS,COMMENT_TEXT,CODE,HOLIDAY_FLAG,COURSE_CLASS,COURSE_ACTIVITY_TYPE,CREATE_DATETIME,CREATE_USER_ID,
MODIFY_DATETIME,MODIFY_USER_ID,SERVICES_ADDRESS_ID,INTERNAL_LOCATION_ID,LIST_SEQ,AGENCY_LOCATION_TYPE, to_char(PROGRAM_ID) PROGRAM_ID_VAL, to_char(INTERNAL_LOCATION_ID) INTERNAL_LOCATION_ID_VAL,
PROVIDER_TYPE,NO_OF_SESSIONS,SESSION_LENGTH,MULTI_PHASE_SCHEDULING_FLAG, SCHEDULE_NOTES,SEAL_FLAG,ALLOW_DOUBLE_BOOK_FLAG,COMMENT_TEXT comment_temp,to_char(INTERNAL_LOCATION_ID) internal_location_id_val_temp FROM COURSE_ACTIVITIES
}
OCMXPROG_CRSACT_INSERT_COURSE_ACTIVITIES {
insert into COURSE_ACTIVITIES(CRS_ACTY_ID, AGY_LOC_ID, CASELOAD_ID, DESCRIPTION, ACTIVE_FLAG, EXPIRY_DATE, CAPACITY, SCHEDULE_START_DATE, SCHEDULE_END_DATE, CASELOAD_TYPE, PROGRAM_ID, PROVIDER_PARTY_ID, PROVIDER_PARTY_CODE, PROVIDER_PARTY_CLASS, COMMENT_TEXT, CODE, HOLIDAY_FLAG, COURSE_CLASS, COURSE_ACTIVITY_TYPE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, SERVICES_ADDRESS_ID, INTERNAL_LOCATION_ID, LIST_SEQ, AGENCY_LOCATION_TYPE, PROVIDER_TYPE, NO_OF_SESSIONS, SESSION_LENGTH, MULTI_PHASE_SCHEDULING_FLAG, SCHEDULE_NOTES, SEAL_FLAG, ALLOW_DOUBLE_BOOK_FLAG) values(:crsActyId, :agyLocId, :caseloadId, :description, :activeFlag, :expiryDate, :capacity, :scheduleStartDate, :scheduleEndDate, :caseloadType, :programId, :providerPartyId, :providerPartyCode, :providerPartyClass, :commentText, :code, :holidayFlag, :courseClass, :courseActivityType, current_timestamp, :createUserId, null, :servicesAddressId, :internalLocationId , :listSeq, :agencyLocationType, :providerType, :noOfSessions, :sessionLength, :multiPhaseSchedulingFlag, :scheduleNotes, :sealFlag, :allowDoubleBookFlag )
}

OCMXPROG_CRSACT_UPDATE_COURSE_ACTIVITIES {
update COURSE_ACTIVITIES set DESCRIPTION =:description, ACTIVE_FLAG =:activeFlag, EXPIRY_DATE =:expiryDate, CAPACITY =:capacity, SCHEDULE_START_DATE =:scheduleStartDate, SCHEDULE_END_DATE =:scheduleEndDate, COMMENT_TEXT =:commentText, CODE =:code, HOLIDAY_FLAG =:holidayFlag, COURSE_CLASS =:courseClass, COURSE_ACTIVITY_TYPE =:courseActivityType, MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID =:modifyUserId, SERVICES_ADDRESS_ID =:servicesAddressId, INTERNAL_LOCATION_ID =:internalLocationId, LIST_SEQ =:listSeq, AGENCY_LOCATION_TYPE =:agencyLocationType, PROVIDER_TYPE =:providerType, NO_OF_SESSIONS =:noOfSessions, SESSION_LENGTH =:sessionLength, MULTI_PHASE_SCHEDULING_FLAG =:multiPhaseSchedulingFlag, SCHEDULE_NOTES =:scheduleNotes, SEAL_FLAG =:sealFlag, ALLOW_DOUBLE_BOOK_FLAG =:allowDoubleBookFlag where CRS_ACTY_ID =:crsActyId 
}


OCMXPROG_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OCMXPROG_GET_ADDRESS_ID_AGY {
	SELECT ADDRESS_ID FROM V_ADDRESSES WHERE OWNER_CLASS = 'AGY' AND OWNER_CODE = :providerPartyCode AND PRIMARY_FLAG = 'Y' AND ACTIVE_FLAG = 'Y'
}

OCMXPROG_GET_ADDRESS_ID_TEAM {
select
	address_id
from
	v_addresses
where
	owner_class = 'AGY'
	and owner_code in (
	select
		tal.agy_loc_id
	from
		automation_teams at2 ,
		TEAM_FUNCTIONS tf,
		team_staff_members tsm,
		team_agency_loc tal
	where
		at2.team_id = :providerId
		and tf.team_id = at2.team_id
		and tf.function_type = 'PWS'
		and tsm.team_id = at2.team_id
		and tsm.staff_id in (
		select
			STAFF_ID
		from
			STAFF_MEMBERS
		where
			USER_ID = :createUserId)
		and tal.team_id = at2.team_id)
	and PRIMARY_FLAG = 'Y'
	and ACTIVE_FLAG = 'Y'
}

OCMXPROG_GET_ADDRESS_ID_CORP {
	SELECT address_id FROM v_addresses WHERE owner_class = 'CORP' AND owner_id IN ( SELECT corporate_id FROM CORPORATES WHERE CORPORATE_NAME = :providerPartyCode AND ACTIVE_FLAG = 'Y') AND SERVICES_FLAG = 'Y' AND PRIMARY_FLAG = 'Y' AND ACTIVE_FLAG = 'Y'
}
OCMXPROG_GETCRS_ACTYID {

SELECT NEXTVAL('crs_acty_id') FROM DUAL
}
OCMSVACP_CHECK_CODE_EXISTS{
SELECT COUNT (ca.crs_acty_id) FROM course_activities ca, program_services ps WHERE (ca.provider_party_id = :providerPartyId AND :providerPartyId IS NOT NULL) AND ca.code = :code AND ca.program_id = ps.program_id AND (ps.program_category IS NULL OR ps.program_category = 'PWS')
}
OCMSVACP_CHECK_CODE_EXISTS_PROVIDERCODE{
SELECT COUNT (ca.crs_acty_id) FROM course_activities ca, program_services ps WHERE ( ca.provider_party_code = :providerPartyCode AND :providerPartyCode IS NOT NULL ) AND ca.code = :code AND ca.program_id = ps.program_id AND (ps.program_category IS NULL OR ps.program_category = 'PWS')
}

OCMXPROG_FIND_RGPROVIDER_TEAM_EXTERNAL {
select
	TO_CHAR(C.CORPORATE_ID) CODE,
	C.CORPORATE_NAME DESCRIPTION
from
	CORPORATES C ,
	CORPORATE_TYPES CT
where
	C.CORPORATE_ID = CT.CORPORATE_ID
	and CT.CORPORATE_TYPE = 'PWS'
	and C.ACTIVE_FLAG = 'Y'
order by
	CORPORATE_NAME
}