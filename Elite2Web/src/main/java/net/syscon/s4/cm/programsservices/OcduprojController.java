package net.syscon.s4.cm.programsservices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OcduprojController {
	@Autowired
	private OcduprojService ocduprojService;
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OcuoscpvController ocuoscpvController;
	
	@Autowired
	private OcdpatteService ocdpatteService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcduprojController.class.getName());
	
	/**
	 * getting rgAttendance LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/rgAttendanceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAttendanceRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocduprojService.rgAttendanceRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgSupervisor LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/rgSupervisorRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSupervisorRecordGroup(@RequestParam("crsActyId") final Long crsActyId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocduprojService.rgSupervisorRecordGroup(crsActyId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgBehaviour LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/rgBehaviourRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgBehaviourRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocduprojService.rgBehaviourRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgWorkQuality LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/rgWorkQualityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWorkQualityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocduprojService.rgWorkQualityRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgProjectCheck LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/rgProjectCheckRecordGroup", method = RequestMethod.GET)
	public List<CourseActivities> rgProjectCheckRecordGroup() {
		List<CourseActivities> recordList = new ArrayList<CourseActivities>();
		try {
			recordList = ocduprojService.rgProjectCheckRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgProject LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/rgProjectRecordGroup", method = RequestMethod.GET)
	public List<CourseActivities> rgProjectRecordGroup(
			@RequestParam(value = "offenderBookId") final Long offenderBookId) {
		List<CourseActivities> recordList = new ArrayList<CourseActivities>();
		try {
			recordList = ocduprojService.rgProjectRecordGroup(offenderBookId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgSkills LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/rgSkillsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSkillsRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocduprojService.rgSkillsRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgStaffCheck LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/rgStaffCheckRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffCheckRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocduprojService.rgStaffCheckRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgAdjReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/rgAdjReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAdjReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocduprojService.rgAdjReasonRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/unpaidWkExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderSentCondActs> unpaidWkExecuteQuery(@RequestBody final VOffenderSentCondActs searchBean) {// Grid1
		List<VOffenderSentCondActs> searchResult = new ArrayList<>();
		try {
			searchResult = ocduprojService.unpaidWkExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/projAllocExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProgramProfiles> projAllocExecuteQuery(@RequestBody final OffenderProgramProfiles searchBean) {// Grid2
		List<OffenderProgramProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocduprojService.projAllocExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/projAllocOnDeleteQuery", method = RequestMethod.POST)
	public Integer projAllocOnDeleteQuery(@RequestBody final OffenderProgramProfiles searchBean) {
		int count = 0;
		try {
			count = ocduprojService.projAllocOnDeleteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocduproj/projAllocCommit", method = RequestMethod.POST)
	public @ResponseBody Integer projAllocCommit(@RequestBody final OffenderProgramProfilesCommitBean commitBean,
			@RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocduprojService.projAllocCommit(commitBean);
			if (1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "41");
			}
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/attendanceExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderCourseEvents> attendanceExecuteQuery(@RequestBody final VOffenderCourseEvents searchBean) { // Grid3
		List<VOffenderCourseEvents> searchResult = new ArrayList<>();
		try {
			searchResult = ocduprojService.attendanceExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocduproj/attendanceCommit", method = RequestMethod.POST)
	public @ResponseBody Integer attendanceCommit(@RequestBody final VOffenderCourseEventsCommitBean commitBean,
			@RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocduprojService.attendanceCommit(commitBean);
			if (0 != liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "42");
			}
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/skillsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCourseSkills> skillsExecuteQuery(@RequestBody final OffenderCourseSkills searchBean) {
		List<OffenderCourseSkills> searchResult = new ArrayList<>();
		try {
			searchResult = ocduprojService.skillsExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocduproj/skillsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer skillsCommit(@RequestBody final OffenderCourseSkillsCommitBean commitBean,
			@RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocduprojService.skillsCommit(commitBean);
			if (1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "43");
			}
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/creditAdjExecuteQuery", method = RequestMethod.POST)
	public List<OffenderUnpaidWorkAdj> creditAdjExecuteQuery(@RequestBody final OffenderUnpaidWorkAdj searchBean) { 
		List<OffenderUnpaidWorkAdj> searchResult = new ArrayList<>();
		try {
			searchResult = ocduprojService.creditAdjExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocduproj/creditAdjCommit", method = RequestMethod.POST)
	public @ResponseBody Integer creditAdjCommit(@RequestBody final OffenderUnpaidWorkAdjCommitBean commitBean,
			@RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocduprojService.creditAdjCommit(commitBean);
			if (1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "44");
			}
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/viewLink", method = RequestMethod.GET)
	public List<ReferenceCodes> dspDescriptionRecordGroup() {
		ReferenceCodes listOne = new ReferenceCodes();
		ReferenceCodes listTwo = new ReferenceCodes();
		listOne.setCode("1");
		listOne.setDescription("All");
		listTwo.setCode("2");
		listTwo.setDescription("Outstanding");
		List<ReferenceCodes> viewList = Arrays.asList(listOne, listTwo);
		return viewList;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/reaonsReferenceCodes", method = RequestMethod.GET)
	public List<ReferenceCodes> reaonsReferenceCodes() { 
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocduprojService.reaonsReferenceCodes();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	@GetMapping("/ocduproj/getActiveFlagCount")
	public Integer getActiveFlagCount(Long offenderId) {
		return ocduprojService.getActiveFlag(offenderId);
	}
	@GetMapping("/ocduproj/getLastAndFirstName")
	public List<VHeaderBlock> getLastAndFirstName(Long offenderId){
		return ocduprojService.getLastAndFirstName(offenderId);
	}


	@PreAuthorize("hasEliteRole('read')")
	@GetMapping("/ocduproj/weeklyDefExecuteQuery")
	public List<OffenderCourseApptGrp> weeklyDefExecuteQuery(BigDecimal offPrgrefId) {
		List<OffenderCourseApptGrp> searchResult = new ArrayList<>();
		OffenderCourseApptGrp searchBean=new OffenderCourseApptGrp();
		searchBean.setOffPrgrefId(offPrgrefId);
		try {
			searchResult = ocuoscpvController.weeklyDefExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in weeklyDefExecuteQuery ocduproj:", e);
		}
		return searchResult;
	}

	@PostMapping("/ocduproj/checkingNonAssociations")
	public String checkingNonAssociations(@RequestBody OffenderProgramProfilesCommitBean commitBean) {
		try {
		return ocduprojService.checkingNonAssociations(commitBean);
		}catch(Exception e) {
			logger.error("Exception in checkingNonAssociation ocduproj:", e);
			return ApplicationConstants.EMPTYDATA;
		}
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocduproj/cancelFlagOutcomeList", method = RequestMethod.POST)
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