package net.syscon.s4.workspace.impl;

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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.workspace.OcdmworkRepository;

/**
 * Class OcdmworkRepositoryImpl
 */
@Repository
public class OcdmworkRepositoryImpl extends RepositoryBase implements OcdmworkRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdmworkRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> tagWorkFlowMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
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
			.put("WORK_TYPE", 							new FieldMapper("workType"))
			.put("WORK_SUB_TYPE", 						new FieldMapper("workSubType"))
			.put("OFFENDER_ID_DISPLAY", 				new FieldMapper("offenderIdDisplay"))
			.build();

	/**
	 * Creates new OcdmworkRepositoryImpl class Object
	 */
	public OcdmworkRepositoryImpl() {
		// OcdmworkRepositoryImpl
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
	public List<TagWorkflowBrowseQueue> workExecuteQuery(final TagWorkflowBrowseQueue objSearchDao) {
		final String sql = getQuery("OCDMWORK_WORK_EXECUTERY");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" AND ");
			if (objSearchDao.getAssignmentDate() != null) {
				sqlQuery.append(" TRUNC(ASSIGNMENT_DATE) =:assignmentDate  " + " AND");
				params.addValue("assignmentDate", objSearchDao.getAssignmentDate());
			}
			if (objSearchDao.getDueDate() != null) {
				sqlQuery.append(" TRUNC(DUE_DATE) =:dueDate  " + " AND");
				params.addValue("dueDate", objSearchDao.getDueDate());
			}
			if (objSearchDao.getSeverityCode() != null ) {
				sqlQuery.append(" SEVERITY_CODE =:severityCode  " + " AND");
				params.addValue("severityCode", objSearchDao.getSeverityCode());
			}
			if (objSearchDao.getWorkType() != null || objSearchDao.getWorkSubType() != null) {
				sqlQuery.append(" work_id in(select WORK_ID from WORKS WHERE ");
				if (objSearchDao.getWorkType() != null) {
					sqlQuery.append(" WORK_TYPE =:workType  ");
					params.addValue("workType", objSearchDao.getWorkType());
				}
				if (objSearchDao.getWorkType() != null && objSearchDao.getWorkSubType() != null) {
					sqlQuery.append(" AND ");
				}
				if (objSearchDao.getWorkSubType() != null) {
					sqlQuery.append("  WORK_SUB_TYPE =:workSubType  ");
					params.addValue("workSubType", objSearchDao.getWorkSubType());
				}
				sqlQuery.append(" ) " + " AND ");
			}
			if (objSearchDao.getOffenderIdDisplay() != null) {
				sqlQuery.append(" OFFENDER_BOOK_ID in (select OFFENDER_BOOK_ID from V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK  where OFFENDER_ID_DISPLAY =:offenderIdDisplay  " + " and ACTIVE_FLAG='Y') ");
				params.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
				params.addValue("USERID", objSearchDao.getCreateUserId());
			}
			
		}
		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY ASSIGNMENT_DATE DESC";
		final RowMapper<TagWorkflowBrowseQueue> tagWorkflowRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				TagWorkflowBrowseQueue.class, tagWorkFlowMapping);
		final ArrayList<TagWorkflowBrowseQueue> returnList = (ArrayList<TagWorkflowBrowseQueue>) namedParameterJdbcTemplate
				.query(preparedSql, params, tagWorkflowRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTagWorkflowBrowseQueue
	 *            TagWorkflowBrowseQueue
	 */
	public Integer workUpdateTagWorkflowBrowseQueue(final TagWorkflowBrowseQueue offTrans) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_QUEUE_NAME", offTrans.getQueueName());
		inParamMap.put("P_MSGID", offTrans.getMsgId());
		inParamMap.put("P_COMPLETE_REASON_CODE", offTrans.getCompleteReasonode());
		inParamMap.put("P_COMPLETE_COMMENT_TEXT", offTrans.getCompleteCommentText());
		inParamMap.put("P_COMPLETE_USER_ID", offTrans.getCompleteUserId());
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.TAG_WORKFLOW.COMPLETE_TASK(:P_QUEUE_NAME, :P_MSGID, :P_COMPLETE_REASON_CODE, :P_COMPLETE_COMMENT_TEXT, "
						+ " :P_COMPLETE_USER_ID)", inParamMap);
		genSeq = 1;
		return genSeq;
	}
	
	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTagWorkflowBrowseQueue
	 *            TagWorkflowBrowseQueue
	 */
	public Integer returnAckReceipt(final TagWorkflowBrowseQueue offTrans) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_TEAM_ID", null);
		inParamMap.put("P_STAFF_ID", offTrans.getStaffId());
		inParamMap.put("P_ORIGINAL_WORK_ID", offTrans.getWorkId());
		inParamMap.put("P_ORIGINAL_SEND_DATE", offTrans.getAssignmentDate()); // Date issue....
		inParamMap.put("P_ACKNOWLEDGEMENT_SUBJECT", offTrans.getAcknowledgementSubject());
		inParamMap.put("P_OFFENDER_BOOK_ID", offTrans.getOffenderBookId());
		inParamMap.put("P_ORIGINAL_SENDER_ID", offTrans.getSenderId());
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.TAG_WORKFLOW_ADM.RETURN_ACK_RECEIPT(:P_TEAM_ID, :P_STAFF_ID, :P_ORIGINAL_WORK_ID, :P_ORIGINAL_SEND_DATE, "
						+ " :P_ACKNOWLEDGEMENT_SUBJECT, :P_OFFENDER_BOOK_ID, :P_ORIGINAL_SENDER_ID)", inParamMap);
		genSeq = 1;
		return genSeq;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		final String sql = getQuery("OCDMWORK_FIND_RGREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgReasonRecordGroup :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		final String sql = getQuery("OCDMWORK_FIND_RGTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgTypeRecordGroup :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		final String sql = getQuery("OCDMWORK_FIND_RGWORKTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgWorkTypeRecordGroup :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSubtypeRecordGroup() {
		final String sql = getQuery("OCDMWORK_FIND_RGSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgSubtypeRecordGroup :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgCompletedRecordGroup() {
		final String sql = getQuery("OCDMWORK_FIND_RGCOMPLETED");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgCompletedRecordGroup :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSeverityRecordGroup() {
		final String sql = getQuery("OCDMWORK_FIND_RGSEVERITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgSeverityRecordGroup :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * memoPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> memoPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDMWORK_MEMO_EXECUTERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public List<TagWorkflowBrowseQueue> memoExecuteQuery(final TagWorkflowBrowseQueue searchRecord) {
		final String sql = getQuery("OCDMWORK_MEMO_EXECUTERY");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (searchRecord != null) {
			sqlQuery.append(" AND ");
			if (searchRecord.getAssignmentDate() != null) {
				sqlQuery.append(" TRUNC(ASSIGNMENT_DATE) =:assignmentDate  " + " AND");
				params.addValue("assignmentDate", searchRecord.getAssignmentDate());
			}
			if (searchRecord.getDueDate() != null) {
				sqlQuery.append(" TRUNC(DUE_DATE) =:dueDate  " + " AND");
				params.addValue("dueDate", searchRecord.getDueDate());
			}
			if (searchRecord.getSeverityCode() != null ) {
				sqlQuery.append(" SEVERITY_CODE =:severityCode  " + " AND");
				params.addValue("severityCode", searchRecord.getSeverityCode());
			}
			if (searchRecord.getWorkType() != null || searchRecord.getWorkSubType() != null) {
				sqlQuery.append(" work_id in(select WORK_ID from WORKS WHERE ");
				if (searchRecord.getWorkType() != null) {
					sqlQuery.append(" WORK_TYPE =:workType  ");
					params.addValue("workType", searchRecord.getWorkType());
				}
				if (searchRecord.getWorkType() != null && searchRecord.getWorkSubType() != null) {
					sqlQuery.append(" AND ");
				}
				if (searchRecord.getWorkSubType() != null) {
					sqlQuery.append("  WORK_SUB_TYPE =:workSubType  ");
					params.addValue("workSubType", searchRecord.getWorkSubType());
				}
				sqlQuery.append(" ) " + " AND ");
			}
			

			if (searchRecord.getOffenderIdDisplay() != null) {
				sqlQuery.append(" OFFENDER_BOOK_ID in (select OFFENDER_BOOK_ID from V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK  where OFFENDER_ID_DISPLAY =:offenderIdDisplay  " + " and ACTIVE_FLAG='Y') ");
				params.addValue("offenderIdDisplay", searchRecord.getOffenderIdDisplay());
				params.addValue("USERID", searchRecord.getCreateUserId());
			}
			
		}
		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY ASSIGNMENT_DATE DESC";
		final RowMapper<TagWorkflowBrowseQueue> tagWorkflowRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				TagWorkflowBrowseQueue.class, tagWorkFlowMapping);
		final ArrayList<TagWorkflowBrowseQueue> returnList = (ArrayList<TagWorkflowBrowseQueue>) namedParameterJdbcTemplate
				.query(preparedSql, params, tagWorkflowRowMapper);
		return returnList;
	}

}
