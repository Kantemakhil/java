package net.syscon.s4.inst.workflow.maintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.OcmteamMainRepository;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;

/**
 * Class OcmteamsRepositoryImpl
 */
@Repository
public class OcmteamMainRepositoryImpl extends RepositoryBase implements OcmteamMainRepository {
	private final Map<String, FieldMapper> staffLocMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FIRST_NAME", new FieldMapper("firstName")).put("SCHEDULE_TYPE", new FieldMapper("scheduleType"))
			.put("HOURS_PER_WEEK", new FieldMapper("hoursPerWeek")).put("LAST_NAME", new FieldMapper("lastName"))
			.build();
	private final Map<String, FieldMapper> teamsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASE_LOAD_ID", new FieldMapper("caseLoadId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TEAM_ID", new FieldMapper("teamId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("TEAM_EMAIL", new FieldMapper("teamEmail"))
			.build();
	private final Map<String, FieldMapper> teamMbrsMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("NO_OF_TASKS", new FieldMapper("noOfTasks"))
			.put("COUNT", new FieldMapper("count")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("TEAM_ID", new FieldMapper("teamId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag")).
			put("USER_ID", new FieldMapper("userId")).build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("TEAM_CODE", new FieldMapper("teamCode  "))
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();
	private final Map<String, FieldMapper> referenceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WORK_TYPE", new FieldMapper("workType")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).put("WORK_SUB_TYPE", new FieldMapper("workSubType"))
			.build();
	private final Map<String, FieldMapper> stafMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();
	/**
	 * Creates new OcmteamMainRepositoryImpl class Object
	 */
	public OcmteamMainRepositoryImpl() {
		// constructor
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmteamMainRepositoryImpl.class);

	@Override
	public List<ReferenceCodes> getCaseloads() {
		final String sql = getQuery("GET_CASE_LOAD_LIST");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceMapping);
		List<ReferenceCodes> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, columnRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	public List<Teams> teamsExecuteQuery() {
		final String sql = getQuery("GET_TEAMS_DATA");
		final RowMapper<Teams> TeamsRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, teamsMapping);
		return (ArrayList<Teams>) namedParameterJdbcTemplate.query(sql,
				createParams(), TeamsRowMapper);
	}

	@Override
	public Integer insertTeamsData(List<Teams> teamList) {
		final String sql = getQuery("INSERT_TEAMS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Teams teams : teamList) {
			parameters.add(new BeanPropertySqlParameterSource(teams));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (teamList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteTeamsData(List<Teams> teamList) {
		final String sql = getQuery("DELETE_TEAMS_DATA");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Teams teams : teamList) {
			parameters.add(new BeanPropertySqlParameterSource(teams));
		}
		try {
			String tableName = "automation_teams";
			String whereCondition = "TEAM_ID  = :teamId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (teamList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateTeamsData(List<Teams> teamList) {
		final String sql = getQuery("UPDATE_TEAMS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Teams teams : teamList) {
			parameters.add(new BeanPropertySqlParameterSource(teams));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (teamList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<AgencyLocations> getAgyLocRecords() {
		final String sql = getQuery("OCMTEAMMAIN_FIND_AGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAdmAgyLocRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer insertTeamMainData(List<Teams> teamList) {
		final String sql = getQuery("INSERT_TEAMS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Teams teams : teamList) {
			parameters.add(new BeanPropertySqlParameterSource(teams));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (teamList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer insertTeamFunctionData(List<Teams> teamList) {
		final String sql = getQuery("INSERT_TEAM_FUNCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Teams teams : teamList) {
			parameters.add(new BeanPropertySqlParameterSource(teams));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (teamList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer insertTeamAgyLocData(List<Teams> teamList) {
		final String sql = getQuery("INSERT_TEAM_AGENCY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Teams teams : teamList) {
			parameters.add(new BeanPropertySqlParameterSource(teams));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (teamList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer getTeamId() {
		final String sql = getQuery("GET_TEAM_ID");
		Integer returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {

		}
		return returnData;
	}

	@Override
	public Integer deleteTeamFunctionData(Integer teamId,String modifyUserId) {
		final String sql = getQuery("DELETE_TEAM_FUNCTIONS");
		Integer returnVal = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "TEAM_FUNCTIONS";
			String whereCondition = "TEAM_ID  = :teamId";
			inputMap.put("teamId", teamId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteTeamFunctionData" + e.getMessage());
		}
		try {
			namedParameterJdbcTemplate.update(sql, createParams("teamId", teamId));
			returnVal = 1;
		} catch (Exception e) {
			returnVal = 0;
		}
		return returnVal;

		
	}

	@Override
	public Integer deleteTeamAgyLocData(Integer teamId,String modifyUserId) {
		final String sql = getQuery("DELETE_TEAM_AGENCY");
		Integer returnVal = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "TEAM_AGENCY_LOC";
			String whereCondition = "TEAM_ID  = :teamId";
			inputMap.put("teamId", teamId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteTeamAgyLocData" + e.getMessage());
		}
		try {
			namedParameterJdbcTemplate.update(sql, createParams("teamId", teamId));
			returnVal = 1;
		} catch (Exception e) {
			returnVal = 0;
		}
		return returnVal;
	}

	@Override
	public List<Teams> teamAgencyExecuteQuery(Integer teamId) {
		final String sql = getQuery("GET_TEAM_AGENCY_DATA");
		final RowMapper<Teams> TeamsRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, teamsMapping);
		return (ArrayList<Teams>) namedParameterJdbcTemplate.query(sql,
				createParams("teamId", teamId), TeamsRowMapper);
	}

	@Override
	public List<Teams> teamFunctionExecuteQuery(Integer teamId) {
		final String sql = getQuery("GET_TEAM_FUNCTION_DATA");
		final RowMapper<Teams> TeamsRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, teamsMapping);
		return (ArrayList<Teams>) namedParameterJdbcTemplate.query(sql,
				createParams("teamId", teamId), TeamsRowMapper);
	}

	@Override
	public List<StaffMembers> getStaffDetails() {
		final String sql = getQuery("GET_STAFF_DETAILS");
		final RowMapper<StaffMembers> mMMTeamMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				stafMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMMTeamMembersRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<TeamMembers> teamMembersExecuteQuery(TeamMembers teamObj) {
		final String sql = getQuery("GET_TEAM_MEMBERS_DATA");
		final RowMapper<TeamMembers> teamMebrsRowMpr = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				teamMbrsMaping);
		return (ArrayList<TeamMembers>) namedParameterJdbcTemplate.query(sql,
				createParams("teamId", teamObj.getTeamId()), teamMebrsRowMpr);
	}

	@Override
	public List<StaffLocationRoles> getStaffMemberDetails(TeamMembers paramBean) {
		final String sql = getQuery("OCMTEAMS_OCMTEAMS_POSTQUERY");
		final RowMapper<StaffLocationRoles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffLocationRoles.class, staffLocMaping);
		return (ArrayList<StaffLocationRoles>) namedParameterJdbcTemplate.query(sql,
				createParams("staffId", paramBean.getStaffId(), "teamMemberId", paramBean.getTeamMemberId(), "role",
						paramBean.getRole(), "position", paramBean.getPosition()),
				columnRowMapper);

	}

	@Override
	public Integer teamMembersInsert(List<TeamMembers> teammemberList) {
		final String sql = getQuery("INSERT_TEAM_MEMBERS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TeamMembers teamMembers : teammemberList) {
			parameters.add(new BeanPropertySqlParameterSource(teamMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (teammemberList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer teamMembersUpdate(List<TeamMembers> teammemberList) {
		final String sql = getQuery("UPDATE_TEAM_MEMBERS_DATA");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TeamMembers teamMembers : teammemberList) {
			parameters.add(new BeanPropertySqlParameterSource(teamMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (teammemberList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Long getTeamMemberId() {
		final String sql = getQuery("GET_TEAM_MEMBER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public Integer teamMembersDelete(List<TeamMembers> teamList) {
		final String sql = getQuery("DELETE_TEAM_MEMBERS_DATA");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TeamMembers teamMembers : teamList) {
			parameters.add(new BeanPropertySqlParameterSource(teamMembers));
		}
		try {
			String tableName = "TEAM_STAFF_MEMBERS";
			String whereCondition = "TEAM_MEMBER_ID = :teamMemberId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (teamList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	

}
