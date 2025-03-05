package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriods;
import net.syscon.s4.iwp.OcmcprevRepository;

/**
 * Class OcmcprevRepositoryImpl
 */
@Repository
public class OcmcprevRepositoryImpl extends RepositoryBase implements OcmcprevRepository {

	/**
	 * Creates new OcmcprevRepositoryImpl class Object
	 */
	public OcmcprevRepositoryImpl() {
		
	}
	private static Logger logger = LogManager.getLogger(OcmcprevRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SUPERVISION_LEVEL", new FieldMapper("supervisionLevel"))
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> caseReviewPeriodsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseReviewPeriods
	 *
	 * @return List<CaseReviewPeriods>
	 *
	 * @throws SQLException
	 */
	public List<CaseReviewPeriods> caseReviewPeriodsExecuteQuery(final CaseReviewPeriods objSearchDao) {
		final String sql = getQuery("OCMCPREV_CASEREVIEWPERIODS_FIND_CASE_REVIEW_PERIODS");
		final RowMapper<CaseReviewPeriods> caseReviewPeriodsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseReviewPeriods.class, caseReviewPeriodsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), caseReviewPeriodsRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCaseReviewPeriods
	 *            List<CaseReviewPeriods>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer caseReviewPeriodsInsertCaseReviewPeriods(final List<CaseReviewPeriods> lstCaseReviewPeriods) {
		final String sql = getQuery("OCMCPREV_CASEREVIEWPERIODS_INSERT_CASE_REVIEW_PERIODS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseReviewPeriods caseReviewPeriods : lstCaseReviewPeriods) {
			parameters.add(new BeanPropertySqlParameterSource(caseReviewPeriods));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseReviewPeriods.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseReviewPeriods
	 *            List<CaseReviewPeriods>
	 *
	 * @throws SQLException
	 */
	  public Integer caseReviewPeriodsUpdateCaseReviewPeriods(final List<CaseReviewPeriods> lstCaseReviewPeriods) {
		final String sql = getQuery("OCMCPREV_CASEREVIEWPERIODS_UPDATE_CASE_REVIEW_PERIODS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseReviewPeriods caseReviewPeriods : lstCaseReviewPeriods) {
			parameters.add(new BeanPropertySqlParameterSource(caseReviewPeriods));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseReviewPeriods.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCaseReviewPeriods
	 *            List<CaseReviewPeriods>
	 *
	 * @throws SQLException
	 */
	public Integer caseReviewPeriodsDeleteCaseReviewPeriods(final List<CaseReviewPeriods> lstCaseReviewPeriods) {
		final String sql = getQuery("OCMCPREV_CASEREVIEWPERIODS_DELETE_CASE_REVIEW_PERIODS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseReviewPeriods caseReviewPeriods : lstCaseReviewPeriods) {
			parameters.add(new BeanPropertySqlParameterSource(caseReviewPeriods));
		}
		try {
			String tableName = "case_review_periods";
			String whereClause = "supervision_level = :supervisionLevel";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method caseReviewPeriodsDeleteCaseReviewPeriods", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseReviewPeriods.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgSupLevelRecordGroup() {
		final String sql = getQuery("OCMCPREV_FIND_RGSUPLEVEL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * caseReviewPeriodsPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> caseReviewPeriodsPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMCPREV_CASE_REVIEW_PERIODS_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
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
		final String sql = getQuery("OCMCPREV_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

}
