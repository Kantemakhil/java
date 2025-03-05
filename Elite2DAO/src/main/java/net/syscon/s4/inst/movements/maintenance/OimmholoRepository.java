package net.syscon.s4.inst.movements.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnitProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

/**
 * Interface OimmholoRepository
 */
public interface OimmholoRepository {
	List<ReferenceCodes> rgNonAssoTypeRecordGroup();

	Integer luProfInsertLivingUnitProfiles(List<LivingUnitProfiles> lstLivingUnitProfiles);

	AgencyLocations defaultAgyrsn(AgencyLocations paramBean);

	Integer usedForDeleteAgyIntLocProfiles(List<AgyIntLocProfiles> lstAgyIntLocProfiles);

	Integer luProfDeleteLivingUnitProfiles(List<LivingUnitProfiles> lstLivingUnitProfiles);

	List<ReferenceCodes> rgUsedForRecordGroup();

	Integer usedForUpdateAgyIntLocProfiles(List<AgyIntLocProfiles> lstAgyIntLocProfiles);

	List<LivingUnitProfiles> luProfExecuteQuery(LivingUnitProfiles objLivingUnitProfiles);

	List<LivingUnits> livUnitsExecuteQuery(LivingUnits objLivingUnits);

	List<ReferenceCodes> rgHouUnTypeRecordGroup();

	Integer usedForInsertAgyIntLocProfiles(List<AgyIntLocProfiles> lstAgyIntLocProfiles);

	List<AgencyLocations> rgAgyLocLovRecordGroup(String userName);

	List<ReferenceCodes> rgHouUnitAttRecordGroup();

	String livUnitsUpdateLivingUnits(List<LivingUnits> lstLivingUnits);

	List<ReferenceCodes> rgDeactLuRsnRecordGroup();

	List<AgyIntLocProfiles> usedForExecuteQuery(AgyIntLocProfiles objAgyIntLocProfiles);

	Integer luProfUpdateLivingUnitProfiles(List<LivingUnitProfiles> lstLivingUnitProfiles);

	List<AssessmentResults> rgSupLvlTypeRecordGroup();

	String livUnitsInsertLivingUnits(List<LivingUnits> lstLivingUnits);

	String cellBlockData(LivingUnits searchdao);

	String getCellDescription(String cellData);

	BigDecimal getLivingUnitId();

	Long getCurrentLevel(BigDecimal livingUnitId);

	String getNewLuType(Long currenLevel, String agyLocId);

	Integer updateParentCapAndCna(BigDecimal livingUnitId);

	LivingUnits getResDescValues(LivingUnits objSearchDao);

	Long getActiveFlagValidation(Integer livingUintId);

	List<LivingUnits> livUnitsDialogExecuteQuery(LivingUnits searchRecord);

	Long getFlagValidation(Integer livingUintId);

	Integer actDeactChildLu(LivingUnits offenderPropertyItemObj);

	Long checkInheritAttributes(Integer livingUintId);

	List<LivingUnitProfiles> attributesData(Integer livingUintId, String string);

	Integer butChangeEvent(LivingUnits searchdao);

	LivingUnits getLivingUnitDetails(Integer livingUintId);

	String getTableName(String liReturn);

	List<AgencyInternalLocations> gettingOldData(Integer internalLocationId);

	List<AgyIntLocProfiles> gettingOldDataFromAgyIntLocAmendments(Integer row_id);

	AgencyInternalLocations getOldDataAgencyInternalLocation(Long internalLocId);
	
	Integer iepLevelCommit(final AgyIntLocProfiles lstAgyIntLocProfiles);

	AgyIntLocProfiles getIepdetails(Long internalLocationId);
	
	Integer iepLevelCommitUpdate(final Long internalLocationId,String iepLevelCode,String modifyUserId);

	Integer iepLevelDelete(Long internalLocationId, String userName);
		
	public String getIEPCodeForInternalLocation(Long internalLocationId);

	IepLevelBean getFacilityIepLevel(String agyLocId);
	
	Long getparentLivingUnitId(Long internalLocationId);
	
	Integer updateChildLivingUnits(LivingUnits livingUnits);

	String getLiveingUnitIdIepcode(Long internalLocationId);
	
	String getAgencyUnitIdIepcode(String agyLocId);
	
	String getDefaultIepCode();
	

}
