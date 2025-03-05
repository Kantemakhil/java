package net.syscon.s4.inst.institutionalactivities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidpactiController
 */
@EliteController
public class OidpactiController {
	@Autowired
	private OidpactiService oidpactiService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidpactiController.class.getName());

	/**
	 * getting rgEstablishment LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/rgEstablishmentRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgEstablishmentRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.rgEstablishmentRecordGroup(caseloadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception in rgEstablishmentRecordGroup: Oidpacti:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgServices LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/rgServicesRecordGroup", method = RequestMethod.GET)
	public List<VPrisonActivities> rgServicesRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<VPrisonActivities> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.rgServicesRecordGroup(agyLocId);
		} catch (Exception e) {
			VPrisonActivities obj = new VPrisonActivities();
			logger.error("Exception in rgServicesRecordGroup: Oidpacti:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgServices LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/getServices", method = RequestMethod.GET)
	public List<VPrisonActivities> getServices() {
		List<VPrisonActivities> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.getServices();
		} catch (Exception e) {
			logger.error("Exception in getServices: Oidpacti:", e);
		}
		return recordList;
	}

	/**
	 * getting rgEndReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/rgEndReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEndReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.rgEndReasonRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgEndReasonRecordGroup: Oidpacti:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting pgPsRejRsn LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/pgPsRejRsnRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> pgPsRejRsnRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.pgPsRejRsnRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in pgPsRejRsnRecordGroup: Oidpacti:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPerformance LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/rgPerformanceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPerformanceRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.rgPerformanceRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgPerformanceRecordGroup: Oidpacti:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPriority LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/rgPriorityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.rgPriorityRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgPriorityRecordGroup: Oidpacti:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgDecision LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/rgDecisionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDecisionRecordGroup(@RequestParam(value = "systemMode") final String systemMode) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.rgDecisionRecordGroup(systemMode);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgDecisionRecordGroup: Oidpacti:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAttendence LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/rgAttendenceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAttendenceRecordGroup(
			@RequestParam(value = "pshowoutcome") final String pshowoutcome) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.rgAttendenceRecordGroup(pshowoutcome);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgAttendenceRecordGroup: Oidpacti:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting lovServices2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/lovServices2RecordGroup", method = RequestMethod.GET)
	public List<VPrisonActivities> lovServices2RecordGroup() {
		List<VPrisonActivities> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.lovServices2RecordGroup();
		} catch (Exception e) {
			VPrisonActivities obj = new VPrisonActivities();
			logger.error("Exception in lovServices2RecordGroup: Oidpacti:", e);
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
	@RequestMapping(value = "/oidpacti/offProgProfExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProgramProfiles> offProgProfExecuteQuery(@RequestBody OffenderProgramProfiles searchBean) {
		List<OffenderProgramProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidpactiService.offProgProfExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderProgramProfiles bean = new OffenderProgramProfiles();
			logger.error("Exception in offProgProfExecuteQuery:", e);
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
	@RequestMapping(value = "/oidpacti/offProgProfCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderProgramProfiles offProgProfCommit(
			@RequestBody OffenderProgramProfilesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		OffenderProgramProfiles liReturn = new OffenderProgramProfiles();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidpactiService.offProgProfCommit(commitBean);
			if(liReturn != null) {
				prosmainService.enableTriggers(commitBean, authorization, "27");
			}
		} catch (Exception e) {

			logger.error("Exception in offProgProfCommit: Oidpacti", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/vOffCourseEvntsExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderCourseEvents> vOffCourseEvntsExecuteQuery(@RequestBody VOffenderCourseEvents searchBean) {
		List<VOffenderCourseEvents> searchResult = new ArrayList<>();
		try {
			searchResult = oidpactiService.vOffCourseEvntsExecuteQuery(searchBean);
		} catch (Exception e) {
			VOffenderCourseEvents bean = new VOffenderCourseEvents();
			logger.error("Exception in vOffCourseEvntsExecuteQuery:", e);
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
	@RequestMapping(value = "/oidpacti/vOffCourseEvntsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vOffCourseEvntsCommit(@RequestBody VOffenderCourseEventsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidpactiService.vOffCourseEvntsCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "28");
			}
		} catch (Exception e) {

			logger.error("Exception in vOffCourseEvntsCommit: Oidpacti", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/offenderProgramProfileswaitExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProgramProfiles> offenderProgramProfileswaitExecuteQuery(
			@RequestBody OffenderProgramProfiles searchBean) {
		List<OffenderProgramProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidpactiService.offenderProgramProfileswaitExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderProgramProfiles bean = new OffenderProgramProfiles();
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidpacti/offenderProgramProfiles2Commit", method = RequestMethod.POST)
	public @ResponseBody OffenderProgramProfiles offenderProgramProfiles2Commit(
			@RequestBody OffenderProgramProfilesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		OffenderProgramProfiles liReturn = new OffenderProgramProfiles();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidpactiService.offenderProgramProfiles2Commit(commitBean);
			if(liReturn != null) {
				prosmainService.enableTriggers(commitBean, authorization, "29");
			}
		} catch (Exception e) {

			logger.error("Exception in offenderProgramProfiles2Commit: Oidpacti", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidpacti/getCourseActivity", method = RequestMethod.POST)
	public @ResponseBody OffenderProgramProfiles getCourseActivity(@RequestBody OffenderProgramProfiles bean) {
		OffenderProgramProfiles liReturn = new OffenderProgramProfiles();
		try {
			liReturn = oidpactiService.getCourseActivity(bean);
		} catch (Exception e) {

			logger.error("Exception in getCourseActivity: Oidpacti", e);
		}
		return liReturn;
	}

	/**
	 * checking wait list
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/checkWaitList2", method = RequestMethod.POST)
	public Map<String, Object> checkWaitList2(@RequestBody final OffenderProgramProfiles searchRecord) {
		Map<String, Object> waitList = null;
		try {
			waitList = oidpactiService.checkWaitList2(searchRecord);
		} catch (Exception e) {
			logger.error("Exception in checkWaitList2:", e);
		}
		return waitList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/courseVacancy", method = RequestMethod.POST)
	public Integer courseVacancy(@RequestBody final OffenderProgramProfiles objPrograms) {
		Integer lireturn = 0;
		try {
			lireturn = oidpactiService.courseVacancy(objPrograms);
		} catch (Exception e) {
			logger.error("In courseVacancy method : ", e);
		}
		return lireturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/cntAsnCur", method = RequestMethod.POST)
	public Integer cntAsnCur(@RequestBody final OffenderProgramProfiles objPrograms) {
		Integer lireturn = 0;
		try {
			lireturn = oidpactiService.cntAsnCur(objPrograms);
		} catch (Exception e) {
			logger.error("In cntAsnCur method : ", e);
		}
		return lireturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/assignCommit", method = RequestMethod.POST)
	public OffenderProgramProfiles assignCommit(@RequestBody final OffenderProgramProfiles objPrograms) {
		OffenderProgramProfiles lireturn = new OffenderProgramProfiles();
		try {
			lireturn = oidpactiService.assignCommit(objPrograms);
		} catch (Exception e) {
			logger.error("In assignCommit method : ", e);
		}
		return lireturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/getProfileValue", method = RequestMethod.GET)
	public String getProfileValue() {
		String lireturn = null;
		try {
			lireturn = oidpactiService.getProfileValue();
		} catch (Exception e) {
			logger.error("In getProfileValue method : ", e);
		}
		return lireturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/getAdmissionDate", method = RequestMethod.POST)
	public Date getAdmissionDate(@RequestBody final OffenderProgramProfiles objPrograms) {
		Date lireturn = null;
		try {
			lireturn = oidpactiService.getAdmissionDate(objPrograms);
		} catch (Exception e) {
			logger.error("In getAdmissionDate method : ", e);
		}
		return lireturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/checkConflict", method = RequestMethod.POST)
	public ProgramsNonAssocTmp checkConflict(@RequestBody final OffenderProgramProfiles objPrograms) {
		ProgramsNonAssocTmp lireturn = new ProgramsNonAssocTmp();
		try {
			lireturn = oidpactiService.checkConflict(objPrograms);
		} catch (Exception e) {
			logger.error("In checkConflict method : ", e);
		}
		return lireturn;

	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/getBookingDate", method = RequestMethod.POST)
	public Date getBookingDate(@RequestBody final VHeaderBlock objPrograms) {
		Date lireturn = null;
		try {
			lireturn = oidpactiService.getBookingDate(objPrograms);
		} catch (Exception e) {
			logger.error("In getBookingDate method : ", e);
		}
		return lireturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpacti/checkNonAssociationsData", method = RequestMethod.POST)
	public String checkNonAssociations(@RequestBody final OffenderProgramProfiles objPrograms) {
		String resultString = null;
		try {
			if (objPrograms != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				objPrograms.setCreateUserId(userName);
			}
			resultString = oidpactiService.checkNonAssociations(objPrograms);
		} catch (final Exception e) {
			logger.error("Error occured in checkNonAssociations :", e);
		}
		return resultString;
	}
	
}