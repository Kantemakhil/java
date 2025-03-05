package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.beans.VPrisonStatusCount;
import net.syscon.s4.inst.casemanagement.beans.VPrisonTotal;

public interface OiiiscouService {
	List<ReferenceCodes> CgfdgetVPrisnCtDrvImpris(ReferenceCodes paramBean);

	List<AgencyLocations> cgfkAgyLocAgyLocIdRecordGroup(String caseloadId);

	Integer vPrisnTotCommit(VPrisonTotal commitBean);

	AgencyLocations CgfkchkAgyLocAgyLocIdAg(AgencyLocations paramBean);

	List<VPrisonStatusCount> vPrisnCtExecuteQuery(String agyLocId);

	SystemProfiles GetProfileValue(SystemProfiles paramBean);

	Integer vPrisnCtCommit(VPrisonStatusCount CommitBean);

	List<VPrisonTotal> vPrisnTotExecuteQuery(String agyLocId);

}
