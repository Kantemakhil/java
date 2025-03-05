package net.syscon.s4.inst.movements.housingchanges;
import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.inst.movements.housingchanges.beans.VLivUnits;
/**
 * Interface OmuavlocRepository
 */
public interface OmuavlocRepository {
	List<VLivUnits> livUnitExecuteQuery(CaseloadAgencyLocations objVLivUnit);

}
