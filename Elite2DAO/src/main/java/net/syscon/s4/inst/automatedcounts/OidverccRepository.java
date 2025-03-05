package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.VReportingLocations;

/**
 * Interface OidverccRepository
 */
public interface OidverccRepository {
	List<AgencyCountTypes> agencyCountTypesExecuteQuery(AgencyCountTypes obj);

	Integer agencyCountTypesDeleteAgencyCountTypes(List<AgencyCountTypes> list);

	List<AgencyLocations> defaultAgyLocdefaultAgyLoc(AgencyLocations paramBean);

	Integer reportingLocationsDeleteVReportingLocations(List<VReportingLocations> list);

	List<VReportingLocations> reportingLocationsExecuteQuery(VReportingLocations obj);

	Integer agencyCountTypesUpdateAgencyCountTypes(List<AgencyCountTypes> list);

	List<ReferenceCodes> cgfkCountTypesRecordGroup(String agyLocId);

	Integer agencyCountTypesInsertAgencyCountTypes(List<AgencyCountTypes> list);

	List<ReferenceCodes> cgfkAgyLocIdRecordGroup(String caseLoadId);

	List<AgencyCountTypes> cgfkScheduledTimeRecordGroup(String agyLocId, String countTypeCode);

	Integer updateAgencyLocationCounts(AgencyLocationCounts bean);

}
