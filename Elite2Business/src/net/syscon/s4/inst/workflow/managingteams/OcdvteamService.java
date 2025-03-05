package net.syscon.s4.inst.workflow.managingteams;

import java.util.List;

import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignmentsCommitBean;

/**
 * Interface OcdvteamService
 */
public interface OcdvteamService {
	List<OffenderTeamAssignments> offTeamAssignCommit(OffenderTeamAssignmentsCommitBean commitBean);

	List<ReferenceCodes> rgFunctionRecordGroup();

	List<OffenderTeamAssignments> offBkgOnCheckDeleteMaster(OffenderTeamAssignments paramBean);

	List<OffenderTeamAssignments> offTeamAssignExecuteQuery(OffenderTeamAssignments object);

	OffenderTeamAssignments getTeamDetails(OffenderTeamAssignments searchBean);

}
