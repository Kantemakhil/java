package net.syscon.s4.pkgs.tag_workflow.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.pkgs.Message;
import net.syscon.s4.pkgs.MessageQueue;
import net.syscon.s4.pkgs.TagWfMessage;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowRepository;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.CreateAgencyWorkflow;

/*
 *  Below comments are copied from package TAG_WORKFLOW 
||    Purpose: This package provides the basic functions of the work flow module.
||             It creates work and completes work automatically, etc...
||
||    MODIFICATION HISTORY (Please put version history IN a REVERSE-chronological ORDER below)
||    ------------------------
||    Person                     DATE     Version  Comments
||    ---------              -----------  -------  -----------------------------------
||   Nasir                  13-AUG-2015  10.2.31.1.6  HPQC#25893 : Modify exception in FORWARD_CASE_NOTE procedure to skip the error if the staff id is null
||   NDB                    25-Nov-2011  10.2.31.1.5  QC 11384 Changes for PTI. New routine create_agency_workflow. Changes to assignment processing.
||   Ruxandra               21-Feb-2011  10.2.31.1.4  Added commnets related to reserved trigger name ADHOC_EMAIL
||   Ruxandra               30-AUG-2010  10.2.31.1.3  Modifications to accommodate executable for email, dynamic recipients, body, subject
||   Ruxandra               30-AUG-2010  10.2.31.1.2  Modifications to accommodate executable for email, dynamic recipients, body, subject
||   ignore line, incorrect version Ruxandra               30-AUG-2010  10.2.31.1.1.1.0 SDSTDOC  Modifications to accommodate executable for email, dynamic recipients, body, subject
||    Steven	26-JAN-2010	10.2.31.1.1 Bug fixes
||    Ruxandra               20-FEB-2009  10.2.31.1.0 Enhancement for Staff memos and Emails messages
||    NIKO                   25-NOV-2008  10.2.31  Added a new field function_type to queue_rec
||                                                 Modified the PROCEDURE browse_queue and PROCEDURE browse_work_message
||    GJC                    11-Jun-2007  10.2.30  Remove ORIGINAL_TAG_WF_MESSAGE from WORKFLOW_HISTORY and replace with individual elements
||    NDB                    13-MAR-2007  10.2.29  #6247 Corrected.
||    NDB                    12-MAR-2007  10.2.28  #6247 Corrected.
||    NDB                    09-MAR-2007  10.2.27  #6247 Changes to support new event date
||    GJC                    14-Oct-2005  2.26     SHOW_VERSION changed from procedure to function
||    Patrick                07-SEP-2006  2.25     Defect 4458. Added ORIGINAL_MSGID in scan_queue and browse queue for iwp linkage
||    Erin                   27-JUN-2006  10.2.24  Remove FUNCTION chk_team_id
||    Erin                   26-Jun-2006  10.2.23  Fix version to re-compile
||    Erin                   22-JUN-2006  10.2.22  Added FUNCTION chk_team_id for from OCMDEFTM
||    GJC                    31-MAY-2006  10.2.21  Defect 1.1 Release 1.1 Workflows
||    GJC                    31-MAY-2006  10.2.20  Defect 1.1 Release 1.1 Workflows
||    GJC                    31-MAY-2006  10.2.19  Defect 1.1 Release 1.1 Workflows
||    Neil                   25-May-2006  10.2.21  Fixed version numbers
||    Neil                   25-May-2006  10.2.20  Changed insert_work_message.
||    Neil                   25-May-2006  10.2.19  Changed workflow history control of open and closing workflows.
||    GJC                    09-MAY-2006  10.2.18  Add work_trigger_executables processing
||    Neil                   05-May-2006  10.2.17  Assignment date is now set to sysdate for every re-assignment
||    Neil                   24-Apr-2006  10.2.16  Fixed add of complete reason code for auto completion.
||    Neil                   21-Apr-2006  10.2.15  Added complete reason code to complete workflow.
||    Neil                   20-Apr-2006  10.2.14  Fixed search_queue
||    Neil                   19-Apr-2006  10.2.13  insert_case_note: added date_creation
||    GJC                    05-Apr-2006  10.2.12  Add check_outstanding_task.
||    Neil                   05-Apr-2006  10.2.11  Changed order by on browse_queue.
||    Neil                   04-Apr-2006  10.2.10  Fixed updates of work count.
||    GJC                    13-Mar-2006  10.2.9   New version for AQ workflow rewrite
*/
@Service
@Transactional
public class TagWorkflowServiceImpl implements TagWorkflowService {
	private static final String RASSIGNTEAM = "RASSIGNTEAM";
	private static final String ASSIGNSTAFF = "ASSIGNSTAFF";
	private static final String TAGWFC = "TAGWFC";
	@Autowired
	private TagWorkflowRepository tagWorkflowRepository;

	private static Logger logger = LogManager.getLogger(TagWorkflowServiceImpl.class.getName());

	@Override
	public String insertCaseNote(final TagWorkflowBrowseQueue newMemoModel) {
		return tagWorkflowRepository.insertcaseNotes(newMemoModel);
	}

	@Override
	public Integer completeTask(final TagWorkflowBrowseQueue object, final String userName) {

		TagWfMessage lMessage = new TagWfMessage();
		final String lMessageHandle = null;
		Long lvEventId;
		try {
			lMessage = ackQueueMsgId(object.getQueueName(), object.getMsgId());

			lvEventId = tagWorkflowRepository.getEventIdFromOrders(lMessage.getOffenderBookId(),
					lMessage.getOriginalMsgId());
			if (lvEventId != null) {
				tagWorkflowRepository.updateOrderStatus(lMessage.getOffenderBookId(), lMessage.getOriginalMsgId(),
						userName);
			}

			lMessage.setCompleteReasonCode(object.getCompleteReasonode());
			lMessage.setCompleteCommentText(object.getCompleteCommentText());
			lMessage.setCompleteUserId(object.getCompleteUserId());
			lMessage.setAssignmentDate(new Date());
			lMessage.setTriggerReason("MANUALCOMP");

			sendMessage("TAGWF2.TAG_WF_AUTO_COMP", lMessage, lMessageHandle);
		} catch (Exception e) {
			logger.error("completeTask", e);
		}
		return 1;
	}

	@Override
	public TagWfMessage ackQueueMsgId(final String pQueueName, final String pMsgId) {
		final TagWfMessage lMessage = new TagWfMessage();
		if (lMessage.getTeamMemberId() != null && (lMessage.getWorkflowType()).toUpperCase().equals("TASK")
				&& lMessage.getTriggerReason() == null) {
			adjustTmNoOfTasks(lMessage.getTeamMemberId(), -1, lMessage.getModifyUserId());
		}
		return lMessage;

	}

	@Override
	public Integer assignToTeamMember(final TagWorkflowBrowseQueue object) {
		Integer returnValue = 0;
		TagWfMessage lMessage = new TagWfMessage();
		TeamMembers getTeamMemberdetails = new TeamMembers();
		final String lMessageHandle = null;
		Integer lTeamId = null;
		Integer lStaffId;
		try {
			getTeamMemberdetails = tagWorkflowRepository.fetchFromTeamMembers(object.getTeamMemberId().longValue());
			lTeamId = getTeamMemberdetails.getTeamId();
			lStaffId = (getTeamMemberdetails.getStaffId()).intValue();
			lMessage.setTriggerReason("ASSIGNSTAFF");
			lMessage.setStaffId(lStaffId);
			lMessage.setTeamId(lTeamId);
			lMessage.setTeamMemberId(object.getTeamMemberId().longValue());
			lMessage.setAssignmentDate(new Date());
			lMessage.setSpareNumber(null);
			lMessage.setCompleteCommentText(null);
			sendMessage(object.getQueueName(), lMessage, lMessageHandle);
			customAssignmentProcessing(lMessage);
			returnValue = 1;
		} catch (Exception e) {
			logger.error("assignToTeamMember", e);
		}
		return returnValue;
	}

	TagWfMessage getQueueMsgid(final String pQueueName, final String pMsgid, Boolean pRemoveMessage) {
		TagWfMessage lMessage = new TagWfMessage();
		if (pRemoveMessage == null) {
			pRemoveMessage = true;
		}
		if (pRemoveMessage) {
			lMessage = ackQueueMsgId(pQueueName, pMsgid);
		} else {
			lMessage = readQueueMsgId(pQueueName, pMsgid);
		}
		return lMessage;

	}

	@Override
	public void adjustTmNoOfTasks(final long teamMemberId, final Integer pAdjustment, final String userName) {
		tagWorkflowRepository.updateTeamMembersNoOfTasks(teamMemberId, pAdjustment, userName);
	}

	TagWfMessage readQueueMsgId(final String pQueueName, final String pMsgid) {
		final TagWfMessage lMessage = new TagWfMessage();
		return lMessage;
	}

	public Map<String, Object> sendMessage(final String pQueueName, final TagWfMessage pMessage,
			final String pMessageHandle) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		if (pMessage.getStaffId() != null) {
		} else if (pMessage.getTeamId() != null) {
		}
		if (pMessage.getSenderId() == null) {
			pMessage.setSenderId("USER");
		}
		returnMap.put("P_MESSAGE", pMessage);
		returnMap.put("P_MESSAGE_HANDLE", pMessageHandle);
		return returnMap;
	}

	private void customAssignmentProcessing(final TagWfMessage pMessage) {
		Date lvAssignmentDate = null;
		final Boolean checkCutWork = checkCustomWork(pMessage.getWorkId(), "CA", "PTR");
		if ("EXT_OWN_PTR".equals(pMessage.getWorkTrigger()) && checkCutWork) {

			if (pMessage.getStaffId() == null) {
				lvAssignmentDate = null;
			} else {
				lvAssignmentDate = pMessage.getAssignmentDate();
			}
			updatePtrAssignment(pMessage.getNoteSourceCode(), pMessage.getStaffId(), lvAssignmentDate,
					pMessage.getModifyUserId());
		}

	}

	private Boolean checkCustomWork(final Long workId, final String workType, final String workSubType) {
		Long lvWorkId = null;
		lvWorkId = tagWorkflowRepository.getWorkIdFromWorks(workId, workType, workSubType);
		if (lvWorkId != null) {
			return true;
		} else {
			return false;
		}

	}

	private void updatePtrAssignment(final String pPtrId, final Integer staffId, final Date lvAssignmentDate,
			final String userName) {
		tagWorkflowRepository.getPrtIdFromOffenderPtr(pPtrId);
		tagWorkflowRepository.updateOffenderPtr(pPtrId, staffId, lvAssignmentDate, userName);
	}

	@Override
	public Integer updateTeamMembersNoOfTasks(final long teamMemberId, final Integer pAdjustment,
			final String userName) {
		return tagWorkflowRepository.updateTeamMembersNoOfTasks(teamMemberId, pAdjustment, userName);
	}

	@Override
	public Boolean checkOutstandingTask(final Long offenderBookId) {
		final List<ReferenceCodes> ownerNameList = null; 
		String lSearchCondition = "tab.user_data.offender_book_id=" + offenderBookId
				+ "".concat(" AND tab.user_data.workflow_type='TASK' AND  tab.user_data.trigger_reason IS NULL");

		for (final ReferenceCodes referenceCodes : ownerNameList) {
			if (searchQueue(referenceCodes.getDescription(), lSearchCondition)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean searchQueue(final String queueName, final String searchCondition) {
		final Boolean retVal = false;
		return retVal;
	}

	@Override
	public Long insertWorkMessage(final MessageQueue bean) {
		final Message msg = new Message();
		if (msg.getTeamMemberId() != null) {
			tagWorkflowRepository.selectTeamMembers(bean);
		}
		msg.setTeamId(bean.getTeamId());
		if (bean.getTeamMemberId() != null) {
		}
		if (bean.getStaffId() != null) {
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
		return workFlowId;
	}

	@Override
	public Integer removeFromQueue(final TagWorkflowBrowseQueue object) {
		try {
			ackQueueMsgId(object.getQueueName(), object.getMsgId());
		} catch (Exception e) {
			logger.error("removeFromQueue", e);
		}
		return 1;
	}

	@Override
	public Integer reassignToTeam(final TagWorkflowBrowseQueue object) {
		int result = 0;
		try {
			Integer lNewTeamClusterId;
			String lNewTeamQueueName;
			TagWfMessage lMessage;
			final String lMessageHandle = null;
			lNewTeamClusterId = getCluster(object);
			lNewTeamQueueName = TAGWFC + lNewTeamClusterId + ".TAG_WF_GENERAL_C" + lNewTeamClusterId;
			final Integer te = object.getEnableOrDisable();
			final Boolean temp = (te == object.getEnableOrDisable());
			lMessage = getQueueMsgid(object.getQueueName(), object.getMsgId(), temp);
			lMessage.setTriggerReason(RASSIGNTEAM);
			lMessage.setTeamId(object.getAssignedTeamId());
			lMessage.setStaffId(null);
			lMessage.setTeamMemberId(null);
			lMessage.setAssignmentDate(new Date());
			lMessage.setSpareNumber(null);
			lMessage.setCompleteCommentText(null);

			sendMessage(lNewTeamQueueName, lMessage, lMessageHandle);
			customAssignmentProcessing(lMessage);
			result = 1;
		} catch (Exception e) {
			logger.error("reassignToTeam :" + e);
		}
		return result;
	}

	@Override
	public Integer getCluster(final TagWorkflowBrowseQueue searchRecord) {
		Integer lQueueClusterId = 0;
		if (searchRecord.getStaffId() != null) {
			lQueueClusterId = tagWorkflowRepository.getClusterStaffId(searchRecord.getStaffId());
		} else if (searchRecord.getTeamId() != null) {
			lQueueClusterId = tagWorkflowRepository.getClusterTeamId(searchRecord.getTeamId());
		}
		return lQueueClusterId;
	}

	@Override
	public Integer reassignToTeamMember(final TagWorkflowBrowseQueue object) {
		int result = 0;
		try {
			String lNewStaffQueueName;
			TagWfMessage lMessage;
			Integer lNewTeamClusterId;
			final String lMessageHandle = null;
			Integer lNewTeamId;
			Integer lNewStaffId;
			final TeamMembers obj = tagWorkflowRepository.reassignToTeamMemberSelect(object.getTeamMemberId());
			lNewTeamId = obj.getTeamId();
			lNewStaffId = obj.getStaffId().intValue();
			final TagWorkflowBrowseQueue obj1 = new TagWorkflowBrowseQueue();
			obj1.setTeamId(lNewTeamId);
			lNewTeamClusterId = getCluster(obj1);
			final Integer te = object.getEnableOrDisable();
			final Boolean temp = (te == object.getEnableOrDisable());
			lMessage = getQueueMsgid(object.getQueueName(), object.getMsgId(), temp);
			lMessage.setTriggerReason(ASSIGNSTAFF);
			lMessage.setStaffId(lNewStaffId);
			lMessage.setTeamId(lNewTeamId);
			lMessage.setTeamMemberId(object.getTeamMemberId().longValue());
			lMessage.setAssignmentDate(new Date());
			lNewStaffQueueName = TAGWFC + lNewTeamClusterId + ".TAG_WF_GENERAL_C" + lNewTeamClusterId;
			sendMessage(lNewStaffQueueName, lMessage, lMessageHandle);
			customAssignmentProcessing(lMessage);
			result = 1;
		} catch (Exception e) {
			logger.error("reassignToTeamMember :" + e);
		}
		return result;
	}

	@Override
	public void createWorkflow(final BigDecimal pKey, final String pContext, final Object pParams,
			final BigDecimal pOffenderBookId, final Date pEventDate, final Date pOverrideDueDate,
			final Integer pDueDatePeriod, final String user, String triggerNmae) {
		String pTriggerName = "REPORT_DONE";
		String lMessageHandle = null;
		TagWfMessage lMessage = new TagWfMessage();

		lMessage.setOffenderBookId(pOffenderBookId);
		lMessage.setOriginalMsgId(pKey);
		lMessage.setWorkTrigger(pTriggerName);
		lMessage.setWorkflowType(pContext);

		if (pParams != null) {
			lMessage.setMessageText((String) pParams);
		}

		lMessage.setSenderId(user);
		lMessage.setAssignmentDate(new Date());
		lMessage.setSpareDate(pEventDate != null ? pEventDate : new Date());
		lMessage.setDueDate(pEventDate);
		lMessage.setOverrideDueDate(pOverrideDueDate);
		lMessage.setDueDatePeriod(pDueDatePeriod);
		sendMessage(null, lMessage, lMessageHandle);
	}

	@Override
	public void createCaseNote(final BigDecimal pOffenderBookId, String pTriggerName, final Object pMessage,
			Long pEventId, final Date pEventDate, final String pNoteCSourceCode, String createUserId) {
		BigDecimal eventId = null;
		String pNoteSourceCode = "AUTO";
		String lMessageHandle = null;
		TagWfMessage lMessage = new TagWfMessage();

		lMessage.setOffenderBookId(pOffenderBookId);
		lMessage.setWorkTrigger(pTriggerName);
		lMessage.setSenderId(createUserId);
		lMessage.setTriggerReason("CASENOTE");
		lMessage.setWorkflowType("CASENOTE");
		lMessage.setAssignmentDate(new Date());
		lMessage.setSpareDate(pEventDate != null ? pEventDate : new Date());
		lMessage.setEventId(eventId);
		lMessage.setNoteSourceCode(pNoteSourceCode);
		lMessage.setMessageText((String) pMessage);

		sendMessage("TAGWF1.TAG_WF_ROUTER", lMessage, lMessageHandle);
	}

	@Override
	public void completeWorkflow(final String pTriggerName, final BigDecimal pKey, final String pContext,
			final Object pParams) {
		String lMessageHandle = null;
		TagWfMessage lMessage = new TagWfMessage();

		if (pKey == null && pParams == null) {
			/**
			 * Tag_Error.raise_app_error ( -20000, 'p_key and p_params cannot both be null',
			 * TRUE );
			 */
		}
		lMessage.setOriginalMsgId(pKey);
		lMessage.setWorkTrigger(pTriggerName);
		lMessage.setWorkflowType(pContext);
		lMessage.setAssignmentDate(new Date());

		if (pParams != null) {
			lMessage.setMessageText((String) pParams);
		}
		lMessage.setTriggerReason("AUTOCOMP");
		sendMessage("TAGWF2.TAG_WF_AUTO_COMP", lMessage, lMessageHandle);
	}

	@Override
	public void createEmailWorkflow(String pTriggerName, Object pParams, Long pOffenderBookId, Date pEventDate) {
	}

	@Override
	public Integer createAgencyWorkflow(CreateAgencyWorkflow createAgencyWorkflow) {
		return null;
	}
}