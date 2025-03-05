package net.syscon.s4.pkgs.tag_workflow_adm.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.au.VCbSentTerms;
import net.syscon.s4.inst.legals.beans.AssignReport;
import net.syscon.s4.inst.legals.beans.Terms;
import net.syscon.s4.inst.legalscreens.sentenceadministration.beans.VCbCustodyPeriod;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasks;
import net.syscon.s4.pkgs.MessageQueue;
import net.syscon.s4.pkgs.tag_workflow_adm.TagWorkflowAdmRepository;

@Repository
public class TagWorkflowAdmRepositoryImpl extends RepositoryBase implements TagWorkflowAdmRepository {

	private static Logger logger = LogManager.getLogger(TagWorkflowAdmRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId")).build();

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FUNCTION_TYPE", new FieldMapper("function")).put("TEAM_CODE", new FieldMapper("teamResponsible"))
			.put("DESCRIPTION", new FieldMapper("teamName")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).build();

	RowMapper<Object[]> mapper1 = new RowMapper<Object[]>() {
		public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
			Object[] obj = new Object[5];
			obj[0] = rs.getString("OFFENDER_ID");
			obj[1] = rs.getString("OFFENDER_ID_DISPLAY");
			obj[2] = rs.getString("LAST_NAME");
			obj[3] = rs.getString("FIRST_NAME");
			return obj;
		}
	};
	RowMapper<Object[]> mapper = new RowMapper<Object[]>() {
		public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
			Object[] obj = new Object[5];
			obj[0] = rs.getString("ROOT_OFFENDER_ID");
			obj[1] = rs.getString("OFFENDER_ID");
			obj[2] = rs.getString("OFFENDER_ID_DISPLAY");
			obj[3] = rs.getString("LAST_NAME");
			obj[4] = rs.getString("FIRST_NAME");
			return obj;
		}
	};

	@Override
	public Object[] getOffDetailsTrans(final Long offenderBookId) {
		final String sql = getQuery("GET_OFF_DETAILS_TRANS_OFF_DET_CUR");
		Object[] returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFF_BOOK_ID", offenderBookId),
					mapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;
	}

	@Override
	public Object[] getOffDetailsWait(final BigDecimal rootOffenderId) {
		final String sql = getQuery("GET_OFF_DETAILS_WAIT_OFF_DET_CUR");
		Object[] returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ROOT_OFF_ID", rootOffenderId),
					mapper1);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;
	}

	@Override
	public Long selectTeamMembers(final MessageQueue bean) {
		final String sql = getQuery("SELECT_TEAM_MEMBERS");
		Long staffId = null;
		try {
			staffId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("selectTeamMembers" + e);
		}
		return staffId;
	}

	@Override
	public Integer teamCountCur(final BigDecimal pStaffId) {
		final String sql = getQuery("DEFAULT_TEAM_TEAM_CNT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_STAFF_ID", pStaffId), Integer.class);
	}

	@Override
	public Integer getDefaultTTeamId(final BigDecimal pStaffId) {
		final String sql = getQuery("DEFAULT_TEAM_GET_TEAM_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_STAFF_ID", pStaffId), Integer.class);
	}

	@Override
	public List<AssignReport> getReportDetailsNew(final BigDecimal offBookId, final Long workFlowId) {
		final String sql = getQuery("P_OFF_MSG_REC_NEW");
		List<AssignReport> retList = new ArrayList<AssignReport>();
		final RowMapper<AssignReport> rowMapper = Row2BeanRowMapper.makeMapping(sql, AssignReport.class, mapping);

		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_workflow_id", workFlowId, "p_off_book_id", offBookId), rowMapper);
		} catch (Exception e) {
			logger.error("getReportDetailsNew :" + e);
		}
		return retList;
	}

	@Override
	public TagWorkflowBrowseQueue getOffenderDetails(final Long pOffenderBookId,String userName) {
		final String sql = getQuery("TAG_WORKFLOW_ADM_GET_OFFENDER_DETAILS");
		TagWorkflowBrowseQueue retObj = new TagWorkflowBrowseQueue();
		final RowMapper<TagWorkflowBrowseQueue> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				TagWorkflowBrowseQueue.class, mMapping);
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID", pOffenderBookId,"USERID",userName),
					rowMapper);
		} catch (Exception e) {
			logger.error("getOffenderDetails :" + e);
			retObj = null;
		}
		return retObj;

	}

	@Override
	public Integer teamCntCur(final Integer pStaffId) {
		final String sql = getQuery("TEAM_CNT_CUR");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_staff_id", pStaffId),
					Integer.class);
		} catch (Exception e) {
			logger.error("teamCntCur :" + e);

		}
		return retVal;
	}

	@Override
	public Integer teamCur(final Integer pStaffId) {
		final String sql = getQuery("TEAM_CUR");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_staff_id", pStaffId),
					Integer.class);
		} catch (Exception e) {
			logger.error("teamCur :" + e);

		}
		return retVal;
	}

	@Override
	public BigDecimal getWorkId(final String task, final String report, final String crtrepreq) {
		final String sql = getQuery("GET_WORK_ID");
		BigDecimal wId = null;
		try {
			wId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("task", task, "report", report, "crtrepreq", crtrepreq), BigDecimal.class);
		} catch (Exception e) {
			logger.error("getWorkId", e);
			wId = null;
		}
		return wId;
	}

	@Override
	public void createTeamAssignHtyNewUpdate(final BigDecimal pOffenderBookId, final BigDecimal pOrderId,
			final Long workflowId, final String userName) {
		final String sql = getQuery("CREATE_TEAM_ASSIGN_HTY_NEW_UPDATE");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("pOffenderBookId", pOffenderBookId);
		inParamMap.put("pOrderId", pOrderId);
		inParamMap.put("workflowId", workflowId);
		inParamMap.put("modifyUserId", userName);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("createTeamAssignHtyNewUpdate", e);
		}
	}

	@Override
	public String getWorkExistsCur(Long pWorkId) {
		final String sql = getQuery("GET_WORK_EXISTS_CUR");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_work_id", pWorkId), String.class);
		} catch (Exception e) {
			logger.error("getWorkExistsCur", e);

		}
		return retVal;
	}

	@Override
	public List<VCbSentTerms> coCursor() {
		final String sql = getQuery("CO_CURSOR");
		final RowMapper<VCbSentTerms> vCbSentTermsRowMapper = Row2BeanRowMapper.makeMapping(sql, VCbSentTerms.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), vCbSentTermsRowMapper);

	}

	@Override
	public List<VCbCustodyPeriod> coCursorOne() {
		final String sql = getQuery("CO_CURSOR_ONE");
		final RowMapper<VCbCustodyPeriod> vCbCustodyPeriodRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VCbCustodyPeriod.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), vCbCustodyPeriodRowMapper);
	}

	@Override
	public TagWorkflowBrowseQueue getWorkCur(final Long pWorkId) {
		final String sql = getQuery("GET_WORK_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_WORK_ID", pWorkId),
				TagWorkflowBrowseQueue.class);
	}

	@Override
	public String caseloadTypeCur(final String caseloadId) {
		final String sql = getQuery("CASE_LOAD_TYPE_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseload_id", caseloadId), String.class);
	}

	@Override
	public OffenderTeamAssignments deleteOffVteamDtls(OffenderTeamAssignments objSearchDao) {
		final String sql = getQuery("DELETE_OFF_VTEAM_DTLS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_TEAM_ASSIGNMENTS";
			String whereCondition = "OFFENDER_BOOK_ID = :P_OFF_BOOK_ID AND FUNCTION_TYPE = :P_FUNCTION_TYPE AND TEAM_ID = :P_TEAM_ID AND ASSIGNMENT_DATE = :P_ASSIGN_DATE";
			inputMap.put("P_OFF_BOOK_ID", objSearchDao.getOffenderBookId());
			inputMap.put("P_FUNCTION_TYPE", objSearchDao.getFunctionType());
			inputMap.put("P_TEAM_ID", objSearchDao.getTeamId());
			inputMap.put("P_ASSIGN_DATE", objSearchDao.getAssignmentDate());
			inputMap.put("modifyUserId", objSearchDao.getModifyUserId());
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffVteamDtls " + e.getMessage());
		}
		try {
			namedParameterJdbcTemplate.update(sql,
					createParams("P_OFF_BOOK_ID", objSearchDao.getOffenderBookId(), "P_FUNCTION_TYPE",
							objSearchDao.getFunctionType(), "P_TEAM_ID", objSearchDao.getTeamId(), "P_ASSIGN_DATE",
							objSearchDao.getAssignmentDate()));
			objSearchDao.setReturnValue(1);
		} catch (DataAccessException dae) {
			logger.error("deleteOffVteamDtls :" + dae);
			objSearchDao.setReturnValue(0);
		}
		return objSearchDao;
	}

	@Override
	public List<Terms> cTeamDesc(BigDecimal teamId) {
		final String sql = getQuery("C_TEAM_DESC");
		return namedParameterJdbcTemplate.query(sql, createParams("p_team_id", teamId),
				new RowMapperResultSetExtractor<Terms>(new BeanPropertyRowMapper<Terms>(Terms.class)));
	}

	@Override
	public Integer assigmentCur(final Long offenderBookId) {
		final String sql = getQuery("ASSIGMENT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_book_id", offenderBookId),
				Integer.class);
	}

	@Override
	public Integer offenderTeamAssignments(final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_TEAM_ASSIGNMENTS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_off_book_id", offenderBookId));
	}

	@Override
	public Integer offenderTeamAssignmentsInsert(final Long offenderBookId, final Integer teamId,
			final String userName) {
		final String sql = getQuery("OFFENDER_TEAM_ASSIGNMENTS_INSERT");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_off_book_id", offenderBookId, "p_team_id", teamId, "createUserId", userName));
	}

	@Override
	public Teams getTeamDetails(final OffenderTeamAssignments searchbean) {
		final String sql = getQuery("GET_TEAM_DETAILS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_TEAM_CODE", searchbean.getTeamCode(), "P_FUNCTION_TYPE", searchbean.getFunctionType()),
				Teams.class);
	}

	@Override
	public Teams getTeamDesc(final BigDecimal pTeamId) {
		final String sql = getQuery("GET_TEAM_DESC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TEAM_ID", pTeamId), Teams.class);
	}

	@Override
	public Long getAckWorkIdCur(String workType, String workSubType) {
		final String sql = getQuery("GET_ACK_WORK_ID_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CP_WORK_TYPE", workType, "CP_WORK_SUBTYPE", workSubType), Long.class);
	}

	@Override
	public Long lvOriginatorStaffId(String pOriginalSenderId) {
		final String sql = getQuery("LV_ORIGINATOR_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ORIGINAL_SENDER_ID", pOriginalSenderId),
				Long.class);
	}

	@Override
	public List<TagWorkflowAdmQueryTeamTasks> queryTeamTasks(final TagWorkflowAdmQueryTeamTasks bean) {
		final String sql = getQuery("QUERY_TEAM_TASKS");
		List<TagWorkflowAdmQueryTeamTasks> list = new ArrayList<TagWorkflowAdmQueryTeamTasks>();
		final RowMapper<TagWorkflowAdmQueryTeamTasks> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				TagWorkflowAdmQueryTeamTasks.class, mMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_caseload_id", bean.getCaseloadId(), "p_offender_book_id", bean.getpOffenderBookId(),
							"p_work_type", bean.getpWorkType(), "p_work_sub_type", bean.getpWorkSubType(), "p_staff_id",
							bean.getpStaffId(), "p_completion_status", bean.getpCompletionStatus(), "p_due_from_date",
							bean.getpDueFromDate(), "p_due_to_date", bean.getpDueToDate()),
					rowMapper);
		} catch (Exception e) {
			logger.error("QUERY_TEAM_TASKS :" + e);
		}
		return list;
	}

	@Override
	public List<TaskAssignmentHty> queryOffenderTasks(final TaskAssignmentHty bean) {
		final String sql = getQuery("QUERY_OFFENDER_TASKS");
		List<TaskAssignmentHty> list = new ArrayList<TaskAssignmentHty>();
		final RowMapper<TaskAssignmentHty> rowMapper = Row2BeanRowMapper.makeMapping(sql, TaskAssignmentHty.class,
				mMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_work_type", bean.getWorkType(), "p_complete_rsn_code", bean.getCompleteReasonCode(),
							"p_team_id", bean.getTeamId(), "p_offender_book_id", bean.getOffenderBookId(), "p_staff_id",
							bean.getStaffId(), "p_work_sub_type", bean.getWorkSubType()),
					rowMapper);
		} catch (Exception e) {
			logger.error("QUERY_OFFENDER_TASKS :" + e);
		}
		return list;
	}

	@Override
	public OffenderTeamAssignments getOldRecOffenderTeamAss(final OffenderTeamAssignments objSearchDao) {
		final String sql = getQuery("GET_OLD_REC_OFFENDER_TEAM_ASSIGNMENTS");
		OffenderTeamAssignments obj = new OffenderTeamAssignments();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId(), "FUNCTION_TYPE",
							objSearchDao.getFunctionType(), "P_TEAM_ID", objSearchDao.getTeamId(), "P_ASSIGN_DATE",
							objSearchDao.getAssignmentDate()),
					new BeanPropertyRowMapper<OffenderTeamAssignments>(OffenderTeamAssignments.class));
		} catch (Exception e) {
			logger.error("getOldRecOffenderTeamAss :" + e);
		}
		return obj;
	}

	@Override
	public Orders getOrdersOldRecord(final Integer pOrderId) {
		final String sql = getQuery("GET_OLD_RECORD_ORDERS");
		Orders obj = new Orders();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ORDER_ID", pOrderId), Orders.class);
		} catch (Exception e) {
			logger.error("getOrdersOldRecord :" + e);
		}
		return obj;
	}
}