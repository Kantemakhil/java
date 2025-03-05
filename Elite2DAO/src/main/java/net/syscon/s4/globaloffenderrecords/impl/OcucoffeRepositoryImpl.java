package net.syscon.s4.globaloffenderrecords.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globaloffenderrecords.OcucoffeRepository;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Class OcucoffeRepositoryImpl
 */
@Repository
public class OcucoffeRepositoryImpl extends RepositoryBase implements OcucoffeRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcucoffeRepositoryImpl.class.getName());

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
			.put("SEXCODEDESC", new FieldMapper("sexCodeDesc"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue")).build();
	private final Map<String, FieldMapper> offenderIdentifiersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("VERIFIED_FLAG", new FieldMapper("verifiedFlag"))
			.put("PARENT_OFFENDER_ID", new FieldMapper("parentOffenderId"))
			.put("SUFFIX", new FieldMapper("suffix"))
			.put("OFFENDER_NAME_SEQ", new FieldMapper("offenderNameSeq"))
			.put("LAST_NAME_SOUNDEX", new FieldMapper("lastNameSoundex"))
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
			.put("AGE", new FieldMapper("age")).build();

	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE_2", new FieldMapper("sysDate"))
			.put("PROFILE_VALUE", new FieldMapper("user")).build();

	/**
	 * Creates new OcucoffeRepositoryImpl class Object
	 */
	public OcucoffeRepositoryImpl() {
		// OcucoffeRepositoryImpl
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgAliasNameTypeRecordGroup() {
		final String sql = getQuery("OCUCOFFE_FIND_RGALIASNAMETYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("rgAliasNameTypeRecordGroup", e);
			return Collections.emptyList();

		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 * @param ReferenceCodes
	 *            obj
	 */
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup() {
		final String sql = getQuery("OCUCOFFE_FIND_RGIDENTIFIERTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("rgIdentifierTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgOffSuffixRecordGroup() {
		final String sql = getQuery("OCUCOFFE_FIND_RGOFFSUFFIX");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("rgOffSuffixRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgOffSexRecordGroup() {
		final String sql = getQuery("OCUCOFFE_FIND_RGOFFSEX");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("rgOffSexRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgOffRaceRecordGroup() {
		final String sql = getQuery("OCUCOFFE_FIND_RGOFFRACE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("rgOffRaceRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgIdSourceRecordGroup() {
		final String sql = getQuery("OCUCOFFE_FIND_RGIDSOURCE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("rgIdSourceRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param Offenders
	 *            objSearchDao
	 * @return List<Offenders>
	 */
	public List<Offenders> offsearchOffenders(final Offenders objSearchDao) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OCUCOFFE_OFF_FIND_OFFENDERS"), offendersMapping).build();

		// final String sql = getQuery("OCUCOFFE_OFF_FIND_OFFENDERS");
		String preparedSql = null;
		StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID = :offenderId and");
				params.addValue("offenderId", objSearchDao.getOffenderId());

			}
			if (objSearchDao.getOffenderNameSeq() != null) {
				sqlQuery.append("OFFENDER_NAME_SEQ = :offenderNameSeq  " + " and");
				params.addValue("offenderNameSeq", objSearchDao.getOffenderNameSeq());
			}
			if (objSearchDao.getIdSourceCode() != null) {
				sqlQuery.append("ID_SOURCE_CODE = :idSourceCode  " + " and");
				params.addValue("idSourceCode", objSearchDao.getIdSourceCode());
			}
			if (objSearchDao.getLastName() != null) {
				sqlQuery.append("LAST_NAME =:lastName " + " and");
				params.addValue("lastName", objSearchDao.getLastName());
			}
			if (objSearchDao.getNameType() != null) {
				sqlQuery.append("NAME_TYPE =:nameType " + " and");
				params.addValue("nameType", objSearchDao.getNameType());
			}
			if (objSearchDao.getFirstName() != null) {
				sqlQuery.append("FIRST_NAME =:firstName " + " and");
				params.addValue("firstName", objSearchDao.getFirstName());
			}
			if (objSearchDao.getMiddleName() != null) {
				sqlQuery.append("MIDDLE_NAME =:middleName " + " and");
				params.addValue("middleName", objSearchDao.getMiddleName());
			}
			if (objSearchDao.getBirthDate() != null) {
				sqlQuery.append("BIRTH_DATE =:birthDate " + " and");
				params.addValue("birthDate", objSearchDao.getBirthDate());
			}
			if (objSearchDao.getSexCode() != null) {
				sqlQuery.append("SEX_CODE =:sexCode " + " and");
				params.addValue("sexCode", objSearchDao.getSexCode());
			}
			if (objSearchDao.getSuffix() != null) {
				sqlQuery.append("SUFFIX =:suffix " + " and");
				params.addValue("suffix", objSearchDao.getSuffix());
			}
			if (objSearchDao.getLastNameSoundex() != null) {
				sqlQuery.append("LAST_NAME_SOUNDEX =:lastNameSoundex " + " and");
				params.addValue("lastNameSoundex", objSearchDao.getLastNameSoundex());
			}
			if (objSearchDao.getBirthPlace() != null) {
				sqlQuery.append("BIRTH_PLACE =:birthPlace " + " and");
				params.addValue("birthPlace", objSearchDao.getBirthPlace());
			}
			if (objSearchDao.getBirthCountryCode() != null) {
				sqlQuery.append("BIRTH_COUNTRY_CODE =:birthCountryCode " + " and");
				params.addValue("birthCountryCode", objSearchDao.getBirthCountryCode());
			}
			if (objSearchDao.getCreateDate() != null) {
				sqlQuery.append("CREATE_DATE =:createDate " + " and");
				params.addValue("createDate", objSearchDao.getCreateDate());
			}
			if (objSearchDao.getLastNameKey() != null) {
				sqlQuery.append("LAST_NAME_KEY =:lastNameKey " + " and");
				params.addValue("lastNameKey", objSearchDao.getLastNameKey());
			}
			if (objSearchDao.getAliasOffenderId() != 0) {
				sqlQuery.append("ALIAS_OFFENDER_ID =:aliasOffenderId " + " and");
				params.addValue("aliasOffenderId", objSearchDao.getAliasOffenderId());
			}
			if (objSearchDao.getFirstNameKey() != null) {
				sqlQuery.append("FIRST_NAME_KEY =:firstNameKey " + " and");
				params.addValue("firstNameKey", objSearchDao.getFirstNameKey());
			}
			if (objSearchDao.getMiddleNameKey() != null) {
				sqlQuery.append("MIDDLE_NAME_KEY =:middleNameKey " + " and");
				params.addValue("middleNameKey", objSearchDao.getMiddleNameKey());
			}

			if (objSearchDao.getOffenderIdDisplay() != null) {
				sqlQuery.append("OFFENDER_ID_DISPLAY =:offenderIdDisplay " + " and");
				params.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
			}
			if (objSearchDao.getRootOffenderId() != null) {
				sqlQuery.append("ROOT_OFFENDER_ID =:rootOffenderId " + " and");
				params.addValue("rootOffenderId", objSearchDao.getRootOffenderId());
			}
			if (objSearchDao.getCaseloadType() != null) {
				sqlQuery.append("CASELOAD_TYPE =:caseloadType " + " and");
				params.addValue("caseloadType", objSearchDao.getCaseloadType());
			}
			if (objSearchDao.getModifyUserId() != null) {
				sqlQuery.append("MODIFY_USER_ID =:modifyUserId " + " and");
				params.addValue("modifyUserId", objSearchDao.getModifyUserId());
			}

			if (objSearchDao.getModifyDateTime() != null) {
				sqlQuery.append("MODIFY_DATETIME =:modifyDatetime " + " and");
				params.addValue("modifyDatetime", objSearchDao.getModifyDateTime());
			}
			if (objSearchDao.getAliasNameType() != null) {
				sqlQuery.append("ALIAS_NAME_TYPE =:aliasNameType " + " and");
				params.addValue("aliasNameType", objSearchDao.getAliasNameType());
			}

			if (objSearchDao.getParentOffenderId() != null) {
				sqlQuery.append("PARENT_OFFENDER_ID =:parentOffenderId " + " and");
				params.addValue("parentOffenderId", objSearchDao.getParentOffenderId());
			}
			if (objSearchDao.getUniqueObligationFlag() != null) {
				sqlQuery.append("UNIQUE_OBLIGATION_FLAG =:uniqueObligationFlag " + " and");
				params.addValue("uniqueObligationFlag", objSearchDao.getUniqueObligationFlag());
			}

			if (objSearchDao.getSuspendedFlag() != null) {
				sqlQuery.append("SUSPENDED_FLAG =:suspendedFlag " + " and");
				params.addValue("suspendedFlag", objSearchDao.getSuspendedFlag());
			}

			if (objSearchDao.getSuspendedDate() != null) {
				sqlQuery.append("SUSPENDED_DATE =:suspendedDate " + " and");
				params.addValue("suspendedDate", objSearchDao.getSuspendedDate());
			}

			if (objSearchDao.getRaceCode() != null) {
				sqlQuery.append("RACE_CODE =:raceCode " + " and");
				params.addValue("raceCode", objSearchDao.getRaceCode());
			}
			if (objSearchDao.getRemarkCode() != null) {
				sqlQuery.append("REMARK_CODE =:remarkCode " + " and");
				params.addValue("remarkCode", objSearchDao.getRemarkCode());
			}

			if (objSearchDao.getAddInfoCode() != null) {
				sqlQuery.append("ADD_INFO_CODE =:addInfoCode " + " and");
				params.addValue("addInfoCode", objSearchDao.getAddInfoCode());
			}

			if (objSearchDao.getBirthCounty() != null) {
				sqlQuery.append("BIRTH_COUNTY =:birthCounty " + " and");
				params.addValue("birthCounty", objSearchDao.getBirthCounty());
			}

			if (objSearchDao.getBirthState() != null) {
				sqlQuery.append("BIRTH_STATE =:birthState " + " and");
				params.addValue("birthState", objSearchDao.getBirthState());
			}
			if (objSearchDao.getMiddleName2() != null) {
				sqlQuery.append("MIDDLE_NAME_2 =:middleName2 " + " and");
				params.addValue("middleName2", objSearchDao.getMiddleName2());
			}

			if (objSearchDao.getTitle() != null) {
				sqlQuery.append("TITLE =:title " + " and");
				params.addValue("title", objSearchDao.getTitle());
			}
			if (objSearchDao.getAge() != null) {
				sqlQuery.append("AGE =:age " + " and");
				params.addValue("age", objSearchDao.getAge());
			}
			if (objSearchDao.getCreateUserId() != null) {
				sqlQuery.append("CREATE_USER_ID =:createUserId " + " and");
				params.addValue("createUserId", objSearchDao.getCreateUserId());
			}
			if (objSearchDao.getLastNameAlphaKey() != null) {
				sqlQuery.append("LAST_NAME_ALPHA_KEY =:lastNameAlphaKey " + " and");
				params.addValue("lastNameAlphaKey", objSearchDao.getLastNameAlphaKey());
			}
			if (objSearchDao.getCreateDateTime() != null) {
				sqlQuery.append("CREATE_DATETIME =:createDatetime " + " and");
				params.addValue("createDatetime", objSearchDao.getCreateDateTime());
			}
			if (objSearchDao.getNameSequence() != null) {
				sqlQuery.append("NAME_SEQUENCE =:nameSequence " + " and");
				params.addValue("nameSequence", objSearchDao.getNameSequence());
			}
			if (objSearchDao.getSealFlag() != null) {
				sqlQuery.append("SEAL_FLAG =:sealFlag " + " and");
				params.addValue("sealFlag", objSearchDao.getSealFlag());
			}
		}

		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<Offenders> OffendersRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, Offenders.class,
				offendersMapping);
		final ArrayList<Offenders> returnList = (ArrayList<Offenders>) namedParameterJdbcTemplate.query(preparedSql,
				params, OffendersRowMapper);
		return returnList;
	}

	/**
	 * @param
	 * @throws SQLException
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param List<Offenders>
	 *            lstOffenders
	 * @return List<Integer>
	 */
	public Long offInsertOffenders(final List<Offenders> lstOffenders) {
		Long rootOffenderId = null;
		for (Offenders offenders : lstOffenders) {
			rootOffenderId = Long.valueOf(offenders.getRootOffenderId().toString());
		}
		final String sql = getQuery("OCUCOFFE_OFF_INSERT_OFFENDERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Offenders offenders : lstOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(offenders));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenders.size() == returnArray.length) {
			return rootOffenderId;
		} else {
			return (long) 0.0;
		}

	}
	
	public BigDecimal fetchExternalId() {
		BigDecimal externalId=null;
		final String sql = getQuery("OCUCOFFE_OFF_INSERT_OFFENDERS_EXTERNAL_SEARCH_ID");
		externalId = namedParameterJdbcTemplate.queryForObject(sql, createParams(),BigDecimal.class);
		return externalId;
	}
	
	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param List<Offenders>
	 *            lstOffenders
	 * @return List<Integer>
	 */
	public void offInsertExternalSearchOffenders(final List<Offenders> lstOffenders) {
		int[] insertCount = new int[] {};
		
		final String sql = getQuery("OCUCOFFE_OFF_INSERT_OFFENDERS_EXTERNAL_SEARCH");
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Offenders offenders : lstOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(offenders));
		}
		insertCount = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenders.size() == insertCount.length) {
			logger.info("Offedner external match created");
		} 

	}
	
	public Long preInsertgetNextAliasDAO() {

		final String sql = getQuery("OCUCOFFE_CHECK_PNC_EXISTS");
		Long refList = null;
		refList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return refList;
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param Offenders
	 *            objSearchDao
	 * @return List<Offenders>
	 */
	public List<Offenders> aliasSearchOffenders(final Offenders objSearchDao) {
		final String sql = getQuery("OCUCOFFE_ALIAS_FIND_OFFENDERS");
		String preparedSql = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);

		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID = :offenderId " + " and ");
				params.addValue("offenderId", objSearchDao.getOffenderId());
			}
			// if (objSearchDao.getOffenderNameSeq() != null) {
			// sqlQuery.append("OFFENDER_NAME_SEQ = " +
			// objSearchDao.getOffenderNameSeq() + " and ");
			// }
			if (objSearchDao.getIdSourceCode() != null) {
				sqlQuery.append("ID_SOURCE_CODE = ':idSourceCode'" + " and ");
				params.addValue("idSourceCode", objSearchDao.getIdSourceCode());
			}
			if (objSearchDao.getLastName() != null) {
				sqlQuery.append("LAST_NAME = ':lastName' " + " and ");
				params.addValue("lastName", objSearchDao.getLastName());
			}
			if (objSearchDao.getNameType() != null) {
				sqlQuery.append("NAME_TYPE = ':nameType' " + " and ");
				params.addValue("nameType", objSearchDao.getNameType());
			}
			if (objSearchDao.getFirstName() != null) {
				sqlQuery.append("FIRST_NAME = ':firstName'" + " and ");
				params.addValue("firstName", objSearchDao.getFirstName());
			}
			if (objSearchDao.getMiddleName() != null) {
				sqlQuery.append("MIDDLE_NAME = ':middleName'" + " and ");
				params.addValue("middleName", objSearchDao.getMiddleName());
			}
			if (objSearchDao.getBirthDate() != null) {
				sqlQuery.append("BIRTH_DATE = :birthDate " + " and ");
				params.addValue("birthDate", "to_date('" + objSearchDao.getBirthDate() + "','yyyy/mm/dd') " + " and ");
			}
			if (objSearchDao.getSexCode() != null) {
				sqlQuery.append("SEX_CODE = ':sexCode'" + "' and ");
				params.addValue("sexCode", objSearchDao.getSexCode());
			}
			if (objSearchDao.getSexCode() != null) {
				sqlQuery.append("GENDER_CODE = ':genderCode'" + "' and ");
				params.addValue("genderCode", objSearchDao.getGenderCode());
			}
			if (objSearchDao.getSuffix() != null) {
				sqlQuery.append("SUFFIX = ':suffix'" + " and ");
				params.addValue("suffix", objSearchDao.getSuffix());
			}
			if (objSearchDao.getLastNameSoundex() != null) {
				sqlQuery.append("LAST_NAME_SOUNDEX = ':lastNameSoundex' " + " and ");
				params.addValue("lastNameSoundex", objSearchDao.getLastNameSoundex());
			}
			if (objSearchDao.getBirthPlace() != null) {
				sqlQuery.append("BIRTH_PLACE = ':birthPlace' " + " and ");
				params.addValue("birthPlace", objSearchDao.getBirthPlace());
			}
			if (objSearchDao.getBirthCountryCode() != null) {
				sqlQuery.append("BIRTH_COUNTRY_CODE = ':birthCountryCode' " + " and ");
				params.addValue("birthCountryCode", objSearchDao.getBirthCountryCode());
			}
			if (objSearchDao.getCreateDate() != null) {
				sqlQuery.append("CREATE_DATE = :createDate " + " and ");
				params.addValue("createDate", objSearchDao.getCreateDate());
			}
			if (objSearchDao.getLastNameKey() != null) {
				sqlQuery.append("LAST_NAME_KEY = ':lastNameKey'" + " and ");
				params.addValue("lastNameKey", objSearchDao.getLastNameKey());
			}
			if (objSearchDao.getAliasOffenderId() != null) {
				sqlQuery.append("ALIAS_OFFENDER_ID = :aliasOffenderId " + " and ");
				params.addValue("aliasOffenderId", objSearchDao.getAliasOffenderId());

			}
			if (objSearchDao.getFirstNameKey() != null) {
				sqlQuery.append("FIRST_NAME_KEY = ':firstNameKey' " + " and ");
				params.addValue("firstNameKey", objSearchDao.getFirstNameKey());
			}
			if (objSearchDao.getMiddleNameKey() != null) {
				sqlQuery.append("MIDDLE_NAME_KEY = ':middleNameKey' " + " and ");
				params.addValue("middleNameKey", objSearchDao.getMiddleNameKey());
			}
			if (objSearchDao.getOffenderIdDisplay() != null) {
				sqlQuery.append("OFFENDER_ID_DISPLAY = :offenderIdDisplay " + " and ");
				params.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
			}
			if (objSearchDao.getRootOffenderId() != null) {
				sqlQuery.append("ROOT_OFFENDER_ID = :rootOffenderId " + " and ");
				params.addValue("rootOffenderId", objSearchDao.getRootOffenderId());
			}
			if (objSearchDao.getCaseloadType() != null) {
				sqlQuery.append("CASELOAD_TYPE = ':caseloadType' " + " and ");
				params.addValue("caseloadType", objSearchDao.getCaseloadType());
			}
			if (objSearchDao.getModifyUserId() != null) {
				sqlQuery.append("MODIFY_USER_ID = ':modifyDatetime' " + " and ");
				params.addValue("modifyUserId", objSearchDao.getModifyUserId());
			}
			if (objSearchDao.getModifyDateTime() != null) {
				sqlQuery.append("MODIFY_DATETIME = :modifyDatetime " + " and ");
				params.addValue("modifyDatetime", objSearchDao.getModifyDateTime());
			}
			if (objSearchDao.getAliasNameType() != null) {
				sqlQuery.append("ALIAS_NAME_TYPE = ':aliasNameType' " + " and ");
				params.addValue("aliasNameType", objSearchDao.getAliasNameType());
			}
			if (objSearchDao.getParentOffenderId() != null) {
				sqlQuery.append("PARENT_OFFENDER_ID = :parentOffenderId " + " and ");
				params.addValue("parentOffenderId", objSearchDao.getParentOffenderId());
			}
			if (objSearchDao.getUniqueObligationFlag() != null) {
				sqlQuery.append("UNIQUE_OBLIGATION_FLAG = ':uniqueObligationFlag' " + " and ");
				params.addValue("uniqueObligationFlag", objSearchDao.getUniqueObligationFlag());
			}
			if (objSearchDao.getSuspendedFlag() != null) {
				sqlQuery.append("SUSPENDED_FLAG = ':suspendedFlag' " + " and ");
				params.addValue("suspendedFlag", objSearchDao.getSuspendedFlag());
			}
			if (objSearchDao.getSuspendedDate() != null) {
				sqlQuery.append("SUSPENDED_DATE = :suspendedDate " + " and ");
				params.addValue("suspendedDate", objSearchDao.getSuspendedDate());
			}
			if (objSearchDao.getRaceCode() != null) {
				sqlQuery.append("RACE_CODE =  ':remarkCode' " + " and ");
				params.addValue("raceCode", objSearchDao.getRaceCode());
			}
			if (objSearchDao.getRemarkCode() != null) {
				sqlQuery.append("REMARK_CODE = ':remarkCode' " + " and ");
				params.addValue("remarkCode", objSearchDao.getRemarkCode());
			}
			if (objSearchDao.getAddInfoCode() != null) {
				sqlQuery.append("ADD_INFO_CODE = ':addInfoCode' " + " and ");
				params.addValue("addInfoCode", objSearchDao.getAddInfoCode());
			}
			if (objSearchDao.getBirthCounty() != null) {
				sqlQuery.append("BIRTH_COUNTY =  ':birthCounty'" + " and ");
				params.addValue("birthCounty", objSearchDao.getBirthCounty());
			}
			if (objSearchDao.getBirthState() != null) {
				sqlQuery.append("BIRTH_STATE = ':birthState' " + " and ");
				params.addValue("birthState", objSearchDao.getBirthState());
			}
			if (objSearchDao.getMiddleName2() != null) {
				sqlQuery.append("MIDDLE_NAME_2 = ':middleName2' " + " and ");
				params.addValue("middleName2", objSearchDao.getMiddleName2());
			}
			if (objSearchDao.getTitle() != null) {
				sqlQuery.append("TITLE = ':title' " + " and ");
				params.addValue("title", objSearchDao.getTitle());
			}
			if (objSearchDao.getAge() != null) {
				sqlQuery.append("AGE = :age " + " and ");
				params.addValue("age", objSearchDao.getAge());
			}
			if (objSearchDao.getCreateUserId() != null) {
				sqlQuery.append("CREATE_USER_ID = ':createUserId' " + " and ");
				params.addValue("createUserId", objSearchDao.getCreateUserId());
			}
			if (objSearchDao.getLastNameAlphaKey() != null) {
				sqlQuery.append("LAST_NAME_ALPHA_KEY = ':lastNameAlphaKey' " + " and ");
				params.addValue("lastNameAlphaKey", objSearchDao.getLastNameAlphaKey());
			}
			if (objSearchDao.getCreateDateTime() != null) {
				sqlQuery.append("CREATE_DATETIME = :createDateTime " + " and ");
				params.addValue("createDateTime", objSearchDao.getCreateDateTime());
			}
			if (objSearchDao.getNameSequence() != null) {
				sqlQuery.append("NAME_SEQUENCE = ':nameSequence'  " + " and ");
				params.addValue("nameSequence", objSearchDao.getNameSequence());
			}
			if (objSearchDao.getSealFlag() != null) {
				sqlQuery.append("SEAL_FLAG = ':sealFlag' " + " and ");
				params.addValue("sealFlag", objSearchDao.getSealFlag());
			}
			if (objSearchDao.getMiddleName() != null) {
				sqlQuery.append("SECOND_MIDDLE_NAME = ':secondMiddleName'" + " and ");
				params.addValue("secondMiddleName", objSearchDao.getsecondMiddleName());
			}
		}
		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<Offenders> OffendersRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, Offenders.class,
				offendersMapping);
		final ArrayList<Offenders> returnList = (ArrayList<Offenders>) namedParameterJdbcTemplate.query(preparedSql,
				params, OffendersRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param lstOffenders
	 *            List<Offenders>
	 * @return List<Integer>
	 */
	public Integer aliasInsertOffenders(final List<Offenders> lstOffenders) {
		final String sql = getQuery("OCUCOFFE_ALIAS_INSERT_OFFENDERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Offenders offenders : lstOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(offenders));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param OffenderIdentifiers
	 *            objSearchDao
	 * @return List<OffenderIdentifiers>
	 */
	public List<OffenderIdentifier> offIdSearchOffenderIdentifiers(final OffenderIdentifier objSearchDao) {
		final String sql = getQuery("OCUCOFFE_OFFID_FIND_OFFENDER_IDENTIFIERS");
		final MapSqlParameterSource params = new MapSqlParameterSource();
		String preparedSql = null;
		StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderId() != 0) {
				sqlQuery.append("OFFENDER_ID = :offenderId and ");
				params.addValue("offenderId", objSearchDao.getOffenderId());
			}
			if (objSearchDao.getRootOffenderId() != null) {
				sqlQuery.append("ROOT_OFFENDER_ID = :rootOffenderId and ");
				params.addValue("rootOffenderId", objSearchDao.getRootOffenderId());
			}
			if (objSearchDao.getOffenderId() == 0 && objSearchDao.getRootOffenderId() == null) {
				return Collections.emptyList();
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		List<OffenderIdentifier> refList = new ArrayList<OffenderIdentifier>();
		final RowMapper<OffenderIdentifier> OffenderIdentifiersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderIdentifier.class, offenderIdentifiersMapping);
		refList = namedParameterJdbcTemplate.query(preparedSql, params, OffenderIdentifiersRowMapper);
		return refList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param lstOffenderIdentifiers
	 *            List<OffenderIdentifiers>
	 * @return List<Integer>
	 */
	@Override
	public Integer offIdInsertOffenderIdentifiers(final List<OffenderIdentifier> lstOffenderIdentifiers) {
		final String sql = getQuery("OCUCOFFE_OFFID_INSERT_OFFENDER_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIdentifier offenderIdentifiers : lstOffenderIdentifiers) {
			parameters.add(new BeanPropertySqlParameterSource(offenderIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderIdentifiers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @return Integer
	 * @param lstOffenderIdentifiers
	 *            List<OffenderIdentifiers>
	 */
	public Integer offIdUpdateOffenderIdentifiers(final List<OffenderIdentifier> lstOffenderIdentifiers) {
		final String sql = getQuery("OCUCOFFE_OFFID_UPDATE_OFFENDER_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIdentifier offenderIdentifiers : lstOffenderIdentifiers) {
			parameters.add(new BeanPropertySqlParameterSource(offenderIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderIdentifiers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	public List<Object> offOnCheckDeleteMasteroffIdAllCur(final OffenderIdentifier paramBean) {
		final String sql = getQuery("OCUCOFFE_OFF_ONCHECKDELETEMASTER_OFF_ID_ALL_CUR");
		List<Object> refList = new ArrayList<Object>();
		refList = namedParameterJdbcTemplate.queryForList(sql,
				createParams("rootOffenderId", paramBean.getRootOffenderId()), Object.class);
		return refList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 *
	 * @param Offenders
	 *            paramBean
	 *
	 */
	public List<Object> offOnCheckDeleteMasteraliasCur(final Offenders paramBean) {
		final String sql = getQuery("OCUCOFFE_OFF_ONCHECKDELETEMASTER_ALIAS_CUR");
		final List<Object> returnList = namedParameterJdbcTemplate.queryForList(sql,
				createParams("ROOTOFFENDERID", paramBean.getOffenderId()), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	public List<Object> aliasOnCheckDeleteMasteroffIdCur(final OffenderIdentifier paramBean) {
		final String sql = getQuery("OCUCOFFE_ALIAS_ONCHECKDELETEMASTER_OFF_ID_CUR");
		final List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("OFFENDERID", paramBean.getOffenderId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return ReferenceCodes
	 * @param ReferenceCodes
	 *            paramBean
	 */
	public ReferenceCodes whenNewRecordInstancedefaultSeqCur(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCUCOFFE_OCUCOFFE_WHENNEWRECORDINSTANCE_DEFAULT_SEQ_CUR");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 * @param Dual
	 *            paramBean
	 */
	public Object whenNewFormInstance(final Dual paramBean) {
		final String sql = getQuery("OCUCOFFE_OCUCOFFE_WHENNEWFORMINSTANCE");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		final Dual returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return ReferenceCodes
	 * @param ReferenceCodes
	 *            paramBean
	 */
	public ReferenceCodes postQueryreferenceCodesC(ReferenceCodes paramBean) {
		final String sql = getQuery("OCUCOFFE_OCUCOFFE_POSTQUERY_REFERENCE_CODES_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_DOMAIN", paramBean.getDomain(), "P_CODE", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called 0
	 * 
	 * @return Offenders
	 * @param Offenders
	 *            paramBean
	 */
	public Offenders postInsertgetRootOffenderId(Offenders paramBean) {
		final String sql = getQuery("OCUCOFFE_OCUCOFFE_POSTINSERT_GET_ROOT_OFFENDER_ID_C");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		final Offenders returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERID", paramBean.getOffenderId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 * @param Dual
	 *            paramBean
	 */
	public Object preInsertgetNextAlias(final Dual paramBean) {
		final String sql = getQuery("OCUCOFFE_OCUCOFFE_PREINSERT_GET_NEXT_ALIAS_C");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		final Dual returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return OffenderIdentifiers
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	public List<Object> preInsertgetNextIdentifier(final OffenderIdentifier paramBean) {
		final String sql = getQuery("OCUCOFFE_OCUCOFFE_PREINSERT_GET_NEXT_IDENTIFIER_C");
		final List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("OFFENDERID", paramBean.getOffenderId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<OffenderIdentifiers>
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	public List<Object> preInsertrecordEx(final OffenderIdentifier paramBean) {
		final String sql = getQuery("OCUCOFFE_OCUCOFFE_PREINSERT_RECORD_EX");
		final List<Object> returnList = namedParameterJdbcTemplate.queryForList(sql,
				createParams("LV_ROOT_OFFENDER_ID", paramBean.getRootOffenderId(), "LV_IDENTIFIER_TYPE",
						paramBean.getIdentifierType(), "IdentifierType", paramBean.getIdentifierType(), "LV_IDENTIFIER",
						paramBean.getIdentifier()),
				Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return OmsModules
	 * @param OmsModules
	 *            paramBean
	 */

	@Override
	public List<Object> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OCUCOFFE_CREATE_FORM_GLOBALS");
		List<Object> fingerPringsObj = null;
		try {
			fingerPringsObj = (namedParameterJdbcTemplate.queryForList(sql,
					createParams("MODULE_NAME", paramBean.getModuleName()), Object.class));
		} catch (EmptyResultDataAccessException e) {
			// log.error("", e);
		}
		return fingerPringsObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return SystemProfiles
	 * @param SystemProfiles
	 *            paramBean
	 */
	public SystemProfiles ageValidationvsRangecur(final SystemProfiles paramBean) {
		final String sql = getQuery("OCUCOFFE_AGE_VALIDATION_VS_RANGECUR");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 * @param Dual
	 *            paramBean
	 */
	public Object ageValidationvsAgecur(final Date paramBean) {
		final String sql = getQuery("OCUCOFFE_AGE_VALIDATION_VS_AGECUR");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("BIRTHDATE",new java.sql.Date(paramBean.getTime())), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Offenders
	 * @param Offenders
	 *            paramBean
	 */
	public Object validateAliasescheckForDupOffCur(final Offenders paramBean) {
		//
		final String sql = getQuery("OCUCOFFE_VALIDATE_ALIASES_CHECK_FOR_DUP_OFF_CUR");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Offenders
	 * @param Offenders
	 *            paramBean
	 */
	/*
	 * public Object validateAliasescheckDupNameCur(final Offenders paramBean) {
	 * final String sql =
	 * getQuery("OCUCOFFE_VALIDATE_ALIASES_CHECK_DUP_NAME_CUR"); final
	 * RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
	 * Offenders.class, offendersMapping); final Object returnObj =
	 * namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
	 * columnRowMapper); return returnObj; }
	 * 
	 * /** This method is execute a sql query when trigger event is called
	 * 
	 * @return List<OffenderIdentifiers>
	 * 
	 * @param OffenderIdentifiers paramBean
	 */
	public List<Object> checkPncExistsgetPncEx(final OffenderIdentifier paramBean) {
		final String sql = getQuery("OCUCOFFE_CHECK_PNC_EXISTS_GET_PNC_EXC");

		final List<Object> returnList = namedParameterJdbcTemplate.queryForList(sql,
				createParams("P_IDENTIFIER", paramBean.getIdentifier(), "P_ROOT_OFF_ID", paramBean.getRootOffenderId()),
				Object.class);
		return returnList;
	}

	@Override
	public List<OffenderIdentifier> offIdAllSearchOffenderIdentifiers(final OffenderIdentifier searchBean) {
		return null;
	}

	@Override
	public List<ReferenceCodes> rgIdentifierTypeRgroup() {
		return null;
	}

	@Override
	public Object validateAliasescheckDupNameCur(Offenders paramBean) {
		final String sql = getQuery("OCUCOFFE_VALIDATE_ALIASES_CHECK_DUP_NAME_CUR");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(paramBean.getBirthDate());
		final Long returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("lastName", paramBean.getLastName(), "birthdate", strDate), Long.class);
		return returnObj;
	}

	@Override
	public Integer getOffenderMinAge(String caseload) {
		final String sql = getQuery("OCUCOFFE_GET_OFFENDER_MIN_AGE");
		final Integer value = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOADID", caseload),
				Integer.class);
		return value;
	}

	@Override
	public Long checkOffenderIdDisplay(String offenderIdDisplay) {
		final String sql = getQuery("OCUCOFFE_CHECK_OFFENDER_ID_DISPLAY");
		final Long value = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderIdDisplay", offenderIdDisplay), Long.class);
		return value;
	}

	@Override
	public Date ocucoffeGetCurrentDate() {
		final String sql = getQuery("OCUCOFFE_GET_CURRENT_DATE");
		final Date value = namedParameterJdbcTemplate.queryForObject(sql,
				createParams(), Date.class);
		return value;
		
	}

	@Override
	public Boolean checkPncValidation(String identifiers) {
		final String sql = getQuery("OCUCOFFE_CHECK_PNC_VALIDATION");
		final Boolean value = namedParameterJdbcTemplate.queryForObject(sql,
				createParams(), Boolean.class);
		return value;
	}

	@Override
	public String getIdDisplayProfileValue() {
		final String sql = getQuery("OCUCOFFE_GET_ID_DISPLAY_PROFILE_VALUES");
		try {
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams(), String.class);
		} catch(EmptyResultDataAccessException e){
			return "N";
		}
	}
}
