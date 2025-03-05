package net.syscon.s4.inst.workflow.managingteams;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasks;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasksCommitBean;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VPimsNameSearch;

/**
 * Interface OcittaskService
 */
public interface OcittaskService {
	List<TagWorkflowAdmQueryTeamTasks> tasksExecuteQuery(TagWorkflowAdmQueryTeamTasks objTagWorkflowAdmQueryTeamTasks);

	Integer tasksCommit(TagWorkflowAdmQueryTeamTasksCommitBean commitBean);

	List<Teams> rgTeamRecordGroup(String user);

	List<ReferenceCodes> rgTaskSubTypeRecordGroup(String taskType);

	List<StaffMembers> rgStaffRecordGroup(String teamCode, String user);

	Teams getTeamId(String teamCode, String user);

	List<ReferenceCodes> rgTaskTypeRecordGroup();

	List<ReferenceCodes> rgCompleteStatusRecordGroup();

	VPimsNameSearch getOffenderBookId(String offenderIdDisplay, String caseloadId);

}
