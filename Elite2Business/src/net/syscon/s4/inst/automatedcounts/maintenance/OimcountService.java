package net.syscon.s4.inst.automatedcounts.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyReportingLocs;
import net.syscon.s4.im.beans.AgencyReportingLocsCommitBean;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypesCommitBean;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Interface OimcountService
 */
public interface OimcountService {
	List<AgencyCountTypes> agencyCountTypesExecuteQuery(AgencyCountTypes obj);

	Integer agencyReportingLocsHousCommit(AgencyReportingLocsCommitBean commitBean);

	Integer agencyReportingLocsHousWhenNewRecordInstance(AgencyReportingLocs paramBean);

	List<LivingUnits> cgfkHousingLevel2RecordGroup(String parentField);

	List<AgencyReportingLocs> processCopy(AgencyReportingLocs paramBean);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<AgencyLocations> agencyLocationsExecuteQuery(AgencyLocations object);

	List<LivingUnits> cgfkHousingLevel3RecordGroup(String parentField);

	Integer agencyCountTypesCommit(AgencyCountTypesCommitBean commitBean);

	List<ReferenceCodes> cgfkCountTypesRecordGroup();

	Integer agencyReportingLocsInitCommit(AgencyReportingLocsCommitBean commitBean);

	List<AgencyReportingLocs> agencyReportingLocsHousExecuteQuery(AgencyReportingLocs object);

	List<AgencyLocations> cgfkAgyLocIdRecordGroup(String caseloadid);

	List<LivingUnits> cgfkHousingLevel1RecordGroup(String agyLocId);

	List<VIntLocSummaries> cgfkInitLocCodeRecordGroup(String agyLocId);

	List<AgencyReportingLocs> agencyReportingLocsInitExecuteQuery(AgencyReportingLocs searchBean);

	Integer acctypeCheckboxChenged(AgencyCountTypes object);

	List<LivingUnits> livingUnitsQuery(String agyLocId);

	AgencyLocations agencyLocationsWhenNewRecordInstance(AgencyLocations paramBean);

	Integer agencyCountReportsCommit(AgencyCountTypesCommitBean commitBean);

	List<LivingUnits> livingUnitsQueryOne(String agyLocId);

	List<LivingUnits> livingUnitsQueryTwo(String parentField1);

}
