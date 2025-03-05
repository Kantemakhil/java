package net.syscon.s4.inst.securitythreatgroups;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.StgCaseNotes;
import net.syscon.s4.im.beans.StgCaseNotesCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * OidstgcnController
 * 
 */
@EliteController
public class OidstgcnController {
	@Autowired
	private OidstgcnService oidstgcnService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstgcnController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgcn/stgCaseNotesExecuteQuery", method = RequestMethod.POST)
	public List<StgCaseNotes> stgCaseNotesExecuteQuery(final @RequestBody StgCaseNotes searchBean) {
		List<StgCaseNotes> searchResult = new ArrayList<StgCaseNotes>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstgcnService.stgCaseNotesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgcn/stgCaseNotesCommit", method = RequestMethod.POST)
	public Integer stgCaseNotesCommit(final @RequestBody StgCaseNotesCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oidstgcnService.stgCaseNotesCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgNoteType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgcn/rgNoteTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgNoteTypeRecordGroup() {
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstgcnService.rgNoteTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgNoteReason LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgcn/rgNoteReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgNoteReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgcnService.rgNoteReasonRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgNoteReason parentValue
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgcn/getParentCodes", method = RequestMethod.GET)
	public List<String> getParentCodes() {
		List<String> recordList = new ArrayList<String>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgcnService.getParentCodes();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIDSTGCN");
	}
}