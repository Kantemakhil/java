package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.beans.VPrisonStatusCount;
import net.syscon.s4.inst.casemanagement.beans.VPrisonTotal;

public interface OiiiscouRepository {
	AgencyLocations cgfkchkAgyLocAgyLocIdAg(AgencyLocations paramBean);

	List<ReferenceCodes> cgfdgetVPrisnCtDrvImpris(ReferenceCodes paramBean);

	SystemProfiles getProfileValue(SystemProfiles paramBean);

	List<AgencyLocations> cgfkAgyLocAgyLocIdRecordGroup(String caseloadId);

	List<VPrisonStatusCount> vPrisnCtExecuteQuery(String agyLocId);

	List<VPrisonTotal> vPrisnTotExecuteQuery(String agyLocId);

}
