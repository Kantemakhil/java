package net.syscon.s4.inst.workflow.managingteams;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VPimsNameSearch;
import net.syscon.s4.inst.workflow.managingteams.beans.TagWorkflowAdmQueryTeamTasks;

/**
 * Interface OcittaskRepository
 */
@Repository
public interface OcittaskRepository {
	Integer tasksUpdateTagWorkflowAdmQueryTeamTasks(List<TagWorkflowAdmQueryTeamTasks> lstTagWorkflowAdmQueryTeamTasks);

	List<TagWorkflowAdmQueryTeamTasks> tasksExecuteQuery(TagWorkflowAdmQueryTeamTasks objTagWorkflowAdmQueryTeamTasks);

	List<Teams> rgTeamRecordGroup(String user);

	Integer tasksDeleteTagWorkflowAdmQueryTeamTasks(List<TagWorkflowAdmQueryTeamTasks> lstTagWorkflowAdmQueryTeamTasks);

	List<ReferenceCodes> rgTaskSubTypeRecordGroup(String taskType);

	List<StaffMembers> rgStaffRecordGroup(Integer teamId);

	Teams rgTeamIdRecordGroup(String teamCode, String user);

	List<ReferenceCodes> rgTaskTypeRecordGroup();

	List<ReferenceCodes> rgCompleteStatusRecordGroup();

	Integer tasksInsertTagWorkflowAdmQueryTeamTasks(List<TagWorkflowAdmQueryTeamTasks> lstTagWorkflowAdmQueryTeamTasks);

	VPimsNameSearch getOffenderBookId(String offenderIdDisplay, String caseloadId);

}
