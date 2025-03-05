package net.syscon.s4.inst.programswithoutschedules.impl;

import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.programswithoutschedules.OsuntaskRepository;



@Repository
public class OsuntaskRepositoryImpl extends RepositoryBase implements OsuntaskRepository {
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsuntaskRepositoryImpl.class.getName());

	/**
	 * Creates new OsuntaskRepositoryImpl class Object
	 */
	public OsuntaskRepositoryImpl() {
	}

	private final Map<String, FieldMapper> staffMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> referenceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WORK_TYPE", new FieldMapper("workType"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("WORK_SUB_TYPE", new FieldMapper("workSubType"))
			.build();
	private final Map<String, FieldMapper> teamMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("TEAM_MEMBER_ID", new FieldMapper("teamMemberId"))
			.put("TEAM_ID", new FieldMapper("teamId")).build();
	private final Map<String, FieldMapper> teamIdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEAM_ID", new FieldMapper("teamId"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).build();
	

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MReferenceCodes>
	 */
	public List<ReferenceCodes> rgWorksRecordGroup(final String caseloadType) {
		final String sql = getQuery("OSUNTASK_FIND_RGWORKS");
		final RowMapper<ReferenceCodes> mapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseload_type",caseloadType), mapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MReferenceCodesM'StfMbrs.staffIdTeams>
	 */
	public List<StaffMembers> rgStaffRecordGroup(final String workId, final String offenderBookId) {
		final String sql = getQuery("OSUNTASK_FIND_RGSTAFF");
		final RowMapper<StaffMembers> mapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("WORKID", workId, "POFFENDERBOOKID", offenderBookId), mapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MReferenceCodesM'StfMbrs.staffIdTeamsMTeams.teamCodeTeams.teamId>
	 */
	public List<TeamMembers> rgTeamsRecordGroup(final String workId, final String offenderBookId,
			final String staffId) {
		final String sql = getQuery("OSUNTASK_FIND_RGTEAMS");
		final RowMapper<TeamMembers> teamIdRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				teamIdMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("STAFFID", staffId, "WORKID", workId, "POFFENDERBOOKID", offenderBookId),
					teamIdRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}




	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * validateAdhocWorkflow
	 *
	 * @param params
	 *
	 */
	public List<TeamMembers> validateAdhocWorkflow(final TeamMembers paramBean) {
		final String sql = getQuery("OSUNTASK_VALIDATE_ADHOC_WORKFLOW");
		final RowMapper<TeamMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				teamMapping);
		return  namedParameterJdbcTemplate.query(sql,
				createParams("T", paramBean.getTeamId(), "", paramBean.getFirstName()), columnRowMapper);
	}

	@Override
	public Integer getTeamemberId(final String teamId, final String staffId) {
		int returnData = 0;
		final String sql = getQuery("OSUNTASK_GET_TEAM_MEMBER_ID");
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("teamId", teamId, "staffId", staffId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return returnData;
	}

	@Override
	public String getSubString(final String offName, final String messageText) {
		final String sql = getQuery("OSUNMEMO_GET_SUB_STRING");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offName", offName, "details", messageText),
				String.class);
	}

	@Override
	public String submitAdhocWorkflow(final TagWorkflowBrowseQueue newTaskModel) {
		String code = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_WORKFLOW_TYPE", Types.VARCHAR),
				new SqlParameter("P_WORK_ID", Types.NUMERIC), new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_TEAM_ID", Types.NUMERIC), new SqlParameter("P_STAFF_ID", Types.NUMERIC),
				new SqlParameter("P_MESSAGE_TEXT", Types.VARCHAR), new SqlParameter("P_DUE_DATE", Types.DATE),
				new SqlParameter("P_SEVERITY", Types.VARCHAR),
				new SqlParameter("P_ACKNOWLEDGEMENT_REQUIRED", Types.VARCHAR),
				new SqlParameter("P_ACKNOWLEDGEMENT_SUBJECT", Types.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADHOC").withProcedureName("CREATE_ADHOC_WORKFLOW")
				.declareParameters(sqlParameters);

		inParamMap.put("P_WORKFLOW_TYPE", "TASK");
		inParamMap.put("P_WORK_ID", newTaskModel.getWorkId());
		inParamMap.put("P_OFFENDER_BOOK_ID", newTaskModel.getOffenderBookId());
		inParamMap.put("P_TEAM_ID", newTaskModel.getTeamId());
		inParamMap.put("P_STAFF_ID", newTaskModel.getStaffId() != 0 ? newTaskModel.getStaffId() : null);
		inParamMap.put("P_MESSAGE_TEXT", newTaskModel.getMessageText());
		inParamMap.put("P_DUE_DATE", newTaskModel.getDueDate());
		inParamMap.put("P_SEVERITY", null);
		inParamMap.put("P_ACKNOWLEDGEMENT_REQUIRED", null);
		inParamMap.put("P_ACKNOWLEDGEMENT_SUBJECT", null);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
			code = "1";// "The system has successfully submitted your request";

		} catch (Exception e) {

			code = "2";// "please contact your administrator";
		}
		return code;
	}

	@Override
	public Integer submitTask(TagWorkflowBrowseQueue newTaskModel) {
		Integer result = null;
		final String sql = getQuery("OSUNTASK_TEAM_INSERT");
		try {
			result= namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newTaskModel));
		}catch(Exception e) {
			result=0;
			
		}
		return result;
	}
	
	@Override
	public Long getTaskId() {
		final String sql = getQuery("TASK_SEQ_ID");
		
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		
	}

	@Override
	public String getUserId(Integer staffId) {
		String userId = null;
		final String sql = getQuery("OSUNTASK_GET_USER_ID");
		try {
			userId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams( "staffId", staffId), String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return userId;
	}

	@Override
	public String getTeamCode(Integer teamId) {
		String teamCode = null;
		final String sql = getQuery("OSUNTASK_GET_TEAM_CODE");
		try {
			teamCode = namedParameterJdbcTemplate.queryForObject(sql,
					createParams( "teamId", teamId), String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return teamCode;
	}

	@Override
	public Integer getTeamemberId(Integer teamId, Integer staffId) {
		int returnData = 0;
		final String sql = getQuery("OSUNTASK_GET_TEAM_MEMBER_ID");
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("teamId", teamId, "staffId", staffId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return returnData;
	}

}
