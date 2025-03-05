package net.syscon.s4.cm.programsservices;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkills;
import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkillsCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OcduatteController
 */
@EliteController
public class OcduatteController {
	@Autowired
	private OcduatteService ocduatteService;
	@Autowired
	private ProsmainService prosmainService;
	@Autowired
	private OcdpatteService ocdpatteService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcduatteController.class.getName());

	/**
	 * getting rgAttendance LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/rgAttendanceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAttendanceRecordGroup(@RequestParam("pShowOutcome") final String pShowOutcome) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocduatteService.rgAttendanceRecordGroup(pShowOutcome);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgAttendanceRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSupervisor LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/rgSupervisorRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSupervisorRecordGroup(@RequestParam("crsActyId") final Long crsActyId) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocduatteService.rgSupervisorRecordGroup(crsActyId);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgSupervisorRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgBehaviour LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/rgBehaviourRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgBehaviourRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocduatteService.rgBehaviourRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgBehaviourRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWorkQuality LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/rgWorkQualityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkQualityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocduatteService.rgWorkQualityRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgWorkQualityRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProjects LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/rgProjectsRecordGroup", method = RequestMethod.GET)
	public List<CourseActivities> rgProjectsRecordGroup(@RequestParam("teamId") final String teamId) {
		List<CourseActivities> recordList = new ArrayList<>();
		try {
			recordList = ocduatteService.rgProjectsRecordGroup(teamId);
		} catch (final Exception e) {
			final CourseActivities obj = new CourseActivities();
			logger.error("rgProjectsRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProjects2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/rgProjects2RecordGroup", method = RequestMethod.GET)
	public List<CourseActivities> rgProjects2RecordGroup(@RequestParam("offenderBookId") final Long offenderBookId) {
		List<CourseActivities> recordList = new ArrayList<>();
		try {
			recordList = ocduatteService.rgProjects2RecordGroup(offenderBookId);
		} catch (final Exception e) {
			final CourseActivities obj = new CourseActivities();
			logger.error("rgProjects2RecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSkills LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/rgSkillsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSkillsRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocduatteService.rgSkillsRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgSkillsRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffCheck LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/rgStaffCheckRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffCheckRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<>();
		try {
			recordList = ocduatteService.rgStaffCheckRecordGroup();
		} catch (final Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("rgStaffCheckRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeams LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/rgTeamsRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamsRecordGroup() {
		
		List<Teams> recordList = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = ocduatteService.rgTeamsRecordGroup(userName);
		} catch (final Exception e) {
			final Teams obj = new Teams();
			logger.error("rgTeamsRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/offCourseAttendExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderCourseEvents> offCourseAttendExecuteQuery(
			@RequestBody final VOffenderCourseEvents searchBean) {
		List<VOffenderCourseEvents> searchResult = new ArrayList<>();
		try {
			searchResult = ocduatteService.offCourseAttendExecuteQuery(searchBean);
		} catch (final Exception e) {
			final VOffenderCourseEvents bean = new VOffenderCourseEvents();
			logger.error("offCourseAttendExecuteQuery :", e);
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocduatte/offCourseAttendCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offCourseAttendCommit(@RequestBody final VOffenderCourseEventsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		int liReturn = 0;
		try {
			liReturn = ocduatteService.offCourseAttendCommit(commitBean);
			if (1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "127");
			}
		} catch (final Exception e) {
			logger.error("offCourseAttendCommit :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/offCourseSkillsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCourseSkills> offCourseSkillsExecuteQuery(@RequestBody final OffenderCourseSkills searchBean) {
		List<OffenderCourseSkills> searchResult = new ArrayList<>();
		try {
			searchResult = ocduatteService.offCourseSkillsExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderCourseSkills bean = new OffenderCourseSkills();
			logger.error("offCourseSkillsExecuteQuery :", e);
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocduatte/offCourseSkillsCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderCourseSkills> offCourseSkillsCommit(
			@RequestBody final OffenderCourseSkillsCommitBean commitBean) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		List<OffenderCourseSkills> liReturn = new ArrayList<>();
		try {
			
			liReturn = ocduatteService.offCourseSkillsCommit(commitBean);
		} catch (final Exception e) {
			final OffenderCourseSkills bean = new OffenderCourseSkills();
			logger.error("Exception : offCourseSkillsCommit", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/compareDateTime", method = RequestMethod.POST)
	public Integer compareDateTime(@RequestBody final VOffenderCourseEvents actionObj) {
		Integer searchResult = null;
		try {
			searchResult = ocduatteService.compareDateTime(actionObj);
		} catch (final Exception e) {
			logger.error("compareDateTime :", e);
			return searchResult;
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/checkUa", method = RequestMethod.POST)
	public VOffenderCourseEvents checkUa(@RequestBody final VOffenderCourseEvents actionObj) {
		VOffenderCourseEvents vOffenderCourseEvents = new VOffenderCourseEvents();
		try {
			vOffenderCourseEvents = ocduatteService.checkUa(actionObj);
		} catch (final Exception e) {
			logger.error("Exception :", e);
			return vOffenderCourseEvents;
		}
		return vOffenderCourseEvents;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/getStaffName", method = RequestMethod.POST)
	public String getStaffName(@RequestBody final OffenderCourseSkills searchBean) {
		String searchResult = null;
		try {
			searchResult = ocduatteService.getStaffName(searchBean.getStaffId());
		} catch (final Exception e) {
			logger.error("Exception :", e);
			return searchResult;
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduatte/cancelFlagOutcomeList", method = RequestMethod.POST)
	public List<EventMeasureOutcomes> cancelFlagOutcomeList(@RequestBody EventMeasures eventMeasureObj) {
		List<EventMeasureOutcomes> recordList = new ArrayList<EventMeasureOutcomes>();
		try {
			recordList = ocdpatteService.cancelFlagOutcomeList(eventMeasureObj);
		} catch (Exception e) {
			logger.error("Exception : cancelFlagOutcomeList:", e);
		}
		return recordList;
	}


}
