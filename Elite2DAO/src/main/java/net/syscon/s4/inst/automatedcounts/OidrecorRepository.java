package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.SalesMaintenances;

public interface OidrecorRepository {
	Integer agencyCountsDeleteAgencyCounts(List<AgencyCounts> lstAgencyCounts);

	List<ReferenceCodes> cgfkRecountRsnRecordGroup();

	Integer agencyCountsInsertAgencyCounts(List<AgencyCounts> lstAgencyCounts);

	Integer agencyCountsUpdateAgencyCounts(List<AgencyCounts> lstAgencyCounts);

	SalesMaintenances printList(SalesMaintenances paramBean);

	AgencyLocations defaultAgyLocDEFAULT_AGY_LOC(AgencyLocations paramBean);

	OmsModules createFormGlobalscreateFormGlobals(OmsModules paramBean);

	SystemProfiles printList(SystemProfiles paramBean);

	List<AgencyCounts> agencyCountsExecuteQuery(AgencyCounts objAgencyCounts);

	Integer agencyLocationCountsDeleteAgencyCounts(AgencyLocationCounts alcBean);

}
