package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountsCommitBean;

public interface OidrecorService {
	List<ReferenceCodes> cgfkRecountRsnRecordGroup();

	AgencyLocations DefaultAgyLoc(AgencyLocations paramBean);

	Integer agencyCountsCommit(AgencyCountsCommitBean commitBean);

	SystemProfiles PrintList(SystemProfiles paramBean);

	OmsModules CreateFormGlobals(OmsModules paramBean);

	List<AgencyCounts> agencyCountsExecuteQuery(AgencyCounts objAgencyCounts);

}
