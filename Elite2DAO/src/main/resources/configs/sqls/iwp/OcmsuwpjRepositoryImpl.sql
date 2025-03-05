
OCMSUWPJ_FIND_RGTEAM {
 	SELECT
    t.description   description,
    t.team_code     code,
    t.team_id       
FROM
    automation_teams t
WHERE
    t.active_flag = 'Y'
    AND exists     ( SELECT
    tm.team_id
FROM
    team_staff_members    tm,
    staff_members   sm
WHERE
    tm.active_flag = 'Y'
    AND tm.staff_id = sm.staff_id
    AND sm.user_id = :createUserId
        AND tm.team_id = t.team_id )
AND t.team_id IN (
                     SELECT
                         team_id
                     FROM
                         team_functions
                     WHERE
                         function_type = 'UNPAID_WORK'
                 )
                 ORDER BY
                    
                     t.description,
                     t.team_code 
}

OCMSUWPJ_FIND_RGBENEFICIARYTYPE {
SELECT
    description,
    code
FROM
    reference_codes
WHERE
    domain = 'PS_BENEF'
    AND ( ( active_flag = 'Y'
            AND expired_date IS NULL )  )
ORDER BY
    list_seq,
    description

}

OCMSUWPJ_FIND_RGPLACEMENTNAME {
 	SELECT   CORPORATE_NAME DESCRIPTION           ,CORPORATE_ID  FROM CORPORATES  WHERE ACTIVE_FLAG = 'Y' ORDER BY CORPORATE_NAME
}

OCMSUWPJ_FIND_RGPLACEMENTADDRESSES {
 	SELECT  *       FROM V_CORPORATE_ADDRESSES WHERE CORPORATE_ID = :PLACEMENTCORPORATEID AND ACTIVE_FLAG = 'Y' ORDER BY HOUSE ,STREET
}

OCMSUWPJ_COURSEACT_FIND_COURSE_ACTIVITIES {
select
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
	session_length,
	multi_phase_scheduling_flag,
	schedule_notes,
	seal_flag,
	allow_double_book_flag
from
	course_activities
where
	course_activity_type = 'WS'
	and provider_party_class = 'TEAM'
	and provider_party_id =  :providerPartyId
order by
	active_flag desc,
	case
		when coalesce(schedule_end_date, current_timestamp + interval '1 day') > current_timestamp then 2
		else 1
	end desc,
	schedule_start_date desc
}

OCMSUWPJ_COURSEACT_INSERT_COURSE_ACTIVITIES {
insert into COURSE_ACTIVITIES(CRS_ACTY_ID, CASELOAD_ID, AGY_LOC_ID, DESCRIPTION, CAPACITY, ACTIVE_FLAG, EXPIRY_DATE, SCHEDULE_START_DATE, 
SCHEDULE_END_DATE, CASELOAD_TYPE, SERVICES_ADDRESS_ID, PROGRAM_ID, PARENT_CRS_ACTY_ID, INTERNAL_LOCATION_ID, PROVIDER_PARTY_CLASS, 
PROVIDER_PARTY_ID, PROVIDER_PARTY_CODE, BENEFICIARY_NAME, BENEFICIARY_CONTACT, LIST_SEQ, PLACEMENT_CORPORATE_ID,
COMMENT_TEXT, AGENCY_LOCATION_TYPE, PROVIDER_TYPE, BENEFICIARY_TYPE, PLACEMENT_TEXT, CODE, HOLIDAY_FLAG, COURSE_CLASS,
COURSE_ACTIVITY_TYPE, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, IEP_LEVEL, NO_OF_SESSIONS, SESSION_LENGTH, 
MULTI_PHASE_SCHEDULING_FLAG, SCHEDULE_NOTES, SEAL_FLAG, ALLOW_DOUBLE_BOOK_FLAG) 
values(:crsActyId, :caseloadId, :agyLocId, :description, :capacity, :activeFlag, :expiryDate, :scheduleStartDate, :scheduleEndDate,
:caseloadType, :servicesAddressId, :programId, :parentCrsActyId, :internaLocationId, :providerPartyClass,
:providerPartyId, :providerPartyCode, :beneficiaryName, :beneficiaryContact, :listSeq, :placementCorporateId,
:commentText, :agencyLocationType, :providerType, :beneficiaryType, :placementText, :code, :holidayFlag, :courseClass, 
:courseActivityType, current_timestamp, :createUserId, NULL, :iepLevel, :noOfSessions, :sessionLength, :multiPhaseSchedulingFlag, :scheduleNotes, :sealFlag, :allowDoubleBookFlag) 
}

OCMSUWPJ_COURSEACT_UPDATE_COURSE_ACTIVITIES {
update COURSE_ACTIVITIES set CASELOAD_ID =:caseloadId, AGY_LOC_ID =:agyLocId, DESCRIPTION =:description, CAPACITY =:capacity,
ACTIVE_FLAG =:activeFlag, EXPIRY_DATE =:expiryDate, SCHEDULE_START_DATE =:scheduleStartDate, SCHEDULE_END_DATE =:scheduleEndDate,
CASELOAD_TYPE =:caseloadType, SERVICES_ADDRESS_ID =:servicesAddressId, PROGRAM_ID =:programId, PARENT_CRS_ACTY_ID =:parentCrsActyId,
INTERNAL_LOCATION_ID =:internaLocationId, PROVIDER_PARTY_CLASS =:providerPartyClass, PROVIDER_PARTY_ID =:providerPartyId,
PROVIDER_PARTY_CODE =:providerPartyCode, BENEFICIARY_NAME =:beneficiaryName, BENEFICIARY_CONTACT =:beneficiaryContact, LIST_SEQ =:listSeq, 
PLACEMENT_CORPORATE_ID =:placementCorporateId, COMMENT_TEXT =:commentText, AGENCY_LOCATION_TYPE =:agencyLocationType,
PROVIDER_TYPE =:providerType, BENEFICIARY_TYPE =:beneficiaryType, PLACEMENT_TEXT =:placementText, CODE =:code, HOLIDAY_FLAG =:holidayFlag,
COURSE_CLASS =:courseClass, COURSE_ACTIVITY_TYPE =:courseActivityType, MODIFY_DATETIME =current_timestamp,
MODIFY_USER_ID =:modifyUserId, IEP_LEVEL =:iepLevel, NO_OF_SESSIONS =:noOfSessions, SESSION_LENGTH =:sessionLength,
MULTI_PHASE_SCHEDULING_FLAG =:multiPhaseSchedulingFlag, SCHEDULE_NOTES =:scheduleNotes, SEAL_FLAG =:sealFlag,
ALLOW_DOUBLE_BOOK_FLAG =:allowDoubleBookFlag where CRS_ACTY_ID =:crsActyId 
}

OCMSUWPJ_COURSEACT_DELETE_COURSE_ACTIVITIES { 
	DELETE FROM COURSE_ACTIVITIES where CRS_ACTY_ID=:crsActyId
}
OCMSUWPJ_GET_CODE_UNIQUE_CNT_CUR{
 SELECT  COUNT(*)
            FROM    COURSE_ACTIVITIES
            WHERE   provider_party_id = :P_TEAM_ID  
            AND     code = :P_CODE
            AND     course_activity_type = 'WS'
           
}
            
OCMSUWPJ_GET_CRS_ACTY_ID_CUR{
SELECT NEXTVAL('crs_acty_id')
}

OCMSUWPJ_GET_PROGRAM_ID{
 SELECT program_id
           FROM PROGRAM_SERVICES
          WHERE program_category = 'UW'
            AND active_flag = 'Y'limit 1
}

OCMSUWPJ_FIND_PLACEMENT {
SELECT
   *
FROM
    course_activities
WHERE 
course_activity_type = 'WS' and provider_party_class = 'TEAM' and PROVIDER_PARTY_ID =:p_team_id and CRS_ACTY_ID =:crsActyId
ORDER BY active_flag desc, case  when coalesce(schedule_end_date,current_timestamp+ interval '1day') > current_timestamp  then      2  else      1  end desc, schedule_start_date desc
}




OCMSUWPJ_CONSTRAINT_VALIDATIONS {
 select
 tc.table_name
from
 information_schema.table_constraints as tc
join information_schema.key_column_usage as kcu on
 tc.constraint_name = kcu.constraint_name
 and tc.table_schema = kcu.table_schema
join information_schema.constraint_column_usage as ccu on
 ccu.constraint_name = tc.constraint_name
 and ccu.table_schema = tc.table_schema
where
 tc.constraint_type = 'FOREIGN KEY'
 and upper(ccu.table_name)= 'COURSE_ACTIVITIES'
 and upper(tc.CONSTRAINT_NAME)= :CONSTRAINTNAME
 and upper(tc.constraint_schema)= 'OMS_OWNER'

}

OCMSUWPJ_FIND_RGPROVIDER_FN {
SELECT DISTINCT
    t.description   description,
    t.team_id party_id,
    t.team_code     code
    
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
            function_type = 'UNPAID_WORK'
    )
    AND tm.staff_id = sm.staff_id
    AND sm.user_id = :createUserId
ORDER BY
    t.description;
}

OCMSUWPJ_FIND_RGINTLOC {
 	SELECT c.description, c.internal_location_code, to_char(c.internal_location_id) code, C.ACTIVE_FLAG FROM int_loc_usage_locations a, internal_location_usages b, agency_internal_locations c WHERE a.internal_location_usage_id = b.internal_location_usage_id AND b.internal_location_usage = 'PROG' AND b.agy_loc_id =:agyLocID AND A.INTERNAL_LOCATION_ID = C.INTERNAL_LOCATION_ID  AND NOT EXISTS ( SELECT 1 FROM int_loc_usage_locations WHERE parent_usage_location_id = a.usage_location_id ) ORDER BY c.list_seq, c.description, c.internal_location_code
}

OCMSUWPJ_FIND_RGPROGRAMTYPE {
 	SELECT PROGRAM_CODE, description, to_char(PROGRAM_ID) CODE, CASE WHEN ( SELECT COUNT(*) FROM program_services WHERE prg.program_category = 'UNPAID_WORK' AND ( prg.active_flag = 'Y' ) ) > 0 THEN 'Y' ELSE 'N' END active_flag FROM program_services prg ORDER BY list_seq, description, program_code
}
OCMSUWPJ_FIND_RGPROVIDER_TEAM_EXTERNAL_QUERY{
select
	TO_CHAR(C.CORPORATE_ID) CODE,
	C.CORPORATE_NAME DESCRIPTION
from
	CORPORATES C ,
	CORPORATE_TYPES CT
where
	C.CORPORATE_ID = CT.CORPORATE_ID
	and CT.CORPORATE_TYPE = 'UNPAID_WORK'
	and C.ACTIVE_FLAG = 'Y'
order by
	CORPORATE_NAME
}
OCMSUWPJ_FIND_RGPROVIDER_TEAM_INTERNAL_QUERY{
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
            function_type = 'UNPAID_WORK'
    )
    AND tm.staff_id = sm.staff_id
    AND sm.user_id = :createUserId
ORDER BY
    t.description
}
OCMSUWPJ_GET_PLACEMENT_LOCATION_BY_CASELOAD_ID
{
select AGY_LOC.AGY_LOC_ID as CODE, AGY_LOC.DESCRIPTION as DESCRIPTION from AGENCY_LOCATIONS AGY_LOC where agy_loc_id in( select agy_loc_id from CASELOAD_AGENCY_LOCATIONS where caseload_id =:caseload)
}