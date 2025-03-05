package net.syscon.s4.inst.careinplacement.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurations;

/**
 * Interface OimpldurRepository
 */
public interface OimpldurRepository {
	List<ReferenceCodes> rgDurationTypeRecordGroup();

	Integer placementDurUpdatePlacementDurations(List<PlacementDurations> paramBean);

	Integer placementDurInsertPlacementDurations(List<PlacementDurations> paramBean);

	Integer placementDurDeletePlacementDurations(List<PlacementDurations> paramBean);

	List<ReferenceCodes> placementDurPostQuery(ReferenceCodes paramBean);

	List<PlacementDurations> placementDurExecuteQuery(PlacementDurations paramBean);

	List<ReferenceCodes> rgPlacementTypeRecordGroup();

}
