package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.AgencyLocations;

public interface AgencyLocationAuthoritiesT1Service {
	Integer agencyLocationAuthoritiesT1Trigger(AgencyLocations oldRef, AgencyLocations newRef, String operationType);
}
