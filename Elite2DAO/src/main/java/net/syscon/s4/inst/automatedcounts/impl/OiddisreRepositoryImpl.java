package net.syscon.s4.inst.automatedcounts.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.OiddisreRepository;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import oracle.jdbc.OracleTypes;

/**
 * Class OiddisreRepositoryImpl
 */
@Repository
public class OiddisreRepositoryImpl extends RepositoryBase implements OiddisreRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiddisreRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> agencyCountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();

	/**
	 * Creates new OiddisreRepositoryImpl class Object
	 */
	public OiddisreRepositoryImpl() {
		// OiddisreRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyCounts
	 *
	 * @return List<AgencyCounts>
	 *
	 * @throws SQLException
	 */
	public List<AgencyCounts> agencyCountsExecuteQuery(AgencyCounts objSearchDao) {
		final String sql = getQuery("OIDDISRE_AGENCYCOUNTS_FIND_AGENCY_COUNTS");
		final RowMapper<AgencyCounts> AgencyCountsRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCounts.class,
				agencyCountsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), AgencyCountsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyCounts
	 *            List<AgencyCounts>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer agencyCountsInsertAgencyCounts(final List<AgencyCounts> lstAgencyCounts) {
		String sql = getQuery("OIDDISRE_AGENCYCOUNTS_INSERT_AGENCY_COUNTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (AgencyCounts agencyCounts : lstAgencyCounts) {
			parameters.add(new BeanPropertySqlParameterSource(agencyCounts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyCounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyCounts
	 *            List<AgencyCounts>
	 *
	 * @throws SQLException
	 */
	public Integer agencyCountsUpdateAgencyCounts(final AgencyCounts bean) {
		String sql = getQuery("OIDDISRE_AGENCYCOUNTS_UPDATE_AGENCY_COUNTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyCounts
	 *            List<AgencyCounts>
	 *
	 * @throws SQLException
	 */
	public Integer agencyCountsDeleteAgencyCounts(final AgencyCounts bean) {
		String sql = getQuery("OIDDISRE_AGENCYCOUNTS_DELETE_AGENCY_COUNTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		try {
			String tableName = "LOCKED_MODULES";
			String whereClause = "SESSION_ID = :sessionId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method agencyCountsDeleteAgencyCounts", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer cancelCountProcedure(final AgencyCounts paramBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SESSION_ID", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDCOUNT").withProcedureName("CANCEL_COUNT").declareParameters(sqlParameters);
		inParamMap.put("P_SESSION_ID", paramBean.getSessionId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
		return 1;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkDiscrepRsnRecordGroup() {
		final String sql = getQuery("OIDDISRE_FIND_CGFKDISCREPRSN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception cgfkDiscrepRsnRecordGroup: Oiddisre:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(OmsModules paramBean) {
		final String sql = getQuery("OIDDISRE_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * printList
	 *
	 * @param params
	 *
	 */

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgyLocDEFAULT_AGY_LOC
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> defaultAgyLoc(AgencyLocations paramBean) {
		final String sql = getQuery("OIDDISRE_DEFAULT_AGY_LOC_DEFAULT_AGY_LOC");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}
}
