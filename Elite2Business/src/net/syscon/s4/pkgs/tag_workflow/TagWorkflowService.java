package net.syscon.s4.pkgs.tag_workflow;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.pkgs.MessageQueue;
import net.syscon.s4.pkgs.TagWfMessage;
import net.syscon.s4.triggers.CreateAgencyWorkflow;

public interface TagWorkflowService {
	String insertCaseNote(final TagWorkflowBrowseQueue newMemoModel);

	Integer completeTask(final TagWorkflowBrowseQueue object, final String userName);

	Map<String, Object> sendMessage(final String pQueueName, final net.syscon.s4.pkgs.TagWfMessage pMessage,
			final String lMessageHandle);
	Integer assignToTeamMember(final TagWorkflowBrowseQueue object);

	Integer updateTeamMembersNoOfTasks(final long teamMemberId, final Integer pAdjustment, final String userName);

	Boolean checkOutstandingTask(final Long offenderBookId);

	Boolean searchQueue(final String queueName, final String searchCondition);

	Long insertWorkMessage(final MessageQueue bean);

	TagWfMessage ackQueueMsgId(final String pQueueName, final String pMsgId);

	void adjustTmNoOfTasks(final long teamMemberId, final Integer pAdjustment, final String userName);

	Integer removeFromQueue(final TagWorkflowBrowseQueue object);

	Integer reassignToTeam(final TagWorkflowBrowseQueue object);

	Integer getCluster(final TagWorkflowBrowseQueue searchRecord);

	Integer reassignToTeamMember(final TagWorkflowBrowseQueue object);

	void createWorkflow(final BigDecimal pKey, final String pContext, final Object pParams,
			final BigDecimal pOffenderBookId, final Date pEventDate, final Date pOverrideDueDate,
			final Integer pDueDatePeriod, final String user, String triggerNmae);

	void createCaseNote(final BigDecimal pOffenderBookId, String triggerNmae, final Object pMessage, Long pEventId,
			final Date pEventDate, final String pNoteCSourceCode, String createUserId);

	void completeWorkflow(final String pTriggerName, final BigDecimal pKey, final String pContext,
			final Object pParams);

	void createEmailWorkflow(String pTriggerName,Object pParams,Long pOffenderBookId,Date pEventDate);

	Integer createAgencyWorkflow(CreateAgencyWorkflow createAgencyWorkflow);
}