package net.syscon.s4.inst.careinplacement.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurations;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurationsCommitBean;

/**
 * Interface OimpldurService
 */
public interface OimpldurService {
	List<ReferenceCodes> placementDurPostQuery(ReferenceCodes paramBean);

	List<ReferenceCodes> rgDurationTypeRecordGroup();

	PlacementDurations placementDurCommit(PlacementDurationsCommitBean commitBean);

	List<PlacementDurations> placementDurExecuteQuery(PlacementDurations paramBean);

	List<ReferenceCodes> rgPlacementTypeRecordGroup();

}
