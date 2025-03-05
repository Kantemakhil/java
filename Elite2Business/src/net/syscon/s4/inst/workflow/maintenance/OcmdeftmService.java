package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctions;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctionsCommitBean;

/**
 * Interface OcmdeftmService
 */
public interface OcmdeftmService {
	List<AgencyLocations> rgAgyLocRecordGroup(String agencyLocationType, String caseloadId);

	List<ReferenceCodes> rgAgyLocTypeRecordGroup(String userId);

	List<AgyLocTeamFunctions> agyTmFnExecuteQuery(AgencyLocations object);

	List<ReferenceCodes> rgFunctionRecordGroup();

	Integer agyTmFnCommit(AgyLocTeamFunctionsCommitBean commitBean);

	List<ReferenceCodes> getWrittenFlagCodes();

}
