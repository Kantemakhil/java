package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.maintenance.beans.AgyLocTeamFunctions;

/**
 * Interface OcmdeftmRepository
 */
public interface OcmdeftmRepository {
	Integer agyTmFnInsertAgyLocTeamFunctions(List<AgyLocTeamFunctions> lstAgyLocTeamFunctions);

	List<AgencyLocations> rgAgyLocRecordGroup(String agencyLocationType, String caseloadId);

	List<ReferenceCodes> rgAgyLocTypeRecordGroup(String userId);

	List<AgyLocTeamFunctions> agyTmFnExecuteQuery(AgencyLocations objAgyLocTeamFunctions);

	List<ReferenceCodes> rgFunctionRecordGroup();

	Integer agyTmFnUpdateAgyLocTeamFunctions(List<AgyLocTeamFunctions> lstAgyLocTeamFunctions);

	String getTeamIdDescription(String teamId);

	String checkAgyLOcTeamExist(String agencyLocationID, String functionType);

	long getAgencyTeamID();
}