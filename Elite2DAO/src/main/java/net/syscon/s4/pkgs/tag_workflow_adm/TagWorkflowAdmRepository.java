package net.syscon.s4.pkgs.tag_workflow_adm;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.au.VCbSentTerms;
import net.syscon.s4.inst.legals.beans.AssignReport;
import net.syscon.s4.inst.legals.beans.Terms;
import net.syscon.s4.inst.legalscreens.sentenceadministration.beans.VCbCustodyPeriod;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasks;
import net.syscon.s4.pkgs.MessageQueue;

public interface TagWorkflowAdmRepository {
	Object[] getOffDetailsTrans(Long offenderBookId);

	Object[] getOffDetailsWait(BigDecimal rootOffenderId);

	public Long selectTeamMembers(MessageQueue bean);

	public Integer teamCountCur(final BigDecimal pStaffId);

	public Integer getDefaultTTeamId(final BigDecimal pStaffId);

	List<AssignReport> getReportDetailsNew(BigDecimal offBookId, Long workFlowId);

	public TagWorkflowBrowseQueue getOffenderDetails(final Long pOffenderBookId,String userId);

	Integer teamCntCur(Integer pStaffId);

	Integer teamCur(Integer pStaffId);

	BigDecimal getWorkId(String task, String report, String crtrepreq);

	void createTeamAssignHtyNewUpdate(final BigDecimal pOffenderBookId, final BigDecimal pOrderId,
			final Long workflowId, final String userName);
	
	Orders getOrdersOldRecord(final Integer pOrderId);

	String getWorkExistsCur(Long pWorkId);

	List<VCbSentTerms> coCursor();

	List<VCbCustodyPeriod> coCursorOne();

	public TagWorkflowBrowseQueue getWorkCur(final Long pWorkId);

	String caseloadTypeCur(final String caseloadId);

	public OffenderTeamAssignments deleteOffVteamDtls(final OffenderTeamAssignments objSearchDao);

	List<Terms> cTeamDesc(final BigDecimal teamId);

	Integer assigmentCur(final Long offenderBookId);

	Integer offenderTeamAssignments(final Long offenderBookId);

	Integer offenderTeamAssignmentsInsert(final Long offenderBookId, final Integer teamId, final String userName);

	public Teams getTeamDetails(final OffenderTeamAssignments searchbean);

	public Teams getTeamDesc(final BigDecimal pTeamId);

	Long getAckWorkIdCur(String workType, String workSubType);

	Long lvOriginatorStaffId(String pOriginalSenderId);

	List<TagWorkflowAdmQueryTeamTasks> queryTeamTasks(final TagWorkflowAdmQueryTeamTasks objSearchDao);

	List<TaskAssignmentHty> queryOffenderTasks(final TaskAssignmentHty bean);
	
	OffenderTeamAssignments getOldRecOffenderTeamAss(final OffenderTeamAssignments objSearchDao);

}