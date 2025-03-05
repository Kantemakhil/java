package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;

/**
 * Interface OiddisreService
 */
public interface OiddisreService {
	List<AgencyLocations> DefaultAgyLoc(AgencyLocations object);

	List<ReferenceCodes> cgfkDiscrepRsnRecordGroup();

	List<OmsModules> CreateFormGlobals(OmsModules object);

	Integer agencyCountsCommit(AgencyCounts object);

	List<AgencyCounts> agencyCountsExecuteQuery(AgencyCounts object);

}
