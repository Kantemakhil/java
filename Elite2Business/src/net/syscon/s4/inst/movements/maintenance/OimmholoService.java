package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.AgyIntLocProfilesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnitProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.LivingUnitsCommitBean;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

/**
 * Interface OimmholoService
 */
public interface OimmholoService {
	List<ReferenceCodes> rgNonAssoTypeRecordGroup();

	String livUnitsCommit(LivingUnitsCommitBean commitBean);

	List<ReferenceCodes> rgUsedForRecordGroup();

	List<LivingUnitProfiles> luProfExecuteQuery(LivingUnitProfiles objAgyIntLoc);

	List<LivingUnits> livUnitsExecuteQuery(LivingUnits objLivingUnits);

	List<ReferenceCodes> rgHouUnTypeRecordGroup();

	List<AgencyLocations> rgAgyLocLovRecordGroup(String userName);

	List<ReferenceCodes> rgHouUnitAttRecordGroup();

	Integer usedForCommit(AgyIntLocProfilesCommitBean commitBean);

	List<ReferenceCodes> rgDeactLuRsnRecordGroup();

	List<AgyIntLocProfiles> usedForExecuteQuery(AgyIntLocProfiles objAgyIntLoc);

	List<AssessmentResults> rgSupLvlTypeRecordGroup();

	String cellBlockData(LivingUnits commitBean);

	LivingUnits getResDescValues(LivingUnits searchBean);

	Long getActiveFlagValidation(Integer livingUintId);

	List<LivingUnits> livUnitsDialogExecuteQuery(LivingUnits searchBean);

	Long getFlagValidation(Integer livingUintId);

	Long checkInheritAttributes(Integer livingUintId);

	String attributsData(Integer livingUintId);

	String nonAssociationData(Integer livingUintId);

	Integer butChangeEvent(LivingUnits obj);

	String getTableName(String liReturn);

	
	Integer iepLevelCommit(final AgyIntLocProfilesCommitBean lstAgyIntLocProfiles);
	
	String getIEPCode(Long internalLocationId,String agyLocId);

	IepLevelBean getFacilityIepLevel(String agyLocId);
	
	String  getIEPExcecuteQuery(Long internalLocationId);
	
	String getAdmisionIepCode(Long internalLocationId,String agyLocId);

}
