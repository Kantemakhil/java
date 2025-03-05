package net.syscon.s4.inst.workflow.maintenance.impl;

import java.sql.SQLException;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.maintenance.OcmdeftmRepository;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctions;

/**
 * Class OcmdeftmRepositoryImpl
 * 
 */
@Repository
public class OcmdeftmRepositoryImpl extends RepositoryBase implements OcmdeftmRepository {

	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmdeftmRepositoryImpl.class);
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("AREA_CODE", new FieldMapper(" areaCode "))
			.put("ABBREVIATION", new FieldMapper("abbreviation"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).build();
	private final Map<String, FieldMapper> agyLocTeamFunctionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_TEAM_FUNCTION_ID", new FieldMapper("agyLocTeamFunctionId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("FUNCTION_TYPE", new FieldMapper("functionType"))
			.put("TEAM_ID", new FieldMapper("teamId"))
			.put("OVERWRITTEN_FLAG", new FieldMapper("overwrittenFlag"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	/**
	 * Creates new OcmdeftmRepositoryImpl class Object
	 */
	public OcmdeftmRepositoryImpl() {
		// OcmdeftmRepositoryImpl
	}


	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgyLocTeamFunctions
	 *
	 * @return List<AgyLocTeamFunctions>
	 *
	 * @throws SQLException
	 */
	public List<AgyLocTeamFunctions> agyTmFnExecuteQuery(final AgencyLocations objSearchDao) {
		final String sql = getQuery("OCMDEFTM_AGYTMFN_FIND_AGY_LOC_TEAM_FUNCTIONS");
		final RowMapper<AgyLocTeamFunctions> AgyLocTeaRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgyLocTeamFunctions.class, agyLocTeamFunctionsMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);

		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID = :AGY_LOC_ID" + " AND ");
				inParameterSource.addValue("AGY_LOC_ID", objSearchDao.getAgyLocId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY active_flag DESC, oms_miscellaneous_getdesccode ( 'FUNCTION', function_type ) ";
		final ArrayList<AgyLocTeamFunctions> returnList = (ArrayList<AgyLocTeamFunctions>) namedParameterJdbcTemplate
				.query(preparedSql, inParameterSource, AgyLocTeaRowMapper);
		return returnList;
	}

	

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgyLocTeamFunctions
	 *            List<AgyLocTeamFunctions>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer agyTmFnInsertAgyLocTeamFunctions(final List<AgyLocTeamFunctions> lstAgyLocTeam) {
		final String sql = getQuery("OCMDEFTM_INSERT_AGY_LOC_TEAM_FUNCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AgyLocTeamFunctions agyLocTeamFunctions : lstAgyLocTeam) {
			parameters.add(new BeanPropertySqlParameterSource(agyLocTeamFunctions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstAgyLocTeam.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgyLocTeamFunctions
	 *            List<AgyLocTeamFunctions>
	 *
	 * @throws SQLException
	 */
	public Integer agyTmFnUpdateAgyLocTeamFunctions(final List<AgyLocTeamFunctions> lstAgyLocTeam) {
		final String sql = getQuery("OCMDEFTM_UPDATE_AGY_LOC_TEAM_FUNCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AgyLocTeamFunctions agyLocTeamFunctions : lstAgyLocTeam) {
			parameters.add(new BeanPropertySqlParameterSource(agyLocTeamFunctions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyLocTeam.size() == returnArray.length) {
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
	public List<AgencyLocations> rgAgyLocRecordGroup(final String agencyLocationType, final String caseloadId) {
		final String sql = getQuery("OCMDEFTM_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("AGENCYLOCTYPE", agencyLocationType, "CASELOADID", caseloadId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			
			logger.error("rgAgyLocRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAgyLocTypeRecordGroup(final String userId) {
		final String sql = getQuery("OCMDEFTM_FIND_RGAGYLOCTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("USER_NAME",userId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("rgAgyLocTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		final String sql = getQuery("OCMDEFTM_FIND_RGFUNCTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("rgFunctionRecordGroup", e);
			return Collections.emptyList();
		}
	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public String getTeamIdDescription(final String teamId) {
		final String sql = getQuery("OCMDEFTM_GET_TEAM_DESCRIPTION");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("teamId",Long.parseLong(teamId)), String.class);
		} catch (final Exception e) {
			returnValue = null;
			logger.error("getTeamIdDescription", e);
		}
		return returnValue;
	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public String checkAgyLOcTeamExist(final String agencyLocationID, final String functionType) {
		String returnValue = null;
		final String sql = getQuery("OCMDEFTM_CHECK_TEAM_EXIST");
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("agyLocId", agencyLocationID, "functionType", functionType), String.class);
		} catch (final EmptyResultDataAccessException e) {
			returnValue = null;
		} catch (final Exception e) {
			logger.error("Exeception in checkAgyLOcTeamExist", e);

		}
		return returnValue;
	}

	@Override
	public long getAgencyTeamID() {
		long returnNumber = 0;
		final String sql = getQuery("OCMDEFTM_GET_LOC_ID");
		try {
			returnNumber = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("Exeception in getAgencyTeamID", e);

		}

		return returnNumber;
	}
}
