package net.syscon.s4.cm.programsservices;

import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcuoscpvController {
	@Autowired
	private OcuoscpvService ocuoscpvService;
	@Autowired
	private CommonService commonService;

	private static Logger logger = LogManager.getLogger(OcuoscpvController.class.getName());

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/crsActExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivities> crsActExecuteQuery(@RequestBody final CourseActivities searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseActivities> searchResult = new ArrayList<>();
		try {
			searchResult = ocuoscpvService.crsActExecuteQuery(searchBean);
		} catch (Exception e) {

			logger.error("Exception in crsActExecuteQuery Ocuoscpv:", e);

		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/crsScheduleRulExecuteQuery", method = RequestMethod.POST)
	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(@RequestBody final CourseScheduleRules searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseScheduleRules> searchResult = new ArrayList<>();
		try {
			searchResult = ocuoscpvService.crsScheduleRulExecuteQuery(searchBean);
		} catch (Exception e) {

			logger.error("Exception in crsScheduleRulExecuteQuery Ocuoscpv:", e);

		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/offSchExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderCourseEvents> offSchExecuteQuery(@RequestBody final VOffenderCourseEvents searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<VOffenderCourseEvents> searchResult = new ArrayList<>();
		try {
			searchResult = ocuoscpvService.offSchExecuteQuery(searchBean);
		} catch (Exception e) {

			logger.error("Exception in offSchExecuteQuery Ocuoscpv:", e);

		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/offSchCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offSchCommit(@RequestBody final VOffenderCourseEventsCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuoscpvService.offSchCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception in offSchCommit Ocuoscpv:", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/weeklyDefExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCourseApptGrp> weeklyDefExecuteQuery(@RequestBody final OffenderCourseApptGrp searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<OffenderCourseApptGrp> searchResult = new ArrayList<>();
		try {
			searchResult = ocuoscpvService.weeklyDefExecuteQuery(searchBean);
		} catch (Exception e) {

			logger.error("Exception in weeklyDefExecuteQuery Ocuoscpv:", e);

		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/weeklyDefCommit", method = RequestMethod.POST)
	public @ResponseBody Integer weeklyDefCommit(@RequestBody final OffenderCourseApptGrpCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuoscpvService.weeklyDefCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception in weeklyDefCommit Ocuoscpv:", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/offSchDefExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCourseApptRule> offSchDefExecuteQuery(@RequestBody final OffenderCourseApptRule searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<OffenderCourseApptRule> searchResult = new ArrayList<>();
		try {
			searchResult = ocuoscpvService.offSchDefExecuteQuery(searchBean);
		} catch (Exception e) {

			logger.error("Exception in offSchDefExecuteQuery Ocuoscpv:", e);

		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/offSchDefCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offSchDefCommit(@RequestBody final OffenderCourseApptRulesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuoscpvService.offSchDefCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception in offSchDefCommit Ocuoscpv:", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/offschGettingWeekDay", method = RequestMethod.POST)
	public @ResponseBody String offSchWeekDay(@RequestBody final VOffenderCourseEvents searchBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		String liReturn = null;
		try {
			liReturn = ocuoscpvService.gettingWeekday(searchBean);
		} catch (Exception e) {

			logger.error("Exception in offschGettingWeekDay Ocuoscpv:", e);

		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/offSchCheckScheduleConflict", method = RequestMethod.POST)
	public @ResponseBody Integer offSchCheckScheduleConflict(@RequestBody final VOffenderCourseEvents searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			liReturn = ocuoscpvService.gettingoffSchCheckScheduleConflict(searchBean);
		} catch (Exception e) {
			logger.error("Exception in offSchCheckScheduleConflict Ocuoscpv:", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/weeklyDefgettingdate", method = RequestMethod.POST)
	public @ResponseBody Date weeklyDefDate(@RequestBody final OffenderCourseApptGrp searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Date liReturn = null;
		try {
			liReturn = ocuoscpvService.gettingStartDate(searchBean);
		} catch (Exception e) {
			logger.error("Exception in weeklyDefDate Ocuoscpv:", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/copyfromprovideravailability", method = RequestMethod.POST)
	public List<CourseScheduleRules> copyFromProviderAvailability(@RequestBody final CourseScheduleRules searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseScheduleRules> searchResult = new ArrayList<>();
		try {
			searchResult = ocuoscpvService.copyFromProviderAvailability(searchBean);
		} catch (Exception e) {

			logger.error("Exception in copyFromProviderAvailability Ocuoscpv:", e);

		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoscpv/offPrgProfilesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProgramProfiles> offPrgProfilesExecuteQuery(@RequestBody final OffenderProgramProfiles searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<OffenderProgramProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocuoscpvService.offPrgProfilesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in offSchExecuteQuery Ocuoscpv:", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocuoscpv/offPrgProfilesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offPrgProfilesCommit(@RequestBody final OffenderCourseApptGrpCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		int liReturn = 0;
		try {
			
		} catch (Exception e) {

			logger.error("Exception in offSchCommit Ocuoscpv:", e);
		}
		return liReturn;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUOSCPV");
	}
}