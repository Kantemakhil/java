package net.syscon.s4.inmate.trust.financialsmaintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CaseloadLimits;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmcslimRepository;

/**
 * Class OtmcslimRepositoryImpl
 */
@Repository
public class OtmcslimRepositoryImpl extends RepositoryBase implements OtmcslimRepository {

	private static Logger logger = LogManager.getLogger(OtmcslimRepositoryImpl.class.getName());
	
	/**
	 * Creates new OtmcslimRepositoryImpl class Object
	 */
	public OtmcslimRepositoryImpl() {
	}

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 			new FieldMapper("listSeq"))
			.put("PERIOD_TYPE", 		new FieldMapper("periodType"))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.put("LIMIT_TYPE", 			new FieldMapper("limitType")).build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 			new FieldMapper("listSeq"))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.put("CASELOAD_ID", 		new FieldMapper("caseloadId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 				new FieldMapper("code"))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.put("LIST_SEQ", 			new FieldMapper("listSeq"))
			.put("CASELOAD_ID", 		new FieldMapper("caseloadId")).build();

	private final Map<String, FieldMapper> caseloadLimitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 				new FieldMapper("code"))
			.put("DESCRIPTION",	 		new FieldMapper("description"))
			.put("CASELOAD_ID", 		new FieldMapper("caseloadId")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadLimits
	 *
	 * @return List<CaseloadLimits>
	 *
	 * 
	 */
	public List<CaseloadLimits> csldLimExecuteQuery(CaseloadLimits objSearchDao) {
		final String sql = getQuery("OTMCSLIM_CSLDLIM_FIND_CASELOAD_LIMITS");
		final RowMapper<CaseloadLimits> caseloadLimitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadLimits.class, caseloadLimitsMapping);
		List<CaseloadLimits> returnList = new ArrayList<CaseloadLimits>();
		String preparedSql = null;
		String preSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);

		if (objSearchDao != null) {
			sqlQuery.append("WHERE ");

			if (objSearchDao.getCaseloadId() != null) {
				sqlQuery.append("CASELOAD_ID = :CASELOAD_ID" + " AND  ");
				inParameterSource.addValue("CASELOAD_ID", objSearchDao.getCaseloadId());
			}
			if (objSearchDao.getLimitType() != null) {
				sqlQuery.append("LIMIT_TYPE = :LIMIT_TYPE" + " AND  ");
				inParameterSource.addValue("LIMIT_TYPE", objSearchDao.getLimitType());
			}
			if (objSearchDao.getPeriodType() != null) {
				sqlQuery.append("PERIOD_TYPE = :PERIOD_TYPE" + " AND  ");
				inParameterSource.addValue("PERIOD_TYPE", objSearchDao.getPeriodType());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		preSql = preparedSql.concat("  ORDER BY LIST_SEQ,CASELOAD_ID,LIMIT_TYPE,PERIOD_TYPE");
		returnList = namedParameterJdbcTemplate.query(preSql, inParameterSource, caseloadLimitsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCaseloadLimits
	 *            List<CaseloadLimits>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer csldLimInsertCaseloadLimits(final List<CaseloadLimits> lstCaseloadLimits) {
		int insertCount = 0;
		String sql = getQuery("OTMCSLIM_CSLDLIM_INSERT_CASELOAD_LIMITS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadLimits caseloadLimits : lstCaseloadLimits) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadLimits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadLimits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadLimits
	 *            List<CaseloadLimits>
	 *
	 * 
	 */
	public Integer csldLimUpdateCaseloadLimits(final List<CaseloadLimits> lstCaseloadLimits) {
		int insertCount = 0;
		String sql = getQuery("OTMCSLIM_CSLDLIM_UPDATE_CASELOAD_LIMITS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadLimits caseloadLimits : lstCaseloadLimits) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadLimits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadLimits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCaseloadLimits
	 *            List<CaseloadLimits>
	 *
	 * 
	 */
	public Integer csldLimDeleteCaseloadLimits(final List<CaseloadLimits> lstCaseloadLimits) {
		int deleteCount = 0;
		String sql = getQuery("OTMCSLIM_CSLDLIM_DELETE_CASELOAD_LIMITS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadLimits caseloadLimits : lstCaseloadLimits) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadLimits));
		}
		try {
			String tableName = "CASELOAD_LIMITS";
			String whereClause = "CASELOAD_ID	=:caseloadId AND LIMIT_TYPE=:limitType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldLimDeleteCaseloadLimits", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstCaseloadLimits.size() == returnArray.length) {
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
	public List<Caseloads> cgfkCsldLimCaseloadIdRecordGroup() {
		final String sql = getQuery("OTMCSLIM_FIND_CGFKCSLDLIMCASELOADID");
		final RowMapper<Caseloads> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, mMapping);

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
	public List<ReferenceCodes> cgfkCsldLimLimitTypeRecordGroup() {
		final String sql = getQuery("OTMCSLIM_FIND_CGFKCSLDLIMLIMITTYPE");
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
	public List<ReferenceCodes> cgfkCsldLimPeriodTypeRecordGroup() {
		final String sql = getQuery("OTMCSLIM_FIND_CGFKCSLDLIMPERIODTYPE");
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
	 * cgfkchkCsldLimCsldLimCsl
	 *
	 * @param params
	 *
	 */
	public List<Caseloads> cgfkchkCsldLimCsldLimCsl(Caseloads paramBean) {
		final String sql = getQuery("OTMCSLIM_CGFKCHK_CSLD_LIM_CSLD_LIM_CSL"); // 1
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		final ArrayList<Caseloads> returnList = (ArrayList<Caseloads>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldLimCsldLimPer
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkCsldLimCsldLimPer(ReferenceCodes paramBean) {
		final String sql = getQuery("OTMCSLIM_CGFKCHK_CSLD_LIM_CSLD_LIM_PER");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldLimCsldLimTyp
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkCsldLimCsldLimTyp(ReferenceCodes paramBean) {
		final String sql = getQuery("OTMCSLIM_CGFKCHK_CSLD_LIM_CSLD_LIM_TYP");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	public String getCaseloadIdDesc(final String caseloadId) {
		final String sql = getQuery("OTMCSLIM_GETCASELOADIDDESC");
		String desc = null;
		desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId), String.class);
		return desc;
	}

}
