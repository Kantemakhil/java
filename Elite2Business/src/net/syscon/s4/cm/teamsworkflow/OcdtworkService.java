package net.syscon.s4.cm.teamsworkflow;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueueCommitBean;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembersCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;

public interface OcdtworkService {
	List<TeamMembers> teamMembersExecuteQuery(TeamMembers searchBean);

	Integer staffMemoQueueCommit(TagWorkflowBrowseQueueCommitBean CommitBean);

	List<AgencyLocations> cgfkCrtMvTmpAgyLocIdRecordGroup(String caseLoadId);

	List<ReferenceCodes> rgSexRecordGroup();

	Integer staffQueueCommit(TagWorkflowBrowseQueueCommitBean CommitBean);

	List<ReferenceCodes> rgRoleRecordGroup();

	List<ReferenceCodes> rgWorkTypeRecordGroup();

	List<Teams> rgTeamStaffRecordGroup();

	List<ReferenceCodes> rgCompletedRecordGroup();

	List<ReferenceCodes> rgWorkSubTypeRecordGroup();

	List<ReferenceCodes> rgPositionRecordGroup();

	List<StaffMembers> rgStaffSearchRecordGroup(String agylocId);

	List<TagWorkflowBrowseQueue> staffQueueExecuteQuery(TagWorkflowBrowseQueue objTagWorkflowBrowseQueue);

	Integer teamMembersCommit(TeamMembersCommitBean CommitBean);

	List<ReferenceCodes> rgReasonRecordGroup();

	List<TagWorkflowBrowseQueue> staffMemoQueueExecuteQuery(TagWorkflowBrowseQueue searchBean);

}
