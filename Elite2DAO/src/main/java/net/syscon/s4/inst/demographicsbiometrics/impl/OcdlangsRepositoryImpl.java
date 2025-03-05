package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderLanguages;
import net.syscon.s4.inst.demographicsbiometrics.OcdlangsRepository;

/**
 * Class OcdlangsRepositoryImpl
 */
@Repository
public class OcdlangsRepositoryImpl extends RepositoryBase implements OcdlangsRepository {
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdlangsRepositoryImpl.class.getName());

	
	private final Map<String, FieldMapper> refCodesMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION",               new FieldMapper("description"))
			.put("CODE",                      new FieldMapper("code"))
			.put("ACTIVE_FLAG", 			  new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> offLangMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG",                  new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",            new FieldMapper("createDatetime"))
			.put("SPEAK_SKILL",                new FieldMapper("speakSkill"))
			.put("PREFERED_WRITE_FLAG",        new FieldMapper("preferedWriteFlag"))
			.put("MODIFY_DATETIME",            new FieldMapper("modifyDatetime"))
			.put("INTERPRETER_REQUESTED_FLAG", new FieldMapper("interpreterRequestedFlag"))
			.put("NUMERACY_SKILL",             new FieldMapper("numeracySkill"))
			.put("PREFERED_SPEAK_FLAG",        new FieldMapper("preferedSpeakFlag"))
			.build();
	
	/**
	 * Creates new OcdlangsRepositoryImpl class Object
	 */
	public OcdlangsRepositoryImpl() {
	}


	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderLanguages
	 *
	 * @return List<OffenderLanguages>
	 *
	 */
	public List<OffenderLanguages> offPrimLangExecuteQuery(final OffenderLanguages objSearchDao) {
		final String sql = getQuery("OCDLANGS_OFFPRIMLANG_FIND_OFFENDER_LANGUAGES");
		List<OffenderLanguages> returnList = new ArrayList<OffenderLanguages>();
		final RowMapper<OffenderLanguages> offLangMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderLanguages.class, offLangMap);
		returnList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID",objSearchDao.getOffenderBookId()), offLangMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 *
	 */
	public int preInsert() {
		return 0;
	}


	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderLanguages
	 *            List<OffenderLanguages>
	 *
	 */
	public Integer offPrimLangUpdateOffenderLanguages(final List<OffenderLanguages> list) {
		final String sql = getQuery("OCDLANGS_OFFPRIMLANG_UPDATE_OFFENDER_LANGUAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderLanguages offenderLanguages : list) {
			parameters.add(new BeanPropertySqlParameterSource(offenderLanguages));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

/**
* This method is used to delete records from  data base tables based on
*
* @param lstOffenderLanguages List<OffenderLanguages>
*
*/
 public Integer offPrimLangDeleteOffenderLanguages(final List<OffenderLanguages> list) {
	final String sql = getQuery("OCDLANGS_OFFPRIMLANG_DELETE_OFFENDER_LANGUAGES");
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (final OffenderLanguages offenderLanguages : list) {
		 parameters.add(new BeanPropertySqlParameterSource(offenderLanguages));
	}
	try {
		String tableName = "OFFENDER_LANGUAGES";
		String whereClause = "OFFENDER_BOOK_ID  = :offenderBookId and LANGUAGE_TYPE  = :languageType";
		batchUpdatePreDeletedRows(tableName, whereClause , parameters);
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method offPrimLangDeleteOffenderLanguages", e);
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (list.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderLanguages
	 *
	 * @return List<OffenderLanguages>
	 *
	 */
	public List<OffenderLanguages> offSecLangExecuteQuery(final OffenderLanguages objSearchDao) {
		final String sql = getQuery("OCDLANGS_OFFSECLANG_FIND_OFFENDER_LANGUAGES");
		List<OffenderLanguages> returnList = new ArrayList<OffenderLanguages>();
		final RowMapper<OffenderLanguages> offLangMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderLanguages.class, offLangMap);
		returnList = namedParameterJdbcTemplate
				.query(sql, createParams("OFFENDERBOOKID",objSearchDao.getOffenderBookId()), offLangMapper);
		return returnList;
	}


	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderLanguages
	 *            List<OffenderLanguages>
	 *
	 */
	public Integer offSecLangUpdateOffenderLanguages(final List<OffenderLanguages> list) {
		final String sql = getQuery("OCDLANGS_OFFSECLANG_UPDATE_OFFENDER_LANGUAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderLanguages offenderLanguages : list) {
			 parameters.add(new BeanPropertySqlParameterSource(offenderLanguages));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

/**
* This method is used to delete records from  data base tables based on
*
* @param lstOffenderLanguages List<OffenderLanguages>
*
* @throws SQLException
*/
 public Integer offSecLangDeleteOffenderLanguages(final List<OffenderLanguages> list) {
	final String sql = getQuery("OCDLANGS_OFFSECLANG_DELETE_OFFENDER_LANGUAGES");
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (final OffenderLanguages offenderLanguages : list) {
		 parameters.add(new BeanPropertySqlParameterSource(offenderLanguages));
	}
	try {
		String tableName = "OFFENDER_LANGUAGES";
		String whereClause = "OFFENDER_BOOK_ID  = :offenderBookId and LANGUAGE_TYPE  = :languageType and LANGUAGE_CODE  = :languageCode";
		batchUpdatePreDeletedRows(tableName, whereClause , parameters);
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method offSecLangDeleteOffenderLanguages", e);
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (list.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgLangSkillsRecordGroup() {
		final String sql = getQuery("OCDLANGS_FIND_RGLANGSKILLS");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, refCodesMap);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("",e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPrefLangRecordGroup() {
		final String sql = getQuery("OCDLANGS_FIND_RGPREFLANG");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, refCodesMap);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("",e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSecLangRecordGroup(final String langCode , final String langCode1) {
		final String sql = getQuery("OCDLANGS_FIND_RGSECLANG");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, refCodesMap);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("LANGUAGECODE",langCode,"LANGUAGECODE1",langCode1), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("",e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Object> offBkgOnCheckDeleteMaster(final OffenderLanguages paramBean) {
		final String sql = getQuery("OCDLANGS_OFF_BKG_ONCHECKDELETEMASTER");
		List<Object> returnObj  = new ArrayList<Object>();
		returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams(paramBean),
				Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getPreferredDefault
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes getPreferredDefault() {
		final String sql = getQuery("OCDLANGS_GET_PREFERRED_DEFAULT");
		ReferenceCodes returnObj = new ReferenceCodes();
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMap);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}


	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderLanguages
	 *            List<OffenderLanguages>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer offPrimLangInsertOffenderLanguages(final List<OffenderLanguages> list) {
		final String sql = getQuery("OCDLANGS_OFFPRIMLANG_INSERT_OFFENDER_LANGUAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderLanguages offenderLanguages : list) {
			 parameters.add(new BeanPropertySqlParameterSource(offenderLanguages));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderLanguages
	 *            List<OffenderLanguages>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer offSecLangInsertOffenderLanguages(final List<OffenderLanguages> list) {
		final String sql = getQuery("OCDLANGS_OFFSECLANG_INSERT_OFFENDER_LANGUAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderLanguages offenderLanguages : list) {
			 parameters.add(new BeanPropertySqlParameterSource(offenderLanguages));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}


	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * prefLangWriteExecuteQuery
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderLanguages> prefLangWriteExecuteQuery(final OffenderLanguages searchRecord) {
		final String sql = getQuery("OCDLANGS_LANG_WRITE_FIND_OFFENDER_LANGUAGES");
		List<OffenderLanguages> returnList = new ArrayList<OffenderLanguages>();
		final RowMapper<OffenderLanguages> offLangMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderLanguages.class, offLangMap);
		returnList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID",searchRecord.getOffenderBookId()), offLangMapper);
		return returnList;
	}


	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * prefLangSpeakExecuteQuery
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderLanguages> prefLangSpeakExecuteQuery(final OffenderLanguages searchRecord) {
		final String sql = getQuery("OCDLANGS_LANG_SPEAK_FIND_OFFENDER_LANGUAGES");
		List<OffenderLanguages> returnList = new ArrayList<OffenderLanguages>();
		final RowMapper<OffenderLanguages> offLangMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderLanguages.class, offLangMap);
		returnList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID",searchRecord.getOffenderBookId()), offLangMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param insertList
	 *
	 * @return Integer
	 */
	@Override
	public Integer prefLangWriteInsertprefLangWrite(final List<OffenderLanguages> insertList) {
		final String sql = getQuery("OCDLANGS_OFFPRIMLANG_INSERT_OFFENDER_LANGUAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderLanguages offenderLanguages : insertList) {
			 parameters.add(new BeanPropertySqlParameterSource(offenderLanguages));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}


	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param updateList
	 *
	 * @return Integer
	 */
	public Integer prefLangWriteUpdateprefLangWrite(final List<OffenderLanguages> updateList) {
		final String sql = getQuery("OCDLANGS_PREFERRED_LANG_UPDATE_OFFENDER_LANGUAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderLanguages offenderLanguages : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(offenderLanguages));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}
