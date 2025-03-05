package net.syscon.s4.inst.securitythreatgroupmaintenance;

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
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.VStgLocationMembers;
import net.syscon.s4.inst.securitythreatgroupsmaintenance.OiistgmbService;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * class OiistgmbController
 */
@EliteController
public class OiistgmbController {

	@Autowired
	private OiistgmbService oiistgmbService;
	@Autowired
	private CommonService commonService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiistgmbController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiistgmb/livingUnitsExecuteQuery", method = RequestMethod.POST)
	public List<LivingUnits> livingUnitsExecuteQuery(@RequestBody final LivingUnits searchBean) {
		List<LivingUnits> searchResult = new ArrayList<LivingUnits>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiistgmbService.livingUnitsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiistgmb/vStgLocationMembersExecuteQuery", method = RequestMethod.POST)
	public List<VStgLocationMembers> vStgLocationMembersExecuteQuery(
			@RequestBody final VStgLocationMembers searchBean) {
		List<VStgLocationMembers> searchResult = new ArrayList<VStgLocationMembers>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiistgmbService.vStgLocationMembersExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiistgmb/GetLocationDescription", method = RequestMethod.POST)
	public List<AgencyLocations> getLocationDescription(@RequestBody final AgencyLocations searchBean) {
		List<AgencyLocations> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiistgmbService.getLocationDescription(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIISTGMB");
	}

}