package net.syscon.s4.cm.teamsworkflow;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Teams;

/**
 * Interface OcdaworkRepository
 */
public interface OcdaworkRepository {
	List<ReferenceCodes> rgSexRecordGroup();

	List<ReferenceCodes> rgWorkflowTypeRecordGroup();

	List<ReferenceCodes> rgRoleRecordGroup();

	List<ReferenceCodes> rgWorkTypeRecordGroup();

	List<Teams> rgTeamStaffRecordGroup(final String agylocId);

	List<ReferenceCodes> rgWorkSubTypeRecordGroup();

	List<ReferenceCodes> rgPositionRecordGroup();

	List<TeamMembers> teamMembersExecuteQuery(TeamMembers object);

	Integer teamMembersUpdateQuery(List<TeamMembers> teamMembersList);

	List<StaffMembers> rgTeamMembersRecordGroup();

	List<TagWorkflowBrowseQueue> teamQueueExecuteQuery(TagWorkflowBrowseQueue object);

	Integer teamQueueUpdateTagWorkflowBrowseQueue(List<TagWorkflowBrowseQueue> object);

	List<ReferenceCodes> rgReasonRecordGroup();

	TagWorkflowBrowseQueue getOffenderDetails(Integer offenderBookId);

	TagWorkflowBrowseQueue getWorkDetails(Integer workId);

	Integer getClusterFunctionStaffId(TagWorkflowBrowseQueue searchRecord);

	Integer getClusterFunctionTeamId(TagWorkflowBrowseQueue searchRecord);

	Integer getStaffId();

	Integer getCompleteTask(TagWorkflowBrowseQueue object);

	Integer getRemoveFromQueue(TagWorkflowBrowseQueue object);

	Integer getReassigntoTeam(TagWorkflowBrowseQueue object);

	Integer getUpdateOrderStatus(TagWorkflowBrowseQueue object);

	Integer getAssigntoTeamMember(TagWorkflowBrowseQueue object);
	
	Integer assignTaskToTeam(TagWorkflowBrowseQueue object);
	
	List<TagWorkflowBrowseQueue> getTaskDetails(TagWorkflowBrowseQueue object);
	
	Integer reassignTeamToTask(TagWorkflowBrowseQueue object);

	Integer CompleteTask(TagWorkflowBrowseQueue object);
	
	List<TagWorkflowBrowseQueue> getTaskDetails(Integer taskId,String userName);
	
	List<StaffMembers> rgStaffSearchRecordGroup(Integer teamId);

}
