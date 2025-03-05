package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.AgencyLocations;

public interface AgencyLocationsT1Service {
	
	Integer agencyLocationsT1Trigger(AgencyLocations oldRef, AgencyLocations newRef, String userId);
}
