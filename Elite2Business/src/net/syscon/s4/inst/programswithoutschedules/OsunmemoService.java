package net.syscon.s4.inst.programswithoutschedules;
import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
/**
 * Interface OsunmemoService 
 */
public interface OsunmemoService  {
	String getDisplayAuto(final String offenderBookId);

	List<TeamMembers> rgTeamsRecordGroup(final String staffWorkAndObId);

	List<ReferenceCodes> rgWorksRecordGroup();

	List<StaffMembers> rgStaffRecordGroup(final String workAndObId);

	List<ReferenceCodes> rgSeverityRecordGroup();
	
	List<TagWorkflowBrowseQueue> submitAdhocWorkflow(final TagWorkflowBrowseQueue newMemoModel);

	Integer getTeamemberId(final String teamMemberId);


}
