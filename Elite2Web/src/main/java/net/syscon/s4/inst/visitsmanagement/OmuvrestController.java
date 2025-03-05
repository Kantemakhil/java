package net.syscon.s4.inst.visitsmanagement;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VisitorRestrictions;
import net.syscon.s4.im.beans.VisitorRestrictionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OmuvrestController {

	@Autowired
	private OmuvrestService omuvrestService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmuvrestController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omuvrest/perExecuteQuery", method = RequestMethod.POST)
	public List<Persons> perExecuteQuery(@RequestBody final Persons searchBean) {
		List<Persons> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = omuvrestService.perExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("perExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * getting rgVisrRestVisitRestricti LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omuvrest/rgVisrRestVisitRestrictiRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgVisrRestVisitRestrictiRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = omuvrestService.rgVisrRestVisitRestrictiRecordGroup();
		} catch (Exception e) {
			logger.error("rgVisrRestVisitRestrictiRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omuvrest/visrRestExecuteQuery", method = RequestMethod.POST)
	public List<VisitorRestrictions> visrRestExecuteQuery(@RequestBody final VisitorRestrictions searchBean) {
		List<VisitorRestrictions> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = omuvrestService.visrRestExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("visrRestExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omuvrest/visrRestCommit", method = RequestMethod.POST)
	public @ResponseBody Integer visrRestCommit(@RequestBody final VisitorRestrictionsCommitBean commitBean) {
		int liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = omuvrestService.visrRestCommit(commitBean);
		} catch (Exception e) {
			logger.error("visrRestCommit", e);
		}
		return liReturn;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OSIPSEAR");
	}
}