package net.syscon.s4.inst.movementexternal;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OidtrwjuController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidtrwjuController.class.getName());

	@Autowired
	private OidtrwjuService oidtrwjuService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OidreleaService oidreleaService;
	

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OffenderExternalMovements>
	 *         offEmSearchOffenderExternalMovements
	 * @Param OffenderExternalMovements searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/offEmExecuteQuery", method = RequestMethod.POST)
	public List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			@RequestBody final OffenderExternalMovements searchBean) {

		List<OffenderExternalMovements> searchResult = new ArrayList<>();
		final OffenderExternalMovements bean = new OffenderExternalMovements();
		try {

			searchResult = oidtrwjuService.offEmSearchOffenderExternalMovements(searchBean);
		} catch (Exception e) {
			logger.error("offEmExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param OffenderExternalMovementsCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidtrwju/offEmCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offEmCommit(@RequestBody final OffenderExternalMovementsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidtrwjuService.offEmCommit(commitBean);
			if(1==liReturn) {
				commitBean.setInsertList(null);
				prosmainService.enableTriggers(commitBean, authorization, "4");
			}
		} catch (Exception e) {

			logger.error("offEmCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkOffEmMovementReasonCo LOV values
	 * 
	 * @return List<MovementReason>cgfkOffEmMovementReasonCoRecordGroup
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/cgfkOffEmMovementReasonCoRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup() {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();

		final MovementReasons obj = new MovementReasons();
		try {

			recordList = oidtrwjuService.cgfkOffEmMovementReasonCoRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkOffEmMovementReasonCoRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmToAgyLocId LOV values
	 * 
	 * @return List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup
	 * @Param AgencyLocations agencyLocations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/cgfkOffEmToAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();

		try {
			recordList = oidtrwjuService.cgfkOffEmToAgyLocIdRecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error("cgfkOffEmToAgyLocIdRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<SystemProfiles> sysPflSearchSystemProfiles
	 * @Param SystemProfiles searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/systemPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflSearchSystemProfiles(@RequestBody final SystemProfiles searchBean) {

		List<SystemProfiles> searchResult = new ArrayList<>();
		final SystemProfiles bean = new SystemProfiles();
		try {

			searchResult = oidtrwjuService.sysPflSearchSystemProfiles(searchBean);
		} catch (Exception e) {
			logger.error("systemPflExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * method for query callings
	 * 
	 * @return OffenderExternalMovements
	 * @Param OffenderExternalMovements paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/offempreinsertc", method = RequestMethod.POST)
	public @ResponseBody OffenderExternalMovements offempreinsertc(
			@RequestBody final OffenderExternalMovements paramBean) {
		OffenderExternalMovements listOfRecords = null;
		try {
			listOfRecords = oidtrwjuService.offempreinsertc(paramBean);
		} catch (Exception e) {
			logger.error("offempreinsertc", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @Param ReferenceCodes paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/cgfkchkoffemoffemrefmovc", method = RequestMethod.POST)
	public @ResponseBody List<Object> cgfkchkoffemoffemrefmovc(@RequestBody final ReferenceCodes paramBean) {
		List<Object> dataObj = new ArrayList<Object>();
		try {

			dataObj = oidtrwjuService.cgfkchkoffemoffemrefmovc(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkoffemoffemrefmovc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @Param MovementReasons paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/cgfkchkoffemoffemmoversc", method = RequestMethod.POST)
	public @ResponseBody List<Object> cgfkchkoffemoffemmoversc(@RequestBody final MovementReasons paramBean) {
		List<Object> dataObj = new ArrayList<Object>();
		try {

			dataObj = oidtrwjuService.cgfkchkoffemoffemmoversc(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkoffemoffemmoversc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @Param MovementReason paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/cgfkchkoffemoffemmove2c", method = RequestMethod.POST)
	public @ResponseBody List<Object> cgfkchkoffemoffemmovetoc(@RequestBody final MovementReasons paramBean) {
		List<Object> dataObj = new ArrayList<Object>();

		try {

			dataObj = oidtrwjuService.cgfkchkoffemoffemmovetoc(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkoffemoffemmove2c", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @Param AgencyLocations paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/cgfkchkoffemoffemagylocc", method = RequestMethod.POST)
	public @ResponseBody List<Object> cgfkchkoffemoffemagylocc(@RequestBody final AgencyLocations paramBean) {
		List<Object> dataObj = new ArrayList<Object>();
		try {

			dataObj = oidtrwjuService.cgfkchkoffemoffemagylocc(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkoffemoffemagylocc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Dual>
	 * @Param Dual paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/cgwhennewforminstancec", method = RequestMethod.GET)
	public @ResponseBody List<Dual> cgwhennewforminstancec(@RequestBody final Dual paramBean) {
		List<Dual> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = oidtrwjuService.cgwhennewforminstancec(paramBean);
		} catch (Exception e) {
			logger.error("cgwhennewforminstancec", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/checkWaitListAndLocations", method = RequestMethod.POST)
	public @ResponseBody Integer checkWaitListAndLocations(@RequestBody final OffenderExternalMovements bean) {
		Integer listOfRecords = null;
		try {
			listOfRecords = oidtrwjuService.checkWaitListAndLocation(bean);
		} catch (Exception e) {
			logger.error("checkWaitListAndLocations", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/suspendAllocations", method = RequestMethod.POST)
	public @ResponseBody Integer suspendAllocations(@RequestBody final OffenderExternalMovements bean) {
		Integer listOfRecords = 0;
		try {
			if (bean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				bean.setCreateUserId(userName);
			}
			listOfRecords = oidtrwjuService.suspendAllocations(bean);
		} catch (Exception e) {
			logger.error("suspendAllocations", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidtrwju/endWaitlistAndAllocations", method = RequestMethod.POST)
	public @ResponseBody Integer endWaitlistAndAllocations(@RequestBody final OffenderExternalMovements bean) {
		Integer listOfRecords = 0;
		try {
			if (bean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				bean.setCreateUserId(userName);
			}
			listOfRecords = oidtrwjuService.endWaitlistAndAllocations(bean);
		} catch (Exception e) {
			logger.error("endWaitlistAndAllocations", e);
		}
		return listOfRecords;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidtrwju/offBookingCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offBookingCommit(@RequestBody final VHeaderBlock commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidreleaService.offBookingUpdateOffenderExternalMovements(commitBean);
		} catch (Exception e) {
			logger.error("offBookingCommit", e);
		}
		return liReturn;
	}
}