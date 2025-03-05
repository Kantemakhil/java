package net.syscon.s4.inst.offenderobservations.maintenance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.automatedcounts.OidsublcService;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristics;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristicsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetails;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetailsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypes;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypesCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypesSaveCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationZones;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationZonesCommitBean;
@EliteController
public class OimoffobController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimoffobController.class.getName());
	
	@Autowired
	private OimoffobService oimoffobService;
	@Autowired
	private OiuzohosService oiuzohosService;
	@Autowired
	private OidsublcService oidsublcService;
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/observationTypesExecuteQuery", method = RequestMethod.GET)
	public List<OffenderObservationTypes> observationTypesExecuteQuery() {
		List<OffenderObservationTypes> recordList = new ArrayList<OffenderObservationTypes>();
		final OffenderObservationTypes bean = new OffenderObservationTypes();
		try {
			recordList = oimoffobService.observationTypesExecuteQuery();
		} catch (Exception e) {
			final OffenderObservationTypes error = new OffenderObservationTypes();
			logger.error("In rgSuffixRecordGroup method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}
	
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimoffob/obserVationTypeCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderObservationTypes> obserVationTypeCommit(@RequestBody final OffenderObservationTypesCommitBean commitBean) {
		List<OffenderObservationTypes> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimoffobService.obserVationTypeCommit(commitBean);
		} catch (Exception e) {
			final OffenderObservationTypes error = new OffenderObservationTypes();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimoffob/zoneDataSaveForm", method = RequestMethod.POST)
	public @ResponseBody List<OffenderObservationZones> zoneDataSaveForm(@RequestBody final OffenderObservationZonesCommitBean commitBean) {
		List<OffenderObservationZones> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimoffobService.zoneDataSaveForm(commitBean);
		} catch (Exception e) {
			final OffenderObservationZones error = new OffenderObservationZones();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/getZoneDetailsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderObservationZones> getZoneDetailsExecuteQuery(@RequestBody final OffenderObservationZones searchBean) {
		List<OffenderObservationZones> recordList = new ArrayList<OffenderObservationZones>();
		final OffenderObservationZones bean = new OffenderObservationZones();
		try {
			recordList = oimoffobService.getZoneDetailsExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderObservationZones error = new OffenderObservationZones();
			logger.error("In rgSuffixRecordGroup method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/getZoneDetailsHousingExecuteQuery", method = RequestMethod.POST)
	public List<OffObsZoneDetails> getZoneDetailsHousingExecuteQuery(@RequestBody final OffObsZoneDetails searchBean) {
		List<OffObsZoneDetails> recordList = new ArrayList<OffObsZoneDetails>();
		try {
			recordList = oimoffobService.getZoneDetailsHousingExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffObsZoneDetails error = new OffObsZoneDetails();
			logger.error("In rgSuffixRecordGroup method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimoffob/zoneHousingDataCommitForm", method = RequestMethod.POST)
	public @ResponseBody List<OffObsZoneDetails> zoneHousingDataCommitForm(@RequestBody final OffObsZoneDetailsCommitBean commitBean) {
		List<OffObsZoneDetails> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimoffobService.zoneHousingDataCommitForm(commitBean);
		} catch (Exception e) {
			final OffObsZoneDetails error = new OffObsZoneDetails();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimoffob/saveCharecterDetails", method = RequestMethod.POST)
	public @ResponseBody List<OffObsCharacteristics> saveCharecterDetails(@RequestBody final OffObsCharacteristicsCommitBean commitBean) {
		List<OffObsCharacteristics> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimoffobService.saveCharecterDetails(commitBean);
		} catch (Exception e) {
			final OffObsCharacteristics error = new OffObsCharacteristics();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/observationCharecterExecuteQuery", method = RequestMethod.POST)
	public List<OffObsCharacteristics> observationCharecterExecuteQuery(@RequestBody final OffObsCharacteristics searchBean) {
		List<OffObsCharacteristics> recordList = new ArrayList<OffObsCharacteristics>();
		try {
			recordList = oimoffobService.observationCharectersticExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffObsCharacteristics error = new OffObsCharacteristics();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/rgAgyLocRecordGroup", method = RequestMethod.GET)
	 public List<AgencyLocations> rgAgyLocRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	
		try {
		recordList = oimoffobService.rgAgyLocRecordGroup(userName);
		} catch (Exception e) {
			logger.error("Exception : Oimiloca:", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimoffob/saveCommonDetails", method = RequestMethod.POST)
	public @ResponseBody List<OffenderObservationTypes> saveCommonDetails(@RequestBody final OffenderObservationTypesSaveCommitBean commitBean) {
		List<OffenderObservationTypes> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimoffobService.saveCommonDetails(commitBean);
		} catch (Exception e) {
			final OffenderObservationTypes error = new OffenderObservationTypes();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
			logger.error("offenderObservationCommonInsertData : ", e);
			if (e.getMessage().contains("offender_obs_types_pk") || e.getMessage().contains("off_obs_characteristics_pk")
					|| e.getMessage().contains("offender_observation_types_pk")) {
				error.setReturnedOutput(BigDecimal.valueOf(2));
			}else if (e.getMessage().contains("off_obs_periods_fk2") || e.getMessage().contains("off_obs_add_details_fk1") || e.getMessage().contains("off_obs_add_check_details_fk2")) {
				error.setReturnedOutput(BigDecimal.valueOf(3));
			}else if (e.getMessage().contains("off_obs_add_details_fk1")) {
				error.setReturnedOutput(BigDecimal.valueOf(4));
			}
		}
		return liReturn;
	}
	

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/rgunitTypeLov", method = RequestMethod.GET)
	public List<ReferenceCodes> rgUnitTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oiuzohosService.rgUnitTypeRecordGroup();
		} catch (Exception e) {
			logger.error("In rgCtrlInstRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/rgLevel1LovData", method = RequestMethod.GET)
	public List<LivingUnits> rgLevel1LovData(@RequestParam(value = "unitTypeValue") final String unitTypeValue,@RequestParam(value = "facility")  final String facility ) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		final LivingUnits bean = new LivingUnits();
		try {
			recordList = oiuzohosService.rgLevel1LovData(unitTypeValue,facility);
		} catch (Exception e) {
			logger.error("In rgLevel1LovData method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/rgLevel2LovData", method = RequestMethod.GET)
	public List<LivingUnits> rgLevel2LovData(@RequestParam(value = "livigUnitId") final Integer livigUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		final LivingUnits bean = new LivingUnits();
		try {
			recordList = oiuzohosService.rgLevel2LovData(livigUnitId);
		} catch (Exception e) {
			logger.error("In rgLevel2LovData method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/rgLevel3LovData", method = RequestMethod.GET)
	public List<LivingUnits> rgLevel3LovData(@RequestParam(value = "parentLivigUnitId") final Integer parentLivigUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		final LivingUnits bean = new LivingUnits();
		try {
			recordList = oiuzohosService.rgLevel3LovData(parentLivigUnitId);
		} catch (Exception e) {
			logger.error("In rgLevel3LovData method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/rgLevel4LovData", method = RequestMethod.GET)
	public List<LivingUnits> rgLevel4LovData(@RequestParam(value = "parentLivigUnitId") final Integer parentLivigUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		final LivingUnits bean = new LivingUnits();
		try {
			recordList = oiuzohosService.rgLevel4LovData(parentLivigUnitId);
		} catch (Exception e) {
			logger.error("In rgLevel4LovData method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/zonehousingSeleExecuteQuery", method = RequestMethod.POST)
	public List<LivingUnits> zonehousingSeleExecuteQuery(@RequestBody final LivingUnits searchBean) {
		List<LivingUnits> searchResult = new ArrayList<>();
		try {
			searchResult = oiuzohosService.zonehousingSeleExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/getZoneAssignedCount", method = RequestMethod.POST)
	public Integer getZoneAssignedCount(@RequestBody final OffObsZoneDetails searchBean) {
		Integer housingCount =0;
		try {
			housingCount = oiuzohosService.getZoneAssignedCount(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return housingCount;
	}
	
	/**
	 * getting cgfkAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoffob/getHousingLevels", method = RequestMethod.GET)
	public AgencyLocations getHousingLevels(@RequestParam(value = "caseloadId") final String caseloadId) {
		AgencyLocations returnList = new AgencyLocations();
		try {
			returnList = oidsublcService.getHousingLevels(caseloadId);
		} catch (Exception e) {
			logger.error("In method getHousingLevels", e);
		}
		return returnList;
	}
	
	
}
