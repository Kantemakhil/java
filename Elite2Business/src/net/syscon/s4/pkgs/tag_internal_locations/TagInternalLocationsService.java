package net.syscon.s4.pkgs.tag_internal_locations;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;

public interface TagInternalLocationsService {
	List<AgencyInternalLocations> queryInternalLocations(final AgencyInternalLocations objSearchDao);

	 AgencyInternalLocations defaultLocationDescription(AgencyInternalLocations agyIntLoc);

	Integer getInternalLocationId();

	AgencyInternalLocations getInternalLocationRecord(final Integer internalLocId);

	Integer actDeactIntChildLocation(final AgencyInternalLocations offTrans);

	Integer updateRelatedTrackingFlags(final AgencyInternalLocations offTrans);
}