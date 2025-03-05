package net.syscon.s4.inst.movements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.movements.beans.HotspotDetails;
import net.syscon.s4.inst.movements.beans.HousingBean;
import net.syscon.s4.inst.movements.beans.UnitProfile;

@EliteController
public class HousingController {
	private static Logger logger = LogManager.getLogger(HousingController.class.getName());

	@Autowired
	private HousingService housingService;

	/**
	 * getting Housing
	 */

	@RequestMapping(value = "/housing/getAllRecords", method = RequestMethod.POST)
	public List<HousingBean> getAllRecords(@RequestBody HousingBean housingParams) {
		List<HousingBean> recordList = new ArrayList<HousingBean>();
		try {
			recordList = housingService.getHousingRecord(housingParams);
		} catch (Exception e) {
			final HousingBean obj = new HousingBean();
			logger.error("GetHousingError", e);
			recordList.add(obj);
		}
		return recordList;
	}

	@RequestMapping(value = "/housing/getAllocatedBedInfo", method = RequestMethod.POST)
	public List<HousingBean> getAllocatedBedInfo(@RequestBody HousingBean housingParams) {
		List<HousingBean> recordList = new ArrayList<HousingBean>();
		try {
			recordList = housingService.getAllocatedBedInfo(housingParams);
		} catch (Exception e) {
			final HousingBean obj = new HousingBean();
			logger.error("GetHousingError", e);
			recordList.add(obj);
		}
		return recordList;
	}

	@RequestMapping(value = "/housing/getConflictInfo", method = RequestMethod.POST)
	public HousingBean getConflictInfo(@RequestBody HousingBean housingParams) {
		HousingBean conflictsList = new HousingBean();
		try {
			conflictsList = housingService.getConflictsInfo(housingParams);
		} catch (Exception e) {
			logger.error("GetHousingError", e);
			//
		}
		return conflictsList;
	}

	@RequestMapping(value = "/housing/getAllImages", method = RequestMethod.GET)
	public List<HousingBean> getAllImages() {
		List<HousingBean> imageList = new ArrayList<HousingBean>();
		try {
			imageList = housingService.getAllImages();
		} catch (Exception e) {
			logger.error("getAllImages", e);
			//
		}
		return imageList;
	}

	@RequestMapping(value = "/housing/getAllocatedOffenderInfo", method = RequestMethod.POST)
	public List<HousingBean> getAllocatedOffenderInfo(@RequestBody HousingBean housingParams) {
		List<HousingBean> recordList = new ArrayList<HousingBean>();
		try {
			recordList = housingService.getAllocatedOffenderInfo(housingParams);
		} catch (Exception e) {
			logger.error("getAllocatedOffenderInfo", e);
		}
		return recordList;
	}

	@RequestMapping(value = "/housing/findBaseImageArch", method = RequestMethod.GET)
	public Integer findBaseImageArch(String agyLocId) {
		Integer imageId = null;
		try {
			imageId = housingService.findBaseImageArch(agyLocId);
		} catch (Exception e) {
			logger.error("findBaseImageArch", e);

		}
		return imageId;
	}

	@RequestMapping(value = "/housing/getBreadCrumbs", method = RequestMethod.POST)
	public List<HousingBean> getBreadCrumbs(@RequestBody Integer floorPlanNextId) {
		List<HousingBean> recordList = new ArrayList<HousingBean>();
		try {
			recordList = housingService.getBreadCrumbs(floorPlanNextId);
		} catch (Exception e) {
			logger.error("getBreadCrumbs", e);
		}
		return recordList;
	}

	@RequestMapping(value = "/housing/populateBedInfoForSelectedHotSpot", method = RequestMethod.POST)
	public List<HousingBean> populateBedInfoForSelectedHotSpot(@RequestBody HousingBean housingParams) {
		List<HousingBean> recordList = new ArrayList<HousingBean>();
		try {
			recordList = housingService.getAllocatedBedInfoForSelectedHotSpot(housingParams);
		} catch (Exception e) {
			final HousingBean obj = new HousingBean();
			logger.error("GetHousingError", e);
			recordList.add(obj);
		}
		return recordList;
	}

	@RequestMapping(value = "/housing/populateDataForFloorPlan", method = RequestMethod.POST)
	public List<HousingBean> populateDataForFloorPlan(@RequestBody Long internalLocationId) {
		List<HousingBean> recordList = new ArrayList<HousingBean>();
		try {
			recordList = housingService.populateDataForFloorPlan(internalLocationId);
		} catch (Exception e) {
			logger.error("populateDataForFloorPlan", e);
		}
		return recordList;
	}

	@RequestMapping(value = "/housing/isEverParentNode", method = RequestMethod.GET)
	public Boolean isEverParentNode(@RequestParam Long internalLocationId) {
		Boolean result = null;
		try {
			result = housingService.isEverParentNode(internalLocationId);
		} catch (Exception e) {
			logger.error("isEverParentNode", e);

		}
		return result;
	}

	@RequestMapping(value = "/housing/facilityHotspotDetails", method = RequestMethod.GET)
	public List<HotspotDetails> getFacilityHotspotsDetails(@RequestParam String agyLocId) {
		List<HotspotDetails> result = null;
		try {
			result = housingService.getFacilityHotspotsDetails(agyLocId);
		} catch (Exception e) {
			logger.error("getFacilityHotspotsDetails", e);
		}
		return result;
	}

	@RequestMapping(value = "/housing/unitHotspotDetails", method = RequestMethod.GET)
	public List<HotspotDetails> getUnitHotspotsDetails(@RequestParam String agyLocId,
			@RequestParam Long unitInternalLocationId) {
		List<HotspotDetails> result = null;
		try {
			result = housingService.getUnitHotspotsDetails(agyLocId, unitInternalLocationId);
		} catch (Exception e) {
			logger.error("getUnitHotspotsDetails", e);
		}
		return result;
	}

	@RequestMapping(value = "/housing/unitHotspotDetails", method = RequestMethod.POST)
	public void setUnitHotspotsDetails(@RequestBody HotspotDetails hotspotDetails, @RequestHeader HttpHeaders headers) {
		try {
			List<String> authorizationList = headers.get("Authorization");
			String authorization = authorizationList.get(0).split(",")[0];
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			hotspotDetails.setCreateUserId(userName);
			housingService.setUnitHotspotsDetails(hotspotDetails);
		} catch (Exception e) {
			logger.error("setUnitHotspotsDetails", e);
		}
	}

	@RequestMapping(value = "/housing/unit-image", method = RequestMethod.POST)
	public HousingBean unitPlanUpload(@RequestParam String agyLocId, @RequestParam Long unitInternalLocationId,
			@RequestParam Integer floorId, @RequestParam String userId, @RequestParam("file") MultipartFile file) {
		HousingBean housing = null;
		try {
			housing = housingService.unitPlanUpload(agyLocId, unitInternalLocationId, floorId, userId, file.getBytes(),SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			logger.error("unitPlanUpload", e);
			housing = null;
		}
		return housing;
	}

	@RequestMapping(value = "/housing/facility-image", method = RequestMethod.POST)
	public HousingBean facilityPlanUpload(@RequestParam String agyLocId, @RequestParam String userId,
			@RequestParam("file") MultipartFile file) {
		HousingBean housing = null;
		try {
			housing=housingService.facilityPlanUpload(agyLocId, userId, file.getBytes(),SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			logger.error("facilityPlanUpload", e);
		}
		return housing;
	}

	@RequestMapping(value = "/housing/unit-profiles", method = RequestMethod.GET)
	public List<UnitProfile> getUnitProfiles(@RequestParam Long unitInternalLocationId) {
		try {
			return housingService.getUnitProfiles(unitInternalLocationId);
		} catch (Exception e) {
			logger.error("getUnitProfiles", e);
		}
		return Collections.emptyList();
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/housing/getFloorDetails", method = RequestMethod.POST)
	public List<HousingBean> getFloorDetails(@RequestBody Integer floorId) {
		List<HousingBean> recordList = new ArrayList<HousingBean>();
		try {
			recordList = housingService.getFloorData(floorId);
		} catch (Exception e) {
			logger.error("getBreadCrumbs", e);
		}
		return recordList;
	}
	@RequestMapping(value = "/housing/updateHotSpotDetails", method = RequestMethod.POST)
	public Integer updateHotSpotDetails(@RequestBody List<HousingBean> hotspotDetails, @RequestHeader HttpHeaders headers) {
		Integer result = null;
		try {
			List<String> authorizationList = headers.get("Authorization");
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			for(HousingBean obj:hotspotDetails) {
				obj.setModifyUserId(userName);
			}
			result = housingService.updateHotSpotDetails(hotspotDetails);
		} catch (Exception e) {
			logger.error("findBaseImageArch", e);

		}
		return result;
	}
}
