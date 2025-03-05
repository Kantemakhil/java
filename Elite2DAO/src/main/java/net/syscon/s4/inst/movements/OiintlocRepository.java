package net.syscon.s4.inst.movements;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.beans.VIntLocUsageLocations;

/**
 * Interface OiintlocRepository
 */
public interface OiintlocRepository {
	List<VIntLocUsageLocations> intLocExecuteQuery(VIntLocUsageLocations objVIntLocUsageLocations);

	Integer intLocUpdateVIntLocUsageLocations(List<VIntLocUsageLocations> lstVIntLocUsageLocations);

	List<ReferenceCodes> rgUsagesRecordGroup();

}
