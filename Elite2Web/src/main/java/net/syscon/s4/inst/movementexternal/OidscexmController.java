package net.syscon.s4.inst.movementexternal;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movementexternal.beans.VOiusstri;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OidscexmController
 */
@EliteController
public class OidscexmController {
	@Autowired
	private OidscexmService oidscexmService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidscexmController.class.getName());

	/**
	 * getting rgMoveType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscexm/rgMoveTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMoveTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidscexmService.rgMoveTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgMoveTypeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgBuilding LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscexm/rgBuildingRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgBuildingRecordGroup() {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidscexmService.rgBuildingRecordGroup();
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("rgBuildingRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscexm/rgAgyIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyIdRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidscexmService.rgAgyIdRecordGroup();
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("rgAgyIdRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTier LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscexm/rgTierRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgTierRecordGroup() {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidscexmService.rgTierRecordGroup();
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("rgAgyIdRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscexm/offSchExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> offSchExecuteQuery(final @RequestBody VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = oidscexmService.offSchExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("rgAgyIdRecordGroup: ", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidscexm/offSchCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offSchCommit(final @RequestBody VOffenderAllSchedulesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			liReturn = oidscexmService.offSchCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "77");
			}
		} catch (Exception e) {
			logger.error("offSchCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiusstri/schTripsExecuteQuery", method = RequestMethod.POST)
	public List<VOiusstri> schTripsExecuteQuery(final @RequestBody VOiusstri searchBean) {
		List<VOiusstri> searchResult = new ArrayList<>();
		try {
			searchResult = oidscexmService.schTripsExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOiusstri bean = new VOiusstri();
			logger.error("schTripsExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscexm/getLastMovementDateTime", method = RequestMethod.POST)
	public OffenderExternalMovements getLastMovementDateTime(
			final @RequestBody VOffenderAllSchedules vOffenderAllSchedules) {
		OffenderExternalMovements searchResult = new OffenderExternalMovements();
		try {
			searchResult = oidscexmService.getLastMovementDateTime(vOffenderAllSchedules);
		} catch (Exception e) {
			logger.error("getLastMovementDateTime", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscexm/processExternalMovement", method = RequestMethod.POST)
	public VOffenderAllSchedules processExternalMovement(
			final @RequestBody VOffenderAllSchedulesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		VOffenderAllSchedules searchResult = new VOffenderAllSchedules();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			searchResult = oidscexmService.processExternalMovement(commitBean);
			if(searchResult.getCheckSum()!=null && !(searchResult.getCheckSum().compareTo(BigDecimal.ZERO) == 0)) {
				prosmainService.enableTriggers(commitBean, authorization, "78");
			}
		} catch (Exception e) {
			logger.error("getLastMovementDateTime", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidscexm/suspendAllocations", method = RequestMethod.POST)
	public OffenderExternalMovements suspendAllocations(
			final @RequestBody VOffenderAllSchedules vOffenderAllSchedules) {
		OffenderExternalMovements searchResult = new OffenderExternalMovements();
		try {
			searchResult = oidscexmService.suspendAllocations(vOffenderAllSchedules);
		} catch (Exception e) {
			logger.error("suspendAllocations", e);
		}
		return searchResult;
	}
}
