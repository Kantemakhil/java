package net.syscon.s4.inst.movements;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.movements.beans.OffenderInterMvmtLocations;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;
import net.syscon.s4.inst.movements.beans.VOffenderBookings;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Interface OidintmvRepository
 */
public interface OidintmvRepository {
	List<LivingUnits> rgFromHlocLevelTwoRecordGroup(String agyLocId, String fromLocLevelOne);

	List<AgencyLocations> rgEstablishmentRecordGroup(String caseLoadId);

	VHeaderBlock getOffenderDetails(VHeaderBlock paramBean);

	List<LivingUnits> rgFromHlocLevelThreeRecordGroup(String agyLocId, String fromLocLevelTwo);

	List<VIntLocSummaries> rgToIlocLevelOneRecordGroup(final String agyLocId, final String fromILocLevelOneId,
			final String fromHLocLevelOne);

	Integer offBlkInsertOidintmvGetSchedules(List<OffenderInterMvmtLocations> lstOidintmvGetSchedules);

	List<VOffenderAllSchedules> offBlkExecuteQuery(VOffenderAllSchedules objOidintmvGetSchedules);

	List<VIntLocSummaries> rgFromIlocLevelOneRecordGroup(String agyLocId);

	List<VIntLocSummaries> rgToIlocLevelTwoRecordGroup(String agyLocId, String toILocLevelOneId);

	List<ReferenceCodes> rgSchReasonRecordGroup();

	SystemProfiles getProfileValue(SystemProfiles paramBean);

	Integer offBlkUpdateOidintmvGetSchedules(OffenderInterMvmtLocations lstOidintmvGetSchedules);

	List<VIntLocSummaries> rgToIlocLevelThreeRecordGroup(String agyLocId, String toILocLevelTwoId);

	List<VIntLocSummaries> rgFromIlocLevelTwoRecordGroup(String agyLocId, String fromILocLevelOneId);

	List<LivingUnits> rgFromHlocLevelOneRecordGroup(String agyLocId);

	List<VIntLocSummaries> rgFromIlocLevelThreeRecordGroup(String agyLocId, String fromILocLevelTwoId);

	List<ReferenceCodes> rgSchTypeRecordGroup();

	List<VOffenderBookings> getUnSchedules(VOffenderAllSchedules objSearchDao);

	List<VOffenderAllSchedules> getSchedules(VOffenderAllSchedules objSearchDao);

	String getHousingLocationDesc(String livingUnitId);

	BigDecimal getEventIdCur(VOffenderAllSchedules vAllBean);

	Long getNxtOffenderIml();

	LivingUnits getLabels(String agyLocId);

	Integer isOffenderExists(String offIdDisplay);

	String chkNaIntLocConflict(VOffenderAllSchedules pSchTab);

	String getInternalLocDesc(String agencyImlId);

	String getNsType(BigDecimal agencyImlId, BigDecimal bigDecimal);

	List<BigDecimal> getparentLocationCurserList(BigDecimal valueOf);

	List<OffenderInterMvmtLocations> getRestricationCusrerList(BigDecimal boolean1, String string);

	String getStgType(BigDecimal offenderBookId, BigDecimal offenderBookIdOne);

	List<Offenders> getOffDetails(final BigDecimal obj);

	List<BigDecimal> getparentLocationSchedList(BigDecimal toInternalLocationId, String pStgIndecator);

	List<OffenderInterMvmtLocations> getParentLocScheduleCusrerList(BigDecimal internalLocationId,
			String pStgIndecator);

	List<Offenders> getOffDetailsNaIntLocList(BigDecimal internalLocationId, String intLocProfileCode,
			BigDecimal offenderId);

	List<Offenders> getOffDetailsStgIntLocList(BigDecimal internalLocationId, String intLocProfileCode,
			BigDecimal offenderId);

	Integer offBlkUpdateOidintmvGetSchedulesOne(OffenderInterMvmtLocations updateList);
	
	ReferenceCodes courtEventsLocation(String caseloadId,String locationCode);

}
