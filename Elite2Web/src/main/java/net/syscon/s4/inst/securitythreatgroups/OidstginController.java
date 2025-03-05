package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.pkgs.common.CommonService;

/**
 */
@EliteController
public class OidstginController {
	@Autowired
	private OidstginService oidstginService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstginController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgin/agyIncExecuteQuery", method = RequestMethod.POST)
	public List<AgencyIncidents> agyIncExecuteQuery(@RequestBody final AgencyIncidents searchBean) {
		List<AgencyIncidents> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstginService.agyIncExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("agyIncExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgin/agencyIncidentPartiesExecuteQuery", method = RequestMethod.POST)
	public List<AgencyIncidentParties> agencyIncidentPartiesExecuteQuery(
			@RequestBody final AgencyIncidentParties searchBean) {
		List<AgencyIncidentParties> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstginService.agencyIncidentPartiesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("agencyIncidentPartiesExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param ofenderIdDisplay
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgin/getBookingNo", method = RequestMethod.GET)
	public String getBookingNo(@RequestParam(value = "offenderIdDisplay") final BigDecimal ofenderIdDisplay) {
		String returnVal = "";
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			returnVal = oidstginService.offenderIdDisplay(ofenderIdDisplay, userId);
		} catch (Exception e) {
			logger.error("getBookingNo", e);
		}
		return returnVal;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIDSTGIN");
	}
}