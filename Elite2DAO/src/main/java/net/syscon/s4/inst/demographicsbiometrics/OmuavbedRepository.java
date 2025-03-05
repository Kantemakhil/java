package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.LivingUnitProfile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.im.beans.TempLivingUnitProfiles;

/**
 * Interface OmuavbedRepository
 */
public interface OmuavbedRepository {
	List<LivingUnits> rgLivingUnitRecordGroup(String caseloadId);

	List<LivingUnits> rgLivingUnitPagyRecordGroup(Long livingUnitId, String level1Code);

	List<LivingUnits> rgLivingUnitLocIdRecordGroup(Integer livingUnitId, String level2Code);

	List<LivingUnits> rgLivingUnitLevelIdRecordGroup(Integer livingUnitId, String level3Code);

	List<ReferenceCodes> rgLivingUnitTypeRecordGroup();

	List<ReferenceCodes> rgUsedForRecordGroup();

	List<ReferenceCodes> rgAttributesRecordGroup();

	Integer livuProfUforDeleteTempLivingUnitProfiles(List<TempLivingUnitProfiles> lstTempLivingUnitProfiles);

	List<AgencyLocations> whenNewFormInstancelevelCur(String pAgyLocId);

	List<TempLivingUnitProfiles> livuprofuforExecuteQuery(TempLivingUnitProfiles objTempLivingUnitProfiles);

	List<LivingUnits> livingUnitsTypeSearchLivingUnits(LivingUnits objLivingUnits);

	Integer livingUnitsTypeUpdateLivingUnits(List<LivingUnits> lstLivingUnits);

	Integer livuProfUforUpdateTempLivingUnitProfiles(List<TempLivingUnitProfiles> lstTempLivingUnitProfiles);

	List<OmuavbedLivUnitsQuery> livingunitsExecuteQuery(OmuavbedLivUnitsQuery objOmuavbedLivUnitsQuery);

	Integer livingUnitsTypeInsertLivingUnits(List<LivingUnits> lstLivingUnits);

	Integer livuprofuforInsertTempLivingUnitProfiles(List<TempLivingUnitProfiles> lstTempLivingUnitProfiles);

	List<String> livingUnitProfilesHouUsedFor(String getpAgyLocId, String profileType, String ProfileTypeOne);

	List<String> livingUnitProfilesHouUnitAtt(String getpAgyLocId, String profileType, String profileCodeOne);

	List<LivingUnitProfile> livingUnitProfilesByLivingUnitId(final String plivingUnitId);

	AgencyLocations gettingLabels(String agyLocId);

	String getLabelDescription(String housingLev1Code);

}
