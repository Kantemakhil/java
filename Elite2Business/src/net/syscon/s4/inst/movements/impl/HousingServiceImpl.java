package net.syscon.s4.inst.movements.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.inst.movements.HousingRepository;
import net.syscon.s4.inst.movements.HousingService;
import net.syscon.s4.inst.movements.beans.HotspotDetails;
import net.syscon.s4.inst.movements.beans.HousingBean;
import net.syscon.s4.inst.movements.beans.UnitProfile;
import net.syscon.s4.pkgs.omuavbed.OmuavbedPkgService;

@Service
public class HousingServiceImpl extends BaseBusiness implements HousingService {

	@Autowired
	HousingRepository housingRepository;
	
	@Autowired
	private OmuavbedPkgService omuavbedPkgService;

	@Override
	public List<HousingBean> getHousingRecord(HousingBean unitType) {
		List<HousingBean> result = new ArrayList<HousingBean>();
		List<OmuavbedLivUnitsQuery> availableBedsDetails = new ArrayList<OmuavbedLivUnitsQuery>();
		result = housingRepository.getHousingRecord(unitType);
		OmuavbedLivUnitsQuery availBedsParams = new OmuavbedLivUnitsQuery();
		availBedsParams.setpOffenderBookId(unitType.getOffenderBookId());
		availBedsParams.setpOffenderId(unitType.getOffenderId());
		availBedsParams.setpCaseloadId(null);
		availBedsParams.setpAgyLocId(unitType.getAgyLocId());
		availBedsParams.setpLivingUnitType(null);
		availBedsParams.setpLevel1Code(unitType.getpLevel1Code());
		availBedsParams.setpLevel2Code(unitType.getpLevel2Code());
		availBedsParams.setpLevel3Code(unitType.getpLevel3Code());
		availBedsParams.setpLevel4Code(unitType.getpLevel4Code());
		if (unitType.getpLevel1Code() != null) {
			List<HousingBean> bedCellRelationship = new ArrayList<>();
			bedCellRelationship = housingRepository.getParentChildInternalLocation(unitType.getAgyLocId());
			Map<Long, Long> parentChildInternalLocationMap = bedCellRelationship.stream()
					.collect(Collectors.toMap(HousingBean::getInternalLocationId, HousingBean::getParentLocId));
			availableBedsDetails = omuavbedPkgService.livUnitsQuery(availBedsParams);
			for (OmuavbedLivUnitsQuery availBed : availableBedsDetails) {
				long parentId = parentChildInternalLocationMap.get(availBed.getLivingUnitId());
				for (HousingBean internalLocationDetail : result) {
					if (internalLocationDetail.getInternalLocationId() == parentId
							|| internalLocationDetail.getInternalLocationId() == availBed.getLivingUnitId()) {
						if (!internalLocationDetail.getSecurityConflict().equals("Y")) {
							internalLocationDetail.setSecurityConflict(availBed.getSecurityConflict());
						}
						if (!internalLocationDetail.getOprationalConflict().equals("Y")) {
							internalLocationDetail.setOprationalConflict(availBed.getUnitAtCapacity());
						}
						if (!internalLocationDetail.getOffenderConflict().equals("Y")) {
							internalLocationDetail.setOffenderConflict(availBed.getPrisonerConflict());
						}
						if (!internalLocationDetail.getCellSharingConflict().equals("Y")) {
							internalLocationDetail.setCellSharingConflict(availBed.getCellSharingConflict());
						}
						break;
					}
				}
			}
		}
		return result;

	}

	public long getParentId(long bedId, List<HousingBean> bedCellRel) {
		long parentId = 0;
		for (HousingBean bedCellMap : bedCellRel) {
			if (bedCellMap.getInternalLocationId() == bedId) {
				parentId = bedCellMap.getParentLocId();
				break;
			}
		}
		return parentId;
	}

	@Override
	public List<HousingBean> getAllocatedBedInfo(HousingBean params) {
		return housingRepository.getAllocatedBedInfo(params);
	}

	@Override
	public HousingBean getConflictsInfo(HousingBean params) {
		HousingBean getData = new HousingBean();
		if (null != params.getInternalLocationType() && params.getFloorPlanId() > 0) {
			String oprationalConflicts = getOprationalConflict(params.getInternalLocationId());
			if (null != oprationalConflicts && !oprationalConflicts.equals("")) {
				getData.setOprationalConflict(oprationalConflicts);
			}

			String nonAssociationConflicts = getNonAssociationConflict(params.getOffenderId(),
					params.getInternalLocationId());
			if (null != nonAssociationConflicts && !nonAssociationConflicts.equals("")) {
				getData.setOffenderConflict(nonAssociationConflicts);
			}

			String sicurityConflicts = getSicurityConflict(params.getOffenderBookId(), params.getInternalLocationId());
			if (null != sicurityConflicts && !sicurityConflicts.equals("")) {
				getData.setSecurityConflict(sicurityConflicts);
			}

			String cellSharingConflicts = getCellSharingConflict(params.getOffenderBookId(),
					params.getInternalLocationId(), params.getAllocatedBeds());
			if (null != cellSharingConflicts && !cellSharingConflicts.equals("")) {
				getData.setCellSharingConflict(cellSharingConflicts);
			}

		}
		return getData;
	}

	@Transactional
	private String getOprationalConflict(Long internalLocationId) {
		return housingRepository.getOprationalConflict(internalLocationId);
	}

	@Transactional
	private String getNonAssociationConflict(Long offenderId, Long internalLocationId) {
		return housingRepository.getNonAssociationConflict(offenderId, internalLocationId);
	}

	@Transactional
	private String getSicurityConflict(Long offenderBookId, Long internalLocationId) {
		return housingRepository.getSicurityConflict(offenderBookId, internalLocationId);
	}

	@Transactional
	private String getCellSharingConflict(Long offenderBookId, Long internalLocationId, Integer allocatedBeds) {
		return housingRepository.getCellSharingConflict(offenderBookId, internalLocationId, allocatedBeds);
	}

	@Override
	public List<HousingBean> getAllImages() {
		return housingRepository.getAllImages();

	}

	@Override
	public List<HousingBean> getAllocatedOffenderInfo(HousingBean params) {
		return housingRepository.getAllocatedOffenderInfo(params);
	}

	@Override
	public Integer findBaseImageArch(String agyLocId) {
		return housingRepository.findBaseImageArch(agyLocId);
	}

	@Override
	public List<HousingBean> getBreadCrumbs(Integer floorPlanNextId) {
		return housingRepository.getBreadCrumbs(floorPlanNextId);
	}

	@Override
	public List<HousingBean> getAllocatedBedInfoForSelectedHotSpot(HousingBean params) {
		return housingRepository.getAllocatedBedInfoForSelectedHotSpot(params);
	}

	@Override
	public List<HousingBean> populateDataForFloorPlan(Long internalLocationId) {
		return housingRepository.populateDataForFloorPlan(internalLocationId);
	}

	@Override
	public Boolean isEverParentNode(Long internalLocationId) {
		return housingRepository.isEverParentNode(internalLocationId);
	}

	@Override
	public List<HotspotDetails> getFacilityHotspotsDetails(String agyLocId) {
		List<HotspotDetails> hotspotsForFloorPlan = housingRepository.getFacilityHotspotsDetails(agyLocId);
		List<HotspotDetails> childData = housingRepository.getAllChildDataForParent(agyLocId);
		List<HotspotDetails> removeList = new ArrayList<>();
		if (!hotspotsForFloorPlan.isEmpty()) {
			for (HotspotDetails hotSpotChild : childData) {
				hotSpotChild.setxCoordinate(0);
				hotSpotChild.setyCoordinate(0);
				for (HotspotDetails hotSpotData : hotspotsForFloorPlan) {
					if (hotSpotChild.getInternalLocationId().equals(hotSpotData.getInternalLocationId())) {
						removeList.add(hotSpotChild);
					}
				}
			}
			if (removeList != null && !removeList.isEmpty()) {
				childData.removeAll(removeList);
			}

			for (HotspotDetails hotSpotChild : childData) {
				hotspotsForFloorPlan.add(hotSpotChild);
			}
		} else {
			for (HotspotDetails hotSpotChild : childData) {
				hotSpotChild.setxCoordinate(0);
				hotSpotChild.setyCoordinate(0);
			}
			return childData;
		}

		return hotspotsForFloorPlan;
	}

	@Override
	public List<HotspotDetails> getUnitHotspotsDetails(String agyLocId, Long unitInternalLocationId) {
		List<HotspotDetails> hotspotsForFloorPlan = housingRepository.getUnitHotspotsDetails(agyLocId,
				unitInternalLocationId);
		List<HotspotDetails> childData = housingRepository.getAllChildDataForFloor(unitInternalLocationId);
		List<HotspotDetails> removeList = new ArrayList<>();
		if (!hotspotsForFloorPlan.isEmpty()) {
			for (HotspotDetails hotSpotChild : childData) {
				hotSpotChild.setxCoordinate(0);
				hotSpotChild.setyCoordinate(0);
				for (HotspotDetails hotSpotData : hotspotsForFloorPlan) {
					if (hotSpotChild.getInternalLocationId().equals(hotSpotData.getInternalLocationId())) {
						removeList.add(hotSpotChild);
					}
				}
			}
			if (removeList != null && !removeList.isEmpty()) {
				childData.removeAll(removeList);
			}

			for (HotspotDetails hotSpotChild : childData) {
				hotspotsForFloorPlan.add(hotSpotChild);
			}
		} else {
			for (HotspotDetails hotSpotChild : childData) {
				hotSpotChild.setxCoordinate(0);
				hotSpotChild.setyCoordinate(0);
			}
			return childData;
		}


		return hotspotsForFloorPlan;
	}

	@Override
	public void setUnitHotspotsDetails(HotspotDetails hotspotDetails) {
		Integer parentFloorId = null;
		if (hotspotDetails.getParentId() != null) {
			parentFloorId = housingRepository.getFloorId(new Long(hotspotDetails.getParentId()));
		} else {
			parentFloorId = findBaseImageArch(hotspotDetails.getAgyLocId());
		}
		hotspotDetails.setFloorPlanNextId(null);
		hotspotDetails.setFloorPlanId(parentFloorId);
		int hotspotDet = housingRepository.getUnitHotspotsDetails(hotspotDetails.getInternalLocationId(),
				hotspotDetails.getFloorPlanId());
		if (hotspotDet > 0) {
			housingRepository.setUnitHotspotsDetails(hotspotDetails);
		} else {
			HousingBean floorDeatils = housingRepository.getFloorDetails(hotspotDetails.getAgyLocId(), hotspotDetails.getInternalLocationId());
			if(floorDeatils!=null) {
				hotspotDetails.setFloorPlanNextId(floorDeatils.getFloorPlanId());
			}
			housingRepository.saveUnitHotspotsDetails(hotspotDetails);
		}

	}

	@Override
	public HousingBean facilityPlanUpload(String agyLocId, String userId, byte[] planData,String userName) {
		Integer imageId;
		Integer floorPlanId = null;
		HousingBean housingData = new HousingBean();
		imageId = housingRepository.facilityPlanUpload(agyLocId, planData);
		if (imageId != null) {
			floorPlanId=housingRepository.getFloorIdByImage(imageId);
			if(floorPlanId!=null) {
               housingRepository.resetFloorHotSpotDetails(floorPlanId,userName);
			}
			housingRepository.updateImageThumbnail(imageId, planData);
		} else {
			 floorPlanId = housingRepository.getFloorPlanIdSeq();
			housingRepository.insertFloorPlanDetails(floorPlanId, null, "Y", null, agyLocId, null,userName);
			imageId = insertImageDetails(agyLocId, floorPlanId, userId, planData);
			if (imageId != null) {
				housingRepository.updateFloorPlanDetails(floorPlanId, imageId,userName);

			}
			housingData.setImageId(new BigDecimal(imageId));
			housingData.setAgyLocId(agyLocId);
			housingData.setInternalLocationId(null);
			housingData.setFloorPlanId(floorPlanId);
			housingData.setRootFloorPlan("Y");
			housingData.setParentFloorPlan(null);
		}
		return housingData;
	}

	@Override
	public HousingBean unitPlanUpload(String agyLocId, Long unitInternalLocationId, Integer floorId, String userId,
			byte[] planData,String userName) {
		Integer imageId;
		Integer parentFloorId = null;
		Integer floorPlanId = null;
		HousingBean housingData = new HousingBean();
		imageId = housingRepository.unitPlanUpload(agyLocId, unitInternalLocationId, planData);
		if (imageId != null) {
			floorPlanId=housingRepository.getFloorIdByImage(imageId);
			if(floorPlanId!=null) {
				housingRepository.resetFloorHotSpotDetails(floorPlanId,userName);
			}
			housingRepository.updateImageThumbnail(imageId, planData);

		} else {
			HousingBean floorDeatils = housingRepository.getFloorDetails(agyLocId, unitInternalLocationId);
			if (floorDeatils != null) {
				imageId = insertImageDetails(agyLocId, floorDeatils.getFloorPlanId(), userId, planData);
				housingRepository.updateFloorPlanDetails(floorDeatils.getFloorPlanId(), imageId,userName);
			} else {
				HotspotDetails locationDetails = housingRepository.getInternalLocDetails(agyLocId,
						unitInternalLocationId);
				if (locationDetails != null && locationDetails.getParentId() != null) {
					parentFloorId = housingRepository.getFloorId(new Long(locationDetails.getParentId()));
				} else {
					parentFloorId = findBaseImageArch(agyLocId);
				}
				if(parentFloorId == null) {
					housingData.setErrorMessage("No_Parent_Data");
					return housingData;
				}
				floorPlanId = housingRepository.getFloorPlanIdSeq();
				Integer result = housingRepository.insertFloorPlanDetails(floorPlanId, null, "N", parentFloorId,
						agyLocId, unitInternalLocationId,userName);
				if (result != null) {
					imageId = insertImageDetails(agyLocId, floorPlanId, userId, planData);
					if (imageId != null) {
						housingRepository.updateAgyIntLocDetails(unitInternalLocationId, floorPlanId,userName);
						housingRepository.updateFloorPlanDetails(floorPlanId, imageId,userName);
					}

				}

			}
			housingData.setImageId(new BigDecimal(imageId));
			housingData.setAgyLocId(agyLocId);
			housingData.setInternalLocationId(unitInternalLocationId);
			housingData.setFloorPlanId(floorPlanId);
			housingData.setParentFloorPlan(parentFloorId);
		}

		return housingData;

	}

	private Integer insertImageDetails(String agyLocId, Integer floorId, String userId, byte[] planData) {
		Integer imageId;
		int result = 0;
		imageId = housingRepository.getMaxImageId();
		Long imageIdToBeInserted = new Long(imageId);
		Date currentDate = getCurrentDatetime();
		Date captureDate = Date.valueOf(java.time.LocalDate.now());
		BigDecimal imageObjectSeq = new BigDecimal(0);
		BigDecimal floorPlanIdInsert = new BigDecimal(floorId);
		Images imageDetails = new Images();
		imageDetails.setImageId(imageIdToBeInserted);
		imageDetails.setCaptureDate(captureDate);
		imageDetails.setImageObjectType("OMUVB_HOUSE");
		imageDetails.setImageObjectId(floorPlanIdInsert);
		imageDetails.setImageObjectSeq(imageObjectSeq);
		imageDetails.setImageViewType("");
		imageDetails.setImageThumbnail(planData);
		imageDetails.setActiveFlag("N");
		imageDetails.setOrientationType("");
		imageDetails.setSealFlag(null);
		imageDetails.setCreateDatetime(currentDate);
		imageDetails.setCreateUserId(userId);
		imageDetails.setModifyDatetime(currentDate);
		imageDetails.setModifyUserId(userId);
		result = housingRepository.insertImage(imageDetails);
		if (result > 0) {
			return imageId;
		}
		return null;
	}

	static java.sql.Date getCurrentDatetime() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	@Override
	public List<UnitProfile> getUnitProfiles(Long unitInternalLocationId) {
		return housingRepository.getUnitProfiles(unitInternalLocationId);
	}

	@Override
	public List<HousingBean> getFloorData(Integer floorId) {
		return housingRepository.getFloorData(floorId);
	}

	@Override
	public Integer updateHotSpotDetails(List<HousingBean> hotspotDetails) {
		Integer liReturn=0;
		if(hotspotDetails!=null && !hotspotDetails.isEmpty()) {
			liReturn = housingRepository.updateHotSpotDetails(hotspotDetails);
		}
		return liReturn;
	}

}
