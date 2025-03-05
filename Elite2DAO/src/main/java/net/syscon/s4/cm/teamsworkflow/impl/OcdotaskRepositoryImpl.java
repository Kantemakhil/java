package net.syscon.s4.cm.teamsworkflow.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.OcdotaskRepository;
import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdotaskRepositoryImpl
 */
@Repository
public class OcdotaskRepositoryImpl extends RepositoryBase implements OcdotaskRepository {
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdotaskRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE",            new FieldMapper("code"))
			.put("DESCRIPTION",     new FieldMapper("description"))
			.put("TEAM_CODE",       new FieldMapper("teamCode"))
			.build();

	/**
	 * Creates new OcdotaskRepositoryImpl class Object
	 */
	public OcdotaskRepositoryImpl() {
		// OcdotaskRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TagWorkflowAdmQueryOffenderTasks
	 *
	 * @return List<TagWorkflowAdmQueryOffenderTasks>
	 *
	 */
	public List<TaskAssignmentHty> tasksExecuteQuery(final TaskAssignmentHty objSearchDao) {
		List<TaskAssignmentHty> returnList = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[7];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_TASK_REC", OracleTypes.CURSOR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_WORK_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_WORK_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TEAM_ID", OracleTypes.NUMBER), new SqlParameter("P_STAFF_ID", OracleTypes.NUMBER),
				new SqlParameter("P_COMPLETE_RSN_CODE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withProcedureName("QUERY_OFFENDER_TASKS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
		inParamMap.put("P_WORK_TYPE", objSearchDao.getWorkType());
		inParamMap.put("P_WORK_SUB_TYPE", objSearchDao.getWorkSubType());
		inParamMap.put("P_TEAM_ID", null);
		inParamMap.put("P_STAFF_ID", objSearchDao.getStaffId());
		inParamMap.put("P_COMPLETE_RSN_CODE", objSearchDao.getCompleteReasonCode());
		inParamMap.put("P_TASK_REC", OracleTypes.CURSOR);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		TaskAssignmentHty bean = new TaskAssignmentHty();
		returnObject = simpleJDBCCall.execute(inParameter);
		returnList = new ArrayList<>();
		final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_TASK_REC");
		for (int i = 0; i < list.size(); i++) {
			final Map<String, String> childMap = list.get(i);
			bean = new TaskAssignmentHty();
			bean.setTaskAssignmentHtyId(Long.valueOf(String.valueOf(childMap.get("TASK_ASSIGNMENT_HTY_ID"))));
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date date;
			try {

				date = format.parse(String.valueOf(childMap.get("ASSIGNMENT_DATE")));
				bean.setAssignmentDate(date);
				bean.setCompletionDate(childMap.get("COMPLETION_DATE") != null
						? format.parse(String.valueOf(childMap.get("COMPLETION_DATE"))) : null);
				bean.setDueDate(childMap.get("DUE_DATE") != null
						? format.parse(String.valueOf(childMap.get("DUE_DATE"))) : null);
			} catch (ParseException e) {

			}
			if(childMap.get("WORK_TYPE")!=null) {
				bean.setWorkType(childMap.get("WORK_TYPE"));	
				bean.setWorkTypeDescription(getWorkTypeDesciption(bean.getWorkType()));
			}
			bean.setWorkSubType(
					childMap.get("WORK_SUB_TYPE") != null ? String.valueOf(childMap.get("WORK_SUB_TYPE")) : null);
			bean.setTeamCode(childMap.get("TEAM_ID") != null ? Integer.valueOf(String.valueOf(childMap.get("TEAM_ID"))) : null);
			bean.setStaffId(childMap.get("STAFF_ID") != null ? new BigDecimal(String.valueOf(childMap.get("STAFF_ID"))) : null);
			bean.setWorkflowHistoryId(BigDecimal.valueOf(Long.parseLong(String.valueOf(childMap.get("WORKFLOW_HISTORY_ID")))));
			bean.setWorkId(BigDecimal.valueOf(Long.parseLong(String.valueOf(childMap.get("WORK_ID")))));
			bean.setCompleteReasonCode(childMap.get("COMPLETE_REASON_CODE") != null
					? String.valueOf(childMap.get("COMPLETE_REASON_CODE")) : null);
			bean.setDetails(childMap.get("DETAILS") != null ? String.valueOf(childMap.get("DETAILS")) : null);
			returnList.add(bean);
		}
		return returnList;
	}
	
	private String getWorkTypeDesciption(String workType) {
		String sql = getQuery("OCDOTASK_FIND_RGTASKTYPE_DESCRIPTION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("workType",workType),  String.class);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgCompleteRsnRecordGroup() {
		final String sql = getQuery("OCDOTASK_FIND_RGCOMPLETERSN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnList = new ArrayList<>();
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<StaffMembers> rgStaffRecordGroup(final String teamId) {
		final String sql = getQuery("OCDOTASK_FIND_RGSTAFF");
		final RowMapper<StaffMembers> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				refCodesMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("TEAMID", teamId), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnList = new ArrayList<>();
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgTeamRecordGroup() {
		final String sql = getQuery("OCDOTASK_FIND_RGTEAM");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				refCodesMapping);
		List<StaffMembers> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnList = new ArrayList<>();
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgTaskSubTypeRecordGroup() {
		final String sql = getQuery("OCDOTASK_FIND_RGTASKSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnList = new ArrayList<>();
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<ReferenceCodes> rgTaskTypeRecordGroup() {
		final String sql = getQuery("OCDOTASK_FIND_RGTASKTYPE");
		final RowMapper<ReferenceCodes> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnList = new ArrayList<>();
			logger.error(e);
		}
		return returnList;
	}

}
