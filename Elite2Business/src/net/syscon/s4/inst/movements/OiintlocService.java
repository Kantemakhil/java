package net.syscon.s4.inst.movements;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.beans.VIntLocUsageLocations;

/**
 * Interface OiintlocService
 */
public interface OiintlocService {

	List<VIntLocUsageLocations> intLocExecuteQuery(VIntLocUsageLocations objVIntLocUsageLocations);

	List<ReferenceCodes> rgUsagesRecordGroup();

}
