package net.syscon.s4.iwp;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.offenderissuestracking.beans.VStaffLocationRoles;

/**
 * OcuwarniController
 */
@EliteController
public class OcuwarniController {
	@Autowired
	private OcuwarniService ocuwarniService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuwarniController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuwarni/offCaseNotesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCaseNotes> offCaseNotesExecuteQuery(@RequestBody final OffenderCaseNotes searchBean) {
		List<OffenderCaseNotes> searchResult = new ArrayList<>();
		try {
			searchResult = ocuwarniService.offCaseNotesExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderCaseNotes bean = new OffenderCaseNotes();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting rgConSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuwarni/rgConSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgConSubTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocuwarniService.rgConSubTypeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuwarni/rgStaffNameRecordGroup", method = RequestMethod.GET)
	public List<VStaffLocationRoles> rgStaffNameRecordGroup(
			@RequestParam(value = "offenderBookId") final Long offenderBookId,
			@RequestParam(value = "caseloadId") final String caseLoadId,
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<VStaffLocationRoles> recordList = new ArrayList<>();
		try {
			recordList = ocuwarniService.rgStaffNameRecordGroup(offenderBookId, caseLoadId, agyLocId);
		} catch (final Exception e) {
			final VStaffLocationRoles obj = new VStaffLocationRoles();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}