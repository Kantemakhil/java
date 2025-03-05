package net.syscon.s4.cm.teamsworkflow;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;

public interface OcdtworkRepository {
	List<TeamMembers> teamMembersExecuteQuery(TeamMembers parmBean);

	List<AgencyLocations> cgfkCrtMvTmpAgyLocIdRecordGroup(String caseLoadId);

	List<ReferenceCodes> rgSexRecordGroup();

	List<AgencyLocations> defaultAgyrsn(AgencyLocations paramBean);

	Integer staffQueueUpdateTagWorkflowBrowseQueue(List<TagWorkflowBrowseQueue> lstTagWorkflowBrowseQueue);

	List<ReferenceCodes> rgRoleRecordGroup();

	List<ReferenceCodes> rgWorkTypeRecordGroup();

	List<Teams> rgTeamStaffRecordGroup();

	List<ReferenceCodes> rgCompletedRecordGroup();

	Integer teamMembersUpdate(List<TeamMembers> objSearchDao);

	List<ReferenceCodes> rgWorkSubTypeRecordGroup();

	List<ReferenceCodes> rgPositionRecordGroup();

	List<StaffMembers> rgStaffSearchRecordGroup(String agylocId);

	List<TagWorkflowBrowseQueue> staffQueueExecuteQuery(TagWorkflowBrowseQueue objTagWorkflowBrowseQueue);

	List<ReferenceCodes> rgReasonRecordGroup();

	List<TagWorkflowBrowseQueue> staffMemoQueueExecuteQuery(TagWorkflowBrowseQueue searchRecord);

	TagWorkflowBrowseQueue getOffenderDetails(Integer offenderBookId,String userName);

	TagWorkflowBrowseQueue getWorkDetails(Integer workId);

	Integer reassigntoTeam(TagWorkflowBrowseQueue obj);

	Integer reAssigntoTeamMember(TagWorkflowBrowseQueue obj);

	Integer removeFromQueue(TagWorkflowBrowseQueue obj);

	Integer completeTask(TagWorkflowBrowseQueue obj);

}
