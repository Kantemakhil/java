package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyLocationsCommitBean;
import net.syscon.s4.im.beans.Areas;

/**
 * Interface OumagyrgService
 */
public interface OumagyrgService {
	List<Areas> nomsRegionRgRecordGroup();

	List<ReferenceCodes> geographicRegionRgRecordGroup();

	List<Areas> subAreaRgRecordGroup(final String subAreaType);

	List<Areas> areaRgRecordGroup(final String agyLocType);

	List<ReferenceCodes> justiceAreaRgRecordGroup();

	List<AgencyLocations> agyLocExecuteQuery(final AgencyLocations objAgencyLocations);

	Integer agyLocCommit(final AgencyLocationsCommitBean commitBean);

	List<ReferenceCodes> agencyLocationTypeRgRecordGroup();
	
	List subAreaRgRecordGroupTot();
	
	List areaRgRecordGroupTot();

}
