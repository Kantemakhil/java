package net.syscon.s4.iwp;

import java.util.List;
import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;

/**
 * Interface OcuauthrRepository
 */
import net.syscon.s4.im.beans.Teams;

public interface OcuauthrRepository {
	List<TaskAssignmentHty> tskAssHtyExecuteQuery(final TaskAssignmentHty tskAsshty);

	List<ReferenceCodes> rgStaffNameRecordGroup(final String teamId);

	Teams teamCodeAndDescriptionPostquery(final Long teamId);

	StaffMembers lastNameAndfirstNamePostquery(final Long staffId);

}
