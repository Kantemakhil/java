package net.syscon.s4.inst.casemanagement;

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
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OcucnameController
 */
@EliteController
public class OcucnameController {
	@Autowired
	private OcucnameService ocucnameService;

	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcucnameController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucname/offCaseNoteExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCaseNotes> offCaseNoteExecuteQuery(@RequestBody final OffenderCaseNotes searchBean) {
		List<OffenderCaseNotes> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocucnameService.offCaseNoteExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderCaseNotes bean = new OffenderCaseNotes();
			logger.error("In method offCaseNoteExecuteQuery", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucname/offCaseNoteCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offCaseNoteCommit(final @RequestBody OffenderCaseNotesCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocucnameService.offCaseNoteCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method offCaseNoteCommit", e);
		}
		return liReturn;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OCUCNAME");
	}
}