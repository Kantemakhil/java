
OIMWORKR_FIND_RGPROJECTTYPE {
SELECT DESCRIPTION, PROGRAM_CODE, PROGRAM_ID::text CODE FROM PROGRAM_SERVICES WHERE PROGRAM_CATEGORY = 'WR' AND ((ACTIVE_FLAG = 'Y' AND EXPIRY_DATE IS NULL)) ORDER BY LIST_SEQ, DESCRIPTION, PROGRAM_CODE
}

OIMWORKR_FIND_RGPROVIDER {
select corporate_name description, corporate_id::text CODE From corporates Where corporate_id in ( select corporate_id from corporate_types where corporate_type = 'WR') and ((active_flag = 'Y' and expiry_date is null)) order by corporate_name asc
	

}

OIMWORKR_FIND_RGPROJECTLOCATION {
 	SELECT FULL_ADDRESS DESCRIPTION , SUITE_NUMBER , STREET_INFORMATION , CITY_NAME , PROV_STATE_DESC , ZIP_POSTAL_CODE ,   ADDRESS_ID::text CODE     FROM V_ADDRESSES VA 	WHERE OWNER_CLASS = 'CORP'  AND OWNER_ID = :providerPartyId    AND SERVICES_FLAG = 'Y'    AND VA.ACTIVE_FLAG = 'Y'
}

OIMWORKR_FIND_RGAGENCYLOCATION {
select AL.DESCRIPTION , AL.AGY_LOC_ID CODE from AGENCY_LOCATIONS AL where AGENCY_LOCATION_TYPE = 'INST' and ( ( ACTIVE_FLAG = 'Y' and DEACTIVATION_DATE is null )) and AL.AGY_LOC_ID in ( select CA.AGY_LOC_ID from CASELOAD_AGENCY_LOCATIONS CA where CA.CASELOAD_ID = :CASELOADID ) and AL.AGY_LOC_ID not in ('TRN' , 'OUT' ) order by LIST_SEQ , DESCRIPTION , AGY_LOC_ID

}

OIMWORKR_CRSACTY_FIND_COURSE_ACTIVITIES {
SELECT * FROM COURSE_ACTIVITIES WHERE COURSE_ACTIVITY_TYPE = 'WR' AND PROVIDER_PARTY_CLASS = 'CORP'AND CASELOAD_ID = :caseloadId ORDER BY ACTIVE_FLAG DESC, CODE ASC
}

OIMWORKR_CRSACTY_INSERT_COURSE_ACTIVITIES {
	INSERT INTO COURSE_ACTIVITIES(CRS_ACTY_ID ,CASELOAD_ID ,AGY_LOC_ID ,DESCRIPTION ,CAPACITY ,ACTIVE_FLAG ,EXPIRY_DATE ,SCHEDULE_START_DATE ,SCHEDULE_END_DATE ,CASELOAD_TYPE ,SERVICES_ADDRESS_ID ,PROGRAM_ID ,PARENT_CRS_ACTY_ID ,INTERNAL_LOCATION_ID ,PROVIDER_PARTY_CLASS ,PROVIDER_PARTY_ID ,PROVIDER_PARTY_CODE ,BENEFICIARY_NAME ,BENEFICIARY_CONTACT ,LIST_SEQ ,PLACEMENT_CORPORATE_ID ,COMMENT_TEXT ,AGENCY_LOCATION_TYPE ,PROVIDER_TYPE ,BENEFICIARY_TYPE ,PLACEMENT_TEXT ,CODE ,HOLIDAY_FLAG ,COURSE_CLASS ,COURSE_ACTIVITY_TYPE ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,IEP_LEVEL ,NO_OF_SESSIONS ,SESSION_LENGTH ,MULTI_PHASE_SCHEDULING_FLAG ,SCHEDULE_NOTES ,SEAL_FLAG ,ALLOW_DOUBLE_BOOK_FLAG ) 
	VALUES(:crsActyId ,:caseloadId ,:agyLocId ,:description ,:capacity ,:activeFlag ,:expiryDate ,:scheduleStartDate ,:scheduleEndDate ,:caseloadType ,:servicesAddressId ,:programId ,:parentCrsActyId ,:internalLocationId ,:providerPartyClass ,:providerPartyId ,:providerPartyCode ,:beneficiaryName ,:beneficiaryContact ,:listSeq ,:placementCorporateId ,:commentText ,:agencyLocationType ,:providerType ,:beneficiaryType ,:placementText ,:code ,:holidayFlag ,:courseClass ,:courseActivityType ,CURRENT_TIMESTAMP,:createUserId ,null ,null ,:iepLevel ,:noOfSessions ,:sessionLength ,:multiPhaseSchedulingFlag ,:scheduleNotes ,:sealFlag ,:allowDoubleBookFlag )
}

OIMWORKR_CRSACTY_UPDATE_COURSE_ACTIVITIES {
	UPDATE COURSE_ACTIVITIES set DESCRIPTION  = :description ,CAPACITY  = :capacity ,ACTIVE_FLAG  = :activeFlag ,EXPIRY_DATE  = :expiryDate ,SCHEDULE_START_DATE  = :scheduleStartDate ,SCHEDULE_END_DATE  = :scheduleEndDate ,PROGRAM_ID  = :programId ,PROVIDER_PARTY_ID  = :providerPartyId ,PROVIDER_TYPE  = :providerType ,CODE  = :code ,MODIFY_DATETIME  = current_timestamp  ,MODIFY_USER_ID  = :modifyUserId ,SERVICES_ADDRESS_ID =:servicesAddressId where CAPACITY = :crsActyId
}

OIMWORKR_CRSACTY_DELETE_COURSE_ACTIVITIES { 
	DELETE FROM COURSE_ACTIVITIES/* where */
}

OIMWORKR_CRSACTY_UPDATE_COURSE_ACTIVITIES_TEMP{
	UPDATE COURSE_ACTIVITIES set SCHEDULE_START_DATE =:scheduleStartDate  , SCHEDULE_END_DATE =:scheduleEndDate ,MODIFY_DATETIME  = current_timestamp ,MODIFY_USER_ID  = :modifyUserId ,
	CAPACITY  = :capacity ,ACTIVE_FLAG  = :activeFlag ,EXPIRY_DATE  = :expiryDate ,SERVICES_ADDRESS_ID =:servicesAddressId where crs_acty_id = :crsActyId
}


OIMWORKR_CRS_ACTY_POSTQUERY_ {
	SELECT DESCRIPTION FROM PROGRAM_SERVICES WHERE PROGRAM_ID = :PROGRAMID
}

OIMWORKR_CRS_ACTY_POSTQUERY_ {
	SELECT CORPORATE_NAME FROM CORPORATES WHERE CORPORATE_ID = :PROVIDERPARTYID
}

OIMWORKR_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}

OIMWORKR_CALL_OCUMPVAV_ {
	SELECT PROGRAM_CATEGORY FROM PROGRAM_SERVICES WHERE PROGRAM_ID = :PROGRAMID
}

OIMWORKR_GET_DEFAULT_LOCATION_ {
	SELECT COUNT(*) FROM V_ADDRESSES VA WHERE OWNER_CLASS = 'CORP' AND OWNER_ID = (SELECT CORPORATE_ID FROM CORPORATES WHERE CORPORATE_NAME = :NBTPROVIDER) AND SERVICES_FLAG = 'Y' AND VA.ACTIVE_FLAG = 'Y'
}

OIMWORKR_GET_DEFAULT_LOCATION_ {
	SELECT ADDRESS_ID FROM V_ADDRESSES VA WHERE OWNER_CLASS = 'CORP' AND OWNER_ID = (SELECT CORPORATE_ID FROM CORPORATES WHERE CORPORATE_NAME = :NBTPROVIDER) AND SERVICES_FLAG = 'Y' AND VA.ACTIVE_FLAG = 'Y'
}

OIMWORKR_DELETE_CRS_ACTY_ {
	SELECT '1' FROM COURSE_SCHEDULE_RULES WHERE CRS_ACTY_ID = :CRSACTYID UNION SELECT '1' FROM COURSE_ACTIVITY_PARTIES WHERE CRS_ACTY_ID = :CRSACTYID UNION SELECT '1' FROM COURSE_ACTIVITY_PROFILES WHERE CRS_ACTY_ID = :CRSACTYID UNION SELECT '1' FROM COURSE_ACTIVITY_REVIEWS WHERE CRS_ACTY_ID = :CRSACTYID UNION SELECT '1' FROM COURSE_ACTIVITY_AREAS WHERE CRS_ACTY_ID = :CRSACTYID UNION SELECT '1' FROM COURSE_SCHEDULES WHERE CRS_ACTY_ID = :CRSACTYID UNION SELECT '1' FROM OFFENDER_COURSE_ATTENDANCES WHERE CRS_ACTY_ID = :CRSACTYID UNION SELECT '1' FROM OFFENDER_PROGRAM_PROFILES WHERE CRS_ACTY_ID = :CRSACTYID
}

OIMWORKR_CHECK_DUPLICATE_CODES_ {
	SELECT COUNT(*) FROM COURSE_ACTIVITIES WHERE COURSE_ACTIVITY_TYPE = 'WR' -- AND PROVIDER_PARTY_CLASS = 'CORP' -- AND CASELOAD_TYPE = :CASELOADTYPE AND UPPER(CODE) = UPPER(:CODE)
}


OIMWORKR_COURSE_ACTIVITY_DELETE_EXIST{
SELECT '1' FROM course_schedule_rules WHERE crs_acty_id = :crsActy UNION SELECT '1' FROM course_activity_parties WHERE crs_acty_id = :crsActy UNION SELECT '1' FROM course_activity_profiles WHERE crs_acty_id = :crsActy UNION SELECT '1' FROM course_activity_reviews WHERE crs_acty_id = :crsActy UNION SELECT '1' FROM course_activity_areas WHERE crs_acty_id = :crsActy UNION SELECT '1' FROM course_schedules WHERE crs_acty_id = :crsActy UNION SELECT '1' FROM offender_course_attendances WHERE crs_acty_id = :crsActy UNION SELECT '1' FROM offender_program_profiles WHERE crs_acty_id = :crsActy }

OIMWORKR_NBT_SUIT_SELECT{
select full_address, suite_number, street_information, city_name, prov_state_desc, zip_postal_code, address_id from v_addresses va where owner_class = 'CORP' and owner_id = :providerPartyId and services_flag = 'Y' and va.active_flag = 'Y' }

OIMWORKR_CRS_ACTY_ID{
SELECT NEXTVAL('CRS_ACTY_ID') FROM DUAL
}