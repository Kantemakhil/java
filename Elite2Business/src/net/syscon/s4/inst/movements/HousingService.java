package net.syscon.s4.inst.movements;

import java.util.List;

import net.syscon.s4.inst.movements.beans.HotspotDetails;
import net.syscon.s4.inst.movements.beans.HousingBean;
import net.syscon.s4.inst.movements.beans.UnitProfile;

public interface HousingService {
	List<HousingBean> getHousingRecord(HousingBean params);

	List<HousingBean> getAllocatedBedInfo(HousingBean params);

	HousingBean getConflictsInfo(HousingBean params);

	List<HousingBean> getAllImages();

	List<HousingBean> getAllocatedOffenderInfo(HousingBean params);

	Integer findBaseImageArch(String agyLocId);

	List<HousingBean> getBreadCrumbs(Integer floorPlanNextId);

	List<HousingBean> getAllocatedBedInfoForSelectedHotSpot(HousingBean params);

	List<HousingBean> populateDataForFloorPlan(Long internalLocationId);

	Boolean isEverParentNode(Long internalLocationId);

	List<HotspotDetails> getFacilityHotspotsDetails(String agyLocId);

	List<HotspotDetails> getUnitHotspotsDetails(String agyLocId, Long unitInternalLocationId);

	void setUnitHotspotsDetails(HotspotDetails hotspotDetails);

	HousingBean facilityPlanUpload(String agyLocId, String userId, byte[] bytes,String userName);

	HousingBean unitPlanUpload(String agyLocId, Long unitInternalLocationId, Integer floorId, String userId,
			byte[] planData,String userName);

	List<UnitProfile> getUnitProfiles(Long unitInternalLocationId);
	
	List<HousingBean> getFloorData(Integer floorId);
	
	Integer updateHotSpotDetails(List<HousingBean> hotspotDetails);
}
