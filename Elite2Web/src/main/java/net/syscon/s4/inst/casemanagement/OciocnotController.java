package net.syscon.s4.inst.casemanagement;

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
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.casemanagement.beans.VOffenderCaseNotes;

/**
 * Class OciocnotController
 */
@EliteController
public class OciocnotController {
	@Autowired
	private OciocnotService ociocnotService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OciocnotController.class.getName());

	/**
	 * getting rgType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociocnot/rgTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			 recordList = ociocnotService.rgTypeRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting rgSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociocnot/rgSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSubTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociocnotService.rgSubTypeRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting rgStaffName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociocnot/rgStaffNameRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffNameRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ociocnotService.rgStaffNameRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting rgLocation LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociocnot/rgLocationRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLocationRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();// ReferenceCodes
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ociocnotService.rgLocationRecordGroup(caseLoadId, userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean
	 *            {@link VOffenderCaseNotes}
	 * @return a list of the VOffenderCaseNotes {@link VOffenderCaseNotes} for the
	 *         matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociocnot/caseNoteExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderCaseNotes> caseNoteExecuteQuery(@RequestBody final VOffenderCaseNotes searchBean) {
		List<VOffenderCaseNotes> searchResult = new ArrayList<>();
		try {
			searchResult = ociocnotService.caseNoteExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * This method is used to get staffId from Staff Members
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociocnot/toGetStaffId", method = RequestMethod.GET)
	public Integer toGetStaffId() {
		Integer searchResult = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchResult = ociocnotService.toGetStaffId(userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * This method is used to get staffId from Staff Members
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociocnot/toGetFirstAndLastName", method = RequestMethod.GET)
	public StaffMembers toGetFirstAndLastName(@RequestParam(value = "staffId") final Integer staffId) {
		StaffMembers searchResult = new StaffMembers();
		try {
			searchResult = ociocnotService.toGetFirstAndLastName(staffId);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * This method is used to get staffId from Staff Members
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociocnot/checkPrevExists", method = RequestMethod.GET)
	public Integer checkPrevExists() {
		Integer lireturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

		try {
			lireturn = ociocnotService.checkPrevExists(userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return lireturn;
	}

}