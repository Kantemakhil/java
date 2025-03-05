package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypesCommitBean;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.VReportingLocations;
import net.syscon.s4.inst.automatedcounts.beans.VReportingLocationsCommitBean;

/**
 * Interface OidverccService
 */
public interface OidverccService {
	Integer agencyCountTypesCommit(AgencyCountTypesCommitBean commitBean);

	List<AgencyCountTypes> agencyCountTypesExecuteQuery(AgencyCountTypes obj);

	List<VReportingLocations> reportingLocationsExecuteQuery(VReportingLocations obj);

	List<ReferenceCodes> cgfkCountTypesRecordGroup(String agyLocId);

	List<ReferenceCodes> cgfkAgyLocIdRecordGroup(String caseLoadId);

	List<AgencyCountTypes> cgfkScheduledTimeRecordGroup(String agyLocId, String countTypeCode);

	List<AgencyLocations> defaultAgyLoc(AgencyLocations paramBean);

	Integer reportingLocationsCommit(VReportingLocationsCommitBean commitBean);

	Integer updateAgencyLocationCounts(AgencyLocationCounts bean);
	
	public String getUserName(String userId);

}
