package net.syscon.s4.inst.visitsmanagement.impl;

import java.text.SimpleDateFormat;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.visitsmanagement.OidvtourRepository;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingGroups;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingMembers;

/**
 * Class OidvtourRepositoryImpl
 */
@Repository
public class OidvtourRepositoryImpl extends RepositoryBase implements OidvtourRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidvtourRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> visitingMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MEMBER_SEQ", new FieldMapper("memberSeq")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime")).put("IDENTIFIER", new FieldMapper("identifier"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("FIRST_NAME", new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description"))
			.put("STATUS", new FieldMapper("status")).build();
	private final Map<String, FieldMapper> visitingGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("GROUP_ID", new FieldMapper("groupId")).put("APPROVED_BY_ID", new FieldMapper("approvedById"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("ESCORTED_BY_ID", new FieldMapper("escortedById")).put("VISIT_DATE", new FieldMapper("visitDate"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("GROUP_NAME", new FieldMapper("groupName"))
			.put("END_TIME", new FieldMapper("endTime")).put("GROUP_PURPOSE", new FieldMapper("groupPurpose"))
			.put("NO_VISITORS", new FieldMapper("noVisitors")).put("START_TIME", new FieldMapper("startTime")).build();
	private final Map<String, FieldMapper> caseloadAgencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_I", new FieldMapper("agyLocI")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).build();
	private final Map<String, FieldMapper> staffmembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId")).put("STAFF_NAME", new FieldMapper("staffName"))
			.put("USER_ID", new FieldMapper("userId")).build();

	/**
	 * Creates new OidvtourRepositoryImpl class Object
	 */
	public OidvtourRepositoryImpl() {
		// OidvtourRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VisitingGroups
	 *
	 * @return List<VisitingGroups>
	 *
	 * @
	 */
	public List<VisitingGroups> visitingGroupsExecuteQuery(final VisitingGroups objSearchDao) {
		final String sql = getQuery("OIDVTOUR_VISITINGGROUPS_FIND_VISITING_GROUPS");
		final RowMapper<VisitingGroups> VisitingGroupsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VisitingGroups.class, visitingGroupsMapping);
		String preparedSql = null;
		String preSql = null;
		List<VisitingGroups> returnList = new ArrayList<VisitingGroups>();
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append("AGY_LOC_ID = :AGY_LOC_ID" + " AND ");
				inParameterSource.addValue("AGY_LOC_ID", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getStartTime() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
				String stFormat = formatter.format(objSearchDao.getStartTime());
				sqlQuery.append("TO_CHAR(START_TIME,'HH24:MI') = '" + stFormat  + "' AND ");
			}
			if (objSearchDao.getVisitDate() != null) {
				sqlQuery.append("VISIT_DATE::date = :VISIT_DATE::date" + " AND ");
				inParameterSource.addValue("VISIT_DATE", objSearchDao.getVisitDate());
			}
			if (objSearchDao.getEndTime() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
				String edFormat = formatter.format(objSearchDao.getEndTime());
				sqlQuery.append("TO_CHAR(END_TIME,'HH24:MI') = '" + edFormat + "' AND ");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith(" WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSql = preparedSql.concat(" ORDER BY VISIT_DATE  DESC, START_TIME DESC");

		returnList = namedParameterJdbcTemplate.query(preSql, inParameterSource, VisitingGroupsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVisitingGroups List<VisitingGroups>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer visitinggroupsInsertVisitingGroups(final List<VisitingGroups> lstVisitingGroups) {
		int insertCount = 0;
		final String sql = getQuery("OIDVTOUR_VISITINGGROUPS_INSERT_VISITING_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount = insertCount++;
		}
		if (lstVisitingGroups.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVisitingGroups List<VisitingGroups>
	 *
	 * @
	 */
	public Integer visitingGroupsUpdateVisitingGroups(final List<VisitingGroups> lstVisitingGroups) {
		final String sql = getQuery("OIDVTOUR_VISITINGGROUPS_UPDATE_VISITING_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VisitingGroups visitingGroups : lstVisitingGroups) {
			parameters.add(new BeanPropertySqlParameterSource(visitingGroups));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitingGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVisitingGroups List<VisitingGroups>
	 *
	 * @
	 */
	public Integer visitingGroupsDeleteVisitingGroups(final List<VisitingGroups> lstVisitingGroups) {
		final String sql = getQuery("OIDVTOUR_VISITINGGROUPS_DELETE_VISITING_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VisitingGroups visitingGroups : lstVisitingGroups) {
			parameters.add(new BeanPropertySqlParameterSource(visitingGroups));
		}
		try {
			String tableName = "VISITING_GROUPS";
			String whereCondition = "GROUP_ID  = :groupId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitingGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VisitingMembers
	 *
	 * @return List<VisitingMembers>
	 *
	 * @
	 */
	public List<VisitingMembers> visitingMembersExecuteQuery(final VisitingMembers objSearchDao) {
		final String sql = getQuery("OIDVTOUR_VISITINGMEMBERS_FIND_VISITING_MEMBERS");
		final RowMapper<VisitingMembers> VisitingMembersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VisitingMembers.class, visitingMembersMapping);
		String preparedSql = null;
		String preSql = null;
		List<VisitingMembers> returnList = new ArrayList<VisitingMembers>();
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getGroupId() != null) {
				sqlQuery.append("GROUP_ID = :GROUP_ID" + " AND ");
				inParameterSource.addValue("GROUP_ID", objSearchDao.getGroupId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith(" WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSql = preparedSql.concat(" ORDER BY LAST_NAME");
		try {
			returnList = namedParameterJdbcTemplate.query(preSql, inParameterSource, VisitingMembersRowMapper);
		} catch (Exception e) {
			logger.error("visitingMembersExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVisitingMembers List<VisitingMembers>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer visitingMembersInsertVisitingMembers(final List<VisitingMembers> lstVisitingMembers) {
		final String sql = getQuery("OIDVTOUR_VISITINGMEMBERS_INSERT_VISITING_MEMBERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VisitingMembers visitingMembers : lstVisitingMembers) {
			parameters.add(new BeanPropertySqlParameterSource(visitingMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitingMembers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVisitingMembers List<VisitingMembers>
	 *
	 * @
	 */
	public Integer visitingMembersUpdateVisitingMembers(final List<VisitingMembers> lstVisitingMembers) {
		final String sql = getQuery("OIDVTOUR_VISITINGMEMBERS_UPDATE_VISITING_MEMBERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VisitingMembers visitingMembers : lstVisitingMembers) {
			parameters.add(new BeanPropertySqlParameterSource(visitingMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitingMembers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVisitingMembers List<VisitingMembers>
	 *
	 * @
	 */
	public Integer visitingMembersDeleteVisitingMembers(final List<VisitingMembers> lstVisitingMembers) {
		final String sql = getQuery("OIDVTOUR_VISITINGMEMBERS_DELETE_VISITING_MEMBERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VisitingMembers visitingMembers : lstVisitingMembers) {
			parameters.add(new BeanPropertySqlParameterSource(visitingMembers));
		}
		try {
			String tableName = "VISITING_MEMBERS";
			String whereCondition = "GROUP_ID  = :groupId AND MEMBER_SEQ  = :memberSeq";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitingMembers.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgGroupPurposRecordGroup() {
		final String sql = getQuery("OIDVTOUR_FIND_RGGROUPPURPOS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgGroupPurposRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgIdTypeRecordGroup() {
		final String sql = getQuery("OIDVTOUR_FIND_RGIDTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgIdTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgStaffMembersRecordGroup() {
		final String sql = getQuery("OIDVTOUR_FIND_RGSTAFFMEMBERS");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgStaffMembersRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<AgencyLocations> rgAgencyLocationsRecordGroup(final String caseloadId, final String caseloadType) {
		final String sql = getQuery("OIDVTOUR_FIND_RGAGENCYLOCATIONS");
		final RowMapper<AgencyLocations> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mmMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "CASELOADTYPE", caseloadType), mMRowMapper);
		} catch (Exception e) {
			logger.error("rgAgencyLocationsRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * visitingGroupsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public VisitingMembers visitingGroupsOnCheckDeleteMaster(final VisitingMembers paramBean) {
		final String sql = getQuery("OIDVTOUR_VISITING_GROUPS_ONCHECKDELETEMASTER");
		final RowMapper<VisitingMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VisitingMembers.class,
				visitingMembersMapping);
		final VisitingMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * visitingGroupsPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer visitingGroupsPreInsert() {
		final String sql = getQuery("OIDVTOUR_VISITING_GROUPS_PREINSERT");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("visitingGroupsPreInsert", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * visitingMembersPreInsert
	 *
	 * @param params
	 *
	 */
	public VisitingMembers visitingMembersPreInsert(final VisitingMembers paramBean) {
		final String sql = getQuery("OIDVTOUR_VISITING_MEMBERS_PREINSERT");
		final RowMapper<VisitingMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VisitingMembers.class,
				visitingMembersMapping);
		final VisitingMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	@Override
	public Integer visitingGroupsInsertVisitingGroups(final List<VisitingGroups> lstVisitingGroups) {
		final String sql = getQuery("OIDVTOUR_VISITINGGROUPS_INSERT_VISITING_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VisitingGroups visitingGroups : lstVisitingGroups) {
			parameters.add(new BeanPropertySqlParameterSource(visitingGroups));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitingGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public StaffMembers getStaffnameQuery(final Integer staffId) {
		final String sql = getQuery("OIDVTOUR_FIND_STAFFNAME");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffmembersMapping);
		StaffMembers returnList = new StaffMembers();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFF_ID", staffId), mRowMapper);
		} catch (Exception e) {
			logger.error("rgStaffnameRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public Integer getMemberSeq(final Integer groupId) {
		final String sql = getQuery("OIDVTOUR_VISITING_MEMBERS_PREINSERT");
		Integer seq = 0;
		try {
			seq = namedParameterJdbcTemplate.queryForObject(sql, createParams("GROUPID", groupId), Integer.class);
		} catch (Exception e) {
			logger.error("getMemberSeq", e);
		}
		return seq;
	}

}
