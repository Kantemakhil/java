package net.syscon.s4.inst.casemanagement;

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
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidcnoteController
 */
@EliteController
public class OidcnoteController {
	@Autowired
	private OidcnoteService oidcnoteService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcnoteController.class.getName());

	/**
	 * getting rgnoteSource LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcnote/rgnoteSourceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgnoteSourceRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidcnoteService.rgnoteSourceRecordGroup();
		} catch (Exception e) {
			logger.error("rgnoteSourceRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgCasenoteType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcnote/rgCasenoteTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCasenoteTypeRecordGroup(
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oidcnoteService.rgCasenoteTypeRecordGroup(caseloadType, userName);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCasenoteSubtype LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcnote/rgCasenoteSubtypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCasenoteSubtypeRecordGroup(@RequestParam(value = "caseloadType") final String caseloadType,
			@RequestParam(value = "caseNoteType") final String caseNoteType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oidcnoteService.rgCasenoteSubtypeRecordGroup(caseNoteType, userName,caseloadType);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffname LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcnote/rgStaffnameRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffnameRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oidcnoteService.rgStaffnameRecordGroup(userName);
		} catch (Exception e) {
			logger.error("rgStaffnameRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean {@link OffenderCaseNotes}
	 * @return a list of the OffenderCaseNotes {@link OffenderCaseNotes} for the
	 *         matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcnote/offNotesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCaseNotes> offNotesExecuteQuery(final @RequestBody OffenderCaseNotes searchBean) {
		List<OffenderCaseNotes> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oidcnoteService.offNotesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offNotesExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert, delete, update int the
	 * database table
	 * 
	 * @param commitBean {@link OffenderCaseNotesCommitBean}
	 * @return the success/failure {@link Integer} of the insert/update operations
	 *         for the matching OffenderCaseNotesCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcnote/offNotesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offNotesCommit(final @RequestBody OffenderCaseNotesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidcnoteService.offNotesCommit(commitBean);
			if (1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "126");
			}
		} catch (Exception e) {

			logger.error("offNotesCommit", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcnote/checkCasenoteSubType", method = RequestMethod.GET)
	public @ResponseBody String checkCasenoteSubType(@RequestParam(value = "caseNoteType") final String caseNoteType, 
			@RequestParam(value = "caseNoteSubType") final String caseNoteCode) {
		String subTypeFlag = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			subTypeFlag = oidcnoteService.checkCasenoteSubType(caseNoteType, caseNoteCode, userName);
		} catch (Exception e) {

			logger.error("offNotesCommit", e);
		}
		return subTypeFlag;
	}

}
