package net.syscon.s4.inst.workflow.managingteams;

import java.util.List;

import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;

/**
 * Interface OcdvteamRepository
 */
public interface OcdvteamRepository {
	OffenderTeamAssignments offBkgOnCheckDeleteMaster(OffenderTeamAssignments paramBean);

	Integer offTeamAssignUpdateOffenderTeamAssignments(List<OffenderTeamAssignments> list);

	List<ReferenceCodes> rgFunctionRecordGroup();

	List<OffenderTeamAssignments> offTeamAssignExecuteQuery(OffenderTeamAssignments object);

	OffenderTeamAssignments offTeamAssignInsertOffenderTeamAssignments(
			List<OffenderTeamAssignments> list);

	String getTeamIdDescription(String teamId);

	OffenderTeamAssignments deleteOffenderAssignment(OffenderTeamAssignments objSearchDao);

	OffenderTeamAssignments getTeamDetails(OffenderTeamAssignments searchbean);

	OffenderTeamAssignments getTeamDesc(OffenderTeamAssignments searchbean);

}
