package net.syscon.s4.pkgs.tag_workflow.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.MessageQueue;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowRepository;

@Repository
public class TagWorkflowRepositoryImpl extends RepositoryBase implements TagWorkflowRepository {
	private static Logger logger = LogManager.getLogger(TagWorkflowRepositoryImpl.class.getName());

	private static final String ONE = "1";
	private static final String TWO = "2";

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId")).put("TEAM_ID", new FieldMapper("teamId")).build();

	@Override
	public String insertcaseNotes(final TagWorkflowBrowseQueue newMemoModel) {
		final String sql = getQuery("INSERT_OFFNDER_CASE_NOTES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("offenderBookId", newMemoModel.getOffenderBookId());
		inParamMap.put("staffId", newMemoModel.getStaffId());
		inParamMap.put("contactTime", newMemoModel.getDueDate());
		inParamMap.put("caseNoteType", newMemoModel.getWorkType());
		inParamMap.put("caseNoteSubType", newMemoModel.getWorkSubType());
		inParamMap.put("caseNoteText", newMemoModel.getMessageText());
		inParamMap.put("noteSourceCode", newMemoModel.getMsgId());
		inParamMap.put("eventId", null);
		inParamMap.put("senderId", newMemoModel.getSenderId());
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (DataAccessException e) {
			logger.error("insertcaseNotes :" + e);
			return TWO;
		}
		return ONE;
	}

	@Override
	public Long getEventIdFromOrders(final BigDecimal offenderBookId, final BigDecimal originalMsgId) {
		String sql = getQuery("GET_EVENT_ID");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_BOOK_ID", offenderBookId, "ORIGINAL_MSGID", originalMsgId), Long.class);
		} catch (Exception e) {
			logger.error("getEventIdFromOrders :", e);
		}
		return retVal;
	}

	@Override
	public Integer updateOrderStatus(final BigDecimal offenderBookId, final BigDecimal originalMsgId,
			final String userName) {
		String sql = getQuery("COMPLETE_TASK_UPDATE_ORDER_STATUS");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("OFFENDER_BOOK_ID", offenderBookId,
					"ORIGINAL_MSGID", originalMsgId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updateOrderStatus :", e);
		}
		return retVal;
	}

	@Override
	public Integer updateTeamMembersNoOfTasks(final long teamMemberId, final Integer pAdjustment,
			final String userName) {
		final String sql = getQuery("COMPLETE_TASK_UPDATE_TEAM_MEMBERS_NO_OF_TASKS");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_adjustment", pAdjustment, "p_team_member_id", teamMemberId, "modifyUserId", userName));
	}

	@Override
	public TeamMembers fetchFromTeamMembers(Long pTeamMemberId) {
		String sql = getQuery("ASSIGN_TO_TEAM_MEMBER_FROM_TEAM_MEMBERS");
		TeamMembers tmdetails = new TeamMembers();
		final RowMapper<TeamMembers> rowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class, mMapping);
		try {
			tmdetails = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TEAM_MEMBER_ID", pTeamMemberId),
					rowMapper);
		} catch (Exception e) {
			logger.error("fetchFromTeamMembers :", e);
		}
		return tmdetails;
	}

	@Override
	public Integer updateTeamMembersNoOfTasks(final Long teamMemberId, final Integer pAdjustment,
			final String userName) {
		String sql = getQuery("ASSIGN_TO_TEAM_MEMBER_UPDATE_NO_OF_TASKS");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("P_ADJUSTMENT", pAdjustment,
					"P_TEAM_MEMBER_ID", teamMemberId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updateTeamMembersNoOfTasks :", e);
		}

		return retVal;
	}

	@Override
	public Long getWorkIdFromWorks(final Long workId, final String workType, final String workSubType) {
		String sql = getQuery("ASSIGN_TO_TEAM_MEMBER_GET_WORK_ID");
		Long retVal = null;
		List<Long> retValue = null;
		try {
			RowMapper<Long> mapper = Row2BeanRowMapper.makeMapping(sql, Long.class, mMapping);
			retValue = namedParameterJdbcTemplate.query(sql,
					createParams("P_WORK_ID", workId, "P_WORK_TYPE", workType, "P_WORK_SUB_TYPE", workSubType), mapper);
			for (Long val : retValue) {
				retVal = val;
			}
		} catch (Exception e) {
			logger.error("getWorkIdFromWorks :", e);
		}
		return retVal;
	}

	@Override
	public Long getPrtIdFromOffenderPtr(final String pPtrId) {
		String sql = getQuery("ASSIGN_TO_TEAM_MEMBER_GET_PTR_ID");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PTR_ID", pPtrId), Long.class);
		} catch (Exception e) {
			logger.error("getPrtIdFromOffenderPtr :", e);
		}
		return retVal;
	}

	@Override
	public Integer updateOffenderPtr(final String pPtrId, final Integer staffId, final Date lvAssignmentDate,
			final String userName) {
		String sql = getQuery("ASSIGN_TO_TEAM_MEMBER_UPDATE_OFFENDER_PTR");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("P_STAFF_ID", staffId, "P_ASSIGNMENT_DATE",
					lvAssignmentDate, "P_PTR_ID", pPtrId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updateTeamMembersNoOfTasks :", e);
		}
		return retVal;
	}

	// This C_QUEUES_CURSOR is migrated from oracle
	@Override
	public List<ReferenceCodes> cQueuesCursor() {
		final String sql = getQuery("C_QUEUES_CURSOR");
		List<ReferenceCodes> retList = null;

		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (Exception e) {
			logger.error("cQueuesCursor", e);
			retList = null;
		}
		return retList;
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
	public Integer getClusterStaffId(final Integer staffId) {
		final String sql = getQuery("GET_CLUSTER_STAFF_ID");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_STAFF_ID", staffId), Integer.class);
		} catch (Exception e) {
			logger.error("getClusterStaffId :" + e);
		}
		return retVal;
	}

	@Override
	public Integer getClusterTeamId(final Integer teamId) {
		final String sql = getQuery("GET_CLUSTER_TEAM_ID");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TEAM_ID", teamId), Integer.class);
		} catch (Exception e) {
			logger.error("getClusterTeamId :" + e);
		}
		return retVal;
	}

	@Override
	public TeamMembers reassignToTeamMemberSelect(final Integer teamMemberId) {
		final String sql = getQuery("REASSIGN_TO_TEAM_MEMBER_SELECT");
		TeamMembers retVal = new TeamMembers();
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_NEW_TEAM_MEMBER_ID", teamMemberId),
					TeamMembers.class);
		} catch (Exception e) {
			logger.error("reassignToTeamMemberSelect :" + e);
		}
		return retVal;
	}

}