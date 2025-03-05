package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;

/**
 * Interface OumagyrgRepository
 */
public interface OumagyrgRepository {
	List<Areas> nomsRegionRgRecordGroup();

	List<ReferenceCodes> geographicRegionRgRecordGroup();

	List<Areas> subAreaRgRecordGroup(final String agencyLocationType, final String areaCode);

	List<Areas> areaRgRecordGroup(final String string, final String string2);

	List<ReferenceCodes> justiceAreaRgRecordGroup();

	List<AgencyLocations> agyLocExecuteQuery(final AgencyLocations objAgencyLocations);

	Integer agyLocUpdateAgencyLocations(final List<AgencyLocations> lstAgencyLocations);

	List<ReferenceCodes> agencyLocationTypeRgRecordGroup();
	
	List<Areas> subAreaRgRecordGroupTot();
	
	List<Areas> areaRgRecordGroupTot();

}
