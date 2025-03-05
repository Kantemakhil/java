package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyReportingLocs;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Interface OidsublcRepository
 */
public interface OidsublcRepository {
	List<AgencyReportingLocs> housingLev1ValHousingLev1Val(AgencyReportingLocs paramBean);

	List<VIntLocSummaries> cgfkInitLocCodeRecordGroup(long countTypeCodeId);

	List<StaffMembers> cgfkConductedBy1RecordGroup();

	List<ReferenceCodes> cfgkRecountCodeRecordGroup();

	AgencyLocationCounts updateCountRecordUpdateCountRecord(AgencyLocationCounts paramBean);

	List<AgencyLocations> subLocCntWhennewrecordinstance(AgencyLocations paramBean);

	SystemProfiles runReport(SystemProfiles paramBean);

	List<StaffMembers> cgfkConductedByRecordGroup();

	List<LivingUnits> cgfkHousingLevel3RecordGroup(long countTypeCodeId, Integer livingUnitId);

	Integer subLocCntInsertAgencyLocationCounts(List<AgencyLocationCounts> object);

	AgencyReportingLocs updateCountRecordUpdateCountRecord(AgencyReportingLocs paramBean);

	List<AgencyCountTypes> defaultAgyLoc(String caseloadId);

	AgencyLocations cgwhenNewFormInstance(AgencyLocations paramBean);

	List<AgencyCounts> updateCountRecordUpdateCountRecord(AgencyCounts paramBean);

	AgencyLocationCounts oidsublcKeyExit(AgencyLocationCounts paramBean);

	List<AgencyCountTypes> cgfkSchTimeRecordGroup();

	List<LivingUnits> cgfkHousingLevel2RecordGroup(long countTypeCodeId, Integer livingUnitId);

	AgencyCountTypes subLocCntWhenCreateRecord(AgencyCountTypes paramBean);

	List<AgencyLocations> cgfkAgyLocIdRecordGroup(String caseloadId);

	List<AgencyLocationCounts> subLocCntExecuteQuery(AgencyLocationCounts object);

	List<OffenderBookings> updateCountRecordUpdateCountRecord(OffenderBookings paramBean);

	List<LivingUnits> cgfkHousingLevel1RecordGroup(long countTypeCodeId);

	OmsModules createFormGlobalsCreateFormGlobals(OmsModules paramBean);

	List<AgencyReportingLocs> housingLev2ValHousingLev2Val(AgencyReportingLocs paramBean);

	List<ReferenceCodes> cgfkCountTypesRecordGroup();

	AgencyLocations getHousingLevels(String caseloadId);

	String getUpdateCountLcheckProc(AgencyLocationCounts obj);

	Integer getAgySeqWithLivUnitId(AgencyLocationCounts obj);

	Integer getActualCountWithLivUnitId(AgencyLocationCounts obj);

	Integer getAgySeqWithIntLocId(AgencyLocationCounts obj);

	Integer getActualCountWithIntLocId(AgencyLocationCounts obj);

	Integer getMaxReportingId(AgencyLocationCounts obj);

	Integer agencyLocationCountsUpdateCountNotEquals(List<AgencyLocationCounts> updateList);

	Integer agencyLocationCountsUpdateCountEquals(List<AgencyLocationCounts> updateList);

	Integer resubmitCountUpdateQuery(List<AgencyLocationCounts> insertList);

	Integer resubmitCountDeleteQuery(List<AgencyLocationCounts> deleteList);

	String getUserId(String userId);

	Integer getSessionId();

	List<TempOidcount> tempOidcountData(Integer sessionIdMain, String caseloadId);

	String getDescription(String agyLocId, final String userId);

	String getCountLcheckProc(AgencyLocationCounts obj);

	List<TempOidcount> cgwhenNewFormInstanceCgPsessionId(String user, Integer sessionId);

	String getLabelDescription(String housingLev1Code);

}
