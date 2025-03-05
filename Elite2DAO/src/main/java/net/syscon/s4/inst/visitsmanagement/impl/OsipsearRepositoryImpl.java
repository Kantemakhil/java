package net.syscon.s4.inst.visitsmanagement.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.inst.booking.beans.PersonEmployments;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.VPersonAddress;
import net.syscon.s4.inst.visitsmanagement.OsipsearRepository;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPartialSoundexPersons;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPersons;
import oracle.jdbc.OracleTypes;

@Repository
public class OsipsearRepositoryImpl extends RepositoryBase implements OsipsearRepository{

private final Map<String, FieldMapper> personEmploymentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("EMPLOYER_NAME", 						new FieldMapper("employerName"))
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("PHONE_AREA", 						new FieldMapper("phoneArea"))
.put("EMPLOYMENT_SEQ", 						new FieldMapper("employmentSeq"))
.put("SUPERVISOR_NAME", 						new FieldMapper("supervisorName"))
.put("PHONE_EXT", 						new FieldMapper("phoneExt"))
.build();
private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ALIAS_PERSON_ID", 						new FieldMapper("aliasPersonId"))
.put("CARE_OF", 						new FieldMapper("careOf"))
.put("STAFF_FLAG", 						new FieldMapper("staffFlag"))
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("ROOT_PERSON_ID", 						new FieldMapper("rootPersonId"))
.put("FIRST_NAME_KEY", 						new FieldMapper("firstNameKey"))
.put("MARITAL_STATUS", 						new FieldMapper("maritalStatus"))
.put("SEX", 						new FieldMapper("sex"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("INTERPRETER_REQUIRED", 						new FieldMapper("interpreterRequired"))
.put("SEX_DESCRIPTION", 						new FieldMapper("sexDescription"))
.build();
private final Map<String, FieldMapper> imagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("IMAGE_ID", 						new FieldMapper("imageId"))
.put("IMAGE_VIEW_TYPE", 						new FieldMapper("imageViewType"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("IMAGE_OBJECT_TYPE", 						new FieldMapper("imageObjectType"))
.put("IMAGE_OBJECT_ID", 						new FieldMapper("imageObjectId"))
.put("IMAGE_THUMBNAIL", 						new FieldMapper("imageThumbnail"))
.put("ORIENTATION_TYPE", 						new FieldMapper("orientationType"))
.build();
private final Map<String, FieldMapper> vPersonAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("STREET_NUMBER", 						new FieldMapper("streetNumber"))
.put("ADDRESS_ID", 						new FieldMapper("addressId"))
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("COUNTRY_CODE", 						new FieldMapper("countryCode"))
.put("HOUSE", 						new FieldMapper("house"))
.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
.build();
private final Map<String, FieldMapper> personIdentifiersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("IDENTIFIER_TYPE", 						new FieldMapper("identifierType"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("ID_SEQ", 						new FieldMapper("idSeq"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("ISSUED_DATE", 						new FieldMapper("issuedDate"))
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("IDENTIFIER", 						new FieldMapper("identifier"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 				new FieldMapper("description"))
.put("CODE", 						new FieldMapper("code"))
.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
.build();

private final Map<String, FieldMapper> personAddNamesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("SECOND_MIDDLE_NAME", 						new FieldMapper("middleName2"))
.put("PERS_ADD_NAME_ID", 						new FieldMapper("persAddNameId"))
.build();

	private static Logger logger = LogManager.getLogger(OsipsearRepositoryImpl.class.getName());

	/**
	 * Creates new OsipsearRepositoryImpl class Object
	 */
	public OsipsearRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TagPersonSearchGetPersons
	 *
	 * @return List<TagPersonSearchGetPersons>
	 *
	 * 
	 */
	public List<TagPersonSearchGetPersons> personsExecuteQuery(TagPersonSearchGetPersons objSearchDao) {
		Map<String, Object> returnObject = null;
		;
		List<TagPersonSearchGetPersons> lListObj = new ArrayList<TagPersonSearchGetPersons>();
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_RESULT_SET", OracleTypes.CURSOR),
				new SqlParameter("P_SEARCH_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_MIDDLE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_PERSON_ID", OracleTypes.NUMBER),
				new SqlParameter("P_IDENTIFIER_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_IDENTIFIER_VALUE", OracleTypes.VARCHAR),
				new SqlParameter("P_SEX", OracleTypes.VARCHAR), new SqlParameter("P_BIRTH_DATE", OracleTypes.DATE),
				new SqlParameter("P_BIRTH_YEAR", OracleTypes.NUMBER),
				new SqlParameter("P_BIRTH_RANGE", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PERSON_SEARCH").withProcedureName("GET_PERSONS").declareParameters(sqlParameters);
		inParamMap.put("P_SEARCH_TYPE", objSearchDao.getpSearchType());
		inParamMap.put("P_LAST_NAME", objSearchDao.getpLastName());
		inParamMap.put("P_FIRST_NAME", objSearchDao.getpFirstName());
		inParamMap.put("P_MIDDLE_NAME", objSearchDao.getpMiddleName());
		inParamMap.put("P_PERSON_ID", objSearchDao.getpPersonId());
		inParamMap.put("P_IDENTIFIER_TYPE", objSearchDao.getpIdentifierType());
		inParamMap.put("P_IDENTIFIER_VALUE", objSearchDao.getpIdentifierValue());
		inParamMap.put("P_SEX", objSearchDao.getpSex());
		inParamMap.put("P_BIRTH_DATE", objSearchDao.getpBirthDate());
		inParamMap.put("P_BIRTH_YEAR", objSearchDao.getpBirthYear());
		inParamMap.put("P_BIRTH_RANGE", objSearchDao.getpBirthRange());
		SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			lListObj = new ArrayList<>();
			List<Map<String, Object>> list = (List<Map<String, Object>>) returnObject.get("P_RESULT_SET");
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> childMap = list.get(i);
				TagPersonSearchGetPersons bean = new TagPersonSearchGetPersons();
				bean.setSex((childMap.get("SEX")) != null ? childMap.get("SEX").toString() : null);
				bean.setLastName((childMap.get("LAST_NAME")) != null ? childMap.get("LAST_NAME").toString() : null);
				bean.setFirstName((childMap.get("FIRST_NAME")) != null ? childMap.get("FIRST_NAME").toString() : null);
				bean.setBirthDate((childMap.get("BIRTHDATE")) != null ? (Date) childMap.get("BIRTHDATE") : null);
				bean.setMiddleName(
						(childMap.get("MIDDLE_NAME")) != null ? childMap.get("MIDDLE_NAME").toString() : null);
				bean.setPersonId(((childMap.get("PERSON_ID")) != null
						? BigDecimal.valueOf(Long.valueOf(childMap.get("PERSON_ID").toString())) : null));
				lListObj.add(bean);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return lListObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VPersonAddresses
	 *
	 * @return List<VPersonAddresses>
	 *
	 * 
	 */
	public List<VPersonAddress> perAddrExecuteQuery(VPersonAddress objSearchDao) {
		final String sql = getQuery("OSIPSEAR_PERADDR_FIND_V_PERSON_ADDRESSES");
		final RowMapper<VPersonAddress> VPersonAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VPersonAddress.class, vPersonAddressesMapping);
		final List<VPersonAddress> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("personId", objSearchDao.getPersonId()), VPersonAddressesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            PersonIdentifiers
	 *
	 * @return List<PersonIdentifiers>
	 *
	 * 
	 */
	public List<PersonIdentifiers> perIdentExecuteQuery(PersonIdentifiers objSearchDao) {
		final String sql = getQuery("OSIPSEAR_PERIDENT_FIND_PERSON_IDENTIFIERS");
		final RowMapper<PersonIdentifiers> PersonIdentifiersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PersonIdentifiers.class, personIdentifiersMapping);
		final List<PersonIdentifiers> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("personId", objSearchDao.getPersonId()), PersonIdentifiersRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstPersonIdentifiers
	 *            List<PersonIdentifiers>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer perIdentInsertPersonIdentifiers(final List<PersonIdentifiers> lstPersonIdentifiers) {
		String sql = getQuery("OSIPSEAR_PERIDENT_INSERT_PERSON_IDENTIFIERS");
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
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPersonIdentifiers
	 *            List<PersonIdentifiers>
	 *
	 * 
	 */
	public Integer perIdentUpdatePersonIdentifiers(final List<PersonIdentifiers> lstPersonIdentifiers) {
		String sql = getQuery("OSIPSEAR_PERIDENT_UPDATE_PERSON_IDENTIFIERS");
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
	 * @param lstPersonIdentifiers
	 *            List<PersonIdentifiers>
	 *
	 * 
	 */
	public Integer perIdentDeletePersonIdentifiers(final List<PersonIdentifiers> lstPersonIdentifiers) {
		String sql = getQuery("OSIPSEAR_PERIDENT_DELETE_PERSON_IDENTIFIERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (PersonIdentifiers personIdentifiers : lstPersonIdentifiers) {
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
		try {
			String tableName = "PERSON_IDENTIFIERS";
			String whereCondition = "PERSON_ID  = :personId AND ID_SEQ  = :idSeq";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
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
	 * @param objSearchDao
	 *            Images
	 *
	 * @return List<Images>
	 *
	 * 
	 */
	public List<Images> imageExecuteQuery(Images objSearchDao) {
		final String sql = getQuery("OSIPSEAR_IMAGE_FIND_IMAGES");
		final RowMapper<Images> ImagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		final List<Images> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("personId", objSearchDao.getImageObjectId()), ImagesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Persons
	 *
	 * @return List<Persons>
	 *
	 * 
	 */
	public List<Persons> perInfoExecuteQuery(Persons objSearchDao) {
		final String sql = getQuery("OSIPSEAR_PERINFO_FIND_PERSONS");
		final RowMapper<Persons> PersonsRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		final List<Persons> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("personId", objSearchDao.getPersonId()), PersonsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPersons
	 *            List<Persons>
	 *
	 * 
	 */
	public Integer perInfoUpdatePersons(final List<Persons> lstPersons) {
		String sql = getQuery("OSIPSEAR_PERINFO_UPDATE_PERSONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Persons persons : lstPersons) {
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
	 * @param objSearchDao
	 *            PersonEmployments
	 *
	 * @return List<PersonEmployments>
	 *
	 * 
	 */
	public List<PersonEmployments> perEmpExecuteQuery(PersonEmployments objSearchDao) {
		final String sql = getQuery("OSIPSEAR_PEREMP_FIND_PERSON_EMPLOYMENTS");
		final RowMapper<PersonEmployments> PersonEmploymentsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PersonEmployments.class, personEmploymentsMapping);
		final List<PersonEmployments> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("personId", objSearchDao.getPersonId()), PersonEmploymentsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstPersonEmployments
	 *            List<PersonEmployments>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer perEmpInsertPersonEmployments(final List<PersonEmployments> lstPersonEmployments) {
		String sql = getQuery("OSIPSEAR_PEREMP_INSERT_PERSON_EMPLOYMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (PersonEmployments personEmployments : lstPersonEmployments) {
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
	 * @param lstPersonEmployments
	 *            List<PersonEmployments>
	 *
	 * 
	 */
	public Integer perEmpUpdatePersonEmployments(final List<PersonEmployments> lstPersonEmployments) {
		String sql = getQuery("OSIPSEAR_PEREMP_UPDATE_PERSON_EMPLOYMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (PersonEmployments personEmployments : lstPersonEmployments) {
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
	 * @param lstPersonEmployments
	 *            List<PersonEmployments>
	 *
	 * 
	 */
	public Integer perEmpDeletePersonEmployments(final List<PersonEmployments> lstPersonEmployments) {
		String sql = getQuery("OSIPSEAR_PEREMP_DELETE_PERSON_EMPLOYMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (PersonEmployments personEmployments : lstPersonEmployments) {
			parameters.add(new BeanPropertySqlParameterSource(personEmployments));
		}
		try {
			String tableName = "PERSON_EMPLOYMENTS";
			String whereCondition = "PERSON_ID  = :personId and EMPLOYMENT_SEQ  = :employmentSeq";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersonEmployments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TagPersonSearchGetPartialSoundexPersons
	 *
	 * @return List<TagPersonSearchGetPartialSoundexPersons>
	 *
	 */
	public List<TagPersonSearchGetPartialSoundexPersons> psPersonNameExecuteQuery(
			TagPersonSearchGetPartialSoundexPersons objSearchDao) {
		Map<String, Object> returnObject = null;
		;
		List<TagPersonSearchGetPartialSoundexPersons> lListObj = new ArrayList<TagPersonSearchGetPartialSoundexPersons>();
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_RESULT_SET", OracleTypes.CURSOR),
				new SqlParameter("P_SEARCH_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_MIDDLE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_PERSON_ID", OracleTypes.NUMBER),
				new SqlParameter("P_IDENTIFIER_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_IDENTIFIER_VALUE", OracleTypes.VARCHAR),
				new SqlParameter("P_SEX", OracleTypes.VARCHAR), new SqlParameter("P_BIRTH_DATE", OracleTypes.DATE),
				new SqlParameter("P_BIRTH_YEAR", OracleTypes.NUMBER),
				new SqlParameter("P_BIRTH_RANGE", OracleTypes.NUMBER), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PERSON_SEARCH").withProcedureName("GET_PARTIAL_SOUNDEX_PERSONS")
				.declareParameters(sqlParameters);

		inParamMap.put("P_SEARCH_TYPE", objSearchDao.getpSearchType());
		inParamMap.put("P_LAST_NAME", objSearchDao.getpLastName());
		inParamMap.put("P_FIRST_NAME", objSearchDao.getpFirstName());
		inParamMap.put("P_MIDDLE_NAME", objSearchDao.getpMiddleName());
		inParamMap.put("P_PERSON_ID", objSearchDao.getpPersonId());
		inParamMap.put("P_IDENTIFIER_TYPE", objSearchDao.getpIdentifierType());
		inParamMap.put("P_IDENTIFIER_VALUE", objSearchDao.getpIdentifierValue());
		inParamMap.put("P_SEX", objSearchDao.getpSex());
		inParamMap.put("P_BIRTH_DATE", objSearchDao.getPBirthDate());
		inParamMap.put("P_BIRTH_YEAR", objSearchDao.getPBirthDate());
		inParamMap.put("P_BIRTH_RANGE", objSearchDao.getPBirthDate());
		SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			lListObj = new ArrayList<>();
			List<Map<String, Object>> list = (List<Map<String, Object>>) returnObject.get("P_RESULT_SET");
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> childMap = list.get(i);
				TagPersonSearchGetPartialSoundexPersons bean = new TagPersonSearchGetPartialSoundexPersons();
				bean.setHits((BigDecimal) childMap.get("HITS"));
				bean.setLastName(childMap.get("LAST_NAME").toString());
				lListObj.add(bean);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return lListObj;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstTagPersonSearchGetPartialSoundexPersons
	 *            List<TagPersonSearchGetPartialSoundexPersons>
	 *
	 * 
	 */
	public Integer psPersonNameDeleteTagPersonSearchGetPartialSoundexPersons(
			final List<TagPersonSearchGetPartialSoundexPersons> lstTagPersonSearchGetPartialSoundexPersons) {
		int deleteCount = 0;
		String sql = getQuery("OSIPSEAR_PSPERSONNAME_DELETE_TAG_PERSON_SEARCH.GET_PARTIAL_SOUNDEX_PERSONS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TagPersonSearchGetPartialSoundexPersons tagPersonSearchGetPartialSoundexPersons : lstTagPersonSearchGetPartialSoundexPersons) {
			parameters.add(new BeanPropertySqlParameterSource(tagPersonSearchGetPartialSoundexPersons));
		}
		try {
			String tableName = "TAG_PERSON_SEARCH.GET_PARTIAL_SOUNDEX_PERSONS";
			batchUpdatePreDeletedRows(tableName, null, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			deleteCount = deleteCount++;
		}
		if (lstTagPersonSearchGetPartialSoundexPersons.size() == deleteCount) {
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
	public List<ReferenceCodes> rgLanguageCodeRecordGroup() {
		final String sql = getQuery("OSIPSEAR_FIND_RGLANGUAGECODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		List<ReferenceCodes> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("OSIPSEAR_FIND_RGLANGUAGECODE", e);
			;
		}
		return resultList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgMaritalStatusRecordGroup() {
		final String sql = getQuery("OSIPSEAR_FIND_RGMARITALSTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		List<ReferenceCodes> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("OSIPSEAR_FIND_RGMARITALSTATUS", e);
			;
		}
		return resultList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		final String sql = getQuery("OSIPSEAR_FIND_RGSEXCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("OSIPSEAR_FIND_RGSEXCODE", e);
			;
		}
		return resultList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSearchTypeRecordGroup() {
		final String sql = getQuery("OSIPSEAR_FIND_RGSEARCHTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("OSIPSEAR_FIND_RGSEARCHTYPE", e);
			;
		}
		return resultList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup() {
		final String sql = getQuery("OSIPSEAR_FIND_RGIDENTIFIERTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * personsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VPersonAddress> personsOnCheckDeleteMaster(VPersonAddress paramBean) {
		final String sql = getQuery("OSIPSEAR_PERSONS_ONCHECKDELETEMASTER");
		final RowMapper<VPersonAddress> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VPersonAddress.class,
				vPersonAddressesMapping);
		final ArrayList<VPersonAddress> returnList = (ArrayList<VPersonAddress>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * personsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public PersonIdentifiers personsOnCheckDeleteMaster(PersonIdentifiers paramBean) {
		final String sql = getQuery("OSIPSEAR_PERSONS_ONCHECKDELETEMASTER");
		final RowMapper<PersonIdentifiers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PersonIdentifiers.class,
				personIdentifiersMapping);
		PersonIdentifiers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * personsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public Persons personsOnCheckDeleteMaster(Persons paramBean) {
		final String sql = getQuery("OSIPSEAR_PERSONS_ONCHECKDELETEMASTER");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		Persons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * personsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public PersonEmployments personsOnCheckDeleteMaster(PersonEmployments paramBean) {
		final String sql = getQuery("OSIPSEAR_PERSONS_ONCHECKDELETEMASTER");
		final RowMapper<PersonEmployments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PersonEmployments.class,
				personEmploymentsMapping);
		PersonEmployments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * personsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Images> personsOnCheckDeleteMaster(Images paramBean) {
		final String sql = getQuery("OSIPSEAR_PERSONS_ONCHECKDELETEMASTER");
		final RowMapper<Images> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		final ArrayList<Images> returnList = (ArrayList<Images>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}
	
	
	@Override
	public List<Offenders> getAdditionalNames(Long personId) {
		final String sql = getQuery("OSIPSEAR_GET_ADDITIONAL_NAMES");
		List<Offenders> list = new ArrayList<Offenders>();
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				personAddNamesMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("personId", personId), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getAdditionalNames" + e);
		}
		return list;
	}
	
	
	public Integer offInsertOffenders(final List<Offenders> lstOffenders) {
		final String sql = getQuery("OSIPSEAR_INSERT_ADDITIONAL_NAMES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Offenders offenders : lstOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(offenders));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " offInsertOffenders" + e);
		}
		if (lstOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	public Integer updateOffenders(final List<Offenders> lstOffenders) {
		final String sql = getQuery("OSIPSEAR_UPDATE_ADDITIONAL_NAMES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Offenders offenders : lstOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(offenders));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateOffenders" + e);
		}
		if (lstOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	public Integer deleteOffenders(final List<Offenders> lstOffenders) {
		final String sql = getQuery("OSIPSEAR_DELETE_ADDITIONAL_NAMES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Offenders offenders : lstOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(offenders));
		}
		try {
			String tableName = "person_additional_names";
			String whereCondition = "pers_add_name_id =:persAddNameId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " deleteOffenders" + e);
		}
		if (lstOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	@Override
	public TagPersonSearchGetPersons getDataFromJisCommonSystem(Long intCorrelationId) {
		final String sql = getQuery("OSIPSEAR_GET_DATA_FROM_JIS_COMMON_SYSTEM");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("intCorrelationId", intCorrelationId),
					new BeanPropertyRowMapper<TagPersonSearchGetPersons>(TagPersonSearchGetPersons.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getDataFromJisCommonSystem" + e);
		}
		return null;
	}

	@Override
	public String getIdentifierData(BigDecimal personId) {
		final String sql = getQuery("OSIPSEAR_GET_IDENTIFIER_DATA");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("personId", personId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getIdentifierData" + e);
		}
		return null;
	}
	
	@Override
	public List<TagPersonSearchGetPersons> getAdditionaNamesAsParent(TagPersonSearchGetPersons searchRecord) {
		final String sql = getQuery("OSIPSEAR_GET_ADDITIONAL_NAMES_AS_PARENT");
		if (ApplicationConstants.NFLAG.equalsIgnoreCase(searchRecord.getpSearchType())) {
			final StringBuffer sqlQuery = new StringBuffer();
			final MapSqlParameterSource params = new MapSqlParameterSource();
			String preparedSql = null;
			sqlQuery.append(sql);
			sqlQuery.append(" WHERE ");
			if (searchRecord.getpFirstName() != null) {
				sqlQuery.append("first_name like :firstName " + " AND");
				params.addValue("firstName", searchRecord.getpFirstName());
			}
			if (searchRecord.getpLastName() != null) {
				sqlQuery.append("  last_name like :lastName " + " AND");
				params.addValue("lastName", searchRecord.getpLastName());
			}
			if (searchRecord.getpMiddleName() != null) {
				sqlQuery.append("  middle_name like :middleName " + " AND");
				params.addValue("middleName", searchRecord.getpMiddleName());
			}
			if (searchRecord.getsecondMiddleName() != null) {
				sqlQuery.append("  second_middle_name like :secondMiddleName " + " AND");
				params.addValue("secondMiddleName", searchRecord.getsecondMiddleName());
			}
			preparedSql = sqlQuery.toString().trim();
			if (preparedSql.endsWith("WHERE")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("AND")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
			}
			try {
				if (preparedSql.contains("WHERE")) {
					final RowMapper<TagPersonSearchGetPersons> mapper = Row2BeanRowMapper.makeMapping(preparedSql,
							TagPersonSearchGetPersons.class, personIdentifiersMapping);
					return namedParameterJdbcTemplate.query(preparedSql, params, mapper);
				}
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " getAdditionaNamesAsParent" + e);
			}
		}
		return null;
	}
	
	
	@Override
	public TagPersonSearchGetPersons getgetAdditionaNamesDetails(BigDecimal personId) {
		final String sql = getQuery("OSIPSEAR_GET_ADDITIONAL_NAME_DETAILS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("personId",personId),
					new BeanPropertyRowMapper<TagPersonSearchGetPersons>(TagPersonSearchGetPersons.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getgetAdditionaNamesDetails" + e);
		}
		return null;
	}
	

}
