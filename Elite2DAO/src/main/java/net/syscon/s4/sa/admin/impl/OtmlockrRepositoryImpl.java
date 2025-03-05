package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.sa.admin.OtmlockrRepository;

/**
 * Class OtmlockrRepositoryImpl
 */
@Repository
public class OtmlockrRepositoryImpl extends RepositoryBase implements OtmlockrRepository {

	private static Logger logger = LogManager.getLogger(OtmlockrRepositoryImpl.class.getName());
	
	/**
	 * Creates new OtmlockrRepositoryImpl class Object
	 */
	public OtmlockrRepositoryImpl() {
		// OtmlockrRepositoryImpl
	}

	private final Map<String, FieldMapper> lockedModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROW_ID", new FieldMapper("rowId")).build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            LockedModules
	 *
	 * @return List<LockedModules>
	 *
	 * @throws SQLException
	 */
	public List<LockedModules> lockModExecuteQuery(final LockedModules objSearchDao) {
		final String sql = getQuery("OTMLOCKR_LOCKMOD_FIND_LOCKED_MODULES");
		final RowMapper<LockedModules> lockedModulesRowMapper = Row2BeanRowMapper.makeMapping(sql, LockedModules.class,
				lockedModulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), lockedModulesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstLockedModules
	 *            List<LockedModules>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer lockModInsertLockedModules(final List<LockedModules> lstLockedModules) {
		final String sql = getQuery("OTMLOCKR_LOCKMOD_INSERT_LOCKED_MODULES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final LockedModules lockedModules : lstLockedModules) {
			parameters.add(new BeanPropertySqlParameterSource(lockedModules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLockedModules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstLockedModules
	 *            List<LockedModules>
	 *
	 * @throws SQLException
	 */
	public Integer lockModUpdateLockedModules(final List<LockedModules> lstLockedModules) {
		final String sql = getQuery("OTMLOCKR_LOCKMOD_UPDATE_LOCKED_MODULES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final LockedModules lockedModules : lstLockedModules) {
			parameters.add(new BeanPropertySqlParameterSource(lockedModules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLockedModules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstLockedModules
	 *            List<LockedModules>
	 *
	 * @throws SQLException
	 */
	public Integer lockModDeleteLockedModules(final List<LockedModules> lstLockedModules) {
		final String sql = getQuery("OTMLOCKR_LOCKMOD_DELETE_LOCKED_MODULES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final LockedModules lockedModules : lstLockedModules) {
			parameters.add(new BeanPropertySqlParameterSource(lockedModules));
		}
		try {
			String tableName = "LOCKED_MODULES";
			String whereClause = "MODULE_NAME=:moduleName AND CASELOAD_ID=:caseloadId AND ROW_ID=:rowId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method lockModDeleteLockedModules", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLockedModules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	@Override
	public List<SysDual> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OTMLOCKR_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}
	
	@Override
	public Long getRepotingLocId(LockedModules lockedModules) {
		final String sql = getQuery("OTMLOCKR_GET_REPOTING_LOC_ID");
		Long repotingLocId = null;
		try {
			repotingLocId = namedParameterJdbcTemplate.queryForObject(sql, createParams("createUserId",lockedModules.getCreateUserId()), Long.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return repotingLocId;
	}
	
	@Override
	public Integer deleteInitiateRecordsOfAgencyLocationCounts(Long repotingLocId) {
		final String sql = getQuery("OTMLOCKR_DELETE_AGENCY_LOCATION_COUNTS");
		Integer lireturn = 0;
		try {
			lireturn = namedParameterJdbcTemplate.queryForObject(sql, createParams("repotingLocId",repotingLocId), Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return lireturn;
		}
		return lireturn;
	}

	@Override
	public Integer deleteInitiateRecords(Long repotingLocId) {
		final String sql = getQuery("OTMLOCKR_DELETE_INITIATE_RECORDS");
		Integer lireturn = 0;
		try {
			lireturn = namedParameterJdbcTemplate.queryForObject(sql, createParams("repotingLocId",repotingLocId), Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return lireturn;
		}
		return lireturn;
	}

}
