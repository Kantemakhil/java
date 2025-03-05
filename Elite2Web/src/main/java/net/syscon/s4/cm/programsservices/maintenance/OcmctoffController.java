package net.syscon.s4.cm.programsservices.maintenance;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcmctoffController {
	@Autowired
	private OcmctoffService ocmctoffService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmctoffController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmctoff/crPrfGdExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivityProfiles> crPrfGdExecuteQuery(@RequestBody final CourseActivityProfiles searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseActivityProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmctoffService.crPrfGdExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmctoff/crPrfGdCommit", method = RequestMethod.POST)
	public @ResponseBody Integer crPrfGdCommit(@RequestBody final CourseActivityProfilesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmctoffService.crPrfGdCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Ocmctoff", e);
		}
		return liReturn;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCMCTOFF");
	}

}