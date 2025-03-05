package net.syscon.s4.inst.movements;

import java.util.List;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.inst.movements.beans.HousingBean;
import net.syscon.s4.inst.movements.beans.UnitProfile;
import net.syscon.s4.inst.movements.beans.HotspotDetails;

public interface HousingRepository {
	List<HousingBean> getHousingRecord(HousingBean params);

	List<HousingBean> getAllocatedBedInfo(HousingBean params);

	String getOprationalConflict(Long id);

	String getNonAssociationConflict(Long offenderId, Long internalLocationId);

	String getSicurityConflict(Long offenderBookId, Long internalLocationId);

	String getCellSharingConflict(Long offenderBookId, Long internalLocationId, Integer allocatedBeds);

	List<HousingBean> getAllImages();

	List<HousingBean> getAllocatedOffenderInfo(HousingBean params);

	List<OmuavbedLivUnitsQuery> livingunitsExecuteQuery(OmuavbedLivUnitsQuery availBedsParams);

	Integer findBaseImageArch(String agyLocId);

	List<HousingBean> getBreadCrumbs(Integer floorPlanNextId);

	List<HousingBean> getParentChildInternalLocation(String agyLocId);

	List<HousingBean> getAllocatedBedInfoForSelectedHotSpot(HousingBean params);

	List<HousingBean> populateDataForFloorPlan(Long internalLocationId);

	Boolean isEverParentNode(Long internalLocationId);

	List<HotspotDetails> getFacilityHotspotsDetails(String agyLocId);

	List<HotspotDetails> getUnitHotspotsDetails(String agyLocId, Long unitInternalLocationId);

	void setUnitHotspotsDetails(HotspotDetails hotspotDetails);

	Integer facilityPlanUpload(String agyLocId, byte[] planData);

	Integer unitPlanUpload(String agyLocId, Long unitInternalLocationId, byte[] planData);

	List<UnitProfile> getUnitProfiles(Long unitInternalLocationId);

	void saveUnitHotspotsDetails(HotspotDetails hotspotDetails);

	Integer getUnitHotspotsDetails(Long internalLocationId, Integer floorPlanId);

	Integer getIntLocHotSpotId(Integer floorPlanId);

	void updateImageThumbnail(Integer imageId, byte[] planData);

	Integer getMaxImageId();

	Integer getFloorPlanId(String agyLocId, Long unitInternalLocationId);

	Integer insertImage(Images imageDetails);

	Integer insertFloorPlanDetails(Integer floorPlanId, Integer imageId, String rootFloorPlan, Integer parentFloorPlan,
			String agyLocId, Long unitInternalLocationId,String user);

	Integer updateAgyIntLocDetails(Long unitInternalLocationId, Integer floorPlanNextId,String userName);

	Integer getFloorPlanIdSeq();

	Integer updateFloorPlanDetails(Integer floorPlanId, Integer imageId,String user);

	List<HotspotDetails> getAllChildDataForFloor(Long unitInternalLocationId);

	List<HotspotDetails> getAllChildDataForParent(String agyLocId);

	Integer getFloorId(Long unitInternalLocationId);

	HousingBean getFloorDetails(String agyLocId, Long unitInternalLocationId);

	HotspotDetails getInternalLocDetails(String agyLocId, Long unitInternalLocationId);

	List<HousingBean> getFloorData(Integer floorId);
	
	Integer updateHotSpotDetails(List<HousingBean> hotspotDetails);
	
	Integer getFloorIdByImage(Integer imageId);
	
	Integer resetFloorHotSpotDetails(Integer floorId,String userName);
	
}
