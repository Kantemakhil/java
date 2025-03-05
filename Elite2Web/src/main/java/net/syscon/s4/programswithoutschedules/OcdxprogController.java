package net.syscon.s4.programswithoutschedules;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderPrgObligationsCommitBean;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.programswithoutschedules.OcdpnoteService;
import net.syscon.s4.inst.programswithoutschedules.OcdxprogService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OcdxprogController {
	@Autowired
	private OcdxprogService ocdxprogService;
	@Autowired
	private OcdpnoteService ocdpnoteService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdxprogController.class.getName());

	/**
	 * getting rgAvailabilityCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/rgAvailabilityCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAvailabilityCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdxprogService.rgAvailabilityCodeRecordGroup();
		} catch (Exception e) {
			logger.error("rgAvailabilityCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgProgram LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/rgProgramRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgProgramRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = ocdxprogService.rgProgramRecordGroup();
		} catch (Exception e) {
			logger.error("rgProgramRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgEndReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/rgEndReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEndReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdxprogService.rgEndReasonRecordGroup();
		} catch (Exception e) {
			logger.error("rgEndReasonRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/offPrgObligationsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPrgObligations> offPrgObligationsExecuteQuery(@RequestBody OffenderPrgObligations searchBean) {
		List<OffenderPrgObligations> searchResult = new ArrayList<>();
		try {
			searchResult = ocdxprogService.offPrgObligationsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offPrgObligationsExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdxprog/offPrgObligationsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offPrgObligationsCommit(@RequestBody OffenderPrgObligationsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdxprogService.offPrgObligationsCommit(commitBean);
			if(0 != liReturn ) {
				prosmainService.enableTriggers(commitBean, authorization, "93");
			}
		} catch (Exception e) {
			logger.error("offPrgObligationsCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/offProgramProfilesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProgramProfiles> offProgramProfilesExecuteQuery(
			@RequestBody OffenderProgramProfiles searchBean) {
		List<OffenderProgramProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdxprogService.offProgramProfilesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offProgramProfilesExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdxprog/offProgramProfilesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offProgramProfilesCommit(@RequestBody OffenderProgramProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdxprogService.offProgramProfilesCommit(commitBean);
		} catch (Exception e) {
			logger.error("offProgramProfilesCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgProgram LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/currentCaseloadType", method = RequestMethod.GET)
	public String currentCaseloadType(@RequestParam("caseloadId") final String caseloadId) {
		String recordList = null;
		try {
			recordList = ocdxprogService.currentCaseloadType(caseloadId);
		} catch (Exception e) {
			logger.error("currentCaseloadType", e);
		}
		return recordList;
	}

	/**
	 * getting rgProgram LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/offProgramPrflesUpdatePrgStatus", method = RequestMethod.GET)
	public Integer offProgramPrflesUpdatePrgStatus(
			@RequestParam("offenderPrgObligationId") final Long offenderPrgObligationId,
			@RequestParam("offenderBookId") final Long offenderBookId) {
		Integer recordList = null;
		try {
			recordList = ocdxprogService.offProgramPrflesUpdatePrgStatus(offenderPrgObligationId, offenderBookId);
		} catch (Exception e) {
			logger.error("currentCaseloadType", e);
		}
		return recordList;
	}
	
	/**
	 * getting rgProgram LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/checkPrivilegeExists", method = RequestMethod.GET)
	public int checkPrivilegeExists() {
		int result = 0;
		try {
			result = ocdxprogService.checkPrivilegeExists();
		} catch (Exception e) {
			logger.error("currentCaseloadType", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/rgOffenderSentencesRecordGroupComm", method = RequestMethod.GET)
	public List<OffenderSentences> rgOffenderSentencesRecordGroup(
			@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		List<OffenderSentences> recordList = new ArrayList<>();
		try {
			recordList = ocdxprogService.rgOffenderSentencesRecordGroup(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception : Ocdprogr:", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/checkNonAssociations", method = RequestMethod.POST)
	public String checkNonAssociations(@RequestBody final OffenderProgramProfilesCommitBean commitBean) {
		String resultString = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			resultString = ocdxprogService.checkNonAssociations(commitBean);
		} catch (final Exception e) {
			logger.error("Error occured in checkNonAssociations :", e);
		}
		return resultString;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/rgOffenderSentencesRecordGroupBothCustAndNonCust", method = RequestMethod.GET)
	public List<OffenderSentences> rgOffenderSentencesRecordGroupBothCustAndNonCust(
			@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		List<OffenderSentences> recordList = new ArrayList<>();
		try {
			recordList = ocdxprogService.rgOffenderSentencesRecordGroupBothCustAndNonCust(offenderBookId);
		} catch (Exception e) {
			logger.error("rgOffenderSentencesRecordGroupBothCustAndNonCust : Ocdprogr:", e);
		}
		return recordList;
	}
	
	/**
	 * getting rgSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/rgSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSubTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpnoteService.rgSubTypeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
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
	@RequestMapping(value = "/ocdxprog/offenderCaseNotesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCaseNotes> offenderCaseNotesExecuteQuery(@RequestBody final OffenderCaseNotes searchBean) {
		List<OffenderCaseNotes> searchResult = new ArrayList<>();
		try {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			searchResult = ocdpnoteService.offenderCaseNotesExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderCaseNotes bean = new OffenderCaseNotes();
			logger.error(e);
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
	@RequestMapping(value = "/ocdxprog/offenderCaseNotesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offenderCaseNotesCommit(@RequestBody final OffenderCaseNotesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdpnoteService.offenderCaseNotesCommit(commitBean);
		} catch (final Exception e) {

			logger.error("offenderCaseNotesCommit ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/ocdpnoteGlobalUserAndCaseloadtype", method = RequestMethod.GET)
	public List<ReferenceCodes> ocdpnoteGlobalUserAndCaseloadtype() {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = ocdpnoteService.ocdpnoteGlobalUserAndCaseloadtype(userName);
		} catch (final Exception e) {
			final ReferenceCodes bean = new ReferenceCodes();
			logger.error("ocdpnoteGlobalUserAndCaseloadtype", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/ocdpnoteStaffMemberName", method = RequestMethod.GET)
	public List<ReferenceCodes> ocdpnoteStaffMemberName() {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpnoteService.ocdpnoteStaffMemberName();
		} catch (final Exception e) {
			final ReferenceCodes bean = new ReferenceCodes();
			logger.error("ocdpnoteStaffMemberName", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdxprog/getModuleName", method = RequestMethod.POST)
	public OffenderCaseNotes getModuleName(@RequestBody final OffenderCaseNotes searchBean) {
		OffenderCaseNotes searchResult = new OffenderCaseNotes();
		try {
			searchResult = ocdpnoteService.getModuleName(searchBean);
		} catch (final Exception e) {
			logger.error(e);
			searchResult.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

}