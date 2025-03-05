package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctions;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;

public interface OffenderBookingsT7Repository {
	OffenderBookings getOffenderBookings(Long offenderBookId);

	List<AgyLocTeamFunctions> getAgyLocTeamFunsCur(String agyLocId);

	Long isOffTeamAssignExists(Long offenderBookId, String functionType);

	Integer insOffenderTeamAssignments(OffenderTeamAssignments offenTeamAssign);

	Integer offenderTeamAssignmentsDelete(OffenderTeamAssignments offenderTeamAssi);
}
