package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.VLivingUnitSummaries;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Interface OiiprollService
 */
public interface OiiprollService {

	List<VIntLocSummaries> itLcSmExecuteQuery(VIntLocSummaries objVIntLocSummaries, String type);

	List<AgencyLocations> rgAgyLocRecordGroup(String caseloadId);

	List<VLivingUnitSummaries> lvUntSmExecuteQuery(VLivingUnitSummaries objVLivingUnitSummaries, String type);

	AgencyLocations oiiprollWhenNewFormInstance(AgencyLocations paramBean);

	VLivingUnitSummaries lvUntSmTotalCount(VLivingUnitSummaries searchRecord, String type);

	VIntLocSummaries itLcSmTotalCount(VIntLocSummaries objSearchDao, String type);

}
