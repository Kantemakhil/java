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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OffCaseNoteRecipients;
import net.syscon.s4.im.beans.OffCaseNoteRecipientsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OcunotcmController
 */
@EliteController
public class OcunotcmController {
	@Autowired
	private OcunotcmService ocunotcmService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcunotcmController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OffCaseNoteRecipients}
	 * @return a list of the OffCaseNoteRecipients {@link OffCaseNoteRecipients} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocunotcm/offCaseNrExecuteQuery", method = RequestMethod.POST)
	public List<OffCaseNoteRecipients> offCaseNrExecuteQuery(@RequestBody final OffCaseNoteRecipients searchBean) {
		List<OffCaseNoteRecipients> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocunotcmService.offCaseNrExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffCaseNoteRecipients bean = new OffCaseNoteRecipients();
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
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocunotcm/offCaseNrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offCaseNrCommit(@RequestBody final OffCaseNoteRecipientsCommitBean commitBean) {
		int liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocunotcmService.offCaseNrCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * getting rgStaffDtls LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocunotcm/rgStaffDtlsRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffDtlsRecordGroup(@RequestParam (value="teamIdDesc") final String teamIdDesc) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = ocunotcmService.rgStaffDtlsRecordGroup(teamIdDesc);
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUNOTCM");
	}
}