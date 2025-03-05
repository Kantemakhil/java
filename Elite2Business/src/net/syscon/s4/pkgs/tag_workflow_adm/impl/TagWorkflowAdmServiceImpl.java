
package net.syscon.s4.pkgs.tag_workflow_adm.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.beans.AssignReport;
import net.syscon.s4.inst.legals.beans.CourtReport;
import net.syscon.s4.inst.legals.beans.Terms;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasks;
import net.syscon.s4.pkgs.Message;
import net.syscon.s4.pkgs.MessageQueue;
import net.syscon.s4.pkgs.calculate_balances.CalculateBalancesService;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.tag_security.TagSecurityService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.pkgs.tag_workflow_adm.TagWorkflowAdmRepository;
import net.syscon.s4.pkgs.tag_workflow_adm.TagWorkflowAdmService;
import net.syscon.s4.triggers.OffenderTeamAssignmentsT1Service;
import net.syscon.s4.triggers.OrdersTjnService;

@Service
public class TagWorkflowAdmServiceImpl implements TagWorkflowAdmService {

	private static Logger logger = LogManager.getLogger(TagWorkflowAdmServiceImpl.class.getName());

	@Autowired
	private TagWorkflowAdmRepository tagWorkflowAdmRepository;

	@Autowired
	private TagWorkflowService tagWorkflowService;
	@Autowired
	private CalculateBalancesService calcuateBalancesService;
	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;
	@Autowired
	private TagSecurityService tagSecurityService;
	@Autowired
	private OffenderTeamAssignmentsT1Service offenderTeamAssignmentsT1Service;
	@Autowired
	private OrdersTjnService ordersTjnService;

	private static final String TASK = "TASK";
	private static final String REPORT = "REPORT";
	private static final String CRTREPREQ = "CRTREPREQ";

	private static final String INST = "INST";
	private static final String COMM = "COMM";
	private static final String ASSIGN_WORK_INST = "ASSIGN_WORK_INST";
	private static final String ASSIGN_SELF_INST = "ASSIGN_SELF_INST";
	private static final String ASSIGN_WORK_COMM = "ASSIGN_WORK_COMM";
	private static final String ASSIGN_SELF_COMM = "ASSIGN_SELF_COMM";
	private static final String ALL = "ALL";
	private static final String SELF = "SELF";
	private static final String ONE = "ONE";
	private static final String TWO = "TWO";
	private static final String MEMO = "MEMO";

	@Override
	public Offenders getOffDetailsTrans(final Long offenderBookId) {
		final Object[] obj = tagWorkflowAdmRepository.getOffDetailsTrans(offenderBookId);

		if (obj.length > 0) {
			final Offenders returnObject = new Offenders();
//			returnObject.setRootOffenderId((BigDecimal) obj[0]);
			final String rootOffId = (String) obj[0];
			final String offId = (String) obj[1];
			returnObject.setRootOffenderId(new BigDecimal(rootOffId));
//			returnObject.setOffenderId((Long) obj[1]);
			returnObject.setOffenderId(Long.parseLong(offId));
			returnObject.setOffenderIdDisplay((String) obj[2]);
			returnObject.setLastName((String) obj[3]);
			returnObject.setFirstName((String) obj[4]);

			return returnObject;
		} else {
			return null;
		}
	}

	@Override
	public Offenders getOffDetailsWait(final BigDecimal rootOffenderId) {
		final Object[] obj = tagWorkflowAdmRepository.getOffDetailsWait(rootOffenderId);

		if (obj.length > 0) {
			final Offenders returnObject = new Offenders();
			returnObject.setOffenderId((Long) obj[0]);
			returnObject.setOffenderIdDisplay((String) obj[1]);
			returnObject.setLastName((String) obj[2]);
			returnObject.setFirstName((String) obj[3]);

			return returnObject;
		} else {
			return null;
		}

	}

	@Override
	public String staffMessageBulk(final StaffMembers newMemoModel, final Integer[] teamsArray,
			final Integer[] staffArray) {

		try {
			if ((teamsArray == null && staffArray == null) || (teamsArray != null && teamsArray.length < 1)
					|| (teamsArray == null && staffArray != null && staffArray.length < 1)
					|| (teamsArray.length < 1 && staffArray.length < 1)) {

				/*
				 * Tag_Error.raise_app_error ( p_error_code => -20205, p_error_message => 'No
				 * recipients were provided.', p_stack_trace => TRUE )
				 */
			}
			// there i have to implement logics rrelated TeamsAttay and staffArray
		} catch (Exception e) {
			logger.error("staffMessageBulk", e);
			return "2";
		}
		return "1";

	}

	public void createStaffMessage(final Message msg) {
		final MessageQueue msgQue = new MessageQueue();
		if (msg.getTeamId() == null && msg.getStaffId() == null) {
			/*
			 * Tag_Error.raise_app_error ( p_error_code => -20205, p_error_message => 'No
			 * team or staff was provided.', p_stack_trace => TRUE );
			 */
		}
		msgQue.setWorkFlowType(msg.getWorkFlowType());
		msgQue.setWorkId(msg.getWorkId());
		msgQue.setSeverity(msg.getSeverity());
		if (msg.getAcknowledgementReqired().equals("Y")) {
			msgQue.setAcknowledgementReqired("Y");// means 1
		} else {
			msgQue.setAcknowledgementReqired(null);
		}
		msgQue.setAcknowledgementSubject(msg.getAcknowledgementSubject());
		msgQue.setMessageText(msg.getMessageText());
		msgQue.setOffenderBookId(msg.getOffenderBookId());
		msgQue.setStaffId(msg.getStaffId());
		msgQue.setTeamId(msg.getTeamId());
		msgQue.setDueDate(new Date());
		insertWorkMessage(msgQue);

	}

	public Long insertWorkMessage(final MessageQueue bean) {
		String queueName = null;
		final Message msg = new Message();
		if (msg.getTeamMemberId() != null) {
			final Long staffId = tagWorkflowAdmRepository.selectTeamMembers(bean);
		} else {
			// Tag_Error.raise_app_error
		}
		queueName = "TAGWF1.TAG_WF_ROUTER";
		msg.setTeamId(bean.getTeamId());
		if (bean.getTeamMemberId() != null) {
			// msg.setStaffId(staffId);
		}
		if (bean.getStaffId() != null) {
			// msg.setStaffId(staffId1);
		}
		msg.setCompleteCommentText(bean.getMessageText());
		msg.setOffenderBookId(bean.getOffenderBookId());
		msg.setAssignDate(new Date());
		msg.setSpareDate(new Date());
		msg.setWorkId(bean.getWorkId());
		msg.setDueDate(bean.getDueDate());
		msg.setTriggereReason("WORKMESSAGE");
		msg.setWorkTriggger("WORKMESSAGE");
		msg.setWorkFlowType(bean.getWorkFlowType());
		final Long workFlowId = bean.getWorkId();
		msg.setWorkFlowId(bean.getWorkId().toString());
		msg.setOriginalMsGrid(bean.getWorkId().toString());
		msg.setNoteSourseCode(bean.getSeverity());
		msg.setSpareNumber(Integer.parseInt(bean.getAcknowledgementReqired()));
		msg.setCompleteCommentText(bean.getAcknowledgementSubject());
		// send_message ( p_queue_name => l_queue_name,
		// p_message => l_message,
		// p_message_handle => l_message_handle );
		return workFlowId;
	}

	@Override
	public Integer defaultTeam(final BigDecimal pStaffId) {
		Integer pTeamId = null;
		Integer lCnt = null;
		try {
			lCnt = tagWorkflowAdmRepository.teamCountCur(pStaffId);
			if (lCnt == 1) {
				pTeamId = tagWorkflowAdmRepository.getDefaultTTeamId(pStaffId);
			} else {
				pTeamId = null;
			}
		} catch (Exception e) {
			logger.error("defaultTeam :" + e);
		}
		return pTeamId;
	}

	@Override
	public List<AssignReport> getReportDetailsNew(final BigDecimal offBookId, final Long workFlowId) {
		return tagWorkflowAdmRepository.getReportDetailsNew(offBookId, workFlowId);

	}

	@Override
	public TagWorkflowBrowseQueue getOffenderDetails(final Long pOffenderBookId,String userId) {
		return tagWorkflowAdmRepository.getOffenderDetails(pOffenderBookId,userId);

	}

	@Override
	public Map<String, Object> defaultTeam(final Integer pStaffId) {
		final Map<String, Object> map = new HashedMap<String, Object>();
		Integer pTeamId;
		Integer lCnt = null;
		lCnt = tagWorkflowAdmRepository.teamCntCur(pStaffId);
		if (lCnt == 1) {
			pTeamId = tagWorkflowAdmRepository.teamCur(pStaffId);
		} else {
			pTeamId = null;
		}
		map.put("P_TEAM_ID", pTeamId);
		return map;
	}

	@Override
	public Integer createTeamAssignHtyNew(final CourtReport courtReport) {
		Integer liReturn = 1;
		Long workflowId = null;
		BigDecimal wId = null;
		final MessageQueue lvMsgQueue = new MessageQueue();

		try {
			// get_work_id 794
			wId = tagWorkflowAdmRepository.getWorkId(TASK, REPORT, CRTREPREQ);

			lvMsgQueue.setTeamId(courtReport.getTeamId().longValue());
			lvMsgQueue.setTeamMemberId(courtReport.getTeamMemberId().longValue());
			lvMsgQueue.setWorkId(wId.longValue());
			lvMsgQueue.setOffenderBookId(courtReport.getOffenderBookId().longValue());
			lvMsgQueue.setRequestDate(new java.sql.Date(courtReport.getDateRequested().getTime()));
			lvMsgQueue.setDueDate(new java.sql.Date(courtReport.getDueDate().getTime()));
			lvMsgQueue.setMessageText(courtReport.getCommentText());
			lvMsgQueue.setWorkFlowType("TASK");
			lvMsgQueue.setFunType(courtReport.getFunctionType());

			// tag_workflow.insert_work_message ( lv_msg_queue, lv_workflow_id ); 802
			workflowId = tagWorkflowService.insertWorkMessage(lvMsgQueue);
			// Update Operation
			final Orders oldRecUpdate = tagWorkflowAdmRepository.getOrdersOldRecord(courtReport.getOrderId());
			tagWorkflowAdmRepository.createTeamAssignHtyNewUpdate(new BigDecimal(courtReport.getOffenderBookId()),
					new BigDecimal(courtReport.getOrderId()), workflowId, courtReport.getModifyUserId());
			//ordersTjnService.ordersTjn(null, oldRecUpdate, "UPDATE");
		} catch (Exception e) {
			liReturn = 0;
			logger.error("create_team_assign_hty_new :" + e.getMessage());
		}
		return liReturn;
	}

	@Override
	public String validateStaffMessage(final StaffMembers newMemoModel) {
		String lvWorkflowType = null;
		String code = null;
		final String pWorkflowType = MEMO;

		final String pAcknowledgementRequired = newMemoModel.isAcknowledgementFlag() ? "Y" : "N";

		try {
			if (pWorkflowType == null) {
				throw new IllegalArgumentException("No workflow was provided.");
			}

			if (newMemoModel.getWorkId() != null) {
				lvWorkflowType = null;
				lvWorkflowType = tagWorkflowAdmRepository.getWorkExistsCur(newMemoModel.getWorkId().longValue());

				if (lvWorkflowType == null) {
					throw new IllegalArgumentException("Work item provided does not exist.");
				}
			}

			if (newMemoModel.getWorkId() == null && pWorkflowType.equals("TASK")) {
				throw new IllegalArgumentException("To send a task always provide work item.");
			}

			if (pWorkflowType != null && newMemoModel.getWorkId() != null && !pWorkflowType.equals(lvWorkflowType)) {
				throw new IllegalArgumentException("Discrepancy between the work item and the workflow type provided.");
			}

			if (newMemoModel.getWorkMessage().trim() == null) {
				throw new IllegalArgumentException("Message text is required.");
			}

			if (pAcknowledgementRequired == null
					|| (!pAcknowledgementRequired.equals("Y") || pAcknowledgementRequired.equals("N"))) {
				throw new IllegalArgumentException("Acknowledgement flag can have only values: Y, N or NULL");
			}

			if ((pAcknowledgementRequired.equals("Y") && newMemoModel.getAcknowledgementSubject() == null)
					|| (!pAcknowledgementRequired.equals("Y") && newMemoModel.getAcknowledgementSubject() != null)) {
				throw new IllegalArgumentException("Discrepancy between acknowledgement flag and subject.");
			}
			code = "1";
		} catch (Exception e) {
			logger.error("Exception :", e);
			code = "2";
		}
		return code;
	}

	@Override
	public TagWorkflowBrowseQueue getWorkDetails(final Long pWorkId) {
		String pWorkflowTypeDesc;
		String pWorkTypeDesc;
		String pWorkSubTypeDesc;
		TagWorkflowBrowseQueue retObj = new TagWorkflowBrowseQueue();
		try {
			retObj = tagWorkflowAdmRepository.getWorkCur(pWorkId);
			pWorkflowTypeDesc = omsMiscellaneousService.getDescCode("ALERT_TASK", retObj.getWorkflowType());
			pWorkTypeDesc = omsMiscellaneousService.getDescCode("TASK_TYPE", retObj.getWorkType());
			pWorkSubTypeDesc = omsMiscellaneousService.getDescCode("TASK_SUBTYPE", retObj.getWorkSubType());

			retObj.setWorkflowTypeDesc(pWorkflowTypeDesc);
			retObj.setWorkTypeDesc(pWorkTypeDesc);
			retObj.setWorkSubTypeDesc(pWorkSubTypeDesc);
		} catch (Exception e) {
			logger.error("getWorkDetails :", e);
		}
		return retObj;
	}

	@Override
	public String getAccessLevel(final String userName) {
		String lvAssignment = "";
		String lvAssignWorkRoleCode = null;
		String lvAssignSelfRoleCode = null;
		String lvCaseloadType = null;
		String caseloadId = null;
		try {
			caseloadId = tagSecurityService.getCaseloadId(userName);
			lvCaseloadType = tagWorkflowAdmRepository.caseloadTypeCur(caseloadId);

			if (INST.equals(lvCaseloadType)) {
				lvAssignWorkRoleCode = ASSIGN_WORK_INST;
				lvAssignSelfRoleCode = ASSIGN_SELF_INST;
			} else if (COMM.equals(lvCaseloadType)) {
				lvAssignWorkRoleCode = ASSIGN_WORK_COMM;
				lvAssignSelfRoleCode = ASSIGN_SELF_COMM;
			}
			if (tagSecurityService.checkPrivilegeExists(lvAssignWorkRoleCode, userName)) {
				lvAssignment = "ALL";
			} else if (tagSecurityService.checkPrivilegeExists(lvAssignSelfRoleCode, userName)) {
				lvAssignment = "SELF";
			}

		} catch (Exception e) {
			logger.error("getAccessLevel", e);
			lvAssignment = "";
		}
		return lvAssignment;
	}

	@Override
	public Map<String, Object> getTeamDesc(final BigDecimal teamId) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			final List<Terms> list = tagWorkflowAdmRepository.cTeamDesc(teamId);
			String pTeamCode = null;
			String pTeamName = null;
			for (final Terms term : list) {
				pTeamCode = term.getTeamCode();
				pTeamName = term.getDescription();
			}
			returnMap.put("P_TEAM_CODE", pTeamCode);
			returnMap.put("P_TEAM_NAME", pTeamName);
		} catch (Exception e) {
			logger.error("getTeamDesc", e);
		}
		return returnMap;
	}

	@Override
	public String assignOffender(final Long offenderBookId, final Integer nbtTeamId, final String userName) {
		Integer vcount = null;
		vcount = tagWorkflowAdmRepository.assigmentCur(offenderBookId);
		if (vcount > 0) {
			tagWorkflowAdmRepository.offenderTeamAssignments(offenderBookId);
		}
		tagWorkflowAdmRepository.offenderTeamAssignmentsInsert(offenderBookId, nbtTeamId, userName);
		return null;
	}

	@Override
	public OffenderTeamAssignments deleteOffVteamDtls(final OffenderTeamAssignments objSearchDao) {
		final OffenderTeamAssignments oldRec = tagWorkflowAdmRepository.getOldRecOffenderTeamAss(objSearchDao);
		final OffenderTeamAssignments retObj = tagWorkflowAdmRepository.deleteOffVteamDtls(objSearchDao);
		offenderTeamAssignmentsT1Service.offenderTeamAssignmentsT1Trigger(oldRec);
		return retObj;
	}

	@Override
	public OffenderTeamAssignments getTeamDetails(final OffenderTeamAssignments searchbean) {
		final OffenderTeamAssignments returnObj = new OffenderTeamAssignments();
		try {
			final Teams obj = tagWorkflowAdmRepository.getTeamDetails(searchbean);
			returnObj.setTeamId(BigDecimal.valueOf(obj.getTeamId()));
			returnObj.setTeamIdDesc(obj.getDescription());
		} catch (Exception e) {
			logger.error("getTeamDetails :" + e);
		}
		return returnObj;
	}

	@Override
	public OffenderTeamAssignments getTeamDesc(final OffenderTeamAssignments searchbean) {
		final OffenderTeamAssignments bean = new OffenderTeamAssignments();
		try {
			final Teams obj = tagWorkflowAdmRepository.getTeamDesc(searchbean.getTeamId());
			bean.setTeamCode(obj.getTeamCode());
			bean.setTeamIdDesc(obj.getDescription());
		} catch (Exception e) {
			logger.error("getTeamDesc :" + e);
		}
		return bean;
	}

	@Override
	public Integer returnAckReceipt(final TagWorkflowBrowseQueue offTrans) {
		String lvAckNarrative;
		Long lvOriginatorStaffId;
		Long lvWorkflowId;
		Long lvAckWorkId = null;
		Integer genSeq = 0;

		if (offTrans.getTeamId() != null && offTrans.getStaffId() != null) {
			// throw new ArithmeticException("Either the team or the staff can send the
			// acknowledgement receipt");
		}

		if (offTrans.getTeamId() != null) {
			lvAckWorkId = tagWorkflowAdmRepository.getAckWorkIdCur("MEMO_REC", "TEAM");
		} else if (offTrans.getStaffId() != null) {
			lvAckWorkId = tagWorkflowAdmRepository.getAckWorkIdCur("MEMO_REC", "STAFF");
		}

		if (lvAckWorkId != null) {
			lvAckNarrative = null;
			// To do call BUILD_ACK_NARRATIVE procedure call
			lvOriginatorStaffId = tagWorkflowAdmRepository.lvOriginatorStaffId(offTrans.getSenderId());
			final MessageQueue lvMsgQueue = new MessageQueue();
			lvMsgQueue.setWorkFlowType("MEMO");
			lvMsgQueue.setWorkId(lvAckWorkId);
			lvMsgQueue.setMessageText(lvAckNarrative);
			lvMsgQueue.setStaffId(lvOriginatorStaffId);
			lvMsgQueue.setDueDate(new Date());
			lvWorkflowId = insertWorkMessage(lvMsgQueue);
		}
		genSeq = 1;
		return genSeq;
	}

	@Override
	public List<TagWorkflowAdmQueryTeamTasks> queryTeamTasks(final TagWorkflowAdmQueryTeamTasks objSearchDao) {
		final Map<String, Object> map = new HashedMap<>();
		final List<Map<String, Object>> mapList = new ArrayList<>();
		List<TagWorkflowAdmQueryTeamTasks> queryList = tagWorkflowAdmRepository.queryTeamTasks(objSearchDao);
//		queryList.forEach(bo -> {
//			map.put("ASSIGNMENT_DATE", bo.getAssignmentDate());
//			map.put("WORK_TYPE", bo.getWorkType());
//			map.put("WORK_TYPE_DESC", bo.getWorkTypeDesc());
//			map.put("WORK_ID", bo.getWorkId());
//			map.put("OFFICER_NAME", bo.getOfficerName());
//			map.put("OFFENDER_ID_DISPLAY", bo.getOffenderIdDisplay());
//			map.put("OFFENDER_LAST_NAME", bo.getOffenderLastName());
//			map.put("TASK_ASSIGNMENT_HTY_ID", bo.getTaskAssignmentHtyId());
//			map.put("WORKFLOW_HISTORY_ID", bo.getWorkflowHistoryId());
//			map.put("OFFENDER_BOOK_ID", bo.getOffenderBookId());
//			map.put("WORK_SUB_TYPE", bo.getpWorkSubType());
//			map.put("ASSIGNMENT_DATE", bo.getAssignmentDate());
//			map.put("DUE_DATE", bo.getDueDate());
//			map.put("COMPLETION_DATE", bo.getCompletionDate());
//			map.put("COMPLETE_REASON_DESC", bo.getCompleteReasonDesc());
//			map.put("COMPLETE_REASON_CODE", bo.getCompleteReasonCode());
//			map.put("DETAILS", bo.getDetails());
//			mapList.add(map);
//		});
		return queryList;
	}

	@Override
	public List<TaskAssignmentHty> queryOffenderTasks(final TaskAssignmentHty bean) {
		final List<Map<String, Object>> mapList = new ArrayList<>();
		final Map<String, Object> map = new HashedMap<>();
		final List<TaskAssignmentHty> list = tagWorkflowAdmRepository.queryOffenderTasks(bean);
//		list.forEach(bo -> {
//			map.put("TASK_ASSIGNMENT_HTY_ID", bo.getTaskAssignmentHtyId());
//			map.put("ASSIGNMENT_DATE", bo.getAssignmentDate());
//			map.put("COMPLETION_DATE", bo.getCompletionDate());
//			map.put("DUE_DATE", bo.getDueDate());
//			map.put("WORK_TYPE", bo.getWorkType());
//			map.put("WORK_SUB_TYPE", bo.getWorkSubType());
//			map.put("TEAM_ID", bo.getTeamId());
//			map.put("STAFF_ID", bo.getStaffId());
//			map.put("WORKFLOW_HISTORY_ID", bo.getWorkflowHistoryId());
//			map.put("WORK_ID", bo.getWorkId());
//			map.put("COMPLETE_REASON_CODE", bo.getCompleteReasonCode());
//			map.put("DETAILS", bo.getDetails());
//			mapList.add(map);
//		});
		return list;
	}

}