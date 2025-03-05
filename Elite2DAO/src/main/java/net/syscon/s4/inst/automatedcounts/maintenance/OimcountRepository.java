package net.syscon.s4.inst.automatedcounts.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyReportingLocs;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Interface OimcountRepository
 */
public interface OimcountRepository {
	AgencyLocations agencyLocationsWhenNewRecordInstance(AgencyLocations paramBean);

	List<AgencyCountTypes> agencyCountTypesExecuteQuery(AgencyCountTypes object);

	Integer agencyReportingLocsHousWhenNewRecordInstance(AgencyReportingLocs paramBean);

	Integer agencyReportingLocsHousPreInsert(AgencyReportingLocs paramBean);

	Integer agencyCountTypesInsertAgencyCountTypes(List<AgencyCountTypes> object);

	List<LivingUnits> cgfkHousingLevel2RecordGroup(String agyLocId, String locCode);

	Integer agencyReportingLocsHousDeleteAgencyReportingLocs(List<AgencyReportingLocs> object);

	Integer agencyCountTypesDeleteAgencyCountTypes(List<AgencyCountTypes> object);

	Integer agencyCountTypesPreInsert(List<String> agyLocId, List<String> countTypeCode, List<String> scheduleTime);

	Integer agencyReportingLocsHousUpdateAgencyReportingLocs(List<AgencyReportingLocs> object);

	Integer agencyCountTypesUpdateAgencyCountTypes(List<AgencyCountTypes> object);

	List<AgencyLocations> agencyLocationsExecuteQuery(AgencyLocations object);

	List<AgencyCountTypes> agencyLocationsOnCheckDeleteMaster(AgencyCountTypes paramBean);

	List<VIntLocSummaries> agencyReportingLocsInitPostQuery(VIntLocSummaries paramBean);

	List<AgencyLocations> agencyLocationsWhenValidateItem(AgencyLocations paramBean);

	List<LivingUnits> cgfkHousingLevel3RecordGroup(String agyLocId, String locCode);

	List<LivingUnits> agencyReportingLocsHousPreInsert(LivingUnits paramBean);

	List<AgencyReportingLocs> agencyCountTypesOnCheckDeleteMaster(AgencyReportingLocs paramBean);

	Integer agencyReportingLocsHousInsertAgencyReportingLocs(AgencyReportingLocs object);

	List<ReferenceCodes> agencyCountTypesPostQuery(ReferenceCodes paramBean);

	Integer agencyCountTypesPreUpdate(List<String> agyLocId, List<String> countTypeCode, List<String> scheduleTime,
			List<Integer> countTypeId);

	List<ReferenceCodes> cgfkCountTypesRecordGroup();

	List<AgencyReportingLocs> agencyReportingLocsHousExecuteQuery(AgencyReportingLocs object);

	Integer agencyReportingLocsInitPreInsert(List<String> locType, List<Integer> countTypeId, List<Integer> locId);

	List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String caseloadid);

	List<AgencyCountTypes> agencyReportingLocsInitWhenNewRecordInstance(AgencyCountTypes paramBean);

	LivingUnits agencyReportingLocsHousPostQuery(Integer livingUnitId);

	List<LivingUnits> cgfkHousingLevel1RecordGroup(String agyLocId);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<VIntLocSummaries> cgfkInitLocCodeRecordGroup(String agyLocId);

	List<AgencyReportingLocs> agencyReportingLocsInitExecuteQuery(AgencyReportingLocs searchRecord);

	VIntLocSummaries agencyReportingLocsInitPostQuery(AgencyReportingLocs bean);

	Integer acctypeCheckboxChenged(AgencyCountTypes bean);

	Integer agencyCountTypesOnCheckDeleteMasters(List<Integer> countTypeId);

	Integer housPreInsertGetSeq(AgencyReportingLocs bean);

	List<LivingUnits> livingUnitsQuery(String agylocId);

	Integer cgkeyDelrec(List<Integer> countTypeId);

	Integer cgkeyDelrecAlc(List<Integer> countTypeId);

	Integer cgkeyDelrecAc(List<Integer> countTypeId);

	Integer getCountTypeId();

	Integer agencyCountReportsCommit(List<AgencyCountTypes> insertList);

	Integer agencyReportingLocsInsert(AgencyReportingLocs bean);

	List<LivingUnits> livingUnitsQueryOne(String agylocId);

	List<LivingUnits> livingUnitsQueryTwo(String agylocId, String loccode);
	
	String getLabelDescription(String housingLev1Code);

}
