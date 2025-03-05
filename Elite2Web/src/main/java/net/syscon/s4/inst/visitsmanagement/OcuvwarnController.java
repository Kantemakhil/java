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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.VisitorRestrictions;
import net.syscon.s4.im.beans.VisitorRestrictionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictionsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OcuvwarnController
 */
@EliteController
public class OcuvwarnController {
	@Autowired
	private OcuvwarnService ocuvwarnService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuvwarnController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuvwarn/offenderRestrictionExecuteQuery", method = RequestMethod.POST)
	public List<OffenderRestrictions> offenderRestrictionExecuteQuery(
			@RequestBody final OffenderRestrictions searchBean) {
		List<OffenderRestrictions> searchResult = new ArrayList<>();
		try {
			searchResult = ocuvwarnService.offenderRestrictionExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderRestrictions bean = new OffenderRestrictions();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
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
	@RequestMapping(value = "/ocuvwarn/offenderRestrictionCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offenderRestrictionCommit(
			@RequestBody final OffenderRestrictionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocuvwarnService.offenderRestrictionCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuvwarn/visitorRestrictionsExecuteQuery", method = RequestMethod.POST)
	public List<VisitorRestrictions> visitorRestrictionsExecuteQuery(
			@RequestBody final VisitorRestrictions searchBean) {
		List<VisitorRestrictions> searchResult = new ArrayList<>();
		try {
			searchResult = ocuvwarnService.visitorRestrictionsExecuteQuery(searchBean);
		} catch (Exception e) {
			final VisitorRestrictions bean = new VisitorRestrictions();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
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
	@RequestMapping(value = "/ocuvwarn/visitorRestrictionsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer visitorRestrictionsCommit(
			@RequestBody final VisitorRestrictionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocuvwarnService.visitorRestrictionsCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuvwarn/populateVisitorDetails", method = RequestMethod.POST)
	public List<VisitorRestrictions> populateVisitorDetails(@RequestBody final OffenderRestrictions searchBean) {
		List<VisitorRestrictions> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocuvwarnService.populateVisitorDetailsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuvwarn/getPersonNames", method = RequestMethod.POST)
	public Persons getPersonNames(@RequestBody final OffenderRestrictions searchBean) {
		Persons searchResult = new Persons();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocuvwarnService.getPersonNames(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * getting rgVisitTimeSlots LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuvwarn/getProfileValues", method = RequestMethod.GET)
	public SystemProfiles getProfileValues(@RequestParam(value = "profileType") final String profileType,
			@RequestParam(value = "profileCode") final String profileCode) {
		SystemProfiles recordList = new SystemProfiles();
		try {
			recordList = ocuvwarnService.getProfileValues(profileType, profileCode);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting rgVisitTimeSlots LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuvwarn/getOffenderNames", method = RequestMethod.GET)
	public Offenders getOffenderNames(@RequestParam(value = "offenderId") final Long offenderId) {
		Offenders recordList = new Offenders();
		try {
			recordList = ocuvwarnService.getOffenderNames(offenderId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUAVISN");
	}

}