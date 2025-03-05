package net.syscon.s4.cm.teamsworkflow;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueueCommitBean;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Teams;

/**
 * Interface OcdaworkService
 * 
 */
public interface OcdaworkService {
	List<ReferenceCodes> rgSexRecordGroup();

	List<ReferenceCodes> rgWorkflowTypeRecordGroup();

	List<ReferenceCodes> rgRoleRecordGroup();

	List<ReferenceCodes> rgWorkTypeRecordGroup();

	List<Teams> rgTeamStaffRecordGroup(final String agylocId);

	Integer teamQueueCommit(TagWorkflowBrowseQueueCommitBean object);

	List<ReferenceCodes> rgWorkSubTypeRecordGroup();

	List<ReferenceCodes> rgPositionRecordGroup();

	List<TeamMembers> teamMembersExecuteQuery(TeamMembers object);

	Integer teamMembersCommit(TeamMembersCommitBean object);

	List<StaffMembers> rgTeamMembersRecordGroup();

	List<TagWorkflowBrowseQueue> teamQueueExecuteQuery(TagWorkflowBrowseQueue object);

	List<ReferenceCodes> rgReasonRecordGroup();

	Integer getCompleteTask(TagWorkflowBrowseQueue searchBean);

	List<StaffMembers> rgStaffSearchRecordGroup(Integer teamCode);
	


}
