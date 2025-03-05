package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;

/**
 * Interface OcuintlcRepository
 */
public interface OcuintlcRepository {
	List<AgencyInternalLocations> intLocExecuteQuery(AgencyInternalLocations object);

}
