package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OcuauthrService
 */
public interface OcuauthrService {
	List<TaskAssignmentHty> tskAssHtyExecuteQuery(final TaskAssignmentHty tskAsshty);

	List<ReferenceCodes> rgStaffNameRecordGroup(final String teamId);

}
