
package net.syscon.s4.pkgs.tag_workflow_adm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.legals.au.VCbSentTerms;
import net.syscon.s4.inst.legals.beans.AssignReport;
import net.syscon.s4.inst.legals.beans.CourtReport;
import net.syscon.s4.inst.legalscreens.sentenceadministration.beans.VCbCustodyPeriod;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasks;
import net.syscon.s4.pkgs.Message;
import net.syscon.s4.pkgs.MessageQueue;

public interface TagWorkflowAdmService {
	Offenders getOffDetailsTrans(final Long offenderBookId);

	Offenders getOffDetailsWait(final BigDecimal rootOffenderId);

	String staffMessageBulk(final StaffMembers newMemoModel, final Integer[] teamArray, final Integer[] staffArray);

	Long insertWorkMessage(final MessageQueue bean);

	void createStaffMessage(final Message msg);

	Integer defaultTeam(final BigDecimal pStaffId);

	List<AssignReport> getReportDetailsNew(final BigDecimal offBookId, final Long workFlowId);

	TagWorkflowBrowseQueue getOffenderDetails(final Long pOffenderBookId,String user);

	Map<String, Object> defaultTeam(final Integer pStaffId);

	Integer createTeamAssignHtyNew(final CourtReport courtReport);

	String validateStaffMessage(final StaffMembers newMemoModel);

	TagWorkflowBrowseQueue getWorkDetails(final Long pWorkId);

	String getAccessLevel(final String userName);

	Map<String, Object> getTeamDesc(final BigDecimal teamId);

	String assignOffender(final Long offenderBookId, final Integer nbtTeamId, final String userId);

	OffenderTeamAssignments deleteOffVteamDtls(final OffenderTeamAssignments objSearchDao);

	OffenderTeamAssignments getTeamDetails(final OffenderTeamAssignments searchbean);

	OffenderTeamAssignments getTeamDesc(final OffenderTeamAssignments searchbean);

	Integer returnAckReceipt(final TagWorkflowBrowseQueue offTrans);

	List<TagWorkflowAdmQueryTeamTasks> queryTeamTasks(final TagWorkflowAdmQueryTeamTasks objSearchDao);

	List<TaskAssignmentHty> queryOffenderTasks(final TaskAssignmentHty objSearchDao);
}