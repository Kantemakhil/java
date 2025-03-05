package net.syscon.s4.inst.offenderobservations.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetails;

@EliteController
public class OiuzohosController {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuzohosController.class.getName());

	@Autowired
	private OiuzohosService oiuzohosService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuzohos/rgunitTypeLov", method = RequestMethod.GET)
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
	@RequestMapping(value = "/oiuzohos/rgLevel1LovData", method = RequestMethod.GET)
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
	@RequestMapping(value = "/oiuzohos/rgLevel2LovData", method = RequestMethod.GET)
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
	@RequestMapping(value = "/oiuzohos/rgLevel3LovData", method = RequestMethod.GET)
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
	@RequestMapping(value = "/oiuzohos/rgLevel4LovData", method = RequestMethod.GET)
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
	@RequestMapping(value = "/oiuzohos/zonehousingSeleExecuteQuery", method = RequestMethod.POST)
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
	@RequestMapping(value = "/oiuzohos/getZoneAssignedCount", method = RequestMethod.POST)
	public Integer getZoneAssignedCount(@RequestBody final OffObsZoneDetails searchBean) {
		Integer housingCount =0;
		try {
			housingCount = oiuzohosService.getZoneAssignedCount(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return housingCount;
	}
	
	
}
