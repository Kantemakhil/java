package net.syscon.s4.inst.workflow.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.workflow.maintenance.OcmteamsRepository;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.inst.workflow.maintenance.beans.TeamFunctions;
import net.syscon.s4.triggers.TeamsT1Repository;
import net.syscon.s4.triggers.TeamsT2Repository;

/**
 * Class OcmteamsRepositoryImpl
 */
@Repository
public class OcmteamsRepositoryImpl extends RepositoryBase implements OcmteamsRepository {
	@Autowired
	private TeamsT2Repository teamsRepository;

	@Autowired
	private TeamsT1Repository teamsT1Repository;

	private final Map<String, FieldMapper> staffLocMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FIRST_NAME", new FieldMapper("firstName")).put("SCHEDULE_TYPE", new FieldMapper("scheduleType"))
			.put("HOURS_PER_WEEK", new FieldMapper("hoursPerWeek")).put("LAST_NAME", new FieldMapper("lastName"))
			.build();
	private final Map<String, FieldMapper> teamsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("TEAM_ID", new FieldMapper("teamId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("FUNCTION_TYPE", new FieldMapper("functionType")).put("CHECKFLAG", new FieldMapper("checkFlag"))
			.put("COUNT", new FieldMapper("count")).build();
	private final Map<String, FieldMapper> teamMbrsMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("NO_OF_TASKS", new FieldMapper("noOfTasks"))
			.put("COUNT", new FieldMapper("count")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("TEAM_ID", new FieldMapper("teamId")).put("CHECKFLAG", new FieldMapper("checkFlag")).build();
	private final Map<String, FieldMapper> teamFnsMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("TEAM_ID", new FieldMapper("teamId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("FUNCTION_TYPE", new FieldMapper("functionType")).put("COUNT", new FieldMapper("count")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("TEAM_CODE", new FieldMapper("teamCode  "))
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();

	/**
	 * Creates new OcmteamsRepositoryImpl class Object
	 */
	public OcmteamsRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmteamsRepositoryImpl.class);

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Teams
	 *
	 * @return List<Teams>
	 *
	 * @throws SQLException
	 */
	public List<Teams> teamsExecuteQuery(final Teams objSearchDao) {
		final String sql = getQuery("OCMTEAMS_TEAMS_FIND_TEAMS");
		final RowMapper<Teams> TeamsRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, teamsMapping);
		return (ArrayList<Teams>) namedParameterJdbcTemplate.query(sql,
				createParams("areaCode", objSearchDao.getAreaCode()), TeamsRowMapper);
	}

	@Override
	public List<Teams> availTeamExecuteQuery(final Teams searchRecord) {
		final String sql = getQuery("OCMTEAMS_AVAIL_TEAMS_FIND_TEAMS");
		final RowMapper<Teams> TeamsRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, teamsMapping);
		return (ArrayList<Teams>) namedParameterJdbcTemplate.query(sql,
				createParams("teamId", searchRecord.getTeamId()), TeamsRowMapper);
	}

	@Override
	public List<Teams> availTeamActiveExecuteQuery(final Teams searchRecord) {
		final String sql = getQuery("OCMTEAMS_AVAIL_TEAMS_ACTIVE_FIND_TEAMS");
		final RowMapper<Teams> TeamsRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, teamsMapping);
		return (ArrayList<Teams>) namedParameterJdbcTemplate.query(sql,
				createParams("areaCode", searchRecord.getAreaCode()), TeamsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstTeams
	 *            List<Teams>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer teamsInsertTeams(final List<Teams> lstTeams) {
		final String sql = getQuery("OCMTEAMS_TEAMS_INSERT_TEAMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Teams teams : lstTeams) {
			if (teams.getTeamId() == null) {
				teams.setTeamId(teamsT1Repository.teamsT1());
			}
			Integer lvProfilevlaue = teamsRepository.teamsT2();
			Integer modCount = teamsRepository.teamsMod(teams.getTeamId().longValue(), lvProfilevlaue);
			teams.setQueueClusterId(modCount + 1);
			parameters.add(new BeanPropertySqlParameterSource(teams));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTeams.size() == returnArray.length) { 
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTeams
	 *            List<Teams>
	 *
	 * @throws SQLException
	 */
	public Integer teamsUpdateTeams(final List<Teams> lstTeams) {
		final String sql = getQuery("OCMTEAMS_TEAMS_UPDATE_TEAMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Teams teams : lstTeams) {
			parameters.add(new BeanPropertySqlParameterSource(teams));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTeams.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstTeams
	 *            List<Teams>
	 *
	 * @throws SQLException
	 */
	public Integer teamsDeleteTeams(final List<Teams> lstTeams) {

		final String sql = getQuery("OCMTEAMS_TEAMS_DELETE_TEAMS");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Teams teams : lstTeams) {
			parameters.add(new BeanPropertySqlParameterSource(teams));
		}
		try {
			String tableName = "TEAMS";
			String whereCondition = "TEAM_ID  = :teamId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstTeams.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TeamFunctions
	 *
	 * @return List<TeamFunctions>
	 *
	 * @throws SQLException
	 */
	public List<TeamFunctions> teamFunctionsExecuteQuery(final TeamFunctions objSearchDao) {
		final String sql = getQuery("OCMTEAMS_TEAMFUNCTIONS_FIND_TEAM_FUNCTIONS");
		final RowMapper<TeamFunctions> temFunRowMpr = Row2BeanRowMapper.makeMapping(sql, TeamFunctions.class,
				teamFnsMaping);
		return (ArrayList<TeamFunctions>) namedParameterJdbcTemplate.query(sql,
				createParams("teamId", objSearchDao.getTeamId()), temFunRowMpr);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstTeamFunctions
	 *            List<TeamFunctions>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer teamFunctionsInsertTeamFunctions(final List<TeamFunctions> lstTeamFunctions) {

		final String sql = getQuery("OCMTEAMS_TEAMFUNCTIONS_INSERT_TEAM_FUNCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TeamFunctions teamFunctions : lstTeamFunctions) {
			parameters.add(new BeanPropertySqlParameterSource(teamFunctions));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}
        catch(Exception e) {
        	logger.error("teamFunctionsInsertTeamFunctions", e);
        }

		if (lstTeamFunctions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public TeamFunctions teamFunctionsUpdateTeamFunctions(final List<TeamFunctions> lstTeamFunctions) {
		final TeamFunctions returnData = new TeamFunctions();
		final String sql = getQuery("OCMTEAMS_TEAMFUNCTIONS_UPDATE_TEAM_FUNCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TeamFunctions teamFunctions : lstTeamFunctions) {
			parameters.add(new BeanPropertySqlParameterSource(teamFunctions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstTeamFunctions.size() == returnArray.length) {
			returnData.setReturnValue(1);
			return returnData;
		} else {
			returnData.setReturnValue(0);
			return returnData;
		}

	}
	
	@Override
	public String errorNameValidationTimes( String errorName) {
		final String sql = getQuery("OCMTEAMS_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		errorName=errorName.toUpperCase();
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME",errorName),
					String.class);
		} catch (Exception e) {
			logger.error("errorNameValidationTimes", e);
			return returnData;
		}
		return returnData;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstTeamFunctions
	 *            List<TeamFunctions>
	 *
	 * @throws SQLException
	 */
	public TeamFunctions teamFunctionsDeleteTeamFunctions(final List<TeamFunctions> lstTeamFunctions) {
		final TeamFunctions returnData = new TeamFunctions();
		final String sql = getQuery("OCMTEAMS_TEAMFUNCTIONS_DELETE_TEAM_FUNCTIONS");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TeamFunctions teamFunctions : lstTeamFunctions) {
			parameters.add(new BeanPropertySqlParameterSource(teamFunctions));
		}
		try {
			String tableName = "TEAM_FUNCTIONS";
			String whereCondition = "ROW_ID = :serverCode";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		
		if (lstTeamFunctions.size() == returnArray.length) {
			returnData.setReturnValue(1);
			return returnData;
		} else {
			returnData.setReturnValue(0);
			return returnData;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TeamMembers
	 *
	 * @return List<TeamMembers>
	 *
	 * @throws SQLException
	 */
	public List<TeamMembers> teamMembersExecuteQuery(final TeamMembers objSearchDao) {
		final String sql = getQuery("OCMTEAMS_TEAMMEMBERS_FIND_TEAM_MEMBERS");
		final RowMapper<TeamMembers> teamMebrsRowMpr = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				teamMbrsMaping);
		return (ArrayList<TeamMembers>) namedParameterJdbcTemplate.query(sql,
				createParams("teamId", objSearchDao.getTeamId()), teamMebrsRowMpr);

	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstTeamMembers
	 *            List<TeamMembers>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer teamMembersInsertTeamMembers(final List<TeamMembers> lstTeamMembers) {

		final String sql = getQuery("OCMTEAMS_TEAMMEMBERS_INSERT_TEAM_MEMBERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TeamMembers teamMembers : lstTeamMembers) {
			parameters.add(new BeanPropertySqlParameterSource(teamMembers));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (lstTeamMembers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTeamMembers
	 *            List<TeamMembers>
	 *
	 * @throws SQLException
	 */
	public Integer teamMembersUpdateTeamMembers(final List<TeamMembers> lstTeamMembers) {

		final String sql = getQuery("OCMTEAMS_TEAMMEMBERS_UPDATE_TEAM_MEMBERS");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TeamMembers teamMembers : lstTeamMembers) {
			parameters.add(new BeanPropertySqlParameterSource(teamMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTeamMembers.size() == returnArray.length) {
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
	public List<Areas> rgTeamAreaRecordGroup(final String areaType) {
		final String sql = getQuery("OCMTEAMS_FIND_RGTEAMAREA");
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("areaType", areaType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgTeamAreaRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Teams> rgTeamsTeamCodeRecordGroup() {
		final String sql = getQuery("OCMTEAMS_FIND_RGTEAMSTEAMCODE");
		final RowMapper<Teams> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgTeamsTeamCodeRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgTeamsTeamCategoryRecordGroup() {
		final String sql = getQuery("OCMTEAMS_FIND_RGTEAMSTEAMCATEGORY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgTeamsTeamCategoryRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAreaTypeRecordGroup() {
		final String sql = getQuery("OCMTEAMS_FIND_RGAREATYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAreaTypeRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgFuctionTypeRecordGroup() {
		final String sql = getQuery("OCMTEAMS_FIND_RGFUCTIONTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgFuctionTypeRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Teams> rgAvailTeamTeamCodeRecordGroup() {
		final String sql = getQuery("OCMTEAMS_FIND_RGAVAILTEAMTEAMCODE");
		final RowMapper<Teams> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAvailTeamTeamCodeRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		final String sql = getQuery("OCMTEAMS_FIND_RGPOSITION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgPositionRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgRoleRecordGroup() {
		final String sql = getQuery("OCMTEAMS_FIND_RGROLE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgRoleRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup() {
		final String sql = getQuery("OCMTEAMS_FIND_RGAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAgyLocIdRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAdmAgyLocRecordGroup(final String areaCode) {
		final String sql = getQuery("OCMTEAMS_FIND_RGADMAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("areaCode", areaCode), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAdmAgyLocRecordGroup : ocmteams" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * teamsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public TeamFunctions teamsOnCheckDeleteMaster(final TeamFunctions paramBean) {
		final String sql = getQuery("OCMTEAMS_TEAMS_ONCHECKDELETEMASTER");
		final RowMapper<TeamFunctions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamFunctions.class,
				teamFnsMaping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * availTeamOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public Integer availTeamOnCheckDeleteMaster(final TeamMembers paramBean) {
		final String sql = getQuery("OCMTEAMS_AVAIL_TEAM_ONCHECKDELETEMASTER");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("teamId", paramBean.getTeamId()),
				Integer.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocmteamsPostQuery
	 *
	 * @param params
	 *
	 */
	public List<StaffLocationRoles> ocmteamsPostQuery(TeamMembers paramBean) {
		final String sql = getQuery("OCMTEAMS_OCMTEAMS_POSTQUERY");
		final RowMapper<StaffLocationRoles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffLocationRoles.class, staffLocMaping);
		return (ArrayList<StaffLocationRoles>) namedParameterJdbcTemplate.query(sql,
				createParams("staffId", paramBean.getStaffId(), "teamMemberId", paramBean.getTeamMemberId(), "role",
						paramBean.getRole(), "position", paramBean.getPosition()),
				columnRowMapper);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocmteamsKeyDelrec
	 *
	 * @param params
	 *
	 */
	public Integer ocmteamsKeyDelrec(final TeamMembers paramBean) {
		final String sql = getQuery("OCMTEAMS_OCMTEAMS_KEYDELREC");

		return namedParameterJdbcTemplate.queryForObject(sql, createParams("teamId", paramBean.getTeamId()),
				Integer.class);

	}

	public Integer getTeamId() {
		final String sql = getQuery("OCMTEAMS_OCMTEAMS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);

	}

	public Long getTeamMemberId() {
		final String sql = getQuery("OCMTEAMS_OCMTEAMMEMBERS_PREINSERT");

		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);

	}

	public Integer validateTeamCode(final Teams teams) {
		final String sql = getQuery("OCMTEAMS_TEAMCODE_KEYDELREC_EXIST");

		return namedParameterJdbcTemplate.queryForObject(sql, createParams("teamCode", teams.getTeamCode()),
				Integer.class);

	}

	public Integer getUpdateAllowedCheck(final String caseloadId, final String agyLocId) {
		final String sql = getQuery("OCMTEAMS_UPDATE_ALLOWED_CHECK");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "agyLocId", agyLocId), Integer.class);

	}

	@Override
	public Integer validateFunctionCode(final TeamFunctions searchBean) {
		final String sql = getQuery("OCMTEAMS_FUNCTIONTYPE_KEYDELREC_EXIST");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("teamId", searchBean.getTeamId(), "functionType", searchBean.getFunctionType()),
				Integer.class);

	}
	@Override
	public List<Teams> getTeamsBasedOnUser(String userId) {
		final String sql = getQuery("OCMTEAMS_TEAMS_GET_TEAMS");
		final RowMapper<Teams> TeamsRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, teamsMapping);
		return (ArrayList<Teams>) namedParameterJdbcTemplate.query(sql,
				createParams("userId", userId), TeamsRowMapper);
	}

	@Override
	public List<Teams> getAllTeams() {
		final String sql = getQuery("GET_TEAMS_DETAIL");
		final RowMapper<Teams> mMMTeamMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMMTeamMembersRowMapper);
		} catch (final Exception e) {
			logger.error("rgTeamRecordGroup", e);
			return Collections.emptyList();
		}
	}

}
