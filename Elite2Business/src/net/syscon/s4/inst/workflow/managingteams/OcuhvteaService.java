package net.syscon.s4.inst.workflow.managingteams;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.managingteams.beans.VOffenderTeamAssignHty;

/**
 * Interface OcuhvteaService
 */
public interface OcuhvteaService {
	List<VOffenderTeamAssignHty> offVteamHtyExecuteQuery(VOffenderTeamAssignHty object);

	List<ReferenceCodes> rgFunctionRecordGroup();

	List<VOffenderTeamAssignHty> offBkgOnCheckDeleteMaster(VOffenderTeamAssignHty paramBean);

}
