package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyReportingLocs;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.inst.automatedcounts.beans.SalesMaintenances;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;

/**
 * Interface OidcountRepository
 */
public interface OidcountRepository {

	Long getGlobalSessionId();

	List<TempOidcount> checkExistingCountSession(Integer sessionId);

	Object checkCountExists(AgencyReportingLocs paramBean);

	Integer deleteInitiateRecords(AgencyCountTypes paramBean);

	List<ReferenceCodes> cgfkCountTypesRecordGroup(String agyLocId);

	List<AgencyCountTypes> cgkeyListvalAgencyCountTypes(AgencyCountTypes paramBean);

	AgencyReportingLocs createRecountRecords(AgencyReportingLocs paramBean);

	List<AgencyCountTypes> cgfkScheduledTimeRecordGroup(String agyLocId, String countTypeId);

	Integer cgwhenNewFormInstanceCgLockedModules(LockedModules paramBean);

	SalesMaintenances printListSalesMaintenances(SalesMaintenances paramBean);

	List<ReferenceCodes> cgfkAgyLocIdRecordGroup(String caseLoadId);

	SystemProfiles printListProfileTypeClient(SystemProfiles paramBean);

	String cgwhenNewFormInstanceCgCaseload(String userId);

	AgencyCounts createInitiatedRecords(AgencyCounts paramBean);

	String countLockedMoulesCursor(AgencyCountTypes bean);

	Integer countAgencyReportinglocsCursor(AgencyCountTypes bean);

	Integer countAgencyCountsTypesCursor(AgencyCountTypes bean);

	String checkCountExistsFunction(AgencyCountTypes bean);

	Integer insertTheDataInLockedModules(AgencyCountTypes bean);

	Integer insertTheDataInAgencyCounts(AgencyCounts agencyCountsbean);

	List<Integer> preInsertOfAagencyLocationCounts(AgencyCounts agencyCountsbean);

	Integer insertTheDataInAgencyLocationCounts(AgencyLocationCounts alcBean);

	Integer gettingMaxValueOfReportinglocidInAgenctLocations();

	List<TempOidcount> initiateCountSetup(AgencyCountTypes paramBean);

	Integer getCountTypeIdFromDb(AgencyCountTypes bean);

	Integer deleteInitiateRecordsOfAgencyLocationCounts(Integer returnValue, String modifyUserId);

	Integer deleteInitiateRecordsOfAgencyCounts(Integer reportingLocId, String modifyUserId);

	Integer deleteInitiateRecordsOfLockedModules(AgencyCountTypes paramBean);

	Integer cancelCountProcedure(AgencyCountTypes paramBean);

	Integer submitCountSetTempOidcount(AgencyCountTypes paramBean);

	List<TempOidcount> cgwhenNewFormInstanceCgPsessionId(String sessionId);

	Integer checkRemoveDeadJobsProcedure(Integer sessionId);

	String cgwhenNewFormInstanceLockedModules();

	String cgwhenNewFormInstanceCgSessionId(Integer sessionId);

	String defaultAgyLocSessionId(Integer sessionId);

	AgencyLocations defaultAgyLocGlobalCaseload(String caseLoadId);

	AgencyLocations defaultAgyLocGlobalCaseloadElseCondtion(String defaultAgyLocId);

	Integer getCountWhenNewFormInstanceFromCaseloadAgencyLocations(final String userId);

	Integer checkExistingGetCount(Integer sessionId);

	TempOidcount refreshCount(Integer sessionId);

	List<TempOidcount> refreshCountOfTempOidCount(Integer sessionId);

	Integer setTempOidcount(AgencyCountTypes paramBean);

	String refreshCountUserCancelledCur(Integer sessionId, String userId);
	
	Integer getTimerValue();

	void deleteCancelRecords(Long sessionId, String modifyUserId);
	Integer getlCountExistingValue(Long sessionId);

	Integer insertCancelCountValue(Long sessionId);

	Integer insertOidTempCountBean(AgencyCountTypes bean);

	List<Long> getListReportingLocId(Long sessionId);

	List<TempOidcount> getLTempCountCurser(Long sessionId);

	Integer deleteRecordFromOidTempCount(Long sessionId, String modifyUserId);

	Integer getLtermintaedSessionFlag(Long sessionId);

	Integer deleteAgencyLocationCount(Long reportLocId, String modifyUserId);

	Integer deleteAgencyCounts(Long long1, String modifyUserId);

	Integer getAgencyLcountInitCurser(Integer lowestLocationId, String agyLocId);

	Integer getLcountLivingUnitCurser(Integer lowestLocationId, String agyLocId);

	Integer updateTempOidcount(Integer actualCount, Long rowId);

	Integer getLcountMaleInitCurser(Integer lowestLocationId, String agyLocId);

	Integer getLcountLivingMaleInitCurser(Integer lowestLocationId, String agyLocId);

	Integer updateLcountMaleOidcount(Integer lActualCount, Long rowId);

	Integer getLcountFeMaleInitCurser(Integer lowestLocationId, String agyLocId);

	Integer getLcountLivingFeMaleInitCurser(Integer lowestLocationId, String agyLocId);

	Integer updateLcountFeMaleOidcount(Integer lTotalFeMale, Long rowId);

	Integer getLcountOtherInitCurser(Integer lowestLocationId, String agyLocId);

	Integer getLcountLivingOtherInitCurser(Integer lowestLocationId, String agyLocId);

	Integer updateLcountOtherOidcount(Integer lTotalOther, Long rowId);

	Integer getLcountLoutInitCurser(Integer lowestLocationId, String agyLocId);

	Integer getLcountLivingLoutInitCurser(Integer lowestLocationId, String agyLocId);

	Integer updateLcountLoutOidcount(Integer lOutTotal, Long rowId);

	Integer getLcountLTotalMaleOutInitCurser(Integer lowestLocationId, String agyLocId);

	Integer getLcountLivingLTotalMaleOutInitCurser(Integer lowestLocationId, String agyLocId);

	Integer updateLcountLTotalMaleOutOidcount(Integer lTotalMaleOut, Long rowId);

	Integer getLcountLTotalFemaleOutInitCurser(Integer lowestLocationId, String agyLocId);

	Integer getLcountLivingLTotalFemaleOutInitCurser(Integer lowestLocationId, String agyLocId);

	Integer updateLcountLTotalFemaleOutOidcount(Integer lOutTotalFemaleOut, Long rowId);

	Integer getLcountLTotalOtherOutInitCurser(Integer lowestLocationId, String agyLocId);

	Integer getLcountLivingLTotalOtherOutInitCurser(Integer lowestLocationId, String agyLocId);

	Integer updateLcountLTotalOtherOutOidcount(Integer lTotalOtherOut, Long rowId);

	Integer getlCountExistingValueCancel(Long sessionId);

}
