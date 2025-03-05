package net.syscon.s4.inst.movementexternal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SystemProfiles;

/**
 * class OidunctaController
 */
@EliteController
public class OidunctaController {
	@Autowired
	private OidunctaService oidunctaService;
	
	@Autowired
	private OidbutabService oidbutabService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidunctaController.class.getName());

	/**
	 * getting cgfkOffEmFromAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiduncta/cgfkOffEmFromAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffEmFromAgyLocIdRecordGroup(
			@RequestParam(value = "toagyLocId") final String toagyLocId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations obj = new AgencyLocations();
		try {
			recordList = oidunctaService.cgfkOffEmFromAgyLocIdRecordGroup(toagyLocId);
		} catch (Exception e) {
			logger.error("cgfkOffEmFromAgyLocIdRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmToAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiduncta/cgfkOffEmToAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup(
			@RequestParam(value = "directionCode") final String directionCode,
			@RequestParam(value = "fromAgyLocId") final String fromAgyLocId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations obj = new AgencyLocations();
		try {
			recordList = oidunctaService.cgfkOffEmToAgyLocIdRecordGroup(directionCode, fromAgyLocId);
		} catch (Exception e) {
			logger.error("cgfkOffEmToAgyLocIdRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmMovementType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiduncta/cgfkOffEmMovementTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidunctaService.cgfkOffEmMovementTypeRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkOffEmMovementTypeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmMovementReasonCo LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiduncta/cgfkOffEmMovementReasonCoRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmMovementReasonCoRecordGroup(
			@RequestParam(value = "movementType") final String movementType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidunctaService.cgfkOffEmMovementReasonCoRecordGroup(movementType);
		} catch (Exception e) {
			logger.error("cgfkOffEmMovementReasonCoRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmToCity LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiduncta/cgfkOffEmToCityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmToCityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oidunctaService.cgfkOffEmToCityRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkOffEmToCityRecordGroup: ", e);
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
	@RequestMapping(value = "/oiduncta/offEmExecuteQuery", method = RequestMethod.POST)
	public OffenderExternalMovements offEmExecuteQuery(@RequestBody final OffenderExternalMovements searchBean) {
		OffenderExternalMovements searchResult = new OffenderExternalMovements();
		try {
			searchResult = oidunctaService.offEmExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offEmExecuteQuery: ", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiduncta/offEmCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offEmCommit(@RequestBody final OffenderExternalMovementsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (Optional.ofNullable(commitBean).isPresent()) {
				final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
						.toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidunctaService.offEmCommit(commitBean);
		} catch (Exception e) {
			logger.error("offEmCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiduncta/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		final SystemProfiles bean = new SystemProfiles();
		try {
			searchResult = oidunctaService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery: ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting cgfkOffEmToAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiduncta/cgfkchkOffEmOffEmAgyLoc", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkchkOffEmOffEmAgyLoc() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations obj = new AgencyLocations();
		try {
			recordList = oidunctaService.cgfkchkOffEmOffEmAgyLoc();
		} catch (Exception e) {
			logger.error("cgfkchkOffEmOffEmAgyLoc: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 * getting rgInstitution LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiduncta/rgInstitutionRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgInstitutionRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations obj = new AgencyLocations();
		try {
			recordList = oidbutabService.rgInstitutionRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
}