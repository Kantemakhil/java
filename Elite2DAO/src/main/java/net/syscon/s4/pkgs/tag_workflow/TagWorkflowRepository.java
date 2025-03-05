package net.syscon.s4.pkgs.tag_workflow;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.pkgs.MessageQueue;

public interface TagWorkflowRepository {
	String insertcaseNotes(final TagWorkflowBrowseQueue newMemoModel);

	Long getEventIdFromOrders(BigDecimal offenderBookId, BigDecimal originalMsgId);

	Integer updateOrderStatus(BigDecimal offenderBookId, BigDecimal originalMsgId, final String userName);

	Integer updateTeamMembersNoOfTasks(long teamMemberId, Integer pAdjustment, final String userName);

	TeamMembers fetchFromTeamMembers(Long pTeamMemberId);

	Integer updateTeamMembersNoOfTasks(Long teamMemberId, Integer pAdjustment, final String userName);

	Long getWorkIdFromWorks(Long workId, String workType, String workSubType);

	Long getPrtIdFromOffenderPtr(String pPtrId);

	Integer updateOffenderPtr(String pPtrId, Integer staffId, Date lvAssignmentDate, final String userName);

	List<ReferenceCodes> cQueuesCursor();

	Long selectTeamMembers(MessageQueue bean);

	public Integer getClusterStaffId(final Integer staffId);

	public Integer getClusterTeamId(final Integer teamId);

	public TeamMembers reassignToTeamMemberSelect(final Integer teamMemberId);

}