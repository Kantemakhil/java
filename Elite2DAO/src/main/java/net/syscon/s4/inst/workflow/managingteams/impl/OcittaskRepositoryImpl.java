package net.syscon.s4.inst.workflow.managingteams.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VPimsNameSearch;
import net.syscon.s4.inst.workflow.managingteams.OcittaskRepository;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasks;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OcittaskRepositoryImpl
 * 
 * @version 1.0
 */
@Repository
public class OcittaskRepositoryImpl extends RepositoryBase implements OcittaskRepository {
	private static final String DESCRIPTION = "description";
	private static final String OFFENDER_ID_DISPLAY = "OFFENDER_ID_DISPLAY";
	private static final String CASELOAD_ID = "caseloadId";
	private static final String OFFICER_NAME = "OFFICER_NAME";
	private static final String YYYY_MM_DD = "yyyy-MM-dd";
	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static Logger logger = LogManager.getLogger(OcittaskRepositoryImpl.class.getName());

	/**
	 * Creates new OcittaskRepositoryImpl class Object
	 */
	public OcittaskRepositoryImpl() {
		/* OcittaskRepositoryImpl */
	}

	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper(DESCRIPTION)).build();
	private final Map<String, FieldMapper> vPimsNameSearchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put(OFFENDER_ID_DISPLAY, new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("BIRTH_DATE", new FieldMapper("birthDate")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("PRISON_LOCATION", new FieldMapper("prisonLocation")).build();

	private final Map<String, FieldMapper> mmmteamMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("DESCRIPTION", new FieldMapper(DESCRIPTION)).put("TEAM_ID", new FieldMapper("teamId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put(OFFICER_NAME, new FieldMapper(DESCRIPTION)).put("CODE", new FieldMapper("code"))
			.put("STAFF_ID", new FieldMapper("staffId")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TagWorkflowAdmQueryTeamTasks
	 *
	 * @return List<TagWorkflowAdmQueryTeamTasks>
	 *
	 */
	public List<TagWorkflowAdmQueryTeamTasks> tasksExecuteQuery(final TagWorkflowAdmQueryTeamTasks objSearchDao) {
		Map<String, Object> returnObject = null;
		final List<TagWorkflowAdmQueryTeamTasks> lListObj = new ArrayList<>();
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters;
		sqlParameters = sqlParmsData();
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withProcedureName("QUERY_TEAM_TASKS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CASELOAD_ID", objSearchDao.getCaseloadId());
		inParamMap.put("P_OFFENDER_BOOK_ID", objSearchDao.getpOffenderBookId());
		inParamMap.put("P_WORK_TYPE", objSearchDao.getpWorkType());
		inParamMap.put("P_WORK_SUB_TYPE", objSearchDao.getpWorkSubType());
		inParamMap.put("P_TEAM_ID", objSearchDao.getpTeamId());
		inParamMap.put("P_STAFF_ID", objSearchDao.getpStaffId());
		inParamMap.put("P_COMPLETION_STATUS", objSearchDao.getpCompletionStatus());
		inParamMap.put("P_DUE_FROM_DATE", objSearchDao.getpDueFromDate());
		inParamMap.put("P_DUE_TO_DATE", objSearchDao.getpDueToDate());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			final List<Map<String, Object>> list = (List<Map<String, Object>>) returnObject.get("refcursor");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					final Map<String, Object> data = list.get(i);
					final TagWorkflowAdmQueryTeamTasks bean = new TagWorkflowAdmQueryTeamTasks();
					dataPreparation(data, bean);
					lListObj.add(bean);
				}
			}
		} catch (final Exception e) {
			logger.error("tasksExecuteQuery", e);
		}
		return lListObj;
	}

	private void dataPreparation(final Map<String, Object> data, final TagWorkflowAdmQueryTeamTasks bean) {
		if (data.get("ASSIGNMENT_DATE") != null) {
			final LocalDate assignmentDate = LocalDate
					.parse(new SimpleDateFormat(YYYY_MM_DD).format(data.get("ASSIGNMENT_DATE")));
			bean.setAssignmentDate(assignmentDate.format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
		}
		if (data.get("WORK_TYPE") != null) {
			bean.setWorkType(data.get("WORK_TYPE").toString());
			bean.setWorkTypeDescription(getWorkTypeDesciption(bean.getWorkType()));
		}
		if (data.get("WORK_TYPE_DESC") != null) {
			bean.setWorkTypeDesc(data.get("WORK_TYPE_DESC").toString());
		}
		if (data.get("WORK_ID") != null) {
			bean.setWorkId(BigDecimal.valueOf(Long.parseLong(String.valueOf(data.get("WORK_ID")))));
		}
		if (data.get(OFFICER_NAME) != null) {
			bean.setOfficerName(data.get(OFFICER_NAME).toString());
		}
		if (data.get(OFFENDER_ID_DISPLAY) != null) {
			bean.setOffenderIdDisplay(data.get(OFFENDER_ID_DISPLAY).toString());
		}
		if (data.get("OFFENDER_LAST_NAME") != null && data.get("OFFENDER_FIRST_NAME") != null) {
			bean.setOffenderName(
					data.get("OFFENDER_LAST_NAME").toString() + "," + data.get("OFFENDER_FIRST_NAME").toString());
			bean.setOffenderFirstName(data.get("OFFENDER_FIRST_NAME").toString());
			bean.setOffenderLastName(data.get("OFFENDER_LAST_NAME").toString());
		}
		if (data.get("TASK_ASSIGNMENT_HTY_ID") != null) {
			bean.setTaskAssignmentHtyId(Long.valueOf(data.get("TASK_ASSIGNMENT_HTY_ID").toString()));
		}
		if (data.get("WORKFLOW_HISTORY_ID") != null) {
			bean.setWorkflowHistoryId(Long.valueOf(data.get("WORKFLOW_HISTORY_ID").toString()));
		}
		if (data.get("OFFENDER_BOOK_ID") != null) {
			bean.setOffenderBookId(Long.valueOf(data.get("OFFENDER_BOOK_ID").toString()));
		}
		if (data.get("WORK_SUB_TYPE") != null) {
			bean.setWorkSubType(data.get("WORK_SUB_TYPE").toString());
		}
		if (data.get("ASSIGNMENT_DATE") != null) {
			final LocalDate dueDate = LocalDate
					.parse(new SimpleDateFormat(YYYY_MM_DD).format(data.get("ASSIGNMENT_DATE")));
			bean.setAssignmentDate(dueDate.format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
		}
		if (data.get("DUE_DATE") != null) {
			final LocalDate dueDate = LocalDate.parse(new SimpleDateFormat(YYYY_MM_DD).format(data.get("DUE_DATE")));
			bean.setDueDate(dueDate.format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
		}
		if (data.get("COMPLETION_DATE") != null) {
			final LocalDate completionDate = LocalDate
					.parse(new SimpleDateFormat(YYYY_MM_DD).format(data.get("COMPLETION_DATE")));
			bean.setCompletionDate(completionDate.format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
		}
		if (data.get("COMPLETE_REASON_DESC") != null) {
			bean.setCompleteReasonDesc(data.get("COMPLETE_REASON_DESC").toString());
		}
		if (data.get("COMPLETE_REASON_CODE") != null) {
			bean.setCompleteReasonCode(data.get("COMPLETE_REASON_CODE").toString());
		}
		if (data.get("DETAILS") != null) {
			bean.setDetails(data.get("DETAILS").toString());
		}
	}

	private String getWorkTypeDesciption(final String workType) {
		final String sql = getQuery("OCITTASK_FIND_RGTASKTYPE_DESCRIPTION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("workType", workType), String.class);
	}

	private SqlParameter[] sqlParmsData() {
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_WORK_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_WORK_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TEAM_ID", OracleTypes.NUMBER), new SqlParameter("P_STAFF_ID", OracleTypes.NUMBER),
				new SqlParameter("P_COMPLETION_STATUS", OracleTypes.VARCHAR),
				new SqlParameter("P_DUE_FROM_DATE", OracleTypes.DATE),
				new SqlParameter("P_DUE_TO_DATE", OracleTypes.DATE),
				new SqlOutParameter("refcursor", OracleTypes.CURSOR), };
		return sqlParameters;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstTagWorkflowAdmQueryTeamTasks
	 *            List<TagWorkflowAdmQueryTeamTasks>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer tasksInsertTagWorkflowAdmQueryTeamTasks(
			final List<TagWorkflowAdmQueryTeamTasks> lstTagWorkflowAdmQueryTeamTasks) {
		final String sql = getQuery("OCITTASK_TASKS_INSERT_TAG_WORKFLOW_ADM.QUERY_TEAM_TASKS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTagWorkflowAdmQueryTeamTasks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTagWorkflowAdmQueryTeamTasks
	 *            List<TagWorkflowAdmQueryTeamTasks>
	 *
	 */
	public Integer tasksUpdateTagWorkflowAdmQueryTeamTasks(
			final List<TagWorkflowAdmQueryTeamTasks> lstTagWorkflowAdmQueryTeamTasks) {
		final String sql = getQuery("OCITTASK_TASKS_UPDATE_TAG_WORKFLOW_ADM.QUERY_TEAM_TASKS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final TagWorkflowAdmQueryTeamTasks tagWorkflowAdmQueryTeamTasks : lstTagWorkflowAdmQueryTeamTasks) {
			parameters.add(new BeanPropertySqlParameterSource(tagWorkflowAdmQueryTeamTasks));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTagWorkflowAdmQueryTeamTasks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstTagWorkflowAdmQueryTeamTasks
	 *            List<TagWorkflowAdmQueryTeamTasks>
	 *
	 */
	public Integer tasksDeleteTagWorkflowAdmQueryTeamTasks(
			final List<TagWorkflowAdmQueryTeamTasks> lstTagWorkflowAdmQueryTeamTasks) {
		final String sql = getQuery("OCITTASK_TASKS_DELETE_TAG_WORKFLOW_ADM.QUERY_TEAM_TASKS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final TagWorkflowAdmQueryTeamTasks tagWorkflowAdmQueryTeamTasks : lstTagWorkflowAdmQueryTeamTasks) {
			parameters.add(new BeanPropertySqlParameterSource(tagWorkflowAdmQueryTeamTasks));
		}
		try {
			String tableName = "TAG_WORKFLOW_ADM.QUERY_TEAM_TASKS";
			batchUpdatePreDeletedRows(tableName, null, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTagWorkflowAdmQueryTeamTasks.size() == returnArray.length) {
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
	public List<StaffMembers> rgStaffRecordGroup(final Integer teamId) {
		final String sql = getQuery("OCITTASK_FIND_RGSTAFF");
		final RowMapper<StaffMembers> staffMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("teamId", teamId), staffMembersRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	@Override
	public Teams rgTeamIdRecordGroup(final String teamCode,String user) {
		final String sql = getQuery("OCITTASK_FIND_TEAMID");
		final RowMapper<Teams> staffMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class,
				mmmteamMembersMapping);

		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("TEAMCODE", teamCode,"createUserId",user),
					staffMembersRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgCompleteStatusRecordGroup() {
		final String sql = getQuery("OCITTASK_FIND_RGCOMPLETESTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			logger.error("rgCompleteStatusRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgTaskSubTypeRecordGroup(final String taskType) {
		final String sql = getQuery("OCITTASK_FIND_RGTASKSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("taskType", taskType), mRowMapper);
		} catch (final Exception e) {
			logger.error("rgTaskSubTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<ReferenceCodes> rgTaskTypeRecordGroup() {
		final String sql = getQuery("OCITTASK_FIND_RGTASKTYPE");
		final RowMapper<ReferenceCodes> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (final Exception e) {
			logger.error("rgTaskTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMMTeamMembers>
	 */
	public List<Teams> rgTeamRecordGroup(String user) {
		final String sql = getQuery("OCITTASK_FIND_RGTEAM");
		final RowMapper<Teams> mMMTeamMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class,
				mmmteamMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("createUserId",user), mMMTeamMembersRowMapper);
		} catch (final Exception e) {
			logger.error("rgTeamRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return VPimsNameSearch
	 */
	@Override
	public VPimsNameSearch getOffenderBookId(final String offenderIdDisplay, final String caseloadId) {
		final String sql = getQuery("OCITTASK_FIND_OFFENDER_BOOKID");
		final RowMapper<VPimsNameSearch> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VPimsNameSearch.class,
				vPimsNameSearchMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(CASELOAD_ID, caseloadId, CASELOAD_ID,
					caseloadId, "offenderIdDisplay", offenderIdDisplay), mRowMapper);
		} catch (final Exception e) {
			logger.error("rgTaskSubTypeRecordGroup", e);
			return null;
		}
	}

}
