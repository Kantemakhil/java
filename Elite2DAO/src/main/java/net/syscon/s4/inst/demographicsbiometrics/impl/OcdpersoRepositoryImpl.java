package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
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
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.PersonEmployments;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.VPersonAddress;
import net.syscon.s4.inst.demographicsbiometrics.OcdpersoRepository;
import net.syscon.s4.pkgs.tag_person_search.TagPersonSearchRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class OcdpersoRepositoryImpl extends RepositoryBase implements OcdpersoRepository {
	@Autowired
	private TagPersonSearchRepository tagpersonsearchrepository;
	private final Map<String, FieldMapper> personEmploymentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WAGE_PERIOD_CODE", new FieldMapper("wagePeriodCode")).put("ADDRESS_1", new FieldMapper("address1"))
			.put("CONTACT_TYPE", new FieldMapper("contactType"))
			.put("TERMINATION_DATE", new FieldMapper("terminationDate")).put("CITY", new FieldMapper("city"))
			.put("CONTACT_NUMBER", new FieldMapper("contactNumber")).put("PERSON_ID", new FieldMapper("personId"))
			.put("ADDRESS", new FieldMapper("address")).put("WAGE", new FieldMapper("wage"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("OCCUPATION_CODE", new FieldMapper("occupationCode")).put("HOURS_WEEK", new FieldMapper("hoursWeek"))
			.put("EMPLOYMENT_DATE", new FieldMapper("employmentDate")).put("ADDRESS_2", new FieldMapper("address2"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SCHEDULE_TYPE", new FieldMapper("scheduleType"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("EMPLOYER_NAME", new FieldMapper("employerName")).put("PHONE_AREA", new FieldMapper("phoneArea"))
			.put("EMPLOYMENT_SEQ", new FieldMapper("employmentSeq"))
			.put("SUPERVISOR_NAME", new FieldMapper("supervisorName")).put("PHONE_EXT", new FieldMapper("phoneExt"))
			.build();
	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("CORONER_NUMBER", new FieldMapper("coronerNumber"))
			.put("ATTENTION", new FieldMapper("attention")).put("LAST_NAME_SOUNDEX", new FieldMapper("lastNameSoundex"))
			.put("MIDDLE_NAME", new FieldMapper("middleName")).put("PERSON_ID", new FieldMapper("personId"))
			.put("CITIZENSHIP", new FieldMapper("citizenship"))
			.put("CRIMINAL_HISTORY_TEXT", new FieldMapper("criminalHistoryText"))
			.put("LANGUAGE_CODE", new FieldMapper("languageCode")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("PRIMARY_LANGUAGE_CODE", new FieldMapper("primaryLanguageCode"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("EMPLOYER", new FieldMapper("employer")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))
			.put("OCCUPATION_CODE", new FieldMapper("occupationCode")).put("NAME_TYPE", new FieldMapper("nameType"))
			.put("COMPREHEND_ENGLISH_FLAG", new FieldMapper("comprehendEnglishFlag"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("MEMO_TEXT", new FieldMapper("memoText"))
			.put("DECEASED_DATE", new FieldMapper("deceasedDate"))
			.put("SUSPENDED_DATE", new FieldMapper("suspendedDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MIDDLE_NAME_KEY", new FieldMapper("middleNameKey"))
			.put("REMITTER_FLAG", new FieldMapper("remitterFlag")).put("BIRTHDATE", new FieldMapper("birthdate"))
			.put("BIRTH_PLACE", new FieldMapper("birthPlace")).put("LAST_NAME_KEY", new FieldMapper("lastNameKey"))
			.put("ALIAS_PERSON_ID", new FieldMapper("aliasPersonId")).put("CARE_OF", new FieldMapper("careOf"))
			.put("STAFF_FLAG", new FieldMapper("staffFlag")).put("ROOT_PERSON_ID", new FieldMapper("rootPersonId"))
			.put("FIRST_NAME_KEY", new FieldMapper("firstNameKey"))
			.put("MARITAL_STATUS", new FieldMapper("maritalStatus")).put("SEX", new FieldMapper("sex"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("INTERPRETER_REQUIRED", new FieldMapper("interpreterRequired")).build();
	private final Map<String, FieldMapper> offenderContactPersonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("EMERGENCY_CONTACT_FLAG", new FieldMapper("emergencyContactFlag")).build();
	private final Map<String, FieldMapper> vPersonAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("END_DATE", new FieldMapper("endDate")).put("ADDRESS_TYPE_DESC", new FieldMapper("addressTypeDesc"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType")).put("VALIDATED_FLAG", new FieldMapper("validatedFlag"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode")).put("AREA", new FieldMapper("area"))
			.put("PERSON_ID", new FieldMapper("personId")).put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("CITY_NAME", new FieldMapper("cityName")).put("COUNTRY", new FieldMapper("country"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag")).put("STREET", new FieldMapper("street"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("CITY_CODE", new FieldMapper("cityCode"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode")).put("START_DATE", new FieldMapper("startDate"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber")).put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode")).put("HOUSE", new FieldMapper("house")).build();
	private final Map<String, FieldMapper> personIdentifiersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("IDENTIFIER_TYPE", new FieldMapper("identifierType")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("ID_SEQ", new FieldMapper("idSeq")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("ISSUED_DATE", new FieldMapper("issuedDate"))
			.put("IDENTIFIER", new FieldMapper("identifier")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PERSON_ID", new FieldMapper("personId"))
			.put("ISSUED_AUTHORITY_TEXT", new FieldMapper("issuedAuthorityText")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	/**
	 * Creates new OcdpersoRepositoryImpl class Object
	 */
	public OcdpersoRepositoryImpl() {
	}

	private static Logger logger = LogManager.getLogger(OcdpersoRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderContactPersons
	 *
	 * @return List<OffenderContactPersons>
	 *
	 * 
	 */
	public List<OffenderContactPersons> offCntPerExecuteQuery(final OffenderContactPersons objSearchDao) {
		final String sql = getQuery("OCDPERSO_OFFCNTPER_FIND_OFFENDER_CONTACT_PERSONS");
		final RowMapper<OffenderContactPersons> OffenderContactPersonsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderContactPersons.class, offenderContactPersonsMapping);
		final ArrayList<OffenderContactPersons> returnList = (ArrayList<OffenderContactPersons>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", objSearchDao.getOffenderBookId()),
						OffenderContactPersonsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderContactPersons List<OffenderContactPersons>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offCntPerInsertOffenderContactPersons(final List<OffenderContactPersons> lstOffenderContactPersons) {
		final String sql = getQuery("OCDPERSO_OFFCNTPER_INSERT_OFFENDER_CONTACT_PERSONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderContactPersons offenderContactPersons : lstOffenderContactPersons) {
			parameters.add(new BeanPropertySqlParameterSource(offenderContactPersons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderContactPersons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderContactPersons List<OffenderContactPersons>
	 *
	 * 
	 */
	public Integer offCntPerUpdateOffenderContactPersons(final List<OffenderContactPersons> lstOffenderContactPersons) {
		final String sql = getQuery("OCDPERSO_OFFCNTPER_UPDATE_OFFENDER_CONTACT_PERSONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderContactPersons offenderContactPersons : lstOffenderContactPersons) {
			parameters.add(new BeanPropertySqlParameterSource(offenderContactPersons));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offCntPerUpdateOffenderContactPersons: ", e);
		}
		if (lstOffenderContactPersons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderContactPersons List<OffenderContactPersons>
	 *
	 * 
	 */
	public Integer offCntPerDeleteOffenderContactPersons(final List<OffenderContactPersons> lstOffenderContactPersons) {
		final String sql = getQuery("OCDPERSO_OFFCNTPER_DELETE_OFFENDER_CONTACT_PERSONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderContactPersons offenderContactPersons : lstOffenderContactPersons) {
			parameters.add(new BeanPropertySqlParameterSource(offenderContactPersons));
		}
		try {
			String tableName = "OFFENDER_CONTACT_PERSONS";
			String whereClause = "OFFENDER_CONTACT_PERSON_ID = :offenderContactPersonId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offCntPerDeleteOffenderContactPersons", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderContactPersons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VPersonAddresses
	 *
	 * @return List<VPersonAddresses>
	 *
	 * 
	 */
	public List<VPersonAddress> perAddrExecuteQuery(final VPersonAddress objSearchDao) {
		final String sql = getQuery("OCDPERSO_PERADDR_FIND_V_PERSON_ADDRESSES");
		final RowMapper<VPersonAddress> VPersonAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VPersonAddress.class, vPersonAddressesMapping);
		final ArrayList<VPersonAddress> returnList = (ArrayList<VPersonAddress>) namedParameterJdbcTemplate.query(sql,
				createParams("personId", objSearchDao.getPersonId()), VPersonAddressesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao PersonIdentifiers
	 *
	 * @return List<PersonIdentifiers>
	 *
	 * 
	 */
	public List<PersonIdentifiers> perIdentExecuteQuery(final PersonIdentifiers objSearchDao) {
		final String sql = getQuery("OCDPERSO_PERIDENT_FIND_PERSON_IDENTIFIERS");
		final RowMapper<PersonIdentifiers> PersonIdentifiersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PersonIdentifiers.class, personIdentifiersMapping);
		final ArrayList<PersonIdentifiers> returnList = (ArrayList<PersonIdentifiers>) namedParameterJdbcTemplate
				.query(sql, createParams("personId", objSearchDao.getPersonId()), PersonIdentifiersRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstPersonIdentifiers List<PersonIdentifiers>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer perIdentInsertPersonIdentifiers(final List<PersonIdentifiers> lstPersonIdentifiers) {
		Boolean insertFlag = true;
		Long idSeq = (long) 0;
		String sql = getQuery("OCDPERSO_PERIDENT_INSERT_PERSON_IDENTIFIERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (PersonIdentifiers personIdentifiers : lstPersonIdentifiers) {
			if (insertFlag != null && insertFlag.equals(true)) {
				idSeq = tagpersonsearchrepository.getNextIdSeq(personIdentifiers.getPersonId());
				insertFlag = false;
			}
			personIdentifiers.setIdSeq(idSeq);
			idSeq = idSeq + 1;
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersonIdentifiers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}


	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPersonIdentifiers List<PersonIdentifiers>
	 *
	 * 
	 */
	public Integer perIdentUpdatePersonIdentifiers(final List<PersonIdentifiers> lstPersonIdentifiers) {
		String sql = getQuery("OCDPERSO_PERIDENT_UPDATE_PERSON_IDENTIFIERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (PersonIdentifiers personIdentifiers : lstPersonIdentifiers) {
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersonIdentifiers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstPersonIdentifiers List<PersonIdentifiers>
	 *
	 * 
	 */
	public Integer perIdentDeletePersonIdentifiers(final List<PersonIdentifiers> lstPersonIdentifiers) {
		final String sql = getQuery("OCDPERSO_PERIDENT_DELETE_PERSON_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PersonIdentifiers personIdentifiers : lstPersonIdentifiers) {
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
		try {
			String tableName = "PERSON_IDENTIFIERS";
			String whereClause = "PERSON_ID  = :personId AND ID_SEQ  = :idSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method perIdentDeletePersonIdentifiers", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersonIdentifiers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao Persons
	 *
	 * @return List<Persons>
	 *
	 * 
	 */
	public List<Persons> perInfoExecuteQuery(final Persons objSearchDao) {
		final String sql = getQuery("OCDPERSO_PERINFO_FIND_PERSONS");
		final RowMapper<Persons> PersonsRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		final ArrayList<Persons> returnList = (ArrayList<Persons>) namedParameterJdbcTemplate.query(sql,
				createParams("personId", objSearchDao.getPersonId()), PersonsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPersons List<Persons>
	 *
	 * 
	 */
	public Integer perInfoUpdatePersons(final List<Persons> lstPersons) {
		final String sql = getQuery("OCDPERSO_PERINFO_UPDATE_PERSONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Persons persons : lstPersons) {
			parameters.add(new BeanPropertySqlParameterSource(persons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao PersonEmployments
	 *
	 * @return List<PersonEmployments>
	 *
	 * 
	 */
	public List<PersonEmployments> perEmpExecuteQuery(final PersonEmployments objSearchDao) {
		final String sql = getQuery("OCDPERSO_PEREMP_FIND_PERSON_EMPLOYMENTS");
		final RowMapper<PersonEmployments> PersonEmploymentsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PersonEmployments.class, personEmploymentsMapping);
		final ArrayList<PersonEmployments> returnList = (ArrayList<PersonEmployments>) namedParameterJdbcTemplate
				.query(sql, createParams("personId", objSearchDao.getPersonId()), PersonEmploymentsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstPersonEmployments List<PersonEmployments>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer perEmpInsertPersonEmployments(final List<PersonEmployments> lstPersonEmployments) {
		Boolean insertFlag = true;
		Long employmentSeq = (long) 0;
		final String sql = getQuery("OCDPERSO_PEREMP_INSERT_PERSON_EMPLOYMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PersonEmployments personEmployments : lstPersonEmployments) {
			if (insertFlag != null && insertFlag.equals(true)) {
				employmentSeq = tagpersonsearchrepository.getNextEmpSeq(personEmployments.getPersonId());
				insertFlag = false;
			}
			personEmployments.setEmploymentSeq(employmentSeq);
			employmentSeq++;
			parameters.add(new BeanPropertySqlParameterSource(personEmployments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersonEmployments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}


	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPersonEmployments List<PersonEmployments>
	 *
	 * 
	 */
	public Integer perEmpUpdatePersonEmployments(final List<PersonEmployments> lstPersonEmployments) {
		final String sql = getQuery("OCDPERSO_PEREMP_UPDATE_PERSON_EMPLOYMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PersonEmployments personEmployments : lstPersonEmployments) {
			parameters.add(new BeanPropertySqlParameterSource(personEmployments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersonEmployments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstPersonEmployments List<PersonEmployments>
	 *
	 * 
	 */
	public Integer perEmpDeletePersonEmployments(final List<PersonEmployments> lstPersonEmployments) {
		final String sql = getQuery("OCDPERSO_PEREMP_DELETE_PERSON_EMPLOYMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PersonEmployments personEmployments : lstPersonEmployments) {
			parameters.add(new BeanPropertySqlParameterSource(personEmployments));
		}
		try {
			String tableName = "PERSON_EMPLOYMENTS";
			String whereClause = "PERSON_ID  = :personId AND EMPLOYMENT_SEQ  = :employmentSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method perEmpDeletePersonEmployments", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersonEmployments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgContactTypeRecordGroup() {
		final String sql = getQuery("OCDPERSO_FIND_RGCONTACTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgRelTypeRecordGroup(final String contactType) {
		final String sql = getQuery("OCDPERSO_FIND_RGRELTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("contactType", contactType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgLanguageCodeRecordGroup() {
		final String sql = getQuery("OCDPERSO_FIND_RGLANGUAGECODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgMaritalStatusRecordGroup() {
		final String sql = getQuery("OCDPERSO_FIND_RGMARITALSTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		final String sql = getQuery("OCDPERSO_FIND_RGSEXCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSearchTypeRecordGroup() {
		final String sql = getQuery("OCDPERSO_FIND_RGSEARCHTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup() {
		final String sql = getQuery("OCDPERSO_FIND_RGIDENTIFIERTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
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
	public List<OffenderContactPersons> offBkgOnCheckDeleteMaster(OffenderContactPersons paramBean) {
		final String sql = getQuery("OCDPERSO_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderContactPersons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderContactPersons.class, offenderContactPersonsMapping);
		final ArrayList<OffenderContactPersons> returnList = (ArrayList<OffenderContactPersons>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkDuplicateContacts
	 *
	 * @param params
	 *
	 */
	public List<OffenderContactPersons> checkDuplicateContacts(OffenderContactPersons paramBean) {
		final String sql = getQuery("OCDPERSO_CHECK_DUPLICATE_CONTACTS");
		final RowMapper<OffenderContactPersons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderContactPersons.class, offenderContactPersonsMapping);
		final ArrayList<OffenderContactPersons> returnList = (ArrayList<OffenderContactPersons>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * changeActiveFlag
	 *
	 * @param params
	 *
	 */
	public List<OffenderContactPersons> changeActiveFlag(OffenderContactPersons paramBean) {
		final String sql = getQuery("OCDPERSO_CHANGE_ACTIVE_FLAG");
		final RowMapper<OffenderContactPersons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderContactPersons.class, offenderContactPersonsMapping);
		final ArrayList<OffenderContactPersons> returnList = (ArrayList<OffenderContactPersons>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	public OffenderContactPersons getpersonnames(final Integer personId) {
		final OffenderContactPersons bean = new OffenderContactPersons();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_PERSON_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_MIDDLE_NAME", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDPERSO").withProcedureName("GETPERSONNAMES").declareParameters(sqlParameters);
		inParamMap.put("P_PERSON_ID", personId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setLastName(returnObject.get("P_LAST_NAME").toString());
			bean.setFirstName(returnObject.get("P_FIRST_NAME").toString());
			bean.setMiddleName(returnObject.get("P_MIDDLE_NAME").toString());
		} catch (Exception e) {
			 logger.error("getpersonnames"+ e);
		}
		return bean;
	}

	@Override
	public Integer copyOffAddr(final Integer rootOffId, final Integer personId) {
		Integer returnIndex = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.INTEGER),
				new SqlParameter("P_ROOT_OFF_ID", OracleTypes.INTEGER),
				new SqlParameter("P_PERSON_ID", OracleTypes.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDPERSO").withFunctionName("COPYOFFADDR").declareParameters(sqlParameters);
		inParamMap.put("P_ROOT_OFF_ID", rootOffId);
		inParamMap.put("P_PERSON_ID", personId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnIndex = simpleJDBCCall.executeFunction(Integer.class, inParameter);
		} catch (Exception e) {
			returnIndex = 0;
		}
		return returnIndex;
	}

	@Override
	public Integer checkChildRecordsCurOne(final Long offenderBookId, final Integer personId) {
		final String sql = getQuery("OCUOCCUP_CHECKCHILDRECORDSCURONE");
		Integer returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId, "personId", personId), Integer.class);
		return returnObj;
	}

	@Override
	public Integer checkChildRecordsCurTwo(final Long offenderBookId, final Integer personId) {
		final String sql = getQuery("OCUOCCUP_CHECKCHILDRECORDSCURTWO");
		Integer returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId, "personId", personId), Integer.class);
		return returnObj;
	}

	@Override
	public Integer checkFutureVisits(final Long offenderBookId, final Integer personId) {
		final String sql = getQuery("OCUOCCUP_CHECKFUTUREVISITS");
		Integer returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId, "personId", personId), Integer.class);
		return returnObj;
	}

	@Override
	public void cancelFutureVisits(final Long offenderBookId, final Integer personId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PERSON_ID", OracleTypes.NUMBER), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDPERSO").withProcedureName("CANCEL_FUTURE_VISITS").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", personId);
		inParamMap.put("P_PERSON_ID", personId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
		 simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			 logger.error("cancelFutureVisits"+ e);
		}
	}
}
