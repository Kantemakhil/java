package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globaloffenderrecords.impl.OsiosearRepositoryImpl;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.casemanagement.OcutasatRepository;

/**
 * Class OcutasatRepositoryImpl
 */
@Repository
public class OcutasatRepositoryImpl extends RepositoryBase implements OcutasatRepository {

	/**
	 * 
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OsiosearRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> teamsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("TEAM_ID", new FieldMapper("teamId"))
			.put("AREA_CODE", new FieldMapper("areaCode"))
			.put("CATEGORY", new FieldMapper("category"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("QUEUE_CLUSTER_ID", new FieldMapper("queueClusterId")).build();
	private final Map<String, FieldMapper> referencecodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Creates new OcutasatRepositoryImpl class Object
	 */
	public OcutasatRepositoryImpl() {
		// OcutasatRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Teams
	 *
	 * @return List<Teams>
	 *
	 * @
	 */
	public List<Teams> teamsExecuteQuery(final Teams objSearchDao) {
		final String sql = getQuery("OCUTASAT_TEAMS_FIND_TEAMS");
		List<Teams> returnList = new ArrayList<Teams>();
		String preSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append("Where ");
			if (objSearchDao.getTeamId() != null) {
				sqlQuery.append("TEAM_ID = :TEAM_ID" + " AND ");
				inParameterSource.addValue("TEAM_ID", objSearchDao.getTeamId());
			}
			if (objSearchDao.getAreaCode() != null) {
				sqlQuery.append("AREA_CODE = :AREA_CODE" + " AND ");
				inParameterSource.addValue("AREA_CODE", objSearchDao.getAreaCode());
			}
			if (objSearchDao.getAgyLocId()!= null) {
				sqlQuery.append("TEAM_ID IN (SELECT TEAM_ID FROM team_agency_loc WHERE AGY_LOC_ID =:AGY_LOC_ID )"
						+ " AND ");
				inParameterSource.addValue("AGY_LOC_ID", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getActiveFlag() != null) {
				sqlQuery.append("ACTIVE_FLAG = :ACTIVE_FLAG" + " AND ");
				inParameterSource.addValue("ACTIVE_FLAG", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getFunctionType() != null) {
				sqlQuery.append("TEAM_ID IN (SELECT TEAM_ID FROM TEAM_FUNCTIONS WHERE FUNCTION_TYPE =:FUNCTION_TYPE )"
						+ " AND ");
				inParameterSource.addValue("FUNCTION_TYPE", objSearchDao.getFunctionType());
			}
		}
		preSql = sqlQuery.toString().trim();
		if (preSql.endsWith("WHERE")) {
			preSql = preSql.substring(0, preSql.length() - 5);
		}
		if (preSql.endsWith("AND")) {
			preSql = preSql.substring(0, preSql.length() - 3);
		}
		preSql = preSql.concat(" ORDER BY DESCRIPTION");
		try {
			final RowMapper<Teams> TeamsRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, teamsMapping);
			returnList = namedParameterJdbcTemplate.query(preSql, inParameterSource, TeamsRowMapper);
		} catch (final Exception e) {
			log.error("teamsExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAreaTypeRecordGroup() {
		final String sql = getQuery("OCUTASAT_FIND_RGAREATYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referencecodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Areas> rgAreaRecordGroup(final String areaType) {
		final String sql = getQuery("OCUTASAT_FIND_RGAREA");
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, referencecodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("NBTAREATYPE", areaType), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

}
