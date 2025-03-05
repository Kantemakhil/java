package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.VLivingUnitSummaries;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Interface OiiprollRepository
 */
public interface OiiprollRepository {
	AgencyLocations oiiprollWhenNewFormInstance(AgencyLocations paramBean);

	List<VIntLocSummaries> itLcSmExecuteQuery(VIntLocSummaries objVIntLocSummaries, String type);

	List<AgencyLocations> rgAgyLocRecordGroup(String caseloadId);

	List<VLivingUnitSummaries> lvUntSmExecuteQuery(VLivingUnitSummaries objVLivingUnitSummaries, String type);

	VLivingUnitSummaries lvUntSmTotalCount(VLivingUnitSummaries objSearchDao, String type);

	VIntLocSummaries itLcSmTotalCount(VIntLocSummaries objSearchDao, String type);

}
