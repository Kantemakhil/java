package net.syscon.s4.cm.teamsworkflow.impl;
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
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.OcdaworkRepository;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Teams;
import oracle.jdbc.OracleTypes;
/**
 * Class OcdaworkRepositoryImpl
 */
@Repository
public class OcdaworkRepositoryImpl extends RepositoryBase implements OcdaworkRepository{
	
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcdaworkRepositoryImpl.class.getName());
	
private final Map<String, FieldMapper> teamMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 				new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> mmmMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("LAST_NAME", 					new FieldMapper("lastName"))
.build();
private final Map<String, FieldMapper> referencecodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 				new FieldMapper("description"))
.put("CODE", 						new FieldMapper("code"))
.build();
private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("STAFF_ID", 							new FieldMapper("staffId"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> teamMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TEAM_MEMBER_ID", 					new FieldMapper("teamMemberId"))
.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("CREATE_DATETIME", 				new FieldMapper("createDateTime"))
.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
.put("EXPIRY_DATE", 					new FieldMapper("expiryDate"))
.put("LOC_ROLE_FROM_DATE", 				new FieldMapper("locRoleFromDate"))
.put("MODIFY_DATETIME", 				new FieldMapper("modifyDateTime"))
.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
.put("NO_OF_TASKS", 					new FieldMapper("noOfTasks"))
.put("POSITION", 						new FieldMapper("position"))
.put("ROLE", 						    new FieldMapper("role"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("STAFF_ID", 						new FieldMapper("staffId"))
.put("TEAM_ID", 					    new FieldMapper("teamId"))
.put("TEAM_CODE", 					    new FieldMapper("teamCode"))
.build();
private final Map<String, FieldMapper> tagWorkFlowMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
.put("TASK_ID", 					new FieldMapper("taskId"))
.put("TASK_ASSIGNMENT_HTY_ID", 				new FieldMapper("taskAssignmentHtyId"))
.put("WORK_ID", 							new FieldMapper("workId"))
.put("TEAM_ID", 							new FieldMapper("teamId"))
.put("STAFF_ID", 							new FieldMapper("staffId"))
.put("ASSIGNMENT_DATE", 					new FieldMapper("assignmentDate"))
.put("DUE_DATE", 							new FieldMapper("dueDate"))
.put("MSGID", 								new FieldMapper("msgId"))
.put("MESSAGE_TEXT", 						new FieldMapper("messageText"))
.put("WORKFLOW_TYPE", 						new FieldMapper("workflowType"))
.put("ORIGINAL_MSGID", 						new FieldMapper("originalMsgId"))
.put("EVENT_DATE", 							new FieldMapper("eventDate"))
.put("FUNCTION_TYPE", 						new FieldMapper("functionType"))
.put("ACKNOWLEDGEMENT_REQUIRED", 			new FieldMapper("acknowledgementRequired"))
.put("ACKNOWLEDGEMENT_SUBJECT", 			new FieldMapper("acknowledgementSubject"))
.put("QUEUE_NAME", 							new FieldMapper("queueName"))
.put("SEVERITY_CODE", 						new FieldMapper("severityCode"))
.put("SENDER_ID", 							new FieldMapper("senderId"))
.put("CLUSTER_ID", 							new FieldMapper("clusterId"))
.put("OFFENDER_ID", 							new FieldMapper("offenderId"))
.put("SOURCE_NAME", 							new FieldMapper("sourceName"))

.build();
private final Map<String, FieldMapper> stafMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("LAST_NAME", new FieldMapper("lastName"))
.put("STAFF_NAME", new FieldMapper("staffName"))
.put("STAFF_ID", new FieldMapper("staffId"))
.put("FIRST_NAME", new FieldMapper("firstName")).build();

	/**
	 * Creates new OcdaworkRepositoryImpl class Object
	 */
	public OcdaworkRepositoryImpl() {
		// OcdaworkRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TagWorkflowBrowseQueue
	 *
	 * @return List<TagWorkflowBrowseQueue>
	 *
	 * @
	 */
	public List<TagWorkflowBrowseQueue> teamQueueExecuteQuery(final TagWorkflowBrowseQueue objSearchDao) {
		final String sql = getQuery("OCDAWORK_TEAMQUEUE_EXECUTEQUERY");
		final RowMapper<TagWorkflowBrowseQueue> tagWorkflowRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TagWorkflowBrowseQueue.class, tagWorkFlowMapping);
		final ArrayList<TagWorkflowBrowseQueue> returnList = (ArrayList<TagWorkflowBrowseQueue>) namedParameterJdbcTemplate
				.query(sql,
						createParams("CLUSTER_ID", objSearchDao.getClusterId(), "TEAM_ID", objSearchDao.getTeamId()),
						tagWorkflowRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTagWorkflowBrowseQueue
	 *            List<TagWorkflowBrowseQueue>
	 *
	 * @
	 */
	public Integer teamQueueUpdateTagWorkflowBrowseQueue(final List<TagWorkflowBrowseQueue> lstTagWorkflowBrowseQueue) {
		final String sql = getQuery("OCDAWORK_TEAMQUEUE_UPDATE_TAG_WORKFLOW.BROWSE_QUEUE");
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
	 *            Teammembers
	 *
	 * @return List<Teammembers>
	 *
	 * @
	 */
	public List<TeamMembers> teamMembersExecuteQuery(final TeamMembers objSearchDao) {
		final String sql = getQuery("OCDAWORK_TEAMMEMBERS_EXECUTEQUERY");
		final RowMapper<TeamMembers> teammemberRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				teamMembersMapping);
		final ArrayList<TeamMembers> returnList = (ArrayList<TeamMembers>) namedParameterJdbcTemplate.query(sql,
				createParams("TEAM_ID", objSearchDao.getTeamId(),"createUserId",objSearchDao.getCreateUserId()), teammemberRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSelectSmLastNameLastNameSmFirstNameFirstNameTmPositionPositionTmRoleRoleSmSexCodeSexCodeTmTeamMemberIdTeamMemberIdTTeamIdTeamIdTTeamCodeTeamCodeTmStaffIdStaffIdTmNoOfTasksNoOfTasksFromStaffMembersSmTeamMembersTmTeamsTWhereTmStaffIdSmStaffIdAndTmTeamIdTTeamIdAndTmActiveFlagY
	 *            List<SelectSmLastNameLastNameSmFirstNameFirstNameTmPositionPositionTmRoleRoleSmSexCodeSexCodeTmTeamMemberIdTeamMemberIdTTeamIdTeamIdTTeamCodeTeamCodeTmStaffIdStaffIdTmNoOfTasksNoOfTasksFromStaffMembersSmTeamMembersTmTeamsTWhereTmStaffIdSmStaffIdAndTmTeamIdTTeamIdAndTmActiveFlagY>
	 *
	 * @
	 */
	public Integer teamMembersUpdateQuery(final List<TeamMembers> teamMembersList) {
		final String sql = getQuery(
				"OCDAWORK_TEAMMEMBERS_UPDATE_SELECT SM.LAST_NAME LAST_NAME, SM.FIRST_NAME FIRST_NAME, TM.POSITION POSITION, TM.ROLE ROLE, SM.SEX_CODE SEX_CODE, TM.TEAM_MEMBER_ID TEAM_MEMBER_ID, T.TEAM_ID TEAM_ID, T.TEAM_CODE TEAM_CODE, TM.STAFF_ID STAFF_ID, TM.NO_OF_TASKS NO_OF_TASKS FROM STAFF_MEMBERS SM, TEAM_MEMBERS TM, TEAMS T WHERE TM.STAFF_ID=SM.STAFF_ID AND TM.TEAM_ID=T.TEAM_ID AND TM.ACTIVE_FLAG = 'Y'");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TeamMembers obj : teamMembersList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (teamMembersList.size() == returnArray.length) {
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
		final String sql = getQuery("OCDAWORK_FIND_RGREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referencecodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgReasonRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSexRecordGroup() {
		final String sql = getQuery("OCDAWORK_FIND_RGSEX");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referencecodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgSexRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		final String sql = getQuery("OCDAWORK_FIND_RGWORKTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referencecodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgWorkTypeRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup() {
		final String sql = getQuery("OCDAWORK_FIND_RGWORKSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referencecodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgWorkSubTypeRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgWorkflowTypeRecordGroup() {
		final String sql = getQuery("OCDAWORK_FIND_RGWORKFLOWTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referencecodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgWorkflowTypeRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		final String sql = getQuery("OCDAWORK_FIND_RGPOSITION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referencecodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgPositionRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgRoleRecordGroup() {
		final String sql = getQuery("OCDAWORK_FIND_RGROLE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referencecodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgRoleRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<Teams> rgTeamStaffRecordGroup(final String agylocId) {
		final String sql = getQuery("OCDAWORK_FIND_RGTEAMSTAFF");
		final RowMapper<Teams> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, teamMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agylocId",agylocId), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgTeamStaffRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMM>
	 */
	public List<StaffMembers> rgTeamMembersRecordGroup() {
		final String sql = getQuery("OCDAWORK_FIND_RGTEAMMEMBERS");
		final RowMapper<StaffMembers> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgTeamMembersRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getOffenderDetails
	 *
	 *
	 */
	@Override
	public TagWorkflowBrowseQueue getOffenderDetails(final Integer offenderBookId) {
		Map<String, Object> returnObject = null;
		final TagWorkflowBrowseQueue bean = new TagWorkflowBrowseQueue();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_OFFENDER_ID_DISPLAY", OracleTypes.VARCHAR),
				new SqlOutParameter("P_ROOT_OFFENDER_ID", OracleTypes.NUMBER), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withProcedureName("GET_OFFENDER_DETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offenderBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setLastName(
					returnObject.get("P_LAST_NAME") != null ? String.valueOf(returnObject.get("P_LAST_NAME")) : null);
			bean.setFirstName(
					returnObject.get("P_FIRST_NAME") != null ? String.valueOf(returnObject.get("P_FIRST_NAME")) : null);
			bean.setOffenderIdDisplay(returnObject.get("P_OFFENDER_ID_DISPLAY") != null
					? String.valueOf(returnObject.get("P_OFFENDER_ID_DISPLAY")) : null);

		} catch (Exception e) {
			logger.error("In method getOffenderDetails", e);
		}
		return bean;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getWorkDetails
	 *
	 *
	 */
	@Override
	public TagWorkflowBrowseQueue getWorkDetails(final Integer workId) {
		Map<String, Object> returnObject = null;
		final TagWorkflowBrowseQueue bean = new TagWorkflowBrowseQueue();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
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
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setWorkflowType(returnObject.get("P_WORKFLOW_TYPE") != null
					? String.valueOf(returnObject.get("P_WORKFLOW_TYPE")) : null);
			bean.setWorkType(
					returnObject.get("P_WORK_TYPE") != null ? String.valueOf(returnObject.get("P_WORK_TYPE")) : null);
			bean.setWorkSubType(returnObject.get("P_WORK_SUB_TYPE") != null
					? String.valueOf(returnObject.get("P_WORK_SUB_TYPE")) : null);
			bean.setModuleName(returnObject.get("P_MODULE_NAME") != null
					? String.valueOf(returnObject.get("P_MODULE_NAME")) : null);
			bean.setManualCloseFlag(returnObject.get("P_MANUAL_CLOSE_FLAG") != null
					? String.valueOf(returnObject.get("P_MANUAL_CLOSE_FLAG")) : null);

		} catch (Exception e) {
			logger.error("In method getWorkDetails", e);
		}
		return bean;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public Integer getClusterFunctionStaffId(final TagWorkflowBrowseQueue searchRecord) {
		final String sql = getQuery("OCDAWORK_GET_CLUSTER_STAFFID");
		Integer clusterIdValue = 0;
		try {
			clusterIdValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("STAFF_ID", searchRecord.getStaffId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method getClusterFunctionStaffId", e);
			return clusterIdValue;
		}
		return clusterIdValue;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public Integer getClusterFunctionTeamId(final TagWorkflowBrowseQueue searchRecord) {
		final String sql = getQuery("OCDAWORK_GET_CLUSTER_TEAMID");
		Integer clusterIdValue = 0;
		try {
			clusterIdValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TEAM_ID", searchRecord.getTeamId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method getClusterFunctionStaffId", e);
			return clusterIdValue;
		}
		return clusterIdValue;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public Integer getStaffId() {
		final String sql = getQuery("OCDAWORK_GET_STAFF_ID");
		Integer staffId = 0;
		try {
			staffId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method getClusterFunctionStaffId", e);
			return staffId;
		}
		return staffId;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getWorkDetails
	 *
	 *
	 */
	@Override
	public Integer getCompleteTask(final TagWorkflowBrowseQueue object) {
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

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getWorkDetails
	 *
	 *
	 */
	@Override
	public Integer getRemoveFromQueue(final TagWorkflowBrowseQueue object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_QUEUE_NAME", Types.VARCHAR),
				new SqlParameter("P_MSGID", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW").withProcedureName("REMOVE_FROM_QUEUE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_QUEUE_NAME", object.getQueueName());
		inParamMap.put("P_MSGID", object.getMsgId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
			returnValue = 1;

		} catch (Exception e) {
			 logger.error("In method getRemoveFromQueue", e);
		}
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getWorkDetails
	 *
	 *
	 */
	@Override
	public Integer getReassigntoTeam(final TagWorkflowBrowseQueue object) {
		int result = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_QUEUE_NAME", Types.VARCHAR),
				new SqlParameter("P_MSGID", Types.VARCHAR), new SqlParameter("P_NEW_TEAM_ID", Types.INTEGER),
				new SqlParameter("P_REMOVE_MESSAGE", Types.INTEGER)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW").withProcedureName("REASSIGN_TO_TEAM").declareParameters(sqlParameters);
		inParamMap.put("P_QUEUE_NAME", object.getQueueName());
		inParamMap.put("P_MSGID", object.getMsgId());
		inParamMap.put("P_NEW_TEAM_ID", object.getAssignedTeamId());
		inParamMap.put("P_REMOVE_MESSAGE", object.getEnableOrDisable());
		
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			result = 1;
		} catch (Exception e) {
			logger.error("In method getReassigntoTeam", e);
		}
		return result;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getWorkDetails
	 *
	 *
	 */
	@Override
	public Integer getUpdateOrderStatus(final TagWorkflowBrowseQueue object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_MSG_ID", Types.NUMERIC) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WF_COURT_REPORT").withProcedureName("UPDATE_ORDER_STATUS").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", object.getOffenderBookId());
		inParamMap.put("P_MSG_ID", object.getOriginalMsgId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
			returnValue = 1;

		} catch (Exception e) {
			logger.error("In method getReassigntoTeam", e);
		}
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getWorkDetails
	 *
	 *
	 */
	@Override
	public Integer getAssigntoTeamMember(final TagWorkflowBrowseQueue object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_QUEUE_NAME", Types.VARCHAR),
				new SqlParameter("P_MSGID", Types.VARCHAR),
				new SqlParameter("P_TEAM_MEMBER_ID", Types.NUMERIC),
				new SqlParameter("P_REMOVE_MESSAGE", Types.NUMERIC) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW").withProcedureName("ASSIGN_TO_TEAM_MEMBER")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(sqlParameters);
		inParamMap.put("P_QUEUE_NAME", object.getQueueName());
		inParamMap.put("P_MSGID", object.getMsgId());
		inParamMap.put("P_TEAM_MEMBER_ID", object.getTeamMemberId());
		inParamMap.put("P_REMOVE_MESSAGE", object.getEnableOrDisable());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
			returnValue = 1;

		} catch (Exception e) {
			 logger.error("In method getAssigntoTeamMember", e);
		}
		return returnValue;
	}

	@Override
	public Integer assignTaskToTeam(TagWorkflowBrowseQueue taskDetails) {
		final String sql = getQuery("OCDAWORK_STAFF_ID_UPDATE");
		Integer value= null;
		try {
			value= namedParameterJdbcTemplate.update(sql, createParams("staffId",taskDetails.getStaffId(),"teamMemberId",
					taskDetails.getTeamMemberId(),"modifyUserId",taskDetails.getModifyUserId(),"taskId",taskDetails.getTaskId()));
		}catch(Exception e) {
			logger.error("In method assignTaskToTeam", e);
		}
		return value;
		
		
		
	}

	@Override
	public List<TagWorkflowBrowseQueue> getTaskDetails(TagWorkflowBrowseQueue object) {
		final String sql = getQuery("OCDAWORK_TASK_GET_DETAILS");
		final RowMapper<TagWorkflowBrowseQueue> tagWorkflowRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TagWorkflowBrowseQueue.class, tagWorkFlowMapping);
		final ArrayList<TagWorkflowBrowseQueue> returnList = (ArrayList<TagWorkflowBrowseQueue>) namedParameterJdbcTemplate
				.query(sql,
						createParams("teamId", object.getTeamId(),"USERID",object.getCreateUserId()),
						tagWorkflowRowMapper);
		return returnList;
	}

	@Override
	public Integer reassignTeamToTask(TagWorkflowBrowseQueue taskDetails) {
		
		final String sql = getQuery("OCDAWORK_NEW_TEAM_ASSIGN");
		Integer value= null;
		try {
			value= namedParameterJdbcTemplate.update(sql, createParams("teamId",taskDetails.getAssignedTeamId(),"modifyUserId",taskDetails.getModifyUserId(),"taskId",taskDetails.getTaskId()));
		}catch(Exception e) {
			logger.error("In method reassignTeamToTask", e);
		}
		return value;
	}

	@Override
	public Integer CompleteTask(TagWorkflowBrowseQueue taskDetails) {
		final String sql = getQuery("OCDAWORK_COMPLETE_TASK");
		Integer value= null;
		try {
			value= namedParameterJdbcTemplate.update(sql, createParams("completionDate",taskDetails.getCompletionDate(),"completeReasonCode",taskDetails.getCompleteReasonode(),
					"completeCommentText",taskDetails.getCompleteCommentText(),"completeUserId",taskDetails.getCompleteUserId()	,"taskId",taskDetails.getTaskId()));
		}catch(Exception e) {
			logger.error("In method reassignTeamToTask", e);
		}
		return value;
	}

	@Override
	public List<TagWorkflowBrowseQueue> getTaskDetails(Integer taskId,String userName) {
		final String sql = getQuery("OCDAWORK_TASK_DETAILS");
		final RowMapper<TagWorkflowBrowseQueue> tagWorkflowRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TagWorkflowBrowseQueue.class, tagWorkFlowMapping);
		final ArrayList<TagWorkflowBrowseQueue> returnList = (ArrayList<TagWorkflowBrowseQueue>) namedParameterJdbcTemplate
				.query(sql,
						createParams("taskId", taskId,"USERID",userName),
						tagWorkflowRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMMTeamMembers>
	 */
	public List<StaffMembers> rgStaffSearchRecordGroup(final Integer teamId) {
		final String sql = getQuery("OCDAWORK_FIND_RGSTAFFSEARCH");
		final RowMapper<StaffMembers> mMMTeamMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				stafMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("teamId", teamId), mMMTeamMembersRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
}
