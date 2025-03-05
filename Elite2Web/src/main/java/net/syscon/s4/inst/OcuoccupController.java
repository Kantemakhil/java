package net.syscon.s4.inst;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.movementexternal.beans.RpOtherOccupants;
import net.syscon.s4.inst.movementexternal.beans.RpOtherOccupantsCommitBean;

/**
 * class OcuoccupController
 */
@EliteController

public class OcuoccupController {
	@Autowired
	private OcuoccupService ocuoccupService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoccupController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuoccup/rpOtherOccupantsExecuteQuery", method = RequestMethod.POST)
	public List<RpOtherOccupants> rpOtherOccupantsExecuteQuery(@RequestBody final RpOtherOccupants searchBean) {
		List<RpOtherOccupants> searchResult = new ArrayList<>();
		try {
			searchResult = ocuoccupService.rpOtherOccupantsExecuteQuery(searchBean);
		} catch (Exception e) {
			final RpOtherOccupants bean = new RpOtherOccupants();
			logger.error("rpOtherOccupantsExecuteQuery: ", e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuoccup/rpOtherOccupantsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer rpOtherOccupantsCommit(@RequestBody final RpOtherOccupantsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuoccupService.rpOtherOccupantsCommit(commitBean);
		} catch (Exception e) {
			logger.error("rpOtherOccupantsCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * getting rgContacted LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuoccup/rgContactedRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgContactedRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocuoccupService.rgContactedRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgContactedRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPersonName LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocuoccup/rgPersonNameRecordGroup", method = RequestMethod.GET)
	public List<OffenderContactPersons> rgPersonNameRecordGroup(
			@RequestParam(value = "offenderBookId") final String offenderBookId) {
		List<OffenderContactPersons> recordList = new ArrayList<OffenderContactPersons>();
		try {
			recordList = ocuoccupService.rgPersonNameRecordGroup(offenderBookId);
		} catch (Exception e) {
			final OffenderContactPersons obj = new OffenderContactPersons();
			logger.error("rgPersonNameRecordGroup: ", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgContactTypes LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocuoccup/rgContactTypesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgContactTypesRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocuoccupService.rgContactTypesRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgContactTypesRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgRelationships LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocuoccup/rgRelationshipsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRelationshipsRecordGroup(
			@RequestParam(value = "contactCode") final String contactCode) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocuoccupService.rgRelationshipsRecordGroup(contactCode);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgRelationshipsRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}