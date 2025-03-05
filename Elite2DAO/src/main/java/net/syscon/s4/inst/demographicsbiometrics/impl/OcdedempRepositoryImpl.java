package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderEducations;
import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VOffenderEducationAddresses;
import net.syscon.s4.common.beans.VOffenderEmployAddresses;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.demographicsbiometrics.OcdedempRepository;

/**
 * Class OcdedempRepositoryImpl
 */
@Repository
public class OcdedempRepositoryImpl extends RepositoryBase implements OcdedempRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OcdedempRepositoryImpl.class.getName());
	
private final Map<String, FieldMapper> offenderEducationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("END_DATE", 						new FieldMapper("endDate"))
	.put("ADDRESS_TYPE_DESC", 				new FieldMapper("addressTypeDesc"))
	.put("CONTACT_TYPE", 					new FieldMapper("contactType"))
	.put("CONTACT_EMPLOYER_FLAG", 			new FieldMapper("contactEmployerFlag"))
	.put("PARTIAL_END_DATE_FLAG", 			new FieldMapper("partialEndDateFlag"))
	.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
	.put("AREA", 						    new FieldMapper("area"))
	.put("NUMBER_OF_YEARS", 				new FieldMapper("numberOfYears"))
	.put("SPECIAL_EDUCATION_FLAG", 			new FieldMapper("specialEducationFlag"))
	.put("MAIL_FLAG", 						new FieldMapper("mailFlag"))
	.put("PARTIAL_EMPLOYMENT_DATE_FLAG", 	new FieldMapper("partialEmploymentDateFlag"))
	.put("CITY_NAME", 						new FieldMapper("cityName"))
	.put("CASELOAD_TYPE", 					new FieldMapper("caseloadType"))
	.put("WAGE", 							new FieldMapper("wage"))
	.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
	.put("COUNTRY", 						new FieldMapper("country"))
	.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
	.put("EMPLOY_SEQ", 						new FieldMapper("employSeq"))
	.put("PARTIAL_START_DATE_FLAG", 		new FieldMapper("partialStartDateFlag"))
	.put("EMPLOYMENT_DATE", 				new FieldMapper("employmentDate"))
	.put("STREET_DIRECTION", 				new FieldMapper("streetDirection"))
	.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
	.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
	.put("TERMINATION_REASON_TEXT", 		new FieldMapper("terminationReasonText"))
	.put("EMPLOYMENT_POST_CODE", 			new FieldMapper("employmentPostCode"))
	.put("PARTIAL_TERMINATION_DATE_FLAG", 	new FieldMapper("partialTerminationDateFlag"))
	.put("SCHEDULE_TYPE", 					new FieldMapper("scheduleType"))
	.put("EDUCATION_SCHEDULE", 				new FieldMapper("educationSchedule"))
	.put("START_DATE", 						new FieldMapper("startDate"))
	.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
	.put("ADDRESS_ID", 						new FieldMapper("addressId"))
	.put("COUNTRY_CODE", 					new FieldMapper("countryCode"))
	.put("GRADUATION_YEAR", 				new FieldMapper("graduationYear"))
	.put("SUPERVISOR_NAME", 				new FieldMapper("supervisorName"))
	.put("OCCUPATIONS_CODE", 				new FieldMapper("occupationsCode"))
	.put("WAGE_PERIOD_CODE", 				new FieldMapper("wagePeriodCode"))
	.put("PROV_STATE_DESC", 				new FieldMapper("provStateDesc"))
	.put("ADDRESS_TYPE", 					new FieldMapper("addressType"))
	.put("VALIDATED_FLAG", 					new FieldMapper("validatedFlag"))
	.put("TERMINATION_DATE", 				new FieldMapper("terminationDate"))
	.put("SUITE_NUMBER", 					new FieldMapper("suiteNumber"))
	.put("ZIP_POSTAL_CODE", 				new FieldMapper("zipPostalCode"))
	.put("CONTACT_NUMBER", 					new FieldMapper("contactNumber"))
	.put("SCHOOL_CODE", 					new FieldMapper("schoolCode"))
	.put("POSITION", 						new FieldMapper("position"))
	.put("CHECK_BOX_1", 					new FieldMapper("checkBox1"))
	.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
	.put("EDUCATION_SEQ", 					new FieldMapper("educationSeq"))
	.put("PRIMARY_FLAG", 					new FieldMapper("primaryFlag"))
	.put("CERTIFICATION", 					new FieldMapper("certification"))
	.put("STREET", 							new FieldMapper("street"))
	.put("HOURS_WEEK", 						new FieldMapper("hoursWeek"))
	.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
	.put("CITY_CODE", 						new FieldMapper("cityCode"))
	.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
	.put("SCHEDULE_HOURS", 					new FieldMapper("scheduleHours"))
	.put("EMPLOYER_AWARE_FLAG", 			new FieldMapper("employerAwareFlag"))
	.put("EDUCATION_TYPE", 					new FieldMapper("educationType"))
	.put("PROV_STATE_CODE", 				new FieldMapper("provStateCode"))
	.put("STUDY_AREA_CODE", 				new FieldMapper("studyAreaCode"))
	.put("EMPLOYER_NAME", 					new FieldMapper("employerName"))
	.put("EMPLOYMENT_SCHEDULE", 			new FieldMapper("employmentSchedule"))
	.put("STREET_NUMBER", 					new FieldMapper("streetNumber"))
	.put("CHECK_BOX_2", 					new FieldMapper("checkBox2"))
	.put("STUDENT_ID", 						new FieldMapper("studentId"))
	.put("EDUCATION_LEVEL_CODE", 			new FieldMapper("educationLevelCode"))
	.put("SCHOOL_NAME", 					new FieldMapper("schoolName"))
	.put("EMPLOYMENT_TYPE", 				new FieldMapper("employmentType"))
	.put("OFFENDER_EMPLOYMENT_ID", 			new FieldMapper("offenderEmploymentId"))
	.put("EMPLOYMENT_SEQ", 					new FieldMapper("employmentSeq"))
	.put("HOUSE", 							new FieldMapper("house"))
    .build();

private final Map<String, FieldMapper> vOffenderEmployAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("END_DATE", 						new FieldMapper("endDate"))
	.put("ADDRESS_TYPE_DESC", 				new FieldMapper("addressTypeDesc"))
	.put("CONTACT_TYPE", 					new FieldMapper("contactType"))
	.put("CONTACT_EMPLOYER_FLAG", 			new FieldMapper("contactEmployerFlag"))
	.put("PARTIAL_END_DATE_FLAG", 			new FieldMapper("partialEndDateFlag"))
	.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
	.put("AREA", 							new FieldMapper("area"))
	.put("NUMBER_OF_YEARS", 				new FieldMapper("numberOfYears"))
	.put("SPECIAL_EDUCATION_FLAG", 			new FieldMapper("specialEducationFlag"))
	.put("MAIL_FLAG", 						new FieldMapper("mailFlag"))
	.put("PARTIAL_EMPLOYMENT_DATE_FLAG", 	new FieldMapper("partialEmploymentDateFlag"))
	.put("CITY_NAME", 						new FieldMapper("cityName"))
	.put("CASELOAD_TYPE", 					new FieldMapper("caseloadType"))
	.put("WAGE", 							new FieldMapper("wage"))
	.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
	.put("COUNTRY", 						new FieldMapper("country"))
	.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
	.put("EMPLOY_SEQ", 						new FieldMapper("employSeq"))
	.put("PARTIAL_START_DATE_FLAG", 		new FieldMapper("partialStartDateFlag"))
	.put("EMPLOYMENT_DATE", 				new FieldMapper("employmentDate"))
	.put("STREET_DIRECTION", 				new FieldMapper("streetDirection"))
	.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
	.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
	.put("TERMINATION_REASON_TEXT", 		new FieldMapper("terminationReasonText"))
	.put("EMPLOYMENT_POST_CODE", 			new FieldMapper("employmentPostCode"))
	.put("PARTIAL_TERMINATION_DATE_FLAG", 	new FieldMapper("partialTerminationDateFlag"))
	.put("SCHEDULE_TYPE", 					new FieldMapper("scheduleType"))
	.put("EDUCATION_SCHEDULE", 				new FieldMapper("educationSchedule"))
	.put("START_DATE", 						new FieldMapper("startDate"))
	.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
	.put("ADDRESS_ID", 						new FieldMapper("addressId"))
	.put("COUNTRY_CODE", 					new FieldMapper("countryCode"))
	.put("GRADUATION_YEAR", 				new FieldMapper("graduationYear"))
	.put("SUPERVISOR_NAME", 				new FieldMapper("supervisorName"))
	.put("OCCUPATIONS_CODE", 				new FieldMapper("occupationsCode"))
	.put("WAGE_PERIOD_CODE", 				new FieldMapper("wagePeriodCode"))
	.put("PROV_STATE_DESC", 				new FieldMapper("provStateDesc"))
	.put("ADDRESS_TYPE", 					new FieldMapper("addressType"))
	.put("VALIDATED_FLAG", 					new FieldMapper("validatedFlag"))
	.put("TERMINATION_DATE", 				new FieldMapper("terminationDate"))
	.put("SUITE_NUMBER", 					new FieldMapper("suiteNumber"))
	.put("ZIP_POSTAL_CODE", 				new FieldMapper("zipPostalCode"))
	.put("CONTACT_NUMBER", 					new FieldMapper("contactNumber"))
	.put("SCHOOL_CODE", 					new FieldMapper("schoolCode"))
	.put("POSITION", 						new FieldMapper("position"))
	.put("CHECK_BOX_1", 					new FieldMapper("checkBox1"))
	.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
	.put("EDUCATION_SEQ", 					new FieldMapper("educationSeq"))
	.put("PRIMARY_FLAG", 					new FieldMapper("primaryFlag"))
	.put("CERTIFICATION", 					new FieldMapper("certification"))
	.put("STREET", 							new FieldMapper("street"))
	.put("HOURS_WEEK", 						new FieldMapper("hoursWeek"))
	.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
	.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
	.put("CITY_CODE", 						new FieldMapper("cityCode"))
	.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
	.put("SCHEDULE_HOURS", 					new FieldMapper("scheduleHours"))
	.put("EMPLOYER_AWARE_FLAG", 			new FieldMapper("employerAwareFlag"))
	.put("EDUCATION_TYPE", 					new FieldMapper("educationType"))
	.put("PROV_STATE_CODE", 				new FieldMapper("provStateCode"))
	.put("STUDY_AREA_CODE", 				new FieldMapper("studyAreaCode"))
	.put("EMPLOYER_NAME", 					new FieldMapper("employerName"))
	.put("EMPLOYMENT_SCHEDULE", 			new FieldMapper("employmentSchedule"))
	.put("STREET_NUMBER", 					new FieldMapper("streetNumber"))
	.put("CHECK_BOX_2", 					new FieldMapper("checkBox2"))
	.put("STUDENT_ID", 						new FieldMapper("studentId"))
	.put("EDUCATION_LEVEL_CODE", 			new FieldMapper("educationLevelCode"))
	.put("SCHOOL_NAME", 					new FieldMapper("schoolName"))
	.put("EMPLOYMENT_TYPE", 				new FieldMapper("employmentType"))
	.put("OFFENDER_EMPLOYMENT_ID", 			new FieldMapper("offenderEmploymentId"))
	.put("EMPLOYMENT_SEQ", 					new FieldMapper("employmentSeq"))
	.put("HOUSE", 							new FieldMapper("house"))	
    .build();
private final Map<String, FieldMapper> vOffenderEducationAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("END_DATE", 						new FieldMapper("endDate"))
	.put("ADDRESS_TYPE_DESC", 				new FieldMapper("addressTypeDesc"))
	.put("CONTACT_TYPE", 					new FieldMapper("contactType"))
	.put("CONTACT_EMPLOYER_FLAG", 			new FieldMapper("contactEmployerFlag"))
	.put("PARTIAL_END_DATE_FLAG", 			new FieldMapper("partialEndDateFlag"))
	.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
	.put("AREA", 							new FieldMapper("area"))
	.put("NUMBER_OF_YEARS", 				new FieldMapper("numberOfYears"))
	.put("SPECIAL_EDUCATION_FLAG", 			new FieldMapper("specialEducationFlag"))
	.put("MAIL_FLAG", 						new FieldMapper("mailFlag"))
	.put("PARTIAL_EMPLOYMENT_DATE_FLAG", 	new FieldMapper("partialEmploymentDateFlag"))
	.put("CITY_NAME", 						new FieldMapper("cityName"))
	.put("CASELOAD_TYPE", 					new FieldMapper("caseloadType"))
	.put("WAGE", 							new FieldMapper("wage"))
	.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
	.put("COUNTRY", 						new FieldMapper("country"))
	.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
	.put("EMPLOY_SEQ", 						new FieldMapper("employSeq"))
	.put("PARTIAL_START_DATE_FLAG", 		new FieldMapper("partialStartDateFlag"))
	.put("EMPLOYMENT_DATE", 				new FieldMapper("employmentDate"))
	.put("STREET_DIRECTION", 				new FieldMapper("streetDirection"))
	.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
	.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
	.put("TERMINATION_REASON_TEXT", 		new FieldMapper("terminationReasonText"))
	.put("STREET_INFORMATION", 				new FieldMapper("streetInformation"))
	.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
	
	.put("EMPLOYMENT_POST_CODE", 			new FieldMapper("employmentPostCode"))
	.put("PARTIAL_TERMINATION_DATE_FLAG", 	new FieldMapper("partialTerminationDateFlag"))
	.put("SCHEDULE_TYPE", 					new FieldMapper("scheduleType"))
	.put("EDUCATION_SCHEDULE", 				new FieldMapper("educationSchedule"))
	.put("START_DATE", 						new FieldMapper("startDate"))
	.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
	.put("ADDRESS_ID", 						new FieldMapper("addressId"))
	.put("COUNTRY_CODE", 					new FieldMapper("countryCode"))
	.put("GRADUATION_YEAR", 				new FieldMapper("graduationYear"))
	.put("SUPERVISOR_NAME", 				new FieldMapper("supervisorName"))
	.put("OCCUPATIONS_CODE", 				new FieldMapper("occupationsCode"))
	.put("WAGE_PERIOD_CODE", 				new FieldMapper("wagePeriodCode"))
	.put("PROV_STATE_DESC", 				new FieldMapper("provStateDesc"))
	.put("ADDRESS_TYPE", 					new FieldMapper("addressType"))
	.put("VALIDATED_FLAG", 					new FieldMapper("validatedFlag"))
	.put("TERMINATION_DATE", 				new FieldMapper("terminationDate"))
	.put("SUITE_NUMBER", 					new FieldMapper("suiteNumber"))
	.put("ZIP_POSTAL_CODE", 				new FieldMapper("zipPostalCode"))
	.put("CONTACT_NUMBER", 					new FieldMapper("contactNumber"))
	.put("SCHOOL_CODE", 					new FieldMapper("schoolCode"))
	.put("POSITION", 						new FieldMapper("position"))
	.put("CHECK_BOX_1", 					new FieldMapper("checkBox1"))
	.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
	.put("EDUCATION_SEQ", 					new FieldMapper("educationSeq"))
	.put("PRIMARY_FLAG", 					new FieldMapper("primaryFlag"))
	.put("CERTIFICATION", 					new FieldMapper("certification"))
	.put("STREET", 							new FieldMapper("street"))
	.put("HOURS_WEEK", 						new FieldMapper("hoursWeek"))
	.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
	.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
	.put("CITY_CODE", 						new FieldMapper("cityCode"))
	.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
	.put("SCHEDULE_HOURS", 					new FieldMapper("scheduleHours"))
	.put("EMPLOYER_AWARE_FLAG", 			new FieldMapper("employerAwareFlag"))
	.put("EDUCATION_TYPE", 					new FieldMapper("educationType"))
	.put("PROV_STATE_CODE", 				new FieldMapper("provStateCode"))
	.put("STUDY_AREA_CODE", 				new FieldMapper("studyAreaCode"))
	.put("EMPLOYER_NAME", 					new FieldMapper("employerName"))
	.put("EMPLOYMENT_SCHEDULE", 			new FieldMapper("employmentSchedule"))
	.put("STREET_NUMBER", 					new FieldMapper("streetNumber"))
	.put("CHECK_BOX_2", 					new FieldMapper("checkBox2"))
	.put("STUDENT_ID", 						new FieldMapper("studentId"))
	.put("EDUCATION_LEVEL_CODE", 			new FieldMapper("educationLevelCode"))
	.put("SCHOOL_NAME", 					new FieldMapper("schoolName"))
	.put("EMPLOYMENT_TYPE", 				new FieldMapper("employmentType"))
	.put("OFFENDER_EMPLOYMENT_ID", 			new FieldMapper("offenderEmploymentId"))
	.put("EMPLOYMENT_SEQ", 					new FieldMapper("employmentSeq"))
	.put("HOUSE", 							new FieldMapper("house"))
    .build();

private final Map<String, FieldMapper> offenderEmploymentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
	  .put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
      .build();
private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("DESCRIPTION", 					new FieldMapper("description"))
	.put("CODE", 							new FieldMapper("code"))
    .build();

private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("'Y'", 							new FieldMapper("'y'"))
	.put("ADDRESS_ID", 						new FieldMapper("addressId"))
	.put("OWNER_ID", 						new FieldMapper("ownerId"))
    .build();

private final Map<String, FieldMapper> addressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("END_DATE", 						new FieldMapper("endDate"))
	.put("OWNER_ID", 						new FieldMapper("ownerId"))
	.put("ADDRESS_TYPE", 					new FieldMapper("addressType"))
	.put("SUITE_NUMBER", 					new FieldMapper("suiteNumber"))
	.put("ZIP_POSTAL_CODE", 				new FieldMapper("zipPostalCode"))
	.put("OWNER_SEQ", 						new FieldMapper("ownerSeq"))
	.put("NO_FIXED_ADDRESS_FLAG", 			new FieldMapper("noFixedAddressFlag"))
	.put("MAIL_FLAG", 						new FieldMapper("mailFlag"))
	.put("BUSINESS_HOUR", 					new FieldMapper("businessHour"))
	.put("CITY_NAME", 						new FieldMapper("cityName"))
	.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
	.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
	.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
	.put("PRIMARY_FLAG", 					new FieldMapper("primaryFlag"))
	.put("STREET", 							new FieldMapper("street"))
	.put("STREET_DIRECTION", 				new FieldMapper("streetDirection"))
	.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
	.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
	.put("CAPACITY", 						new FieldMapper("capacity"))
	.put("OWNER_CLASS", 					new FieldMapper("ownerClass"))
	.put("OWNER_CODE", 						new FieldMapper("ownerCode"))
	.put("CITY_CODE", 						new FieldMapper("cityCode"))
	.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
	.put("CONTACT_PERSON_NAME", 			new FieldMapper("contactPersonName"))
	.put("SERVICES_FLAG", 					new FieldMapper("servicesFlag"))
	.put("PROV_STATE_CODE", 				new FieldMapper("provStateCode"))
	.put("START_DATE", 						new FieldMapper("startDate"))
	.put("STREET_NUMBER", 					new FieldMapper("streetNumber"))
	.put("MAIL_CARE_OF", 					new FieldMapper("mailCareOf"))
	.put("ADDRESS_ID", 						new FieldMapper("addressId"))
	.put("COUNTRY_CODE", 					new FieldMapper("countryCode"))
	.put("VALIDATED_PAF_FLAG", 				new FieldMapper("validatedPafFlag"))
	.put("SPECIAL_NEEDS_CODE", 				new FieldMapper("specialNeedsCode"))
    .build();

private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("PROFILE_VALUE", 					new FieldMapper("profileValue"))
    .build();

	/**
	 * Creates new OcdedempRepositoryImpl class Object
	 */
	public OcdedempRepositoryImpl() {
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> eduSchedRgRgroup() {
		final String sql = getQuery("OCDEDEMP_FIND_EDUSCHEDRG");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		 List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("eduSchedRgRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> payPeriodRgRgroup() {
		final String sql = getQuery("OCDEDEMP_FIND_PAYPERIODRG");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		 List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("payPeriodRgRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> occupationRgRgroup() {
		final String sql = getQuery("OCDEDEMP_FIND_OCCUPATIONRG");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("occupationRgRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> scheduleTypeRgRgroup() {
		final String sql = getQuery("OCDEDEMP_FIND_SCHEDULETYPERG");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("scheduleTypeRgRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> employStsRgRgroup() {
		final String sql = getQuery("OCDEDEMP_FIND_EMPLOYSTSRG");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
	      List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("employStsRgRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> studyAreaRgRgroup() {
		final String sql = getQuery("OCDEDEMP_FIND_STUDYAREARG");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("studyAreaRgRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> eduLevelRgRgroup() {
		final String sql = getQuery("OCDEDEMP_FIND_EDULEVELRG");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("eduLevelRgRgroup",e);
		}
		return refList;
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao
	 * @return List<OffenderEducations>
	 */
	public List<OffenderEducations> offEducationsSearchOffenderEducations(final OffenderEducations searchBean) {
		final String sql = getQuery("OCDEDEMP_OFFEDUCATIONS_FIND_OFFENDER_EDUCATIONS");
		final RowMapper<OffenderEducations> OffenderEducationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderEducations.class, offenderEducationsMapping);
		List<OffenderEducations> returnList = new ArrayList<OffenderEducations>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", searchBean.getOffenderBookId()), OffenderEducationsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param lstOffenderEducations
	 * @return List<OffenderEducations>
	 */
	public Integer offEducationsInsertOffenderEducations(final List<OffenderEducations> lstOffenderEducations) {
		final String sql = getQuery("OCDEDEMP_OFFEDUCATIONS_INSERT_OFFENDER_EDUCATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderEducations offenderEducations : lstOffenderEducations) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEducations));
		}
		try {			
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			log.error("offEducationsInsertOffenderEducations",e);
			return 0;
		}
		if (lstOffenderEducations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param lstOffenderEducations
	 * @return List<OffenderEducations>
	 */
	public Integer offEducationsUpdateOffenderEducations(final List<OffenderEducations> lstOffenderEducations) {
		final String sql = getQuery("OCDEDEMP_OFFEDUCATIONS_UPDATE_OFFENDER_EDUCATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderEducations offenderEducations : lstOffenderEducations) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEducations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderEducations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param lstOffenderEducations
	 * @return List<OffenderEducations>
	 */
	public Integer offEducationsDeleteOffenderEducations(final List<OffenderEducations> lstOffenderEducations) {
		final String sql = getQuery("OCDEDEMP_OFFEDUCATIONS_DELETE_OFFENDER_EDUCATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderEducations offenderEducations : lstOffenderEducations) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEducations));
		}
		try {
			String tableName = "OFFENDER_EDUCATIONS";
			String whereClause = "OFFENDER_BOOK_ID = :offenderBookId and EDUCATION_SEQ = :educationSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			log.error("Exception occured in " + this.getClass().getName() + " in method offEducationsDeleteOffenderEducations", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderEducations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao
	 * @return List<VOffenderEducationAddresses>
	 */
	public List<VOffenderEducationAddresses> vOffEduAddrSearchVOffenderEducationAddresses(
			final VOffenderEducationAddresses searchBean) {
		final String sql = getQuery("OCDEDEMP_VOFFEDUADDR_FIND_V_OFFENDER_EDUCATION_ADDRESSES");
		final RowMapper<VOffenderEducationAddresses> VOffenderEducationAddressesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, VOffenderEducationAddresses.class, vOffenderEducationAddressesMapping);
		List<VOffenderEducationAddresses> returnList = new ArrayList<VOffenderEducationAddresses>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",
				searchBean.getOffenderBookId(), "educationSeq", searchBean.getEducationSeq()),
				VOffenderEducationAddressesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao
	 * @return List<OffenderEmployments>
	 */
	public List<OffenderEmployments> offEmploymentsSearchOffenderEmployments(final OffenderEmployments searchBean) {
		final String sql = getQuery("OCDEDEMP_OFFEMPLOYMENTS_FIND_OFFENDER_EMPLOYMENTS");
		final RowMapper<OffenderEmployments> OffenderEmploymentsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderEmployments.class, offenderEmploymentsMapping);
		List<OffenderEmployments> returnList = new ArrayList<OffenderEmployments>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", searchBean.getOffenderBookId()), OffenderEmploymentsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param lstOffenderEmployments
	 * @return List<OffenderEmployments>
	 */
	public Integer offEmploymentsInsertOffenderEmployments(final List<OffenderEmployments> lstOffenderEmployments) {
		String sql = getQuery("OCDEDEMP_OFFEMPLOYMENTS_INSERT_OFFENDER_EMPLOYMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderEmployments offenderEmployments : lstOffenderEmployments) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEmployments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderEmployments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param lstOffenderEmployments
	 * @return List<OffenderEmployments>
	 */
	public Integer offEmploymentsUpdateOffenderEmployments(final List<OffenderEmployments> lstOffenderEmployments) {
		String sql = getQuery("OCDEDEMP_OFFEMPLOYMENTS_UPDATE_OFFENDER_EMPLOYMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderEmployments offenderEmployments : lstOffenderEmployments) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEmployments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderEmployments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param lstOffenderEmployments
	 * @return List<OffenderEmployments>
	 */
	public Integer offEmploymentsDeleteOffenderEmployments(final List<OffenderEmployments> lstOffenderEmployments) {
		String sql = getQuery("OCDEDEMP_OFFEMPLOYMENTS_DELETE_OFFENDER_EMPLOYMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderEmployments offenderEmployments : lstOffenderEmployments) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEmployments));
		}
		try {
			String tableName = "OFFENDER_EMPLOYMENTS";
			String whereClause = "OFFENDER_BOOK_ID = :offenderBookId and EMPLOY_SEQ = :employSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			log.error("Exception occured in " + this.getClass().getName() + " in method offEmploymentsDeleteOffenderEmployments", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderEmployments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchBean
	 * @return List<VOffenderEmployAddresses>
	 */
	public List<VOffenderEmployAddresses> vOffEmpAddrSearchVOffenderEmployAddresses(
			final VOffenderEmployAddresses searchBean) {
		final String sql = getQuery("OCDEDEMP_VOFFEMPADDR_FIND_V_OFFENDER_EMPLOY_ADDRESSES");
		final RowMapper<VOffenderEmployAddresses> VOffenderEmployAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderEmployAddresses.class, vOffenderEmployAddressesMapping);
		final ArrayList<VOffenderEmployAddresses> returnList = (ArrayList<VOffenderEmployAddresses>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", searchBean.getOffenderBookId(), "employmentSeq",
						searchBean.getEmploymentSeq()), VOffenderEmployAddressesRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return OffenderEducations
	 */
	public List<String> offBkgOnCheckDeleteMasteroffEducationsCur(final OffenderEducations paramBean) {
		final String sql = getQuery("OCDEDEMP_OFF_BKG_ONCHECKDELETEMASTER_OFF_EDUCATIONS_CUR");
		List<Object> returnObj = new ArrayList<>();
		returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), Object.class);
		final List<String> returnObjList = new ArrayList<>();
		for (final Object object : returnObj) {
			returnObjList.add(object.toString());
		}
		return returnObjList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return OffenderEmployments
	 */
	public List<String> offBkgOnCheckDeleteMasteroffEmploymentsCur(final OffenderEmployments paramBean) {
		List<Object> returnObj = new ArrayList<>();
		final String sql = getQuery("OCDEDEMP_OFF_BKG_ONCHECKDELETEMASTER_OFF_EMPLOYMENTS_CUR");
		returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), Object.class);
		final List<String> returnObjList = new ArrayList<>();
		for (final Object object : returnObj) {
			returnObjList.add(object.toString());
		}
		return returnObjList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return VOffenderEducationAddresses
	 */
	public List<String> offEducationsOnCheckDeleteMastervOffEduAddrCur(final VOffenderEducationAddresses paramBean) {
		List<Object> returnObj = new ArrayList<>();
		final String sql = getQuery("OCDEDEMP_OFF_EDUCATIONS_ONCHECKDELETEMASTER_V_OFF_EDU_ADDR_CUR");
		returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams("offenderBookId",
				paramBean.getOffenderBookId(), "educationSeq", paramBean.getEducationSeq()), Object.class);
		final List<String> returnObjList = new ArrayList<>();
		for (final Object object : returnObj) {
			returnObjList.add(object.toString());
		}
		return returnObjList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return OffenderEducations
	 */
	public Integer offEducationsPreInsert(final OffenderEducations paramBean) {
		final String sql = getQuery("OCDEDEMP_OFF_EDUCATIONS_PREINSERT");
		Integer returnObj =null;
		returnObj  = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), Integer.class);
		return returnObj;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return Addresses
	 */
	public Integer offEducationsPreDeleteAddress(final Addresses paramBean) {
		final String sql = getQuery("OCDEDEMP_OFF_EDUCATIONS_PREDELETEAS");
		Integer returnObj =null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOwnerId(), "educationSeq", paramBean.getOwnerSeq()),
				Integer.class);		
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return Phones
	 */
	public Integer offEducationsPreDeletePhones(final Phones paramBean) {
		final String sql = getQuery("OCDEDEMP_OFF_EDUCATIONS_PREDELETEPS");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("offenderBookId", paramBean.getOwnerId(), "educationSeq", paramBean.getOwnerSeq()),
				Object.class);
		String count = returnList.get(0).toString();
		return Integer.valueOf(count);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return InternetAddresses
	 */
	public Integer offEducationsPreDeleteInternetAddress(final InternetAddresses paramBean) {
		final String sql = getQuery("OCDEDEMP_OFF_EDUCATIONS_PREDELETEIAS");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("offenderBookId", paramBean.getOwnerId(), "educationSeq", paramBean.getOwnerSeq()),
				Object.class);
		String count = returnList.get(0).toString();
		return Integer.valueOf(count);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return OffenderEmployments
	 */
	public Integer offEmploymentsPreInsert(final OffenderEmployments paramBean) {
		final String sql = getQuery("OCDEDEMP_OFF_EMPLOYMENTS_PREINSERT");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), Object.class);
		String offenderId = returnList.get(0).toString();
		return Integer.valueOf(offenderId);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return VOffenderEmployAddresses
	 */
	public List<String> offEmploymentsOnCheckDeleteMastervOffEmpAddrCur(final VOffenderEmployAddresses paramBean) {
		List<Object> returnObj = new ArrayList<>();
		final String sql = getQuery("OCDEDEMP_OFF_EMPLOYMENTS_ONCHECKDELETEMASTER_V_OFF_EMP_ADDR_CUR");
		returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams("offenderBookId",
				paramBean.getOffenderBookId(), "employSeq", paramBean.getEmploymentSeq()), Object.class);
		List<String> returnObjList = new ArrayList<>();
		for (Object object : returnObj) {
			returnObjList.add(object.toString());
		}
		return returnObjList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return Addresses
	 */
	public Integer offEmploymentsPreDeleteAddress(final Addresses paramBean) {
		final String sql = getQuery("OCDEDEMP_OFF_EMPLOYMENTS_PREDELETEA");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("offenderBookId", paramBean.getOwnerId(), "employSeq", paramBean.getOwnerSeq()),
				Object.class);
		String count = returnList.get(0).toString();
		return Integer.valueOf(count);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return Phones
	 */
	public Integer offEmploymentsPreDeletePhones(final Phones paramBean) {
		final String sql = getQuery("OCDEDEMP_OFF_EMPLOYMENTS_PREDELETEP");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("offenderBookId", paramBean.getOwnerId(), "employSeq", paramBean.getOwnerSeq()),
				Object.class);
		String count = returnList.get(0).toString();
		return Integer.valueOf(count);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return InternetAddresses
	 */
	public Integer offEmploymentsPreDeleteInternetAddress(final InternetAddresses paramBean) {
		final String sql = getQuery("OCDEDEMP_OFF_EMPLOYMENTS_PREDELETEIA");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("offenderBookId", paramBean.getOwnerId(), "employSeq", paramBean.getOwnerSeq()),
				Object.class);
		String count = returnList.get(0).toString();
		return Integer.valueOf(count);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCityRgroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGCITY");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgCityRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCountyRgroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGCOUNTY");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgCountyRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCountryRgroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGCOUNTRY");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		 List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgCountryRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgTypeRgroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgTypeRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSpecialNeedsRgroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGSPECIALNEEDS");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgSpecialNeedsRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgProvStateCodeRgroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGPROVSTATECODE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgProvStateCodeRgroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStreetDirRgroup() {
		final String sql = getQuery("OCDOAPOP_FIND_RGSTREETDIR");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgStreetDirRgroup",e);
		}
		return refList;
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param paramBean
	 * @return List<Addresses>
	 */
	public List<Addresses> addressSearchAddresses(final Addresses paramBean) {
		final String sql = getQuery("OCDOAPOP_ADDRESS_FIND_ADDRESSES");
		final RowMapper<Addresses> AddressesRowMapper = Row2BeanRowMapper.makeMapping(sql, Addresses.class,
				addressesMapping);
		List<Addresses> returnList=new ArrayList<Addresses>();
		 returnList =  namedParameterJdbcTemplate.query(sql,
				createParams("addressId", paramBean.getAddressId()), AddressesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param lstAddresses
	 * @return List<Addresses>
	 */
	public Integer addressInsertAddresses(final List<Addresses> lstAddresses) {
		String sql = getQuery("OCDOAPOP_ADDRESS_INSERT_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param lstAddresses
	 * @return List<Addresses>
	 */
	public Integer addressUpdateAddresses(final List<Addresses> lstAddresses) {
		String sql = getQuery("OCDOAPOP_ADDRESS_UPDATE_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param lstAddresses
	 * @return List<Addresses>
	 */
	public Integer addressDeleteAddresses(final List<Addresses> lstAddresses) {
		String sql = getQuery("OCDOAPOP_ADDRESS_DELETE_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
		}
		try {
			String tableName = "ADDRESSES";
			String whereClause = "ADDRESS_ID = :addressId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			log.error("Exception occured in " + this.getClass().getName() + " in method addressDeleteAddresses", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return ReferenceCodes
	 */
	public ReferenceCodes addressWhenCreateRecordgetCountryDesc() {
		final String sql = getQuery("OCDOAPOP_ADDRESS_WHENCREATERECORD_GET_COUNTRY_DESC");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return Phones
	 */
	public List<Phones> addressKeyDelrecphoneEx(final Phones paramBean) {
		final String sql = getQuery("OCDOAPOP_ADDRESS_KEYDELREC_PHONE_EX");
		final RowMapper<Phones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		 List<Phones> returnList=new ArrayList<Phones>();
	      returnList = namedParameterJdbcTemplate.query(sql,
				createParams("ownerId", paramBean.getOwnerId()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 */
	public Object preInsertaddressIdCur() {
		final String sql = getQuery("OCDOAPOP_OCDOAPOP_PREINSERT_ADDRESS_ID_CUR");
		 Object returnObj=null;
		 returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 */
	public Object validateCityInfogetCityDescription() {
		final String sql = getQuery("OCDOAPOP_VALIDATE_CITY_INFO_GET_CITY_DESCRIPTION_C");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> validateCityInfogetCityCode() {
		final String sql = getQuery("OCDOAPOP_VALIDATE_CITY_INFO_GET_CITY_CODE_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
	     List<ReferenceCodes> returnList=new ArrayList<ReferenceCodes>();
		 returnList = namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return SystemProfiles
	 */
	public SystemProfiles citySystemProfilecityProfileCur() {
		final String sql = getQuery("OCDOAPOP_CITY_SYSTEM_PROFILE_CITY_PROFILE_CUR");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		  SystemProfiles returnObj =null;
		  returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	public Integer offEducationsgetMaxBookIdEduSeq(final Long offenderBookId) {
		final String sql = getQuery("OCDEDEMP_OFF_EDUCATIONS_MAX_VALUE");
		Integer refList;
		refList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
				Integer.class);
		return refList;

	}

	public Integer offEmploymentsgetMaxBookIdEmpSeq(final Long offenderBookId) {
		final String sql = getQuery("OCDEDEMP_OFF_EMPLOYMENTS_MAX_VALUE");
		Integer refList;
		refList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
				Integer.class);
		return refList;

	}

	
}
