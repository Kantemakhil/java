package net.syscon.s4.cm.programsservices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.InternalLocationUsages;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OcdprogrController
 */
@EliteController
public class OcdprogrController {
	@Autowired
	private OcdprogrService ocdprogrService;
	@Autowired
	private ProsmainService prosmainService;
	@Autowired
	private OcusmoduService ocusmoduService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdprogrController.class.getName());
	
	/**
	 * getting rgOffPrgSts LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgOffPrgStsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOffPrgStsRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdprogrService.rgOffPrgStsRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgIntLocation LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgIntLocationRecordGroup", method = RequestMethod.GET)
	public List<InternalLocationUsages> rgIntLocationRecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<InternalLocationUsages> recordList = new ArrayList<>();
		try {
			recordList = ocdprogrService.rgIntLocationRecordGroup(agyLocId);
		} catch (Exception e) {
			IntLocUsageLocations obj = new IntLocUsageLocations();
			logger.error("Exception : Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgProgramServices LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgProgramServicesRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgProgramServicesRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = ocdprogrService.rgProgramServicesRecordGroup();
		} catch (Exception e) {
			ProgramServices obj = new ProgramServices();
			logger.error("Exception : Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPsPrgAvail LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgPsPrgAvailRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsPrgAvailRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdprogrService.rgPsPrgAvailRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOffenderSentences LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgOffenderSentencesRecordGroup", method = RequestMethod.GET)
	public List<OffenderSentences> rgOffenderSentencesRecordGroup(
			@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		List<OffenderSentences> recordList = new ArrayList<>();
		try {
			recordList = ocdprogrService.rgOffenderSentencesRecordGroup(offenderBookId);
		} catch (Exception e) {
			OffenderSentences obj = new OffenderSentences();
			logger.error("Exception : Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgEventSubTypes LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgEventSubTypesRecordGroup", method = RequestMethod.GET)
	public List<EventMeasures> rgEventSubTypesRecordGroup() {
		List<EventMeasures> recordList = new ArrayList<EventMeasures>();
		try {
			recordList = ocdprogrService.rgEventSubTypesRecordGroup();
		} catch (Exception e) {
			EventMeasures obj = new EventMeasures();
			logger.error("Exception : Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOutcomeReasons LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgOutcomeReasonsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOutcomeReasonsRecordGroup(
			@RequestParam(value = "eventOutcomeDbVal") final String eventOutcomeDbVal) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdprogrService.rgOutcomeReasonsRecordGroup(eventOutcomeDbVal);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocIdRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = ocdprogrService.rgAgyLocIdRecordGroup(caseloadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception in rgAgyLocIdRecordGroup: Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPhases LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgPhasesRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgPhasesRecordGroup(@RequestParam(value = "programId") final Integer programId) {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = ocdprogrService.rgPhasesRecordGroup(programId);
		} catch (Exception e) {
			ProgramServices obj = new ProgramServices();
			logger.error("Exception in rgPhasesRecordGroup: Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgModules LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgModulesRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgModulesRecordGroup(@RequestParam(value = "phaseId") final Integer phaseId) {
		List<ProgramServices> recordList = new ArrayList<>();
		try {
			recordList = ocdprogrService.rgModulesRecordGroup(phaseId);
		} catch (Exception e) {
			ProgramServices obj = new ProgramServices();
			logger.error("Exception in rgModulesRecordGroup: Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgEngagement LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgEngagementRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEngagementRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdprogrService.rgEngagementRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgEngagementRecordGroup: Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgUnderstanding LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgUnderstandingRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgUnderstandingRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdprogrService.rgUnderstandingRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgUnderstandingRecordGroup: Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPsEndAlloc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgPsEndAllocRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsEndAllocRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdprogrService.rgPsEndAllocRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgPsEndAllocRecordGroup: Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFutureAttendance LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgFutureAttendanceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFutureAttendanceRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdprogrService.rgFutureAttendanceRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgFutureAttendanceRecordGroup: Ocdprogr:", e);
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
	@RequestMapping(value = "/ocdprogr/vOffPrgOblExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderPrgObligations> vOffPrgOblExecuteQuery(@RequestBody VOffenderPrgObligations searchBean) {
		List<VOffenderPrgObligations> searchResult = new ArrayList<>();
		try {
			searchResult = ocdprogrService.vOffPrgOblExecuteQuery(searchBean);
		} catch (Exception e) {
			VOffenderPrgObligations bean = new VOffenderPrgObligations();
			logger.error("Exception in vOffPrgOblExecuteQuery:", e);
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
	@RequestMapping(value = "/ocdprogr/vOffPrgOblCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vOffPrgOblCommit(@RequestBody VOffenderPrgObligationsCommitBean commitBean,
			@RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			liReturn = ocdprogrService.vOffPrgOblCommit(commitBean);
			if (1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "32");
			}
		} catch (Exception e) {

			logger.error("Exception in vOffPrgOblCommit: Ocdprogr", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/vAcpProgressExecuteQuery", method = RequestMethod.POST)
	public List<VAcpProgress> vAcpProgressExecuteQuery(@RequestBody VAcpProgress searchBean) {
		List<VAcpProgress> searchResult = new ArrayList<>();
		try {
			searchResult = ocdprogrService.vAcpProgressExecuteQuery(searchBean);
		} catch (Exception e) {
			VAcpProgress bean = new VAcpProgress();
			logger.error("Exception in vAcpProgressExecuteQuery:", e);
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
	@RequestMapping(value = "/ocdprogr/vAcpProgressCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vAcpProgressCommit(@RequestBody VAcpProgressCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdprogrService.vAcpProgressCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception in vAcpProgressCommit: Ocdprogr", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/offPgmProfExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProgramProfiles> offPgmProfExecuteQuery(@RequestBody OffenderProgramProfiles searchBean) {
		List<OffenderProgramProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdprogrService.offPgmProfExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderProgramProfiles bean = new OffenderProgramProfiles();
			logger.error("Exception in offPgmProfExecuteQuery:", e);
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
	@RequestMapping(value = "/ocdprogr/offPgmProfCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderProgramProfiles offPgmProfCommit(
			@RequestBody OffenderProgramProfilesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		OffenderProgramProfiles liReturn = new OffenderProgramProfiles();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdprogrService.offPgmProfCommit(commitBean);
			if (liReturn != null) {
				prosmainService.enableTriggers(commitBean, authorization, "33");
			}
		} catch (Exception e) {

			logger.error("Exception in offPgmProfCommit: Ocdprogr", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/offCrsAppExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCourseAttendance> offCrsAppExecuteQuery(@RequestBody OffenderCourseAttendance searchBean) {
		List<OffenderCourseAttendance> searchResult = new ArrayList<>();
		try {
			searchResult = ocdprogrService.offCrsAppExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in offCrsAppExecuteQuery:", e);
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
	@RequestMapping(value = "/ocdprogr/offCrsAppCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offCrsAppCommit(@RequestBody OffenderCourseAttendancesCommitBean commitBean,
			@RequestHeader HttpHeaders headers) {
		Integer liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdprogrService.offCrsAppCommit(commitBean);
			if (1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "34");
			}
		} catch (Exception e) {

			logger.error("Exception in offCrsAppCommit: Ocdprogr", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/getOffenderDates", method = RequestMethod.GET)
	public @ResponseBody Date getOffenderDates(@RequestParam BigDecimal offenderBookId) {
		Date liReturn = null;
		try {
			liReturn = ocdprogrService.getOffenderDates(offenderBookId);
		} catch (Exception e) {

			logger.error("Exception in getOffenderDates: Ocdprogr", e);
		}
		return liReturn;
	}

	/**
	 * getting rgOffPrgSts LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/rgObligationSource", method = RequestMethod.GET)
	public List<ReferenceCodes> rgObligationSource() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdprogrService.rgObligationSource();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgObligationSource: Ocdprogr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/checkScheduleConflict", method = RequestMethod.POST)
	public @ResponseBody Integer checkScheduleConflict(@RequestBody OffenderCourseAttendance bean) {
		Integer liReturn = 0;
		try {
			liReturn = ocdprogrService.checkScheduleConflict(bean);
		} catch (Exception e) {

			logger.error("Exception in getOffenderDates: Ocdprogr", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/checkUa", method = RequestMethod.POST)
	public @ResponseBody OffenderCourseAttendance checkUa(@RequestBody OffenderCourseAttendance bean) {
		OffenderCourseAttendance liReturn = new OffenderCourseAttendance();
		try {
			liReturn = ocdprogrService.checkUa(bean);
		} catch (Exception e) {

			logger.error("Exception in getOffenderDates: Ocdprogr", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/validAllocationEndDate", method = RequestMethod.POST)
	public @ResponseBody Boolean validAllocationEndDate(@RequestBody OffenderProgramProfiles bean) {
		Boolean liReturn = null;
		try {
			liReturn = ocdprogrService.validAllocationEndDate(bean);
		} catch (Exception e) {

			logger.error("Exception in validAllocationEndDate: Ocdprogr", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/checkAttendanceOutcomes", method = RequestMethod.POST)
	public @ResponseBody Boolean checkAttendanceOutcomes(@RequestBody OffenderProgramProfiles bean) {
		Boolean liReturn = false;
		try {
			liReturn = ocdprogrService.checkAttendanceOutcomes(bean);
		} catch (Exception e) {

			logger.error("Exception in checkAttendanceOutcomes: Ocdprogr", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/checkAllocationExists", method = RequestMethod.POST)
	public @ResponseBody OffenderProgramProfiles checkAllocationExists(@RequestBody OffenderProgramProfiles bean) {
		OffenderProgramProfiles liReturn = new OffenderProgramProfiles();
		try {
			liReturn = ocdprogrService.checkAllocationExists(bean);
		} catch (Exception e) {

			logger.error("Exception in checkAttendanceOutcomes: Ocdprogr", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdprogr/checkNonAssociations", method = RequestMethod.POST)
	public String checkNonAssociations(@RequestBody final OffenderCourseAttendancesCommitBean commitBean) {
		String resultString = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			resultString = ocdprogrService.checkNonAssociations(commitBean);
		} catch (final Exception e) {
			logger.error("Error occured in checkNonAssociations :", e);
		}
		return resultString;
	}
	
	
	@PostMapping("/ocdprogr/checkInstNonAssociation")
	public String checkInstNonAssociations(@RequestBody OffenderProgramProfilesCommitBean commitBean) {
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		return ocdprogrService.checkNonAssociationsforInstWhileAssigning(commitBean);
		}catch(Exception e) {
			logger.error("Exception in checkInstNonAssociations ocduproj:", e);
			return ApplicationConstants.EMPTYDATA;
		}
	}

	
	@PostMapping("/ocdprogr/checkInstNonAssociationsWhileScheduling")
	public String checkInstNonAssociationsWhileScheduling(@RequestBody OffenderCourseAttendancesCommitBean commitBean) {
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		return ocdprogrService.checkNonAssociationsWhileScheduling(commitBean);
		}catch(Exception e) {
			logger.error("Exception in checkInstNonAssociations ocduproj:", e);
			return ApplicationConstants.EMPTYDATA;
		}
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdprogr/updateoffPgmProfCommit", method = RequestMethod.POST)
	public Integer updateOffPgmProfCommit(@RequestBody OffenderProgramProfilesCommitBean commitBean,
			@RequestHeader HttpHeaders headers) {
		Integer liReturn = null;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdprogrService.updateoffPgmProfCommit(commitBean);
			if (liReturn != null) {
				prosmainService.enableTriggers(commitBean, authorization, "33");
			}
		} catch (Exception e) {

			logger.error("Exception in updateoffPgmProfCommit: Ocdprogr", e);
		}
		return liReturn;
	}
	
	/*Moved from Ocusmodu Controller*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdprogr/vAcpSchExecuteQuery", method=RequestMethod.POST)
	public List<VAcpSchedules> vAcpSchExecuteQuery(@RequestBody final VAcpSchedules searchBean) {
		List<VAcpSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = ocusmoduService.vAcpSchExecuteQuery(searchBean);
		} catch (Exception e) {
			final VAcpSchedules bean = new VAcpSchedules();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
}
