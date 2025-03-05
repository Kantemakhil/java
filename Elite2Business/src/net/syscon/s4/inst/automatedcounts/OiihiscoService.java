package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocationCounts;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;

/**
 * Interface OiihiscoService
 */
public interface OiihiscoService {
	List<AgencyCountTypes> cgfkSchTimeRecordGroup(String countTypeCode, String agylocId);

	List<AgencyLocations> cgfkAgyLocIdRecordGroup(String caseloadId);

	List<AgencyLocationCounts> agencyLocationCountsExecuteQuery(AgencyLocationCounts searchBean);

	List<ReferenceCodes> cgfkCountTypesRecordGroup(String location);

	List<AgencyCounts> agencyCountsExecuteQuery(AgencyCounts searchBean);

}
