package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.AgencyInternalLocations;

public interface AgencyInternalLocationsT1Service {
	Boolean isChanged(String pOldVal, String pNewVal, String pActionCode);

	Integer AgencyInternalLocationsT1(AgencyInternalLocations oldRef, AgencyInternalLocations newRef,
			String lvActionCode);

}
