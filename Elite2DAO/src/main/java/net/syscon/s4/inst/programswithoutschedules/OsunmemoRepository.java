package net.syscon.s4.inst.programswithoutschedules;
import java.util.List;
/**
 * Interface OsunmemoRepository
 */

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
public interface OsunmemoRepository {

	String getDisplayAuto(final String offenderBookId);


	List<TeamMembers> rgTeamsRecordGroup(final String workId,final String offenderBookId,final String staffId) ;

	List<ReferenceCodes> rgWorksRecordGroup() ;

	List<StaffMembers>rgStaffRecordGroup(final String workId,final String offenderBookId) ;

	List<ReferenceCodes> rgSeverityRecordGroup() ;
	
	String submitAdhocWorkflow(final TagWorkflowBrowseQueue newMemoModel);
	

	Integer getTeamemberId(final String string, final String string2);

	String getSubString(final String offName,final String messageText);

}
