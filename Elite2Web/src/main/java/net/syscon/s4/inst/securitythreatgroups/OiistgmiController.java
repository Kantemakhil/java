package net.syscon.s4.inst.securitythreatgroups;

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
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.VStgMembershipInquiry;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OiistgmiController {
	@Autowired
	private OiistgmiService oiistgmiService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiistgmiController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiistgmi/vStgMembershipInquiryExecuteQuery", method = RequestMethod.POST)
	public List<VStgMembershipInquiry> vStgMembershipInquiryExecuteQuery(
			@RequestBody final VStgMembershipInquiry searchBean) {
		List<VStgMembershipInquiry> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiistgmiService.vStgMembershipInquiryExecuteQuery(searchBean);
		} catch (Exception e) {
			final VStgMembershipInquiry bean = new VStgMembershipInquiry();
			logger.error("In vStgMembershipInquiryExecuteQuery method : ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiistgmi/getStgGroupDescription", method = RequestMethod.POST)
	public String getStgGroupDescription(@RequestBody final SecurityThreatGroups searchBean) {
		String searchResult = null;
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiistgmiService.getStgGroupDescription(searchBean);
		} catch (Exception e) {
			logger.error("In getStgGroupDescription method : ", e);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIISTGMI");
	}

}