
OCDEDEMP_FIND_EDUSCHEDRG {
	SELECT
  REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE
FROM REFERENCE_CODES REF_CODE
WHERE
  DOMAIN = 'EDU_SCHED' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE

}

OCDEDEMP_FIND_PAYPERIODRG {
	SELECT
  REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE
FROM REFERENCE_CODES REF_CODE
WHERE
  DOMAIN = 'PAY_PERIOD' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE

}

OCDEDEMP_FIND_OCCUPATIONRG {
	SELECT
  REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE
FROM REFERENCE_CODES REF_CODE
WHERE
  DOMAIN = 'OCCUPATION' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE

}

OCDEDEMP_FIND_SCHEDULETYPERG {
	SELECT
  REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE
FROM REFERENCE_CODES REF_CODE
WHERE
  DOMAIN = 'EMP_SCH_TYPE' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE

}

OCDEDEMP_FIND_EMPLOYSTSRG {
	SELECT
  REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE
FROM REFERENCE_CODES REF_CODE
WHERE
  DOMAIN = 'EMPLOY_STS' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE

}

OCDEDEMP_FIND_STUDYAREARG {
	SELECT
  REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE
FROM REFERENCE_CODES REF_CODE
WHERE
  DOMAIN = 'STUDY_AREA' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE

}

OCDEDEMP_FIND_EDULEVELRG {
	SELECT
  REF_CODE.DESCRIPTION DESCRIPTION, REF_CODE.CODE CODE
FROM REFERENCE_CODES REF_CODE
WHERE
  DOMAIN = 'EDU_LEVEL' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, DESCRIPTION, CODE

}

OCDEDEMP_OFFEDUCATIONS_FIND_OFFENDER_EDUCATIONS {
	SELECT OFFENDER_BOOK_ID ,EDUCATION_SEQ ,EDUCATION_TYPE ,STUDY_AREA_CODE ,EDUCATION_LEVEL_CODE ,NUMBER_OF_YEARS ,GRADUATION_YEAR ,START_DATE ,COMMENT_TEXT ,SCHOOL_NAME ,SPECIAL_EDUCATION_FLAG ,CASELOAD_TYPE ,MODIFY_USER_ID ,MODIFY_DATETIME ,ROOT_OFFENDER_ID ,END_DATE ,PARTIAL_END_DATE_FLAG ,PARTIAL_START_DATE_FLAG ,STUDENT_ID ,AGY_LOC_ID ,SCHOOL_CODE ,CREATE_DATETIME ,CREATE_USER_ID ,EDUCATION_SCHEDULE ,SEAL_FLAG   FROM OFFENDER_EDUCATIONS   where OFFENDER_BOOK_ID = :offenderBookId ORDER BY START_DATE 
}

OCDEDEMP_OFFEDUCATIONS_INSERT_OFFENDER_EDUCATIONS {
 insert into OFFENDER_EDUCATIONS(OFFENDER_BOOK_ID , EDUCATION_SEQ , EDUCATION_TYPE , STUDY_AREA_CODE , EDUCATION_LEVEL_CODE , NUMBER_OF_YEARS , GRADUATION_YEAR , START_DATE , COMMENT_TEXT , SCHOOL_NAME , SPECIAL_EDUCATION_FLAG , CASELOAD_TYPE , MODIFY_DATETIME , ROOT_OFFENDER_ID , END_DATE , PARTIAL_END_DATE_FLAG , PARTIAL_START_DATE_FLAG , STUDENT_ID , AGY_LOC_ID , SCHOOL_CODE , CREATE_DATETIME , CREATE_USER_ID , EDUCATION_SCHEDULE , SEAL_FLAG ) values(:offenderBookId , :educationSeq , :educationType , :studyAreaCode , :educationLevelCode , :numberOfYears , :graduationYear , :startDate , :commentText , :schoolName , :specialEducationFlag , :caseloadType, NULL , :rootOffenderId , :endDate , :partialEndDateFlag , :partialStartDateFlag , :studentId , :agyLocId , :schoolCode , current_timestamp , :createUserId , :educationSchedule , :sealFlag ) 
} 

OCDEDEMP_OFFEDUCATIONS_UPDATE_OFFENDER_EDUCATIONS {
	UPDATE OFFENDER_EDUCATIONS set OFFENDER_BOOK_ID  = :offenderBookId ,EDUCATION_SEQ  = :educationSeq ,EDUCATION_TYPE  = :educationType ,STUDY_AREA_CODE  = :studyAreaCode ,EDUCATION_LEVEL_CODE  = :educationLevelCode ,NUMBER_OF_YEARS  = :numberOfYears ,GRADUATION_YEAR  = :graduationYear ,START_DATE  = :startDate ,COMMENT_TEXT  = :commentText ,SCHOOL_NAME  = :schoolName ,SPECIAL_EDUCATION_FLAG  = :specialEducationFlag ,CASELOAD_TYPE  = :caseloadType ,MODIFY_USER_ID  = :modifyUserId ,MODIFY_DATETIME  = current_timestamp ,ROOT_OFFENDER_ID  = :rootOffenderId ,END_DATE  = :endDate ,PARTIAL_END_DATE_FLAG  = :partialEndDateFlag ,PARTIAL_START_DATE_FLAG  = :partialStartDateFlag ,STUDENT_ID  = :studentId ,AGY_LOC_ID  = :agyLocId ,SCHOOL_CODE  = :schoolCode ,CREATE_DATETIME  = current_timestamp ,CREATE_USER_ID  = :createUserId ,EDUCATION_SCHEDULE  = :educationSchedule ,SEAL_FLAG  = :sealFlag where OFFENDER_BOOK_ID = :offenderBookId  and EDUCATION_SEQ = :educationSeq
} 

OCDEDEMP_OFFEDUCATIONS_DELETE_OFFENDER_EDUCATIONS { 
	DELETE FROM OFFENDER_EDUCATIONS where OFFENDER_BOOK_ID = :offenderBookId and EDUCATION_SEQ = :educationSeq
} 

OCDEDEMP_VOFFEDUADDR_FIND_V_OFFENDER_EDUCATION_ADDRESSES {
	SELECT
  ADDRESS_ID, ADDRESS_TYPE, ADDRESS_TYPE_DESC, OFFENDER_BOOK_ID, EDUCATION_SEQ,
  START_DATE, END_DATE, ACTIVE_FLAG, HOUSE, STREET, AREA, COUNTRY, SUITE_NUMBER,
  STREET_NUMBER, STREET_DIRECTION, CITY_CODE, PROV_STATE_CODE, ZIP_POSTAL_CODE,
  COUNTRY_CODE, COMMENT_TEXT, PRIMARY_FLAG, MAIL_FLAG, VALIDATED_FLAG,
  STREET_INFORMATION, CITY_NAME, PROV_STATE_DESC,IS_ADDRESS_VALID,ADDRESS_LINE1 
FROM V_OFFENDER_EDUCATION_ADDRESSES
where
  OFFENDER_BOOK_ID=:offenderBookId AND
  EDUCATION_SEQ=:educationSeq
ORDER BY
  PRIMARY_FLAG, ACTIVE_FLAG

}

OCDEDEMP_OFFEMPLOYMENTS_FIND_OFFENDER_EMPLOYMENTS {
	SELECT OFFENDER_BOOK_ID ,EMPLOY_SEQ ,EMPLOYMENT_DATE ,EMPLOYMENT_POST_CODE ,EMPLOYMENT_TYPE ,TERMINATION_DATE ,EMPLOYER_NAME ,SUPERVISOR_NAME ,POSITION ,TERMINATION_REASON_TEXT ,WAGE ,WAGE_PERIOD_CODE ,OCCUPATIONS_CODE ,COMMENT_TEXT ,CASELOAD_TYPE ,ROOT_OFFENDER_ID ,CONTACT_TYPE ,CONTACT_NUMBER ,SCHEDULE_TYPE ,SCHEDULE_HOURS ,HOURS_WEEK ,PARTIAL_EMPLOYMENT_DATE_FLAG ,PARTIAL_TERMINATION_DATE_FLAG ,CHECK_BOX_1 ,CHECK_BOX_2 ,EMPLOYER_AWARE_FLAG ,CONTACT_EMPLOYER_FLAG ,OFFENDER_EMPLOYMENT_ID ,EMPLOYMENT_SCHEDULE ,CERTIFICATION ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM OFFENDER_EMPLOYMENTS  where OFFENDER_BOOK_ID = :offenderBookId ORDER BY EMPLOYMENT_DATE
}

OCDEDEMP_OFFEMPLOYMENTS_INSERT_OFFENDER_EMPLOYMENTS {
 insert into OFFENDER_EMPLOYMENTS(OFFENDER_BOOK_ID , EMPLOY_SEQ , EMPLOYMENT_DATE , EMPLOYMENT_POST_CODE , EMPLOYMENT_TYPE , TERMINATION_DATE , EMPLOYER_NAME , SUPERVISOR_NAME , position , TERMINATION_REASON_TEXT , WAGE , WAGE_PERIOD_CODE , OCCUPATIONS_CODE , COMMENT_TEXT , CASELOAD_TYPE , ROOT_OFFENDER_ID , CONTACT_TYPE , CONTACT_NUMBER , SCHEDULE_TYPE , SCHEDULE_HOURS , HOURS_WEEK , PARTIAL_EMPLOYMENT_DATE_FLAG , PARTIAL_TERMINATION_DATE_FLAG , CHECK_BOX_1 , CHECK_BOX_2 , EMPLOYER_AWARE_FLAG , CONTACT_EMPLOYER_FLAG , OFFENDER_EMPLOYMENT_ID , EMPLOYMENT_SCHEDULE , CERTIFICATION , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG ) values(:offenderBookId , :employSeq , :employmentDate , :employmentPostCode , :employmentType , :terminationDate , :employerName , :supervisorName , :position , :terminationReasonText , :wage , :wagePeriodCode , :occupationsCode , :commentText , :caseloadType , :rootOffenderId , :contactType , :contactNumber , :scheduleType , :scheduleHours , :hoursWeek , :partialEmploymentDateFlag , :partialTerminationDateFlag , :checkBox1 , :checkBox2 , :employerAwareFlag , :contactEmployerFlag , :offenderEmploymentId , :employmentSchedule , :certification , current_timestamp , :createUserId , NULL , :sealFlag ) 
} 

OCDEDEMP_OFFEMPLOYMENTS_UPDATE_OFFENDER_EMPLOYMENTS {
 update OFFENDER_EMPLOYMENTS set OFFENDER_BOOK_ID = :offenderBookId , EMPLOY_SEQ = :employSeq , EMPLOYMENT_DATE = :employmentDate , EMPLOYMENT_POST_CODE = :employmentPostCode , EMPLOYMENT_TYPE = :employmentType , TERMINATION_DATE = :terminationDate , EMPLOYER_NAME = :employerName , SUPERVISOR_NAME = :supervisorName , position = :position , TERMINATION_REASON_TEXT = :terminationReasonText , WAGE = :wage , WAGE_PERIOD_CODE = :wagePeriodCode , OCCUPATIONS_CODE = :occupationsCode , COMMENT_TEXT = :commentText , CASELOAD_TYPE = :caseloadType , ROOT_OFFENDER_ID = :rootOffenderId , CONTACT_TYPE = :contactType , CONTACT_NUMBER = :contactNumber , SCHEDULE_TYPE = :scheduleType , SCHEDULE_HOURS = :scheduleHours , HOURS_WEEK = :hoursWeek , PARTIAL_EMPLOYMENT_DATE_FLAG = :partialEmploymentDateFlag , PARTIAL_TERMINATION_DATE_FLAG = :partialTerminationDateFlag , CHECK_BOX_1 = :checkBox1 , CHECK_BOX_2 = :checkBox2 , EMPLOYER_AWARE_FLAG = :employerAwareFlag , CONTACT_EMPLOYER_FLAG = :contactEmployerFlag , OFFENDER_EMPLOYMENT_ID = :offenderEmploymentId , EMPLOYMENT_SCHEDULE = :employmentSchedule , CERTIFICATION = :certification , MODIFY_DATETIME = current_timestamp , MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag where OFFENDER_BOOK_ID = :offenderBookId and EMPLOY_SEQ = :employSeq 
} 

OCDEDEMP_OFFEMPLOYMENTS_DELETE_OFFENDER_EMPLOYMENTS { 
	DELETE FROM OFFENDER_EMPLOYMENTS where OFFENDER_BOOK_ID = :offenderBookId and EMPLOY_SEQ = :employSeq
} 

OCDEDEMP_VOFFEMPADDR_FIND_V_OFFENDER_EMPLOY_ADDRESSES {
	SELECT ADDRESS_ID ,ADDRESS_TYPE ,ADDRESS_TYPE_DESC ,OFFENDER_BOOK_ID ,EMPLOYMENT_SEQ ,START_DATE ,END_DATE ,ACTIVE_FLAG ,HOUSE ,STREET ,AREA ,COUNTRY ,SUITE_NUMBER ,STREET_NUMBER ,STREET_DIRECTION ,CITY_CODE ,PROV_STATE_CODE ,ZIP_POSTAL_CODE ,COUNTRY_CODE ,COMMENT_TEXT ,PRIMARY_FLAG ,MAIL_FLAG ,VALIDATED_FLAG ,STREET_INFORMATION ,CITY_NAME ,PROV_STATE_DESC, IS_ADDRESS_VALID,ADDRESS_LINE1   FROM V_OFFENDER_EMPLOY_ADDRESSES   where OFFENDER_BOOK_ID = :offenderBookId and EMPLOYMENT_SEQ=:employmentSeq ORDER BY PRIMARY_FLAG DESC , ACTIVE_FLAG DESC
}

OCDEDEMP_OFF_BKG_ONCHECKDELETEMASTER_OFF_EDUCATIONS_CUR {
	SELECT 1 FROM OFFENDER_EDUCATIONS O WHERE O.OFFENDER_BOOK_ID = :offenderBookId
}

OCDEDEMP_OFF_BKG_ONCHECKDELETEMASTER_OFF_EMPLOYMENTS_CUR {
	SELECT 1 FROM OFFENDER_EMPLOYMENTS O WHERE O.OFFENDER_BOOK_ID = :offenderBookId
}

OCDEDEMP_OFF_EDUCATIONS_ONCHECKDELETEMASTER_V_OFF_EDU_ADDR_CUR {
	SELECT 1 FROM V_OFFENDER_EDUCATION_ADDRESSES V WHERE V.OFFENDER_BOOK_ID = :offenderBookId AND V.EDUCATION_SEQ = :educationSeq
}

OCDEDEMP_OFF_EDUCATIONS_PREINSERT {
	select
	coalesce (MAX(EDUCATION_SEQ),
	0) + 1 EDUCATION_SEQ
from
	OFFENDER_EDUCATIONS
where
	OFFENDER_BOOK_ID = :offenderBookId
}

OCDEDEMP_OFF_EDUCATIONS_PREDELETEAS {
	select
	COUNT(*)
from
	ADDRESSES
where
	OWNER_CLASS = 'OFF_EDU'
	and OWNER_ID = :offenderBookId
	and OWNER_SEQ = :educationSeq
}

OCDEDEMP_OFF_EDUCATIONS_PREDELETEPS {
	select
	COUNT(*)
from
	PHONES
where
	OWNER_CLASS = 'OFF_EDU'
	and OWNER_ID = :offenderBookId
	and OWNER_SEQ = :educationSeq
}

OCDEDEMP_OFF_EDUCATIONS_PREDELETEIAS {
	select
	COUNT(*)
from
	INTERNET_ADDRESSES
where
	OWNER_CLASS = 'OFF_EDU'
	and OWNER_ID = :offenderBookId
	and OWNER_SEQ = :educationSeq
}

OCDEDEMP_OFF_EMPLOYMENTS_PREINSERT {
	select
	coalesce (MAX(EMPLOY_SEQ),
	0) + 1 EMPLOY_SEQ
from
	OFFENDER_EMPLOYMENTS
where
	OFFENDER_BOOK_ID =:offenderBookId
}

OCDEDEMP_OFF_EMPLOYMENTS_ONCHECKDELETEMASTER_V_OFF_EMP_ADDR_CUR {
	select
	1
from
	V_OFFENDER_EMPLOY_ADDRESSES V
where
	V.OFFENDER_BOOK_ID = :offenderBookId
	and V.EMPLOYMENT_SEQ = :employSeq
}

OCDEDEMP_OFF_EMPLOYMENTS_PREDELETEA {
	SELECT COUNT(*)
FROM ADDRESSES
WHERE
  OWNER_CLASS = 'OFF_EMP' AND
  OWNER_ID = :offenderBookId AND
  OWNER_SEQ = :employSeq
}

OCDEDEMP_OFF_EMPLOYMENTS_PREDELETEP {
	SELECT COUNT(*)
FROM PHONES
WHERE
  OWNER_CLASS = 'OFF_EMP' AND
  OWNER_ID = :offenderBookId AND
  OWNER_SEQ = :employSeq

}

OCDEDEMP_OFF_EMPLOYMENTS_PREDELETEIA {
	SELECT COUNT(*)
FROM INTERNET_ADDRESSES
WHERE
  OWNER_CLASS = 'OFF_EMP' AND
  OWNER_ID = :offenderBookId AND
  OWNER_SEQ = :employSeq

}

OCDOAPOP_FIND_RGCITY {
	SELECT
  DESCRIPTION, CODE
FROM REFERENCE_CODES RC
WHERE
  RC.DOMAIN = 'CITY' AND
  ((RC.ACTIVE_FLAG = 'Y' AND RC.EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, CODE

}

OCDOAPOP_FIND_RGCOUNTY {
	SELECT
  DESCRIPTION, CODE
FROM REFERENCE_CODES
WHERE
  DOMAIN='COUNTY' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, CODE

}

OCDOAPOP_FIND_RGCOUNTRY {
SELECT
  DESCRIPTION, CODE
FROM REFERENCE_CODES RC
WHERE
  RC.DOMAIN = 'COUNTRY' AND
  ((RC.ACTIVE_FLAG = 'Y' AND RC.EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, CODE

}

OCDOAPOP_FIND_RGTYPE {
	SELECT
  DESCRIPTION, CODE
FROM REFERENCE_CODES
WHERE
  DOMAIN = 'ADDR_TYPE' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, CODE

}

OCDOAPOP_FIND_RGSPECIALNEEDS {
	SELECT
  DESCRIPTION, CODE
FROM REFERENCE_CODES
WHERE
  DOMAIN = 'PS_NEEDS' AND
  ((ACTIVE_FLAG = 'Y' AND EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, CODE

}
	
OCDOAPOP_FIND_RGPROVSTATECODE {
	SELECT
  REF_CODE4.DESCRIPTION, REF_CODE4.CODE
FROM REFERENCE_CODES REF_CODE4
WHERE
  DOMAIN = 'PROV_STATE' AND
  ACTIVE_FLAG = 'Y' AND
  EXPIRED_DATE IS NULL
ORDER BY
  REF_CODE4.LIST_SEQ, REF_CODE4.DESCRIPTION

}
	
OCDOAPOP_FIND_RGSTREETDIR {
	SELECT
  DESCRIPTION, CODE
FROM REFERENCE_CODES RC
WHERE
  RC.DOMAIN = 'STREET_DIR' AND
  ((RC.ACTIVE_FLAG = 'Y' AND RC.EXPIRED_DATE IS NULL ))
ORDER BY
  LIST_SEQ, CODE

}

OCDOAPOP_ADDRESS_FIND_ADDRESSES {
	SELECT
  ADDRESS_ID, OWNER_CLASS, OWNER_ID, OWNER_SEQ, OWNER_CODE, ADDRESS_TYPE,
  CITY_CODE, COUNTRY_CODE, VALIDATED_PAF_FLAG, PRIMARY_FLAG, MAIL_FLAG,
  CAPACITY, COMMENT_TEXT, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME,
  MODIFY_USER_ID, NO_FIXED_ADDRESS_FLAG, SERVICES_FLAG, SPECIAL_NEEDS_CODE,
  CONTACT_PERSON_NAME, BUSINESS_HOUR, START_DATE, END_DATE, CITY_NAME,
  PROV_STATE_CODE, STREET, ZIP_POSTAL_CODE, SUITE_NUMBER, STREET_NUMBER,
  STREET_DIRECTION, MAIL_CARE_OF, SEAL_FLAG
FROM ADDRESSES
where
  ADDRESS_ID = :addressId

}

OCDOAPOP_ADDRESS_INSERT_ADDRESSES {
 insert into ADDRESSES(ADDRESS_ID , OWNER_CLASS , OWNER_ID , OWNER_SEQ , OWNER_CODE , ADDRESS_TYPE , CITY_CODE , COUNTRY_CODE , VALIDATED_PAF_FLAG , PRIMARY_FLAG , MAIL_FLAG , CAPACITY , COMMENT_TEXT , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , NO_FIXED_ADDRESS_FLAG , SERVICES_FLAG , SPECIAL_NEEDS_CODE , CONTACT_PERSON_NAME , BUSINESS_HOUR , START_DATE , END_DATE , CITY_NAME , PROV_STATE_CODE , STREET , ZIP_POSTAL_CODE , SUITE_NUMBER , STREET_NUMBER , STREET_DIRECTION , MAIL_CARE_OF , SEAL_FLAG ) values(:addressId , :ownerClass , :ownerId , :ownerSeq , :ownerCode , :addressType , :cityCode , :countryCode , :validatedPafFlag , :primaryFlag , :mailFlag , :capacity , :commentText , :createDatetime , :createUserId , NULL , :noFixedAddressFlag , :servicesFlag , :specialNeedsCode , :contactPersonName , :businessHour , :startDate , :endDate , :cityName , :provStateCode , :street , :zipPostalCode , :suiteNumber , :streetNumber , :streetDirection , :mailCareOf , :sealFlag ) 
} 

OCDOAPOP_ADDRESS_UPDATE_ADDRESSES {
 update ADDRESSES set ADDRESS_ID = :addressId , OWNER_CLASS = :ownerClass , OWNER_ID = :ownerId , OWNER_SEQ = :ownerSeq , OWNER_CODE = :ownerCode , ADDRESS_TYPE = :addressType , CITY_CODE = :cityCode , COUNTRY_CODE = :countryCode , VALIDATED_PAF_FLAG = :validatedPafFlag , PRIMARY_FLAG = :primaryFlag , MAIL_FLAG = :mailFlag , CAPACITY = :capacity , COMMENT_TEXT = :commentText , MODIFY_DATETIME = :modifyDatetime , MODIFY_USER_ID = :modifyUserId , NO_FIXED_ADDRESS_FLAG = :noFixedAddressFlag , SERVICES_FLAG = :servicesFlag , SPECIAL_NEEDS_CODE = :specialNeedsCode , CONTACT_PERSON_NAME = :contactPersonName , BUSINESS_HOUR = :businessHour , START_DATE = :startDate , END_DATE = :endDate , CITY_NAME = :cityName , PROV_STATE_CODE = :provStateCode , STREET = :street , ZIP_POSTAL_CODE = :zipPostalCode , SUITE_NUMBER = :suiteNumber , STREET_NUMBER = :streetNumber , STREET_DIRECTION = :streetDirection , MAIL_CARE_OF = :mailCareOf , SEAL_FLAG = :sealFlag where ADDRESS_ID = :addressId 
} 

OCDOAPOP_ADDRESS_DELETE_ADDRESSES { 
	DELETE FROM ADDRESSES where ADDRESS_ID = :addressId
} 

OCDOAPOP_ADDRESS_WHENCREATERECORD_GET_COUNTRY_DESC {
	SELECT DESCRIPTION
FROM REFERENCE_CODES
WHERE
  DOMAIN = 'COUNTRY' AND
  LIST_SEQ = 1

}

OCDOAPOP_ADDRESS_KEYDELREC_PHONE_EX {
	SELECT 'Y'
FROM PHONES
WHERE
  OWNER_CLASS = 'ADDR' AND
  OWNER_ID = :addressId

}

OCDOAPOP_OCDOAPOP_PREINSERT_ADDRESS_ID_CUR {
	SELECT NEXTVAL('ADDRESS_IDN') FROM DUAL
}

OCDOAPOP_VALIDATE_CITY_INFO_GET_CITY_DESCRIPTION_C {
	SELECT COUNT(1)
FROM REFERENCE_CODES
WHERE
  DOMAIN = 'CITY' AND
  ACTIVE_FLAG = 'Y' AND
  EXPIRED_DATE IS NULL AND
  UPPER(DESCRIPTION) LIKE '%'

}

OCDOAPOP_VALIDATE_CITY_INFO_GET_CITY_CODE_C {
	SELECT
  CODE, DESCRIPTION
FROM REFERENCE_CODES
WHERE
  DOMAIN = 'CITY' AND
  ACTIVE_FLAG = 'Y' AND
  EXPIRED_DATE IS NULL AND
  UPPER(DESCRIPTION) LIKE '%'

}

OCDOAPOP_CITY_SYSTEM_PROFILE_CITY_PROFILE_CUR {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'MAN_CITY_FLD'
}
OCDEDEMP_OFF_EDUCATIONS_MAX_VALUE{

SELECT  coalesce (MAX(EDUCATION_SEQ),0) + 1 seq FROM OFFENDER_EDUCATIONS WHERE OFFENDER_BOOK_ID=:offenderBookId
}
OCDEDEMP_OFF_EMPLOYMENTS_MAX_VALUE{

SELECT  coalesce(MAX(EMPLOY_SEQ),0) + 1 seq FROM OFFENDER_EMPLOYMENTS WHERE OFFENDER_BOOK_ID=:offenderBookId
}

