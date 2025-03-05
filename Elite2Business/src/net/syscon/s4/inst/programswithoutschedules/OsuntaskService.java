package net.syscon.s4.inst.programswithoutschedules;
import java.util.List;
/**
 * Interface OsuntaskService 
 */

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
public interface OsuntaskService  {
	List<TeamMembers> rgTeamsRecordGroup(final String staffWorkAndObId) ;

	List<ReferenceCodes> rgWorksRecordGroup(String caseloadType) ;

	List<StaffMembers> rgStaffRecordGroup(final String workAndObId) ;

	Integer getTeamemberId(final String teamMemberId);

	List<TagWorkflowBrowseQueue> submitAdhocWorkflow(final TagWorkflowBrowseQueue newTaskModel);

}
