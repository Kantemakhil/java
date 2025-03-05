package net.syscon.s4.inst.programswithoutschedules;
import java.util.List;
/**
 * Interface OsuntaskRepository
 */

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
public interface OsuntaskRepository {
	

	List<TeamMembers> validateAdhocWorkflow(TeamMembers paramBean);

	List<TeamMembers> rgTeamsRecordGroup(final String workId, final String offenderBookId, final String staffId) ;

	List<ReferenceCodes> rgWorksRecordGroup(String caseloadId) ;

	List<StaffMembers> rgStaffRecordGroup(final String workId, final String offenderBookId) ;

	Integer getTeamemberId(String teamId, String staffId);

	String getSubString(String offName, String messageText);

	String submitAdhocWorkflow(TagWorkflowBrowseQueue newTaskModel);
	
Integer submitTask(TagWorkflowBrowseQueue newTaskModel);
	
	Long getTaskId();
	
	String getUserId(Integer staffId);
	
	String getTeamCode(Integer teamId);
	
	Integer getTeamemberId(Integer teamId, Integer staffId);

}
