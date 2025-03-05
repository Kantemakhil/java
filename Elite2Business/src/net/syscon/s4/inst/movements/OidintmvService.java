package net.syscon.s4.inst.movements;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Interface OidintmvService
 */
public interface OidintmvService {
	List<LivingUnits> rgFromHlocLevelTwoRecordGroup(String agyLocId, String fromLocLevelOne);

	List<AgencyLocations> rgEstablishmentRecordGroup(String caseLoadId);

	List<LivingUnits> rgFromHlocLevelThreeRecordGroup(String agyLocId, String fromLocLevelTwo);

	List<VIntLocSummaries> rgToIlocLevelOneRecordGroup(String agyLocId, String fromILocLevelOneId,
			String fromHLocLevelOne);

	List<VOffenderAllSchedules> offBlkExecuteQuery(VOffenderAllSchedules objOidintmvGetSchedules);

	List<VIntLocSummaries> rgFromIlocLevelOneRecordGroup(String agyLocId);

	List<VIntLocSummaries> rgToIlocLevelTwoRecordGroup(String agyLocId, String toILocLevelOneId);

	List<SystemProfiles> GetProfileValue(SystemProfiles paramBean);

	List<ReferenceCodes> rgSchReasonRecordGroup();

	List<VOffenderAllSchedules> offBlkCommit(VOffenderAllSchedulesCommitBean CommitBean);

	List<VIntLocSummaries> rgToIlocLevelThreeRecordGroup(String agyLocId, String toILocLevelTwoId);

	List<VIntLocSummaries> rgFromIlocLevelTwoRecordGroup(String agyLocId, String fromILocLevelOneId);

	List<LivingUnits> rgFromHlocLevelOneRecordGroup(String agyLocId);

	VHeaderBlock getOffenderDetails(VHeaderBlock paramBean);

	List<VIntLocSummaries> rgFromIlocLevelThreeRecordGroup(String agyLocId, String fromILocLevelOneId);

	List<ReferenceCodes> rgSchTypeRecordGroup();

	LivingUnits getLabels(String agyLocId);

	Integer isOffenderExists(String offIdDisplay);

	List<ReferenceCodes> rgMovmentTypeRecordGroup();

}
