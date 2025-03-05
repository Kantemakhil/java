package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;

/**
 * Interface OiddisreRepository
 */
public interface OiddisreRepository {
	List<ReferenceCodes> cgfkDiscrepRsnRecordGroup();

	List<AgencyLocations> defaultAgyLoc(AgencyLocations paramBean);

	Integer agencyCountsUpdateAgencyCounts(AgencyCounts object);

	Integer agencyCountsDeleteAgencyCounts(AgencyCounts object);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<AgencyCounts> agencyCountsExecuteQuery(AgencyCounts objAgencyCounts);

	Integer cancelCountProcedure(AgencyCounts commitBean);

}
