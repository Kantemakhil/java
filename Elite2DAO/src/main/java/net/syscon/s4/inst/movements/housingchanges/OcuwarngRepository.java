package net.syscon.s4.inst.movements.housingchanges;

import java.util.List;

/**
 * Interface OcuwarngRepository
 */
public interface OcuwarngRepository {
	

	List<String> allowOverride(String userName);

}
