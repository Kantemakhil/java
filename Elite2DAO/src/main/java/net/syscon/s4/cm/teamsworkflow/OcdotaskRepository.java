package net.syscon.s4.cm.teamsworkflow;


import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;

import java.util.List;

/**
 * Interface OcdotaskRepository
 */
public interface OcdotaskRepository {
	List<StaffMembers> rgStaffRecordGroup(String teamId);

	List<ReferenceCodes> rgTaskTypeRecordGroup();

	List<StaffMembers> rgTeamRecordGroup();

	List<ReferenceCodes> rgCompleteRsnRecordGroup();

	List<ReferenceCodes> rgTaskSubTypeRecordGroup();

	List<TaskAssignmentHty> tasksExecuteQuery(TaskAssignmentHty obj);

}
