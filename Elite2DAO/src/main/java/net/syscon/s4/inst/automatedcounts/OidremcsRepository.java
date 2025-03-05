package net.syscon.s4.inst.automatedcounts;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocationCounts;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyReportingLocs;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OidremcsRepository
 */
public interface OidremcsRepository {
	List<OffenderBookings> calculateActualCount(OffenderBookings paramBean);

	Integer subRemCntInsertsubRemCnt(List<AgencyLocationCounts> lstAgencyLocationCounts);

	List<AgencyReportingLocs> housingLev1Val(AgencyReportingLocs paramBean);

	AgencyReportingLocs updateCountRecord(AgencyReportingLocs paramBean);

	OmsModules createFormGlobals(OmsModules paramBean);

	List<LivingUnits> cgfkHousingLevel1RecordGroup(Integer countTypeId);

	List<LivingUnits> cgfkHousingLevel2RecordGroup(Integer countTypeId, Integer livingUnitId);

	List<LivingUnits> cgfkHousingLevel3RecordGroup(Integer countTypeId, Integer livingUnitId);

	List<LivingUnits> cgfkInitLocCodeRecordGroup(Integer countTypeId);

	List<StaffMembers> cgfkConductedByRecordGroup();

	AgencyLocations oidremcsWhenNewFormInstance(AgencyLocations paramBean);

	List<AgencyReportingLocs> housingLev2Val(AgencyReportingLocs paramBean);

	List<AgencyLocationCounts> subRemCntExecuteQuery(AgencyLocationCounts objAgencyLocationCounts);

	Integer getActualCount(Integer livingUnitId);

	Integer subRemCntUpdatesubRemCnt(List<AgencyLocationCounts> updateList);

	Integer getAgySeq(AgencyLocationCounts searchBean);

	Integer getInternalLoc(AgencyLocationCounts searchBean);

	String checkLocValidation(AgencyLocationCounts searchBean);

	Integer getInternalLocationCount(Integer livingUnitId);

	Integer getLivingUnit3(Integer livingUnitId,final Integer livingUnitId2);

	List<Object> checklcheck(Integer livingUnitId, Integer livingUnitId2);

	Map<String, Object> getHousingLocationLovNames(Integer countTypeId);

	int getAgencyReportingLocsOne(int livUnitId);

	String getAgencyReportingLocsTwo(int livUnitId);

	int getAgencyReportingLocsThree(Integer livingUnitIdOne, Integer livingUnitIdTwo);

	String getAgencyReportingLocsFour(Integer livingUnitIdOne, Integer livingUnitIdTwo);

	Integer resubmitCountDeleteQuery(List<AgencyLocationCounts> returnData);

	String getCountLcheckProc(AgencyLocationCounts agencyLocationCounts);
	
	String getLabelDescription(String housingLev1Code);

}
