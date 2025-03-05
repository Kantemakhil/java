package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TempLivingUnitProfilesCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.im.beans.TempLivingUnitProfiles;

/**
 * Interface OmuavbedBusiness
 */
public interface OmuavbedService {
	List<LivingUnits> rgLivingUnitRecordGroup(String caseloadId);

	List<LivingUnits> rgLivingUnitPagyRecordGroup(Long livingUnitId, String level1Code);

	List<LivingUnits> rgLivingUnitLocIdRecordGroup(Integer livingUnitId, String level2Code);

	List<LivingUnits> rgLivingUnitLevelIdRecordGroup(Integer livingUnitId, String level3Code);

	List<ReferenceCodes> rgLivingUnitTypeRecordGroup();

	List<ReferenceCodes> rgUsedForRecordGroup();

	List<ReferenceCodes> rgAttributesRecordGroup();

	Integer livuProfUforUpdateTempLivingUnitProfiles(List<TempLivingUnitProfiles> lstTempLivingUnitProfiles);

	Integer livingUnitsTypeUpdateLivingUnits(List<LivingUnits> lstLivingUnits);

	List<LivingUnits> livingUnitsTypeSearchLivingUnits(LivingUnits objLivingUnits);

	Integer livuprofuforInsertTempLivingUnitProfiles(List<TempLivingUnitProfiles> lstTempLivingUnitProfiles);

	List<OmuavbedLivUnitsQuery> livingunitsExecuteQuery(OmuavbedLivUnitsQuery objOmuavbedLivUnitsQuery);

	Integer livuProfUforDeleteTempLivingUnitProfiles(List<TempLivingUnitProfiles> lstTempLivingUnitProfiles);

	List<AgencyLocations> whenNewFormInstancelevelCur(String pAgyLocId);

	Integer livingunitstypeInsertLivingUnits(List<LivingUnits> lstLivingUnits);

	List<TempLivingUnitProfiles> livuprofuforExecuteQuery(TempLivingUnitProfiles objTempLivingUnitProfiles);

	Integer livuprofuforCommit(final TempLivingUnitProfilesCommitBean commitBean);

	AgencyLocations gettingLabels(String agencyLocationId);
}
