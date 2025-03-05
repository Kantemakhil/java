package net.syscon.s4.programswithoutschedules;

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
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.programswithoutschedules.OiuvlcteService;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * OiuvlcteController
 */
@EliteController
public class OiuvlcteController {
	@Autowired
	private OiuvlcteService oiuvlcteService;
	@Autowired
	private CommonService commonService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuvlcteController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuvlcte/perExecuteQuery", method = RequestMethod.POST)
	public VAddresses perExecuteQuery(@RequestBody final CourseActivities searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		VAddresses searchResult = null;
		try {
			searchResult = oiuvlcteService.perExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("perExecuteQuery method : ", e);
		}
		return searchResult;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OIUVLCTE");
	}
}