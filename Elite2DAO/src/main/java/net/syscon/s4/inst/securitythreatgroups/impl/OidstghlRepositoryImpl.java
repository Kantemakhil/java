package net.syscon.s4.inst.securitythreatgroups.impl;

import java.util.ArrayList;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.StgLocations;
import net.syscon.s4.inst.securitythreatgroups.OidstghlRepository;

/**
 * Class OidstghlRepositoryImpl
 */
@Repository
public class OidstghlRepositoryImpl extends RepositoryBase implements OidstghlRepository {

	private static Logger logger = LogManager.getLogger(OidstghlRepositoryImpl.class.getName());
 
	private final Map<String, FieldMapper> stgLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LOCATION_SEQ", new FieldMapper("locationSeq"))
			.put("MAX(NVL(LOCATION_SEQ", new FieldMapper(" max(nvl(locationSeq"))
			.put("STG_ID", new FieldMapper("stgId")).build();
	private final Map<String, FieldMapper> secThrtGrMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("DESCRIPTION", new FieldMapper("description")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("STG_ID", new FieldMapper("stgId")).put("STG_CODE", new FieldMapper("stgCode"))
			.put("STG_LEVEL", new FieldMapper("stgLevel")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("HISTORY_TEXT", new FieldMapper("historyText"))

			.build();
	private final Map<String, FieldMapper> refCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CITY_CODE", new FieldMapper("cityCode")).put("COUNTRY", new FieldMapper("country"))
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> staffMemRMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROLE_ID", new FieldMapper("roleId")).build();

	/**
	 * Creates new OidstghlRepositoryImpl class Object
	 */
	public OidstghlRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SecurityThreatGroups
	 *
	 * @return List<SecurityThreatGroups>
	 */
	public List<SecurityThreatGroups> stgExecuteQuery(final SecurityThreatGroups objSearchDao) {
		final String sql = getQuery("OIDSTGHL_STG_FIND_SECURITY_THREAT_GROUPS");
		final RowMapper<SecurityThreatGroups> securityRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, secThrtGrMapping);
		List<SecurityThreatGroups> returnList = new ArrayList<>();
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");

			if (objSearchDao.getHistoryText() != null) {
				sqlQuery.append("HISTORY_TEXT = :HISTORY_TEXT" + " AND ");
				inParameterSource.addValue("HISTORY_TEXT", objSearchDao.getHistoryText());
			}
			if (objSearchDao.getStgId() != null) {
				sqlQuery.append("STG_ID = :STG_ID" + " AND ");
				inParameterSource.addValue("STG_ID", objSearchDao.getStgId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource, securityRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstSecurityThreatGroups List<SecurityThreatGroups>
	 *
	 * @return List<Integer>
	 */
	public Integer stgInsertSecurityThreatGroups(final List<SecurityThreatGroups> lstSecThrtGroups) {
		final String sql = getQuery("OIDSTGHL_STG_INSERT_SECURITY_THREAT_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SecurityThreatGroups secThreatGroups : lstSecThrtGroups) {
			parameters.add(new BeanPropertySqlParameterSource(secThreatGroups));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSecThrtGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSecurityThreatGroups List<SecurityThreatGroups>
	 */
	public Integer stgUpdateSecurityThreatGroups(final List<SecurityThreatGroups> lstSecThrtGroups) {
		final String sql = getQuery("OIDSTGHL_STG_UPDATE_SECURITY_THREAT_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SecurityThreatGroups secThreatGroups : lstSecThrtGroups) {
			parameters.add(new BeanPropertySqlParameterSource(secThreatGroups));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSecThrtGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSecurityThreatGroups List<SecurityThreatGroups>
	 */
	public Integer stgDeleteSecurityThreatGroups(final List<SecurityThreatGroups> lstSecThrtGroups) {
		final String sql = getQuery("OIDSTGHL_STG_DELETE_SECURITY_THREAT_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SecurityThreatGroups securityThreatGroups : lstSecThrtGroups) {
			parameters.add(new BeanPropertySqlParameterSource(securityThreatGroups));
		}
		try {
			String tableName = "SECURITY_THREAT_GROUPS";
			batchUpdatePreDeletedRows(tableName, null, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSecThrtGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao StgLocations
	 *
	 * @return List<StgLocations>
	 */
	public List<StgLocations> stgLocationsExecuteQuery(final StgLocations objSearchDao) {
		final String sql = getQuery("OIDSTGHL_STGLOCATIONS_FIND_STG_LOCATIONS");
		final RowMapper<StgLocations> stgLocRowMapper = Row2BeanRowMapper.makeMapping(sql, StgLocations.class,
				stgLocMapping);
		List<StgLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("STGID", objSearchDao.getStgId()),
				stgLocRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstStgLocations List<StgLocations>
	 *
	 * @return List<Integer>
	 */
	public Integer stgLocationsInsertStgLocations(final List<StgLocations> lstStgLocations) {
		final String sql = getQuery("OIDSTGHL_STGLOCATIONS_INSERT_STG_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgLocations stgLocations : lstStgLocations) {
			parameters.add(new BeanPropertySqlParameterSource(stgLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgLocations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStgLocations List<StgLocations>
	 */
	public Integer stgLocationsUpdateStgLocations(final List<StgLocations> lstStgLocations) {
		final String sql = getQuery("OIDSTGHL_STGLOCATIONS_UPDATE_STG_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgLocations stgLocations : lstStgLocations) {
			parameters.add(new BeanPropertySqlParameterSource(stgLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgLocations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstStgLocations List<StgLocations>
	 */
	public Integer stgLocationsDeleteStgLocations(final List<StgLocations> lstStgLocations) {
		final String sql = getQuery("OIDSTGHL_STGLOCATIONS_DELETE_STG_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgLocations stgLocations : lstStgLocations) {
			parameters.add(new BeanPropertySqlParameterSource(stgLocations));
		}
		try {
			String tableName = "STG_LOCATIONS";
			String whereCondition = "STG_ID=:stgId AND LOCATION_SEQ=:locationSeq";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgLocations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> recCityRecordGroup() {
		final String sql = getQuery("OIDSTGHL_FIND_RECCITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> recStateRecordGroup() {
		final String sql = getQuery("OIDSTGHL_FIND_RECSTATE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> recCountryRecordGroup() {
		final String sql = getQuery("OIDSTGHL_FIND_RECCOUNTRY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<StgLocations> stgOnCheckDeleteMaster(final StgLocations paramBean) {
		final String sql = getQuery("OIDSTGHL_STG_ONCHECKDELETEMASTER");
		final RowMapper<StgLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StgLocations.class,
				stgLocMapping);
		List<StgLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("STGID", paramBean.getStgId()),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgPreInsert
	 *
	 * @param params
	 *
	 */
	public List<SecurityThreatGroups> stgPreInsert(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OIDSTGHL_STG_PREINSERT");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, secThrtGrMapping);
		List<SecurityThreatGroups> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgLocationsPreInsert
	 *
	 * @param params
	 *
	 */
	public Long stgLocationsPreInsert(final Long stgId) {
		final String sql = getQuery("OIDSTGHL_STG_LOCATIONS_PREINSERT");
		Long returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), Long.class);
		if (returnList == null) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgLocationsPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> stgLocationsPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDSTGHL_STG_LOCATIONS_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<StaffMemberRoles> cgwhenNewFormInstance(String userName) {
		final String sql = getQuery("OIDSTGHL_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<StaffMemberRoles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMemberRoles.class,
				staffMemRMapping);
		List<StaffMemberRoles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("USER_NAME", userName), columnRowMapper);
		return returnList;
	}

	public String getProfileValue() {
		final String sql = getQuery("OIDSTGHL_GET_PROFILE_VALUE");
		String profileValue = null;
		try {
			profileValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (EmptyResultDataAccessException e) {
			profileValue = null;
		}
		return profileValue;

	}

}
