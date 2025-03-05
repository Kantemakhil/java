package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StgSearchV1;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.securitythreatgroups.OsistgkwRepository;

/**
 * Class OsistgkwRepositoryImpl
 */
@Repository
public class OsistgkwRepositoryImpl extends RepositoryBase implements OsistgkwRepository {



	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsistgkwRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> securityThreatGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NULL", 							new FieldMapper(" null"))
			.put("DESCRIPTION",						new FieldMapper(" description "))
			.put("STG_ID", 							new FieldMapper("stgId"))
			.put("DESCRIPTIO", 						new FieldMapper("descriptio"))
			.build();

	
	private final Map<String, FieldMapper> stgSearchV1Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID", 							new FieldMapper("stgId"))
			.put("SEQ", 							new FieldMapper("seq"))
			.put("CODE", 						    new FieldMapper("code"))
			.put("DESCRIPTION",						new FieldMapper("description"))
			.put("SOURCE",						    new FieldMapper("source"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				 new FieldMapper("description"))
			.build();

	/**
	 * Creates new OsistgkwRepositoryImpl class Object
	 */
	public OsistgkwRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StgSearchV1
	 *
	 * @return List<StgSearchV1>
	 *
	 * 
	 */
	public List<StgSearchV1> stgSearchV1ExecuteQuery(final StgSearchV1 objSearchDao) {
		final String sql = getQuery("OSISTGKW_STGSEARCHV1_FIND_STG_SEARCH_V1");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		List<StgSearchV1> returnList = new ArrayList<StgSearchV1>();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if ("IDENTIFYING WORDS".equals(objSearchDao.getIdentifier())) {
				sqlQuery.append(
						" (UPPER(CODE) LIKE upper(:KEYWORDTEXT) OR UPPER(DESCRIPTION) LIKE upper(:KEYWORDTEXT)) and source = 'I' ");
				params.addValue("KEYWORDTEXT", "%" + objSearchDao.getKeywordText() + "%");
			} else if ("GROUP CHARACTERISTICS".equals(objSearchDao.getIdentifier())) {
				sqlQuery.append(" (UPPER(DESCRIPTION) LIKE '%' || upper(:KEYWORDTEXT) ||'%') and source = 'C' ");
				params.addValue("KEYWORDTEXT",objSearchDao.getKeywordText());
			} else if (null == objSearchDao.getIdentifier()) {
				if (null == objSearchDao.getKeywordText()) {
					objSearchDao.setKeywordText("Y");
					return returnList;
				}
				sqlQuery.append(
						" ((UPPER(CODE) LIKE upper(:KEYWORDTEXT) OR UPPER(DESCRIPTION) LIKE upper(:KEYWORDTEXT)) "
								+ " and source = 'I') or (UPPER(DESCRIPTION) LIKE upper(:KEYWORDTEXT) and source = 'C') ");
				params.addValue("KEYWORDTEXT", "%" + objSearchDao.getKeywordText() + "%");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		final RowMapper<StgSearchV1> StgSearchV1RowMapper = Row2BeanRowMapper.makeMapping(sql, StgSearchV1.class,
				stgSearchV1Mapping);
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, params, StgSearchV1RowMapper);
		} catch (Exception e) {
			logger.error("stgSearchV1ExecuteQuery", e);
		}

		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * 
	 * @param lstStgSearchV1
	 * @return Integer
	 */

	public Integer stgSearchV1InsertStgSearchV1(final List<StgSearchV1> lstStgSearchV1) {
		final String sql = getQuery("OSISTGKW_STGSEARCHV1_INSERT_STG_SEARCH_V1");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgSearchV1.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStgSearchV1
	 * @return Integer
	 *
	 * 
	 */
	public Integer stgSearchV1UpdateStgSearchV1(final List<StgSearchV1> lstStgSearchV1) {
		final String sql = getQuery("OSISTGKW_STGSEARCHV1_UPDATE_STG_SEARCH_V1");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgSearchV1 stgSearchV1 : lstStgSearchV1) {
			parameters.add(new BeanPropertySqlParameterSource(stgSearchV1));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgSearchV1.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstStgSearchV1
	 * @return Integer
	 */
	public Integer stgSearchV1DeleteStgSearchV1(final List<StgSearchV1> lstStgSearchV1) {
		final String sql = getQuery("OSISTGKW_STGSEARCHV1_DELETE_STG_SEARCH_V1");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgSearchV1 stgSearchV1 : lstStgSearchV1) {
			parameters.add(new BeanPropertySqlParameterSource(stgSearchV1));
		}
		try {
			String tableName = "STG_SEARCH_V1";
			batchUpdatePreDeletedRows(tableName, null, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgSearchV1.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OSISTGKW_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		List<OmsModules> returnList = new ArrayList<OmsModules>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("createFormGlobals", e);
		}

		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getStgGroupDescription
	 *
	 * @param params
	 *
	 */
	public List<SecurityThreatGroups> getStgGroupDescription(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OSISTGKW_GET_STG_GROUP_DESCRIPTION");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		List<SecurityThreatGroups> returnList = new ArrayList<SecurityThreatGroups>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("getStgGroupDescription", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vprofvaluestgDescription
	 * 
	 * @return String
	 *
	 */
	public String vprofvaluestgDescription() {
		final String sql = getQuery("OSISTGKW_V_PROF_VALUE");

		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);

		} catch (Exception e) {
			logger.error("vprofvaluestgDescription", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vprofvaluestgDescriptionOne
	 *
	 * @param stgId
	 * @return String
	 *
	 */
	public String vprofvaluestgDescriptionOne(final BigDecimal stgId) {
		final String sql = getQuery("OSISTGKW_V_PROF_VALUE_DESCRIPTION_ONE");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), String.class);

		} catch (Exception e) {
			logger.error("vprofvaluestgDescriptionOne", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vprofvaluestgDescriptionTwo
	 *
	 * @param stgId
	 * @return String
	 *
	 */
	public String vprofvaluestgDescriptionTwo(final BigDecimal stgId) {
		final String sql = getQuery("OSISTGKW_V_PROF_VALUE_DESCRIPTION_TWO");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), String.class);

		} catch (Exception e) {
			logger.error("vprofvaluestgDescriptionTwo", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vprofvaluestgDescriptionThree
	 *
	 * @param stgId
	 * @return String
	 *
	 */
	public String vprofvaluestgDescriptionThree(final BigDecimal stgId) {
		final String sql = getQuery("OSISTGKW_V_PROF_VALUE_DESCRIPTION_THREE");
		String returnList = null;

		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), String.class);

		} catch (Exception e) {
			logger.error("vprofvaluestgDescriptionThree", e);
		}
		return returnList;
	}

}
