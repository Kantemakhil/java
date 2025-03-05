package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;

/**
 * Interface OcuintlcService
 */
public interface OcuintlcService {
	List<AgencyInternalLocations> intLocExecuteQuery(AgencyInternalLocations object);

}
