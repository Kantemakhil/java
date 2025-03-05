package net.syscon.s4.cm.programsservices;

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

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcupatofController {
	@Autowired
	private OcupatofService ocupatofService;
	@Autowired
	private OcdpatteService ocdpatteService;
	@Autowired
	private CommonService commonService;

	/**
	 * Logger object used to print the log in the file
	 */

	private static Logger logger = LogManager.getLogger(OcupatofController.class.getName());

	/**
	 * getting rgAttendances LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocupatof/rgAttendancesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAttendancesRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocupatofService.rgAttendancesRecordGroup();
		} catch (Exception e) {
		}
		return recordList;
	}

	/**
	 * getting rgAttendancyView LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocupatof/rgAttendancyViewRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAttendancyViewRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocupatofService.rgAttendancyViewRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgEngagement LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocupatof/rgEngagementRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEngagementRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocupatofService.rgEngagementRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgUnderstanding LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocupatof/rgUnderstandingRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgUnderstandingRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocupatofService.rgUnderstandingRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocupatof/offCrsAttExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCourseAttendance> offCrsAttExecuteQuery(
			@RequestBody final OffenderCourseAttendance searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<OffenderCourseAttendance> searchResult = new ArrayList<>();
		try {
			searchResult = ocupatofService.offCrsAttExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring update operation
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocupatof/offCrsAttCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offCrsAttCommit(@RequestBody final OffenderCourseAttendancesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocupatofService.offCrsAttCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocupatof/cancelFlagOutcomeList", method = RequestMethod.POST)
	public List<EventMeasureOutcomes> cancelFlagOutcomeList(@RequestBody EventMeasures eventMeasureObj) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<EventMeasureOutcomes> recordList = new ArrayList<EventMeasureOutcomes>();
		try {
			recordList = ocdpatteService.cancelFlagOutcomeList(eventMeasureObj);
		} catch (Exception e) {
			logger.error("Exception : cancelFlagOutcomeList:", e);
		}
		return recordList;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUPATOF");
	}

}