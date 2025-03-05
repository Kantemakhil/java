package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.OidstabsRepository;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VAddressUsages;
import net.syscon.s4.inst.schedules.bean.VAgencyAddresses;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VPhones;
import oracle.jdbc.OracleTypes;

/**
 * Class OidstabsRepositoryImpl
 */
@Repository
public class OidstabsRepositoryImpl extends RepositoryBase implements OidstabsRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstabsRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vAddressUsagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_USAGE", new FieldMapper("addressUsage"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber"))
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("HOUSE", new FieldMapper("house"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("COUNTRY", new FieldMapper("country"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress")).build();
	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> vPhonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PHONE_ID", new FieldMapper("phoneId"))
			.put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_CODE", new FieldMapper("ownerCode"))
			.put("EXT_NO", new FieldMapper("extNo"))
			.put("PHONE_TYPE", new FieldMapper("phoneType"))
			.put("PHONE_NO", new FieldMapper("phoneNo"))
			.put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("PHONE_AREA", new FieldMapper("phoneArea"))
			.put("TO_ADDRESS_ID", new FieldMapper("toAddressId"))
			.put("FORMAT", new FieldMapper("format"))
			.put("OWNER_ID", new FieldMapper("ownerId"))
			.build();
	private final Map<String, FieldMapper> vOffenderAllSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("SICK_NOTE_EXPIRY_DATE", new FieldMapper("sickNoteExpiryDate"))
			.put("TO_INT_LOC_LEVEL_1_CODE", new FieldMapper("toIntLocLevel1Code"))
			.put("LU_LEVEL_3_CODE", new FieldMapper("luLevel3Code"))
			.put("TO_CITY_NAME", new FieldMapper("toCityName"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("TO_ADDRESS_ID", new FieldMapper("toAddressId"))
			.put("UNPAID_WORK_SUPERVISOR", new FieldMapper("unpaidWorkSupervisor"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("UNPAID_WORK_ACTION", new FieldMapper("unpaidWorkAction"))
			.put("AGENCY_IML_LEVEL_1_CODE", new FieldMapper("agencyImlLevel1Code"))
			.put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("TO_INT_LOC_USER_DESC", new FieldMapper("toIntLocUserDesc"))
			.put("EVENT_STATUS_DESC", new FieldMapper("eventStatusDesc"))
			.put("TO_INTERNAL_LOCATION_DESC", new FieldMapper("toInternalLocationDesc"))
			.put("AGY_LOC_DESC", new FieldMapper("agyLocDesc"))
			.put("LU_LEVEL_2_CODE", new FieldMapper("luLevel2Code"))
			.put("FROM_CITY_CODE", new FieldMapper("fromCityCode"))
			.put("EVENT_SUB_TYPE", new FieldMapper("eventSubType"))
			.put("DETAILS", new FieldMapper("details"))
			.put("CHECK_BOX_1", new FieldMapper("checkBox1"))
			.put("BUSY_DATE_FLAG", new FieldMapper("busyDateFlag"))
			.put("EVENT_OUTCOME_DESC", new FieldMapper("eventOutcomeDesc"))
			.put("SCHEDULED_TRIP_ID", new FieldMapper("scheduledTripId"))
			.put("EVENT_SUB_TYPE_DESC", new FieldMapper("eventSubTypeDesc"))
			.put("HIDDEN_COMMENT_TEXT", new FieldMapper("hiddenCommentText"))
			.put("IN_CHARGE_STAFF_NAME", new FieldMapper("inChargeStaffName"))
			.put("TO_INT_LOC_LEVEL_2_CODE", new FieldMapper("toIntLocLevel2Code"))
			.put("TRANSPORT_CODE", new FieldMapper("transportCode"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("EVENT_TYPE_DESC", new FieldMapper("eventTypeDesc"))
			.put("AGENCY_IML_LEVEL_2_CODE", new FieldMapper("agencyImlLevel2Code"))
			.put("IN_TIME", new FieldMapper("inTime"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("OFF_PRGREF_ID", new FieldMapper("offPrgrefId"))
			.put("SICK_NOTE_RECEIVED_DATE", new FieldMapper("sickNoteReceivedDate"))
			.put("CONTACT_PERSON_NAME", new FieldMapper("contactPersonName"))
			.put("OUT_TIME", new FieldMapper("outTime"))
			.put("AGREED_TRAVEL_HOUR", new FieldMapper("agreedTravelHour"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("EVENT_TYPE", new FieldMapper("eventType"))
			.put("REFERENCE_ID", new FieldMapper("referenceId"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("LU_LEVEL_1_CODE", new FieldMapper("luLevel1Code"))
			.put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("AGENCY_IML_DESC", new FieldMapper("agencyImlDesc"))
			.put("TO_INT_LOC_LEVEL_3_CODE", new FieldMapper("toIntLocLevel3Code"))
			.put("CHECK_BOX_2", new FieldMapper("checkBox2"))
			.put("TO_AGY_LOC_DESC", new FieldMapper("toAgyLocDesc"))
			.put("END_TIME", new FieldMapper("endTime"))
			.put("TO_LOC", new FieldMapper("toLoc"))
			.put("EVENT_CLASS", new FieldMapper("eventClass"))
			.put("RETURN_DATE", new FieldMapper("returnDate"))
			.put("EVENT_PURPOSE", new FieldMapper("eventPurpose"))
			.build();
	private final Map<String, FieldMapper> mmmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.build();
	private final Map<String, FieldMapper> vCorporateAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STREET_NUMBER", new FieldMapper("streetNumber"))
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode"))
			.put("HOUSE", new FieldMapper("house"))
			.put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("COUNTRY", new FieldMapper("country"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress")).build();
	private final Map<String, FieldMapper> vAgencyAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber"))
			.put("MAIL_CARE_OF", new FieldMapper("mailCareOf"))
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode"))
			.put("HOUSE", new FieldMapper("house"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("AGY_LOC_ID_DESC", new FieldMapper("agyLocIdDesc"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress")).build();
	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("DOMAIN", new FieldMapper("domain"))
			.put("PARENT_DOMAIN", new FieldMapper("parentDomain"))
			.put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADMISSION_TYPE", new FieldMapper("admissionType"))
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("MOVE_RSN1.MOVEMENT_REASON_CODE", new FieldMapper("moveRsn1.movementReasonCode"))
			.put("MOVE_RSN1.DESCRIPTION", new FieldMapper("moveRsn1.description"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("OEM.MOVEMENT_REASON_CODE", new FieldMapper("oem.movementReasonCode"))
			.put("LIV_UNITS.DESCRIPTION", new FieldMapper("livUnits.description"))
			.put("MR1.IN_MOVEMENT_REASON_CODE", new FieldMapper("mr1.inMovementReasonCode"))
			.put("RECORD_STATUS", new FieldMapper("recordStatus"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("DSP_DESCRIPTION4", new FieldMapper("dspDescription4"))
			.put("MR2.DESCRIPTION", new FieldMapper("mr2.description"))
			.build();
	private final Map<String, FieldMapper> corporateMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("CORPORATE_NAME)", new FieldMapper("corporateName"))
			.put(" CASELOAD_ID", new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("VERIFIED_FLAG", new FieldMapper("verifiedFlag"))
			.put("PARENT_OFFENDER_ID", new FieldMapper("parentOffenderId"))
			.put("SUFFIX", new FieldMapper("suffix"))
			.put("OFFENDER_NAME_SEQ", new FieldMapper("offenderNameSeq"))
			.put("LAST_NAME_SOUNDEX", new FieldMapper("lastNameSoundex"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("ADD_INFO_CODE", new FieldMapper("addInfoCode"))
			.put("MIDDLE_NAME_2", new FieldMapper("middleName2"))
			.put("LAST_NAME_ALPHA_KEY", new FieldMapper("lastNameAlphaKey"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("BIRTH_STATE", new FieldMapper("birthState"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("BIRTH_COUNTY", new FieldMapper("birthCounty"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("UNIQUE_OBLIGATION_FLAG", new FieldMapper("uniqueObligationFlag"))
			.put("ID_SOURCE_CODE", new FieldMapper("idSourceCode"))
			.put("REMARK_CODE", new FieldMapper("remarkCode"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("IDENTIFIER_TYPE", new FieldMapper("identifierType"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))
			.put("NAME_TYPE", new FieldMapper("nameType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SUSPENDED_DATE", new FieldMapper("suspendedDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("BIRTH_COUNTRY_CODE", new FieldMapper("birthCountryCode"))
			.put("MIDDLE_NAME_KEY", new FieldMapper("middleNameKey"))
			.put("NAME_SEQUENCE", new FieldMapper("nameSequence"))
			.put("TITLE", new FieldMapper("title"))
			.put("SEX_CODE", new FieldMapper("sexCode"))
			.put("BIRTH_PLACE", new FieldMapper("birthPlace"))
			.put("LAST_NAME_KEY", new FieldMapper("lastNameKey"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("OFFENDER_ID_SEQ", new FieldMapper("offenderIdSeq"))
			.put("ISSUED_AUTHORITY_TEXT", new FieldMapper("issuedAuthorityText"))
			.put("RACE_CODE", new FieldMapper("raceCode"))
			.put("ALIAS_NAME_TYPE", new FieldMapper("aliasNameType"))
			.put("ISSUED_DATE", new FieldMapper("issuedDate"))
			.put("IDENTIFIER", new FieldMapper("identifier"))
			.put("CREATE_DATE", new FieldMapper("createDate"))
			.put("FIRST_NAME_KEY", new FieldMapper("firstNameKey"))
			.put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("AGE", new FieldMapper("age"))
			.build();

	/**
	 * Creates new OidstabsRepositoryImpl class Object
	 */
	public OidstabsRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 * @
	 */
	public List<VOffenderAllSchedules> offSchedulesExecuteQuery(final VOffenderAllSchedules objSearchDao) {
		logger.info(logger.getName().getClass()+" offSchedulesExecuteQuery exeution method start");
		final String sql = getQuery("OIDSTABS_OFFSCHEDULES_FIND_V_OFFENDER_ALL_SCHEDULES");
		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId()),
							VOffenderAllSchedulesRowMapper);
			
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesExecuteQuery error", e);
			return Collections.emptyList();
		}
		
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVOffenderAllSchedules
	 *            List<VOffenderAllSchedules>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offSchedulesInsertVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffenderIndSchedules) {
		logger.info(logger.getName().getClass()+" offSchedulesInsertVOffenderAllSchedules exeution method start");

		final String sql = getQuery("OIDSTABS_INSERT_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules offenderIndSchedules : lstOffenderIndSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(offenderIndSchedules));
		}
		try {
			logger.info(logger.getName().getClass()+" offSchedulesInsertVOffenderAllSchedules insert operation start");
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" offSchedulesInsertVOffenderAllSchedules insert operation end");
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesInsertVOffenderAllSchedules error", e);	
		}
		if (lstOffenderIndSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderAllSchedules
	 *            List<VOffenderAllSchedules>
	 *
	 * @
	 */
	public Integer offSchedulesUpdateVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffenderIndSchedules) {
		logger.info(logger.getName().getClass()+" offSchedulesUpdateVOffenderAllSchedules exeution method start");
		final String sql = getQuery("OIDSTABS_PRE_UPDATE_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules offenderIndSchedules : lstOffenderIndSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(offenderIndSchedules));
		}
		try {
			logger.info(logger.getName().getClass()+" offSchedulesUpdateVOffenderAllSchedules exeution method start");
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" offSchedulesUpdateVOffenderAllSchedules exeution method end");	
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesUpdateVOffenderAllSchedules error", e);	
		}
		if (lstOffenderIndSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVOffenderAllSchedules
	 *            List<VOffenderAllSchedules>
	 *
	 * @
	 */
	public Integer offSchedulesDeleteVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffenderIndSchedules) {
		Integer returnValue;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SCHEDULE").withProcedureName("DELETE_SCHEDULE").declareParameters(sqlParameters);
		for (final OffenderIndSchedules obj : lstOffenderIndSchedules) {
			inParamMap.put("P_EVENT_ID", obj.getEventId());
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			returnObject = simpleJDBCCall.execute(inParameter);
		}
		if (returnObject != null && returnObject.size() == 0) {
			returnValue = 1;
		} else {
			returnValue = 0;
		}
		return returnValue;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VAgencyAddresses
	 *
	 * @return List<VAgencyAddresses>
	 *
	 * @
	 */
	public List<VAgencyAddresses> agyAdrExecuteQuery(final VAgencyAddresses objSearchDao) {
		logger.info(logger.getName().getClass()+" agyAdrExecuteQuery exeution method start");
		final String sql = getQuery("OIDSTABS_AGYADR_FIND_V_AGENCY_ADDRESSES");
		final RowMapper<VAgencyAddresses> VAgencyAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VAgencyAddresses.class, vAgencyAddressesMapping);
	   try {
		return  namedParameterJdbcTemplate
					.query(sql, createParams("ADDRESS_ID", objSearchDao.getAddressId()), VAgencyAddressesRowMapper);
	} catch (Exception e) {
		logger.error(this.getClass().getName()+"In method agyAdrExecuteQuery error", e);
		return Collections.emptyList();
	}
		
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVAgencyAddresses
	 *            List<VAgencyAddresses>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer agyAdrInsertVAgencyAddresses(final List<VAgencyAddresses> lstVAgencyAddresses) {
		logger.info(logger.getName().getClass()+" agyAdrInsertVAgencyAddresses exeution method start");
		final String sql = getQuery("OIDSTABS_AGYADR_INSERT_V_AGENCY_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			logger.info(logger.getName().getClass()+" agyAdrInsertVAgencyAddresses exeution method start");
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" agyAdrInsertVAgencyAddresses exeution method end");
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method agyAdrInsertVAgencyAddresses error", e);
		}
		if (lstVAgencyAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVAgencyAddresses
	 *            List<VAgencyAddresses>
	 *
	 * @
	 */
	public Integer agyAdrUpdateVAgencyAddresses(final List<VAgencyAddresses> lstVAgencyAddresses) {
		logger.info(logger.getName().getClass()+" agyAdrUpdateVAgencyAddresses exeution method start");
		final String sql = getQuery("OIDSTABS_AGYADR_UPDATE_V_AGENCY_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VAgencyAddresses vAgencyAddresses : lstVAgencyAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(vAgencyAddresses));
		}
		try {
			logger.info(logger.getName().getClass()+" agyAdrUpdateVAgencyAddresses exeution method start");
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" agyAdrUpdateVAgencyAddresses exeution method end");
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method agyAdrUpdateVAgencyAddresses error", e);

		}
		if (lstVAgencyAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VCorporateAddresses
	 *
	 * @return List<VCorporateAddresses>
	 *
	 * @
	 */
	public List<VCorporateAddresses> busAdrExecuteQuery(final VCorporateAddresses objSearchDao) {
		logger.info(logger.getName().getClass()+" agyAdrUpdateVAgencyAddresses exeution method start");
		final String sql = getQuery("OIDSTABS_BUSADR_FIND_V_CORPORATE_ADDRESSES");
		final RowMapper<VCorporateAddresses> VCorporateAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VCorporateAddresses.class, vCorporateAddressesMapping);
		try {
			return namedParameterJdbcTemplate
					.query(sql, createParams("ADDRESS_ID", objSearchDao.getAddressId()), VCorporateAddressesRowMapper);
		} catch (Exception  e) {
			logger.error(this.getClass().getName()+"In method busAdrExecuteQuery error", e);
			return Collections.emptyList();
		}
		
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVCorporateAddresses
	 *            List<VCorporateAddresses>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer busAdrInsertVCorporateAddresses(final List<VCorporateAddresses> lstVCorporateAddresses) {
		logger.info(logger.getName().getClass()+" busAdrInsertVCorporateAddresses exeution method start");
		final String sql = getQuery("OIDSTABS_BUSADR_INSERT_V_CORPORATE_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			logger.info(logger.getName().getClass()+" busAdrInsertVCorporateAddresses exeution method start");
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" busAdrInsertVCorporateAddresses exeution method end");

		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method busAdrInsertVCorporateAddresses error", e);
		}
		if (lstVCorporateAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVCorporateAddresses
	 *            List<VCorporateAddresses>
	 *
	 * @
	 */
	public Integer busAdrUpdateVCorporateAddresses(final List<VCorporateAddresses> lstVCorporateAddresses) {
		logger.info(logger.getName().getClass()+" busAdrUpdateVCorporateAddresses exeution method start");
		final String sql = getQuery("OIDSTABS_BUSADR_UPDATE_V_CORPORATE_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VCorporateAddresses vCorporateAddresses : lstVCorporateAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(vCorporateAddresses));
		}
		try {
			logger.info(logger.getName().getClass()+" busAdrUpdateVCorporateAddresses exeution method start");
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" busAdrUpdateVCorporateAddresses exeution method end");

		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method busAdrUpdateVCorporateAddresses error", e);
		}
		if (lstVCorporateAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVCorporateAddresses
	 *            List<VCorporateAddresses>
	 *
	 * @
	 */
	public Integer busAdrDeleteVCorporateAddresses(final List<VCorporateAddresses> lstVCorporateAddresses) {
		logger.info(logger.getName().getClass()+" busAdrDeleteVCorporateAddresses exeution method start");
		final String sql = getQuery("OIDSTABS_BUSADR_DELETE_V_CORPORATE_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VCorporateAddresses vCorporateAddresses : lstVCorporateAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(vCorporateAddresses));
		}
		try {
			String tableName = "V_CORPORATE_ADDRESSES";
			batchUpdatePreDeletedRows(tableName, null, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			logger.info(logger.getName().getClass()+" busAdrDeleteVCorporateAddresses exeution method start");
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" busAdrDeleteVCorporateAddresses exeution method end");
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method busAdrDeleteVCorporateAddresses error", e);

		}
		if (lstVCorporateAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VAddressUsages
	 *
	 * @return List<VAddressUsages>
	 *
	 * @
	 */
	public List<VAddressUsages> othAdrExecuteQuery(final VAddressUsages objSearchDao) {
		logger.info(logger.getName().getClass()+" othAdrExecuteQuery exeution method start");
		final String sql = getQuery("OIDSTABS_OTHADR_FIND_V_ADDRESS_USAGES");
		final RowMapper<VAddressUsages> VAddressUsagesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VAddressUsages.class, vAddressUsagesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("ADDRESS_ID", objSearchDao.getAddressId()), VAddressUsagesRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" othAdrExecuteQuery error", e);
			return Collections.emptyList();
		}
	
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVAddressUsages
	 *            List<VAddressUsages>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer othAdrInsertVAddressUsages(final List<VAddressUsages> lstVAddressUsages) {
		logger.info(logger.getName().getClass()+" othAdrInsertVAddressUsages exeution method start");
		final String sql = getQuery("OIDSTABS_OTHADR_INSERT_V_ADDRESS_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			logger.info(logger.getName().getClass()+" othAdrInsertVAddressUsages exeution method start");	
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" othAdrInsertVAddressUsages exeution method end");
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" othAdrInsertVAddressUsages error", e);
		}
		if (lstVAddressUsages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVAddressUsages
	 *            List<VAddressUsages>
	 *
	 * @
	 */
	public Integer othAdrUpdateVAddressUsages(final List<VAddressUsages> lstVAddressUsages) {
		logger.info(logger.getName().getClass()+" othAdrUpdateVAddressUsages exeution method start");
		final String sql = getQuery("OIDSTABS_OTHADR_UPDATE_V_ADDRESS_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VAddressUsages vAddressUsages : lstVAddressUsages) {
			parameters.add(new BeanPropertySqlParameterSource(vAddressUsages));
		}
		try {
			logger.info(logger.getName().getClass()+" othAdrUpdateVAddressUsages exeution method start");
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" othAdrUpdateVAddressUsages exeution method end");
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" othAdrUpdateVAddressUsages error", e);

		}
		if (lstVAddressUsages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVAddressUsages
	 *            List<VAddressUsages>
	 *
	 * @
	 */
	public Integer othAdrDeleteVAddressUsages(final List<VAddressUsages> lstVAddressUsages) {
		logger.info(logger.getName().getClass()+" othAdrDeleteVAddressUsages exeution method start");
		final String sql = getQuery("OIDSTABS_OTHADR_DELETE_V_ADDRESS_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VAddressUsages vAddressUsages : lstVAddressUsages) {
			parameters.add(new BeanPropertySqlParameterSource(vAddressUsages));
		}
		try {
			String tableName = "V_ADDRESS_USAGES";
			batchUpdatePreDeletedRows(tableName, null, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			logger.info(logger.getName().getClass()+" othAdrDeleteVAddressUsages exeution method start");
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" othAdrDeleteVAddressUsages exeution method end");
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" othAdrDeleteVAddressUsages error", e);
		}
		if (lstVAddressUsages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VPhones
	 *
	 * @return List<VPhones>
	 *
	 * @
	 */
	public List<VPhones> agyPhonesExecuteQuery(final VPhones objSearchDao) {
		logger.info(logger.getName().getClass()+" agyPhonesExecuteQuery exeution method start");
		final String sql = getQuery("OIDSTABS_AGYPHONES_FIND_V_PHONES");
		final RowMapper<VPhones> VPhonesRowMapper = Row2BeanRowMapper.makeMapping(sql, VPhones.class, vPhonesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("OWNER_ID", objSearchDao.getOwnerId()), VPhonesRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" agyPhonesExecuteQuery error", e);
			return Collections.emptyList();
		}
		
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<MovementReasons> rgSubTypeRecordGroup(String type) {
		logger.info(logger.getName().getClass()+" rgSubTypeRecordGroup exeution method start");
		final String sql = getQuery("OIDSTABS_FIND_RGSUBTYPE");
		final RowMapper<MovementReasons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				movementReasonsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", "ENTER-QUERY", "movementType", type), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method rgSubTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgEscortRecordGroup() {
		logger.info(logger.getName().getClass()+" rgEscortRecordGroup exeution method start");
		final String sql = getQuery("OIDSTABS_FIND_RGESCORT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", "ENTER-QUERY"), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method rgEscortRecordGroup error", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgTransportRecordGroup() {
		logger.info(logger.getName().getClass()+" rgTransportRecordGroup exeution method start");
		final String sql = getQuery("OIDSTABS_FIND_RGTRANSPORT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method rgTransportRecordGroup error", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		logger.info(logger.getName().getClass()+" rgStatusRecordGroup exeution method start");
		final String sql = getQuery("OIDSTABS_FIND_RGSTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", "ENTER-QUERY"), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method rgStatusRecordGroup error", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<VCorporateAddresses> rgCorpLocRecordGroup() {
		logger.info(logger.getName().getClass()+" rgCorpLocRecordGroup exeution method start");
		final String sql = getQuery("OIDSTABS_FIND_RGCORPLOC");
		final RowMapper<VCorporateAddresses> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, VCorporateAddresses.class,
				vCorporateAddressesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method rgCorpLocRecordGroup error", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMM>
	 */
	public List<VAgencyAddresses> rgAgyLocRecordGroup() {
		logger.info(logger.getName().getClass()+" rgAgyLocRecordGroup exeution method start");
		final String sql = getQuery("OIDSTABS_FIND_RGAGYLOC");
		final RowMapper<VAgencyAddresses> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, VAgencyAddresses.class,
				vAgencyAddressesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMMRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method rgAgyLocRecordGroup error", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMMM>
	 */
	public List<VAddressUsages> rgOthLocRecordGroup(final String rootOffenderId) {
		logger.info(logger.getName().getClass()+" rgOthLocRecordGroup exeution method start");
		final String sql = getQuery("OIDSTABS_FIND_RGOTHLOC");
		final RowMapper<VAddressUsages> adrUsageRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddressUsages.class,
				vAddressUsagesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("ROOTOFFENDERID", rootOffenderId),
					adrUsageRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method rgOthLocRecordGroup error", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(final VOffenderAllSchedules paramBean) {
		logger.info(logger.getName().getClass()+" offBkgOnCheckDeleteMaster exeution method start");
		final String sql = getQuery("OIDSTABS_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<VOffenderAllSchedules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offBkgOnCheckDeleteMaster error", e);
			return Collections.emptyList();
		}
		
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchedulesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VAgencyAddresses> offSchedulesOnCheckDeleteMaster(final VAgencyAddresses paramBean) {
		logger.info(logger.getName().getClass()+" offSchedulesOnCheckDeleteMaster exeution method start");
		final String sql = getQuery("OIDSTABS_OFF_SCHEDULES_ONCHECKDELETEMASTER");
		final RowMapper<VAgencyAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VAgencyAddresses.class,
				vAgencyAddressesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesOnCheckDeleteMaster error", e);
			return Collections.emptyList();
		}
		
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchedulesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VCorporateAddresses> offSchedulesOnCheckDeleteMaster(final VCorporateAddresses paramBean) {
		logger.info(logger.getName().getClass()+" offSchedulesOnCheckDeleteMaster exeution method start");
		final String sql = getQuery("OIDSTABS_OFF_SCHEDULES_ONCHECKDELETEMASTER");
		final RowMapper<VCorporateAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VCorporateAddresses.class, vCorporateAddressesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesOnCheckDeleteMaster error", e);
			return Collections.emptyList();
		}
		
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchedulesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VAddressUsages> offSchedulesOnCheckDeleteMaster(final VAddressUsages paramBean) {
		logger.info(logger.getName().getClass()+" offSchedulesOnCheckDeleteMaster exeution method start");
		final String sql = getQuery("OIDSTABS_OFF_SCHEDULES_ONCHECKDELETEMASTER");
		final RowMapper<VAddressUsages> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddressUsages.class,
				vAddressUsagesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesOnCheckDeleteMaster error", e);
			return Collections.emptyList();
		}
		
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchedulesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VPhones> offSchedulesOnCheckDeleteMaster(final VPhones paramBean) {
		logger.info(logger.getName().getClass()+" offSchedulesOnCheckDeleteMaster exeution method start");
		final String sql = getQuery("OIDSTABS_OFF_SCHEDULES_ONCHECKDELETEMASTER");
		final RowMapper<VPhones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VPhones.class, vPhonesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(),columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesOnCheckDeleteMaster error", e);
			return Collections.emptyList();
		}
	
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * offSchedulesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VPhones> offSchedulesOnCheckDeleteMasterDetails(final VPhones paramBean) {
		logger.info(logger.getName().getClass()+" offSchedulesOnCheckDeleteMasterDetails exeution method start");
		final String sql = getQuery("OIDSTABS_OFF_SCHEDULES_ONCHECKDELETEMASTER");
		final RowMapper<VPhones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VPhones.class, vPhonesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(),columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesOnCheckDeleteMasterDetails error", e);
			return Collections.emptyList();
		}
		
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * offSchedulesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VPhones> offSchedulesOnCheckDeleteMasterDuplicate(final VPhones paramBean) {
		logger.info(logger.getName().getClass()+" offSchedulesOnCheckDeleteMasterDuplicate exeution method start");
		final String sql = getQuery("OIDSTABS_OFF_SCHEDULES_ONCHECKDELETEMASTER");
		final RowMapper<VPhones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VPhones.class, vPhonesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(),columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method offSchedulesOnCheckDeleteMasterDuplicate error", e);
			return Collections.emptyList();
		}
		
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateOthAddress
	 *
	 * @param params
	 *
	 */
	public List<VAddressUsages> populateOthAddress(final VAddressUsages paramBean) {
		logger.info(logger.getName().getClass()+" populateOthAddress exeution method start");
		final String sql = getQuery("OIDSTABS_POPULATE_OTH_ADDRESS");
		final RowMapper<VAddressUsages> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddressUsages.class,
				vAddressUsagesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method populateOthAddress error", e);
			return Collections.emptyList();
		}
		
	}

	public List<VPhones> busPhonesExecuteQuery(final VPhones searchRecord) {
		logger.info(logger.getName().getClass()+" busPhonesExecuteQuery exeution method start");
		final String sql = getQuery("OIDSTABS_AGYPHONES_FIND_V_PHONES");
		final RowMapper<VPhones> VPhonesRowMapper = Row2BeanRowMapper.makeMapping(sql, VPhones.class, vPhonesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("OWNER_ID", searchRecord.getOwnerId()), VPhonesRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method populateOthAddress error", e);
			return Collections.emptyList();
		}
		
	}

	public List<VPhones> othPhonesExecuteQuery(final VPhones searchRecord) {
		logger.info(logger.getName().getClass()+" othPhonesExecuteQuery exeution method start");
		final String sql = getQuery("OIDSTABS_AGYPHONES_FIND_V_PHONES");
		final RowMapper<VPhones> VPhonesRowMapper = Row2BeanRowMapper.makeMapping(sql, VPhones.class, vPhonesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("OWNER_ID", searchRecord.getOwnerId()), VPhonesRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method othPhonesExecuteQuery error", e);
			return Collections.emptyList();
		}
		
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param eventDate
	 * 
	 * @param returnDate
	 *
	 * @return Integer    eventDate
	 *
	 */
	public Integer calculateDays(final String eventDate, final String returnDate) {
		logger.info(logger.getName().getClass()+" calculateDays exeution method start");
		Integer returnValue = 0;
		final String sql = "SELECT COALESCE(TO_DATE('"+returnDate+"','DD/MM/YYYY')-(TO_DATE('"+eventDate+"','DD/MM/YYYY')),0) FROM DUAL";
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("RETURN_DATE", returnDate, "EVENT_DATE", eventDate), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method calculateDays error", e);
		}
		return returnValue;

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param eventDate
	 * 
	 * @param returnDate
	 *
	 * @return Integer
	 *
	 */
	public Object calculateHours( String startTime,  String returnTime) {
		logger.info(logger.getName().getClass()+" calculateHours exeution method start");
		Object returnValue = 0;
		final String sql = getQuery("OIDSTABS_CALUCULATE_HOURS_OUT");

		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("RETURN_TIME", returnTime, "START_TIME", startTime), Object.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+"calculateHours error",e);
		}
		return returnValue;

	}

	/**
	 * This method is used to get max OIC_NOTICE_SEQ from OIC_HEARING_NOTICES
	 * table
	 * 
	 * getMaxOicNoticeSeq
	 *
	 */
	public Integer getMaxEventid() {
		logger.info(logger.getName().getClass()+" getMaxEventid exeution method start");
		final String sql = getQuery("OIDSTABS_PRE_INSERT_OFFENDER_IND_SCHEDULES");
		Integer returnList=0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"getMaxEventid error",e);
		}
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderAllSchedules
	 *            List<VOffenderAllSchedules>
	 *
	 * @
	 */
	public Integer adressLocationsUpdateQuery(final List<OffenderIndSchedules> lstOffenderIndSchedules) {
		logger.info(logger.getName().getClass()+" adressLocationsUpdateQuery exeution method start");
		Integer result = 0;
		try {
			final String sql = getQuery("OIDSTABS_ADRESS_LOC_UPDATE_OFFENDER_IND_SCHEDULES");
			int[] returnArray = new int[] {};
			final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (final OffenderIndSchedules offenderIndSchedules : lstOffenderIndSchedules) {
				parameters.add(new BeanPropertySqlParameterSource(offenderIndSchedules));
			}
			logger.info(logger.getName().getClass()+" adressLocationsUpdateQuery exeution method start");
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			logger.info(logger.getName().getClass()+" adressLocationsUpdateQuery exeution method end");
			if (lstOffenderIndSchedules.size() == returnArray.length) {
				result = 1;
			} else {
				result = 0;
			}
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in adressLocationsUpdateQuery  ", e);
		}
		return result;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchCheckScheduleConflict
	 *
	 *
	 */
	public Integer offSchCheckScheduleConflict(final OffenderIndSchedules lstOffenderIndSchedules) {
		logger.info(logger.getName().getClass()+" offSchCheckScheduleConflict exeution method start");
		Integer returnList=0;
		try {
			final String sql = getQuery("OIDSTABS_CHECK_SCHEDULE_CONFLICT");
			String preparedSql = null;
			final StringBuffer sqlQuery = new StringBuffer(sql);
			if (lstOffenderIndSchedules.getEventId() != null) {
				sqlQuery.append("  AND (EVENT_ID <> :EVENT_ID or EVENT_ID is null) ");
			} else {
				sqlQuery.append("  AND (EVENT_ID is not null or EVENT_ID is null) ");
			}
			preparedSql = sqlQuery.toString().trim();

			returnList = namedParameterJdbcTemplate.queryForObject(preparedSql,
					createParams("OFFENDER_BOOK_ID", lstOffenderIndSchedules.getOffenderBookId(), "EVENT_ID",
							lstOffenderIndSchedules.getEventId(), "EVENT_DATE", lstOffenderIndSchedules.getEventDate(), "RETURN_DATE", lstOffenderIndSchedules.getReturnDate()),
					Integer.class);
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in offSchCheckScheduleConflict ", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createSchedule
	 *
	 *
	 */
	public Integer createSchedule(final OffenderIndSchedules vOffAllSch) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[70];
		Integer value = 0;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SCHEDULE_REC", OracleTypes.CURSOR),
				new SqlOutParameter("P_EVENT_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SCHEDULE").withProcedureName("CREATE_SCHEDULE").declareParameters(sqlParameters);
		inParamMap.put("P_SCHEDULE_REC", vOffAllSch);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			value = simpleJDBCCall.executeObject(Integer.class, inParameter);
		} catch (Exception e) {
			logger.error("In method createSchedule", e);
		}
		return value;
	}

	@Override
	public List<OffenderIndSchedules> checkOffenderScheduleConflicts(BigDecimal offenderBookId,
			OffenderIndSchedules vOffSch) {
		logger.info(logger.getName().getClass()+" checkOffenderScheduleConflicts exeution method start");
		final String sql = getQuery("OIDSTABS_CHECK_SCHEDULE_CONFLICTS");
		List<OffenderIndSchedules> returnList = new ArrayList();
		final RowMapper<OffenderIndSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderIndSchedules.class, referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookID",offenderBookId,"eventDate",vOffSch.getEventDate(),
					"returnDate",vOffSch.getReturnDate()),rowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"in checkOffenderScheduleConflicts method error: ", e);   
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> rgPurposeRecordGroup(String reason) {
			logger.info(logger.getName().getClass()+" rgPurposeRecordGroup exeution method start");
			final String sql = getQuery("OIDSTABS_FIND_RGPURPOSE");
			final RowMapper<ReferenceCodes> rRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					referenceCodeMapping);
			try {
				return namedParameterJdbcTemplate.query(sql, createParams( "reason", reason), rRowMapper);
			} catch (Exception e) {
				logger.error(this.getClass().getName()+"In method rgPurposeRecordGroup", e);
				return Collections.emptyList();
			}
		}
}
