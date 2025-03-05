package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCountsCommitBean;
import net.syscon.s4.inst.automatedcounts.beans.AgencyReportingLocs;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Interface OidsublcService
 */
public interface OidsublcService {
	SystemProfiles runReport(SystemProfiles paramBean);

	AgencyCountTypes getDefaultAgyLoc(String caseloadId);

	List<AgencyReportingLocs> housingLev2Val(AgencyReportingLocs paramBean);

	List<VIntLocSummaries> cgfkInitLocCodeRecordGroup(long countTypeCodeId);

	List<AgencyLocationCounts> oidsublcKeyExit(AgencyLocationCounts paramBean);

	AgencyLocations cgwhenNewFormInstance(AgencyLocations paramBean);

	List<StaffMembers> cgfkConductedBy1RecordGroup();

	AgencyReportingLocs housingLev1Val(AgencyReportingLocs paramBean);

	List<ReferenceCodes> cfgkRecountCodeRecordGroup();

	Integer subLocCntCommit(AgencyLocationCountsCommitBean commitBean);

	List<AgencyCountTypes> cgfkSchTimeRecordGroup();

	List<LivingUnits> cgfkHousingLevel2RecordGroup(long countTypeCodeId, Integer livingUnitId);

	List<AgencyLocations> subLocCntWhennewrecordinstance(AgencyLocations paramBean);

	List<AgencyLocations> cgfkAgyLocIdRecordGroup(Integer sessionId, String caseloadId,String userId);

	List<AgencyReportingLocs> updateCountRecord(AgencyReportingLocs paramBean);

	List<AgencyLocationCounts> subLocCntExecuteQuery(AgencyLocationCounts object);

	List<StaffMembers> cgfkConductedByRecordGroup();

	List<AgencyLocations> subLocCntWhenCreateRecord(AgencyLocations paramBean);

	List<LivingUnits> cgfkHousingLevel3RecordGroup(long countTypeCodeId, Integer livingUnitId);

	List<LivingUnits> cgfkHousingLevel1RecordGroup(long countTypeCodeId);

	List<ReferenceCodes> cgfkCountTypesRecordGroup();

	OmsModules createFormGlobals(OmsModules paramBean);

	AgencyLocations getHousingLevels(String caseloadId);

	Integer reSubLocCntCommit(AgencyLocationCountsCommitBean commitBean, String userId);

}
