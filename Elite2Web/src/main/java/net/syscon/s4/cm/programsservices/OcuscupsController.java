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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcuscupsController {
	@Autowired
	private OcuscupsService ocuscupsService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuscupsController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuscups/offCrsAttendExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCourseAttendance> offCrsAttendExecuteQuery(
			@RequestBody final OffenderCourseAttendance searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<OffenderCourseAttendance> searchResult = new ArrayList<>();
		try {
			searchResult = ocuscupsService.offCrsAttendExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderCourseAttendance bean = new OffenderCourseAttendance();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/ocuscups/offCrsAttendCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offCrsAttendCommit(@RequestBody final OffenderCourseAttendancesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuscupsService.offCrsAttendCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Ocuscups", e);
		}
		return liReturn;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUSCUPS");
	}
}