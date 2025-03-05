package net.syscon.s4.pkgs.tag_internal_locations;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;

public interface TagInternalLocationsRepository {
	List<AgencyInternalLocations> queryOne(final AgencyInternalLocations bean);

	List<AgencyInternalLocations> queryTwo(final String pAgyLocId, final String pShowLivingUnits);

	List<AgencyInternalLocations> queryThree(final AgencyInternalLocations bean);

	String getDefaultLocationDesc(final String internalLocCode, final Integer parentInternalLocId);

	Integer getInternalLocationId();

	AgencyInternalLocations getInternalLocationRecord(final Integer internalLocId);

	Integer agencyInternalLocations(final AgencyInternalLocations bean);

	Integer agencyInternalLocationsUpdate(final AgencyInternalLocations bean);

	Integer updateRelatedTrackingFlagsUpdate(final AgencyInternalLocations bean);
	
	AgencyInternalLocations getOldRecordAgencyIntLoc(Integer internalLocationId);
}