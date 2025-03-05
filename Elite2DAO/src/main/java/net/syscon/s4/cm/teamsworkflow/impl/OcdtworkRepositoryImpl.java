package net.syscon.s4.cm.teamsworkflow.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.OcdtworkRepository;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdtworkRepositoryImpl
 * 
 */
@Repository
public class OcdtworkRepositoryImpl extends RepositoryBase implements OcdtworkRepository {

	/**
	 * Creates new OcdtworkRepositoryImpl class Object
	 */
	public OcdtworkRepositoryImpl() {
	}

	private final Map<String, FieldMapper> teamMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEAM_CODE", new FieldMapper("teamCode")).build();
	private final Map<String, FieldMapper> stafMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> tagWorkFlowMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("WORK_ID", new FieldMapper("workId"))
			.put("TEAM_ID", new FieldMapper("teamId")).put("STAFF_ID", new FieldMapper("staffId"))
			.put("ASSIGNMENT_DATE", new FieldMapper("assignmentDate")).put("DUE_DATE", new FieldMapper("dueDate"))
			.put("MSGID", new FieldMapper("msgId")).put("MESSAGE_TEXT", new FieldMapper("messageText"))
			.put("WORKFLOW_TYPE", new FieldMapper("workflowType"))
			.put("ORIGINAL_MSGID", new FieldMapper("originalMsgId")).put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("FUNCTION_TYPE", new FieldMapper("functionType"))
			.put("ACKNOWLEDGEMENT_REQUIRED", new FieldMapper("acknowledgementRequired"))
			.put("ACKNOWLEDGEMENT_SUBJECT", new FieldMapper("acknowledgementSubject"))
			.put("QUEUE_NAME", new FieldMapper("queueName")).put("SEVERITY_CODE", new FieldMapper("severityCode"))
			.put("SENDER_ID", new FieldMapper("senderId")).put("CLUSTER_ID", new FieldMapper("clusterId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))

			.build();

	private static Logger logger = LogManager.getLogger(OcdtworkRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TagWorkflowBrowseQueue
	 *
	 * @return List<TagWorkflowBrowseQueue>
	 *
	 */
	public List<TagWorkflowBrowseQueue> staffQueueExecuteQuery(final TagWorkflowBrowseQueue objSearchDao) {
		final String sql = getQuery("OCDTWORK_SELECT_TASKS");
		final RowMapper<TagWorkflowBrowseQueue> tagWorkflowRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TagWorkflowBrowseQueue.class, tagWorkFlowMapping);
		final ArrayList<TagWorkflowBrowseQueue> returnList = (ArrayList<TagWorkflowBrowseQueue>) namedParameterJdbcTemplate
				.query(sql,
						createParams("CLUSTER_ID", objSearchDao.getClusterId(), "staffId", objSearchDao.getStaffId()),
						tagWorkflowRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTagWorkflowBrowseQueue
	 *            List<TagWorkflowBrowseQueue>
	 *
	 */
	public Integer staffQueueUpdateTagWorkflowBrowseQueue(
			final List<TagWorkflowBrowseQueue> lstTagWorkflowBrowseQueue) {
		final String sql = getQuery("OCDTWORK_STAFFQUEUE_UPDATE_TAG_WORKFLOW.BROWSE_QUEUE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TagWorkflowBrowseQueue tagWorkflowBrowseQueue : lstTagWorkflowBrowseQueue) {
			parameters.add(new BeanPropertySqlParameterSource(tagWorkflowBrowseQueue));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTagWorkflowBrowseQueue.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SelectSmLastNameLastNameSmFirstNameFirstNameTmPositionPositionTmRoleRoleSmSexCodeSexCodeTmNoOfTasksNoOfTasksTmTeamMemberIdTeamMemberIdTmStaffIdStaffIdTDescriptionTeamNameTmAgyLocIdAgyLocIdFromStaffMembersSmStaffLocationRolesSrTeamMembersTmTeamsTWhereTmStaffIdSmStaffIdAndTmTeamIdTTeamIdAndTmStaffIdSrSacStaffIdAndTmAgyLocIdSrCalAgyLocIdAndTmLocRoleFromDateSrFromDateAndTmPositionSrPositionAndTmRoleSrRoleAndSrToDateIsNullAndTmActiveFlagY
	 *
	 * @return List<SelectSmLastNameLastNameSmFirstNameFirstNameTmPositionPositionTmRoleRoleSmSexCodeSexCodeTmNoOfTasksNoOfTasksTmTeamMemberIdTeamMemberIdTmStaffIdStaffIdTDescriptionTeamNameTmAgyLocIdAgyLocIdFromStaffMembersSmStaffLocationRolesSrTeamMembersTmTeamsTWhereTmStaffIdSmStaffIdAndTmTeamIdTTeamIdAndTmStaffIdSrSacStaffIdAndTmAgyLocIdSrCalAgyLocIdAndTmLocRoleFromDateSrFromDateAndTmPositionSrPositionAndTmRoleSrRoleAndSrToDateIsNullAndTmActiveFlagY>
	 *
	 */
	public List<TeamMembers> teamMembersExecuteQuery(final TeamMembers objSearchDao) {
		final String sql = getQuery("OCDTWORK_SELECT_TEAMMEMBERS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" and ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append("tm.agy_loc_id = :agyLocId " + " and ");
				valuesList.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getStaffId() != null) {
				sqlQuery.append("((:ocdaWorkAccess = 'SELF' AND tm.staff_id = :staffId ) OR (:ocdaWorkAccess = 'ALL'))"
						+ " and ");
				valuesList.addValue("staffId", objSearchDao.getStaffId());
				valuesList.addValue("ocdaWorkAccess", ocdaWorkAccess());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " order by  sm.last_name,sm.first_name ";
		final RowMapper<TeamMembers> staffMembersRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				TeamMembers.class, stafMembersMapping);
		List<TeamMembers> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, staffMembersRowMapper);
		} catch (Exception e) {
			logger.error("teamMembersExecuteQuery:", e);
		}
		return returnList;
	}

	public String ocdaWorkAccess() {
		String value = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withFunctionName("GET_ACCESS_LEVEL")
				.declareParameters(sqlParameters);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			value = simpleJDBCCall.executeFunction(String.class, inParameter);
		} catch (Exception e) {
			value = "";
		}
		return value;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSelectSmLastNameLastNameSmFirstNameFirstNameTmPositionPositionTmRoleRoleSmSexCodeSexCodeTmNoOfTasksNoOfTasksTmTeamMemberIdTeamMemberIdTmStaffIdStaffIdTDescriptionTeamNameTmAgyLocIdAgyLocIdFromStaffMembersSmStaffLocationRolesSrTeamMembersTmTeamsTWhereTmStaffIdSmStaffIdAndTmTeamIdTTeamIdAndTmStaffIdSrSacStaffIdAndTmAgyLocIdSrCalAgyLocIdAndTmLocRoleFromDateSrFromDateAndTmPositionSrPositionAndTmRoleSrRoleAndSrToDateIsNullAndTmActiveFlagY
	 *            List<SelectSmLastNameLastNameSmFirstNameFirstNameTmPositionPositionTmRoleRoleSmSexCodeSexCodeTmNoOfTasksNoOfTasksTmTeamMemberIdTeamMemberIdTmStaffIdStaffIdTDescriptionTeamNameTmAgyLocIdAgyLocIdFromStaffMembersSmStaffLocationRolesSrTeamMembersTmTeamsTWhereTmStaffIdSmStaffIdAndTmTeamIdTTeamIdAndTmStaffIdSrSacStaffIdAndTmAgyLocIdSrCalAgyLocIdAndTmLocRoleFromDateSrFromDateAndTmPositionSrPositionAndTmRoleSrRoleAndSrToDateIsNullAndTmActiveFlagY>
	 *
	 * @throws SQLException
	 */
	public Integer teamMembersUpdate(final List<TeamMembers> objSearchDao) {
		final String sql = getQuery("OCDTWORK_TEAMMEMBERS_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TeamMembers obj : objSearchDao) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (objSearchDao.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgReasonRecordGroup() {
		final String sql = getQuery("OCDTWORK_FIND_RGREASON");
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
	public List<ReferenceCodes> rgCompletedRecordGroup() {
		final String sql = getQuery("OCDTWORK_FIND_RGCOMPLETED");
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
	public List<AgencyLocations> cgfkCrtMvTmpAgyLocIdRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OCDTWORK_FIND_CGFKCRTMVTMPAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSexRecordGroup() {
		final String sql = getQuery("OCDTWORK_FIND_RGSEX");
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
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		final String sql = getQuery("OCDTWORK_FIND_RGWORKTYPE");
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
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup() {
		final String sql = getQuery("OCDTWORK_FIND_RGWORKSUBTYPE");
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
	public List<ReferenceCodes> rgPositionRecordGroup() {
		final String sql = getQuery("OCDTWORK_FIND_RGPOSITION");
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
	public List<ReferenceCodes> rgRoleRecordGroup() {
		final String sql = getQuery("OCDTWORK_FIND_RGROLE");
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
	 * @return List<MM>
	 */
	public List<Teams> rgTeamStaffRecordGroup() {
		final String sql = getQuery("OCDTWORK_FIND_RGTEAMSTAFF");
		final RowMapper<Teams> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, teamMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMMTeamMembers>
	 */
	public List<StaffMembers> rgStaffSearchRecordGroup(final String agylocId) {
		final String sql = getQuery("OCDTWORK_FIND_RGSTAFFSEARCH");
		final RowMapper<StaffMembers> mMMTeamMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				stafMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agylocId", agylocId), mMMTeamMembersRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgyrsn
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> defaultAgyrsn(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDTWORK_DEFAULT_AGYRSN");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public List<TagWorkflowBrowseQueue> staffMemoQueueExecuteQuery(final TagWorkflowBrowseQueue objSearchDao) {
		final String sql = getQuery("OCDTWORK_SELECT_MEMO");
		final RowMapper<TagWorkflowBrowseQueue> tagWorkflowRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TagWorkflowBrowseQueue.class, tagWorkFlowMapping);
		final ArrayList<TagWorkflowBrowseQueue> returnList = (ArrayList<TagWorkflowBrowseQueue>) namedParameterJdbcTemplate
				.query(sql,
						createParams("CLUSTER_ID", objSearchDao.getClusterId(), "staffId", objSearchDao.getStaffId()),
						tagWorkflowRowMapper);
		return returnList;
	}

	@Override
	public TagWorkflowBrowseQueue getOffenderDetails(final Integer offenderBookId,String userName) {
		TagWorkflowBrowseQueue returnObj = new TagWorkflowBrowseQueue();
		final String sql = getQuery("GET_OFFENDER_DETAILS");
		final RowMapper<TagWorkflowBrowseQueue> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TagWorkflowBrowseQueue.class, tagWorkFlowMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId,"USERID",userName),
					columnRowMapper);
		} catch (Exception e) {
			returnObj = new TagWorkflowBrowseQueue();
		}
		return returnObj;
	}

	@Override
	public TagWorkflowBrowseQueue getWorkDetails(final Integer workId) {
		final TagWorkflowBrowseQueue bean = new TagWorkflowBrowseQueue();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_WORK_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_WORKFLOW_TYPE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_WORKFLOW_TYPE_DESC", OracleTypes.VARCHAR),
				new SqlOutParameter("P_WORK_TYPE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_WORK_TYPE_DESC", OracleTypes.VARCHAR),
				new SqlOutParameter("P_WORK_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_WORK_SUB_TYPE_DESC", OracleTypes.VARCHAR),
				new SqlOutParameter("P_MANUAL_CLOSE_FLAG", OracleTypes.VARCHAR),
				new SqlOutParameter("P_MODULE_NAME", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withProcedureName("GET_WORK_DETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_WORK_ID", workId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setWorkflowType(
					returnObject.get("P_WORKFLOW_TYPE") != null ? String.valueOf(returnObject.get("P_WORKFLOW_TYPE"))
							: null);
			bean.setWorkType(
					returnObject.get("P_WORK_TYPE") != null ? String.valueOf(returnObject.get("P_WORK_TYPE")) : null);
			bean.setWorkSubType(
					returnObject.get("P_WORK_SUB_TYPE") != null ? String.valueOf(returnObject.get("P_WORK_SUB_TYPE"))
							: null);
			bean.setModuleName(
					returnObject.get("P_MODULE_NAME") != null ? String.valueOf(returnObject.get("P_MODULE_NAME"))
							: null);
			bean.setManualCloseFlag(returnObject.get("P_MANUAL_CLOSE_FLAG") != null
					? String.valueOf(returnObject.get("P_MANUAL_CLOSE_FLAG"))
					: null);
		} catch (Exception e) {
			logger.error(" getWorkDetails: ", e);
		}
		return bean;
	}

	@Override
	public Integer reassigntoTeam(final TagWorkflowBrowseQueue object) {
		int result = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlParameter("p_queue_name", Types.VARCHAR),
				new SqlParameter("p_msgid", Types.VARCHAR), new SqlParameter("p_new_team_id", Types.INTEGER),
				new SqlParameter("p_remove_message", Types.INTEGER)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW").withProcedureName("REASSIGN_TO_TEAM").declareParameters(sqlParameters);
		inParamMap.put("p_queue_name", object.getQueueName());
		inParamMap.put("p_msgid", object.getMsgId());
		inParamMap.put("p_new_team_id", object.getAssignedTeamId());
		inParamMap.put("p_remove_message", object.getEnableOrDisable());
		
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer reAssigntoTeamMember(final TagWorkflowBrowseQueue object) {

		int result = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlParameter("p_queue_name", Types.VARCHAR),
				new SqlParameter("p_msgid", Types.VARCHAR), new SqlParameter("p_new_team_member_id", Types.INTEGER),
				new SqlParameter("p_remove_message", Types.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW").withProcedureName("REASSIGN_TO_TEAM_MEMBER").declareParameters(sqlParameters);
		inParamMap.put("p_queue_name", object.getQueueName());
		inParamMap.put("p_msgid", object.getMsgId());
		inParamMap.put("p_new_team_member_id", object.getTeamMemberId());
		inParamMap.put("p_remove_message", object.getEnableOrDisable());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer removeFromQueue(final TagWorkflowBrowseQueue object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_QUEUE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_MSGID", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW").withProcedureName("REMOVE_FROM_QUEUE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_QUEUE_NAME", object.getQueueName());
		inParamMap.put("P_MSGID", object.getMsgId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				returnValue = 1;
			}

		} catch (Exception e) {
			logger.error("In method getRemoveFromQueue", e);
		}
		return returnValue;
	}

	@Override
	public Integer completeTask(final TagWorkflowBrowseQueue object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_QUEUE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_MSGID", OracleTypes.VARCHAR),
				new SqlParameter("P_COMPLETE_REASON_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_COMPLETE_COMMENT_TEXT", OracleTypes.VARCHAR),
				new SqlParameter("P_COMPLETE_USER_ID", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW").withProcedureName("COMPLETE_TASK").declareParameters(sqlParameters);
		inParamMap.put("P_QUEUE_NAME", object.getQueueName());
		inParamMap.put("P_MSGID", object.getMsgId());
		inParamMap.put("P_COMPLETE_REASON_CODE", object.getCompleteReasonode());
		inParamMap.put("P_COMPLETE_COMMENT_TEXT", object.getCompleteCommentText());
		inParamMap.put("P_COMPLETE_USER_ID", object.getCompleteUserId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				returnValue = 1;
			}
		} catch (Exception e) {
			logger.error("In method getCompleteTask", e);
		}
		return returnValue;
	}

}
