package net.syscon.s4.inst.workflow.managingteams;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.managingteams.beans.VOffenderTeamAssignHty;
/**
 * Interface OcuhvteaRepository
 */
public interface OcuhvteaRepository {
	List<VOffenderTeamAssignHty> offVteamHtyExecuteQuery(VOffenderTeamAssignHty objVOffenderTeamAssignHty) ;

	List<VOffenderTeamAssignHty> offBkgOnCheckDeleteMaster(VOffenderTeamAssignHty paramBean);

	List<ReferenceCodes> rgFunctionRecordGroup() ;

}
