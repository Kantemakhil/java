package net.syscon.s4.programswithoutschedules;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.inst.programswithoutschedules.OcdpnoteService;
/**
 * OcdpnoteController
 */
@EliteController
public class OcdpnoteController {
	@Autowired
	private OcdpnoteService ocdpnoteService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdpnoteController.class.getName());

	/**
	 * getting rgSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpnote/rgSubTypeRecordGroup", method = RequestMethod.GET)
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
	@RequestMapping(value = "/ocdpnote/offenderCaseNotesExecuteQuery", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ocdpnote/offenderCaseNotesCommit", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ocdpnote/ocdpnoteGlobalUserAndCaseloadtype", method = RequestMethod.GET)
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
	@RequestMapping(value = "/ocdpnote/ocdpnoteStaffMemberName", method = RequestMethod.GET)
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
	@RequestMapping(value = "/ocdpnote/getModuleName", method = RequestMethod.POST)
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